<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>My Shop 服务与支持</title>
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
				<div class="height1"></div>
				<p class="outputlable ml130">拍照小Tips</p>
				<div class="height3"></div>
					<div class="paragraph">
						<p>1.阳光十六法则 (Sunny 16 Rule)“阳光十六法则”
						是在没有测光表时，正确估算日光下曝光数值的方法，所以这条法则只有在阳光充足的情况下适合使用。将光圈设为F/16，快门与ISO同步，或者略快。如，ISO设为100时，快门应该设为1/100秒(1/125秒)。所以，根据这个法则，在海滩上摄影时，应该使用F/22光圈，在多云时应使用F/11光圈。</p>
						<p>2.月光11、8和5.6法则 (Moony 11,8and 5.6
							Rules)如果你想拍摄月亮，那么这里有一条很好的法则。快门与ISO同步的时候，拍摄满月用F11光圈，弦月的时候用F8光圈，新月则使用F5.6光圈。
						</p>
						<p>3.相机抖动法则 (Camera Shake Rule)
						当你手持相机拍摄时，快门的速度不能小于镜头焦距的倒数。如果快门速度越慢，那么拍摄抖动时就越可能降低锐度。如果用50mm焦距的话，快门就要达到1/60秒以上为宜，只有当环境实在昏暗时，用闪光灯、脚架或者把相机放在硬物上防止抖动。
						</p>
						<p>4.灰板法则 (Anatomical Gray Card) 
							随身携带18%中灰板是拍摄的利器。可是如果身上没有灰度板怎么办呢?可以将手掌摊开面向阳光，对手掌进行测光，然后加一档曝光。</p>
						<p>5.景深法则 (Depth of Field Rules) 后景深是前景深的2倍
							当被摄物体比较深的时候，应该对焦点选择在景深的前1/3处，因为这样，对焦点后的景深是之前的2倍。各种光圈和焦段组合都可以使用这个法则。记住，光圈越小，焦距越短，距离被摄物体越远，景深就越大。
						</p>
						<p>6.数码冲印尺寸法则 (Largest Digital Print Rule)
							如果你想把你的作品打印成大尺寸的照片时，照片的尺寸不能大于数码图片的长宽像素各除以200，如果你对作品要求很高，那么至少得除以250。</p>
						<p>7.曝光法则 (Exposure Rules)
							在处理数码照片时，最普遍的法则是保证高光区曝光准确，低光区随他去。可是当处理负片，特别是彩色负片的时候，你最好增曝一档。</p>
						<p>8.快速闪光输出法则 (Quick Flash-fill Rule)
							当你的相机不能自动输出控制的闪光灯时，将闪光灯的感光度设为胶卷的二倍。如果对主体测光，机身选择光圈整档，闪光灯设为同样的光圈。这样，照片的阴影区会比主体的亮度低一档。
						</p>
						<p>9.闪光距离法则 (Flash Range Rule)
							这个法则很简单：距离乘以2，感光度乘以4。例如，你的闪光灯在ISO100时，有效距离为20米。如果你想使闪光灯的距离达到40米时，则需要提高感光度到ISO400。</p>
						<p>10.像素翻倍法则 (Megapixel Multiplier
							Rule)如果你想使数码相机的分辨率增倍，那么很简单，就是要像素翻两番。</p>
						<p>11.捕捉动态法则 (Action-stopping
							Rule)这条法则是根据角度与速度的经验公式而来的。如果物体沿着镜头的轴线运动你能够用1/125的快门捕捉下，那么它追至于镜头轴线的运动能用1/500秒捕捉下来。也就是说，如果物体沿镜头轴线称45度运动，只需要1/250的快门速度。
						</p>
						<p>12.日落法则 (Sunset Rule)
							拍摄落日时，要对落日上部测光，但是不能让太阳出现在你的取景器中。如果想让日落看上去比实际晚一小时，可以在曝光补偿中减1。</p>
					</div>
				</div>
			</div>
			<jsp:include page="footer.jsp"></jsp:include>
		</h:form>
	</f:view>
</body>
</html>