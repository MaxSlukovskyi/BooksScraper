package org.max.booksscraper.scraper.impl;

import lombok.RequiredArgsConstructor;
import org.max.booksscraper.model.dto.BookDto;
import org.max.booksscraper.scraper.ScraperService;
import org.max.booksscraper.scraper.impl.books.BookScraper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScraperServiceImpl implements ScraperService {

    private final BookScraper bookScraper;

    @Override
    public List<BookDto> scrapeBooksFromFirstPage() {
        bookScraper.scrape(false);
        return bookScraper.getItems();
    }

    @Override
    public List<BookDto> scrapeBooksFromAllPages() {
        bookScraper.scrape(true);
        return bookScraper.getItems();
    }
}
