package com.voting.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class VotingSystemApplication {

    private static final Logger logger = LoggerFactory.getLogger(VotingSystemApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(VotingSystemApplication.class, args);
        logger.info("Voting System Application is running...");
    }
}
