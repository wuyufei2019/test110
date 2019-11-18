<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>第三方单位管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>

     <form id="inputForm" action="${ctx}/dsffw/dsf/${action}"  method="post" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
			  	<tr>
					<td class="width-15 active"><label class="pull-right">姓名：</label></td>
					<td class="width-35" ><input value="${tz.m1 }" name="M1" style="width: 100%;height: 30px;"
								class="easyui-textbox"
								data-options="required:'true',validType:'length[0,20]'" /></td>
					<td class="width-15 active"><label class="pull-right">性别：</label></td>
					<td class="width-35" ><input name="M2" class="easyui-combobox" style="width: 100%;height: 30px;"
								value="${tz.m2 }"
								data-options="editable:false ,panelHeight:'auto' ,data: [
									{value:'0',text:'男'},
									{value:'1',text:'女'}]" /></td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">类别：</label></td>
					<td class="width-35" ><input name="M8" class="easyui-combobox" style="width: 100%;height: 30px;"
								value="${tz.m8 }"
								data-options="editable:false ,panelHeight:'auto' ,data: [
									{value:'一般',text:'一般'},
									{value:'特种',text:'特种'}]" /></td>				
					<td class="width-15 active"><label class="pull-right">操作证号：</label></td>
					<td class="width-35"><input name="M3" class="easyui-textbox" value="${tz.m3 }"
								style="width: 100%;height: 30px;"
								data-options="required:'true',validType:'length[0,20]'" /></td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">身份证号：</label></td>
					<td class="width-35"><input name="M4" class="easyui-textbox" value="${tz.m4 }"
								style="width: 100%;height: 30px;" data-options="validType:'length[0,20]' ,validType:'idCode' " /></td>
					<td class="width-15 active"><label class="pull-right">作业类型：</label></td>
					<td class="width-35"><input name="M5" class="easyui-combobox" value="${tz.m5 }"
								style="width: 100%;height: 30px;"
								data-options="panelHeight:'100px' ,editable:false ,panelHeight:'auto' ,data: [
									{value:'动火作业',text:'动火作业'},
									{value:'受限空间作业',text:'受限空间作业'},
									{value:'管道拆卸作业',text:'管道拆卸作业'},
									{value:'盲板抽堵作业',text:'盲板抽堵作业'},
									{value:'高处安全作业',text:'高处安全作业'},
									{value:'吊装安全作业',text:'吊装安全作业'},
									{value:'临时用电安全作业',text:'临时用电安全作业'},
									{value:'动土安全作业',text:'动土安全作业'},
									{value:'断路安全作业',text:'断路安全作业'},
									{value:'其他作业',text:'其他作业'}]" /></td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">到期日期：</label></td>
					<td class="width-35"><input name="M6" class="easyui-datebox" value="${tz.m6 }" editable="false"
								style="width: 100%;height: 30px;" data-options="required:'true'" /></td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">备注：</label></td>
					<td class="width-85" colspan="3">
					<input name="M7" type="text" value="${tz.m7 }"   class="easyui-textbox" style="width: 100%;height: 80px;" data-options="multiline:true">
					</td>
				</tr>
				
				<c:if test="${ empty tz.ID1}">
					<input type="hidden" name="ID1" value="${dwid }" />
				</c:if>
				<c:if test="${not empty tz.ID1}">
					<input type="hidden" name="ID1" value="${tz.ID1 }" />
					<input type="hidden" name="S3" value="${tz.s3 }" />
					<input type="hidden" name="S1" value="<fmt:formatDate value="${tz.s1}" pattern="yyyy-MM-dd HH:mm:ss"  />" />
					<input type="hidden" name="ID" value="${tz.ID }" />
				</c:if>
				</tbody>
			</table>

       </form>
 
<script type="text/javascript">
var usertype=${usertype};
var $ = jQuery;
	
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
	    	parent.tzdg.datagrid('reload');
	    	parent.layer.close(index);//关闭对话框。
	    }    
	})

})
</script>
</body>
</html>