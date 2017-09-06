<%@ page language="java" errorPage="errorPage.jsp"   contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>退出登录</title>
		<link rel="shortcut icon" type="image/x-icon" href="../page-resource/img/favicon.ico" />
		<link rel="stylesheet" type="text/css" href="../page-resource/css/base.css" />
</head>
<body>
	<f:view>
			
				<%
					session.setAttribute("userId", null);
					session.setAttribute("tryId", null);
					session.setAttribute("status", null);
					session.setAttribute("proName", null);
				%>				
			<h:form>
			<jsp:include page="header.jsp"></jsp:include>
			<div class="homeCon">
			<div class="successPage">
				<i class="successImg"></i>
				<span class="successPageTxt">退出登录成功,您可以选择:</span>
				<div class="height3"></div>
				<h:commandButton value="继续逛逛" action="index.jsp" styleClass="chooseBtn fl"></h:commandButton>
				<h:commandButton value="重新登录" action="login.jsp" styleClass="chooseBtn fl"></h:commandButton>
			</div>
			<div class="height3"></div>
		</div>
			<jsp:include page="footer.jsp"></jsp:include>
			</h:form>


	</f:view>
</body>
</html>