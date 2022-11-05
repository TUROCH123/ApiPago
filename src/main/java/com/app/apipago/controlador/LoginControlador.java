package com.app.apipago.controlador;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.app.apipago.entity.UsuarioResponse;
import com.app.apipago.excepciones.ResourceNotFoundException;
import com.app.apipago.modelo.Usuario;
import com.app.apipago.repositorio.UsuarioRepositorio;
import com.app.apipago.service.ApiLoginService;
import com.app.apipago.util.Constantes;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
@RequestMapping("/api/v1/login")
@CrossOrigin(origins = "http://localhost:8095")
public class LoginControlador {
	private static final Logger logger = LoggerFactory.getLogger(LoginControlador.class);
	@Autowired
	private UsuarioRepositorio usuarioRepositorio;
	@Autowired
	private ApiLoginService service;
    
	@GetMapping("/listarUsuarios")
	public List<Usuario> listarUsuarios() {
		return usuarioRepositorio.findAll();
	}

	@SuppressWarnings("unchecked")
	@PostMapping("/guardarUsuario")
	public ResponseEntity<UsuarioResponse> guardarUsuario(@RequestBody Usuario usuario) throws JsonProcessingException {
        return (ResponseEntity<UsuarioResponse>) service.guardarUsuario(usuario);
	}

	@GetMapping("/obtenerUsuarioPorID/{id}")
	public ResponseEntity<?> obtenerUsuarioPorID(@PathVariable Long id) throws JsonProcessingException {
		return service.obtenerUsuarioPorID(id);
	}
	
	@PutMapping("/actualizarUsuarioPorID")
	public ResponseEntity<?> actualizarUsuarioPorID(@RequestParam(value = "id", required = false) Long id,
			@RequestBody Usuario usuarioAct) throws JsonProcessingException {
		logger.info(Constantes.MENSAJE2, "[actualizarUsuarioPorID][controller][id] ", id);
		return service.actualizarUsuarioPorID(id, usuarioAct);
	}
	
//	@PutMapping("/actualizarUsuarioPorID")
//	@PutMapping(path = "/actualizarUsuarioPorID/{id}")
//	public ResponseEntity<?> actualizarUsuarioPorID(@PathVariable Integer id,@RequestBody Usuario usuarioAct) throws JsonProcessingException {
//		logger.info(Constantes.MENSAJE2, "[actualizarUsuarioPorID][controller][id] ", id);
//		return service.actualizarUsuarioPorID(id, usuarioAct);
//	}

	@DeleteMapping("/eliminarUsuario/{id}")
	public ResponseEntity<Map<String,Boolean>> eliminarUsuario(@PathVariable Long id){
		
			Usuario usuario = usuarioRepositorio.findById(id)
					            .orElseThrow(() -> new ResourceNotFoundException("No existe el usuario con el ID : " + id));
			
			usuarioRepositorio.delete(usuario);
			Map<String, Boolean> respuesta = new HashMap<>();
			respuesta.put("eliminar",Boolean.TRUE);
			return ResponseEntity.ok(respuesta);
	 }

	@GetMapping("/validarDatos")
	public ResponseEntity<UsuarioResponse> validarDatos(@RequestParam(value = "email", required = false) String email,
			@RequestParam(value = "pass", required = false) String pass) throws JsonProcessingException {
		logger.info(Constantes.MENSAJE2,"[validarDatos][user] ", email);
		logger.info(Constantes.MENSAJE2,"[validarDatos][pass] ", pass);
		return service.validarDatos(email, pass);
	}
	
	@GetMapping("/findByEmail")
	public Usuario findByEmail(@RequestParam(value = "email", required = true) String email)
			throws JsonProcessingException {
		logger.info(Constantes.MENSAJE2,"[validarDatos][user] ", email);
		return service.findByEmail(email);
	}
	
	@SuppressWarnings("unchecked")
	@PostMapping("/obtenerMayorIndice")
	public ResponseEntity<Respuesta> mayorIndicePlanes(Respuesta respuesta) {
        return (ResponseEntity<Respuesta>) service.mayorIndiceUsuario(respuesta);
	}
}