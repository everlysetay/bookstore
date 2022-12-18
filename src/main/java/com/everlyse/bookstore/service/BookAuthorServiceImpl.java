package com.everlyse.bookstore.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.everlyse.bookstore.entity.BookAuthor;
import com.everlyse.bookstore.repository.BookAuthorRepository;


@Service
public class BookAuthorServiceImpl implements BookAuthorService {
  private static final Logger LOGGER = LoggerFactory.getLogger(BookAuthorServiceImpl.class);
  private final BookAuthorRepository repo;

  @Autowired
  public BookAuthorServiceImpl(BookAuthorRepository repo) {
    this.repo = repo;
  }

  public List<BookAuthor> findByAuthorId(Long authorId) {
    List<BookAuthor> list = new ArrayList<>();
    try {
      list = repo.findByAuthorId(authorId);
    } catch (Exception e) {
      LOGGER.error("Unable to retrieve List with author id: " + authorId, e);
    }
    return list;
  }

  public List<BookAuthor> findByBookId(Long bookId) {
    List<BookAuthor> list = new ArrayList<>();
    try {
      list = repo.findByBookId(bookId);
    } catch (Exception e) {
      LOGGER.error("Unable to retrieve List with book id: " + bookId, e);
    }
    return list;
  }

  public BookAuthor findByBookIdAndAuthorId(Long bookId, Long authorId) {
    BookAuthor bookAuthor = null;
    try {
      bookAuthor = repo.findByBookIdAndAuthorId(bookId, authorId);
    } catch (Exception e) {
      LOGGER.error("Unable to retrieve List with book id: " + bookId + " authorId: " + authorId, e);
    }
    return bookAuthor;
  }

  @Override
  public void save(BookAuthor bookAuthor) {
    repo.save(bookAuthor);
  }

  @Override
  public void delete(BookAuthor bookAuthor) {
    repo.delete(bookAuthor);
  }
}
