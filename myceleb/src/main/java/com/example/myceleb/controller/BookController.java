package com.example.myceleb.controller;

import com.example.myceleb.dto.BookDto;
import com.example.myceleb.dto.BookViewDto;
import com.example.myceleb.entity.Book;
import com.example.myceleb.repository.BookRepository;
import com.example.myceleb.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
    @Operation(summary = "책 전체목록 조회", description = "책 내용을 제외한 정보 전체를 조회합니다")
    public ResponseEntity<List<BookViewDto>> getAllBooks(){
        List<BookViewDto> books = bookService.findAll();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }
    @GetMapping("/books/test")
    @Operation(summary = "책 전체목록 조회", description = "책 내용을 제외한 정보 전체를 조회합니다")
    public ResponseEntity<List<Book>> getAllBookss(){
        List<Book> books = bookService.findAlll();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/book/{id}")
    @Operation(summary = "책 선택 및 정보조회", description = "책을 선택하여 조회수가 1증가하고, 책 전체내용을 포함한 정보가 조회됩니다")
    public ResponseEntity<Book> getBookById(@PathVariable Long id){
        Book bookById = bookService.findById(id);
        return new ResponseEntity<>(bookById, HttpStatus.OK);
    }



    @GetMapping("/book")
    public BookDto getBookByIdAndSizeAndIndex(@RequestParam(name="id") Long id,
                   @RequestParam(name="size") int size,
                   @RequestParam(name="index") int index){
        BookDto byIdAndSizeAndCount = bookService.findByIdAndSizeAndCount(id, size, index);
        return byIdAndSizeAndCount;
    }
    @PostMapping("/book")
    @Operation(summary = "책 생성", description = "책 한권을 생성합니다")
    public ResponseEntity<Book> createBook(@RequestBody Book book){
        bookService.save(book);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }
}
