package com.everlyse.bookstore.request;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FindBookRequest {
  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

  public String bookTitle;
  public String authorName;
  public String authorBirthday;

  public FindBookRequest() {
    this.bookTitle = "";
    this.authorName = "";
    this.authorBirthday = formatter.format(LocalDate.now());
  }

  public FindBookRequest(String title, String name, String birthday) {
    this.bookTitle = title;
    this.authorName = name;
    this.authorBirthday = birthday;
  }

  public String getBookTitle() {
    return bookTitle;
  }

  public String getAuthorName(){
    return authorName;
  }

  public String getAuthorBirthday() {
    return authorBirthday;
  }
}
