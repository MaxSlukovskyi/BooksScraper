package org.max.booksscraper.model.dto;

import lombok.Data;

@Data
public class BookDto {

    private String title;
    private Double price;
    private Boolean availability;
    private Integer rating;
}
