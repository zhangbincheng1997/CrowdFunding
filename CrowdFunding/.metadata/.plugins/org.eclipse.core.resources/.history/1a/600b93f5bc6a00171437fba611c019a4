package com.redhat.crowdfunding.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

import com.redhat.crowdfunding.contract.CrowdFundingContract;

/**
 * @author littleredhat
 */
public class Util {

	/**
	 * 读取钱包
	 * 
	 * @return
	 */
	private static String readWallet() {
		try {
			InputStream is = Util.class.getResourceAsStream(Consts.PATH);
			InputStreamReader isReader = new InputStreamReader(is, "UTF-8");
			BufferedReader bReader = new BufferedReader(isReader);
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = bReader.readLine()) != null)
				sb.append(line);
			bReader.close();
			isReader.close();
			is.close();
			return sb.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取管理员凭证
	 * 
	 * @return
	 */
	public static Credentials GetCredentials() {
		// 管理员凭证
		return GetCredentials(Consts.PASSWORD, readWallet());
	}

	/**
	 * 获取发送者凭证
	 * 
	 * @param password
	 *            密钥密码
	 * @param content
	 *            密钥内容
	 * @return
	 */
	public static Credentials GetCredentials(String password, String content) {
		// 临时文件
		File tmp = null;
		try {
			tmp = File.createTempFile(Consts.PREFIX, Consts.SUFFIX);
			// 自动删除
			tmp.deleteOnExit();
			// 写入内容
			BufferedWriter out = new BufferedWriter(new FileWriter(tmp));
			out.write(content);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// 发送者凭证
		Credentials credentials = null;
		try {
			credentials = WalletUtils.loadCredentials(password, tmp);
		} catch (IOException | CipherException e) {
			e.printStackTrace();
		}
		return credentials;
	}

	/**
	 * 众筹合约
	 * 
	 * @param credentials
	 * @param contractAddress
	 * @return
	 */
	public static CrowdFundingContract GetCrowdFundingContract(Credentials credentials, String contractAddress) {
		// defaults to http://localhost:8545/
		Web3j web3j = Web3j.build(new HttpService());
		// 获取合约
		CrowdFundingContract contract = new CrowdFundingContract(contractAddress, web3j, credentials, Consts.GAS_PRICE,
				Consts.GAS_LIMIT);
		return contract;
	}
}
