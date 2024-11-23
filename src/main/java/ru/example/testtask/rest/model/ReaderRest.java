package ru.example.testtask.rest.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ReaderRest {
    private Long id;
    private String phoneNumber;
    private String firstName;
    private String lastName;
    private String gender;
    private LocalDateTime birthDate;
}
