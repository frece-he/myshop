<%@ page language="java"  errorPage="errorPage.jsp"  contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>My Shop 登录</title>
<link rel="shortcut icon" type="image/x-icon"
	href="../page-resource/img/favicon.ico" />
<link rel="stylesheet" type="text/css"
	href="../page-resource/css/base.css" />
</head>
<body>
	<f:view>	
		<h:form>
			<div class="homeCon">
			<div class="secHeader">
				<a href="index.jsp" class="logoHeader"></a>
				<div class="regHeaderTxt">
					<a href="registration.jsp" class="headerBtn bg-008fdf fc-fff fr">注册 </a>
					<span class="regHeaderTxt fs-16">还没有账号？</span>					
				</div>
				<div class="clear"></div>
			</div>
			<div class="loginCon">
				<i class="adImg"></i>
				<div class="loginBar">
					<h2 class="loginTxtUp">MyShop 账户登录</h2>
					<ul class="loginBarCon">
						<li>
							<span class="loginBarSpan">用户名：</span>
							<h:inputText id="userId" value="#{loginBean.userId}" styleClass="inputBar"></h:inputText>
						</li>
						<li>
							<h:message for="userId"></h:message>
						</li>
						<li>
							<span class="loginBarSpan">密码：</span>
							<h:inputSecret id="pswd"  value="#{loginBean.password}" styleClass="inputBar"></h:inputSecret>
						</li>
						<li>
							<h:message for="pswd"></h:message>
							<h:outputLabel value="#{loginBean.message}"  styleClass="message"></h:outputLabel>
						</li>
					</ul>
					<div class="clear"></div>
					<div class="loginTxtDown">
						<h:commandLink value="忘记密码" action="#{forgotPasswordBean.forgetPassword}"
						styleClass="forgetPass"></h:commandLink>
					</div>
					<div class="loginBtn">
						<h:commandButton value="登录"  styleClass="bigButton"
						action="#{loginBean.doLogin}"></h:commandButton>
					</div>
					
				</div>
				<div class="height1"></div>
			</div>

		</div>

		<div class="secFooter">
			<div class="copyRight">
				<p>CopyRight©2012-2027 MyShop Co.,Ltd.. All Rights Reserved </p>
				<p>沪ICP备12345678号</p>
			</div>
		</div>

		</h:form>
	</f:view>
</body>
</html>