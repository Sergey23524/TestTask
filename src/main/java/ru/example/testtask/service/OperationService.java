package ru.example.testtask.service;

import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import ru.example.testtask.model.BookEntity;
import ru.example.testtask.model.OperationEntity;
import ru.example.testtask.model.OperationType;
import ru.example.testtask.model.ReaderEntity;
import ru.example.testtask.repository.OperationRepository;

import java.time.LocalDateTime;

import static ru.example.testtask.model.OperationType.RETURN;
import static ru.example.testtask.model.OperationType.TOOK;

@Service
@AllArgsConstructor
public class OperationService {

    private final OperationRepository operationRepository;

    public void callOperationAction(@NotNull Long bookId, @NotNull Long readerId) {
        String operation = operationRepository.findLastBookOperation(bookId);
        if (operation != null && OperationType.valueOf(operation) == TOOK) {
            returnBook(bookId, readerId);
        } else {
            tookBook(bookId, readerId);
        }
    }

    private void tookBook(Long bookId, Long readerId) {
        operationRepository.save(buildEntity(bookId, readerId, TOOK));
    }

    private void returnBook(Long bookId, Long readerId) {
        operationRepository.save(buildEntity(bookId, readerId, RETURN));
    }

    private OperationEntity buildEntity(Long bookId, Long readerId, OperationType operationType) {
        return OperationEntity.builder()
                .operationType(operationType)
                .operationTime(LocalDateTime.now())
                .readerEntity(ReaderEntity.builder().id(readerId).build())
                .bookEntity(BookEntity.builder().id(bookId).build())
                .build();
    }
}