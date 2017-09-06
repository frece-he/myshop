<%@ page language="java"  errorPage="errorPage.jsp" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>My Shop 会员认证</title>
<link rel="shortcut icon" type="image/x-icon"
	href="../page-resource/img/favicon.ico" />
<link rel="stylesheet" type="text/css"
	href="../page-resource/css/base.css" />
</head>
<body>
	<f:view>
		<h:form>
			<jsp:include page="header.jsp"></jsp:include>
			<div class="home">
				<div class="homeCon">
					<p class="breadCrumbs bg-fafafa ">首页>个人中心>会员认证</p>
					<jsp:include page="leftlist.jsp"></jsp:include>
					<div class="rightCon">
						<ul class="memberCtf">
							<li><span class="memberCtfSpan">注册日期</span> <h:outputLabel
									value="#{personalInfoBean.dateOfRegist}"
									styleClass="outputlable">
									<f:converter converterId="dateConverter" />
								</h:outputLabel></li>
							<li></li>
							<li><span class="memberCtfSpan">用户名：</span> <h:outputLabel
									value="#{personalInfoBean.userId}" styleClass="outputlable"></h:outputLabel>
							</li>
							<li></li>
							<li><span class="memberCtfSpan">姓名</span> <h:inputText id="userName"
									value="#{personalInfoBean.customerName}" styleClass="inputBar"
									required="true" requiredMessage="请输入姓名">
									<f:validator validatorId="noSpecialChar" />
									<f:validator validatorId="nameValidator" />
								</h:inputText></li>
							<li><h:message for="userName" styleClass="message"></h:message></li>
							<li><span class="memberCtfSpan">性别</span> <h:selectOneRadio
									value="#{personalInfoBean.gender}" id="usergender" >
									<f:selectItem itemValue="男" itemLabel="男" />
									<f:selectItem itemValue="女" itemLabel="女" />
								</h:selectOneRadio></li>
							<li><h:message for="usergender" styleClass="message"></h:message></li>
							<li><span class="memberCtfSpan">手机号</span> <h:inputText id="userPhNo"
									required="true" requiredMessage="请输入正确的手机号"
									value="#{personalInfoBean.phoneNumber}" styleClass="inputBar">
									<f:converter converterId="phoneNumberConverter" />
									<f:validator validatorId="phoneNoValidator" />
								</h:inputText></li>
							<li><h:message for="userPhNo" styleClass="message"></h:message></li>
							<li><span class="memberCtfSpan">电子邮箱邮箱</span> <h:inputText id="userEmail"
									value="#{personalInfoBean.emailAddress}" styleClass="inputBar">
									<f:validator validatorId="emailValidator" />
								</h:inputText></li>
							<li><h:message for="userEmail" styleClass="message"></h:message></li>
						</ul>
						<ul class="memberCtf ml50">
							<li><span class="memberCtfSpan">是否开启会员认证</span> <h:outputLabel
									value="#{personalInfoBean.certification}"
									styleClass="outputlable"></h:outputLabel></li>
							<li></li>
							<li><span class="memberCtfSpan">密码安全问题</span> <h:inputText id="userQue"
									value="#{personalInfoBean.securityQuestion}"
									styleClass="inputBar" required="true"
									requiredMessage="请配置密码安全问题">
									<f:validator validatorId="noSpecialChar" />
								</h:inputText></li>
							<li><h:message for="userQue" styleClass="message"></h:message></li>
							<li><span class="memberCtfSpan">安全问题答案</span> <h:inputText id="userAns"
									value="#{personalInfoBean.securityAnswer}"
									styleClass="inputBar" required="true"
									requiredMessage="请输入安全问题答案">
									<f:validator validatorId="noSpecialChar" />
								</h:inputText></li>
							<li><h:message for="userAns" styleClass="message"></h:message></li>
							<li><span class="memberCtfSpan">出生日期</span> <h:inputText id="userDOB"
									value="#{personalInfoBean.dateOfBirth}" styleClass="inputBar">		
									<f:validator validatorId="dateValidator"/>							
								</h:inputText></li>
							<li><h:message for="userDOB" styleClass="message"></h:message></li>
							<li><span class="memberCtfSpan">收货地址</span> <h:inputText id="userAddress"
									value="#{personalInfoBean.address}" styleClass="inputBar"
									required="true" requiredMessage="请输入正确收货地址">
									<f:validator validatorId="noSpecialChar" />
								</h:inputText></li>
							<li><h:message for="userAddress" styleClass="message"></h:message></li>
						</ul>
						<div class="clear"></div>
						<h:outputText value="#{personalInfoBean.message}"
								styleClass="message"></h:outputText>
						<div class="middleBtn">
							<h:commandButton value="提交" styleClass="bigButton"
								action="#{personalInfoBean.updateInfo}"></h:commandButton>
						</div>
					</div>
					<div class="clear"></div>
				</div>
			</div>
			<jsp:include page="footer.jsp"></jsp:include>
		</h:form>
	</f:view>
</body>
</html>