package org.max.booksscraper.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.max.booksscraper.model.Book;
import org.max.booksscraper.model.dto.BookDto;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BookMapper {

    Book toBook(BookDto bookDto);

    BookDto toBookDto(Book book);
}
