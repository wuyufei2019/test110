<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>上传年度报表</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	</script>
</head>
<body>
	<form id="inputForm" action="${ctx}/sbssgl/sbgx/${action}"  method="post" class="form-horizontal" >
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			<tbody>
				<tr>
					<td class="width-20 active"><label class="pull-right">选择企业：</label></td>
					<td class="width-80" colspan="3">
						<input id="qyid" name="qyid" class="easyui-combobox" style="width: 100%;height: 30px;" value="${sbgx.qyid }" 
							data-options="required:'true',editable:false,panelHeight:'150px',valueField:'id',textField:'text',url:'${ctx}/bis/qyjbxx/idjson',
								onSelect: function(row){
									$('#deptid').combobox('setValue', '');
									$('#deptid').combobox('reload', '${ctx}/system/department/deptjson3/'+row.id);
								}" />
					</td>
				</tr> 
				<tr>
					<td class="width-20 active"><label class="pull-right">年度：</label></td>
					<td class="width-30">
						<input id="m1" name="m1" class="easyui-combobox" style="width: 100%;height: 30px;" value="${ndbb.m1 }" 
							data-options="required:'true', editable:false, panelHeight: '150px'" />
				   	</td>
				   	<td class="width-20 active"><label class="pull-right">部门名称：</label></td>
					<td class="width-30">
						<input id="deptid" name="deptid" class="easyui-combobox" style="width: 100%;height: 30px;" value="${ndbb.deptid }" 
							data-options="editable:false,panelHeight:'100px',valueField:'id',textField:'text',url: '${ctx}/system/department/deptjson'" />
				   	</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">标题：</label></td>
					<td class="width-30" colspan="3">
						<input id="m2" name="m2" class="easyui-textbox" style="width: 100%;height: 30px;" value="${ndbb.m2 }" data-options="validType:'length[0,50]'" />
				   	</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">上传附件：</label></td>
					<td class="width-80" colspan="3">
						<div id="uploader_ws">
						    <div id="fileList" class="uploader-list" ></div>
						    <div id="insertfilebt">选择文件</div>
						</div>
					</td>
				</tr>
			</tbody>
		</table>
	</form>

<script type="text/javascript">
var index = parent.layer.getFrameIndex(window.name); //获取窗口索引	

$(function(){
	var curYear = new Date().getFullYear();
		var data = [];
		for (var  i = (curYear - 3); i < (curYear + 3); i++) {
			data.push({value: i, text: i});
		}
		$("#m1").combobox('loadData', data);
})
var $ = jQuery,
$list = $('#fileList'); //文件上传

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
		
		$("[name='m3']").remove();
		
		var $input = $('<input id="input_'+file.id+'" type="hidden" name="m3" value="'+newurl+'"/>');
		
		$('#uploader_ws').append( $input );
		
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
	if ($('#deptid').val() != '' && $('#deptid').val() != null) {//当用户选择设备编号后，将部门编号放入id为deptid隐藏域中，如果用户并未修改部门编号，保存时，将值取出来赋给m5
		$("#m5").combobox('setValue', $('#deptid').val());
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
});
	
</script>
</body>
</html>
