var dg;
var d;
var url;
$(function(){
	url = ctx+'/sbssgl/sbbf/list?sbtype=' + sbtype;
	dg=$('#sbssgl_sbbf_dg').datagrid({    
	method: "post",
    url: url, 
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
        {field:'deptname',title:'部门名称',sortable:false,width:50,align : 'center'},
        {field:'m1',title:'设备编号',sortable:false,width:50,align : 'center'},
        {field:'m2',title:'设备名称',sortable:false,width:50,align : 'center'},
        {field:'m6',title:'购入时间',sortable:false,width:50,align:'center',
        	formatter: function(value) {
        		if (value) {
        			return new Date(value).format("yyyy-MM-dd");
        		}
        	}
        },
        {field:'zcy',title:'资产原/净值',sortable:false,width:50,align:'center'},
        {field:'gdsynx',title:'规定使用年限',sortable:false,width:50,align:'center'},
        {field:'sjsynx',title:'实际使用年限',sortable:false,width:50,align:'center'},
        {field:'status',title:'状态',sortable:false,width:40,align:'center',
        	formatter : function(value, row, index) {
        		if (value == '1') {
        			return "<span style='color: white;'>待审核</span>";
        		} else if(value == '2') {
        			return "<span style='color: white;'>通过</span>";
        		} else if(value == '3') {
        			return "<span style='color: white;'>不通过</span>";
        		} else {
        			return "待上传附件";
        		}
        	},
            styler: function(value, row, index){
            	if (value == '1') {//当状态为通过时，背景颜色为黄色    
            		return 'background-color: #f8ac59';
        		} else if (value == '2') {//当状态为通过时，背景颜色为绿色    #f8ac59
            		return 'background-color: #23c6c8';
        		} else if (value == '3') {//当状态为不通过时，背景颜色为红色
            		return 'background-color: #ed5565';
        		} 
        	} 
        },
        {field:'cz',title:'操作',sortable:false,width:70,align:'center',
        	formatter : function(value, row, index) {	
        		if (row.status == '0' && uploadrole == '1') {//状态为待上传附件
        			
        			return "<a class='btn btn-warning btn-xs' onclick=uploadfj("+row.id+")>上传附件</a>";
        			
        		} else if (row.status == '1' && role == '1') {//状态为待审核
        			
        			var fj = row.fj;
        			var fileurl = fj.split("||");
        			
        			return "<a class='btn btn-info btn-xs' onclick=changeZt("+row.id+","+row.sbid+")>审核</a>"+
        				   "<a class='btn btn-warning btn-xs' onclick='window.open(\""+fileurl[0]+"\")' style='margin-left: 5px;'> 查看附件</a>";
        			
        		} else if (row.status == '2') {//当状态为通过时，只显示审核记录
        			
        			return "<a class='btn btn-success btn-xs' onclick=viewShjl("+row.id+") style='margin-left: 5px;'>查看审核记录</a>";
        			
        		} else if (row.status == '3') {//状态为不通过
        			if (role == '1') {
        				return "<a class='btn btn-success btn-xs' onclick=viewShjl("+row.id+")>查看审核记录</a>";
        			} else if (uploadrole == '1') {
        				return "<a class='btn btn-warning btn-xs' onclick=uploadfj("+row.id+")>上传附件</a>"+ 
        					   "<a class='btn btn-success btn-xs' onclick=viewShjl("+row.id+") style='margin-left: 5px;'>查看审核记录</a>";
        			} 
        		} 
        	} 
        }
    ]],
    onLoadSuccess: function(){
    	if(type == '1'){
			$(this).datagrid("hideColumn", [ 'qyname']);
			$(this).datagrid("hideColumn", [ 'deptname']);
		}else{
			$(this).datagrid("showColumn", [ 'qyname' ]);
			$(this).datagrid("showColumn", [ 'deptname' ]);
			$(this).datagrid("autoMergeCells", [ 'qyname']);
			$(this).datagrid("autoMergeCells", [ 'deptname']);
		}
    },
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
        view();
    },
	checkOnSelect:false,
	selectOnCheck:false,
    toolbar:'#sbssgl_sbbf_tb'
	});
});


//弹窗增加设备报废信息
function add() {
	openDialog("添加设备报废信息",ctx+"/sbssgl/sbbf/create?sbtype="+sbtype,"800px", "430px","");
}

//查看
function view(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	openDialogView("查看设备报废信息",ctx+"/sbssgl/sbbf/view/"+row.id,"800px", "430px","");
}

//弹窗修改
function upd(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	if(row.status != '0'){
		layer.msg("附件上传后不能进行修改操作！",{time: 3000});
	} else {
		openDialog("修改设备报废信息",ctx+"/sbssgl/sbbf/update/"+row.id+"?sbtype="+sbtype,"800px", "430px","");
	}
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
			url:ctx+"/sbssgl/sbbf/delete/"+ids,
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

//导出word
function fileexport(){
	var row = dg.datagrid('getSelected');
	if(row==null){
		layer.msg('请选择一行记录',{time: 1000});
		return;
	}
	
	$.ajax({
		url:ctx+"/sbssgl/sbbf/export/"+row.id,
		type:"POST",
		success:function(data){
			window.open(ctx+data);
		}
	});
}

//上传附件
function uploadfj(id){
	openDialog("上传附件",ctx+"/sbssgl/sbbf/uploadindex/"+id,"400px", "250px","");
}

//选择审核结果操作
function changeZt(id, sbid){
	openDialog("选择审核结果",ctx+"/sbssgl/sbbf/shjg/"+id+"?sbid="+ sbid,"500px", "280px","");
}

//查看审核记录
function viewShjl(id){
	openDialogView("查看审核记录",ctx+"/sbssgl/sbbf/viewshjl/"+id,"600px", "300px","");
}
