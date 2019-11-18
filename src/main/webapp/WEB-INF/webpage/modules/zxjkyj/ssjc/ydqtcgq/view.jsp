<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>气体实时监测数据</title>
	<meta name="decorator" content="default"/>
	<style type="text/css">
	.wdytyl_cg_bg1 {
	background:url('${ctx}/static/model/images/qiti/warnin.gif') no-repeat ;
	background-size:300px ;
	 background-position:center;
	 width: 300px;height:300px;
	}
	
	.wdytyl_cg_bg2 {
	background:url('${ctx}/static/model/images/qiti/normal.gif') no-repeat ;
	background-size:300px ;
	 background-position:center;
	 width: 300px;height:300px;
	}
	
   
   
.r_in {width:140px; height:140px; border-radius:50%; -webkit-border-radius:50%; -moz-border-radius:50%; -ms_border-radius:50%; overflow:hidden; position:relative;}
.r_c {width:140px; height:140px; position:absolute; bottom:0; left:0; height:0;}
.c1 {background:#5cd0ce;}
.r_num {color:#fffda0; font-size:14px; position:absolute; top:50%; margin-top:-25px; text-align:center; width:100%;}

  .qtinfor{
   position:relative; 
   top:82px; left:40px;
   z-index:10;
   color: #ff0000;
   cursor: pointer;
   width: 121px;
   text-align: left;
   }
   .qtinfor span{line-height: 20px;}
</style>
</head>
  
  <body>
  <div style="width: 100%;text-align: center;"><h4><span style="color: #f8ac59;font-size: 24px;">${qiye.m1}</span></h4></div>
   <div class="wdytyl_pic_list" align="center" >
   		<c:forEach items="${cllist}" var="st" varStatus="status">
   		
   		
			<div style="float: left;position:relative;margin: 10px 10px 0px 10px;border: 2px #f2f2f2 solid;padding: 20px;"  >
				<!-- 百分比大于等级1时显示静态，否则显示动态 -->
				<div class="<c:if test="${st.bfb gt st.level1}">wdytyl_cg_bg1</c:if><c:if test="${st.bfb le st.level1}">wdytyl_cg_bg2</c:if>">
				
					<div class="qtinfor">
					<span style="font-weight: bold;"></span><label style="color:#c2d1e5">${st.ydqtmc}</label><br/>
					 
					<span><fmt:formatNumber value="${st.ydqtssnd}" maxFractionDigits="2"/></span><br/>
					 
					<span><fmt:formatNumber value="${st.bfb}" maxFractionDigits="5"/>%</span><br/> 
					
					<span class="refreshtime"></span><br/> 					
					</div>
					<div style="position:relative; top:195px;color: #ff0000;cursor: pointer;width: 130px;text-align:center;">
					<label style="color:#337ab7">位号:${st.wh}</label><br/> 
					<label style="color:#337ab7">更新时间:<span class="refreshdate"></span></label><br/> 
					</div>
				</div>	
			</div>

   			
		</c:forEach>
	</div>

	 
	 
 
	 

  <script type="text/javascript">
    var wlname=""; 
  	var href=window.location.href;
	setTimeout("location.href='"+href+"'",60000);
	
	
	$(function(){
		refreshtime();
		$(".refreshdate").html(new Date().format("yyyy-MM-dd"));
	});
	
	function refreshtime(){
		var time=new Date();
		$(".refreshtime").html(time.format("hh:mm:ss"));
		setTimeout("refreshtime()",1000);
	}
	
  </script>

    </body>
</html>
