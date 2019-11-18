var dg;
var d;
var sh=0;
var sp=0;
$(function(){
	dg=$('#zyaqgl_xgfpdjh_dg').datagrid({    
	method: "post",
    url:ctx+'/zyaqgl/xgfpdjh/list?qyid='+qyid,
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
              {field:'m1',title:'评定日期',width:60,align:'center',
      	    	formatter : function(value, row, index) {
    	          	if(value!==null&&value!='') {
    	          		var datetime=new Date(value);
    	          		 return datetime.format('yyyy-MM-dd');  
    	          	}	
              	} 
              },  
              {field:'pddw',title:'评定单位',width:60,align:'center'},    
              {field:'m3',title:'评定主持人',width:70,align:'center'},
              {field:'m4',title:'总分',width:100,align:'center'},
              {field:'m5',title:'评定等级',width:60,align:'center'}, 
              {field:'zt',title:'状态',sortable:false,width:80,align:'center',
                	formatter : function(value, row, index) {
                		var a = '';
                		//无审核审批权限
                		if(sh==0&&sp==0){
                			if(row.m9 == '0'){
        	            		a +="<font style='margin:2px' >批准未通过</font>";
        	            	}else if(row.m9 == '1'){
        	            		a +="<font style='margin:2px' >批准通过</font>";
        	            	}else if(row.m6 == '0'){
        	            		a +="<font style='margin:2px' >审核未通过</font>";
        	            	}else if(row.m6 == '1'){
        	            		a +="<font style='margin:2px' >审核通过待批准</font>";
        	            	}else{
        	            		a +="<font style='margin:2px' >待审核</font>";
        	            	}
                		}else if(sh==1&&sp==0){//有审核权限无审批权限
                			if(row.m9 == '0'){
        	            		a +="<font style='margin:2px'>批准未通过</font>";
        	            	}else if(row.m9 == '1'){
        	            		a +="<font style='margin:2px'>批准通过</font>";
        	            	}else if(row.m6 == '0'){
        	            		a +="<a style='margin:2px' class='btn btn-danger btn-xs' onclick='addsh("+row.id+")'>审核未通过</a>";
        	            	}else if(row.m6 == '1'){
        	            		a +="<a style='margin:2px' class='btn btn-success btn-xs' onclick='addsh("+row.id+")'>审核通过待批准</a>";
        	            	}else{
        	            		a +="<a style='margin:2px' class='btn btn-warning btn-xs' onclick='addsh("+row.id+")'>待审核</a>";
        	            	}
                		}else if(sh==1&&sp==1){//有审核审批权限
                			if(row.m9 == '0'){
        	            		a +="<a style='margin:2px' class='btn btn-success btn-xs' onclick='addsp("+row.id+")'>批准未通过</a>";
        	            	}else if(row.m9 == '1'){
        	            		a +="<a style='margin:2px' class='btn btn-success btn-xs' onclick='addsp("+row.id+")'>批准通过</a>";
        	            	}else if(row.m6 == '0'){
        	            		a +="<a style='margin:2px' class='btn btn-danger btn-xs' onclick='addsh("+row.id+")'>审核未通过</a>";
        	            	}else if(row.m6 == '1'){
        	            		a +="<a style='margin:2px' class='btn btn-success btn-xs' onclick='addsh("+row.id+")'>审核通过</a> &nbsp <a style='margin:2px' class='btn btn-success btn-xs' onclick='addsp("+row.id+")'>待批准</a>";
        	            	}else{
        	            		a +="<a style='margin:2px' class='btn btn-warning btn-xs' onclick='addsh("+row.id+")'>待审核</a>";
        	            	}
                		}else if(sh==0&&sp==1){//无审核有审批权限
                			if(row.m9 == '0'){
        	            		a +="<a style='margin:2px' class='btn btn-danger btn-xs' onclick='addsp("+row.id+")'>批准未通过</a>";
        	            	}else if(row.m9 == '1'){
        	            		a +="<a style='margin:2px' class='btn btn-success btn-xs' onclick='addsp("+row.id+")'>批准通过</a>";
        	            	}else if(row.m6 == '0'){
        	            		a +="<font style='margin:2px'>审核未通过</font>";
        	            	}else if(row.m6 == '1'){
        	            		a +="<a style='margin:2px' class='btn btn-warning btn-xs' onclick='addsp("+row.id+")'>审核通过待批准</a>";
        	            	}else{
        	            		a +="<font style='margin:2px'>待审核</font>";
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
	  	  if(usertype=="1"){
			  $(this).datagrid("hideColumn",['qynm']);
		  }else{
			  $(this).datagrid("showColumn",['qynm']);
		  }
    	  $(this).datagrid("autoMergeCells",['qynm']);
      },
    toolbar:'#zyaqgl_xgfpdjh_tb'
	});
});

//弹窗增加
function add(u) {
	openDialog("添加相关方评定信息",ctx+"/zyaqgl/xgfpdjh/create/","800px", "400px","");
}

//向子页面发送pdryids
function  getpdryids(){
	return $("#pdryids").val();
}

//弹出评定人员选择框
function openpdrylist() {
	layer.open({
	    type: 2,  
	    area: ['700px', '300px'],
	    title: '选择评定人员',
        maxmin: false, 
	    content: ctx + "/zyaqgl/xgfpdjh/pdrychoose" ,
	    btn: ['确定', '关闭'],
	    yes: function(index, layero){
	    	var  $list= $("#pdryList");
	    	$list.html("");
	    	var iframeWin = layero.find('iframe')[0];
	    	var idname=iframeWin.contentWindow.getidname();
	        var arr1=idname.split(",");
	        var ids="";
			for (var i = 0; i < arr1.length-1; i++) {
				var arr2=arr1[i].split("||");
					// 评定人员名称拼接
					var $li = $("<div id=\""
							+ arr2[0]
							+ "\" style=\"margin-bottom: 10px;\">"
							+ "<a style=\"color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;\">"
							+ arr2[1]
							+ "</a>"
							+ "<span class=\"ss\" onClick=\"removepdry('"
							+ arr2[0]
							+ "')\" style=\"cursor: pointer\">删除</span>"
							+ "</div>");
					$list.append($li);
					ids = ids + arr2[0] + ",";
				}
			$("#pdryids").val(ids);
			layer.close(index);
			},
	cancel: function(index){}
	}); 	
}

// 删除预览评定人员
function removepdry(fileid) {
	var ids = $("#pdryids");
	var pdry = ids.val();

	if (pdry.split(",").length == 2) {
		ids.val("");
	} else {
		ids.val(pdry.replace(fileid + ",", ""));
	}
	$("#" + fileid).remove();

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
			url:ctx+"/zyaqgl/xgfpdjh/delete/"+ids,
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
	if(row.m9==1) {
		layer.msg("批准已通过，不得进行修改！",{time: 1000});
		return;
	}else if(row.m6==1&&row.m9==0) {//已审核，批准未通过
		openDialog("修改相关方评定信息",ctx+"/zyaqgl/xgfpdjh/update/update/"+row.id,"800px", "400px","");
		return;
	}else if(row.m6==1) {//已审核，还未批准
		layer.msg("已审核通过，还未进行批准操作，不得进行修改！",{time: 1000});
		return;
	}else {//审核不通过或者还未审核
		openDialog("修改相关方评定信息",ctx+"/zyaqgl/xgfpdjh/update/update/"+row.id,"800px", "400px","");
		return;
	}
	
}

//查看评定记录
function viewpdjl() {
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	openDialog("评定记录",ctx+'/zyaqgl/xgfpdjh/pdnr?id1='+row.id, "900px", "450px","");
}

//审核
function addsh(id){
	openDialog("审核相关方评定信息",ctx+"/zyaqgl/xgfpdjh/update/sh/"+id,"800px", "400px","");
}

//批准
function addsp(id){
	openDialog("批准相关方评定信息",ctx+"/zyaqgl/xgfpdjh/update/sp/"+id,"800px", "400px","");
}

//查看
function view(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	
	openDialogView("查看相关方评定信息",ctx+"/zyaqgl/xgfpdjh/view/"+row.id,"800px", "400px","");
	
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
