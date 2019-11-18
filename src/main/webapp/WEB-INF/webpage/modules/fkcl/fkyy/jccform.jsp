<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>添加进出厂信息</title>
<meta name="decorator" content="default" />
</head>
<body>
	<form id="inputForm" action="${ctx}/fkcl/fkyy/${action}" method="post" class="form-horizontal">
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
			<tbody>
				<tr>
					<td class="width-20 active">
						<label class="pull-right">
							<c:if test="${action eq 'jcupdate'}">
								进厂人数：
							</c:if>
							<c:if test="${action eq 'ccupdate'}">
								出厂人数：
							</c:if>
						</label>
					</td>
					<td class="width-80">
						<input id="rynum" name="rynum" type="text" class="easyui-numberbox" value="" style="width: 290px;height: 30px;" required="required" data-options="min:0" />
					</td>
				</tr>
				<input type="hidden" name="id" value="${id}" />
			</tbody>
		</table>
	</form>

<script type="text/javascript">
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
	    	}else{
	    		parent.layer.open({icon:2,title: '提示',offset: 'rb',content: '操作失败！',shade: 0 ,time: 2000 });
			}
	    	parent.dg.datagrid('reload');
	    	parent.layer.close(index);//关闭对话框。
	    }    
	});
});
</script>
</body>
</html>