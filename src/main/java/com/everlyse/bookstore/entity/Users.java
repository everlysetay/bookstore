package com.everlyse.bookstore.entity;

import org.hibernate.annotations.NaturalId;

import com.everlyse.bookstore.constants.UserAccess;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class Users {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NaturalId
  private String username;
  private String password;
  @Enumerated(EnumType.ORDINAL)
  private UserAccess accessType;

  public Users(){}

  public Users( String username, String password, UserAccess type) {
    this.username = username;
    this.password = password;
    this.accessType = type;
  }

  public String getUsername() {
    return this.username;
  }

  public String getPassword() {
    return this.password;
  }

  public void changePassword(String password) {
    this.password = password;
  }

  public UserAccess getUserAccess() {
    return this.accessType;
  }

  public void updateAccess(UserAccess type) {
    this.accessType = type;
  }
}
