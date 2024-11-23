package ru.example.testtask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.example.testtask.model.ReaderEntity;

@Repository
public interface ReaderRepository extends JpaRepository<ReaderEntity, Long> {
}