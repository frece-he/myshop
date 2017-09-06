<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>My Shop 后台管理系统</title>
		<link rel="shortcut icon" type="image/x-icon" href="../../page-resource/img/favicon.ico" />
		<link rel="stylesheet" type="text/css" href="../../page-resource/css/base.css"/>
</head>
<body>
<f:view>
<h:form>
<jsp:include page="adheader.jsp"></jsp:include>
		<div class="adNav">
			<div class="adNavCon">
					<a href="manaRelease.jsp">发布商品</a> 
					 <a	href="manaExistPro.jsp">商品管理</a>
					<a href="manaAccount.jsp"	>用户管理</a> 
					<a href="manaOrder.jsp">订单管理</a>
					<a href="manaFeedback.jsp" class="current">查看反馈</a>		
			</div>			
		</div>
		
		<div class="home">
			<div class="homeCon">
				<h:dataTable id="feedback" value="#{manaFeedbackBean.feedbackList}"
						var="item" rowClasses="height50"
						columnClasses="tableTxt,tableTxt"
						styleClass="dataTable" rules="none">
						<h:column id="userId" headerClass=" row1">
							<f:facet name="header">
								<h:outputText value="账户名"></h:outputText>
							</f:facet>
							<h:outputText value="#{item.userId}"></h:outputText>
						</h:column>
						
						<h:column id="comment" headerClass="width70 row1">
							<f:facet name="header">
								<h:outputText value="内容"></h:outputText>
							</f:facet>
							<h:outputText value="#{item.feedbackComment}"></h:outputText>
						</h:column>
						
						
				</h:dataTable>
				<div class="height250"></div>
			</div>
		</div>
		

		</h:form>
		<jsp:include page="adfooter.jsp"></jsp:include>
</f:view>
</body>
</html>