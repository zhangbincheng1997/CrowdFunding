package com.redhat.crowdfunding.controller;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.web3j.crypto.CipherException;

import com.alibaba.fastjson.JSON;
import com.redhat.crowdfunding.model.Fund;
import com.redhat.crowdfunding.pool.CrowdFundingServicePool;
import com.redhat.crowdfunding.service.CrowdFundingService;
import com.redhat.crowdfunding.service.CrowdFundingServiceImpl;

/**
 * @author littleredhat
 */
@Controller
public class CrowdFundingController {

	private static Logger logger = LoggerFactory.getLogger(CrowdFundingController.class);

	@RequestMapping("list")
	public String list(HttpServletRequest request, HttpServletResponse response) {
		try {

			// 获取对象
			CrowdFundingService service = CrowdFundingServicePool.borrowObject();
			// 获取数量
			int totalNum = service.getFundCount();
			// 归还对象
			CrowdFundingServicePool.returnObject(service);

			request.setAttribute("totalNum", totalNum);
			return "list";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "error";
	}

	@RequestMapping("getFunds")
	@ResponseBody
	public String getFunds(HttpServletRequest request, HttpServletResponse response) {
		int pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
		logger.info("getFunds request : " + pageIndex);

		try {

			// 获取对象
			CrowdFundingService service = CrowdFundingServicePool.borrowObject();
			// 获取列表
			List<Fund> data = service.getFunds(pageIndex);
			// 归还对象
			CrowdFundingServicePool.returnObject(service);

			String res = JSON.toJSONString(data);
			logger.info("getFunds response : " + res);
			return res;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	@RequestMapping("raiseFund")
	@ResponseBody
	public boolean raiseFund(HttpServletRequest request, HttpServletResponse response) {
		String owner = request.getParameter("owner");
		logger.info("raiseFund request : " + owner);

		try {

			// 获取对象
			CrowdFundingService service = CrowdFundingServicePool.borrowObject();
			// 发起众筹
			boolean res = service.raiseFund(owner);
			// 归还对象
			CrowdFundingServicePool.returnObject(service);

			logger.info("raiseFund response : " + res);
			return res;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@RequestMapping("sendCoin")
	@ResponseBody
	public boolean sendCoin(HttpServletRequest request, HttpServletResponse response) {
		String owner = request.getParameter("owner");
		int coin = Integer.parseInt(request.getParameter("coin"));
		String password = request.getParameter("password");
		String content = request.getParameter("content");
		logger.info("sendCoin request : " + owner + " " + coin + " " + password + " " + content);

		try {

			// 这里不使用对象池
			CrowdFundingService service = new CrowdFundingServiceImpl(password, content);
			boolean res = service.sendCoin(owner, coin);

			logger.info("sendCoin response : " + res);
			return res;
		} catch (IOException | CipherException | InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		return false;
	}
}
