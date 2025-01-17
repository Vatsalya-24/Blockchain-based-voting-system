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
    uint public candidatesCount;
    address public admin;

    // Declare events
    event CandidateAdded(uint candidateId, string name);
    event Voted(address indexed voter, uint candidateId);

    // Modifier to allow only admin to call certain functions
    modifier onlyAdmin() {
        require(msg.sender == admin, "Only admin can call this function");
        _;
    }

    // Modifier to prevent reentrancy attacks on vote function
    modifier nonReentrant() {
        bool _status = false;
        require(!_status, "Reentrancy attack detected!");
        _status = true;
        _;
        _status = false;
    }

    constructor() {
        admin = msg.sender;
    }

    // Function to add a candidate (only accessible by admin)
    function addCandidate(string memory _name) public onlyAdmin {
        candidatesCount++;
        candidates[candidatesCount] = Candidate(candidatesCount, _name, 0);
        emit CandidateAdded(candidatesCount, _name);
    }

    // Function for users to vote for a candidate
    function vote(uint _candidateId) public nonReentrant {
        require(!voters[msg.sender], "You have already voted!");
        require(_candidateId > 0 && _candidateId <= candidatesCount, "Invalid candidate ID");

        voters[msg.sender] = true;
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
    function hasVoted(address _voter) public view returns (bool) {
        return voters[_voter];
    }
}
