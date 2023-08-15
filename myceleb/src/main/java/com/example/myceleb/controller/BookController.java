package com.example.myceleb.controller;

import com.example.myceleb.entity.Book;
import com.example.myceleb.repository.BookRepository;
import com.example.myceleb.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BookController {
    private final BookRepository bookRepository;
    private final BookRepository bookService;

    @GetMapping("/books")
    public ResponseEntity<List<Book>> getAllBooks(){
        List<Book> books = bookRepository.findAll();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/book/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id){
        Book bookById = bookRepository.findBookById(id);
        return new ResponseEntity<>(bookById, HttpStatus.OK);
    }

    @PostMapping("/book")
    public ResponseEntity<Book> createBook(@RequestBody Book book){
        bookRepository.save(book);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }
}
