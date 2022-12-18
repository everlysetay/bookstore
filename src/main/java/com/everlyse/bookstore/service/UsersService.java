package com.everlyse.bookstore.service;

import com.everlyse.bookstore.constants.UserAccess;

public interface UsersService {
  UserAccess findUserAccess(String username, String password);
}
