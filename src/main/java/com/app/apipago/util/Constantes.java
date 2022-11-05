package com.app.apipago.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.app.apipago.excepciones.WSException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class Constantes {
	private static final Logger logger = LoggerFactory.getLogger(Constantes.class);
	public static final String MENSAJE2 = "message {}{}";
	public static final String TEXTO_VACIO = "";
	public static final String TIMEOUT = "Timeout";
	public static final String MENSAJE3 = "message {}{}{}";
	public static final String MENSAJE_IDT1 = "Error de timeout en %s - %s";
	public static final String MENSAJE_IDT2 = "Error de disponibilidad en %s - %s";
	public static final String MENSAJE_IDT3 = "Error inesperado. Error: %s - %s";
	public static final String CODIGO_IDT3 = "-3";
	public static final String CODIGO_IDT2 = "-2";
	public static final String CODIGO_IDT1 = "-1";
	public static final String MONTH = "MONTH";
	public static final String YEAR = "YEAR";

	public Constantes() {
		// Do nothing because of X and Y.
	}

	public static String printPrettyJSONString(Object o) throws JsonProcessingException {
		return new ObjectMapper().setDateFormat(getLocalFormat())
				.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false).writerWithDefaultPrettyPrinter()
				.writeValueAsString(o);
	}

	public static DateFormat getLocalFormat() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		dateFormat.setTimeZone(TimeZone.getDefault());
		return dateFormat;
	}
	
	public static void capturarErrorWs(Exception e, String nombreComponente, String nombreMetodo) throws WSException {
		String error = (e + Constantes.TEXTO_VACIO);
		if (error.contains(Constantes.TIMEOUT)) {
			throw new WSException(Constantes.CODIGO_IDT1,
					String.format(Constantes.MENSAJE_IDT1, nombreComponente, nombreMetodo), e);
		} else {
			throw new WSException(Constantes.CODIGO_IDT2,
					String.format(Constantes.MENSAJE_IDT2, nombreComponente, nombreMetodo), e);
		}
	}
	
	public static int agregarvalor(String id) {
        int idnew = 1;
        int idres = 0;
        if (id ==null) {
        	 idres = idres+idnew;
		}else {
	        logger.info(Constantes.MENSAJE2, "[id] " , id);
	        idres = Integer.parseInt(id) + idnew;

		}
        logger.info(Constantes.MENSAJE2, "[idres] " , idres);
        return idres;
    }
}
