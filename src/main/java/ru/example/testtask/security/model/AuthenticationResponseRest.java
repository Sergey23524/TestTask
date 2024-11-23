package ru.example.testtask.security.model;

import lombok.Builder;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

@Data
@Builder
public class AuthenticationResponseRest {
    @NotNull
    private String token;
    @NotNull
    private String refreshToken;
}
