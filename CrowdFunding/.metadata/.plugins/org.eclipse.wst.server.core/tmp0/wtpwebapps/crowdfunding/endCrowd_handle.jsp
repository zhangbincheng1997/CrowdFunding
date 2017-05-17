<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="com.redhat.crowdfunding.contract.CrowdFundingService"%>
<%
	CrowdFundingService service = new CrowdFundingService();
	String contractAddress = request.getParameter("contractAddress");

	// 结束众筹
	if (service.endCrowd(contractAddress)) {
		out.println("<script>alert('接收金币成功！');window.location.href='index.jsp'</script>");
	} else {
		out.println("<script>alert('发生错误！');");
	}
%>
