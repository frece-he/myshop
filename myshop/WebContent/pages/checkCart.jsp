<%@ page language="java"  errorPage="errorPage.jsp"  contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>My Shop 购物车</title>
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
					<p class="breadCrumbs bg-fafafa ">首页>个人中心>查看购物车</p>
					<jsp:include page="leftlist.jsp"></jsp:include>
					<div class="rightCon">
						<h:dataTable id="cart" value="#{cartBean.settledPro}" var="pro"
							columnClasses="tableImg,tableTxt,tableTxt,tableTxt,tableTxt"
							styleClass="dataTable" rules="none"
							rendered="#{not empty cartBean.products}">

							<h:column id="pro-pic" headerClass="width30 row1">
								<f:facet name="header">
									<h:outputText value="产品图片"></h:outputText>
								</f:facet>
								<h:graphicImage url="#{pro.imgUrl}"
									style="width:160px;height:125px"></h:graphicImage>
							</h:column>
							<h:column id="pro-name" headerClass="width20 row1">
								<f:facet name="header">
									<h:outputText value="产品名称"></h:outputText>
								</f:facet>
								<h:outputText value="#{pro.proName}"></h:outputText>
							</h:column>
							<h:column id="pro-type" headerClass="width13 row1">
								<f:facet name="header">
									<h:outputText value="产品类别"></h:outputText>
								</f:facet>
								<h:outputText value="#{pro.proCategory}"></h:outputText>
							</h:column>
							<h:column id="pro-price" headerClass="width15 row1">
								<f:facet name="header">
									<h:outputText value="单价（元）"></h:outputText>
								</f:facet>
								<h:outputText value="#{pro.price}"></h:outputText>
							</h:column>
							<h:column id="pro-num" headerClass="width row1">
								<f:facet name="header">
									<h:outputText value="数量"></h:outputText>
								</f:facet>
								<h:commandButton value="-"  styleClass="squareBtn margin1015 fl"
								  actionListener="#{cartBean.deleteOne}" >
									<f:attribute name="proName" value="#{pro.proName}" />
									</h:commandButton>
								<h:inputText value="#{pro.proNum}"  onblur="submit()" valueChangeListener="#{cartBean.changeProNum}" styleClass="purNum fl">
								<f:validator validatorId="onlyNumberValidator"/>
								<f:attribute name="proName" value="#{pro.proName}" />								
								</h:inputText>
								<h:commandButton value="+"  styleClass="squareBtn margin1015 fl"
								  actionListener="#{cartBean.addOne}" >
									<f:attribute name="proName" value="#{pro.proName}" />
									</h:commandButton>
							</h:column>
														
						</h:dataTable>
						
						<h:commandLink value="清空购物车" styleClass="tableLink"
							rendered="#{not empty cartBean.products}"
							action="#{cartBean.clearCart}"></h:commandLink>

						<h:commandButton value="结算全部" action="#{cartBean.settlementCart}"
							rendered="#{not empty cartBean.products}"
							styleClass="bigButton fr"></h:commandButton>
						<h:outputLabel value="#{cartBean.totalAmount}"
							rendered="#{not empty cartBean.products}"
							styleClass="totalAmount fr"></h:outputLabel>							
						<h:outputLabel value="#{cartBean.message}" styleClass="message"></h:outputLabel>
						<div class="successPage">
							<h:outputText value="购物车空空如也,您可以选择:" styleClass="successPageTxt"
								rendered="#{empty cartBean.products}"></h:outputText>
							<div class="height3"></div>
							<h:commandButton value="浏览商城" action="mall.jsp"
								rendered="#{empty cartBean.products}" styleClass="chooseBtn fl"></h:commandButton>
							<h:commandButton value="查看订单" action="#{orderBean.viewMyOrder}"
								rendered="#{empty cartBean.products}" styleClass="chooseBtn fl"></h:commandButton>
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