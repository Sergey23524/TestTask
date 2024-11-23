package ru.example.testtask.security.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ErrorResponseRest {
    private Integer code;
    private String message;
    private List<String> details;
}
