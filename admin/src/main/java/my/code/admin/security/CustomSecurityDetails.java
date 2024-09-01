package my.code.admin.security;

import my.code.admin.filters.CustomAuthenticationFilter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

@EnableWebSecurity

public class CustomSecurityDetails extends AbstractHttpConfigurer<CustomSecurityDetails, HttpSecurity> {

    @Override
    public void configure(HttpSecurity http) {
        AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
        CustomAuthenticationFilter filter = new CustomAuthenticationFilter(authenticationManager);
        filter.setFilterProcessesUrl("/api/login");
        http.addFilter(filter);
    }

    public static CustomSecurityDetails customDsl() {
        return new CustomSecurityDetails();
    }
}
