package com.everlyse.bookstore.service;

import java.util.List;

import com.everlyse.bookstore.entity.BookAuthor;

public interface BookAuthorService {
  public void save(BookAuthor bookAuthor);

  public List<BookAuthor> findByAuthorId (Long authorId);
  
  public List<BookAuthor> findByBookId (Long bookId);

  public BookAuthor findByBookIdAndAuthorId(Long bookId, Long authorId);

  public void delete(BookAuthor bookAuthor);
}
