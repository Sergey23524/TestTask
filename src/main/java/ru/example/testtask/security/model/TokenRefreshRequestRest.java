package ru.example.testtask.security.model;

import lombok.Data;
import org.jetbrains.annotations.NotNull;

@Data
public class TokenRefreshRequestRest {
    @NotNull
    private String refreshToken;
}
