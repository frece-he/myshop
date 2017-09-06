<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>My Shop 后台管理系统</title>
<link rel="shortcut icon" type="image/x-icon"
	href="../../page-resource/img/favicon.ico" />
<link rel="stylesheet" type="text/css"
	href="../../page-resource/css/base.css" />
</head>
<body>
	<f:view>
		<h:form>
			<jsp:include page="adheader.jsp"></jsp:include>
			<div class="adNav">
				<div class="adNavCon">
					<a href="manaRelease.jsp">发布商品</a> 
					<a href="manaExistPro.jsp">商品管理</a>
					<a href="manaAccount.jsp"	class="current">用户管理</a> 
					<a href="manaOrder.jsp">订单管理</a>
					<a href="manaFeedback.jsp">查看反馈</a>
				</div>
			</div>
			<div class="home">
				<div class="homeCon">
					<div class="adHeaderBar">
						<span class="regBarSpan ml100">查找账户：</span>
													
							<h:selectOneMenu id="selectAccount"
							value="#{manaAccountBean.tempLogin}" onchange="submit()"
							valueChangeListener="#{manaAccountBean.selectAccount}"
							styleClass="selectMenu fl">
							<f:selectItems value="#{manaAccountBean.allAccountId}" />
						</h:selectOneMenu>
							
						<h:commandButton value="查询"
							action="#{manaAccountBean.searchAccount}"
							styleClass="smallButton ml20 fl"></h:commandButton>
							<h:outputLabel value="#{manaAccountBean.message}!" styleClass="rightMessage ml20 fl"
							rendered="#{manaAccountBean.message ne null}"
							></h:outputLabel>							
					</div>
					<h:commandButton value="查看所有被锁定账户"
							action="#{manaAccountBean.checklockedAccount}"
							styleClass="dp-block  longButton ml130"></h:commandButton>
					<h:dataTable id="account" value="#{manaAccountBean.lockedAccount}"
						var="item" rowClasses="height50"
						columnClasses="tableControl,tableTxt,tableTxt,tableTxt,tableTxt,tableTxt"
						styleClass="dataTable" rules="none">
						
						<h:column id="oper" headerClass="width15  row1">
							<f:facet name="header">
								<h:outputText value="选择账户"></h:outputText>
							</f:facet>
							<h:selectBooleanCheckbox value="#{item.selected}"></h:selectBooleanCheckbox>
						</h:column>
						
						<h:column id="userId" headerClass="width35 row1">
							<f:facet name="header">
								<h:outputText value="账户名"></h:outputText>
							</f:facet>
							<h:outputText value="#{item.userId}"></h:outputText>
						</h:column>

						<h:column id="status" headerClass="width30 row1">
							<f:facet name="header">
								<h:outputText value="账当前状态"></h:outputText>
							</f:facet>
							<h:outputText value="#{item.status}"></h:outputText>
						</h:column>
						
						<h:column id="lock" headerClass=" row1">
							<f:facet name="header">
								<h:outputText value="操作"></h:outputText>
							</f:facet>
							<h:outputLabel value="锁定账户" styleClass="smallGrayButton mar5auto" rendered="#{item.status eq '锁定'}"></h:outputLabel>									
									<h:commandButton value="锁定账户" styleClass="smallButton mar5auto"  actionListener="#{manaAccountBean.lockAccount}"  rendered="#{item.status ne '锁定'}" >
									<f:attribute name="userId" value="#{item.userId}" />
									</h:commandButton>
						</h:column>
						
					</h:dataTable>
					
					<h:commandButton value="恢复选中账户"
						styleClass="dp-block bigButton mar0-auto"
						action="#{manaAccountBean.unlockAccount}"
						rendered="#{not empty manaAccountBean.lockedAccount && manaAccountBean.btnDisplay eq '可显示'}"></h:commandButton>

					<div class="height08"></div>
					<div class="height4"></div>
					<div class="height150"></div>

				</div>
			</div>


		</h:form>
		<jsp:include page="adfooter.jsp"></jsp:include>
	</f:view>
</body>
</html>