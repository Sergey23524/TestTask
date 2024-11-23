package ru.example.testtask.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.example.testtask.repository.OperationRepository;
import ru.example.testtask.repository.ReaderRepository;
import ru.example.testtask.rest.mapper.ReaderMapper;
import ru.example.testtask.rest.model.ReaderRest;
import ru.example.testtask.rest.model.ReaderUnreturnedBooksRest;

import java.util.List;
import java.util.Optional;

import static ru.example.testtask.utils.NullSafe.id;

@Service
@AllArgsConstructor
public class ReaderService {
    private final OperationRepository operationRepository;
    private final ReaderRepository readerRepository;
    private final ReaderMapper mapper;

    public ReaderRest findMostActiveReader() {
        Optional<Long> id = operationRepository.findMostActiveReaders();
        return id.map(identifier -> mapper.mapToRest(readerRepository.findById(identifier).orElseThrow())).orElse(null);
    }

    public List<ReaderUnreturnedBooksRest> findReadersAndCountOfUnreturnedBooks() {
        var reader = operationRepository.findReadersAndCountOfUnreturnedBooks();
        return reader.stream().map(r -> mapper.mapToRest(readerRepository.findById(r.get("reader_entity_id")).orElseThrow(), id(r.get("count")))).toList();
    }
}
