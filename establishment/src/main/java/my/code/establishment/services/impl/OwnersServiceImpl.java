package my.code.establishment.services.impl;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import my.code.common.dtos.RegisterOwnerDto;
import my.code.establishment.entities.Establishment;
import my.code.establishment.entities.Owner;
import my.code.establishment.repositories.OwnersRepo;
import my.code.establishment.services.EstablishmentService;
import my.code.establishment.services.OwnersService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class OwnersServiceImpl implements OwnersService {

    private final OwnersRepo ownersRepo;
    private final EstablishmentService establishmentService;

    @Override
    public Long register(RegisterOwnerDto registerOwnerDto) {

        Owner owner = Owner.builder()
                .firstName(registerOwnerDto.getFirstName())
                .lastName(registerOwnerDto.getLastName())
                .email(registerOwnerDto.getEmail())
                .phone(registerOwnerDto.getPhone())
                .establishments(new ArrayList<>())
                .build();

        return ownersRepo.save(owner).getId();
    }

    @Override
    @Transactional
    public Long addEstablishment(HashMap<String, Long> ids) {

        Establishment establishmentToAdd = establishmentService.findActiveById(ids.get("EstablishmentId"));
        Owner owner = findActiveById(ids.get("OwnerId"));

        owner.getEstablishments().size();
        owner.getEstablishments().add(establishmentToAdd);
        ownersRepo.save(owner);

        return establishmentToAdd.getId();
    }

    @Override
    public Owner findActiveById(Long ownerId) {
        return ownersRepo.findById(ownerId)
                .orElseThrow(()-> new NotFoundException("Owner with id - %s not found".formatted(ownerId)));
    }
}
