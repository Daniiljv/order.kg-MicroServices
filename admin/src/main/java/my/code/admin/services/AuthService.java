package my.code.admin.services;

import my.code.admin.dtos.AuthenticatedUserDto;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {

    AuthenticatedUserDto authAttempt(String username, String password);
}
