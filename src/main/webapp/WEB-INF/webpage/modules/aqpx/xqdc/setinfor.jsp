<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>培训课程设置</title>
<meta name="decorator" content="default"/>
</head>
<body>
<div id="remark_tb" style="padding:5px;height:auto">
	<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="addRemark()" title="添加行"><i class="fa fa-plus"></i> 添加行</button>
	<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="removeit()" title="删除"><i class="fa fa-trash-o"></i> 删除</button>
</div>
<table id="remark_dg"></table>
<script type="text/javascript">
	var ztid = '${ztid}';
	$obj = $("#remark_dg");
	var dg;
	$(function(){
		 dg=$("#remark_dg").datagrid({
		 	 method: "post",
		     url:ctx+'/aqpx/xqdc/kclist/'+ztid,
		     columns: [[ 
				   {field: 'id',title: '编号',width: 100,sortable: true,checkbox: true},
				   {field: 'm1',title: '课程名称',width: 100,sortable: false,
				   	   editor: {
				           type: 'textbox',
				           options: {
				           		required:'true',
				           		validType:'length[0,50]'
				           }
				       }
				   },
				   {field: 'm2',title: '投票数',width: 70,sortable: false,
				       editor: {
				           type: 'numberbox',
				           options: {
				           		min:0
				           }
				       }
				   }
		 	 ]],
		     toolbar:'remark_tb',  //表格菜单
		     fit:true,
		     fitColumns:true,
		     loadMsg:'加载中...', //加载提示
		     rownumbers:true, //显示行号列
		     singleSelect:true,//是允许选择一行
		     checkOnSelect:false,
			 selectOnCheck:false,
		     onClickCell: onClickCell,
		     queryParams:{   //在请求数据是发送的额外参数，如果没有则不用写
		     },
		     onLoadSuccess:function(data){
		     },
		     rowStyler:function(index,row){
		     }
		 });
	});
    
    $.extend($.fn.datagrid.methods, {
		editCell: function(jq,param){
			return jq.each(function(){
				var opts = dg.datagrid('options');
				var fields = dg.datagrid('getColumnFields',true).concat(dg.datagrid('getColumnFields'));
				for(var i=0; i<fields.length; i++){
					var col = dg.datagrid('getColumnOption', fields[i]);
					col.editor1 = col.editor;
					if (fields[i] != param.field){
						col.editor = null;
					}
				}
				dg.datagrid('beginEdit', param.index);
				for(var i=0; i<fields.length; i++){
					var col = dg.datagrid('getColumnOption', fields[i]);
					col.editor = col.editor1;
				}
			});
		}
	});

	var editIndex = undefined;
	function endEditing(){
		if (editIndex == undefined){return true}
		if (dg.datagrid('validateRow', editIndex)){
			dg.datagrid('endEdit', editIndex);
			editIndex = undefined;
			return true;
		} else {
			return false;
		}
	}
	
	function onClickCell(index, field){
		if (endEditing()){
			dg.datagrid('selectRow', index)
					.datagrid('editCell', {index:index,field:field});
			editIndex = index;
		}
	}
    
    // 添加
    function addRemark(){
         if (endEditing()){
             dg.datagrid('appendRow',{status:'P'});
             editIndex = dg.datagrid('getRows').length-1;
             dg.datagrid('selectRow', editIndex);
             dg.datagrid('beginEdit', editIndex);
         }
    }
      
    // 删除
    function removeit(){ 
    	 var row = dg.datagrid('getChecked');
		if(row==null||row=='') {
			layer.msg("请至少勾选一行记录！",{time: 1000});
			return;
		}
	
        confirmx('确认删除吗?', function(index) {
        	  var ids="";
			  for(var i=0;i<row.length;i++){
			  		if(row[i].id == undefined){
			  			dg.datagrid('deleteRow', dg.datagrid('getRowIndex', row[i]));
			  		}else{
			  			if(ids==""){
						ids=row[i].id;
						}else{
							ids=ids+","+row[i].id;
						}
			  		}
			  }
			  if(ids!=""){
			  		$.ajax({  
	                   url : ctx+"/aqpx/xqdc/kcdelete/"+ids, 
	                   type : 'get',                     
	                   success : function(data) {  
                       	   layer.alert(data, {offset: 'rb',shade: 0,time: 2000}); 
                           dg.datagrid('reload');
	                   }  
             	   });
			  }
			  dg.datagrid('clearChecked');
         });  
     }
	 
	 // 保存方法（添加修改用了一个方法accept()）
	 var index2 = parent.layer.getFrameIndex(window.name); //获取窗口索引
     function doSubmit(){
     	 dg.datagrid('endEdit', editIndex);
     	 var rows = dg.datagrid('getData').rows;
		 var rs = [];
		 var m1="";
		 for(var i=0;i<rows.length;i++){
				rs.push({
					id : rows[i].id,
					m1 : rows[i].m1,
					m2 : rows[i].m2
				});
		 }
		 top.layer.confirm('是否确认培训课程设置？', {icon: 3, title:'提示'}, function(index){
	         $.ajax({
	                url:ctx+"/aqpx/xqdc/kcsave/"+ztid,
	                type:"get",
	                data : {'list' : JSON.stringify(rs)},
	                success:function(data){
	                    top.layer.close(index);
	                    window.parent.location.reload();
	                    parent.layer.close(index2);
	                    
	                }
	         });
         });
     }
</script>
</body>
</html>
