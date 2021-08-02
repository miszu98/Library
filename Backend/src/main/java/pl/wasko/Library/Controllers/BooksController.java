package pl.wasko.Library.Controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.wasko.Library.DTO.Book;
import pl.wasko.Library.Exceptions.AuthorNotFound;
import pl.wasko.Library.Exceptions.BookCreateError;
import pl.wasko.Library.Exceptions.BookNotFound;
import pl.wasko.Library.Exceptions.BookTypeNotFound;
import pl.wasko.Library.service.BooksService;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("/books")
public class BooksController {

    private BooksService booksService;


    @GetMapping("/")
    public ResponseEntity<List<Book>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(
                booksService.getAll()
        );
    }

    @GetMapping("/sortBy={field},{type}")
    public ResponseEntity<List<Book>> getSorted(@PathVariable String field, @PathVariable String type) {
        return ResponseEntity.status(HttpStatus.OK).body(
                booksService.getSorted(field, type)
        );
    }

    @GetMapping("/author={firstName},{lastName}")
    public ResponseEntity<List<Book>> getByAuthor(@PathVariable String firstName,
                                                  @PathVariable String lastName) throws AuthorNotFound {
        return ResponseEntity.status(HttpStatus.OK).body(booksService.findBooksByAuthor(firstName, lastName));
    }

    @PostMapping("/")
    public ResponseEntity<Book> add(@Valid @RequestBody Book book, BindingResult result) throws AuthorNotFound, BookTypeNotFound, BookCreateError {
        return ResponseEntity.status(HttpStatus.OK).body(booksService.add(book, result));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> update(@PathVariable long id,
                                       @Valid @RequestBody Book book,
                                       BindingResult result) throws BookNotFound, AuthorNotFound, BookTypeNotFound, BookCreateError {
        return ResponseEntity.status(HttpStatus.OK).body(
                booksService.update(id, book, result)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable long id) throws BookNotFound {
        booksService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("deleted");
    }


}
