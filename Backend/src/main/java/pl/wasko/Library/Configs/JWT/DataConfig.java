package pl.wasko.Library.Configs.JWT;

import org.springframework.context.annotation.Configuration;
import pl.wasko.Library.Entities.BookTypeEntity;
import pl.wasko.Library.Entities.RoleEntity;
import pl.wasko.Library.Repositories.BookTypeRepository;
import pl.wasko.Library.Repositories.RoleRepository;

@Configuration
public class DataConfig {

    private RoleRepository roleRepository;
    private BookTypeRepository bookTypeRepository;

    public DataConfig(RoleRepository roleRepository, BookTypeRepository bookTypeRepository) {
        this.roleRepository = roleRepository;
        this.bookTypeRepository = bookTypeRepository;

        roleRepository.save(new RoleEntity("USER"));
        roleRepository.save(new RoleEntity("ADMIN"));

        bookTypeRepository.save(new BookTypeEntity("DRAMA"));
        bookTypeRepository.save(new BookTypeEntity("LYRIC"));
        bookTypeRepository.save(new BookTypeEntity("EPIC"));

    }
}
