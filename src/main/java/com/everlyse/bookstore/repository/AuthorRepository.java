package com.everlyse.bookstore.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.everlyse.bookstore.entity.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long>{
  
  Author findByName(String name);

  Optional<Author> findById(Long id);

  Author findByNameAndBirthday(String name, String birthday);

}
