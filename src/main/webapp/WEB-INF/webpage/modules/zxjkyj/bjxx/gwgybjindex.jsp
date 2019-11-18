<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>高危工艺报警信息</title>
	<meta name="decorator" content="default"/>
</head>
<body >
<div id="zxjkyj_bjxx_tb" style="padding:5px;height:auto">
	<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">				
		<div class="form-group">
			<c:if test="${usertype ne '1'}">
				<input type="text" name="qyname" class="easyui-combobox" style="height: 30px;" data-options="editable:true,panelHeight:'150px',valueField: 'text',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson',prompt: '企业名称' "/>
			</c:if>
			<input name="gwgyname" id="gwgyname" class="easyui-combobox" style="height: 30px;"
				   data-options="prompt: '工艺名称',editable:false,panelHeight:'150px',valueField: 'text',textField: 'text',url:'${ctx}/tcode/dict/gwgy'" />
			<input name="dwh" id="dwh" class="easyui-textbox" style="height: 30px;"
				   data-options="prompt: '点位号'" />
			<input name="type" id="type" class="easyui-combobox" style="height: 30px;"
				   data-options="prompt: '监测类型',editable:false,panelHeight:'auto',data:[
				   	   {value:'温度',text:'温度'},
				   	   {value:'压力',text:'压力'},
				   	   {value:'液位',text:'液位'}]" />
			<input id="datetype" name="datetype" type="text" class="easyui-combobox" style="height: 30px;"
				   data-options="editable:false ,panelHeight:'auto',valueField: 'value',textField: 'text', prompt: '时间段', data: [
                                            {value:'1',text:'近一周'},
                                            {value:'2',text:'近一个月'},
                                            {value:'3',text:'近三个月'} ]
                                    "/>
			<input name="starttime" id="starttime" class="easyui-datebox" style="height: 30px;" data-options="editable:false,prompt: '报警时间'" />~
			<input name="endtime" id="endtime" class="easyui-datebox" style="height: 30px;" data-options="editable:false,prompt: '报警时间'" />
			<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</span>
			<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span>
			<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="refresh()" ><i class="fa fa-refresh"></i> 刷新</span>
		</div>
	</form>
</div>

<table id="zxjkyj_bjxx_dg"></table> 

<script>
	var usertype=${usertype};
	var sigid = '${sigid}';
	var type = '${type}';
	var dg;
	var d;

	$(function(){
		dg=$('#zxjkyj_bjxx_dg').datagrid({
			nowrap:false,
			method: "post",
			url:ctx+'/zxjkyj/bjxx/gwgybjlist',
			fit : true,
			fitColumns : true,
			selectOnCheck:false,
			nowrap: false,
			idField : 'id',
			striped:true,
			scrollbarSize:0,
			pagination:true,
			rownumbers:true,
			pageNumber:1,
			pageSize : 50,
			pageList : [20, 50, 100, 150, 200, 250 ],
			singleSelect:true,
			columns:[[
				{field:'qyname',title:'企业名称',sortable:false,width:150,align:'center'},
				{field:'gwgyname',title:'工艺名称',sortable:false,width:80,align:'center'},
				{field:'bit_no',title:'点位号',sortable:false,width:80,align:'center'},
				{field:'kzfs',title:'控制方式',sortable:false,width:60,align:'center'},
				{field:'kzcs',title:'控制参数',sortable:false,width:80,align:'center'},
				{field:'alarmtype',title:'报警类型',sortable:false,width:60,align:'center'},
				{field:'value',title:'报警信息',sortable:false,width:80,align:'center',
					formatter : function(value, row, index) {
						return value.toFixed(2) + ' ' +row.unit;
					}
				},
				{field:'alarmtime',title:'报警时间',sortable:false,width:100,align:'center',
					formatter : function(value) {
						var datetime = new Date(value);
						return datetime.format("yyyy-MM-dd hh:mm:ss");
					}
				}
			]],
			onDblClickRow: function (rowdata, rowindex, rowDomElement){
			},
			onLoadSuccess: function(){
				if (usertype == '1') {
					$(this).datagrid("hideColumn",['qyname']);
				} else {
					$(this).datagrid("autoMergeCells",['qyname']);
				}
			},
			onLoadError:function(){
				layer.open({title: '提示',offset: 'rb',content: '数据加载中，请耐心等待。',shade: 0 ,time: 2000 });
			},
			rowStyler:function(index,row){
				if (row.m10!=null&&row.m10>0&&row.m8>row.m10){
					return 'background-color:rgb(232, 190, 101);';
				}
			},
			enableHeaderClickMenu: true,
			enableRowContextMenu: false,
			toolbar:'#zxjkyj_bjxx_tb'
		});

	});

	timerID = setInterval("refresh()",60000);

	//清空
	function reset(){
		$("#searchFrom").form("clear");
		search();
	}
	//查询
	function search(){
		var obj=$("#searchFrom").serializeObject();
		dg.datagrid('load',obj);
	}

	//datagrid刷新
	function refresh(){
		dg.datagrid('reload');
	}

</script>
</body>
</html>