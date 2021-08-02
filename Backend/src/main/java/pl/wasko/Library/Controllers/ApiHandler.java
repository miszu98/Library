package pl.wasko.Library.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.wasko.Library.Entities.ErrorImpl;
import pl.wasko.Library.Exceptions.*;
import java.util.List;

@RestControllerAdvice
public class ApiHandler {


    @ExceptionHandler(RegistrationError.class)
    public ResponseEntity<List<ErrorImpl>> handleRegistrationError(RegistrationError exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getErrors());
    }

    @ExceptionHandler(RoleNotFound.class)
    public ResponseEntity<String> handleRoleNotFoundError(RoleNotFound exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
    }

    @ExceptionHandler(UserNotFound.class)
    public ResponseEntity<String> handleUserNotFoundError(UserNotFound exception) {
        return ResponseEntity.status(HttpStatus.OK).body(exception.getMessage());
    }

    @ExceptionHandler(BookTypeNotFound.class)
    public ResponseEntity<String> handleBookTypeNotFoundError(BookTypeNotFound exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
    }

    @ExceptionHandler(BookCreateError.class)
    public ResponseEntity<List<ErrorImpl>> handleBookTypeNotFoundError(BookCreateError exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getErrorList());
    }

    @ExceptionHandler(BookNotFound.class)
    public ResponseEntity<String> handleBookNotFoundError(BookNotFound exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
    }

    @ExceptionHandler(AuthorCreateError.class)
    public ResponseEntity<List<ErrorImpl>> handleCreationError(AuthorCreateError exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getErrorList());
    }

    @ExceptionHandler(AuthorNotFound.class)
    public ResponseEntity<String> handleNotFoundAuthor(AuthorNotFound exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
    }
}
