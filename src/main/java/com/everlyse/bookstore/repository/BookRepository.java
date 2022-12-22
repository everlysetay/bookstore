package com.everlyse.bookstore.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.everlyse.bookstore.entity.Book;

@Repository
public interface BookRepository extends  JpaRepository<Book, Long>{
  
  List<Book> findByTitle(String title);

  // @Query(value = "SELECT * FROM book WHERE title = :title", nativeQuery = true)
  // List<Book> findByTitle(@Param("title") String title);

  Book findByIsbn(String isbn);
}
