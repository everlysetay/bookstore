package com.everlyse.bookstore.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.everlyse.bookstore.entity.Author;
import com.everlyse.bookstore.repository.AuthorRepository;

@Service
public class AuthorServiceImpl implements AuthorService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorServiceImpl.class);

  private final AuthorRepository repo;

  @Autowired
  public AuthorServiceImpl(AuthorRepository repo) {
    this.repo = repo;
  }

  public Author getAuthor(String name) {
    Author author = null;
    try {
      author = repo.findByName(name);
    } catch (Exception e) {
      LOGGER.error("Unable to retrieve author: " + name, e);
    }
    return author;
  }

  public Long saveAuthor(String name, String birthday) {
    Author author = new Author(name, birthday);
    repo.save(author);
    return author.getId();
  }

  public Long saveAuthor(Author author) {
    repo.save(author);
    return author.getId();
  }

  @Override
  public Author findById(Long authorId) {
    try {
      Optional<Author> author = repo.findById(authorId);
      if (author.isPresent())
        return author.get();
      return null;
    } catch (Exception e) {
      LOGGER.info("Error in retrieval of author via id: " + authorId);
      return null;
    }
  }

  @Override
  public Author findByNameAndBirthday(String name, String birthday) {
    try {
      return repo.findByNameAndBirthday(name, birthday);
    } catch (Exception e) {
      LOGGER.info("Error in retrival of author via name and birthday: " + name + ", " + birthday);
      return null;
    }
  }

  @Override
  public void delete(Author author) {
    repo.delete(author);
  }
  
}
