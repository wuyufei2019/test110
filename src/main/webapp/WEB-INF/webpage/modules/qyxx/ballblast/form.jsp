<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>抛丸管理</title>
	<meta name="decorator" content="default"/>

</head>
<body>
     <form id="inputForm" action="${ctx}/bis/ballblast/${action}"  method="post" class="form-horizontal" >
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
					<td class="width-20 active"><label class="pull-right">设备型号：</label></td>
					<td class="width-30">
					<input name="M1" id="M1" style="width: 100%;height: 30px;" class="easyui-combobox" value="${entity.m1 }" data-options="required:true,editable:false ,panelHeight:'auto' ,data: [
								        {value:'1',text:'立式'},
								        {value:'2',text:'卧式'},
								        {value:'3',text:'手动'} ]" />
					</td>
                    <td class="width-20 active"><label class="pull-right">台数：</label></td>
                    <td class="width-30"><input name="M2" class="easyui-numberbox" value="${entity.m2 }" style="width: 100%;height: 30px;" data-options="required:false, min:0, precision:0"/></td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">作业区域人数：</label></td>
					<td class="width-30"><input name="M3" class="easyui-numberbox" value="${entity.m3 }" style="width: 100%;height: 30px;" data-options="required:false, min:0, precision:0" /></td>
					<td class="width-20 active"><label class="pull-right">集尘器位置：</label></td>
					<td class="width-30"><input name="M4" class="easyui-textbox" value="${entity.m4 }" style="width: 100%;height: 30px;" data-options="required:false, validType:'length[0,250]'" /></td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">产品材质：</label></td>
					<td class="width-30">
                        <input name="M5" class="easyui-combobox" value="${entity.m5 }" style="width: 100%;height: 30px;" data-options="required:false, editable:false ,panelHeight:'120px' ,data: [
								        {value:'不锈钢',text:'不锈钢'},
								        {value:'黄铜',text:'黄铜'},
								        {value:'铸铁',text:'铸铁'},
								        {value:'铁',text:'铁'},
								        {value:'铸钢',text:'铸钢'},
								        {value:'碳纤维',text:'碳纤维'},
								        {value:'轴承钢',text:'轴承钢'},
								        {value:'钢架构',text:'钢架构'},
								        {value:'铝合金',text:'铝合金'},
								        {value:'钢',text:'钢'},
								        {value:'模具钢',text:'模具钢'},
								        {value:'钢材',text:'钢材'},
								        {value:'钢铁',text:'钢铁'},
								        {value:'不锈钢等硬质合金',text:'不锈钢等硬质合金'},
								        {value:'铝',text:'铝'} ]" />
                    </td>
					<td class="width-20 active"><label class="pull-right">砂丸材质：</label></td>
					<td class="width-30"><input class="easyui-combobox" name="M6" value="${entity.m6 }" style="width: 100%;height: 30px;" data-options="required:false, editable:false ,panelHeight:'120px' ,data: [
								        {value:'钢丸',text:'钢丸'},
								        {value:'玻璃',text:'玻璃'},
								        {value:'钢',text:'钢'},
								        {value:'钢珠',text:'钢珠'},
								        {value:'棕刚玉',text:'棕刚玉'},
								        {value:'不锈钢',text:'不锈钢'},
								        {value:'棕钢砂',text:'棕钢砂'},
								        {value:'氧化锆',text:'氧化锆'},
								        {value:'金钢玉',text:'金钢玉'},
								        {value:'铁',text:'铁'} ]"/></td>
				</tr>
				<tr >
					<td class="width-20 active"><label class="pull-right">清理制度：</label></td>
					<td class="width-30">
					<input class="easyui-combobox" name="M7" value="${entity.m7 }" style="width: 100%;height: 30px;" data-options="required:true,editable:false ,panelHeight:'auto' ,data: [
								        {value:'0',text:'有'},
								        {value:'1',text:'无'} ]"/>
					</td>
					<td class="width-20 active"><label class="pull-right">清理记录：</label></td>
					<td class="width-30">
					<input class="easyui-combobox" name="M8" value="${entity.m8 }" style="width: 100%;height: 30px;" data-options="required:true,editable:false ,panelHeight:'auto' ,data: [
								        {value:'0',text:'有'},
								        {value:'1',text:'无'} ]"/>
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