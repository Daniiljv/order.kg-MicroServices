package my.code.establishment.services;

import my.code.establishment.dtos.PositionDto;
import my.code.establishment.entities.Position;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PositionService {

    Long create (String name);

    String rename (Long id, String name);

    Position findActiveById(Long id);

    List<PositionDto> findByEstablishmentId(Long id);

    List<PositionDto> findAllActive();

    String delete (Long id);
}
