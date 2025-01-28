package com.voting.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class VotingSystemApplication {

    private static final Logger logger = LoggerFactory.getLogger(VotingSystemApplication.class);

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(VotingSystemApplication.class);
        Environment env = app.run(args).getEnvironment();

        // Get the port or set default if not configured
        String port = env.getProperty("server.port", "8080");
        String profiles = String.join(", ", env.getActiveProfiles());

        logger.info("Voting System Application is running...");
        logger.info("Access URLs: \nLocal: \t\thttp://localhost:{}\n", port);

        if (!profiles.isEmpty()) {
            logger.info("Active Profiles: {}", profiles);
        } else {
            logger.info("No active profiles set. Using default profile.");
        }

        // Add shutdown hook
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            logger.info("Shutting down Voting System Application gracefully...");
        }));
    }
}
