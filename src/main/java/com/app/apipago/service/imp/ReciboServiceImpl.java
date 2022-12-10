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
import com.app.apipago.modelo.Recibo;
import com.app.apipago.modelo.Usuario;
import com.app.apipago.repositorio.PagoRepositorio;
import com.app.apipago.repositorio.PlanesRepositorio;
import com.app.apipago.repositorio.ReciboRepositorio;
import com.app.apipago.service.PagoService;
import com.app.apipago.service.ReciboService;
import com.app.apipago.util.Constantes;
import com.fasterxml.jackson.core.JsonProcessingException;

@Component
public class ReciboServiceImpl implements ReciboService {
	private static final Logger logger = LoggerFactory.getLogger(ReciboServiceImpl.class);

	@Autowired
	private ReciboRepositorio reciboRepositorio;
//	@Autowired
//	private PlanesRepositorio planesRepositorio;
	@Autowired
	private UsuarioWs usuarioWs;
	
	@Override
	public ResponseEntity<?> realizarRecibo(Recibo recibo) throws JsonProcessingException {

		Respuesta respuesta = new Respuesta();
		boolean existeMedioPago = false;
//		boolean activo = false;
		String msj ="[ACTIVIDAD][realizarRecibo]";
		try {
			logger.info(Constantes.MENSAJE2,msj, "[obtenerUsuarioPorId][INICIO]");
			Usuario usuario = usuarioWs.obtenerUsuarioPorId(recibo.getUserId());
			String usr = Constantes.printPrettyJSONString(usuario);
			logger.info(Constantes.MENSAJE3, msj,"[obtenerUsuarioPorId]", usr);
			logger.info(Constantes.MENSAJE2,msj,"[obtenerUsuarioPorId][FIN]");
			if (usr.contains("\"id\" : null")) {
				throw new IDFException("1", "El usuario no existe");
			}else {
//				existeMedioPago = existeMedioPago(existeMedioPago,usuario,pago,pago.getUserId(),msj);
				existeMedioPago = true;
			}
			
			if (existeMedioPago) {
				Recibo newRecibo = reciboRepositorio.save(recibo);
				String usrs = Constantes.printPrettyJSONString(newRecibo);
				logger.info(Constantes.MENSAJE2, msj+"[new] ", usrs);
				
				return ResponseEntity.status(HttpStatus.OK).body(newRecibo);
			} else {
				throw new IDFException("2", "El recibo ya se realizo.");
			}
		} catch (IDFException idf) {
			respuesta.setCodigo(idf.getCode());
			respuesta.setMensaje(idf.getMessage());
			return ResponseEntity.status(HttpStatus.OK).body(respuesta);
		} catch (Exception e) {
			respuesta.setCodigo("-3");
			respuesta.setMensaje(e.getMessage());
			String obj = Constantes.printPrettyJSONString(respuesta);
			logger.info(Constantes.MENSAJE2, "[realizarRecibo] ", obj);
			return ResponseEntity.status(HttpStatus.OK).body(respuesta);
		}
	}

//	private boolean existeMedioPago(boolean existeMedioPago, Usuario usuario, Pago pago, Long id, String msj) throws IDFException, WSException {
//		logger.info(Constantes.MENSAJE2, msj,"[existeMedioPago][INICIO]");
//		if (usuario.getMedioPago().isEmpty()) {
//			if (pago.getMedioPago() == null) {
//				throw new IDFException("3", "Favor de Agregar un Medio de Pago al usuario");
//			}else {
//				logger.info(Constantes.MENSAJE2, msj,"[actualizarUsuarioPorID][INICIO]");
//
//				usuario.getMedioPago().add(pago.getMedioPago());
//				usuarioWs.actualizarUsuarioPorID(id, usuario);
//				logger.info(Constantes.MENSAJE2, msj,"[actualizarUsuarioPorID][FIN]");
//				existeMedioPago = true;
//			}
//		}else {
//			for (MedioPago medioPago : usuario.getMedioPago()) {
//				if (medioPago.getId().equals(pago.getMedioPago().getId())) {
//					logger.info(Constantes.MENSAJE3,  msj,"[existeMedioPago] "+pago.getMedioPago().getId());
//					existeMedioPago = true;
//				}
//			}
//		}
//		logger.info(Constantes.MENSAJE3,  msj, "[existeMedioPago] ",existeMedioPago);
//		logger.info(Constantes.MENSAJE2,  msj, "[existeMedioPago][FIN]");
//		return existeMedioPago;
//	}

//	private boolean existePago(Pago pago, boolean existeMedioPago, boolean activo) throws JsonProcessingException {
//		if(existeMedioPago) {
//			String pag = Constantes.printPrettyJSONString(pago);
//			logger.info(Constantes.MENSAJE2, "[realizarPago] ", pag);
//			activo = pagoRepositorio.findById(pago.getId()).isPresent();
//			logger.info(Constantes.MENSAJE2, "[realizarPago][activo] ", activo);
//		}else {
//			String pag = Constantes.printPrettyJSONString(pago);
//			logger.info(Constantes.MENSAJE2, "[realizarPago] ", pag);
//			activo = pagoRepositorio.findById(pago.getId()).isPresent();
//			logger.info(Constantes.MENSAJE2, "[realizarPago][activo] ", activo);
//		}
//		return activo;
//	}

//	@Override
//	public ResponseEntity<Respuesta> mayorIndicePago(Respuesta respuesta) {
//
//		Integer aa = pagoRepositorio.verficarPagos();
//		respuesta.setCodigo(String.valueOf(aa));
//		logger.info(Constantes.MENSAJE2, "[mayorIndicePago][aa] ", aa);
//		return ResponseEntity.ok(respuesta);
//	}

//	@Override
//	public ResponseEntity<Respuesta> mayorIndicePlanes(Respuesta respuesta) {
//
//		Integer aa = planesRepositorio.verficarPlanes();
//		respuesta.setCodigo(String.valueOf(aa));
//		logger.info(Constantes.MENSAJE2, "[mayorIndicePlanes][aa] ", aa);
//		return ResponseEntity.ok(respuesta);
//	}
	
	@Override
	public Recibo findByUserId(String userId) throws JsonProcessingException {
		Recibo recibo = new Recibo();
		Respuesta respuesta = new Respuesta();
		try {
			if (userId.isEmpty()) {
				throw new IDFException("1", "Los campos ingresados son nulos o vacios");
			} else {

				logger.info(Constantes.MENSAJE2, "[findByUserId][user] ", userId);

				recibo = reciboRepositorio.findByUserId(userId);

				if (recibo != null) {
					respuesta.setCodigo("0");
					respuesta.setMensaje("Proceso Exitoso");
					String obj = Constantes.printPrettyJSONString(respuesta);
					logger.info(Constantes.MENSAJE2, "[findByUserId] ", obj);
				} else {
					throw new IDFException("1", "Los campos ingresados no son los correctos");
				}
			}
		} catch (IDFException idf) {
			respuesta.setCodigo(idf.getCode());
			respuesta.setMensaje(idf.getMessage());
//			return ResponseEntity.status(HttpStatus.OK).body(respuesta);
		} catch (Exception e) {
			respuesta.setCodigo("-3");
			respuesta.setMensaje(e.getMessage());
//			return ResponseEntity.status(HttpStatus.OK).body(respuesta);
		}
		return recibo;
	}
}