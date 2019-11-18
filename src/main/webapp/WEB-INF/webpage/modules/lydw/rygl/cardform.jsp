<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>选择工卡</title>
<meta name="decorator" content="default" />
</head>
<body>

	<form id="inputForm" action="${ctx}/lydw/rygl/${action}" method="post" class="form-horizontal">
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
			<tbody>
				<tr>
					<td class="width-20 active"><label class="pull-right">工卡号：</label></td>
					<td class="width-80"><input id="fileid" name="fileid" type="text" class="easyui-combobox" value=""
						style="width: 100%;height: 30px;"
						data-options="valueField: 'id',textField: 'text', panelHeight:'140', url:'${ctx}/lydw/gkgl/jsonlist'" /></td>
				</tr>

				<input type="hidden" id="empid" name="empid" value="${ygid}" />
			</tbody>
		</table>
	</form>

<script type="text/javascript">
var usertype = '${usertype}';
var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
var validateForm;

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
	    	if(data=='success'){
	    		parent.layer.open({icon:1,title: '提示',offset: 'rb',content: '操作成功！',shade: 0 ,time: 2000 });
	    		parent.dg.datagrid('reload');
		    	parent.layer.close(index);//关闭对话框。
	    	}else if (data=='error') {
                parent.layer.open({icon:2,title: '提示',offset: 'rb',content: '工卡号不存在！',shade: 0 ,time: 2000 });
                parent.dg.datagrid('reload');
                parent.layer.close(index);//关闭对话框。
            }else{
	    		parent.layer.open({icon:2,title: '提示',offset: 'rb',content: '操作失败！',shade: 0 ,time: 2000 });
	    		parent.dg.datagrid('reload');
		    	parent.layer.close(index);//关闭对话框。
			}
	    }    
	});
});

</script>
</body>
</html>