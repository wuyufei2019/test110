var dg;

$(function(){
	dg=$('#dxj_dxjsb_dg').datagrid({    
	method: "post",
    url:ctx+'/dxj/sb/list', 
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
  			{field:'qyname',title:'企业名称',sortable:false,width:100},
			{field:'m1',title:'设备名称',sortable : false,width :80},
			{field:'bindcontent',title:'绑定二维码',sortable:false,width:60,align:'center'},
	        {field:'rfid',title:'绑定rfid',sortable:false,width:60,align:'center'},
	        {field:'m2',title:'现场照片',sortable:false,width:50,align:'center',
	        	formatter : function(value) {
	        		if(value){
	        			var urls=value.split(",");
	        			var html="";
	        			for(var index in urls){
	        				html+="<a class='fa fa-picture-o btn-danger btn-outline' target='_blank' style='margin:1px auto' onclick='openImgView(\""+urls[index].split("||")[0]+"\")'> 照片"+(parseInt(index)+1)+"</a><br>"; 
	        			}
	        			return html;
	        		}
	        		else return ; 
	        	}
	        },
	        {field:'caozuo',title:'操作',sortable:false,width:50,align:'center',
	        	formatter : function(value, row, index) {
	          	 return "<a class='btn btn-success btn-xs' onclick='openerm("+row.id+")'>生成二维码</a>"
	        }}		
    ]],
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
        view();
    },
    onLoadSuccess: function(){
    	if(type == '1'){
			$(this).datagrid("hideColumn", [ 'qyname' ]);
		}else{
			$(this).datagrid("showColumn", [ 'qyname' ]);
			$(this).datagrid("autoMergeCells", [ 'qyname' ]);
		}
    },
    onLoadError:function(){
    	layer.open({title: '提示',offset: 'rb',content: '数据加载中，请耐心等待。',shade: 0 ,time: 2000 });
    },
	checkOnSelect:false,
	selectOnCheck:false,
    toolbar:'#dxj_dxjsb_tb'
	});
	
});

//添加
function add(u) {
	openDialog("设备管理",ctx+"/dxj/sb/create/","800px", "400px","");
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

	top.layer.confirm('该设备所设的项目也会删除，您确定要删除？', {icon: 3, title:'提示'}, function(index){
		$.ajax({
			type:'get',
			url:ctx+"/dxj/sb/delete/"+ids,
			success: function(data){
				parent.layer.alert(data, {icon:6,title: '提示',offset: 'rb',shade: 0,time: 2000}); 
				top.layer.close(index);
				dg.datagrid('reload');
				dg.datagrid('clearChecked');
				dg.datagrid('clearSelections');
			}
		});
	});
}

//创建查询对象并查询
function search() {
	var obj = $("#searchFrom").serializeObject();
	dg.datagrid('load', obj);
}
 
//重置
function reset() {
	$("#searchFrom").form("clear");
	var obj = $("#searchFrom").serializeObject();
	dg.datagrid('load', obj);
}

//弹窗修改
function upd(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	openDialog("设备记录管理",ctx+"/dxj/sb/update/"+row.id,"800px", "400px","");
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
	openDialogView("查看设备信息",ctx + "/dxj/sb/view/" + row.id,"800px", "400px","");
}

//生成二维码图片
function openerm(id){
	$.ajax({
		type : 'get',
		url : ctx + "/dxj/sb/erm?id=" + id,
		success : function(data) {
			window.open(ctx+data);
		}
	});
}