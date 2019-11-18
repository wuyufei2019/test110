<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>设备更新计划</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	</script>
</head>
<body>
	<form id="inputForm" action="${ctx}/sbssgl/sbgx/${action}"  method="post" class="form-horizontal" >
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			<tbody>
			<c:if test="${type == '2'}">
				<tr>
					<td class="width-20 active"><label class="pull-right">选择企业：</label></td>
					<td class="width-80" colspan="3">
						<input id="qyid" name="qyid" class="easyui-combobox" style="width: 100%;height: 30px;" value="${sbgx.qyid }" 
							data-options="required:'true',editable:false,panelHeight:'150px',valueField:'id',textField:'text',url:'${ctx}/bis/qyjbxx/idjson',
								onSelect: function(row){
									$('#m4').combobox('setValue', '');
									$('#m4').combobox('reload', '${ctx}/system/department/deptjson3/'+row.id);
								}
							" />
					</td>
				</tr> 
			</c:if>
				<tr>
				   	<td class="width-20 active"><label class="pull-right">工作令号：</label></td>
					<td class="width-30">
						<input id="m1" name="m1" class="easyui-textbox" style="width: 100%;height: 30px;" value="${sbgx.m1 }" data-options="validType:'length[0,50]'" />
				   	</td>
				   	<td class="width-20 active"><label class="pull-right">设备名称：</label></td>
					<td class="width-30">
						<input id="m2" name="m2" class="easyui-textbox" style="width: 100%;height: 30px;" value="${sbgx.m2 }" data-options="validType:'length[0,50]'"/>
				   	</td>
				</tr>
				<tr>
				   	<td class="width-20 active"><label class="pull-right">规格型号：</label></td>
					<td class="width-30">
						<input id="m3" name="m3" class="easyui-textbox" style="width: 100%;height: 30px;" value="${sbgx.m3 }" data-options="validType:'length[0,50]'" />
				   	</td>
				   	<td class="width-20 active"><label class="pull-right">使用单位：</label></td>
					<td class="width-30">
						<input id="m4" name="m4" class="easyui-combobox" style="width: 100%;height: 30px;" value="${sbgx.m4 }" 
							data-options="panelHeight:'100px',valueField:'id',textField:'text',url: '${ctx}/system/department/deptjson'" />
				   	</td>
				</tr>
				<tr>
				   	<td class="width-20 active"><label class="pull-right">单价（万元）：</label></td>
					<td class="width-30">
						<input id="m5" name="m5" class="easyui-numberbox" style="width: 100%;height: 30px;" value="${sbgx.m5 }" data-options="required:'true',validType:'length[0,10]'" />
				   	</td>
				   		<td class="width-20 active"><label class="pull-right">数量（台）：</label></td>
					<td class="width-30">
						<input id="m6" name="m6" class="easyui-numberbox" style="width: 100%;height: 30px;" value="${sbgx.m6 }" data-options="required:'true',validType:'length[0,10]',
							onChange: function(newValue,oldValue){
								var dj = $('#m5').numberbox('getValue');
								$('#m7').numberbox('setValue', dj*newValue);
							}
						" />
				   	</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">合计（万元）：</label></td>
					<td class="width-30">
						<input id="m7" name="m7" class="easyui-numberbox" style="width: 100%;height: 30px;" value="${sbgx.m7 }" data-options="validType:'length[0,10]'" />
				   	</td>
					<td class="width-20 active"><label class="pull-right">设备更新类别：</label></td>
					<td class="width-30">
						<input id="m8" name="m8" class="easyui-combobox" style="width: 100%;height: 30px;" value="${sbgx.m8 }" 
							data-options="editable:false, panelHeight: 'auto', data: [{'value': '0', 'text': '替换'},{'value': '1', 'text': '新增'}],
								onSelect: function(row){
									if (row.value == '0') {
										$('#th').show();
										$('#xz').hide();
									} else {
										$('#xz').show();
										$('#th').hide();
									}
								}
							" />
				   	</td>
				</tr>
				<tr id="th">
					<td class="width-20 active"><label class="pull-right">设备替换原因描述：</label></td>
					<td class="width-80" colspan="3">
						<input id="m9" name="m9" class="easyui-textbox" style="width: 100%;height: 50px;" value="${sbgx.m9 }" data-options="multiline: true,validType:'length[0,100]'" />
				   	</td>
				</tr>
				<tr id="xz">
					<td class="width-20 active"><label class="pull-right">设备新增原因描述：</label></td>
					<td class="width-80" colspan="3">
						<input id="m10" name="m10" class="easyui-textbox" style="width: 100%;height: 50px;" value="${sbgx.m10 }" data-options="multiline: true,validType:'length[0,250]'" />
					</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">设备更新后作用描述：</label></td>
					<td class="width-80" colspan="3">
						<input id="m11" name="m11" class="easyui-textbox" style="width: 100%;height: 50px;" value="${sbgx.m11 }" data-options="multiline: true,validType:'length[0,100]'" />
					</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">是否完成：</label></td>
					<td class="width-30">
						<input id="m12" name="m12" class="easyui-combobox" style="width: 100%;height: 30px;" value="${sbgx.m12 }" 
							data-options="required:'true', editable:false, panelHeight: 'auto', data: [{'value': '0', 'text': '未完成'},{'value': '1', 'text': '已完成'}]" />
				   	</td>
				   	<td class="width-20 active"><label class="pull-right">年度：</label></td>
					<td class="width-30">
						<input id="m14" name="m14" class="easyui-combobox" style="width: 100%;height: 30px;" value="${sbgx.m14 }" 
							data-options="required:'true', editable:false, panelHeight: '150px'" />
				   	</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">上传验收报告：</label></td>
					<td class="width-80" colspan="3">
						<div id="uploader_ws">
						    <div id="fileList" class="uploader-list" ></div>
						    <div id="insertfilebt">选择文件</div>
						</div>
					</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">上传合同附件：</label></td>
					<td class="width-80" colspan="3">
						<div id="uploader_ws2">
						    <div id="fileList2" class="uploader-list" ></div>
						    <div id="insertfilebt2">选择文件</div>
						</div>
					</td>
				</tr>
			</tbody>
		</table>	
			<input type="hidden" name="type" value="${type}" /><!-- 用户类型 -->
			<input type="hidden" name="sbtype" value="${sbtype}" /><!-- 设备类型 -->
		<c:if test="${not empty sbgx.ID}">
			<input type="hidden" name="ID" value="${sbgx.ID}" />
			<input type="hidden" name="qyid" value="${sbgx.qyid}" />
			<input type="hidden" name="S1" value="<fmt:formatDate value="${sbgx.s1}" pattern="yyyy-MM-dd HH:mm:ss"/>" />
			<input type="hidden" name="S3" value="${sbgx.s3}" />
		</c:if>			
	</form>
<script type="text/javascript">
var index = parent.layer.getFrameIndex(window.name); //获取窗口索引

$(function() {
	if ('${action}' == 'create') {
		$('#th').hide();
		$('#xz').hide();
	} else if ('${action}' == 'update') {
		if ('${sbgx.m8 }' == '0') {
			$('#th').show();
			$('#xz').hide();
		} else if ('${sbgx.m8 }' == '1') {
			$('#xz').show();
			$('#th').hide();
		}
	}
	
	if ('${type}' == '2' && '${action}' == 'update') {
		$('#m4').combobox('reload', '${ctx}/system/department/deptjson3/'+'${sbgx.qyid}');
	}
	
	var currentYear = new Date().getFullYear();
	var data = [];
	for (var i = (currentYear - 3); i < (currentYear + 3); i++) {
		data.push({value: i, text: i});
	}
	$('#m14').combobox('loadData', data);
	
});

var $ = jQuery,
$list = $('#fileList'); //文件上传
$list2 = $('#fileList2'); //文件上传

var fileuploader = WebUploader.create({
   auto: true,
   swf:  '${ctxStatic}/webuploader-0.1.5/Uploader.swf',
   server: '${ctx}/kindeditor/upload?dir=file',		
   pick: {
	   	id:'#insertfilebt',
	   	multiple : false,
   },
   duplicate :true  
});

fileuploader.on( 'uploadProgress', function( file, percentage ) {
	$.jBox.tip("正在上传，请稍等...",'loading',{opacity:0});
});

// 文件上传成功 
fileuploader.on( 'uploadSuccess', function( file ,res) {
	$.jBox.closeTip();
	if(res.error==0){
		var $li = $(
	            "<div id=\"" + file.id + "\" style=\"margin-bottom: 10px;\">" +
	            	"<a style=\"color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;\" onclick=\"window.open('"+res.url+"')\">"+res.fileName+"</a>"+
	            	"<span class=\"ss\" onClick=\"removeFile('"+file.id+"')\" style=\"cursor: pointer\">删除</span>"+
	            "</div>"
	            );		    
		
		$list.html( $li );
 
		var newurl=res.url+"||"+res.fileName;
		
		$("[name='m13']").remove();
		
		var $input = $('<input id="input_'+file.id+'" type="hidden" name="m13" value="'+newurl+'"/>');
		
		$('#uploader_ws').append( $input );
		
		imgflag = true;
	}else{
		layer.msg(res.message,{time: 3000});
	}
});

var fileuploader2 = WebUploader.create({
   auto: true,
   swf:  '${ctxStatic}/webuploader-0.1.5/Uploader.swf',
   server: '${ctx}/kindeditor/upload?dir=file',		
   pick: {
	   	id:'#insertfilebt2',
	   	multiple : false,
   },
   duplicate :true  
});

fileuploader2.on( 'uploadProgress', function( file, percentage ) {
	$.jBox.tip("正在上传，请稍等...",'loading',{opacity:0});
});

// 文件上传成功 
fileuploader2.on( 'uploadSuccess', function( file ,res) {
	$.jBox.closeTip();
	if(res.error==0){
		var $li2 = $(
	            "<div id=\"" + file.id + "\" style=\"margin-bottom: 10px;\">" +
	            	"<a style=\"color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;\" onclick=\"window.open('"+res.url+"')\">"+res.fileName+"</a>"+
	            	"<span class=\"ss\" onClick=\"removeFile('"+file.id+"')\" style=\"cursor: pointer\">删除</span>"+
	            "</div>"
	            );		    
		
		$list2.html( $li2 );
 
		var newurl=res.url+"||"+res.fileName;
		
		$("[name='m15']").remove();
		
		var $input = $('<input id="input_'+file.id+'" type="hidden" name="m15" value="'+newurl+'"/>');
		
		$('#uploader_ws2').append( $input );
		
		imgflag = true;
	}else{
		layer.msg(res.message,{time: 3000});
	}
});

// 负责预览图的销毁
function removeFile(fileid) {
	$("#"+fileid).remove();
	$("#input_"+fileid).remove();
};
	
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
	
	if('${action}'=='update'){
		var fjUrl = '${sbgx.m13}';
		if(fjUrl!=null&&fjUrl!=''){
			arry =fjUrl.split(",");
			for(var i=0;i<arry.length;i++){
				var arry2 =arry[i].split("||");
				var id="ws_fj_"+i;
				var $li = $(
			            "<div id=\"" + id + "\" style=\"margin-bottom: 10px;\">" +
			            	"<a style=\"color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;\" onclick=\"window.open('"+arry2[0]+"')\">"+arry2[1]+"</a>"+
			            	"<span class=\"ss\" onClick=\"removeFile('"+id+"')\" style=\"cursor: pointer\">删除</span>"+
			            "</div>"
			            );
				$list.html( $li );
			    $("[name='m13']").remove();
			    var $input = $('<input id="input_'+id+'" type="hidden" name="m13" value="'+arry[i]+'"/>');
				$('#uploader_ws').append( $input );
			}
		}
		
		var fjUrl2 = '${sbgx.m15}';
		if(fjUrl2!=null&&fjUrl2!=''){
			arry =fjUrl2.split(",");
			for(var i=0;i<arry.length;i++){
				var arry2 =arry[i].split("||");
				var id="ws_fj_"+i;
				var $li = $(
			            "<div id=\"" + id + "\" style=\"margin-bottom: 10px;\">" +
			            	"<a style=\"color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;\" onclick=\"window.open('"+arry2[0]+"')\">"+arry2[1]+"</a>"+
			            	"<span class=\"ss\" onClick=\"removeFile('"+id+"')\" style=\"cursor: pointer\">删除</span>"+
			            "</div>"
			            );
				$list2.html( $li );
			    $("[name='m15']").remove();
			    var $input = $('<input id="input_'+id+'" type="hidden" name="m15" value="'+arry[i]+'"/>');
				$('#uploader_ws2').append( $input );
			}
		}
	}
});
</script>
</body>
</html>
