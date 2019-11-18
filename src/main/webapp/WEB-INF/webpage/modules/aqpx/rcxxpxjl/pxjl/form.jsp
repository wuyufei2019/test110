<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>日常线下培训记录</title>
	<meta name="decorator" content="default"/>
</head>
<body>

     <form id="inputForm" action="${ctx}/aqpx/rcxxpxjl/pxjl/${action}"  method="post" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
			  	<tr>
					<td class="width-15 active"><label class="pull-right">培训类别：</label></td>
					<td class="width-85" colspan="3">
					<input id="M1_4" name="M1_4" type="text" class="easyui-textbox"  value="${pxjl.m1_4 }" style="width: 100%;height: 30px;" data-options="required:'true',panelHeight:'150'">
					</td>					
				</tr>

				<tr>
					<td class="width-15 active"><label class="pull-right">姓名：</label></td>
					<td class="width-35"><input name="M1_1" style="width: 100%;height: 30px;" class="easyui-combogrid"
								value="${pxjl.m1_1}"
								data-options="required:'true',panelWidth:350,fitColumns : true,editable:false ,idField:'text',textField: 'text',url:'${ctx}/bis/ygxx/ygxxlist/${pxjl.ID1}',
								columns:[[
										   {field:'text',title:'姓名',width:100},
										   {field:'sex',title:'性别',width:60},
										   {field:'idcard',title:'身份证号',width:200},
										   {field:'gw',title:'岗位',width:100}
										   ]],
								onClickRow:function(rowIndex,rowData){
									$('#M1_3').textbox('clear');
									$('#M1_3').textbox('setValue',rowData.gw);
								}"			   
					 /></td>
					<td class="width-15 active"><label class="pull-right">工作岗位：</label></td>
					<td class="width-35"><input id="M1_3" name="M1_3" class="easyui-textbox" value="${pxjl.m1_3 }" style="width: 100%;height: 30px;"
								data-options="editable:true"/></td>
				</tr>

				<tr>
					
					<td class="width-15 active"><label class="pull-right">培训时间：</label></td>
					<td class="width-35"><input name="M2" class="easyui-datebox"
								value="${pxjl.m2 }" style="width: 100%;height: 30px;" data-options="required:'true'" /></td>
								<td class="width-15 active"><label class="pull-right">培训人员：</label></td>
					<td class="width-35"><input name="M4" class="easyui-textbox" value="${pxjl.m4 }" style="width: 100%;height: 30px;"
								data-options=""/></td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">考试成绩：</label></td>
					<td class="width-35"><input name="M7" class="easyui-numberbox" value="${pxjl.m7 }" style="width: 100%;height: 30px;"
								data-options="min:0,max:100 " /></td>
					<td class="width-15 active"><label class="pull-right">培训结果：</label></td>
					<td class="width-35"><input name="M8" class="easyui-combobox"
						value="${pxjl.m8 }" style="width: 100%;height: 30px;" data-options="panelHeight:'auto', 
								editable:false, data: [
										{value:'合格',text:'合格'},
								        {value:'不合格',text:'不合格'} ]" /></td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">培训内容：</label></td>
					<td class="width-35" colspan="3">
						<input name="M3" class="easyui-textbox" value="${pxjl.m3 }" style="width: 100%;height: 30px;"
								data-options="" />
					</td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">备注：</label></td>
					<td class="width-35" colspan="3">
						<input name="M9" class="easyui-textbox" value="${pxjl.m9 }" style="width: 100%;height: 30px;"
								data-options="" />
					</td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">附件：</label></td>
					<td class="width-35" colspan="3">
						<div id="uploader_ws_m10">
					    <div id="m10fileList" class="uploader-list" ></div>
					    <div id="filePicker">选择文件</div>
					</div> 
					</td>
				</tr>
				
				<c:if test="${not empty pxjl.ID}">
					<input type="hidden" name="ID" value="${pxjl.ID}" />
					<input type="hidden" name="ID1" value="${pxjl.ID1}" />
					<input type="hidden" name="S1"
						value="<fmt:formatDate value="${pxjl.s1}" pattern="yyyy-MM-dd HH:mm:ss"  />" />
					<input type="hidden" name="S3" value="${pxjl.s3}" />
					<input type="hidden" name="state" value="${pxjl.state}" />
				</c:if>
			</table>

		  	<tbody>
       </form>
 
<script type="text/javascript">
var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
var validateForm;	

function doSubmit(){
	$("#inputForm").submit(); 
}

$(function(){
	$('#inputForm').form({    
	    onSubmit: function(){    
	    	var isValid = $(this).form('validate');
			return isValid;	// 返回false终止表单提交
	    },    
	    success:function(data){ 
	    	if(data=='success')
	    		parent.layer.open({icon:1,title: '提示',offset: 'rb',content: '操作成功！',shade: 0 ,time: 2000 });
	    	else if(data=='isexist')
	    		parent.layer.open({icon:2,title: '提示',offset: 'rb',content: '操作失败！该员工三级教育培训记录已存在！',shade: 0 ,time: 2000 });
	    	else
	    		parent.layer.open({icon:2,title: '提示',offset: 'rb',content: '操作失败！',shade: 0 ,time: 2000 });
	    	parent.dg.datagrid('reload');
	    	parent.layer.close(index);//关闭对话框。
	    }    
	});

});	

	$list2 = $('#m10fileList'); //文件上传
	
	    // 负责预览图的销毁
    function removeFile(fileid) {
    	$("#"+fileid).remove();
    	$("#input_"+fileid).remove();
    };
	
	var fileuploader = WebUploader.create({
	    auto: true,
	    swf:  '${ctxStatic}/webuploader-0.1.5/Uploader.swf',
	    server: '${ctx}/kindeditor/upload',
	    pick: {
	    	id:'#filePicker',
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
	
		    $list2.append( $li );
	 
			var newurl=res.url+"||"+res.fileName;
			var $input = $('<input id="input_'+file.id+'" type="hidden" name="M10" value="'+newurl+'"/>');
			
			$('#uploader_ws_m10').append( $input );
		}else{
			layer.msg(res.message,{time: 3000});
		}
	});
	
	if('${action}' == 'update'){
		var fjUrl = '${pxjl.m10}';
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
			    $list2.append( $li );
			    var $input = $('<input id="input_'+id+'" type="hidden" name="M10" value="'+arry[i]+'"/>');
				
				$('#uploader_ws_m10').append( $input );
			}
		}
	}
</script>
</body>
</html>