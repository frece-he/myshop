<%@ page language="java"  errorPage="errorPage.jsp"  contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>My Shop</title>
<link rel="shortcut icon" type="image/x-icon" href="page-resource/img/favicon.ico" />
<link rel="stylesheet" type="text/css" href="../page-resource/css/base.css" />
</head>
<body>
<f:view>
<h:form>
	<jsp:include page="header.jsp"></jsp:include>
</h:form>
	<div class="home">
			<div class="homeCon">
			<h:form>
				<div class="purchaseProduct">
					<div class="bigProPic">
						<h:graphicImage url="#{productBean.imgUrl}"></h:graphicImage>
					</div>
					<div class="proDetails">
						<h:outputLabel value="[旗舰店]#{productBean.proName}"  styleClass="proDetailsH2"></h:outputLabel>
						<div class="proPrice">
							<span class="infoTxt">新品上市，85折销售，满2000抵10,</span>
							<span class="charTxt fl">价格</span>
							<h:outputLabel value="¥#{productBean.price}" styleClass="price fl"></h:outputLabel>
						</div>
						<div class="height1"></div>
						<span class="charTxt">运费</span>
						<span class="infoTxt freight">  上海 至： 上海  快递费：0 </span>
						<div class="height2"></div>
						<span class="charTxt fl"> 月销量：</span>
						<span class="shortTxt fc-c40000 fs-16 fl "> 233 </span>
						<a class="charLinkTxt goToComment fl">查看累计评论</a>
						<div class="height1"></div>
						<span class="charTxt fl">套餐类型：</span>
						<h:selectOneRadio value="#{productBean.selected}" id="choosePro" required="true" 
						requiredMessage="请选择商品！"  onchange="submit()" valueChangeListener="#{productBean.selectProduct}"
						styleClass="selectBtn fl #{productBean.sytleClass}">						
							<f:selectItem itemValue="true" itemLabel="官方标配" />
						</h:selectOneRadio>				
						
						<div class="height1"></div>
						<span class="charTxt fl">购买数量：</span>
						<h:inputText value="#{productBean.purNum}" id="purNum" required="true"  disabled="true"
						requiredMessage="请输入购买数量！" styleClass="purNum fl"></h:inputText>
						<div class="tinnyBar fl ml10">
						<h:commandButton value = "∧"  styleClass=" squareBtn"  action="#{productBean.addone}"></h:commandButton>
						<div class="height08"></div>
						<h:commandButton value = "∨"  styleClass=" squareBtn"  action="#{productBean.minusone}"></h:commandButton>
						</div>
						<span class=" shortCharTxt fl"> &nbsp; &nbsp; 件</span>
						<h:outputLabel value="库存：#{productBean.stock} 件" styleClass="charTxt  ml20 fl"></h:outputLabel>
						<div class="height2"></div>
						<h:message for="choosePro" styleClass="message"></h:message>
						<h:message for="purNum" styleClass="message"></h:message>
						<h:commandButton value="立即购买"  styleClass="purBtn fc-c40000 bg-f3 bd-c40000 fl" 
						action="#{productBean.doPurchase}"></h:commandButton>
						<h:commandButton value="加入购物车" styleClass="purBtn fc-fff bg-c40000 bd-none fl" 
						action="#{productBean.addToCart}"	></h:commandButton>			
						<div class="height3"></div>
						<h:outputLabel value="#{productBean.message}" styleClass="message"></h:outputLabel>			
					</div>
					
					<div class="clear"></div>
					
				</div>
				</h:form>
				<h:form>
				<h2 id="comment" class="commentH2">累计评论</h2>
				<div class="proCommentPanel ">
				<a name="comment"></a>
				<h:dataTable id="comment" value="#{proCommentBean.pcList}" var="item"
				columnClasses="commenUser,commenCon" rowClasses="proComment" rules="none">					
					<h:column id="comUserId" >
							<h:outputText value="#{item.userId}">
							<f:converter converterId="nameHideConverter"/>
							</h:outputText>
					</h:column>
					<h:column id="comCon">
							<h:outputText value="#{item.commentCon}"></h:outputText>
					</h:column>
				</h:dataTable>
					
					<div class="height3"></div>
					<h:inputTextarea value="#{proCommentBean.commentCon}" styleClass="writeComment"></h:inputTextarea>
					<div class="submitComment">
					<h:commandButton value="评论" action="#{proCommentBean.submitComment}" styleClass="bigButton" ></h:commandButton>
					<h:outputLabel value="#{proCommentBean.message}" styleClass="shortMessage"></h:outputLabel>
					</div>
					<div class="height3"></div>
				</div>
				</h:form>
			</div>			
		</div>
		<h:form>
		<jsp:include page="footer.jsp"></jsp:include>
		</h:form>
</f:view>>
</body>
</html>