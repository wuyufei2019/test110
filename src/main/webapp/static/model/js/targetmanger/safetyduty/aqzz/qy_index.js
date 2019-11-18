var dg;
$(function(){
	dg=$('#target_aqzz_dg').datagrid({    
	method: "post",
    url:ctx+'/target/aqzz/qylist?ajqyid='+ajqyid,
    fit : true,
	fitColumns : true,
	border : false,
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
              {field:'id',title:'id',width:50,align:'center',hidden: true},
              {field:'qyname',title:'企业名称',hidden: true,width:200,align:'center'},    
              {field:'jobname',title:'岗位名称',sortable:false,width:50,align:'center'},
          /*    {field:'duty',title:'岗位安全职责',sortable:false,width:80,align:'center'},    */
              {field:'s2',title:'更新时间',sortable:false,width:60,align:'center',
            	  formatter : function(value) {
              		if(value)return new Date(value).format('yyyy-MM-dd');
              }},   
              {field:'bzperson',title:'编制人',sortable:false,width:60,align:'center'},    
              {field:'shperson',title:'审核人',sortable:false,width:60,align:'center'},    
              {field:'pzperson',title:'批准人',sortable:false,width:60,align:'center'},    
              {field:'note',title:'备注',sortable:false,width:50,align:'center'},
			  {field:'fileurl',title:'附件',sortable:false,width:50,align:'center',
				  formatter: function(value){
					  if(value){
						  var urls=value.split(",");
						  var html="";
						  for(var index in urls){
							  html+="<a class='fa fa-file-word-o btn-danger btn-outline' target='_blank' style='margin:1px auto' href='"+urls[index].split("||")[0]+"'> 附件"+(parseInt(index)+1)+"</a><br>";
						  }
						  return html;
					  }
					  else return ;
				  }
			  },
              {field:'caozuo',title:'操作',sortable:false,width:50,align:'center',
            	  formatter : function(value ,row){
            		  if(row.id){
            			  return "<a class='btn btn-info btn-xs' onclick='upd("+row.id+")'>修订责任书</a>";
            		  }else{
            			  return "<a class='btn btn-success btn-xs' onclick='add("+row.jid+")'>添加责任书</a>";
            		  }
            	  }
              }
    ]],
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
                 view();
    },
    onLoadSuccess: function(){
    	if(usertype=='isbloc'){
    		$(this).datagrid("showColumn","qyname");
    		$(this).datagrid("autoMergeCells",['qyname']);
    	}
    },
	checkOnSelect:false,
	selectOnCheck:false,
    toolbar:'#target_aqzz_tb'
	});
	
});