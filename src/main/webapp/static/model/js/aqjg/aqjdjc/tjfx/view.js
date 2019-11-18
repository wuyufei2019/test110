var dg;
var dg2;
var dg3;
var dg4;
var year;
var month;
$(function(){
	year=$("#year").val();
	month=$("#month").val();
	dg=$('#aqjg_tjfx_qy_dg').datagrid({    
	method: "post",
    url:ctx+'/aqjd/tjfx/list/'+year+"/"+month, 
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
        {field:'name',title:'检查名称',sortable:false,width:100,align:'center'},
        {field:'m2',title:'检查日期',sortable:false,width:100,align:'center'},
        {field:'qyname',title:'企业名称',sortable:false,width:100,align:'center'}
    ]],
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
    },
    onLoadSuccess:function(){
    	   var titles = $('#qycount').find('.tabs').find('.tabs-title');
    	   titles.eq(0).text('计划检查企业数量('+dg.datagrid('getData').total+")");
  	  $(this).datagrid("autoMergeCells",['name']);
    },
	checkOnSelect:false,
	selectOnCheck:false,
	});
	
	dg2=$('#aqjg_tjfx_fin_dg').datagrid({    
		method: "post",
	    url:ctx+'/aqjd/tjfx/list2/'+year+"/"+month+"/1", 
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
	        {field:'m1',title:'检查名称',sortable:false,width:100,align:'center'},
	        {field:'qyname',title:'企业名称',sortable:false,width:100,align:'center'}
	    ]],
	    onDblClickRow: function (rowdata, rowindex, rowDomElement){
	    },
	    onLoadSuccess:function(){
	  	  $(this).datagrid("autoMergeCells",['name']);
	  	 var titles = $('#qycount').find('.tabs').find('.tabs-title');
  	      titles.eq(1).text('完成检查企业('+dg2.datagrid('getData').total+")");
	    },
		checkOnSelect:false,
		selectOnCheck:false,
		});
		
	dg3=$('#aqjg_tjfx_unfin_dg').datagrid({    
		method: "post",
	    url:ctx+'/aqjd/tjfx/list2/'+year+"/"+month+"/2", 
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
	         {field:'m1',title:'检查名称',sortable:false,width:100,align:'center'},
	        {field:'qyname',title:'企业名称',sortable:false,width:100,align:'center'}
	    ]],
	    onDblClickRow: function (rowdata, rowindex, rowDomElement){
	    },
	    onLoadSuccess:function(){
	  	  $(this).datagrid("autoMergeCells",['name']);
	  	  var titles = $('#qycount').find('.tabs').find('.tabs-title');
  	      titles.eq(2).text('只完成初查企业('+dg3.datagrid('getData').total+")");
	    },
		checkOnSelect:false,
		selectOnCheck:false,
		});
	
	dg4 = $('#aqjg_tjfx_dg').datagrid(
			{
				method : "get",
				url : ctx + '/aqjd/jcjh/list',
				fit : true,
				fitColumns : true,
				border : false,
				idField : 'ID',
				striped : true,
				pagination : true,
				rownumbers : true,
				nowrap : false,
				pageNumber : 1,
				pageSize : 50,
				pageList : [ 50, 100, 150, 200, 250 ],
				scrollbarSize : 5,
				singleSelect : true,
				striped : true,
				columns : [ [
						{
							field : 'ID',
							title : 'ID',
							checkbox : true,
							width : 50,
							align : 'center'
						},
						{
							field : 'm2',
							title : '计划时间',
							sortable : false,
							width : 40,
							align : 'center',
							formatter : function(value) {
								var arry=value.split("-");
								return arry[0]+"年"+arry[1]+"月"
							}
						},
						{
							field : 'm1',
							title : '专项检查名称',
							sortable : false,
							width : 100,
							align : 'center'
						},
						{
							field : 'qyids',
							title : '计划检查企业数',
							sortable : false,
							width : 50,
							align : 'center',
							formatter : function(value){
								return (value.split(",").length-1);
							}
						},
						{
							field : 'schedule',
							title : '完成复查企业数量',
							sortable : false,
							width : 100,
							align : 'center',
							formatter : function(value) {
								var arr=value.split(";");
								return parseInt(arr[1]==''?0:arr[1]);
				            	}
						},
						{
							field : 'num',
							title : '只完成初查企业数量',
							sortable : false,
							width : 100,
							align : 'center',
							formatter : function(value, row, index) {
								var arr=row.schedule.split(";");
								return parseInt(arr[0]==''?0:arr[0]);
				            	}
						}
				] ],
				onDblClickRow : function(rowdata, rowindex, rowDomElement) {
				},
				onLoadSuccess : function(rowdata, rowindex, rowDomElement) {
				},
				checkOnSelect : false,
				selectOnCheck : false,
			});
});


function reload(year){
$.ajax({
	url:ctx+ '/aqjd/jcjh/list',
	type:"POST",
	data:{"aqjg_jcjh_year":year},
	success:function(data){
		dg4.datagrid('loadData',data);
	}
});
}

//弹窗修改
function upd() {
	var row = dg4.datagrid('getSelected');
	if (row == null) {
		layer.msg("请选择一行记录！", {
			time : 1000
		});
		return;
	}

	openDialogView("修改文件信息", ctx + "/aqjd/jcjh/update/" + row.ID, "900px",
			"400px", "");

}

