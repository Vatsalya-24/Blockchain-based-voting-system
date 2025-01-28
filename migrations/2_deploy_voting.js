const Voting = artifacts.require("Voting");

module.exports = function(deployer) {
  const votingStartTime = Math.floor(Date.now() / 1000); // Current time in seconds
  const votingEndTime = votingStartTime + 86400; // Voting ends in 24 hours (86400 seconds)

  // Log the start and end time for deployment clarity
  console.log("Deploying Voting contract...");
  console.log("Voting Start Time:", new Date(votingStartTime * 1000).toISOString());
  console.log("Voting End Time:", new Date(votingEndTime * 1000).toISOString());

  // Deploy the contract with error handling
  deployer.deploy(Voting, votingStartTime, votingEndTime)
    .then(() => {
      console.log("Voting contract deployed successfully.");
    })
    .catch(error => {
      console.error("Error deploying contract:", error);
      process.exit(1); // Exit with failure code in case of an error
    });
};
