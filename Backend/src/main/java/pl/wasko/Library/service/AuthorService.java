package pl.wasko.Library.service;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import pl.wasko.Library.DTO.Author;
import pl.wasko.Library.Entities.AuthorEntity;
import pl.wasko.Library.Entities.ErrorImpl;
import pl.wasko.Library.Exceptions.AuthorCreateError;
import pl.wasko.Library.Exceptions.AuthorNotFound;

import java.time.LocalDate;
import java.util.List;

public interface AuthorService {
    Author add(Author author, BindingResult result) throws AuthorCreateError;
    List<Author> getAll();
    AuthorEntity findById(long id) throws AuthorNotFound;
    void deleteById(long id);
    void delete(AuthorEntity authorEntity);
    List<ErrorImpl> extractErrors(List<ObjectError> errors);
    void checkErrors(BindingResult result, String date) throws AuthorCreateError;
    Author update(long id, Author author, BindingResult result) throws AuthorCreateError, AuthorNotFound;
    LocalDate createDataObject(String date);
    boolean checkIfExist(long id);
    List<Author> getSortedBy(String field, String type);
    AuthorEntity findByFirstNameAndLastName(String firstName, String lastName) throws AuthorNotFound;

}
