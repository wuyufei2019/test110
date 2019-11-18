<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp" %>
<html>
<head>
    <title>报警管理列表</title>
    <meta name="decorator" content="default"/>
</head>
<body>
<!-- 工具栏 -->
<div id="lydw_bjgl_tb" style="padding:5px;height:auto">
    <form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">
        <div class="form-group">
            <input name="lydw_bjgl_cx_rq" class="easyui-datebox" style="width: 120px;height: 30px;"
                   data-options="editable:false,prompt: '日期'"/>

            <span class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()"><i
                    class="fa fa-search"></i> 查询</span>
            <span class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()"><i
                    class="fa fa-refresh"></i> 全部</span>
        </div>
    </form>
    <div class="row">
        <div class="col-sm-12">
            <div class="pull-left">
                <%--
                            <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除"><i class="fa fa-trash-o"></i> 删除</button>
                --%>
                <%--<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view()" title="查看"><i class="fa fa-search-plus"></i> 查看</button>--%>

                <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()"
                        title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新
                </button>
            </div>
        </div>
    </div>
</div>
<table id="lydw_bjgl_dg"></table>

<script type="text/javascript">
    var dg;
    $(function () {
        dg = $('#lydw_bjgl_dg').datagrid({
            method: "post",
            url: ctx +'/lydw/bjjl/list',
            fit: true,
            fitColumns: true,
            border: false,
            idField: 'id',
            striped: true,
            pagination: true,
            rownumbers: true,
            nowrap: false,
            pageNumber: 1,
            pageSize: 50,
            pageList: [50, 100, 150, 200, 250],
            scrollbarSize: 5,
            singleSelect: true,
            striped: true,
            columns: [[
                {field: 'id', title: 'id', checkbox: true, width: 50, align: 'center'},
                {field: 'username', title: '人员', sortable: false, width: 60},
                {
                    field: 'type', title: '报警类型', sortable: false, width: 100, align: 'center',
                    formatter: function (value, row) {
                        var a = "";
                        if (value == '1') {
                            a = '人员串岗';
                        } else if (value == '2') {
                            a = '人员超限';
                        } else if (value == '3') {
                            a = '员工报警';
                        }
                        return a;
                    }
                },
                {field: 'message', title: '报警信息', sortable: false, width: 100, align: 'center'},
                {
                    field: 'date', title: '报警时间', sortable: false, width: 40, align: 'center',
                    formatter: function (value) {
                        if (value) {
                            return new Date(value).format("yyyy-MM-dd");
                        }
                    }
                }
            ]],
            onDblClickRow: function (rowdata, rowindex, rowDomElement) {

            },
            checkOnSelect: false,
            selectOnCheck: false,
            toolbar: '#lydw_bjgl_tb'
        });

    });

    //创建查询对象并查询
    function search() {
        var obj = $("#searchFrom").serializeObject();
        dg.datagrid('load', obj);
    }

    function reset() {
        $("#searchFrom").form("clear");
        var obj = $("#searchFrom").serializeObject();
        dg.datagrid('load', obj);
    }

    //弹窗修改
    function upd(id) {
        var row = dg.datagrid('getSelected');
        if (id == null) {
            if (row == null) {
                layer.msg("请选择一行记录！", {time: 1000});
                return;
            } else {
                id = row.id;
            }
        }

        openDialog("修改报警信息", ctx + "/lydw/bjgl/update/" + id, "400px", "350px", "");

    }

    function del() {
        var row = dg.datagrid('getChecked');
        if (row == null || row == '') {
            layer.msg("请至少勾选一行记录！", {time: 1000});
            return;
        }

        var ids = "";
        for (var i = 0; i < row.length; i++) {
            if (ids == "") {
                ids = row[i].id;
            } else {
                ids = ids + "," + row[i].id;
            }
        }

        top.layer.confirm('删除后无法恢复您确定要删除？', {icon: 3, title: '提示'}, function (index) {
            $.ajax({
                type: 'get',
                url: ctx + "/lydw/bjgl/delete/" + ids,
                success: function (data) {
                    layer.alert(data, {offset: 'rb', shade: 0, time: 2000});
                    top.layer.close(index);
                    dg.datagrid('reload');
                    dg.datagrid('clearChecked');
                    dg.datagrid('clearSelections');
                }
            });
        });
    }

</script>
</body>
</html>