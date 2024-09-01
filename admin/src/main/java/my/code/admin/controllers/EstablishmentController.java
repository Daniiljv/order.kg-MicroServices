package my.code.admin.controllers;

import lombok.RequiredArgsConstructor;
import my.code.admin.dtos.SuccessfullyCreatedEstablishmentDto;
import my.code.admin.services.EstablishmentService;
import my.code.common.dtos.CreateEstablishmentDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/establishments")
@RequiredArgsConstructor
public class EstablishmentController {

    private final EstablishmentService establishmentService;

    @PostMapping
    public SuccessfullyCreatedEstablishmentDto createEstablishment(@RequestBody CreateEstablishmentDto establishmentDto) {
        return establishmentService.createEstablishment(establishmentDto);
    }
}
