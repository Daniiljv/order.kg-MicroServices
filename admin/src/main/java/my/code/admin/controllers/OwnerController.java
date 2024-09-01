package my.code.admin.controllers;

import lombok.RequiredArgsConstructor;
import my.code.admin.services.OwnersService;
import my.code.common.dtos.RegisterOwnerDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/owners")
@RequiredArgsConstructor
public class OwnerController {

    private final OwnersService ownersService;

    @PostMapping
    public Long register(@RequestBody RegisterOwnerDto registerOwnerDto) {

        return ownersService.register(registerOwnerDto);
    }

    @PutMapping("/addEstablishment")
    public Long addEstablishment(@RequestParam Long ownerId,
                                 @RequestParam Long establishmentId) {

        return ownersService.addEstablishment(ownerId, establishmentId);
    }
}
