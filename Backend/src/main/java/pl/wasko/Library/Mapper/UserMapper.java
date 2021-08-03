package pl.wasko.Library.Mapper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.wasko.Library.DTO.Role;
import pl.wasko.Library.DTO.User;
import pl.wasko.Library.Entities.UserEntity;
import pl.wasko.Library.Exceptions.RoleNotFound;
import pl.wasko.Library.service.RoleService;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class UserMapper {

    private RoleService roleService;

    public UserEntity dtoToEntity(User user) throws RoleNotFound {
        return new UserEntity(
                user.getEmail(),
                user.getPassword(),
                user.getFirstName(),
                user.getLastName(),
                roleService.findRoleById(user.getRole().getId())
        );
    }

    public static User entityToDto(UserEntity userEntity) {
        return new User(
                userEntity.getId(),
                userEntity.getEmail(),
                userEntity.getPassword(),
                userEntity.getFirstName(),
                userEntity.getLastName(),
                RoleMapper.entityToDto(userEntity.getRoleEntity()),
                userEntity.getDateJoined()
        );
    }

    public static List<User> entitiesToDto(List<UserEntity> userEntities) {
        return userEntities.stream().map(userEntity -> new User(
                userEntity.getId(),
                userEntity.getEmail(),
                "password",
                userEntity.getFirstName(),
                userEntity.getLastName(),
                new Role(userEntity.getRoleEntity().getId(), userEntity.getRoleEntity().getRoleName()),
                userEntity.getDateJoined()
        )).collect(Collectors.toList());
    }

}
