package my.code.admin.controllers;

import lombok.RequiredArgsConstructor;
import my.code.admin.services.ImageService;
import my.code.common.dtos.CreatePositionDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/positions")
@RequiredArgsConstructor
public class PositionsController {

    private final ImageService imageService;

    @PostMapping
    public void test(@ModelAttribute CreatePositionDto positionDto) {
        imageService.uploadPositionImage(positionDto.getImage());
    }
}
