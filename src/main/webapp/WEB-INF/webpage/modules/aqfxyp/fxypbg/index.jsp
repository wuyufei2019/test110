<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
    <title>风险研报告信息</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript" src="${ctx}/static/model/js/aqfxyp/fxypbg/index.js?v=0.2"></script>
    <script type="text/javascript">
        var type='${type}';
        var usertype = '${usertype}';
    </script>
</head>
<body >
<!-- 工具栏 -->
<div id="aqfxyp_fxypbg_tb" style="padding:5px;height:auto">
    <form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">
        <div class="form-group">
            <c:if test="${ type eq '2' }">
                <input type="text" id="view_qyname" name="view_qyname" class="easyui-combobox"  style="height: 30px;" data-options="editable:true ,valueField: 'text',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson',prompt: '企业名称' "/>
            </c:if>
            <input type="text" id="m1" name="m1" class="easyui-combobox" style="height: 30px;" data-options="prompt: '等级',editable:false,panelHeight:'auto',data:[{value:'厂级',text:'厂级'},{value:'车间级',text:'车间级'},{value:'班组级',text:'班组级'}]"/>
            <input type="text" id="m2" name="m2" class="easyui-textbox" style="height: 30px;" data-options="prompt: '车间',panelHeight:'auto'"/>
            <input type="text" id="m3" name="m3" class="easyui-textbox" style="height: 30px;" data-options="prompt: '班组',panelHeight:'auto'"/>
            <input type="text" id="m10" name="m10" class="easyui-textbox" style="height: 30px;" data-options="prompt: '负责人',panelHeight:'auto'"/>
            <span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</span>
            <span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span>
        </div>
    </form>

    <div class="row">
        <div class="col-sm-12">
            <div class="pull-left">
		<span id="divper">
            <shiro:hasPermission name="aqfxyp:fxypbg:add">
                <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add()" title="添加"><i class="fa fa-file-text-o"></i> 添加</button>
            </shiro:hasPermission>
			<shiro:hasPermission name="aqfxyp:fxypbg:update">
                <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd()" title="修改"><i class="fa fa-file-text-o"></i> 修改</button>
            </shiro:hasPermission>
			<shiro:hasPermission name="aqfxyp:fxypbg:delete">
                <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除"><i class="fa fa-trash-o"></i> 删除</button>
            </shiro:hasPermission>
            <shiro:hasPermission name="aqfxyp:fxypbg:export">
                <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="fileexportword()" title="导出Word"><i class="fa fa-file-word-o"></i> 导出Word</button>
            </shiro:hasPermission>
		</span>
                <shiro:hasPermission name="aqfxyp:fxypbg:view">
                    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view()" title="查看"><i class="fa fa-search-plus"></i> 查看</button>
                </shiro:hasPermission>
                <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>

            </div>
        </div>
    </div>

</div>
<table id="aqfxyp_fxypbg_dg"></table>

</body>
</html>