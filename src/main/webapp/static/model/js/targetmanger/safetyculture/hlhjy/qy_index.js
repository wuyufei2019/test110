var dg;
$(function(){
	dg=$('#target_hlhjy_dg').datagrid({    
	method: "post",
    url:ctx+'/target/hlhjy/qylist?qyid='+qyid,
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
              {field:'qyname',title:'企业名称',hidden:true,width:200},  
              {field:'theme',title:'主题',sortable:false,width:100},  
              {field:'username',title:'建言人',sortable:false,width:60,align:'center'},
              {field:'s1',title:'发布时间',sortable:false,width:80,align:'center',
              	formatter : function(value){
              		if(value!=null&&value!=''){
              			var date = new Date(value);
              			return date.format("yyyy-MM-dd"); 
              		}else{
              			return '';
              		}
              	}},
              {field:'type',title:'类别',sortable:false,width:60,align:'center'},
              {field:'url',title:'附件',sortable: false,width:60,align:'center',
        		  formatter:function(value){
        			  if(value){
              			var urls=value.split(",");
              			var html="";
              			for(var index in urls){
              				html+="<a class='fa fa-file-word-o btn-danger btn-outline' target='_blank' style='margin:1px auto' href='"+urls[index].split("||")[0]+"'> 附件"+(parseInt(index)+1)+"</a><br>"; 
              			}
              			return html;
              		}
              		else return ; 
        		  }}, 
        	{field:'result',title:'审核情况',sortable:false,width:60,align:'center',
        			  formatter :function(value,row){
        				  if(value){
        					  if(value=='1') return "<a class='fa  btn-success btn-outline' target='_blank'> 审核通过</a>/<a class='btn btn-success btn-xs' onclick='viewReview("+row.id+")'>查看详情</a>";
        					  else if(value=='0') return "<a class='fa  btn-danger btn-outline' target='_blank'>审核未通过/</a><a class='btn btn-danger btn-xs' onclick='viewReview("+row.id+")'>查看详情</a>"
        				  }else{
        					  if(sh==1)
        					    return "<a class='btn btn-info btn-xs' onclick='addReview("+row.id+")'>审核</a>";
        					  else return '未审核';
        				  }
        			  }}, 
        		  ]],
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
                 view();
    },
    onLoadSuccess: function(){
    	if(usertype=='isbloc'){
    		$(this).datagrid("showColumn","qyname");
    		$(this).datagrid("autoMergeCells", [ 'qyname' ]);
		}
    },
	checkOnSelect:false,
	selectOnCheck:false,
    toolbar:'#target_hlhjy_tb'
	});
	
});