<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>集体讨论记录管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>

     <form id="inputForm" action="${ctx}/xzcf/ybcf/jttl/${action}"  method="post" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
			  	<c:if test="${action eq 'update'}">
				<tr>
					<td class="width-20 active"><label class="pull-right">记录编号：</label></td>
					<td class="width-30" colspan="3"><input data-options="readonly:'true' " type="text" id="M1" name="M1" class="easyui-textbox" value="${jttl.m1 }"  style="height: 30px;width: 100%" /></td>
				</tr>
				</c:if>
				<tr>
					<td class="width-20 active"><label class="pull-right">案件名称：</label></td>
					<td class="width-30" colspan="3" >
						<input style="width:100%;height: 30px;"  id="M2"name="M2"  class="easyui-textbox" value="${jttl.m2 }" 
						data-options="required:'true',validType:'length[0,250]'" />
				   </td>	
				</tr>
				<tr>
				    <td class="width-20 active"><label class="pull-right">讨论开始时间：</label></td>
					<td class="width-30"><input id="M3" name="M3" class="easyui-datetimebox" value="${jttl.m3 }" style="height: 30px;width: 100%;height: 30px;" data-options="editable:false,showSeconds:false" />
					<td class="width-20 active"><label class="pull-right">讨论结束时间：</label></td>
					<td class="width-30"><input id="M4" name="M4" class="easyui-datetimebox" value="${jttl.m4 }" style="height: 30px;width: 100%;height: 30px;" data-options="editable:false,showSeconds:false" />
				</td>
				<tr>
					<td class="width-20 active"><label class="pull-right">地点：</label></td>
					<td class="width-30" colspan="3" >
						<input style="width:100%;height: 30px;"  id="M5"name="M5"  class="easyui-textbox" value="${jttl.m5 }" 
						data-options="validType:'length[0,100]'" />
				   </td>	
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">主持人：</label></td>
					<td class="width-30"><input type="text" id="M6" name="M6" class="easyui-combobox" value="${jttl.m6 }" style="width: 100%;height: 30px;" data-options="panelHeight:'150px', editable:false ,valueField: 'text', multiple:true, textField: 'text',url:'${ctx}/aqzf/zfry/idlist' "/></td>	
					<td class="width-20 active"><label class="pull-right">汇报人：</label></td>
					<td class="width-30"><input type="text" id="M7" name="M7" class="easyui-combobox" value="${jttl.m7 }" style="width: 100%;height: 30px;" data-options="panelHeight:'150px', editable:false ,valueField: 'text', multiple:true, textField: 'text',url:'${ctx}/aqzf/zfry/idlist' "/></td>															
				</tr>
				<tr> 
					<td class="width-20 active"><label class="pull-right">记录人：</label></td>
					<td class="width-30" colspan="3"><input type="text" id="M8" name="M8" class="easyui-combobox" value="${jttl.m8 }" style="width: 100%;height: 30px;" data-options="panelHeight:'150px', editable:false ,valueField: 'text', multiple:true, textField: 'text',url:'${ctx}/aqzf/zfry/idlist' "/></td>								
				</tr>
				<tr> 
					<td class="width-20 active"><label class="pull-right">出席人员及职务：</label></td>
					<td class="width-30" colspan="3"><input type="text" id="M9" name="M9" class="easyui-combobox" value="${jttl.m9 }" style="width: 100%;height: 60px;" data-options="multiline:true,panelHeight:'150px', editable:false ,valueField: 'text', multiple:true, textField: 'text',url:'${ctx}/aqzf/zfry/xmzwlist' "/></td>								
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">讨论内容：</label></td>
					<td class="width-30" colspan="3"><input name="M10" type="text" value="${jttl.m10 }"   class="easyui-textbox" style="width: 100%;height: 80px;" data-options="multiline:true, validType:'length[0,1000]' "></td>					
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">讨论记录：</label></td>
					<td class="width-30" colspan="3"><input name="M11" type="text" value="${jttl.m11 }"   class="easyui-textbox" style="width: 100%;height: 80px;" data-options="multiline:true, validType:'length[0,1000]' "></td>					
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">结论性意见：</label></td>
					<td class="width-30" colspan="3"><input name="M12" type="text" value="${jttl.m12 }"   class="easyui-textbox" style="width: 100%;height: 80px;" data-options="multiline:true, validType:'length[0,1000]' "></td>					
				</tr>
				
				<input type="hidden" name="ID1" value="${jttl.ID1}" />
				<c:if test="${not empty jttl.ID}">
					<input type="hidden" name="ID" value="${jttl.ID}" />
					<input type="hidden" name="S1"  value="<fmt:formatDate value="${jttl.s1}" pattern="yyyy-MM-dd HH:mm:ss"/>" />
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