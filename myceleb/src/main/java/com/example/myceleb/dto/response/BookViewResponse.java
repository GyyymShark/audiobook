package com.example.myceleb.dto.response;

import lombok.Data;

@Data
public class BookViewResponse {
    private Long id;
    private Long views;
    private String title;
    private String author;

    public BookViewResponse(Long id, Long views, String title, String author) {
        this.id = id;
        this.views = views;
        this.title = title;
        this.author = author;
    }
}
