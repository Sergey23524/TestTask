package ru.example.testtask.security.model;

import lombok.Data;
import org.jetbrains.annotations.NotNull;

@Data
public class AuthenticationRequestRest {
    @NotNull
    private String login;
    @NotNull
    private String password;
}
