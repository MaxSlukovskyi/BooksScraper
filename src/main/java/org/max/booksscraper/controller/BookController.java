package org.max.booksscraper.controller;

import lombok.RequiredArgsConstructor;
import org.max.booksscraper.model.dto.BookDto;
import org.max.booksscraper.service.BookService;
import org.max.booksscraper.scraper.ScraperService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/books")
public class BookController {

    private final ScraperService scraperService;
    private final BookService bookService;

    @GetMapping
    public List<BookDto> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{title}")
    public List<BookDto> getBooksByTitle(@RequestParam("title") String title) {
        return bookService.getBooksByTitle(title);
    }

    @PostMapping("/scraper")
    public void scrapeBooksFromFirstPage() {
        scraperService.scrapeBooksFromFirstPage();
    }

    @PostMapping("/scraper/all")
    public void scrapeBooksFromAllPages() {
        scraperService.scrapeBooksFromAllPages();
    }

    @DeleteMapping
    public void deleteAllScrapedBooks() {
        bookService.deleteAllScrapedBooks();
    }
}
