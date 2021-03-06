<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>其他信息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	var usertype=${usertype};

	var index = parent.layer.getFrameIndex(window.name); //获取窗口索引

function doSubmit(){
	if(usertype!= '1'){
	 	var options = $("#_qyid").combobox('options');  
     	var data = $("#_qyid").combobox('getData');		/* 下拉框所有选项 */  
     	var value = $("#_qyid").combobox('getValue');	/* 用户输入的值 */  
     	var b = false;		/* 标识是否在下拉列表中找到了用户输入的字符 */  
     	for (var i = 0; i < data.length; i++) {  
        	if (data[i][options.valueField] == value) {  
            	b=true;  
           	 	break;  
        	}  
    	}  
		if(b==false){
				layer.open({title: '提示',offset: 'auto',content: '所选企业不存在！',shade: 0 ,time: 2000 });
				return;
			}
		}
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

     <form id="inputForm" action="${ctx}/bis/otherinformation/${action}"  method="post" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<c:if test="${usertype != '1' and action eq 'create'}">
					<tr>
						<td class="width-20 active"><label class="pull-right">企业名称：</label></td>
						<td class="width-30" colspan="3">
							<input value="${otherinformationlist.ID1 }" id="_qyid" name="ID1" style="width: 100%;height: 30px;"
									class="easyui-combobox"
									data-options="required:'true',valueField: 'id',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson'" />
						</td>
					</tr>
			    </c:if>
			    <c:if test="${usertype != '1' and action eq 'update'}">
					<tr >  
					<td class="width-20 active" ><label class="pull-right">企业名称：</label></td>
					<td class="width-30" colspan="3">
						<input value="${otherinformationlist.ID1 }" id="_qyid" name="ID1" style="width: 100%;height: 30px;" class="easyui-combobox"
									data-options="editable:false, disabled:true, valueField: 'id',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson'" /></td>
				</tr>
				</c:if>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">现场供气：</label></td>
					<td class="width-30"><input name="M1" class="easyui-combobox" style="width: 100%;height: 30px;"
								value="${otherinformationlist.m1 }" data-options="required:'true',panelHeight:'auto', editable:false ,data: [
									{value:'0',text:'是'},
									{value:'1',text:'否'}]" /></td>
					<td class="width-20 active"><label class="pull-right">用途：</label></td>
					<td class="width-30"><input class="easyui-textbox" name="M2" value="${otherinformationlist.m2 }" style="width: 100%;height: 30px;" /></td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">污水处理：</label></td>
					<td class="width-30"><input name="M3" class="easyui-combobox" style="width: 100%;height: 30px;"
												value="${otherinformationlist.m3 }" data-options="required:'true',panelHeight:'auto', editable:false ,data: [
									{value:'0',text:'是'},
									{value:'1',text:'否'}]" /></td>
					<td class="width-20 active"><label class="pull-right">用途：</label></td>
					<td class="width-30"><input class="easyui-textbox" name="M4" value="${otherinformationlist.m4 }" style="width: 100%;height: 30px;"  /></td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">涂装：</label></td>
					<td class="width-30"><input name="M5" class="easyui-combobox" style="width: 100%;height: 30px;"
												value="${otherinformationlist.m5 }" data-options="required:'true',panelHeight:'auto', editable:false ,data: [
									{value:'0',text:'是'},
									{value:'1',text:'否'}]" /></td>
					<td class="width-20 active"><label class="pull-right">用途：</label></td>
					<td class="width-30"><input class="easyui-textbox" name="M6" value="${otherinformationlist.m6 }" style="width: 100%;height: 30px;"  /></td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">电镀：</label></td>
					<td class="width-30"><input name="M7" class="easyui-combobox" style="width: 100%;height: 30px;"
												value="${otherinformationlist.m7 }" data-options="required:'true',panelHeight:'auto', editable:false ,data: [
									{value:'0',text:'是'},
									{value:'1',text:'否'}]" /></td>
					<td class="width-20 active"><label class="pull-right">用途：</label></td>
					<td class="width-30"><input class="easyui-textbox" name="M8" value="${otherinformationlist.m8 }" style="width: 100%;height: 30px;"  /></td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">阴极氧化：</label></td>
					<td class="width-30"><input name="M9" class="easyui-combobox" style="width: 100%;height: 30px;"
												value="${otherinformationlist.m9 }" data-options="required:'true',panelHeight:'auto', editable:false ,data: [
									{value:'0',text:'是'},
									{value:'1',text:'否'}]" /></td>
					<td class="width-20 active"><label class="pull-right">用途：</label></td>
					<td class="width-30"><input class="easyui-textbox" name="M10" value="${otherinformationlist.m10 }" style="width: 100%;height: 30px;"  /></td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">厂房权属：</label></td>
					<td class="width-30"><input name="M11" class="easyui-combobox" style="width: 100%;height: 30px;"
												value="${otherinformationlist.m11 }" data-options="required:'true',panelHeight:'auto', editable:false ,data: [
									{value:'自有',text:'自有'},
									{value:'租赁',text:'租赁'}]" /></td>
					<td class="width-20 active"><label class="pull-right">有协议：</label></td>
					<td class="width-30"><input name="M12" class="easyui-combobox" style="width: 100%;height: 30px;"
												value="${otherinformationlist.m12 }" data-options="required:'true',panelHeight:'auto', editable:false ,data: [
									{value:'0',text:'是'},
									{value:'1',text:'否'}]" /></td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">锅炉：</label></td>
					<td class="width-30"><input name="M13" class="easyui-combobox" style="width: 100%;height: 30px;"
												value="${otherinformationlist.m13 }" data-options="required:'true',panelHeight:'auto', editable:false ,data: [
									{value:'0',text:'是'},
									{value:'1',text:'否'}]" /></td>
					<td class="width-20 active"><label class="pull-right">锅炉原料：</label></td>
					<td class="width-30"><input class="easyui-textbox" name="M14" value="${otherinformationlist.m14 }" style="width: 100%;height: 30px;"  /></td>
				</tr>

				<tr>
					<td class="width-15 active"><label class="pull-right">备注：</label></td>
					<td class="width-85" colspan="3">
						<input name="M15" type="text" value="${cklist.m15 }"   class="easyui-textbox" style="width: 100%;height: 80px;" data-options="multiline:true,validType:'length[1,250]'">
					</td>
				</tr>
				
				<c:if test="${not empty otherinformationlist.ID}">
					<input type="hidden" name="ID" value="${otherinformationlist.ID}" />
					<input type="hidden" name="ID1" value="${otherinformationlist.ID1}" />
					<input type="hidden" name="S1"
						value="<fmt:formatDate value="${otherinformationlist.s1}" pattern="yyyy-MM-dd HH:mm:ss"  />" />
					<input type="hidden" name="S3" value="${otherinformationlist.s3}" />
				</c:if>
				</tbody>
			</table>
       </form>
 
<script type="text/javascript">
	var usertype=${usertype};
	</script>
</body>
</html>