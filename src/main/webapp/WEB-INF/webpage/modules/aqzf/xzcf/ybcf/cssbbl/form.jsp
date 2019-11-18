<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>陈述申辩笔录管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>

     <form id="inputForm" action="${ctx}/xzcf/ybcf/cssbbl/${action}"  method="post" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<tr>
					<td class="width-20 active"><label class="pull-right">案件名称：</label></td>
					<td class="width-30" colspan="3" >
						<input style="width:100%;height: 30px;"  id="M11" name="M11" class="easyui-textbox" value="${cssbbl.m11 }" 
						data-options="required:'true',validType:'length[0,100]'" />
				   </td>	
				</tr>
				<tr>
				    <td class="width-20 active"><label class="pull-right">申辩开始时间：</label></td>
					<td class="width-30"><input id="M1" name="M1" class="easyui-datetimebox" value="${cssbbl.m1 }" style="height: 30px;width: 100%;height: 30px;" data-options="editable:false,showSeconds:false" />
					<td class="width-20 active"><label class="pull-right">申辩结束时间：</label></td>
					<td class="width-30"><input id="M2" name="M2" class="easyui-datetimebox" value="${cssbbl.m2 }" style="height: 30px;width: 100%;height: 30px;" data-options="editable:false,showSeconds:false" />
				</td>
				<tr>
					<td class="width-20 active"><label class="pull-right">地点：</label></td>
					<td class="width-30" colspan="3" >
						<input style="width:100%;height: 30px;"  id="M3"name="M3"  class="easyui-textbox" value="${cssbbl.m3 }" 
						data-options="validType:'length[0,100]'" />
				   </td>	
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">陈述申辩人：</label></td>
					<td class="width-30">
						<input style="width:100%;height: 30px;"  id="M4"name="M4"  class="easyui-textbox" value="${cssbbl.m4 }" data-options="required:'true',validType:'length[0,25]'" />
				   </td>	
				   <td class="width-20 active"><label class="pull-right">性别：</label></td>
				   <td class="width-30">
				   <input name="M5" class="easyui-combobox" value="${cssbbl.m5 }" style="width: 100%;height: 30px;" data-options="panelHeight:'auto', data:[{value:'男',text:'男'},{value:'女',text:'女'}]"/>
				   </td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">职务：</label></td>
					<td class="width-30" colspan="3">
						<input style="width:100%;height: 30px;"  id="M6"name="M6"  class="easyui-textbox" value="${cssbbl.m6 }" data-options="validType:'length[0,50]'" />
				   </td>	
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">工作单位：</label></td>
					<td class="width-30" colspan="3">
						<input style="width:100%;height: 30px;"  id="M7"name="M7"  class="easyui-textbox" value="${cssbbl.m7 }" data-options="validType:'length[0,100]'" />
				   </td>	
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">电话：</label></td>
					<td class="width-30">
						<input style="width:100%;height: 30px;"  id="M8"name="M8"  class="easyui-textbox" value="${cssbbl.m8 }" data-options="validType:'length[0,50]'" />
				   </td>	
				   <td class="width-20 active"><label class="pull-right">邮编：</label></td>
				   <td class="width-30">
						<input style="width:100%;height: 30px;"  id="M10"name="M10"  class="easyui-textbox" value="${cssbbl.m10 }" data-options="validType:'ZIP'"/>
				   </td>	
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">联系地址：</label></td>
					<td class="width-30" colspan="3">
						<input style="width:100%;height: 30px;"  id="M9"name="M9"  class="easyui-textbox" value="${cssbbl.m9 }" data-options="validType:'length[0,100]'" />
				   </td>	
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">承办人：</label></td>
					<td class="width-30"><input type="text" id="M12" name="M12" class="easyui-combobox" value="${cssbbl.m12 }" style="width: 100%;height: 30px;" data-options="panelHeight:'170px',editable:true ,valueField: 'id',textField: 'text',url:'${ctx}/aqzf/zfry/idlist' "/></td>
					<td class="width-20 active"><label class="pull-right">证号：</label></td>
					<td class="width-30"><input type="text" id="M13" name="M13" class="easyui-textbox" style="height: 30px; width: 100%;" value="${cssbbl.m13 }" data-options="readonly:true " /></td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">承办人：</label></td>
					<td class="width-30"><input type="text" id="M14" name="M14" class="easyui-combobox" value="${cssbbl.m14 }" style="width: 100%;height: 30px;" data-options="panelHeight:'170px',editable:true ,valueField: 'id',textField: 'text',url:'${ctx}/aqzf/zfry/idlist' "/></td>
					<td class="width-20 active"><label class="pull-right">证号：</label></td>
					<td class="width-30"><input type="text" id="M15" name="M15" class="easyui-textbox" style="height: 30px; width: 100%;" value="${cssbbl.m15 }" data-options="readonly:true " /></td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">记录人：</label></td>
					<td class="width-30"><input type="text" id="M17" name="M17" class="easyui-combobox" value="${cssbbl.m17 }" style="width: 100%;height: 30px;" data-options="panelHeight:'170px',editable:true ,valueField: 'text',textField: 'text',url:'${ctx}/aqzf/zfry/idlist' "/></td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">陈述申辩记录：</label></td>
					<td class="width-30" colspan="3"><input name="M16" type="text" value="${cssbbl.m16 }"   class="easyui-textbox" style="width: 100%;height: 80px;" data-options="multiline:true, validType:'length[0,2500]' "></td>					
				</tr>
				
				<input type="hidden" name="ID1" value="${cssbbl.ID1}" />
				<c:if test="${not empty cssbbl.ID}">
					<input type="hidden" name="ID" value="${cssbbl.ID}" />
					<input type="hidden" name="S1"  value="<fmt:formatDate value="${cssbbl.s1}" pattern="yyyy-MM-dd HH:mm:ss"/>" />
				</c:if>
				</tbody>
			</table>
       </form>
 
<script type="text/javascript">
	var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
	function doSubmit(){
		$('#M12').combobox('setValue',$('#M12').combobox('getText'));
		$('#M14').combobox('setValue',$('#M14').combobox('getText'));
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
	
	//下拉关联信息
	$("#M12").combobox({
		onSelect: function(){
			var id=$("#M12").combobox('getValue');
			$.ajax({
			type:'get',
			url:ctx+"/aqzf/zfry/findidcard/"+id,
			success: function(data){
				$("#M13").textbox('setValue',data);
			}
		});
			
		}
	});

	$("#M14").combobox({
		onSelect: function(){
			var id=$("#M14").combobox('getValue');
			$.ajax({
			type:'get',
			url:ctx+"/aqzf/zfry/findidcard/"+id,
			success: function(data){
				$("#M15").textbox('setValue',data);
			}
		});
		}
	});
	
</script>
</body>
</html>