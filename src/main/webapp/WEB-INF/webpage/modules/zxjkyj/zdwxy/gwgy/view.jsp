<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>高危工艺实时监测数据</title>
	<meta name="decorator" content="default"/>
	<style type="text/css">
	.wdytyl_cg_bg1 {
	background:url('${ctx}/static/model/images/fyf/bg_3.gif') no-repeat ;
	background-size:300px ;
	 background-position:center;
	 width: 300px;height:546px;
	}
	
	.wdytyl_cg_bg2 {
	background:url('${ctx}/static/model/images/fyf/bg_1.jpg') no-repeat ;
	background-size:300px ;
	 background-position:center;
	 width: 300px;height:546px;
	}
	
	
	
   .wdytyl_pic_1 {
   width: 300px;
   position:absolute;
   left:20px; bottom:38px;
   font-size: 14px;
   background-image: url('${ctx}/static/model/images/fyf/bg_2.png');
   background-size:300px ;
   }
   
   
.r_in {width:140px; height:140px; border-radius:50%; -webkit-border-radius:50%; -moz-border-radius:50%; -ms_border-radius:50%; overflow:hidden; position:relative;}
.r_c {width:140px; height:140px; position:absolute; bottom:0; left:0; height:0;}
.c1 {background:#5cd0ce;}
.r_num {color:#fffda0; font-size:14px; position:absolute; top:50%; margin-top:-25px; text-align:center; width:100%;}

  .wdytyl_wxxinfor{
   width:251px;
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
   <div class="wdytyl_pic_list" align="center">
   		<%--<c:forEach items="${cllist}" var="st" varStatus="status">
   		
   		
			<div style="float: left;position:relative;margin: 10px 10px 0px 10px;border: 2px #f2f2f2 solid;padding: 20px;" onclick="showGwgysssjXq(${st.id})">
				<!-- 流量为0时显示静态的反应釜，否则显示动态反应釜 -->
				&lt;%&ndash;<div class="<c:if test="${st.flux ==0}">wdytyl_cg_bg2</c:if><c:if test="${st.flux !=0}">wdytyl_cg_bg1</c:if>">&ndash;%&gt;
				<div class="wdytyl_cg_bg1">
				<div style="position:relative; top:140px; left:-55px;z-index:3;color: #ab7715;"></div>
					
					<!-- 流量为0反应釜不填充液面 -->
					&lt;%&ndash;<c:if test="${st.flux !=0}">
					<div class="wdytyl_pic_1" style="<c:if test="${st.yw>0.95}">background-image: url('${ctx}/static/model/images/chuguan/cg_31.png'); </c:if>background-position:0px <fmt:formatNumber type="number" value="${st.percent*225+68}" maxFractionDigits="0"/>px;height: <fmt:formatNumber type="number" value="${st.percent*225+68}" maxFractionDigits="0"/>px;"></div>
					</c:if>&ndash;%&gt;
					<div class="wdytyl_pic_1" style="background-image: url('${ctx}/static/model/images/chuguan/cg_31.png');background-position:0px 100px;height: 0px;"></div>
					<div style="position:relative; top:180px; left:100px;z-index:10;color: #ab7715;cursor: pointer;width: 121px;text-align: left;" onmouseover="showInfor('${status.count}','${st.gwgyname}',0,event)" onmouseout="hideInfor('${status.count}')">
					<span style="font-weight: bold;">工艺名称：</span>${st.gwgyname}<br/>
					<span style="font-weight: bold;">温度：</span>${st.wd}℃<br/>
					<span style="font-weight: bold;">压力：</span>${st.yl}kPa<br/>
					&lt;%&ndash;<span style="font-weight: bold;">流量：</span><fmt:formatNumber value="${st.flux}" maxFractionDigits="2"/>(m³/h)&ndash;%&gt;
					<span style="font-weight: bold;">液位：</span>${st.yw}m
					</div>
				</div>	
				&lt;%&ndash;<div> 位号：${st.storageno}</div>&ndash;%&gt;
				<div id="cg_infor_${status.count}" class="wdytyl_wxxinfor" style=""> </div>
			</div>

   			
		</c:forEach>--%>
	</div>

	 
	 
 
	 

  <script type="text/javascript">
    var wlname=""; 
  	var href=window.location.href;
	//setTimeout("location.href='"+href+"'",60000);

	$(function () {
		$.ajax({
			type: 'POST',
			async: false,
			url: '${ctx}/zxjkyj/zdwxygwgy/getgwgyinfo',
			dataType: 'json',
			success: function (data) {
				if (data != null && data.length > 0) {
					var html = '';
					var sub_html = '';
					$.each(data, function (index, el) {
						var gwgyId = el.id;
						$.ajax({
							type: 'POST',
							async: false,
							url: '${ctx}/zxjkyj/zdwxygwgy/getgwgysssj/'+gwgyId,
							dataType: 'json',
							success: function (data1) {
								sub_html = '';
								$.each(data1, function (index1, el1) {
									if (el1.bjsj != null && el1.bjsj != '') {
										sub_html += '<span style="font-weight: bold;color: red;">'+(index1+1)+''+el1.label+'：'+el1.value+' '+ el1.unit+'</span><br/>';
									} else {
										sub_html += '<span style="font-weight: bold;">'+(index1+1)+''+el1.label+'：'+el1.value+' '+ el1.unit+'</span><br/>';
									}
								})
							}
						})

						html += '<div style="float: left;position:relative;margin: 10px 10px 0px 10px;border: 2px #f2f2f2 solid;padding: 20px;" onclick="showGwgysssjXq('+el.id+')">\n' +
							    '    <div class="wdytyl_cg_bg1">\n' +
							    '        <div style="position:relative; top:140px; left:-55px;z-index:3;color: #ab7715;"></div>\n' +
								'        <div class="wdytyl_pic_1" style="background-image: url(\'${ctx}/static/model/images/chuguan/cg_31.png\');background-position:0px 100px;height: 0px;"></div>\n' +
								'        <div style="position:relative; top:180px; left:100px;z-index:10;color: #ab7715;cursor: pointer;width: 121px;text-align: left;" onmouseover=showInfor('+index+',"'+el.name+'",0,event) onmouseout="hideInfor('+index+')">\n' +
								'            <span style="font-weight: bold;">工艺名称：</span>'+el.name+'<br/>'+
								sub_html +
								'</div>\n' +
								'\t\t\t\t</div>\t\n' +
								'\t\t\t\t<div id="cg_infor_'+index+'" class="wdytyl_wxxinfor" style=""> </div>\n' +
								'\t\t\t</div>';
					})
					$('.wdytyl_pic_list').html(html);
				}
			}
		})
	})
	
	function showInfor(divid,name,type,event){

		$.ajax({
		      method : 'POST',
		      url : ctx+'/bis/gwgy/gwgyjson1',
		      dataType : 'json',
		      data:{ name: name },
		      success : function(data) {
		    	 $("#cg_infor_"+divid).html("工艺危险特点：");
		    	 if(data.m4!=null)
		    		 $("#cg_infor_"+divid).append(data.m4);

		    	  
		    	 $("#cg_infor_"+divid).append("<br/><br/>重点监控参数：");
		    	 
		    	 if(data.m6!=null&&data.m6!="")
		    		 $("#cg_infor_"+divid).append(data.m6);
		    	 
		    	 $("#cg_infor_"+divid).append("<br/><br/>安全控制基本要求：");
		    	 
		    	 if(data.m7!=null&&data.m7!="")
		    		 $("#cg_infor_"+divid).append(data.m7);
		    	 
		      },
		      error: function(){
		    	  $("#cg_infor_"+divid).hide();
		      }
		});
		
		var parentdiv=$("#cg_infor_"+divid).parent();
		var parentdiv_X=parentdiv.offset().left;
		
		
		if(type=="0"){
			if(parentdiv_X+550 > $(window).width() )
				left=-250;
			else
				left=300;
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

	// 查看高危工艺实时数据信息
	function showGwgysssjXq(gwgyid) {
		openDialogView("查看高危工艺实时数据信息",ctx+"/zxjkyj/zdwxygwgy/viewgwgysssjxq/"+gwgyid,"800px", "400px","");
	}
	
  </script>

    </body>
</html>
