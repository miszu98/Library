package pl.wasko.Library.service;

import pl.wasko.Library.DTO.BookType;
import pl.wasko.Library.Entities.BookTypeEntity;
import pl.wasko.Library.Exceptions.BookTypeNotFound;

import java.util.List;

public interface BookTypeService {
    BookTypeEntity findTypeByID(long id);
    BookTypeEntity findTypeByName(String typeName) throws BookTypeNotFound;
    BookTypeEntity add(BookTypeEntity bookTypeEntity);
    List<BookType> getAll();
}
