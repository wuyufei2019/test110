var dg;
var d;
$(function(){
	dg=$('#aqpx_stgz_dg').datagrid({    
	method: "post",
    url:ctx+'/aqpx/stgz/list', 
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
              {field:'ID',title:'id',rowspan:2,checkbox:true,width:50,align:'center'},   
              {field:'type',title:'培训类别',rowspan:2,width:100,
            	  formatter : function(value, view ,index){
                    	if(value=='1') 
                    		return '日常计划培训';  
                    	else if (value=='2')
                    		return '入职三级教育培训';
                    	else if (value=='3')
                    		return '外来方培训';
                    	else return value;
              	  }},    
              {field:'kc',title:'课程',rowspan:2,width:100},    
              {title:'单选',colspan:2,align:'center'},
              {title:'多选',colspan:2,align:'center'},
              {title:'填空',colspan:2,align:'center'},
              {title:'判断',colspan:2,align:'center'},
              {field:'m9',title:'及格线',rowspan:2,width:50,align:'center'}
              
          ],[ {field:'m1',title:'数量',width:50,align:'center'},
              {field:'m5',title:'分值',width:50,align:'center'},
              {field:'m2',title:'数量',width:50,align:'center'},
              {field:'m6',title:'分值',width:50,align:'center'},
              {field:'m3',title:'数量',width:50,align:'center'},
              {field:'m7',title:'分值',width:50,align:'center'},
              {field:'m4',title:'数量',width:50,align:'center'},
              {field:'m8',title:'分值',width:50,align:'center'}
              ]],
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
                 view();
    },
	checkOnSelect:false,
	selectOnCheck:false,
    toolbar:'#aqpx_stgz_tb'
	});
	
});

//弹窗增加
function add(u) {
	openDialog("制定课程出卷规则",ctx+"/aqpx/stgz/create/","800px", "400px","");
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
			url:ctx+"/aqpx/stgz/delete/"+ids,
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
	
	openDialog("修改出卷规则信息",ctx+"/aqpx/stgz/update/"+row.id,"800px", "400px","");
	
}

//查看
function view(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	
	openDialogView("查看出卷规则信息",ctx+"/aqpx/stgz/view/"+row.id,"800px", "400px","");
	
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

//导出
function fileexport(){
	url=ctx+"/aqpx/stgz/export";
	window.location.href=url;
}