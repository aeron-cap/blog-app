package com.example.blog_app.controller;

import com.example.blog_app.config.SecurityConfig;
import com.example.blog_app.dto.LoginRequest;
import com.example.blog_app.dto.RegisterRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
  private final UserDetailsManager userDetailsManager;
  private final PasswordEncoder passwordEncoder;
  private final AuthenticationManager authenticationManager;

  public AuthController(
      UserDetailsManager userDetailsManager,
      PasswordEncoder passwordEncoder,
      AuthenticationManager authenticationManager
  ) {
    this.userDetailsManager = userDetailsManager;
    this.passwordEncoder = passwordEncoder;
    this.authenticationManager = authenticationManager;
  }

  @PostMapping("/register")
  public ResponseEntity<String> register(@Valid @RequestBody RegisterRequest request) {
    if (userDetailsManager.userExists(request.username())) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username already exists.");
    }

    UserDetails newUser = User.builder()
        .username(request.username())
        .password(passwordEncoder.encode(request.password()))
        .roles("USER")
        .build();

    userDetailsManager.createUser(newUser);

    return ResponseEntity.ok("User created successfully!");
  }

  @PostMapping("/login")
  public ResponseEntity<String> login(@Valid @RequestBody LoginRequest request) {
    UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
        request.username(),
        request.password()
    );

    Authentication authentication = authenticationManager.authenticate(token);

    SecurityContextHolder.getContext().setAuthentication(authentication);

    return ResponseEntity.ok("Logged In!");
  }
}
