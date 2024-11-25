package org.max.booksscraper.service;

import org.max.booksscraper.data.model.dto.BookDto;

import java.util.List;

public interface BookService {

    List<BookDto> getAllBooks();

    List<BookDto> getBooksByTitle(String title);

    void deleteAllScrapedBooks();

    void saveBooksFromDto(List<BookDto> dtos);
}
