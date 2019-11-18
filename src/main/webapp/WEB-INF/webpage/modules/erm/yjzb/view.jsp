<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>应急装备管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=OTVG2uzqudiovwoHlFIs0P8Z3T7ID4K2"></script>
    <script type="text/javascript" src="${ctx}/static/common/initmap.js?v=1.1"></script> 
</head>
<body>

     <form id="inputForm" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right">装备分类：</label></td>
					<td class="width-35">
					${res.m1}
					 </td>
					<td class="width-15 active"><label class="pull-right">装备名称：</label></td>
					<td class="width-35">${res.m2 }</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">规格 型号：</label></td>
					<td class="width-35">${res.m3 }</td>
					<td class="width-15 active"><label class="pull-right">数量：</label></td>
					<td class="width-35">${res.m4 }</td>
				</tr>

				<tr>
					<td class="width-15 active"><label class="pull-right">功能用途：</label></td>
					<td class="width-35">${res.m5 }</td>
					<td class="width-15 active"><label class="pull-right">自储数量：</label></td>
					<td class="width-35">${res.m6 }</td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">代储数量：</label></td>
					<td class="width-35">${res.m7 }</td>
					<td class="width-15 active"><label class="pull-right">储存单位：</label></td>
					<td class="width-35">${res.m8 }</td>
				</tr>

				<tr >
					<td class="width-15 active"><label class="pull-right">地址：</label></td>
					<td class="width-35" colspan="3">
						${res.m9 }
					</td>
				</tr>
				
				<tr>
					<td id="map_test" class="width-15 active" ><label class="pull-right">位置 ：</label></td>
					<td colspan="3">
						<div id="erm_yjzb_dlg_map_view" style="width:100%;height: 100px;"></div>
					</td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">联系人：</label></td>
					<td class="width-35">${res.m10 }</td>
					<td class="width-15 active"><label class="pull-right">应急电话：</label></td>
					<td class="width-35">${res.m11 }</td>
				</tr>
					
				</tr>
				
				<tr> 
					<td class="width-15 active"><label class="pull-right">应对事故类型：</label></td><td colspan="3"><input type="checkbox" id="ydlx1" name="M12" value="1" /><a style="cursor:default;" onclick="ydlxChange1()">物体打击</a>
																		                <input type="checkbox" id="ydlx2" name="M12" value="2" /><a style="cursor:default;" onclick="ydlxChange2()">车辆伤害</a>
																		                <input type="checkbox" id="ydlx3" name="M12" value="3" /><a style="cursor:default;" onclick="ydlxChange3()">机械伤害</a>
																		                <input type="checkbox" id="ydlx4" name="M12" value="4" /><a style="cursor:default;" onclick="ydlxChange4()">起重伤害</a>
																		                <input type="checkbox" id="ydlx5" name="M12" value="5" /><a style="cursor:default;" onclick="ydlxChange5()">触电</a><br/>
																		                <input type="checkbox" id="ydlx6" name="M12" value="6" /><a style="cursor:default;" onclick="ydlxChange6()">淹溺</a>
																		                <input type="checkbox" id="ydlx7" name="M12" value="7" /><a style="cursor:default;" onclick="ydlxChange7()">灼烫</a>
																		                <input type="checkbox" id="ydlx8" name="M12" value="8" /><a style="cursor:default;" onclick="ydlxChange8()">火灾</a>
																		                <input type="checkbox" id="ydlx9" name="M12" value="9" /><a style="cursor:default;" onclick="ydlxChange9()">高处坠落</a>
																		                <input type="checkbox" id="ydlx10" name="M12" value="10" /><a style="cursor:default;" onclick="ydlxChange10()">坍塌</a><br/>
																		                <input type="checkbox" id="ydlx11" name="M12" value="11" /><a style="cursor:default;" onclick="ydlxChange11()">冒顶片帮</a>
																		                <input type="checkbox" id="ydlx12" name="M12" value="12" /><a style="cursor:default;" onclick="ydlxChange12()">透水</a>
																		                <input type="checkbox" id="ydlx13" name="M12" value="13" /><a style="cursor:default;" onclick="ydlxChange13()">放炮</a>
																		                <input type="checkbox" id="ydlx14" name="M12" value="14" /><a style="cursor:default;" onclick="ydlxChange14()">火药爆炸</a>
																		                <input type="checkbox" id="ydlx15" name="M12" value="15" /><a style="cursor:default;" onclick="ydlxChange15()">瓦斯爆炸</a><br/>
																		                <input type="checkbox" id="ydlx16" name="M12" value="16" /><a style="cursor:default;" onclick="ydlxChange16()">锅炉爆炸</a>
																		                <input type="checkbox" id="ydlx17" name="M12" value="17" /><a style="cursor:default;" onclick="ydlxChange17()">容器爆炸</a>
																		                <input type="checkbox" id="ydlx18" name="M12" value="18" /><a style="cursor:default;" onclick="ydlxChange18()">其它爆炸</a>
																		                <input type="checkbox" id="ydlx19" name="M12" value="19" /><a style="cursor:default;" onclick="ydlxChange19()">中毒和窒息</a>
																		                <input type="checkbox" id="ydlx20" name="M12" value="20" /><a style="cursor:default;" onclick="ydlxChange20()">其它伤害</a></td>
				</tr>

				<tr>
					<td class="width-15 active"><label class="pull-right">备注：</label></td>
					<td class="width-85" colspan="3" style="height: 80px;">
					${res.m13 }
					</td>			
				</tr>

			</table>

		  	<tbody>
       </form>
 
 		<div id="yjzb_dlg" class="easyui-dialog" style="width:600px;height:450px;padding:10px 20px;text-align:center;"
                closed="true" >
            <div class="ftitle" style="color: red;">请在地图上标注厂区位置(点击地图后显示厂区经度、纬度)!</div>
			<table cellspacing="2px" cellpadding="0" width="100%" style="margin:auto;text-align:left;"> 
				<tr><td>
					<input class="easyui-searchbox" style="width:300px" data-options="prompt:'请输入搜索条件',searcher:function search(value,name){ 
																													init_map(value);
																													}" />
				</td></tr>
				<tr><td>
					<div id="erm_yjzb_dlg_map" style="width:100%;height: 300px;"></div>
				</td></tr>
			</table>  
        <div id="yjzb_dlg-buttons" style="text-align:center;">
            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#yjzb_dlg').dialog('close')" style="width:90px">取消</a>
        </div>
        </div>
<script type="text/javascript">
	var ydlx = "${ydlx}";
	var ydlxArr = ydlx.split(",");
	var pointx='${res.m14}';
	var pointy='${res.m15}';
	for(var i=0;i<ydlxArr.length;i++){
		$("input[name='M12']:checkbox[value='"+ydlxArr[i]+"']").attr('checked','true');
	}
	$("#map_test_divaxy").hide();
	$("*[name='M12']").attr('disabled', 'true');
	$(function(){ 
		$(".erm_yjzb_form_id_table td").mouseover(function() {
			$(this).addClass("over");
		});
		$(".erm_yjzb_form_id_table td").mouseout(function() {
			$(this).removeClass("over");
		});
		
		$("#erm_yjzb_dlg_map_view").show();
		init_map();
	});
	//初始化地图
	function init_map(){
		//地图初期化
		var map = new BMap.Map("erm_yjzb_dlg_map_view");
		var point = new BMap.Point(pointx, pointy);//设置地图中心位置
		map.setDefaultCursor("crosshair");//设置地图默认的鼠标指针样式
		map.enableScrollWheelZoom();//启用滚轮放大缩小，默认禁用。
		map.centerAndZoom(point, 15);
		var marker = new BMap.Marker(point); //创建marker对象
		map.addOverlay(marker); //在地图中添加marker
		marker.enableDragging();
	}
</script>
</body>
</html>