package org.max.booksscraper.data.model.dto;

import lombok.Data;

@Data
public class BookDto {

    private String title;
    private String price;
    private String availability;
    private Integer rating;
}
