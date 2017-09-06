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
					<h:commandLink id="manaExistPro" value="订单管理"
							action="#{manaOrderBean.viewOrder}"
							styleClass="current"></h:commandLink>
					<a href="manaFeedback.jsp" >查看反馈</a>			
			</div>			
		</div>
		
		<div class="home">
			<div class="homeCon">
				<h:selectOneRadio value="#{manaOrderBean.status}" onchange="submit()" valueChangeListener="#{manaOrderBean.selectStatus}" styleClass="radioBar mar0-auto">
							<f:selectItems value="#{manaOrderBean.statusItem}" />
						</h:selectOneRadio>
						<h:dataTable id="order" value="#{manaOrderBean.orderList}" var="order" columnClasses="tableTxt,tableTxt,tableTxt,tableTxt,tableTxt,tableTxt,tableControl"
								styleClass="dataTable"  rules="none" rowClasses="height50">
									<h:column id="orderId" headerClass="width20 row1">
									<f:facet name="header">
										<h:outputText value="订单号"></h:outputText>
									</f:facet>
									<h:outputText value="#{order.orderId}" ></h:outputText>
								</h:column>
								<h:column id="products" headerClass="width16 row1">
									<f:facet name="header">
										<h:outputText value="产品名称"></h:outputText>
									</f:facet>
									<h:outputText value="#{order.proNamelist}" ></h:outputText>
								</h:column>
								<h:column id="proNum" headerClass="width15 row1">
									<f:facet name="header">
										<h:outputText value="购买账户"></h:outputText>
									</f:facet>
									<h:outputText value="#{order.userId}" ></h:outputText>
								</h:column>
								<h:column id="totalAmount" headerClass="width13 row1">
									<f:facet name="header">
										<h:outputText value="总价（元）"></h:outputText>
									</f:facet>
									<h:outputText value="#{order.totalPrice}" ></h:outputText>
								</h:column>
								
								<h:column id="selledDate" headerClass="width15 row1">
									<f:facet name="header">
										<h:outputText value="交易日期">										
										</h:outputText>
									</f:facet>
									<h:outputText value="#{order.orderTime}" >
									<f:converter converterId="dateConverter"/>
									</h:outputText>
								</h:column>		
								<h:column id="status" headerClass="width10 row1">
									<f:facet name="header">
										<h:outputText value="交易状态"></h:outputText>
									</f:facet>
									<h:outputText value="#{order.status}" ></h:outputText>
								</h:column>							
								<h:column id="orderOper" headerClass="row1">
									<f:facet name="header">
										<h:outputText value="操作"></h:outputText>
									</f:facet>								
									<h:commandButton value="发货" styleClass="smallButton mar5auto"  actionListener="#{manaOrderBean.changeToSent}"  rendered="#{order.status eq '待发货'}" >
									<f:attribute name="orderId" value="#{order.orderId}" />
									</h:commandButton>
									<h:commandButton value="关闭交易" styleClass="smallButton mar5auto"  actionListener="#{manaOrderBean.closeOrder}"  rendered="#{order.status ne '已完成' && order.status ne '已关闭' }">
									<f:attribute name="orderId" value="#{order.orderId}" />
									</h:commandButton>
								</h:column>
							</h:dataTable>
				<div class="clear"></div>
				<div class="height150"></div>
			</div>
		</div>
		</h:form>
		<jsp:include page="adfooter.jsp"></jsp:include>
</f:view>
</body>
</html>