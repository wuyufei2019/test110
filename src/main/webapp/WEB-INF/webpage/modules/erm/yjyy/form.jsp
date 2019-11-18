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

     <form id="inputForm" action="${ctx}/erm/yjyy/${action}"  method="post" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right">医院名称：</label></td>
					<td class="width-35"><input style="width: 100%;height: 30px;"  name="M1" class="easyui-textbox " value="${res.m1 }" data-options="required:true"/></td>
					<td class="width-15 active"><label class="pull-right">等级：</label></td>
					<td class="width-35"><input class="easyui-combobox" name="M2" value="${res.m2 }" style="width: 100%;height: 30px;"  data-options="
								editable:false ,data: [
								        {value:'一级甲等',text:'一级甲等'},
								        {value:'一级乙等',text:'一级乙等'},
								        {value:'一级丙等',text:'一级丙等'},
								        {value:'二级甲等',text:'二级甲等'},
								        {value:'二级乙等',text:'二级乙等'},
								        {value:'二级丙等',text:'二级丙等'},
								        {value:'三级特等',text:'三级特等'},
								        {value:'三级甲等',text:'三级甲等'},
								        {value:'三级乙等',text:'三级乙等'},
								        {value:'三级丙等',text:'三级丙等'} ]
						    "/></td>
				</tr>


				<tr >
					<td class="width-15 active"><label class="pull-right">地址：</label></td>
					<td class="width-35" colspan="3">
						<input style="width: 100%;height: 30px;"  name="M3" class="easyui-textbox" value="${res.m3 }" data-options="required:true"/>
					</td>
				</tr>
				
				<tr>
					<td id="map_test" class="width-15 active" ><label class="pull-right">位置 ：</label></td><td colspan="3">
						<label style="margin-left: 10px;height:30px;line-height:30px;">经度：</label>
						<input id="erm_map_c_x" name="M11" class="easyui-textbox" value="${res.m11 }" readonly="readonly" data-options="" style="width:35%;height: 30px;"  />
						<label style="margin-left: 10px;height:30px;line-height:30px;">纬度：</label>
						<input id="erm_map_c_y" name="M12" class="easyui-textbox" value="${res.m12 }" readonly="readonly" data-options="" style="width:35%;height: 30px;"  />
						<a class="btn btn-primary" onclick="showMapXY( )" style="width:60px;">定位</a></td>
				</tr>

				
				<tr>
					<td class="width-15 active"><label class="pull-right">主要负责人：</label></td>
					<td class="width-35"><input style="width: 100%;height: 30px;"  name="M4" class="easyui-textbox " value="${res.m4 }" data-options="validType:'length[0,10]'"/></td>
					<td class="width-15 active"><label class="pull-right">主要负责人电话：</label></td>
					<td class="width-35"><input style="width: 100%;height: 30px;"  name="M5" class="easyui-textbox" value="${res.m5 }" data-options="validType:'mobile'"/></td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">联系人：</label></td>
					<td class="width-35"><input style="width: 100%;height: 30px;"  name="M6" class="easyui-textbox " value="${res.m6 }" data-options="validType:'length[0,10]'"/></td>
					<td class="width-15 active"><label class="pull-right">联系人电话：</label></td>
					<td class="width-35"><input style="width: 100%;height: 30px;"  name="M7" class="easyui-textbox" value="${res.m7 }" data-options="validType:'mobile'" /></td>
				</tr>

				</tr>
				
								
				<tr>
					<td class="width-15 active"><label class="pull-right">医院专长：</label></td>
					<td class="width-35"><input style="width: 100%;height: 30px;"  name="M8" class="easyui-textbox " value="${res.m8 }" data-options="validType:'length[0,200]'"/></td>
					<td class="width-15 active"><label class="pull-right">病床数量：</label></td>
					<td class="width-35"><input style="width: 100%;height: 30px;"  name="M9" class="easyui-textbox" value="${res.m9 }" data-options="validType:'number'"/></td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">备注：</label></td>
					<td class="width-85" colspan="3">
					<input name="M10" type="text" value="${res.m10 }"   class="easyui-textbox" style="width: 100%;height: 80px;" data-options="multiline:true , validType:'length[0,250]'">
					</td>	
				</tr>
				
				<c:if test="${not empty res.ID}">
					<input type="hidden" name="ID" value="${res.ID}" />
					<input type="hidden" name="ID1" value="${res.ID1}" />
					<input type="hidden" name="S1"
						value="<fmt:formatDate value="${res.s1}" pattern="yyyy-MM-dd HH:mm:ss"  />" />
					<input type="hidden" name="S3" value="${res.s3}" />
				</c:if>
			</table>

		  	<tbody>
       </form>
 
	<div id="enterprise_dlg" style="width:100%;height:100%; text-align:center;display: none;" >
		<div><span style="color: red;margin: 0 10px;">点击地图标注应急医院位置!</span>
		<input class="easyui-searchbox" style="width:300px" data-options="prompt:'请输入搜索条件',searcher:function search(value,name){ addmap(value); }" />
		</div>
		<div id="bis_erm_dlg_map" style="width:100%;height: 285px;"></div>
	</div>
<script type="text/javascript">
var usertype=${usertype};
var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
var validateForm;	

function doSubmit(){
	$("#inputForm").submit(); 
}

$(function(){
	var flag=true;
	$('#inputForm').form({    
	    onSubmit: function(){    
	    	var isValid = $(this).form('validate');
	    	if(isValid&&flag){
	    		flag=false;
	    		$.jBox.tip("正在提交，请稍等...",'loading',{opacity:0});
	    		return true;
	    	}
			return false;	// 返回false终止表单提交
	    },    
	    success:function(data){
	    	$.jBox.closeTip();  
	    	if(data=='success')
	    		parent.layer.open({icon:1,title: '提示',offset: 'rb',content: '操作成功！',shade: 0 ,time: 2000 });
	    	else
	    		parent.layer.open({icon:2,title: '提示',offset: 'rb',content: '操作失败！',shade: 0 ,time: 2000 });
	    	parent.dg.datagrid('reload');
	    	parent.layer.close(index);//关闭对话框。
	    }    
	});

});

	var action = "${action}";
	var bmx_x="";
	var bmy_y="";
	var locx ='${res.m11}';
	var locy ='${res.m12}';
	
	$(function(){ 
		$(".erm_yjyy_form_id_table td").mouseover(function() {
			$(this).addClass("over");
		});
		$(".erm_yjyy_form_id_table td").mouseout(function() {
			$(this).removeClass("over");
		});
		
		$("#erm_yjyy_dlg_map").show();
		
	});
	
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
		
	}
</script>
</body>
</html>