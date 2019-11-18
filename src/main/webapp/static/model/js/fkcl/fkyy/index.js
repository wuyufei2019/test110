var dg;
var d;
$(function(){
	dg=$('#fkcl_fkyy_dg').datagrid({    
	method: "post",
    url:ctx+'/fkcl/fkyy/list', 
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
              {field:'m1',title:'预约时间',sortable:false,width:60,align:'center',
             	  formatter : function(value, row, index) {
		              if(value!='') {
			              var datetime=new Date(value);
			              return datetime.format('yyyy-MM-dd hh:mm:ss');  
		              }
	              }	 
              },    
              {field:'m2',title:'被预约人',sortable:false,width:60,align:'center'},
              {field:'m4',title:'预约人',sortable:false,width:50,align:'center'},
              {field:'m6',title:'预约确认人员',sortable:false,width:40,align:'center'},
              {field:'m7',title:'预约确认时间',sortable:false,width:60,align:'center',
             	  formatter : function(value, row, index) {
		              if(value!=''&&value!=null) {
			              var datetime=new Date(value);
			              return datetime.format('yyyy-MM-dd hh:mm:ss');  
		              }
	              }	 
              }, 
              {field:'m8',title:'进厂确认人员',sortable:false,width:40,align:'center'},
              {field:'m9',title:'进厂时间',sortable:false,width:60,align:'center',
             	  formatter : function(value, row, index) {
		              if(value!=''&&value!=null) {
			              var datetime=new Date(value);
			              return datetime.format('yyyy-MM-dd hh:mm:ss');  
		              }
	              }	 
              }, 
              {field:'m10',title:'进厂人数',sortable:false,width:30,align:'center'},
              {field:'m11',title:'出厂确认人员',sortable:false,width:40,align:'center'},
              {field:'m12',title:'出厂时间',sortable:false,width:60,align:'center',
             	  formatter : function(value, row, index) {
		              if(value!=''&&value!=null) {
			              var datetime=new Date(value);
			              return datetime.format('yyyy-MM-dd hh:mm:ss');  
		              }
	              }	 
              }, 
              {field:'m13',title:'出厂人数',sortable:false,width:30,align:'center'},
              {field:'status',title:'预约状态',sortable:false,width:70,align:'center',
             	  formatter : function(value, row, index) {
             		  var zt = '';
             		  if(value=='1'){
             			  zt='预约确认中';
             		  }else if(value=='2'){
             			  zt='拒绝预约';
             		  }else if(value=='3'){
             			  zt='预约通过待进厂';
             		  }else if(value=='4'){
             			  zt='进厂待出厂';
             		  }else if(value=='5'){
             			  zt='已出厂';
             		  }
		              return zt;
	              }	 
              },
              {field:'cz',title:'操作',sortable:false,width:70,align:'center',
             	  formatter : function(value, row, index) {
             		  var cz = '';
             		  if(row.status=='1'&&yyqr=='2'){
             			  //预约确认
	        			  cz += "<a style='margin:2px' class='btn btn-success btn-xs' onclick='yyjg("+row.id+",1)'>同意</a>";
	        			  cz += "<a style='margin:2px' class='btn btn-danger btn-xs' onclick='yyjg("+row.id+",2)'>拒绝</a>";
	        		  }else if(row.status=='3'&&jccqr=='2'){
	        			  //进厂
	        			  cz += "<a style='margin:2px' class='btn btn-warning btn-xs' onclick='jc("+row.id+")'>进厂</a>";
	        		  }else if(row.status=='4'&&jccqr=='2'){
	        			  //出厂
	        			  cz += "<a style='margin:2px' class='btn btn-warning btn-xs' onclick='cc("+row.id+")'>出厂</a>";
	        		  }
		              return cz;
	              }	 
              }
    ]],
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
    	view();
    },
	checkOnSelect:false,
	selectOnCheck:false,
    toolbar:'#fkcl_fkyy_tb'
	});
	
});

//预约同意
function yyjg(id,type){
	var ts = '';
	var yyqrurl = '';
	if(type == 1){//同意
		ts = '您确定同意该预约请求？';
		yyqrurl = '/fkcl/fkyy/yyqr/'+id+'/3';
	}else{//拒绝
		ts = '您确定拒绝该预约请求？';
		yyqrurl = '/fkcl/fkyy/yyqr/'+id+'/2';
	}
	top.layer.confirm(ts, {icon: 3, title:'提示'}, function(index){
		$.ajax({
			type:'get',
			url:ctx+yyqrurl,
			success: function(data){
				layer.alert(data, {offset: 'rb',shade: 0,time: 2000}); 
				top.layer.close(index);
				dg.datagrid('reload');
			}
		});
	});
}

//进厂
function jc(id) {
	openDialog("添加进厂信息",ctx+"/fkcl/fkyy/jc/"+id,"400px", "150px","");
}

//出厂
function cc(id,jcrynum) {
	openDialog("添加出厂信息",ctx+"/fkcl/fkyy/cc/"+id,"400px", "150px","");
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
			url:ctx+"/fkcl/fkyy/delete/"+ids,
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

//弹窗增加
function add(u) {
	openDialog("添加访客预约信息",ctx+"/fkcl/fkyy/create","800px", "400px","");
}

//弹窗修改
function upd(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	
	openDialog("修改访客预约信息",ctx+"/fkcl/fkyy/update/"+row.id,"800px", "400px","");
	
}

//查看
function view(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	
	openDialogView("查看访客预约信息",ctx+"/fkcl/fkyy/view/"+row.id,"800px", "400px","");
	
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

//导出
function fileexport(){
	window.expara=$("#searchFrom").serializeObject();
	window.exdata=[
			   		{colval:'m1', coltext:'预约时间'},
			   		{colval:'m2', coltext:'被预约人'},
			   		{colval:'m3', coltext:'事由'},
			   		{colval:'m4', coltext:'预约人'},
			   		{colval:'m5', coltext:'手机号码'},
			   		{colval:'m6', coltext:'预约确认人员'},
			   		{colval:'m7', coltext:'预约确认时间'},
			   		{colval:'m8', coltext:'进厂确认人员'},
			   		{colval:'m9', coltext:'进厂时间'},
			   		{colval:'m10', coltext:'进厂人数'},
			   		{colval:'m11', coltext:'出厂确认人员'},
			   		{colval:'m12', coltext:'出厂时间'},
			   		{colval:'m13', coltext:'出厂人数'},
			   		{colval:'zt', coltext:'预约状态'}
		   	];

	layer.open({
	    type: 2,  
	    area: ['350px', '350px'],
	    title: '导出列选择',
        maxmin: false, 
        shift: 1,
	    content: ctx+'/fkcl/fkyy/colindex',
		btn: ['导出'],
	    yes: function(index, layero){
	    	 var body = layer.getChildFrame('body', index);
	         var iframeWin = layero.find('iframe')[0];  
	         var inputForm = body.find('#excel_mainform');
	         iframeWin.contentWindow.doExport();
		  },
	});
		
}