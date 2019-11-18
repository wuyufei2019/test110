<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>隐患排查点</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=OTVG2uzqudiovwoHlFIs0P8Z3T7ID4K2"></script>
    <script type="text/javascript" src="${ctx}/static/common/initmap.js?v=1.1"></script> 
	<script type="text/javascript">
	var usertype=${usertype};
	var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
	var validateForm;

function doSubmit(){
	if(usertype!= '1'){
	 	var options = $("#_qyid").combobox('options');  
     	var data = $("#_qyid").combobox('getData');		/* 下拉框所有选项 */  
     	var value = $("#_qyid").combobox('getValue');	/* 用户输入的值 */  
     	var b = false;		/* 标识是否在下拉列表中找到了用户输入的字符 */  
     	for (var i = 0; i < data.length; i++) {  
        	if (data[i][options.valueField] == value) {  
            	b=true;  
           	 	break;  
        	}  
    	}  
		if(b==false){
				layer.open({title: '提示',offset: 'auto',content: '所选企业不存在！',shade: 0 ,time: 2000 });
				return;
		}
	}
	var rows = dg.datagrid('getData').rows;
	if(rows.length==0){
		layer.open({title: '提示',offset: 'auto',content: '未绑定巡检内容，请绑定！',shade: 0 ,time: 2000 });
		return;
	}
	var ids="";
	for(var i=0;i<rows.length;i++){
		if(ids==""){
			ids=rows[i].id;
		}else{
			ids=ids+","+rows[i].id;
		}
	}
	$("#bdnrids").val(ids);
	$("#inputForm").serializeObject();
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
	    	else if(data=='ewmerror')
	    		parent.layer.open({icon:2,title: '提示',offset: 'rb',content: '二维码重复，操作失败！',shade: 0 ,time: 2000 });
	    	else if(data=='rfiderror')
	    		parent.layer.open({icon:2,title: '提示',offset: 'rb',content: 'rfid重复，操作失败！',shade: 0 ,time: 2000 });
	    	else if(data=='bderror')
	    		parent.layer.open({icon:2,title: '提示',offset: 'rb',content: '绑定巡检内容失败！',shade: 0 ,time: 2000 });
	    	else
	    		parent.layer.open({icon:2,title: '提示',offset: 'rb',content: '操作失败！',shade: 0 ,time: 2000 });
	    	parent.dg.datagrid('reload');
	    	parent.layer.close(index);//关闭对话框。
	    }    
	});

});
	
	</script>
	
	<style type="text/css">
	.BMap_cpyCtrl  
    {  
        display:none;   
    }  
    .anchorBL{  
        display:none;   
    }  
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
	} 
	</style>
	<style type="text/css">
 
</style>
</head>
<body>

     <form id="inputForm" action="${ctx}/yhpc/yhpcd/${action}"  method="post" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<c:if test="${usertype != '1' and action eq 'create'}">
					<tr>
						<td class="width-15 active"><label class="pull-right">企业名称：</label></td>
						<td class="width-35" colspan="3">
							<input value="${yhpcd.ID1 }" id="_qyid" name="ID1" style="width: 100%;height: 30px;"
									class="easyui-combobox"
									data-options="required:'true',valueField: 'id',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson',
									onSelect : function(rec){	
											 	$.ajax({
											 	url:'${ctx}/yhpc/yhpcd/qypmt',
					 							data:{'qyid':rec.id},
					 							dataType:'text',
					 							type : 'POST',
					 							contentType:'application/x-www-form-urlencoded; charset=UTF-8',
					 				 			success: function (data){
					 				 				var url=data.split('||');
					 				 				initImg(url[0]);
						           				}
										    });
										 }
									" />
						</td>
					</tr>
			    </c:if>
			    <c:if test="${action eq 'update'}">
					<tr >  
					<td class="width-15 active" ><label class="pull-right">企业名称：</label></td>
					<td class="width-35" colspan="3">
						<input value="${yhpcd.id1 }" id="_qyid" name="ID1" style="width: 100%;height: 30px;" class="easyui-combobox"
									data-options="editable:false, readonly:true, valueField: 'id',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson'" /></td>
					</tr>
				</c:if>
				
				
				
				<tr >
					<td class="width-15 active"><label class="pull-right">巡查点名称：</label></td>
					<td class="width-35">
						<input name="name"  class="easyui-textbox" value="${yhpcd.name }" style="width: 100%;height: 30px;"
								data-options="required:'true',validType:['length[0,50]']" />
					</td>
					
					<td class="width-15 active"><label class="pull-right">绑定二维码：</label></td>
					<td class="width-35">
						<input name="bindcontent" class="easyui-textbox" value="${yhpcd.bindcontent }" style="width: 100%;height: 30px;" readonly="readonly" data-options="validType:'length[0,100]'" />
					</td>
				</tr>

				<tr >

					<td class="width-15 active"><label class="pull-right">绑定rfid：</label></td>
					<td class="width-35">
						<input name="rfid" class="easyui-textbox" value="${yhpcd.rfid }" style="width: 100%;height: 30px;" data-options="validType:'length[0,25]'" />
					</td>
					<td class="width-15 active" ><label class="pull-right">rfid卡批次代码：</label></td>
					<td class="width-35">
						<input name="area" class="easyui-textbox" value="${yhpcd.area }" style="width: 100%;height: 30px;" data-options="validType:'length[0,25]'" /></td>				
				</tr>
				
				<tr >  
					<td class="width-15 active"><label class="pull-right">是否重大危险源：</label></td>
					<td class="width-35"><input class="easyui-combobox" style="width: 100%;height: 30px;" name="iszdwxy" id="iszdwxy" value="${yhpcd.iszdwxy }"  data-options="required:'true',
								 editable:false,panelHeight:'auto',data: [
								        {value:'1',text:'是'},
								        {value:'0',text:'否'}]"/>
					</td>
				</tr>

				<tr>
					<td class="width-22 active"><label class="pull-right">巡检点坐标：</label></td>
					<td colspan="3" style="height:30px;line-height:30px;">
						<label>经度：</label>
						<input id="lng" name="lng" value="${yhpcd.lng }" class="easyui-textbox" readonly="readonly" style="width:150px;height:30px;"/>
						<label>纬度：</label> 
						<input id="lat" name="lat" value="${yhpcd.lat }" class="easyui-textbox" readonly="readonly" style="width:150px;height:30px;"/>
						<a class="btn btn-primary" onclick="showMapXY( )" style="width:60px;">定位</a>
					</td>
				</tr>

				<tr>
					<td class="width-22 active"><label class="pull-right">平面图坐标：</label></td>
					<td colspan="3" style="height:30px;line-height:30px;">
						<label style="margin-left:19px" >x：</label>
						<input id="bis_map_c_x" name="x" value="${yhpcd.x }" class="easyui-textbox" readonly="readonly" style="width:150px;height:30px;"/>
						<label style="margin-left:19px">y：</label> 
						<input id="bis_map_c_y" name="y" value="${yhpcd.y }" class="easyui-textbox" readonly="readonly" style="width:150px;height:30px;"/>
						<a class="btn btn-primary" onclick="showpmt( )" style="width:60px;">定位</a></td>
				</tr>
				
				<tr>
		          	<td class="width-20" colspan="4" style="text-align:center;">
		          		<label>
			                <a class='btn btn-success btn-xs' onclick='addbdrn()'>添加巡检内容</a>
			                <a class='btn btn-success btn-xs' onclick='delbdrn()'>删除巡检内容</a>
		             	</label>
		            </td>
		       </tr>
				
				<input type="hidden" name="usetype" value="2" />
				<input type="hidden" id="bdnrids" name="bdnrids"/>
				<c:if test="${not empty yhpcd.id}">
					<input type="hidden" name="ID" value="${yhpcd.id}" />
				</c:if>
				</tbody>
			</table>
			
			<div id="bdnr">
               	<table id="fxgk_bdnr_dg" ></table>
            	</div>
 				<div id="select_dlg" style="display:none"  >
		  		<ul id="fxxx-selects-ul" class="icon-selector-ul"></ul>
			</div>
       </form>
       
	<div id="enterprise_dlg" style="width:100%;height:100%; text-align:center;display: none;" >
		<div><span style="color: red;margin: 0 10px;">点击地图标注企业位置!</span>
		<input class="easyui-searchbox" style="width:300px" data-options="prompt:'请输入搜索条件',searcher:function search(value,name){ addmap(value); }" />
		</div>
		<div id="bis_enterprise_dlg_map" style="width:100%;height: 285px;"></div>
	</div>
	
        <div id="xfss_dlg" style="background-color:#F4F4F4;padding:10px 20px;text-align:center;display: none;" >               
            <div class="ftitle" style="color: red;">请在平面图上标注设施位置!</div>
			<div id="xfss_dlg_map" class="wrap" ><img style="width:100% " id="img1" alt=""></img></div>
        </div>	
<script type="text/javascript">
	var usertype=${usertype};
	var locx ='${yhpcd.lng}';
	var locy ='${yhpcd.lat}';
	if(locx==''){//如果风险点没有坐标则打开地图用企业坐标为中心点
		locx = '${lng}';
		locy = '${lat}';
	}
	var yhdid;//隐患点id
	
	//添加
	function addbdrn() {
		if(usertype=='1')
			qyid='${yhpcd.id1}';
		else
		 	qyid=$("#ID1").combobox("getValue");
		if(qyid==null||qyid==""){
			layer.msg("请先选择企业！");
			return;
		}
		var id1='${id1}';
		openDialogView("绑定巡检内容",ctx+"/yhpc/yhpcd/xjnrcreate/"+id1+","+qyid,"800px", "400px","");
	}
	
	//删除
	function delbdrn() {
		var rows = dg.datagrid('getChecked');
		if(rows==null||rows=='') {
			layer.msg("请至少勾选一行记录！",{time: 1000});
			return;
		}
		while(rows.length!=0){
			var row=dg.datagrid("getChecked")[0];
			var rowIndex = dg.datagrid('getRowIndex', row);
			dg.datagrid('deleteRow',rowIndex);  
		}
	}
	
	function initImg(pmtpath){
		$("#img1").attr("src",pmtpath);
	}

	$(function(){ 	
		if('${action}'=="create"){
			yhdid="add";
		}else{
			yhdid='${yhpcd.id}';
		}
	
		dg=$('#fxgk_bdnr_dg').datagrid({    
				method: "post",
			    url:ctx+'/yhpc/yhpcd/xjnrlist/'+yhdid,
			    fit : false,
				fitColumns : true,
				idField : 'ID',
				striped:true,
				pagination:false,
				rownumbers:true,
				nowrap:false,
				pageNumber:1,
				pageSize : 1000,
				pageList : [ 1000, 2000, 3000, 4000, 5000 ],
				striped:true,
			    columns:[[    
			  			{field : 'ID',title : 'id',checkbox : true,width : 50,align : 'center'},  
			  	        {field:'dangerlevel',title:'隐患级别',width:50,
			  	        	formatter : function(value, row, index){
			  	        		if(value=="1") return value='一般';
			  	        		if(value=="2") return value='重大'; 
			  	        	}
			  	        },
			  	        {field:'checktitle',title:'检查单元',width:80 },  
			  	        {field:'content',title:'检查项目',width:150 } 
			    ]],
			    onLoadSuccess: function(){
			    },
			    onLoadError:function(){
			    	layer.open({title: '提示',offset: 'rb',content: '数据加载中，请耐心等待。',shade: 0 ,time: 2000 });
			    },
				checkOnSelect:true,
				selectOnCheck:true,
		});
	
	
		if('${action}'=='update'){
			var pmtpath='${yhpcd.pmt}';
			var url=pmtpath.split('||');
			initImg(url[0]);
		}
		
		if(usertype=='1'&&'${action}'=='create'){
			var pmtpath='${yhpcd.pmt}';
			var url=pmtpath.split('||');
			initImg(url[0]);
		}
			
		$('.wrap').click(function(e){
	        var offset=$('.wrap').offset();
	        var top=e.offsetY+"px";
	        var left=e.offsetX+"px";
	        $('.ball').remove();
	        $('.wrap').append('<span class="ball" style="top:'+top+';left:'+left+'"></span>');
	       	//计算x轴长度比例
	       	wh=$("#img1").width();
			var xp=(e.offsetX/wh).toFixed(4);
	       	//计算y轴长度比例
	       	wh=$("#img1").height();
			var yp=(e.offsetY/wh).toFixed(4);			
	        $("#bis_map_c_x").textbox("setValue",xp);//X坐标
			$("#bis_map_c_y").textbox("setValue",yp);//Y坐标
	    });
	});
	
	function addmap(searchcon){	
		initMap("bis_enterprise_dlg_map",locx,locy,'','',1);
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
	
	//弹出地图界面
	function showMapXY( ){
		layer.open({
		    type: 1,  
		    area: ['500px', '380px'],
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
		    	$("#lng").textbox("setValue", locx);//经度
				$("#lat").textbox("setValue", locy);//纬度
				layer.close(index);
			  },
			  cancel: function(index){ 
		       }
		});
	}

	//弹出平面图界面
	function showpmt(){		
		layer.open({
		    type: 1,  
		    area: ['550px', '380px'],
		    title: '标注坐标',
	        maxmin: true, 
	        shift: 1,
	        shade :0,
		    content: $('#xfss_dlg'),
		    btn: ['确定', '关闭'],
		    success: function(layero, index){
		    	addmap("");
		    },
		    yes: function(index, layero){
		    	layer.close(index);
			  },
			  cancel: function(index){ 
		       }
		})
		if('${action}'=='update')
		{
			init_Map();
		}
	}

	function init_Map(){
		var wh=$("#img1").width();
		var wh2=$("#img1").height();
		var x=$("#bis_map_c_x").val();
		var y=$("#bis_map_c_y").val();

		if(x!="" && y!=""){
	        var top=y*wh2+"px";
	        var left=x*wh+"px";
	        $('.ball').remove();
	        $('.wrap').append('<div class="ball" style="top:'+top+';left:'+left+'"></div>');
		}
	}

	</script>
</body>
</html>