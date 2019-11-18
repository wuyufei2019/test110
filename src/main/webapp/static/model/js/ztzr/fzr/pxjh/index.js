var dg;
var d;
var gqCnt = 0;
$(function () {
    dg = $('#aqpx_pxjhxx_dg').datagrid({
        method: "post",
        url: ctx + '/ztzr/aqpxjh/list',
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
            {field: 'qyname', title: '企业名称', sortable: false, width: 80},
            {field: 'm1', title: '计划名称', sortable: false, width: 80},
            {field: 'm2', title: '培训类别', sortable: false, width: 50, align: 'center'},
            {field: 'm3', title: '培训学时', sortable: false, width: 50, align: 'center'},
            {
                field: 'm5', title: '开始日期', sortable: false, width: 50, align: 'center',
                formatter: function (value, view, index) {
                    if (value) {
                        var datetime = new Date(value);
                        return datetime.format('yyyy-MM-dd');
                    }
                }
            },
            {
                field: 'm6', title: '结束日期', sortable: false, width: 50, align: 'center',
                styler: function (value, row, index) {
                    if (value) {
                        var nowhm = (new Date()).getTime();
                        if (nowhm <= value) {
                            var cha = (value - nowhm) / 1000 / 60 / 60 / 24;
                            if (cha <= 90) return 'background-color:rgb(255, 228, 141);';
                        } else {
                            gqCnt = gqCnt + 1;
                            return 'background-color:rgb(249, 156, 140);';
                        }
                    }
                },
                formatter: function (value, view, index) {
                    if (value) {
                        var datetime = new Date(value);
                        return datetime.format('yyyy-MM-dd');
                    }
                },
            },
            {
                field: 'm4', title: '制定日期', sortable: false, width: 50, align: 'center',
                formatter: function (value, view, index) {
                    if (value) {
                        var datetime = new Date(value);
                        return datetime.format('yyyy-MM-dd');
                    }
                }
            },
            {
                field: 'cjks', title: '状态', sortable: false, width: 100, align: 'center',
                formatter: function (value, row, index) {
                    var html = "<span class='fa fa-check btn-primary btn-outline' onclick='showkslist(" + row.id + ",1)'>参加考试:" + row.cjks + "</span>&nbsp;&nbsp;" + "<span class='fa fa-close btn-warning btn-outline' onclick='showkslist(" + row.id + ",2)'>未参加考试:" + row.wcjks + "</span>&nbsp;&nbsp;" +
                        "<span class='fa fa-check btn-success btn-outline' onclick='showkslist(" + row.id + ",3)'>考试合格:" + row.hg + "</span>&nbsp;&nbsp;" + "<span class='fa fa-close btn-danger btn-outline' onclick='showkslist(" + row.id + ",4)'>考试不合格:" + row.bhg + "</span>";
                    return html;
                }
            },
            {field:'dcd',title:'达成度',width:60,align:'center',
                formatter:function(value, row, index){
                    return "<span class=\'fa fa-close btn-danger btn-outline\' >未落实</span>";
                }
            }
        ]],
        onDblClickRow: function (rowdata, rowindex, rowDomElement) {
            view();
        },
        onLoadSuccess: function () {
            $(this).datagrid("autoMergeCells", ['qyname']);
            /*    	if(gqCnt > 0){
                        layer.open({icon:1,title: '提示',offset: 'rb',content:"有" + gqCnt + "个培训计划已过期",shade: 0 ,time: 2000 });
                    }
                    gqCnt=0;*/
        },
        checkOnSelect: false,
        selectOnCheck: false,
        toolbar: '#aqpx_pxjhxx_tb'
    });

});

//弹窗增加
function add(u) {
    openDialog("添加培训计划信息", ctx + "/aqpx/aqpxjh/create/", "800px", "400px", "");
}

//删除
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
            url: ctx + "/aqpx/aqpxjh/delete/" + ids,
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

//弹窗修改
function upd() {
    var row = dg.datagrid('getSelected');
    if (row == null) {
        layer.msg("请选择一行记录！", {time: 1000});
        return;
    }

    openDialog("修改培训计划信息", ctx + "/aqpx/aqpxjh/update/" + row.id, "800px", "400px", "");

}

//查看
function view() {
    var row = dg.datagrid('getSelected');
    if (row == null) {
        layer.msg("请选择一行记录！", {time: 1000});
        return;
    }

    openDialogView("查看培训计划信息", ctx + "/aqpx/aqpxjh/view/" + row.id, "800px", "400px", "");

}

//创建查询对象并查询
function search() {
    var obj = $("#searchFrom").serializeObject();
    dg.datagrid('load', obj);
}

function reset() {
    $("#searchFrom").form("clear");
    var obj = $("#searchFrom").serializeObject();
    dg.datagrid('load', obj);
    //if(btflg=='2') $("#filter_EQS_departmen").hide();
}

//导出
function fileexport() {
    url = ctx + "/aqpx/aqpxjh/export";
    window.location.href = url;
}

//根据计划查看考试记录
function showkslist(jhid, type) {
    if (type == 2) {
        layer.open({
            title: '未参加考试人员',
            type: 2,
            shift: 1,
            area: ['80%', '80%'],
            maxmin: false,
            closeBtn: 0,
            content: ctx + '/aqpx/aqpxjh/ztindex/' + jhid + '/' + type,
            btn: ['关闭']
        });
    } else {
        layer.open({
            title: '考试记录',
            type: 2,
            shift: 1,
            area: ['80%', '80%'],
            maxmin: false,
            closeBtn: 0,
            content: ctx + '/aqpx/aqpxjh/ztindex/' + jhid + '/' + type,
            btn: ['关闭']
        });
    }

}