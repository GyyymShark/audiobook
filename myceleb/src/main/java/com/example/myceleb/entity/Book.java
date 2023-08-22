package com.example.myceleb.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Book {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable=false)
    private String title;

    @Column(length = 30)
    private String author;

    @Column(columnDefinition = "TEXT")
    private String contents;

    private Long views=0L;

    public Book(String title){
        this.title=title;
    }

}
