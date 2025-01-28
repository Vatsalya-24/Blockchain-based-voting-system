
# Blockchain-based Voting System

This project implements a decentralized voting system using **Blockchain** to securely record and validate votes. The backend is built with **Spring Boot** and **Web3j** for blockchain integration.

We welcome contributions in the **backend** area.

---

## Table of Contents

- [Technologies Used](#technologies-used)
- [Project Structure](#project-structure)
- [Setup Instructions](#setup-instructions)
  - [Backend Setup](#backend-setup)
- [Running the Application](#running-the-application)
- [License](#license)

---

## Technologies Used

- **Backend**:
  - Java
  - Spring Boot
  - Web3j (Blockchain Integration)
  - Maven

---

## Project Structure

```
Blockchain_based_voting
│
├── .mvn
│   └── wrapper
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── voting
│   │   │           └── system
│   │   │               ├── contract
│   │   │               ├── entity
│   │   │               └── repository
│   │   └── resources
│   ├── test
│   │   └── java
│   │       └── Blockchain_based_voting
├── target
│   ├── classes
│   │   └── com
│   │       └── voting
│   │           └── system
│   │               ├── contract
│   │               ├── entity
│   │               └── repository
│   ├── generated-sources
│   │   └── annotations
│   ├── generated-test-sources
│   │   └── test-annotations
│   ├── maven-status
│   │   └── maven-compiler-plugin
│   │       ├── compile
│   │       └── testCompile
│   └── test-classes
│       └── Blockchain_based_voting
├── build
│   └── contracts
├── contracts
├── migrations
└── test
```

---

## Setup Instructions

### Backend Setup

1. **Clone the Repository**:
   Clone this repository to your local machine.

   ```bash
   git clone https://github.com/vatsalya-24/blockchain-based-voting.git
   ```

2. **Navigate to the Backend Directory**:
   Open the backend project directory.

   ```bash
   cd Blockchain-based-voting
   ```

3. **Configure Maven**:
   Ensure that **Maven** is installed on your machine. Run the following command to install the necessary dependencies:

   ```bash
   mvn clean install
   ```

4. **Run the Backend**:
   Once the dependencies are installed, run the backend using:

   ```bash
   mvn spring-boot:run
   ```

   The backend server should now be running on [http://localhost:8080](http://localhost:8080).

---

## Running the Application

1. **Start the Backend**:
   Ensure the backend is running as per the steps above. It handles the API for managing votes and interacting with the blockchain.

2. **Interacting with the Application**:
   - Navigate to [http://localhost:8080](http://localhost:8080) for API access.
   - Users can interact with the system to cast their votes, and the data will be recorded on the blockchain.

---

## License

This project is licensed under the MIT License. See the [LICENSE.md](LICENSE.md) file for more details.

---
