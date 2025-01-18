package com.voting.system;

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

    // Constructor injection for contract details
    public BlockchainService(@Value("${ethereum.rpc.url}") String rpcUrl,
                             @Value("${ethereum.contract.address}") String contractAddress,
                             @Value("${ethereum.private.key}") String privateKey) {
        this.web3j = Web3j.build(new HttpService(rpcUrl)); // Connect to Ethereum node
        this.contractAddress = contractAddress;
        this.privateKey = privateKey;
    }

    // Method to load the smart contract
    private Voting loadContract() {
        Credentials credentials = Credentials.create(privateKey);
        ContractGasProvider gasProvider = new DefaultGasProvider(); // Provides default gas settings
        return Voting.load(contractAddress, web3j, credentials, gasProvider);
    }

    // Method to add a candidate (requires admin rights)
    public void addCandidate(String name) throws Exception {
        Voting contract = loadContract();
        contract.addCandidate(name).send(); // Interact with the contract to add a candidate
    }

    // Method to cast a vote for a specific candidate
    public void voteForCandidate(int candidateId) throws Exception {
        Voting contract = loadContract();
        contract.vote(BigInteger.valueOf(candidateId)).send(); // Vote for the candidate
    }

    // Method to retrieve the vote count of a specific candidate
    public BigInteger getVoteCount(int candidateId) throws Exception {
        Voting contract = loadContract();
        return contract.getVoteCount(BigInteger.valueOf(candidateId)).send(); // Fetch vote count
    }

    // Method to get the total number of candidates
    public BigInteger getCandidatesCount() throws Exception {
        Voting contract = loadContract();
        return contract.getCandidatesCount().send(); // Fetch total number of candidates
    }
}
