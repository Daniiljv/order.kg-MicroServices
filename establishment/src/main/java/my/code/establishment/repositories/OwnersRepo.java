package my.code.establishment.repositories;

import my.code.establishment.entities.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnersRepo extends JpaRepository<Owner, Long> {

}
