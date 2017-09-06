<%@ page language="java"  errorPage="errorPage.jsp"  contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>My Shop 我的订单</title>
<link rel="shortcut icon" type="image/x-icon" href="../page-resource/img/favicon.ico" />
		<link rel="stylesheet" type="text/css" href="../page-resource/css/base.css" />
</head>
<body>
<f:view>
		<h:form>
		<jsp:include page="header.jsp"></jsp:include>
			<div class="home">
				<div class="homeCon">
					<p class="breadCrumbs bg-fafafa ">首页>个人中心>我的订单</p>
					<jsp:include page="leftlist.jsp"></jsp:include>
					<div class="rightCon">
						<h:selectOneRadio value="#{orderBean.status}" onchange="submit()" valueChangeListener="#{orderBean.selectStatus}" styleClass="radioBar">
							<f:selectItems value="#{orderBean.statusItem}" />
						</h:selectOneRadio>
						<h:dataTable id="order" value="#{orderBean.orderList}" var="order" columnClasses="tableTxt,tableTxt,tableTxt,tableTxt,tableTxt,tableTxt,tableControl"
								styleClass="dataTable"  rules="none" rowClasses="height75">
									<h:column id="orderId" headerClass="width23 row1">
									<f:facet name="header">
										<h:outputText value="订单号"></h:outputText>
									</f:facet>
									<h:outputText value="#{order.orderId}" ></h:outputText>
								</h:column>
								<h:column id="products" headerClass="width18 row1">
									<f:facet name="header">
										<h:outputText value="产品名称"></h:outputText>
									</f:facet>
									<h:outputText value="#{order.proNamelist}" ></h:outputText>
								</h:column>
								<h:column id="proNum" headerClass="width10 row1">
									<f:facet name="header">
										<h:outputText value="产品总数"></h:outputText>
									</f:facet>
									<h:outputText value="#{order.totalNum}" ></h:outputText>
								</h:column>
								<h:column id="totalAmount" headerClass="width13 row1">
									<f:facet name="header">
										<h:outputText value="总价（元）"></h:outputText>
									</f:facet>
									<h:outputText value="#{order.totalPrice}" ></h:outputText>
								</h:column>
								
								<h:column id="selledDate" headerClass="width15 row1">
									<f:facet name="header">
										<h:outputText value="交易日期"></h:outputText>
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
									<h:outputLabel value="确认收货" styleClass="smallGrayButton mar5auto" rendered="#{order.status eq '待发货'}"></h:outputLabel>									
									<h:commandButton value="确认收货" styleClass="smallButton mar5auto"  actionListener="#{orderBean.finishOrder}"  rendered="#{order.status eq '已发货'}" >
									<f:attribute name="orderId" value="#{order.orderId}" />
									</h:commandButton>
									<h:commandButton value="关闭交易" styleClass="smallButton mar5auto"  actionListener="#{orderBean.closeOrder}"  rendered="#{order.status ne '已完成' && order.status ne '已关闭' }">
									<f:attribute name="orderId" value="#{order.orderId}" />
									</h:commandButton>
								</h:column>
							</h:dataTable>
					</div>
					<div class="clear"></div>
				</div>

			</div>
<div class="clear"></div>
		<jsp:include page="footer.jsp"></jsp:include>
		</h:form>


</f:view>
</body>
</html>