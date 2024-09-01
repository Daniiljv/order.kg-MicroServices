package my.code.admin.services;

import my.code.admin.dtos.SuccessfullyCreatedEstablishmentDto;
import my.code.common.dtos.CreateEstablishmentDto;
import org.springframework.stereotype.Service;

@Service
public interface EstablishmentService {

    SuccessfullyCreatedEstablishmentDto createEstablishment(CreateEstablishmentDto createEstablishmentDto);
}
