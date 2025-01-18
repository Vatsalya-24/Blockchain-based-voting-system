package com.voting.system.entity;

import jakarta.persistence.*; // For JPA annotations

@Entity // Marks this class as a JPA Entity
@Table(name = "user") // Optional: Specifies the table name (defaults to the class name if not provided)
public class User {

    @Id // Marks this field as the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment primary key
    private Long id;

    @Column(name = "username", nullable = false, unique = true) // Maps to a column in the database
    private String username;

    @Column(name = "password", nullable = false) // Marks this field as a required column
    private String password;

    // Constructors
    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getters and Setters (used for accessing and modifying private fields)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
