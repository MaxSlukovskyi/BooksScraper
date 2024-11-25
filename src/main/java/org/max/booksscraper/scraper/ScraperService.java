package org.max.booksscraper.scraper;

import org.max.booksscraper.model.dto.BookDto;

import java.util.List;

public interface ScraperService {

    List<BookDto> scrapeBooksFromFirstPage();

    List<BookDto> scrapeBooksFromAllPages();
}
