package my.code.establishment.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my.code.establishment.dtos.CreatePositionDto;
import my.code.establishment.dtos.PositionDto;
import my.code.establishment.entities.Establishment;
import my.code.establishment.entities.Position;
import my.code.establishment.mappers.PositionMapper;
import my.code.establishment.repositories.PositionRepo;
import my.code.establishment.services.EstablishmentService;
import my.code.establishment.services.PositionService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PositionServiceImpl implements PositionService {

    private final PositionRepo positionRepo;

    private final EstablishmentService establishmentService;

    private final PositionMapper positionMapper;

    @Override
    public Long create(CreatePositionDto createPositionDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Establishment establishment = establishmentService.findActiveById(createPositionDto.getEstablishmentId());

        try {
            Position position = Position.builder()
                    .name(createPositionDto.getName())
                    .establishment(establishment)
                    .createdBy(authentication.getName())
                    .createdAt(new Timestamp(System.currentTimeMillis()))
                    .build();

            return positionRepo.save(position).getId();
        }
        catch (Exception e){
            log.error(e.getMessage());
            throw new RuntimeException("Error creating position");
        }
    }

    @Override
    public String rename(Long id, String name) {
        Position position = findActiveById(id);

        position.setName(name);
        positionRepo.save(position);

        return "Name has been updated Successfully";
    }

    @Override
    public Position findActiveById(Long id) {
        return positionRepo.findActiveById(id)
                .orElseThrow(() -> new IllegalArgumentException("No position found with id: " + id));

    }


    @Override
    public List<PositionDto> findByEstablishmentId(Long id) {
        Establishment establishment = establishmentService.findActiveById(id);

        return positionMapper.toDtos(establishment.getPositions());
    }

    @Override
    public List<PositionDto> findAllActive() {
        List<Position> positions = positionRepo.findAllActive()
                .orElseThrow(() -> new NullPointerException("No position found"));
        return positionMapper.toDtos(positions);
    }

    @Override
    public String delete(Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Position position = findActiveById(id);

        position.setDeletedBy(authentication.getName());
        position.setDeletedAt(new Timestamp(System.currentTimeMillis()));
        positionRepo.save(position);

        return "Position has been deleted Successfully";
    }
}
