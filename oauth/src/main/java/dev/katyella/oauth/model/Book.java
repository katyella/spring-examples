package dev.katyella.oauth.model;

import jakarta.persistence.*;

@Entity
@Table(name = "books")
public class Book {

    private long id;
    private String authorFirstName;
    private String authorLastName;
    private String title;

    public Book() {

    }

    public Book(String authorFirstName, String authorLastName, String title) {
        this.authorFirstName = authorFirstName;
        this.authorLastName = authorLastName;
        this.title = title;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "author_first_name", nullable = false)
    public String getAuthorFirstName() {
        return authorFirstName;
    }
    public void setAuthorFirstName(String authorFirstName) {
        this.authorFirstName = authorFirstName;
    }

    @Column(name = "author_last_name", nullable = false)
    public String getAuthorLastName() {
        return authorLastName;
    }
    public void setAuthorLastName(String authorLastName) {
        this.authorLastName = authorLastName;
    }

    @Column(name = "title", nullable = false)
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
}
