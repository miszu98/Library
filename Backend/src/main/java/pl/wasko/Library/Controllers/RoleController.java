package pl.wasko.Library.Controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.wasko.Library.DTO.Role;
import pl.wasko.Library.service.RoleService;

import java.util.List;

@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("/roles")
public class RoleController {


    private RoleService roleService;

    @GetMapping("/")
    public ResponseEntity<List<Role>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(roleService.getAll());
    }

    @PostMapping("/")
    public ResponseEntity<Role> getAll(@RequestBody Role role) {
        return ResponseEntity.status(HttpStatus.OK).body(roleService.add(role));
    }
}
