<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>安全责任书回执信息</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
   var uid ='${uid}';
</script>
<script type="text/javascript" src="${ctx}/static/model/js/targetmanger/safetyduty/aqzrssc/qy_index.js?v=1.2"></script>
</head>
<body>
   <!-- 工具栏 -->
   <div id="target_aqzrssc_tb" style="padding:5px;height:auto">
      <form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline pull-left">
         <div class="form-group">
            <input id="target_aqzrssc_year" name="target_aqzrssc_year" class="easyui-combobox" style="height: 30px;"
               data-options="prompt: '年份',panelHeight:'100',valueField: 'year',textField: 'year' ">
         </div>
      </form>
      <div class="pull-left">
         <button class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()">
            <i class="fa fa-search"></i> 查询
         </button>
         <button class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()">
            <i class="fa fa-refresh"></i> 全部
         </button>
      </div>
      <div class="row">
         <div class="col-sm-12">
            <div class="pull-left">
               <shiro:hasPermission name="target:aqzrssc:update">
                  <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd()" title="修改">
                     <i class="fa fa-file-text-o"></i> 修改
                  </button>
               </shiro:hasPermission>
               <shiro:hasPermission name="target:aqzrssc:delete">
                  <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除">
                     <i class="fa fa-trash-o"></i> 删除
                  </button>
               </shiro:hasPermission>
               <shiro:hasPermission name="target:aqzrssc:view">
                  <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view()" title="查看">
                     <i class="fa fa-search-plus"></i> 查看
                  </button>
               </shiro:hasPermission>
               <%-- <shiro:hasPermission name="target:aqzrssc:export">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="fileexport()" title="导出"><i class="fa fa-external-link"></i> 导出</button> 
        	</shiro:hasPermission> --%>
               <%-- <shiro:hasPermission name="target:aqzrssc:exin">
				<button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="openImportDialog('${ctx}/target/aqzrssc/exinjump','${ctx}/target/aqzrssc/exin','${ctx}/static/templates/指标信息导入模板.xls')" title="导入"><i class="fa fa-folder-open-o"></i> 导入</button>
			</shiro:hasPermission> --%>
               <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新">
                  <i class="glyphicon glyphicon-repeat"></i> 刷新
               </button>
            </div>
         </div>
      </div>
   </div>

   <table id="target_aqzrssc_dg"></table>
   <script type="text/javascript">
   var ajqyid='${ajqyid}';
   
				$(function() {
					var data = [];
					var thisYear;
					var startYear = new Date().getUTCFullYear() + 1;
					for (var i = 0; i < 5; i++) {
						thisYear = startYear - i;
						data.push({
							"year" : thisYear
						});
					}
					$("#target_aqzrssc_year").combobox("loadData", data);
				});


				//删除
				function del() {
					var row = dg.datagrid('getChecked');
					if (row == null || row == '') {
						layer.msg("请至少勾选一行记录！", {
							time : 1000
						});
						return;
					}

					var ids = "";
					for (var i = 0; i < row.length; i++) {
						if (row[i].id == null) {
							layer.msg("未上传责任书的数据，不得删除", {
								time : 2000
							});
							return;
						} else {
							if (ids == "") {
								ids = row[i].id;
							} else {
								ids = ids + "," + row[i].id;
							}
						}
					}

					top.layer.confirm('删除后无法恢复您确定要删除？', {
						icon : 3,
						title : '提示'
					}, function(index) {
						$.ajax({
							type : 'get',
							url : ctx + "/target/aqzrssc/delete/" + ids,
							success : function(data) {
								layer.alert(data, {
									offset : 'rb',
									shade : 0,
									time : 2000
								});
								top.layer.close(index);
								dg.datagrid('reload');
								dg.datagrid('clearChecked');
								dg.datagrid('clearSelections');
							}
						});
					});
				}

				//上传责任书
				function addzrs(aid, uid) {
				openDialog("上传信息", ctx + "/target/aqzrssc/create/" +aid+ "/" +uid , "800px", "400px", "");
				}
				//弹窗修改
				function upd() {

					var row = dg.datagrid('getSelected');
					if (row == null) {
						layer.msg("请选择一行记录！", {
							time : 1000
						});
						return;
					}
					
					if (row.id == null) {
						layer.msg("未上传责任书，不得修改", {
							time : 2000
						});
						return;
					} else {
						openDialog("修改安全责任书回执信息", ctx + "/target/aqzrssc/update/" + row.id, "800px", "400px", "");
					}
				}

				//查看
				function view() {
					var row = dg.datagrid('getSelected');
					if (row == null) {
						layer.msg("请选择一行记录！", {
							time : 1000
						});
						return;
					}
					if(!row.id){
						layer.msg("未上传责任书，不得查看");
						return ;
					}
					openDialogView("查看安全责任书回执信息", ctx + "/target/aqzrssc/view/" + row.id, "800px", "400px", "");
				}

				//创建查询对象并查询
				function search() {
					var obj = $("#searchFrom").serializeObject();
					dg.datagrid('load', obj);
				}

				function reset() {
					$("#searchFrom").form("clear");
					var obj = $("#searchFrom").serializeObject();
					dg.datagrid('load', obj);
				}
			</script>

</body>
</html>