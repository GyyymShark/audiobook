package com.example.myceleb.repository;

import com.example.myceleb.entity.Book;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(value = false)
class BookRepositoryTest {

    @Autowired BookRepository bookRepository;

    @Test
    public void testSave(){
        Book book1=new Book("book1");
        Book book2=new Book("book2");

        bookRepository.save(book1);
        bookRepository.save(book2);
    }

    @Test
    public void testFind(){
        Book book1=new Book("book1");
        Book book2=new Book("book2");

        bookRepository.save(book1);
        bookRepository.save(book2);

        Book findBook1 = bookRepository.findById(book1.getId()).get();
        Book findBook2 = bookRepository.findById(book2.getId()).get();


        assertThat(book1).isEqualTo(findBook1);
        assertThat(book2).isEqualTo(findBook2);
    }

    @Test
    public void testFindById(){
        Book testBook1 = new Book("testBook1");
        Book testBook2 = new Book("testBook2");

        bookRepository.save(testBook1);
        bookRepository.save(testBook2);

        Book bookById1 = bookRepository.findBookById(1L);
        Book bookById2 = bookRepository.findBookById(2L);


        assertThat(testBook1).isEqualTo(bookById1);
        assertThat(testBook2).isEqualTo(bookById2);

    }
}