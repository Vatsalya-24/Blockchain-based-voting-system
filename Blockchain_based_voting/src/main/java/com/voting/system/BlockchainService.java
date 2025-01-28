package com.voting.system;

import com.voting.system.entity.Candidate;
import com.voting.system.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;
import com.voting.system.contracts.Voting;

import java.math.BigInteger;

@Service
public class BlockchainService {

    private final Web3j web3j;  
    private final String contractAddress;
    private final String privateKey;
    private final CandidateRepository candidateRepository;

    public BlockchainService(
            @Value("${ethereum.rpc.url}") String rpcUrl,
            @Value("${ethereum.contract.address}") String contractAddress,
            @Value("${ethereum.private.key}") String privateKey,
            CandidateRepository candidateRepository) {
        this.web3j = Web3j.build(new HttpService(rpcUrl));
        this.contractAddress = contractAddress;
        this.privateKey = privateKey;
        this.candidateRepository = candidateRepository;
    }

    private Voting loadContract() {
        Credentials credentials = Credentials.create(privateKey);
        ContractGasProvider gasProvider = new DefaultGasProvider();
        return Voting.load(contractAddress, web3j, credentials, gasProvider);
    }

    public void addCandidate(String name) throws Exception {
        Voting contract = loadContract();
        try {
            contract.addCandidate(name).send();
            Candidate candidate = new Candidate();
            candidate.setName(name);
            candidate.setVoteCount(BigInteger.ZERO); // Initialize vote count
            candidateRepository.save(candidate);
        } catch (Exception e) {
            throw new Exception("Error adding candidate: " + e.getMessage(), e);
        }
    }

    public void voteForCandidate(int candidateId) throws Exception {
        Voting contract = loadContract();
        try {
            contract.vote(BigInteger.valueOf(candidateId)).send();
            Candidate candidate = candidateRepository.findById((long) candidateId)
                    .orElseThrow(() -> new Exception("Candidate not found in database"));
            candidate.setVoteCount(candidate.getVoteCount().add(BigInteger.ONE)); // Increment vote count
            candidateRepository.save(candidate);
        } catch (Exception e) {
            throw new Exception("Error voting for candidate: " + e.getMessage(), e);
        }
    }

    public BigInteger getVoteCount(int candidateId) throws Exception {
        Voting contract = loadContract();
        try {
            BigInteger voteCount = contract.getVoteCount(BigInteger.valueOf(candidateId)).send();
            Candidate candidate = candidateRepository.findById((long) candidateId)
                    .orElseThrow(() -> new Exception("Candidate not found in database"));
            candidate.setVoteCount(voteCount);
            candidateRepository.save(candidate); // Sync database with blockchain
            return voteCount;
        } catch (Exception e) {
            throw new Exception("Error retrieving vote count: " + e.getMessage(), e);
        }
    }

    public BigInteger getCandidatesCount() throws Exception {
        Voting contract = loadContract();
        try {
            return contract.getCandidatesCount().send();
        } catch (Exception e) {
            throw new Exception("Error retrieving candidates count: " + e.getMessage(), e);
        }
    }
}
