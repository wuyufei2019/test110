<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>目标信息</title>
<meta name="decorator" content="default" />
<script type="text/javascript" src="${ctx}/static/model/js/targetmanger/mbzb/mbsz/qy_index.js?v=1.1"></script>
</head>
<body>
   <!-- 工具栏 -->
   <div id="target_zbsz_tb" style="padding:5px;height:auto">
      <form id="searchFrom" style="margin-bottom: 8px;" class="form-inline pull-left">
         <div class="form-group">
            <input type="text" id="target_zbsz_m1" name="target_zbsz_m1" style="height:30px" class="easyui-textbox"
               data-options="width:200,prompt: '指标名称'" />
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
               <shiro:hasPermission name="target:zbsz:add">
                  <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add()" title="添加">
                     <i class="fa fa-plus"></i> 添加
                  </button>
               </shiro:hasPermission>
               <shiro:hasPermission name="target:zbsz:update">
                  <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd()" title="修改">
                     <i class="fa fa-file-text-o"></i> 修改
                  </button>
               </shiro:hasPermission>
               <shiro:hasPermission name="target:zbsz:delete">
                  <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除">
                     <i class="fa fa-trash-o"></i> 删除
                  </button>
               </shiro:hasPermission>
               <shiro:hasPermission name="target:zbsz:view">
                  <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view()" title="查看">
                     <i class="fa fa-search-plus"></i> 查看
                  </button>
               </shiro:hasPermission>
             <%--   <shiro:hasPermission name="target:zbsz:export">
                  <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="fileexport()" title="导出">
                     <i class="fa fa-external-link"></i> 导出
                  </button>
               </shiro:hasPermission> --%>
               <%-- <shiro:hasPermission name="target:zbsz:exin">
				<button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="openImportDialog('${ctx}/target/zbsz/exinjump','${ctx}/target/zbsz/exin','${ctx}/static/templates/指标信息导入模板.xls')" title="导入"><i class="fa fa-folder-open-o"></i> 导入</button>
			</shiro:hasPermission> --%>
               <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新">
                  <i class="glyphicon glyphicon-repeat"></i> 刷新
               </button>

            </div>

         </div>
      </div>

   </div>


   <table id="target_zbsz_dg"></table>
   <script type="text/javascript">
   				var qyid = '${qyid}';
   
				//弹窗增加
				function add(u) {
					openDialog("添加指标信息", ctx + "/target/zbsz/create/", "550px", "300px", "");
				}

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
						if (ids == "") {
							ids = row[i].id;
						} else {
							ids = ids + "," + row[i].id;
						}
					}

					top.layer.confirm('删除后无法恢复您确定要删除？', {
						icon : 3,
						title : '提示'
					}, function(index) {
						$.ajax({
							type : 'get',
							url : ctx + "/target/zbsz/delete/" + ids,
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

				//弹窗修改
				function upd() {
					var row = dg.datagrid('getSelected');
					if (row == null) {
						layer.msg("请选择一行记录！", {
							time : 1000
						});
						return;
					}

					openDialog("修改指标信息", ctx + "/target/zbsz/update/" + row.id, "550px", "300px", "");

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

					openDialogView("查看指标信息", ctx + "/target/zbsz/view/" + row.id,"550px", "auto", "");

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

				//导出
				function fileexport() {
					window.expara = $("#searchFrom").serializeObject();
					var Columns = dg.datagrid("options").columns[0];
					window.exdata = [];
					for (var i = 0; i < Columns.length; i++) {
						if (Columns[i].field.toUpperCase() != "ID")
							window.exdata.push({
								colval : Columns[i].field,
								coltext : Columns[i].title
							});
					}
					layer.open({
						type : 2,
						area : [ '350px', '350px' ],
						title : '导出列选择',
						maxmin : false,
						shift : 1,
						content : ctx + '/target/zbsz/colindex',
						btn : [ '导出' ],
						yes : function(index, layero) {
							var body = layer.getChildFrame('body', index);
							var iframeWin = layero.find('iframe')[0];
							var inputForm = body.find('#excel_mainform');
							iframeWin.contentWindow.doExport();
						},
					});

				}
			</script>
</body>
</html>