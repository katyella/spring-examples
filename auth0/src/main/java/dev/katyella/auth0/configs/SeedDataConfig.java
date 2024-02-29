package dev.katyella.auth0.configs;

import dev.katyella.auth0.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import dev.katyella.auth0.models.Role;
import dev.katyella.auth0.models.User;
import dev.katyella.auth0.repositories.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class SeedDataConfig implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    @Override
    public void run(String... args) throws Exception {

        if (userRepository.count() == 0) {

            User admin = User
                    .builder()
                    .firstName("admin")
                    .lastName("admin")
                    .email("admin@admin.com")
                    .password(passwordEncoder.encode("password"))
                    .role(Role.ROLE_ADMIN)
                    .build();

            userService.save(admin);
            log.debug("created ADMIN user - {}", admin);

            User user1 = User.builder()
                    .firstName("John")
                    .lastName("Doe")
                    .email("john.doe@example.com")
                    .password(passwordEncoder.encode("password1"))
                    .role(Role.ROLE_USER)
                    .build();

            userService.save(user1);
            log.debug("created USER user - {}", user1);

            User user2 = User.builder()
                    .firstName("Jane")
                    .lastName("Smith")
                    .email("jane.smith@example.com")
                    .password(passwordEncoder.encode("password2"))
                    .role(Role.ROLE_USER)
                    .build();

            userService.save(user2);
            log.debug("created USER user - {}", user2);

        }
    }

}
