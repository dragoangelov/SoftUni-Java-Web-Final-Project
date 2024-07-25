package org.example.deliveryservice.config;

import org.example.deliveryservice.model.enums.UserRoleEnum;
import org.example.deliveryservice.repository.UserRepository;
import org.example.deliveryservice.service.FoodDeliveryUserDetailsService;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests()
                //for the static resources
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                //visible for everyone
                .requestMatchers("/",
                        "/menu",
                        "/menu/**",
                        "/contact",
                        "/users/profile").permitAll()

                .requestMatchers("/users/login"
                        , "/users/register",
                        "/users/login-error",
                        "/",
                        "/contact").anonymous()

                .requestMatchers("/menu/**",
                        "/menu",
                        "/closed",
                        "/contact",
                        "/users/edit/**",
                        "/orders/finalize",
                        "/",
                        "/cart",
                        "/orders/details/**",
                        "/orders/history",
                        "/users/profile").hasRole(UserRoleEnum.USER.name())

                .requestMatchers(
                        "/",
                        "/products/add",
                        "/users/all",
                        "/menu",
                        "/menu/**",
                        "/products/edit/**",
                        "/orders/all/history",
                        "/users/change/**",
                        "/users/profile",
                        "/users/profile/**")
                .hasRole(UserRoleEnum.ADMIN.name())

                .anyRequest()
                .authenticated()

                .and()
                .formLogin()
                .loginPage("/users/login")
                .usernameParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY)
                .passwordParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY)
                .defaultSuccessUrl("/")
                .failureForwardUrl("/users/login-error")

                .and()
                .logout()
                .logoutUrl("/users/logout")
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true);

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return new FoodDeliveryUserDetailsService(userRepository);
    }

}

