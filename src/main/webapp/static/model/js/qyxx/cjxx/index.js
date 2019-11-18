var dg;
var d;
$(function(){
	dg=$('#bis_cjxx_dg').datagrid({    
	method: "post",
    url:ctx+'/bis/cjxx/list', 
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
        {field:'m1',title:'车间名称',sortable:false,width:80},    
        {field:'m3',title:'占地面积(㎡)',sortable:true,width:50,align:'center',
        	formatter:function(value, row, index){
        		if(value!=null&&value!=""){
        			return value.toFixed(2);
        		}
        	}
        },
        {field:'m6',title:'层数',sortable:true,width:50,align:'center',
        	formatter : function(value, row, index) {
        	 if(value==null){
        		 return '';
        	 }
           	 return value+'层';
        	}
        },
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
        {field:'m4',title:'火灾危险性分类',sortable:true,width:50,align:'center', 
        	formatter : function(value, row, index){
        		if(value=='1') return value='甲类';
        		if(value=='2') return value='乙类';
        		if(value=='3') return value='丙类';
        		if(value=='4') return value='丁类';
        		if(value=='5') return value='戊类';
        	}
        
        },
        {field:'m8',title:'耐火等级',sortable:true,width:50,align:'center',
        	formatter : function(value, row, index){
        		if(value=='1') return value='一级';
        		if(value=='2') return value='二级';
        		if(value=='3') return value='三级';
        		if(value=='4') return value='四级';
        	}
        },
        {field:'m9',title:'照片',sortable:false,width:50,align:'center',
        	formatter : function(value,row,index) {
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
	checkOnSelect:false,
	selectOnCheck:false,
    toolbar:'#bis_cjxx_tb'
	});
	
});

//弹窗增加
function add(u) {
	openDialog("添加车间信息",ctx+"/bis/cjxx/create/","800px", "400px","");
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
			url:ctx+"/bis/cjxx/delete/"+ids,
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
	
	openDialog("修改车间信息",ctx+"/bis/cjxx/update/"+row.id,"800px", "400px","");
	
}

//查看
function view(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	
	openDialogView("查看车间信息",ctx+"/bis/cjxx/view/"+row.ID,"800px", "400px","");
	
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
			   		{colval:'m1', coltext:'车间名称'},
			   		{colval:'m2', coltext:'车间编号'},
			   		{colval:'m3', coltext:'建筑面积'},
			   		{colval:'m4', coltext:'火灾危险等级'},
			   		{colval:'m5', coltext:'建筑结构'},
			   		{colval:'m6', coltext:'层数'},
			   		{colval:'m7', coltext:'备注'},
			   		{colval:'m8', coltext:'耐火等级'},
		   	];
	layer.open({
	    type: 2,  
	    area: ['350px', '350px'],
	    title: '导出列选择',
        maxmin: false, 
        shift: 1,
	    content: ctx+'/bis/cjxx/colindex',
		btn: ['导出'],
	    yes: function(index, layero){
	    	 var body = layer.getChildFrame('body', index);
	         var iframeWin = layero.find('iframe')[0];  
	         var inputForm = body.find('#excel_mainform');
	         iframeWin.contentWindow.doExport();
		  },
	});
		
}