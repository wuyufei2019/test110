<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>日常检查记录</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/model/js/yhpc/rcaqjc/jcyh/index.js?v=1.5"></script>
</head>
<body >
<script type="text/javascript">
var type = '${type}';
var userid='${userid}';
</script>

		<div class="easyui-tabs" fit="true">

			<shiro:hasPermission name="yhpc:jcyh:viewall">

			<div title="所有隐患" style="height:100%;" data-options="" id="fp">
			<div id="yhpc_jcyh_tb2" style="padding:5px;height:auto">
			      <form id="searchFrom2" action="" style="margin-bottom: 8px;" class="form-inline">
			         <div class="form-group">
			            <c:if test="${type != '1' }">
							<input type="text" id="view_qyname" name="view_qyname" class="easyui-combobox" style="height: 30px;" data-options="editable:true ,valueField: 'text',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson',prompt: '企业名称' "/>	  	 
						</c:if> 
						<input name="yhpc_jcyh_starttime" class="easyui-datebox"  style="height: 30px;" data-options="editable:false,prompt: '检查开始时间'" />
					    <input name="yhpc_jcyh_finishtime" class="easyui-datebox"  style="height: 30px;" data-options="editable:false,prompt: '检查结束时间'" />	
			         	<input style="height: 30px;" name="yhpc_jcyh_yhlb" class="easyui-combobox"  data-options="panelHeight:'120', editable:true ,
												valueField: 'text',textField: 'text',prompt:'隐患类别',
												data: [
											        {value:'火灾',text:'火灾'},
											        {value:'容器爆炸',text:'容器爆炸'},
											        {value:'锅炉爆炸',text:'锅炉爆炸'},
											        {value:'其他爆炸',text:'其他爆炸'},
											        {value:'中毒和窒息',text:'中毒和窒息'},
											        {value:'灼烫',text:'灼烫'},
											        {value:'触电',text:'触电'},
											        {value:'物体打击',text:'物体打击'},
											        {value:'车辆伤害',text:'车辆伤害'},
											        {value:'机械伤害',text:'机械伤害'},
											        {value:'起重伤害',text:'起重伤害'}, 
											        {value:'淹溺',text:'淹溺'},
											        {value:'高处坠落',text:'高处坠落'},
											        {value:'坍塌',text:'坍塌'},
											        {value:'其他伤害',text:'其他伤害'},]"/>
						<input style="height: 30px;" name="yhpc_jcyh_yhdj" class="easyui-combobox"  data-options="panelHeight:'120', editable:false ,
												prompt:'隐患等级',panelHeight:'auto',
												data: [
											        {value:'一般',text:'一般'},
											        {value:'重大',text:'重大'}]"/>					        
			         	<input style="height: 30px;" name="yhpc_jcyh_clzt" class="easyui-combobox"  data-options="panelHeight:'120', editable:false ,
												prompt:'处理状态',panelHeight:'auto',
												data: [
											        {value:'0',text:'未整改'},
											        {value:'1',text:'整改未完成'},
											        {value:'2',text:'整改待复查'},
											        {value:'3',text:'复查不通过'},
											        {value:'4',text:'整改通过'}]"/>
											        
						<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search2()" ><i class="fa fa-search"></i> 查询</span>
						<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset2()" ><i class="fa fa-refresh"></i> 全部</span>       	        
			         </div>
			      </form>
			
			      <div class="row">
					<div class="col-sm-12">
						<div class="pull-left">
							<shiro:hasPermission name="yhpc:jcyh:view">
				        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view()" title="查看"><i class="fa fa-search-plus"></i> 查看</button> 
				        	</shiro:hasPermission>
				        	
				        	<shiro:hasPermission name="yhpc:jcyh:delete">
								<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除"><i class="fa fa-trash-o"></i> 删除</button> 
							</shiro:hasPermission>
				        	
				        	
				        	<%--<shiro:hasPermission name="yhpc:jcyh:exin">
								<button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="openImportDialog('${ctx}/yhpc/jcyh/exinjump','${ctx}/yhpc/jcyh/exin','${ctx}/static/templates/隐患信息导入模板.xls')" title="导入"><i class="fa fa-folder-open-o"></i> 导入</button>
							</shiro:hasPermission>  --%>
					       <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
							</div>
					</div>
				  </div> 
			</div>
			<table id="yhpc_jcyh_dg2"></table> 	
			</div>

			</shiro:hasPermission>



			<div title="我要处理" style="height:100%;" id="rw">
				<div id="yhpc_jcyh_tb" style="padding:5px;height:auto">
				      <form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">
				         <div class="form-group">
				            <c:if test="${type != '1' }">
								<input type="text" id="view_qyname" name="view_qyname" class="easyui-combobox" style="height: 30px;" data-options="editable:true ,valueField: 'text',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson',prompt: '企业名称' "/>	  	 
							</c:if> 
							<input name="yhpc_jcyh_starttime" class="easyui-datebox"  style="height: 30px;" data-options="editable:false,prompt: '检查开始时间'" />
						    <input name="yhpc_jcyh_finishtime" class="easyui-datebox"  style="height: 30px;" data-options="editable:false,prompt: '检查结束时间'" />	
				         	<input style="height: 30px;" name="yhpc_jcyh_yhlb" class="easyui-combobox"  data-options="panelHeight:'120', editable:true ,
													valueField: 'text',textField: 'text',prompt:'隐患类别',
													data: [
												        {value:'火灾',text:'火灾'},
												        {value:'容器爆炸',text:'容器爆炸'},
												        {value:'锅炉爆炸',text:'锅炉爆炸'},
												        {value:'其他爆炸',text:'其他爆炸'},
												        {value:'中毒和窒息',text:'中毒和窒息'},
												        {value:'灼烫',text:'灼烫'},
												        {value:'触电',text:'触电'},
												        {value:'物体打击',text:'物体打击'},
												        {value:'车辆伤害',text:'车辆伤害'},
												        {value:'机械伤害',text:'机械伤害'},
												        {value:'起重伤害',text:'起重伤害'}, 
												        {value:'淹溺',text:'淹溺'},
												        {value:'高处坠落',text:'高处坠落'},
												        {value:'坍塌',text:'坍塌'},
												        {value:'其他伤害',text:'其他伤害'},]"/>
							<input style="height: 30px;" name="yhpc_jcyh_yhdj" class="easyui-combobox"  data-options="panelHeight:'120', editable:false ,
													prompt:'隐患等级',panelHeight:'auto',
													data: [
												        {value:'一般',text:'一般'},
												        {value:'重大',text:'重大'}]"/>					        
				         	<input style="height: 30px;" name="yhpc_jcyh_clzt" class="easyui-combobox"  data-options="panelHeight:'120', editable:false ,
													prompt:'处理状态',panelHeight:'auto',
													data: [
												        {value:'0',text:'未整改'},
												        {value:'1',text:'整改未完成'},
												        {value:'2',text:'整改待复查'},
												        {value:'3',text:'复查不通过'},
												        {value:'4',text:'整改通过'}]"/>
												        
							<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</span>
							<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span>
				         </div>
				      </form>
				
				      <div class="row">
					<div class="col-sm-12">
						<div class="pull-left">
							<shiro:hasPermission name="yhpc:jcyh:view">
				        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view()" title="查看"><i class="fa fa-search-plus"></i> 查看</button> 
				        	</shiro:hasPermission>
							<%--<shiro:hasPermission name="yhpc:jcyh:delete">
								<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除"><i class="fa fa-trash-o"></i> 删除</button>
							</shiro:hasPermission>--%>
				        	<%--<shiro:hasPermission name="yhpc:jcyh:exin">
								<button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="openImportDialog('${ctx}/yhpc/jcyh/exinjump','${ctx}/yhpc/jcyh/exin','${ctx}/static/templates/隐患信息导入模板.xls')" title="导入"><i class="fa fa-folder-open-o"></i> 导入</button>
							</shiro:hasPermission>    --%>
					       <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
							</div>
					</div>
					</div> 
				</div>
				<table id="yhpc_jcyh_dg"></table> 	
			</div>
		</div>

</body>
</html>