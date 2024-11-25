package org.max.booksscraper.scraper;

import java.util.List;

public interface Scraper<T> {

    void scrape(boolean pagination);

    List<T> getItems();
}
