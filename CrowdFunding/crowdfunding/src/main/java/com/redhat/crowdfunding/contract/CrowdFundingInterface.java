package com.redhat.crowdfunding.contract;

import java.math.BigInteger;
import java.util.concurrent.Future;

import org.web3j.abi.EventValues;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

/**
 * @author littleredhat
 */
public interface CrowdFundingInterface {

	/**
	 * 发送金币
	 * 
	 * @param value
	 *            金币
	 * @return
	 */
	public TransactionReceipt sendCoin(BigInteger value);

	/**
	 * 结束众筹
	 * 
	 * @return
	 */
	public Future<TransactionReceipt> endCrowd();

	/**
	 * 众筹结束事件
	 * 
	 * @param transactionReceipt
	 *            事务凭据
	 * @return
	 */
	public EventValues processCrowdEndEvent(TransactionReceipt future);
}
