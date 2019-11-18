var dg;
var d;
var id1;
var type;
$(function(){
	if(type==0){
		dg=$('#zyaqgl_sxzy_dg').datagrid({    
		method: "post",
	    url:ctx+'/ztzyaqgl/sxzy/aqcslist',
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
		onClickCell: onClickCell,
	    columns:[[    
	              {field:'ID',title:'id',checkbox:true,width:50,align:'center'},   
	              {field:'m1',title:'安全措施',width:80,align:'center',editor:'text'}   
	    ]],
	    onDblClickRow: function (rowdata, rowindex, rowDomElement){
	    },
		checkOnSelect:false,
		selectOnCheck:false,
	    toolbar:'#csdata'
		});
	}
	else{
		dg=$('#zyaqgl_sxzy_dg').datagrid({    
		method: "post",
	    url:ctx+'/ztzyaqgl/sxzy/aqcslist?type='+type+'&id1='+id1,
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
		onClickCell: onClickCell,
	    columns:[[    
	              {field:'ID',title:'id',checkbox:true,width:50,align:'center'},   
	              {field:'m1',title:'安全措施2',width:80,align:'center',editor:'text'},
	              {field:'cz',title:'执行情况',width:80,align:'center',
	            	  formatter : function(value, row, index) {
		            	  return '<input type="radio"  name="cz_'+row.id+'" style="margin-bottom: 6px;" value="已执行" >已执行'
		            	         +'<input type="radio" name="cz_'+row.id+'" style="margin-bottom: 6px;margin-left: 20px;" value="未执行"> 未执行';
	            	  }
	              }
	    ]],
	    onDblClickRow: function (rowdata, rowindex, rowDomElement){
	    },
		checkOnSelect:false,
		selectOnCheck:false,
	    toolbar:'#zyaqgl_sxzy_tb'
		});
	}
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
	if ($('#zyaqgl_sxzy_dg').datagrid('validateRow', editIndex)){
		$('#zyaqgl_sxzy_dg').datagrid('endEdit', editIndex);
		editIndex = undefined;
		return true;
	} else {
		return false;
	}
}

function onClickCell(index, field){
	if (endEditing()){
		$('#zyaqgl_sxzy_dg').datagrid('selectRow', index)
				.datagrid('editCell', {index:index,field:field});
		editIndex = index;
	}
}

// 弹出外来方选择框
function openwlflist() {
	layer.open({
	    type: 2,  
	    area: ['90%', '90%'],
	    title: '选择外来方',
        maxmin: false, 
	    content: ctx + "/ztzyaqgl/sxzy/choose" ,
	    btn: ['确定', '关闭'],
	    yes: function(index, layero){
	    	var  $list= $("#wlfList");
	    	//var body = layer.getChildFrame('body', index);
	    	var iframeWin = layero.find('iframe')[0];
	    	var idname=iframeWin.contentWindow.getidname();
	        var arr1=idname.split(",");
	        var ids=$("#wlfids").val();
			for (var i = 0; i < arr1.length-1; i++) {
				var arr2=arr1[i].split("||");
					// 企业名称拼接
					var $li = $("<div id=\""
							+ arr2[0]
							+ "\" style=\"margin-bottom: 10px;\">"
							+ "<a style=\"color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;\" onClick='viewwlf("+arr2[0]+")'>"
							+ arr2[1]
							+ "</a>"
							+ "<span class=\"ss\" onClick=\"removeWlf('"
							+ arr2[0]
							+ "')\" style=\"cursor: pointer\">删除</span>"
							+ "</div>");
					$list.append($li);
					ids = ids + arr2[0] + ",";
				}
			$("#tip").hide();
			$("#wlfids").val(ids);
			layer.close(index);
			},
	cancel: function(index){}
	}); 	
	
}
//查看
function viewwlf(id){
	openDialogView("查看相关方单位信息",ctx+"/ztzyaqgl/xgdw/view/"+id,"90%", "90%","");
	
}
//删除预览外来方
function removeWlf(fileid) {
	var ids = $("#wlfids");
	var wlf = ids.val();

	if (wlf.split(",").length == 2) {
		ids.val("");
	} else {
		ids.val(wlf.replace(fileid + ",", ""));
	}
	$("#" + fileid).remove();

};