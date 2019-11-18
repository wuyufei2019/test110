<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>演练计划管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=OTVG2uzqudiovwoHlFIs0P8Z3T7ID4K2"></script>
</head>
<body>

     <form id="inputForm" action="${ctx}/erm/yljh/${action}"  method="post" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
			  	<c:if test="${usertype != '1' and action eq 'create'}">
					<tr>
						<td class="width-15 active"><label class="pull-right">企业名称：</label></td>
						<td class="width-35" colspan="3">
							<input value="${res.qyid }" id="_qyid" name="qyid" style="width: 100%;height: 30px;"
									class="easyui-combobox"
									data-options="required:'true',valueField: 'id',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson'" />
						</td>
					</tr>
			    </c:if>
			    <c:if test="${usertype != '1' and action eq 'update'}">
					<tr>  
						<td class="width-15 active" ><label class="pull-right">企业名称：</label></td>
						<td class="width-35" colspan="3">
							<input value="${res.qyid }" id="_qyid" name="qyid" style="width: 100%;height: 30px;"
										class="easyui-combobox"
										data-options="editable:false, disabled:true, valueField: 'id',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson'" /></td>
					</tr>
				</c:if>


				<tr >
					<td class="width-15 active"><label class="pull-right">年度：</label></td>
					<td class="width-35" colspan="3">
						<input style="width: 100%;height: 30px;" id="M1" name="M1" class="easyui-textbox" value="${res.m1 }" data-options="validType:'length[0,50]'"/>
					</td>
				</tr>

				<tr>
					<td class="width-15 active"><label class="pull-right">层级：</label></td>
					<td class="width-35"><input style="width: 100%;height: 30px;" name="M2" class="easyui-combobox" value="${res.m2 }" data-options="panelHeight:'75px', data: [
								        {value:'公司级',text:'公司级'},
								        {value:'部门级',text:'部门级'},
								        {value:'班组级',text:'班组级'}]"/></td>
					<td class="width-15 active"><label class="pull-right">部门：</label></td>
					<td class="width-35"><input style="width: 100%;height: 30px;"id="M3" name="M3" class="easyui-combobox" value="${res.m3 }" data-options="required:true,editable : false ,panelHeight:100,valueField:'text', textField:'text',url: '${ctx}/system/department/deptjson' "/></td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">演练主题：</label></td>
					<td class="width-35" colspan="3">
						<input style="width: 100%;height: 30px;" id="M4" name="M4" class="easyui-textbox" value="${res.m4 }" data-options="validType:'length[0,50]'"/>
					</td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">参演部门：</label></td>
					<td class="width-35"><input style="width: 100%;height: 30px;"" name="M5" class="easyui-combobox " value="${res.m5 }" data-options="multiple:true,required:true,editable : false ,panelHeight:100,valueField:'text', textField:'text',url: '${ctx}/system/department/deptjson' "/></td>
					<td class="width-15 active"><label class="pull-right">执行人：</label></td>
					<td class="width-35"><input style="width: 100%;height: 30px;"" name="M6" class="easyui-textbox" value="${res.m6 }" data-options="validType:'length[0,50]'"/></td>
				</tr>

				<tr>
					<td class="width-15 active"><label class="pull-right">附件：</label></td>
					<td class="width-35" colspan="3">
						<div id="uploader_ws_m8">
							<div id="m8fileList" class="uploader-list"></div>
							<div id="filePicker">选择文件</div>
						</div>
					</td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">完成情况：</label></td>
					<td class="width-35" colspan="3"><input id="M7" name="M7" class="easyui-textbox" style="width: 100%;height: 30px;" value="${res.m7 }" data-options=" editable:true " /></td>
				</tr>
				
				<c:if test="${not empty res.ID}">
					<input type="hidden" name="ID" value="${res.ID}" />
					<input type="hidden" name="ID1" value="${res.ID1}" />
					<input type="hidden" name="qyid" value="${res.qyid}" />
					<input type="hidden" name="S1"
						value="<fmt:formatDate value="${res.s1}" pattern="yyyy-MM-dd HH:mm:ss"  />" />
					<input type="hidden" name="S3" value="${res.s3}" />
				</c:if>
				
				<c:if test="${action eq 'create'}">
					<input type="hidden" name="ID1" value="0" />
				</c:if>
			</table>

		  	<tbody>
       </form>
 
<script type="text/javascript">
	var usertype=${usertype};
	var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
	var $ = jQuery, $list2 = $('#m8fileList'); //文件上传
	var validateForm;	

	var fileuploader = WebUploader.create({

		auto : true,

		swf : '${ctxStatic}/webuploader-0.1.5/Uploader.swf',

		server : '${ctx}/kindeditor/upload?dir=file',

		pick : {
			id : '#filePicker',
			multiple : false,
		},
		duplicate : true
	});

	// 文件上传成功 
	fileuploader.on('uploadSuccess',
				function(file, sfr) {
					if (sfr.error == 0) {
						var $li = $("<div id=\"" + file.id + "\" style=\"margin-bottom: 10px;\">"
								+ "<a style=\"color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;\" onclick=\"window.open('"
								+ sfr.url
								+ "')\">"
								+ sfr.fileName
								+ "</a>"
								+ "<span class=\"ss\" onClick=\"removeFile('"
								+ file.id
								+ "')\" style=\"cursor: pointer\">删除</span>"
								+ "</div>");

						$list2.append($li);

						var newurl = sfr.url + "||" + sfr.fileName;

						var $input = $('<input id="input_'+file.id+'" type="hidden" name="M8" value="'+newurl+'"/>');

						$('#uploader_ws_m8').append($input);
					} else {
						layer.msg(sfr.message, {
							time : 3000
						});
					}
				});
	
	// 负责预览图的销毁
	function removeFile(fileid) {
		$("#" + fileid).remove();
		$("#input_" + fileid).remove();
	};

		if ('${action}' == 'update') {
			var fjUrl = '${res.m8}';
			if (fjUrl != null && fjUrl != '' && fjUrl != 'null') {
				var arry = fjUrl.split(",");
				for (var i = 0; i < arry.length; i++) {
					var arry2 = arry[i].split("||");
					var id = "ws_fj_" + i;
					var $li = $("<div id=\"" + id + "\" style=\"margin-bottom: 10px;\">"
							+ "<a style=\"color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;\" onclick=\"window.open('"
							+ arry2[0]
							+ "')\">"
							+ arry2[1]
							+ "</a>"
							+ "<span class=\"ss\" onClick=\"removeFile('"
							+ id
							+ "')\" style=\"cursor: pointer\">删除</span>"
							+ "</div>");
					$list2.append($li);

					var $input = $('<input id="input_'+id+'" type="hidden" name="M8" value="'+arry[i]+'"/>');
					$('#uploader_ws_m8').append($input);
				}
			}
		}

//年份下拉框初始化
$("#M1").combobox({ 
	valueField:'year',    
	textField:'year',  
	panelHeight:'auto'
});

var data = [];
var thisYear;
var startYear=new Date().getUTCFullYear()+2;

for(var i=0;i<6;i++){
	thisYear=startYear-i;
	data.push({"year":thisYear});
}
	
$("#M1").combobox("loadData", data);//下拉框加载数据

function doSubmit(){
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