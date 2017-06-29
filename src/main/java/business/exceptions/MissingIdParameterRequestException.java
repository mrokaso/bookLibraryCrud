package business.exceptions;


public class MissingIdParameterRequestException extends RuntimeException {

    static private String message = "Missing ID parameter";

    public MissingIdParameterRequestException() { super(message); }
}
