package com.everlyse.bookstore.request;

import java.util.HashMap;

public class BookDetailsRequest {
  
  //save name, birthday
  public HashMap<String, String> authors = new HashMap<>();
  
  //book
  public String isbn;
  public String title;
  public Integer publishedYear;
  public float price;
  public String genre;

  public BookDetailsRequest (String isbn, String title, Integer publishedYear, float price, String genre, HashMap<String, String> authors) { 
    this.isbn = isbn;
    this.title = title;
    this.publishedYear = publishedYear;
    this.price = price;
    this.genre = genre;

    this.authors = authors;
  }

  public String getIsbn() {
    return isbn;
  }

  public String getTitle() {
    return title;
  }

  public Integer getPublishedYear() {
    return publishedYear;
  }

  public float getPrice() {
    return price;
  }

  public String getGenre() {
    return genre;
  }

  public HashMap<String, String> getAuthors() {
    return authors;
  }
}
