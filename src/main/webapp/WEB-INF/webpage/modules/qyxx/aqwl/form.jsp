<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>安全管理网络</title>
	<meta name="decorator" content="default"/>
</head>
<body>
<div>
	<form id="inputForm"  action="${ctx}/bis/aqwlgl/${action}" method="post" class="form-horizontal" >
	<input type="hidden" name="id" value="${bis.id }"/>
	<input type="hidden" name="id1" value="${bis.id1}" />
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
		<tbody>
			<tr>
		         <td class="width-15 active"><label class="pull-right">名称:</label></td>
		         <td class="width-35"><input type="text" name="m1" value="${bis.m1}" class="easyui-textbox" style="width:250px;height: 30px;" data-options="required:'required',validType:'length[1,100]'"/></td>
		         <td class="width-15 active"><label class="pull-right">所属上级:</label></td>
		         <td class="width-35"><input type="text" name="pid" value="${bis.pid}" class="easyui-combotree" style="width:250px;height: 30px;" data-options="required:'required',url: '${ctx}/bis/aqwlgl/json'"/></td>
		      </tr>
		      <tr>
		         <td class="width-15 active"><label class="pull-right">负责人:</label></td>
		         <td class="width-35" ><input type="text" name="m2" value="${bis.m2}" class="easyui-textbox" style="width:250px;height: 30px;" data-options="validType:'length[1,25]'"/></td>
		         <td class="width-15 active"><label class="pull-right">负责人联系方式:</label></td>
		         <td class="width-35"><input type="text" name="m3" value="${bis.m3}" class="easyui-textbox" style="width:250px;height: 30px;" data-options="validType:'length[1,25]'"/></td>
		      </tr>
		      <tr>
		         <td class="width-15 active"><label class="pull-right">安全职责:</label></td>
		         <td class="width-35" colspan="3"><input type="text" name="m4" value="${bis.m4}" class="easyui-textbox" style="width:640px;height: 80px;" data-options="multiline:true,validType:'length[1,1000]'"/></td> 
		      </tr>
		      <tr>
		         <td class="width-15 active"><label class="pull-right">备注:</label></td>
		         <td class="width-35" colspan="3"><input type="text" name="m5" value="${bis.m5}" class="easyui-textbox" style="width:640px;height: 80px;" data-options="multiline:true,validType:'length[1,500]'"/></td> 
		      </tr>
		      
		    
		</tbody>
		
	</table>
	</form>
</div> 
<script type="text/javascript">
//父级权限
var usertype='${usertype}';

$('#pid').val(parent.parentPermId);


var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
var validateForm;

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
	    	parent.dg.treegrid('reload');
	    	parent.dg.treegrid('clearChecked');
	    	parent.layer.close(index);//关闭对话框。
      }    
});

});


</script>
</body>
</html>