var dg;
var d;
$(function(){
	dg=$('#zyaq_sxzy_dg').datagrid({    
	method: "post",
    url:ctx+'/zyaqgl/sxzy/list?qyid='+qyid,
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
        {field:'qyname',title:'企业名称',width:60},  
        {field:'m0',title:'作业证编号',sortable:false,width:50,align:'center'},
        {field:'m1',title:'申请单位',sortable:false,width:70,align:'center'},
        {field:'sqr',title:'申请人',sortable:false,width:30,align:'center'},
        {field:'m3',title:'受限空间',sortable:false,width:50,align:'center'},
        {field:'m4',title:'作业内容',sortable:false,width:100,align:'center'},
        {field:'s1',title:'申请时间',sortable:true,width:50,align:'center',
      	    formatter : function(value, row, index) {
            	if(value!==null&&value!='') {
            		var datetime=new Date(value);
            		 return datetime.format('yyyy-MM-dd hh:mm:ss');  
            	}
        	} 
        },
        {field:'zt',title:'状态',sortable:true,width:40,align:'center',
        	formatter : function(value, row, index) {
        		if(value=='0') {
	          		return '已申请';  
	          	}else if(value=='1') {
	          		return '已分配任务';  
	          	}else if(value=='2'){
	          		return '已分析';  
	          	}else if(value=='3'){
	          		return '已编制安全措施';  
	          	}else if(value=='4'){
	          		return '已确认安全措施';  
	          	}else if(value=='5'){
	          		return '已实施安全教育';  
	          	}else if(value=='6'){
	          		return '申请单位确认';  
	          	}else if(value=='7'){
	          		return '已审批';  
	          	}else if(value=='8'){
	          		return '已验收';  
	          	}else if(value=='9'){
	          		return '已关闭';  
	          	}
          	} 
        },
        {field:'cz',title:'操作',sortable:false,width:50,align:'center',
      	    formatter : function(value, row, index) {
      	    	var a = "";
      	    	if(row.zt == '0' && fp =='1'){
      	    		a = "<a style='margin:3px;' class='btn btn-info btn-xs' onclick='addfp("+row.id+")'>分配任务</a>";
      	    	}
      	    	if(row.zt == '1' && fx == '1'){
      	    		a = "<a style='margin:3px;' class='btn btn-info btn-xs' onclick='addfx("+row.id+")'>分析</a>";
      	    	}
      	    	if(row.zt == '2' && userid == row.m15){
      	    		a = "<a style='margin:3px;' class='btn btn-info btn-xs' onclick='addbzcs("+row.id+")'>编制安全措施</a>";
      	    	}
      	    	if(row.zt == '3' && userid == row.m16){
      	    		a = "<a style='margin:3px;' class='btn btn-info btn-xs' onclick='addqrcs("+row.id+")'>确认安全措施</a>";
      	    	}
      	    	if(row.zt == '4' && userid == row.m13){
      	    		a = "<a style='margin:3px;' class='btn btn-info btn-xs' onclick='addssjy("+row.id+")'>实施安全教育</a>";
      	    	}
      	    	if(row.zt == '5' && userid == row.m17){
      	    		a = "<a style='margin:3px;' class='btn btn-info btn-xs' onclick='adddwqr("+row.id+")'>申请单位确认</a>";
      	    	}
      	    	if(row.zt == '6' && userid == row.m18){
      	    		a = "<a style='margin:3px;' class='btn btn-info btn-xs' onclick='addsp("+row.id+")'>审批</a>";
      	    	}
      	    	if(row.zt == '7' && userid == row.m19){
      	    		a = "<a style='margin:3px;' class='btn btn-info btn-xs' onclick='addys("+row.id+")'>验收</a>";
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
	  	  if(type=="1"){
	  		  $(this).datagrid("hideColumn",['qyname']);
	  	  }else{
	  		  $(this).datagrid("showColumn",['qyname']);
	  		  $(this).datagrid("autoMergeCells",['qyname']);
	  		  $(this).datagrid("hideColumn",['cz']);
	  	  }	  
	},
    toolbar:'#zyaq_sxzy_tb'
	});
});

//创建查询对象并查询
function search(){
	var obj=$("#searchFrom").serializeObject();
	dg.datagrid('load',obj); 
}

function reset(){
	$("#searchFrom").form("clear");
	search();
}
//提交审批
function flow(){
    var row = dg.datagrid('getSelected');
    if(row==null) {
        layer.msg("请选择一行记录！",{time: 1000});
        return;
    }
    if (businesstype == '') {
        layer.msg("无可用流程！",{time: 1000});
        return;
    }
    if (row.status == 0 ) {
        openDialog("提交审批",ctx+"/BusinessAndProcess/approve/"+businesstype+"/"+row.id,"800px", "400px","");
    }else {
        layer.msg("该记录已在审批中，无法提交审批！",{time: 1000});
        return;
    }



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
		if(row[i].id1!=qyid) {
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
			url:ctx+"/zyaqgl/sxzy/delete/"+ids,
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

//弹窗增加
function add() {
	openDialog("申请受限空间作业",ctx+"/zyaqgl/sxzy/create","90%", "85%","");
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
	openDialog("修改受限空间作业信息",ctx+"/zyaqgl/sxzy/update/"+row.id,"90%", "85%","");
}

//查看状态
function viewzt(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	openDialogView("查看受限空间作业证状态",ctx+"/zyaqgl/sxzy/viewzt/"+row.id,"800px", "400px","");
}

//分配任务
function addfp(id){
	openDialog("受限空间作业分配任务信息",ctx+"/zyaqgl/sxzy/fprw/"+id,"800px", "400px","");
}

//分析
function addfx(id){
	openDialog("受限空间作业分析",ctx+"/zyaqgl/sxzy/fx/"+id,"800px", "400px","");
}

//编制安全措施
function addbzcs(id){
	openDialog("编制安全措施",ctx+"/zyaqgl/sxzy/aqcsindex?id1="+id+"&type=0","800px", "400px","");
}

//确认安全措施
function addqrcs(id){
	openDialog("确认安全措施",ctx+"/zyaqgl/sxzy/aqcsindex?id1="+id+"&type=1","800px", "400px","");
}

//实施安全教育
function addssjy(id){
	top.layer.confirm('确认实施安全教育？', {icon: 3, title:'提示'}, function(index){
		$.ajax({
			type:'get',
			url:ctx+"/zyaqgl/sxzy/ssjy/"+id,
			success: function(data){
				layer.alert(data, {offset: 'rb',shade: 0,time: 2000}); 
				top.layer.close(index);
				dg.datagrid('reload');
			}
		});
	});
}

//申请单位确认
function adddwqr(id){
	openDialog("申请单位确认",ctx+"/zyaqgl/sxzy/dwqr/"+id,"800px", "200px","");
}

//审批
function addsp(id){
	openDialog("审批",ctx+"/zyaqgl/sxzy/sp/"+id,"800px", "200px","");
}

//验收
function addys(id){
	top.layer.confirm('确认验收？', {icon: 3, title:'提示'}, function(index){
		$.ajax({
			type:'get',
			url:ctx+"/zyaqgl/sxzy/ys/"+id,
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
		layer.msg("该受限空间作业证已关闭！",{time: 1000});
		return;
	}else{
		top.layer.confirm('确认关闭？', {icon: 3, title:'提示'}, function(index){
			$.ajax({
				type:'get',
				url:ctx+"/zyaqgl/sxzy/gb/"+row.id,
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
	openDialogView("查看受限空间安全生产作业证信息",ctx+"/zyaqgl/sxzy/view/"+row.id,"100%", "100%","");
}

//导出
function fileexport(){
	window.expara=$("#searchFrom").serializeObject();
	window.exdata=[
	               	{colval:'qyname', coltext:'企业名称'},
			   		{colval:'m0', coltext:'作业证编号'},
			   		{colval:'sqr', coltext:'申请人'},
			   		{colval:'s1', coltext:'申请时间'},
			   		{colval:'m1', coltext:'申请单位'},
			   		{colval:'m2', coltext:'受限空间所属单位'},
			   		{colval:'m3', coltext:'受限空间名称'},
			   		{colval:'m4', coltext:'作业内容'},
			   		{colval:'m5', coltext:'空间内介质名称'},
			   		{colval:'m6', coltext:'作业时间起'},
			   		{colval:'m7', coltext:'作业时间止'},
			   		{colval:'zyfzr', coltext:'作业单位负责人'},
			   		{colval:'zyr', coltext:'作业人'},
			   		{colval:'jhr', coltext:'监护人'},
			   		{colval:'m20', coltext:'rfid'},
			   		{colval:'m8', coltext:'涉及的其他特殊作业'},
			   		{colval:'m9', coltext:'危害辨识'},
			   		{colval:'m29', coltext:'有毒有害介质标准'},
			   		{colval:'m30', coltext:'可燃气标准'},
			   		{colval:'m31', coltext:'氧含量标准'},
			   		{colval:'fxr', coltext:'分析人'},
			   		{colval:'bzcsr', coltext:'安全措施编制人'},
			   		{colval:'ssjyr', coltext:'实施安全教育人'},
			   		{colval:'m21', coltext:'申请单位意见'},
			   		{colval:'dwfzr', coltext:'申请单位负责人'},
			   		{colval:'m22', coltext:'确认时间'},
			   		{colval:'m23', coltext:'审批单位意见'},
			   		{colval:'spr', coltext:'审批单位负责人'},
			   		{colval:'m24', coltext:'审批时间'},
			   		{colval:'ysr', coltext:'验收人'},
			   		{colval:'m25', coltext:'验收时间'}
			   	  ];
	layer.open({
	    type: 2,  
	    area: ['350px', '350px'],
	    title: '导出列选择',
        maxmin: true, 
        shift: 1,
	    content: ctx+'/zyaqgl/sxzy/colindex',
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
		url:ctx+"/zyaqgl/sxzy/exportword/"+row.id,
		type:"POST",
		success:function(data){
			window.open(ctx+data);
		}
	});
}
