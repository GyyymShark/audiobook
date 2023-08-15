package com.example.myceleb.repository;

import com.example.myceleb.dto.BookDto;
import com.example.myceleb.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {

    //Optional<Book> findBookById(Long id);
    Book findBookById(Long id);

    @Query("select b.title, b.contents from Book b")
    BookDto findBookDtoById(Long id);
}
