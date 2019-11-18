<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>违法行为管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>

     <form id="inputForm" action="${ctx}/aqzf/wfxw/${action}"  method="post" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right">违法行为：</label></td>
					<td class="width-85" >
						<input value="${wfxw.m1}" type="text" id="M1" name="M1" style="width: 100%;height: 30px;"
								class="easyui-textbox"
								data-options="required:true,validType:'length[0,200]'" />
					</td>
				</tr>
				<tr>
				    <td class="width-15 active"><label class="pull-right">违法条款：</label></td>
					<td class="width-85" >
						<input value="${wfxw.m2}" type="text" id="M2" name="M2" style="width: 100%;height: 30px;"
								class="easyui-textbox"
								data-options="editable:true,validType:'length[0,100]'" />
					</td>
				</tr>
                <tr>
					<td class="width-15 active"><label class="pull-right">违反条款详情：</label></td>
					<td class="width-85" >
						<input type="text" name="M3" class="easyui-textbox" value="${wfxw.m3 }"  
						data-options="multiline:true , validType:'length[0,1000]'" style="width: 100%;height: 80px;" />
					</td>
				</tr>
				<tr>
				    <td class="width-15 active"><label class="pull-right">处罚依据：</label></td>
					<td class="width-85" >
						<input value="${wfxw.m4}" type="text" id="M4" name="M4" style="width: 100%;height: 30px;"
								class="easyui-textbox"
								data-options="editable:true,validType:'length[0,100]'" />
					</td>
				</tr>
                <tr>
					<td class="width-15 active"><label class="pull-right">处罚标准：</label></td>
					<td class="width-85" >
						<input type="text" name="M5" class="easyui-textbox" value="${wfxw.m5 }"  
						data-options="multiline:true , validType:'length[0,1000]'" style="width: 100%;height: 80px;" />
					</td>
				</tr>
				
				<c:if test="${not empty wfxw.ID}">
					<input type="hidden" name="ID" value="${wfxw.ID}" />
					<input type="hidden" name="ID1" value="${wfxw.ID1}" />
					<input type="hidden" name="S1"  value="<fmt:formatDate value="${wfxw.s1}" pattern="yyyy-MM-dd HH:mm:ss"/>" />
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
		    	parent.dg.datagrid('reload');
		    	parent.layer.close(index);//关闭对话框。
		    }    
		});
	
	});
	
</script>
</body>
</html>