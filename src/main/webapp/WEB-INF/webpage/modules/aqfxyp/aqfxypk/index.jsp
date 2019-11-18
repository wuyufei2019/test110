<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
    <title>安全风险研判库信息</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript" src="${ctx}/static/model/js/aqfxyp/aqfxypk/index.js?v=0.1"></script>
    <script type="text/javascript">
        var type='${type}';
        var usertype = '${usertype}';
    </script>
</head>
<body >
<!-- 工具栏 -->
<div id="aqfxyp_aqfxypk_tb" style="padding:5px;height:auto">
    <form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">
        <div class="form-group">
            <c:if test="${ type eq '2' }">
                <input type="text" id="view_qyname" name="view_qyname" class="easyui-combobox"  style="height: 30px;" data-options="editable:true ,valueField: 'text',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson',prompt: '企业名称' "/>
            </c:if>
            <input type="text" id="m1" name="m1" class="easyui-combobox" style="height: 30px;" data-options="prompt: '大类',editable:false,panelHeight:'auto',data:
                       [{value:'1',text:'生产装置安全运行状态'},
                       {value:'2',text:'危险化学品、罐区、仓库等重大危险源的安全运行状态'},
                       {value:'3',text:'高危生产活动及作业的安全风险可控状态'}]"/>
            <input type="text" id="m2" name="m2" class="easyui-textbox" style="height: 30px;" data-options="prompt: '小类',panelHeight:'auto'"/>
            <span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</span>
            <span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span>
        </div>
    </form>

    <div class="row">
        <div class="col-sm-12">
            <div class="pull-left">
		<span id="divper">
            <shiro:hasPermission name="aqfxyp:aqfxypk:add">
                <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add()" title="添加"><i class="fa fa-file-text-o"></i> 添加</button>
            </shiro:hasPermission>
			<shiro:hasPermission name="aqfxyp:aqfxypk:update">
                <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd()" title="修改"><i class="fa fa-file-text-o"></i> 修改</button>
            </shiro:hasPermission>
			<shiro:hasPermission name="aqfxyp:aqfxypk:delete">
                <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除"><i class="fa fa-trash-o"></i> 删除</button>
            </shiro:hasPermission>
		</span>
                <shiro:hasPermission name="aqfxyp:aqfxypk:view">
                    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view()" title="查看"><i class="fa fa-search-plus"></i> 查看</button>
                </shiro:hasPermission>
                <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>

            </div>
        </div>
    </div>

</div>
<table id="aqfxyp_aqfxypk_dg"></table>

</body>
</html>