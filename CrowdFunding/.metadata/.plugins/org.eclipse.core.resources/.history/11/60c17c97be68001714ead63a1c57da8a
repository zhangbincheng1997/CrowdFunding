package com.redhat.crowdfunding.controller;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.redhat.crowdfunding.bean.Fund;
import com.redhat.crowdfunding.service.CrowdFundingService;
import com.redhat.crowdfunding.service.CrowdFundingServiceImpl;

/**
 * @author littleredhat
 */
@Controller
public class CrowdFundingController {

	@RequestMapping("getFunds")
	public String getFunds(HttpServletRequest request, HttpServletResponse response) {
		CrowdFundingService service = new CrowdFundingServiceImpl();
		try {
			List<Fund> fList = service.getFunds();
			request.setAttribute("fList", fList);
			System.out.println(fList);
			return "list";
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		return "error";
	}

	@RequestMapping("raiseFund")
	public String raiseFund(HttpServletRequest request, HttpServletResponse response) {
		String owner = request.getParameter("owner");
		String content = request.getParameter("content");
		String password = request.getParameter("password");
		System.out.println(owner + "  " + content + "  " + password);
		CrowdFundingService service = new CrowdFundingServiceImpl(password, content);
		try {
			service.raiseFund(owner);
			return "success";
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		return "error";
	}

	@RequestMapping("sendCoin")
	public String sendCoin(HttpServletRequest request, HttpServletResponse response) {
		String owner = request.getParameter("owner");
		int coin = Integer.parseInt(request.getParameter("coin"));
		String password = request.getParameter("password");
		String content = "";
		try {
			content = request.getPart("content").getContentType();
		} catch (IOException | ServletException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println(content);
		CrowdFundingService service = new CrowdFundingServiceImpl(password, content);
		try {
			service.sendCoin(owner, coin);
			return "success";
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		return "error";
	}

	@RequestMapping("test")
	public String test() {
		return "error";
	}
}
