package ru.example.testtask.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.example.testtask.rest.model.AuthorRest;
import ru.example.testtask.rest.model.ReaderRest;
import ru.example.testtask.rest.model.ReaderUnreturnedBooksRest;
import ru.example.testtask.rest.model.SearchParams;
import ru.example.testtask.service.AuthorService;
import ru.example.testtask.service.OperationService;
import ru.example.testtask.service.ReaderService;

import java.util.List;

import static ru.example.testtask.utils.NullSafe.id;

@RestController
@AllArgsConstructor
public class RestApi {

    private final OperationService operationService;
    private final AuthorService authorService;
    private final ReaderService readerService;

    @Operation(summary = "Вызвать операцию для книги и читателя",
            description = "Выполняет определённое действие для книги и читателя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Ууспешно"),
            @ApiResponse(responseCode = "400", description = "Некорректный запрос"),
            @ApiResponse(responseCode = "404", description = "Книга или читатель не найдены")
    })
    @RequestMapping(method = RequestMethod.POST, value = "/book/{book_id}/reader/{reader_id}/action")
    public ResponseEntity<Void> callOperationAction(@PathVariable(value = "book_id") Integer bookId, @PathVariable(value = "reader_id") Integer readerId) {
        operationService.callOperationAction(id(bookId), id(readerId));
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Получить самого популярного автора",
            description = "Возвращает информацию о самом популярном авторе")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешно"),
            @ApiResponse(responseCode = "400", description = "Некорректный запрос")
    })
    @RequestMapping(method = RequestMethod.GET, value = "/author/popular")
    public ResponseEntity<AuthorRest> getMostPopularAuthor(@RequestBody SearchParams params) {
        return ResponseEntity.ok(authorService.findMostPopularAuthor(params));
    }

    @Operation(summary = "Самый активный читатель",
            description = "Возвращает информацию о самом активном читателе")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешно")
    })
    @RequestMapping(method = RequestMethod.GET, value = "/reader/popular")
    public ResponseEntity<ReaderRest> getMostActiveReader() {
        return ResponseEntity.ok(readerService.findMostActiveReader());
    }

    @Operation(summary = "Читателей с количеством невозвращенных книг",
            description = "Возвращает список читателей и количество книг, которые они не вернули")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешно")
    })
    @RequestMapping(method = RequestMethod.GET, value = "/unreturnedBooks")
    public ResponseEntity<List<ReaderUnreturnedBooksRest>> getReadersAndCountOfUnreturnedBooks() {
        return ResponseEntity.ok(readerService.findReadersAndCountOfUnreturnedBooks());
    }
}
