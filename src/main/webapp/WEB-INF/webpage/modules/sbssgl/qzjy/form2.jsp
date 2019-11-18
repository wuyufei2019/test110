<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default" />
<title></title>

</head>
<body>
		<form id="inputForm" action="${ctx}/sbssgl/qzjy/${action}" method="post">
			<table class="table table-bordered dataTable" style="margin:auto;">
			<tbody>
			<tr>
				<td class="width-15 active"><label class="pull-right">设备名称：</label></td>
				<td class="width-35">${tzsb.m2 }</td>
				<td class="width-15 active"><label class="pull-right">厂商：</label></td>
				<td class="width-35">${tzsb.m5 }</td>
			</tr>
			<tr>
				<td class="width-15 active"><label class="pull-right">设备类型：</label></td>
				<td class="width-35">${tzsb.m20 }</td>
				<td class="width-15 active"><label class="pull-right">型号：</label></td>
				<td class="width-35">${tzsb.m27 }</td>
			</tr>
			<tr>
				<td class="width-15 active"><label class="pull-right">规格：</label></td>
				<td class="width-35">${tzsb.m3 }</td>
				<td class="width-15 active"><label class="pull-right">放置地点：</label></td>
				<td class="width-35">${tzsb.m8 }</td>
			</tr>
			<tr>
				<td class="width-15 active"><label class="pull-right">资质编号：</label></td>
				<td class="width-35">${tzsb.m17 }</td>
				<td class="width-15 active"><label class="pull-right">出厂编号：</label></td>
				<td class="width-35">${tzsb.m4 }</td>
			</tr>
			<tr>
				<td class="width-15 active"><label class="pull-right">注册登记日期：</label></td>
				<td class="width-35"><fmt:formatDate value="${tzsb.m28 }" pattern="yyyy-MM"/></td>
				<td class="width-15 active"><label class="pull-right">安装单位：</label></td>
				<td class="width-35">${tzsb.m30 }</td>
			</tr>				
			<tr>
					<td class="width-15 active"><label class="pull-right">本次检验日期：</label></td>
					<td><input id="m31" name="m31" class="easyui-datebox" value='<fmt:formatDate value="${tzsb.m31}" pattern="yyyy-MM-dd"/>' style="width: 100%;height: 30px;" data-options="editable:false" /></td>
					<td class="width-15 active"><label class="pull-right">下次检验日期：</label></td>
					<td><input id="m32" name="m32" class="easyui-datebox" value='<fmt:formatDate value="${tzsb.m32}" pattern="yyyy-MM-dd"/>' style="width: 100%;height: 30px;" data-options="editable:false" /></td>
			</tr>

					<input type="hidden" name="ID" value="${tzsb.ID}" />
				</tbody>
			</table>
		</form>
<script type="text/javascript">
var index = parent.layer.getFrameIndex(window.name); //获取窗口索引

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