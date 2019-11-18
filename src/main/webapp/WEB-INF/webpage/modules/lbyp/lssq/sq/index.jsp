<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>劳保用品临时申请信息</title>
<meta name="decorator" content="default" />
</head>
<body>
   <!-- 工具栏 -->
   <div id="lbyp_sq_tb" style="padding:5px;height:auto">
      <form id="sqsearchFrom" style="margin-bottom: 8px;" class="form-inline">

         <div class="form-group">
            <input type="text" name="view_starttime" class="easyui-datebox" style="height: 30px;" data-options="prompt: '申请开始时间'" />
            <input type="text" name="view_endtime" class="easyui-datebox" style="height: 30px;" data-options="prompt: '申请结束时间'" />
            <input type="text" name="view_result" class="easyui-combobox" style="height: 30px;"
               data-options="prompt: '审核结果',panelHeight:'auto',valueField: 'value',textField: 'text',
               data : [{'value' : '1','text' : '通过'},{'value' : '0','text' : '未通过'},{'value' : '2','text' : '未审核'}]" />
            <span class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="sqsearch()"><i class="fa fa-search"></i> 查询</span> 
            <span class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="sqreset()"><i class="fa fa-refresh"></i> 全部</span>
         </div>
      </form>

      <div class="row">
         <div class="col-sm-12">
            <div class="pull-left">
               <shiro:hasPermission name="lbyp:lssq:add">
                  <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="sqadd()" title="添加">
                     <i class="fa fa-plus"></i> 添加
                  </button>
               </shiro:hasPermission>
               <shiro:hasPermission name="lbyp:lssq:update">
                  <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="squpd()" title="修改">
                     <i class="fa fa-file-text-o"></i> 修改
                  </button>
               </shiro:hasPermission>
               <shiro:hasPermission name="lbyp:lssq:delete">
                  <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="sqdel()" title="删除">
                     <i class="fa fa-trash-o"></i> 删除
                  </button>
               </shiro:hasPermission>
               <shiro:hasPermission name="lbyp:lssq:view">
                  <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="sqview()" title="查看">
                     <i class="fa fa-search-plus"></i> 查看
                  </button>
               </shiro:hasPermission>
               <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新">
                  <i class="glyphicon glyphicon-repeat"></i> 刷新
               </button>
            </div>
         </div>
      </div>
   </div>
   <table id="lbyp_sq_dg"></table>
   <div id="select_list" style="display:none;height:100%">
      <table id="areadata"></table>
   </div>
   <script type="text/javascript">
   var sqdg;
   $(function(){
	var qyid = '${qyid}';
   	sqdg=$('#lbyp_sq_dg').datagrid({    
   	method: "post",
    url:ctx+'/lbyp/lssq/list?qyid='+qyid,
    queryParam :{"type":"sq"},
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
   	scrollbarSize:0,
   	singleSelect:true,
   	striped:true,
       columns:[[    
           {field:'ID',title:'id',hidden:true,width:50,align:'center'},    
           {field:'sqname',title:'申请人',sortable:false,width:50},    
           {field:'shname',title:'审核人',sortable:false,width:50},    
           {field:'s1',title:'申请时间',sortable:false,width:50,
        	   formatter : function (value, row){
           		if(value) return new Date(value).format("yyyy-MM-dd");
          	 	else return '';
           }},    
           {field:'reason',title:'申请原因',sortable:false,width:100} , 
         
           {field:'result',title:'审核结果',sortable:false,align: 'center',width:40,formatter : function (value, row){
           	if(value) {
       			if(value=="1") return "审核通过--<a class='fa fa-edit btn-success btn-outline' onclick='viewReview("+row.id+")'>查看详情</a>";
       			else return "审核不通过--<a class='fa fa-edit btn-success btn-outline' onclick='viewReview("+row.id+")'>查看详情</a>";
           	}
           	else{
       			return '未审核';
           	}
           }},
           {field:'caozuo',title:'查看申请用品清单',sortable:false,width:40,align: 'center',formatter : function (value, row){
           	return "<a class='btn btn-info btn-xs' onclick='sqviewgoods("+row.id+")'>查看</a>"
           }} 
       ]],
       onDblClickRow: function (rowdata, rowindex, rowDomElement){
               view();
       },
       onLoadSuccess: function(){
           $(this).datagrid("autoMergeCells",['sqname']);
       },
   	checkOnSelect:false,
   	selectOnCheck:false,
       toolbar:'#lbyp_sq_tb'
   	});
   	
   });

   //弹窗增加
   function sqadd(u) {
   	openDialog("添加临时申请记录",ctx+"/lbyp/lssq/create/","800px", "400px","");
   }

   //查看审核
   function viewReview(u) {
   	openDialogView("查看审核信息",ctx+"/lbyp/lssq/view/"+u,"800px", "450px","");
   }

   function sqviewgoods(id) {
   		layer.open({
   			type: 1,  
   			area: ['800px', '300px'],
   			title:'查看用品清单',
   			content: $("#select_list"),
   			btn: ['关闭'],
   		    success: function(layero, index){
   		    	    var d =$('#areadata').datagrid({    
   		    		method: "post",
   		    		url : ctx+"/lbyp/ffjl/list2/"+id,
   		    	    loadMsg :'正在加载',
   		    		fitColumns : true,
   					border : true,
   					rownumbers : true,
   					singleSelect : true,
   					striped : true,
   					nowrap : false,
   					scrollbarSize : 0,
   		    	    columns:[[   
   		    	              {field:'goodsname',title:'用品名称',width:100},
   		    	              {field:'amount',title:'用品数量',width:100}
   		    	]],
   		    	});
   		      },
   			cancel: function(index){ 
   			}
   		});
   }

   //删除
   function sqdel(){
   	var row = sqdg.datagrid('getSelected');
   	if(row==null||row=='') {
   		layer.msg("请至少勾选一行记录！",{time: 1000});
   		return;
   	}
   	if(row.result=="1"){
   		layer.msg("已审批通过，不允许删除！");
   		return ;
   	}
   	top.layer.confirm('删除后无法恢复您确定要删除？', {icon: 3, title:'提示'}, function(index){
   		$.ajax({
   			type:'get',
   			url:ctx+"/lbyp/lssq/delete/"+row.id,
   			success: function(data){
   				layer.alert(data, {offset: 'rb',shade: 0,time: 2000}); 
   				top.layer.close(index);
   				sqdg.datagrid('reload');
   				sqdg.datagrid('clearChecked');
   				sqdg.datagrid('clearSelections');
   			}
   		});
   	});
    
   }

   //弹窗修改
   function squpd(){
   	var row = sqdg.datagrid('getSelected');
   	if(row==null) {
   		layer.msg("请选择一行记录！",{time: 1000});
   		return;
   	}
   	if(row.result=="1"){
   		layer.msg("已审批通过，不允许修改！");
   		return ;
   	}
   	
   	openDialog("修改临时申请记录信息",ctx+"/lbyp/lssq/update/"+row.id,"800px", "400px","");
   	
   }

   //查看
   function sqview(){
   	var row = sqdg.datagrid('getSelected');
   	if(row==null) {
   		layer.msg("请选择一行记录！",{time: 1000});
   		return;
   	}
   	openDialogView("查看审核信息",ctx+"/lbyp/lssq/view/"+row.id,"800px", "450px","");
   }

   //创建查询对象并查询
   function sqsearch(){
   	var obj=$("#sqsearchFrom").serializeObject();
   	sqdg.datagrid('load',obj); 
   }

   function sqreset(){
   	$("#sqsearchFrom").form("clear");
   	var obj=$("#sqsearchFrom").serializeObject();
   	sqdg.datagrid('load',obj); 
   }

   </script>
</body>
</html>