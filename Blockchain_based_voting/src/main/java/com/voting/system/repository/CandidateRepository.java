package com.voting.system.repository;

import com.voting.system.entity.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long> {
    // Custom query methods can be added if needed
    Candidate findByName(String name);
}
