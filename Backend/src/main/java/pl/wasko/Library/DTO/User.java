package pl.wasko.Library.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.wasko.Library.Annotation.ValidPassword;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;

    @Email(message = "Not valid format of email")
    @NotNull(message = "Email cannot be null")
    private String email;

    @ValidPassword
    @NotNull(message = "Password cannot be null")
    private String password;

    @Size(min = 3, max = 10, message = "First name length (6-10)")
    @NotNull(message = "First name cannot be null")
    private String firstName;

    @Size(min = 3, max = 10, message = "Last name length (6-10)")
    @NotNull(message = "First name cannot be null")
    private String lastName;


}
