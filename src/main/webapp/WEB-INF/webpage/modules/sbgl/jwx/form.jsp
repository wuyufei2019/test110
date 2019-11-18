<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>检维修管理</title>
<meta name="decorator" content="default" />
</head>
<body>

   <form id="inputForm" action="${ctx}/sbgl/jwx/${action}" method="post" class="form-horizontal">
      <table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
         <tbody>
            <tr>
               <td class="width-15 active" align="center" colspan="4">设备基本信息</td>
            </tr>
            <tr>
               <td class="width-15 active"><label class="pull-right">设备名称：</label></td>
               <td class="width-35">
                  <input id="ID1" name="ID1" style="width: 84%;height: 30px;" required ="true" class="easyui-combogrid" value="${sbgl.ID1 }" />
                  <span  class="btn btn-success btn-sm  "  style="width: 15%;height: 30px;" onclick="search()" ><i class="fa fa-search"></i> 查询</span>
               </td>
               <td class="width-15 active"><label class="pull-right">设备类别：</label></td>
               <td class="width-35" ><input id="sbtype" name="sbtype" readonly="true" class="easyui-combobox" value="${sbgl.sbtype }"
                   style="width:100%;height: 30px;"
                   data-options="panelHeight:100,valueField:'value', textField:'text',data:[{value:'tzsb',text:'特种设备'},
                   {value:'scsb',text:'生产设备'},{value:'aqss',text:'安全设施'}]"/></td>
            </tr>
            <tr>
               <td class="width-15 active"><label class="pull-right">设备规格：</label></td>
               <td class="width-35"><input id="specification" name="specification" type="text" value="${sbgl.specification }"
                class="easyui-textbox" style="width: 100%;height: 30px;"/></td>
               <td class="width-15 active"><label class="pull-right">设备型号：</label></td>
               <td class="width-35"><input id="number" name="number" type="text" value="${sbgl.number }"
                class="easyui-textbox" style="width: 100%;height: 30px;"/></td>
            </tr>
            <tr>
               <td class="width-15 active"><label class="pull-right">统一编号：</label></td>
               <td class="width-35"><input id="unifynumber" name="unifynumber" type="text" value="${sbgl.unifynumber }"
                class="easyui-textbox" style="width: 100%;height: 30px;"/></td>
               <td class="width-15 active"><label class="pull-right">使用部门：</label></td>
               <td class="width-35"><input id="userdept" name="userdept" type="text" value="${sbgl.userdept }"
                class="easyui-combobox" style="width: 100%;height: 30px;" 
                data-options="editable : false ,panelHeight:100,valueField:'text', textField:'text',url: '${ctx}/system/department/deptjson'"></td>
            </tr>
              <tr>
               <td class="width-15 active" align="center" colspan="4">检维修记录</td>
            </tr>
             <tr>
               <td class="width-15 active"><label class="pull-right">操作人员：</label></td>
               <td class="width-35" ><input id="operator" name="operator" class="easyui-textbox" value="${sbgl.operator }"
                     style="width:100%;height: 30px;"/> </td>
               <td class="width-15 active"><label class="pull-right">检修类别：</label></td>
               <td class="width-35" ><input name="type" class="easyui-combobox" value="${sbgl.type }"
                   style="width:100%;height: 30px;"data-options="panelHeight:'auto', editable:false, valueField:'value',textField:'text',
                   data:[{value:1,text:'计划维修'},{value:2,text:'故障维修'}]"/> </td>
            </tr>
             <tr>
               <td class="width-15 active"><label class="pull-right">实际开工检修时间：</label></td>
               <td class="width-35" ><input id="starttime" name="starttime" class="easyui-datebox" value="${sbgl.starttime }"
                     style="width:100%;height: 30px;"/> </td>
               <td class="width-15 active"><label class="pull-right">实际检修竣工时间：</label></td>
               <td class="width-35" ><input id="endtime" name="endtime" class="easyui-datebox" value="${sbgl.endtime }"
                     style="width:100%;height: 30px;"/> </td>
            </tr>
             <tr>
              <td class="width-15 active"><label class="pull-right">修理内容：</label></td>
               <td class="width-35" colspan="3"><input id="content" name="content" type="text" value="${sbgl.content }"
                class="easyui-textbox" style="width: 100%;height: 50px;"
                     data-options="multiline : true,validType:'length[0,500]'"/></td>
            </tr>
             <tr>
              <td class="width-15 active"><label class="pull-right">工具及防护：</label></td>
               <td class="width-35" colspan="3"><input id="toolfence" name="toolfence" type="text" value="${sbgl.toolfence }"
                class="easyui-textbox" style="width: 100%;height: 30px;"
                     data-options="validType:'length[0,100]'"/></td>
            </tr>
             <tr>
               <td class="width-15 active" align="center" colspan="4">设试运行情况</td>
            </tr>
             <tr>
              <td class="width-15 active"><label class="pull-right">空负荷：</label></td>
               <td class="width-35" colspan="3"><input id="runcondk" name="runcondk" type="text" value="${sbgl.runcondk }"
                class="easyui-textbox" style="width: 100%;height: 30px;"
                     data-options="validType:'length[0,100]'"/></td>
            </tr>
             <tr>
              <td class="width-15 active"><label class="pull-right">带负荷：</label></td>
               <td class="width-35" colspan="3"><input id="runcondd" name="runcondd" type="text" value="${sbgl.runcondd }"
                class="easyui-textbox" style="width: 100%;height: 30px;"
                     data-options="validType:'length[0,100]'"/></td>
            </tr>
                <tr>
               <td class="width-15 active" align="center" colspan="4">检维修后交付手续</td>
            </tr>
             <tr>
               <td class="width-15 active"><label class="pull-right">交付人：</label></td>
               <td class="width-35"><input id="jfr" name="jfr" type="text" value="${sbgl.jfr }"
                class="easyui-textbox" style="width: 100%;height: 30px;"
                     data-options="validType:'length[0,25]'"/></td>
               <td class="width-15 active"><label class="pull-right">接收人：</label></td>
               <td class="width-35"><input id="jsr" name="jsr" type="text" value="${sbgl.jsr }"
                class="easyui-textbox" style="width: 100%;height: 30px;"
                     data-options="validType:'length[0,25]'"/></td>
            </tr>
             <tr>
               <td class="width-15 active"><label class="pull-right">车间主管：</label></td>
               <td class="width-35"><input id="cjzg" name="cjzg" type="text" value="${sbgl.cjzg }"
                class="easyui-textbox" style="width: 100%;height: 30px;"
                     data-options="validType:'length[0,25]'"/></td>
               <td class="width-15 active"><label class="pull-right">安全主管：</label></td>
               <td class="width-35"><input id="aqzg" name="aqzg" type="text" value="${sbgl.aqzg }"
                class="easyui-textbox" style="width: 100%;height: 30px;"
                     data-options="validType:'length[0,25]'"/></td>
            </tr>
               <input type="hidden" id="sbname" name="sbname" value="${sbgl.sbname}" />
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
			$('#ID1').combogrid({
				panelWidth : '34%',
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
		    	  		$('#ID1').combogrid("setValue",rowData.id);
   		    	  		$('#sbname').val(rowData.name);
		    	  		$('#sbtype').combobox("setValue",rowData.type);
		    	  		if(rowData.specification)
   		    	  			$('#specification').textbox("setValue",rowData.specification);
		    	  		if(rowData.number)
   		    	  			$('#number').textbox("setValue",rowData.number);
		    		}
				}   
			});
		if("${action}"=="create"){
			$("#endtime").datebox("setValue", new Date().format("yyyy-MM-dd"));
			$("#operator").textbox("setValue", '${username}');
		}
	});
	function search(){
		var sbname=$('#ID1').combogrid("getText");
		$('#ID1').combogrid("showPanel");
		$('#ID1').combogrid("grid").datagrid("reload", { 'view_sbname': sbname });
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