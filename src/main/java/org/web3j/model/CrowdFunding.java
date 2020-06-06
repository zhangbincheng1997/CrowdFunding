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
import org.web3j.tuples.generated.Tuple2;
import org.web3j.tuples.generated.Tuple5;
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
    public static final String BINARY = "608060405234801561001057600080fd5b5061057b806100206000396000f3fe60806040526004361061004a5760003560e01c8063141961bc1461004f5780632c0f7b6f1461011f578063c10d5bc914610146578063c1cbbca7146101fd578063ff5357ed1461021a575b600080fd5b34801561005b57600080fd5b506100796004803603602081101561007257600080fd5b503561026d565b60405180866001600160a01b03166001600160a01b0316815260200180602001858152602001848152602001838152602001828103825286818151815260200191508051906020019080838360005b838110156100e05781810151838201526020016100c8565b50505050905090810190601f16801561010d5780820380516001836020036101000a031916815260200191505b50965050505050505060405180910390f35b34801561012b57600080fd5b50610134610332565b60408051918252519081900360200190f35b34801561015257600080fd5b506101fb6004803603604081101561016957600080fd5b81019060208101813564010000000081111561018457600080fd5b82018360208201111561019657600080fd5b803590602001918460018302840111640100000000831117156101b857600080fd5b91908080601f0160208091040260200160405190810160405280939291908181526020018383808284376000920191909152509295505091359250610338915050565b005b6101fb6004803603602081101561021357600080fd5b50356103d0565b34801561022657600080fd5b5061024a6004803603604081101561023d57600080fd5b5080359060200135610484565b604080516001600160a01b03909316835260208301919091528051918290030190f35b600160208181526000928352604092839020805481840180548651600261010097831615979097026000190190911695909504601f81018590048502860185019096528585526001600160a01b03909116949193929091908301828280156103165780601f106102eb57610100808354040283529160200191610316565b820191906000526020600020905b8154815290600101906020018083116102f957829003601f168201915b5050505050908060020154908060030154908060040154905085565b60005481565b6040805160a081018252338152602080820185815282840185905260006060840181905260808401819052805460018082018355908252808452949020835181546001600160a01b0319166001600160a01b0390911617815590518051939491936103ab939285019291909101906104b4565b5060408201516002820155606082015160038201556080909101516004909101555050565b6000818152600160205260408082208054915190926001600160a01b03909216913480156108fc02929091818181858888f19350505050158015610418573d6000803e3d6000fd5b506003810180543490810190915560408051808201825233815260208082019384526000805481526002825283812060049096018054600181810190925582529590915291909120905181546001600160a01b0319166001600160a01b03909116178155905191015550565b6002602090815260009283526040808420909152908252902080546001909101546001600160a01b039091169082565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106104f557805160ff1916838001178555610522565b82800160010185558215610522579182015b82811115610522578251825591602001919060010190610507565b5061052e929150610532565b5090565b61054c91905b8082111561052e5760008155600101610538565b9056fea165627a7a72305820bb161891637acf9f005f0df4fd57c0923df9dcadcd6014d55167339c15feb2c10029";

    public static final String FUNC_CAMPAIGNS = "campaigns";

    public static final String FUNC_NUMCAMPAIGNS = "numCampaigns";

    public static final String FUNC_SPONSOR = "sponsor";

    public static final String FUNC_CONTRIBUTE = "contribute";

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

    public RemoteFunctionCall<Tuple5<String, String, BigInteger, BigInteger, BigInteger>> campaigns(BigInteger param0) {
        final Function function = new Function(FUNC_CAMPAIGNS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        return new RemoteFunctionCall<Tuple5<String, String, BigInteger, BigInteger, BigInteger>>(function,
                new Callable<Tuple5<String, String, BigInteger, BigInteger, BigInteger>>() {
                    @Override
                    public Tuple5<String, String, BigInteger, BigInteger, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple5<String, String, BigInteger, BigInteger, BigInteger>(
                                (String) results.get(0).getValue(), 
                                (String) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue(), 
                                (BigInteger) results.get(3).getValue(), 
                                (BigInteger) results.get(4).getValue());
                    }
                });
    }

    public RemoteFunctionCall<BigInteger> numCampaigns() {
        final Function function = new Function(FUNC_NUMCAMPAIGNS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> sponsor(String content, BigInteger goal) {
        final Function function = new Function(
                FUNC_SPONSOR, 
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

    public RemoteFunctionCall<Tuple2<String, BigInteger>> funders(BigInteger param0, BigInteger param1) {
        final Function function = new Function(FUNC_FUNDERS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0), 
                new org.web3j.abi.datatypes.generated.Uint256(param1)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
        return new RemoteFunctionCall<Tuple2<String, BigInteger>>(function,
                new Callable<Tuple2<String, BigInteger>>() {
                    @Override
                    public Tuple2<String, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple2<String, BigInteger>(
                                (String) results.get(0).getValue(), 
                                (BigInteger) results.get(1).getValue());
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
