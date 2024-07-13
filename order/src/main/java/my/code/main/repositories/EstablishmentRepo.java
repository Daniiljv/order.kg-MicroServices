package my.code.main.repositories;

import my.code.main.entities.Establishment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstablishmentRepo extends JpaRepository<Establishment, Long> {
}
