package com.example.myceleb.repository;

import com.example.myceleb.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
    Book findBookByTitle(String title);
    //Optional<Book> findBookById(Long id);
    Book findBookById(Long id);
}
