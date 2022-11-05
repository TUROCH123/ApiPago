package com.app.apipago.integration;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestConfig {
	
	@Bean(name = "restTemplateUsuario")
	public RestTemplate restTemplateTipificacion(RestTemplateBuilder restTemplateBuilder) {
		return restTemplateBuilder.setConnectTimeout(null).setReadTimeout(null).build();
	}
	
}
