package pl.wasko.Library.Mapper;

import pl.wasko.Library.DTO.User;
import pl.wasko.Library.Entities.UserEntity;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {
    public static UserEntity dtoToEntity(User user) {
        return new UserEntity(
                user.getEmail(),
                user.getPassword(),
                user.getFirstName(),
                user.getLastName()
        );
    }

    public static User entityToDto(UserEntity userEntity) {
        return new User(
                userEntity.getEmail(),
                userEntity.getPassword(),
                userEntity.getFirstName(),
                userEntity.getLastName()
        );
    }

    public static List<User> entitiesToDto(List<UserEntity> userEntities) {
        return userEntities.stream().map(userEntity -> new User(
                userEntity.getEmail(),
                "*********",
                userEntity.getFirstName(),
                userEntity.getLastName()
        )).collect(Collectors.toList());
    }

    private UserMapper() {

    }
}
