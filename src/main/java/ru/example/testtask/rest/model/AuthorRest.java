package ru.example.testtask.rest.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class AuthorRest {
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDateTime birthDate;
}
