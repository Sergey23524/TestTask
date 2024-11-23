package ru.example.testtask.rest.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReaderUnreturnedBooksRest {
    private ReaderRest reader;
    private Integer count;
}
