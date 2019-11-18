var dg;
var d;
$(function(){
	dg=$('#sggl_sgdc_dg').datagrid({    
	method: "post",
    url:ctx+'/sggl/sgdc/list', 
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
              {field:'qynm',title:'企业名称',width:100,align:'center'},   
              {field:'m1',title:'事故名称',width:120,align:'center'},
              {field:'m2',title:'发生时间',width:60,align:'center',
            	  formatter : function(value, row, index){
            		  if(value!=null&&value!=''){
            			  var datetime=new Date(value);
            			  return datetime.format('yyyy-MM-dd hh:mm:ss');  
            		  }
            	  }  
              },    
              {field:'m3',title:'经济损失(万元)',width:70,align:'center',
              	formatter:function(value, row, index){
            		if(value!=null&&value!=""){
            			return value.toFixed(2);
            		}
            	}
              },
              {field:'m4',title:'伤亡人数',width:70,align:'center',
                	formatter:function(value, row, index){
              		if(value!=null&&value!=""){
              			return value.toFixed(2);
              		}
              	}
              },
              {field:'m5',title:'事故调查报告',width:70,align:'center',
                	formatter : function(value, row, index){
                  		var content="";
                  		if(value!=null&&value!='') {
                  			var arr1=value.split(",");
                  			for (var i = 0; i < arr1.length; i++) {
                  				var url=arr1[i];
                  				var arr2=url.split("||");
                      			content=content+'<a href="'+arr2[0]+'" target="_blank">'+arr2[1]+'</a><br>'; 
                  			} 
                      		return content;
                  		}else{
    						return "<a  class='btn btn-info btn-xs'style='margin-right:5px' onclick='upd2("
							+ row.id + ")'>上传事故调查报告</a>";
                  		}
                   }
              },                
    ]],
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
                 view();
    },
	checkOnSelect:false,
	selectOnCheck:false,
    onLoadSuccess:function(){
	  	  if(usertype=="1"){
			  $(this).datagrid("hideColumn",['qynm']);
		  }else{
			  $(this).datagrid("showColumn",['qynm']);
		  }
    	  $(this).datagrid("autoMergeCells",['qynm']);
      },
    toolbar:'#sggl_sgdc_tb'
	});
});

//弹窗增加
function add(u) {
	openDialog("添加事故调查信息",ctx+"/sggl/sgdc/create/","800px", "400px","");
}

//删除
function del(){
	var row = dg.datagrid('getChecked');
	if(row==null||row=='') {
		layer.msg("请至少勾选一行记录！",{time: 1000});
		return;
	}

	var ids="";
	for(var i=0;i<row.length;i++){
		if(ids==""){
			ids=row[i].id;
		}else{
			ids=ids+","+row[i].id;
		}
	}

	top.layer.confirm('删除后无法恢复您确定要删除？', {icon: 3, title:'提示'}, function(index){
		$.ajax({
			type:'get',
			url:ctx+"/sggl/sgdc/delete/"+ids,
			success: function(data){
				layer.alert(data, {offset: 'rb',shade: 0,time: 2000}); 
				top.layer.close(index);
				dg.datagrid('reload');
				dg.datagrid('clearChecked');
				dg.datagrid('clearSelections');
			}
		});
	});
 
}


//弹窗修改
function upd(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	
	openDialog("修改事故登记信息",ctx+"/sggl/sgdc/update/"+row.id,"800px", "400px","");
	
}

//弹窗修改
function upd2(id){
	openDialog("修改事故登记信息",ctx+"/sggl/sgdc/update/"+id,"800px", "400px","");
	
}

//查看
function view(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	
	openDialogView("查看事故登记信息",ctx+"/sggl/sgdc/view/"+row.id,"800px", "400px","");
	
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
}
