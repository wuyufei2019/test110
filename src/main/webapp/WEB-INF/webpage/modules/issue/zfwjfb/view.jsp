<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>文件信息查看</title>
<script type="text/javascript" src="${ctxStatic}/flexpaper/jquery.js"></script>
<script type="text/javascript" src="${ctxStatic}/flexpaper/flexpaper.js"></script>
<script type="text/javascript">var  ctxStatic='${ctxStatic}';</script>
<script type="text/javascript">
var swfurl='${sfr.m7}';
var swf=swfurl.split(",");
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
	  	<div  class="fltitle" >${sfr.m1 }</div>
	  	<div  class="intro" >发布时间: ${sfr.s1 }</div>
	  	<hr />
			<div >
				 <c:if test="${not empty sfr.m3}">
				 <label >文件预览(点击切换):</label>	
				 <div id="art_addFormFj" style="width:100%;float: left;">
				 <c:forTokens items="${sfr.m3}" delims="," var="url" varStatus="urls">
				 	<c:set var="urlna" value="${fn:split(url, '||')}" />
				 	<c:set var="urlscount" value="${urls.count}" />
				 	<div data-id='_file-${urls.index +1}' class="new_file_div " style="float:left" > 
				 	<a style="color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;" onclick="changeFile('${urlna[0]}')">${urlna[1]}</a>
				 	</div>
				 </c:forTokens>
				 </div>
				 </c:if>
				 <div style="clear: both;"></div>
			</div>	
		<br />
	  	<div >
	  	<div>  
	  	 <c:if test="${not empty sfr.m7}">
            <div id="documentViewer" class="flexpaper_viewer" style="width:100%;height:100%;display:block;margin:0px auto;"></div>
            <script type="text/javascript"> 
            	 var startDocument = "Paper";  
             $('#documentViewer').FlexPaperViewer(  
                    { config : {  
  
                        SWFFile : swf[0],  
  
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
		  	<label >备注:</label>${sfr.m4 }
		  	<hr style="height:1px;border:none;border-top:1px dashed #999;" />
		  	<label>附件下载：</label>
			<div >
				 <c:if test="${not empty sfr.m3}">
				 <div id="art_addFormFj" style="width:100%;float: left;">
				 <c:forTokens items="${sfr.m3}" delims="," var="url" varStatus="urls">
				 	<c:set var="urlna" value="${fn:split(url, '||')}" />
				 	<c:set var="urlscount" value="${urls.count}" />
				 	<div data-id='_file-${urls.index +1}' class="new_file_div" > 
				 	<a style="color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;" onclick="downFile('${urlna[0]}')">${urlna[1]}</a>
				 	</div>
				 </c:forTokens>
				 </div>
				 </c:if>
				 <div style="clear: both;"></div>
			</div>			
	  	</div>
  	</div>
 </div>
<script type="text/javascript">
	function changeFile(url){
	var str = url.split(".");
	var swf=str[0]+".swf";
          $('#documentViewer').FlexPaperViewer(  
                { config : {  
                    SWFFile : swf,  
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
	}
	
	function downFile(fileurl){
			$.get('${ctx}/issue/zfwjfb/down/${sfr.ID}');
			window.open(fileurl);
	}
</script>
</body>
</html>