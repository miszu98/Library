package pl.wasko.Library.service;

import pl.wasko.Library.DTO.Role;
import pl.wasko.Library.Entities.RoleEntity;
import pl.wasko.Library.Exceptions.RoleNotFound;

public interface RoleService {
    void add(Role role);
    RoleEntity findRoleById(long id) throws RoleNotFound;
}
