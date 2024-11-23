package ru.example.testtask.rest.mapper;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import ru.example.testtask.model.ReaderEntity;
import ru.example.testtask.rest.model.ReaderRest;
import ru.example.testtask.rest.model.ReaderUnreturnedBooksRest;

@Component
public class ReaderMapper {
    public ReaderRest mapToRest(@NotNull ReaderEntity entity) {
        return ReaderRest.builder()
                .id(entity.getId())
                .phoneNumber(entity.getPhoneNumber())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .birthDate(entity.getBirthDate())
                .gender(entity.getGender())
                .build();
    }

    public ReaderUnreturnedBooksRest mapToRest(@NotNull ReaderEntity entity, Integer count) {
        return ReaderUnreturnedBooksRest.builder().reader(mapToRest(entity)).count(count).build();
    }
}
