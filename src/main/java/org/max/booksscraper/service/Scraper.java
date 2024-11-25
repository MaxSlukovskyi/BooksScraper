package org.max.booksscraper.service;

import java.util.List;

public interface Scraper<T> {

    void scrape();

    List<T> getItems();
}
