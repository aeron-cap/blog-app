package com.example.blog_app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.blog_app.entity.Blog;
import com.example.blog_app.repository.BlogRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BlogService {

  private final BlogRepository blogRepository;

  public Blog saveBlog(Blog blog) {
    // check required
    return blogRepository.save(blog);
  }

  public List<Blog> getAllBlogs() {
    return blogRepository.findAll();
  }

  public Optional<Blog> getBlogById(Long id) {
    return blogRepository.findById(id);
  }

  public void deleteBlog(Long id) {
    blogRepository.deleteById(id);
  }
}
