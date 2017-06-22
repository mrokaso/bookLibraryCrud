package business.exceptions;


public class InvalidTokenException extends RuntimeException {
    private static final long serialVersionUID = 6212059497246133316L;

    public InvalidTokenException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidTokenException(String message) {
        super(message);
    }

    public InvalidTokenException(Throwable cause) {
        super(cause);
    }

    public InvalidTokenException() {
        super();
    }
}

