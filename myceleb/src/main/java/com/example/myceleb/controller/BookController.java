package com.example.myceleb.controller;

import com.example.myceleb.dto.BookDto;
import com.example.myceleb.entity.Book;
import com.example.myceleb.repository.BookRepository;
import com.example.myceleb.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;




    @GetMapping("/books")
    public ResponseEntity<List<Book>> getAllBooks(){
        List<Book> books = bookService.findAll();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/book/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id){
        Book bookById = bookService.findById(id);
        return new ResponseEntity<>(bookById, HttpStatus.OK);
    }

//    @GetMapping("/book")
//    public BookDto getBookByIdAndCount(
//            @RequestParam(name ="id") Long id,
//            @RequestParam(name = "count") int count
//    ){
//        BookDto book = bookService.findByIdAndCount(id, count);
//        return book;
//    }

    @GetMapping("/book")
    public BookDto getBookByIdAndSizeAndIndex(@RequestParam(name="id") Long id,
                   @RequestParam(name="size") int size,
                   @RequestParam(name="index") int index){
        BookDto byIdAndSizeAndCount = bookService.findByIdAndSizeAndCount(id, size, index);
        return byIdAndSizeAndCount;
    }
    @PostMapping("/book")
    public ResponseEntity<Book> createBook(@RequestBody Book book){
        bookService.save(book);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }
}
