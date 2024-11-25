package org.max.booksscraper.service.impl;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.max.booksscraper.model.dto.BookDto;
import org.max.booksscraper.service.Scraper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookScraper implements Scraper<BookDto> {

    @Value("${scraper.books.url}")
    private String booksUrl;

    List<BookDto> books = new ArrayList<>();

    @Override
    public void scrape() {
        try {
            Document document = Jsoup.connect(booksUrl).get();
            Elements bookElements = document.select(".product_pod");

            for (Element element : bookElements) {
                String title = element.select("h3 a").attr("title");
                String price = element.select(".price_color").text();
                String availability = element.select(".instock.availability").text();

                String ratingElementClassName = element.selectFirst(".star-rating").className();
                Integer rating = getRatingFromClassName(ratingElementClassName);

                BookDto book = new BookDto();
                book.setTitle(title);
                book.setTitle(price);
                book.setAvailability(availability);
                book.setRating(rating);

                books.add(book);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<BookDto> getItems() {
        return books;
    }

    private Integer getRatingFromClassName(String className) {
        if (className.contains("One")) return 1;
        if (className.contains("Two")) return 2;
        if (className.contains("Three")) return 3;
        if (className.contains("Four")) return 4;
        if (className.contains("Five")) return 5;
        else return 0;
    }
}
