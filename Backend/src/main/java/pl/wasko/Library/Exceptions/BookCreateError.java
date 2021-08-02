package pl.wasko.Library.Exceptions;

import lombok.Getter;
import pl.wasko.Library.Entities.ErrorImpl;

import java.util.List;

@Getter
public class BookCreateError extends Exception {
    private List<ErrorImpl> errorList;
    public BookCreateError(List<ErrorImpl> errorList) {
        this.errorList = errorList;
    }
}
