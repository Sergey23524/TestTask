package ru.example.testtask.model;

import jakarta.persistence.*;
import lombok.Builder;

import java.time.LocalDateTime;

@Entity
@Builder
public class OperationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private OperationType operationType;
    private LocalDateTime operationTime;
    @ManyToOne(fetch = FetchType.LAZY)
    private ReaderEntity readerEntity;
    @ManyToOne(fetch = FetchType.LAZY)
    private BookEntity bookEntity;
}