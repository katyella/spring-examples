package dev.katyella.auth0.repositories;

import java.util.Optional;

import dev.katyella.auth0.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

}
