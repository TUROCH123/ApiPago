package com.app.apipago.controlador;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.apipago.entity.Respuesta;
import com.app.apipago.excepciones.ResourceNotFoundException;
import com.app.apipago.excepciones.WSException;
import com.app.apipago.integration.UsuarioWs;
import com.app.apipago.modelo.Perfiles;
import com.app.apipago.modelo.Usuario;
import com.app.apipago.repositorio.PerfilesRepositorio;
import com.app.apipago.repositorio.UsuarioRepositorio;
import com.app.apipago.util.Constantes;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
@RequestMapping("/api/v1/perfil")
@CrossOrigin(origins = "http://localhost:8095")
public class PerfilControlador {
	private static final Logger logger = LoggerFactory.getLogger(PerfilControlador.class);
	@Autowired
	private PerfilesRepositorio perfilesRepositorio;
	@Autowired
	private UsuarioRepositorio usuarioRepositorio;
	@Autowired
	private UsuarioWs usuariows;

	@GetMapping("/listarPerfiles")
	public List<Perfiles> listarPerfiles() {
		return perfilesRepositorio.findAll();
	}

	@PostMapping("/guardarPerfil")
	public ResponseEntity<?> guardarPerfil(@RequestBody Perfiles perfil) throws JsonProcessingException {
		try {
			String usr = Constantes.printPrettyJSONString(perfil);
			logger.info(Constantes.MENSAJE2, "[guardarPerfil] ", usr);
			boolean activo = perfilesRepositorio.findById(perfil.getId()).isPresent();
			logger.info(Constantes.MENSAJE2, "[guardarPerfil][activo] ", activo);
			if (!activo) {
				return ResponseEntity.status(HttpStatus.OK).body(perfilesRepositorio.save(perfil));
			} else {
				Respuesta respuesta = new Respuesta();
				respuesta.setCodigo("1");
				respuesta.setMensaje("Error. El perfil ya existe.");
				String obj = Constantes.printPrettyJSONString(respuesta);
				logger.info(Constantes.MENSAJE2, "[guardarEmpleado] ", obj);
				return ResponseEntity.status(HttpStatus.OK).body(respuesta);
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("{\"error\":\"Error. Por favor intente más tarde.\"}");
		}
	}

	@GetMapping("/obtenerPerfilPorID/")
	public ResponseEntity<Perfiles> obtenerPerfilPorID(@RequestParam(value = "id", required = true) Integer id)
			throws JsonProcessingException {
		logger.info(Constantes.MENSAJE2, "[obtenerPerfilPorID][id] ", id);
		Perfiles perfil = perfilesRepositorio.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No existe el perfil"));
		String obj = Constantes.printPrettyJSONString(perfil);
		logger.info(Constantes.MENSAJE2, "[obtenerPerfilPorID] ", obj);
		return ResponseEntity.ok(perfil);
	}

	@PutMapping("/editarPerfilPorID")
	public ResponseEntity<Perfiles> actualizarPerfilPorID(@RequestParam(value = "id", required = true) Long id,
			@RequestBody Perfiles perfilAct) throws JsonProcessingException, WSException {
		Perfiles perfilActualizado = new Perfiles();

//		String obj = Constantes.printPrettyJSONString(perfilAct);
//		logger.info(Constantes.MENSAJE2, "[actualizarPerfilPorID] ", obj);
		logger.info(Constantes.MENSAJE2, "[actualizarPerfilPorID][id] ", id);
		Usuario usuario = usuarioRepositorio.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No exite el Usuario"));
		if (usuario != null) {
			logger.info(Constantes.MENSAJE2, "[actualizarPerfilPorID][size] ", usuario.getPerfiles().size());
			if (usuario.getPerfiles().isEmpty() || usuario.getPerfiles() == null) {
				Perfiles perfil = new Perfiles(perfilAct.getAlias(), perfilAct.getAvatar(), perfilAct.getIdioma(),
						perfilAct.isReproduccionAutomatica(), perfilAct.getPing(), perfilAct.isControlParental(),perfilAct.getPortada());
				perfilActualizado = perfilesRepositorio.save(perfil);

				usuario.getPerfiles().clear();
				usuario.getPerfiles().add(perfilActualizado);

			} else {
				for (Perfiles perfilUser : usuario.getPerfiles()) {
					if (perfilUser.getId().equals(perfilAct.getId())) {
						Perfiles perfil = perfilesRepositorio.findById(perfilAct.getId())
								.orElseThrow(() -> new ResourceNotFoundException("No exite el perfil"));
						perfil.setAlias(perfilAct.getAlias());
						perfil.setAvatar(perfilAct.getAvatar());
						perfil.setIdioma(perfilAct.getIdioma());
						perfil.setPing(perfilAct.getPing());
						perfil.setReproduccionAutomatica(perfilAct.isReproduccionAutomatica());
						perfil.setControlParental(perfilAct.isControlParental());
						perfil.setPortada(perfilAct.getPortada());
//						perfil.getMiListas().clear();
//						perfil.getMiListas().addAll(perfilAct.getMiListas());
						perfilActualizado = perfilesRepositorio.save(perfil);
						break;
					}
				}
			}
			usuariows.actualizarUsuarioPorID(usuario.getId(), usuario);
		}
		return ResponseEntity.ok(perfilActualizado);
	}

	// este metodo sirve para eliminar un perfil
	@DeleteMapping("/eliminarPerfil/{id}")
	public ResponseEntity<Map<String, Boolean>> eliminarPerfil(@PathVariable Integer id) {
		Perfiles perfil = perfilesRepositorio.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No existe el perfil con el ID : " + id));

		perfilesRepositorio.delete(perfil);
		Map<String, Boolean> respuesta = new HashMap<>();
		respuesta.put("eliminar", Boolean.TRUE);
		return ResponseEntity.ok(respuesta);
	}

	@GetMapping("/obtenerPerfilPorEmail/")
	public ResponseEntity<Usuario> obtenerPerfilPorEmail(@RequestParam(value = "email",required = true) String email) throws JsonProcessingException {
		
		Usuario usuario = usuarioRepositorio.findByEmail(email);
//				.orElseThrow(() -> new ResourceNotFoundException("No existe el perfil"));
		String obj = Constantes.printPrettyJSONString(usuario);
		logger.info(Constantes.MENSAJE2, "[obtenerPerfilPorEmail] ", obj);
		return ResponseEntity.ok(usuario);
	}
}