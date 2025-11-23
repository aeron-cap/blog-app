package com.example.blog_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.blog_app.entity.Blog;

public interface BlogRepository extends JpaRepository<Blog, Long> {

}
