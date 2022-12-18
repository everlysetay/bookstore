package com.everlyse.bookstore.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.everlyse.bookstore.constants.UserAccess;
import com.everlyse.bookstore.entity.Users;
import com.everlyse.bookstore.repository.UsersRepository;

@Service
public class UsersServiceImpl implements UsersService{

  private static final Logger LOGGER = LoggerFactory.getLogger(UsersServiceImpl.class);
  private final UsersRepository repo;

  @Autowired
  public UsersServiceImpl(UsersRepository repo) {
    this.repo = repo;
  }

  @Override
  public UserAccess findUserAccess(String username, String password) {
    Users user = repo.findByUsername(username);
    if (user != null && password.contentEquals(user.getPassword())) {
      return user.getUserAccess();
    }
    LOGGER.debug("Unable to find user");
    return UserAccess.NO_ACCESS;
  }
  
}
