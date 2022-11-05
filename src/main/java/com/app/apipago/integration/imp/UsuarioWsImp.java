package com.app.apipago.integration.imp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.remoting.jaxws.JaxWsSoapFaultException;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import com.app.apipago.excepciones.WSException;
import com.app.apipago.integration.UsuarioWs;
import com.app.apipago.modelo.Usuario;
import com.app.apipago.util.Constantes;

@Component
public class UsuarioWsImp  implements UsuarioWs {
	private static final Logger logger = LoggerFactory.getLogger(UsuarioWsImp.class);
	@Autowired
	RestTemplate restTemplateUsuario;

	@Override
	public Usuario obtenerUsuarioPorId(Long id) throws WSException {
		Usuario response = new Usuario();
		String url = "http://localhost:8095/api/v1/login/obtenerUsuarioPorID/"+id;
		String nombreComponente = "login";
		String nombreMetodo = "obtenerUsuarioPorID";
		String msj = "[" + nombreComponente + "][" + nombreMetodo + "]";
		logger.info("message {}{}", "[INICIO]", msj);
		logger.info("message {}{}", "[url]", url);
		logger.info("message {}{}", "[id]", id);
		try {
			response = restTemplateUsuario.getForObject(url, Usuario.class);
	    } catch (JaxWsSoapFaultException e) {
	        throw new WSException("-3", String.format("Error inesperado. Error: %s - %s", nombreComponente, nombreMetodo), e);
	    } catch (Exception e) {
	    	Constantes.capturarErrorWs(e, nombreComponente, nombreMetodo);
	      } finally {
				logger.info("message {}{}", "[FIN]", msj);
	      }
		return response;
	}

	@Override
	public ResponseEntity<?> actualizarUsuarioPorID(Long id, Usuario usuario) throws WSException {
		ResponseEntity<Usuario> response = null;
		String url = "http://localhost:8095/api/v1/login/actualizarUsuarioPorID?id="+id;
//		String url = "http://localhost:8095/api/v1/login/actualizarUsuarioPorID/"+id;
		String nombreComponente = "login";
		String nombreMetodo = "actualizarUsuarioPorID";
		String msj = "[" + nombreComponente + "][" + nombreMetodo + "]";
		logger.info("message {}{}", "[INICIO]", msj);
		logger.info("message {}{}", "[url]", url);
		logger.info("message {}{}", "[id]", id);
		try {
			HttpEntity<Usuario> req = new HttpEntity<>(usuario);
//			Map<String, String> uriVariables = new HashMap<>();
//			uriVariables.put("id", String.valueOf(id));
			response = restTemplateUsuario.exchange(url, HttpMethod.PUT, req, Usuario.class);

		} catch (JaxWsSoapFaultException e) {
			throw new WSException(Constantes.CODIGO_IDT3,
					String.format(Constantes.MENSAJE_IDT3, nombreComponente, nombreMetodo), e);
		} catch (Exception e) {
			Constantes.capturarErrorWs(e, nombreComponente, nombreMetodo);
		} finally {
			logger.info("message {}{}", "[FIN]", msj);
		}
		return response;
	}
	@Override
	public Usuario validarDatos(String email,String pass) throws WSException {
		Usuario response = null;
		String url = "http://localhost:8095/api/v1/login/validarDatos/?email="+email+"&pass="+pass+"";
		String nombreComponente = "login";
		String nombreMetodo = "validarDatos";
		String msj = "[" + nombreComponente + "][" + nombreMetodo + "]";
		logger.info("message {}{}", "[INICIO]", msj);
		logger.info("message {}{}", "[url]", url);
		logger.info("message {}{}", "[email]", email);
		try {

			response = restTemplateUsuario.getForObject(url, Usuario.class);
			
	    } catch (JaxWsSoapFaultException e) {
	        throw new WSException("-3", String.format("Error inesperado. Error: %s - %s", nombreComponente, nombreMetodo), e);
	    } catch (Exception e) {
	        Constantes.capturarErrorWs(e, nombreComponente, nombreMetodo);
	    } finally {
	    	logger.info("message {}{}", "[FIN]", msj);
	    }
		return response;
	}
}