var dg;
var d;
var type;//操作类型
var userid;//用户id
$(function(){
	dg=$('#zyaqgl_dhzy_dg').datagrid({    
	method: "post",
    url:ctx+'/zyaqgl/dhzy/list?qyid='+qyid,
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
              {field:'ID',title:'id',checkbox:true,align:'center'}, 
              {field:'qyname',title:'企业名称',width:60},  
              {field:'m1',title:'作业证编号',width:50,align:'center'},  
              {field:'sqr',title:'申请人',width:40,align:'center'},
			  {field:'m3',title:'动火作业级别',sortable:true,width:50,align:'center'},
              {field:'s1',title:'申请时间',sortable:true,width:50,align:'center',
            	    formatter : function(value, row, index) {
                  	if(value!==null&&value!='') {
                  		var datetime=new Date(value);
                  		 return datetime.format('yyyy-MM-dd hh:mm:ss');  
                  	}
              	} 
              },
            {field:'m29',title:'承包商名称',width:40,align:'center',
                formatter: function (value,row) {
                    if (value) {
                        return "<span style='color: #1c84c6;cursor: pointer;' onclick='viewXgfdw("+row.id3+")'>"+value+"</span>";
                    }
                }
            },
              {field:'zt',title:'状态',sortable:true,width:50,align:'center',
            	  formatter : function(value, row, index){
            		  if(value==0){
            			  return '已申请，等待分配任务';
            		  }else if(value==1){
            			  return '已分配任务，等待分析';
            		  }else if(value==2){
            			  return '已分析，等待编制安全措施';
            		  }else if(value==3){
            			  return '已编制安全措施，等待确认';
            		  }else if(value==4){
            			  return '已确认安全措施，等待生产单位确认';
            		  }else if(value==5){
            			  return '生产单位已确认，等待监火人确认';
            		  }else if(value==6){
            			  return '监火人已确认，等待动火初审人确认';
            		  }else if(value==7){
            			  return '动火初审人已确认，等待实施安全教育';
            		  }else if(value==8){
            			  return '已实施安全教育 ，等待申请单位确认';
            		  }else if(value==9){
            			  return '申请单位已确认，等待安全部门确认';
            		  }else if(value==10){
            			  return '安全部门已确认，等待动火审批人审批';
            		  }else if(value==11){
            			  return '动火审批人已审批，等待验收';
            		  }else if(value==12){
            			  return '已验收';
            		  }else if(value==13){
            			  return '已关闭';
            		  }
            	  }
              },
              {field:'m7',title:'操作',width:50,align:'center',
            	  formatter : function(value, row, index){
	            		  if(row.zt==0&&fp==1){//分配任务
	            			  return "<a style='margin:2px' class='btn btn-success btn-xs' onclick='addfp("+row.id+")'>分配任务</a>";
	            		  }else if(row.zt == 1 && fx == 1){
	            			  return "<a style='margin:3px;' class='btn btn-success btn-xs' onclick='addfx("+row.id+")'>分析</a>";
	            	      }else if(row.zt==2 && (row.m15==userid)){
	            			  return "<a style='margin:2px' class='btn btn-success btn-xs' onclick='addbzcs("+row.id+")'>编制安全措施</a>";
	            		  }else if(row.zt==3 && (row.m16==userid)){
	            			  return "<a style='margin:2px' class='btn btn-success btn-xs' onclick='addqrcs("+row.id+")'>确认安全措施</a>";
	            		  }else if(row.zt==4 && (row.m13==userid)){
	            			  return "<a style='margin:2px' class='btn btn-success btn-xs' onclick='addscdw("+row.id+")'>生产单位确认</a>";
	            		  }else if(row.zt==5 && (row.m17==userid)){
	            			  return "<a style='margin:2px' class='btn btn-success btn-xs' onclick='addjhr("+row.id+")'>监火人确认</a>";
	            		  }else if(row.zt==6 && (row.m19==userid)){
	            			  return "<a style='margin:2px' class='btn btn-success btn-xs' onclick='addcsr("+row.id+")'>动火初审人确认</a>";
	            		  }else if(row.zt==7 && (row.m21==userid)){
	            			  return "<a style='margin:2px' class='btn btn-success btn-xs' onclick='addaqjy("+row.id+")'>实施安全教育</a>";
	            		  }else if(row.zt==8 && (row.m22==userid)){
	            			  return "<a style='margin:2px' class='btn btn-success btn-xs' onclick='addsqdw("+row.id+")'>申请单位确认</a>";
	            		  }else if(row.zt==9 && (row.m23==userid)){
	            			  return "<a style='margin:2px' class='btn btn-success btn-xs' onclick='addaqbm("+row.id+")'>安全部门确认</a>";
	            		  }else if(row.zt==10 && (row.m24==userid)){
	            			  return "<a style='margin:2px' class='btn btn-success btn-xs' onclick='addspr("+row.id+")'>动火审批人审批</a>";
	            		  }else if(row.zt==11 && (row.m25==userid)){
	            			  return "<a style='margin:2px' class='btn btn-success btn-xs' onclick='addys("+row.id+")'>验收</a>";
	            		  }else{
	            			  return "";
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
	  	  if(type=="1"){
	  		  $(this).datagrid("hideColumn",['qyname']);
	  	  }else{
	  		  $(this).datagrid("showColumn",['qyname']);
	  		  $(this).datagrid("autoMergeCells",['qyname']);
	  	  }
	},
    toolbar:'#zyaqgl_dhzy_tb'
	});
});

//查看状态
function viewzt(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	openDialogView("动火作业证状态页面",ctx+"/zyaqgl/dhzy/viewzt/"+row.id,"800px", "400px","");
}

//分配任务
function addfp(id){
	openDialog("动火作业分配任务信息",ctx+"/zyaqgl/dhzy/fprw/"+id,"90%", "85%","");
}



//分析
function addfx(id){
	openDialog("动火作业分析",ctx+"/zyaqgl/dhzy/fx/"+id,"800px", "400px","");
}

//编制安全措施
function addbzcs(id) {
	openDialog("编制安全措施",ctx+"/zyaqgl/dhzy/aqcsindex?id1="+id+"&type=0","800px", "400px","");
}

//确认安全措施
function addqrcs(id) {
	openDialog("确认安全措施",ctx+"/zyaqgl/dhzy/aqcsindex?id1="+id+"&type=1","800px", "400px","");
}

//生产单位确认
function addscdw(id) {
	top.layer.confirm('是否确认通过？', {icon: 3, title:'提示'}, function(index){
		$.ajax({
			type:'get',
			url:ctx+"/zyaqgl/dhzy/scdwqr/"+id,
			success: function(data){
				layer.alert(data, {offset: 'rb',shade: 0,time: 2000}); 
				top.layer.close(index);
				dg.datagrid('reload');
			}
		});
	});
}

//监火人确认
function addjhr(id) {
	top.layer.confirm('是否确认通过？', {icon: 3, title:'提示'}, function(index){
		$.ajax({
			type:'get',
			url:ctx+"/zyaqgl/dhzy/jhrqr/"+id,
			success: function(data){
				layer.alert(data, {offset: 'rb',shade: 0,time: 2000}); 
				top.layer.close(index);
				dg.datagrid('reload');
			}
		});
	});
}

//动火初审人确认
function addcsr(id) {
	top.layer.confirm('是否确认通过？', {icon: 3, title:'提示'}, function(index){
		$.ajax({
			type:'get',
			url:ctx+"/zyaqgl/dhzy/csrqr/"+id,
			success: function(data){
				layer.alert(data, {offset: 'rb',shade: 0,time: 2000}); 
				top.layer.close(index);
				dg.datagrid('reload');
			}
		});
	});
}

//实施安全教育
function addaqjy(id) {
	top.layer.confirm('确认实施安全教育？', {icon: 3, title:'提示'}, function(index){
		$.ajax({
			type:'get',
			url:ctx+"/zyaqgl/dhzy/aqjy/"+id,
			success: function(data){
				layer.alert(data, {offset: 'rb',shade: 0,time: 2000}); 
				top.layer.close(index);
				dg.datagrid('reload');
			}
		});
	});
}

//申请单位确认
function addsqdw(id) {
	openDialog("申请单位确认页面",ctx+"/zyaqgl/dhzy/dwqr/"+id,"800px", "200px","");
}

//安全部门确认
function addaqbm(id) {
	openDialog("安全部门确认页面",ctx+"/zyaqgl/dhzy/aqbm/"+id,"800px", "200px","");
}

//动火审批人审批
function addspr(id) {
	openDialog("动火审批人审批页面",ctx+"/zyaqgl/dhzy/spr/"+id,"800px", "200px","");
}

//验收
function addys(id) {
	top.layer.confirm('确认验收？', {icon: 3, title:'提示'}, function(index){
		$.ajax({
			type:'get',
			url:ctx+"/zyaqgl/dhzy/ys/"+id,
			success: function(data){
				layer.alert(data, {offset: 'rb',shade: 0,time: 2000}); 
				top.layer.close(index);
				dg.datagrid('reload');
			}
		});
	});
}

//添加动火作业信息
function add(u) {
	openDialog("申请动火作业",ctx+"/zyaqgl/dhzy/create/","90%", "85%","");
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
		if( '9'!= usertype && row[i].id1!=qyid) { //如果是admin账号登录，可直接删除
			layer.msg("无法对下属企业数据进行操作！",{time: 3000});
			return;
		}
		if(ids==""){
			ids=row[i].id;
		}else{
			ids=ids+","+row[i].id;
		}
	}

	top.layer.confirm('删除后无法恢复您确定要删除？', {icon: 3, title:'提示'}, function(index){
		$.ajax({
			type:'get',
			url:ctx+"/zyaqgl/dhzy/delete/"+ids,
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
	if(row.id1!=qyid) {
		layer.msg("无法对下属企业数据进行操作！",{time: 3000});
		return;
	}
	
	openDialog("修改动火作业信息",ctx+"/zyaqgl/dhzy/update/"+row.id,"90%", "85%","");
	
}

//关闭
function gb(){
	var row = dg.datagrid('getSelected');
	if(row.id1!=qyid) {
		layer.msg("无法对下属企业数据进行操作！",{time: 3000});
		return;
	}
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}else if(row.zt == '9'){
		layer.msg("该动火作业证已关闭！",{time: 1000});
		return;
	}else{
		top.layer.confirm('确认关闭？', {icon: 3, title:'提示'}, function(index){
			$.ajax({
				type:'get',
				url:ctx+"/zyaqgl/dhzy/gb/"+row.id,
				success: function(data){
					layer.alert(data, {offset: 'rb',shade: 0,time: 2000}); 
					top.layer.close(index);
					dg.datagrid('reload');
				}
			});
		});
	}
}

//查看
function view(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	
	openDialogView("查看动火作业信息",ctx+"/zyaqgl/dhzy/view/"+row.id,"100%", "100%","");
	
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
	               	{colval:'qyname', coltext:'企业名称'},
			   		{colval:'m1', coltext:'作业证编号'},
			   		{colval:'sqr', coltext:'申请人'},
			   		{colval:'s1', coltext:'申请时间'},
			   		{colval:'m2', coltext:'申请单位'},
			   		{colval:'m3', coltext:'动火作业级别'},
			   		{colval:'m4', coltext:'动火方式'},
			   		{colval:'m5', coltext:'动火地点'},
			   		{colval:'m6', coltext:'动火时间起'},
			   		{colval:'m7', coltext:'动火时间止'},
			   		{colval:'m8', coltext:'动火作业负责人'},
			   		{colval:'m9', coltext:'动火人'},
			   		{colval:'m10', coltext:'涉及的其他特殊作业'},
			   		{colval:'m11', coltext:'危害辨识'},
			   		{colval:'fxr', coltext:'分析人'},
			   		{colval:'bzcsr', coltext:'安全措施编制人'},
			   		{colval:'sqdwfzr', coltext:'生产部门负责人'},
			   		{colval:'jhr', coltext:'监护人'},
			   		{colval:'dhcsr', coltext:'作业区域负责人'},
			   		{colval:'aqjyr', coltext:'实施安全教育人'},
			   		{colval:'m22_1', coltext:'申请单位意见'},
			   		{colval:'sqdwfzr', coltext:'申请单位负责人'},
			   		{colval:'m22_2', coltext:'确认时间'},
			   		{colval:'m23_1', coltext:'安全管理部门意见'},
			   		{colval:'aqglr', coltext:'安全管理部门负责人'},
			   		{colval:'m23_2', coltext:'确认时间'},
			   		{colval:'m24_1', coltext:'动火审批人意见'},
			   		{colval:'dhspr', coltext:'动火审批人'},
			   		{colval:'m24_2', coltext:'审批时间'},
			   		{colval:'ysr', coltext:'验收人'},
			   		{colval:'m25_1', coltext:'验收时间'}
			   	  ];
	layer.open({
	    type: 2,  
	    area: ['350px', '350px'],
	    title: '导出列选择',
        maxmin: true, 
        shift: 1,
	    content: ctx+'/zyaqgl/dhzy/colindex',
		btn: ['导出'],
	    yes: function(index, layero){
	    	 var body = layer.getChildFrame('body', index);
	         var iframeWin = layero.find('iframe')[0];
	         var inputForm = body.find('#excel_mainform');
	         iframeWin.contentWindow.doExport();
		  },
	});
}

//导出word
function fileexportword(){
	var row = dg.datagrid('getSelected');
	if(row==null){
		layer.msg('请选择一行记录',{time: 1000});
		return;
	}
	
	$.ajax({
		url:ctx+"/zyaqgl/dhzy/exportword/"+row.id,
		type:"POST",
		success:function(data){
			window.open(ctx+data);
		}
	});
}


function viewXgfdw(id){
	openDialogView("查看承包商信息",ctx+"/zyaqgl/xgdw/view/"+id,"900px", "500px","");
}