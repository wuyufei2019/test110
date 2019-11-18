<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>第三方评价管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>

     <form id="inputForm" action="${ctx}/dsffw/pj/${action}"  method="post" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<tr>
				<c:if test="${action eq 'create'}">
					<td class="width-20 active"><label class="pull-right">第三方机构名称：</label></td>
					<td class="width-35" >
						<input value="${pjlist.m1}" type="text" id="M1" name="M1" style="width: 500px;height: 30px;"
								class="easyui-combobox"
								data-options="panelHeight:'auto' ,required:true,editable:true,valueField:'id',textField:'text',url:'${ctx }/dsffw/pj/dnamelist'" />
					</td>
				</c:if>
				<c:if test="${action eq 'update'}">
					<td class="width-20 active"><label class="pull-right">第三方机构名称：</label></td>
					<td class="width-35" >
						<input value="${pjlist.m1}" type="text" id="M1" name="M1" style="width: 500px;height: 30px;"
								class="easyui-combobox"
								data-options="panelHeight:'auto' ,required:true,disabled:true,editable:true,valueField:'id',textField:'text',url:'${ctx }/dsffw/pj/dnamelist2'" />
					</td>
				</c:if>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">评价：</label></td>
					<td class="width-35" >
					<input name="M2" class="easyui-combobox" value="${pjlist.m2 }" style="width: 500px;height: 30px;" data-options="required:true,panelHeight:'auto' ,editable:false,data: [
						        {value:'1',text:'优秀'},
						        {value:'2',text:'良好'},
						        {value:'3',text:'合格'},
						        {value:'4',text:'不合格'},
						        	]  " />
					</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">评价人：</label></td>
					<td class="width-35" >
					<input name="M5" class="easyui-textbox" value="${pjlist.m5 }" style="width: 500px;height: 30px;" data-options="validType:'length[2,20]'" />
					</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">备注：</label></td>
					<td class="width-35">
					<input name="M6" type="text" value="${pjlist.m6 }"   class="easyui-textbox" style="width: 100%;height: 80px;" data-options="multiline:true ,validType:'length[0,100]'">
					</td>
					
				</tr>
				<c:if test="${not empty pjlist.ID}">
					<input type="hidden" name="ID" value="${pjlist.ID}" />
				</c:if>
				</tbody>
			</table>
       </form>
 
<script type="text/javascript">
	var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
	var validateForm;
    var action = '${action}';
	function doSubmit(){
	 	var options = $("#M1").combobox('options');  
     	var data = $("#M1").combobox('getData');/* 下拉框所有选项 */  
     	var value = $("#M1").combobox('getValue');/* 用户输入的值 */  
     	if(action == 'create'){
	     	var b = false;/* 标识是否在下拉列表中找到了用户输入的字符 */  
	     	for (var i = 0; i < data.length; i++) {  
	        	if (data[i][options.valueField] == value) {  
	            	b=true;  
	           	 	break;  
	        	}  
	    	}  
			if(b==false){
					layer.open({title: '提示',offset: 'auto',content: '第三方机构不存在或今年已评价！',shade: 0 ,time: 2000 });
					return;
			}
		}
	    $("#inputForm").submit(); 
	}
	
	$(function(){
		$('#inputForm').form({    
		    onSubmit: function(){    
		    	var isValid = $(this).form('validate');
				return isValid;	// 返回false终止表单提交
		    },    
		    success:function(data){ 
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