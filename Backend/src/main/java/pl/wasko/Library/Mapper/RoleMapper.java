package pl.wasko.Library.Mapper;

import pl.wasko.Library.DTO.Role;
import pl.wasko.Library.Entities.RoleEntity;

public class RoleMapper {
    public static RoleEntity dtoToEntity(Role role) {
        return new RoleEntity(
                role.getRoleName()
        );
    }

    private RoleMapper() {
    }
}
