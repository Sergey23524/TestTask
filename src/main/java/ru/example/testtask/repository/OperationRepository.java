package ru.example.testtask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.example.testtask.model.OperationEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface OperationRepository extends JpaRepository<OperationEntity, Long> {

    // Получения самого популярного автора в диапазоне дат
    @NativeQuery("select author_entity_id from operation_entity op\n" +
            "\tinner join author_entity_book_entities aebe on op.book_entity_id = aebe.book_entity_id\n" +
            " where operation_type = 'TOOK' and operation_time >= :startDate and operation_time <= :endDate group by author_entity_id order by count(*) desc limit 1")
    Optional<Long> findMostPopularAuthor(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    // Запрос для получения самого активного читателя
    @NativeQuery("select reader_entity_id from operation_entity op\n" +
            " where operation_type = 'TOOK' group by reader_entity_id order by count(*) desc limit 1")
    Optional<Long> findMostActiveReaders();

    // Запрос для подсчета количества книг, которые не были возвращены
    @NativeQuery("select reader_entity_id, count(*) as count from operation_entity op where operation_type = 'TOOK' and not exists\n" +
            "\t(select * from operation_entity op1 where operation_type = 'RETURN' \n" +
            "\tand op.book_entity_id=op1.book_entity_id and op.reader_entity_id=op1.reader_entity_id\n" +
            "\tand op1.operation_time>op.operation_time) group by reader_entity_id order by count(*) desc")
    List<Map<String, Long>> findReadersAndCountOfUnreturnedBooks();

    // Получение последнего действия с книгой
    @Query("SELECT t.operationType FROM OperationEntity t WHERE t.bookEntity.id = :bookId and t.operationTime = (select max(t1.operationTime) FROM OperationEntity t1 WHERE t1.bookEntity.id = :bookId)")
    String findLastBookOperation(@Param("bookId") Long bookId);
}