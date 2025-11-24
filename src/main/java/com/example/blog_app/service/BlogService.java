package com.example.blog_app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.example.blog_app.entity.Blog;
import com.example.blog_app.repository.BlogRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BlogService {

  private final BlogRepository blogRepository;

  @CacheEvict(value = "blogs", key = "'all_blogs'")
  public Blog saveBlog(Blog blog) { return blogRepository.save(blog); }

  @Cacheable(value = "blogs", key = "'all_blogs'")
  public List<Blog> getAllBlogs() {
    return blogRepository.findAll();
  }

  public Optional<Blog> getBlogById(Long id) {
    return blogRepository.findById(id);
  }

  @CacheEvict(value = "blogs", key = "'all_blogs'")
  public void deleteBlog(Long id) {
    blogRepository.deleteById(id);
  }
}
