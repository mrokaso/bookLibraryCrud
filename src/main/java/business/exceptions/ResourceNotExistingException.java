package business.exceptions;


public class ResourceNotExistingException extends RuntimeException {

    static private String message = "Resource not exists";

    public ResourceNotExistingException() { super(message); }

}
