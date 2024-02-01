package dev.katyella.oauth.repositories;

import dev.katyella.oauth.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByCreatedBy(String createdBy);

}
