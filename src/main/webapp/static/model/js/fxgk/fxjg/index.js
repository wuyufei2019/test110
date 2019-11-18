var dg;
var d;
var wqyname;
$(function() {
	
	dg = $('#fxgk_fxjg_dg').datagrid(
			{
				method : "post",
				url : ctx + '/fxgk/fxjg/riskPageList',
				fit : true,
				fitColumns : true,
				border : false,
				idField : 'id',
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

				    {field:'id',title:'ID',checkbox:true,width:150,align:'center'},    
					{field:'m1',title:'企业名称',sortable:false,width:100,align:'center'},   
					{field:'m11_3',title:'乡镇',sortable:false,width:50,align:'center'},    
					{field:'red',title:'红点数量',sortable:true,width:50,align:'center'},
					{field:'orange',title:'橙点数量',sortable:true,width:50,align:'center'},
					{field:'yellow',title:'黄点数量',sortable:true,width:50,align:'center'},
					{field:'blue',title:'蓝点数量',sortable:true,width:50,align:'center'},
					{field:'count',title:'风险点总数',sortable:true,width:50,align:'center'},
					{field:'yanse',title:'企业风险等级',sortable:true,width:50,align:'center',
						formatter : function(value, row, index){
			        		if(value=='1') return value='红';
			        		else if(value=='2') return value='橙';
			        		else if(value=='3') return value='黄';
			        		else if(value=='4') return value='蓝'; 
			        		else{
			        			return;
			        		}
			        	},
			        	styler : function(value, row, index){
			        		if(value=='1')  return 'background-color:#FF0000;color:#1E1E1E';
			        		else if(value=='2')  return 'background-color:#FFC125;color:#1E1E1E';
			        		else if(value=='3')  return 'background-color:#FFFF00;color:#1E1E1E';
			        		else if(value=='4')  return 'background-color:#3A5FCD;color:#1E1E1E'; 
			     		}
			        },
					{field:'cz',title:'操作',sortable:false,width:110,align:'center',
						formatter : function(value, row, index) {
							var html= "<a  class='btn btn-info btn-xs'style='margin-right:5px' onclick='opengkdc("
										+ row.id + ")'>查看管控对策</a>"
										+"<a  class='btn btn-success btn-xs' onclick='viewXjdzt("+row.id+",\""+row.m1+"\")'>查看风险点状态</a>";
							if(row.m33_3!=null&&row.m33_3!='')
								html=html+"<a  class='btn btn-warning btn-xs' style='margin-left:5px' onclick='pmt("+row.id+")'>平面分布图</a>";
							return html;
							}
					}
				] ],
				onDblClickRow : function(rowdata, rowindex, rowDomElement) {
					viewfxd();
				},
				 onBeforeLoad:function(param){
			    	if(f!=''&&f=='sys'){
			    		$("#fxgk_fxjg_fxdj").combobox('setValue',fxdj);
			    		param.fxgk_fxjg_fxdj=fxdj;
			    	 }
				    },
				onLoadSuccess : function(rowdata, rowindex, rowDomElement) {
					$(this).datagrid("autoMergeCells", [ 'm1' ]);
				},
				checkOnSelect : false,
				selectOnCheck : false,
				toolbar : '#fxgk_fxjg_tb'
			});

});

// 查看
function view() {
	var row = dg.datagrid('getSelected');
	if (row == null) {
		layer.msg("请选择一行记录！", {
			time : 1000
		});
		return;
	}
	openDialogView("查看风险点信息", ctx + "/fxgk/fxjg/view/" + row.id, "800px","400px", "");

}

//查看
function pmt(qyid) {
	layer.open({
	    type: 2,  
	    area: ["100%", "100%"],
	    title: " ",
        maxmin: true, 
	    content: ctx + "/fxgk/fxjg/pmt/" + qyid,
	    btn: ['保存图片','关闭'],
	    yes: function(index){
	    	window.open(ctx + "/fxgk/fxjg/xzpmt/" + qyid);
	    },
	    cancel: function(index){ 
	    
	    }
	}); 
}

//跳转隐患风险点状态
function viewXjdzt(id,qyname){
	wqyname=qyname;
	layer.open({
	    type: 2,  
	    area: ["100%", "100%"],
	    title: " ",
        maxmin: true, 
	    content: ctx + "/yhpc/xjdzt/index2?qyid=" + id,
	    btn: ['关闭'],
	    cancel: function(index){ 
	       }
	}); 
}
// 查看管控对策
function opengkdc(id) {
	openDialogView("查看管控对策", ctx + "/fxgk/fxjg/gkdc/" + id, "850px","550px", "");
	
}

// 创建查询对象并查询
function search() {
	var obj = $("#searchFrom").serializeObject();
	dg.datagrid('load', obj);
}
 

function reset() {
	$("#searchFrom").form("clear");
	var obj = $("#searchFrom").serializeObject();
	dg.datagrid('load', obj);
}

function viewfxd() {
	var row = dg.datagrid('getSelected');
	if (row == null) {
		layer.msg("请选择一行记录！", {
			time : 1000
		});
		return;
	}else{
		layer.open({
			type: 1,  
			area: ['800px', '450px'],
			title: '查看企业风险信息',
			maxmin: false, 
			content: $("#select_type"),
			btn: ['关闭'],
		    success: function(layero, index){
		    	var dg2 =$('#areadata').datagrid({    
		    		nowrap:false,
		    		method: "post",
		    		url:ctx+'/fxgk/fxxx/tablist/'+row.id,
		    	    loadMsg :'正在加载',
		    	    fit : true,
		    		fitColumns : true,
		    		selectOnCheck:false,
		    		border : false,
		    		idField : 'id',
		    		striped:true,
		    		pagination:true,
		    		rownumbers:true,
		    		pageNumber:1,
		    		pageSize : 50,
		    		pageList : [ 50, 100, 150, 200, 250 ],
		    		scrollbarSize:0,
		    		selectOnCheck:false,
		    		border:false,
		    		singleSelect:true,
		    		checkOnSelect:false,
		    	    columns:[[    
				{field:'m1',title:'较大风险点名称',sortable:false,width:100,align:'center'},    
				{field:'m2',title:'风险类别',sortable:false,width:100,align:'center'},    
				{field:'m3',title:'行业',sortable:false,width:100,align:'center'},    
				{field:'m4',title:'行业类别',sortable:false,width:100,align:'center'},    
				{field:'m8',title:'事故类型',sortable:false,width:100,align:'center'},    
				{field:'m9',title:'风险分级',sortable:false,width:100,align:'center',sortable:true, 
					formatter : function(value, row, index){
						if(value=='1') return value='红';
						else if(value=='2') return value='橙';
						else if(value=='3') return value='黄';
						else if(value=='4') return value='蓝'; 
					},
					styler : function(value, row, index){
						if(value=='1')  return 'background-color:#FF0000;color:#1E1E1E';
						else if(value=='2')  return 'background-color:#FFC125;color:#1E1E1E';
						else if(value=='3')  return 'background-color:#FFFF00;color:#1E1E1E';
						else if(value=='4')  return 'background-color:#3A5FCD;color:#1E1E1E'; 
						}
				}
		    	]],
		    	/*onLoadSuccess:function(rowdata, rowindex, rowDomElement){
		    		dg.datagrid("autoMergeCells", [ 'qyname' ]);
		    	},*/
		    		checkOnSelect:true,
		    		selectOnCheck:false,
		    		});
		    	//dg.datagrid('loadData',{"rows":rydata});//datagrid加载数据
		      },
			cancel: function(index){ 
			}
		}); 
	}
}

