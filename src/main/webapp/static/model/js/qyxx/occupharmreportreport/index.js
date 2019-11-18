var dg;
var d;
var gqCnt = 0;
$(function(){
	dg=$('#bis_occupharmreport_dg').datagrid({    
	method: "post",
	url:ctx+'/bis/occupharmreport/list', 
	queryParams : (parent.P_HeaderType=="expiration"?{bis_zybjcbg_cx_time : 0 }:null),
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
        {field:'m10',title:'职业病危害因素类别',sortable:true,width:100},    
        {field:'m11',title:'职业病危害因素名称',sortable:false,width:100},    
        {field:'m1',title:'检测机构',sortable:false,width:100},    
        {field:'m2',title:'检测日期',sortable:true,width:60,align:'center',
        	formatter:function(value,row,index){
        		if(value!=null){
                var datetime = new Date(value);  
                 return datetime.format('yyyy-MM-dd');    }  
             } },
        {field:'m3',title:'检测结果编号',sortable:false,width:100,align:'center'},
        {field:'m14',title:'采样地点',sortable:false,width:100,align:'center'},
        {field:'m4',title:'下次检测日期',sortable:true,width:60,align:'center',
        	formatter: function(value,row,index){
				if(value!=null){
					var datetime = new Date(value);  
					 return datetime.format('yyyy-MM-dd');    
				}  
			},
	    	styler: function(value, row, index){
	    		var nowhm=(new Date()).getTime();
	    		var m5time=value;
	    		if(m5time){//时间存在的情况下
	    			if(nowhm<=m5time){//未过期（3个月以内）
		        		var cha=(m5time-nowhm)/1000/60/60/24;
		        		if(cha<=90) return 'background-color:rgb(255,240,245);';
		    		}else{
	          			gqCnt = gqCnt + 1;
		    			return 'background-color:rgb(249, 156, 140);';
		    		}
	    		}
	    	}},
        {field:'m5',title:'附件下载',sortable:false,width:60,align:'center',
        	formatter:function(val,row,index){
        		if(val!=null&&val!=""){
        			var t="||";
        			val=val.split(t);
        			return '<a onclick="window.open(\''+val[0]+'\')" href="javascript:void(0)">附件下载</a>';  
        		}else{
        			return '--';
        		}
	        	
        	}  
        }
        
    ]],
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
                 view();
    },
    onLoadSuccess:function (){
    	if(gqCnt > 0){
    		layer.open({icon:1,title: '提示',offset: 'rb',content:"有" + gqCnt + "个检测计划已到期",shade: 0 ,time: 3000 });
    	}
    	gqCnt=0;
    },
    onBeforeLoad:function(param){
    	if(f!=''&&f=='sys'){
    		$("#bis_zybjcbg_cx_time").combobox('setValue','0');
    		param.bis_zybjcbg_cx_time=0;
    	 }
    },
	checkOnSelect:false,
	selectOnCheck:false,
    toolbar:'#bis_occupharmreport_tb'
	});
	
});

//弹窗增加
function add(u) {
	openDialog("添加检测报告信息",ctx+"/bis/occupharmreport/create/","100%", "100%","");
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
			url:ctx+"/bis/occupharmreport/delete/"+ids,
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
	
	openDialog("修改检测报告信息",ctx+"/bis/occupharmreport/update/"+row.id,"100%", "100%","");
	
}

//查看
function view(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	
	openDialogView("查看检测报告信息",ctx+"/bis/occupharmreport/view/"+row.ID,"100%", "100%","");
	
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
	                {colval:'m10', coltext:'职业病危害因素类别'},
	                {colval:'m11', coltext:'职业病危害因素名称'},
			   		{colval:'m1', coltext:'检测机构'},
			   		{colval:'m2', coltext:'检测日期'},
			   		{colval:'m3', coltext:'检测报告编号'},
			   		{colval:'m14', coltext:'采样地点'},
			   		{colval:'m4', coltext:'下次检测日期'},
			   		{colval:'m6', coltext:'检测结果'}
		   	];
	layer.open({
	    type: 2,  
	    area: ['350px', '350px'],
	    title: '导出列选择',
        maxmin: false, 
        shift: 1,
	    content: ctx+'/bis/occupharmreport/colindex',
		btn: ['导出'],
	    yes: function(index, layero){
	    	 var body = layer.getChildFrame('body', index);
	         var iframeWin = layero.find('iframe')[0];  
	         var inputForm = body.find('#excel_mainform');
	         iframeWin.contentWindow.doExport();
		  },
	});
		
}