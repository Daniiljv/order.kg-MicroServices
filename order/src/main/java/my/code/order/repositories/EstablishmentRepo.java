package my.code.order.repositories;

import my.code.order.entities.Establishment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstablishmentRepo extends JpaRepository<Establishment, Long> {
}
