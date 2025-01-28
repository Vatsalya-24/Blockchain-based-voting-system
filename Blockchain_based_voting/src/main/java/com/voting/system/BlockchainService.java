package com.voting.system;

import com.voting.system.entity.Candidate;
import com.voting.system.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.EthSyncing;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;
import com.voting.system.contract.Voting;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.math.BigInteger;

@Service
public class BlockchainService {

    private static final Logger logger = LoggerFactory.getLogger(BlockchainService.class);

    private final Web3j web3j;
    private final String contractAddress;
    private final String privateKey;
    private final CandidateRepository candidateRepository;
    private final boolean isGanache;

    public BlockchainService(
            @Value("${ethereum.rpc.url}") String rpcUrl,
            @Value("${ethereum.contract.address}") String contractAddress,
            @Value("${ethereum.private.key}") String privateKey,
            @Value("${ethereum.network.ganache:false}") boolean isGanache,
            CandidateRepository candidateRepository) {
        this.web3j = Web3j.build(new HttpService(rpcUrl));
        this.contractAddress = contractAddress;
        this.privateKey = privateKey;
        this.candidateRepository = candidateRepository;
        this.isGanache = isGanache;

        if (!isGanache) {
            checkNodeSync();
        }
    }

    private void checkNodeSync() {
        try {
            EthSyncing ethSyncing = web3j.ethSyncing().send();
            if (ethSyncing.isSyncing()) {
                logger.warn("Node is still syncing...");
            } else {
                logger.info("Node is fully synced!");
            }
        } catch (IOException e) {
            logger.error("Error checking sync status: {}", e.getMessage());
        }
    }

    private Voting loadContract() {
        Credentials credentials = Credentials.create(privateKey);
        ContractGasProvider gasProvider = new DefaultGasProvider();
        return Voting.load(contractAddress, web3j, credentials, gasProvider);
    }

    public void addCandidate(String name) throws Exception {
        // Input validation
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Candidate name cannot be empty or null.");
        }

        if (!name.matches("^[a-zA-Z\\s]+$")) {
            throw new IllegalArgumentException("Candidate name contains invalid characters.");
        }

        logger.info("Received candidate name: {}", name); // Added logging

       Voting contract = loadContract();
       System.err.println("iam here");
        
        logger.info("Adding candidate to blockchain: {}", name); // Added logging
        String transactionHash = contract.addCandidate(name).send().getTransactionHash();
        logger.info("Candidate added to blockchain. Name={}, Transaction Hash={}", name, transactionHash);

            // Save to database
            Candidate candidate = new Candidate();
            candidate.setName(name);
            candidate.setVoteCount(BigInteger.ZERO);
            candidateRepository.save(candidate);
            logger.info("Candidate {} saved to database.", name);

        }


    public void voteForCandidate(int candidateId) throws Exception {
        if (candidateId <= 0) {
            throw new IllegalArgumentException("Candidate ID must be a positive integer.");
        }

        if (!isGanache) {
            checkNodeSync();
        }

        Voting contract = loadContract();
        try {
            Candidate candidate = candidateRepository.findById((long) candidateId)
                    .orElseThrow(() -> new Exception("Candidate not found in database"));

            String transactionHash = contract.vote(BigInteger.valueOf(candidateId)).send().getTransactionHash();
            logger.info("Vote cast on blockchain. Candidate ID={}, Transaction Hash={}", candidateId, transactionHash);

            candidate.setVoteCount(candidate.getVoteCount().add(BigInteger.ONE));
            candidateRepository.save(candidate);
            logger.info("Vote count updated in database for candidate ID {}.", candidateId);

        } catch (Exception e) {
            logger.error("Error voting for candidate: {}", e.getMessage());
            throw new Exception("Error voting for candidate: " + e.getMessage(), e);
        }
    }

    public BigInteger getVoteCount(int candidateId) throws Exception {
        if (candidateId <= 0) {
            throw new IllegalArgumentException("Candidate ID must be a positive integer.");
        }

        if (!isGanache) {
            checkNodeSync();
        }

        Voting contract = loadContract();
        try {
            BigInteger voteCount = contract.getVoteCount(BigInteger.valueOf(candidateId)).send();
            logger.info("Vote count retrieved from blockchain for candidate ID {}: {}", candidateId, voteCount);

            Candidate candidate = candidateRepository.findById((long) candidateId)
                    .orElseThrow(() -> new Exception("Candidate not found in database"));

            candidate.setVoteCount(voteCount);
            candidateRepository.save(candidate);
            logger.info("Database synced with blockchain for candidate ID {}.", candidateId);

            return voteCount;
        } catch (Exception e) {
            logger.error("Error retrieving vote count: {}", e.getMessage());
            throw new Exception("Error retrieving vote count: " + e.getMessage(), e);
        }
    }

    public BigInteger getCandidatesCount() throws Exception {
        if (!isGanache) {
            checkNodeSync();
        }

        Voting contract = loadContract();
        try {
            BigInteger candidatesCount = contract.getCandidatesCount().send();
            logger.info("Total candidates retrieved from blockchain: {}", candidatesCount);
            return candidatesCount;
        } catch (Exception e) {
            logger.error("Error retrieving candidates count: {}", e.getMessage());
            throw new Exception("Error retrieving candidates count: " + e.getMessage(), e);
        }
    }
}
