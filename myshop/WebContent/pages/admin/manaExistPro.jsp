<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>MyShop 后台管理</title>
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
					<h:commandLink id="manaExistPro" value="商品管理"
							action="#{manaExistProBean.viewExistPro}"
							styleClass="current"></h:commandLink>
					<a href="manaAccount.jsp">用户管理</a>
					<a href="manaOrder.jsp">订单管理</a> <a href="manaFeedback.jsp">查看反馈</a>
				</div>
			</div>
			<div class="home">
				<div class="homeCon">
					<div class="adHeaderBar">
						<h:outputLabel value="添加库存" styleClass="outputlable"></h:outputLabel>
						<div class="height2"></div>
						<span class="regBarSpan ">查找产品：</span>

						<h:selectOneMenu id="searchProName"
							value="#{manaExistProBean.searchProName}" onchange="submit()"
							valueChangeListener="#{manaExistProBean.chooseAddStockPro}"
							styleClass="selectMenu fl">
							<f:selectItems value="#{manaExistProBean.allProItem}" />
						</h:selectOneMenu>

						<h:commandButton id="searchPro" value="查询"
							action="#{manaExistProBean.searchPro}"
							styleClass="smallButton ml20 fl"></h:commandButton>

						<h:outputLabel value="库存：#{manaExistProBean.stock}件"
							styleClass="charTxt ml20 fl"
							rendered="#{manaExistProBean.stock ne null}"></h:outputLabel>

						<h:inputText id="addNum" value="#{manaExistProBean.addNum}"
							styleClass="purNum ml20 fl"
							rendered="#{manaExistProBean.stock ne null}">
						</h:inputText>
						<h:commandButton id="addStock" value="添加"
							action="#{manaExistProBean.addStock}"
							rendered="#{manaExistProBean.stock ne null}"
							styleClass="smallButton ml20 fl"></h:commandButton>

						<h:outputLabel value="#{manaExistProBean.addStockMessage}"
							styleClass="rightMessage ml20 fl"
							rendered="#{manaExistProBean.addStockMessage ne null}"></h:outputLabel>
					</div>
					<div class="height3"></div>
					<h:outputLabel value="更换首页产品" styleClass="outputlable"></h:outputLabel>
					<div class="height2"></div>
					<span class="regBarSpan ">选择首页产品：</span>
					<h:selectOneMenu id="indexPro" value="#{manaExistProBean.indexPro}"
						onchange="submit()" valueChangeListener="#{manaExistProBean.choosePro}" 
						styleClass="selectMenu fl">
						<f:selectItem itemLabel="—请选择—"/>
						<f:selectItems value="#{manaExistProBean.indexProItem}" />
					</h:selectOneMenu>
					<h:outputText value="选择替换的产品：" styleClass="regBarSpan"></h:outputText>
					<h:selectOneMenu id="replacePro"
						value="#{manaExistProBean.replacePro}" styleClass="selectMenu fl">
						<f:selectItems value="#{manaExistProBean.allProItem}" />
					</h:selectOneMenu>
					<h:commandButton id="changeIndexPro" value="更换"
						action="#{manaExistProBean.changePro}"
						styleClass="smallButton ml20 fl"></h:commandButton>					
						<div class="clear"></div>
						<h:outputLabel value="当前产品：#{manaExistProBean.indexPro}"
						styleClass="longOutputlable"></h:outputLabel>
						<div class="clear"></div>
						<h:outputLabel value="#{manaExistProBean.changeProMessage}"
						styleClass="message ml20 fl"
						rendered="#{manaExistProBean.changeProMessage ne null}"></h:outputLabel>										
					<div class="height150"></div>
					<div class="height08"></div>		

				</div>
			</div>

			<jsp:include page="adfooter.jsp"></jsp:include>
		</h:form>
	</f:view>
</body>
</html>