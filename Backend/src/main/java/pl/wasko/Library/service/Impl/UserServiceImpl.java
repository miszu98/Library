package pl.wasko.Library.service.Impl;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import pl.wasko.Library.DTO.LoginRequest;
import pl.wasko.Library.DTO.LoginResponse;
import pl.wasko.Library.DTO.User;
import pl.wasko.Library.Entities.ErrorImpl;
import pl.wasko.Library.Entities.RoleEntity;
import pl.wasko.Library.Entities.UserEntity;
import pl.wasko.Library.Exceptions.RegistrationError;
import pl.wasko.Library.Exceptions.RoleNotFound;
import pl.wasko.Library.Exceptions.UserNotFound;
import pl.wasko.Library.Mapper.UserMapper;
import pl.wasko.Library.Repositories.UserRepository;
import pl.wasko.Library.service.RoleService;
import pl.wasko.Library.service.UserService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private UserMapper userMapper;
    private RoleService roleService;


    public User add(User user, BindingResult result) throws RoleNotFound, RegistrationError {
        checkErrors(result);
        UserEntity userEntity = userMapper.dtoToEntity(user);
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        return UserMapper.entityToDto(userRepository.save(userEntity));
    }

    public List<User> getAll() {
        return UserMapper.entitiesToDto(userRepository.findAll());
    }

    public LoginResponse login(LoginRequest request) {
        Authentication auth = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                ));
        SecurityContextHolder.getContext().setAuthentication(auth);
        return new LoginResponse(request.getEmail(), "", "");
    }

    @Override
    public UserEntity findById(long id) throws UserNotFound {
        return userRepository.findById(id).orElseThrow(
                () -> new UserNotFound(
                        "User with id: " + id + " not found"
                )
        );
    }

    @Override
    public void deleteById(long id) {
        userRepository.deleteById(id);
    }

    public List<ErrorImpl> extractErrors(List<ObjectError> errors) {
        List<ErrorImpl> extractedErrors = new ArrayList<>();
        for (ObjectError error : errors) {
            FieldError fieldError = (FieldError) error;
            String fieldName = fieldError.getField();
            String message = fieldError.getDefaultMessage();
            if (fieldName.equals("password")) {
                extractedErrors.add(new ErrorImpl(fieldName, Arrays.stream(message.split(",")).collect(Collectors.toList())));
            } else {
                extractedErrors.add(new ErrorImpl(fieldName, Arrays.asList(message)));
            }
        }
        return extractedErrors;
    }

    public void checkErrors(BindingResult result) throws RegistrationError {
        if (result.hasErrors()) {
            throw new RegistrationError(extractErrors(result.getAllErrors()));
        }
    }








}
