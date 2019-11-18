<%@page contentType="text/html;charset=UTF-8" isErrorPage="true"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<script src="${ctxStatic}/jquery/jquery-2.1.1.js" type="text/javascript"></script>
<!DOCTYPE html>
<html>
<head>
	<title>500 - 系统内部错误</title>
</head>
<body style="height: 100%">
	<div class="container-fluid" >
		<div class="page-header"><h1>系统内部错误.</h1></div>
		<div class="errorMessage">
			错误信息：未知错误！ <br/> <br/>
			请将详细错误信息发送给系统管理员，谢谢！<br/> <br/>
			<a onclick="openDetail()">点击查看详情</a>
         <div id="exception" style="display: none;"></div>
		</div>

		<script>try{top.$.jBox.closeTip();}catch(e){}
		function openDetail(){
			if('${exception }'){
				$("#exception").text("${exception }");
			}else{
			    $("#exception").text("<%=exception %>"); 
			}
			$("#exception").toggle();
		}
		</script>
	</div>
</body>
</html>
