<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>员工信息</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/model/js/qyxx/ygxx/index.js?v=1.9"></script>
</head>
<body >
<!-- 工具栏 -->
<div id="bis_ygxx_tb" style="padding:5px;height:auto">
	<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">				
	<div class="form-group">
	<input type="text" id="bis_ygxx_cx_m2" name="bis_ygxx_cx_m2" class="easyui-textbox"  style="height: 30px;" data-options="prompt: '工号'"/>
	<input type="text" id="bis_ygxx_cx_m1" name="bis_ygxx_cx_m1" class="easyui-textbox"  style="height: 30px;" data-options="prompt: '姓名'"/>
	<input type="text" name="bis_ygxx_cx_m4" class="easyui-textbox" style="height: 30px;" data-options="prompt: '职位' " />
	<input type="text" name="bis_ygxx_cx_status" class="easyui-combobox" style="height: 30px;" data-options="prompt: '人员状态',editable:false ,panelHeight:'auto', data: [
										  {value:'1',text:'正常'},
								          {value:'2',text:'离职'}]" />
	<input type="text" name="bis_ygxx_cx_m17" class="easyui-textbox" style="height: 30px;" data-options="prompt: '二维码' " />
	<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</span>
	<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span>
    </div>
	</form>
	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
			<shiro:hasPermission name="bis:ygxx:add">
		       		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add()" title="添加"><i class="fa fa-plus"></i> 添加</button>
			</shiro:hasPermission>
			<shiro:hasPermission name="bis:ygxx:update">
			    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd()" title="修改"><i class="fa fa-file-text-o"></i> 修改</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="bis:ygxx:delete">
				<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除"><i class="fa fa-trash-o"></i> 删除</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="bis:ygxx:view">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view()" title="查看"><i class="fa fa-search-plus"></i> 查看</button> 
        	</shiro:hasPermission>
        	<shiro:hasPermission name="bis:ygxx:export">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="fileexport()" title="导出"><i class="fa fa-external-link"></i> 导出</button>
        	</shiro:hasPermission>
        	<shiro:hasPermission name="bis:ygxx:xycdexin">
				<button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="openImportDialog('${ctx}/bis/ygxx/exinjump','${ctx}/bis/ygxx/exin','${ctx}/static/templates/员工信息导入模板2.xls')" title="导入"><i class="fa fa-folder-open-o"></i> 导入</button>
			</shiro:hasPermission>
			<shiro:hasPermission name="bis:ygxx:bind">
                <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="connectUserAccount()" title="关联"><i class="fa fa-exchange"></i> 关联用户信息</button>
            </shiro:hasPermission>
            <shiro:hasPermission name="bis:ygxx:exportword1">
            	<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="fileexportword()" title="导出个人档案"><i class="fa fa-file-word-o"></i> 导出个人档案</button>
            </shiro:hasPermission>
            <shiro:hasPermission name="bis:ygxx:exportword2">
            	<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="fileexportword2()" title="导出职业健康档案"><i class="fa fa-file-word-o"></i> 导出职业健康档案</button>
            </shiro:hasPermission>
            <shiro:hasPermission name="bis:ygxx:disable">
				<button class="btn btn-danger btn-sm" data-toggle="tooltip" data-placement="left" onclick="disableYg()" title="离职"> 离职</button>
			</shiro:hasPermission>
			<shiro:hasPermission name="bis:ygxx:enable">
				<button class="btn btn-success btn-sm" data-toggle="tooltip" data-placement="left" onclick="enableYg()" title="恢复"> 恢复</button>
			</shiro:hasPermission>
            <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
			</div>
	</div>
	</div>    
</div>
<table id="bis_ygxx_dg"></table> 
</body>
</html>