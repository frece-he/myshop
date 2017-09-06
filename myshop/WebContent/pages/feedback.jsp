<%@ page language="java"  errorPage="errorPage.jsp"  contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>My Shop 意见反馈</title>
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
			<div class=" homeCon">
			<p class="breadCrumbs bg-fafafa ">首页>个人中心>意见反馈</p>
				<jsp:include page="leftlist.jsp"></jsp:include>
				<div class=" rightCon feedbackPage">
					<div class="feedbackTxt">
						十分感谢您向我们提出宝贵意见
					</div>
					<div class="clear"></div>
					<div class="marl60Message"></div>
					<div class="feedbackTxtArea">
						<span class="feedbackPageSpan">内容：</span>
						<h:inputTextarea id="feedbackComment" value="#{feedbackBean.feedbackComment}" styleClass="areaTxt">
						<f:validator validatorId="length1000"/>
						<f:validator validatorId="noSpecialChar"/>
						</h:inputTextarea>
						<div class="clear"></div>
					</div>
					<h:outputText value="#{feedbackBean.message}" styleClass="marl60Message"></h:outputText>
					<h:message for="feedbackComment" styleClass="message"></h:message>
					<div class="clear"></div>
					
					<div class="feedbackBtn ml50">
						<h:commandButton value="提交" action="#{feedbackBean.SubmitFeedBack}"  styleClass="bigButton"></h:commandButton>
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