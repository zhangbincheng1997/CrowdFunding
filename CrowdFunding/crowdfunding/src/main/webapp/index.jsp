<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>欢迎</title>
</head>
<body>
	<%
		// session为空
		if (session.getAttribute("contractAddress") == null) {
			out.println("<script>alert('请先部署合约！');window.location.href='deploy.jsp'</script>");
			return;
		}
		// 获取session
		Object contractAddress = session.getAttribute("contractAddress");
		out.println("合约地址" + contractAddress);
	%>

	<br />
	<form action="sendCoin_handle.jsp" method="POST">
		<input type="text" name="contractAddress" placeholder="请输入合约地址" /><br />
		<input type="text" name="value" placeholder="请输入发送金额" /><br />
		<input type="text" name="content" placeholder="请输入钱包内容" /> <br />
		<input type="password" name="password" placeholder="请输入钱包密码" /><br />
		<input type="submit" value="发送金币" />
	</form>

	<br />
	<form action="endCrowd_handle.jsp" method="POST">
		<input type="text" name="contractAddress" placeholder="请输入合约地址" /><br /> 
		<input type="submit" value="接收金币" />
	</form>

	<br />
	<form action="#" method="POST">
		<button type="submit" formaction="quit.jsp">退出</button>
	</form>
</body>
</html>
