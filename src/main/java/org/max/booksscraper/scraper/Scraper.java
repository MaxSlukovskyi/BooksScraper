package org.max.booksscraper.scraper;

public interface Scraper<T> {

    void scrape(boolean pagination);

}
