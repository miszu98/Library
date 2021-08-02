package pl.wasko.Library.service.Impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.wasko.Library.DTO.BookType;
import pl.wasko.Library.Entities.BookEntity;
import pl.wasko.Library.Exceptions.BookTypeNotFound;
import pl.wasko.Library.Entities.BookTypeEntity;
import pl.wasko.Library.Mapper.BookTypeMapper;
import pl.wasko.Library.Repositories.BookTypeRepository;
import pl.wasko.Library.service.BookTypeService;

import java.util.List;

@Service
@AllArgsConstructor
public class BookTypeServiceImpl implements BookTypeService {

    private BookTypeRepository bookTypeRepository;


    public BookTypeEntity findTypeByID(long id) {
        return bookTypeRepository.findTypeByidType(id).get();
    }

    public BookTypeEntity findTypeByName(String typeName) throws BookTypeNotFound {
        return bookTypeRepository.findTypeByType(typeName).orElseThrow(() -> new BookTypeNotFound("Type: " + typeName + " not found"));
    }

    public BookTypeEntity add(BookTypeEntity bookTypeEntity) {
        return bookTypeRepository.save(bookTypeEntity);
    }

    public List<BookType> getAll() {
        return BookTypeMapper.entitiesToDto(bookTypeRepository.findAll());
    }

}
