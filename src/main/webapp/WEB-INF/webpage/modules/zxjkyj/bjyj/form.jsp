<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>报警信息处理</title>
	<meta name="decorator" content="default"/>
</head>
<body>

     <form id="inputForm" action="${ctx}/fmew/bj/${action}"  method="post" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>				
				<tr>
					<td class="width-15 active"><label class="pull-right">报警原因：</label></td>
					<td class="width-85" colspan="3">
					<input name="reason" type="text" value="${bj.reason }"   class="easyui-textbox" style="width: 100%;height: 80px;" data-options="multiline:true ,required:true, validType:'length[0,250]'">
					</td>
				</tr>
				
				<c:if test="${not empty bj.ID}">
					<input type="hidden" name="ID" value="${bj.ID}" />
					<input type="hidden" name="ID1" value="${bj.ID1}" />
					<input type="hidden" name="ID2" value="${bj.ID2}" />
					<input type="hidden" name="colltime" value="<fmt:formatDate pattern="yyyy-MM-dd hh:mm:ss"  value="${bj.colltime}"/>" />
					<input type="hidden" name="type" value="${bj.type}" />
					<input type="hidden" name="situation" value="${bj.situation}" />
					<input type="hidden" name="status" value="1" />
				</c:if>
			</table>

		  	<tbody>
       </form>
 
<script type="text/javascript">
var usertype=${usertype};
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
	    	parent.dg1.datagrid('reload');
	    	parent.dg2.datagrid('reload');
	    	parent.layer.close(index);//关闭对话框。
	    }    
	});

});
</script>
</body>
</html>