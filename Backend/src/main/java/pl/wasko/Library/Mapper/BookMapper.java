package pl.wasko.Library.Mapper;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.wasko.Library.DTO.BookAuthor;
import pl.wasko.Library.DTO.Book;
import pl.wasko.Library.DTO.BookType;
import pl.wasko.Library.Exceptions.AuthorNotFound;
import pl.wasko.Library.Exceptions.BookTypeNotFound;
import pl.wasko.Library.Entities.AuthorEntity;
import pl.wasko.Library.Entities.BookEntity;
import pl.wasko.Library.service.AuthorService;
import pl.wasko.Library.service.BookTypeService;
import pl.wasko.Library.service.BooksService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookMapper {

    @Autowired
    private AuthorService authorsService;

    @Autowired
    private BookTypeService bookTypeService;

    @Autowired
    private BooksService booksService;

    public Book entityToDto(BookEntity bookEntity) {
        return Book
                .builder()
                .id(bookEntity.getId())
                .author(BookAuthor
                        .builder()
                        .firstName(bookEntity.getAuthorEntity().getFirstName())
                        .lastName(bookEntity.getAuthorEntity().getLastName())
                        .build())
                .title(bookEntity.getTitle())
                .bookType(BookType
                        .builder()
                        .id(bookEntity.getType().getIdType())
                        .type(bookEntity.getType().getType())
                        .build())
                .publicationDate(bookEntity.getPublicationDate().toString())
                .quantity(bookEntity.getQuantity())
                .description(bookEntity.getDescription())
                .imageUrl(bookEntity.getImageUrl())
                .build();
    }

    public List<Book> entitiesToDto(List<BookEntity> bookEntities) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return bookEntities.stream().map(
                book -> Book
                        .builder()
                        .id(book.getId())
                        .author(BookAuthor
                                .builder()
                                .id(book.getAuthorEntity().getId())
                                .firstName(book.getAuthorEntity().getFirstName())
                                .lastName(book.getAuthorEntity().getLastName())
                                .build())
                        .title(book.getTitle())
                        .bookType(BookType
                                .builder()
                                .id(book.getType().getIdType())
                                .type(book.getType().getType())
                                .build())
                        .publicationDate(formatter.format(LocalDate.parse(book.getPublicationDate().toString())))
                        .quantity(book.getQuantity())
                        .description(book.getDescription())
                        .imageUrl(book.getImageUrl())
                        .build()
        ).collect(Collectors.toList());
    }

    public BookEntity dtoToEntity(Book book) throws AuthorNotFound, BookTypeNotFound {
        AuthorEntity authorEntity = authorsService.findByFirstNameAndLastName(book.getAuthor().getFirstName(), book.getAuthor().getLastName());
        return BookEntity
                .builder()
                .authorEntity(authorEntity)
                .title(book.getTitle())
                .type(bookTypeService.findTypeByName(book.getBookType().getType()))
                .publicationDate(booksService.createLocalDateObject(book.getPublicationDate()))
                .quantity(book.getQuantity())
                .description(book.getDescription())
                .imageUrl(book.getImageUrl())
                .build();
    }
}
