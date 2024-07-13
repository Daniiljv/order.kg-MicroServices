package my.code.establishment.mappers;

import my.code.establishment.dtos.PositionDto;
import my.code.establishment.entities.Position;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PositionMapper {

    List<PositionDto> toDtos(List<Position> positions);

    PositionDto toDto(Position position);
}
