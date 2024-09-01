package my.code.admin.services;

import my.code.common.dtos.RegisterOwnerDto;
import org.springframework.stereotype.Service;

@Service
public interface OwnersService {

    Long register(RegisterOwnerDto registerOwnerDto);

    Long addEstablishment(Long userId, Long establishmentId);
}

