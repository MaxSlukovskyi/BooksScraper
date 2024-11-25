package org.max.booksscraper.scraper.impl.books;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.max.booksscraper.model.dto.BookDto;
import org.max.booksscraper.scraper.Scraper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookScraper implements Scraper<BookDto> {

    @Value("${scraper.books.url}")
    private String booksUrl;

    @Value("${base.books.catalogue.url}")
    private String baseBooksCatalogueUrl;

    List<BookDto> books = new ArrayList<>();

    @Override
    public void scrape(boolean pagination) {
        boolean hasNextPage = true;
        String currentUrl = booksUrl;

        while (hasNextPage) {
            try {
                Document document = Jsoup.connect(currentUrl).get();
                saveBooksFromPage(document);

                Element nextButton = document.selectFirst(".next a");

                if (nextButton != null && pagination) {
                    String nextButtonUrl = nextButton.attr("href");
                    currentUrl = baseBooksCatalogueUrl + nextButtonUrl;
                } else {
                    hasNextPage = false;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public List<BookDto> getItems() {
        return books;
    }

    private void saveBooksFromPage(Document document) {
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
