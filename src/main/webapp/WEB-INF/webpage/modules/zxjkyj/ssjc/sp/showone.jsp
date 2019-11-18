<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>视频监控</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctxStatic}/ckplayer/ckplayer.js"></script>
</head>

<body>
	<div style="width: 100%;">
	<div id="playlive" style="margin: 0 auto; height: 300px; width: 540px;"></div>
	</div>


<script type="text/javascript">

$(function () {
	
	 var url = '${video.url}';
	 
		var videoObject = {
				container: '#playlive',//“#”代表容器的ID，“.”或“”代表容器的class
				variable: 'player',//该属性必需设置，值等于下面的new chplayer()的对象
				autoplay: true, //是否自动播放
				video:url //视频地址
		};
		var player=new ckplayer(videoObject);
		
	 
});
</script>
</body>
</html>