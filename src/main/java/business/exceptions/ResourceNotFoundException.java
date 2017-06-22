package business.exceptions;

public class ResourceNotFoundException extends RuntimeException {

    private static String message = "Resource not found";

    public ResourceNotFoundException(){ super(message); }
}
