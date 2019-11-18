<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>检查工作计划信息查看</title>
<script type="text/javascript">
</script>
<style type="text/css">
	#xbf {
		background-color: #E7E8C6
	}
	.text {
		background-color: #D9E2F3
	}
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
		box-shadow: 5px 5px 10px 5px #999;
		margin-bottom: 8px;
	}
	body{margin:auto;width:60%}
	
</style>
</head>
<body>
<div class="shadow">
	<div style="margin:20px 10px 10px 20px;">
	  	<div  class="fltitle" >${cpe.m1 }</div>
	  	<div  class="intro" >发布时间: <fmt:formatDate value="${cpe.s1 }" pattern="yyyy-MM-dd HH:mm:ss"  /></div>
	  	<hr />
		<br />
	  	<div >
	  	工作清单：
	  	<p style="line-height:20px;">${cpe.m3 }</p>
		  	<br />
		  	<hr />
		  	备注:<p>${cpe.m4 }</p>
		  	<hr />
		  	检查表附件：
			<div style="width: 500px;padding-bottom:50px;">
				 <c:if test="${empty cpe.m5}">
					<input type="hidden" id="art_upfjsl" value="0" />
					<div id="art_addFormFj" style="width:100%;float: left;"></div>
				 </c:if>
				 <c:if test="${not empty cpe.m5}">
				 <div id="art_addFormFj" style="width:100%;float: left;">
				 <c:forTokens items="${cpe.m5}" delims="," var="url" varStatus="urls">
				 	<c:set var="urlna" value="${fn:split(url, '||')}" />
				 	<c:set var="urlscount" value="${urls.count}" />
				 	<div data-id='_file-${urls.index +1}' class="new_file_div" style="width:100%;float:left;">
				 	<a id='${urls.index +1}' style="color:blue;text-decoration:underline;cursor:pointer;" onclick="window.open('${_ctx}${urlna[0]}')">${urlna[1]}</a>&nbsp;&nbsp;
				 	</div>
				 </c:forTokens>
				 </div>
				 <input type="hidden" id="art_upfjsl" value="${urlscount}" />
				 </c:if>
			</div>
	  	</div>
  	</div>
 </div>
</body>
</html>