package pl.wasko.Library.Mapper;

import pl.wasko.Library.DTO.Role;
import pl.wasko.Library.Entities.RoleEntity;

import java.util.List;
import java.util.stream.Collectors;

public class RoleMapper {
    public static RoleEntity dtoToEntity(Role role) {
        return new RoleEntity(
                role.getRoleName()
        );
    }

    public static List<Role> entitiesToDto(List<RoleEntity> roleEntities) {
        return roleEntities.stream()
                .map(roleEntity -> new Role(roleEntity.getId(), roleEntity.getRoleName())).collect(Collectors.toList());
    }

    public static Role entityToDto(RoleEntity roleEntity) {
        return new Role(roleEntity.getId(), roleEntity.getRoleName());
    }

    private RoleMapper() {
    }
}
