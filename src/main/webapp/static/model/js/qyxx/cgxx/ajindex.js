var dg;
var d;
$(function(){
	dg=$('#bis_cgxx_dg').datagrid({    
	method: "post",
    url:ctx+'/bis/cgxx/ajlist', 
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
        {field:'qyname',title:'企业名称',sortable:false,width:200,align:'left'}, 
        {field:'M1',title:'储罐名称',sortable:false,width:100},    
        {field:'M9',title:'储罐位号',sortable:false,width:100,align:'center'},   
        {field:'M2',title:'储罐类型',sortable:true,width:100,align:'center',
         	formatter : function(value, row, index) {
            	if(value=='1') return value='立式圆筒形储罐';
            	if(value=='2') return value='卧式圆筒形储罐';
				if(value=='3') return value='球形储罐';
				if(value=='4') return value='其他储罐';
        	}      
        },
        {field:'M4',title:'材质',sortable:true,width:100,align:'center',
         	formatter : function(value, row, index) {
            	if(value=='1') return value='滚塑';
            	if(value=='2') return value='玻璃钢';
				if(value=='3') return value='碳钢';
				if(value=='4') return value='陶瓷';
				if(value=='5') return value='橡胶';
				if(value=='7') return value='不锈钢';
				if(value=='6') return value='其他';
        	}      
       	},
        {field:'M3',title:'容积(m³)',sortable:true,width:100,align:'center'},
        {field:'M6',title:'火灾危险性等级',sortable:true,width:100,align:'center',
         	formatter : function(value, row, index) {
            	if(value=='1') return value='甲类';
            	if(value=='2') return value='乙类';
				if(value=='3') return value='丙类';
				if(value=='4') return value='丁类';
				if(value=='5') return value='戊类';
        	}      
        },
        {field:'M7',title:'存储物料名称',sortable:false,width:100,align:'center'},
        {field:'M8',title:'CAS号',sortable:false,width:100,align:'center'},
        {field:'m15',title:'照片',sortable:false,width:50,align:'center',
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
    toolbar:'#bis_cgxx_tb'
	});
	
});

//弹窗增加
function add(u) {
	openDialog("添加储罐信息",ctx+"/bis/cgxx/create/","800px", "400px","");
}
//统计
function statistics(){
	top.layer.open({
	    type: 2,  
	    area: ['80%', '80%'],
	    title: '储罐信息统计分析',
        maxmin: true, 
	    content: ctx+"/bis/cgxx/statistics" ,
	    btn: ['关闭'],
	    cancel: function(index){ 
	       }
	});
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
			url:ctx+"/bis/cgxx/delete/"+ids,
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
	
	openDialog("修改储罐信息",ctx+"/bis/cgxx/update/"+row.ID,"800px", "400px","");
	
}

//查看
function view(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	
	openDialogView("查看储罐信息",ctx+"/bis/cgxx/view/"+row.ID,"800px", "400px","");
	
}

//创建查询对象并查询
function search(){
	$('#bis_cgxx_cx_qyname').combobox('setValue',$('#bis_cgxx_cx_qyname').combobox('getText'));
	$('#M7').combobox('setValue',$('#M7').combobox('getText'));
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
	$('#bis_cgxx_cx_qyname').combobox('setValue',$('#bis_cgxx_cx_qyname').combobox('getText'));
	$('#M7').combobox('setValue',$('#M7').combobox('getText'));
	window.expara=$("#searchFrom").serializeObject();
	window.exdata=[
	               	{colval:'qyname', coltext:'企业名称'},
			   		{colval:'m1', coltext:'储罐名称'},
			   		{colval:'m2', coltext:'储罐类型'},
			   		{colval:'m3', coltext:'容积'},
			   		{colval:'m4', coltext:'材质'},
			   		{colval:'m6', coltext:'火灾危险性等级'},
			   		{colval:'m7', coltext:'储存物料名称'},
			   		{colval:'m8', coltext:'cas号'},
			   		{colval:'m9', coltext:'位号'},
			   		{colval:'m10', coltext:'罐径'},
			   		{colval:'m11', coltext:'罐高'},
			   		{colval:'m12', coltext:'储罐区面积'},
			   		{colval:'m13', coltext:'有无防火堤'},
			   		{colval:'m14', coltext:'防火堤所围面积'},
//			   		{colval:'deviceno', coltext:'设备编号'},
//			   		{colval:'channelno', coltext:'设备通道'},
			   		{colval:'whp', coltext:'危化品类别'}
		   	];
	layer.open({
	    type: 2,  
	    area: ['350px', '350px'],
	    title: '导出列选择',
        maxmin: false, 
        shift: 1,
	    content: ctx+'/bis/cgxx/colindex',
		btn: ['导出'],
	    yes: function(index, layero){
	    	 var body = layer.getChildFrame('body', index);
	         var iframeWin = layero.find('iframe')[0];  
	         var inputForm = body.find('#excel_mainform');
	         iframeWin.contentWindow.doExport();
		  },
	});
		
}