<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>检查内容表库管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>

     <form id="inputForm" action="${ctx}/yhpc/jcbk/${action}"  method="post" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<tr>
				    <td class="width-15 active"><label class="pull-right">隐患级别 ：</label></td>
					<td class="width-35"><input class="easyui-combobox" name="dangerlevel" value="${jcbk.dangerlevel }" style="width: 100%;height: 30px;" data-options="
								 editable:false ,panelHeight:'auto' ,required:true, data: [
								        {value:'1',text:'一般'},
								        {value:'2',text:'重大'}
														]
						    "/></td>
				    <c:if test="${jcbk.usetype eq '2'}">
						<td class="width-15 active"><label class="pull-right">检查单元：</label></td>
					    <td class="width-35"><input name="checktitle" class="easyui-combobox" value="${jcbk.checktitle }" style="width: 100%;height: 30px;"
									data-options="required:'true',editable:true ,
									valueField: 'text',textField: 'text',url:'${ctx}/yhpc/jcbk/gettype' " /></td>
					</c:if>
				</tr>
				
				<tr>
				    <td class="width-15 active"><label class="pull-right">检查项目：</label></td>
					<td class="width-35" colspan="3">
			        <input value="${jcbk.content}" type="text" id="content" name="content" class="easyui-textbox" style="height: 80px;width: 100%"
								data-options="multiline:true,validType:'length[0,500]'" />
					</td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">隐患内容：</label></td>
				    <td class="width-35"><input id="checkyes" name="checkyes" type="text"  class="easyui-textbox" value="${jcbk.checkyes}"  style="height: 30px;width: 100%" /></td>
				    <td class="width-15 active"><label class="pull-right">正常内容 ：</label></td>
				<td class="width-35"><input id="checkno" name="checkno" type="text"  class="easyui-textbox" value="${jcbk.checkno}"  style="height: 30px;width: 100%" /></td>
				</tr>
				
				<input type="hidden" name="ID1" value="${jcbk.ID1}" />
				<input type="hidden" name="usetype" value="${jcbk.usetype}" />
				<c:if test="${not empty jcbk.ID}">
					<input type="hidden" name="ID" value="${jcbk.ID}" />
				</c:if>
				</tbody>
			</table>
       </form>
 
<script type="text/javascript">
	var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
	function doSubmit(){
		$("#inputForm").submit(); 
	}
	
	$(function(){
		if ('${action}' == 'create') {
		    $("#checkyes").textbox("setValue", "有隐患");
		    $("#checkno").textbox("setValue", "已检查无隐患");
		}
	    var flag=true;
			$('#inputForm').form({
				onSubmit : function() {
					var isValid = $(this).form('validate');
			    	if(isValid&&flag){
			    		flag=false;
			    		$.jBox.tip("正在提交，请稍等...",'loading',{opacity:0});
			    		return true;
			    	}
					return false; // 返回false终止表单提交
			},  
		    success:function(data){ 
		         $.jBox.closeTip();
		    	if(data=='success')
		    		parent.layer.open({icon:1,title: '提示',offset: 'rb',content: '操作成功！',shade: 0 ,time: 2000 });
		    	else
		    		parent.layer.open({icon:2,title: '提示',offset: 'rb',content: '操作失败！',shade: 0 ,time: 2000 });
		    	var usertype = '${usertype}';
		    	parent.dg.datagrid('reload');
		    	parent.dg2.datagrid('reload');
		    	if(usertype != 1){
		    		parent.dg3.datagrid('reload');
		    	}
		    	parent.layer.close(index);//关闭对话框。
		    }    
		});
	
	});
	
</script>
</body>
</html>