package dev.katyella.oauth.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
@Table(name = "books")
public class Book {

    // Getter and Setter methods
    // Make sure to have getters and setters for all fields
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Setter
    @Column(name = "created_by")
    private String createdBy;

    @Setter
    @Column(name = "author_first_name", nullable = false)
    private String authorFirstName;

    @Setter
    @Column(name = "author_last_name", nullable = false)
    private String authorLastName;

    @Setter
    @Column(name = "title", nullable = false)
    private String title;

    // Default constructor
    public Book() {
    }

    // Constructor with parameters
    public Book(String createdBy, String authorFirstName, String authorLastName, String title) {
        this.createdBy = createdBy;
        this.authorFirstName = authorFirstName;
        this.authorLastName = authorLastName;
        this.title = title;
    }

}
