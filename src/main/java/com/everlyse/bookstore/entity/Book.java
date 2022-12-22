package com.everlyse.bookstore.entity;
import org.hibernate.annotations.NaturalId;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="book")
public class Book {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NaturalId
  private String isbn;
  private String title;
  private Integer publishedYear;
  private float price;
  private String genre;

  public Book(){}

  public Book(String isbn, String title,  Integer publishedYear, float price, String genre) {
    this.isbn = isbn;
    this.title = title;
    this.publishedYear = publishedYear;
    this.price = price;
    this.genre = genre;
  }

  public Long getId() {
    return this.id;
  }

  public void setIsbn(String isbn) {
    this.isbn = isbn;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setPublishedYear (Integer publishedYear) {
    this.publishedYear = publishedYear;
  }

  public void setPrice(float price) {
    this.price = price;
  }

  public void setGenre(String genre) {
    this.genre = genre;
  }

  public String getIsbn() {
    return this.isbn;
  }

  public String getTitle() {
    return this.title;
  }

  public Integer getPublishedYear () {
    return this.publishedYear;
  }

  public float getPrice() {
    return this.price;
  }

  public String getGenre() {
    return this.genre;
  }
}
