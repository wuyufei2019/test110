var dg;
$(function(){
	dg=$('#target_aqhy_dg').datagrid({    
	method: "post",
    url:ctx+'/target/aqhy/qylist?qyid='+qyid,
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
              {field:'qyname',title:'企业名称',hidden:true,width:200,align:'center'},  
              {field:'theme',title:'会议主题',sortable:false,width:100,align:'center'},  
              {field:'time',title:'会议时间',sortable:false,width:80,align:'center',
            	  formatter : function(value) {
              		return new Date(value).format('yyyy-MM-dd hh:mm:ss');
              }},
              {field:'address',title:'地点',sortable:false,width:150,align:'center'},    
              {field:'type',title:'会议类型',sortable:false,width:50,align:'center',
            	  formatter: function(value){
            		  if(value=='1') return "公司级";
            		  else if(value=='2') return "部门级";
            		  else if(value=='3') return "临时会议";
            		  else if(value=='4') return "其他会议";
            		  else return ;
            	  }}, 
              {field:'state',title:'状态',sortable:false,width:100,align:'center',
            		  formatter: function(value){
                		  if(value=='1') return "待开";
                		  else if(value=='2') return "推迟";
                		  else if(value=='3') return "结束";
                		  else return ;
                 }},
             {field:'url',title:'会议纪要',sortable:false,width:100,align:'center',
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
           		{field:'caozuo',title:'操作', sortable:false, width:100, formatter : function (value, row){
           			var html="";
             		if(row.state=="3"){//会议结束
             			if(!row.feedback){
             				html+="<a class='btn btn-warning btn-xs' onclick='addFeedback("+row.id+")'>督办事项反馈</a>";
             			}
             			html+="<a class='btn btn-success btn-xs' onclick='update("+row.id+")'>修改</a>";
             		}else{
             			html+= "<a class='btn btn-danger btn-xs' onclick='delay("+row.id+")'>推迟会议</a>";
             			//会议推迟
             			html+="<a class='btn btn-info btn-xs' onclick='addCont("+row.id+")'>添加会议纪要</a>";
             		} 
         			return html;
                 }}  	  
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
    toolbar:'#target_aqhy_tb'
	});
	
});