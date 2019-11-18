var dg;
$(function(){
	dg=$('#target_aqcns_dg').datagrid({    
	method: "post",
    url:ctx+'/target/aqcns/qylist?qyid='+qyid,
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
              {field:'id',title:'id',checkbox:true,width:50,align:'center'},    
              {field:'year',title:'年份',sortable:false,width:50,align:'center'},    
              {field:'post',title:'岗位名称',sortable:false,width:50,align:'center'},    
              {field:'pername',title:'姓名',sortable:false,width:80,align:'center'},    
              {field:'phone',title:'电话',sortable:false,width:60,align:'center'}, 
              {field : 'url',
      			title : '安全承诺书',
      			sortable : false,
      			width : 100,
      			align : 'center',
      			formatter : function(value) {
      				if (value) {
      					var urls = value.split(",");
      					var html = "";
      					for ( var index in urls) {
      						html += "<a class='fa fa-file-word-o btn-danger btn-outline' target='_blank' style='margin:1px auto' href='" + urls[index].split("||")[0] + "'> 下载</a><br>";
      					}
      					return html;
      				} else
      					return;
      			}
      		},
              {field:'s1',title:'上传时间',sortable:false,width:60,align:'center',
            	  formatter : function(value) {
              		return new Date(value).format('yyyy-MM-dd');
              }},   
    ]],
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
                 view();
    },
    onLoadSuccess: function(){
    	 $(this).datagrid("autoMergeCells",['year']);
    },
	checkOnSelect:false,
	selectOnCheck:false,
    toolbar:'#target_aqcns_tb'
	});
	
});