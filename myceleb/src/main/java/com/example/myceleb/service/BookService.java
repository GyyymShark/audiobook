package com.example.myceleb.service;

import com.example.myceleb.dto.BookDto;
import com.example.myceleb.dto.BookViewDto;
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
        Book bookById = bookRepository.findById(id).get();

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

    @Transactional(readOnly = true)
    public BookViewDto findBookViewsById(Long id){
        BookViewDto bookViewDto = bookRepository.findBookViewDto(id);
        return bookViewDto;
    }




    public BookDto findByIdAndCount(Long id, int count){
        Book bookById = bookRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("book doesn't exist"));
        String[] split = bookById.getContents().split("\n", count+1);

        BookDto bookDto=new BookDto();
        String[] contents=new String[count];
        bookDto.setTitle(bookById.getTitle());

        for(int i=0; i<Math.min(count, split.length); i++){
            contents[i]=split[i].trim();
        }
        bookDto.setContents(contents);
        return bookDto;
    }



    public BookDto findByIdAndSizeAndCount(Long id, int size, int index){
        Book bookById = bookRepository.findById(id).get();
        String[] split = bookById.getContents().split("\\.", size*index+1);

        BookDto bookDto=new BookDto();
        String[] contents=new String[size];
        bookDto.setTitle(bookById.getTitle());

        for(int i=0; i<Math.min(size, split.length); i++){
            contents[i]=split[(index-1)*size+i].trim();
        }
        bookDto.setContents(contents);
        return bookDto;
    }



//    public BookDto findByIdAndSizeAndCount(Long id, int size, int count){
//        Book bookById = bookRepository.findBookById(id);
//        String[] split = bookById.getContents().split("\n", count+1);
//
//        BookDto bookDto=new BookDto();
//        String[] contents=new String[count];
//        bookDto.setTitle(bookById.getTitle());
//
//        for(int i=0; i<Math.min(count, split.length); i++){
//            contents[i]=split[i].trim();
//        }
//        bookDto.setContents(contents);
//        return bookDto;
//    }


}
