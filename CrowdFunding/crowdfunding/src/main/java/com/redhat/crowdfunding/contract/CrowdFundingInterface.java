package com.redhat.crowdfunding.contract;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

/**
 * @author littleredhat
 */
public interface CrowdFundingInterface {

	/********** 管理员 **********/

	// 获取数量
	public Future<Uint256> getFundCount();

	// 获取信息
	public CompletableFuture<List<Type>> getFundInfo(int i);

	// 是否存在
	public Future<Bool> isExist(String owner);

	/********** 用户 **********/

	// 发起众筹
	public Future<TransactionReceipt> raiseFund(String owner);

	// 发送金币
	public Future<TransactionReceipt> sendCoin(String owner, int coin);
}
