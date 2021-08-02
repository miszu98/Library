package pl.wasko.Library.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Author {

    private Long id;
    
    @Size(min = 3, message = "First name is too short, min 3 length")
    @NotNull(message = "First name cannot be null value")
    private String firstName;

    @Size(min = 3, message = "Last name is too short, min 3 length")
    @NotNull(message = "Last name cannot be null value")
    private String lastName;

    @NotNull(message = "Date of birth field cannot be empty")
    private String dateOfBirth;
}
