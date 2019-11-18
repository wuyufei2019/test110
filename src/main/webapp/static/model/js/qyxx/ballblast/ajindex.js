var dg;
var d;
$(function(){
	dg=$('#bis_ballblast_dg').datagrid({
	method: "post",
    url:ctx+'/bis/ballblast/ajlist',
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
        {field:'qyname',title:'企业名称',sortable:false,width:100,align:'left'},
        {field:'m1',title:'设备型号',sortable:false,width:80,align:'center',
            formatter:function(value, row, index){
                if(value == '1') {
                    return '立式';
                }else if(value == '2') {
                    return '卧式';
                }else if(value == '3') {
                    return '手动';
                }
            }
        },
        {field:'m2',title:'台数',sortable:false,width:50,align:'center'},
        {field:'m3',title:'作业区域人数',sortable:false,width:50,align:'center'},
        {field:'m5',title:'产品材质',sortable:false,width:50,align:'center'},
        {field:'m6',title:'砂丸材质',sortable:false,width:50,align:'center'},
        {field:'m7',title:'清理制度',sortable:false,width:50,align:'center',
            formatter:function(value, row, index){
                if(value=='0'){
                    return '有';
                }else if(value=='1') {
                    return '无';
                }
            }
        },
        {field:'m8',title:'清理记录',sortable:false,width:50,align:'center',
            formatter:function(value, row, index){
                if(value=='0'){
                    return '有';
                }else if(value=='1') {
                    return '无';
                }
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
    toolbar:'#bis_ballblast_tb'
	});
	
});

//弹窗增加
function add(u) {
	openDialog("添加抛丸信息",ctx+"/bis/ballblast/create/","800px", "400px","");
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
			url:ctx+"/bis/ballblast/delete/"+ids,
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
	
	openDialog("修改抛丸信息",ctx+"/bis/ballblast/update/"+row.id,"800px", "400px","");
	
}

//查看
function view(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	
	openDialogView("查看抛丸信息",ctx+"/bis/ballblast/view/"+row.id,"800px", "400px","");
	
}

//创建查询对象并查询
function search(){
	$('#bis_ballblast_cx_qyname').combobox('setValue',$('#bis_ballblast_cx_qyname').combobox('getText'));
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
	window.exdata= [{colval:'qyname', coltext:'企业名称'},
                    {colval:'m1', coltext:'设备型号'},
                    {colval:'m2', coltext:'台数'},
                    {colval:'m3', coltext:'作业区域人数'},
                    {colval:'m4', coltext:'集尘器位置'},
                    {colval:'m5', coltext:'产品材质'},
                    {colval:'m6', coltext:'砂丸材质'},
                    {colval:'m7', coltext:'清理制度'},
                    {colval:'m8', coltext:'清理记录'}
                    ];
	layer.open({
	    type: 2,  
	    area: ['350px', '350px'],
	    title: '导出列选择',
        maxmin: true, 
        shift: 1,
	    content: ctx+'/bis/ballblast/colindex',
		btn: ['导出'],
	    yes: function(index, layero){
	    	 var body = layer.getChildFrame('body', index);
	         var iframeWin = layero.find('iframe')[0]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
	         var inputForm = body.find('#excel_mainform');
	         iframeWin.contentWindow.doExport();
		  },
	});
		
}