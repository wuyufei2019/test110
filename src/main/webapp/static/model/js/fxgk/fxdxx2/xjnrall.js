//绑定
function add(u) {
	var cds = dg.datagrid("getChecked");
	if (cds == null || cds == "") {
		layer.msg("请选择需要绑定的数据！",{time: 1000});
		return;
	}
	for(var ckecknum=0;ckecknum<cds.length;ckecknum++){
		var srow=dg.datagrid("getChecked")[ckecknum];
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
	}
}

//检查表库增加
function add2() {
	openDialog("添加企业自增信息",ctx+"/yhpc/jcbk/create3","600px", "300px","");
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
