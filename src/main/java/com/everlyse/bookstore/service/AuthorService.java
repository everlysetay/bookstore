package com.everlyse.bookstore.service;

import com.everlyse.bookstore.entity.Author;

public interface AuthorService {
  Author getAuthor(String name);

  Long saveAuthor(String name, String birthday);

  Long saveAuthor(Author author);

  Author findById(Long authorId);

  Author findByNameAndBirthday(String name, String birthday);

  void delete(Author author);
}
