package com.example.blog_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.blog_app.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
  // i can now secretly call
  // .save(user)
  // .findAll()
  // .findById(1L)
  // .deleteById(1L)
}
