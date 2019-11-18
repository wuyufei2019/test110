<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>安全检查与隐患治理</title>
	<meta name="decorator" content="default"/>
</head>
<body >
<script type="text/javascript">
var dg;
var dg2;
	$(function(){
		dg=$('#yhpc_xjjl_dg').datagrid({
			method: "post",
			url:'',
			fit : true,
			fitColumns : true,
			selectOnCheck:false,
			idField : 'id',
			striped:true,
			pagination:true,
			rownumbers:true,
			nowrap:false,
			pageNumber:1,
			pageSize : 50,
			pageList : [ 50, 100, 150, 200, 250 ],
			scrollbarSize:5,
			singleSelect:true,
			striped:true,
			columns:[[
				{field : 'id',title : 'id',checkbox : true,width : 50,align : 'center'},
				{field : 'qyname',title : '企业名称',sortable : false,width : 80},
				{field : 'fzr',title : '主要负责人',sortable : false,width : 50,align : 'center'},
				{field : 'jcdname',title : '检查点',sortable : false,width : 80,align : 'center'},
				{field : 'name',title : '所属班次',sortable : false,width : 50,align : 'center',
					formatter : function(value, row, index) {
						if(value==null||value=='') {
							return '该班次已被删除';
						}else{
							return value;
						}
					}
				},
				{field : 'createtime',title : '检查时间',sortable : false,width : 70,align : 'center',
					formatter : function(value, row, index) {
						if(value!=null&&value!='') {
							var datetime=new Date(value);
							return datetime.format('yyyy-MM-dd hh:mm:ss');
						}
					}},
				{field : 'uname',title : '检查人',sortable : false,width : 60,align : 'center'},
				{field : 'checkresult',title : '检查结果',sortable : false,width : 40,align : 'center',
					formatter : function(value, row, index) {
						if(value=='0'){
							return '无隐患';
						}else if(value=='1'){
							return '有隐患';
						}
					},styler: function(value,row,index){
						if (value == '1'){
							return 'color:red;';
						}
					}
				},
				{field : 'note',title : '问题备注',sortable : false,width : 100,align : 'center'},
				{field : 'checkphoto',title : '现场照片',sortable : false,width : 60,align : 'center',
					formatter : function(value, row, index) {
						var content="";
						if(value!=null&&value!='') {
							var arr1=value.split("||");
							for (var i = 0; i < arr1.length-1; i++) {
								content=content+ '<img onclick=openImgView("'+arr1[i]+'") src='+arr1[i]+' style=margin-right:5px;max-width:100px;max-height:50px; />';
							}
							return content;
						}else{
							return '/';
						}
					}
				}
			]],
			onLoadSuccess: function(){
				if(type == '1'){
					$(this).datagrid("hideColumn", [ 'qyname' ]);
				}else{
					$(this).datagrid("showColumn", [ 'qyname' ]);
					/*$(this).datagrid("autoMergeCells", [ 'qyname' ]);*/
				}
			},
			onLoadError:function(){
				layer.open({title: '提示',offset: 'rb',content: '数据加载中，请耐心等待。',shade: 0 ,time: 2000 });
			},
			onDblClickRow: function (rowdata, rowindex, rowDomElement){
				viewXjdnr();
			},
			rowStyler:function(index,row){
				if (row.checkresult == '1'){
					return 'background-color:#f9ebed;';
				}
			},
			checkOnSelect:false,
			selectOnCheck:false,
			toolbar:'#yhpc_xjjl_tb'
		});

		dg2=$('#yhpc_yhpcjl_dg').datagrid({
			method: "post",
			url:'',
			fit : true,
			fitColumns : true,
			selectOnCheck:false,
			idField : 'id',
			striped:true,
			pagination:true,
			rownumbers:true,
			nowrap:false,
			pageNumber:1,
			pageSize : 50,
			pageList : [ 50, 100, 150, 200, 250 ],
			scrollbarSize:5,
			singleSelect:true,
			striped:true,
			columns:[[
				{field : 'id',title : 'id',checkbox : true,width : 50,align : 'center'},
				{field : 'qyname',title : '企业名称',sortable : false,width : 50,align : 'center'},
				{field : 'fzr',title : '主要负责人',sortable : false,width : 50,align : 'center'},
				{field : 'xjdname',title : '巡检点名称',sortable : false,width : 50,align : 'center'},
				{field : 'jcnr',title : '隐患内容',sortable : false,width : 90,align : 'center'},
				{field : 'yhjb',title : '隐患级别',sortable : false,width : 20,align : 'center',
					formatter : function(value, row, index) {
						if(value=='1') {
							return '一般';
						}else{
							return '重大';
						}
					}
				},
				{field : 'createtime',title : '发现时间',sortable : false,width : 40,align : 'center',
					formatter : function(value, row, index) {
						if(value!=null&&value!='') {
							var datetime=new Date(value);
							return datetime.format('yyyy-MM-dd hh:mm:ss');
						}
					}
				},
				{field : 'dangerphoto',title : '隐患照片',sortable : false,width : 40,align : 'center',
					formatter : function(value, row, index) {
						var content="";
						if(value!=null&&value!='') {
							var arr1=value.split("||");
							for (var i = 0; i < arr1.length-1; i++) {
								content=content+ '<img onclick=openImgView("'+arr1[i]+'") src='+arr1[i]+' style=margin-right:5px;max-width:100px;max-height:50px; />';
							}
							return content;
						}else{
							return '/';
						}
					}
				},
				{field : 'zgrname',title : '整改人',sortable : false,width : 30,align : 'center'},
				{field : 'zgsj',title : '整改时间',sortable : false,width : 40,align : 'center',
					formatter : function(value, row, index) {
						if(value!=null&&value!='') {
							var datetime=new Date(value);
							return datetime.format('yyyy-MM-dd hh:mm:ss');
						}
					}
				},
				{field : 'zgzp',title : '整改后照片',sortable : false,width : 40,align : 'center',
					formatter : function(value, row, index) {
						var content="";
						if(value!=null&&value!='') {
							var arr1=value.split("||");
							for (var i = 0; i < arr1.length-1; i++) {
								content=content+ '<img onclick=openImgView("'+arr1[i]+'") src='+arr1[i]+' style=margin-right:5px;max-width:100px;max-height:50px; />';
							}
							return content;
						}else{
							return '/';
						}
					}
				},
				{field : 'dangerstatus',title : '隐患状态',sortable : false,width : 30,align : 'center',
					formatter : function(value, row, index) {
						if(value == '1'){
							return "<a style='margin:2px' class='btn btn-danger btn-xs'>整改待复查</a>"
						}else if(value == '2'){
							return "<a style='margin:2px' class='btn btn-danger btn-xs'>复查未通过</a>"
						}else if(value == '3'){
							return "<a  class='btn btn-success btn-xs'>整改完成</a>"
						}else{
							return "<a style='margin:2px' class='btn btn-danger btn-xs' >未整改</a>"
						}
					}
				},
				{field : 'cz',title : '操作',sortable : false,width : 20,align : 'center',
					formatter : function(value, row, index) {
						return " <a class='btn btn-info btn-xs' onclick='viewjd("+row.id+")'>查看进度</a> ";
					}
				}
			]],
			onBeforeLoad:function(param){
				if(f!=''&&f=='sys'){
					$("#zgzt").combobox('setValue',cljd);
					param.zgzt=cljd;
				}
			},
			onLoadSuccess: function(){
				if(type == '1'){
					$(this).datagrid("hideColumn", [ 'qyname' ]);
				}else{
					$(this).datagrid("showColumn", [ 'qyname' ]);
					/*$(this).datagrid("autoMergeCells", [ 'qyname' ]);*/
				}
			},
			onLoadError:function(){
				layer.open({title: '提示',offset: 'rb',content: '数据加载中，请耐心等待。',shade: 0 ,time: 2000 });
			},
			onDblClickRow: function (rowdata, rowindex, rowDomElement){
				view();
			},
			rowStyler:function(index,row){
				if (row.dangerstatus != '3'){
					return 'background-color:#f9ebed;';
				}
			},
			checkOnSelect:false,
			selectOnCheck:false,
			toolbar:'#yhpc_yhpcjl_tb'
		});
	});
</script>
<div class="easyui-tabs" fit="true">
	<div title="检查记录" style="height:100%;" data-options="">
		<div id="yhpc_xjjl_tb" style="padding:5px;height:auto">
			<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">
				<div class="form-group">
					<input type="text" id="yhpc_xjjl_qyname" name="yhpc_xjjl_qyname" class="easyui-combobox"  style="height: 30px;" data-options="editable:true ,valueField: 'text',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson',prompt: '企业名称' "/>
					<input name="yhpc_xjjl_jcdname" class="easyui-textbox"  style="height: 30px;" data-options="prompt: '检查点名称'" />
					<input name="yhpc_xjjl_starttime" class="easyui-datebox"  style="height: 30px;" data-options="editable:false,prompt: '巡检开始时间'" />
					<input name="yhpc_xjjl_finishtime" class="easyui-datebox"  style="height: 30px;" data-options="editable:false,prompt: '巡检结束时间'" />
					<input type="text" name="yhpc_xjjl_checkresult" class="easyui-combobox" style="height: 30px;" data-options="panelHeight:'auto',editable:false,prompt: '检查结果',data: [
						         {value:'0',text:'无隐患'},
						         {value:'1',text:'有隐患'}] "/>
					<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</span>
					<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span>
				</div>
			</form>

			<div class="row">
				<div class="col-sm-12">
					<div class="pull-left">
						<shiro:hasPermission name="yhpc:xjjl:export2">
							<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="fileexport()" title="导出"><i class="fa fa-external-link"></i> 导出</button>
						</shiro:hasPermission>
						<shiro:hasPermission name="yhpc:xjjl:delete">
							<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除"><i class="fa fa-trash-o"></i> 删除</button>
						</shiro:hasPermission>
						<button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>

					</div>
					<div class="pull-right">
					</div>
				</div>
			</div>
		</div>
			<table id="yhpc_xjjl_dg"></table>
		</div>

	<div title="隐患治理" style="height:100%;">
		<div id="yhpc_yhpcjl_tb" style="padding:5px;height:auto">
			<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">
				<div class="form-group">
					<input class="easyui-combobox" name="yhjb" style="height: 30px;" data-options="prompt: '隐患级别' ,
											 editable:false ,panelHeight:'auto' ,data: [
											        {value:'1',text:'一般'},
											        {value:'2',text:'重大'}
																	]"/>
					<input name="yhpc_yhpcjl_starttime" class="easyui-datebox"  style="height: 30px;" data-options="editable:false,prompt: '检查开始时间'" />
					<input name="yhpc_yhpcjl_finishtime" class="easyui-datebox"  style="height: 30px;" data-options="editable:false,prompt: '检查结束时间'" />
					<input type="text" id="zgzt"name="zgzt" class="easyui-combobox" style="height: 30px;" data-options="panelHeight:'auto',editable:false,prompt: '隐患处理情况',data: [
									         {value:'0',text:'未整改'},
									         {value:'1',text:'整改待复查'},
									         {value:'2',text:'复查未通过'},
									         {value:'3',text:'整改完成'}] "/>
					<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</span>
					<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span>
				</div>
			</form>

			<div class="row">
				<div class="col-sm-12">
					<div class="pull-left">
					<span id="divper">
						<shiro:hasPermission name="yhpc:yhpcjl:view">
							<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view()" title="查看"><i class="fa fa-search-plus"></i> 查看</button>
						</shiro:hasPermission>
			        	</span>
						<span id="divper2">
						</span>
						<button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>

					</div>
					<div class="pull-right">
					</div>
				</div>
			</div>
		</div>
		<table id="yhpc_yhpcjl_dg"></table>
	</div>
</div>
</body>
</html>