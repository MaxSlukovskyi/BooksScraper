package org.max.booksscraper.scraper.impl.books;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.max.booksscraper.data.model.dto.BookDto;
import org.max.booksscraper.scraper.Scraper;
import org.max.booksscraper.service.BookService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookScraper implements Scraper<BookDto> {

    @Value("${scraper.books.url}")
    private String booksUrl;

    private final BookService bookService;

    @Override
    public void scrape(boolean pagination) {
        boolean hasNextPage = true;
        String currentUrl = booksUrl;

        while (hasNextPage) {
            try {
                Document document = Jsoup.connect(currentUrl).get();
                log.info("scrape(): Loaded page with url " + currentUrl);
                saveBooksFromPage(document);

                Element nextButton = document.selectFirst(".next a");

                if (nextButton != null && pagination) {
                    currentUrl = getNextUrl(currentUrl, nextButton);
                } else {
                    hasNextPage = false;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void saveBooksFromPage(Document document) {
        List<BookDto> books = new ArrayList<>();
        Elements bookElements = document.select(".product_pod");

        for (Element element : bookElements) {
            String title = element.select("h3 a").attr("title");
            String price = element.select(".price_color").text();
            String availability = element.select(".instock.availability").text();

            String ratingElementClassName = element.selectFirst(".star-rating").className();
            Integer rating = getRatingFromClassName(ratingElementClassName);

            BookDto book = new BookDto();
            book.setTitle(title);
            book.setPrice(price);
            book.setAvailability(availability);
            book.setRating(rating);

            books.add(book);
        }
        bookService.saveBooksFromDto(books);
        log.info("saveBooksFromPage(): Saved books from page with url " + document.location());
    }

    private String getNextUrl(String currentUrl, Element nextButton) {
        String nextButtonUrl = nextButton.attr("href");
        int lastSlashIndex = currentUrl.lastIndexOf("/");
        return currentUrl.substring(0, lastSlashIndex) + "/" + nextButtonUrl;
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
