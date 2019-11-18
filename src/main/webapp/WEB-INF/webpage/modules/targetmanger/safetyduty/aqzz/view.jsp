<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>安全职责信息查看</title>
<meta name="decorator" content="default"/>
<style type="text/css">
	 
	.fltitle{
		text-align:center;
		font-weight:bold;
		color: #BA0101;
		font-size: 24px;
		margin-bottom: 8px;
		padding-top: 30px;
		text-shadow: 1px 2px 2px #D2D7DA;
	}
	.intro{
    	font-size: 12px;
    	font-weight: normal;
    	text-align: center;
    	color: #999;
	}
	.shadow{
		width:1000px;
		box-shadow: 5px 5px 10px 5px #999;
		margin: 10px auto;
	}
	
</style>
</head>
<body>
<div class="shadow">
	<div style="padding: 30px 35px;">
	  	<div  class="fltitle" >${jobname}</div>
	  	<div  class="intro" >修订时间: <fmt:formatDate value="${entity.s2 }" pattern="yyyy-MM-dd hh:mm:ss"/></div>
	  	<hr />
		<br />
	  	<div >
	  	<p style="line-height:20px;">${entity.duty }</p>
		  	<br />
		  	<hr />
		  	备注:${entity.note }
		  	<hr />
		  	 制定人：${entity.bzperson }
			<div >
				 <div style="clear: both;"></div>
			</div>
	  	</div>
  	</div>
 </div>
 
 
</body>
</html>