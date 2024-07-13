package my.code.establishment.controllers;

import lombok.RequiredArgsConstructor;
import my.code.establishment.dtos.CustomResponseDto;
import my.code.establishment.dtos.PositionDto;
import my.code.establishment.enums.StatusCode;
import my.code.establishment.services.PositionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/positions")
@RequiredArgsConstructor
public class PositionController {

    private final PositionService positionService;

    @PostMapping("/create")
    public CustomResponseDto<Long> create(@RequestParam String name) {
        try {
            return new CustomResponseDto<>(StatusCode.CREATED, positionService.create(name),
                    "Successful");
        }
        catch (Exception e) {
            return new CustomResponseDto<>(StatusCode.BAD_REQUEST, null,
                    e.getMessage());
        }
    }

    @PutMapping("/rename")
    public CustomResponseDto<String> rename (@RequestParam Long id, @RequestParam String name){
        try {
            return new CustomResponseDto<>(StatusCode.OK, positionService.rename(id, name),
                    "Successful");
        }
        catch (IllegalArgumentException illegalArgumentException){
            return new CustomResponseDto<>(StatusCode.BAD_REQUEST, null,
                    illegalArgumentException.getMessage());
        }
    }

    @GetMapping("/findByEstablishmentId/{establishmentId}")
    public CustomResponseDto<List<PositionDto>> findByEstablishmentId(@PathVariable Long establishmentId){
        try {
            return new CustomResponseDto<>(StatusCode.OK, positionService.findByEstablishmentId(establishmentId),
                    "Successful");
        }
        catch (NullPointerException nullPointerException){
            return new CustomResponseDto<>(StatusCode.NOT_FOUND, null,
                    nullPointerException.getMessage());
        }
        catch (IllegalArgumentException illegalArgumentException){
            return new CustomResponseDto<>(StatusCode.BAD_REQUEST, null,
                    illegalArgumentException.getMessage());
        }
    }

    @GetMapping("/findAllActive")
    public CustomResponseDto<List<PositionDto>> findAllActive(){
        try {
            return new CustomResponseDto<>(StatusCode.OK, positionService.findAllActive(),
                    "Successful");
        }
        catch (NullPointerException nullPointerException){
            return new CustomResponseDto<>(StatusCode.NOT_FOUND, null,
                    nullPointerException.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public CustomResponseDto<String> delete (@PathVariable Long id){
        try {
            return new CustomResponseDto<>(StatusCode.OK, positionService.delete(id),
                    "Successful");
        }
        catch (IllegalArgumentException illegalArgumentException){
            return new CustomResponseDto<>(StatusCode.BAD_REQUEST, null,
                    illegalArgumentException.getMessage());
        }
    }
}
