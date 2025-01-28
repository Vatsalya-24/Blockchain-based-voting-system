// SPDX-License-Identifier: MIT
pragma solidity ^0.8.0;

contract Voting {

    struct Candidate {
        uint id;
        string name;
        uint voteCount;
    }

    mapping(uint => Candidate) public candidates;
    mapping(address => bool) public voters;
    mapping(string => bool) private candidateNames;
    mapping(address => bool) public registeredVoters;  // To track registered voters
    
    uint public candidatesCount;
    address public admin;
    
    uint public votingStartTime;
    uint public votingEndTime;
    bool private _status = false; // Variable for reentrancy protection

    address[] public allVoters;  // Store all voters' addresses

    // Declare events
    event CandidateAdded(uint candidateId, string name);
    event Voted(address indexed voter, uint candidateId);
    event VotingReset(); // Event for resetting voting process
    event VoterRegistered(address indexed voter); // Event for voter registration

    // Modifier to allow only admin to call certain functions
    modifier onlyAdmin() {
        require(msg.sender == admin, "Only admin can call this function");
        _;
    }

    // Modifier to prevent reentrancy attacks on vote function
    modifier nonReentrant() {
        require(!_status, "Reentrancy attack detected!");
        _status = true;
        _;
        _status = false;
    }

    // Modifier to ensure voting is within the allowed time
    modifier onlyDuringVotingPeriod() {
        require(block.timestamp >= votingStartTime && block.timestamp <= votingEndTime, "Voting is not allowed outside of the voting period.");
        _;
    }

    constructor(uint _votingStartTime, uint _votingEndTime) {
        admin = msg.sender;
        votingStartTime = _votingStartTime;
        votingEndTime = _votingEndTime;
    }

    // Function to add a candidate (only accessible by admin)
    function addCandidate(string memory _name) public onlyAdmin {
        require(!candidateNames[_name], "Candidate with this name already exists.");
        candidateNames[_name] = true;
        candidatesCount++;
        candidates[candidatesCount] = Candidate(candidatesCount, _name, 0);
        emit CandidateAdded(candidatesCount, _name);
    }

    // Function to register a voter (only accessible by admin)
    function registerVoter(address _voter) public onlyAdmin {
        require(!registeredVoters[_voter], "Voter is already registered.");
        registeredVoters[_voter] = true;
        emit VoterRegistered(_voter);
    }

    // Function for users to vote for a candidate (only during voting period)
    function vote(uint _candidateId) public nonReentrant onlyDuringVotingPeriod {
        require(registeredVoters[msg.sender], "You are not registered to vote!");
        require(!voters[msg.sender], "You have already voted!");
        require(_candidateId > 0 && _candidateId <= candidatesCount, "Invalid candidate ID");

        voters[msg.sender] = true;
        allVoters.push(msg.sender);  // Store voter address
        candidates[_candidateId].voteCount++;

        emit Voted(msg.sender, _candidateId);
    }

    // Function to get vote count of a specific candidate
    function getVoteCount(uint _candidateId) public view returns (uint) {
        require(_candidateId > 0 && _candidateId <= candidatesCount, "Invalid candidate ID");
        return candidates[_candidateId].voteCount;
    }

    // Function to get total number of candidates
    function getCandidatesCount() public view returns (uint) {
        return candidatesCount;
    }

    // Function to check if an address has voted
    function hasVotedFor(address _voter) public view returns (bool) {
        return voters[_voter];
    }

    // Function to reset the voting process (only accessible by admin)
    function resetVoting(uint _startTime, uint _endTime) public onlyAdmin {
        // Reset candidates' vote counts
        for (uint i = 1; i <= candidatesCount; i++) {
            candidates[i].voteCount = 0;
        }

        // Reset the voters' status
        for (uint i = 0; i < allVoters.length; i++) {
            voters[allVoters[i]] = false; // Reset voter status
        }
        delete allVoters;  // Clear the list of voters

        // Set new voting period
        votingStartTime = _startTime;
        votingEndTime = _endTime;

        emit VotingReset(); // Emit event for voting reset
    }

    // Function to get the list of all candidates
    function getCandidates() public view returns (Candidate[] memory) {
        Candidate[] memory allCandidates = new Candidate[](candidatesCount);
        for (uint i = 1; i <= candidatesCount; i++) {
            allCandidates[i - 1] = candidates[i];
        }
        return allCandidates;
    }
}
