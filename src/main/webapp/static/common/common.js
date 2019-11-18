function menuaction(){
	$("menu").siblings().click(function(){
		$("menu dd ul").hide();
		$("menu dd").removeClass("active");
	});
	$("menu li a").click(function(){
		$("menu dd ul").hide();
	})
	$("menu dd").click(function(){
		$(this).siblings().children("ul").hide();
		$("menu dd").removeClass("active");
		$(this).addClass("active");
		$(this).children("ul").toggle(500);
	})	
}
function setheight(){
	var h=document.body.clientHeight
	mh=h-50;
	fh=h-109;
	dh=h-70;
	$iconbox = document.getElementById("iconbox");
	$iconbox.style.height = dh+"px";
	$menu = document.getElementById("menu");
	$menu.style.height = mh+"px";

	$("#menu ul").css({"height": mh+"px"});
	$page = document.getElementById("page-content");
	$page.style.height=fh+"px";
}