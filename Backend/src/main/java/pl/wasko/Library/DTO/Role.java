package pl.wasko.Library.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    private Long id;
    private String roleName;

    public Role(String roleName) {
        this.roleName = roleName;
    }
}
