<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="../page-resource/css/base.css" />
</head>
<body>
<f:subview id="footer">

<!-- 公用底部模块 begin -->
	<div class="footerBg">
		<div class="footer">
			<div class="footerCon">
				<i class="footerImg fl"></i>
				<div class="footerList fl">
					<h3>账号相关</h3>
					<a href="login.jsp">注册登录</a>
					<a href="forgotpassword.jsp">更改密码</a>
					<a href="changeInfo.jsp">信息修改</a>
				</div>
				<div class="footerList fl">
					<h3>产品相关</h3>
					<a href="mall.jsp">热门商品</a>
					<a href="#">产品知识</a>
					<a href="">社区交流</a>					
				</div>
				<div class="footerList fl">
					<h3>联系我们</h3>
					<a href="#">售后服务</a>
					<a href="#">咨询投诉</a>
					<a href="feedback.jsp">意见反馈</a>					
				</div>				
				<div class="footerCode fl">
					<h3>关注我们</h3>
					<div class="codeDiv fl">
						<span>官方微信号</span>
						<img class="codeImg" src="../page-resource/img/QRcode1.png" />
					</div>
					<div class="codeDiv fl">
						<span>APP下载</span>
						<img class="codeImg" src="../page-resource/img/QRcode2.png" />
					</div>				
				</div>
				<div class="clear"></div>
			</div>			
			<div class="copyRight">
				<p>CopyRight©2012-2027 MyShop Co.,Ltd.. All Rights Reserved </p>
				<p>沪ICP备12345678号</p>
			</div>
		</div>
	</div>
	
	<!-- 公用底部模块 end -->
	<!-- 右侧菜单 begin -->
		<div class="rightMenu">
			<ul>
				<li title="返回顶部">
					<a href="#" class="goToTop" target="_self"></a>
				</li>
				<li title="在线客服">
					<a href="#" class="serviceOnline"></a>
				</li>
				<li title="意见反馈">					
					<h:commandLink  action="#{feedbackBean.goToFeedback}"   onclick="login()"
				immediate="true" styleClass="feedback">
				</h:commandLink>
				</li>
			</ul>

		</div>
		<!-- 右侧菜单 end -->

</f:subview>
<script type="text/javascript" src="../page-resource/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="../page-resource/js/common.js"></script>
</body>
</html>