var dg;
var d;
$(function () {
    dg = $('#dg').datagrid({
        method: "post",
        url: ctx + '/task/todo/list',
        fit: true,
        fitColumns: true,
        border: false,
        idField: 'id',
        striped: true,
        pagination: true,
        rownumbers: true,
        nowrap: false,
        scrollbarSize: 5,
        singleSelect: true,
        striped: true,
        columns: [[
            {field: 'id', title: 'id', checkbox: true, width: 50, align: 'center'},
            {field: 'pdname', title: '流程名称', sortable: false, width: 50, align: 'center'},
            {field: 'pdversion', title: '流程版本', sortable: false, width: 20, align: 'center'},
            {
                field: 'createTime',
                title: '任务创建日期',
                sortable: false,
                width: 50,
                align: 'center',
                formatter: function (value) {
                    return new Date(value).format("yyyy-MM-dd hh:mm:ss")
                }
            },
            {
                field: 'name',
                title: '当前节点（点击查看流程图）',
                sortable: false,
                width: 50,
                align: 'center',
                formatter: function (value, row) {
                    return '<a class="trace" pdid="'+row.pdid+'" status="finished" pid="' + row.pid
                        + '" href=\'#\' title="点击查看流程图">' + value + ' </a>'

                }
            },
            {
                field: 'status',
                title: '操作',
                sortable: false,
                width: 50,
                align: 'center',
                formatter: function (value, row) {
                    console.log(row);
                    if (value == 'claim') {
                        return '<a href="#" onclick="claim(\'' + row.id + '\')">签收</a>';
                    } else if (value = 'todo') {
                        return '<a href="#" onclick="handle(\''
                            + row.url + '\',\'' + row.name
                            + '\',\'' + row.pid + '\',\'' + row.id + '\')">办理</a>';
                    }

                }
            }

        ]],
        onDblClickRow: function () {
            view();
        },
        checkOnSelect: false,
        selectOnCheck: false,
        onLoadSuccess: function () {
            $('.trace').click(graphTrace);
        },
        toolbar: '#dg_tb'
    });
});

function claim(taskId) {
    layer.load();
    setTimeout(function () {
        $.post(ctx + '/task/claim/' + taskId, function (data) {
            layer.closeAll();
            if (data == 'success') {
                layer.msg("签收成功！");
            } else {
                layer.msg("签收失败！");
            }
            dg.datagrid("reload");
        });
    }, 200);

}


function handle(url, name, pid, taskId) {
    var layerOpts = {
        type: 2,
        shift: 1,
        area: ['100%', '100%'],
        title: "流程办理[" + name + "]",
        content: ctx + '/task/create/business/' + pid + "?url=" + url + "&taskId=" + taskId
    };
    $.ajax({
        url: ctx + '/task/getHandleBtn/' + pid,
        asyn: false,
        success: function (data) {
            var btn = [];
            if ($.inArray("提交", data) != -1 ) {
                btn.push("提交");
                $.extend(layerOpts, {
                    btn: data,
                    yes: function (index, layero) {
                        var body = layer.getChildFrame('body', index);
                        var iframeWin = layero.find('iframe')[0];
                        var inputForm = body.find('#inputForm');
                        iframeWin.contentWindow.doSubmit();
                    }
                });
            }
            if ($.inArray("同意", data) != -1) {
                btn.push("同意");
                $.extend(layerOpts, {
                    btn: data,
                    yes: function (index, layero) {
                        var body = layer.getChildFrame('body', index);
                        var iframeWin = layero.find('iframe')[0];
                        var inputForm = body.find('#inputForm');
                        iframeWin.contentWindow.doPass();
                    }
                });
            }
            if ($.inArray("驳回", data) != -1 || $.inArray("不同意", data) != -1) {
                btn.push("不同意");
                $.extend(layerOpts, {
                    btn: data,
                    btn2: function (index, layero) {
                        var body = layer.getChildFrame('body', index);
                        var iframeWin = layero.find('iframe')[0];
                        var inputForm = body.find('#inputForm');
                        iframeWin.contentWindow.doUnPass();
                        return false;
                    }
                });
            }

            btn.push("关闭");
            $.extend(layerOpts, {btn: btn});
            layer.open(layerOpts);
        }, error: function () {
            layer.open(layerOpts);
        }
    });

}


// 删除
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
            url: ctx + "/aqjg/sjgl/delete/" + ids,
            success: function (data) {
                layer.alert(data, {offset: 'rb', shade: 0, time: 2000});
                top.layer.close(index);
                dg.datagrid('reload');
                dg.datagrid('clearChecked');
            }
        });
    });

}

// 查看
function view() {
    var row = dg.datagrid('getSelected');
    if (row == null) {
        layer.msg("请选择一行记录！", {time: 1000});
        return;
    }

    openDialogView("查看信息", ctx +  "/task/view/workflow/"
        + row.pid + "?url=" + row.url, "100%", "100%", "");

}

// 创建查询对象并查询
function search() {
    var obj = $("#searchFrom").serializeObject();
    dg.datagrid('load', obj);
}

function reset() {
    $("#searchFrom").form("clear");
    var obj = $("#searchFrom").serializeObject();
    dg.datagrid('load', obj);
    // if(btflg=='2') $("#filter_EQS_departmen").hide();
}

