package org.max.booksscraper.scraper;

import org.max.booksscraper.data.model.dto.BookDto;

import java.util.List;

public interface ScraperService {

    void scrapeBooksFromFirstPage();

    void scrapeBooksFromAllPages();
}
