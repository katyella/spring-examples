package dev.katyella.auth0.repositories;

import dev.katyella.auth0.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByCreatedBy(String createdBy);

}
