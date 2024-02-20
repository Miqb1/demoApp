package com.klaudiusz.demonstration.service;

import com.klaudiusz.demonstration.dto.BookDto;
import com.klaudiusz.demonstration.mapper.BookMapper;
import com.klaudiusz.demonstration.model.Book;
import com.klaudiusz.demonstration.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@SuppressWarnings("unused")
public class BookService {

    private static final Logger BookLOGGER = LoggerFactory.getLogger(BookService.class);
    private BookRepository bookRepository;

    // Retrieves a list of all books.
    public List<BookDto> list() {
        final List<Book> books = bookRepository.findAll();
        BookLOGGER.info("Book list created");
        return BookMapper.MAPPER.mapListToBookDtoList(books);
    }

    //Creates a book position.
    public BookDto create(final BookDto bookDto) {
        final Book book = bookRepository.save(BookMapper.MAPPER.mapToBook(bookDto));
        BookLOGGER.info("Book No. {} created", bookDto.getId());
        return BookMapper.MAPPER.mapToBookDto(book);
    }
}