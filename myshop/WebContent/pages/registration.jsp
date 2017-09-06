<%@ page language="java"  errorPage="errorPage.jsp"  contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>My Shop 注册</title>
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
						<a href="login.jsp" class="headerBtn bg-008fdf fc-fff fr">登录 </a>
						<span class="regHeaderTxt fs-16">已有账号？</span>
					</div>

					<div class="clear"></div>
				</div>
				<div class="regBar">
					<h2 class="regH2">欢迎注册MyShop</h2>
					<ul>
						<li><span class="regBarSpan">用户名：</span> <h:inputText
								id="userId" value="#{registrationBean.userId}"
								styleClass="inputBar" required="true" requiredMessage="请输入用户名">
								<f:validator validatorId="length6To20"/>
								<f:validator validatorId="noSpecialChar"/>
							</h:inputText></li>
						<li><span class="regBarSpan">创建密码：</span> <h:inputSecret
								id="passWord" value="#{registrationBean.password}"
								styleClass="inputBar" required="true" requiredMessage="请输入密码">
								<f:validator validatorId="length6To20"/>
								<f:validator validatorId="noSpecialChar"/>
							</h:inputSecret></li>
						<li><span class="regBarSpan">确认密码：</span> <h:inputSecret
								id="confPass" value="#{registrationBean.confirmPassword}"
								styleClass="inputBar" required="true" requiredMessage="请输入确认密码">
							</h:inputSecret></li>
						<li><span class="regBarSpan">请输入密保问题：</span> <h:inputText
								value="#{registrationBean.securityQuestion}"
								styleClass="inputBar" id="question" required="true"
								requiredMessage="请配置密码安全问题">
								<f:validator validatorId="noSpecialChar" />
							</h:inputText></li>
						<li><span class="regBarSpan">安全问题答案：</span> <h:inputText
								id="answer" value="#{registrationBean.securityAnswer}"
								styleClass="inputBar" required="true"
								requiredMessage="请输入安全问题答案">
								<f:validator validatorId="noSpecialChar" />
							</h:inputText></li>

						<li><span class="regBarSpan">姓名：</span> <h:inputText
								id="customerName" value="#{registrationBean.customerName}"
								styleClass="inputBar" required="true" requiredMessage="请输入姓名">
								<f:validator validatorId="noSpecialChar" />
								<f:validator validatorId="nameValidator" />
							</h:inputText></li>

						<li><span class="regBarSpan">手机：</span> <h:inputText
								id="phoneNumber" value="#{registrationBean.phoneNumber}"
								styleClass="inputBar" required="true"
								requiredMessage="请输入正确的手机号">
								<f:converter converterId="phoneNumberConverter" />
							</h:inputText></li>

						<li><span class="regBarSpan">收货地址：</span><h:inputText
								id="address" value="#{registrationBean.address}"
								styleClass="inputBar" required="true"
								requiredMessage="请输入正确收货地址">
								<f:validator validatorId="noSpecialChar" />
							</h:inputText> </li>		
							<li><h:outputText value="#{registrationBean.message}"
								styleClass="mandatory txt-c"></h:outputText></li>				
					</ul>
					<ul>
						<li><h:message for="userId" styleClass="mandatory"></h:message>
						</li>
						<li><h:message for="passWord" styleClass="mandatory"></h:message>
						</li>
						<li><h:message for="confPass" styleClass="mandatory"></h:message>
						</li>
						<li><h:message for="question" styleClass="mandatory"></h:message>
						</li>
						<li><h:message for="answer" styleClass="mandatory"></h:message>
						</li>

						<li><h:message for="customerName" styleClass="mandatory"></h:message>
						</li>
						<li><h:message for="phoneNumber" styleClass="mandatory"></h:message>
						</li>
						<li><h:message for="address" styleClass="mandatory"></h:message>
						</li>
					</ul>
					<div class="clear"></div>
					<h:commandButton value="马上注册" styleClass="bigButton regBtn"
						action="#{registrationBean.registerUser}">
					</h:commandButton>
				</div>
				<div class="height3"></div>
				<div class="remark">填写物流信息：</div>
			</div>
			<div class="secFooter">
				<div class="copyRight">
					<p>CopyRight©2012-2027 MyShop Co.,Ltd.. All Rights Reserved</p>
					<p>沪ICP备12345678号</p>
				</div>
			</div>

		</h:form>
	</f:view>
</body>
</html>