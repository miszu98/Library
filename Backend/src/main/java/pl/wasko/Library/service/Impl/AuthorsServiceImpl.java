package pl.wasko.Library.service.Impl;

import lombok.AllArgsConstructor;
import org.apache.commons.validator.GenericValidator;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import pl.wasko.Library.DTO.Author;
import pl.wasko.Library.Exceptions.AuthorCreateError;
import pl.wasko.Library.Exceptions.AuthorNotFound;
import pl.wasko.Library.Entities.AuthorEntity;
import pl.wasko.Library.Entities.ErrorImpl;
import pl.wasko.Library.Mapper.AuthorMapper;
import pl.wasko.Library.Repositories.AuthorRepository;
import pl.wasko.Library.service.AuthorService;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
public class AuthorsServiceImpl implements AuthorService {


    private AuthorRepository authorRepository;
    private AuthorMapper authorMapper;


    public Author add(Author author, BindingResult result) throws AuthorCreateError {
        checkErrors(result, author.getDateOfBirth());
        AuthorEntity authorEntity = authorMapper.dtoToEntity(author);
        authorRepository.save(authorEntity);
        return authorMapper.entityToDto(authorEntity);
    }

    public List<Author> getAll() {
        return authorMapper.entitiesToDto(authorRepository.findAll());
    }

    public AuthorEntity findById(long id) throws AuthorNotFound {
        return authorRepository.findAuthorById(id).orElseThrow(
                () -> new AuthorNotFound(
                        "Author with id: " + id + " not exist"
                )
        );
    }

    public void deleteById(long id) {
        authorRepository.deleteById(id);
    }

    public void delete(AuthorEntity authorEntity) {
        authorRepository.delete(authorEntity);
    }


    public List<ErrorImpl> extractErrors(List<ObjectError> errors) {
        var extractedErrors = new ArrayList<ErrorImpl>();
        for (ObjectError e : errors) {
            FieldError field = (FieldError) e;
            var error = new ErrorImpl(field.getField(), Arrays.asList(field.getDefaultMessage()));
            extractedErrors.add(error);
        }
        return extractedErrors;
    }

    public void checkErrors(BindingResult result, String date) throws AuthorCreateError{
        boolean isCorrect = GenericValidator.isDate(date, "dd/MM/yyyy", true);
        if (result.hasErrors() && !isCorrect) {
            List<ErrorImpl> extracted = extractErrors(result.getAllErrors());
            extracted.add(new ErrorImpl("dateOfBirth", Arrays.asList("Date wrong format, need to be (dd/MM/yyyy)")));
            throw new AuthorCreateError(extracted);
        } else if (!isCorrect) {
            throw new AuthorCreateError(Arrays.asList(new ErrorImpl("dateOfBirth", Arrays.asList("Date wrong format, need to be (dd/MM/yyyy)"))));
        } else if (result.hasErrors()) {
            List<ErrorImpl> extracted = extractErrors(result.getAllErrors());
            throw new AuthorCreateError(extracted);
        }
    }

    public Author update(long id, Author author, BindingResult result) throws AuthorCreateError, AuthorNotFound {
        if (!checkIfExist(id)) {
            throw new AuthorNotFound("Author with id: " + id + " not found");
        }
        checkErrors(result, author.getDateOfBirth());
        AuthorEntity authorEntity = findById(id);
        LocalDate date = createDataObject(author.getDateOfBirth());
        authorEntity.setFirstName(author.getFirstName());
        authorEntity.setLastName(author.getLastName());
        authorEntity.setDateOfBirth(date);
        return authorMapper.entityToDto(authorRepository.save(authorEntity));
    }

    public LocalDate createDataObject(String date) {
        var formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(date, formatter);
    }

    public boolean checkIfExist(long id) {
        return authorRepository.existsById(id);
    }

    public List<Author> getSortedBy(String field, String type) {
        return type.equals("asc") ? authorMapper.entitiesToDto(authorRepository.findAll(Sort.by(field).ascending())) : authorMapper.entitiesToDto(authorRepository.findAll(Sort.by(field).descending()));
    }

    public AuthorEntity findByFirstNameAndLastName(String firstName, String lastName) throws AuthorNotFound {
        return authorRepository
                .findAuthorByFirstNameAndLastName(firstName, lastName)
                .orElseThrow(
                        () -> new AuthorNotFound(
                                "Author with first name: " + firstName + " and last name: " + lastName + " not found"
                        )
                );
    }













}
