<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>安全文件查看下载</title>
<script type="text/javascript" src="${ctxStatic}/flexpaper/jquery.js"></script>
<script type="text/javascript" src="${ctxStatic}/flexpaper/flexpaper.js"></script>
<script type="text/javascript">var  ctxStatic='${ctxStatic}';</script>
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
		width:1000px;
		box-shadow: 5px 5px 10px 5px #999;
		margin: 10px auto;
	}
	label{
		color: #999;
		text-shadow: 1px 2px 2px #D2D7DA;
		font-size: 18px;
	}
	
</style>
</head>
<body>
<div class="shadow">
	<div style="padding: 30px 35px;">
	  	<div  class="fltitle" >${wjfb.m1 }</div>
	  	<div  class="intro" >发布日期: <fmt:formatDate pattern="yyyy-MM-dd" value="${wjfb.s1 }" /></div>
	  	<hr />
		<br />
	  	<div >
	  	<div>  
	  	 <c:if test="${not empty wjfb.m15}">
            <div id="documentViewer" class="flexpaper_viewer" style="width:100%;height:100%;display:block;margin:0px auto;"></div>
            <script type="text/javascript"> 
            	 var startDocument = "Paper";  
             $('#documentViewer').FlexPaperViewer(  
                    { config : {  
  
                        SWFFile : '${wjfb.m15}',  
  
                        Scale : 2.0,  
                        ZoomTransition : 'easeOut',  
                        ZoomTime : 0.5,  
                        ZoomInterval : 0.2,  
                        FitPageOnLoad : true,  
                        FitWidthOnLoad : true,  
                        FullScreenAsMaxWindow : false,  
                        ProgressiveLoading : false,  
                        MinZoomSize : 0.2,  
                        MaxZoomSize : 5,  
                        SearchMatchAll : false,  
                        InitViewMode : 'Portrait',  
                        RenderingOrder : 'flash',  
                        StartAtPage : '',  
  
                        ViewModeToolsVisible : true,  
                        ZoomToolsVisible : true,  
                        NavToolsVisible : true,  
                        CursorToolsVisible : true,  
                        SearchToolsVisible : true,  
                        WMode : 'window',  
                        localeChain: 'en_US'  
                    }}  
            ); 
</script> 
        </c:if>
        </div>
		  	<br />
		  	<hr style="height:1px;border:none;border-top:1px dashed #999;" />
		  	<label>附件：</label>
			<div>
				<c:if test="${not empty wjfb.m6}">
					<c:set var="url" value="${fn:split(wjfb.m6, '||')}" />
						<div  style="margin-bottom: 10px;">
							<c:set var="url2" value="${fn:split(url[0], '.')}" />
							<c:if test="${url2[1] eq 'doc' || url2[1] eq 'docx'}">
							<img src="${ctxStatic}/model/images/w.png"><a style="color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;" onclick="downFile('${url[0]}')">${url[1]}.doc</a>
							</c:if>
							<img src="${ctxStatic}/model/images/p.png"><a style="color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;" onclick="downFile('${wjfb.m14}')">${url[1]}.pdf</a>
						</div>
				</c:if>
			</div>
			
	  	</div>
  	</div>
 </div>
<script type="text/javascript">
	function downFile(fileurl){
		$.get('${ctx}/zdgl/wjck/down/${cdjsid}');
		window.open(fileurl);
	}
</script>
</body>
</html>