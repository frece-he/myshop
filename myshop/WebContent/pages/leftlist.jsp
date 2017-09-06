<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css"
	href="../page-resource/css/base.css" />
</head>
<body>
	<f:subview id = "left">

			<div class="leftList">			
			<div class="subCon">
				<h:commandLink value="我的订单" action="#{orderBean.viewMyOrder}"  
				immediate="true" styleClass="subConSpan">
				</h:commandLink>
			</div>
			<div class="subCon">
				<h:commandLink value="我的购物车" action="#{cartBean.viewMyCart}"  
				immediate="true" styleClass="subConSpan">
				</h:commandLink>
			</div>
			<div class="subCon">
				<h:commandLink value="编辑个人信息" action="#{personalInfoBean.viewMyInfo}"  
				immediate="true" styleClass="subConSpan">
				</h:commandLink>
			</div>
			<div class="subCon">
				<h:commandLink value="更改密码" action="#{forgotPasswordBean.forgetPassword}"  
				immediate="true" styleClass="subConSpan">
				</h:commandLink>
			</div>
			<div class="subCon">
				<h:commandLink value="意见反馈" action="#{feedbackBean.goToFeedback}"  
				immediate="true" styleClass="subConSpan">
				</h:commandLink>
			</div>
			<!-- 
				<a href="checkOrder.jsp" class="subCon"><span class="subConSpan">我的订单</span></a> 
				<a href="checkCart.jsp" class="subCon"><span class="subConSpan">我的购物车</span></a> 				
				<a href="changeInfo.jsp" class="subCon"><span class="subConSpan">修改个人信息</span></a> 
				<a href="forgotpassword.jsp" class="subCon"><span class="subConSpan">更改密码</span></a> 	
				<a href="feedback.jsp"  class="subCon"><span class="subConSpan">意见反馈</span></a>				
				 -->
				<div class="clear"></div>
			</div>

	</f:subview>
</body>
</html>