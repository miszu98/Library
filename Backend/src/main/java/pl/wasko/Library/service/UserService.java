package pl.wasko.Library.service;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import pl.wasko.Library.DTO.LoginRequest;
import pl.wasko.Library.DTO.LoginResponse;
import pl.wasko.Library.DTO.User;
import pl.wasko.Library.Entities.ErrorImpl;
import pl.wasko.Library.Entities.UserEntity;
import pl.wasko.Library.Exceptions.RegistrationError;
import pl.wasko.Library.Exceptions.RoleNotFound;
import pl.wasko.Library.Exceptions.UserNotFound;

import java.util.List;

public interface UserService {
    User add(User user, BindingResult result) throws RoleNotFound, RegistrationError;
    List<User> getAll();
    List<ErrorImpl> extractErrors(List<ObjectError> errors);
    void deleteById(long id);
    UserEntity findById(long id) throws UserNotFound;
    LoginResponse login(LoginRequest request);
    void checkErrors(BindingResult result) throws RegistrationError;
}
