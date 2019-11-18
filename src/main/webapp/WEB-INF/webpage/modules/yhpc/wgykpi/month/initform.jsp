<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>网格员月度绩效考核管理</title>
<meta name="decorator" content="default" />
</head>

<body>
   <div id="tg_tb" style="padding:5px;height:auto">
      <form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">
         <div class="form-group" id="group">
            <input id="add_month" style="height: 30px;" class="easyui-datebox" data-options="editable :false,prompt: '日期'" />
            <input id="add_wgyname" style="height: 30px;" class="easyui-combobox"  />
            <span class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()"><i class="fa fa-search"></i> 选择网格员</span><!--  <span
               class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()"><i class="fa fa-refresh"></i> 全部</span> -->
         </div>
      </form>
   </div>

   <table id="dg"></table>
   <script type="text/javascript">
	$("#add_wgyname").combobox({ 
		editable:'false',
		method : 'get',
		valueField:'id',    
		textField:'name',  
		panelHeight:'150',
		prompt: '网格员姓名',
		url :'${ctx}/system/admin/xzqy/wguserjson?code='+parent.wgcode
	});
	var time = new Date().format("yyyy-MM");
	var editIndex = undefined;
	var url="";
	if(parent.ovid){
		$("#group").hide();
		url="${ctx}/yhpc/wgykpi/month/viewdetail/"+parent.ovid;
	}
				parent.loadMonthContorl($('#add_month'));
				parent.dg = $('#dg').datagrid({
					method : "post",
					url : url,
					fitColumns : true,
					border : true,
					singleSelect : true,
					striped : true,
					nowrap : false,
				    toolbar:'#tg_tb',
					columns : [ [{
						field : 'time',
						title : '年月',
						width : 40,
						formatter : function(value,row) {
							if(value) return value;
							else{
								return time;
							}
						}
					}, {
						field : 'wgyname',
						title : '网格员姓名',
						width : 50
					}, {
						field : 'name',
						title : '评分项目',
						width : 100,
						formatter : function(value, row) {
							return row.name + "(" + row.allscore + "分)";
						}
					}, {
						field : 'content',
						title : '考核内容',
						width : 200
					}, {
						field : 'note',
						title : '备注',
						width : 100,
						editor : 'text',formatter : function(value,row){
							if (!value) {
								row.note ="";
								return row.note;
							} else
								return value;
						}
					}, {
						field : 'score',
						title : '得分',
						width : 40,
						formatter : function(value,row){
							if(!value){
								row.score=0;
								return row.score;
							}else
								return value;
						},
						editor : {type : 'textbox', options: {
							 validType:['number','FUN[ValidateNumber,\'不能大于项目总分\']']
						}}
					} ] ],
					onLoadSuccess : function(rowdata) {
						var rows = rowdata.rows;
						for (index in rows){
							if(rows[index].name!='安全巡查'){
								editIndex=index;
								parent.dg.datagrid('beginEdit', index);
								break;
							}
						}
						parent.dg.datagrid("autoMergeCells", [ 'time', 'wgyname' ]);
					},
					onClickCell : function(index, field, value) {
						var row = parent.dg.datagrid('getData').rows[index];
						if(row.name=="安全巡查"){
      						layer.msg("统计数据，不可修改");
      					}else{
      						if (endEditing()){
      							editIndex=index;
      							parent.dg.datagrid('beginEdit', index);
      							parent.dg.datagrid("autoMergeCells", [ 'time', 'wgyname' ]);
      						}
      					}
					} 
				});

			 	
				function endEditing() {
					if (editIndex == undefined) {
						return true;
					}
					if (parent.dg.datagrid('validateRow', editIndex)) {
						parent.dg.datagrid('endEdit', editIndex);
						return true;
					} else {
						return false;
					}
				} 
				
				function ValidateNumber(val) {
					var row = parent.dg.datagrid('getData').rows[editIndex];
					if (row.allscore < val)
						return false;
					else
						return true;
				}
				// 创建查询对象并查询
				function search() {
					time = new Date().format("yyyy-MM");
					dtime = $("#add_month").datebox("getValue");
					time = dtime ? dtime : time;
					var wgyname = $("#add_wgyname").combobox("getValue");
					if (wgyname) {
						parent.dg.datagrid('options').url = '${ctx}/yhpc/wgykpi/month/examinelist?wgcode=' + parent.wgcode;
						parent.dg.datagrid('load', {
							'wgyname' : wgyname,
							'time' : time
						});
					}
				}

				function reset() {
					$("#searchFrom").form("clear");
					var obj = $("#searchFrom").serializeObject();
					parent.dg.datagrid('load', obj);
				}

				function onchange() {
					search();
				}
			</script>
</body>
</html>