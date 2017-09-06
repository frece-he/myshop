<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>My Shop 后台管理系统</title>
		<link rel="shortcut icon" type="image/x-icon" href="../../page-resource/img/favicon.ico" />
		<link rel="stylesheet" type="text/css" href="../../page-resource/css/base.css" />
</head>
<body>
<f:view>
<h:form>
<jsp:include page="adheader.jsp"></jsp:include>
		<div class="homeCon">

			<div class="loginCon">
				<div class="middlePanel">
					<h2 class="title">My Shop 后台管理系统</h2>
					<ul class="loginBarCon">
						<li>
							<span class="loginBarSpan">管理员账号：</span>
							<h:inputText id="adminId" value="#{adminLogin.adminId}" styleClass="inputBar"
							required="true" requiredMessage="请输入正确的账号"
							></h:inputText>
						</li>
						<li><h:message for="adminId" styleClass="message"></h:message></li>
						<li>
							<span class="loginBarSpan">密码：</span>
							<h:inputSecret id="adminPass"  value="#{adminLogin.password}" styleClass="inputBar"
								required="true" requiredMessage="请输入正确的密码"
							></h:inputSecret>
						</li>
						<li>
							<h:message for="adminPass" styleClass="message"></h:message>
							<h:outputLabel value="#{adminLogin.message}"  styleClass="message"></h:outputLabel>
							</li>
					</ul>
					<div class="clear"></div>
					<h:commandButton value="登录"  styleClass="bigButton dp-block mar0-auto"
						action="#{adminLogin.adminLogin}"></h:commandButton>

				</div>
				<div class="clear"></div>
			</div>

		</div>
		<div class="height100"></div>
		<div class="footerBg">
			<div class="footer">
				
			<div class="adCopy">
				<p>CopyRight©2012-2027 MyShop Co.,Ltd.. All Rights Reserved&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;沪ICP备12345678号</p>
			</div>
		</div>
		</div>
		</h:form>
</f:view>
</body>
</html>