var dg;
var d;
$(function(){
	dg=$('#zyaqgl_xgdw_dg').datagrid({    
	method: "post",
    url:ctx+'/zyaqgl/xgdw/list?qyid='+qyid,
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
              {field:'qyname',title:'企业名称',width:80,align:'center'},
              {field:'m1',title:'类别',width:60,align:'center'},  
              {field:'m2',title:'单位名称',width:60,align:'center'},    
              {field:'m3',title:'行业类型',width:70,align:'center'},
              {field:'m6',title:'联系人',width:100,align:'center'},
              {field:'m7',title:'联系电话',width:60,align:'center'},
              {field:'type',title:'是否列入黑名单',width:60,align:'center',
                formatter: function(value) {
                  if(value == '1') {
                      return "<a class='btn btn-danger btn-xs'>是</a>";
                  }else {
                      return "<a class='btn btn-success btn-xs'>否</a>";
                  }
                }
              }
    ]],
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
                 view();
    },
	checkOnSelect:false,
	selectOnCheck:false,
    onLoadSuccess:function(){
	  	  if(usertype=="1"){
			  $(this).datagrid("hideColumn",['qyname']);
		  }else{
			  $(this).datagrid("showColumn",['qyname']);
		  }
    	  $(this).datagrid("autoMergeCells",['qyname']);
      },
    toolbar:'#zyaqgl_xgdw_tb'
	});
});

//弹窗增加
function add(u) {
	openDialog("添加相关方单位信息",ctx+"/zyaqgl/xgdw/create/","800px", "400px","");
}

//弹窗增加
function addgl() {
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	openDialogView("添加相关方关联信息",ctx+"/zyaqgl/xgdw/creategl/"+row.id,"900px", "450px","");
	
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
			url:ctx+"/zyaqgl/xgdw/delete/"+ids,
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
	
	openDialog("修改相关方单位信息",ctx+"/zyaqgl/xgdw/update/"+row.id,"800px", "400px","");
	
}

//查看
function view(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	
	openDialogView("查看相关方单位信息",ctx+"/zyaqgl/xgdw/view/"+row.id,"900px", "500px","");
	
}

//加入黑名单
function addblacklist(){
    var row = dg.datagrid('getSelected');
    if(row==null) {
        layer.msg("请选择一行记录！",{time: 1000});
        return;
    }
    if(row.type==1){
        layer.msg("该承包商已列入黑名单！");
        return;
    }
    top.layer.confirm('确定是否加入黑名单？', {icon: 3, title:'提示'}, function(index){
        $.ajax({
            type:'get',
            url:ctx+"/zyaqgl/xgdw/blacklist/"+row.id+"/1",
            success: function(data){
                layer.alert(data, {offset: 'rb',shade: 0,time: 2000});
                top.layer.close(index);
                dg.datagrid('reload');
                dg.datagrid('clearChecked');
            }
        });
    });
}

//恢复
function removeblacklist(){
    var row = dg.datagrid('getSelected');
    if(row==null) {
        layer.msg("请选择一行记录！",{time: 1000});
        return;
    }
    if(row.type!=1){
        layer.msg("该承包商未列入黑名单！");
        return;
    }
    top.layer.confirm('确定是否移出黑名单？', {icon: 3, title:'提示'}, function(index){
        $.ajax({
            type:'get',
            url:ctx+"/zyaqgl/xgdw/blacklist/"+row.id+"/0",
            success: function(data){
                layer.alert(data, {offset: 'rb',shade: 0,time: 2000});
                top.layer.close(index);
                dg.datagrid('reload');
                dg.datagrid('clearChecked');
            }
        });
    });
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
