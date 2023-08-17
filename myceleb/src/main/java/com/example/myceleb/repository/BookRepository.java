package com.example.myceleb.repository;

import com.example.myceleb.dto.BookDto;
import com.example.myceleb.dto.BookViewDto;
import com.example.myceleb.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
    @Query("select new com.example.myceleb.dto.BookViewDto(:id,b.views,b.title,b.author)"+ "from Book b where b.id = :id")
   BookViewDto findBookViewDto(@Param("id") Long id);
}
