package com.redhat.crowdfunding.contract;

import com.redhat.crowdfunding.util.Consts;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

// 部署合约方式一 - wallet 部署
// 部署合约方式二 - geth 部署
// 部署合约方式三 - web3j 部署
public class CrowdFundingMain {

    public static void main(String args[]) throws Exception {
        // defaults to http://localhost:8545/
        Web3j web3j = Web3j.build(new HttpService());

        // 获取凭证
        Credentials credentials = WalletUtils.loadCredentials(Consts.PASSWORD, Consts.PATH);

        // 部署合约
        CrowdFundingContract contract = CrowdFundingContract.deploy(web3j, credentials, Consts.GAS_PRICE, Consts.GAS_LIMIT).send();

        // 返回地址
        // 部署合约方式三 - 记录地址 config.properties
        System.out.println("[ContractAddress] " + contract.getContractAddress());
    }
}