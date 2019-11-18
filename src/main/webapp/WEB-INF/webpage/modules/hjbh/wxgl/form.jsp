<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>危险废物特性管理</title>
<meta name="decorator" content="default" />
</head>
<body>
   <form id="inputForm" action="${ctx}/hjbh/wxgl/${action}"  method="post" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right">废物名称：</label></td>
					<td class="width-35"><input type="text" id="name" name="name" class="easyui-textbox" value="${entity.name}"  style="height: 30px;width: 100%" data-options="required:'true'"/></td>
					<td class="width-15 active"><label class="pull-right">废物类别：</label></td>
					<td class="width-35"><input type="text" id="kind" name="kind" class="easyui-textbox" value="${entity.kind}"  style="height: 30px;width: 100%" data-options="required:'true'"/></td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">主要危险性：</label></td>
					<td class="width-35"><input data-options=" panelHeight:'auto',multiple:true,data: [
										{value:'腐蚀性',text:'腐蚀性'},
								        {value:'急性毒性',text:'急性毒性'},
								        {value:'浸出毒性',text:'浸出毒性'},
								        {value:'易燃性',text:'易燃性'},
								        {value:'反应性',text:'反应性'},
								        {value:'含毒性物质',text:'含毒性物质'},
								        {value:'传染性物质',text:'传染性物质'}
								        ]" type="text" id="danger_type" name="danger_type" class="easyui-combobox" value="${entity.danger_type}"  style="height: 30px;width: 100%" />
					</td>
					<td class="width-15 active"><label class="pull-right">主要化学组成：</label></td>
					<td class="width-35"><input data-options="" type="text" id="content" name="content" class="easyui-textbox" value="${entity.content}"  style="height: 30px;width: 100%" /></td>
				</tr>		
				<tr>
					<td class="width-15 active"><label class="pull-right">废物表现形态：</label></td>
					<td class="width-35"><input data-options=" panelHeight:'auto',data: [
										{value:'固态',text:'固态'},
								        {value:'半固态',text:'半固态'},
								        {value:'液态',text:'液态'},
								        {value:'气态',text:'气态'}
								        ]" type="text" id="express_type" name="express_type" class="easyui-combobox" value="${entity.express_type}"  style="height: 30px;width: 100%" />
					</td>
					<td class="width-15 active"><label class="pull-right">贮存方式：</label></td>
					<td class="width-35"><input data-options=" panelHeight:'auto',data: [
										{value:'圆桶',text:'圆桶'},
								        {value:'槽罐',text:'槽罐'},
								        {value:'编织袋',text:'编织袋'}
								        ]" type="text" id="store_type" name="store_type" class="easyui-combobox" value="${entity.store_type}"  style="height: 30px;width: 100%" />
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">是否提供委托外单位利用处理：</label></td>
					<td class="width-35"><input type='radio' id="other_handler" name='other_handler'  value='1' class='i-checks' checked='checked' />是
					                    <input type='radio' id="other_handler1" name='other_handler'  value='0' class='i-checks' />否
					</td>
				     <td class="width-15 active"><label class="pull-right">单位内部处置方法描述：</label></td>
				     <td class="width-35"><input data-options="required:'true'" type="text" id="description" name="description" class="easyui-textbox" value="${entity.description}"  style="height: 30px;width: 100%" disabled="disabled"/></td>
					</div>
				</tr>
				<tr>
				    <c:if test="${action eq 'wxglcreate'}">
						<td align="center" colspan="4" >
							<a id="addOtherDw" name="addOtherDw" class="btn btn-info btn-sm" data-toggle="tooltip" onclick="addWxOtherDw();" data-placement="left"  title="添加外单位"><i class="fa fa-plus"></i> 添加外单位</a>
						</td>
				    </c:if>	
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
				$(function() {
					//根据数据库中的值来为单选按钮赋值
					if ('${entity.other_handler}' == 0) {
						$('#other_handler1').iCheck('check');
					} else if ('${entity.other_handler}' == 1) {
						$('#other_handler').iCheck('check');
					}
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
							field : 'name',
							title : '单位名称',
							sortable : false,
							width : 100
						}, {
							field : 'location',
							title : '所在地',
							sortable : false,
							width : 100
						}, {
							field : 'permit_num',
							title : '危险废物经营许可证',
							sortable : false,
							width : 80
						}, {
							field : 'description',
							title : '方法描述',
							sortable : false,
							width : 100
						}, {
							field : 'contact_name',
							title : '联系人',
							sortable : false,
							width : 100
						}, {
							field : 'contact_phone',
							title : '联系方式',
							sortable : false,
							width : 100
						}, {
							field : 'operation',
							title : '操作',
							sortable : false,
							width : 100,
							formatter : function(value, row) {
								return "<a class='btn btn-warning btn-xs' onclick='toupdotherdw(" + JSON.stringify(row) + ")'>修改</a>"
								+ "<a class='btn btn-danger btn-xs' onclick='deleteOtherdw(" + row.time + ")'>删除</a>";
							}
						}

						] ],
						onLoadSuccess : function() {
						},
						checkOnSelect : false,
						selectOnCheck : false
					};
				});
				//单选按钮关联添加外单位信息按钮操作
				$("[name='other_handler']").on('ifChecked', function(event){    
					  if ((event.target.value) == 0) {//否
						  $("#addOtherDw").attr("disabled", true);
						  $("#description").textbox({disabled:false});
					  } else if ((event.target.value) == 1) {//是
						  
						  $("#description").textbox("setValue", "");
						  $("#addOtherDw").attr("disabled", false);
						  $("#description").textbox({disabled:true});
					  }
				});
				function addWxOtherDw() {
					openDialog("添加外单位信息", ctx + "/hjbh/otherDw/create/", "80%", "90%", "");
				}
				function toupdotherdw(row) {
					time = row.time;
					openDialog("修改外单位信息",ctx + "/hjbh/otherDw/update?time=" + row.time + "&data=" + encodeURIComponent(JSON.stringify(row)) ,"80%", "80%","");
				}
				function deleteOtherdw(rowtime) {
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
				var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
				function doSubmit() {
					$("#danger_type").combobox("getValue");
					var obj = $("#inputForm").serializeObject();
					//如果danger_type是多选
					if (typeof(obj.danger_type) == 'object') {
						var d= obj.danger_type;
						obj.danger_type= d.join();
					}
					var data = (action == 'wxglcreate') ? {
						"list" : JSON.stringify(dgdata),
						"entity" : JSON.stringify(obj)
					} : obj;
					$.ajax({
						type : 'POST',
						url : "${ctx}/hjbh/wxfwgl/" + action,
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