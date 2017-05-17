<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="com.redhat.crowdfunding.contract.CrowdFundingService"%>
<%
	CrowdFundingService service = new CrowdFundingService();
	String beneficiary = request.getParameter("beneficiary");

	// 部署合约
	String contractAddress = service.deploy(beneficiary);
	if (contractAddress != null) {
		out.println("<script>alert('部署成功！');window.location.href='index.jsp'</script>");
		session.setAttribute("contractAddress", contractAddress);
		session.setMaxInactiveInterval(3600); // 维持60分钟
	} else {
		out.println("<script>alert('部署失败！');window.location.href='deploy.jsp'</script>");
	}
%>
