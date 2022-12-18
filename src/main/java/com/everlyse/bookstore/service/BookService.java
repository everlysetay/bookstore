package com.everlyse.bookstore.service;

import java.util.List;

import com.everlyse.bookstore.constants.UserAccess;
import com.everlyse.bookstore.entity.Book;

public interface BookService {
  String addBook(Book book);

  Book findByIsbn(String isbn);

  List<Book> findByTitle(String title);

  String updateBook(Book book);

  String deleteBook(Book book, UserAccess access);

  Book findById(Long id);
}
