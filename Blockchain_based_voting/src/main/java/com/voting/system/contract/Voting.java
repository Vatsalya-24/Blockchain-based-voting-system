package com.voting.system.contract;

import io.reactivex.Flowable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.DynamicStruct;
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
    public static final String BINARY = "0x60806040526000600860006101000a81548160ff0219169083151502179055503480156200002c57600080fd5b5060405162001d0b38038062001d0b8339818101604052810190620000529190620000c0565b33600560006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055508160068190555080600781905550505062000125565b600081519050620000ba816200010b565b92915050565b60008060408385031215620000d457600080fd5b6000620000e485828601620000a9565b9250506020620000f785828601620000a9565b9150509250929050565b6000819050919050565b620001168162000101565b81146200012257600080fd5b50565b611bd680620001356000396000f3fe608060405234801561001057600080fd5b50600436106101005760003560e01c80637686a55c11610097578063a9ff6df111610066578063a9ff6df1146102af578063b2c2f2e8146102df578063bb9aa28f1461030f578063f851a4401461032d57610100565b80637686a55c1461020357806377b446d11461021f578063856506741461024f578063a3ec138d1461027f57610100565b80632d35a8a2116100d35780632d35a8a21461017b5780633477ee2e1461019957806338db6dd3146101cb578063462e91ec146101e757610100565b80630121b93f1461010557806306a49fce146101215780631ea736e01461013f5780632019a6081461015d575b600080fd5b61011f600480360381019061011a9190611242565b61034b565b005b6101296106c4565b6040516101369190611703565b60405180910390f35b610147610884565b6040516101549190611840565b60405180910390f35b61016561088a565b6040516101729190611840565b60405180910390f35b610183610890565b6040516101909190611840565b60405180910390f35b6101b360048036038101906101ae9190611242565b610896565b6040516101c29392919061188b565b60405180910390f35b6101e560048036038101906101e091906111d8565b610948565b005b61020160048036038101906101fc9190611201565b610b03565b005b61021d6004803603810190610218919061126b565b610cf2565b005b610239600480360381019061023491906111d8565b610eed565b6040516102469190611725565b60405180910390f35b61026960048036038101906102649190611242565b610f43565b60405161027691906116e8565b60405180910390f35b610299600480360381019061029491906111d8565b610f82565b6040516102a69190611725565b60405180910390f35b6102c960048036038101906102c491906111d8565b610fa2565b6040516102d69190611725565b60405180910390f35b6102f960048036038101906102f49190611242565b610fc2565b6040516103069190611840565b60405180910390f35b610317611031565b6040516103249190611840565b60405180910390f35b61033561103b565b60405161034291906116e8565b60405180910390f35b600860009054906101000a900460ff161561039b576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161039290611760565b60405180910390fd5b6001600860006101000a81548160ff02191690831515021790555060065442101580156103ca57506007544211155b610409576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401610400906117c0565b60405180910390fd5b600360003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060009054906101000a900460ff16610495576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161048c90611740565b60405180910390fd5b600160003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060009054906101000a900460ff1615610522576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161051990611780565b60405180910390fd5b60008111801561053457506004548111155b610573576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161056a90611800565b60405180910390fd5b60018060003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060006101000a81548160ff0219169083151502179055506009339080600181540180825580915050600190039060005260206000200160009091909190916101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550600080828152602001908152602001600020600201600081548092919061065390611a8b565b91905055503373ffffffffffffffffffffffffffffffffffffffff167f4d99b957a2bc29a30ebd96a7be8e68fe50a3c701db28a91436490b7d53870ca48260405161069e9190611840565b60405180910390a26000600860006101000a81548160ff02191690831515021790555050565b6060600060045467ffffffffffffffff81111561070a577f4e487b7100000000000000000000000000000000000000000000000000000000600052604160045260246000fd5b60405190808252806020026020018201604052801561074357816020015b610730611061565b8152602001906001900390816107285790505b5090506000600190505b600454811161087c576000808281526020019081526020016000206040518060600160405290816000820154815260200160018201805461078d90611a59565b80601f01602080910402602001604051908101604052809291908181526020018280546107b990611a59565b80156108065780601f106107db57610100808354040283529160200191610806565b820191906000526020600020905b8154815290600101906020018083116107e957829003601f168201915b5050505050815260200160028201548152505082600183610827919061199b565b8151811061085e577f4e487b7100000000000000000000000000000000000000000000000000000000600052603260045260246000fd5b6020026020010181905250808061087490611a8b565b91505061074d565b508091505090565b60075481565b60065481565b60045481565b60006020528060005260406000206000915090508060000154908060010180546108bf90611a59565b80601f01602080910402602001604051908101604052809291908181526020018280546108eb90611a59565b80156109385780601f1061090d57610100808354040283529160200191610938565b820191906000526020600020905b81548152906001019060200180831161091b57829003601f168201915b5050505050908060020154905083565b600560009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff16146109d8576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016109cf90611820565b60405180910390fd5b600360008273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060009054906101000a900460ff1615610a65576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401610a5c906117e0565b60405180910390fd5b6001600360008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060006101000a81548160ff0219169083151502179055508073ffffffffffffffffffffffffffffffffffffffff167fb6be2187d059cc2a55fe29e0e503b566e1e0f8c8780096e185429350acffd3dd60405160405180910390a250565b600560009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff1614610b93576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401610b8a90611820565b60405180910390fd5b600281604051610ba391906116d1565b908152602001604051809103902060009054906101000a900460ff1615610bff576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401610bf6906117a0565b60405180910390fd5b6001600282604051610c1191906116d1565b908152602001604051809103902060006101000a81548160ff02191690831515021790555060046000815480929190610c4990611a8b565b91905055506040518060600160405280600454815260200182815260200160008152506000806004548152602001908152602001600020600082015181600001556020820151816001019080519060200190610ca6929190611082565b50604082015181600201559050507fe83b2a43e7e82d975c8a0a6d2f045153c869e111136a34d1889ab7b598e396a360045482604051610ce792919061185b565b60405180910390a150565b600560009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff1614610d82576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401610d7990611820565b60405180910390fd5b6000600190505b6004548111610dc0576000806000838152602001908152602001600020600201819055508080610db890611a8b565b915050610d89565b5060005b600980549050811015610ea05760006001600060098481548110610e11577f4e487b7100000000000000000000000000000000000000000000000000000000600052603260045260246000fd5b9060005260206000200160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060006101000a81548160ff0219169083151502179055508080610e9890611a8b565b915050610dc4565b5060096000610eaf9190611108565b81600681905550806007819055507feb4b0a76799cb305ae86d5a0b30e0e77b1e23fa8cb85eb788ede212bd6b324a660405160405180910390a15050565b6000600160008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060009054906101000a900460ff169050919050565b60098181548110610f5357600080fd5b906000526020600020016000915054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b60016020528060005260406000206000915054906101000a900460ff1681565b60036020528060005260406000206000915054906101000a900460ff1681565b60008082118015610fd557506004548211155b611014576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161100b90611800565b60405180910390fd5b600080838152602001908152602001600020600201549050919050565b6000600454905090565b600560009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b60405180606001604052806000815260200160608152602001600081525090565b82805461108e90611a59565b90600052602060002090601f0160209004810192826110b057600085556110f7565b82601f106110c957805160ff19168380011785556110f7565b828001600101855582156110f7579182015b828111156110f65782518255916020019190600101906110db565b5b5090506111049190611129565b5090565b50805460008255906000526020600020908101906111269190611129565b50565b5b8082111561114257600081600090555060010161112a565b5090565b6000611159611154846118fa565b6118c9565b90508281526020810184848401111561117157600080fd5b61117c848285611a17565b509392505050565b60008135905061119381611b72565b92915050565b600082601f8301126111aa57600080fd5b81356111ba848260208601611146565b91505092915050565b6000813590506111d281611b89565b92915050565b6000602082840312156111ea57600080fd5b60006111f884828501611184565b91505092915050565b60006020828403121561121357600080fd5b600082013567ffffffffffffffff81111561122d57600080fd5b61123984828501611199565b91505092915050565b60006020828403121561125457600080fd5b6000611262848285016111c3565b91505092915050565b6000806040838503121561127e57600080fd5b600061128c858286016111c3565b925050602061129d858286016111c3565b9150509250929050565b60006112b38383611663565b905092915050565b6112c4816119cf565b82525050565b60006112d58261193a565b6112df818561195d565b9350836020820285016112f18561192a565b8060005b8581101561132d578484038952815161130e85826112a7565b945061131983611950565b925060208a019950506001810190506112f5565b50829750879550505050505092915050565b611348816119e1565b82525050565b600061135982611945565b611363818561196e565b9350611373818560208601611a26565b61137c81611b61565b840191505092915050565b600061139282611945565b61139c818561197f565b93506113ac818560208601611a26565b6113b581611b61565b840191505092915050565b60006113cb82611945565b6113d58185611990565b93506113e5818560208601611a26565b80840191505092915050565b60006113fe601f8361197f565b91507f596f7520617265206e6f74207265676973746572656420746f20766f746521006000830152602082019050919050565b600061143e601b8361197f565b91507f5265656e7472616e63792061747461636b2064657465637465642100000000006000830152602082019050919050565b600061147e60178361197f565b91507f596f75206861766520616c726561647920766f746564210000000000000000006000830152602082019050919050565b60006114be60288361197f565b91507f43616e64696461746520776974682074686973206e616d6520616c726561647960008301527f206578697374732e0000000000000000000000000000000000000000000000006020830152604082019050919050565b600061152460338361197f565b91507f566f74696e67206973206e6f7420616c6c6f776564206f757473696465206f6660008301527f2074686520766f74696e6720706572696f642e000000000000000000000000006020830152604082019050919050565b600061158a601c8361197f565b91507f566f74657220697320616c726561647920726567697374657265642e000000006000830152602082019050919050565b60006115ca60148361197f565b91507f496e76616c69642063616e6469646174652049440000000000000000000000006000830152602082019050919050565b600061160a60218361197f565b91507f4f6e6c792061646d696e2063616e2063616c6c20746869732066756e6374696f60008301527f6e000000000000000000000000000000000000000000000000000000000000006020830152604082019050919050565b600060608301600083015161167b60008601826116b3565b5060208301518482036020860152611693828261134e565b91505060408301516116a860408601826116b3565b508091505092915050565b6116bc81611a0d565b82525050565b6116cb81611a0d565b82525050565b60006116dd82846113c0565b915081905092915050565b60006020820190506116fd60008301846112bb565b92915050565b6000602082019050818103600083015261171d81846112ca565b905092915050565b600060208201905061173a600083018461133f565b92915050565b60006020820190508181036000830152611759816113f1565b9050919050565b6000602082019050818103600083015261177981611431565b9050919050565b6000602082019050818103600083015261179981611471565b9050919050565b600060208201905081810360008301526117b9816114b1565b9050919050565b600060208201905081810360008301526117d981611517565b9050919050565b600060208201905081810360008301526117f98161157d565b9050919050565b60006020820190508181036000830152611819816115bd565b9050919050565b60006020820190508181036000830152611839816115fd565b9050919050565b600060208201905061185560008301846116c2565b92915050565b600060408201905061187060008301856116c2565b81810360208301526118828184611387565b90509392505050565b60006060820190506118a060008301866116c2565b81810360208301526118b28185611387565b90506118c160408301846116c2565b949350505050565b6000604051905081810181811067ffffffffffffffff821117156118f0576118ef611b32565b5b8060405250919050565b600067ffffffffffffffff82111561191557611914611b32565b5b601f19601f8301169050602081019050919050565b6000819050602082019050919050565b600081519050919050565b600081519050919050565b6000602082019050919050565b600082825260208201905092915050565b600082825260208201905092915050565b600082825260208201905092915050565b600081905092915050565b60006119a682611a0d565b91506119b183611a0d565b9250828210156119c4576119c3611ad4565b5b828203905092915050565b60006119da826119ed565b9050919050565b60008115159050919050565b600073ffffffffffffffffffffffffffffffffffffffff82169050919050565b6000819050919050565b82818337600083830152505050565b60005b83811015611a44578082015181840152602081019050611a29565b83811115611a53576000848401525b50505050565b60006002820490506001821680611a7157607f821691505b60208210811415611a8557611a84611b03565b5b50919050565b6000611a9682611a0d565b91507fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff821415611ac957611ac8611ad4565b5b600182019050919050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052601160045260246000fd5b7f4e487b7100000000000000000000000000000000000000000000000000000000600052602260045260246000fd5b7f4e487b7100000000000000000000000000000000000000000000000000000000600052604160045260246000fd5b6000601f19601f8301169050919050565b611b7b816119cf565b8114611b8657600080fd5b50565b611b9281611a0d565b8114611b9d57600080fd5b5056fea264697066735822122021dc10902d1d6a64087ddb8a96afa23d6b72525122edb095a4e5dc33b54eef0164736f6c63430008000033";

    private static String librariesLinkedBinary;

    public static final String FUNC_ADMIN = "admin";

    public static final String FUNC_ALLVOTERS = "allVoters";

    public static final String FUNC_CANDIDATES = "candidates";

    public static final String FUNC_CANDIDATESCOUNT = "candidatesCount";

    public static final String FUNC_REGISTEREDVOTERS = "registeredVoters";

    public static final String FUNC_VOTERS = "voters";

    public static final String FUNC_VOTINGENDTIME = "votingEndTime";

    public static final String FUNC_VOTINGSTARTTIME = "votingStartTime";

    public static final String FUNC_ADDCANDIDATE = "addCandidate";

    public static final String FUNC_REGISTERVOTER = "registerVoter";

    public static final String FUNC_VOTE = "vote";

    public static final String FUNC_GETVOTECOUNT = "getVoteCount";

    public static final String FUNC_GETCANDIDATESCOUNT = "getCandidatesCount";

    public static final String FUNC_HASVOTEDFOR = "hasVotedFor";

    public static final String FUNC_RESETVOTING = "resetVoting";

    public static final String FUNC_GETCANDIDATES = "getCandidates";

    public static final Event CANDIDATEADDED_EVENT = new Event("CandidateAdded", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}));
    ;

    public static final Event VOTED_EVENT = new Event("Voted", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event VOTERREGISTERED_EVENT = new Event("VoterRegistered", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}));
    ;

    public static final Event VOTINGRESET_EVENT = new Event("VotingReset", 
            Arrays.<TypeReference<?>>asList());
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

    public static List<VoterRegisteredEventResponse> getVoterRegisteredEvents(
            TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(VOTERREGISTERED_EVENT, transactionReceipt);
        ArrayList<VoterRegisteredEventResponse> responses = new ArrayList<VoterRegisteredEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            VoterRegisteredEventResponse typedResponse = new VoterRegisteredEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.voter = (String) eventValues.getIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static VoterRegisteredEventResponse getVoterRegisteredEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(VOTERREGISTERED_EVENT, log);
        VoterRegisteredEventResponse typedResponse = new VoterRegisteredEventResponse();
        typedResponse.log = log;
        typedResponse.voter = (String) eventValues.getIndexedValues().get(0).getValue();
        return typedResponse;
    }

    public Flowable<VoterRegisteredEventResponse> voterRegisteredEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getVoterRegisteredEventFromLog(log));
    }

    public Flowable<VoterRegisteredEventResponse> voterRegisteredEventFlowable(
            DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(VOTERREGISTERED_EVENT));
        return voterRegisteredEventFlowable(filter);
    }

    public static List<VotingResetEventResponse> getVotingResetEvents(
            TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(VOTINGRESET_EVENT, transactionReceipt);
        ArrayList<VotingResetEventResponse> responses = new ArrayList<VotingResetEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            VotingResetEventResponse typedResponse = new VotingResetEventResponse();
            typedResponse.log = eventValues.getLog();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static VotingResetEventResponse getVotingResetEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(VOTINGRESET_EVENT, log);
        VotingResetEventResponse typedResponse = new VotingResetEventResponse();
        typedResponse.log = log;
        return typedResponse;
    }

    public Flowable<VotingResetEventResponse> votingResetEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getVotingResetEventFromLog(log));
    }

    public Flowable<VotingResetEventResponse> votingResetEventFlowable(
            DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(VOTINGRESET_EVENT));
        return votingResetEventFlowable(filter);
    }

    public RemoteFunctionCall<String> admin() {
        final Function function = new Function(FUNC_ADMIN, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> allVoters(BigInteger param0) {
        final Function function = new Function(FUNC_ALLVOTERS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
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

    public RemoteFunctionCall<Boolean> registeredVoters(String param0) {
        final Function function = new Function(FUNC_REGISTEREDVOTERS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<Boolean> voters(String param0) {
        final Function function = new Function(FUNC_VOTERS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<BigInteger> votingEndTime() {
        final Function function = new Function(FUNC_VOTINGENDTIME, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> votingStartTime() {
        final Function function = new Function(FUNC_VOTINGSTARTTIME, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> addCandidate(String _name) {
        final Function function = new Function(
                FUNC_ADDCANDIDATE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_name)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> registerVoter(String _voter) {
        final Function function = new Function(
                FUNC_REGISTERVOTER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _voter)), 
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

    public RemoteFunctionCall<Boolean> hasVotedFor(String _voter) {
        final Function function = new Function(FUNC_HASVOTEDFOR, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _voter)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<TransactionReceipt> resetVoting(BigInteger _startTime,
            BigInteger _endTime) {
        final Function function = new Function(
                FUNC_RESETVOTING, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_startTime), 
                new org.web3j.abi.datatypes.generated.Uint256(_endTime)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<List> getCandidates() {
        final Function function = new Function(FUNC_GETCANDIDATES, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Candidate>>() {}));
        return new RemoteFunctionCall<List>(function,
                new Callable<List>() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public List call() throws Exception {
                        List<Type> result = (List<Type>) executeCallSingleValueReturn(function, List.class);
                        return convertToNative(result);
                    }
                });
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
            ContractGasProvider contractGasProvider, BigInteger _votingStartTime,
            BigInteger _votingEndTime) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_votingStartTime), 
                new org.web3j.abi.datatypes.generated.Uint256(_votingEndTime)));
        return deployRemoteCall(Voting.class, web3j, credentials, contractGasProvider, getDeploymentBinary(), encodedConstructor);
    }

    public static RemoteCall<Voting> deploy(Web3j web3j, TransactionManager transactionManager,
            ContractGasProvider contractGasProvider, BigInteger _votingStartTime,
            BigInteger _votingEndTime) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_votingStartTime), 
                new org.web3j.abi.datatypes.generated.Uint256(_votingEndTime)));
        return deployRemoteCall(Voting.class, web3j, transactionManager, contractGasProvider, getDeploymentBinary(), encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<Voting> deploy(Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit, BigInteger _votingStartTime,
            BigInteger _votingEndTime) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_votingStartTime), 
                new org.web3j.abi.datatypes.generated.Uint256(_votingEndTime)));
        return deployRemoteCall(Voting.class, web3j, credentials, gasPrice, gasLimit, getDeploymentBinary(), encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<Voting> deploy(Web3j web3j, TransactionManager transactionManager,
            BigInteger gasPrice, BigInteger gasLimit, BigInteger _votingStartTime,
            BigInteger _votingEndTime) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_votingStartTime), 
                new org.web3j.abi.datatypes.generated.Uint256(_votingEndTime)));
        return deployRemoteCall(Voting.class, web3j, transactionManager, gasPrice, gasLimit, getDeploymentBinary(), encodedConstructor);
    }

    public static void linkLibraries(List<Contract.LinkReference> references) {
        librariesLinkedBinary = linkBinaryWithReferences(BINARY, references);
    }

    private static String getDeploymentBinary() {
        if (librariesLinkedBinary != null) {
            return librariesLinkedBinary;
        } else {
            return BINARY;
        }
    }

    public static class Candidate extends DynamicStruct {
        public BigInteger id;

        public String name;

        public BigInteger voteCount;

        public Candidate(BigInteger id, String name, BigInteger voteCount) {
            super(new org.web3j.abi.datatypes.generated.Uint256(id), 
                    new org.web3j.abi.datatypes.Utf8String(name), 
                    new org.web3j.abi.datatypes.generated.Uint256(voteCount));
            this.id = id;
            this.name = name;
            this.voteCount = voteCount;
        }

        public Candidate(Uint256 id, Utf8String name, Uint256 voteCount) {
            super(id, name, voteCount);
            this.id = id.getValue();
            this.name = name.getValue();
            this.voteCount = voteCount.getValue();
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

    public static class VoterRegisteredEventResponse extends BaseEventResponse {
        public String voter;
    }

    public static class VotingResetEventResponse extends BaseEventResponse {
    }
}
