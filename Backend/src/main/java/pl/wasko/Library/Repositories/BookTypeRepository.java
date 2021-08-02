package pl.wasko.Library.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.wasko.Library.Entities.BookTypeEntity;

import java.util.Optional;

@Repository
public interface BookTypeRepository extends JpaRepository<BookTypeEntity, Long> {
    Optional<BookTypeEntity> findTypeByidType(long id);
    Optional<BookTypeEntity> findTypeByType(String typeName);
}