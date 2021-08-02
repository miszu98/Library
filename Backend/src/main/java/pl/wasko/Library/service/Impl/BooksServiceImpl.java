package pl.wasko.Library.service.Impl;

import org.apache.commons.validator.GenericValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import pl.wasko.Library.DTO.Book;
import pl.wasko.Library.Exceptions.AuthorNotFound;
import pl.wasko.Library.Exceptions.BookCreateError;
import pl.wasko.Library.Exceptions.BookNotFound;
import pl.wasko.Library.Entities.BookEntity;
import pl.wasko.Library.Entities.ErrorImpl;
import pl.wasko.Library.Exceptions.BookTypeNotFound;
import pl.wasko.Library.Mapper.BookMapper;
import pl.wasko.Library.Repositories.BookRepository;
import pl.wasko.Library.service.AuthorService;
import pl.wasko.Library.service.BooksService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class BooksServiceImpl implements BooksService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private AuthorService authorsService;


    public Book add(Book book, BindingResult result) throws AuthorNotFound, BookTypeNotFound, BookCreateError {
        boolean isCorrect = GenericValidator.isDate(book.getPublicationDate(), "dd/MM/yyyy", true);
        checkErrors(result, isCorrect);
        BookEntity bookEntity = bookMapper.dtoToEntity(book);
        return bookMapper.entityToDto(bookRepository.save(bookEntity));
    }

    public List<Book> getAll() {
        return bookMapper.entitiesToDto(bookRepository.findAll());
    }

    public BookEntity findById(long id) throws BookNotFound {
        return bookRepository.findById(id).orElseThrow(
                () -> new BookNotFound(
                        "Book with id: " + id + " not found"
                )
        );
    }

    public Book update(long id, Book book, BindingResult result) throws BookNotFound, AuthorNotFound, BookTypeNotFound, BookCreateError {
        BookEntity bookEntity = findById(id);
        boolean isCorrect = GenericValidator.isDate(book.getPublicationDate(), "dd/MM/yyyy", true);
        checkErrors(result, isCorrect);
        BookEntity updatedAndConverted = bookMapper.dtoToEntity(book);
        bookEntity.setAuthorEntity(updatedAndConverted.getAuthorEntity());
        bookEntity.setTitle(updatedAndConverted.getTitle());
        bookEntity.setPublicationDate(updatedAndConverted.getPublicationDate());
        bookEntity.setQuantity(updatedAndConverted.getQuantity());
        bookEntity.setType(updatedAndConverted.getType());
        return bookMapper.entityToDto(bookRepository.save(bookEntity));
    }

    public void deleteById(long id) {
        bookRepository.deleteById(id);
    }

    public List<ErrorImpl> extractErrors(List<ObjectError> errors) {
        List<ErrorImpl> errorList = new ArrayList<>();

        for (ObjectError error : errors) {
            FieldError fieldError = (FieldError) error;
            errorList.add(new ErrorImpl(fieldError.getField(), Arrays.asList(fieldError.getDefaultMessage())));
        }
        return errorList;
    }


    public void checkErrors(BindingResult result, boolean isCorrect) throws BookCreateError {
        if (result.hasErrors() && !isCorrect) {
            List<ErrorImpl> extracted = extractErrors(result.getAllErrors());
            extracted.add(new ErrorImpl("publicationDate", Arrays.asList("publicationDate wrong format, need to be (dd/MM/yyyy")));
            throw new BookCreateError(extracted);
        } else if (!isCorrect) {
            throw new BookCreateError(Arrays.asList(new ErrorImpl("publicationDate", Arrays.asList("publicationDate wrong format, need to be (dd/MM/yyyy)"))));
        } else if (result.hasErrors()) {
            List<ErrorImpl> extracted = extractErrors(result.getAllErrors());
            throw new BookCreateError(extracted);
        }
    }

    public LocalDate createLocalDateObject(String date) {
        String[] dateNumbersAsString = date.split("/");
        int[] dateNumbersAsInt = Arrays.stream(dateNumbersAsString).mapToInt(i -> Integer.parseInt(i)).toArray();
        return LocalDate.of(dateNumbersAsInt[2], dateNumbersAsInt[1], dateNumbersAsInt[0]);
    }

    public List<Book> getSorted(String field, String type) {
        return type.equals("asc") ?
                bookMapper.entitiesToDto(bookRepository.findAll(Sort.by(field).ascending())) : bookMapper.entitiesToDto(bookRepository.findAll(Sort.by(field).descending()));
    }

    @Transactional
    public List<Book> findBooksByAuthor(String firstName, String lastName) throws AuthorNotFound {
        return bookMapper.entitiesToDto(
                authorsService.
                        findByFirstNameAndLastName(
                                firstName,
                                lastName
                        ).getBookEntityList()
        );
    }






}
