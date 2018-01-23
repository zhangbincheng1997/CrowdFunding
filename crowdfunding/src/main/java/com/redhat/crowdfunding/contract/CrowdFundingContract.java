package com.redhat.crowdfunding.contract;

import com.redhat.crowdfunding.util.Consts;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.*;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.utils.Convert;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

/**
 * @author littleredhat
 */
public class CrowdFundingContract extends Contract implements CrowdFundingInterface {

    // 合约二进制
    private static final String BINARY = Consts.BINARY;

    /**
     * CrowdFunding合约
     *
     * @param contractAddress 合约地址
     * @param web3j           RPC请求
     * @param credentials     钱包凭证
     * @param gasPrice        GAS价格
     * @param gasLimit        GAS上限
     */
    private CrowdFundingContract(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice,
                                 BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    /**
     * 部署合约
     *
     * @param web3j       RPC请求
     * @param credentials 钱包凭证
     * @param gasPrice    GAS价格
     * @param gasLimit    GAS上限
     * @return
     */
    public static RemoteCall<CrowdFundingContract> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        // 构造函数传参 NULL
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList());
        return deployRemoteCall(CrowdFundingContract.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    /**
     * 加载合约
     *
     * @param contractAddress 合约地址
     * @param web3j           RPC请求
     * @param credentials     钱包凭证
     * @param gasPrice        GAS价格
     * @param gasLimit        GAS上限
     * @return
     */
    public static CrowdFundingContract load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new CrowdFundingContract(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    //////////////////////////////////////////
    //          crowdfunding.sol            //
    //////////////////////////////////////////

    /**
     * 获取众筹项目数量
     * function getFundCount() public view returns (uint)
     */
    public RemoteCall<Uint256> getFundCount() {
        Function function = new Function("getFundCount", Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {
                }));
        return executeRemoteCallSingleValueReturn(function);
    }

    /**
     * 获取众筹项目信息
     * function getFundInfo(uint fundIndex) public view returns (address, string, uint, uint, bool)
     */
    public RemoteCall<List<Type>> getFundInfo(int fundIndex) {
        Function function = new Function("getFundInfo", Arrays.<Type>asList(new Uint256(fundIndex)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {
                }, new TypeReference<Utf8String>() {
                }, new TypeReference<Uint256>() {
                }, new TypeReference<Uint256>() {
                }, new TypeReference<Bool>() {
                }));
        return executeRemoteCallMultipleValueReturn(function);
    }

    /**
     * 获取众筹捐赠人数
     * function getRecordCount(uint fundIndex) public view returns (uint)
     */
    public RemoteCall<Uint256> getRecordCount(int fundIndex) {
        Function function = new Function("getRecordCount", Arrays.<Type>asList(new Uint256(fundIndex)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {
                }));
        return executeRemoteCallSingleValueReturn(function);
    }

    /**
     * 获取众筹捐赠记录
     * function getRecordInfo(uint fundIndex, uint recordIndex) public view returns (address, uint, uint)
     */
    public RemoteCall<List<Type>> getRecordInfo(int fundIndex, int recordIndex) {
        Function function = new Function("getRecordInfo", Arrays.<Type>asList(new Uint256(fundIndex), new Uint256(recordIndex)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {
                }, new TypeReference<Uint256>() {
                }, new TypeReference<Uint256>() {
                }));
        return executeRemoteCallMultipleValueReturn(function);
    }

    /**
     * 为自己发起众筹
     * function raiseFund(string desc, uint goal) public
     */
    public RemoteCall<TransactionReceipt> raiseFund(String desc, int goal) {
        Function function = new Function("raiseFund", Arrays.<Type>asList(new Utf8String(desc), new Uint256(goal)),
                Arrays.<TypeReference<?>>asList());
        return executeRemoteCallTransaction(function);
    }

    /**
     * 为别人发送金币
     * function sendCoin(uint fundIndex) public payable
     */
    public RemoteCall<TransactionReceipt> sendCoin(int fundIndex, int coin) {
        Function function = new Function("sendCoin", Arrays.<Type>asList(new Uint256(fundIndex)),
                Arrays.<TypeReference<?>>asList());
        return executeRemoteCallTransaction(function, BigInteger.valueOf(coin).multiply(Convert.Unit.ETHER.getWeiFactor().toBigInteger()));
    }
}