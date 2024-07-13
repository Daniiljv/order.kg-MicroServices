package my.code.main.repositories;

import my.code.main.entities.ProductPosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductPositionRepo extends JpaRepository<ProductPosition, Long> {
}
