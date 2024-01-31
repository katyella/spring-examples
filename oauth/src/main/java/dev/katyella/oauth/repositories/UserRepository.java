package dev.katyella.oauth.repositories;

import java.util.Optional;

import dev.katyella.oauth.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

}
