<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	// 移除session
	session.removeAttribute("contractAddress");
	out.println("<script>window.location.href='deploy.jsp'</script>");
%>
