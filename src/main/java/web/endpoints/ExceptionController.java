package web.endpoints;

import business.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionController {

    private Map<String, HttpStatus> statusCodes = new HashMap<String, HttpStatus>();

    public ExceptionController() {
        statusCodes.put(ResourceExistingException.class.getName(), HttpStatus.CONFLICT);
        statusCodes.put(ResourceNotExistingException.class.getName(), HttpStatus.BAD_REQUEST);
        statusCodes.put(ResourceNotFoundException.class.getName(), HttpStatus.NOT_FOUND);
        statusCodes.put(InvalidTokenException.class.getName(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    ResponseEntity<String> handleException(Exception ex) {
        ex.printStackTrace();
        HttpStatus status = statusCodes.get(ex.getClass().getName());
        if (status == null)
            return doHandle("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        else
            return doHandle(ex.getMessage(), status);
    }
    private ResponseEntity<String> doHandle(String message, HttpStatus code) {
        return new ResponseEntity<String>(message, code);
    }
}
