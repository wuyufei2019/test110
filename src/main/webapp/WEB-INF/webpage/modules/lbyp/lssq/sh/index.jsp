<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>劳保用品临时申请信息</title>
<meta name="decorator" content="default" />
</head>
<body>
	<!-- 工具栏 -->
   <div id="lbyp_sh_tb" style="padding:5px;height:auto">
      <form id="shsearchFrom" action="" style="margin-bottom: 8px;" class="form-inline">

         <div class="form-group">
            <input type="text" name="view_starttime" class="easyui-datebox" style="height: 30px;" data-options="prompt: '申请开始时间'" />
            <input type="text" name="view_endtime" class="easyui-datebox" style="height: 30px;" data-options="prompt: '申请结束时间'" />
             <input type="text" name="view_result" class="easyui-combobox" style="height: 30px;"
               data-options="prompt: '审核结果',panelHeight:'auto',valueField: 'value',textField: 'text',
               data : [{'value' : '1','text' : '通过'},{'value' : '0','text' : '未通过'},{'value' : '2','text' : '未审核'}]" />
            <span class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()"><i class="fa fa-search"></i> 查询</span> <span
               class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()"><i class="fa fa-refresh"></i> 全部</span>
         </div>
      </form>

      <div class="row">
         <div class="col-sm-12">
            <div class="pull-left">
               <shiro:hasPermission name="lbyp:lssq:update">
                  <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd()" title="修改">
                     <i class="fa fa-file-text-o"></i> 修改
                  </button>
               </shiro:hasPermission>
               <shiro:hasPermission name="lbyp:lssq:view">
                  <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view()" title="查看">
                     <i class="fa fa-search-plus"></i> 查看
                  </button>
               </shiro:hasPermission>
               <%-- 	<shiro:hasPermission name="lbyp:lssq:export">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="fileexport()" title="导出"><i class="fa fa-external-link"></i> 导出</button> 
        	</shiro:hasPermission>
        	<shiro:hasPermission name="lbyp:lssq:exin">
				<button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="openImportDialog('${ctx}/lbyp/lssq/exinjump','${ctx}/lbyp/lssq/exin','${ctx}/static/templates/仓库信息导入模板.xls')" title="导入"><i class="fa fa-folder-open-o"></i> 导入</button>
			</shiro:hasPermission> --%>
               <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新">
                  <i class="glyphicon glyphicon-repeat"></i> 刷新
               </button>

            </div>
         </div>
      </div>

   </div>


   <table id="lbyp_sh_dg"></table>
   <div id="select_sh_list" style="display:none;height:100%">
      <table id="shareadata"></table>
   </div>
   <script type="text/javascript">
	var dg;
	var d;
	$(function(){
		var qyid = '${qyid}';
		dg=$('#lbyp_sh_dg').datagrid({    
		method: "post",
	    url:ctx+'/lbyp/lssq/list?qyid='+qyid,
	    queryParam :{"type":"sh"},
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
	        {field:'s1',title:'申请时间',sortable:false,width:50,formatter : function (value, row){
	        	if(value) return new Date(value).format("yyyy-MM-dd");
	        	else return '';
	        }},    
	        {field:'reviewtime',title:'审核时间',sortable:false,width:50,formatter : function (value, row){
	        	if(value) return new Date(value).format("yyyy-MM-dd");
	        	else return '';
	        }},    
	        {field:'reason',title:'申请原因',sortable:false,width:100} , 
	      
	        {field:'result',title:'审核结果',sortable:false,align: 'center',width:40,formatter : function (value, row){
	        	if(value) {
        			if(value=="1") return "审核通过--<a class='fa fa-edit btn-danger btn-outline' onclick='addReview("+row.id+")'>重新审核</a>";
        			else return "审核不通过--<a class='fa fa-edit btn-danger btn-outline' onclick='addReview("+row.id+")'>重新审核</a>";
	        	}
	        	else{
	        		return "<a class='btn btn-info btn-xs' onclick='addReview("+row.id+")'>审核</a>";
	        	}
	        }},
	        {field:'caozuo',title:'查看申请用品清单',sortable:false,width:40,align: 'center',formatter : function (value, row){
	        	return "<a class='btn btn-info btn-xs' onclick='viewgoods("+row.id+")'>查看</a>"
	        }} 
	    ]],
	    onDblClickRow: function (rowdata, rowindex, rowDomElement){
	                   view();
	    },
	    onLoadSuccess: function(){
	    },
		checkOnSelect:false,
		selectOnCheck:false,
	    toolbar:'#lbyp_sh_tb'
		});
		
	});

	//弹窗审核
	function addReview(u) {
		openDialog("审核信息",ctx+"/lbyp/lssq/review/"+u,"800px", "450px","");
	}
	//查看审核
	function viewReview(u) {
		openDialogView("查看审核信息",ctx+"/lbyp/lssq/view/"+u,"800px", "450px","");
	}
	function viewgoods(id) {
			layer.open({
				type: 1,  
				area: ['800px', '300px'],
				title:'查看用品清单',
				content: $("#select_sh_list"),
				btn: ['关闭'],
			    success: function(layero, index){
			    	    d =$('#shareadata').datagrid({    
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

	//弹窗修改
	function upd(){
		var row = dg.datagrid('getSelected');
		if(row==null) {
			layer.msg("请选择一行记录！",{time: 1000});
			return;
		}
		openDialog("审核信息",ctx+"/lbyp/lssq/review/"+row.id,"800px", "450px","");

	}

	//查看
	function view(){
		var row = dg.datagrid('getSelected');
		if(row==null) {
			layer.msg("请选择一行记录！",{time: 1000});
			return;
		}
		openDialogView("查看审核信息",ctx+"/lbyp/lssq/view/"+row.id,"800px", "450px","");
	}

	//创建查询对象并查询
	function search(){
		var obj=$("#shsearchFrom").serializeObject();
		dg.datagrid('load',obj); 
	}

	function reset(){
		$("#shsearchFrom").form("clear");
		var obj=$("#shsearchFrom").serializeObject();
		dg.datagrid('load',obj); 
	}


	</script>
</body>
</html>