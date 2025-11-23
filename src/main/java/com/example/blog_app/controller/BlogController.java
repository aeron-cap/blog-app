package com.example.blog_app.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.blog_app.entity.Blog;
import com.example.blog_app.service.BlogService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/blogs")
@RequiredArgsConstructor
public class BlogController {

  private final BlogService blogService;

  @PostMapping("")
  public ResponseEntity<Blog> createBlog(@RequestBody Blog blog) {
    return ResponseEntity.ok(blogService.saveBlog(blog));
  }

  @GetMapping
  public ResponseEntity<List<Blog>> getAllBlogs() {
    return ResponseEntity.ok(blogService.getAllBlogs());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Blog> getBlogById(@PathVariable Long id) {
    return blogService.getBlogById(id)
      .map(ResponseEntity::ok)
      .orElse(ResponseEntity.notFound().build());
  } 

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteBlog(@PathVariable Long id) {
    blogService.deleteBlog(id);
    return ResponseEntity.noContent().build();
  }
  
}
