package com.voting.system.controller;

import com.voting.system.service.BlockchainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@RestController
@RequestMapping("/api/voting")
public class VotingController {

    @Autowired
    private BlockchainService blockchainService;

    // Endpoint to add a candidate
    @PostMapping("/addCandidate")
    public String addCandidate(@RequestBody String candidateName) {
        try {
            blockchainService.addCandidate(candidateName);
            return "Candidate added successfully!";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    // Endpoint to vote for a candidate
    @PostMapping("/vote")
    public String vote(@RequestBody int candidateId) {
        try {
            blockchainService.voteForCandidate(candidateId);
            return "Vote cast successfully!";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    // Endpoint to get vote count for a candidate
    @GetMapping("/voteCount/{candidateId}")
    public BigInteger getVoteCount(@PathVariable int candidateId) {
        try {
            return blockchainService.getVoteCount(candidateId);
        } catch (Exception e) {
            return BigInteger.valueOf(-1); // Return -1 if error occurs
        }
    }

    // Endpoint to get the number of candidates
    @GetMapping("/candidatesCount")
    public BigInteger getCandidatesCount() {
        try {
            return blockchainService.getCandidatesCount();
        } catch (Exception e) {
            return BigInteger.valueOf(-1); // Return -1 if error occurs
        }
    }
}
