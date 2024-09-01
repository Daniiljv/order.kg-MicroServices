package my.code.establishment.services.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my.code.establishment.dtos.CreateEstablishmentDto;
import my.code.establishment.dtos.EstablishmentDto;
import my.code.establishment.entities.Establishment;
import my.code.establishment.mappers.EstablishmentMapper;
import my.code.establishment.repositories.EstablishmentRepo;
import my.code.establishment.services.EstablishmentService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor

public class EstablishmentServiceImpl implements EstablishmentService {

    private final EstablishmentRepo establishmentRepo;

    private final EstablishmentMapper establishmentMapper;

    @Override
    @Transactional
    public Long create(CreateEstablishmentDto establishmentDto) {

        try {

            Establishment establishmentToCreate = Establishment.builder()
                    .name(establishmentDto.getName())
                    .location(establishmentDto.getLocation())
                    .ownerId(establishmentDto.getOwnerId())
                    .createdAt(new Timestamp(System.currentTimeMillis()))
                    .build();

            return establishmentRepo.save(establishmentToCreate).getId();
        }
        catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException("Error creating establishment: ", e);
        }
    }

    @Override
    public List<EstablishmentDto> findAllActive() {

        List<Establishment> establishments = establishmentRepo.findAllActive()
                .orElseThrow(() -> new NullPointerException("No establishments found"));

        return establishmentMapper.toDtos(establishments);
    }

    @Override
    public Establishment findActiveById(Long id) {

        return establishmentRepo.findActiveById(id)
                .orElseThrow(() -> new IllegalArgumentException("No establishment found with id: " + id));

    }

    @Override
    public EstablishmentDto findActiveByName(String establishmentName) {

        Establishment establishment = establishmentRepo.findActiveByName(establishmentName)
                .orElseThrow(() -> {
                    log.error("No establishment found with name: %s".formatted(establishmentName));
                    return new IllegalArgumentException("No establishment found with name: %s".formatted(establishmentName));
                });

        return establishmentMapper.toDto(establishment);
    }

    @Override
    @Transactional
    public String rename(Long id, String newName) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Establishment establishmentToUpdate = findActiveById(id);

        establishmentToUpdate.setName(newName);
        establishmentToUpdate.setLastModifiedBy(authentication.getName());
        establishmentToUpdate.setLastModifiedAt(new Timestamp(System.currentTimeMillis()));
        establishmentRepo.save(establishmentToUpdate);

        return "Establishment has been updated successfully";
    }

    @Override
    public String delete(Long id) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Establishment establishmentToDelete = findActiveById(id);

        establishmentToDelete.setDeletedBy(authentication.getName());
        establishmentToDelete.setDeletedAt(new Timestamp(System.currentTimeMillis()));
        establishmentRepo.save(establishmentToDelete);

        return "Establishment has been deleted successfully";
    }

    @Override
    public List<Establishment> tempFindAllActive() {
        return establishmentRepo.findAll();
    }
}
