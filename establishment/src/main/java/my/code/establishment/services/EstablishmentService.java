package my.code.establishment.services;

import my.code.establishment.dtos.CreateEstablishmentDto;
import my.code.establishment.dtos.EstablishmentDto;
import my.code.establishment.entities.Establishment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EstablishmentService {

    Long create(CreateEstablishmentDto establishmentDto);

    List<EstablishmentDto> findAllActive();

    Establishment findActiveById(Long id);

    EstablishmentDto findActiveByName(String establishmentName);

    String rename(Long id, String newName);

    String delete(Long id);
}
