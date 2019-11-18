<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>个人信息修改</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
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
		    	else
		    		parent.layer.open({icon:2,title: '提示',offset: 'rb',content: '操作失败！',shade: 0 ,time: 2000 });
		    }    
		});
	
	});
	</script>
</head>
<body>
 <div id="tt" class="easyui-tabs" style="width:100%;height:100%;">
    <div title="账号信息修改" style="padding:20px;display:none;">
		<form id="inputForm" action="${ctx}/system/user/update" method="post" class="form-horizontal" style="width: 800px;margin: 20px auto 0;">
		<input type="hidden" name="id" value="${user.id }" />
		<input type="hidden" name="id2" value="<c:if test="${empty user.id2}">0</c:if><c:if test="${not empty user.id2}">${user.id2}</c:if>" />
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
		   <tbody>
			  <tr>
		         <td class="width-15 active"><label class="pull-right"><font color="red">*</font>用户名：</label></td>
		         <td class="width-35"><input id="loginName" name="loginName" class="easyui-textbox easyui-validatebox" data-options=" " value="${user.loginName }" style="width: 100%;height: 30px;"> </td>
		         <td class="width-15 active"><label class="pull-right"><font color="red">*</font>昵称:</label></td>
		         <td class="width-35"><input name="name" type="text" value="${user.name }" class="easyui-textbox easyui-validatebox" data-options="required:'required',validType:'length[1,20]'" style="width: 100%;height: 30px;"/></td>
		      </tr>
		      
		      <tr>
		         <td class="width-15 active"><label class="pull-right"><font color="red">*</font>出生日期：</label></td>
		         <td class="width-35"><input id="birthday" name="birthday" type="text" class="easyui-datebox"  value="<fmt:formatDate value="${user.birthday}" pattern="yyyy-MM-dd"/>"  style="width: 100%;height: 30px;"/></td>
		         <td class="width-15 active"><label class="pull-right"><font color="red">*</font>性别:</label></td>
		         <td class="width-35"><input type="radio" id="man" name="gender" value="1" class="i-checks"/><label for="man">男</label><input type="radio" id="woman" name="gender" value="0" class="i-checks"/><label for="woman">女</label></td>
		      </tr>
		      <tr>
		         <td class="width-15 active"><label class="pull-right">Email：</label></td>
		         <td class="width-35"><input type="text" name="email" value="${user.email }" class="easyui-textbox easyui-validatebox" data-options=" validType:'email'" style="width: 100%;height: 30px;"> </td>
		         <td class="width-15 active"><label class="pull-right">手机:</label></td>
		         <td class="width-35"><input type="text" name="phone" value="${user.phone }" class="easyui-numberbox easyui-validatebox"  data-options="validType:'mobile'" style="width: 100%;height: 30px;"/></td>
		      </tr>
		      
			   
				<tr>
					<td class="width-15 active"><label class="pull-right">所属部门：</label></td>
					<td class="width-35"><input id="departmen" name="departmen" value="${user.departmen}"  class="easyui-combobox" style="width: 100%;height: 30px;" data-options="editable:false,panelHeight:180, valueField: 'id',textField: 'text',url:'${ctx}/system/department/deptjson' "/></td>
					<td class="width-15 active"></td>
					<td class="width-35"></td>
				</tr>
			   
		      <tr>
		         <td class="width-15 active">	<label class="pull-right">描述:</label></td>
		         <td class="width-85" colspan="3" ><input type="text" name="description" value="${user.description}" class="easyui-textbox" style="width: 100%;height: 80px;" data-options="multiline:true"/></td>
		      </tr>
		     </tbody>
		  </table>
		 </form>
		 <div style="text-align:center;margin: 20px;">
			<a class="btn btn-primary"  onclick="doSubmit()" style="width:120px">保存信息</a>				
		 </div>
    </div>
    <%-- <div title="员工档案信息" data-options=" " style="overflow:auto;padding:20px;display:none;">
		<iframe frameborder="0" src="${ctx}/bis/ygxx/myinfor" style="width: 100%;height: 95%;"></iframe>
    </div> --%>
    <div title="密码修改" data-options=" " style="padding:20px;display:none;">
		<iframe frameborder="0" src="${ctx}/system/user/updatePwd" style="width: 100%;min-height: 200px;"></iframe>
    </div>
</div>
	
<script type="text/javascript">

	$("input[name='loginName']").attr('readonly','readonly');
	$("input[name='loginName']").css('background','#eee')
	$("input[name='gender'][value=${user.gender}]").attr("checked",true);

   
</script>
</body>
</html>