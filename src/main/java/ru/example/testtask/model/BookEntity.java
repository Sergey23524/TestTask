package ru.example.testtask.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Entity
@Builder
@Getter
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private Integer year;
    @ManyToMany(mappedBy = "books", fetch = FetchType.LAZY)
    private List<AuthorEntity> authors;
}
