//绑定
function add(u) {
	var cds = dg.datagrid("getChecked");
	if (cds == null || cds == "") {
		layer.msg("请选择需要绑定的数据！",{time: 1000});
		return;
	}
	while(cds.length!=0){
		var srow=dg.datagrid("getChecked")[0];
		var rowIndex = dg.datagrid('getRowIndex', srow);
		var prows=parent.dg.datagrid('getData');
		var b=true;
		for(var i=0;i<prows.total;i++){
			if(prows.rows[i].id==srow.id){
				b=false;
				break;
			}
		}
		if(b){
			parent.dg.datagrid('appendRow',srow);
		}
		dg.datagrid('deleteRow',rowIndex);  
	}
}

//查询
function search(){
	var obj = $("#searchFrom").serializeObject();
	dg.datagrid('load', obj);
	dg.datagrid('clearChecked');
}


//清空
function reset(){
	$("#searchFrom").form("clear");
	search();
}
