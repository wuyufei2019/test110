<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>员工管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>

     <form id="inputForm" action="${ctx}/xzcf/yhxx/${action}"  method="post" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right">银行名称：</label></td>
					<td class="width-35"><input name="bankname"class="easyui-textbox" style="width: 100%;height: 30px;" class="easyui-textbox"
								value="${bie.bankname }"
								data-options="required:'true'" /></td>
					<td class="width-15 active"><label class="pull-right">银行账号：</label></td>
					<td class="width-35"><input name="account" class="easyui-textbox"
								value="${bie.account }" style="width: 100%;height: 30px;" validtype='number' data-options="required:'true', " /></td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">地址：</label></td>
					<td class="width-35 " colspan="3" ><input name="address" style="width: 100%;height: 30px;" class="easyui-textbox"
								value="${bie.address }" /></td>
				</tr>
				
					<input type="hidden" name="ID" value="${bie.ID}" />
					<input type="hidden" name="S1" value="<fmt:formatDate value="${bie.s1}" pattern="yyyy-MM-dd HH:mm:ss"  />" />
				</tbody>
			</table>
       </form>
 


<script type="text/javascript">
var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
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
			return false; //返回false终止表单提交
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