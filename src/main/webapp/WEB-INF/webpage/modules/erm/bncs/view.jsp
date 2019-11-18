<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>避难场所管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=OTVG2uzqudiovwoHlFIs0P8Z3T7ID4K2"></script>
    <script type="text/javascript" src="${ctx}/static/common/initmap.js?v=1.1"></script> 
   
</head>
<body>

     <form id="inputForm" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right">场所名称：</label></td>
					<td class="width-35">
					${res.m1 }
					 </td>
					<td class="width-15 active"><label class="pull-right">场所类型：</label></td>
					<td class="width-35">${res.m2 }</td>
				</tr>
				
				<tr >
					<td class="width-15 active"><label class="pull-right">详细地址：</label></td>
					<td class="width-35" colspan="3">
						${res.m3 }
					</td>
				</tr>
				
				<tr>
					<td id="map_test" class="width-15 active" ><label class="pull-right">位置 ：</label></td><td colspan="3">
					<div id="erm_bncs_dlg_map_view" style="width:100%;height: 100px;"></div>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">可容纳人数：</label></td>
					<td class="width-35" colspan="3">${res.m4 }</td>
				</tr>

				<tr>
					<td class="width-15 active"><label class="pull-right">联系人：</label></td>
					<td class="width-35">${res.m5 }</td>
					<td class="width-15 active"><label class="pull-right">联系人电话：</label></td>
					<td class="width-35">${res.m6 }</td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">负责人：</label></td>
					<td class="width-35">${res.m7 }</td>
					<td class="width-15 active"><label class="pull-right">负责人电话：</label></td>
					<td class="width-35">${res.m8 }</td>
				</tr>

				<tr >  
					<td class="width-15 active"><label class="pull-right">功能描述：</label></td>
					<td class="width-85" colspan="3" style="height:80px">${res.m9 }</td> 
				</tr>
				<tr >  
					<td class="width-15 active"><label class="pull-right">备注：</label></td>
					<td class="width-85" colspan="3" style="height:80px">${res.m11 }</td> 
				</tr>
			</table>

		  	<tbody>
       </form>
 
<script type="text/javascript">

	$(function(){ 
		$(".erm_bncs_form_id_table td").mouseover(function() {
			$(this).addClass("over");
		});
		$(".erm_bncs_form_id_table td").mouseout(function() {
			$(this).removeClass("over");
		});
		
		$("#erm_bncs_dlg_map_view").show();
		initMap("erm_bncs_dlg_map_view",${res.m12 }, ${res.m13 });
		var marker = new BMap.Marker(point); //创建marker对象
		map.addOverlay(marker); //在地图中添加marker
	});
</script>
</body>
</html>