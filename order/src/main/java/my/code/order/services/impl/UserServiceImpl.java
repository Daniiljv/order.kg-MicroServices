package my.code.order.services.impl;

import lombok.RequiredArgsConstructor;
import my.code.order.dtos.RegisterUserDto;
import my.code.order.entities.Role;
import my.code.order.entities.User;
import my.code.order.repositories.RoleRepo;
import my.code.order.repositories.UserRepo;
import my.code.order.services.UserService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username).
                orElseThrow(() -> new UsernameNotFoundException
                        ("User with username %s is not found".formatted(username)));

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

    @Override
    public RegisterUserDto register(RegisterUserDto userToRegister) {

        if(!isEmailUnique(userToRegister.getEmail())){
            throw new IllegalArgumentException
                    ("User with email - %s is already registered".formatted(userToRegister.getEmail()));
        }

        if(!isUsernameUnique(userToRegister.getUsername())){
            throw new IllegalArgumentException
                    ("User whose username - %s is already registered".formatted(userToRegister.getUsername()));
        }

        Role roleUser = roleRepo.findByName("USER").
                orElseThrow(()-> new IllegalArgumentException("Role User is not available now!"));
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(roleUser);

        User user = User.builder()
                .username(userToRegister.getUsername())
                .email(userToRegister.getEmail())
                .password(passwordEncoder.encode(userToRegister.getPassword()))
                .roles(userRoles)
                .build();

        userToRegister.setId(userRepo.save(user).getId());

        return userToRegister;
    }

    @Override
    public User findByUsername(String username) {
        return null;
    }

    @Override
    public User findById(Long id) {
        return null;
    }


    private boolean isUsernameUnique(String username) {
        return userRepo.findByUsername(username).isEmpty();
    }

    private boolean isEmailUnique(String email) {
        return userRepo.findByEmail(email).isEmpty();
    }

}
