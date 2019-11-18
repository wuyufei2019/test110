<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>scl风险评估管理</title>
<meta name="decorator" content="default" />
</head>
<body>
   <form id="inputForm" class="form-horizontal">
      <table id="rwtable" class="table table-bordered">
         <c:if test="${action eq 'create' }">
            <tfoot>
               <tr>
                  <td align="center" colspan="4"><a class="btn btn-info btn-sm" data-toggle="tooltip" data-placement="left" onclick="addaction()"
                        title="新增活动">
                        <i class="fa fa-plus"></i>新增活动
                     </a></td>
               </tr>
            </tfoot>
         </c:if>
         <tbody>
            <tr>
               <td class="width-15 active"><label class="pull-right">责任部门：</label></td>
               <td class="width-35"><input id="deptid" name="deptid" type="text" value="${entity.deptid }" class="easyui-combobox"
                     style="width: 100%;height: 30px;"
                     data-options="required:true,editable : false ,panelHeight:100,valueField:'id', textField:'text',url: '${ctx}/system/department/deptjson'">
               <td class="width-15 active"><label class="pull-right">工段：</label></td>
               <td class="width-35"><input name="m1" id="m1" style="width: 100%;height: 30px;" class="easyui-textbox"
                     value="${entity.m1 }" data-options="validType:'length[0,250]'" /></td>
            </tr>
            <tr>
            </tr>
            <tr>
               <td class="width-15 active"><label class="pull-right">装置/设备：</label></td>
               <td class="width-35" colspan="3"><input name="equipment" id="equipment" style="width: 100%;height: 30px;" class="easyui-textbox"
                     value="${entity.equipment }" data-options="validType:'length[0,100]'" /></td>
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

            <c:if test="${not empty entity.ID}">
               <input type="hidden" name="ID" value="${entity.ID}" />
               <input type="hidden" name="qyid" value="${entity.qyid}" />
               <input type="hidden" name="S1" value="<fmt:formatDate value="${entity.s1}" pattern="yyyy-MM-dd HH:mm:ss"  />" />
               <input type="hidden" name="S3" value="${entity.s3}" />
            </c:if>
         </tbody>
      </table>
      <div class="easyui-accordion" id="accordion" border="false">
         <!-- <div title="风险活动" data-options="selected:true" style="padding:10px;">
             <table id="fxhd_dg"></table>
         </div> -->
      </div>
   </form>
   <script type="text/javascript">
				var time;
				var dgdata = [];
				var dg;
				var data;//datagrid参数
				var action = '${action}';
				$(function() {
					data = {
						fitColumns : true,
						border : true,
						striped : true,
						rownumbers : true,
						nowrap : false,
						idField : 'id',
						scrollbarSize : 0,
						singleSelect : true,
						striped : true,
						columns : [ [ {
							field : 'project',
							title : '检查项目',
							sortable : false,
							width : 100
						}, {
							field : 'standard',
							title : '检查标准',
							sortable : false,
							width : 100
						}, {
							field : 'mainresult',
							title : '不符合标准主要后果',
							sortable : false,
							width : 100
						}, {
							field : 'safetymeasure',
							title : '现有安全措施',
							sortable : false,
							width : 100
						}, {
							field : 'possibility',
							title : '可能性(等级)',
							sortable : false,
							width : 50
						}, {
							field : 'severity',
							title : '严重度(等级)',
							sortable : false,
							width : 50
						}, {
							field : 'riskvalue',
							title : '风险值',
							sortable : false,
							width : 50
						}, {
							field : 'risklevel',
							title : '风险等级',
							sortable : false,
							width : 50
						}, {
							field : 'improvemeasure',
							title : '改进措施',
							sortable : false,
							width : 100
						}, {
							field : 'operation',
							title : '操作',
							sortable : false,
							width : 50,
							formatter : function(value, row) {
								return "<a class='btn btn-warning btn-xs' onclick='updaction(" + JSON.stringify(row) + ")'>修改</a>"
								+ "<a class='btn btn-danger btn-xs' onclick='deleterow(" + row.time + ")'>删除</a>";
							}
						}

						] ],
						onLoadSuccess : function() {
						},
						checkOnSelect : false,
						selectOnCheck : false
					};
					if (action == 'create') {
						$("#analysisper").textbox("setValue", '${username}');
						$("#analysistime").datebox("setValue", new Date().format("yyyy-MM-dd"));
					}
				});
				function deleterow(rowtime) {
					top.layer.confirm('删除后无法恢复您确定要删除？', {icon: 3, title:'提示'}, function(tmpindex){
						for ( var index in dgdata) {
							if (dgdata[index].time == rowtime) {
								dgdata.splice(index, 1);
							}
						}
						dg.datagrid("loadData", dgdata);
						if (dgdata.length == 0) {
							$('#accordion').accordion('remove', 0);
						}
						top.layer.close(tmpindex);
					});
				}
				function addaction() {
					openDialog("添加风险活动信息", ctx + "/fxpg/scl/actioncreate/", "800px", "400px", "");
				}
				function updaction(row) {
					time = row.time;
					openDialog("修改风险活动信息", ctx + "/fxpg/scl/actionupdate?time=" + row.time + "&data=" + encodeURIComponent(JSON.stringify(row)), "800px", "400px", "");
				}
				var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
				function doSubmit() {
					var obj = $("#inputForm").serializeObject();
					var data = (action == 'create') ? {
						"list" : JSON.stringify(dgdata),
						"entity" : JSON.stringify(obj)
					} : obj;
					$.ajax({
						type : 'POST',
						url : "${ctx}/fxpg/scl/" + action,
						data : data,
						success : function(data) {
							$.jBox.closeTip();
							if (data == 'success') {
								parent.layer.open({
									icon : 1,
									title : '提示',
									offset : 'rb',
									content : '操作成功！',
									shade : 0,
									time : 2000
								});
								parent.dg.datagrid('reload');
								parent.layer.close(index);//关闭对话框。
							} else {
								parent.layer.open({
									icon : 2,
									title : '提示',
									offset : 'rb',
									content : '操作失败！',
									shade : 0,
									time : 2000
								});
							}
						},
						beforeSend : function() {
							var isValid = $("#inputForm").form('validate');
							if (isValid) {
								$.jBox.tip("正在提交，请稍等...", 'loading', {
									opacity : 0
								});
								return true;
							}
							return false; // 返回false终止表单提交
						}
					});
				}
			</script>


</body>
</html>