package ru.example.testtask.rest.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.Nullable;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchParams {
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Nullable
    private LocalDateTime filterFrom;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Nullable
    private LocalDateTime filterTo;
}
