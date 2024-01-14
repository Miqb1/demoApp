package com.klaudiusz.demonstration.service;

import com.klaudiusz.demonstration.dto.BookDto;
import com.klaudiusz.demonstration.mapper.BookMapper;
import com.klaudiusz.demonstration.model.Book;
import com.klaudiusz.demonstration.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BookService {

    private final static Logger BookLOGGER = LoggerFactory.getLogger(BookService.class);
    BookRepository bookRepository;

    BookService(final BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    public List<BookDto> list(){
        final List<Book> books = bookRepository.findAll();
        BookLOGGER.info("Booklist created");
        return BookMapper.MAPPER.mapListToBookDtoList(books);
    }

    public BookDto create (final BookDto bookDto){
        final Book book = bookRepository.save(BookMapper.MAPPER.mapToBook(bookDto));
        BookLOGGER.info("Book No. {} created", bookDto.getId());
        return BookMapper.MAPPER.mapToBookDto(book);
    }


}