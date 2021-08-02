package pl.wasko.Library.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Lob;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    private Long id;

    @NotNull(message = "Author cannot be null value")
    private BookAuthor author;

    @Size(min = 3, message = "Title is too short, min length is 3")
    @NotNull(message = "Title cannot be null value")
    private String title;

    @NotNull(message = "Type cannot be null value")
    private BookType bookType;

    @NotNull(message = "Date cannot be null value")
    private String publicationDate;

    @NotNull(message = "Quantity cannot be null value")
    private long quantity;

    @NotNull(message = "Description cannot be null value")
    private String description;

    private String imageUrl;
}
