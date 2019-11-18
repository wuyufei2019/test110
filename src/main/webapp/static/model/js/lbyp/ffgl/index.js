var dg;
var jobtype,eid,ename,goodsname,amount;
$(function(){
	dg=$('#lbyp_ffgl_dg').datagrid({    
	method: "post",
    url:ctx+'/lbyp/ffgl/list?qyid='+qyid,
    fit : true,
	fitColumns : true,
	border : false,
	striped:true,
	pagination:true,
	rownumbers:true,
	nowrap:false,
	pageNumber:1,
	pageSize : 50,
	pageList : [ 50, 100, 150, 200, 250 ],
	scrollbarSize:0,
	singleSelect:true,
	striped:true,
    columns:[[    
        {field:'jobtype',title:'岗位/工种名称',sortable:false,width:50},
        {field:'ename',title:'员工姓名',sortable:false,width:50},    
        {field:'goodsname',title:'用品名称',sortable:false,width:40},
        {field:'amount',title:'发放数量',sortable:false,width:40},
        {field:'lasttime',title:'最近领取日期',sortable:false,width:40, formatter : function (value, row){
        	if(value) return new Date(value).format("yyyy-MM-dd");
        	else return '';
        }},    
        {field:'nexttime',title:'下次领取日期',sortable:false,width:40, formatter : function (value, row){
        	var d = getNextDate(row);
        	if(d) return d;
        },
        styler: function(value, row, index){
        	var d = getNextDate(row);
    		var now=new Date().format("yyyy-MM-dd");
			if(now>=d){
    			return 'background-color:rgb(249, 156, 140);';
    		}
    	}},    
        {field:'caozuo',title:'操作', sortable:false, width:30, formatter : function (value, row){
        	var d = getNextDate(row);
    		var now=new Date().format("yyyy-MM-dd");
    		var html="";
    		html+= "<a class='btn btn-success btn-xs' onclick='addrecord("+JSON.stringify(row)+")'>发放用品</a>";
    		html+= "<a class='btn btn-info btn-xs' onclick='exportbd("+JSON.stringify(row)+")'>导出发放表单</a>";
			return html;
        }}   
    ]],
    onDblClickRow: function (index, row){
    	addrecord(row);
    },
    onLoadSuccess: function(){
    	if(expirationcount>0)
    		layer.open({icon:7,title: '提示',offset: 'rb',content:"有" + expirationcount + "个劳保用品待发放",shade: 0 ,time: 3000 });
        $(this).datagrid("autoMergeCells",['jobtype','ename']);
    },
	checkOnSelect:false,
	selectOnCheck:false,
    toolbar:'#lbyp_ffgl_tb'
	});
	
});

function getNextDate(row){
	if(row.lasttime) {
		var d= new Date(row.lasttime);
		if(row.cyclemonth)
			d.setMonth(d.getMonth()+parseInt(row.cyclemonth));
		return d.format("yyyy-MM-dd");
	}
	else return null;
}

//弹窗增加
function addrecord(row) {
	goodsname=row.goodsname;
	jobtype=row.jobtype;
	eid=row.eid;
	ename=row.ename;
	amount=row.amount;
	layer.open({
	    type: 2,  
	    shift: 1,
	    skin: 'self-class',
	    area: ["800px", "400px"],
	    title: '添加发放记录',
        maxmin: false, 
	    content: ctx+"/lbyp/ffjl/create/" ,
	    btn: ['确定','关闭','导出发放表单'],
	    yes: function(index, layero){
             var iframeWin = layero.find('iframe')[0];
             iframeWin.contentWindow.doSubmit();
		  },
	     btn2: function(index, layero){
	     },
	     btn3: function(index, layero){
	    	 var iframeWin = layero.find('iframe')[0];
             iframeWin.contentWindow.exportbd();
		 },
	}); 
}

function exportbd(row) {
	$.post(ctx+"/lbyp/ffjl/exportbd",{"ID1": row.eid,"goodsname":row.goodsname},function(data){window.open(ctx+'/download/' + data);});
}

function addAll(row) {
	layer.open({
	    type: 2,  
	    shift: 1,
	    skin: 'self-class',
	    area: ["900px", "450px"],
	    title: '批量处理待领取用品',
        maxmin: false, 
	    content: ctx+"/lbyp/ffgl/createall/" ,
	    btn: ['确定','关闭','导出发放表单'],
	    yes: function(index, layero){
             var iframeWin = layero.find('iframe')[0];
             iframeWin.contentWindow.doSubmit();
		  },
	     btn2: function(index, layero){
	     },
	     btn3: function(index, layero){
	    	 var iframeWin = layero.find('iframe')[0];
             iframeWin.contentWindow.exportbd();
		 },
	}); 
}
function updgoods(id) {
	openDialog("修改用品信息",ctx+"/lbyp/wpxx/update/"+id,"800px", "300px","");
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
}

