<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>三同时管理</title>
<meta name="decorator" content="default" />
</head>
<body>
   <form id="inputForm" action="${ctx}/hjbh/stsgl/${action}"  method="post" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right">企业名称：</label></td>
					<td class="width-35"><input type="text" id="M1" name="M1" class="easyui-textbox" value="${entity.m1}"  style="height: 30px;width: 100%" data-options="required:'true'"/></td>
					<td class="width-15 active"><label class="pull-right">所在乡镇：</label></td>
					<td class="width-35"><input type="text" id="M2" name="M2" class="easyui-textbox" value="${entity.m2}"  style="height: 30px;width: 100%" data-options=""/></td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">项目名称：</label></td>
					<td class="width-35"><input type="text" id="M3" name="M3" class="easyui-textbox" value="${entity.m3}"  style="height: 30px;width: 100%" data-options="required:'true'"/></td>
					<td class="width-15 active"><label class="pull-right">建设地址：</label></td>
					<td class="width-35"><input data-options="" type="text" id="M4" name="M4" class="easyui-textbox" value="${entity.m4}"  style="height: 30px;width: 100%" /></td>
				</tr>		
				<tr>
					<td class="width-15 active"><label class="pull-right">产能(万只/年)：</label></td>
					<td class="width-35"><input type="text" id="M5" name="M5" class="easyui-numberbox" value="${entity.m5}"  style="height: 30px;width: 100%" data-options="min:0,precision:2"/></td>
					<td class="width-15 active"><label class="pull-right">劳动定员：</label></td>
					<td class="width-35"><input data-options="" type="text" id="M6" name="M6" class="easyui-numberbox" value="${entity.m6}"  style="height: 30px;width: 100%" data-options="min:0"/></td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">环评批复意见文号：</label></td>
					<td class="width-35"><input type="text" id="M3" name="M7" class="easyui-textbox" value="${entity.m7}"  style="height: 30px;width: 100%" data-options=""/></td>
					<td class="width-15 active"><label class="pull-right">批复时间：</label></td>
					<td class="width-35"><input id="M8" name="M8" class="easyui-datebox" value="${entity.m8 }"
						style="width: 100%;height: 30px;" data-options="editable:true " /></td>
				</tr>
				<tr>
				    <td class="width-15 active"><label class="pull-right">批准部门：</label></td>
					<td class="width-35"><input id="M14" name="M14" type="text" class="easyui-combobox" value="${entity.m14 }"
						style="width: 100%;height: 30px;"
						data-options="editable:true ,valueField: 'text',textField: 'text', panelHeight: 'auto', url:'${ctx}/hjbh/stsgl/findpz'" /></td>
					<td class="width-15 active"><label class="pull-right">试生产核准文号：</label></td>
					<td class="width-35"><input data-options="" type="text" id="M9" name="M9" class="easyui-textbox" value="${entity.m9}"  style="height: 30px;width: 100%" /></td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">核准部门：</label></td>
					<td class="width-35"><input id="M15" name="M15" type="text" class="easyui-combobox" value="${entity.m15 }"
						style="width: 100%;height: 30px;"
						data-options="editable:true ,valueField: 'text',textField: 'text', panelHeight:'auto', url:'${ctx}/hjbh/stsgl/findsh'" /></td>
					<td class="width-15 active"><label class="pull-right">核准时间：</label></td>
					<td class="width-35"><input id="M10" name="M10" class="easyui-datebox" value="${entity.m10 }"
						style="width: 100%;height: 30px;" data-options="editable:true " /></td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">三同时验收部门：</label></td>
					<td class="width-35"><input id="M16" name="M16" type="text" class="easyui-combobox" value="${entity.m16 }"
						style="width: 100%;height: 30px;"
						data-options="editable:true ,valueField: 'text',textField: 'text', panelHeight:'auto', url:'${ctx}/hjbh/stsgl/findys'" /></td>
				    <td class="width-15 active"><label class="pull-right">验收时间：</label></td>
					<td class="width-35"><input id="M11" name="M11" class="easyui-datebox" value="${entity.m11 }"
						style="width: 100%;height: 30px;" data-options="editable:true " /></td>
				</tr>
				<tr>
				    <td class="width-15 active"><label class="pull-right">产能核准量(万只/年)：</label></td>
					<td class="width-35"><input type="text" id="M12" name="M12" class="easyui-numberbox" value="${entity.m12}"  style="height: 30px;width: 100%" data-options="min:0,precision:2"/></td>
					<td class="width-15 active"><label class="pull-right">产污核准量(吨/年)：</label></td>
					<td class="width-35"><input type="text" id="M13" name="M13" class="easyui-numberbox" value="${entity.m13}"  style="height: 30px;width: 100%" data-options="min:0,precision:2"/></td>
				</tr>
				</tbody>
			</table>
			<c:if test="${not empty entity.ID}">
               <input type="hidden" name="ID" value="${entity.ID}" />
            </c:if>
			<div class="easyui-accordion" id="accordion" border="false">
     		</div>
       </form>
   <script type="text/javascript">
				var time;
				var dgdata = [];
				var dg;
				var data;//datagrid参数
				var action = '${action}';
				var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
				$(function() {
					var flag=true;
					$('#inputForm').form({
					    onSubmit: function(){    
					    	var isValid = $(this).form('validate');
					    	if(isValid&&flag){
					    		flag=false;
					    		$.jBox.tip("正在提交，请稍等...",'loading',{opacity:0});
					    		return true;
					    	}
							return false; //返回false终止表单提交
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
					
				function doSubmit() {
					$("#inputForm").submit();
				}
				
			</script>


</body>
</html>