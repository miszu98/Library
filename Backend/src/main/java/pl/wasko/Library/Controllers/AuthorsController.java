package pl.wasko.Library.Controllers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.wasko.Library.DTO.Author;
import pl.wasko.Library.Exceptions.AuthorCreateError;
import pl.wasko.Library.Exceptions.AuthorNotFound;
import pl.wasko.Library.Mapper.AuthorMapper;
import pl.wasko.Library.Entities.AuthorEntity;
import pl.wasko.Library.service.AuthorService;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/authors")
public class AuthorsController {


    private AuthorMapper authorMapper;
    private AuthorService authorService;

    public AuthorsController(AuthorMapper authorMapper, AuthorService authorService) {
        this.authorMapper = authorMapper;
        this.authorService = authorService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Author>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(authorService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> getById(@PathVariable long id) throws AuthorNotFound {
        return ResponseEntity.status(HttpStatus.OK).body(authorMapper.entityToDto(authorService.findById(id)));
    }

    @GetMapping("/sortBy={field},{type}")
    public ResponseEntity<List<Author>> getSorted(@PathVariable String field,
                                                  @PathVariable String type) {
        return ResponseEntity.status(HttpStatus.OK).body(authorService.getSortedBy(field, type));
    }

    @PostMapping("/")
    public ResponseEntity<Author> create(@Valid @RequestBody Author author,
                                         BindingResult result) throws AuthorCreateError {
        return ResponseEntity.status(HttpStatus.OK).body(
                authorService.add(author, result)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Author> deleteById(@PathVariable Long id) throws AuthorNotFound {
        AuthorEntity foundedAuthorEntity = authorService.findById(id);
        authorService.delete(foundedAuthorEntity);
        return ResponseEntity.status(HttpStatus.OK).body(
                authorMapper.entityToDto(foundedAuthorEntity)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Author> update(@PathVariable long id,
                                         @Valid @RequestBody Author author,
                                         BindingResult result) throws AuthorCreateError, AuthorNotFound {
        return ResponseEntity.status(HttpStatus.OK).body(
                authorService.update(id, author, result)
        );
    }








}
