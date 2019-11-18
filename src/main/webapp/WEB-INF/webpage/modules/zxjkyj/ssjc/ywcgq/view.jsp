<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>物料实时监测数据</title>
	<meta name="decorator" content="default"/>
	<style type="text/css">
	.wdytyl_cg_bg1 {
	background:url('${ctx}/static/model/images/chuguan/cg_11.png') no-repeat;
	 background-position:center;
	 width: 250px;height:250px;
	}
	
	.wdytyl_cg_bg2 {
	background:url('${ctx}/static/model/images/chuguan/cg_12.png') no-repeat;
	 background-position:center;
	 width: 400px;height:250px;
	}
	
	.wdytyl_cg_bg3 {
	background:url('${ctx}/static/model/images/chuguan/cg_13.png') no-repeat;
	 background-position:center;
	 width: 250px;height:250px;
	}
	
	
   .wdytyl_pic_1 {
   width: 250px;
   position:absolute;
   left:0px; bottom:16px;
   font-size: 14px;
   background-image: url('${ctx}/static/model/images/chuguan/cg_21.png');
   }
   
   .wdytyl_pic_2{
   width: 400px;
   position:absolute;
   left:0px; bottom:16px;
   font-size: 14px;
   background-image: url('${ctx}/static/model/images/chuguan/cg_22.png');
   }
   
   .wdytyl_pic_3{
   width: 250px;
   position:absolute;
   left:0px; bottom:16px;
   font-size: 14px;
   background-image: url('${ctx}/static/model/images/chuguan/cg_23.png');
   }
   
   .wdytyl_pic_ck{
   width: 290px;
   height: 290px;
   margin: 10px 0px;
   position:relative;
   }
   
.r_in {width:140px; height:140px; border-radius:50%; -webkit-border-radius:50%; -moz-border-radius:50%; -ms_border-radius:50%; overflow:hidden; position:relative;}
.r_c {width:140px; height:140px; position:absolute; bottom:0; left:0; height:0;}
.c1 {background:#5cd0ce;}
.r_num {color:#fffda0; font-size:14px; position:absolute; top:50%; margin-top:-25px; text-align:center; width:100%;}

  .wdytyl_wxxinfor{
   width:250px;
   color:#ffffff;
   background: rgba(103,103,103,0.8);
   position:absolute;
   top:10px;
   border-radius: 20px;
   padding:6px 0px 6px 6px;
   z-index:5;
   display: none;
   text-align: left;
   }
</style>
</head>
  
  <body>
  <div style="width: 100%;text-align: center;"><h4><span style="color: #f8ac59;font-size: 24px;">${qiye.m1}</span></h4></div>
   <div class="wdytyl_pic_list" align="center" style="margin: 0px auto;">
   
   		<c:forEach items="${cllist}" var="st" varStatus="status">
   		
		<c:if test="${st.lx==1}">
			<div style="float: left;position:relative;margin: 10px 10px 0px 10px;"  >
				<div class="wdytyl_cg_bg1">
				
					<div class="wdytyl_pic_1" style="<c:if test="${st.percent>0.95}">background-image: url('${ctx}/static/model/images/chuguan/cg_31.png'); </c:if>background-position:0px <fmt:formatNumber type="number" value="${st.percent*231+19}" maxFractionDigits="0"/>px;height: <fmt:formatNumber type="number" value="${st.percent*231+19}" maxFractionDigits="0"/>px;"></div>
					<div style=" position:relative; top:0px; left:0px;z-index:2;color: #666;cursor: pointer;width: 220px;padding: 85px 0px;" onmouseover="showInfor('${status.count}','${st.wl}',0,event)" onmouseout="hideInfor('${status.count}')">
					物质:${st.wl}<br/>
					液位:<fmt:formatNumber value="${st.ssyw}" maxFractionDigits="2"/>cm<br/>
					
				</div>	
				<div> 位号：${st.wh}</div>
				<div id="cg_infor_${status.count}" class="wdytyl_wxxinfor" style=""> </div>
			</div>
		</c:if>
		

		<c:if test="${st.lx==2}">
			<div style="float: left;position:relative;margin: 10px 10px 0px 10px;" >
				<div class="wdytyl_cg_bg2">
				
					<div class="wdytyl_pic_2" style="<c:if test="${st.percent>0.95}">background-image: url('${ctx}/static/model/images/chuguan/cg_32.png'); </c:if>background-position:0px <fmt:formatNumber type="number" value="${st.percent*171+77}" maxFractionDigits="0"/>px;height: <fmt:formatNumber type="number" value="${st.percent*171+79}" maxFractionDigits="0"/>px;"></div>
					<div style="position:relative; top:18px; left:0px;z-index:2;color: #666;cursor: pointer;width: 400px;padding: 45px 0px 100px;"onmouseover="showInfor('${status.count}','${st.wl}',1,event)" onmouseout="hideInfor('${status.count}')">
					物质:${st.wl}<br/>
					液位:<fmt:formatNumber value="${st.ssyw}" maxFractionDigits="2"/>cm<br/>
					
				</div>	
				<div> 位号：${st.wh}</div>
				<div id="cg_infor_${status.count}" class="wdytyl_wxxinfor" style=""> </div>
			</div>
		</c:if>
		
		
		<c:if test="${st.lx==3}">
			<div style="float: left;position:relative;margin:10px 10px 0px 10px;" >
				<div class="wdytyl_cg_bg3">
				
					<div class="wdytyl_pic_3" style="<c:if test="${st.percent>0.95}">background-image: url('${ctx}/static/model/images/chuguan/cg_33.png'); </c:if>background-position:0px <fmt:formatNumber type="number" value="${st.percent*200+50}" maxFractionDigits="0"/>px;height: <fmt:formatNumber type="number" value="${st.percent*200+50}" maxFractionDigits="0"/>px;"></div>
					<div style="position:relative; top:0px; left:0px;z-index:2;color: #666;cursor: pointer;width: 250px;padding: 70px 0px;" onmouseover="showInfor('${status.count}','${st.wl}',0,event)" onmouseout="hideInfor('${status.count}')">
					物质:${st.wl}<br/>
					液位:<fmt:formatNumber value="${st.ssyw}" maxFractionDigits="2"/>cm<br/>
					
				</div>	
				<div> 位号：${st.wh}</div>
				<div id="cg_infor_${status.count}" class="wdytyl_wxxinfor" style=""> </div>
			</div>
		</c:if>
		<c:if test="${st.lx==4}">
			<div style="float: left;position:relative;margin: 10px 10px 0px 10px;" >
				<div class="wdytyl_cg_bg1">
				
					<div class="wdytyl_pic_1" style="<c:if test="${st.percent>0.95}">background-image: url('${ctx}/static/model/images/chuguan/cg_31.png'); </c:if>background-position:0px <fmt:formatNumber type="number" value="${st.percent*250}" maxFractionDigits="0"/>px;height: <fmt:formatNumber type="number" value="${st.percent*250}" maxFractionDigits="0"/>px;"></div>
					<div style="position:relative; top:0px; left:0px;z-index:2;color: #666;cursor: pointer;width: 220px;padding: 85px 0px;" onmouseover="showInfor('${status.count}','${st.wl}',0,event)" onmouseout="hideInfor('${status.count}')">
					物质:${st.wl}<br/>
					液位:<fmt:formatNumber value="${st.ssyw}" maxFractionDigits="2"/>cm<br/>
					
				</div>	
				<div> 位号：${st.wh}</div>
				<div id="cg_infor_${status.count}" class="wdytyl_wxxinfor" style=""> </div>
			</div>
		</c:if>

   			
		</c:forEach>
	</div>

	 
	 
 
	 

  <script type="text/javascript">
    var wlname=""; 
  	var href=window.location.href;
	//setTimeout("location.href='"+href+"'",60000);
	
	
	function showInfor(divid,name,type,event){

		$.ajax({
		      method : 'POST',
		      url : ctx+'/sekb/msds/json',
		      dataType : 'json',
		      data:{ name: name },
		      success : function(data) {
		    	 $("#cg_infor_"+divid).html("主要危险性：");
		    	 if(data.m10!=null)
		    		 $("#cg_infor_"+divid).append(data.m10);
		    	 if(data.m11!=null)
		    		 $("#cg_infor_"+divid).append(data.m11);
		    	 if(data.m12!=null)
		    		 $("#cg_infor_"+divid).append(data.m12);
		    	  
		    	 $("#cg_infor_"+divid).append("<br/><br/>应急处理：");
		    	 
		    	 if(data.m20!=null&&data.m20!="")
		    		 $("#cg_infor_"+divid).append(data.m20);
		    	 
		    	 $("#cg_infor_"+divid).append("<br/><br/>灭火方法：");
		    	 
		    	 if(data.m19!=null&&data.m19!="")
		    		 $("#cg_infor_"+divid).append(data.m19);
		    	 
		      },
		      error: function(){
		    	  $("#cg_infor_"+divid).hide();
		      }
		});
		
		var parentdiv=$("#cg_infor_"+divid).parent();
		var parentdiv_X=parentdiv.offset().left;
		
		
		if(type=="0"){
			if(parentdiv_X+500 > $(window).width() )
				left=-250;
			else
				left=250;
		}
		
		if(type=="1"){
			if(parentdiv_X+600 > $(window).width() )
				left=-255;
			else
				left=400;
		}
		
		$("#cg_infor_"+divid).show();
		$("#cg_infor_"+divid).css("left", left ); 
		$("#cg_infor_"+divid).css("top", 0 ); 
	}
	
	function hideInfor(divid){
		$("#cg_infor_"+divid).hide();
	}
	
  </script>

    </body>
</html>
