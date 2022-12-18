package com.everlyse.bookstore.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@Table(name="author")
public class Author {
 
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  private String name;
  private String birthday;

  public Author(String name, String birthday) {
    this.name = name;
    this.birthday = birthday;
  }

  public Long getId() {
    return this.id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setBirthday(String birthday) {
    this.birthday = birthday;
  }

  public String getName(){
    return name;
  }

  public String getBirthday() {
    return birthday;
  }
}
