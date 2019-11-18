<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>现场供气管理</title>
	<meta name="decorator" content="default"/>

</head>
<body>
     <form id="inputForm" action="${ctx}/bis/fieldsupply/${action}"  method="post" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
                  <c:if test="${usertype != '1' and action eq 'create'}">
                      <tr>
                          <td class="width-20 active"><label class="pull-right">企业名称：</label></td>
                          <td class="width-30" colspan="3">
                              <input value="${entity.id1 }" id="_qyid" name="id1" style="width: 100%;height: 30px;"
                                     class="easyui-combobox"
                                     data-options="required:'true',valueField: 'id',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson'" />
                          </td>
                      </tr>
                  </c:if>
                  <c:if test="${usertype != '1' and action eq 'update'}">
                      <tr >
                          <td class="width-20 active" ><label class="pull-right">企业名称：</label></td>
                          <td class="width-30" colspan="3">
                              <input value="${entity.id1 }" id="_qyid" name="id1" style="width: 100%;height: 30px;" class="easyui-combobox"
                                     data-options="editable:false, disabled:true, valueField: 'id',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson'" /></td>
                      </tr>
                  </c:if>
				
				<tr >
					<td class="width-20 active"><label class="pull-right">介质：</label></td>
					<td class="width-30" colspan="3">
					<input name="M1" id="M1" style="width: 100%;height: 30px;" class="easyui-textbox" value="${entity.m1 }" data-options="required:true,validType:'length[0,100]'" />
					</td>
				</tr>
				<tr>
                    <td class="width-20 active"><label class="pull-right">容积（m³）：</label></td>
                    <td class="width-30"><input name="M2" class="easyui-textbox" value="${entity.m2 }" style="width: 100%;height: 30px;" data-options="required:false, validType:'number'"/></td>
					<td class="width-20 active"><label class="pull-right">用量（m³/月）：</label></td>
					<td class="width-30"><input name="M3" class="easyui-textbox" value="${entity.m3 }" style="width: 100%;height: 30px;" data-options="required:false, validType:'number'" /></td>
				</tr>
				<tr>
                    <td class="width-20 active"><label class="pull-right">气站性质：</label></td>
                    <td class="width-30"><input name="M4" class="easyui-combobox" value="${entity.m4 }" style="width: 100%;height: 30px;" data-options="required:false, editable:false ,panelHeight:'auto' ,data: [
								        {value:'0',text:'自建'},
								        {value:'1',text:'租用'} ]" /></td>
					<td class="width-20 active"><label class="pull-right">供气单位：</label></td>
					<td class="width-30">
                        <input name="M5" class="easyui-textbox" value="${entity.m5 }" style="width: 100%;height: 30px;" data-options="required:false, validType:'length[0,100]'" />
                    </td>
				</tr>
                <tr>
                    <td class="width-20 active"><label class="pull-right">备注：</label></td>
                    <td class="width-30" colspan="3">
                        <input name="M9" class="easyui-textbox" value="${entity.m9 }" style="width: 100%;height: 30px;" data-options="required:false, validType:'length[0,500]'" />
                    </td>
                </tr>

                <tr style="height: 10px"></tr>
				<tr >
                    <td class="width-20 active"><label class="pull-right">实施日期：</label></td>
                    <td class="width-30" ><input name="m8_1" class="easyui-datebox" style="width: 100%;height: 30px;" value="${entity.m8_1 }" data-options="editable:false" /></td>
                    <td class="width-20 active"><label class="pull-right">备案编号：</label></td>
                    <td class="width-30">
                        <input name="m7_1" class="easyui-textbox" value="${entity.m7_1 }" style="width: 100%;height: 30px;" data-options="required:false, validType:'length[0,100]'" />
                    </td>
				</tr>
                <tr >
                    <td class="width-20 active"><label class="pull-right">安评单位：</label></td>
                    <td class="width-30" colspan="3">
                        <input name="m6_1" class="easyui-textbox" value="${entity.m6_1 }" style="width: 100%;height: 30px;" data-options="required:false, validType:'length[0,250]'" />
                    </td>
                </tr>
                <tr style="height: 7px"></tr>
				<tr >
                    <td class="width-20 active"><label class="pull-right">实施日期：</label></td>
                    <td class="width-30" ><input name="m8_2" class="easyui-datebox" style="width: 100%;height: 30px;" value="${entity.m8_2 }" data-options="editable:false" /></td>
                    <td class="width-20 active"><label class="pull-right">备案编号：</label></td>
                    <td class="width-30">
                        <input name="m7_2" class="easyui-textbox" value="${entity.m7_2 }" style="width: 100%;height: 30px;" data-options="required:false, validType:'length[0,100]'" />
                    </td>
				</tr>
                <tr >
                    <td class="width-20 active"><label class="pull-right">安评单位：</label></td>
                    <td class="width-30" colspan="3">
                        <input name="m6_2" class="easyui-textbox" value="${entity.m6_2 }" style="width: 100%;height: 30px;" data-options="required:false, validType:'length[0,250]'" />
                    </td>
                </tr>
                <tr style="height: 7px"></tr>
				<tr >
                    <td class="width-20 active"><label class="pull-right">实施日期：</label></td>
                    <td class="width-30" ><input name="m8_3" class="easyui-datebox" style="width: 100%;height: 30px;" value="${entity.m8_3 }" data-options="editable:false" /></td>
                    <td class="width-20 active"><label class="pull-right">备案编号：</label></td>
                    <td class="width-30">
                        <input name="m7_3" class="easyui-textbox" value="${entity.m7_3 }" style="width: 100%;height: 30px;" data-options="required:false, validType:'length[0,100]'" />
                    </td>
				</tr>
                <tr >
                    <td class="width-20 active"><label class="pull-right">安评单位：</label></td>
                    <td class="width-30" colspan="3">
                        <input name="m6_3" class="easyui-textbox" value="${entity.m6_3 }" style="width: 100%;height: 30px;" data-options="required:false, validType:'length[0,250]'" />
                    </td>
                </tr>

				<c:if test="${not empty entity.ID}">
					<input type="hidden" name="ID" value="${entity.ID}" />
					<input type="hidden" name="id1" value="${entity.id1}" />
					<input type="hidden" name="S1"
						value="<fmt:formatDate value="${entity.s1}" pattern="yyyy-MM-dd HH:mm:ss"  />" />
					<input type="hidden" name="S3" value="${entity.s3}" />
				</c:if>
				</tbody>
			</table>

		  	
       </form>


<script type="text/javascript">
	var usertype=${usertype};


	var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
	var validateForm;

function doSubmit(){
	if(usertype!= '1'){
	 	var options = $("#_qyid").combobox('options');  
     	var data = $("#_qyid").combobox('getData');/* 下拉框所有选项 */  
     	var value = $("#_qyid").combobox('getValue');/* 用户输入的值 */  
     	var b = false;/* 标识是否在下拉列表中找到了用户输入的字符 */  
     	for (var i = 0; i < data.length; i++) {  
        	if (data[i][options.valueField] == value) {  
            	b=true;  
           	 	break;  
        	}  
    	}  
		if(b==false){
				layer.open({icon:1,title: '提示',offset: 'auto',content: '所选企业不存在！',shade: 0 ,time: 2000 });
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


</body>
</html>