package my.code.establishment.mappers.impl;

import lombok.RequiredArgsConstructor;
import my.code.common.dtos.CommonEstablishmentDto;
import my.code.establishment.dtos.EstablishmentDto;
import my.code.establishment.entities.Establishment;
import my.code.establishment.mappers.EstablishmentMapper;
import my.code.establishment.mappers.PositionMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class EstablishmentMapperImpl implements EstablishmentMapper {

    private final PositionMapper positionMapper;

    @Override
    public List<EstablishmentDto> toDtos(List<Establishment> establishments) {

        List<EstablishmentDto> establishmentDtos = new ArrayList<>();

        for(Establishment establishment : establishments) {

            EstablishmentDto establishmentDto = EstablishmentDto.builder()
                    .id(establishment.getId())
                    .name(establishment.getName())
                    .location(establishment.getLocation())
                    .ownerId(establishment.getOwnerId())
                    .positions(positionMapper.toDtos(establishment.getPositions()))
                    .createdBy(establishment.getCreatedBy())
                    .createdAt(establishment.getCreatedAt())
                    .lastModifiedBy(establishment.getLastModifiedBy())
                    .lastModifiedAt(establishment.getLastModifiedAt())
                    .deletedBy(establishment.getDeletedBy())
                    .deletedAt(establishment.getDeletedAt())
                    .build();

            establishmentDtos.add(establishmentDto);
        }

        return establishmentDtos;
    }

    @Override
    public EstablishmentDto toDto(Establishment establishment) {
        return  EstablishmentDto.builder()
                .id(establishment.getId())
                .logoImage(establishment.getLogoImage())
                .name(establishment.getName())
                .location(establishment.getLocation())
                .ownerId(establishment.getOwnerId())
                .positions(positionMapper.toDtos(establishment.getPositions()))
                .createdBy(establishment.getCreatedBy())
                .createdAt(establishment.getCreatedAt())
                .lastModifiedBy(establishment.getLastModifiedBy())
                .lastModifiedAt(establishment.getLastModifiedAt())
                .deletedBy(establishment.getDeletedBy())
                .deletedAt(establishment.getDeletedAt())
                .build();
    }

    @Override
    public CommonEstablishmentDto toCommonEstablishmentDto(Establishment establishment) {
        return  CommonEstablishmentDto.builder()
                .id(establishment.getId())
                .name(establishment.getName())
                .location(establishment.getLocation())
                .ownerId(establishment.getOwnerId())
                .positions(positionMapper.toCommonPositionsDto(establishment.getPositions()))
                .createdBy(establishment.getCreatedBy())
                .createdAt(establishment.getCreatedAt())
                .lastModifiedBy(establishment.getLastModifiedBy())
                .lastModifiedAt(establishment.getLastModifiedAt())
                .deletedBy(establishment.getDeletedBy())
                .deletedAt(establishment.getDeletedAt())
                .build();
    }


}
