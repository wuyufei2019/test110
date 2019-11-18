var dg;
var d;
var type;//操作类型
var userid;//用户id
$(function(){
	dg=$('#zyaqgl_lsyd_dg').datagrid({    
	method: "post",
    url:ctx+'/ztzyaqgl/lsyd/list?spzt='+spzt,
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
              {field:'depname',title:'所属部门',width:50},  
              {field:'m2',title:'申请单位',width:50,align:'center'},  
              {field:'m3',title:'申请人',width:50,align:'center'},    
              {field:'m1',title:'作业证编号',width:70,align:'center'},
              {field:'m10',title:'作业等级',width:50,align:'center'},
              {field:'m11',title:'作业人',width:80,align:'center'},
              {field:'m13',title:'安全监护人',width:50,align:'center'},
              {field:'s1',title:'作业时间',width:100,align:'center',
          	    formatter : function(value, row, index) {
                	if(value!==null&&value!='') {
                		var datetime1=new Date(row.m4);
                		var datetime2=new Date(row.m5);
                		var datetime=datetime1.format('yyyy-MM-dd hh:mm')+"~"+datetime2.format('yyyy-MM-dd hh:mm')
                		return datetime;  
                	}
            	} 
              },
              {field:'zt',title:'状态',width:50,align:'center',
            	  formatter : function(value, row, index){
            		  var a="";
            		  if(value==0){
            			  if(row.m22_1==userid){
            				  a="<a style='margin:2px' class='btn btn-success btn-xs' onclick='addbzcs("+row.id+")'>编制措施</a>"; 
            			  }else{
            				  a="待编制措施";
            			  }
            		  }else if(value==1){
            			  if(row.m22_1==userid){
            				  a="<a style='margin:2px' class='btn btn-success btn-xs' onclick='zydwqr("+row.id+")'>作业单位确认</a>"; 
            				  a=a+"<a style='margin:2px' class='btn btn-success btn-xs' onclick='updaqcs("+row.id+")'>补充安全措施</a>"; 
            			  }else{
            				  a="待作业单位确认";
            			  }
            		  }else if(value==2){
            			  if(role0==1){
            				  a="<a style='margin:2px' class='btn btn-success btn-xs' onclick='ajysp("+row.id+")'>安技员审批</a>"; 
            				  a=a+"<a style='margin:2px' class='btn btn-success btn-xs' onclick='updaqcs("+row.id+")'>补充安全措施</a>"; 
            			  }else{
            				  a="待安技员审批";
            			  }
            		  }else if(value==3){
            			  if(role1==1){
            				  a="<a style='margin:2px' class='btn btn-success btn-xs' onclick='aqkzsp("+row.id+")'>分厂安全分管领导审批</a>"; 
            				  a=a+"<a style='margin:2px' class='btn btn-success btn-xs' onclick='updaqcs("+row.id+")'>补充安全措施</a>"; 
            			  }else{
            				  a="待分厂安全分管领导审批";
            			  }
            		  }else if(value==4){
            			  if(role2==1){
            				  a="<a style='margin:2px' class='btn btn-success btn-xs' onclick='txldsp("+row.id+")'>条线分管领导审批</a>"; 
            				  a=a+"<a style='margin:2px' class='btn btn-success btn-xs' onclick='updaqcs("+row.id+")'>补充安全措施</a>"; 
            			  }else{
            				  a="待条线分管领导审批";
            			  }
            		  }else if(value==5){
            			  if(role3==1){
            				  a="<a style='margin:2px' class='btn btn-success btn-xs' onclick='nyzxsp("+row.id+")'>能源管控中心审批</a>"; 
            				  a=a+"<a style='margin:2px' class='btn btn-success btn-xs' onclick='updaqcs("+row.id+")'>补充安全措施</a>"; 
            			  }else{
            				  a="待能源管控中心审批";
            			  }
            		  }else if(value==6){
            			  if(role4==1){
            				  a="<a style='margin:2px' class='btn btn-success btn-xs' onclick='qrcs("+row.id+")'>确认安全措施</a><br/>";
            			  }else{
							  a="待确认安全措施<br/>";
						  }
            			  if(role5==1||role6==1){
            				  a+="<a style='margin:2px' class='btn btn-success btn-xs' onclick='cancel("+row.id+")'>作废</a>";
            			  }
            		  }else if(value==7){
            			  if(role0==1||role1==1||row.m22_1==userid){
            				  a="<a style='margin:2px' class='btn btn-success btn-xs' onclick='dwqz("+row.id+")'>作业单位完工签字</a><br/>";
            			  }else{
							  a="待作业单位完工签字<br/>";
						  }
            			  if(role5==1||role6==1){
            				  a+="<a style='margin:2px' class='btn btn-success btn-xs' onclick='cancel("+row.id+")'>作废</a>";
            			  }
            		  }else if(value==8){
            			  if(role0==1||role1==1||row.m22_1==userid){
            				  a="<a style='margin:2px' class='btn btn-success btn-xs' onclick='fcqz("+row.id+")'>分厂完工签字</a><br/>";
            			  }else{
							  a="待分厂完工签字<br/>";
						  }
            			  if(role5==1||role6==1){
            				  a+="<a style='margin:2px' class='btn btn-success btn-xs' onclick='cancel("+row.id+")'>作废</a>";
            			  }
            		  }else if(value==9){
            			  if(role3==1){
            				  a="<a style='margin:2px' class='btn btn-success btn-xs' onclick='nyzxqz("+row.id+")'>能源中心完工签字</a><br/>";
            			  }else{
							  a="待能源中心完工签字<br/>";
						  }
            			  if(role5==1||role6==1){
            				  a+="<a style='margin:2px' class='btn btn-success btn-xs' onclick='cancel("+row.id+")'>作废</a>";
            			  }
            		  }else if(value==10){
            			  return '已完工';
            		  }else if(value==(-1)){
            			  if(row.m22_1==userid){
            				  a="<a style='margin:2px' class='btn btn-success btn-xs' onclick='upd("+row.id+")'>驳回修改</a>"; 
            			  }else{
            				  a="审批驳回待修改";
            			  }
            		  }else if(value==(-2)){
            			  a="已作废";
            		  }
            		  //判断是否过期
					  if(row.m22_7!=null&&row.m22_7!='') {
						  var hour = compare(row.m22_7);
						  if (hour > 15 * 24 && row.zt != 10) {
							  a = "已过期";
						  }
					  }
            		  return a;
            	  }
              }
    ]],
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
                 view();
    },
	checkOnSelect:false,
	selectOnCheck:false,
	onLoadSuccess:function(){
	},
    toolbar:'#zyaqgl_lsyd_tb'
	});
});

//判断日期间隔天数
function compare(m4){
	var sdate = new Date(m4); 
	var now = new Date(); 
	var days = now.getTime() - sdate.getTime(); 
	var hour = parseInt(days / (1000 * 60 * 60)); 
	return hour;
}

//添加临时用电作业信息
function add(u) {
	openDialog("申请临时用电作业",ctx+"/ztzyaqgl/lsyd/create/","900px", "90%","");
}

//编制安全措施
function addbzcs(id) {
	openDialog("编制安全措施",ctx+"/ztzyaqgl/lsyd/aqcsindex?id1="+id,"800px", "600px","");
}

//补充安全措施
function updaqcs(id) {
	openDialog("编制安全措施",ctx+"/ztzyaqgl/lsyd/updaqcsindex?id1="+id,"800px", "600px","");
}

//作业单位确认
function zydwqr(id) {
	openDialog("作业单位确认",ctx+"/ztzyaqgl/lsyd/zydwqr/"+id,"800px", "300px","");
}

//能源中心确认
function nyzxqr(id) {
	openDialog("作业单位确认",ctx+"/ztzyaqgl/lsyd/nyzxqr/"+id,"800px", "300px","");
}

//安技员审批
function ajysp(id) {
	openDialog("安技员审批",ctx+"/ztzyaqgl/lsyd/nbsp/"+id,"800px", "300px","");
}

//分厂安全分管领导审批
function aqkzsp(id) {
	openDialog("分厂安全分管领导审批",ctx+"/ztzyaqgl/lsyd/nbsp/"+id,"800px", "300px","");
}

//条线分管领导审批
function txldsp(id) {
	openDialog("条线分管领导审批",ctx+"/ztzyaqgl/lsyd/nbsp/"+id,"800px", "300px","");
}

//能源中心审批
function nyzxsp(id) {
	openDialog("能源中心审批",ctx+"/ztzyaqgl/lsyd/nbsp/"+id,"800px", "300px","");
}

//确认安全措施
function qrcs(id) {
	openDialog("确认安全措施",ctx+"/ztzyaqgl/lsyd/qraqcsindex?id1="+id+"&type=1","800px", "400px","");
}

//作业完工签字
function dwqz(id) {
	openDialog("作业单位完工签字",ctx+"/ztzyaqgl/lsyd/wgqzindex?id1="+id,"800px", "400px","");
}

//分厂完工签字
function fcqz(id) {
	openDialog("分厂完工签字",ctx+"/ztzyaqgl/lsyd/wgqzindex?id1="+id,"800px", "400px","");
}

//能源中心完工签字
function nyzxqz(id) {
	openDialog("能源中心完工签字",ctx+"/ztzyaqgl/lsyd/wgqzindex?id1="+id,"800px", "300px","");
}

//查看状态
function viewzt(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	openDialogView("临时用电作业证状态页面",ctx+"/ztzyaqgl/lsyd/viewzt/"+row.id,"800px", "400px","");
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
			url:ctx+"/ztzyaqgl/lsyd/delete/"+ids,
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
function upd(id){
	openDialog("修改临时用电作业信息",ctx+"/ztzyaqgl/lsyd/update/"+id,"90%", "85%","");
	
}

//编制人撤回
function revoke(id){
	top.layer.confirm('确认撤回？', {icon: 3, title:'提示'}, function(index){
		$.ajax({
			type:'get',
			url:ctx+"/ztzyaqgl/lsyd/revoke/"+id,
			success: function(data){
				layer.alert(data, {offset: 'rb',shade: 0,time: 2000}); 
				top.layer.close(index);
				dg.datagrid('reload');
			}
		});
	});
	
}

//安全处作废
function cancel(id){
	top.layer.confirm('确认作废该申请？', {icon: 3, title:'提示'}, function(index){
		$.ajax({
			type:'get',
			url:ctx+"/ztzyaqgl/lsyd/cancel/"+id,
			success: function(data){
				layer.alert(data, {offset: 'rb',shade: 0,time: 2000}); 
				top.layer.close(index);
				dg.datagrid('reload');
			}
		});
	});
	
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
		layer.msg("该临时用电作业证已关闭！",{time: 1000});
		return;
	}else{
		top.layer.confirm('确认关闭？', {icon: 3, title:'提示'}, function(index){
			$.ajax({
				type:'get',
				url:ctx+"/ztzyaqgl/lsyd/gb/"+row.id,
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
	console.log(row.id)
	
	openDialogView("查看临时用电作业信息",ctx+"/ztzyaqgl/lsyd/view/"+row.id,"900px", "100%","");
	
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
			   		{colval:'m3', coltext:'临时用电作业级别'},
			   		{colval:'m4', coltext:'临时用电方式'},
			   		{colval:'m5', coltext:'临时用电地点'},
			   		{colval:'m6', coltext:'临时用电时间起'},
			   		{colval:'m7', coltext:'临时用电时间止'},
			   		{colval:'m8', coltext:'临时用电作业负责人'},
			   		{colval:'m9', coltext:'临时用电人'},
			   		{colval:'m10', coltext:'涉及的其他特殊作业'},
			   		{colval:'m11', coltext:'危害辨识'},
			   		{colval:'fxr', coltext:'分析人'},
			   		{colval:'bzcsr', coltext:'安全措施编制人'},
			   		{colval:'sqdwfzr', coltext:'生产单位负责人'},
			   		{colval:'jhr', coltext:'监火人'},
			   		{colval:'dhcsr', coltext:'临时用电初审人'},
			   		{colval:'aqjyr', coltext:'实施安全教育人'},
			   		{colval:'m22_1', coltext:'申请单位意见'},
			   		{colval:'sqdwfzr', coltext:'申请单位负责人'},
			   		{colval:'m22_2', coltext:'确认时间'},
			   		{colval:'m23_1', coltext:'安全管理部门意见'},
			   		{colval:'aqglr', coltext:'安全管理部门负责人'},
			   		{colval:'m23_2', coltext:'确认时间'},
			   		{colval:'m24_1', coltext:'临时用电审批人意见'},
			   		{colval:'dhspr', coltext:'临时用电审批人'},
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
	    content: ctx+'/ztzyaqgl/lsyd/colindex',
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
		url:ctx+"/ztzyaqgl/lsyd/exportword/"+row.id,
		type:"POST",
		success:function(data){
			window.open(ctx+data);
		}
	});
}
