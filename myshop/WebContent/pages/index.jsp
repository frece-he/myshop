<%@ page language="java"  errorPage="errorPage.jsp"  contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>My Shop</title>
<link rel="shortcut icon" type="image/x-icon"
	href="../page-resource/img/favicon.ico" />
<link rel="stylesheet" type="text/css"
	href="../page-resource/css/base.css" />
<script type="text/javascript" charset="utf-8"
	src="../page-resource/js/banner.js"></script>
</head>
<body>
	<f:view>		
		<h:form>
		<jsp:include page="header.jsp"></jsp:include>
		<!-- 主页内容  begin -->
		<div class="banner">
			<script language="JavaScript">
				var da = [];
				da.push({
					src : "../page-resource/img/banner1.jpg",
					name : "第一张",
					link : ""
				});
				da.push({
					src : "../page-resource/img/banner2.jpg",
					name : "第二张",
					link : ""
				});
				var c = {
					bg : "#008EFE", //右下数字背景颜色
					color : "#FFFFFF", //右下数字颜色
					cbg : "#FFFFFF", //右下当前数字背景颜色
					ccolor : "#008FDF", //右下当前数字颜色
					bc : "#ccc", //边框颜色
					step : 2500
				//时长，1000一秒
				}
				new IMGS("indexBanner", 1200, 350, da, c);// id,宽度,高度,数据,设置;
			</script>
		</div>
		
			<div class="home">
				<div class="homeCon ">

					<div class="mainProArea">
						<h:commandLink action="#{productBean.goToUrl}"
							styleClass="mainProBig">
							<f:param name="proName" value="#{indexBean.pro1Name}"/>
								<h:graphicImage url="#{indexBean.pro1ImgUrl}" styleClass="proB"></h:graphicImage>
								<h:outputText value="#{indexBean.pro1Name}"
									styleClass="mainProAreaSpan"></h:outputText>
							
						</h:commandLink>
						<h:commandLink action="#{productBean.goToUrl}"
							styleClass="mainProSmall">
							<f:param name="proName" value="#{indexBean.pro4Name}"/>
								<h:graphicImage url="#{indexBean.pro4ImgUrl}" styleClass="proS"></h:graphicImage>
								<h:outputText value="#{indexBean.pro4Name}"
									styleClass="mainProAreaSpan"></h:outputText>
							
						</h:commandLink>
					</div>
					<div class="mainProArea">
						<h:commandLink action="#{productBean.goToUrl}"
							styleClass="mainProBig">
							<f:param name="proName" value="#{indexBean.pro2Name}"/>
								<h:graphicImage url="#{indexBean.pro2ImgUrl}" styleClass="proB"></h:graphicImage>
								<h:outputText value="#{indexBean.pro2Name}"
									styleClass="mainProAreaSpan"></h:outputText>
							
						</h:commandLink>
						<h:commandLink action="#{productBean.goToUrl}"
							styleClass="mainProSmall">
							<f:param name="proName" value="#{indexBean.pro5Name}"/>
								<h:graphicImage url="#{indexBean.pro5ImgUrl}" styleClass="proS"></h:graphicImage>
								<h:outputText value="#{indexBean.pro5Name}"
									styleClass="mainProAreaSpan"></h:outputText>
						
						</h:commandLink>
					</div>
					<h:commandLink action="#{productBean.goToUrl}"
						styleClass="mainProArea mainPro">
						<f:param name="proName" value="#{indexBean.pro3Name}"/>
							<h:graphicImage url="#{indexBean.pro3ImgUrl}" styleClass="proB"></h:graphicImage>
							<div class="height5"></div>
							<h:outputText value="#{indexBean.pro3Name}"
								styleClass="mainProAreaSpan"></h:outputText>
					
					</h:commandLink>
				</div>
				<div class="clear"></div>
			</div>
				<jsp:include page="footer.jsp"></jsp:include>
		</h:form>
		<!-- 主页内容 end -->

	
	</f:view>
</body>
</html>