var dg;
$(function(){
	dg=$('#target_aqcns_dg').datagrid({    
	method: "post",
    url:ctx+'/target/aqcns/adminlist', 
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
              {field:'qyname',title:'企业名称',sortable: false,width:150,align:'center'},  
              {field:'name',title:'岗位名称',sortable:false,width:50,align:'center'},    
             /* {field:'duty',title:'岗位安全职责',sortable:false,width:80,align:'center'},    */
              {field:'s2',title:'更新时间',sortable:false,width:60,align:'center',
            	  formatter : function(value) {
              		if(value)return new Date(value).format('yyyy-MM-dd');
              },    
              {field:'bzperson',title:'编制人',sortable:false,width:60,align:'center'},    
              {field:'shperson',title:'审核人',sortable:false,width:60,align:'center'},    
              {field:'pzperson',title:'批准人',sortable:false,width:60,align:'center'},    
              {field:'note',title:'备注',sortable:false,width:50,align:'center'},
    ]],
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
                 view();
    },
    onLoadSuccess: function(){
        $(this).datagrid("autoMergeCells",['qyname']);
    },
	checkOnSelect:false,
	selectOnCheck:false,
    toolbar:'#target_aqcns_tb'
	});
	
});