package my.code.establishment.repositories;

import my.code.establishment.entities.Establishment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EstablishmentRepo extends JpaRepository<Establishment, Long> {

    @Query("""
           SELECT e
           FROM Establishment e
           WHERE e.name = :name
           AND e.deletedAt IS NULL
           """)
    Optional<Establishment> findActiveByName(@Param("name") String name);

    @Query("""
           SELECT e
           FROM Establishment e
           WHERE e.deletedAt IS NULL
           """)
    Optional<List<Establishment>> findAllActive();

    @Query("""
           SELECT e
           FROM Establishment e
           WHERE e.id = :id
           AND e.deletedAt IS NULL
           """)
    Optional<Establishment> findActiveById(Long id);
}
