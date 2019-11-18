var dg;
var d;
var gqCnt = 0;
$(function(){
	var gqCnt = 0;
	dg=$('#bis_aqss_dg').datagrid({    
	method: "post",
	url:ctx+'/bis/aqss/list', 
	queryParams : (parent.P_HeaderType=="expiration"?{bis_aqss_dqsj2 : 0 }:null),
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
        {field:'m1',title:'大类',sortable:true,width:100,align:'center'},    
        {field:'m2',title:'小类',sortable:true,width:100,align:'center'},
        {field:'m9',title:'安全设施名称',sortable:false,width:100,align:'center'},
        {field:'m3',title:'涉及工艺设施',sortable:false,width:100,align:'center' },
        {field:'m4',title:'数量',sortable:true,width:50,align:'center' },
        {field:'m5',title:'检测时间',sortable:true,width:60,align:'center',
        	formatter : function(value, row, index) {
           	 if(value!=null&&value!=""){
           		var datetime = new Date(value);  
				 return datetime.format('yyyy-MM-dd');    
        	 }
       	}},
       	{field:'m6',title:'到期时间',sortable:true,width:60,align:'center',
          	styler: function(value, row, index){
          		if(value!=null&&value!=""){
          		var nowhm=(new Date()).getTime();
          		if(nowhm<=value){
              		var cha=(value-nowhm)/1000/60/60/24;
              		if(cha<=90) return 'background-color:rgb(255, 228, 141);';
          		}else{
          			gqCnt = gqCnt + 1;
          			return 'background-color:rgb(249, 156, 140);';
          		}
          		}
          	},
       		formatter : function(value, row, index) {
        		if(value!=null){
               		var datetime = new Date(value);  
    				 return datetime.format('yyyy-MM-dd');    
            	 }
        	}
       	},
       	{field:'m7',title:'状态',sortable:true,width:50,align:'center',
       		formatter : function(value, row, index){
       			if(value==null) return value='';
       			else if(value=='1') return value='失效';
        		else if(value=='2') return value='停用';
        		else if(value=='0') return value='正常'; 
        		else return value='';
        	}} 
    ]],
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
                 view();
    },
    onLoadSuccess:function (){
    	if(gqCnt > 0){
    		layer.open({icon:1,title: '提示',offset: 'rb',content:"有" + gqCnt + "个安全设施已到期",shade: 0 ,time: 3000 });
    	}
    	gqCnt=0;
    },
	checkOnSelect:false,
	selectOnCheck:false,
    toolbar:'#bis_aqss_tb'
	});
	
});

//弹窗增加
function add(u) {
	openDialog("添加安全设施信息",ctx+"/bis/aqss/create/","800px", "400px","");
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
			url:ctx+"/bis/aqss/delete/"+ids,
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

//弹窗修改
function upd(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	
	openDialog("修改安全设施信息",ctx+"/bis/aqss/update/"+row.id,"800px", "400px","");
	
}

//查看
function view(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	
	openDialogView("查看安全设施信息",ctx+"/bis/aqss/view/"+row.ID,"800px", "400px","");
	
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
			   		{colval:'m1', coltext:'大类'},
			   		{colval:'m2', coltext:'小类'},
			   		{colval:'m9', coltext:'安全设施名称'},
			   		{colval:'m3', coltext:'涉及工艺设施'},
			   		{colval:'m4', coltext:'数量'},
			   		{colval:'m5', coltext:'检测时间'},
			   		{colval:'m6', coltext:'到期时间'},
			   		{colval:'m7', coltext:'状态'},
			   		{colval:'m8', coltext:'备注'},
		   	];
	layer.open({
	    type: 2,  
	    area: ['350px', '350px'],
	    title: '导出列选择',
        maxmin: false, 
        shift: 1,
	    content: ctx+'/bis/aqss/colindex',
		btn: ['导出'],
	    yes: function(index, layero){
	    	 var body = layer.getChildFrame('body', index);
	         var iframeWin = layero.find('iframe')[0];  
	         var inputForm = body.find('#excel_mainform');
	         iframeWin.contentWindow.doExport();
		  },
	});
		
}