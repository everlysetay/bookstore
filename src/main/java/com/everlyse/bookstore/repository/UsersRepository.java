package com.everlyse.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.everlyse.bookstore.entity.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long>{

  Users findByUsername(String username);

}
