<%@ page language="java" errorPage="errorPage.jsp"   contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>My Shop Success</title>
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
					<p class="breadCrumbs bg-fafafa ">首页>个人中心</p>
					<jsp:include page="leftlist.jsp"></jsp:include>
					<div class="rightCon">
						<div class="successPage">
						<i class="successImg"></i>
							<span class="successPageTxt">操作成功！</span>
							<div class="height3"></div>
							<h:commandButton value="返回首页" action="index.jsp"
								styleClass="chooseBtn fl"></h:commandButton>								
							<h:commandButton value="浏览商城" action="mall.jsp"
								styleClass="chooseBtn fl"></h:commandButton>
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