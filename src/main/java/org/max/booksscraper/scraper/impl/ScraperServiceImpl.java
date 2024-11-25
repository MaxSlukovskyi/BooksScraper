package org.max.booksscraper.scraper.impl;

import lombok.RequiredArgsConstructor;
import org.max.booksscraper.scraper.ScraperService;
import org.max.booksscraper.scraper.impl.books.BookScraper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScraperServiceImpl implements ScraperService {

    private final BookScraper bookScraper;

    @Override
    public void scrapeBooksFromFirstPage() {
        bookScraper.scrape(false);
    }

    @Override
    public void scrapeBooksFromAllPages() {
        bookScraper.scrape(true);
    }
}
