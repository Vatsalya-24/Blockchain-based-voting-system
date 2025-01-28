package com.voting.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Map;

@RestController
@RequestMapping("/api/voting")
public class VotingController {

    private static final Logger LOGGER = Logger.getLogger(VotingController.class.getName());
    private final BlockchainService blockchainService;

    // Constructor injection for BlockchainService
    @Autowired
    public VotingController(BlockchainService blockchainService) {
        this.blockchainService = blockchainService;
    }

    @PostMapping("/addCandidate")
    
    public ResponseEntity<String> addCandidate(@RequestBody String candidateName) {
            try {
                blockchainService.addCandidate(candidateName);
                return ResponseEntity.ok("Candidate added successfully!");
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
            }
        }
    

    // Endpoint to vote for a candidate
    @PostMapping("/vote/{candidateId}")
    public ResponseEntity<String> vote(@PathVariable int candidateId) {
        if (candidateId <= 0) {
            return new ResponseEntity<>("Invalid candidate ID!", HttpStatus.BAD_REQUEST);
        }

        try {
            LOGGER.log(Level.INFO, "Attempting to cast vote for candidate ID: {0}", candidateId);
            blockchainService.voteForCandidate(candidateId);
            LOGGER.log(Level.INFO, "Vote cast successfully for candidate ID: {0}", candidateId);
            return new ResponseEntity<>("Vote cast successfully!", HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error while casting vote: {0}", e.getMessage());
            return new ResponseEntity<>("Error while casting vote: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Endpoint to get vote count for a candidate
    @GetMapping("/voteCount/{candidateId}")
    public ResponseEntity<Object> getVoteCount(@PathVariable int candidateId) {
        if (candidateId <= 0) {
            return new ResponseEntity<>("Invalid candidate ID!", HttpStatus.BAD_REQUEST);
        }

        try {
            LOGGER.log(Level.INFO, "Fetching vote count for candidate ID: {0}", candidateId);
            BigInteger voteCount = blockchainService.getVoteCount(candidateId);
            LOGGER.log(Level.INFO, "Vote count for candidate ID {0}: {1}", new Object[]{candidateId, voteCount});
            return new ResponseEntity<>(voteCount, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error while fetching vote count: {0}", e.getMessage());
            return new ResponseEntity<>("Error while fetching vote count: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Endpoint to get the number of candidates
    @GetMapping("/candidatesCount")
    public ResponseEntity<Object> getCandidatesCount() {
        try {
            LOGGER.log(Level.INFO, "Fetching total candidates count...");
            BigInteger count = blockchainService.getCandidatesCount();
            LOGGER.log(Level.INFO, "Total candidates count: {0}", count);
            return new ResponseEntity<>(count, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error while fetching candidates count: {0}", e.getMessage());
            return new ResponseEntity<>("Error while fetching candidates count: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
