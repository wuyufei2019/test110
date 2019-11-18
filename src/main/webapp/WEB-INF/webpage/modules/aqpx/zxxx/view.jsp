<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%> 
<html>
<head>
<title>在线学习</title>
<meta name="decorator" content="default"/>
<script type="text/javascript" src="${ctxStatic}/ckplayer/ckplayer.js"></script>
<script type="text/javascript" src="${ctx}/static/model/js/aqpx/zxxx/jquery.media.js"></script>
<script type="text/javascript" src="${ctxStatic}/flexpaper/flexpaper.js"></script>
<script type="text/javascript" src="${ctxStatic}/common/flashUtil.js"></script>
<style type="text/css">
body{
	background-color: #EEEEEE;
}
hr{
	margin:10px 0px 10px 0px;
}
#aqpx_zxxx_form_mainform {
	margin: 0;
	padding: 5px 5px;
	text-align: left;
}
.aqpx_zxxx_form_id_table{
	border-collapse:collapse;
}
.aqpx_zxxx_form_id_table tr td {
	border-width:1px;
	border-style: solid;
	border-color: rgb(149, 184, 231);
	height:30px;
}
.aqpx_zxxx_form_id_table tr td.over {
    background:rgb(224, 236, 255);  /*将是鼠标高亮行的背景色*/
}
.aqpx_zxxx_form_id_table tr td.over input {
    background:rgb(224, 236, 255);  /*将是鼠标高亮行的背景色*/
}
.aqpx_zxxx_form_id_table tr td.over textarea {
    background:rgb(224, 236, 255);  /*将是鼠标高亮行的背景色*/
}
.aqpx_zxxx_form_id_table_td {
	text-align: right;
	background:rgb(224, 236, 255);
	width:100px;
	min-width:100px;
}
#zxxx_kjlist{
overflow :auto;
border:1px;
border-radius:5px;
box-shadow: 0px 0px 8.82px 1px #ccc;
background-color: #FFFFFF;
width:155px;
height:96%;
float:left;
padding:10px;
margin:18px 0px 0px 15px;
}
#zxxx_kjlist div{
padding: 5px 0px 3px 0px;
}
#zxxx_kjlist a{
color: #464545;
font-size: 16px;
text-decoration: none;
}
#zxxx_kjlist a:HOVER{
color: #95b8e7;
}
#zxxx_content{
position:relative;
border:1px;
border-radius:5px;
box-shadow: 0px 0px 8.82px 1px #ccc;
background-color: #FFFFFF;
height:96%;
padding:10px;
margin:18px 20px 0px 195px;
}
audio:-webkit-full-page-media, video:-webkit-full-page-media {
    max-height: 100%;
    max-width: 100%;
}
video {
    object-fit: contain;
}
</style>
</head>
<body>
		<div id="zxxx_kjlist">
			<font style="font-size: 15px;color: #5EB0DE;font-weight: bold">学习课件</font>
			<hr style="border-top:1px solid #eeeeee; height:1px">
		</div>
		 
		<div style="padding:10px;text-align: center;" id="zxxx_content" >
			<a href="" class="media"></a> 
		</div>
<script type="text/javascript">
//显示选择的课程课件信息
$(function(){
	$.ajax({
	      method : 'GET',
	      url : ctx+'/aqpx/zxxx/showkjlist/'+parent.pid,
	      dataType : 'json',
	      success : function(data) {
	      	 $.each(data, function(i, item) {
	      		var st=i+1;
	      		var lx=item.m3;
	      		var img="";
	      		var html='<a href="javascript:void(0)"  style="font-size:13px"  onclick="showkj('+item.id+')"><div style="text-align:center">';
	      		if(lx=="docx"||lx=="doc"){
	      			img='<img src="${ctx}/static/model/images/aqpx/zxxx/doc.png" style="margin:0 auto" /><br>';
	      		}else if(lx=="xls"||lx=="xlsx"){
	      			img='<img src="${ctx}/static/model/images/aqpx/zxxx/xls.png" style="margin:0 auto" /><br>';
	      		}else if(lx=="ppt"||lx=="pptx"){
	      			img='<img src="${ctx}/static/model/images/aqpx/zxxx/ppt.png" style="margin:0 auto" /><br>';
	      		}else if(lx=="pdf"){
	      			img='<img src="${ctx}/static/model/images/aqpx/zxxx/pdf.png" style="margin:0 auto" /><br>';
	      		}else if(lx=="mp4"){
	      			img='<img src="${ctx}/static/model/images/aqpx/zxxx/mp4.png" style="margin:0 auto" /><br>';
	      		}
	      		html+=img;
	      		html=html+ '['+st+'] '+item.m1+'.'+lx+'</div></a>';
				$('#zxxx_kjlist').append(html);
	           });
	      }
	});	
});

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
	    		  var str = '<iframe frameborder="0" src="${ctx}/aqpx/zxxx/pdfView?url='+fileurl+'" style="width: 100%;height: 99%;"></iframe>';
	    		  var cont= $(str);
				  $('#zxxx_content').append(cont);
	    	  }
	    	  if(type=="movie"){
	    	  	  var mp4url = ctx+fileurl;
	    		 var cont= $("<div id='playlive' style='margin: 0 auto; height: 100%; width: 100%;'></div>");	    		  
	    		 $('#zxxx_content').empty();
				 $('#zxxx_content').append(cont);
				  var videoObject = {
					container: '#playlive',//“#”代表容器的ID，“.”或“”代表容器的class
					variable: 'player',//该属性必需设置，值等于下面的new chplayer()的对象
					autoplay: true, //是否自动播放
					video: mp4url //视频地址
				  };
				  var player=new ckplayer(videoObject);
		      }
	      }
	});	
}


function sssa(a) {
    sss(a);
}
</script>
</body>

</html>