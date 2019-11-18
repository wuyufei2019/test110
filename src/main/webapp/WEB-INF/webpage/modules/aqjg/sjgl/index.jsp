<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>事件信息</title>
<meta name="decorator" content="default" />
<script type="text/javascript"
	src="${ctx}/static/model/js/aqjg/sjgl/index.js?v=1.4"></script>
</head>
<body>
	<!-- 工具栏 -->
	<div id="aqjg_sjgl_tb" style="padding:5px;height:auto">
		<form id="searchFrom" action="" style="margin-bottom: 8px;"
			class="form-inline">
			<div class="form-group">
				<input type="text" name="aqjg_sjgl_dwname" class="easyui-textbox"
					style="height: 30px;" data-options="prompt: '单位名称'" /> <input
					type="text" name="aqjg_sjgl_sgtype" class="easyui-combobox"
					style="height: 30px;"
					data-options="panelHeight:'auto' ,editable:false ,valueField: 'value',textField: 'text',prompt: '事故类型',data: [
								        {value:'wtdj',text:'物体打击'},
								        {value:'zt',text:'灼烫'},
								        {value:'wsbz',text:'瓦斯爆炸'},
								        {value:'clsh',text:'车辆伤害'},
								        {value:'hz',text:'火灾'},
								        {value:'hybz',text:'火药爆炸'},
								        {value:'jxsh',text:'机械伤害'},
								        {value:'gczl',text:'高处坠落'},
								        {value:'glbz',text:'锅炉爆炸'},
								        {value:'qzsh',text:'起重伤害'},
								        {value:'tt',text:'坍塌'},
								        {value:'rqbz',text:'容器爆炸'},
								        {value:'cd',text:'触电'},
								        {value:'mdpb',text:'冒顶片帮'},
								        {value:'qtbz',text:'其他爆炸'},
								        {value:'yn',text:'淹溺'},
								        {value:'ts',text:'透水'},
								        {value:'zdhzx',text:'中毒和窒息'},
								        {value:'fp',text:'放炮'},
								        {value:'qtsh',text:'其他伤害'} ] " />
				<input type="text" id="aqjg_sjgl_sglevel" name="aqjg_sjgl_sglevel"
					class="easyui-combobox" style="height: 30px;"
					data-options="panelHeight:'auto' ,editable:false ,valueField: 'value',textField: 'text',prompt: '事故等级',data: [
								        {value:'0',text:'特别重大事故'},
								        {value:'1',text:'重大事故'},
								        {value:'2',text:'较大事故'},
								        {value:'3',text:'一般事故'} ]" />
	<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</span>
	<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span>
			</div>
		</form>
		<div class="row">
			<div class="col-sm-12">
				<div class="pull-left">
					<shiro:hasPermission name="aqjg:sjgl:add">
						<button class="btn btn-white btn-sm" data-toggle="tooltip"
							data-placement="left" onclick="add()" title="添加">
							<i class="fa fa-plus"></i> 添加
						</button>
					</shiro:hasPermission>
					<shiro:hasPermission name="aqjg:sjgl:update">
						<button class="btn btn-white btn-sm" data-toggle="tooltip"
							data-placement="left" onclick="upd()" title="修改">
							<i class="fa fa-file-text-o"></i> 修改
						</button>
					</shiro:hasPermission>
					<shiro:hasPermission name="aqjg:sjgl:delete">
						<button class="btn btn-white btn-sm" data-toggle="tooltip"
							data-placement="left" onclick="del()" title="删除">
							<i class="fa fa-trash-o"></i> 删除
						</button>
					</shiro:hasPermission>
					<shiro:hasPermission name="aqjg:sjgl:view">
						<button class="btn btn-white btn-sm" data-toggle="tooltip"
							data-placement="left" onclick="view()" title="查看">
							<i class="fa fa-search-plus"></i> 查看
						</button>
					</shiro:hasPermission>
					<shiro:hasPermission name="aqjg:sjgl:export">
						<button class="btn btn-white btn-sm" data-toggle="tooltip"
							data-placement="left" onclick="fileexport()" title="导出">
							<i class="fa fa-external-link"></i> 导出
						</button>
					</shiro:hasPermission>
					<%-- <shiro:hasPermission name="aqjg:sjgl:exin">
						<button class="btn btn-white btn-sm " data-toggle="tooltip"
							data-placement="left"
							onclick="openImportDialog('${ctx}/bis/cjxx/exinjump','${ctx}/bis/cjxx/exin','${ctx}/static/templates/车间信息导入模板.xls')"
							title="导入">
							<i class="fa fa-folder-open-o"></i> 导入
						</button>
					</shiro:hasPermission> --%>
					<button class="btn btn-white btn-sm " data-toggle="tooltip"
						data-placement="left" onclick="refresh()" title="刷新">
						<i class="glyphicon glyphicon-repeat"></i> 刷新
					</button>

				</div>
			</div>
		</div>

	</div>


	<table id="aqjg_sjgl_dg"></table>

</body>
</html>