package com.example.myceleb.repository;

import com.example.myceleb.dto.response.BookViewResponse;
import com.example.myceleb.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {

    @Query("select new com.example.myceleb.dto.response.BookViewResponse(b.id, b.views,b.title,b.author) from Book b")
    List<BookViewResponse> findBookListDto();

    @Query("select new com.example.myceleb.dto.response.BookViewResponse(:id,b.views,b.title,b.author)"+ "from Book b where b.id = :id")
    BookViewResponse findBookViewDto(@Param("id") Long id);
}
