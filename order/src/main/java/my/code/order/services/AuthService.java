package my.code.order.services;

import my.code.order.dtos.AuthenticatedUserDto;

import org.springframework.stereotype.Service;

@Service
public interface AuthService {

    AuthenticatedUserDto authAttempt(String username, String password);
}
