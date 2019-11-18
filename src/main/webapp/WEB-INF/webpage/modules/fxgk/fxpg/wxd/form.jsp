<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>危险度风险评估管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript" src="${ctx}/static/model/js/fxgk/fxpg/data.js?v=1.1"></script>
</head>
<body>
   <form id="inputForm" action="${ctx}/fxpg/wxd/${action}" method="post" class="form-horizontal">
      <table id="rwtable" class="table table-bordered">
         <tbody>
            <tr>
               <td class="width-15 active"><label class="pull-right">责任部门：</label></td>
               <td class="width-35" colspan="3"><input id="deptid" name="deptid" type="text" value="${entity.deptid }" class="easyui-combobox"
                     style="width: 100%;height: 30px;"
                     data-options="required:true,editable : false ,panelHeight:100,valueField:'id', textField:'text',url: '${ctx}/system/department/deptjson'">
            </tr>
            <tr>
               <td class="width-15 active"><label class="pull-right">分析人：</label></td>
               <td class="width-35"><input name="analysisper" id="analysisper" style="width: 100%;height: 30px;" class="easyui-textbox"
                     value="${entity.analysisper }" data-options="validType:'length[0,25]'" /></td>
               <td class="width-15 active"><label class="pull-right">分析时间：</label></td>
               <td class="width-35"><input name="analysistime" id="analysistime" style="width: 100%;height: 30px;" class="easyui-datebox"
                     value="${entity.analysistime }" /></td>
            </tr>
            <tr>
               <td class="width-15 active"><label class="pull-right">审核人：</label></td>
               <td class="width-35"><input name="reviewer" id="reviewer" style="width: 100%;height: 30px;" class="easyui-textbox"
                     value="${entity.reviewer }" data-options="validType:'length[0,25]'" /></td>
               <td class="width-15 active"><label class="pull-right">审定人：</label></td>
               <td class="width-35"><input name="auditor" id="auditor" style="width: 100%;height: 30px;" class="easyui-textbox"
                     value="${entity.auditor }" data-options="validType:'length[0,25]'" /></td>
            </tr>
            <tr>
               <td class="width-15 active"><label class="pull-right">单元名称：</label></td>
               <td class="width-35" colspan="3"><input name="unit" id="unit" style="width: 100%;height: 30px;" class="easyui-textbox"
                     value="${entity.unit }" data-options="required:true,validType:'length[0,50]'" /></td>
            </tr>
            <tr>
               <td class="width-15 active"><label class="pull-right">涉及物料：</label></td>
               <td class="width-35" colspan="3"><input name="material" id="material" style="width: 100%;height: 30px;" class="easyui-textbox"
                     value="${entity.material }" data-options="validType:'length[0,100]'" /></td>
            </tr>
            <tr>
               <td class="width-15 active"><label class="pull-right">物质分值：</label></td>
               <td class="width-35" colspan="3"><input name="matter" id="matter" style="width: 100%;height: 30px;" class="easyui-combobox"
                     value="${entity.matter }" /></td>
            </tr>
            <tr>
               <td class="width-15 active"><label class="pull-right">容量分值：</label></td>
               <td class="width-35" colspan="3"><input name="capacity" id="capacity" style="width: 100%;height: 30px;" class="easyui-combobox"
                     value="${entity.capacity }"  /></td>
            </tr>
            <tr>
               <td class="width-15 active"><label class="pull-right">温度分值：</label></td>
               <td class="width-35" colspan="3"><input name="temperature" id="temperature" style="width: 100%;height: 30px;"
                     class="easyui-combobox" value="${entity.temperature }" /></td>
            </tr>
            <tr>
               <td class="width-15 active"><label class="pull-right">压力分值：</label></td>
               <td class="width-35" colspan="3"><input name="pressure" id="pressure" style="width: 100%;height: 30px;" class="easyui-combobox"
                     value="${entity.pressure }" /></td>
            </tr>
            <tr>
               <td class="width-15 active"><label class="pull-right">操作分值：</label></td>
               <td class="width-35" colspan="3"><input name="operation" id="operation" style="width: 100%;height: 30px;" class="easyui-combobox"
                     value="${entity.operation }" /></td>
            </tr>
            <tr>
               <td class="width-15 active"><label class="pull-right">风险值：</label></td>
               <td class="width-35"><input name="riskvalue" id="riskvalue" style="width: 100%;height: 30px;" class="easyui-textbox"
                     value="${entity.riskvalue }" data-options="readonly:true" /></td>
               <td class="width-15 active"><label class="pull-right">风险等级：</label></td>
               <td class="width-35"><input name="risklevel" id="risklevel" style="width: 100%;height: 30px;" class="easyui-textbox"
                     value="${entity.risklevel }" data-options="readonly:true" /></td>
            </tr>
            <tr>
               <td class="width-15 active"><label class="pull-right">改进措施：</label></td>
               <td class="width-35" colspan="3"><input name="improvemeasure" id="improvemeasure" style="width: 100%;height: 30px;"
                     class="easyui-textbox" value="${entity.improvemeasure }" data-options="validType:'length[0,250]'" /></td>
            </tr>

            <c:if test="${not empty entity.ID}">
               <input type="hidden" name="ID" value="${entity.ID}" />
               <input type="hidden" name="qyid" value="${entity.qyid}" />
               <input type="hidden" name="S1" value="<fmt:formatDate value="${entity.s1}" pattern="yyyy-MM-dd HH:mm:ss"  />" />
               <input type="hidden" name="S3" value="${entity.s3}" />
            </c:if>
         </tbody>
      </table>
   </form>
   <script type="text/javascript">
				var matter, capacity, temperature,pressure,operation;
				$(function(){
      				if ('${action}' == 'create') {
      					$("#analysisper").textbox("setValue", '${username}');
      					$("#analysistime").datebox("setValue", new Date().format("yyyy-MM-dd"));
      				}else{
      					matter=parseInt("${entity.matter }");
      					capacity=parseInt("${entity.capacity }");
      					temperature=parseInt("${entity.temperature }");
      					pressure=parseInt("${entity.pressure }");
      					operation=parseInt("${entity.operation }");
      				}
				});
					$("#matter").combobox({
						required : true,
						editable : false,
						panelHeight : '100',
						valueField : 'score',
						textField : 'matter',
						data : WxdMatter,
						onSelect : function(rec) {
							matter = parseInt(rec.score);
							if (capacity!=null&&temperature!=null&&pressure!=null&&operation!=null) {
								calculation(matter,capacity, temperature, pressure,operation);
							}
						}
					});
					$("#capacity").combobox({
						required : true,
						editable : false,
						panelHeight : '100',
						valueField : 'score',
						textField : 'capacity',
						data : WxdCapacity,
						onSelect : function(rec) {
							capacity = parseInt(rec.score);
							if (matter!=null&&temperature!=null&&pressure!=null&&operation!=null) {
								calculation(matter,capacity, temperature, pressure,operation);
							}
						}
					});
					$("#temperature").combobox({
						required : true,
						editable : false,
						panelHeight : '100',
						valueField : 'score',
						textField : 'temperature',
						data : WxdTemperature,
						onSelect : function(rec) {
							temperature = parseInt(rec.score);
							if (capacity!=null&&matter!=null&&pressure!=null&&operation!=null) {
								calculation(matter,capacity, temperature, pressure,operation);
							}
						}
					});
					$("#pressure").combobox({
						required : true,
						editable : false,
						panelHeight : '100',
						valueField : 'score',
						textField : 'pressure',
						data : WxdPressure,
						onSelect : function(rec) {
							pressure = parseInt(rec.score);
							if (capacity!=null&&temperature!=null&&matter!=null&&operation!=null) {
								calculation(matter,capacity, temperature, pressure,operation);
							}
						}
					});
					$("#operation").combobox({
						required : true,
						editable : false,
						panelHeight : '100',
						valueField : 'score',
						textField : 'operation',
						data : WxdOperation,
						onSelect : function(rec) {
							operation = parseInt(rec.score);
							if (capacity!=null&&temperature!=null&&pressure!=null&&matter!=null) {
								calculation(matter,capacity, temperature, pressure,operation);
							}
						}
					});

				//自动计算值
				function calculation(matter,capacity, temperature, pressure,operation) {
					var product = matter + capacity + temperature+pressure+operation;
					$("#riskvalue").textbox("setValue", product);
					for ( var index in WxdLevel) {
						var d = WxdLevel[index];
						if (product >= d.min && product <= d.max) {
							$("#risklevel").textbox("setValue", d.severity);
							//$("#improvemeasure").textbox("setValue",d.severity);
						}
					}
				}
				var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
				function doSubmit() {
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