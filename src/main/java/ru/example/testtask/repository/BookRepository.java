package ru.example.testtask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.example.testtask.model.BookEntity;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {
}
