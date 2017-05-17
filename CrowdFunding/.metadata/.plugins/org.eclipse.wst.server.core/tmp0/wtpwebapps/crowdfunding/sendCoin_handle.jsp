<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="com.redhat.crowdfunding.contract.CrowdFundingService"%>
<%
	CrowdFundingService service = new CrowdFundingService();
	String contractAddress = request.getParameter("contractAddress");
	int value = Integer.parseInt(request.getParameter("value"));
	String content = request.getParameter("content");
	String password = request.getParameter("password");

	// 发送金币
	if (service.sendCoin(contractAddress, value, content, password)) {
		out.println("<script>alert('发送金币成功！');window.location.href='index.jsp'</script>");
	} else {
		out.println("<script>alert('发生错误！');");
	}
%>
