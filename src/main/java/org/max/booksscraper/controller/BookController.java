package org.max.booksscraper.controller;

import lombok.RequiredArgsConstructor;
import org.max.booksscraper.model.dto.BookDto;
import org.max.booksscraper.service.BookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    @GetMapping
    public List<BookDto> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{title}")
    public List<BookDto> getBooksByTitle(@RequestParam("title") String title) {
        return bookService.getBooksByTitle(title);
    }
}
