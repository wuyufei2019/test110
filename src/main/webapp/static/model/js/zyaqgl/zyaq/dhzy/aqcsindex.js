var dg;
var d;
var id1;
var type;
$(function(){
	if(type==0){
		dg=$('#zyaqgl_dhzy_dg').datagrid({    
		method: "post",
	    url:ctx+'/zyaqgl/dhzy/aqcslist', 
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
	    toolbar:'#zyaqgl_dhzy_tb'
		});
	}
	else{
		dg=$('#zyaqgl_dhzy_dg').datagrid({    
		method: "post",
	    url:ctx+'/zyaqgl/dhzy/aqcslist?type='+type+'&id1='+id1, 
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
	              {field:'m1',title:'安全措施',width:80,align:'center',editor:'text'}   
	    ]],
	    onDblClickRow: function (rowdata, rowindex, rowDomElement){
	    },
		checkOnSelect:false,
		selectOnCheck:false,
	    toolbar:'#zyaqgl_dhzy_tb'
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
	if ($('#zyaqgl_dhzy_dg').datagrid('validateRow', editIndex)){
		$('#zyaqgl_dhzy_dg').datagrid('endEdit', editIndex);
		editIndex = undefined;
		return true;
	} else {
		return false;
	}
}

function onClickCell(index, field){
	if (endEditing()){
		$('#zyaqgl_dhzy_dg').datagrid('selectRow', index)
				.datagrid('editCell', {index:index,field:field});
		editIndex = index;
	}
}

