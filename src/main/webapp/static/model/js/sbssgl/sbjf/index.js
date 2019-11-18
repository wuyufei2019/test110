var dg;
var d;
$(function(){
	dg=$('#sbssgl_sbjf_dg').datagrid({    
	method: "post",
    url:ctx+'/sbssgl/sbjf/list', 
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
        {field:'id',title:'id',checkbox:true,width:50,align:'center'},    
        {field:'qyname',title:'所属企业',sortable:false,width:70,align : 'center'},
        {field:'m4',title:'设备编号',sortable:false,width:40,align:'center'},
        {field:'m5',title:'名称型号',sortable:false,width:50,align:'center'},
        {field:'m6',title:'出厂编号',sortable:false,width:40,align:'center'},
        {field:'m15',title:'启用日期',sortable:false,width:40,align:'center',
      	    formatter : function(value, row, index) {
            	if(value!==null&&value!='') {
            		var datetime=new Date(value);
            		 return datetime.format('yyyy-MM-dd');  
            	}
        	} 
        },
        {field:'m1',title:'调出单位',sortable:false,width:60,align : 'center'},
        {field:'m2',title:'调入单位',sortable:false,width:60,align : 'center'},
        {field:'m3',title:'事由',sortable:false,width:40,align : 'center'},
        /*{field:'m20',title:'状态',sortable:false,width:50,align:'center',
        	formatter : function(value, row, index) {
        		if(value == '1'){
        			return "待建立设备信息";
        		}else if(value == '2'){
        			return "已建立设备信息";
        		}else{
        			return "待上传附件";
        		}
        	} 
        },*/
        {field:'cz',title:'操作',sortable:false,width:50,align:'center',
        	formatter : function(value, row, index) {
        		if(uploadrole == '1' && row.m20 == '0'){
        			return "<a style='margin:2px' class='btn btn-success btn-xs' onclick=uploadfj("+row.id+")>上传附件</a>";
        		}else if(sbglrole == '1' && row.m20 == '1'){
        			return "<a style='margin:2px' class='btn btn-primary btn-xs' onclick=addsbgl("+row.id+")>添加设备信息</a>";
        		}else if(sbglrole == '1' && row.m20 == '2'){
        			return '已建立设备信息';
        		}else {
        			return '';
        		}
        	} 
        }
    ]],
    onLoadSuccess: function(){
    	if(type == '1'){
			$(this).datagrid("hideColumn", [ 'qyname' ]);
		}else{
			$(this).datagrid("showColumn", [ 'qyname' ]);
			$(this).datagrid("autoMergeCells", [ 'qyname' ]);
		}
    },
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
        view();
    },
	checkOnSelect:false,
	selectOnCheck:false,
    toolbar:'#sbssgl_sbjf_tb'
	});
});

//弹窗增加设备台账信息
function addsbgl(id) {
	openDialog("添加设备信息",ctx+"/sbssgl/sbgl/create1/"+id,"800px", "400px","");
}

//上传附件
function uploadfj(id){
	openDialog("上传附件",ctx+"/sbssgl/sbjf/uploadindex/"+id,"400px", "250px","");
}

//创建查询对象并查询
function search(){
	var obj=$("#searchFrom").serializeObject();
	dg.datagrid('load',obj); 
}

function reset(){
	$("#searchFrom").form("clear");
	search();
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
			url:ctx+"/sbssgl/sbjf/delete/"+ids,
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

//查看
function view(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	openDialogView("查看设备启用单信息",ctx+"/sbssgl/sbjf/view/"+row.id,"1000px", "450px","");
}

//弹窗修改
function upd(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	openDialog("修改设备启用单信息",ctx+"/sbssgl/sbjf/update/"+row.id,"1000px", "450px","");
}

//导出word
function fileexport(){
	var row = dg.datagrid('getSelected');
	if(row==null){
		layer.msg('请选择一行记录',{time: 1000});
		return;
	}
	
	$.ajax({
		url:ctx+"/sbssgl/sbjf/export/"+row.id,
		type:"POST",
		success:function(data){
			window.open(ctx+data);
		}
	});
}