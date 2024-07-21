package my.code.order.repositories;

import my.code.order.entities.ProductPosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductPositionRepo extends JpaRepository<ProductPosition, Long> {
}
