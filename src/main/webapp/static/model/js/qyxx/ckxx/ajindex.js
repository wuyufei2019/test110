var dg;
var d;
$(function(){
	dg=$('#bis_ckxx_dg').datagrid({    
	method: "post",
    url:ctx+'/bis/ckxx/ajlist', 
    fit : true,
	fitColumns : true,
	border : false,
	idField : 'ID',
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
        {field:'qyname',title:'企业名称',sortable:false,width:100,align:'left'}, 
        {field:'m1',title:'仓库名称',sortable:false,width:80},    
        {field:'m3',title:'建筑面积(㎡)',sortable:true,width:50,align:'center',
        	formatter:function(value, row, index){
        		if(value!=null&&value!=""){
        			return value.toFixed(2);
        		}
        	}
        },
        {field:'m8',title:'占地面积(㎡)',sortable:true,width:50,align:'center',
        	formatter:function(value, row, index){
        		if(value!=null&&value!=""){
        			return value.toFixed(2);
        		}
        	}	
        },
        {field:'m6',title:'层数',sortable:true,width:50,align:'center',
        	formatter : function(value, row, index) {
        		if(value!==null&&value!==undefined)
        			return value+'层';
        		else
        			return "";
       	}} ,
        {field:'m5',title:'建筑结构',sortable:true,width:50,align:'center',
        	formatter : function(value, row, index){
        		if(value=='0') return value='钢混结构';
        		if(value=='1') return value='砖混结构';
        		if(value=='2') return value='钢结构';
        		if(value=='3') return value='框架结构';
        		if(value=='5') return value='框排架结构';
        		if(value=='4') return value='其他结构';
        	}
        },
        {field:'m4',title:'火灾危险等级',sortable:true,width:50,align:'center', 
        	formatter : function(value, row, index){
        		if(value=='1') return value='甲类';
        		if(value=='2') return value='乙类';
        		if(value=='3') return value='丙类';
        		if(value=='4') return value='丁类';
        		if(value=='5') return value='戊类';
        	}
        },
        {field:'m9',title:'耐火等级',sortable:true,width:50,align:'center', 
        	formatter : function(value, row, index){
        		if(value=='1') return value='一级';
        		if(value=='2') return value='二级';
        		if(value=='3') return value='三级';
        		if(value=='4') return value='四级';
        	}
        },
        {field:'m11',title:'照片',sortable:false,width:50,align:'center',
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
        }
    ]],
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
                 view();
    },
    onLoadSuccess: function(){
        $(this).datagrid("autoMergeCells",['qyname']);
    },
	checkOnSelect:false,
	selectOnCheck:false,
    toolbar:'#bis_ckxx_tb'
	});
	
});

//弹窗增加
function add(u) {
	openDialog("添加仓库信息",ctx+"/bis/ckxx/create/","800px", "400px","");
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
			ids=row[i].ID;
		}else{
			ids=ids+","+row[i].ID;
		}
	}

	top.layer.confirm('删除后无法恢复您确定要删除？', {icon: 3, title:'提示'}, function(index){
		$.ajax({
			type:'get',
			url:ctx+"/bis/ckxx/delete/"+ids,
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
	
	openDialog("修改仓库信息",ctx+"/bis/ckxx/update/"+row.ID,"800px", "400px","");
	
}

//查看
function view(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	
	openDialogView("查看仓库信息",ctx+"/bis/ckxx/view/"+row.ID,"800px", "400px","");
	
}

//创建查询对象并查询
function search(){
	$('#bis_ckxx_cx_qyname').combobox('setValue',$('#bis_ckxx_cx_qyname').combobox('getText'));
	var obj=$("#searchFrom").serializeObject();
	dg.datagrid('load',obj); 
}

function reset(){
	$("#searchFrom").form("clear");
	var obj=$("#searchFrom").serializeObject();
	dg.datagrid('load',obj); 
	//if(btflg=='2') $("#filter_EQS_departmen").hide();
}
//统计
function statistics(){
	top.layer.open({
	    type: 2,  
	    area: ['80%', '80%'],
	    title: '仓库信息统计分析',
        maxmin: true, 
	    content: ctx+"/bis/ckxx/statistics" ,
	    btn: ['关闭'],
	    cancel: function(index){ 
	       }
	});
}

//导出
function fileexport(){
	window.expara=$("#searchFrom").serializeObject();
	window.exdata= [{colval:'qyname', coltext:'企业名称'},
			   		{colval:'m1', coltext:'仓库名称'},
			   		{colval:'m2', coltext:'仓库编号'},
			   		{colval:'m3', coltext:'建筑面积（m^2）'},
			   		{colval:'m4', coltext:'火灾危险等级'},
			   		{colval:'m5', coltext:'建筑结构'},
			   		{colval:'m6', coltext:'层数'},
			   		{colval:'m7', coltext:'备注'},
			   		{colval:'m8', coltext:'占地面积'},
			   		{colval:'m9', coltext:'耐火等级'},
			   		{colval:'m10', coltext:'储存物料'}
		   	];
	layer.open({
	    type: 2,  
	    area: ['350px', '350px'],
	    title: '导出列选择',
        maxmin: true, 
        shift: 1,
	    content: ctx+'/bis/ckxx/colindex',
		btn: ['导出'],
	    yes: function(index, layero){
	    	 var body = layer.getChildFrame('body', index);
	         var iframeWin = layero.find('iframe')[0]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
	         var inputForm = body.find('#excel_mainform');
	         iframeWin.contentWindow.doExport();
		  },
	});
		
}