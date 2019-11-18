<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>设备项目</title>
	<meta name="decorator" content="default"/>
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
</head>
<body>
     <form id="inputForm" action="${ctx}/dxj/sbxjd/${action}"  method="post" class="form-horizontal" >
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<tr >
					<td class="width-15 active"><label class="pull-right">选择设备：</label></td>
					<td class="width-35">
						<input type="text" id="ID2" name="ID2" class="easyui-combobox" style="width: 100%;height: 30px;" value="${sbxm.ID2 }"
							   data-options="required:'true',panelHeight:'100px',editable:false,valueField: 'id',textField: 'text',url:'${ctx}/dxj/sb/sblist'"/>
					</td>
					<td class="width-15 active"><label class="pull-right">设备项目名称：</label></td>
					<td class="width-35">
						<input name="name"  class="easyui-textbox" value="${sbxm.name }" style="width: 100%;height: 30px;"
								data-options="required:'true',validType:['length[0,100]']" />
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">标准：</label></td>
					<td class="width-35">
						<input name="standard"  class="easyui-textbox" value="${sbxm.standard }" style="width: 100%;height: 30px;"
								data-options="validType:['length[0,50]']" />
					</td>
					<td class="width-15 active"><label class="pull-right">范围：</label></td>
					<td class="width-35">
						<input name="scope"  class="easyui-textbox" value="${sbxm.scope }" style="width: 100%;height: 30px;"
								data-options="validType:['length[0,100]']" />
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">点检方法：</label></td>
					<td class="width-35" ><input class="easyui-combobox" name="checkmethod" value="${sbxm.checkmethod }" style="width: 100%;height: 30px;" data-options="
								editable:false ,panelHeight:'auto' ,data: [
								        {value:'目测',text:'目测'},
								        {value:'操作',text:'操作'} ]"/>
				    </td>
				</tr>
				<c:if test="${not empty sbxm.ID}">
					<input type="hidden" name="ID" value="${sbxm.ID}" />
					<input type="hidden" name="ID1" value="${sbxm.ID1}" />
					<input type="hidden" name="createtime"
						value="<fmt:formatDate value="${sbxm.createtime}" pattern="yyyy-MM-dd HH:mm:ss"  />" />
				</c:if>
				</tbody>
			</table>
       </form>
</body>
</html>