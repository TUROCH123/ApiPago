package com.app.apipago.service.imp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.app.apipago.entity.Respuesta;
import com.app.apipago.entity.UsuarioResponse;
import com.app.apipago.excepciones.IDFException;
import com.app.apipago.excepciones.ResourceNotFoundException;
import com.app.apipago.modelo.Usuario;
import com.app.apipago.repositorio.UsuarioRepositorio;
import com.app.apipago.service.ApiLoginService;
import com.app.apipago.util.Constantes;
//import com.app.apilogin.entity.Respuesta;
//import com.app.apilogin.entity.UsuarioResponse;
//import com.app.apilogin.excepciones.IDFException;
//import com.app.apilogin.excepciones.ResourceNotFoundException;
//import com.app.apilogin.modelo.Usuario;
//import com.app.apilogin.repositorio.UsuarioRepositorio;
//import com.app.apilogin.service.ApiLoginService;
//import com.app.apilogin.util.Constantes;
import com.fasterxml.jackson.core.JsonProcessingException;

@Component
public class ApiLoginServiceImpl implements ApiLoginService {
	private static final Logger logger = LoggerFactory.getLogger(ApiLoginServiceImpl.class);

	@Autowired
	private UsuarioRepositorio usuarioRepositorio;

	@SuppressWarnings("finally")
	@Override
	public ResponseEntity<UsuarioResponse> validarDatos(String email, String pass) throws JsonProcessingException {
		UsuarioResponse response = new UsuarioResponse();
		Respuesta respuesta = new Respuesta();
		Usuario usuario = new Usuario();
		try {
			if (email.isEmpty() || pass.isEmpty()) {
				throw new IDFException("1", "Los campos ingresados son nulos o vacios");
			} else {

				logger.info(Constantes.MENSAJE2, "[validarDatos][user] ", email);
				logger.info(Constantes.MENSAJE2, "[validarDatos][pass] ", pass);

				usuario = usuarioRepositorio.validarDatos(email, pass);

				if (usuario != null) {
					respuesta.setCodigo("0");
					respuesta.setMensaje("Proceso Exitoso");
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
		}finally {
			response.setRespuesta(respuesta);
			response.setUsuario(usuario);
			String obj = Constantes.printPrettyJSONString(response);
			logger.info(Constantes.MENSAJE2, "[guardarUsuario] ", obj);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}
	}

	@Override
	public ResponseEntity<?> actualizarUsuarioPorID(Long id, Usuario usuarioAct) throws JsonProcessingException {

		String obj = Constantes.printPrettyJSONString(usuarioAct);
		logger.info(Constantes.MENSAJE2, "[actualizarUsuarioPorID] ", obj);
		logger.info(Constantes.MENSAJE2, "[actualizarUsuarioPorID][service][id] ", id);
		Respuesta respuesta = new Respuesta();
		Usuario usuarioActualizado = new Usuario();
		try {
			Usuario usuario = usuarioRepositorio.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("No exite el Usuario"));
			String obSj = Constantes.printPrettyJSONString(usuario);
			logger.info(Constantes.MENSAJE2, "[actualizarUsuarioPorID][service][usuario] ", obSj);
			if (usuario != null) {
				usuario.setNombre(usuarioAct.getNombre());
				usuario.setApellido(usuarioAct.getApellido());
				usuario.setEmail(usuarioAct.getEmail());
//				usuario.setCelular(usuarioAct.getCelular());
				usuario.setPassword(usuarioAct.getPassword());
				usuario.setFechaInscripcion(usuarioAct.getFechaInscripcion());
				usuario.setFechaVencimiento(usuarioAct.getFechaVencimiento());

//				usuario.getPerfiles().clear();

//				if (null == usuarioAct.getPerfiles() ||usuarioAct.getPerfiles().isEmpty()) {
					logger.info(Constantes.MENSAJE2, "[actualizarUsuarioPorID][service][usuario] ", "PERFILESnulosvacios");
//				}else {
//					usuario.getPerfiles().addAll(usuarioAct.getPerfiles());
//				}

				usuario.getMedioPago().clear();

				usuario.getMedioPago().addAll(usuarioAct.getMedioPago()==null?null:usuarioAct.getMedioPago());
				
				String objaa = Constantes.printPrettyJSONString(usuario);
				logger.info(Constantes.MENSAJE2, "[actualizarUsuarioPorID][usuario][id3] ", objaa);
				usuario.setRoles(usuarioAct.getRoles() ==null?null:usuarioAct.getRoles());
				usuario.setPlanes(usuarioAct.getPlanes() ==null?null:usuarioAct.getPlanes());
				usuarioActualizado = usuarioRepositorio.save(usuario);
				return ResponseEntity.status(HttpStatus.OK).body(usuarioActualizado);
			} else {
				throw new IDFException("1", "No exite el Usuario");
			}
		} catch (IDFException idf) {
			respuesta.setCodigo(idf.getCode());
			respuesta.setMensaje(idf.getMessage());
			return ResponseEntity.status(HttpStatus.OK).body(respuesta);
		} catch (Exception e) {
			respuesta.setCodigo("-3");
			respuesta.setMensaje(e.getMessage());
			return ResponseEntity.status(HttpStatus.OK).body(respuesta);
		}

	}

	@SuppressWarnings("finally")
	@Override
	public ResponseEntity<UsuarioResponse> guardarUsuario(Usuario usuario) throws JsonProcessingException {
		Respuesta respuesta = new Respuesta();
		UsuarioResponse response = new UsuarioResponse();
		try {
			String usr = Constantes.printPrettyJSONString(usuario);
			logger.info(Constantes.MENSAJE2, "[guardarUsuario] ", usr);
			boolean activo = usuarioRepositorio.findById(usuario.getId()).isPresent();
			logger.info(Constantes.MENSAJE2, "[guardarUsuario][activo] ", activo);
			if (!activo) {
				Usuario newUsuario = usuarioRepositorio.save(usuario);
				String usrs = Constantes.printPrettyJSONString(newUsuario);
				logger.info(Constantes.MENSAJE2, "[guardarUsuario][new] ", usrs);
				respuesta.setCodigo("0");
				respuesta.setMensaje("OPERACION EXITOSA");
				response.setRespuesta(respuesta);
				response.setUsuario(newUsuario);
			} else {
				throw new IDFException("1", "El usuario ya existe.");
			}
		} catch (IDFException idf) {
			respuesta.setCodigo(idf.getCode());
			respuesta.setMensaje(idf.getMessage());
			response.setRespuesta(respuesta);
		} catch (Exception e) {
			respuesta.setCodigo("-3");
			respuesta.setMensaje(e.getMessage());
			response.setRespuesta(respuesta);
		}finally {
			String obj = Constantes.printPrettyJSONString(response);
			logger.info(Constantes.MENSAJE2, "[guardarUsuario] ", obj);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}
	}

	@Override
	public ResponseEntity<?> obtenerUsuarioPorID(Long id) throws JsonProcessingException {
		Respuesta respuesta = new Respuesta();
		try {
			Usuario usuario = usuarioRepositorio.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("No existe el usuario"));
			if (usuario != null) {
			String obj = Constantes.printPrettyJSONString(usuario);
			logger.info(Constantes.MENSAJE2, "[obtenerUsuarioPorID] ", obj);
			return ResponseEntity.status(HttpStatus.OK).body(usuario);
			}else {
				throw new IDFException("1", "El usuario no existe.");
			}
		} catch (IDFException idf) {
			respuesta.setCodigo(idf.getCode());
			respuesta.setMensaje(idf.getMessage());
			return ResponseEntity.status(HttpStatus.OK).body(respuesta);
		} catch (Exception e) {
			respuesta.setCodigo("-3");
			respuesta.setMensaje(e.getMessage());
			String obj = Constantes.printPrettyJSONString(respuesta);
			logger.info(Constantes.MENSAJE2, "[guardarUsuario] ", obj);
			return ResponseEntity.status(HttpStatus.OK).body(respuesta);
		}
	}

	@Override
	public Usuario findByEmail(String email) throws JsonProcessingException {
		Usuario usuario = new Usuario();
		Respuesta respuesta = new Respuesta();
		try {
			if (email.isEmpty()) {
				throw new IDFException("1", "Los campos ingresados son nulos o vacios");
			} else {

				logger.info(Constantes.MENSAJE2, "[validarDatos][user] ", email);

				usuario = usuarioRepositorio.findByEmail(email);

				if (usuario != null) {
					respuesta.setCodigo("0");
					respuesta.setMensaje("Proceso Exitoso");
					String obj = Constantes.printPrettyJSONString(respuesta);
					logger.info(Constantes.MENSAJE2, "[guardarUsuario] ", obj);
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
		return usuario;
	}

	@Override
	public ResponseEntity<Respuesta> mayorIndiceUsuario(Respuesta respuesta) {

		Integer aa = usuarioRepositorio.verficarUsuario();
		respuesta.setCodigo(String.valueOf(aa));
		logger.info(Constantes.MENSAJE2, "[mayorIndiceUsuario][id] ", aa);
		return ResponseEntity.ok(respuesta);
	}

}