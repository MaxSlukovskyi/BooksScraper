package org.max.booksscraper.service.impl;

import lombok.RequiredArgsConstructor;
import org.max.booksscraper.mapper.BookMapper;
import org.max.booksscraper.model.dto.BookDto;
import org.max.booksscraper.repository.BookRepository;
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
}
