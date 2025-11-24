package com.example.blog_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.blog_app.entity.Blog;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog, Long> {
  List<Blog> findByUsername(String username);
}
