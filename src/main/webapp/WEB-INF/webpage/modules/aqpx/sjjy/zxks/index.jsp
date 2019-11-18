<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>三级安全教育培训在线考试</title>
<meta name="decorator" content="default"/>

<style type="text/css">
body{
	background-color: #ddf3ff;
}
.aqpx_zxks_top{
margin:0 auto;
width: 778px;
height:auto;
background-color:#FFFFFF;
-webkit-box-shadow: 2px 3px 7px rgba(0,20,31,.35);
-moz-box-shadow: 2px 3px 7px rgba(0,20,31,.35);
box-shadow: 2px 3px 7px rgba(0,20,31,.35);
}
.paper{
font-family: "Microsoft YaHei";
font-size: 14px;
color: #fff;
}
.exam{
width: 92px;
height: 29px;
margin-right:30px;
float:right;
border:none;
border-radius:5px;
background-color: #03a9f5;
box-shadow: 2px 2px 8px #ccc;
}
</style>

</head>
<body>
<c:if test="${empty kclist }">
	<div style="color: #03A9F3;margin: 50px 0 30px; text-align: center;"><h3 style="font-size: 24px">暂无相关考试课程</h3></div>
</c:if>
<c:if test="${!empty kclist }">
	<div style="color: #03A9F3;margin: 50px 0 30px; text-align: center;"><h3 style="font-size: 24px">三级安全教育考试课程选择</h3></div>
</c:if>

<div class="aqpx_zxks_top">
<c:forEach items="${kclist}" var="st" varStatus="status">
	<div style="padding: 10px 0px 0px 10px;margin: 3px 5px;" >
		<img src="${ctx}/static/model/images/aqpx/sj.png"/><span style="font-size: 14px;line-height: 30px;margin-left:8px">${st.m1}</span>
		<button class="exam" data-placement="left" onclick="selectkc('${st.ID}')" title="进入考试"><span style="font-size: 14px;color: #FFFFFF;">进入考试</span></button>
		<hr style="border-top:1px dashed #eeeeee; height:1px;margin:10px 0px 0px 0px">
	</div>
</c:forEach> 
<div align="center" style="padding:10px 0px 20px 0px">
<img src="${ctx}/static/model/images/aqpx/test.png"/>
</div>
</div>


<script type="text/javascript">
var href;

//选择考试的课程
function selectkc(id){
	$.ajax({
	      method : 'GET',
	      url : ctx+"/aqpx/zxks/createjy/"+id,
	      success : function(data) {
	    	if(data==""){
	    		href=ctx+"/aqpx/zxks/showsj/"+id; 
				layer.open({
	    			area: [400, 200],
	   				title: '考试提醒',
        			maxmin: false, 
	    			content: '1.考试过程中不允许考生离开考场。<P>2.考生考试过程中不允许参考任何资料。<P>3.确定要开始考试吗？' ,
	    			btn: ['开始考试', '取消'],
	    			yes: function(index, layero){
	    					layer.close(index);
							showsj();
		  			},
					cancel: function(index){ 
				     	}
				}); 
	    	}else{
	    		layer.msg(data,{time:5000});
	    	}	
	      }
	});
}

//显示试卷
function showsj(){
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
</script>
</body>

</html>