var dg;
var d;
$(function(){
	dg=$('#sbssgl_sbsq_dg').datagrid({    
	method: "post",
    url:ctx+'/sbssgl/sbsq/list', 
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
        {field:'sqbmname',title:'请购部门',sortable:false,width:50,align:'center'},
        {field:'sqrname',title:'请购人',sortable:false,width:30,align:'center'},
        {field:'m3',title:'请购日期',sortable:false,width:40,align:'center',
      	    formatter : function (value, row, index) {
            	if (value!==null&&value!='') {
            		var datetime=new Date(value);
            		 return datetime.format('yyyy-MM-dd');  
            	}
        	} 
        },
        {field:'m4',title:'请购资产明细',sortable:false,width:100,align:'center'},
        {field:'m5',title:'合计（RMB）',sortable:false,width:30,align:'center'},
        {field:'m6',title:'供应商',sortable:false,width:60,align:'center'},
        {field:'m10',title:'状态',sortable:false,width:40,align:'center',
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
        {field:'m12',title:'操作',sortable:false,width:70,align:'center',
        	formatter : function(value, row, index) {
        		console.log(role+";");
        		if (row.m10 == '0' && role == '0') {//状态为待上传附件
        			
        			return "<a class='btn btn-success btn-xs' onclick=upload("+row.id+")>上传附件</a>";
        			
        		} else if (row.m10 == '1' && role == '1') {//状态为待审核
        			
        			var m11 = row.m11;
        			var fileurl = m11.split("||");
        			
        			return "<a class='btn btn-info btn-xs' onclick=changeZt("+row.id+",'1')>审核</a>"+
        				   "<a class='btn btn-warning btn-xs' onclick='window.open(\""+fileurl[0]+"\")' style='margin-left: 5px;'> 查看附件</a>";
        			
        		} else if (row.m10 == '2' && role == '1') {//主管部门当状态为通过时，只显示审核记录
        			
        			return "<a class='btn btn-success btn-xs' onclick=viewShjl("+row.id+")>查看审核记录</a>";
        			
        		} else if (row.m10 == '2' && role == '0' && value == '0') {//申请部门当状态为通过时，显示验收和审核记录
        			
        			return "<a class='btn btn-info btn-xs'    onclick=alertQgsb("+row.id+")>验收</a>"+
 				           "<a class='btn btn-success btn-xs' onclick=viewShjl("+row.id+") style='margin-left: 5px;'>查看审核记录</a>";
        			
        		} else if (row.m10 == '3' && value == '0') {//状态为不通过
        			if (role == '1') {
        				return "<a class='btn btn-success btn-xs' onclick=viewShjl("+row.id+")>查看审核记录</a>";
        			} else if (role == '0') {
        				return "<a class='btn btn-warning btn-xs' onclick=upload("+row.id+")>上传附件</a>"+ 
        					   "<a class='btn btn-success btn-xs' onclick=viewShjl("+row.id+") style='margin-left: 5px;'>查看审核记录</a>";
        			} 
        		} else if (role == '0' && value == '1') {
        			return "已全部验收"+
			               "<a class='btn btn-success btn-xs' onclick=viewShjl("+row.id+") style='margin-left: 5px;'>查看审核记录</a>";
        			
        		} 
        	} 
        }
    ]],
    onLoadSuccess: function(){
    	if(type == '1'){
			$(this).datagrid("hideColumn", [ 'qyname' ]);
			$(this).datagrid("autoMergeCells", [ 'sqbmname' ]);
			$(this).datagrid("autoMergeCells", [ 'sqrname' ]);
			$(this).datagrid("autoMergeCells", [ 'm3' ]);
		}else{
			$(this).datagrid("showColumn", [ 'qyname' ]);
			$(this).datagrid("autoMergeCells", [ 'qyname' ]);
			$(this).datagrid("autoMergeCells", [ 'sqbmname' ]);
			$(this).datagrid("autoMergeCells", [ 'sqrname' ]);
			$(this).datagrid("autoMergeCells", [ 'm3' ]);
		}
    },
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
        view();
    },
	checkOnSelect:false,
	selectOnCheck:false,
    toolbar:'#sbssgl_sbsq_tb'
	});
});

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
			url:ctx+"/sbssgl/sbsq/delete/"+ids,
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
	openDialogView("查看固定资产请购单信息",ctx+"/sbssgl/sbsq/view/"+row.id,"1000px", "100%","");
}

//弹窗增加
function add() {
	openDialog("添加固定资产请购单信息",ctx+"/sbssgl/sbsq/create/","1000px", "100%","");
}

//弹窗修改
function upd(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	if(row.m10 == '1' || row.m10 == '2' || row.m10 == '3'){
		layer.msg("附件上传后不能进行修改操作！",{time: 3000});
	} else {
		openDialog("修改固定资产请购单信息",ctx+"/sbssgl/sbsq/update/"+row.id,"1000px", "100%","");
	}
}

//导出word
function fileexport(){
	var row = dg.datagrid('getSelected');
	if(row==null){
		layer.msg('请选择一行记录',{time: 1000});
		return;
	}
	
	$.ajax({
		url:ctx+"/sbssgl/sbsq/export/"+row.id,
		type:"POST",
		success:function(data){
			window.open(ctx+data);
		}
	});
}

//上传附件
function upload(id){
	openDialog("上传附件",ctx+"/sbssgl/sbsq/uploadfile/"+id,"400px", "250px","");
}

//选择审核结果操作
function changeZt(id){
	openDialog("选择审核结果",ctx+"/sbssgl/sbsq/shjg/"+id,"500px", "280px","");
}

//查看审核记录
function viewShjl(id){
	openDialogView("查看审核记录",ctx+"/sbssgl/sbsq/viewshjl/"+id,"600px", "300px","");
}

//请购资产页面跳转
function alertQgsb(sbsqid){
	openDialogView("请购设备信息",ctx+"/sbssgl/sbsq/qgsb/"+sbsqid,"1000px", "100%","");
}