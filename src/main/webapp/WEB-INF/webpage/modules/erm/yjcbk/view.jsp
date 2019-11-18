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
					<td class="width-15 active"><label class="pull-right">储备库名称：</label></td>
					<td class="width-35">
					${res.m1 }
					 </td>
					<td class="width-15 active"><label class="pull-right">所属单位：</label></td>
					<td class="width-35">${res.m7 }</td>
				</tr>
				
				<tr >
					<td class="width-15 active"><label class="pull-right">储备库地址：</label></td>
					<td class="width-35" colspan="3">
						${res.m2 }
					</td>
				</tr>
				
				<tr>
					<td id="map_test" class="width-15 active" ><label class="pull-right">位置 ：</label></td><td colspan="3">
					<div id="erm_bncs_dlg_map_view" style="width:100%;height: 100px;"></div>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">负责人：</label></td>
					<td class="width-35">${res.m3 }</td>
					<td class="width-15 active"><label class="pull-right">负责人电话：</label></td>
					<td class="width-35">${res.m4 }</td>
				</tr>

				<tr>
					<td class="width-15 active"><label class="pull-right">库容：</label></td>
					<td class="width-35" colspan="3">${res.m6 }</td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">存放物品：</label></td>
					<td class="width-35" colspan="3">${res.m5 }</td>
				</tr>
				
				<tr> 
					<td class="width-15 active"><label class="pull-right">应对事故类型：</label></td><td colspan="3"><input type="checkbox" id="ydlx1" name="M9" value="1" readonly="readonly" />物体打击
																		                <input type="checkbox" id="ydlx2" name="M9" value="2" />车辆伤害
																		                <input type="checkbox" id="ydlx3" name="M9" value="3" />机械伤害
																		                <input type="checkbox" id="ydlx4" name="M9" value="4" />起重伤害
																		                <input type="checkbox" id="ydlx5" name="M9" value="5" />触电<br/>
																		                <input type="checkbox" id="ydlx6" name="M9" value="6" />淹溺
																		                <input type="checkbox" id="ydlx7" name="M9" value="7" />灼烫
																		                <input type="checkbox" id="ydlx8" name="M9" value="8" />火灾
																		                <input type="checkbox" id="ydlx9" name="M9" value="9" />高处坠落
																		                <input type="checkbox" id="ydlx10" name="M9" value="10" />坍塌<br/>
																		                <input type="checkbox" id="ydlx11" name="M9" value="11" />冒顶片帮
																		                <input type="checkbox" id="ydlx12" name="M9" value="12" />透水
																		                <input type="checkbox" id="ydlx13" name="M9" value="13" />放炮
																		                <input type="checkbox" id="ydlx14" name="M9" value="14" />火药爆炸
																		                <input type="checkbox" id="ydlx15" name="M9" value="15" />瓦斯爆炸<br/>
																		                <input type="checkbox" id="ydlx16" name="M9" value="16" />锅炉爆炸
																		                <input type="checkbox" id="ydlx17" name="M9" value="17" />容器爆炸
																		                <input type="checkbox" id="ydlx18" name="M9" value="18" />其它爆炸
																		                <input type="checkbox" id="ydlx19" name="M9" value="19" />中毒和窒息
																		                <input type="checkbox" id="ydlx20" name="M9" value="20" />其它伤害</td>
				</tr>

				<tr >  
					<td class="width-15 active"><label class="pull-right">功能描述：</label></td>
					<td class="width-85" colspan="3" style="height:80px">${res.m8 }</td> 
				</tr>
				<tr >  
					<td class="width-15 active"><label class="pull-right">备注：</label></td>
					<td class="width-85" colspan="3" style="height:80px">${res.m10 }</td> 
				</tr>
			</table>

		  	<tbody>
       </form>
 
<script type="text/javascript">
	var ydlx = "${ydlx}";
	var pointx= '${res.m11 }';
	var pointy= '${res.m12 }';
	var ydlxArr = ydlx.split(",");
	for(var i=0;i<ydlxArr.length;i++){
		$("input[name='M9']:checkbox[value='"+ydlxArr[i]+"']").attr('checked','true');
	}
	$("#map_test_divaxy").hide();
	$("*[name='M9']").attr('disabled', 'true');
	$(function(){ 
		$(".erm_yjzb_form_id_table td").mouseover(function() {
			$(this).addClass("over");
		});
		$(".erm_yjzb_form_id_table td").mouseout(function() {
			$(this).removeClass("over");
		});
		
		$("#erm_yjzb_dlg_map_view").show();
		initMap("erm_bncs_dlg_map_view",pointx, pointy);
		var marker = new BMap.Marker(point); //创建marker对象
		map.addOverlay(marker); //在地图中添加marker
		
		
	});
</script>
</body>
</html>