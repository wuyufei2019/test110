var dg;
var d;
$(function(){
	dg=$('#issue_aqwj_dg').datagrid({    
	method: "post",
    url:ctx+'/issue/zfwjfb/list2', 
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
        {field:'ID',title:'id',checkbox:true,width:50,align:'center'},    
        {field:'M1',title:'文件名称',sortable:false,width:100 },    
        {field:'M5',title:'文件类型',sortable:false,width:50,align:'center'},  
        
        {field:'look',title:'查阅情况',sortable:false,width:50,align:'center',
        	formatter : function(value, row, index) {
        	 if(value==1){
        		 return '已查看';
        	 }
           	 return '未查看';
        	}
        },
        {field:'M2',title:'下载情况',sortable:false,width:50,align:'center',
        	formatter : function(value, row, index) {
        	 if(value==1){
        		 return '已下载';
        	 }
           	 return '未下载';
        	}
        },
        {field:'S2',title:'更新时间',sortable:false,width:60,align:'center',formatter : function(value){
        	if(value!=null&&value!=''){
        		var date = new Date(value);
	        	return date.format("yyyy-MM-dd hh:mm:ss"); 
        	}else{
        		return '';
        	}
        }},
        {field:'M4',title:'备注',sortable:false,width:100 },
        {field:'hz',title:'操作',align:'center',sortable:false,width:50,formatter : function(rowdata, rowindex, rowDomElement){
        	var html;
        	if(rowdata!=null&&rowdata=='1')
        		html="<a  class='btn btn-success btn-xs' onclick='updateHz("+ rowindex.ID + ")'>修改回执</a>";
        	else
        		html="<a  class='btn btn-info btn-xs' onclick='addHz("+ rowindex.ID + ")'>添加回执</a>";
        	return html;
        } }
     
    ]],
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
                 view();
    },
    onLoadSuccess: function(){
        $(this).datagrid("autoMergeCells",['wj']);
        $.ajax({
        	   url: ctx+"/issue/zfwjfb/searchNoRead2",
               dataType: "text",
               success: function(data){
            	   if(data!=null&&data>0){
            		   layer.open({icon:1,title: '提示',offset: 'rb',content:"您有" + data + "个文件未查看，请及时查阅！",shade: 0 ,time: 3000 });
            	   }
               }
        }
        );
    },
  
	checkOnSelect:false,
	selectOnCheck:false,
    toolbar:'#issue_aqwj_tb'
	});
	
});
//弹窗增加
function addHz(id) {
	openDialog("添加回执信息", ctx + "/issue/wjcdjs/create/"+id, "600px", "300px", "");
}
//弹窗增加
function updateHz(id) {
	openDialog("修改回执信息", ctx + "/issue/wjcdjs/update/"+id, "600px", "300px", "");
}

//查看
function view(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	window.open(ctx+"/issue/zfwjfb/view/"+row.ID,"发布文件查看");
}



//创建查询对象并查询
function search(){
	//优化日期判断
	var obj=$("#searchFrom").serializeObject();
	dg.datagrid('load',obj); 
}

function reset(){
	$("#searchFrom").form("clear");
	var obj=$("#searchFrom").serializeObject();
	dg.datagrid('load',obj); 
	//if(btflg=='2') $("#filter_EQS_departmen").hide();
}

 