package my.code.establishment.repositories;

import my.code.establishment.entities.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PositionRepo extends JpaRepository<Position, Long> {

    @Query("""
           SELECT p
           FROM Position p
           WHERE p.id = :id
           AND p.deletedAt IS NULL
           """)
    Optional<Position> findActiveById(@Param("id") Long id);

    @Query("""
           SELECT p
           FROM Position p
           WHERE p.deletedAt IS NULL
           """)
    Optional<List<Position>> findAllActive();
}
