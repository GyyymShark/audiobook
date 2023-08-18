package com.example.myceleb.controller;

import com.example.myceleb.dto.request.CreateBookRequest;
import com.example.myceleb.dto.response.BookResponse;
import com.example.myceleb.dto.response.BookViewResponse;
import com.example.myceleb.dto.response.DefaultResponse;
import com.example.myceleb.dto.response.StatusCode;
import com.example.myceleb.entity.Book;
import com.example.myceleb.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<DefaultResponse<List<BookViewResponse>>> getAllBooks(){
        DefaultResponse<List<BookViewResponse>> all = bookService.findAll();
        return new ResponseEntity<>(all, HttpStatus.OK);
    }



    @GetMapping("/book/{id}")
    @Operation(summary = "책 선택 및 정보조회", description = "책을 선택하여 조회수가 1증가하고, 책 전체내용을 포함한 정보가 조회됩니다")
    public ResponseEntity<Book> getBookById(@PathVariable Long id){
        Book bookById = bookService.findById(id);
        return new ResponseEntity<>(bookById, HttpStatus.OK);
    }



    @GetMapping("/book")
    public ResponseEntity<DefaultResponse<BookResponse>> getBookSentences(@RequestParam(name="id") Long id,
                                         @RequestParam(name="offset") int offset,
                                         @RequestParam(name="limit") int limit){

        DefaultResponse<BookResponse> bookSentences = bookService.getBookSentences(id, offset, limit);
        if(bookSentences.getCode()== StatusCode.BAD_REQUEST){
            return new ResponseEntity<>(bookSentences,HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(bookSentences,HttpStatus.OK);
    }
    @PostMapping("/book")
    @Operation(summary = "책 생성", description = "책 한권을 생성합니다")
    public ResponseEntity<DefaultResponse<Long>> createBook(@Valid @RequestBody CreateBookRequest createBookRequest){
        DefaultResponse<Long> save = bookService.save(createBookRequest);
        return new ResponseEntity<>(save, HttpStatus.OK);
    }
}
