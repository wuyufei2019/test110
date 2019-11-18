<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>hazop风险评估管理</title>
<meta name="decorator" content="default" />
</head>
<body>
   <form id="inputForm" class="form-horizontal">
      <table id="rwtable" class="table table-bordered">
         <c:if test="${action eq 'create' }">
            <tfoot>
               <tr>
                  <td align="center" colspan="4"><a class="btn btn-info btn-sm" data-toggle="tooltip" data-placement="left" onclick="addaction()"
                        title="新增过程">
                        <i class="fa fa-plus"></i>新增过程
                     </a></td>
               </tr>
            </tfoot>
         </c:if>
         <tbody>
            <tr>
               <td class="width-15 active"><label class="pull-right">分析题目：</label></td>
               <td class="width-35" colspan="3"><input name="question" id="question" style="width: 100%;height: 30px;" class="easyui-textbox"
                     value="${entity.question }" required="required" data-options="validType:'length[0,50]'" /></td>
            </tr>
            <tr>
            </tr>
            <tr>
               <td class="width-15 active"><label class="pull-right">图纸编号：</label></td>
               <td class="width-35"><input name="drawingnumber" id="drawingnumber" style="width: 100%;height: 30px;" class="easyui-textbox"
                     value="${entity.drawingnumber }" data-options="validType:'length[0,25]'" />
               <td class="width-15 active"><label class="pull-right">修订号：</label></td>
               <td class="width-35"><input name="revision" id="revision" style="width: 100%;height: 30px;" class="easyui-textbox"
                     value="${entity.revision }" data-options="validType:'length[0,25]'" /></td>
            </tr>
            <tr>
               <td class="width-15 active"><label class="pull-right">分析日期：</label></td>
               <td class="width-35"><input name="analysistime" id="analysistime" style="width: 100%;height: 30px;" class="easyui-datebox"
                     value="${entity.analysistime }" /></td>
               <td class="width-15 active"><label class="pull-right">会议日期：</label></td>
               <td class="width-35"><input name="meetingtime" id="meetingtime" style="width: 100%;height: 30px;" class="easyui-datebox"
                     value="${entity.meetingtime }" /></td>
            </tr>
            <tr>
               <td class="width-15 active"><label class="pull-right">小组成员：</label></td>
               <td class="width-35" colspan="3"><input name="member" id="member" style="width: 100%;height: 30px;" class="easyui-textbox"
                     value="${entity.member }" data-options="validType:'length[0,100]'" /></td>
            </tr>
            
            <tr>
               <td class="width-15 active"><label class="pull-right">分析部分：</label></td>
               <td class="width-35" colspan="3"><input name="analysispart" id="analysispart" style="width: 100%;height: 30px;" class="easyui-textbox"
                     value="${entity.analysispart }" data-options="validType:'length[0,100]'" /></td>
            </tr>
            <tr>
               <td class="width-15 active"><label class="pull-right">物料：</label></td>
               <td class="width-35"><input name="material" id="material" style="width: 100%;height: 30px;" class="easyui-textbox"
                     value="${entity.material }" data-options="validType:'length[0,50]'" /></td>
               <td class="width-15 active"><label class="pull-right">功能：</label></td>
               <td class="width-35"><input name="function" id="function" style="width: 100%;height: 30px;" class="easyui-textbox"
                     value="${entity.function }" data-options="validType:'length[0,50]'" /></td>
            </tr>

            <tr>
               <td class="width-15 active"><label class="pull-right">来源：</label></td>
               <td class="width-35"><input name="source" id="source" style="width: 100%;height: 30px;" class="easyui-textbox"
                     value="${entity.source }" data-options="validType:'length[0,50]'" /></td>
               <td class="width-15 active"><label class="pull-right">目的地：</label></td>
               <td class="width-35"><input name="purpose" id="purpose" style="width: 100%;height: 30px;" class="easyui-textbox"
                     value="${entity.purpose }" data-options="validType:'length[0,50]'" /></td>
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
							field : 'guidanceword',
							title : '引导词',
							sortable : false,
							width : 100
						}, {
							field : 'factor',
							title : '要素',
							sortable : false,
							width : 100
						}, {
							field : 'deviation',
							title : '偏差',
							sortable : false,
							width : 50
						}, {
							field : 'possiblereason',
							title : '可能原因',
							sortable : false,
							width : 100
						}, {
							field : 'result',
							title : '后果',
							sortable : false,
							width : 50
						}, {
							field : 'safetymeasure',
							title : '安全措施',
							sortable : false,
							width : 100
						}, {
							field : 'note',
							title : '注释',
							sortable : false,
							width : 100
						}, {
							field : 'suggestion',
							title : '建议安全措施',
							sortable : false,
							width : 100
						}, {
							field : 'executor',
							title : '执行人',
							sortable : false,
							width : 50
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
						$("#meetingtime").datebox("setValue", new Date().format("yyyy-MM-dd"));
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
					openDialog("添加风险活动信息", ctx + "/fxpg/hazop/actioncreate/", "800px", "400px", "");
				}
				function updaction(row) {
					time = row.time;
					openDialog("修改风险活动信息", ctx + "/fxpg/hazop/actionupdate?time=" + row.time + "&data=" + encodeURIComponent(JSON.stringify(row)), "800px", "400px", "");
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
						url : "${ctx}/fxpg/hazop/" + action,
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