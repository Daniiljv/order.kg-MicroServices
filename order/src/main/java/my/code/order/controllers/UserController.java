package my.code.order.controllers;

import lombok.RequiredArgsConstructor;
import my.code.order.dtos.RegisterUserDto;
import my.code.order.services.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public RegisterUserDto register(@RequestBody RegisterUserDto registerUserDto) {
        return userService.register(registerUserDto);
    }

}
