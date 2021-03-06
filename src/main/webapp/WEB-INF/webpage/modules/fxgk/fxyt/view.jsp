<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>查看信息</title>
<meta name="decorator" content="default" />
<%@ include file="/WEB-INF/webpage/include/kindeditor.jsp"%>
<style type="text/css">
    .ball {
    width: 10px;
    height: 10px;
    background: red;
    border-radius: 50%;
    position: absolute;
	} 
	.wrap{
    background: #ccc;
    position: relative;
    width:800px;
	}
	.BMap_cpyCtrl  
    {  
        display:none;   
    }  
    .anchorBL{  
        display:none;   
    }
</style>
<script type="text/javascript" src="${ctx}/static/model/js/fxgk/yjczk/index.js?v=1.0"></script>
</head>
<body>
	<div class="easyui-tabs" fit="true">
		<div title="风险点信息" style="height:100%;" data-options="" id="">
			<div class="easyui-layout" style="margin:10px auto;" >
				<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
					<tbody>
						<tr>
							<td class="width-20 active"><label class="pull-right">负责人：</label></td>
							<td class="width-30">
								${sgfx.m13 }
							</td>
							<td  class="width-20 active"><label class="pull-right">联系方式：</label></td>
							<td  class="width-30">
								${sgfx.m14 }
							</td>
						</tr>
						<tr>
							<td class="width-20 active"><label class="pull-right">风险点名称：</label></td>
							<td class="width-30">
								${sgfx.m1 }
							</td>
							<td class="swidth-20 active"><label class="pull-right">编号：</label></td>
							<td class="width-30">
								${sgfx.m18}
							</td>
						</tr>
						<tr>
							<td class="width-20 active"><label class="pull-right">风险分类：</label></td>
							<td class="width30">${sgfx.m2 }</td>
							<td class="width-20 active"><label class="pull-right">风险分级：</label></td>
							<td class="width-30" id="level">${sgfx.m9 }</td>
						</tr>
						 <tr>
							<td class="width-20 active"><label class="pull-right">场地名称：(备注:一车间、二车间、罐区)</label></td>
							<td class="width-30"  colspan="3">
								${sgfx.areaname }
							</td>
						</tr>
						<tr>
							<td class="width-20 active"><label class="pull-right">行业：</label></td>
							<td class="width-30" colspan="3">${sgfx.m3 }</td>

						</tr>
						<tr>
							<td class="width-20 active"><label class="pull-right">行业类别：</label></td>
							<td class="width-30" colspan="3">${sgfx.m4 }</td>
						</tr>
						<tr>
							<td class="width-20 active"><label class="pull-right">工段：</label></td>
							<td class="width-30" colspan="3">${sgfx.m5 }</td>

						</tr>

						<tr>
							<td class="width-20 active"><label class="pull-right">部位：</label></td>
							<td class="width-30" colspan="3">${sgfx.m6 }</td>
						</tr>
						<tr>
							<td class="width-20 active"><label class="pull-right">易发生事故类型 ：</label></td>
							<td class="width-30" colspan="3">
							${sgfx.m8 }
						</tr>
						<tr>
							<td class="width-20 active"><label class="pull-right">主要危险有害性：</label></td>
							<td class="width-30" colspan="3"> ${sgfx.m7 }
							</td>
						</tr>

						<tr>
							<td class="width-20 active"><label class="pull-right">管控措施 ：</label></td>
							<td class="width-30" colspan="3">
							${sgfx.m10 }
							</td>
						</tr>
						<tr>
							<td class="width-20 active"><label class="pull-right">依据 ：</label></td>
							<td class="width-30" colspan="3">
							${sgfx.m12 }</td>
						</tr>
						 <tr>
							<td class="width-20 active"><label class="pull-right">应急处置对策：</label></td>
							<td class="width-30" colspan="3">
							<div>${sgfx.m11 }</div>

							</td>
						</tr>
						<tr>
							<td class="width-20 active"><label class="pull-right">管控层级：</label></td>
							<td class="width-30" colspan="3">
							${sgfx.m15 }</td>
						</tr>
						<tr>
							<td colspan="4" style="text-align: center;color: dodgerblue;font-size: 14px;"><strong>风险分级管控责任单位及责任人</strong></td>
						</tr>
						<tr>
							<td class="width-20 active"><label class="pull-right">公司：</label></td>
							<td class="width-30">${sgfx.fjgk1 }</td>
							<td class="width-20 active"><label class="pull-right">部门：</label></td>
							<td class="width-30">${sgfx.fjgk2 }</td>
						</tr>
						<tr>
							<td class="width-20 active"><label class="pull-right">班组：</label></td>
							<td class="width-30">${sgfx.fjgk3 }</td>
							<td class="width-20 active"><label class="pull-right">岗位：</label></td>
							<td class="width-30">${sgfx.fjgk4 }</td>
						</tr>

						<tr>
							<td class="width-25 active"><label class="pull-right">风险告知卡：</label></td>
							<td colspan="3"><c:if test="${not empty sgfx.fxgzk}">
								<div style="margin-bottom: 10px;">
									<a style="color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;" onclick="window.open('${ctx}/${sgfx.fxgzk}')">风险告知卡</a>
								</div>
							</c:if></td>
						</tr>

						<tr>
							<td class="width-25 active"><label class="pull-right">安全承诺卡：</label></td>
							<td colspan="3"><c:if test="${not empty sgfx.m24}">
								<c:set var="url" value="${fn:split(sgfx.m24, '||')}" />
								<div style="margin-bottom: 10px;">
									<a style="color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;" onclick="window.open('${url[0]}')">${url[1]}</a>
								</div>
							</c:if></td>
						</tr>

						  <tr>
							<td class="width-20 active"><label class="pull-right">现场图片：</label></td>
							<td class="width-30" colspan="3">
							<c:if test="${not empty sgfx.m16}">
							 <c:forTokens items="${sgfx.m16}" delims="," var="url" varStatus="urls">
								<c:set var="urlna" value="${fn:split(url, '||')}" />
								<div style="float: left;text-align: center;margin: 0 10px 10px 0;">
									<a target="_blank" href="${urlna[0]}"><img src="${urlna[0]}" alt="${urlna[1]}" height="250px;"/><br/>${urlna[1]}</a>
								</div>
							 </c:forTokens>
							 </c:if>
							 </td>
						</tr>

						<tr>
							<td class="width-20 active"><label class="pull-right">警示标志：</label></td>
							<td class="width-30" colspan="3">
							<c:if test="${not empty sgfx.m17}">
							 <c:forTokens items="${sgfx.m17}" delims="," var="url" varStatus="urls">
								<c:set var="urlna" value="${fn:split(url, '||')}" />
								<div style="float: left;text-align: center;margin: 0 10px 10px 0;">
									<a target="_blank" href="${urlna[0]}"><img src="${urlna[0]}" alt="${urlna[1]}" height="200px;"/><br/>${urlna[1]}</a>
								</div>
							 </c:forTokens>
							 </c:if>
							 </td>
						</tr>

						<tr>
							<td  class="width-20 active"><label class="pull-right">绑定二维码：</label></td>
							<td  class="width-30"  >
								${sgfx.bindcontent }
							</td>
						</tr>
						<tr>
							<td class="width-15 active"><label class="pull-right">平面图坐标：</label></td>
							<td class="width-85" colspan="3" >
								<div id="xfss_dlg" style="background-color:#F4F4F4;padding:10px 20px;text-align:center;" >
									<div id="xfss_dlg_map" class="wrap" style="margin:0 auto;width:800px"><img style="width:800px" id="img1"></img></div>
								</div>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		<div title="风险点应急处置卡" style="height:100%;" data-options="" id="">
			<div class="easyui-layout" style="height:100%; ">
				<div id="fxgk_yjczk_tb" style="padding:5px;height:auto">

				</div>

				<table id="fxgk_yjczk_dg"></table>
			</div>
		</div>
	</div>
<script type="text/javascript">
	var locx ='${sgfx.m19}';
	var locy ='${sgfx.m20}';
	var pmtpath='${sgfx.pmt}';
	var url=pmtpath.split('||');
	
	function initImg(url){
		$("#img1").width($('.wrap').width());
		$("#img1").attr("src",url);
	}
	
$(function(){
	 var level=$("#level").text();
	 if(level==1)
		 level="红";
	 if(level==2)
		 level="橙";
	 if(level==3)
		 level="黄";
	 if(level==4)
		 level="蓝";
	 $("#level").text(level);
	 
	 //初始化平面图
	 initImg(url[0]);
	 $("#img1").load(function() {
	 	initMap();
	 });

});

 	function initMap(){
		var wh=$("#img1").width();
		var wh2=$("#img1").height();
		var x=locx;
		var y=locy;
		if(x!="" && y!=""){
	        var top=y*wh2+"px";
	        var left=x*wh+"px";
	        $('.wrap').append('<div class="ball" style="top:'+top+';left:'+left+'"></div>');
		}
	}
</script>
	</body>
</html>