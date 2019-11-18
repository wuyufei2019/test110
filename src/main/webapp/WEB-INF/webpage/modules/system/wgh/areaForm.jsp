<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>区域网格管理</title>
	<meta name="decorator" content="default"/>
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=OTVG2uzqudiovwoHlFIs0P8Z3T7ID4K2"></script>
    <script type="text/javascript" src="${ctx}/static/common/initmap.js?v=1.1"></script> 
	<script type="text/javascript">
	var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
	var bmx_x="";
	var bmy_y="";
	var locx ='${bis.lng}';
	var locy ='${bis.lat}'; 
	function doSubmit(){
		$("#inputForm").submit(); 
	}
 
	//弹出地图界面
	function showMapXY( ){
		layer.open({
		    type: 1,  
		    area: ['500px', '300px'],
		    title: '标注坐标',
	        maxmin: true, 
	        shift: 1,
	        shade :0,
		    content: $('#enterprise_dlg'),
		    btn: ['确定', '关闭'],
		    success: function(layero, index){
		    	addmap("");
		    },
		    yes: function(index, layero){
		    	$("#erm_map_c_x").textbox("setValue", locx);//经度
				$("#erm_map_c_y").textbox("setValue", locy);//纬度
				layer.close(index);
			  },
			  cancel: function(index){ 
		       }
		});
	}
	function addmap(searchcon){	
		initMap("bis_erm_dlg_map",locx,locy,'','',1);
		map.setDefaultCursor("crosshair");//设置地图默认的鼠标指针样式
		var local = new BMap.LocalSearch(map, {
			renderOptions:{map: map}
		});
		local.search(searchcon);

		var marker = new BMap.Marker(point); //创建marker对象
		map.addOverlay(marker); //在地图中添加marker
		
		map.addEventListener("click", function(e){	
			locx=e.point.lng;
			locy=e.point.lat;
			var now_point =  new BMap.Point(e.point.lng, e.point.lat );
			marker.setPosition(now_point);//设置覆盖物位置
			marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
		});
		
		//创建多边形图
		var mappoint='${bis.mappoint}';
		if(mappoint!=""){
			var arry = mappoint.split("||");//lat，lnt
			var maparry = [];//坐标数组
			var narry;
			var m;
			for (var i = 0; i < arry.length; i++) {
				narry = arry[i].split(",");
				m = new BMap.Point(narry[0], narry[1]);
				maparry.push(m);
			}
			 polygon = new BMap.Polygon(maparry, {
				strokeColor : "red",
				strokeWeight : 2,
				strokeOpacity : 0.5,
				fillOpacity : 0.3
			}); //创建多边形
			map.addOverlay(polygon); //增加多边形
			//map.setViewport(polygon.getPath());
		}

	}
	
	
	$(function(){
		if('${action}'=="create"&&parent.parentPermId!=null)
			$('#fid').combotree('setValue', parent.parentPermId);
		$('#inputForm').form({    
		    onSubmit: function(){    
		    	var isValid = $(this).form('validate');
				return isValid;	// 返回false终止表单提交
		    },    
		    success:function(data){   
		    	if(data=='success')
		    		parent.layer.open({icon:1,title: '提示',offset: 'rb',content: '操作成功！',shade: 0 ,time: 2000 });
		    	else
		    		parent.layer.open({icon:2,title: '提示',offset: 'rb',content: '操作失败！',shade: 0 ,time: 2000 });
		    	parent.dg.treegrid('reload');
		    	parent.layer.close(index);//关闭对话框。
		    }    
		}); 
	});
	</script>
</head>
<body>
	<form id="inputForm"  action="${ctx}/system/admin/xzqy/${action}" method="post" class="form-horizontal">
		<input type="hidden" name="id" value="${bis.id }"/>
		<input type="hidden" name="code" value="${bis.code}" />
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
		   <tbody>
		      <tr>
		         <td  class="width-15 active"><label class="pull-right">网格名称:</label></td>
		         <td  class="width-35" ><input name="m1" type="text" value="${bis.m1 }" class="easyui-textbox" style="width: 100%;height: 30px;" data-options="required:'required',validType:'length[2,100]'"/></td>
		         <td  class="width-15 active"><label class="pull-right">上级网格:</label></td>
		         <td class="width-35" ><input id="fid" type="text" name="fid" value="${bis.fid}" class="easyui-combotree" style="width: 100%;height: 30px;" data-options="required:'required',url:'${ctx}/system/admin/xzqy/idjson',method:'GET'"/></td>
		      </tr>
                    <tr>
                         <td id="map_test" class="width-15 active" ><label class="pull-right">网格中心位置 ：</label></td><td colspan="3">
                              <label style="margin-left: 10px;height:30px;line-height:30px;">经度：</label>
                              <input id="erm_map_c_x" name="lng" class="easyui-textbox" value="${bis.lng }" style="width:35%;height: 30px;"  readonly="readonly" data-options=""  />
                              <label style="margin-left: 10px;height:30px;line-height:30px;">纬度：</label>
                              <input id="erm_map_c_y" name="lat" class="easyui-textbox" value="${bis.lat }" style="width:35%;height: 30px;"  readonly="readonly" data-options=""  />
                              <a class="btn btn-primary" onclick="showMapXY( )" style="width:60px;">定位</a></td></td>
                    </tr>
		      <tr>
		         <td  class="width-15 active"><label class="pull-right">排序:</label></td>
		         <td  class="width-35" ><input type="text" name="m3" value="${bis.m3 }" class="easyui-numberbox" style="width: 100%;height: 30px;" /></td>
		         <td  class="width-15 active"><label class="pull-right">备注:</label></td>
		         <td class="width-35" ><input type="text" name="m4" value="${bis.m4 }" class="easyui-textbox form-control" style="width: 100%;height: 80px;" data-options="multiline:true"> </td>
		      </tr>
                <div id="enterprise_dlg" style="width:100%;height:100%; text-align:center;display: none;" >
          <div><span style="color: red;margin: 0 10px;">点击地图标注位置1!</span>
          <input class="easyui-searchbox" style="width:300px" data-options="prompt:'请输入搜索内容',searcher:function search(value,name){ addmap(value); }" />
          </div>
          <div id="bis_erm_dlg_map" style="width:100%;height: 285px;"></div>
     </div>
			  <!-- 隐藏区域 -->
                 <input type="hidden" id="mappoint" name="mappoint" value="${bis.mappoint}"/>
		</tbody>
		</table>
	</form>
</body>
</html>