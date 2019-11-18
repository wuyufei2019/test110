var dg;
var zzdg;
var tzdg;
var d;
var dwview;
var tzorzz;
$(function(){
	dg=$('#aqjg_dsf_dg').datagrid({    
	method: "post",
    url:ctx+'/dsffw/dsf/list', 
    fit : true,
	fitColumns : true,
	border : false,
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
  			{field : 'ID',title : 'id',checkbox : true,width : 50,align : 'center'},
			{field : 'm1',title : '单位名称',sortable : false,width : 100},
			{field : 'm2',title : '单位类型',sortable : false,width : 100,align : 'center'},
			{field : 'm3',title : '法人代表',sortable : false,width : 100,align : 'center'},
			{field : 'm6',title : '注册资金',sortable : false,width : 100,align : 'center'},
			{field : 'm7',title : '备注',sortable : false,width : 100,align : 'center'}
    ]],
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
                 view();
    },
	checkOnSelect:false,
	selectOnCheck:false,
    toolbar:'#aqjg_dsf_tb'
	});
	
});

function zzlist(){
	zzdg =$('#aqjg_dsf_zz_dg').datagrid({    
		method: "post",
		url:ctx+'/dsffw/dsf/tablistzz/'+dwid, 
	    fit : true,
		fitColumns : true,
		border : false,
		idField : 'id',
		striped:true,
		pagination:true,
		rownumbers:true,
		nowrap:false,
		pageNumber:1,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50 ],
		scrollbarSize:5,
		singleSelect:true,
		striped:true,
		columns:[[   
			{field : 'ID',title : 'id',checkbox : true,width : 50,align : 'center'},
			{field:'m1',title:'资质证书名称',sortable:true,width:100},    
			{field:'m2',title:'级别',sortable:true,width:100,align:'center', 
				formatter : function(value, row, index){
					if(value=='一级') return value='一级';
					if(value=='二级') return value='二级';
					if(value=='三级') return value='三级';
					if(value=='四级') return value='四级';
					if(value=='五级') return value='五级';
					if(value=='六级') return value='六级';
				}},
				{field:'m3',title:'许可内容',sortable:true,width:100,align:'center'},
				{field:'m4',title:'有效期起',sortable:true,width:100,align:'center',
					formatter:function(value,row,index){
						if(value!=null){
							var datetime = new Date(value);  
							 return datetime.format('yyyy-MM-dd');   
						}  
		            }
				},
				{field:'m5',title:'有效期止',sortable:true,width:100,align:'center',
					styler: function(value, row, index){
						var nowhm=(new Date()).getTime();
						if(nowhm>=value){
							return 'background-color:rgb(249, 156, 140);';
						}else{
							var cha=(value-nowhm)/1000/60/60/24;
							if(cha<=90) return 'background-color:rgb(255, 228, 141);';
						}
					},   
					formatter:function(value,row,index){
						if(value!=null){
							var datetime = new Date(value);  
							 return datetime.format('yyyy-MM-dd');   
						}  
		            }
				}
				]],	onDblClickRow : function(index, row) {
					updZz();
				},
				checkOnSelect:false,
				selectOnCheck:false,
	});
}

function tzlist(){
	tzdg =$('#aqjg_dsf_tz_dg').datagrid({    
		method: "post",
		url:ctx+'/dsffw/dsf/tablisttz/'+dwid, 
	    fit : true,
		fitColumns : true,
		border : false,
		idField : 'id',
		striped:true,
		pagination:true,
		rownumbers:true,
		nowrap:false,
		pageNumber:1,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50 ],
		scrollbarSize:5,
		singleSelect:true,
		striped:true,
		columns:[[    {
			field : 'ID',
			title : 'id',
			checkbox : true,
			width : 50,
			align : 'center'
		},
			{field:'m1',title:'姓名',sortable:true,width:100},    
			{field:'m2',title:'性别',sortable:true,width:100,align:'center',
				formatter : function(value, row, index){
					if(value=='0') return value='男';
					if(value=='1') return value='女';}},
			{field:'m8',title:'类别',sortable:false,width:100,align:'center'},		
			{field:'m3',title:'操作证号',sortable:true,width:100,align:'center'},
			{field:'m4',title:'身份证号',sortable:true,width:100,align:'center'},
			{field:'m5',title:'作业类型',sortable:true,width:100,align:'center'},
			{field:'m6',title:'到期日期',sortable:true,width:100,align:'center',
				formatter:function(value,row,index){
					if(value!=null){
						var datetime = new Date(value);  
						 return datetime.format('yyyy-MM-dd');  
					}  
	            },styler: function(value, row, index){
					var nowhm=(new Date()).getTime();
					if(nowhm>=value){
						return 'background-color:rgb(249, 156, 140);';
					}else{
						var cha=(value-nowhm)/1000/60/60/24;
						if(cha<=90) return 'background-color:rgb(255, 228, 141);';
					}
				}
			},
			{field:'m7',title:'备注',sortable:true,width:100,align:'center'}
			]],onDblClickRow : function(index, row) {
				updTz();
			},
			checkOnSelect:false,
			selectOnCheck:false,
	});
}



//弹窗增加
function add(u) {
	openDialog("添加第三方机构信息",ctx+"/dsffw/dsf/create/","800px", "420px","");
}

//增加资质
function addZz() {
	openDialog("添加资质信息",ctx+"/dsffw/dsf/zzcreate/" + dwid,"800px", "350px","");
}

//增加特种
function addTz() {
	openDialog("添加资质信息",ctx+"/dsffw/dsf/tzcreate/" + dwid,"800px", "350px","");
}

//删除
function del(){
	var row = dg.datagrid('getChecked');
	if(row==null||row=='') {
		layer.msg("请至少勾选一行记录！",{time: 1000});
		return;
	}

	var ids="";
	for(var i=0;i<row.length;i++){
		if(ids==""){
			ids=row[i].id;
		}else{
			ids=ids+","+row[i].id;
		}
	}

	top.layer.confirm('删除后无法恢复您确定要删除？', {icon: 3, title:'提示'}, function(index){
		$.ajax({
			type:'get',
			url:ctx+"/dsffw/dsf/delete/"+ids,
			success: function(data){
				layer.alert(data, {offset: 'rb',shade: 0,time: 2000}); 
				top.layer.close(index);
				dg.datagrid('reload');
				dg.datagrid('clearChecked');
				dg.datagrid('clearSelections');
			}
		});
	});
 
}

function delZzorTz(){
	if(tzorzz){
		delZz();
	}else{
		delTz();
	}
}

//删除资质
function delZz() {
	var row = zzdg.datagrid('getChecked');
	if(row==null||row=='') {
		layer.msg("请至少勾选一行记录！",{time: 1000});
		return;
	}

	var ids="";
	for(var i=0;i<row.length;i++){
		if(ids==""){
			ids=row[i].id;
		}else{
			ids=ids+","+row[i].id;
		}
	}

	top.layer.confirm('删除后无法恢复您确定要删除？', {icon: 3, title:'提示'}, function(index){
		$.ajax({
			type:'get',
			url:ctx+"/dsffw/dsf/zzdelete/"+ids,
			success: function(data){
				layer.alert(data, {offset: 'rb',shade: 0,time: 2000}); 
				top.layer.close(index);
				zzdg.datagrid('reload');
				zzdg.datagrid('clearChecked');
				zzdg.datagrid('clearSelections');
			}
		});
	});
 
	var cds = zzdg.datagrid("getChecked");
	if (cds == null || cds == "") {
		parent.$.messager.show({
			title : "提示",
			msg : "请选择需要删除的数据！",
			position : "bottomRight"
		});
		return;
	}
}

//删除特种
function delTz() {
	var row = tzdg.datagrid('getChecked');
	if(row==null||row=='') {
		layer.msg("请至少勾选一行记录！",{time: 1000});
		return;
	}

	var ids="";
	for(var i=0;i<row.length;i++){
		if(ids==""){
			ids=row[i].id;
		}else{
			ids=ids+","+row[i].id;
		}
	}

	top.layer.confirm('删除后无法恢复您确定要删除？', {icon: 3, title:'提示'}, function(index){
		$.ajax({
			type:'get',
			url:ctx+"/dsffw/dsf/tzdelete/"+ids,
			success: function(data){
				layer.alert(data, {offset: 'rb',shade: 0,time: 2000}); 
				top.layer.close(index);
				tzdg.datagrid('reload');
				tzdg.datagrid('clearChecked');
				tzdg.datagrid('clearSelections');
			}
		});
	});
}


//弹窗修改
function upd(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	
	openDialog("修改第三方机构信息",ctx+"/dsffw/dsf/update/"+row.id,"800px", "420px","");
	
}

function updZzprTz(){
	if(tzorzz){
		updZz();
	}else{
		updTz();
	}
}

//修改资质
function updZz(){
	var row = zzdg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	
	openDialog("资质信息详细页面",ctx+"/dsffw/dsf/zzupdate/"+row.id,"800px", "420px","");
	
}

//修改特种
function updTz(){
	var row = tzdg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	
	openDialog("特种作业人员信息详细页面",ctx+"/dsffw/dsf/tzupdate/"+row.id,"800px", "350px","");
	
}

//查看
function view(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	dwview=true;
	openDialogView("查看第三方机构信息",ctx+"/dsffw/dsf/view/"+row.id,"850px", "520px","");
	
}

//创建查询对象并查询
function search(){
	var obj=$("#searchFrom").serializeObject();
	dg.datagrid('load',obj); 
}

function reset(){
	$("#searchFrom").form("clear");
	var obj=$("#searchFrom").serializeObject();
	dg.datagrid('load',obj); 
	//if(btflg=='2') $("#filter_EQS_departmen").hide();
}

//导出
function fileexport(){
	window.expara=$("#searchFrom").serializeObject();
	window.exdata=[
			   		{colval:'m1', coltext:'单位名称'},
			   		{colval:'m2', coltext:'单位类型'},
			   		{colval:'m3', coltext:'法人代表'},
			   		{colval:'m4', coltext:'联系方式'},
			   		{colval:'m5', coltext:'注册地址'},
			   		{colval:'m6', coltext:'注册资金'},
			   		{colval:'m7', coltext:'项目负责人'},
			   		{colval:'m8', coltext:'联系方式'},
			   		{colval:'m9', coltext:'备注'}
		   	];
	layer.open({
	    type: 2,  
	    area: ['350px', '350px'],
	    title: '导出列选择',
        maxmin: false, 
        shift: 1,
	    content: ctx+'/dsffw/dsf/colindex',
		btn: ['导出'],
	    yes: function(index, layero){
	    	 var body = layer.getChildFrame('body', index);
	         var iframeWin = layero.find('iframe')[0];  
	         var inputForm = body.find('#excel_mainform');
	         iframeWin.contentWindow.doExport();
		  },
	});
		
}