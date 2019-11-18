<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>视频监控</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctxStatic}/ckplayer/ckplayer.js"></script>
</head>

<body>
	<div id="playlist" style="width: 1120px;margin: 0 auto;">
	</div>


<script type="text/javascript">
var player;
function load(seturl,divid) {
	var videoObject = {
			container: '#'+divid,//“#”代表容器的ID，“.”或“”代表容器的class
			variable: 'player',//该属性必需设置，值等于下面的new chplayer()的对象
			autoplay: true, //是否自动播放
			video:seturl //视频地址
	};
	player=new ckplayer(videoObject);
	
}

$(function () {
	
	$.ajax({
	    method : 'GET',
	    url : '${ctx}/zxjkyj/spjk/listjson',
	    data : {'id':${qyid}},
	    dataType : 'json',
	    success : function(data) {
	    	$.each(data, function(i, item) {
	    		var divid="dvplay"+i;
	    		var contdiv =$("<div id='"+divid+"' style=\"margin:5px; height: 300px; width: 540px;float: left;\"></div>");
				$('#playlist').append(contdiv);
				load(item.url,divid);
	        });
	    },
		error : function(){
	    	
	    }
	});	
	
 
    
});
</script>
</body>
</html>