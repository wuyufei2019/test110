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
				<tr>
					<td class="width-20 active"><label class="pull-right">体检类型：</label></td>
					<td class="width-30"><input id="M3" name="M3" class="easyui-combobox" value="${ygtjlist.m3 }"  style="width: 100%;height: 30px;"
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
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">接触危害因素：</label></td>
					<td class="width-85" colspan="3">
					<input id="M5" name="M5" type="text" value="${ygtjlist.m5}"   class="easyui-combotree" style="width: 100%;height: 30px;" data-options="method: 'get',multiple:true,panelHeight:'150',url:'${ctx}/bis/ygtjxx/whys/json'">
					</td>					
				</tr>	       	        			
				<tr>
					<td class="width-20 active"><label class="pull-right">体检日期：</label></td>
					<td class="width-30"><input id="M1" name="M1" class="easyui-datebox" value="${ygtjlist.m1 }" style="width: 100%;height: 30px;" data-options="editable:false " /></td>
					<td class="width-20 active"><label class="pull-right">下次体检日期：</label></td>
					<td class="width-30"><input id="M2" name="M2" class="easyui-datebox" value="${ygtjlist.m2 }" style="width: 100%;height: 30px;" data-options="editable:false " /></td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">体检结果：</label></td>
					<td class="width-85" colspan="3">
					<input name="M6" type="text" value="${ygtjlist.m6}"   class="easyui-textbox" style="width: 100%;height: 80px;" data-options="multiline:true,validType:'length[0,500]'">
					</td>					
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">体检结论：</label></td>
					<td class="width-85" colspan="3">
					<input name="M7" type="text" value="${ygtjlist.m7}"   class="easyui-textbox" style="width: 100%;height: 80px;" data-options="multiline:true,validType:'length[0,500]'">
					</td>
				</tr>
				<c:choose>
					<c:when test="${action == 'createnew' }">
						<input type="hidden" name="ID1" value="${ID1}" />
					</c:when>
					<c:otherwise>
						<input type="hidden" name="ID1" value="${ygtjlist.ID1}" />
					</c:otherwise>
					</c:choose>
				<c:if test="${not empty ygtjlist.ID}">
					<input type="hidden" name="ID" value="${ygtjlist.ID}" />
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
	$("#M5").combotree('setValue',$("#M5").combotree('getText'));
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