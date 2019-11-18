var dg;
$(function() {
		dg = $('#target_jfgl_dg').datagrid({
			method : "post",
			url : ctx + '/target/jfgl/listoverview?qyid='+qyid,
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
			columns : [ [ {
				field : 'id',
				title : 'ID',
				checkbox : true,
				width : 50,
				align : 'center'
			}, {
				field : 'qyname',
				title : '企业名称',
				hidden : true,
				width : 200,
				align : 'center'
			}, {
				field : 'year',
				title : '年份',
				sortable : false,
				width : 50,
				align : 'center'
			}, {
				field : 'name',
				title : '用户姓名',
				sortable : false,
				width : 80,
				align : 'center'
			}, {
				field : 'yhpc',
				title : '隐患排查',
				sortable : false,
				width : 50,
				align : 'center'
			}, {
				field : 'ssp',
				title : '随手拍',
				sortable : false,
				width : 50,
				align : 'center'
			},
			 {
				field : 'aqpx',
				title : '安全培训',
				sortable : false,
				width : 50,
				align : 'center'
			},
			 {
				field : 'jyxc',
				title : '建言献策',
				sortable : false,
				width : 50,
				align : 'center'
			},{
				field : 'qt',
				title : '其他',
				sortable : false,
				width : 50,
				align : 'center'
			},{
				field : 'zjf',
				title : '总积分',
				sortable : false,
				width : 50,
				align : 'center'
			},{
				field : 'caozuo',
				title : '查看详情',
				sortable : false,
				width : 50,
				align : 'center',formatter(value,row){
					return "<a class='btn btn-info btn-xs' onclick='view("+JSON.stringify(row)+")'>查看详情</a>";
				}
			}] ],
			onDblClickRow : function(rowdata, rowindex, rowDomElement) {
			},
			onLoadSuccess: function(){
				$(this).datagrid("autoMergeCells",['year']);
				if(usertype=='isbloc'){
		    		$(this).datagrid("showColumn","qyname");
		    		$(this).datagrid("autoMergeCells",['qyname']);
		    	}
			},
			checkOnSelect : false,
			selectOnCheck : false,
			toolbar : '#target_jfgl_tb'
		});
});

//查看
function view(row) {
      	layer.open({
      	    type: 1,  
      	    title: '信息详情',
      	    area:['700px','400px'],
      	    content: $("#select_dg"),
      	    btn: ['关闭'],
      	    success: function(index,layero){
      	      	var d=$('#target_dg').datagrid({    
      	    		method: "post",
      	    		url  : ctx+'/target/jfgl/qylist',
      	    		queryParams :{'year':row.year,'uid':row.id2},
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
      	    		columns:[[    
			    	              {field:'name',title:'用户姓名',sortable: false,width:60,align:'center'},
			    	              {field:'m1',title:'类别',sortable: false,width:60,align:'center',formatter : function(value,row){
			    	            	  var html="";
			    	            	  if(value==1) html="隐患排查";
			    	            	  else if(value==2) html="随手拍";
			    	            	  else if(value==3) html="安全培训";
			    	            	  else if(value==4) html="建言献策";
			    	            	  else if(value==5) html="其他";
			    	            	 return html;
			    	              }}, 
			    	              {field:'m2',title:'积分值',sortable: false,width:50,align:'center'},
			    	              {field:'m3',title:'积分说明',sortable: false,width:150,align:'center'},
			    	              {field:'m4',title:'评定日期',sortable: false,width:80,align:'center',
			    	            	  formatter:function(value){
			    	            		  if(value) return new Date(value).format('yyyy-MM-dd');
			    	            		  else return ;
			    	            	  }},      
  		 		    		]],
  		 		    onLoadSuccess: function(){dg.datagrid("autoMergeCells",['year','quarter','department']);}
      	    		});
      	    },
      	    cancel: function(index){}
      	}); 

}

function search() {
	var obj = $("#searchFrom").serializeObject();
	dg.datagrid('load', obj);
}

function reset() {
	$("#searchFrom").form("clear");
	var obj = $("#searchFrom").serializeObject();
	dg.datagrid('load', obj);
}

//导出
function fileexport() {
	window.expara = $("#searchFrom").serializeObject();
	var Columns = dg.datagrid("options").columns[0];
	window.exdata = [];
	for (var i = 0; i < Columns.length; i++) {
		if (Columns[i].field.toUpperCase() != "ID")
			window.exdata.push({
				colval : Columns[i].field,
				coltext : Columns[i].title
			});
	}
	layer.open({
		type : 2,
		area : [ '350px', '350px' ],
		title : '导出列选择',
		maxmin : false,
		shift : 1,
		content : ctx + '/target/jfgl/colindex',
		btn : [ '导出' ],
		yes : function(index, layero) {
			var body = layer.getChildFrame('body', index);
			var iframeWin = layero.find('iframe')[0];
			var inputForm = body.find('#excel_mainform');
			iframeWin.contentWindow.doExport();
		},
	});

}
