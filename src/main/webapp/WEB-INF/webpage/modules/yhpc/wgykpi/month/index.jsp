<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>网格员月度绩效考核管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript" src="${ctx}/static/jquery-easyui-1.4.5/datagrid-detailview.js"></script>
<style type="text/css">
.myPanelHead .panel-title {
	font-size: 18px;
	height: 25px;
	line-height: 25px;
	color: red;
	text-align :center
}
</style>
</head>

<body>
<div class="easyui-panel" title="网格员考核规则"style="width:100%;height:100%;" data-options=" headerCls:'myPanelHead'">
	<div id="layout" class="easyui-layout" style="height:100%; ">
		<div data-options="region:'west',split:true,border:false,title:'网格名称'" style="width: 200px">
			<table id="menuDg"></table>
		</div>
		<div data-options="region:'center',split:true,border:false,title:'考核情况'">
			<div id="tg_tb" style="padding:5px;height:auto">
				<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">
					<div class="form-group">
						<input id="search_month" name="month" style="height: 30px;" class="easyui-datebox" data-options="editable :false,prompt: '日期'" />
						<input id="search_wgyname" style="height: 30px;" class="easyui-combobox" /> <span
							class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()"><i class="fa fa-search"></i> 查询</span>
						<span class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()"><i class="fa fa-refresh"></i>
							全部</span>
					</div>
				</form>
				<div>
					<shiro:hasPermission name="yhpc:wgykpicont:add">
						<button class="btn btn-success btn-sm" data-toggle="tooltip" data-placement="left" onclick="init()" title="添加">
							<i class="fa fa-plus"></i> 考核评分
						</button>
					</shiro:hasPermission>
				</div>
			</div>
			<table id="dg" style="width:100%;height:100%"></table>
		</div>
	</div>
</div>
			<div id="select_type" style="display:none;height:100%">
				<table id="areadata"></table>
			</div>

	<script type="text/javascript">
				var dg;
				var menuDg;
				var wgcode="";
				var ovid;
				$("#search_wgyname").combobox({ 
					editable:'false',
					method : 'get',
					valueField:'id',    
					textField:'name',  
					panelHeight:'150',
					prompt: '网格员姓名',
					url :'${ctx}/system/admin/xzqy/wguserjson?code='+wgcode
				});
				$(function() {
					menuDg = $('#menuDg').treegrid({
						method : "get",
						url : '${ctx}/system/admin/xzqy/xzqyjson',
						fit : true,
						fitColumns : true,
						border : false,
						idField : 'id',
						treeField : 'text',
						animate : true,
						rownumbers : true,
						singleSelect : true,
						scrollbarSize : 0,
						striped : true,
						loadFilter: lazyLoadFilter,
						columns : [ [ {
							field : 'id',
							title : 'id',
							hidden : true
						}, {
							field : 'text',
							title : '名称',
							width : 100
						} ] ],
						enableHeaderClickMenu : false,
						enableHeaderContextMenu : false,
						enableRowContextMenu : false,
						dataPlain : true,
						onClickRow : function(rowData) {
							$('#layout').layout('panel', 'center').panel({
								title : rowData.text + '网格员考核情况'
							});
							wgcode = rowData.id;
							d.datagrid('reload', {
								wgcode : wgcode
							});
							$("#searchFrom").form("clear");
							$('#search_wgyname').combobox('reload','${ctx}/system/admin/xzqy/wguserjson?code='+wgcode);
						}
					});

					d = $('#dg').datagrid({
						url : '${ctx}/yhpc/wgykpi/month/overviewlist',
						fitColumns : true,
						border : true,
						pagination:true,
						rownumbers:true,
						pageNumber:1,
						idField : 'id',
						pageSize : 50,
						pageList : [ 50, 100, 150, 200, 250 ],
						scrollbarSize:0,
						singleSelect:true,
						columns : [ [{
						field : 'id',
						title : 'id',
						hidden : true
						},{
							field : 'time',
							title : '年月',
							width : 50
						}, {
							field : 'name',
							title : '网格员姓名',
							width : 50
						}, {
							field : 'score',
							title : '得分',
							width : 40,
							sortable: true,
							styler: function(value, row, index){
	                			return 'background-color:rgb(249, 156, 130)';
	            	}
						}, {
							field : 'caozuo',
							title : '操作',
							width : 50,
							formatter : function(value, row) {
								return "<a class='btn btn-info btn-xs' onclick='view(" + row.id + ")'>查看信息</a>&nbsp;&nbsp;"
								       +"<a class='btn btn-danger btn-xs' onclick='upd(" + row.id + ")'>修改信息</a>";
							}
						}
						] ],
						onLoadSuccess : function(rowdata, rowindex, rowDomElement) {
							d.datagrid("autoMergeCells", [ 'time', 'wgyname' ]);
						},
						toolbar : '#tg_tb',
					});
					loadMonthContorl($('#search_month'));
				});

				//弹窗增加
				function init() {
					var row = menuDg.datagrid('getSelected');
					if(row==null) {
						layer.msg("请先选择网格！",{time: 1000});
						return;
					}
					openDialog();
				}
				
				function openDialog(id){
					ovid=id;
					layer.open({
						type : 2,
						area : [ '100%', '100%' ],
						title : '评分信息',
						maxmin : false,
						content : '${ctx}/yhpc/wgykpi/month/initform',
						zIndex : 1,
						btn : [ '全部保存', '关闭' ],
						yes : function(index,layero) {
							var iframeWin = layero.find('iframe')[0].contentWindow;
							if(dg.datagrid('validateRow', iframeWin.editIndex)){
								dg.datagrid('endEdit',iframeWin.editIndex);
							}else{
								return ;
							}
							var rows = dg.datagrid('getData').rows;
							len = rows.length;
							
							if (len > 0) {
								var rs = [];
								var count= 0;
								for (var i = 0; i < len; i++) {
									rs.push({
										id : rows[i].mid,
										id2 : rows[i].id2,
										score : rows[i].score,
										note : rows[i].note
										/* time : iframeWin.time */
									});
									count+=parseFloat(rows[i].score);
								}
								var overview={"time" : iframeWin.time, "score" : count ,"id1" :rows[0].uid,"id" : rows[0].ovid };
								$.ajax({
									type : "POST",
									url : "${ctx}/yhpc/wgykpi/month/init",
									data : {'list' : JSON.stringify(rs),'entity': JSON.stringify(overview)},
									success : function(data) {
										if (data == 'success')
											layer.msg("保存成功");
										else
											layer.msg("保存失败");
											layer.close(index);
											d.datagrid('reload', {wgcode : wgcode});
										$.jBox.closeTip();
									}
								});
							}
							
						},
						cancel : function(index) {
						}
					});
				}
					
				//修改
				function upd(id) {
					openDialog(id);
				}

				function view(id) {
						layer.open({
							type: 1,  
							area: ['100%', '100%'],
							title:'查看评分信息',
							content: $("#select_type"),
							btn: ['关闭'],
						    success: function(layero, index){
						    	   var dg =$('#areadata').datagrid({    
						    		nowrap:false,
						    		method: "post",
						    		url:'${ctx}/yhpc/wgykpi/month/viewdetail/'+id,
						    	    loadMsg :'正在加载',
						    		fitColumns : true,
									border : true,
									iconCls : 'icon',
									animate : true,
									rownumbers : true,
									singleSelect : true,
									striped : true,
									nowrap : false,
									scrollbarSize : 0,
						    	    columns:[[{
										field : 'time',
										title : '年月',
										width : 40
									}, {
										field : 'wgyname',
										title : '网格员姓名',
										width : 40
									}, {
										field : 'name',
										title : '评分项目',
										width : 100 ,
										formatter : function(value,row){
											return value+"("+row.allscore+"分)";
										}
									}, {
										field : 'content',
										title : '考核内容',
										width : 200
									}, {
										field : 'note',
										title : '备注',
										width : 100
									
									}, {
										field : 'score',
										title : '得分',
										width : 40
										}
						    	]],
						    	onLoadSuccess : function(rowdata, rowindex, rowDomElement) {
									dg.datagrid("autoMergeCells", [ 'time', 'wgyname']);
								},
						    	});
						      },
							cancel: function(index){ 
							}
						}); 
					}
				//创建查询对象并查询
				function search() {
					var time = $("#search_month").datebox("getValue");
					d.datagrid('load',{'wgcode' : wgcode,'time' : time ? time : "", 'wgyid' : $("#search_wgyname").combobox("getValue")});
				}
				function reset() {
					$("#searchFrom").form("clear");
					d.datagrid('load', {'wgcode' : wgcode});
				}

				function loadMonthContorl(M2) {
					//日期控件只显示年月
					M2.datebox({
						onShowPanel : function() {//显示日期选择对象后再触发弹出月份层的事件，初始化时没有生成月份层
							span.trigger('click'); //触发click事件弹出月份层
							if (p.find('div.calendar-menu').is(':hidden'))
								p.find('div.calendar-menu').show();
							if (!tds)
								setTimeout(function() {//延时触发获取月份对象，因为上面的事件触发和对象生成有时间间隔
									tds = p.find('div.calendar-menu-month-inner td');
									tds.click(function(e) {
										e.stopPropagation(); //禁止冒泡执行easyui给月份绑定的事件
										var year = /\d{4}/.exec(span.html())[0]//得到年份
										, month = parseInt($(this).attr('abbr'), 10); //
										M2.datebox('hidePanel')//隐藏日期对象
										.datebox('setValue', year + '-' + (month<10?'0'+month : month)); //设置日期的值
									});
								}, 0);
							yearIpt.unbind();//解绑年份输入框中任何事件
						},
						parser : function(s) {
							if (!s)
								return new Date();
							var arr = s.split('-');
							return new Date(parseInt(arr[0], 10), parseInt(arr[1], 10) - 1, 1);
						},
						formatter : function(d) {
							return d.format("yyyy-MM");
						}
					});
					var p = M2.datebox('panel'), //日期选择对象
					tds = false, //日期选择对象中月份
					aToday = p.find('a.datebox-current'), yearIpt = p.find('input.calendar-menu-year'), //年份输入框
					//显示月份层的触发控件
					span = aToday.length ? p.find('div.calendar-title span') : p.find('span.calendar-text');
					if (aToday.length) {//重新绑定新事件设置日期框为今天，防止弹出日期选择面板
						aToday.unbind('click').click(function() {
							var now = new Date();
							M2.datebox('hidePanel').datebox('setValue', now.format("yyyy-MM"));
						});
					}
				}
			</script>
</body>
</html>