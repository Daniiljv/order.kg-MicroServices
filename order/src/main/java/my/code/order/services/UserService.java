package my.code.order.services;

import my.code.order.dtos.RegisterUserDto;
import my.code.order.entities.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    RegisterUserDto register(RegisterUserDto userToRegister);

    User findByUsername(String username);

    User findById(Long id);
}
