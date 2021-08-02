package pl.wasko.Library.Exceptions;

import lombok.Getter;
import pl.wasko.Library.Entities.ErrorImpl;

import java.util.List;

@Getter
public class RegistrationError extends Exception {
    private List<ErrorImpl> errors;

    public RegistrationError(List<ErrorImpl> errors) {
        this.errors = errors;
    }
}
