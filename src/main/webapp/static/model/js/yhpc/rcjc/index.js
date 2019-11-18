var dg;
$(function(){
	dg=$('#table_dg').datagrid({    
	method: "post",
	url:ctx+'/yhpc/rcjc/list', 
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
	scrollbarSize:10,
	singleSelect:true,
	striped:true,
	columns:[[ 
            {field:'id',title:'id',checkbox:true,width:50,align:'center'}, 
            {field:'qyname',title : '所属企业',sortable:false,width : 50,align : 'center'},
            {field:'m1',title:'检查日期',sortable:false,width:60,align: 'center',formatter:function(value){
                  if(value) 
                	  return new Date(value).format("yyyy-MM-dd");
            }}, 
            {field:'m3',title:'辖区部门',sortable:false,align: 'center',width:50}, 
            {field:'m2',title:'责任部门',sortable:false,width:50,align: 'center'}, 
           /* {field:'m4',title:'现场照片(点击查看)',sortable:false,align: 'center',align: 'center',width:100,
            	formatter : function(value, row, index) {
    				var content="";
                  	if(value!=null&&value!='') {
                  		var sub_value = value.split(",");
                    	for (var i = 0; i < sub_value.length; i++) {
                    		var arr1=sub_value[i].split("||");
                    		content=content+ '<img onclick=openImgView("'+arr1[0]+'") src='+arr1[0]+' style=margin-right:5px;max-width:100px;max-height:50px; />';
                    	} 
                    	return content;
                  	}else{
                  		return '/';
                  	}
                }	
            }, */
            {field:'m6_1',title:'检查类型',sortable:false,align: 'center',width:50},
        	{field:'m6_2',title:'缺失类型',sortable:false,align: 'center',width:50},
        	{field:'m5',title:'缺失情况',sortable:false,align: 'center',width:150},
            {field:'m8',title:'责任部门负责人',sortable:false,align: 'center',width:70},
        	{field:'m9',title:'指定整改人',sortable:false,align: 'center',width:70},
        	{field:'m16',title:'严重程度',sortable:false,align: 'center',width:50},
        	{field:'m10',title:'计划完成时间',sortable:false,align: 'center',width:60,formatter:function(value){
                  if(value) return new Date(value).format("yyyy-MM-dd");
            }},
        /*{field:'m7',title:'解决措施',sortable:false,align: 'center',width:150},
        {field:'m12',title:'整改后照片(点击查看)',align: 'center',sortable:false,width:100,
            	formatter : function(value, row, index) {
    				var content="";
    				if(value!=null&&value!='') {
                  		var sub_value = value.split(",");
                    	for (var i = 0; i < sub_value.length; i++) {
                    		var arr1=sub_value[i].split("||");
                    		content=content+ '<img onclick=openImgView("'+arr1[0]+'") src='+arr1[0]+' style=margin-right:5px;max-width:100px;max-height:50px; />';
                    	} 
                    	return content;
                  	}else{
                  		return '/';
                  	}
                }		
            }, */
            {field:'m11',title:'实际完成时间',sortable:false,align: 'center',width:60,formatter:function(value){
                  if(value) return new Date(value).format("yyyy-MM-dd");
            }}, 
            {field:'m14',title:'稽核人',sortable:false,align: 'center',width:50}, 
            {field:'m15',title:'整改费用',sortable:false,align: 'center',width:50}, 

            {field:'m17',title:'审核反馈',sortable:false,align: 'center',width:100},
			{field:'shstate',title:'审核状态',sortable:false,width:50,align:'center',
				formatter : function(value, row, index) {
					if(value=='0')
						return "<a class='btn btn-warning btn-xs'>待审核</a>";
					if(value=='1')
                        return "<a class='btn btn-info btn-xs'>审核通过</a>";
					if(value=='2')
						return "<a class='btn btn-danger btn-xs'>审核未通过</a>";
				}
			},
			{field:'m13',title:'整改状态',sortable:false,width:50,align:'center',
				formatter : function(value, row, index) {
					if(value=='1')
						return "<a class='btn btn-warning btn-xs'>待整改</a>";
					if(value=='2')
                        return "<a class='btn btn-warning btn-xs'>整改待审核</a>";
					if(value=='3')
						return "<a class='btn btn-danger btn-xs'>整改未通过</a>";
                    if(value=='4')
                        return "<a class='btn btn-info btn-xs'>整改完成</a>";
				}
			},
            {field:'cz',title:'操作',sortable:false,width:40,align:'center',
				formatter : function(value, row, index) {
            	var html="";
            		if(row.shstate=='0'||row.shstate=='2'){//待审核和审核未通过，并且负责人为当前用户，执行审核操作
                        if(row.m8==userid)
                            html+="<a class='btn btn-success btn-xs' onclick='shenhe("+row.id+")'>审核</a>"
                    }else if(row.shstate=='1'){//审核通过才行执行整改操作
						if(row.m13=='1'&&row.m9==userid){//如果未整改，并且指定整改人为当前用户，则可以进行整改
                            html+="<a class='btn btn-success btn-xs' onclick='addReform("+row.id+")'>添加整改</a>"
						}else if(row.m13=='3'&&row.m9==userid){//如果整改未通过，并且指定整改人为当前用户，则可以修改整改信息
                            html+="<a class='btn btn-success btn-xs' onclick='addReform("+row.id+")'>修改整改</a>"
                        }else if(row.m13=='2'){//如果整改待审核，并且负责人为当前用户，则进行整改审核
                            if(row.m8==userid)
								html+="<a class='btn btn-success btn-xs' onclick='zhgshenhe("+row.id+")'>整改审核</a>";
						}
            		}
            		return html;
				}
            }
        ]],
        onLoadSuccess:function(data){
        	if(type == '1'){
    			$(this).datagrid("hideColumn", [ 'qyname' ]);
    		}else{
    			$(this).datagrid("showColumn", [ 'qyname' ]);
    			/*$(this).datagrid("autoMergeCells", [ 'qyname' ]);*/
    		}
    	},
        onDblClickRow: function (rowindex, rowdata, rowDomElement){
             view();
        },
	checkOnSelect:false,
	selectOnCheck:false,
	toolbar:'#dg_tb'
	});
});

//弹窗增加
function add() {
	openDialog("添加日常检查信息",ctx+"/yhpc/rcjc/create/","800px", "400px","");
}

//添加整改信息
function addReform(id){
    openDialog("添加整改信息", ctx + "/yhpc/rcjc/reform/"+id, "800px", "600px", "");
}

//删除
function del(){
	var rows = dg.datagrid('getChecked');
	if(rows.length==0) {
		layer.msg("请至少勾选一行记录！",{time: 1000});
		return;
	}

	var ids="";
    for(var item of rows){
    	if(item.shstate=='1'){
            layer.msg("只能删除未审核或审核未通过的记录！",{time: 1000});
            return;
		}
       if(ids==""){
          ids=item.id;
       }else{
          ids=ids+","+item.id;
       }
    }

	top.layer.confirm('删除后无法恢复您确定要删除？', {icon: 3, title:'提示'}, function(index){
		$.ajax({
			type:'get',
			url:ctx+"/yhpc/rcjc/delete/"+ids,
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
	if(row.shstate == '1') {
		layer.msg("审核通过不能修改！",{time: 1000});
		return;
	}
	openDialog("修改日常检查信息信息",ctx+"/yhpc/rcjc/update/"+row.id,"800px", "400px","");
	
}

//整改审核
function zhgshenhe(id){
	openDialog("整改信息审核",ctx+"/yhpc/rcjc/zhgshenhe/"+id,"800px", "400px","");
	
}
//弹窗审核
function shenhe(id) {
	openDialog("添加日常检查审核信息",ctx+"/yhpc/rcjc/shform/"+id,"800px", "500px","");
}

//查看
function view(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	openDialogView("查看隐患排查---日常检查信息信息",ctx+"/yhpc/rcjc/view/"+row.id,"800px", "400px","");
	
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
	window.expara=$("#searchFrom").serializeObject();
	window.exdata=[
			   		{colval:'m1', coltext:'检查日期'},
			   		{colval:'m2', coltext:'责任部门'},
			   		{colval:'m3', coltext:'辖区部门'},
			   		{colval:'m5', coltext:'缺失情况'},
			   		{colval:'m6_1', coltext:'检查类型'},
			   		{colval:'m6_2', coltext:'缺失类型'},
			   		{colval:'m7', coltext:'解决措施'},
			   		{colval:'m8', coltext:'责任部门负责人'},
			   		{colval:'m9', coltext:'协助部门负责人'},
			   		{colval:'m10', coltext:'计划完成时间'},
			   		{colval:'m11', coltext:'实际完成时间'},
			   		{colval:'zt', coltext:'状态确认'},
			   		{colval:'m14', coltext:'稽核人'},
			   		{colval:'m15', coltext:'整改费用'},
			   		{colval:'m16', coltext:'严重程度'},
			   		{colval:'m17', coltext:'备注'}
		   	];
	layer.open({
	    type: 2,  
	    area: ['350px', '350px'],
	    title: '导出列选择',
        maxmin: false, 
        shift: 1,
	    content: ctx+'/yhpc/rcjc/colindex',
		btn: ['导出'],
	    yes: function(index, layero){
	    	 var body = layer.getChildFrame('body', index);
	         var iframeWin = layero.find('iframe')[0];  
	         var inputForm = body.find('#excel_mainform');
	         iframeWin.contentWindow.doExport();
		  },
	});
		
}

//类别管理
function lxgl(){
	openDialogView("类型管理",ctx+"/yhpc/rcjc/lxgl/","500px", "400px","");
}