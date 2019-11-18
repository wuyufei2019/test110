<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>报警管理</title>
	<meta name="decorator" content="default"/>
 

<script type="text/javascript">
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

     <form id="inputForm" action="${ctx}/lydw/bjgl/${action}"  method="post" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				 
				<tr >
					<td class="width-30 active"><label class="pull-right">报警类别：</label></td>
					<td class="width-70">
                        <input type="text" name="M1" class="easyui-combobox" value="${lydw_bjgl.m1 }"
                               data-options="required:'true',valueField:'text',textField:'text',url:'${ctx }/lydw/bjgl/bjlb'" style="width: 100%;height: 30px;" />
					</td>
				</tr>
				<tr>
					<td class="width-30 active"><label class="pull-right">报警内容：</label></td>
					<td class="width-70"><input name="M2" class="easyui-textbox" value="${lydw_bjgl.m2 }" style="width: 100%;height: 60px;" data-options="multiline:true,editable:false,required:'true'"/></td>
				</tr>
                <tr>
                    <td class="width-30 active"><label class="pull-right">处理反馈：</label></td>
                    <td class="width-70" ><input name="M3" class="easyui-textbox" value="${lydw_bjgl.m3 }" style="width: 100%;height: 60px;" data-options="multiline:true,required:'true'" /></td>
                </tr>
				</tbody>
			</table>
			<c:if test="${not empty lydw_bjgl.ID}">
                <input type="hidden" name="S3" value="${lydw_bjgl.s3}" />
                <input type="hidden" name="S1" value="<fmt:formatDate value="${lydw_bjgl.s1}" pattern="yyyy-MM-dd HH:mm:ss"  />" />
                <input type="hidden" name="ID" value="${lydw_bjgl.ID}" />
                <input type="hidden" name="ID1" value="${lydw_bjgl.ID1}" />
            </c:if>
       </form>
</body>
</html>