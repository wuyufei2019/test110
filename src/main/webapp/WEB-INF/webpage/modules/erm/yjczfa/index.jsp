<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
    <title>应急处置方案信息</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript" src="${ctx}/static/model/js/erm/yjczfa/index.js?v=0.1"></script>
    <script type="text/javascript">
        var type='${type}';
        var usertype = '${usertype}';
    </script>
</head>
<body >
<!-- 工具栏 -->
<div id="erm_yjczfa_tb" style="padding:5px;height:auto">
    <form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">
        <div class="form-group">
            <c:if test="${ type eq '2' }">
                <input type="text" id="view_qyname" name="view_qyname" class="easyui-combobox"  style="height: 30px;" data-options="editable:true ,valueField: 'text',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson',prompt: '企业名称' "/>
            </c:if>
            <input type="text" id="m1" name="m1" class="easyui-combobox" style="height: 30px;" data-options="prompt: '等级',editable:false,panelHeight:'auto',data:[{value:'厂级',text:'厂级'},{value:'车间级',text:'车间级'},{value:'班组级',text:'班组级'}]"/>
            <input type="text" id="m2" name="m2" class="easyui-combobox" style="height: 30px;" data-options="prompt: '岗位名称',editable:false,panelHeight:'auto',data:[{value:'总指挥(主要负责人)_1',text:'总指挥(主要负责人)'},
                       {value:'副总指挥(应急负责人)_1',text:'副总指挥(应急负责人)'},
                       {value:'应急抢险组_2',text:'应急抢险组'},
                       {value:'污染防控组_2',text:'污染防控组'},
                       {value:'后勤保障组_2',text:'后勤保障组'},
                       {value:'警戒保卫组_2',text:'警戒保卫组'},
                       {value:'医疗救护组_2',text:'医疗救护组'},
                       {value:'甲醛生产线_3',text:'甲醛生产线'},
                       {value:'原料成品罐区(火灾)_3',text:'原料成品罐区(火灾)'},
                       {value:'原料成品罐区(泄漏)_3',text:'原料成品罐区(泄漏)'},
                       {value:'装车台(火灾)_3',text:'装车台(火灾)'},
                       {value:'装车台(泄漏)_3',text:'装车台(泄漏)'}]"/>
            <span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</span>
            <span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span>
        </div>
    </form>

    <div class="row">
        <div class="col-sm-12">
            <div class="pull-left">
		<span id="divper">
            <shiro:hasPermission name="erm:yjczfa:add">
                <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add()" title="添加"><i class="fa fa-file-text-o"></i> 添加</button>
            </shiro:hasPermission>
			<shiro:hasPermission name="erm:yjczfa:update">
                <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd()" title="修改"><i class="fa fa-file-text-o"></i> 修改</button>
            </shiro:hasPermission>
			<shiro:hasPermission name="erm:yjczfa:delete">
                <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除"><i class="fa fa-trash-o"></i> 删除</button>
            </shiro:hasPermission>
		</span>
                <shiro:hasPermission name="erm:yjczfa:view">
                    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view()" title="查看"><i class="fa fa-search-plus"></i> 查看</button>
                </shiro:hasPermission>
                <shiro:hasPermission name="erm:yjczfa:export">
                    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="fileexportword()" title="导出Word"><i class="fa fa-file-word-o"></i> 导出Word</button>
                </shiro:hasPermission>

                <%--<shiro:hasPermission name="erm:yjczfa:export">
                    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="exportword()" title="导出"><i class="fa fa-external-link"></i> 导出</button>
                </shiro:hasPermission>

                <span id="divper2">
        	<shiro:hasPermission name="erm:yjczfa:exin">
                <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="openImportDialog('${ctx}/erm/yjyl/yjczfa/yjdw/exinjump','${ctx}/erm/yjyl/yjczfa/yjdw/exin','${ctx}/static/templates/应急队伍导入模板.xls')" title="导入"><i class="fa fa-folder-open-o"></i> 导入</button>
            </shiro:hasPermission>
                </span>--%>
                <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>

            </div>
        </div>
    </div>

</div>
<table id="erm_yjczfa_dg"></table>

</body>
</html>