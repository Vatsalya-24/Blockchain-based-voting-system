package com.voting.system.contracts;

import io.reactivex.Flowable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple3;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;



/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/hyperledger-web3j/web3j/tree/main/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 1.6.2.
 */
@SuppressWarnings("rawtypes")
public class Voting extends Contract {
    public static final String BINARY = "0x608060405234801561001057600080fd5b5033600360006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550610e19806100616000396000f3fe608060405234801561001057600080fd5b50600436106100935760003560e01c8063462e91ec11610066578063462e91ec14610134578063a3ec138d14610150578063b2c2f2e814610180578063bb9aa28f146101b0578063f851a440146101ce57610093565b80630121b93f1461009857806309eef43e146100b45780632d35a8a2146100e45780633477ee2e14610102575b600080fd5b6100b260048036038101906100ad91906108a1565b6101ec565b005b6100ce60048036038101906100c99190610837565b6103e9565b6040516100db9190610a71565b60405180910390f35b6100ec61043f565b6040516100f99190610b0c565b60405180910390f35b61011c600480360381019061011791906108a1565b610445565b60405161012b93929190610b57565b60405180910390f35b61014e60048036038101906101499190610860565b6104f7565b005b61016a60048036038101906101659190610837565b610643565b6040516101779190610a71565b60405180910390f35b61019a600480360381019061019591906108a1565b610663565b6040516101a79190610b0c565b60405180910390f35b6101b86106d2565b6040516101c59190610b0c565b60405180910390f35b6101d66106dc565b6040516101e39190610a56565b60405180910390f35b6000801561022f576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161022690610a8c565b60405180910390fd5b60019050600160003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060009054906101000a900460ff16156102c0576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016102b790610aac565b60405180910390fd5b6000821180156102d257506002548211155b610311576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161030890610acc565b60405180910390fd5b60018060003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060006101000a81548160ff021916908315150217905550600080838152602001908152602001600020600201600081548092919061038e90610cce565b91905055503373ffffffffffffffffffffffffffffffffffffffff167f4d99b957a2bc29a30ebd96a7be8e68fe50a3c701db28a91436490b7d53870ca4836040516103d99190610b0c565b60405180910390a2600090505050565b6000600160008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060009054906101000a900460ff169050919050565b60025481565b600060205280600052604060002060009150905080600001549080600101805461046e90610c9c565b80601f016020809104026020016040519081016040528092919081815260200182805461049a90610c9c565b80156104e75780601f106104bc576101008083540402835291602001916104e7565b820191906000526020600020905b8154815290600101906020018083116104ca57829003601f168201915b5050505050908060020154905083565b600360009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff1614610587576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161057e90610aec565b60405180910390fd5b6002600081548092919061059a90610cce565b919050555060405180606001604052806002548152602001828152602001600081525060008060025481526020019081526020016000206000820151816000015560208201518160010190805190602001906105f7929190610702565b50604082015181600201559050507fe83b2a43e7e82d975c8a0a6d2f045153c869e111136a34d1889ab7b598e396a360025482604051610638929190610b27565b60405180910390a150565b60016020528060005260406000206000915054906101000a900460ff1681565b6000808211801561067657506002548211155b6106b5576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016106ac90610acc565b60405180910390fd5b600080838152602001908152602001600020600201549050919050565b6000600254905090565b600360009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b82805461070e90610c9c565b90600052602060002090601f0160209004810192826107305760008555610777565b82601f1061074957805160ff1916838001178555610777565b82800160010185558215610777579182015b8281111561077657825182559160200191906001019061075b565b5b5090506107849190610788565b5090565b5b808211156107a1576000816000905550600101610789565b5090565b60006107b86107b384610bc6565b610b95565b9050828152602081018484840111156107d057600080fd5b6107db848285610c5a565b509392505050565b6000813590506107f281610db5565b92915050565b600082601f83011261080957600080fd5b81356108198482602086016107a5565b91505092915050565b60008135905061083181610dcc565b92915050565b60006020828403121561084957600080fd5b6000610857848285016107e3565b91505092915050565b60006020828403121561087257600080fd5b600082013567ffffffffffffffff81111561088c57600080fd5b610898848285016107f8565b91505092915050565b6000602082840312156108b357600080fd5b60006108c184828501610822565b91505092915050565b6108d381610c12565b82525050565b6108e281610c24565b82525050565b60006108f382610bf6565b6108fd8185610c01565b935061090d818560208601610c69565b61091681610da4565b840191505092915050565b600061092e601b83610c01565b91507f5265656e7472616e63792061747461636b2064657465637465642100000000006000830152602082019050919050565b600061096e601783610c01565b91507f596f75206861766520616c726561647920766f746564210000000000000000006000830152602082019050919050565b60006109ae601483610c01565b91507f496e76616c69642063616e6469646174652049440000000000000000000000006000830152602082019050919050565b60006109ee602183610c01565b91507f4f6e6c792061646d696e2063616e2063616c6c20746869732066756e6374696f60008301527f6e000000000000000000000000000000000000000000000000000000000000006020830152604082019050919050565b610a5081610c50565b82525050565b6000602082019050610a6b60008301846108ca565b92915050565b6000602082019050610a8660008301846108d9565b92915050565b60006020820190508181036000830152610aa581610921565b9050919050565b60006020820190508181036000830152610ac581610961565b9050919050565b60006020820190508181036000830152610ae5816109a1565b9050919050565b60006020820190508181036000830152610b05816109e1565b9050919050565b6000602082019050610b216000830184610a47565b92915050565b6000604082019050610b3c6000830185610a47565b8181036020830152610b4e81846108e8565b90509392505050565b6000606082019050610b6c6000830186610a47565b8181036020830152610b7e81856108e8565b9050610b8d6040830184610a47565b949350505050565b6000604051905081810181811067ffffffffffffffff82111715610bbc57610bbb610d75565b5b8060405250919050565b600067ffffffffffffffff821115610be157610be0610d75565b5b601f19601f8301169050602081019050919050565b600081519050919050565b600082825260208201905092915050565b6000610c1d82610c30565b9050919050565b60008115159050919050565b600073ffffffffffffffffffffffffffffffffffffffff82169050919050565b6000819050919050565b82818337600083830152505050565b60005b83811015610c87578082015181840152602081019050610c6c565b83811115610c96576000848401525b50505050565b60006002820490506001821680610cb457607f821691505b60208210811415610cc857610cc7610d46565b5b50919050565b6000610cd982610c50565b91507fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff821415610d0c57610d0b610d17565b5b600182019050919050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052601160045260246000fd5b7f4e487b7100000000000000000000000000000000000000000000000000000000600052602260045260246000fd5b7f4e487b7100000000000000000000000000000000000000000000000000000000600052604160045260246000fd5b6000601f19601f8301169050919050565b610dbe81610c12565b8114610dc957600080fd5b50565b610dd581610c50565b8114610de057600080fd5b5056fea2646970667358221220a0184a00c280c4edefa4b4fb78b85fa0b9a42af57f4ff38095236b878e2e170d64736f6c63430008000033";

    private static String librariesLinkedBinary;

    public static final String FUNC_ADMIN = "admin";

    public static final String FUNC_CANDIDATES = "candidates";

    public static final String FUNC_CANDIDATESCOUNT = "candidatesCount";

    public static final String FUNC_VOTERS = "voters";

    public static final String FUNC_ADDCANDIDATE = "addCandidate";

    public static final String FUNC_VOTE = "vote";

    public static final String FUNC_GETVOTECOUNT = "getVoteCount";

    public static final String FUNC_GETCANDIDATESCOUNT = "getCandidatesCount";

    public static final String FUNC_HASVOTED = "hasVoted";

    public static final Event CANDIDATEADDED_EVENT = new Event("CandidateAdded", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}));
    ;

    public static final Event VOTED_EVENT = new Event("Voted", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}));
    ;

    @Deprecated
    protected Voting(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Voting(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Voting(String contractAddress, Web3j web3j, TransactionManager transactionManager,
            BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Voting(String contractAddress, Web3j web3j, TransactionManager transactionManager,
            ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static List<CandidateAddedEventResponse> getCandidateAddedEvents(
            TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(CANDIDATEADDED_EVENT, transactionReceipt);
        ArrayList<CandidateAddedEventResponse> responses = new ArrayList<CandidateAddedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            CandidateAddedEventResponse typedResponse = new CandidateAddedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.candidateId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.name = (String) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static CandidateAddedEventResponse getCandidateAddedEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(CANDIDATEADDED_EVENT, log);
        CandidateAddedEventResponse typedResponse = new CandidateAddedEventResponse();
        typedResponse.log = log;
        typedResponse.candidateId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.name = (String) eventValues.getNonIndexedValues().get(1).getValue();
        return typedResponse;
    }

    public Flowable<CandidateAddedEventResponse> candidateAddedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getCandidateAddedEventFromLog(log));
    }

    public Flowable<CandidateAddedEventResponse> candidateAddedEventFlowable(
            DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(CANDIDATEADDED_EVENT));
        return candidateAddedEventFlowable(filter);
    }

    public static List<VotedEventResponse> getVotedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(VOTED_EVENT, transactionReceipt);
        ArrayList<VotedEventResponse> responses = new ArrayList<VotedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            VotedEventResponse typedResponse = new VotedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.voter = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.candidateId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static VotedEventResponse getVotedEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(VOTED_EVENT, log);
        VotedEventResponse typedResponse = new VotedEventResponse();
        typedResponse.log = log;
        typedResponse.voter = (String) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.candidateId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
        return typedResponse;
    }

    public Flowable<VotedEventResponse> votedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getVotedEventFromLog(log));
    }

    public Flowable<VotedEventResponse> votedEventFlowable(DefaultBlockParameter startBlock,
            DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(VOTED_EVENT));
        return votedEventFlowable(filter);
    }

    public RemoteFunctionCall<String> admin() {
        final Function function = new Function(FUNC_ADMIN, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<Tuple3<BigInteger, String, BigInteger>> candidates(
            BigInteger param0) {
        final Function function = new Function(FUNC_CANDIDATES, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}));
        return new RemoteFunctionCall<Tuple3<BigInteger, String, BigInteger>>(function,
                new Callable<Tuple3<BigInteger, String, BigInteger>>() {
                    @Override
                    public Tuple3<BigInteger, String, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple3<BigInteger, String, BigInteger>(
                                (BigInteger) results.get(0).getValue(), 
                                (String) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue());
                    }
                });
    }

    public RemoteFunctionCall<BigInteger> candidatesCount() {
        final Function function = new Function(FUNC_CANDIDATESCOUNT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<Boolean> voters(String param0) {
        final Function function = new Function(FUNC_VOTERS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<TransactionReceipt> addCandidate(String _name) {
        final Function function = new Function(
                FUNC_ADDCANDIDATE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_name)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> vote(BigInteger _candidateId) {
        final Function function = new Function(
                FUNC_VOTE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_candidateId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> getVoteCount(BigInteger _candidateId) {
        final Function function = new Function(FUNC_GETVOTECOUNT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_candidateId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> getCandidatesCount() {
        final Function function = new Function(FUNC_GETCANDIDATESCOUNT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<Boolean> hasVoted(String _voter) {
        final Function function = new Function(FUNC_HASVOTED, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _voter)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    @Deprecated
    public static Voting load(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        return new Voting(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Voting load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Voting(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Voting load(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        return new Voting(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Voting load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Voting(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Voting> deploy(Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Voting.class, web3j, credentials, contractGasProvider, getDeploymentBinary(), "");
    }

    public static RemoteCall<Voting> deploy(Web3j web3j, TransactionManager transactionManager,
            ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Voting.class, web3j, transactionManager, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<Voting> deploy(Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Voting.class, web3j, credentials, gasPrice, gasLimit, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<Voting> deploy(Web3j web3j, TransactionManager transactionManager,
            BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Voting.class, web3j, transactionManager, gasPrice, gasLimit, getDeploymentBinary(), "");
    }

    // public static void linkLibraries(List<Contract.LinkReference> references) {
    //     librariesLinkedBinary = linkBinaryWithReferences(BINARY, references);
    // }

    private static String getDeploymentBinary() {
        if (librariesLinkedBinary != null) {
            return librariesLinkedBinary;
        } else {
            return BINARY;
        }
    }

    public static class CandidateAddedEventResponse extends BaseEventResponse {
        public BigInteger candidateId;

        public String name;
    }

    public static class VotedEventResponse extends BaseEventResponse {
        public String voter;

        public BigInteger candidateId;
    }
}
