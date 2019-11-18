<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>设备信息</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/common/initmap.js?v=1.1"></script> 
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=OTVG2uzqudiovwoHlFIs0P8Z3T7ID4K2"></script>
	<script type="text/javascript">
	var usertype=${usertype};

	var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
	var validateForm;

function doSubmit(){
	if(usertype!= '1'){
	 	var options = $("#_qyid").combobox('options');  
     	var data = $("#_qyid").combobox('getData');/* 下拉框所有选项 */  
     	var value = $("#_qyid").combobox('getValue');/* 用户输入的值 */  
     	var b = false;/* 标识是否在下拉列表中找到了用户输入的字符 */  
     	for (var i = 0; i < data.length; i++) {  
        	if (data[i][options.valueField] == value) {  
            	b=true;  
           	 	break;  
        	}  
    	}  
		if(b==false){
				layer.open({icon:1,title: '提示',offset: 'auto',content: '所选企业不存在！',shade: 0 ,time: 2000 });
				return;
			}
		}
	/* $('#M2').combobox('setValue',$('#M2').combobox('getText'));
	$('#bis_scsb_form_mainform_M3').combobox('setValue',$('#bis_scsb_form_mainform_M3').combobox('getText')); */
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
	
	</script>
</head>
<body>

     <form id="inputForm" action="${ctx}/jtjcsj/sbxx/${action}"  method="post" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<c:if test="${usertype != '1' and action eq 'create'}">
					<tr>
						<td class="width-15 active"><label class="pull-right">企业名称：</label></td>
						<td class="width-35" colspan="3">
							<input value="${scsblist.ID1 }" id="_qyid" name="ID1" style="width: 100%;height: 30px;"
									class="easyui-combobox"
									data-options="required:'true',valueField: 'id',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson'" />
						</td>
					</tr>
			    </c:if>
			    <c:if test="${usertype != '1' and action eq 'update'}">
					<tr >  
					<td class="width-15 active" ><label class="pull-right">企业名称：</label></td>
					<td class="width-35" colspan="3">
						<input value="${scsblist.ID1 }" id="_qyid" name="ID1" style="width: 100%;height: 30px;"
									class="easyui-combobox"
									data-options="editable:false, disabled:true, valueField: 'id',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson'" /></td>
				</tr>
				</c:if>
				
				
				<tr>
					<td class="width-15 active"><label class="pull-right">设备编码：</label></td>
					<td class="width-35" ><input id="" name="equipcode" style="width: 100%;height: 30px;" class="easyui-textbox" value="${scsblist.equipcode }"  data-options="validType:'length[0,19]',required:true" /></td>
					<td class="width-15 active"><label class="pull-right">设备名称：</label></td>
					<td class="width-35" ><input id="bis_scsb_form_mainform_M3" name="M3" style="width: 100%;height: 30px;" class="easyui-textbox" value="${scsblist.m3 }"  data-options="validType:'length[0,50]',required:true ,valueField: 'text',textField: 'text'" /></td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">重大危险源名称：</label></td>
					<td class="width-35"><input id="" name="hazardcode" style="width: 100%;height: 30px;" class="easyui-combobox" value="${scsblist.hazardcode }" data-options="editable : false, panelHeight:100,valueField:'id', textField:'text',url: '${ctx}/bis/hazard/hazardCodeJson'"/></td>
					<td class="width-15 active"><label class="pull-right">设备类型：</label></td>
					<td class="width-35"><input id="" name="equiptype" style="width: 100%;height: 30px;" class="easyui-combobox" value="${scsblist.equiptype }" data-options="required:'true',editable : false, panelHeight:100,data:[{value:'G0',text:'罐'},{value:'Q0',text:'气体检测仪'},{value:'S0',text:'生产装置'},{value:'C0',text:'仓库'}]"/></td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">设备描述：</label></td>
					<td class="width-35" colspan="3"><input name="equipdescribe" type="text" value="${scsblist.equipdescribe}"   class="easyui-textbox" style="width: 100%;height: 80px;" data-options="multiline:true,validType:'length[0,200]'"></td>		
				</tr>

				<tr>
					<td class="width-15 active"><label class="pull-right">设备介质：</label></td>
					<td class="width-35" colspan="3"><input id="bis_scsb_form_mainform_M9" name="M9" style="width: 100%;height: 30px;" class="easyui-textbox" value="${scsblist.m9 }" data-options="required:'true',validType:'length[0,50]'"/></td>
				</tr>

				<tr>
					<td class="width-15 active"><label class="pull-right">设备运行状态：</label></td>
					<td class="width-35"><input id="" name="equipstatus" style="width: 100%;height: 30px;" class="easyui-combobox" value="${scsblist.equipstatus }" data-options="required:'true',editable : false, panelHeight:'auto',data:[{value:'0',text:'停用'},{value:'1',text:'在用'}]"/></td>
					<td class="width-15 active"><label class="pull-right">安装位置：</label></td>
					<td class="width-35"><input id="" name="installloc" style="width: 100%;height: 30px;" class="easyui-textbox" value="${scsblist.installloc }" data-options="required:'true', panelHeight:'auto',validType:'length[0,50]'"/></td>
				</tr>
				
				
				<tr>
					<td class="width-15 active"><label class="pull-right">经纬度：</label></td>
					<td colspan="3">
						<span><label>经度</label>
						<input id="longitude" name="longitude" value="${scsblist.longitude}" class="easyui-textbox" readonly="readonly" data-options="required:'true'" style="width:150px;height:30px;"/>
						<label>纬度</label> 
						<input id="latitude" name="latitude" value="${scsblist.latitude }" class="easyui-textbox" readonly="readonly" data-options="required:'true'" style="width:150px;height:30px;"/>
						<a class="btn btn-primary" onclick="showMapXY( )" style="width:60px;">定位</a></span></td>
				</tr>
				
				
				<c:if test="${not empty scsblist.ID}">
					<input type="hidden" name="ID" value="${scsblist.ID}" />
					<input type="hidden" name="ID1" value="${scsblist.ID1}" />
					<input type="hidden" name="createby" value="${scsblist.createby}" />
					<input type="hidden" name="S1"
						value="<fmt:formatDate value="${scsblist.s1}" pattern="yyyy-MM-dd HH:mm:ss"  />" />
					<input type="hidden" name="S3" value="${scsblist.s3}" />
					<input type="hidden" name="parkid" value="${scsblist.parkid}" />
					<input type="hidden" name="districtcode" value="${scsblist.districtcode}" />
				</c:if>
				</tbody>
			</table>

		  	
       </form>
       
        <div id="enterprise_dlg" style="width:100%;height:100%; text-align:center;display: none;" >
		<div><span style="color: red;margin: 0 10px;">点击地图标注位置!</span>
		<input class="easyui-searchbox" style="width:300px" data-options="prompt:'请输入搜索条件',searcher:function search(value,name){ addmap(value); }" />
		</div>
		<div id="bis_enterprise_dlg_map" style="width:100%;height: 285px;"></div>
		</div>
       
 
<script type="text/javascript">
	var usertype=${usertype};
	
	var locx ='${scsblist.longitude}';
	var locy ='${scsblist.latitude}';
	
	//弹出地图界面
	function showMapXY( ){
		layer.open({
		    type: 1,  
		    area: ['500px', '400px'],
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
		    	$("#longitude").textbox("setValue", locx);//经度
				$("#latitude").textbox("setValue", locy);//纬度
				layer.close(index);
			  },
			  cancel: function(index){ 
		       }
		});
	}
	
	function addmap(searchcon){	
		initMap("bis_enterprise_dlg_map",locx,locy);
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