<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css"
	href="../page-resource/css/base.css" />
</head>
<body>
	<f:subview id="header">
	
			<!-- 公用头部模块 begin -->
			<div class="header">
				<div class="headerCon">
					<div class="leftbar">
						<i class="logoHeader"></i>
						<ul class="leftbarTxt">
							<li>
							<h:commandLink value="首 页" action="#{indexBean.goToIndex}" immediate="true" >
						</h:commandLink>
							</li>
							<li><a href="mall.jsp">官方商城</a></li>
							<li><h:commandLink value="个人中心" action="#{orderBean.viewMyOrder}"  onclick="login()" immediate="true" >
						</h:commandLink></li>
							<li><a href="serviceSupport.jsp">服务支持</a></li>
						</ul>
					</div>
					<!--  
					<div class="search">
						<input type="text" class="searchInput" /> <input type="button"
							class="searchBtn" />
					</div>
					-->
					<div class="rightbar">
						<h:commandLink action="#{cartBean.viewMyCart}"  onclick="login()" immediate="true" styleClass="rightA">
						<i class="rightbarImg" title="购物车"></i>
						</h:commandLink>
						<div class="rightbarTxt">

							<%
								if (session.getAttribute("userId") == null) {
											out.print("<a href=\"login.jsp\">登录</a><span class=\"fc-fff\">|</span><a href=\"registration.jsp\">注册</a>");
										} else {
											out.print("<a href=\"changeInfo.jsp\">"
													+ session.getAttribute("userId")
													+ "</a> <span class=\"fc-fff\">|</span><a href=\"logout.jsp\" >退出登录</a>	");
										}
							%>						
							

						</div>
					</div>
				</div>
				<div class="clear"></div>
			</div>

			<!-- 公用头部模块 end  -->
	</f:subview>
	<script type="text/javascript">
		function login(){
			<%if (session.getAttribute("userId") == null) {%>
				alert("请先登录！");
			<%}%>
		
		}
	</script>
</body>
</html>