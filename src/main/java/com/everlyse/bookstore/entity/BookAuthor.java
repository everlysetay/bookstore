package com.everlyse.bookstore.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "bookauthor")
public class BookAuthor {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Long bookId;
  private Long authorId;

  public BookAuthor(Long bookId, Long authorId) {
    this.bookId = bookId;
    this.authorId = authorId;
  }

  public Long getbookId() {
    return bookId;
  }

  public Long getAuthorId() {
    return authorId;
  }
}
