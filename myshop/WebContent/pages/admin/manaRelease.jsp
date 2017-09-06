<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
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

		<h:form id="fileUpoad" prependId="false" enctype="multipart/form-data">
			<jsp:include page="adheader.jsp"></jsp:include>
			<div class="adNav">
				<div class="adNavCon">
					<a href="manaRelease.jsp" class="current">发布商品</a> <a
						href="manaExistPro.jsp">商品管理</a><a href="manaAccount.jsp">用户管理</a>
					<a href="manaOrder.jsp">订单管理</a> <a href="manaFeedback.jsp">查看反馈</a>
				</div>
			</div>

			<div class="home">
				<div class="homeCon">
					<div class="regBar">
						<h2 class="regH2">添加产品</h2>
						<ul>
							<li><span class="regBarSpan">产品名称</span> <h:inputText
									id="proName" value="#{manaProBean.proName}" required="true"
									requiredMessage="请输入产品名" styleClass="inputBar">
								</h:inputText></li>
							<li><span class="regBarSpan">产品类别</span>
							<h:selectOneRadio id="proCategory" value="#{manaProBean.proCategory}" 
							required="true" requiredMessage="请选择产品类别" >
								<f:selectItems value="#{manaProBean.typeList}"/>
							</h:selectOneRadio>							
							</li>
							<li><span class="regBarSpan">产品图片</span> <h:inputFile
									id="img" value="#{manaProBean.file}" required="true"
									requiredMessage="请选择产品图片" styleClass="inputFile fl">
									<f:validator validatorId="fileUploadValidator" />
								</h:inputFile></li>
							<li><span class="regBarSpan">产品价格</span> <h:inputText
									id="proPrice" value="#{manaProBean.proPrice}" required="true"
									requiredMessage="请输入产品价格" styleClass="inputBar">
								</h:inputText></li>
								<li><span class="regBarSpan">新产品数量</span> <h:inputText
									id="proNum" value="#{manaProBean.newProStock}"
									styleClass="inputBar" required="true"
									requiredMessage="请输入产品数量" >
									<f:validator validatorId="onlyNumberValidator"/>
								</h:inputText></li>
							<li><h:outputLabel value="选填项：" styleClass="outputlable"></h:outputLabel></li>
							<li><span class="regBarSpan">产品描述</span> <h:inputText
									id="proDes" value="#{manaProBean.proDesc}"
									styleClass="inputBar">
								</h:inputText></li>
							<li><span class="regBarSpan">产品连接</span> <h:inputText
									id="proLink" value="#{manaProBean.prolink}"
									styleClass="inputBar">
								</h:inputText></li>								
							
								<li><h:outputLabel value="#{manaProBean.message}"
							styleClass="message ml20 fl"
							rendered="#{manaProBean.message ne null}"></h:outputLabel></li>
						</ul>
						<ul>
							<li><h:message for="proName" styleClass="mandatory"></h:message></li>
							<li><h:message for="proCategory" styleClass="mandatory"></h:message></li>
							<li><h:message for="img" styleClass="mandatory"></h:message></li>
							<li><h:message for="proPrice" styleClass="mandatory"></h:message></li>							
							<li><h:message for="proNum" styleClass="mandatory"></h:message></li>
						</ul>
						<div class="clear"></div>
						
						<h:commandButton id="releasePro" value="添加产品"
							action="#{manaProBean.addProduct}"
							styleClass="bigButton regBtn"></h:commandButton>
					</div>
				</div>
				<div class="clear"></div>
			</div>

			<jsp:include page="adfooter.jsp"></jsp:include>
		</h:form>
	</f:view>
</body>
</html>