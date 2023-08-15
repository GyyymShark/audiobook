package com.example.myceleb.service;

import com.example.myceleb.entity.Book;
import com.example.myceleb.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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

        return Book.builder()
                .author(bookById.getAuthor())
                .contents(bookById.getContents())
                .id(bookById.getId())
                .title(bookById.getTitle())
                .views(bookById.getViews()+1)
                .build();
    }

    @Transactional
    public Book save(Book book){
        bookRepository.save(book);
        return book;
    }


    public void findByIdAndCount2(Long id, Long count){
        Book bookById = bookRepository.findBookById(id);
        String[] split = bookById.getContents().split(".", 3);
        System.out.println("split = " + split);
    }

}
