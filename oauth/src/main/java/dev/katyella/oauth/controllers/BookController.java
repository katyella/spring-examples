package dev.katyella.oauth.controllers;

import dev.katyella.oauth.models.Book;
import dev.katyella.oauth.repositories.BookRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class BookController {
    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/books")
    public List <Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @GetMapping("/books/{id}")
    public ResponseEntity <Book> getBookById(@PathVariable(value = "id") Long bookId)
        throws ConfigDataResourceNotFoundException {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found for this id :: " + bookId));
        return ResponseEntity.ok().body(book);
    }

    @PostMapping("/books")
    public Book createBook(@Valid @RequestBody Book book) {
        return bookRepository.save(book);
    }

    @PutMapping("/books/{id}")
    public ResponseEntity <Book> updateBook(@PathVariable(value = "id") Long bookId, @Valid @RequestBody Book bookDetails) throws ConfigDataResourceNotFoundException {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found for this id :: " + bookId));

        book.setAuthorFirstName(bookDetails.getAuthorFirstName());
        book.setAuthorLastName(bookDetails.getAuthorLastName());
        book.setTitle(book.getTitle());
        final Book updatedBook = bookRepository.save(book);
        return ResponseEntity.ok(updatedBook);
    }

    @DeleteMapping("/books/{id}")
    public Map <String, Boolean > deleteBook(@PathVariable(value = "id") Long bookId) throws ConfigDataResourceNotFoundException {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found for this id :: " + bookId));

        bookRepository.delete(book);
        Map <String, Boolean> response = new HashMap <> ();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
