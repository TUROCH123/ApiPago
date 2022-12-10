package com.app.apipago.service.imp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import com.app.apipago.entity.Respuesta;
import com.app.apipago.excepciones.IDFException;
import com.app.apipago.integration.UsuarioWs;
import com.app.apipago.modelo.Recibo;
import com.app.apipago.modelo.Usuario;
import com.app.apipago.repositorio.ReciboRepositorio;
import com.app.apipago.service.ReciboService;
import com.app.apipago.util.Constantes;
import com.fasterxml.jackson.core.JsonProcessingException;

@Component
public class ReciboServiceImpl implements ReciboService {
	private static final Logger logger = LoggerFactory.getLogger(ReciboServiceImpl.class);

	@Autowired
	private ReciboRepositorio reciboRepositorio;

	@Autowired
	private UsuarioWs usuarioWs;
	
	@Override
	public ResponseEntity<?> realizarRecibo(Recibo recibo) throws JsonProcessingException {

		Respuesta respuesta = new Respuesta();
		boolean existeMedioPago = false;
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
		} catch (Exception e) {
			respuesta.setCodigo("-3");
			respuesta.setMensaje(e.getMessage());
		}
		return recibo;
	}
}