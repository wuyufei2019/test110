var dg;
var d;
$(function(){
	dg=$('#aqjg_aqba_dg').datagrid({    
	method: "post",
    url:ctx+'/aqjg/aqba/pjlist', 
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
        {field:'qyname',title:'企业名称',sortable:false,width:100},    
        {field:'m3',title:'备案类别',sortable:false,width:50,align:'center'},
        {field:'m1',title:'备案编号',sortable:false,width:80},
        {field:'m2',title:'备案日期',sortable:false,width:50,align:'center',
        	formatter:function(value,row,index){
     		if(value!=null){
                var datetime = new Date(value);  
                return datetime.format('yyyy-MM-dd');  }  
            } },
        {field:'m4',title:'是否审核',sortable:false,width:50,align:'center',
         	formatter : function(value, row, index) {
            	if(value=='0') return value='否';
		        if(value=='1') return value='是';
		        else return value='否';
        	} 
        },
        {field:'m7',title:'备案经手人',sortable:false,width:50,align:'center'},
        {field:'m6',title:'附件下载',sortable:false,width:50,align:'center',
        	formatter : function(value) {
    		if(value){
    			var urls=value.split(",");
    			var html="";
    			for(var index in urls){
    				html+="<a class='btn btn-info btn-xs' target='_blank' style='margin:1px auto' href='"+urls[index].split("||")[0]+"'> 下载文件 </a><br>"; 
    			}
    			return html;
    		}
    		else return "未上传"; 
   	 }}
    ]],
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
                 view();
    },
	checkOnSelect:false,
	selectOnCheck:false,
    onLoadSuccess:function(){
    	  if(usertype=="1"){
    		  $(this).datagrid("hideColumn",['qyname']);
    	  }else{
    		  $(this).datagrid("showColumn",['qyname']);
    	  }
    	  $(this).datagrid("autoMergeCells",['qyname']);
      },
    toolbar:'#aqjg_aqba_tb'
	});
});

//查看
function view(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	
	openDialogView("查看评价报告信息",ctx+"/aqjg/aqba/view/"+row.id,"800px", "400px","");
	
}

//创建查询对象并查询
function search(){
	var obj=$("#searchFrom").serializeObject();
	dg.datagrid('load',obj); 
}

function reset(){
	$("#searchFrom").form("clear");
	var obj=$("#searchFrom").serializeObject();
	dg.datagrid('load',obj); 
	//if(btflg=='2') $("#filter_EQS_departmen").hide();
}

//导出
function fileexport(){
	window.expara=$("#searchFrom").serializeObject();
	window.exdata=[
			   		{colval:'label', coltext:'备案类别'},
			   		{colval:'m2', coltext:'备案日期'},
			   		{colval:'m7', coltext:'备案经手人'},
			   		{colval:'m5', coltext:'审核意见'},
			   		{colval:'m8', coltext:'备注'}
		   	];
	layer.open({
	    type: 2,  
	    area: ['350px', '350px'],
	    title: '导出列选择',
        maxmin: false, 
        shift: 1,
	    content: ctx+'/aqjg/aqba/colindex2',
		btn: ['导出'],
	    yes: function(index, layero){
	    	 var body = layer.getChildFrame('body', index);
	         var iframeWin = layero.find('iframe')[0];  
	         var inputForm = body.find('#excel_mainform');
	         iframeWin.contentWindow.doExport();
		  },
	});
		
}