package com.voting.system.entity;

import jakarta.persistence.*;

import java.math.BigInteger;

@Entity
@Table(name = "candidates")  // This table will be created in MySQL
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Auto-incremented ID
    private Long id;

    @Column(nullable = false, unique = true)  // Ensuring unique candidate names
    private String name;

    @Column(nullable = false)
    private BigInteger voteCount = BigInteger.ZERO;  // Initialize vote count as 0

    // Default constructor
    public Candidate() {}

    // Constructor with parameters
    public Candidate(String name, BigInteger voteCount) {
        this.name = name;
        this.voteCount = voteCount;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigInteger getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(BigInteger voteCount) {
        this.voteCount = voteCount;
    }
}
