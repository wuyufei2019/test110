<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp" %>
<html>
<head>
    <title>动火作业信息</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript" src="${ctx}/static/model/js/zyaqgl/zyaq/dhzy/act/index.js?v=1.4"></script>
    <script type="text/javascript" src="${ctx}/static/model/js/workflow/workflow.js?v=1.0"></script>
</head>
<body>
<!-- 工具栏 -->
<div id="zyaqgl_dhzy_tb" style="padding:5px;height:auto">
    <form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">
        <div class="form-group">
            <input type="text" id="aqgl_dhzy_cx_m1" name="aqgl_dhzy_cx_m1" class="easyui-textbox" style="height: 30px;"
                   data-options="prompt: '作业证编号 '"/>
            <input class="easyui-combobox" name="aqgl_dhzy_cx_m3" style="height: 30px;"
                   data-options="panelHeight:'auto', prompt: '动火作业级别 ',editable:false ,
                   data: [{value:'全部',text:'全部'},{value:'特殊动火',text:'特殊动火'},
						{value:'一级动火',text:'一级动火'},{value:'二级动火',text:'二级动火'}] "/>
            <span class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()"><i
                    class="fa fa-search"></i> 查询</span>
            <span class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()"><i
                    class="fa fa-refresh"></i> 全部</span>
        </div>
    </form>

    <div class="row">
        <div class="col-sm-12">
            <div class="pull-left">
		<span id="divper">
			<shiro:hasPermission name="zyaqgl:dhzy:add">
                <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add()"
                        title="申请"><i class="fa fa-plus"></i> 申请</button>
            </shiro:hasPermission>
			<shiro:hasPermission name="zyaqgl:dhzy:update">
                <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd()"
                        title="修改"><i class="fa fa-file-text-o"></i> 修改</button>
            </shiro:hasPermission>
			<shiro:hasPermission name="zyaqgl:dhzy:delete">
                <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()"
                        title="删除"><i class="fa fa-trash-o"></i> 删除</button>
            </shiro:hasPermission>
			</span>
                <shiro:hasPermission name="zyaqgl:dhzy:view">
                    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view()"
                            title="查看"><i class="fa fa-search-plus"></i> 查看
                    </button>
                    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="viewflow()"
                            title="查看"><i class="fa fa-search-plus"></i> 查看流程
                    </button>
                </shiro:hasPermission>
                <shiro:hasPermission name="zyaqgl:dhzy:export">
                    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left"
                            onclick="fileexportword()" title="导出Word"><i class="fa fa-file-word-o"></i> 导出Word
                    </button>
                </shiro:hasPermission>
			</span>
                <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()"
                        title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新
                </button>

            </div>
            <div class="pull-right">
            </div>
        </div>
    </div>

</div>


<table id="zyaqgl_dhzy_dg"></table>
<script type="text/javascript">
    var qyid = '${qyid}';
    var type = '${type}';
    userid = '${userid}';
    var userLoginName = '${userLoginName}';
    var fp = 0;//是否具有分配权限的标识
    var fx = 0;//是否具有分析权限的标识
</script>
<shiro:hasPermission name="zyaqgl:dhzy:fp">
    <script type="text/javascript">
        fp = 1;
    </script>
</shiro:hasPermission>
<shiro:hasPermission name="zyaqgl:dhzy:fx">
    <script type="text/javascript">
        fx = 1;
    </script>
</shiro:hasPermission>
</body>
</html>