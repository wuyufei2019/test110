<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>三同时管理列表</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx }/static/model/js/hjbh/stsgl/index.js?v=1.1"></script>
</head>
<style>
	.datagrid-body {
		overflow: hidden;
	}
</style>
   <div id="hjbh_stsgl_tb" style="padding:5px;height:auto;">
      <form id="searchForm" action="" style="margin-bottom: 8px;" class="form-inline">
         <div class="form-group">
            <c:if test="${type != '1' }">
				<input type="text" id="view_qyname" name="view_qyname" class="easyui-combobox" style="height: 30px;" data-options="editable:true ,valueField: 'text',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson',prompt: '企业名称' "/>	  	 
			</c:if> 
         	<input type="text" id="stsgl_sqyname" name="stsgl_sqyname" class="easyui-textbox" style="height: 30px;"
               data-options="prompt: '企业名称',panelHeight:'100',valueField: 'text',textField: 'text'" />
               <input type="text" id="stsgl_projectname" name="stsgl_projectname" class="easyui-textbox" style="height: 30px;"
               data-options="prompt: '项目名称',panelHeight:'100',valueField: 'text',textField: 'text'" />
            <span class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()"><i class="fa fa-search"></i> 查询</span> <span
               class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()"><i class="fa fa-refresh"></i> 全部</span>
         </div>
      </form>

      <div class="row">
         <div class="col-sm-12">
            <div class="pull-left">
             	<shiro:hasPermission name="hjbh:stsgl:add">
						    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add()" title="添加"><i class="fa fa-plus"></i> 添加</button> 
						</shiro:hasPermission>
						<shiro:hasPermission name="hjbh:stsgl:update">
						    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd()" title="修改"><i class="fa fa-file-text-o"></i> 修改</button> 
						</shiro:hasPermission>
						<shiro:hasPermission name="hjbh:stsgl:delete">
							<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除"><i class="fa fa-trash-o"></i> 删除</button> 
						</shiro:hasPermission>
						<shiro:hasPermission name="hjbh:stsgl:view">
			        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view()" title="查看"><i class="fa fa-search-plus"></i> 查看</button> 
			        	</shiro:hasPermission>
				       <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>

            </div>
         </div>
      </div>

   </div>

   
   
<table id="hjbh_stsgl_dg"></table> 

<script type="text/javascript">
var dg;
var riskid;//全局变量，选中记录的id
var type = '${type}';
$(function(){
	/* if (type == '2') { *///集团登录
		dg=$('#hjbh_stsgl_dg').datagrid({    
			method: "post",
		    url:ctx+'/hjbh/stsgl/list', 
		    fit : true,
			fitColumns : true,
			border : false,
			idField : 'ID',
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
					{field : 'id',title : 'id',checkbox : true,width : 50},
					{field : 'm1',title : '企业名称',sortable : false,width : 50},
		  			{field : 'm3',title : '项目名称',sortable : false,width : 50},
		  			{field : 'm4',title : '建设地址',sortable : false,width : 50},
		  			{field : 'm7',title : '环评批复意见文号',sortable : false,width : 50},
					{field : 'pz',title : '批准部门',sortable : false,width : 50},
					{field : 'm11',title : '验收时间',sortable : false,width : 50,
						formatter : function(value) {
							if (value != null && value != '') {
								var datetime=new Date(value);
			              		return datetime.format('yyyy-MM-dd');	
							}
	              	}	
				}
		    ]],
		    onDblClickRow: function (rowdata, rowindex, rowDomElement){
		        view();
		    },
		    onLoadSuccess: function(){
		    	 $(this).datagrid("autoMergeCells",['qyname']);
		    	 $(this).datagrid("autoMergeCells",['m1']);
		    },
		     onLoadError:function(){
		    	layer.open({title: '提示',offset: 'rb',content: '数据加载中，请耐心等待。',shade: 0 ,time: 2000 });
		    },
			checkOnSelect:false,
			selectOnCheck:false,
			toolbar:'#hjbh_stsgl_tb'
			});
/* 	} */
		
    
});
	
</script>

</body>
</html>