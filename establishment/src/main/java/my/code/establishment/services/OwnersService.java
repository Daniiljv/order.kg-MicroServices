package my.code.establishment.services;

import my.code.common.dtos.RegisterOwnerDto;
import my.code.establishment.entities.Owner;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public interface OwnersService {

    Long register(RegisterOwnerDto registerOwnerDto);

    Long addEstablishment(HashMap<String, Long> ids);

    Owner findActiveById(Long ownerId);
}

