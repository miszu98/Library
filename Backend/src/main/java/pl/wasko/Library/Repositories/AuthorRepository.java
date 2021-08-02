package pl.wasko.Library.Repositories;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.wasko.Library.Entities.AuthorEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<AuthorEntity, Long> {
    Optional<AuthorEntity> findAuthorById(long id);
    List<AuthorEntity> findAll();
    List<AuthorEntity> findAll(Sort sort);
    Optional<AuthorEntity> findAuthorByFirstNameAndLastName(String firstName, String lastName);
}
