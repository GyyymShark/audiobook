package com.example.myceleb.service;

import com.example.myceleb.entity.Book;
import com.example.myceleb.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;


    @Transactional(readOnly = true)
    public List<Book> findAll(){
        return bookRepository.findAll();
    }


    @Transactional
    public Book findById(Long id){
        Book bookById = bookRepository.findBookById(id);

        Book book = Book.builder()
                .author(bookById.getAuthor())
                .contents(bookById.getContents())
                .id(bookById.getId())
                .title(bookById.getTitle())
                .views(bookById.getViews() + 1)
                .build();
        bookRepository.save(book);
        return book;
    }

    @Transactional
    public Book save(Book book){
        bookRepository.save(book);
        return book;
    }


    public void findByIdAndCount2(Long id, int count){
        Book bookById = bookRepository.findBookById(id);
        String[] split = bookById.getContents().split("\\\\n", count+1);
        for(int i=0; i<Math.min(count, split.length); i++){
            System.out.println("split = " + split[i].trim());
        }
    }

}
