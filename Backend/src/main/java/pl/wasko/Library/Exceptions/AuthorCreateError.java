package pl.wasko.Library.Exceptions;

import pl.wasko.Library.Entities.ErrorImpl;

import java.util.List;

public class AuthorCreateError extends Exception {
    private List<ErrorImpl> errorList;

    public AuthorCreateError(List<ErrorImpl> errorList) {
        this.errorList = errorList;
    }

    public List<ErrorImpl> getErrorList() {
        return this.errorList;
    }
}
