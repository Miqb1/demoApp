package com.klaudiusz.demonstration.controller;

import com.klaudiusz.demonstration.dto.BookDto;
import com.klaudiusz.demonstration.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookController {

    BookService bookService;

    BookController(final BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("book")
    ResponseEntity<List<BookDto>> getAllBooks() {
        final BookDto firstBook = new BookDto(1L, "Biblia");
        bookService.create(firstBook);

        final BookDto secondBook = new BookDto(2L, "Ania z zielonych szczytów");
        bookService.create(secondBook);

        final BookDto thirdBook = new BookDto(3L, "Potop");
        bookService.create(thirdBook);

        final List<BookDto> booklist = bookService.list();

        return new ResponseEntity<>(booklist, HttpStatus.CREATED);
    }
}