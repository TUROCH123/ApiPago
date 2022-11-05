package com.app.apipago.service.imp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import com.app.apipago.entity.Respuesta;
import com.app.apipago.excepciones.IDFException;
import com.app.apipago.excepciones.WSException;
import com.app.apipago.integration.UsuarioWs;
import com.app.apipago.modelo.MedioPago;
import com.app.apipago.modelo.Pago;
import com.app.apipago.modelo.Usuario;
import com.app.apipago.repositorio.PagoRepositorio;
import com.app.apipago.repositorio.PlanesRepositorio;
import com.app.apipago.service.PagoService;
import com.app.apipago.util.Constantes;
import com.fasterxml.jackson.core.JsonProcessingException;

@Component
public class PagoServiceImpl implements PagoService {
	private static final Logger logger = LoggerFactory.getLogger(PagoServiceImpl.class);

	@Autowired
	private PagoRepositorio pagoRepositorio;
	@Autowired
	private PlanesRepositorio planesRepositorio;
	@Autowired
	private UsuarioWs usuarioWs;
	
	@Override
	public ResponseEntity<?> realizarPago(Pago pago) throws JsonProcessingException {

		Respuesta respuesta = new Respuesta();
		boolean existeMedioPago = false;
		boolean activo = false;
		String msj ="[ACTIVIDAD][realizarPago]";
		try {
			logger.info(Constantes.MENSAJE2,msj, "[obtenerUsuarioPorId][INICIO]");
			Usuario usuario = usuarioWs.obtenerUsuarioPorId(pago.getUserId());
			String usr = Constantes.printPrettyJSONString(usuario);
			logger.info(Constantes.MENSAJE3, msj,"[obtenerUsuarioPorId]", usr);
			logger.info(Constantes.MENSAJE2,msj,"[obtenerUsuarioPorId][FIN]");
			if (usr.contains("\"id\" : null")) {
				throw new IDFException("1", "El usuario no existe");
			}else {
				existeMedioPago = existeMedioPago(existeMedioPago,usuario,pago,pago.getUserId(),msj);
			}
			
//			activo = existePago(pago,existeMedioPago,activo);
			
			if (!activo) {
				logger.info(Constantes.MENSAJE2,msj, "[obtenerPlanesPorId][INICIO]");

				logger.info(Constantes.MENSAJE2,msj, "[obtenerPlanesPorId][FIN]");
				Pago newPago= pagoRepositorio.save(pago);
				String usrs = Constantes.printPrettyJSONString(newPago);
				logger.info(Constantes.MENSAJE2, "[realizarPago][new] ", usrs);
				
				return ResponseEntity.status(HttpStatus.OK).body(newPago);
			} else {
				throw new IDFException("2", "El pago ya se realizo.");
			}
		} catch (IDFException idf) {
			respuesta.setCodigo(idf.getCode());
			respuesta.setMensaje(idf.getMessage());
			return ResponseEntity.status(HttpStatus.OK).body(respuesta);
		} catch (Exception e) {
			respuesta.setCodigo("-3");
			respuesta.setMensaje(e.getMessage());
			String obj = Constantes.printPrettyJSONString(respuesta);
			logger.info(Constantes.MENSAJE2, "[realizarPago] ", obj);
			return ResponseEntity.status(HttpStatus.OK).body(respuesta);
		}
	}

	private boolean existeMedioPago(boolean existeMedioPago, Usuario usuario, Pago pago, Long id, String msj) throws IDFException, WSException {
		logger.info(Constantes.MENSAJE2, msj,"[existeMedioPago][INICIO]");
		if (usuario.getMedioPago().isEmpty()) {
			if (pago.getMedioPago() == null) {
				throw new IDFException("3", "Favor de Agregar un Medio de Pago al usuario");
			}else {
				logger.info(Constantes.MENSAJE2, msj,"[actualizarUsuarioPorID][INICIO]");

				usuario.getMedioPago().add(pago.getMedioPago());
				usuarioWs.actualizarUsuarioPorID(id, usuario);
				logger.info(Constantes.MENSAJE2, msj,"[actualizarUsuarioPorID][FIN]");
				existeMedioPago = true;
			}
		}else {
			for (MedioPago medioPago : usuario.getMedioPago()) {
				if (medioPago.getId().equals(pago.getMedioPago().getId())) {
					logger.info(Constantes.MENSAJE3,  msj,"[existeMedioPago] "+pago.getMedioPago().getId());
					existeMedioPago = true;
				}
			}
		}
		logger.info(Constantes.MENSAJE3,  msj, "[existeMedioPago] ",existeMedioPago);
		logger.info(Constantes.MENSAJE2,  msj, "[existeMedioPago][FIN]");
		return existeMedioPago;
	}

	private boolean existePago(Pago pago, boolean existeMedioPago, boolean activo) throws JsonProcessingException {
		if(existeMedioPago) {
			String pag = Constantes.printPrettyJSONString(pago);
			logger.info(Constantes.MENSAJE2, "[realizarPago] ", pag);
			activo = pagoRepositorio.findById(pago.getId()).isPresent();
			logger.info(Constantes.MENSAJE2, "[realizarPago][activo] ", activo);
		}else {
			String pag = Constantes.printPrettyJSONString(pago);
			logger.info(Constantes.MENSAJE2, "[realizarPago] ", pag);
			activo = pagoRepositorio.findById(pago.getId()).isPresent();
			logger.info(Constantes.MENSAJE2, "[realizarPago][activo] ", activo);
		}
		return activo;
	}

	@Override
	public ResponseEntity<Respuesta> mayorIndicePago(Respuesta respuesta) {

		Integer aa = pagoRepositorio.verficarPagos();
		respuesta.setCodigo(String.valueOf(aa));
		logger.info(Constantes.MENSAJE2, "[mayorIndicePago][aa] ", aa);
		return ResponseEntity.ok(respuesta);
	}

	@Override
	public ResponseEntity<Respuesta> mayorIndicePlanes(Respuesta respuesta) {

		Integer aa = planesRepositorio.verficarPlanes();
		respuesta.setCodigo(String.valueOf(aa));
		logger.info(Constantes.MENSAJE2, "[mayorIndicePlanes][aa] ", aa);
		return ResponseEntity.ok(respuesta);
	}
}