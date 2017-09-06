<%@ page language="java"  errorPage="errorPage.jsp"  contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>My Shop 重置密码</title>
<link rel="shortcut icon" type="image/x-icon"
	href="../page-resource/img/favicon.ico" />
<link rel="stylesheet" type="text/css"
	href="../page-resource/css/base.css" />
</head>
<body>
	<f:view>
		<h:form>
			<div class="home">
				<div class="homeCon">
				<div class="secHeader">
				<a href="index.jsp" class="logoHeader"></a>
				<div class="regHeaderTxt">
					<a href="registration.jsp" class="headerBtn bg-008fdf fc-fff fr">注册 </a>
					<span class="regHeaderTxt fs-16">还没有账号？</span>					
				</div>
				<div class="clear"></div>
			</div>
					<div class="middlePanel">
						<h2 class="title">MyShop 重置密码</h2>

						<h:panelGroup rendered="#{forgotPasswordBean.userId eq null && forgotPasswordBean.changePassPanl ne 'display'}">
							<ul>
								<li><span class="middlePanelSpan">用户名：</span> <h:inputText id="userId"
										value="#{forgotPasswordBean.userId}" styleClass="inputBar">
										<f:validator validatorId="noSpecialChar" />
									</h:inputText></li>
								<li><h:message for="userId" styleClass="message"></h:message></li>
								<h:outputLabel value="#{forgotPasswordBean.message}" styleClass="message"></h:outputLabel>
								<h:commandButton value="下一步"
									styleClass=" middleBtn bigButton clear"
									action="#{forgotPasswordBean.getProfileDetails}"></h:commandButton>
							</ul>
						</h:panelGroup>
						<h:panelGroup rendered="#{forgotPasswordBean.userId ne null && forgotPasswordBean.changePassPanl ne 'display'}">
							<ul>
								<li><span class="middlePanelSpan">安全提示问题</span> <h:outputLabel
										value="#{forgotPasswordBean.securityQuestion}"
										styleClass="outputlable"></h:outputLabel></li>
								<li></li>
								<li><span class="middlePanelSpan">安全问题答案</span> <h:inputText id="answer"
										value="#{forgotPasswordBean.answer}" styleClass="inputBar">
										<f:validator validatorId="noSpecialChar" />
									</h:inputText></li>
								<li><h:message for="answer" styleClass="message"></h:message> </li>
								<h:outputLabel value="#{forgotPasswordBean.message}" styleClass="message"></h:outputLabel>
								<h:commandButton value="提交"
									styleClass=" middleBtn bigButton clear" rendered="#{forgotPasswordBean.count < 3}"
									action="#{forgotPasswordBean.validAnswer}"></h:commandButton>
									<h:commandButton value="返回首页"
									styleClass=" middleBtn height30  clear" rendered="#{forgotPasswordBean.count >= 3}"
									action="index.jsp"></h:commandButton>
							</ul>
						</h:panelGroup>
						<h:panelGroup rendered="#{forgotPasswordBean.changePassPanl eq 'display'}">
							<ul>
								
								<li><span class="middlePanelSpan">新密码</span> <h:inputSecret id="newPass"
										value="#{forgotPasswordBean.newPassword}" styleClass="inputBar"
										required="true" requiredMessage="请输入新密码">
										<f:validator validatorId="length6To20" />
									</h:inputSecret></li>
								<li><h:message for="newPass" styleClass="message"></h:message></li>
								
								<li><span class="middlePanelSpan">确认密码</span> <h:inputSecret id="confPass"
										value="#{forgotPasswordBean.confirmPass}" styleClass="inputBar"
										required="true" requiredMessage="请确认密码">
										<f:validator validatorId="noSpecialChar" />
									</h:inputSecret></li>
								<li><h:message for="confPass" styleClass="message"></h:message></li>
								<h:outputLabel value="#{forgotPasswordBean.message}" styleClass="message"></h:outputLabel>
								<h:commandButton value="重置密码"
									styleClass=" middleBtn bigButton clear"
									action="#{forgotPasswordBean.resetPassword}"></h:commandButton>
							</ul>
						</h:panelGroup>

					
					</div>
				</div>
			</div>
		</h:form>
	</f:view>
</body>
</html>