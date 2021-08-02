package pl.wasko.Library.Mapper;

import pl.wasko.Library.DTO.BookType;
import pl.wasko.Library.Entities.BookTypeEntity;

import java.util.List;
import java.util.stream.Collectors;

public class BookTypeMapper {
    public static List<BookType> entitiesToDto(List<BookTypeEntity> bookTypeEntities) {
        return bookTypeEntities.stream().map(type ->
                BookType
                        .builder()
                        .id(type.getIdType())
                        .type(type.getType()).build()
        ).collect(Collectors.toList());
    }

    public static BookType entityToDto(BookTypeEntity bookTypeEntity) {
        return BookType.builder().id(bookTypeEntity.getIdType()).type(bookTypeEntity.getType()).build();
    }

    public static BookTypeEntity dtoToEntity(BookType bookType) {
        return BookTypeEntity.builder().type(bookType.getType()).build();
    }

}
