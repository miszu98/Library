package pl.wasko.Library.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.wasko.Library.DTO.BookType;
import pl.wasko.Library.Entities.BookTypeEntity;
import pl.wasko.Library.service.BookTypeService;

import java.util.List;


@CrossOrigin
@RestController
@RequestMapping("/types")
public class TypeOfBookController {


    private BookTypeService bookTypeService;

    public TypeOfBookController(BookTypeService bookTypeService) {
        this.bookTypeService = bookTypeService;
    }

    @PostMapping("/")
    public ResponseEntity<BookTypeEntity> addBookType(@RequestBody BookTypeEntity bookTypeEntity) {
        return ResponseEntity.status(HttpStatus.OK).body(bookTypeService.add(bookTypeEntity));
    }

    @GetMapping("/")
    public ResponseEntity<List<BookType>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(bookTypeService.getAll());
    }



}
