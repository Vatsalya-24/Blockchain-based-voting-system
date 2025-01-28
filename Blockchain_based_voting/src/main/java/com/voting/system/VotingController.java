package com.voting.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@RestController
@RequestMapping("/api/voting")
public class VotingController {

    private final BlockchainService blockchainService;

    // Constructor injection for BlockchainService
    @Autowired
    public VotingController(BlockchainService blockchainService) {
        this.blockchainService = blockchainService;
    }

    // Endpoint to add a candidate
    @PostMapping("/addCandidate")
    public ResponseEntity<String> addCandidate(@RequestBody String candidateName) {
        if (candidateName == null || candidateName.trim().isEmpty()) {
            return new ResponseEntity<>("Candidate name cannot be empty!", HttpStatus.BAD_REQUEST);
        }
        
        try {
            blockchainService.addCandidate(candidateName);
            return new ResponseEntity<>("Candidate added successfully!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error while adding candidate: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Endpoint to vote for a candidate
    @PostMapping("/vote/{candidateId}")
    public ResponseEntity<String> vote(@PathVariable int candidateId) {
        if (candidateId <= 0) {
            return new ResponseEntity<>("Invalid candidate ID!", HttpStatus.BAD_REQUEST);
        }

        try {
            blockchainService.voteForCandidate(candidateId);
            return new ResponseEntity<>("Vote cast successfully!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error while casting vote: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Endpoint to get vote count for a candidate
    @GetMapping("/voteCount/{candidateId}")
    public ResponseEntity<BigInteger> getVoteCount(@PathVariable int candidateId) {
        if (candidateId <= 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {
            BigInteger voteCount = blockchainService.getVoteCount(candidateId);
            return new ResponseEntity<>(voteCount, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Endpoint to get the number of candidates
    @GetMapping("/candidatesCount")
    public ResponseEntity<BigInteger> getCandidatesCount() {
        try {
            BigInteger count = blockchainService.getCandidatesCount();
            return new ResponseEntity<>(count, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
