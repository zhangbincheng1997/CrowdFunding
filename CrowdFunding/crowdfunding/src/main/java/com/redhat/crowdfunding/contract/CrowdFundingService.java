package com.redhat.crowdfunding.contract;

import java.math.BigInteger;

import org.web3j.abi.datatypes.Address;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

import com.redhat.crowdfunding.util.Consts;
import com.redhat.crowdfunding.util.Util;

/**
 * @author littleredhat
 */
public class CrowdFundingService {

	// 部署合约
	public String deploy(String beneficiary) throws Exception {
		Web3j web3j = Web3j.build(new HttpService());
		// 获取管理员凭证
		Credentials credentials = Util.GetCredentials();

		// 部署合约
		CrowdFundingContract contract = CrowdFundingContract
				.deploy(web3j, credentials, BigInteger.valueOf(Consts.GAS_PRICE), BigInteger.valueOf(Consts.GAS_LIMIT),
						BigInteger.valueOf(0), new Address(beneficiary))
				.get();
		// 返回地址
		return contract.getContractAddress();
	}

	// 发送金币
	public boolean sendCoin(String contractAddress, int value, String content, String password) throws Exception {
		// 获取发送者凭证
		Credentials credentials = Util.GetCredentials(password, content);
		if (credentials == null)
			return false;
		// 获取合约
		CrowdFundingContract contract = Util.GetCrowdFundingContract(credentials, contractAddress);
		// 发送金币
		contract.sendCoin(BigInteger.valueOf(value).multiply(Consts.ETHER));
		return true;
	}

	// 结束众筹
	public boolean endCrowd(String contractAddress) throws Exception {
		// 获取管理员凭证
		Credentials credentials = Util.GetCredentials();
		if (credentials == null)
			return false;
		// 获取合约
		CrowdFundingContract contract = Util.GetCrowdFundingContract(credentials, contractAddress);
		// 结束众筹
		contract.endCrowd();
		return true;
	}
}
