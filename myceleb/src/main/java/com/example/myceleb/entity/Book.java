package com.example.myceleb.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Book {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable=false)
    private String title;

    @Column(length = 30)
    private String author;

    @Column(columnDefinition = "TEXT")
    private String contents;

    private Long view;

    public Book(String title){
        this.title=title;
    }

}
