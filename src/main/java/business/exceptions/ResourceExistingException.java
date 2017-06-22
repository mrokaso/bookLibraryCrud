package business.exceptions;


public class ResourceExistingException extends RuntimeException {

    static private String message = "Resource exists";

    public ResourceExistingException() { super(message); }

}
