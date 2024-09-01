package my.code.admin.services;

import my.code.admin.dtos.RegisterUserDto;
import my.code.admin.entities.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    RegisterUserDto register(RegisterUserDto userToRegister);

    User findByUsername(String username);

    User findById(Long id);
}
