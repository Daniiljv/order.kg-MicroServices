package my.code.establishment.mappers;

import my.code.common.dtos.CommonEstablishmentDto;
import my.code.establishment.dtos.EstablishmentDto;
import my.code.establishment.entities.Establishment;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface EstablishmentMapper {

    List<EstablishmentDto> toDtos(List<Establishment> establishments);

    EstablishmentDto toDto(Establishment establishment);

    CommonEstablishmentDto toCommonEstablishmentDto(Establishment establishment);
}
