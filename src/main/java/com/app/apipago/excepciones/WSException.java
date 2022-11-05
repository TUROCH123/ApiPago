package com.app.apipago.excepciones;

public class WSException extends BaseException {

  private static final long serialVersionUID = 1L;

  public WSException(String string) {
    super(string);
  }

  public WSException(Exception exception) {
    super(exception);
  }

  public WSException(String string, Exception exception) {
    super(string, exception);
  }

  public WSException(String code, String message, Exception exception) {
    super(code, message, exception);
  }

  public WSException(String code, String message, String messageDeveloper, Exception exception, int status) {
    super(code, message, messageDeveloper, exception, status);
  }

  public WSException(String code, String message) {
    super(code, message);
  }

  public WSException(String code, String message, String messageDeveloper) {
    super(code, message, messageDeveloper);
  }

}
