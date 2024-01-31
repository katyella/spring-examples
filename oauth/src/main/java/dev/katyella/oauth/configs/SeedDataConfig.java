package dev.katyella.oauth.configs;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import dev.katyella.oauth.models.Role;
import dev.katyella.oauth.models.User;
import dev.katyella.oauth.repositories.UserRepository;
import dev.katyella.oauth.services.UserService;

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
        }
    }

}
