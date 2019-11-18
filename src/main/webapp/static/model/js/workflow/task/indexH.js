var dg;
var d;
$(function () {
    dg = $('#dg').datagrid({
        method: "post",
        url: ctx + '/task/history/list',
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
                title: '处理任务（点击追踪）',
                sortable: false,
                width: 50,
                align: 'center',
                formatter: function (value, row) {
                    var html = '<a class="trace" status="unfinished" pid="' + row.pid
                        + '" href=\'#\' title="点击查看流程图">'
                        + value;
                    if (row.finished) {
                        html += '(流程已结束)';
                    }
                    html += '</a>';
                    return html;
                }
            }
        ]],
        onDblClickRow: function () {
            view();
        },
        checkOnSelect: false,
        selectOnCheck: false,
        onLoadSuccess: function () {
            $('.trace').click();
        },
        toolbar: '#dg_tb'
    });
});


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

