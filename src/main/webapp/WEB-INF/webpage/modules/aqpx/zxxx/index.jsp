<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%> 
<html>
<head>
<title>在线学习</title>
<meta name="decorator" content="default"/>
<script type="text/javascript" src="${ctx}/static/model/js/aqpx/zxxx/jquery.media.js"></script>
<script type="text/javascript" src="${ctxStatic}/flexpaper/flexpaper.js"></script>
<style type="text/css">
#aqpx_zxxx_form_mainform {
	margin: 0;
	padding: 5px 5px;
	text-align: left;
}

.aqpx_zxxx_form_id_table {
	border-collapse: collapse;
}

.aqpx_zxxx_form_id_table tr td {
	border-width: 1px;
	border-style: solid;
	border-color: rgb(149, 184, 231);
	height: 30px;
}

.aqpx_zxxx_form_id_table tr td.over {
	background: rgb(224, 236, 255); /*将是鼠标高亮行的背景色*/
}

.aqpx_zxxx_form_id_table tr td.over input {
	background: rgb(224, 236, 255); /*将是鼠标高亮行的背景色*/
}

.aqpx_zxxx_form_id_table tr td.over textarea {
	background: rgb(224, 236, 255); /*将是鼠标高亮行的背景色*/
}

.aqpx_zxxx_form_id_table_td {
	text-align: right;
	background: rgb(224, 236, 255);
	width: 100px;
	min-width: 100px;
}

.aqpx_zxxx_top {
	width: 1100px;
	height: 750px;
	margin: 0 auto;
}

#zxxx_kjlist div {
	padding: 5px 0px 3px 5px;
}

#zxxx_kjlist a {
	color: #464545;
	font-size: 16px;
	text-decoration: none;
}

#zxxx_kjlist a:HOVER {
	color: #95b8e7;
}

.subject {
	border: none;
	border-radius: 5px;
	box-shadow: 0px 0px 8.82px 0.18px #ccc;
	width: 330px;
	height: 209px;
}

.subject:hover {
	cursor: pointer;
	border: 2px solid #30a6f5;
	-moz-box-shadow: 0 0 10px rgba(48, 166, 245, 1);
	-webkit-box-shadow: 0 0 10px rgba(48, 166, 245, 1);
	box-shadow: 0 0 13px rgba(48, 166, 245, 1);
	width: 330px;
	height: 209px;
}

.subject:hover .pic {
	background: url('${ctx}/static/model/images/aqpx/apply-blue.png')
		no-repeat center;
}

.pic {
	float: left;
	width: 90px;
	height: 90px;
	margin: 25px 0px 0px 20px;
	background: url('${ctx}/static/model/images/aqpx/apply-gray.png')
		no-repeat center;
}

.content {
  position:relative;
	float: left;
	width: 205px;
	height: 100px;
	margin: 20px 0px 15px 0px;
}

.exam {
	width: 100px;
	height: 40px;
	algin: center;
	border: 2px solid #03a9f5;
	border-radius: 5px;
	background-color: #FFFFFF;
	color: #03a9f5;
	font-size: 15px;
	font-weight: bold;
	box-shadow: 2px 2px 8px #ccc;
}

.exam:hover {
	width: 100px;
	height: 40px;
	algin: center;
	border: none;
	border-radius: 5px;
	background-color: #03a9f5;
	box-shadow: 2px 2px 8px #ccc;
	font-size: 15px;
	font-weight: bold;
	color: #FFFFFF;
}

.syiconbox {
	background-color: #f5f5f5;
   width: 96%
}

.syiconbox h5 {
	font-size: 16px;
	margin: 16px 10px;
	padding: 15px;
	border-left: #1e87f2 5px solid;
}
</style>
</head>
<body>
<c:if test="${empty kclist }">
	<div style="height:80px; color: #03A9F3;text-align:center; 60px;background-color:#F8F8F8;padding:26px 0 0px 60px; "><h3 style="font-size: 24px;color: #5CB6F6">暂无学习课程</h3></div>
</c:if>
<c:if test="${!empty kclist }">
	<div style="height:80px; color: #03A9F3;padding-left:30px;margin-bottom: 30px;background-color:#F8F8F8;padding:26px 0 0px 60px; "><h3 style="font-size: 24px;color: #5CB6F6">选择需要学习的课程</h3></div>
</c:if>
<div class="aqpx_zxxx_top">
        
    <c:forEach items="${kclist}" var="st" varStatus="status">
    <c:if test="${kclist[status.index-1].jh ne kclist[status.index].jh}">
          <div class="syiconbox">
			<h5 class="title">计划名称:${st.jh}&emsp;<small style="font-size: 14px">开始时间：<fmt:formatDate pattern="yyyy-MM-dd" value="${st.m5}" />&emsp;截止时间:<fmt:formatDate pattern="yyyy-MM-dd" value="${st.m6}" /></small></h5> 
         </div> 
   </c:if>
	<div class="subject" style="float: left;margin: 13px 13px;" >
		<div class='pic'>
		</div>
		<div class='content'>
			<br/>
			<span style="padding:5px;font-size: 15px;font-weight: bold;color: #242E34;position: absolute;top: 50%;transform: translateY(-50%);">${st.m1}</span>
			<br/>
		</div>
		<div style="clear: both;text-align:center">
		<hr style="border-top:1px solid #eeeeee; height:1px;margin:20px 0px 15px 0px;">
		<button class="exam" onclick="selectkc('${st.id}','${st.planid }')" title="开始学习">开始学习${st.ID}</button>
		</div>
	</div>
      <c:if test="${kclist[status.index].jh ne kclist[status.index+1].jh}">
         <div style="clear: both;"></div>
   </c:if>
</c:forEach> 
</div>
	

<script type="text/javascript">
var starttime;
var endtime;
var pid;
//显示选择的课程课件信息
function selectkc(id,planid){
	pid=id;
	starttime=new Date().getTime();
	window.planid=planid;
	layer.open({
			type: 2,  
	    	shift: 1,
  			area: ['100%', '100%'],
			title: '在线学习',
			content: ctx+'/aqpx/zxxx/kjindex',
   			maxmin: false, 
   			btn:['进入考试','关闭'],
   			yes: function(index){
				$.ajax({
				      method : 'GET',
				      url : ctx+"/aqpx/zxks/createjy/"+id,
				      success : function(data) {
				    	if(data==""){
				    	    closeIndex(index,id);
				    		href=ctx+"/aqpx/zxks/showsj/"+id; 
							layer.open({
				    			area: [400, 200],
				   				title: '考试提醒',
			        			maxmin: false, 
				    			content: '1.考试过程中不允许考生离开考场。<P>2.考生考试过程中不允许参考任何资料。<P>3.确定要开始考试吗？' ,
				    			btn: ['开始考试', '取消'],
				    			yes: function(index, layero){
				    					layer.close(index);
										showsj(href);
					  			},
								cancel: function(index){ 
							     	}
							}); 
				    	}else{
				    		layer.msg(data,{time:5000});
				    	}	
				      }
				});
	     	},
            btn2:function(index){
                closeIndex(index,id);
            },
			cancel: function(index){
			    closeIndex(index,id);
	     	}
	}); 
}
function closeIndex(index,id){
    $.post(ctx+'/aqpx/zxxx/studytime',{start:starttime,end: new Date().getTime(),kcid:id});
	layer.close(index);
} 

//显示试卷
function showsj(href){
	layer.open({
	    type: 2,  
	    shift: 1,
	    area: ['100%', '100%'],
	    title: false,
	    closeBtn: 0,
        maxmin: false, 
	    content: href
	}); 	
	
}

//显示课件信息
function showkj(n){
	$.ajax({
	      method : 'GET',
	      url : ctx+'/aqpx/zxxx/showkj/'+n,
	      success : function(data) {
	    	  var filename=data.name;
	    	  var fileurl=data.url;
	    	  var type=data.type;
	    	  if(type=="file"){
	    		  $('#zxxx_content').empty();
	    		  var cont= $("<div id='documentViewer' class='flexpaper_viewer' style='width:100%;height:100%;display:block;margin:0px auto;' ></div> ");
				  $('#zxxx_content').append(cont);
		     var startDocument = "Paper";  
             $('#documentViewer').FlexPaperViewer(  
                    { config : {  
  
                        SWFFile : fileurl,  
  
                        Scale : 1.5,  
                        ZoomTransition : 'easeOut',  
                        ZoomTime : 0.5,  
                        ZoomInterval : 0.2,  
                        FitPageOnLoad : false,  
                        FitWidthOnLoad : false,  
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
	    	  if(type=="movie"){
	    		  var cont= $("<div style='width:700px;height:440px;margin:0 auto;' ><video src='"+fileurl+"' autoplay controls ></video><div>");	    		  
	    		  $('#zxxx_content').empty();
				  $('#zxxx_content').append(cont);
		      }
	      }
	});	
}
</script>
</body>

</html>