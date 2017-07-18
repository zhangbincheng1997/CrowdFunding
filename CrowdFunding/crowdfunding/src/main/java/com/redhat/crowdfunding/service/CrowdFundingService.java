package com.redhat.crowdfunding.service;

import java.util.List;
import java.util.concurrent.ExecutionException;

import com.redhat.crowdfunding.model.Fund;

/**
 * @author littleredhat
 */
public interface CrowdFundingService {

	// 获取数量
	public int getFundCount() throws InterruptedException, ExecutionException;

	// 众筹列表
	public List<Fund> getFunds(int pageIndex) throws InterruptedException, ExecutionException;

	// 发起众筹
	public boolean raiseFund(String owner) throws InterruptedException, ExecutionException;

	// 发送金币
	public boolean sendCoin(String owner, int coin) throws InterruptedException, ExecutionException;
}
