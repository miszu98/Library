package pl.wasko.Library.service;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import pl.wasko.Library.DTO.Book;
import pl.wasko.Library.Entities.BookEntity;
import pl.wasko.Library.Entities.ErrorImpl;
import pl.wasko.Library.Exceptions.AuthorNotFound;
import pl.wasko.Library.Exceptions.BookCreateError;
import pl.wasko.Library.Exceptions.BookNotFound;
import pl.wasko.Library.Exceptions.BookTypeNotFound;

import java.time.LocalDate;
import java.util.List;

public interface BooksService {
    Book add(Book book, BindingResult result) throws AuthorNotFound, BookTypeNotFound, BookCreateError;
    List<Book> getAll();
    BookEntity findById(long id) throws BookNotFound;
    Book update(long id, Book book, BindingResult result) throws BookNotFound, AuthorNotFound, BookTypeNotFound, BookCreateError;
    void deleteById(long id);
    List<ErrorImpl> extractErrors(List<ObjectError> errors);
    void checkErrors(BindingResult result, boolean isCorrect) throws BookCreateError;
    LocalDate createLocalDateObject(String date);
    List<Book> getSorted(String field, String type);
    List<Book> findBooksByAuthor(String firstName, String lastName) throws AuthorNotFound;
}
