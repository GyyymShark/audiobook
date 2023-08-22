package com.example.myceleb.dto.request;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateBookRequest {



    @Column(length = 50, nullable=false)
    private String title;

    @Column(length = 30)
    private String author;

    @Column(columnDefinition = "TEXT")
    private String contents;

    private Long views=0L;

}
