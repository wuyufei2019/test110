var a = 0;
//根据风险等级分风险点
function fengxianGrid(type){
	$('#fxgk_tjfx_dg').datagrid('loadData', { total: 0, rows: [] });  
	dg = $('#fxgk_tjfx_dg').datagrid({
		method: "post",
		queryParams:{'type':type},
	    url:ctx+'/fxgk/tjfx/FXDjajlist', 
	    fit : false,
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
	        {field:'m9',title:'风险分级',sortable:false,width:70,align:'center',
				formatter: function(value,row,index){
					if (value == '1'){
						return '红';
					} else if(value == '2'){
						return '橙';
					}else if(value == '3'){
						return '黄';
					}else if(value == '4'){
						return '蓝';
					}
				}},
	        {field:'qyname',title:'企业名称',sortable:false,width:100},
	        {field:'m1',title:'风险点名称',sortable:false,width:100}
	    ]],
	    onLoadSuccess: function(){
	        $(this).datagrid("autoMergeCells",['m9']);
	        $(this).datagrid("autoMergeCells",['qyname']);
	    },
	    checkOnSelect : false,
		selectOnCheck : false,
		});
}

//根据乡镇分风险点
function xiangzhenGrid(xzqyname){
	$('#fxgk_tjfx_dg').datagrid('loadData', { total: 0, rows: [] });  
	dg = $('#fxgk_tjfx_dg').datagrid({
		method: "post",
		queryParams:{'xzqyname':xzqyname},
	    url:ctx+'/fxgk/tjfx/XZjajlist', 
	    fit : false,
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
	        {field:'street',title:'乡镇',sortable:false,width:70,align:'center'},
	        {field:'qyname',title:'企业名称',sortable:false,width:100},
	        {field:'yanse',title:'风险分级',sortable:false,width:100,
	        	formatter:function(value, row, index){
	        		if(value==1){
	        			return "红";
	        		}else if(value==2){
	        			return "橙";
	        		}else if(value==3){
	        			return "黄";
	        		}else if(value==4){
	        			return "蓝";
	        		}
	        	}
	        },
	        {field:'m1',title:'风险点名称',sortable:false,width:100}
	    ]],
	    onLoadSuccess: function(){
	        $(this).datagrid("autoMergeCells",['street']);
	        $(this).datagrid("autoMergeCells",['qyname']);
	        $(this).datagrid("autoMergeCells",['yanse']);
	    },
	    checkOnSelect : false,
		selectOnCheck : false,
		toolbar : '#1'
		});
}

//根据风险分类分风险点
function fenleiGrid(type){
	if(a != 0){
		$('#fxgk_tjfx_dg').datagrid('loadData', { total: 0, rows: [] });  
	}
	dg = $('#fxgk_tjfx_dg').datagrid({
		method: "post",
	    url:ctx+'/fxgk/tjfx/FLjajlist', 
	    fit : false,
		fitColumns : true,
		border : false,
		idField : 'id',
		queryParams:{'type':type},
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
	        {field:'fenlei',title:'风险分类',sortable:false,width:70,align:'center'},
	        {field:'qyname',title:'企业名称',sortable:false,width:100},
	        {field:'m1',title:'风险点名称',sortable:false,width:100},
	        {field:'m9',title:'风险分级',sortable:false,width:50,
	        	formatter:function(value, row, index){
	        		if(value==1){
	        			return "红";
	        		}else if(value==2){
	        			return "橙";
	        		}else if(value==3){
	        			return "黄";
	        		}else if(value==4){
	        			return "蓝";
	        		}
	        	}
	        }
	    ]],
	    onLoadSuccess: function(){
	        $(this).datagrid("autoMergeCells",['fenlei']);
	        $(this).datagrid("autoMergeCells",['qyname']);
	    },
	    checkOnSelect : false,
		selectOnCheck : false,
		toolbar : '#1'
		});
	a++;
}

//根据已发生事故分类分风险点
function shiguGrid(type){
	if(a != 0){
		$('#fxgk_tjfx_dg').datagrid('loadData', { total: 0, rows: [] });  
	}
	dg = $('#fxgk_tjfx_dg').datagrid({
		method: "post",
	    url:ctx+'/fxgk/tjfx/Sgjajlist', 
	    queryParams:{'type':type},
	    fit : false,
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
	        {field:'shigu',title:'事故类型',sortable:false,width:70,align:'center'},
	        {field:'qyname',title:'企业名称',sortable:false,width:100},
	        {field:'m1',title:'风险点名称',sortable:false,width:100}
	    ]],
	    onLoadSuccess: function(){
	        $(this).datagrid("autoMergeCells",['shigu']);
	        $(this).datagrid("autoMergeCells",['qyname']);
	    },
	    checkOnSelect : false,
		selectOnCheck : false,
		toolbar : '#1'
		});
	a++;
}