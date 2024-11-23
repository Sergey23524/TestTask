package ru.example.testtask.model;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
public class AuthorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDateTime birthDate;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "author_entity_book_entities",
            joinColumns = @JoinColumn(name = "author_entity_id"),
            inverseJoinColumns = @JoinColumn(name = "book_entity_id")
    )
    private List<BookEntity> books;
}
