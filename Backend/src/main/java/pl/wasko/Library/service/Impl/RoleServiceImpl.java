package pl.wasko.Library.service.Impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.wasko.Library.DTO.Role;
import pl.wasko.Library.Entities.RoleEntity;
import pl.wasko.Library.Exceptions.RoleNotFound;
import pl.wasko.Library.Mapper.RoleMapper;
import pl.wasko.Library.Repositories.RoleRepository;
import pl.wasko.Library.service.RoleService;

import java.util.List;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;


    @Override
    public Role add(Role role) {
        return RoleMapper.entityToDto(roleRepository.save(RoleMapper.dtoToEntity(role)));
    }

    @Override
    public RoleEntity findRoleById(long id) throws RoleNotFound {
        return roleRepository.findById(id).orElseThrow(
                () -> new RoleNotFound(
                        "Role with id: " + id + " not found"
                )
        );
    }

    @Override
    public List<Role> getAll() {
        return RoleMapper.entitiesToDto(roleRepository.findAll());
    }
}
