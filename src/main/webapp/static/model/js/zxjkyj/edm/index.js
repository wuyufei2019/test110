var dg;

$(function(){
	dg=$('#zxjkyj_edm_dg').datagrid({    
	method: "post",
    url:ctx+'/zxjkyj/edm/list', 
    fit : true,
	fitColumns : true,
	selectOnCheck:false,
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
  			{field : 'id',title : 'id',checkbox : true,width : 50,align : 'center'},
			{field : 'qyname',title : '企业名称',sortable : false,width : 60,align : 'center'},
  			{field : 'ygcode',title : '员工工号',sortable : false,width : 60,align : 'center'},
			{field : 'ygname',title : '员工姓名',sortable : false,width : 60,align : 'center'},
			{field : 'type',title : '类型',sortable : false,width : 40,align : 'center',
				formatter: function (value) {
					var title = '';
					var imgUrl = '';
					if (value == '进') {
						title = '进厂';
						imgUrl = ctx + '/static/model/images/hgqy/in.png';
					} else if (value == '出') {
						title = '出厂';
						imgUrl = ctx + '/static/model/images/hgqy/off.png';
					}
					return value + ' <img src="'+imgUrl+'" title="'+title+'"/>';
				}
			},
			{field : 'edmname',title : '二道门名称',sortable : false,width : 60,align : 'center'},
			{field : 'updatetime',title : '采集时间',sortable : false,width : 60,align : 'center',
				formatter : function(value, row, index) {
	              	if(value!=null&&value!='') {
	              		var datetime=new Date(value);
	              		return datetime.format('yyyy-MM-dd hh:mm:ss');
	              	}
	            }
			}
    ]],
    onLoadSuccess: function(){
		$(this).datagrid("autoMergeCells",['qyname']);
		if (usertype == '1') {
			$(this).datagrid("hideColumn",['qyname']);
		}
    },
     onLoadError:function(){
    	layer.open({title: '提示',offset: 'rb',content: '数据加载中，请耐心等待。',shade: 0 ,time: 2000 });
    },
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
    },
	checkOnSelect:false,
	selectOnCheck:false,
    toolbar:'#zxjkyj_edm_tb'
	});
	
});

//清空
function reset(){
	$("#searchFrom").form("clear");
	search();
}

//查询
function search(){
	var obj=$("#searchFrom").serializeObject();
	dg.datagrid('load',obj);
}