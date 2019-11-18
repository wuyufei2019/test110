var dg;
var dg2;
var d;
//所有隐患list
$(function(){
	dg2=$('#yhpc_jcyh_dg2').datagrid({    
	method: "post",
    url:ctx+'/yhpc/jcyh/list', 
    fit : true,
	fitColumns : true,
	border : false,
	idField : 'id',
	striped:true,
	pagination:true,
	nowrap:false,
	pageNumber:1,
	pageSize : 50,
	pageList : [ 50, 100, 150, 200, 250 ],
	scrollbarSize:5,
	singleSelect:true,
	striped:true,
    columns:[[    
        {field:'index',title:' ',align: 'center',
  			formatter:function(val,row,index){
  				var options = dg.datagrid('getPager').data("pagination").options; 
  			    var currentPage = options.pageNumber;
  			    var pageSize = options.pageSize;
  			    return value=(pageSize * (currentPage -1))+(index+1);
  			}
  		},
  		
  		{field:'id',title:'id',checkbox:true,width:50,align:'center'},
  		
  		{field :'qyname',title : '所属企业',sortable : false,width : 50,align : 'center'},
        {field:'taskname',title:'任务名称',sortable:false,width:60,align:'center'},
        {field:'createtime',title:'检查日期',sortable:false,width:60,align:'center',
      	  formatter : function(value, row, index) {
            	if(value!='') {
            		var datetime=new Date(value);
            		return datetime.format("yyyy-MM-dd");
            	}
        	} 
        },
        {field:'m13',title:'责任部门',sortable:false,width:60,align:'center'},
        {field:'m14',title:'发生区域',sortable:false,width:60,align:'center'},
        {field:'m1',title:'问题描述',sortable:false,width:70,align:'center'},
        {field:'m3',title:'隐患类别',sortable:false,width:40,align:'center'},
        {field:'m15',title:'解决措施',sortable:false,width:70,align:'center'},
        {field : 'm2',title : '现场照片',sortable : false,width : 40,align : 'center',
			formatter : function(value, row, index) {
				var content="";
              	if(value!=null&&value!='') {
              		var arr1=value.split("||");
                	for (var i = 0; i < arr1.length-1; i++) {
                		content=content+ '<img onclick=openImgView("'+arr1[i]+'") src='+arr1[i]+' style=margin-right:5px;max-width:100px;max-height:50px; />';
                	} 
                	return content;
              	}else{
              		return '/';
              	}
            }
		},
        {field:'m4',title:'计划完成时间',sortable:false,width:80,align:'center',
        	  formatter : function(value, row, index) {
              	if(value!='') {
              		var datetime=new Date(value);
              		return datetime.format("yyyy-MM-dd");
              	}
          	} 
        },
        {field:'m5',title:'实际完成时间',sortable:false,width:80,align:'center',
      	  formatter : function(value, row, index) {
            	if(value!=null&&value!='') {
            		var datetime=new Date(value);
            		return datetime.format("yyyy-MM-dd");
            	}
        	} 
        },
        {field:'zgr',title:'整改人',sortable:false,width:40,align:'center'},
		{field : 'm6',title : '整改照片',sortable : false,width : 40,align : 'center',
			formatter : function(value, row, index) {
				var content="";
              	if(value!=null&&value!='') {
              		var arr1=value.split("||");
                	for (var i = 0; i < arr1.length-1; i++) {
                		content=content+ '<img onclick=openImgView("'+arr1[i]+'") src='+arr1[i]+' style=margin-right:5px;max-width:100px;max-height:50px; />';
                	} 
                	return content;
              	}else{
              		return '/';
              	}
            }
		},
        {field:'m10',title:'整改费用',sortable:false,width:40,align:'center'},
        {field:'m7',title:'隐患等级',sortable:false,width:40,align:'center'},
        {field:'m11',title:'处理状态',sortable:false,width:40,align:'center',
			formatter : function(value, row, index) {
				var content="";
              	if(value=='0'&&row.m9==userid) {
              		return "<a style='margin:2px' class='btn btn-danger btn-xs' >未整改</a>"
              	}
              	if(value=='0'&&row.m9!=userid){
              		return "未整改"
              	}
              	if(value=='1'&&row.m9==userid){
              		return "<a style='margin:2px' class='btn btn-danger btn-xs' >整改未完成</a>";
              	}
              	if(value=='1'&&row.m9!=userid){
              		return "整改未完成";
              	}
              	if(value=='2'&&row.m12!=userid) {
              		return "<a  class='btn btn-success btn-xs' >整改完成</a>"
              	}
              	if(value=='2'&&row.m12==userid) {
              		return "<a  class='btn btn-success btn-xs' >发现人复查</a>"
              	}
				if(value=='3'&&row.m9==userid){
              		return "<a  class='btn btn-success btn-xs' >复查不通过</a>"
              	}
				if(value=='3'&&row.m9!=userid){
              		return "<a  class='btn btn-success btn-xs' >复查不通过</a>"
              	}
              	if(value=='4'){
              		return "<a  class='btn btn-success btn-xs' >复查通过</a>"
              	}
			}
        }
    ]],
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
        view();
    },
    onLoadSuccess:function(data){
    	if(type == '1'){
			$(this).datagrid("hideColumn", [ 'qyname' ]);
		}else{
			$(this).datagrid("showColumn", [ 'qyname' ]);
			/*$(this).datagrid("autoMergeCells", [ 'qyname' ]);*/
		}
	},
	checkOnSelect:false,
	selectOnCheck:false,
    toolbar:'#yhpc_jcyh_tb2'
	});
	
});

//创建查询对象并查询
function search2() {
	var obj = $("#searchFrom2").serializeObject();
	dg2.datagrid('load', obj);
}

//重置
function reset2() {
	$("#searchFrom2").form("clear");
	var obj = $("#searchFrom2").serializeObject();
	dg2.datagrid('load', obj);
}

//隐患整改
function upd(id){
	openDialog("隐患整改",ctx+"/yhpc/jcyh/update/"+id,"900px", "90%","");
}

//检查人复查
function review(id){
	openDialog("检查人复查",ctx+"/yhpc/jcyh/review/"+id,"900px", "90%","");
}

//查看
function view(){
	var row = dg2.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	openDialogView("查看隐患整改信息",ctx+"/yhpc/jcyh/view/"+row.id,"900px", "90%","");
}

//我要处理list
$(function(){
	dg=$('#yhpc_jcyh_dg').datagrid({    
	method: "post",
    url:ctx+'/yhpc/jcyh/mylist', 
    fit : true,
	fitColumns : true,
	border : false,
	idField : 'id',
	striped:true,
	pagination:true,
	nowrap:false,
	pageNumber:1,
	pageSize : 50,
	pageList : [ 50, 100, 150, 200, 250 ],
	scrollbarSize:5,
	singleSelect:true,
	striped:true,
    columns:[[    
        {field:'index',title:' ',align: 'center',
  			formatter:function(val,row,index){
  				var options = dg.datagrid('getPager').data("pagination").options; 
  			    var currentPage = options.pageNumber;
  			    var pageSize = options.pageSize;
  			    return value=(pageSize * (currentPage -1))+(index+1);
  			}
  		},   
  		{field :'qyname',title : '所属企业',sortable : false,width : 50,align : 'center'},
        {field:'taskname',title:'任务名称',sortable:false,width:60,align:'center'},
        {field:'createtime',title:'检查日期',sortable:false,width:60,align:'center',
      	  formatter : function(value, row, index) {
            	if(value!='') {
            		var datetime=new Date(value);
            		return datetime.format("yyyy-MM-dd");
            	}
        	} 
        },
        {field:'m13',title:'责任部门',sortable:false,width:60,align:'center'},
        {field:'m14',title:'发生区域',sortable:false,width:60,align:'center'},
        {field:'m1',title:'问题描述',sortable:false,width:70,align:'center'},
        {field:'m3',title:'隐患类别',sortable:false,width:40,align:'center'},
        {field:'m15',title:'解决措施',sortable:false,width:70,align:'center'},
        {field : 'm2',title : '现场照片',sortable : false,width : 40,align : 'center',
			formatter : function(value, row, index) {
				var content="";
              	if(value!=null&&value!='') {
              		var arr1=value.split("||");
                	for (var i = 0; i < arr1.length-1; i++) {
                		content=content+ '<img onclick=openImgView("'+arr1[i]+'") src='+arr1[i]+' style=margin-right:5px;max-width:100px;max-height:50px; />';
                	} 
                	return content;
              	}else{
              		return '/';
              	}
            }
		},
        {field:'m4',title:'计划完成时间',sortable:false,width:80,align:'center',
        	  formatter : function(value, row, index) {
              	if(value!='') {
              		var datetime=new Date(value);
              		return datetime.format("yyyy-MM-dd");
              	}
          	} 
        },
        {field:'m5',title:'实际完成时间',sortable:false,width:80,align:'center',
      	  formatter : function(value, row, index) {
            	if(value!=null&&value!='') {
            		var datetime=new Date(value);
            		return datetime.format("yyyy-MM-dd");
            	}
        	} 
        },
        {field:'zgr',title:'整改人',sortable:false,width:40,align:'center'},
		{field : 'm6',title : '整改照片',sortable : false,width : 40,align : 'center',
			formatter : function(value, row, index) {
				var content="";
              	if(value!=null&&value!='') {
              		var arr1=value.split("||");
                	for (var i = 0; i < arr1.length-1; i++) {
                		content=content+ '<img onclick=openImgView("'+arr1[i]+'") src='+arr1[i]+' style=margin-right:5px;max-width:100px;max-height:50px; />';
                	} 
                	return content;
              	}else{
              		return '/';
              	}
            }
		},
        {field:'m10',title:'整改费用',sortable:false,width:40,align:'center'},
        {field:'m7',title:'隐患等级',sortable:false,width:40,align:'center'},
        {field:'m11',title:'处理状态',sortable:false,width:40,align:'center',
			formatter : function(value, row, index) {
				var content="";
              	if(value=='0'&&row.m9==userid) {
              		return "<a style='margin:2px' class='btn btn-danger btn-xs' onclick='upd("+row.id+")' >未整改</a>"
              	}
              	if(value=='0'&&row.m9!=userid){
              		return "未整改"
              	}
              	if(value=='1'&&row.m9==userid){
              		return "<a style='margin:2px' class='btn btn-danger btn-xs' onclick='upd("+row.id+")'>整改未完成</a>";
              	}
              	if(value=='1'&&row.m9!=userid){
              		return "整改未完成";
              	}
              	if(value=='2'&&row.m12!=userid) {
              		return "<a  class='btn btn-success btn-xs' onclick='view("+row.id+")' >整改完成</a>"
              	}
              	if(value=='2'&&row.m12==userid) {
              		return "<a  class='btn btn-success btn-xs' onclick='review("+row.id+")' >发现人复查</a>"
              	}
				if(value=='3'&&row.m9==userid){
              		return "<a  class='btn btn-success btn-xs' onclick='upd("+row.id+")' >复查不通过</a>"
              	}
				if(value=='3'&&row.m9!=userid){
              		return "<a  class='btn btn-success btn-xs' >复查不通过</a>"
              	}
              	if(value=='4'){
              		return "<a  class='btn btn-success btn-xs' onclick='view("+row.id+")' >复查通过</a>"
              	}
			}
        }
    ]],
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
        myview();
    },
    onLoadSuccess:function(data){
    	if(type == '1'){
			$(this).datagrid("hideColumn", [ 'qyname' ]);
		}else{
			$(this).datagrid("showColumn", [ 'qyname' ]);
			/*$(this).datagrid("autoMergeCells", [ 'qyname' ]);*/
		}
	},
	checkOnSelect:false,
	selectOnCheck:false,
    toolbar:'#yhpc_jcyh_tb'
	});
	
});

//创建查询对象并查询
function search() {
	var obj = $("#searchFrom").serializeObject();
	dg.datagrid('load', obj);
}

//重置
function reset() {
	$("#searchFrom").form("clear");
	var obj = $("#searchFrom").serializeObject();
	dg.datagrid('load', obj);
}

//查看
function myview(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	openDialogView("查看隐患整改信息",ctx+"/yhpc/jcyh/view/"+row.id,"900px", "90%","");
}

//查看2
function view2(id){
	openDialogView("查看隐患整改信息",ctx+"/yhpc/jcyh/view/"+id,"900px", "90%","");
}


//删除
function del(){
	var row = dg2.datagrid('getChecked');
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
			url : ctx + "/yhpc/jcyh/delete/" + ids,
			success: function(data){
				layer.alert(data, {offset: 'rb',shade: 0,time: 2000}); 
				top.layer.close(index);
				dg.datagrid('reload');
				dg.datagrid('clearChecked');
				dg.datagrid('clearSelections');
				dg2.datagrid('reload');
				dg2.datagrid('clearChecked');
				dg2.datagrid('clearSelections');
			}
		});
	});
 
}
















