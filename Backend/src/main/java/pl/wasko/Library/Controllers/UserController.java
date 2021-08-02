package pl.wasko.Library.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.wasko.Library.DTO.LoginRequest;
import pl.wasko.Library.DTO.LoginResponse;
import pl.wasko.Library.DTO.User;
import pl.wasko.Library.Exceptions.RegistrationError;
import pl.wasko.Library.Exceptions.RoleNotFound;
import pl.wasko.Library.service.UserService;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserController {


    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@Valid @RequestBody User user, BindingResult result) throws RegistrationError, RoleNotFound {
        return ResponseEntity.status(HttpStatus.OK).body(userService.add(user, 2, result));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(
                userService.login(request)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable long id) {
        userService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("deleted");
    }

    @GetMapping("/")
    public ResponseEntity<List<User>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(
                userService.getAll()
        );
    }




}

