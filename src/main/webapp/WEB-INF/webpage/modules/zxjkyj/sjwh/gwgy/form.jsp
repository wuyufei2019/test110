<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>高危工艺管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>

     <form id="inputForm" action="${ctx}/zxjkyj/gwgy/${action}"  method="post" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
			  <c:if test="${usertype eq '9' and action eq 'create'}">
					<tr>
						<td class="width-20 active"><label class="pull-right">企业名称：</label></td>
						<td class="width-30" colspan="3">
							<input value="${gwgy.ID1 }" id="ID1" name="ID1" style="width: 100%;height: 30px;"
									class="easyui-combobox"
									data-options="required:'true',valueField: 'id',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson'" />
						</td>
					</tr>
			    </c:if>
			    <c:if test="${usertype eq '9' and action eq 'update'}">
					<tr >  
					<td class="width-20 active" ><label class="pull-right">企业名称：</label></td>
					<td class="width-30" colspan="3">
						<input value="${gwgy.ID1 }" id="ID1" name="ID1" style="width: 100%;height: 30px;"
									class="easyui-combobox"
									data-options="editable:false, disabled:true, valueField: 'id',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson'" /></td>
				</tr>
				</c:if>
			  <tr>
			     <td class="width-20 active"><label class="pull-right">高危工艺名称：</label></td>
			     <td class="width-30" colspan="3">
			     <input name="M1" class="easyui-textbox" value="${gwgy.m1 }" style="width: 100%;height: 30px;" 
			              data-options="required:'true',validType:'length[0,50]'" />
			     </td>
			  </tr>
			  <tr>
			  	<td class="width-20 active"><label class="pull-right">高危工艺类型：</label></td>
					<td class="width-30" colspan="3"><div>
					<input name="M8" class="easyui-combobox" value="${gwgy.m8 }" style="width:100%;height: 30px;"
								data-options="editable:false ,valueField: 'id',textField: 'text',url:'${ctx}/tcode/dict/gwgy' " />
						</div></td>
			  </tr>
			  <tr>
			  <td class="width-20 active"><label class="pull-right">位号：</label></td>
					<td class="width-30" colspan="3">
						<input name="M5"  class="easyui-textbox" value="${gwgy.m5 }" style="width: 100%;height: 30px;"
								data-options="required:'true',validType:['englishOrNum','length[0,50]']" />
					</td>
			  </tr>
			  <tr>
				<td class="width-20 active"><label class="pull-right">物料名称：</label></td>
				<td class="width-30"><input class="easyui-textbox" name="M2" value="${gwgy.m2 }" style="width: 100%;height: 30px;" data-options="validType:'length[0,50]'" /></td>
				<td class="width-20 active"><label class="pull-right">类型：</label></td>
				<td class="width-30"><input name="M4" class="easyui-combobox" value="${gwgy.m4 }" style="width: 100%;height: 30px;" data-options="required:'true',panelHeight:'auto' ,editable:false,data: [
						        {value:'1',text:'反应釜'},
						        {value:'2',text:'反应塔'}]  "/></td>
			</tr>
			<tr>
				<td class="width-20 active"><label class="pull-right">罐径（m）：</label></td>
				<td class="width-30"><input class="easyui-textbox" name="M6" value="${gwgy.m6 }" style="width: 100%;height: 30px;" data-options="validType:'mone'" /></td>
				<td class="width-20 active"><label class="pull-right">罐高（m）：</label></td>
				<td class="width-30"><input class="easyui-textbox" name="M7" value="${gwgy.m7 }" style="width: 100%;height: 30px;" data-options="validType:'mone'" /></td>
			</tr>
			 <tr>
			  <td class="width-20 active"><label class="pull-right">容积（㎥）：</label></td>
					<td class="width-30" colspan="3">
						<input name="M3"  class="easyui-textbox" value="${gwgy.m3 }" style="width: 100%;height: 30px;"
								data-options="validType:'mone'" />
					</td>
			  </tr>
			<tr>
				<td class="width-20 active"><label class="pull-right">高液位预警值（m）：</label></td>
				<td class="width-30"><input class="easyui-textbox" name="Level1" value="${gwgy.level1 }" style="width: 100%;height: 30px;" data-options="validType:'mone'" /></td>
				<td class="width-20 active"><label class="pull-right">低液位预警值（m）：</label></td>
				<td class="width-30"><input class="easyui-textbox" name="Level2" value="${gwgy.level2 }" style="width: 100%;height: 30px;" data-options="validType:'mone'" /></td>
			</tr>
			<tr>
				<td class="width-20 active"><label class="pull-right">釜内高温度预警（℃）：</label></td>
				<td class="width-30"><input class="easyui-textbox" name="Temperature1" value="${gwgy.temperature1 }" style="width: 100%;height: 30px;" data-options="validType:'mone'" /></td>
				<td class="width-20 active"><label class="pull-right">釜内低温度预警（℃）：</label></td>
				<td class="width-30"><input class="easyui-textbox" name="Temperature2" value="${gwgy.temperature2 }" style="width: 100%;height: 30px;" data-options="validType:'mone'" /></td>
			</tr>
			<tr>
				<td class="width-20 active"><label class="pull-right">夹套高温度预警（℃）：</label></td>
				<td class="width-30"><input class="easyui-textbox" name="Temperature3" value="${gwgy.temperature3 }" style="width: 100%;height: 30px;" data-options="validType:'mone'" /></td>
				<td class="width-20 active"><label class="pull-right">夹套低温度预警（℃）：</label></td>
				<td class="width-30"><input class="easyui-textbox" name="Temperature4" value="${gwgy.temperature4 }" style="width: 100%;height: 30px;" data-options="validType:'mone'" /></td>
			</tr>
			<tr>
				<td class="width-20 active"><label class="pull-right">高压力预警值（MPa）：</label></td>
				<td class="width-30"><input class="easyui-textbox" name="Pressure1" value="${gwgy.pressure1 }" style="width: 100%;height: 30px;" data-options="validType:'mone'" /></td>
				<td class="width-20 active"><label class="pull-right">低压力预警值（MPa）：</label></td>
				<td class="width-30"><input class="easyui-textbox" name="Pressure2" value="${gwgy.pressure2 }" style="width: 100%;height: 30px;" data-options="validType:'mone'" /></td>
			</tr>
			<tr>
				<td class="width-20 active"><label class="pull-right">高流量预警（m³/h）：</label></td>
				<td class="width-30"><input class="easyui-textbox" name="Flux1" value="${gwgy.flux1 }" style="width: 100%;height: 30px;" data-options="validType:'mone'" /></td>
				<td class="width-20 active"><label class="pull-right">低流量预警（m³/h）：</label></td>
				<td class="width-30"><input class="easyui-textbox" name="Flux2" value="${gwgy.flux2 }" style="width: 100%;height: 30px;" data-options="validType:'mone'" /></td>
			</tr>
			<c:if test="${usertype eq '9'}">
			<tr>
				<td class="width-20 active"><label class="pull-right">液位点号 ：</label></td>
				<td class="width-30"><input class="easyui-textbox" name="R1" value="${gwgy.r1  }" style="width: 100%;height: 30px;" data-options="validType:'length[0,10]'" /></td>
				<td class="width-20 active"><label class="pull-right">温度点号：</label></td>
				<td class="width-30"><input class="easyui-textbox" name="R2" value="${gwgy.r2 }" style="width: 100%;height: 30px;" data-options="validType:'length[0,10]'" /></td>
			</tr>
			<tr>
				<td class="width-20 active"><label class="pull-right">压力点号 ：</label></td>
				<td class="width-30"><input class="easyui-textbox" name="R3" value="${gwgy.r3 }" style="width: 100%;height: 30px;" data-options="validType:'length[0,10]'" /></td>
				<td class="width-20 active"><label class="pull-right">流量点号 ：</label></td>
				<td class="width-30"><input class="easyui-textbox" name="R4" value="${gwgy.r4 }" style="width: 100%;height: 30px;" data-options="validType:'length[0,10]'" /></td>
			</tr>
			</c:if>
			<c:if test="${not empty gwgy.ID}">
				<input type="hidden" name="ID" value="${gwgy.ID}" />
			</c:if>
				</tbody>
			</table>
       </form>
 
<script type="text/javascript">
	var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
	var validateForm;
    var action = '${action}';
    var usertype = '${usertype}';
	function doSubmit(){
	if(usertype=='9'&&action=='create'){
	var options = $("#ID1").combobox('options');  
     	var data = $("#ID1").combobox('getData');/* 下拉框所有选项 */  
     	var value = $("#ID1").combobox('getValue');/* 用户输入的值 */  
     	var b = false;/* 标识是否在下拉列表中找到了用户输入的字符 */  
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