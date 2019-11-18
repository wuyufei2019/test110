var dg;
var cxtype;
var d;
var usertype;
$(function(){
	cxtype="1";
	dg=$('#sekb_msds_dg').datagrid({    
	method: "post",
    url:ctx+'/sekb/msds/list', 
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
              {field:'m1',title:'化学品中文名',width:100},    
              {field:'m2',title:'化学品英文名称',width:100},
              {field:'m3',title:'中文名称2',width:100},
              {field:'m4',title:'英文名称2',width:100},
              {field:'m5',title:'技术说明书编号',width:100}
    ]],
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
                 view();
    },
	checkOnSelect:false,
	selectOnCheck:false,
    toolbar:'#sekb_msds_tb'
	});
});

//弹窗增加
function add(u) {
	openDialog("添加SDS信息",ctx+"/sekb/msds/create/","800px", "400px","");
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
			url:ctx+"/sekb/msds/delete/"+ids,
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
	
	openDialog("修改SDS信息",ctx+"/sekb/msds/update/"+row.id,"800px", "400px","");
	
}

//查看
function view(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	window.open(ctx+'/sekb/msds/view/'+row.id, "化学安全技术说明书");
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
					{colval:'m1', coltext:'化学品中文名'},
					{colval:'m2', coltext:'化学品英文名称'},
					{colval:'m3', coltext:'中文名称2'},
					{colval:'m4', coltext:'英文名称2'},
					{colval:'m5', coltext:'技术说明书编号'},
					{colval:'m6', coltext:'CASNo'},
					{colval:'m7', coltext:'分子式'},
					{colval:'m8', coltext:'分子量'},
					{colval:'m9', coltext:'含量'},
					{colval:'m10', coltext:'健康危害'},
					{colval:'m11', coltext:'环境危害'},
					{colval:'m12', coltext:'燃爆危险'},
					{colval:'m13', coltext:'皮肤接触'},
					{colval:'m14', coltext:'眼睛接触'},
					{colval:'m15', coltext:'吸入'},
					{colval:'m16', coltext:'食入'},
					{colval:'m17', coltext:'危险特性'},
					{colval:'m18', coltext:'有害燃烧产物'},
					{colval:'m19', coltext:'灭火方法'},
					{colval:'m20', coltext:'应急处理'},
					{colval:'m21', coltext:'操作注意事项'},
					{colval:'m22', coltext:'储存注意事项'},
					{colval:'m23', coltext:'中国MAC'},
					{colval:'m24', coltext:'前苏联'},
					{colval:'m25', coltext:'TLVTN'},
					{colval:'m26', coltext:'TLVWN'},
					{colval:'m27', coltext:'工程控制'},
					{colval:'m28', coltext:'呼吸系统防护'},
					{colval:'m29', coltext:'眼睛防护'},
					{colval:'m30', coltext:'身体防护'},
					{colval:'m31', coltext:'手防护'},
					{colval:'m32', coltext:'其他防护'},
					{colval:'m33', coltext:'主要成分'},
					{colval:'m34', coltext:'外观与性状'},
					{colval:'m35', coltext:'熔点'},
					{colval:'m36', coltext:'沸点'},
					{colval:'m37', coltext:'相对密度'},
					{colval:'m38', coltext:'相对蒸气密度'},
					{colval:'m39', coltext:'饱和蒸气压'},
					{colval:'m40', coltext:'燃烧热'},
					{colval:'m41', coltext:'临界温度'},
					{colval:'m42', coltext:'临界压力'},
					{colval:'m43', coltext:'水分配系数的对数值'},
					{colval:'m44', coltext:'闪点'},
					{colval:'m45', coltext:'引燃温度'},
					{colval:'m46', coltext:'爆炸上限'},
					{colval:'m47', coltext:'爆炸下限'},
					{colval:'m48', coltext:'主要用途'},
					{colval:'m49', coltext:'禁配物'},
					{colval:'m50', coltext:'急性毒性LD50'},
					{colval:'m51', coltext:'急性毒性LC50'},
					{colval:'m52', coltext:'其它有害作用'},
					{colval:'m53', coltext:'废弃物性质废弃处置方法'},
					{colval:'m54', coltext:'危险货物编号'},
					{colval:'m55', coltext:'UN编号'},
					{colval:'m56', coltext:'包装类别'},
					{colval:'m57', coltext:'包装方法'},
					{colval:'m58', coltext:'运输注意事项'},
					{colval:'m59', coltext:'法规信息'}
		   	];
	layer.open({
	    type: 2,  
	    area: ['350px', '350px'],
	    title: '导出列选择',
        maxmin: false, 
        shift: 1,
	    content: ctx+'/sekb/msds/colindex',
		btn: ['导出'],
	    yes: function(index, layero){
	    	 var body = layer.getChildFrame('body', index);
	         var iframeWin = layero.find('iframe')[0];  
	         var inputForm = body.find('#excel_mainform');
	         iframeWin.contentWindow.doExport();
		  },
	});
		
}