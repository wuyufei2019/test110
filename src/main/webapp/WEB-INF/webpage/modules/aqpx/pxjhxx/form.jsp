<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>培训计划信息</title>
	<meta name="decorator" content="default"/>
</head>
<body>

     <form id="inputForm" action="${ctx}/aqpx/aqpxjh/${action}"  method="post" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right">培训计划名称：</label></td>
					<td class="width-35" colspan="3"><input name="M1" style="width: 100%;height: 30px;" class="easyui-textbox"
								value="${aqpxjh.m1 }"
								data-options="required:'true'" /></td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">培训日期起：</label></td>
					<td class="width-35"><input name="M5" type="text" class="easyui-datebox" value="${aqpxjh.m5 }" style="width: 100%;height: 30px;" datefmt="yyyy-MM-dd" data-options="editable:false, required:'true'"/></td>
					<td class="width-15 active"><label class="pull-right">培训日期止：</label></td>
					<td class="width-35"><input name="M6" type="text" class="easyui-datebox" value="${aqpxjh.m6 }" style="width: 100%;height: 30px;" datefmt="yyyy-MM-dd" data-options="editable:false, required:'true'"/></td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">培训课程：</label></td>
					<td class="width-35" colspan="3"><input name="ID2" type="text" value="${aqpxjh.ID2}"  class="easyui-combobox"  style="width: 100%;height: 30px;" data-options="panelHeight:'150px', required:'true',
								editable:false ,valueField: 'id',textField: 'text',url: '${ctx}/aqpx/kcxx/json',multiple:'true',queryParams:{'type':1} "/></td>
				</tr>
				<tr>	
					<td class="width-15 active"><label class="pull-right">培训部门：</label></td>
					<td class="width-35" colspan="3"><input name="ID3" value="${aqpxjh.ID3}" type="text" class="easyui-combobox"  style="width: 100%;height: 30px;"
						data-options="panelHeight:'150px', required:'true',editable:true ,valueField: 'id',textField: 'text',url:'${ctx}/system/department/deptjson',multiple:'true' "/></td>
				</tr>

				<tr>
					<td class="width-15 active"><label class="pull-right">培训学时(h)：</label></td>
					<td class="width-35"><input name="M3" class="easyui-textbox" value="${aqpxjh.m3 }" style="width: 100%;height: 30px;"
								data-options="required:'true', validType:'mone' " /></td>
					<td class="width-15 active"><label class="pull-right">培训类别：</label></td>
					<td class="width-35"><input name="M2" class="easyui-combobox"
								value="${aqpxjh.m2 }" style="width: 100%;height: 30px;" data-options="panelHeight:'auto', required:'true',
								editable:false ,data: [
										{value:'定期',text:'定期'},
								        {value:'脱岗',text:'脱岗'} ] " /></td>
				</tr>
				
					
				</tr>

				<tr>
					<td class="width-15 active"><label class="pull-right">备注：</label></td>
					<td class="width-85" colspan="3">
					<input name="M7" type="text" value="${aqpxjh.m7 }"   class="easyui-textbox" style="width: 100%;height: 80px;" data-options="multiline:true , validType:'length[0,250]'">
					</td>
					
				</tr>
				
				<c:if test="${not empty aqpxjh.ID}">
					<input type="hidden" name="ID" value="${aqpxjh.ID}" />
					<input type="hidden" name="ID1" value="${aqpxjh.ID1}" />
					<input type="hidden" name="S1"
						value="<fmt:formatDate value="${aqpxjh.s1}" pattern="yyyy-MM-dd HH:mm:ss"  />" />
					<input type="hidden" name="S3" value="${aqpxjh.s3}" />
					<input type="hidden" name="M4" 
						value="<fmt:formatDate value="${aqpxjh.s1}" pattern="yyyy-MM-dd HH:mm:ss"  />"  />
				</c:if>
					<tbody>
			</table>

       </form>
 
<script type="text/javascript">
var usertype=${usertype};
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