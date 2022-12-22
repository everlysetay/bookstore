package com.everlyse.bookstore.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.everlyse.bookstore.constants.UserAccess;
import com.everlyse.bookstore.entity.Book;
import com.everlyse.bookstore.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService {
  private static final Logger LOGGER = LoggerFactory.getLogger(BookServiceImpl.class);
  private final BookRepository repo;

  @Autowired
  public BookServiceImpl(BookRepository repo) {
    this.repo = repo;
  }

  public String addBook(Book book) {
    try {
      repo.save(book);
    } catch (Exception e) {
      LOGGER.error("Unable to save book: " + book.getTitle(), e);
    }
    return "Book successfully saved";
  }

  public String updateBook(Book book){
    try {
      repo.save(book);
    } catch (Exception e) {
      LOGGER.error("Unable to update book with new details: " + book.getTitle(), e);
    }
    return "Book successefully updated";
  }

  public String deleteBook(Book book, UserAccess access) {
    if (access == UserAccess.ADMIN) {
      repo.delete(book);
      return "Book successfully deleted";
    }
    return "Doesn't have the access rights to remove books";
  }

  @Override
  public Book findByIsbn(String isbn) {
    try  {
      return repo.findByIsbn(isbn);
    } catch (Exception e) {
      LOGGER.info("Book with isbn " + isbn + " doesn't exist");
      return null;
    }
  }

  @Override
  public List<Book> findByTitle(String title) {
    try {
      return repo.findByTitle(title);
    } catch (Exception e) {
      LOGGER.info("Book with title " + title + " doesn't exist");
      LOGGER.error("",e);
      return null;
    }
  }

  @Override
  public Book findById(Long id) {
    try {
      Optional<Book> book = repo.findById(id);
      if(book.isPresent())
        return book.get();
    } catch (Exception e) {
      LOGGER.info("Unable to find book with id " + id);
    }
    return null;
  }
}
