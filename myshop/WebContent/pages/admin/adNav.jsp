<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="../../page-resource/css/base.css" />
</head>
<body>
<f:subview id="adNav">

<div class="adNav">
			<div class="adNavCon">
				<a href="manaRelease.jsp" id="nav1">发布商品</a> 
				<h:commandLink id="manaExistPro" value="商品管理"
							action="#{manaExistProBean.viewExistPro}"></h:commandLink>
				<a href="manaAccount.jsp" id="nav3"	class="current">用户管理</a> 
				<h:commandLink id="manaExistPro" value="订单管理"
							action="#{manaOrderBean.viewOrder}"></h:commandLink>
				<a href="manaFeedback.jsp" id="nav5">查看反馈</a>
			</div>			
		</div>

		<jsp:include page="adfooter.jsp"></jsp:include>
</f:subview>
<script type="text/javascript" src="../../page-resource/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="../../page-resource/js/common.js"></script>
</body>
</html>