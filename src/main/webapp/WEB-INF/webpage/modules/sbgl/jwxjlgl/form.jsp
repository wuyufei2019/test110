<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>检维修管理</title>
<meta name="decorator" content="default" />
</head>
<body>

   <form id="inputForm" action="${ctx}/sbgl/jwxjlgl/${action}" method="post" class="form-horizontal">
      <table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
         <tbody>
            <tr>
               <td class="width-15 active"><label class="pull-right">设备名称：</label></td>
               <td class="width-35">
                  <input id="sbname" name="sbname" style="width: 77%;height: 30px;" required ="true" class="easyui-combogrid" value="${sbgl.sbname }" />
                  <span  class="btn btn-success btn-sm  "  style="width: 21%;height: 30px;" onclick="search()" ><i class="fa fa-search"></i> 查询</span>
               </td>
               <td class="width-15 active"><label class="pull-right">使用部门：</label></td>
               <td class="width-35" ><input id="" name="M1" class="easyui-combobox" value="${sbgl.m1 }"
                   style="width:100%;height: 30px;" data-options="required:true, editable : false, panelHeight:100,valueField:'id', textField:'text',url: '${ctx}/system/department/deptjson'"/></td>
            </tr>
            <tr>
               <td class="width-15 active"><label class="pull-right">规格型号：</label></td>
               <td class="width-35"><input id="specification" name="sbxh" type="text" value="${sbgl.sbxh }"
                class="easyui-textbox" style="width: 100%;height: 30px;" data-options="editable : true"/></td>
               <td class="width-15 active"><label class="pull-right">设备编号：</label></td>
               <td class="width-35"><input id="number" name="sbbh" type="text" value="${sbgl.sbbh }"
                class="easyui-textbox" style="width: 100%;height: 30px;" data-options="editable : true"/></td>
            </tr>
            <tr>
				<td class="width-15 active"><label class="pull-right">故障现象：</label></td>
				<td class="width-35" colspan="3"><input name="M2" type="text" value="${sbgl.m2 }"   class="easyui-textbox" style="width: 100%;height: 60px;" data-options="multiline:true, validType:'length[0,500]' "></td>					
			</tr>
            <tr>
            	<td class="width-15 active"><label class="pull-right">分析人：</label></td>
                <td class="width-35"><input id="" name="M3" class="easyui-combobox" value="${sbgl.m3 }"
                     style="width:100%;height: 30px;" data-options="panelHeight:'120',editable:true ,valueField: 'text',textField: 'text',url:'${ctx}/system/compuser/userjson'"/></td>
            </tr>
            <tr>
				<td class="width-15 active"><label class="pull-right">维修风险分析：</label></td>
				<td class="width-35" colspan="3"><input name="M4" type="text" value="${sbgl.m4 }"   class="easyui-textbox" style="width: 100%;height: 60px;" data-options="multiline:true, validType:'length[0,500]' "></td>					
			</tr>
			<tr>
            	<td class="width-15 active"><label class="pull-right">方案制定人：</label></td>
                <td class="width-35"><input id="" name="M5" class="easyui-combobox" value="${sbgl.m5 }"
                     style="width:100%;height: 30px;" data-options="panelHeight:'120',editable:true ,valueField: 'text',textField: 'text',url:'${ctx}/system/compuser/userjson'"/></td>
            </tr>
            <tr>
				<td class="width-15 active"><label class="pull-right">检维修方案：</label></td>
				<td class="width-35" colspan="3"><input name="M6" type="text" value="${sbgl.m6 }"   class="easyui-textbox" style="width: 100%;height: 60px;" data-options="multiline:true, validType:'length[0,500]' "></td>					
			</tr>
			<tr>
            	<td class="width-15 active"><label class="pull-right">措施制定人：</label></td>
                <td class="width-35"><input id="" name="M7" class="easyui-combobox" value="${sbgl.m7 }"
                     style="width:100%;height: 30px;" data-options="panelHeight:'120',editable:true ,valueField: 'text',textField: 'text',url:'${ctx}/system/compuser/userjson'"/></td>
            </tr>
            <tr>
				<td class="width-15 active"><label class="pull-right">采取和落实<br>预防和控制<br>措施：</label></td>
				<td class="width-35" colspan="3"><input name="M8" type="text" value="${sbgl.m8 }"   class="easyui-textbox" style="width: 100%;height: 60px;" data-options="multiline:true, validType:'length[0,500]' "></td>					
			</tr>
			<tr>
				<td class="width-15 active"><label class="pull-right">检维修安全<br>教育培训：</label></td>
				<td class="width-35" colspan="3">
					<table id="rwtable" style="width: 100%;"  border="1" cellspacing="0" cellpadding="0" bordercolor="#e7e7e7" >
						<tr>
			            	<td style="width: 20%;height: 40px;background-color: #f5f5f5;"><label class="pull-right">培训人：</label></td>
			                <td style="width: 80%;" align="center"><input id="" name="M9" class="easyui-combobox" value="${sbgl.m9 }"
			                     style="width:95%;height: 30px;" data-options="panelHeight:'120',editable:true ,valueField: 'text',textField: 'text',url:'${ctx}/system/compuser/userjson'"/></td>
			            </tr>
			            <tr>
							<td style="width: 20%;height: 70px;background-color: #f5f5f5;"><label class="pull-right">培训内容：</label></td>
							<td style="width: 80%;" align="center"><input name="M10" type="text" value="${sbgl.m10 }"   class="easyui-textbox" style="width: 95%;height: 60px;" data-options="multiline:true, validType:'length[0,500]' "></td>					
						</tr>
						<tr>
			            	<td style="width: 20%;height: 40px;background-color: #f5f5f5;"><label class="pull-right">被培训人：</label></td>
			            	<td style="width: 80%; height: 90px;" align="center">
				                <table style="width: 95%;"  border="1" cellspacing="0" cellpadding="0" bordercolor="#e7e7e7" >
				                	<tr>
						            	<td style="width: 20%;height: 40px;background-color: #f5f5f5;"><label class="pull-right">检修人：</label></td>
						                <td style="width: 80%;" align="center"><input id="" name="M11" class="easyui-combobox" value="${sbgl.m11 }"
						                     style="width:95%;height: 30px;" multiple='true' data-options="panelHeight:'120',editable:false ,valueField: 'text',textField: 'text',url:'${ctx}/system/compuser/userjson'"/></td>
						            </tr>
						            <tr>
						            	<td style="width: 20%;height: 40px;background-color: #f5f5f5;"><label class="pull-right">监护人：</label></td>
						                <td style="width: 80%;" align="center"><input id="" name="M12" class="easyui-combobox" value="${sbgl.m12 }"
						                     style="width:95%;height: 30px;" data-options="panelHeight:'120',editable:true ,valueField: 'text',textField: 'text',url:'${ctx}/system/compuser/userjson'"/></td>
						            </tr>
				                </table>
			            	</td>
			            </tr>
					</table>	
				</td>
			</tr>
			<tr>
            	<td class="width-15 active"><label class="pull-right">检维修人：</label></td>
                <td class="width-35"><input id="" name="M13" class="easyui-combobox" value="${sbgl.m13 }"
                     style="width:100%;height: 30px;" data-options="panelHeight:'120',editable:true ,valueField: 'text',textField: 'text',url:'${ctx}/system/compuser/userjson'"/></td>
            </tr>
            <tr>
				<td class="width-15 active"><label class="pull-right">检维修记录：</label></td>
				<td class="width-35" colspan="3"><input name="M14" type="text" value="${sbgl.m14 }"   class="easyui-textbox" style="width: 100%;height: 60px;" data-options="multiline:true, validType:'length[0,500]' "></td>					
			</tr>
			<tr>
            	<td class="width-15 active"><label class="pull-right">验收人：</label></td>
                <td class="width-35"><input id="" name="M15" class="easyui-combobox" value="${sbgl.m15 }"
                     style="width:100%;height: 30px;" data-options="panelHeight:'120',editable:true ,valueField: 'text',textField: 'text',url:'${ctx}/system/compuser/userjson'"/></td>
                <td class="width-15 active"><label class="pull-right">检维修时间：</label></td>
                <td class="width-35" ><input id="M8" name="M17" value="${sbgl.m17 }" class="easyui-datebox" style="width: 100%;height: 30px;" data-options="required:true"/></td>
            </tr>
            <tr>
				<td class="width-15 active"><label class="pull-right">验收记录：</label></td>
				<td class="width-35" colspan="3"><input name="M16" type="text" value="${sbgl.m16 }"   class="easyui-textbox" style="width: 100%;height: 60px;" data-options="multiline:true, validType:'length[0,500]' "></td>					
			</tr>
            <c:if test="${not empty sbgl.ID}">
               <input type="hidden" name="ID" value="${sbgl.ID}" />
               <input type="hidden" name="qyid" value="${sbgl.qyid}" />
               <input type="hidden" name="S1" value="<fmt:formatDate value="${sbgl.s1}" pattern="yyyy-MM-dd HH:mm:ss"  />" />
               <input type="hidden" name="S3" value="${sbgl.s3}" />
            </c:if>
         </tbody>
      </table>
   </form>
   <script type="text/javascript">
	$(function() {
			$('#sbname').combogrid({
				panelWidth : '50%',
				idField : 'id', //ID字段 
				textField : 'name',
				fitColumns : true,
				method: "post",
				url:'${ctx}/sbgl/jwx/sbjson',
				striped : true,
				editable : true,
				pagination : true,//是否分页 
				rownumbers : true,//序号 
				collapsible : false,//是否可折叠的 
				nowrap:false,
				pageSize : 10,//每页显示的记录条数，默认为10 
				pageList : [ 10 ],//可以设置每页记录条数的列表 
				method : 'post',
				columns : [ [ 
			        {field:'name',title:'设备名称',sortable:false,width:120,align:'center'},    
			        {field:'type',title:'设备类别',sortable:false,width:50,align:'center',
			            	  formatter: function(value){
			            		  if(value=='tzsb') return "特种设备";
			            		  else if(value=='scsb') return "生产设备  ";
			            		  else if(value=='gygc') return "公用工程";
			            		  else if(value=='aqss') return "安全设施";
			            		  else return ;
			            	  }},   
			        {field:'specification',title:'设备规格',sortable:false,width:50,align:'center'},    
			        {field:'number',title:'设备编号',sortable:false,width:50,align:'center'}
				] ],
		     	onSelect : function (rowIndex, rowData){
		    		if(rowData.id){
   		    	  		$('#specification').textbox("setValue","");
   		    	  		$('#number').textbox("setValue","");
		    			$('#sbname').combogrid("setValue",rowData.name);
		    	  		if(rowData.specification)
   		    	  			$('#specification').textbox("setValue",rowData.specification);
		    	  		if(rowData.number)
   		    	  			$('#number').textbox("setValue",rowData.number);
		    		}
				}   
			});
	});
	function search(){
		var sbname=$('#sbname').combogrid("getText");
		$('#sbname').combogrid("showPanel");
		$('#sbname').combogrid("grid").datagrid("reload", { 'view_sbname': sbname });
	}
	
	
				//提交处理
				var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
				var validateForm;
				function doSubmit() {
					$("#inputForm").serializeObject();
					$("#inputForm").submit();
				}

				$(function() {
					var flag = true;
					$('#inputForm').form({
						onSubmit : function() {
							var b = $("#sbname").combogrid("getText");
							$.ajax({
								type : "post",
								url : "${ctx}/sbgl/jwx/sbnameyz",
								data : {"m3":b},
								async: false,
								success : function(data) {
									if(data == 'no') {
										$("#sbname").combogrid("setValue","");
										layer.msg("设备名称不存在!请重新选择",{time: 2000});
										return false;
									}
								}
							});
							var isValid = $(this).form('validate');
							if (isValid && flag) {
								flag = false;
								$.jBox.tip("正在提交，请稍等...", 'loading', {
									opacity : 0
								});
								return true;
							}
							return false; // 返回false终止表单提交
						},
						success : function(data) {
							$.jBox.closeTip();
							if (data == 'success')
								parent.layer.open({
									icon : 1,
									title : '提示',
									offset : 'rb',
									content : '操作成功！',
									shade : 0,
									time : 2000
								});
							else
								parent.layer.open({
									icon : 2,
									title : '提示',
									offset : 'rb',
									content : '操作失败！',
									shade : 0,
									time : 2000
								});
							parent.dg.datagrid('reload');
							parent.layer.close(index);//关闭对话框。
						}
					});
				});
			</script>
</body>

</html>