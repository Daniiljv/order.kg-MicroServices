package my.code.establishment.controllers;

import lombok.RequiredArgsConstructor;
import my.code.establishment.dtos.CreateEstablishmentDto;
import my.code.establishment.dtos.CustomResponseDto;
import my.code.establishment.dtos.EstablishmentDto;
import my.code.establishment.enums.StatusCode;
import my.code.establishment.services.EstablishmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/establishments")
@RequiredArgsConstructor

public class EstablishmentController {

    private final EstablishmentService establishmentService;

    @PostMapping("/create")
    public CustomResponseDto<Long> create(@RequestBody CreateEstablishmentDto establishmentDto){
        try {
            return new CustomResponseDto<>(StatusCode.CREATED, establishmentService.create(establishmentDto),
                    "Successful");
        } catch (Exception e){
            return new CustomResponseDto<>(StatusCode.BAD_REQUEST, null,
                    e.getMessage());
        }
    }

    @GetMapping("/findAllActive")
    public CustomResponseDto<List<EstablishmentDto>> findAllActive(){
        try {
            return new CustomResponseDto<>(StatusCode.OK, establishmentService.findAllActive(),
                    "Successful");
        } catch (NullPointerException nullPointerException){
            return new CustomResponseDto<>(StatusCode.NOT_FOUND, null,
                    nullPointerException.getMessage());
        }
    }

    @GetMapping("/findActiveByName")
    public CustomResponseDto<EstablishmentDto> findActiveByName(@RequestParam String establishmentName){
        try {
             return new CustomResponseDto<>(StatusCode.OK, establishmentService.findActiveByName(establishmentName),
                     "Successful");
        } catch (IllegalArgumentException illegalArgumentException){
            return new CustomResponseDto<>(StatusCode.BAD_REQUEST, null,
                    illegalArgumentException.getMessage());
        }
    }

    @PutMapping("/rename")
    public CustomResponseDto<String> rename(@RequestParam Long id, @RequestParam String newName){
        try {
            return new CustomResponseDto<>(StatusCode.OK, establishmentService.rename(id, newName),
                    "Successful");
        } catch (IllegalArgumentException illegalArgumentException){
            return new CustomResponseDto<>(StatusCode.BAD_REQUEST, null,
                    illegalArgumentException.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public CustomResponseDto<String> delete(@PathVariable Long id){
        try {
            return new CustomResponseDto<>(StatusCode.OK, establishmentService.delete(id),
                    "Successful");
        } catch (IllegalArgumentException illegalArgumentException){
            return new CustomResponseDto<>(StatusCode.BAD_REQUEST, null,
                    illegalArgumentException.getMessage());
        }
    }
}
