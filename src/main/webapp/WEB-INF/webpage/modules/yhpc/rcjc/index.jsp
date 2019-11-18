<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>隐患排查---日常检查信息信息</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src=" ${ctx}/static/model/js/yhpc/rcjc/index2.js?v=1.2"></script>
</head>
<body >
<script type="text/javascript">
var type = '${type}';
var userid='${userid}';
</script>
<!-- 工具栏 -->
   <div id="dg_tb" style="padding:5px;height:auto">
      <form id="searchFrom" style="margin-bottom: 8px;" class="form-inline">
         <div class="form-group">
         	<c:if test="${type != '1' }">
				<input type="text" id="view_qyname" name="view_qyname" class="easyui-combobox" style="height: 30px;" data-options="editable:true ,valueField: 'text',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson',prompt: '企业名称' "/>	  	 
			</c:if>          
			<input name="yhpc_jcyh_starttime" class="easyui-datebox"  style="height: 30px;width: 120px;" data-options="editable:false,prompt: '检查开始时间'" />
		    <input name="yhpc_jcyh_finishtime" class="easyui-datebox"  style="height: 30px;width: 120px;" data-options="editable:false,prompt: '检查结束时间'" />
         	<input style="height: 30px;width: 140px;" name="yhpc_jcyh_jclx" class="easyui-combobox"  data-options="panelHeight:'120', editable:true ,
									valueField: 'text',textField: 'text',prompt:'检查类型',url:'${ctx }/yhpc/rcjc/lxjson/1'"/>
         	<input style="height: 30px;width: 140px;" name="yhpc_jcyh_qslx" class="easyui-combobox"  data-options="panelHeight:'120', editable:true ,
									valueField: 'text',textField: 'text',prompt:'缺失类型',url:'${ctx }/yhpc/rcjc/lxjson/2'"/>
			
			<input style="height: 30px;width: 100px;" name="yhpc_jcyh_yhdj" class="easyui-combobox"  data-options="panelHeight:'120', editable:false ,
									prompt:'严重程度',panelHeight:'auto',
									data: [ {value:'A',text:'A'},
								            {value:'B',text:'B'},
								            {value:'C',text:'C'}]"/>
             <input style="height: 30px;width: 100px;" name="yhpc_jcyh_shstate" class="easyui-combobox"  data-options="panelHeight:'120', editable:false ,
									prompt:'审核状态',panelHeight:'auto',
									data: [ {value:'0',text:'待审核'},
								            {value:'1',text:'审核通过'},
								            {value:'2',text:'审核未通过'}]"/>
             <input style="height: 30px;width: 100px;" name="yhpc_jcyh_zgstate" class="easyui-combobox"  data-options="panelHeight:'120', editable:false ,
									prompt:'整改状态',panelHeight:'auto',
									data: [ {value:'1',text:'待整改'},
								            {value:'2',text:'整改待审核'},
								            {value:'3',text:'审核不通过'},
								            {value:'4',text:'整改完成'}]"/>

			<span class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()"><i class="fa fa-search"></i> 查询</span> <span
               class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()"><i class="fa fa-refresh"></i> 全部</span>
         </div>
      </form>

      <div class="row">
         <div class="col-sm-12">
            <div class="pull-left">
               <shiro:hasPermission name="yhpc:rcjc:add">
                  <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add()" title="添加">
                     <i class="fa fa-plus"></i> 添加
                  </button>
               </shiro:hasPermission>
               <shiro:hasPermission name="yhpc:rcjc:update">
        			<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd()" title="修改"><i class="fa fa-file-text-o"></i> 修改</button> 
        	   </shiro:hasPermission>
               <shiro:hasPermission name="yhpc:rcjc:delete">
                  <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除">
                     <i class="fa fa-trash-o"></i> 删除
                  </button>
               </shiro:hasPermission>
               <shiro:hasPermission name="yhpc:rcjc:view">
                  <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view()" title="查看">
                     <i class="fa fa-search-plus"></i> 查看
                  </button>
               </shiro:hasPermission>
	           <shiro:hasPermission name="yhpc:rcjc:export">
	        	   <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="fileexport()" title="导出"><i class="fa fa-external-link">
	        	   		</i> 导出
	        	   </button> 
	           </shiro:hasPermission>
	           <shiro:hasPermission name="yhpc:rcjc:exin">
					<button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="openImportDialog('${ctx}/yhpc/rcjc/exinjump','${ctx}/yhpc/rcjc/exin','${ctx}/static/templates/日常检查信息导入模板.xls')" title="导入">
						<i class="fa fa-folder-open-o"></i> 导入
					</button>
			   </shiro:hasPermission>
			   <shiro:hasPermission name="yhpc:rcjc:lxgl">
			   		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="lxgl()" title="类型管理"><i class="fa fa-file-text-o"></i> 类型管理</button>               
			   </shiro:hasPermission>
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