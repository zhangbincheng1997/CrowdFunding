package org.web3j.model;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple3;
import org.web3j.tuples.generated.Tuple6;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 4.5.11.
 */
@SuppressWarnings("rawtypes")
public class CrowdFunding extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b506105fe806100206000396000f3fe6080604052600436106100555760003560e01c8063141961bc1461005a5780632c0f7b6f1461013157806346f81a8714610158578063c1cbbca71461020f578063cdff98351461022c578063ff5357ed14610256575b600080fd5b34801561006657600080fd5b506100846004803603602081101561007d57600080fd5b50356102ae565b60405180876001600160a01b03166001600160a01b0316815260200180602001868152602001858152602001848152602001838152602001828103825287818151815260200191508051906020019080838360005b838110156100f15781810151838201526020016100d9565b50505050905090810190601f16801561011e5780820380516001836020036101000a031916815260200191505b5097505050505050505060405180910390f35b34801561013d57600080fd5b50610146610379565b60408051918252519081900360200190f35b34801561016457600080fd5b5061020d6004803603604081101561017b57600080fd5b81019060208101813564010000000081111561019657600080fd5b8201836020820111156101a857600080fd5b803590602001918460018302840111640100000000831117156101ca57600080fd5b91908080601f016020809104026020016040519081016040528093929190818152602001838380828437600092019190915250929550509135925061037f915050565b005b61020d6004803603602081101561022557600080fd5b5035610427565b34801561023857600080fd5b506101466004803603602081101561024f57600080fd5b50356104e8565b34801561026257600080fd5b506102866004803603604081101561027957600080fd5b50803590602001356104fd565b604080516001600160a01b039094168452602084019290925282820152519081900360600190f35b600160208181526000928352604092839020805481840180548651600261010097831615979097026000190190911695909504601f81018590048502860185019096528585526001600160a01b03909116949193929091908301828280156103575780601f1061032c57610100808354040283529160200191610357565b820191906000526020600020905b81548152906001019060200180831161033a57829003601f168201915b5050505050908060020154908060030154908060040154908060060154905086565b60005481565b6040805160c0810182523381526020808201858152828401859052600060608401819052608084018190524260a0850152805460018082018355908252808452949020835181546001600160a01b0319166001600160a01b0390911617815590518051939491936103f893928501929190910190610537565b5060408201516002820155606082015160038201556080820151600482015560a0909101516006909101555050565b6000818152600160205260408082208054915190926001600160a01b03909216913480156108fc02929091818181858888f1935050505015801561046f573d6000803e3d6000fd5b50600381018054349081019091556040805160608101825233815260208082019384524282840190815260048601805460018181019092556000908152600590970190925292909420905181546001600160a01b0319166001600160a01b03909116178155915192820192909255905160029091015550565b60009081526001602052604090206004015490565b60009182526001602081815260408085209385526005909301905291208054918101546002909101546001600160a01b0390921692909190565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f1061057857805160ff19168380011785556105a5565b828001600101855582156105a5579182015b828111156105a557825182559160200191906001019061058a565b506105b19291506105b5565b5090565b6105cf91905b808211156105b157600081556001016105bb565b9056fea165627a7a72305820bbd2e477e10e04b4a2f0b3065a47cc9dd2e4c55528cadd118d1daf4bce4f28a00029";

    public static final String FUNC_CAMPAIGNS = "campaigns";

    public static final String FUNC_NUMCAMPAIGNS = "numCampaigns";

    public static final String FUNC_CREATE = "create";

    public static final String FUNC_CONTRIBUTE = "contribute";

    public static final String FUNC_NUMFUNDERS = "numFunders";

    public static final String FUNC_FUNDERS = "funders";

    @Deprecated
    protected CrowdFunding(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected CrowdFunding(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected CrowdFunding(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected CrowdFunding(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<Tuple6<String, String, BigInteger, BigInteger, BigInteger, BigInteger>> campaigns(BigInteger param0) {
        final Function function = new Function(FUNC_CAMPAIGNS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        return new RemoteFunctionCall<Tuple6<String, String, BigInteger, BigInteger, BigInteger, BigInteger>>(function,
                new Callable<Tuple6<String, String, BigInteger, BigInteger, BigInteger, BigInteger>>() {
                    @Override
                    public Tuple6<String, String, BigInteger, BigInteger, BigInteger, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple6<String, String, BigInteger, BigInteger, BigInteger, BigInteger>(
                                (String) results.get(0).getValue(), 
                                (String) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue(), 
                                (BigInteger) results.get(3).getValue(), 
                                (BigInteger) results.get(4).getValue(), 
                                (BigInteger) results.get(5).getValue());
                    }
                });
    }

    public RemoteFunctionCall<BigInteger> numCampaigns() {
        final Function function = new Function(FUNC_NUMCAMPAIGNS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> create(String content, BigInteger goal) {
        final Function function = new Function(
                FUNC_CREATE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(content), 
                new org.web3j.abi.datatypes.generated.Uint256(goal)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> contribute(BigInteger campaignID, BigInteger weiValue) {
        final Function function = new Function(
                FUNC_CONTRIBUTE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(campaignID)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteFunctionCall<BigInteger> numFunders(BigInteger campaignID) {
        final Function function = new Function(FUNC_NUMFUNDERS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(campaignID)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<Tuple3<String, BigInteger, BigInteger>> funders(BigInteger campaignID, BigInteger funderID) {
        final Function function = new Function(FUNC_FUNDERS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(campaignID), 
                new org.web3j.abi.datatypes.generated.Uint256(funderID)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        return new RemoteFunctionCall<Tuple3<String, BigInteger, BigInteger>>(function,
                new Callable<Tuple3<String, BigInteger, BigInteger>>() {
                    @Override
                    public Tuple3<String, BigInteger, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple3<String, BigInteger, BigInteger>(
                                (String) results.get(0).getValue(), 
                                (BigInteger) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue());
                    }
                });
    }

    @Deprecated
    public static CrowdFunding load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new CrowdFunding(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static CrowdFunding load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new CrowdFunding(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static CrowdFunding load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new CrowdFunding(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static CrowdFunding load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new CrowdFunding(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<CrowdFunding> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(CrowdFunding.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<CrowdFunding> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(CrowdFunding.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<CrowdFunding> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(CrowdFunding.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<CrowdFunding> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(CrowdFunding.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }
}
