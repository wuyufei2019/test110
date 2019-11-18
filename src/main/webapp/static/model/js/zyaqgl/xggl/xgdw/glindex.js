var dg;
var dg2;
var dg3;
var d;
var gqCnt = 0;
var gqCnt1 = 0;
var select = 0;//选中的tab页
function selectInfo(index) {
    if (index == 0) {
        if(gqCnt > 0){
            layer.open({icon:1,title: '提示',offset: 'rb',content:"有" + gqCnt + "个培训计划已过期",shade: 0 ,time: 2000 });
        }
    }else if (index == 1) {
        if(gqCnt1 > 0){
            layer.open({icon:1,title: '提示',offset: 'rb',content:"有" + gqCnt1 + "个作业信息已过期",shade: 0 ,time: 2000 });
        }
    }
}
$(function () {
    $('.easyui-tabs').tabs({onSelect:function(title,index){
            select=index;
            selectInfo(select);
        }});
});
//资质信息表datagrid
$(function(){
	dg=$('#zyaqgl_xgdw_dg').datagrid({    
	method: "post",
    url:ctx+'/zyaqgl/zzxx/list?dwid='+dwid, 
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
	pageList : [50, 100, 150, 200, 250 ],
	scrollbarSize:5,
	singleSelect:true,
	striped:true,
    columns:[[    
		{field : 'ID',title : 'id',checkbox : true,width : 50,align : 'center'},
		{field:'m1',title:'资质证书名称',sortable:false,width:100},    
		{field:'m2',title:'级别',sortable:false,width:100,align:'center', 
			formatter : function(value, row, index){
				if(value=='一级') return value='一级';
				if(value=='二级') return value='二级';
				if(value=='三级') return value='三级';
				if(value=='四级') return value='四级';
				if(value=='五级') return value='五级';
				if(value=='六级') return value='六级';
			}},
			{field:'m3',title:'许可内容',sortable:false,width:100,align:'center'},
			{field:'m4',title:'有效期起',sortable:false,width:100,align:'center',
				formatter:function(value,row,index){
					if(value!=null){
						var datetime = new Date(value);  
						 return datetime.format('yyyy-MM-dd');   
					}  
	            }
			},
			{field:'dq',title:'有效期止',sortable:false,width:100,align:'center',
				styler: function(value, row, index){
					var nowhm=(new Date()).getTime();
					/*if(nowhm>=value){
						return 'background-color:rgb(249, 156, 140);';
					}else{
						var cha=(value-nowhm)/1000/60/60/24;
						if(cha<=90) return 'background-color:rgb(255, 228, 141);';
					}*/
					if(nowhm<=value){
                  		var cha=(value-nowhm)/1000/60/60/24;
                  		if(cha<=90) return 'background-color:rgb(255, 228, 141);';
              		}else{
              			gqCnt = gqCnt + 1;
              			return 'background-color:rgb(249, 156, 140);';
              		}
				},   
				formatter:function(value,row,index){
					if(value!=null){
						var datetime = new Date(value);  
						 return datetime.format('yyyy-MM-dd');   
					}  
	            }
			} 
    ]],
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
    	view(1);
    },
    
    onLoadSuccess:function (){
    	if(gqCnt > 0 && select==0){
            selectInfo(0);
    	}
    },
    
	checkOnSelect:false,
	selectOnCheck:false,
    toolbar:'#zyaqgl_xgdw_tb'
	});
	
});


//资质信息表查询
function search(){
    gqCnt=0;
	var obj=$("#zyaqgl_xgdw_searchFrom").serializeObject();
	$('#zyaqgl_xgdw_dg').datagrid('load',obj); 
}

//清空
function clearA(){
	$("#zyaqgl_xgdw_searchFrom").form("clear");
	search();
}

//特种作业人员表datagrid
$(function(){   
	dg2=$('#zyaqgl_xgdw_dg2').datagrid({    
	method: "post",
    url:ctx+'/zyaqgl/tzzyry/list?dwid='+dwid, 
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
	pageList : [50, 100, 150, 200, 250 ],
	scrollbarSize:5,
	singleSelect:true,
	striped:true,
    columns:[[
        {field:'ID',title:'id',checkbox:true,width:50,align:'center'},      
		{field:'m1',title:'姓名',sortable:false,width:50},    
		{field:'m2',title:'性别',sortable:false,width:30,align:'center',
			formatter : function(value, row, index){
				if(value=='0') return value='男';
				if(value=='1') return value='女';}},
		{field:'m8',title:'类别',sortable:false,width:70,align:'center'},
		{field:'m3',title:'操作证号',sortable:false,width:100,align:'center'},
		{field:'m4',title:'身份证号',sortable:false,width:100,align:'center'},
		{field:'m5',title:'作业类型',sortable:false,width:70,align:'center'},
		{field:'m6',title:'到期日期',sortable:false,width:100,align:'center',
			formatter:function(value,row,index){
				if(value!=null){
					var datetime = new Date(value);  
					 return datetime.format('yyyy-MM-dd');  
				}  
            },styler: function(value, row, index){
				var nowhm=(new Date()).getTime();
				if(nowhm>=value){
                    gqCnt1 = gqCnt1 + 1;
					return 'background-color:rgb(249, 156, 140);';
				}else{
					var cha=(value-nowhm)/1000/60/60/24;
					if(cha<=90) return 'background-color:rgb(255, 228, 141);';
				}
			}
		},
		{field:'m7',title:'备注',sortable:false,width:100,align:'center'}  
    ]],
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
    	view(2);
    },
    onLoadSuccess:function (){
        if(gqCnt1 > 0 && select==1){
            selectInfo(1);        }
    },
	checkOnSelect:false,
	selectOnCheck:false,
    toolbar:'#zyaqgl_xgdw_tb2'
	});
	
});


//特种作业人员表查询
function search2(){
    gqCnt1=0;
	var obj=$("#zyaqgl_xgdw_searchFrom2").serializeObject();
	$('#zyaqgl_xgdw_dg2').datagrid('load',obj); 
}

//清空
function clearA2(){
	$("#zyaqgl_xgdw_searchFrom2").form("clear");
    search2();
}

//服务项目表datagrid
$(function(){   
	dg3=$('#zyaqgl_xgdw_dg3').datagrid({    
	method: "post",
    url:ctx+'/zyaqgl/fwxm/list?dwid='+dwid, 
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
	pageList : [50, 100, 150, 200, 250 ],
	scrollbarSize:5,
	singleSelect:true,
	striped:true,
    columns:[[    
		{field:'ID', title:'id',checkbox : true,width : 50,align : 'center'},
   		{field:'m1', title:'项目类型',sortable : false,width : 100},
   		{field:'m2', title:'项目名称',sortable : false,width : 100},
   		{field:'m3', title:'服务项目内容',sortable : false,width : 100},
   		{field:'m4', title:'项目负责人',sortable : false,width : 100},
   		{field:'m5', title:'项目合同资金',sortable : false,width : 100},
   		{field:'m6', title:'开始时间',sortable : false,width : 100,
   			formatter:function(value,row,index){
			if(value!=null){
				var datetime = new Date(value);  
				 return datetime.format('yyyy-MM-dd');   
			}  
        }},
   		{field:'m7', title:'结束时间',sortable : false,	width : 100,
			formatter:function(value,row,index){
				if(value!=null){
					var datetime = new Date(value);  
					 return datetime.format('yyyy-MM-dd');  
				}  
        }}  
    ]],
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
    	view(3);
    },
	checkOnSelect:false,
	selectOnCheck:false,
    toolbar:'#zyaqgl_xgdw_tb3'
	});
	
});


//服务项目表查询
function search3(){
	var obj=$("#zyaqgl_xgdw_searchFrom3").serializeObject();
	$('#zyaqgl_xgdw_dg3').datagrid('load',obj); 
}

//清空
function clearA3(){
	$("#zyaqgl_xgdw_searchFrom3").form("clear");
	var obj=$("#zyaqgl_xgdw_searchFrom3").serializeObject();
	$('#zyaqgl_xgdw_dg3').datagrid('load',obj);
}

//检查表库增加
function add(flag) {
	if(flag==1){
		openDialog("添加资质信息",ctx+"/zyaqgl/zzxx/create?dwid="+dwid,"700px", "350px","");
	}else if(flag==2){
		openDialog("添加特种作业人员信息",ctx+"/zyaqgl/tzzyry/create?dwid="+dwid,"700px", "350px","");
	}else if(flag==3){
		openDialog("添加服务项目信息",ctx+"/zyaqgl/fwxm/create?dwid="+dwid,"700px", "350px","");
	}
}

//删除
function del(flag){
	var delurl="";
	var row;
	if(flag==1){
		row = dg.datagrid('getChecked');
		delurl=ctx+"/zyaqgl/zzxx/delete/"
	}else if(flag==2){
		row = dg2.datagrid('getChecked');
		delurl=ctx+"/zyaqgl/tzzyry/delete/"
	}else if(flag==3){
		row = dg3.datagrid('getChecked');
		delurl=ctx+"/zyaqgl/fwxm/delete/"
	}
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
			url:delurl+ids,
			success: function(data){
				layer.alert(data, {icon:1,offset: 'rb',shade: 0,time: 2000}); 
				top.layer.close(index);
				dg.datagrid('reload');
				dg2.datagrid('reload');
				if(flag==1){
					dg3.datagrid('reload');
					dg.datagrid('clearChecked');
					dg.datagrid('clearSelections');
				}else if(flag==2){
					dg2.datagrid('clearChecked');
					dg2.datagrid('clearSelections');
				}else if(flag==3){
					dg3.datagrid('reload');
					dg3.datagrid('clearChecked');
					dg3.datagrid('clearSelections');
				}
			}
		});
	});
 
}

//弹窗修改
function upd(flag){
	var row;
	if(flag==1){
		row = dg.datagrid('getSelected');
	}else if(flag==2){
		row = dg2.datagrid('getSelected');
	}else if(flag==3){
		row = dg3.datagrid('getSelected');
	}
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	if(flag==1){
		openDialog("修改资质信息",ctx+"/zyaqgl/zzxx/update/"+row.id,"700px", "350px","");
	}
	if(flag==2){
		openDialog("修改特种作业人员信息",ctx+"/zyaqgl/tzzyry/update/"+row.id,"700px", "350px","");
	}
	if(flag==3){
		openDialog("修改服务项目信息",ctx+"/zyaqgl/fwxm/update/"+row.id,"700px", "350px","");
	}
}

//查看
function view(flag){
	var viewurl="";
	var title="";
	var row="";
	if(flag==1){
		row = dg.datagrid('getSelected');
		viewurl=ctx+"/zyaqgl/zzxx/view/";
		title="查看资质信息"
	}else if(flag==2){
		row = dg2.datagrid('getSelected');
		viewurl=ctx+"/zyaqgl/tzzyry/view/";
		title="查看特种作业人员信息"
	}else if(flag==3){
		row = dg3.datagrid('getSelected');
		viewurl=ctx+"/zyaqgl/fwxm/view/";
		title="查看服务项目信息"
	}
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	openDialogView(title,viewurl+row.id,"700px", "350px","");
	
}
