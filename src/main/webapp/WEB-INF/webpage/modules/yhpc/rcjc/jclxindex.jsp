<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>隐患排查---检查类型</title>
	<meta name="decorator" content="default"/>
</head>
<body >
<script type="text/javascript">
var dg;
$(function(){
	dg=$('#table_dg').datagrid({    
	method: "post",
	url:ctx+'/yhpc/rcjc/lxlist', 
	queryParams: {  		
	    type: 1  		
	  },
	fit : true,
	fitColumns : true,
	border : false,
	idField : 'id',
	striped:true,
	scrollbarSize:0,
	singleSelect:true,
	striped:true,
	columns:[[ 
            {field:'id',title:'id',checkbox:true,width:50,align:'center'}, 
            {field:'m1',title:'检查类型',sortable:false,width:200,align: 'center'}
        ]],
        onLoadSuccess:function(data){
    	},
        onDblClickRow: function (rowindex, rowdata, rowDomElement){
        },
	checkOnSelect:false,
	selectOnCheck:false,
	toolbar:'#dg_tb'
	});
});

//弹窗增加
function add() {
	openDialog("添加检查类型",ctx+"/yhpc/rcjc/lxcreate/1","350px", "150px","");
}

//删除
function del(){
	var rows = dg.datagrid('getChecked');
	if(rows.length==0) {
		layer.msg("请至少勾选一行记录！",{time: 1000});
		return;
	}

	var ids="";
    for(var item of rows){
       if(ids==""){
          ids=item.id;
       }else{
          ids=ids+","+item.id;
       }
    }

	top.layer.confirm('删除后无法恢复您确定要删除？', {icon: 3, title:'提示'}, function(index){
		$.ajax({
			type:'get',
			url:ctx+"/yhpc/rcjc/lxdelete/"+ids,
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
	openDialog("修改检查类型信息",ctx+"/yhpc/rcjc/lxupdate/"+row.id,"350px", "150px","");
	
}

</script>
<!-- 工具栏 -->
   <div id="dg_tb" style="padding:5px;height:auto">

      <div class="row">
         <div class="col-sm-12">
            <div class="pull-left">
                  <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add()" title="添加">
                     <i class="fa fa-plus"></i> 添加
                  </button>
				  <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd()" title="修改">
				  	<i class="fa fa-file-text-o"></i> 修改
				  </button> 
                  <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除">
                     <i class="fa fa-trash-o"></i> 删除
                  </button>
               <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新">
                  <i class="glyphicon glyphicon-repeat"></i> 刷新
               </button>

            </div>
         </div>
      </div>
      
   </div>

   <table id="table_dg"></table> 

</body>
</html>