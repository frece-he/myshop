<%@ page language="java" errorPage="errorPage.jsp"   contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>My Shop 商城</title>
<link rel="shortcut icon" type="image/x-icon"
	href="../page-resource/img/favicon.ico" />
<link rel="stylesheet" type="text/css"
	href="../page-resource/css/base.css" />
</head>
<body>
	<f:view>
		<h:form>
			<jsp:include page="header.jsp"></jsp:include>
			<a name="top"></a>
			<div class="home">
				<div class="mallTitle"></div>
				<div class="mallBanner"></div>
				<div id="slrArea" class="slrArea mallArea">
					<h1 class="fc-dd0066">数码单反</h1>
					<ul class="showPro">
						<li><h:commandLink action="#{productBean.goToUrl}">
								<f:param name="proName" value="EOS 80D" />
								<span class="proName">EOS 80D</span>
								<img src="../page-resource/img/products/EOS_80D.jpg"
									class="proImg" />
								<span class="proPara">2420万像素/139 x 105.2 x
									78.5毫米/LP-E6N电池</span>
								<span class="mallProPrice">¥7699</span>
							</h:commandLink></li>
						<li>
						<h:commandLink action="#{productBean.goToUrl}">
								<f:param name="proName" value="EOS 5D Mark III" />		
								<span class="proName">EOS 5D Mark	III</span> <img src="../page-resource/img/products/EOS_5D.jpg"
								class="proImg" /> <span class="proPara">2230万像素/152.0 x
									116.4 x 76.4毫米/LP-E6电池</span> <span class="mallProPrice">¥25899</span>						
							</h:commandLink>
						
						</li>
						<li>
						<h:commandLink action="#{productBean.goToUrl}">
								<f:param name="proName" value="EOS 6D" />	
								<span class="proName">EOS 6D</span> <img
								src="../page-resource/img/products/EOS_6D.jpg" class="proImg" />
								<span class="proPara">2020万像素/144.5 x 110.5 x
									71.2毫米/LP-E6电池</span> <span class="mallProPrice">¥13299</span>							
							</h:commandLink>
						</li>
						<li><h:commandLink action="#{productBean.goToUrl}">
								<f:param name="proName" value="EOS 7D" />	
								<span class="proName">EOS 7D</span> <img
								src="../page-resource/img/products/EOS_7D.jpg" class="proImg" />
								<span class="proPara">2020万像素/144.5 x 110.5 x
									71.2毫米/可充电锂电池</span> <span class="mallProPrice">¥9699</span>							
							</h:commandLink>
						</li>
					</ul>
					<div class="clear"></div>
				</div>
				<div id="digitalArea" class="digitalArea mallArea">
					<h1 class="fc-fff">换镜数码</h1>
					<ul class="showPro">
						<li><h:commandLink action="#{productBean.goToUrl}">
								<f:param name="proName" value="EOS M10" />	
								<span class="proName">EOS M10</span> <img
								src="../page-resource/img/products/EOS_M10.jpg" class="proImg" />
								<span class="proPara">1800万像素/108 x 66.6 x 35毫米/LP-E12电池</span>
								<span class="mallProPrice">¥2899</span>							
							</h:commandLink>
						</li>
						<li><h:commandLink action="#{productBean.goToUrl}">
								<f:param name="proName" value="EOS M2" />	
								<span class="proName">EOS M2</span> <img
								src="../page-resource/img/products/EOS_M2.jpg" class="proImg" />
								<span class="proPara">1800万像素/104.9 x 65.2 x
									31.6毫米/LP-E12电池</span> <span class="mallProPrice">¥2299</span>							
							</h:commandLink>
						</li>
						<li><h:commandLink action="#{productBean.goToUrl}">
								<f:param name="proName" value="EOS M3" />		
								 <span class="proName">EOS M3</span> <img
								src="../page-resource/img/products/EOS_M3.jpg" class="proImg" />
								<span class="proPara">2420万像素/110.9 x 68 x
									44.4毫米/LP-E17电池</span> <span class="mallProPrice">¥3699</span>						
							</h:commandLink>
							</li>
					</ul>
					<div class="clear"></div>
				</div>
				<div id="videdArea" class="videdArea mallArea">
					<a name="video"></a>
					<h1 class="fc-fff">摄像专区</h1>
					<ul class="showPro">
						<li><h:commandLink action="#{productBean.goToUrl}">
								<f:param name="proName" value="LEGRIA HF G40" />		
								<span class="proName">LEGRIA HF
									G40</span> <img src="../page-resource/img/products/LEGRIA_HF_G40.jpg"
								class="proImg" /> <span class="proPara">动态291万像素/ 115 x
									84 x 231毫米/765克/20倍光学变焦</span> <span class="mallProPrice">¥8999</span>						
							</h:commandLink>
						</li>
						<li><h:commandLink action="#{productBean.goToUrl}">
								<f:param name="proName" value="LEGRIA HF R706" />	
								<span class="proName">LEGRIA HF
									R706</span> <img
								src="../page-resource/img/products/LEGRIA_HF_R706.jpg"
								class="proImg" /> <span class="proPara">动态207万像素/53 x 58
									x 116mm/240克/32倍光学变焦</span> <span class="mallProPrice">¥2399</span>							
							</h:commandLink>
						</li>
						<li><h:commandLink action="#{productBean.goToUrl}">
								<f:param name="proName" value="LEGRIA mini X" />	
								<span class="proName">LEGRIA mini
									X</span> <img src="../page-resource/img/products/LEGRIA_mini_X.jpg"
								class="proImg" /> <span class="proPara">动态207万像素/82 x 30
									x 109毫米/205克/单焦点/超广角镜头</span> <span class="mallProPrice">¥2688</span>							
							</h:commandLink>
						</li>

					</ul>
					<div class="clear"></div>
				</div>
				<div id="othersArea" class="othersArea mallArea">
					<h1 class="fc-fff">配件专区</h1>
					<ul class="showPro">
						<li><h:commandLink action="#{productBean.goToUrl}">
								<f:param name="proName" value="600EX-RT" />		
								<span class="proName">闪光灯</span> <img
								src="../page-resource/img/products/other_flash.jpg"
								class="proImg" /> <span class="proPara"></span> <span
								class="mallProPrice">¥1689</span>						
							</h:commandLink>
							</li>
						<li><h:commandLink action="#{productBean.goToUrl}">
								<f:param name="proName" value="EF 8-15mm f4L USM" />		
								<span class="proName">EF 8-15mm f4L USM</span> <img
								src="../page-resource/img/products/EF_8-15mm_f4L_USM.jpg"
								class="proImg" /> <span class="proPara"></span> <span
								class="mallProPrice">¥1488</span>						
							</h:commandLink>
							</li>
					</ul>
					<div class="clear"></div>
				</div>

			</div>
			<!-- 底部菜单 begin -->
			<div class="bottomMenu">
				<ul class="bottomMenuCon">
					<li><a href="#" class="goToSRL">单反专区</a></li>
					<li><a href="#" class="goToDigital">换镜数码</a></li>
					<li><a href="#" class="goToVideo">摄像专区</a></li>
					<li><a href="#" class="goToOthers">配件专区</a></li>
				</ul>

			</div>
			<!-- 底部菜单 end -->
			<jsp:include page="footer.jsp"></jsp:include>
		</h:form>
	</f:view>

</body>
</html>