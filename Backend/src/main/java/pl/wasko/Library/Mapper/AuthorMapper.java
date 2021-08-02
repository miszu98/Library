package pl.wasko.Library.Mapper;

import org.springframework.stereotype.Component;
import pl.wasko.Library.DTO.Author;
import pl.wasko.Library.Entities.AuthorEntity;
import pl.wasko.Library.service.AuthorService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AuthorMapper {

    public Author entityToDto(AuthorEntity authorEntity) {
        return Author
                .builder()
                .id(authorEntity.getId())
                .firstName(authorEntity.getFirstName())
                .lastName(authorEntity.getLastName())
                .dateOfBirth(authorEntity.getDateOfBirth().toString())
                .build();
    }

    public List<Author> entitiesToDto(List<AuthorEntity> authorEntityEntities) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        return authorEntityEntities.stream().map(author -> Author
                .builder()
                .id(author.getId())
                .firstName(author.getFirstName())
                .lastName(author.getLastName())
                .dateOfBirth(formatter.format(LocalDate.parse(author.getDateOfBirth().toString())))
                .build()
        ).collect(Collectors.toList());
    }

    public AuthorEntity dtoToEntity(Author author) {
        return AuthorEntity
                .builder()
                .firstName(author.getFirstName())
                .lastName(author.getLastName())
                .dateOfBirth(createDataObject(author.getDateOfBirth()))
                .build();
    }

    public LocalDate createDataObject(String date) {
        String[] dateNumbersAsString = date.split("/");
        int[]  dateNumbersAsInt = Arrays.stream(dateNumbersAsString).mapToInt(i -> Integer.parseInt(i)).toArray();
        return LocalDate.of(dateNumbersAsInt[2], dateNumbersAsInt[1], dateNumbersAsInt[0]);
    }



}
