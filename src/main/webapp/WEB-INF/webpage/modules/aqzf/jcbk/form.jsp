<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>检查表库管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>

     <form id="inputForm" action="${ctx}/aqzf/jcbk/${action}"  method="post" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<tr>
				<c:if test="${action eq 'create'}">
					<td class="width-15 active"><label class="pull-right">检查单元：</label></td>
					<td class="width-35" >
						<input value="${jcbk.m1}" type="text" id="M1" name="M1" style="width: 100%;height: 30px;"
								class="easyui-combobox"
								data-options="panelHeight:'auto' ,required:true,editable:true,valueField:'id',textField:'text',url:'${ctx }/aqzf/jcdy/jcdylist'" />
					</td>
			    </c:if>
				<c:if test="${action eq 'update'}">
				    <td class="width-15 active"><label class="pull-right">检查单元：</label></td>
					<td class="width-35" >
						<input value="${jcbk.m1}" type="text" id="M1" name="M1" style="width: 100%;height: 30px;"
								class="easyui-combobox"
								data-options="panelHeight:'auto' ,required:true,disabled:true,valueField:'text',textField:'text',url:'${ctx }/aqzf/jcdy/jcdylist'" />
					</td>
				</c:if>
				</tr>
				<tr>
				    <td class="width-15 active"><label class="pull-right">检查内容：</label></td>
					<td class="width-35" >
						<input value="${jcbk.m2}" type="text" id="M2" name="M2" style="width:650px;height: 80px;"
								class="easyui-textbox"
								data-options="multiline:true ,required:true,validType:'length[0,500]'" />
					</td>
				</tr>
				<tr>
				<td class="width-15 active"><label class="pull-right">检查依据：</label></td>
					<td class="width-35" >
						<input value="${jcbk.m3}" type="text" id="M3" name="M3" style="width: 100%;height: 30px;"
								class="easyui-textbox"
								data-options="validType:'length[0,500]'" />
					</td>
				</tr>
				<tr>
				    <td class="width-15 active"><label class="pull-right">备注：</label></td>
					<td class="width-35" >
						<input value="${jcbk.m4}" type="text" id="M4" name="M4" style="width:100%;height: 80px;"
								class="easyui-textbox"
								data-options="multiline:true ,validType:'length[0,500]'" />
					</td>
				</tr>
				<c:if test="${not empty jcbk.ID}">
					<input type="hidden" name="ID" value="${jcbk.ID}" />
				</c:if>
				</tbody>
			</table>
       </form>
 
<script type="text/javascript">
	var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
	var validateForm;
    var action = '${action}';
	function doSubmit(){
	   if(action == 'create'){
		 	var options = $("#M1").combobox('options');  
	     	var data = $("#M1").combobox('getData');/* 下拉框所有选项 */  
	     	var value = $("#M1").combobox('getValue');/* 用户输入的值 */  
	     	var b = false;/* 标识是否在下拉列表中找到了用户输入的字符 */  
	     	for (var i = 0; i < data.length; i++) {  
	        	if (data[i][options.valueField] == value) {  
	            	b=true;  
	           	 	break;  
	        	}  
	    	}  
			if(b==false){
					layer.open({title: '提示',offset: 'auto',content: '所选检查单元不存在！',shade: 0 ,time: 2000 });
					return;
			}
		}
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