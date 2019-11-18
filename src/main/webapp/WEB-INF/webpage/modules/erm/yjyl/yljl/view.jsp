<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>演练记录管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=OTVG2uzqudiovwoHlFIs0P8Z3T7ID4K2"></script>
</head>
<body>

     <form id="inputForm" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<tr >
					<td class="width-15 active"><label class="pull-right">演练时间：</label></td>
					<td class="width-35" colspan="3">
						<fmt:formatDate value="${res.m1}"/>
					</td>
				</tr>

				<tr>
					<td class="width-15 active"><label class="pull-right">演练地点：</label></td>
					<td class="width-35" colspan="3">${res.m2 }</td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">总指挥：</label></td>
					<td class="width-35">${res.m3 }</td>
					<td class="width-15 active"><label class="pull-right">参演部门：</label></td>
					<td class="width-35">${res.m4 }</td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">演练名称：</label></td>
					<td class="width-35" colspan="3">${res.m5 }</td>
				</tr>

				<tr>
					<td class="width-15 active"><label class="pull-right">演练内容：</label></td>
					<td class="width-85" colspan="3" style="height:80px">
						${res.m6}
					</td>		
				</tr>

				<tr>
					<td class="width-15 active"><label class="pull-right">效果评价：</label></td>
					<td class="width-35">${res.m7 }</td>
					<td class="width-15 active"><label class="pull-right">评审时间：</label></td>
					<td class="width-35"><fmt:formatDate value="${res.m9}"/></td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">评审人员：</label></td>
					<td class="width-35" colspan="3">${res.m8 }</td>
				</tr>

				<tr>
					<td class="width-15 active"><label class="pull-right">存在问题：</label></td>
					<td class="width-85" colspan="3" style="height:80px">
						${res.m10}
					</td>		
				</tr>

				<tr>
					<td class="width-15 active" ><label class="pull-right">附件资料：</label></td>
					<td class="width-35" colspan="3">
					 <c:if test="${not empty res.m11}">
					 <c:forTokens items="${res.m11}" delims="," var="url" varStatus="urls">
					 	<c:set var="urlna" value="${fn:split(url, '||')}" />
					 	<div style="margin: 5px;"> 
			            	<a style="color:#337ab7;text-decoration:none;" target="_blank" href="${urlna[0]}">${urlna[1]}</a>
			            </div>
					 </c:forTokens>
					 </c:if> 
					</td>
				</tr>
				
				<c:if test="${not empty res.ID}">
					<input type="hidden" name="ID" value="${res.ID}" />
					<input type="hidden" name="ID1" value="${res.ID1}" />
					<input type="hidden" name="qyid" value="${res.qyid}" />
					<input type="hidden" name="S1"
						value="<fmt:formatDate value="${res.s1}" pattern="yyyy-MM-dd HH:mm:ss"  />" />
					<input type="hidden" name="S3" value="${res.s3}" />
				</c:if>
			</table>

		  	<tbody>
       </form>
 
<script type="text/javascript">
	var usertype='${usertype}';
	var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
	var $ = jQuery, $list2 = $('#m11fileList'); //文件上传
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

						var $input = $('<input id="input_'+file.id+'" type="hidden" name="M11" value="'+newurl+'"/>');

						$('#uploader_ws_m11').append($input);
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
			var fjUrl = '${sfr.m11}';
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

					var $input = $('<input id="input_'+id+'" type="hidden" name="M11" value="'+arry[i]+'"/>');
					$('#uploader_ws_m11').append($input);
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