<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>员工体检信息</title>
	<meta name="decorator" content="default"/>
</head>
<body>

     <form id="inputForm" action="${ctx}/bis/ygtjxx/${action}"  method="post" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<tr>
					<td class="width-20 active"><label class="pull-right">员工姓名：</label></td>
					<td class="width-30">${ygtjlist.m7 }</td>
					<td class="width-20 active"><label class="pull-right">身份证号：</label></td>
					<td class="width-30">${ygtjlist.m1 }</td>
				<tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">体检类型：</label></td>
					<td class="width-30">${ygtjlist.m2 }</td>
					<td class="width-20 active"><label class="pull-right">体检医院：</label></td>
					<td class="width-30" >${ygtjlist.m4 }</td>	       	        			
				<tr>
					<td class="width-20 active"><label class="pull-right">体检日期：</label></td>
					<td class="width-30"><input id="M3" name="M3" class="easyui-datebox" value="${ygtjlist.m3 }" style="width: 100%;height: 30px;" data-options="editable:true " /></td>
					<td class="width-20 active"><label class="pull-right">下次体检日期：</label></td>
					<td class="width-30"><input id="M8" name="M8" class="easyui-datebox" value="${ygtjlist.m8 }" style="width: 100%;height: 30px;" data-options="editable:true " /></td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">体检结论：</label></td>
					<td class="width-85" colspan="3">
					<input name="M5" type="text" value="${ygtjlist.m5}"   class="easyui-textbox" style="width: 100%;height: 80px;" data-options="multiline:true,validType:'length[0,500]'">
					</td>					
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">备注：</label></td>
					<td class="width-85" colspan="3">
					<input name="M6" type="text" value="${ygtjlist.m6}"   class="easyui-textbox" style="width: 100%;height: 80px;" data-options="multiline:true,validType:'length[0,500]'">
					</td>					
				</tr>
				
				<c:if test="${not empty ygtjlist.ID}">
					<input type="hidden" name="ID" value="${ygtjlist.ID}" />
					<input type="hidden" name="ID1" value="${ygtjlist.ID1}" />
					<input type="hidden" name="S1"
						value="<fmt:formatDate value="${ygtjlist.s1}" pattern="yyyy-MM-dd HH:mm:ss"  />" />
					<input type="hidden" name="S3" value="${ygtjlist.s3}" />
				</c:if>
				</tbody>
			</table>
       </form>
 
<script type="text/javascript">
var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
var usertype=${usertype};
	
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