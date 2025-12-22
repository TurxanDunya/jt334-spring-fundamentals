package com.texnoera.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BookDto {

    private String name;
    private LocalDateTime publishedDate;

}
