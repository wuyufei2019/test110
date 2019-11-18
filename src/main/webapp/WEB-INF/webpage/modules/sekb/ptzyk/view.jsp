<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
pageContext.setAttribute("path", path);
pageContext.setAttribute("basePath", basePath);
%>
<html>
<head>
<title>平台资源库</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="google" content="notranslate">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
<%-- <script src="${ctx}/static/pdfview/build/pdf.js"></script>
<script src="${ctx}/static/pdfview/web/viewer.js"></script> --%>
<script src="${ctx}/static/jquery/jquery-1.8.3.js"></script>
<body>
<script type="text/javascript">
	$(function(){
		/* var m11 = encodeURIComponent('${res.m11}');
		$.ajax({
			type: 'POST',
			url: '${ctx }/sekb/ptzyk/pdfStreamHandeler', 
			data: {"filePath":m11},
			success: function(data){console.log(12);
				$("#my_iframe").prop("src", "${ctxStatic}/pdfview/web/viewer.html?file="+data);
				//console.log("1:"+$("#my_iframe").prop("src");/ZHAJ/src/main/webapp/static/pdfjs/web/viewer.html
			}
		}) */
		/* var filePath = encodeURIComponent('${res.m11}');
		var pdfPath = "C:\\Users\\XY\\Desktop\\1.pdf";
		$("#my_iframe").prop("src", "${ctxStatic}/pdfview/web/viewer.html"); */
	})
	
</script>
<iframe id="my_iframe" src="${ctxStatic }/pdfjs/web/viewer.html" width="100%" height="800"></iframe>

</body>
</html>