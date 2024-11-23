package ru.example.testtask.rest.mapper;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import ru.example.testtask.model.AuthorEntity;
import ru.example.testtask.rest.model.AuthorRest;

@Component
public class AuthorMapper {
    public AuthorRest mapToRest(@NotNull AuthorEntity entity) {
        return AuthorRest.builder()
                .id(entity.getId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .birthDate(entity.getBirthDate())
                .build();
    }
}
