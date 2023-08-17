package com.example.myceleb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class BookViewDto {
    private Long id;
    private Long views;
    private String title;
    private String author;

    public BookViewDto(Long id, Long views, String title, String author) {
        this.id = id;
        this.views = views;
        this.title = title;
        this.author = author;
    }
}
