var dg;
var d;
$(function(){
	dg=$('#sbssgl_sbys_dg').datagrid({    
	method: "post",
    url:ctx+'/sbssgl/sbys/list', 
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
        {field:'qyname',title:'所属企业',sortable:false,width:60,align : 'center'},
        {field:'m1',title:'设备类型',sortable:false,width:50,align:'center'},
        {field:'m2',title:'设备编号',sortable:false,width:40,align:'center'},
        {field:'m4',title:'设备名称',sortable:false,width:50,align:'center'},
        {field:'m3',title:'验收日期',sortable:false,width:40,align:'center',
      	    formatter : function(value, row, index) {
            	if(value!==null&&value!='') {
            		var datetime=new Date(value);
            		 return datetime.format('yyyy-MM-dd');  
            	}
        	} 
        },
        {field:'m5',title:'规格/型号',sortable:false,width:40,align:'center'},
        {field:'m9',title:'放置地点',sortable:false,width:60,align:'center'},
        {field:'m30',title:'检验条件',sortable:false,width:30,align:'center',
      	    formatter : function(value, row, index) {
            	if(value=='1') {
            		return '不需校验';  
            	}else if(value=='2'){
            		return '需校验';  
            	}
        	} 
        },
        {field:'m34',title:'状态',sortable:false,width:40,align:'center',
        	/*formatter : function(value, row, index) {
        		if(value == '1'){
        			return "待审核";
        		}else if(value == '2'){
        			return "通过";
        		}else if(value == '3'){
        			return "驳回";
        		}else{
        			return "待上传附件";
        		}
        	} */
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
        	/*formatter : function(value, row, index) {
        		if(row.m34 == '0' && uploadrole == '1'){
        			return "<a style='margin:2px' class='btn btn-success btn-xs' onclick=uploadfj("+row.id+")>上传附件</a>";
        		}else if(row.m34 == '1' && role == '1'){
        			return "<a style='margin:2px' class='btn btn-success btn-xs' onclick=changeZt("+row.id+",'2')>通过</a>" +
        					"<a style='margin:2px' class='btn btn-danger btn-xs' onclick=changeZt("+row.id+",'3')>驳回</a>";
        		}else if(row.m34 == '2' && sbjfrole == '1' && row.sbjfid == null){
        			return "<a style='margin:2px' class='btn btn-primary btn-xs' onclick=addsbjf("+row.id+")>添加设备启用单</a>";
        		}else{
        			return '';
        		}
        	} */
        	formatter : function(value, row, index) {	
        		if (row.m34 == '0' && uploadrole == '1') {//状态为待上传附件
        			
        			return "<a class='btn btn-warning btn-xs' onclick=uploadfj("+row.id+")>上传附件</a>";
        			
        		} else if (row.m34 == '1' && role == '1') {//状态为待审核
        			
        			var m35 = row.m35;
        			var fileurl = m35.split("||");
        			
        			return "<a class='btn btn-info btn-xs' onclick=changeZt("+row.id+",'1')>审核</a>"+
        				   "<a class='btn btn-warning btn-xs' onclick='window.open(\""+fileurl[0]+"\")' style='margin-left: 5px;'> 查看附件</a>";
        			
        		} else if (row.m34 == '2' && role == '1') {//主管部门当状态为通过时，显示添加 设备启用单 和 审核记录
        			if (row.sbjfid == null) {
        				return "<a style='margin:2px' class='btn btn-primary btn-xs' onclick=addsbjf("+row.id+")>添加设备启用单</a>"+
     			               "<a class='btn btn-success btn-xs' onclick=viewShjl("+row.id+")>查看审核记录</a>";
        			} else {
        				return "<a class='btn btn-success btn-xs' onclick=viewShjl("+row.id+")>查看审核记录</a>";
        			}
        		} else if (row.m34 == '2' && uploadrole == '1') {//申请部门当状态为通过时，只显示审核记录
        			
        			return "<a class='btn btn-success btn-xs' onclick=viewShjl("+row.id+") style='margin-left: 5px;'>查看审核记录</a>";
        			
        		} else if (row.m34 == '3') {//状态为不通过
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
			$(this).datagrid("hideColumn", [ 'qyname' ]);
			$(this).datagrid("autoMergeCells", [ 'm1' ]);
		}else{
			$(this).datagrid("showColumn", [ 'qyname' ]);
			$(this).datagrid("autoMergeCells", [ 'qyname' ]);
			$(this).datagrid("autoMergeCells", [ 'm1' ]);
		}
    },
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
        view();
    },
	checkOnSelect:false,
	selectOnCheck:false,
    toolbar:'#sbssgl_sbys_tb'
	});
});

//弹窗增加设备启用单信息
function addsbjf(id) {
	openDialog("添加设备启用单信息",ctx+"/sbssgl/sbjf/create/"+id,"1000px", "450px","");
}

//上传附件
function uploadfj(id){
	openDialog("上传附件",ctx+"/sbssgl/sbys/uploadindex/"+id,"400px", "250px","");
}

//通过或驳回操作
/*function changeZt(id,type){
	var title = "";
	if(type == '2'){//通过
		title = "你是否通过此次验收？"
	}else if(type == '3'){//驳回
		title = "你是否驳回此次验收？"
	}
	top.layer.confirm(title, {icon: 3, title:'提示'}, function(index){
		$.ajax({
			type:'get',
			url:ctx+"/sbssgl/sbys/changezt/"+id+"/"+type,
			success: function(data){
				layer.alert(data, {offset: 'rb',shade: 0,time: 2000}); 
				top.layer.close(index);
				dg.datagrid('reload');
			}
		});
	});
}*/
//选择审核结果操作
function changeZt(id){
	openDialog("选择审核结果",ctx+"/sbssgl/sbys/shjg/"+id,"500px", "280px","");
}

//查看审核记录
function viewShjl(id){
	openDialogView("查看审核记录",ctx+"/sbssgl/sbys/viewshjl/"+id,"600px", "300px","");
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
			url:ctx+"/sbssgl/sbys/delete/"+ids,
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
	openDialogView("查看设备验收单信息",ctx+"/sbssgl/sbys/view/"+row.id,"1000px", "100%","");
}

//弹窗增加
function add() {
	openDialog("添加设备验收单信息",ctx+"/sbssgl/sbys/create/","1000px", "100%","");
}

//弹窗修改
function upd(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	if(row.m34 != '0'){
		layer.msg("附件上传后不能进行修改操作！",{time: 3000});
	} else {
		openDialog("修改设备验收单信息",ctx+"/sbssgl/sbys/update/"+row.id,"1000px", "100%","");
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
		url:ctx+"/sbssgl/sbys/export/"+row.id,
		type:"POST",
		success:function(data){
			window.open(ctx+data);
		}
	});
}