package ru.example.testtask.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.example.testtask.repository.AuthorRepository;
import ru.example.testtask.repository.OperationRepository;
import ru.example.testtask.rest.mapper.AuthorMapper;
import ru.example.testtask.rest.model.AuthorRest;
import ru.example.testtask.rest.model.SearchParams;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthorService {
    private final OperationRepository operationRepository;
    private final AuthorRepository authorRepository;
    private final AuthorMapper mapper;

    public AuthorRest findMostPopularAuthor(SearchParams params) {
        Optional<Long> id = operationRepository.findMostPopularAuthor(params.getFilterFrom(), params.getFilterTo());
        return id.map(identifier -> mapper.mapToRest(authorRepository.findById(identifier).orElseThrow())).orElse(AuthorRest.builder().build());
    }
}
