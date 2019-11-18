<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>员工体检信息</title>
	<meta name="decorator" content="default"/>
</head>
<body>

     <form id="inputForm" action="${ctx}/bis/ygtjxx/${action}"  method="post" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<c:if test="${usertype != '1' and action eq 'create'}">
					<tr>
						<td class="width-15 active"><label class="pull-right">企业名称：</label></td>
						<td class="width-35" colspan="3">
							<input value="${ygtjlist.ID1 }" id="_qyid" name="ID1" style="width: 100%;height: 30px;"
									class="easyui-combobox"
									data-options="required:'true',valueField: 'id',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson' " />
						</td>
					</tr>
			    </c:if>
			    <c:if test="${usertype != '1' and action eq 'update'}">
					<tr >  
					<td class="width-15 active" ><label class="pull-right">企业名称：</label></td>
					<td class="width-35" colspan="3">
						<input value="${ygtjlist.ID1 }" id="_qyid" name="ID1" style="width: 100%;height: 30px;"
									class="easyui-combobox"
									data-options="editable:false, disabled:true, valueField: 'id',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson' " 
								/></td>
				</tr>
				</c:if>
				<tr>
					<td class="width-20 active"><label class="pull-right">员工姓名：</label></td>
					<td class="width-30"><input id="M7" name="M7" style="width: 100%;height: 30px;" class="easyui-combogrid"
								value="${ygtjlist.m7 }"
								data-options="required:'true',panelWidth:350,fitColumns : true,editable:true,idField: 'id',textField: 'text',url:'${ctx}/bis/ygxx/ygxxlist/${ygtjlist.ID1}',
								columns:[[
										   {field:'text',title:'姓名',width:100},
										   {field:'sex',title:'性别',width:60},
										   {field:'idcard',title:'身份证号',width:200}
										   ]],
							   onClickRow: function(rowIndex,rowData){
							   				var yg_name = rowData.text;
										  	$.ajax({
									           url:'${ctx}/bis/ygtjxx/isexist?yg_name='+yg_name,
									           type : 'POST',
									           success: function (data){
									               if (data == 'yes') {
									               	   layer.msg('已存在该用户体检记录,请到列表页进行操作',{time: 3000},function(){
									               	   	   parent.layer.close(index);
									               	   });
									               } else {
									               	   $('#M1').textbox('clear');
										  			   $('#M1').textbox('setValue', rowData.idcard);
									               }
									           }
									        });
										  	
										   }" /></td>
					<td class="width-20 active"><label class="pull-right">身份证号：</label></td>
					<td class="width-30"><input id="M1" name="M1" class="easyui-textbox" value="${ygtjlist.m1 }"  style="width: 100%;height: 30px;" data-options="required:'true',validType:'idCode'"/></td>
				<tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">接触危害因素：</label></td>
					<td class="width-85" colspan="3">
					<input id="M9" name="M9" type="text" value="${ygtjlist.m9}"   class="easyui-combotree" style="width: 100%;height: 30px;" data-options="method: 'get',multiple:true,panelHeight:'150',url:'${ctx}/bis/ygtjxx/whys/json'">
					</td>					
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">体检类型：</label></td>
					<td class="width-30"><input id="M2" name="M2" class="easyui-combobox" value="${ygtjlist.m2 }"  style="width: 100%;height: 30px;"
								data-options=" editable:false ,valueField: 'text', textField: 'text',panelHeight:'auto', data: [
       	        				{value:'定期',text:'定期'},
				       		 	{value:'岗前',text:'岗前'},
				        		{value:'岗中',text:'岗中'},
				        		{value:'离岗',text:'离岗'},
				        		{value:'其他',text:'其他'}       	        		
       	        			]  ,"/></td>
					<td class="width-20 active"><label class="pull-right">体检医院：</label></td>
					<td class="width-30" >
					<input id="M4" name="M4" class="easyui-textbox" value="${ygtjlist.m4 }" style="width: 100%;height: 30px;" data-options="editable:true,validType:'length[0,100]'" />
					</td>	       	        			
				<tr>
					<td class="width-20 active"><label class="pull-right">体检日期：</label></td>
					<td class="width-30"><input id="M3" name="M3" class="easyui-datebox" value="${ygtjlist.m3 }" style="width: 100%;height: 30px;" data-options="editable:false " /></td>
					<td class="width-20 active"><label class="pull-right">下次体检日期：</label></td>
					<td class="width-30"><input id="M8" name="M8" class="easyui-datebox" value="${ygtjlist.m8 }" style="width: 100%;height: 30px;" data-options="editable:false " /></td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">体检结果：</label></td>
					<td class="width-85" colspan="3">
					<input name="M10" type="text" value="${ygtjlist.m10}"   class="easyui-textbox" style="width: 100%;height: 80px;" data-options="multiline:true,validType:'length[0,500]'">
					</td>					
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">体检结论：</label></td>
					<td class="width-85" colspan="3">
					<input name="M5" type="text" value="${ygtjlist.m5}"   class="easyui-textbox" style="width: 100%;height: 80px;" data-options="multiline:true,validType:'length[0,500]'">
					</td>					
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">备注：</label></td>
					<td class="width-85" colspan="3">
					<input name="M6" type="text" value="${ygtjlist.m6}"   class="easyui-textbox" style="width: 100%;height: 80px;" data-options="multiline:true,validType:'length[0,500]'">
					</td>					
				</tr>
				
				<c:if test="${not empty ygtjlist.ID}">
					<input type="hidden" name="ID" value="${ygtjlist.ID}" />
					<input type="hidden" name="ID1" value="${ygtjlist.ID1}" />
					<input type="hidden" name="S1"
						value="<fmt:formatDate value="${ygtjlist.s1}" pattern="yyyy-MM-dd HH:mm:ss"  />" />
					<input type="hidden" name="S3" value="${ygtjlist.s3}" />
				</c:if>
				</tbody>
			</table>
       </form>
 
<script type="text/javascript">
var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
var usertype=${usertype};
	
function doSubmit(){
	$("#M7").combogrid('setValue',$("#M7").combogrid('getText'));
 	$("#M9").combotree('setValue',$("#M9").combotree('getText'));
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