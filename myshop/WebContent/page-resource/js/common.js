//页面滚动
 $(document).ready(function() {
	$('a.goToTop').click(function() {
		$('html, body').animate({
			scrollTop: 0
		}, 700);
		return false;
	});
})
 $(document).ready(function() {
	$('a.goToSRL').click(function() {
		$('html, body').animate({
			scrollTop: document.getElementById("slrArea").offsetTop
		}, 850);
		return false;
	});
})
 $(document).ready(function() {
	$('a.goToDigital').click(function() {
		$('html, body').animate({
			scrollTop: document.getElementById("digitalArea").offsetTop
		}, 850);
		return false;
	});
})
 $(document).ready(function() {
	$('a.goToVideo').click(function() {
		$('html, body').animate({
			scrollTop: document.getElementById("videdArea").offsetTop
		}, 850);
		return false;
	});
})
 $(document).ready(function() {
	$('a.goToOthers').click(function() {
		$('html, body').animate({
			scrollTop: document.getElementById("othersArea").offsetTop
		}, 850);
		return false;
	});
})
 $(document).ready(function() {
	$('a.goToComment').click(function() {
		$('html, body').animate({
			scrollTop: document.getElementById("comment").offsetTop
		}, 850);
		return false;
	});
})

$(document).ready(function(){
$(".flip").click(function(){
    $(".panel").slideToggle(400);
  });
});



