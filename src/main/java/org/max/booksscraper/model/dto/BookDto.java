package org.max.booksscraper.model.dto;

import lombok.Data;

@Data
public class BookDto {

    private String title;
    private Double price;
    private String availability;
    private Integer rating;
}
