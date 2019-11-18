<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>修改密码</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		 
	</script>
</head>
<script type="text/javascript" src="${ctx}/static/plugins/jquery/jquery.form.js"></script>
<body>
	<div style="width:100%; margin-top:20px;font-size:12px; font-family:微软雅黑;">
	<form id="mainform" style="width: 500px;margin: 0 auto;" class="form-horizontal">
	<input type="hidden" name="id" value="${user.id }"/>
	<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
		<tr>
			<td class="width-15 active"><label class="pull-right"><font color="red">*</font>原密码:</label></td>
			<td class="width-35"> <input id="oldPassword" name="oldPassword" type="password" class="easyui-textbox easyui-validatebox" data-options="width: 200,required:'required',validType:{length:[6,20],remote:['${ctx}/system/user/checkPwd','oldPassword']}"/></td>
		</tr>
		<tr>
			<td class="width-15 active"><label class="pull-right"><font color="red">*</font>新密码:</label></td>
			<td class="width-35"><input id="plainPassword" name="plainPassword" type="password" class="easyui-textbox easyui-validatebox" data-options="width: 200,required:'required',validType:'length[6,20]'"/></td>
		</tr>
		<tr>
			<td class="width-15 active"><label class="pull-right"><font color="red">*</font>确认新密码:</label></td>
			<td class="width-35"><input id="confirmPassword" name="confirmPassword" type="password" class="easyui-textbox easyui-validatebox" data-options="width: 200,required:'required',validType:'equalTo[$(\'#plainPassword\').val()]'"/></td>
		</tr>
		<tr>
			<td colspan="2" style="text-align: center;"><a href="javascript:void(0)" class="btn btn-primary"  id="submit_form_bt" style="width:120px">保存</a></td>
		</tr>
	</table>
	</form>
</div>
<script>
$(function(){

	$("#submit_form_bt").bind('click',function(){
		if($("#mainform").form('validate')){
			
			$.ajax({
				type:'post',
				url:'${ctx}/system/user/updatePwd',
				data:$("#mainform").serialize(),
				dataType: 'text',
	            success:function(data){
	            	layer.open({title: '提示',offset: 'rb',content: data,shade: 0 ,time: 2000 });
	            }
			});
		} 
	});
	
	
});

 
</script>
</body>
</html>