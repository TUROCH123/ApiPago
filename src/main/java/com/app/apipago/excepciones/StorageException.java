package com.app.apipago.excepciones;

public class StorageException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public StorageException(String message) {
        super(message);
    }

    public StorageException(String message, Throwable exception) {
        super(message, exception);
    }
}
