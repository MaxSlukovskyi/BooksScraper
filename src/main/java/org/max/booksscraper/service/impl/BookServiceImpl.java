package org.max.booksscraper.service.impl;

import lombok.RequiredArgsConstructor;
import org.max.booksscraper.data.mapper.BookMapper;
import org.max.booksscraper.data.model.Book;
import org.max.booksscraper.data.model.dto.BookDto;
import org.max.booksscraper.data.repository.BookRepository;
import org.max.booksscraper.scraper.ScraperService;
import org.max.booksscraper.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    public List<BookDto> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(bookMapper::toBookDto)
                .toList();
    }

    @Override
    public List<BookDto> getBooksByTitle(String title) {
        return bookRepository.findByTitle(title).stream()
                .map(bookMapper::toBookDto)
                .toList();
    }

    @Override
    public void saveBooksFromDto(List<BookDto> dtos) {
        List<Book> books = dtos.stream()
                .map(bookMapper::toBook)
                .toList();
        if (!books.isEmpty()) {
            bookRepository.saveAll(books);
        }
    }

    @Override
    public void deleteAllScrapedBooks() {
        bookRepository.deleteAll();
    }

}
