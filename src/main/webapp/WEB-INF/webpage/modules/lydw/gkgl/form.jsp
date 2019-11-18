<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>工卡管理</title>
	<meta name="decorator" content="default"/>
 

<script type="text/javascript">
var uploadImgFlag;//是否上传图片
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
			return false; //返回false终止表单提交
	    },
	    success:function(data){
	    	$.jBox.closeTip();
	    	if(data=='success')
	    		parent.layer.open({icon:1,title: '提示',offset: 'rb',content: '操作成功！',shade: 0 ,time: 2000 });
	    	else
	    		parent.layer.open({icon:2,title: '提示',offset: 'rb',content: '操作失败！',shade: 0 ,time: 2000 });
	    	parent.dg.datagrid('reload');
            parent.dg.datagrid('clearChecked');
            parent.dg.datagrid('clearSelections');
	    	parent.layer.close(index);//关闭对话框。
	    }    
	});

});
</script>
</head>
<body>

     <form id="inputForm" action="${ctx}/lydw/gkgl/${action}"  method="post" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				 
				<tr >
					<td class="width-20 active"><label class="pull-right">标签号：</label></td>
					<td class="width-80" colspan="3">
                        <input type="text" name="fileid" class="easyui-textbox" value="${pub_file.fileid }" <c:if test="${action eq 'update'}" >readonly</c:if>
                               data-options="required:'true',validType:'mone'" style="width: 100%;height: 30px;" />
					</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">标签编码：</label></td>
					<td class="width-30"><input name="filecode" class="easyui-textbox" value="${pub_file.filecode }" style="width: 100%;height: 30px;" data-options="validType:['cczu_englishCheckSub'] "/></td>
					<td class="width-20 active"><label class="pull-right">标签：</label></td>
					<td class="width-30"><input name="tag" class="easyui-textbox" value="${pub_file.tag }" style="width: 100%;height: 30px;" data-options="required:'true'" /></td>
				</tr>
				</tbody>
			</table>
			<c:if test="${not empty pub_file.fileid}">
					<input type="hidden" name="intime" value="<fmt:formatDate value="${pub_file.intime}" pattern="yyyy-MM-dd HH:mm:ss"  />" />
				</c:if>
       </form>
</body>
</html>