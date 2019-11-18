<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>设备大修项</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	</script>
</head>
<body>
	<form id="inputForm" action="${ctx}/sbssgl/sbgz/${action}"  method="post" class="form-horizontal" >
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			<tbody>
			<c:if test="${type == '2'}">
				<tr>
					<td class="width-20 active"><label class="pull-right">选择企业：</label></td>
					<td class="width-80" colspan="3">
						<input id="qyid" name="qyid" class="easyui-combobox" style="width: 100%;height: 30px;" value="${sbgz.qyid }" 
							data-options="required:'true',editable:false,panelHeight:'150px',valueField:'id',textField:'text',url:'${ctx}/bis/qyjbxx/idjson',
								onSelect: function(row){
									$('#deptid').combobox('setValue', '');
									$('#deptid').combobox('reload', '${ctx}/system/department/deptjson3/'+row.id);
								}" />
					</td>
				</tr> 
			</c:if>
				<tr>
					<td class="width-20 active"><label class="pull-right">部门名称：</label></td>
					<td class="width-30">
					<c:if test="${action eq 'create' }">
						<input name="deptid" id="deptid" class="easyui-combobox" style="width: 100%;height: 30px;" value="${sbgz.deptid }"
							data-options="panelHeight:'150px',valueField: 'id',textField: 'text',url: '${ctx}/system/department/deptjson',
								onSelect: function(row){
									if ('${sbtype}' == '0') {
										$('#sbid').combobox('setValue', '');
										$('#sbid').combobox('reload', '${ctx}/sbssgl/sbgl/sbjson4/'+row.id);
									} else if ('${sbtype}' == '1') {
										$('#sbid').combobox('setValue', '');
										$('#sbid').combobox('reload', '${ctx}/sbssgl/sbgl/tzsbjson4/'+row.id);
									}
								}
							"/>
					</c:if>
					<c:if test="${action eq 'update' }">
						<input name="deptid" id="deptid" class="easyui-combobox" style="width: 100%;height: 30px;" value="${sbgz.deptid }"
							data-options="panelHeight:'150px',valueField: 'id',textField: 'text',url: '${ctx}/system/department/deptjson3/'+${sbgz.qyid },
								onSelect: function(row){
									if ('${sbtype}' == '0') {
										$('#sbid').combobox('setValue', '');
										$('#sbid').combobox('reload', '${ctx}/sbssgl/sbgl/sbjson4/'+row.id);
									} else if ('${sbtype}' == '1') {
										$('#sbid').combobox('setValue', '');
										$('#sbid').combobox('reload', '${ctx}/sbssgl/sbgl/tzsbjson4/'+row.id);
									}
									
								}
							"/>
					</c:if>	
					</td>
				   	<td class="width-20 active"><label class="pull-right">报修日期：</label></td>
					<td class="width-30">
						<input id="m1" name="m1" class="easyui-datebox" style="width: 100%;height: 30px;" value="${sbgz.m1 }" data-options="editable:false" />
				   	</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">报修单编号：</label></td>
					<td class="width-30">
						<input id="m2" name="m2" class="easyui-textbox" style="width: 100%;height: 30px;" value="${sbgz.m2 }" data-options="validType:'length[0,50]'" />
				   	</td>
					<c:if test="${sbtype eq '0'}">
					   	<td class="width-20 active"><label class="pull-right">设备名称：</label></td>
						<td class="width-30">
							<input id="sbid" name="sbid" class="easyui-combobox" style="width: 100%;height: 30px;" value="${sbgz.sbid }"
								data-options="panelHeight:'150px',valueField:'id',textField:'text',url:'${ctx}/sbssgl/sbgl/sbjson/',
									onSelect: function(row){
										$.ajax({
											type: 'POST',
											url: '${ctx}/sbssgl/sbgl/sbinfojson/'+row.id,
											success: function(data){
												var sdata = JSON.parse(data);
												$('#m3').textbox('setValue', sdata.m27);
												$('#m4').textbox('setValue', sdata.m1);
											}
										});
									}
								" />
					   	</td>
					</c:if>
					<c:if test="${sbtype eq '1'}">
					   	<td class="width-20 active"><label class="pull-right">设备名称：</label></td>
						<td class="width-30">
							<input id="sbid" name="sbid" class="easyui-combobox" style="width: 100%;height: 30px;" value="${sbgz.sbid }" 
								data-options="panelHeight:'150px',valueField:'id',textField:'text',url:'${ctx}/sbssgl/sbgl/tzsbjson/',
									onSelect: function(row){
										$.ajax({
											type: 'POST',
											url: '${ctx}/sbssgl/sbgl/sbinfojson/'+row.id,
											success: function(data){
												var sdata = JSON.parse(data);
												$('#m3').textbox('setValue', sdata.m27);
												$('#m4').textbox('setValue', sdata.m1);
											}
										});
									}
								" />
					   	</td>
					</c:if>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">设备型号：</label></td>
					<td class="width-30">
						<input id="m3" name="m3" class="easyui-textbox" style="width: 100%;height: 30px;" value="${sbgz.m3 }" data-options="validType:'length[0,25]'" />
				   	</td>
					<td class="width-20 active"><label class="pull-right">设备编号：</label></td>
					<td class="width-30">
						<input id="m4" name="m4" class="easyui-textbox" style="width: 100%;height: 30px;" value="${sbgz.m4 }" data-options="validType:'length[0,25]'" />
				   	</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">故障现象：</label></td>
					<td class="width-80" colspan="3">
						<input id="m5" name="m5" class="easyui-textbox" style="width: 100%;height: 50px;" value="${sbgz.m5 }" data-options="multiline: true,validType:'length[0,100]'" />
				   	</td>
				</tr>
				<tr>
				   	<td class="width-20 active"><label class="pull-right">原因分析：</label></td>
					<td class="width-30" colspan="3">
						<input id="m6" name="m6" class="easyui-textbox" style="width: 100%;height: 50px;" value="${sbgz.m6 }" data-options="multiline: true,validType:'length[0,100]'" />
				   	</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">采取对策：</label></td>
					<td class="width-80" colspan="3">
						<input id="m7" name="m7" class="easyui-textbox" style="width: 100%;height: 50px;" value="${sbgz.m7 }" data-options="multiline: true,validType:'length[0,100]'" />
				   	</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">维修开始时间：</label></td>
					<td class="width-30">
						<input id="m9" name="m9" class="easyui-datetimebox" style="width: 100%;height: 30px;" value="${sbgz.m9 }" data-options="required:'true', editable:false" />
				   	</td>
				   	<td class="width-20 active"><label class="pull-right">维修结束时间：</label></td>
					<td class="width-30">
						<input id="m10" name="m10" class="easyui-datetimebox" style="width: 100%;height: 30px;" value="${sbgz.m10 }" data-options="required:'true', editable:false" />
				   	</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">上传维修需求单：</label></td>
					<td class="width-80" colspan="3">
						<input type="hidden" id="m14" name="m14" value="" />
						<div id="uploader_ws2">
						    <div id="fileList2" class="uploader-list" ></div>
						    <div id="insertfilebt2">选择文件</div>
						</div>
					</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">上传维修报告：</label></td>
					<td class="width-80" colspan="3">
						<input type="hidden" id="m8" name="m8" value="" />
						<div id="uploader_ws">
						    <div id="fileList" class="uploader-list" ></div>
						    <div id="insertfilebt">选择文件</div>
						</div>
					</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">维修停机时间（H）：</label></td>
					<td class="width-30">
						<input id="m11" name="m11" class="easyui-textbox" style="width: 100%;height: 30px;" value="${sbgz.m11 }" data-options="validType:'length[0,50]'" />
				   	</td>
				   	<td class="width-20 active"><label class="pull-right">更滑零部件记录：</label></td>
					<td class="width-30">
						<input id="m12" name="m12" class="easyui-textbox" style="width: 100%;height: 30px;" value="${sbgz.m12 }" data-options="validType:'length[0,50]'" />
				   	</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">备注：</label></td>
					<td class="width-80" colspan="3">
						<input id="m13" name="m13" class="easyui-textbox" style="width: 100%;height: 80px;" value="${sbgz.m13 }" data-options="multiline: true,validType:'length[0,100]'" />
					</td>
				</tr>
			</tbody>
		</table>	
			<input type="hidden" name="type" value="${type}" /><!-- 用户类型 -->
		<c:if test="${not empty sbgz.ID}">
			<input type="hidden" name="ID" value="${sbgz.ID}" />
			<input type="hidden" name="qyid" value="${sbgz.qyid}" />
			<input type="hidden" name="S1" value="<fmt:formatDate value="${sbgz.s1}" pattern="yyyy-MM-dd HH:mm:ss"/>" />
			<input type="hidden" name="S3" value="${sbgz.s3}" />
		</c:if>			
	</form>
<script type="text/javascript">
var index = parent.layer.getFrameIndex(window.name); //获取窗口索引

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
		$('#m8').val( newurl );
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
		var $li = $(
	            "<div id=\"" + file.id + "\" style=\"margin-bottom: 10px;\">" +
	            	"<a style=\"color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;\" onclick=\"window.open('"+res.url+"')\">"+res.fileName+"</a>"+
	            	"<span class=\"ss\" onClick=\"removeFile('"+file.id+"')\" style=\"cursor: pointer\">删除</span>"+
	            "</div>"
	            );		    
	    $list2.html( $li );
		var newurl=res.url+"||"+res.fileName;
		$('#m14').val( newurl );
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

//验证下拉框的值是否有效
function checkcombobox(id){
	var options = $("#"+id).combobox('options');  
 	var data = $("#"+id).combobox('getData');		/* 下拉框所有选项 */  
 	var value = $("#"+id).combobox('getValue');	/* 用户输入的值 */  
 	var b = false;		/* 标识是否在下拉列表中找到了用户输入的字符 */  
 	for (var i = 0; i < data.length; i++) {  
    	if (data[i][options.valueField] == value) {  
        	b=true;  
       	 	break;  
    	}  
	}
 	return b;
}

function doSubmit(){
	if(!checkcombobox('sbid')){
		layer.open({title: '提示',offset: 'auto',content: '所选设备不存在！',shade: 0 ,time: 2000 });
		return;
	}
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
		var fjUrl = '${sbgz.m8}';
		if(fjUrl!=null&&fjUrl!=''){
			arry =fjUrl.split(",");
			for(var i=0;i<arry.length;i++){
				var arry2 =arry[i].split("||");
				var id="ws_fj_"+i;
				var $li = $(
			            "<div id=\"" + id + "\" style=\"margin-bottom: 10px;\">" +
			            	"<a style=\"color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;\" onclick=\"window.open('"+arry2[0]+"')\">"+arry2[1]+"</a>"+
			            "</div>"
			            );
			    $list.html( $li );
			    
			    $("#m8").val(arry[i]);
			}
		}
		var fjUrl2 = '${sbgz.m14}';
		if(fjUrl2!=null&&fjUrl2!=''){
			arry =fjUrl2.split(",");
			for(var i=0;i<arry.length;i++){
				var arry2 =arry[i].split("||");
				var id="ws_fj_"+i;
				var $li = $(
			            "<div id=\"" + id + "\" style=\"margin-bottom: 10px;\">" +
			            	"<a style=\"color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;\" onclick=\"window.open('"+arry2[0]+"')\">"+arry2[1]+"</a>"+
			            "</div>"
			            );
			    $list2.html( $li );
			    
			    $("#m14").val(arry[i]);
			}
		}
	}
});

</script>
</body>
</html>
