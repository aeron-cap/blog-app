package com.example.blog_app.controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import com.example.blog_app.entity.Blog;
import com.example.blog_app.service.BlogService;

import lombok.RequiredArgsConstructor;

import java.security.Principal;
import java.util.List;

import org.springframework.http.ResponseEntity;


@RestController
@RequestMapping("/api/blogs")
@RequiredArgsConstructor
public class BlogController {

  private final BlogService blogService;

  @PostMapping("")
  public ResponseEntity<Blog> createBlog(@Valid @RequestBody Blog blog, Principal principal) {
// Principal gets the current logged-in user
    String currentUsername = principal.getName();
    blog.setUsername(currentUsername);
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

  @PutMapping("/{id}")
  public ResponseEntity<?> updateBlog(@PathVariable Long id, @Valid @RequestBody Blog blog, Principal principal)  {
    return blogService.getBlogById(id).map(existingBlog -> {
      if (!existingBlog.getUsername().equals(principal.getName())) {
        return ResponseEntity.status(403).body("You can't edit other user's posts.");
      }

      existingBlog.setTitle(blog.getTitle());
      existingBlog.setContent(blog.getContent());
      existingBlog.setLink(blog.getLink());

      return ResponseEntity.ok(blogService.saveBlog(existingBlog));
    }).orElse(ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteBlog(@PathVariable Long id, Principal principal) {
    return blogService.getBlogById(id).map(existingBLog -> {
      if (!existingBLog.getUsername().equals(principal.getName())) {
        return ResponseEntity.status(403).body("You can't delete other user's posts.");
      }

      blogService.deleteBlog(id);
      return ResponseEntity.ok("Blog post deleted.");
    }).orElse(ResponseEntity.notFound().build());
  }
}
