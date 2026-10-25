package in.codekerdos.ticketservice.exception;

import java.time.Instant;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice 
public class GlobalExceptionHandler {

    @ExceptionHandler({ResourceNotFoundException.class}) //ResourceNotFoundException is a custom exception class that is thrown when a requested resource (e.g., a ticket) is not found in the system.
    public ResponseEntity<ApiError> handleNotFound(ResourceNotFoundException ex, HttpServletRequest request) {
       
        return build(HttpStatus.NOT_FOUND, ex.getMessage(), request.getRequestURI()); //build method is called to create a ResponseEntity<ApiError> object with the appropriate HTTP status, error message, and request path.
    }

    
    @ExceptionHandler (MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidation(MethodArgumentNotValidException ex, HttpServletRequest request){

        String msg = ex.getBindingResult().getFieldErrors().stream()
            .map(error -> error.getField() + ": " + error.getDefaultMessage())
            .collect(Collectors.joining(", ")); //This line of code is used to extract and format the validation error messages from the MethodArgumentNotValidException. 
                                                            // It retrieves the field errors from the exception's binding result, maps each error to a string representation that includes the field name and the corresponding error message, 
                                                            // and then joins all the formatted error messages into a single string separated by commas. The resulting string (msg) contains a summary of all validation errors that occurred during the request processing.


        return build(HttpStatus.BAD_REQUEST, msg, request.getRequestURI());
    }


    //helper method
    private ResponseEntity<ApiError> build(HttpStatus status, String message, String path) { //build method is a private helper method 

        ApiError apiError = new ApiError(Instant.now(), status.value(), message, path); 

        return new ResponseEntity<>(apiError, status);
    }

}
