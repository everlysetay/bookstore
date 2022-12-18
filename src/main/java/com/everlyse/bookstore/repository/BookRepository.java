package com.everlyse.bookstore.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.everlyse.bookstore.entity.Book;

@Repository
public interface BookRepository extends  JpaRepository<Book, Long>{
  
  List<Book> findByTitle(String title);

  Book findByIsbn(String isbn);
}