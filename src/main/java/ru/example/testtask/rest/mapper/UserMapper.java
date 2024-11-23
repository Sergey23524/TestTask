package ru.example.testtask.rest.mapper;

import ru.example.testtask.model.UserEntity;
import ru.example.testtask.rest.model.UserRest;

import java.util.function.Function;

public class UserMapper {
    public static Function<UserEntity, UserRest> toRest() {
        return user -> UserRest.builder().username(user.getUsername()).build();
    }
}
