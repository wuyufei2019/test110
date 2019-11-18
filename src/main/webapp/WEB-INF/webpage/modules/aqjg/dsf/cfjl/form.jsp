<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>第三方处罚管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>

     <form id="inputForm" action="${ctx}/dsffw/cfjl/${action}"  method="post" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<tr>
				<c:if test="${action eq 'create'}">
					<td class="width-15 active"><label class="pull-right">第三方机构名称：</label></td>
					<td class="width-35" colspan="3">
						<input value="${cfjllist.dname}" type="text" id="Dname" name="Dname" style="width: 100%;height: 30px;"
								class="easyui-combobox"
								data-options="required:true,editable:true,valueField:'text',textField:'text',url:'${ctx }/dsffw/fwxmbb/dwnamelist'" />
					</td>
			    </c:if>
				<c:if test="${action eq 'update'}">
				    <td class="width-15 active"><label class="pull-right">第三方机构名称：</label></td>
					<td class="width-35" colspan="3">
						<input value="${cfjllist.dname}" type="text" id="Dname" name="Dname" style="width: 100%;height: 30px;"
								class="easyui-combobox"
								data-options="required:true,disabled:true,editable:true,valueField:'text',textField:'text',url:'${ctx }/dsffw/fwxmbb/dwnamelist'" />
					</td>
				</c:if>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">处罚类型：</label></td>
					<td class="width-35"><input name="M2" class="easyui-combobox" value="${cfjllist.m2 }" style="width: 100%;height: 30px;" data-options="required:false,panelHeight:'auto' ,editable:false,data: [
						        {value:'1',text:'警告'},
						        {value:'2',text:'罚款'},
						        {value:'3',text:'没收违法所得、没收非法开采的煤炭产品、采掘设备'},
						        {value:'4',text:'责令停产停业整顿、责令停产停业、责令停止建设、责令停止施工'},
						        {value:'5',text:'暂扣或者吊销有关许可证，暂停或者撤销有关执业资格、岗位证书'},
						        {value:'6',text:'关闭'},
						        {value:'7',text:'拘留'}
						        	]  "/></td>
					
					<td class="width-15 active"><label class="pull-right">处罚时间：</label></td>
					<td class="width-35"><input id="S1" name="S1" style="width: 100%;height: 30px;" class="easyui-datebox" value="<fmt:formatDate value="${cfjllist.s1 }"/>" style="width:198px;" data-options="required:true "/></td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">处罚内容：</label></td>
					<td class="width-85" colspan="3">
					<input name="M3" type="text" value="${cfjllist.m3 }"   class="easyui-textbox" style="width: 100%;height: 80px;" data-options="multiline:true ,validType:'length[0,200]', required:true">
					</td>
					
				</tr>

				<tr>
					<td class="width-15 active"><label class="pull-right">备注：</label></td>
					<td class="width-85" colspan="3">
					<input name="M5" type="text" value="${cfjllist.m5 }"   class="easyui-textbox" style="width: 100%;height: 80px;" data-options="multiline:true ,validType:'length[0,200]'">
					</td>
					
				</tr>
				
				<c:if test="${not empty cfjllist.ID}">
					<input type="hidden" name="ID" value="${cfjllist.ID}" />
				</c:if>
				</tbody>
			</table>
       </form>
 
<script type="text/javascript">
	var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
	var validateForm;
    var action = '${action}';
	function doSubmit(){
	 	var options = $("#Dname").combobox('options');  
     	var data = $("#Dname").combobox('getData');/* 下拉框所有选项 */  
     	var value = $("#Dname").combobox('getValue');/* 用户输入的值 */  
     	var b = false;/* 标识是否在下拉列表中找到了用户输入的字符 */  
     	for (var i = 0; i < data.length; i++) {  
        	if (data[i][options.valueField] == value) {  
            	b=true;  
           	 	break;  
        	}  
    	}  
		if(b==false){
				layer.open({title: '提示',offset: 'auto',content: '所选第三方机构不存在！',shade: 0 ,time: 2000 });
				return;
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