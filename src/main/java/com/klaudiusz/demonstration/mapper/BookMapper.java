package com.klaudiusz.demonstration.mapper;

import com.klaudiusz.demonstration.dto.BookDto;
import com.klaudiusz.demonstration.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface BookMapper {

    BookMapper MAPPER = Mappers.getMapper(BookMapper.class);

    BookDto mapToBookDto(Book book);

    Book mapToBook(BookDto bookDto);

    List<BookDto> mapListToBookDtoList(List<Book> books);
}