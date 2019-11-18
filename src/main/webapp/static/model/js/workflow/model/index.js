var dg;
var d;
$(function () {
    dg = $('#dg').datagrid({
        method: "post",
        url: ctx + '/workflow/model/list',
        fit: true,
        fitColumns: true,
        border: false,
        idField: 'id',
        striped: true,
        pagination: false,
        rownumbers: true,
        nowrap: false,
        scrollbarSize: 5,
        singleSelect: true,
        striped: true,
        columns: [[
            {field: 'id', title: 'id', checkbox: true, width: 50, align: 'center'},
            {field: 'key', title: 'KEY', sortable: false, width: 50, align: 'center'},
            {field: 'name', title: '名称', sortable: false, width: 50, align: 'center'},
            {field: 'version', title: '版本', sortable: false, width: 20, align: 'center'},
            {
                field: 'createTime',
                title: '创建时间',
                sortable: false,
                width: 50,
                align: 'center',
                formatter: function (value) {
                    return new Date(value).format("yyyy-MM-dd hh:mm:ss")
                }
            },
            {
                field: 'lastUpdateTime',
                title: '最后更新时间',
                sortable: false,
                width: 50,
                align: 'center',
                formatter: function (value) {
                    return new Date(value).format("yyyy-MM-dd hh:mm:ss")
                }
            },
            {field: 'metaInfo', title: '元数据', sortable: false, width: 200, align: 'center'},
            {
                field: 'operation',
                title: '操作',
                sortable: false,
                width: 50,
                align: 'center',
                formatter: function (value, row) {
                    var modelid = row.id;
                    return ' <a href="' + ctx + '/modeler.html?modelId='
                        + modelid + '" target="_blank">编辑</a>&nbsp;<a href="#" onclick="deploy('
                        + modelid+')">部署</a>导出(<a href="' + ctx + '/workflow/model/export/'
                        + modelid + '/bpmn" target="_blank">BPMN</a>|&nbsp;<a href="' + ctx + '/workflow/model/export/'
                        + modelid + '/json" target="_blank">JSON</a>  |&nbsp;<a href="javascript:;" onclick=\'showSvgTip()\'>SVG</a>)';

                }
            }

        ]],
        onDblClickRow: function (rowdata, rowindex, rowDomElement) {
        },
        checkOnSelect: false,
        selectOnCheck: true,
        onLoadSuccess: function () {
        },
        toolbar: '#dg_tb'
    });
});

function deploy(id) {
    $.post(ctx+'/workflow/model/deploy/'+id,function (data) {
        if(data=='success'){
            layer.msg("部署成功！");
        }else{
            layer.msg("部署失败！");
        }
    });
}

// 弹窗增加
function add() {
    openDialog("添加模型", ctx + "/workflow/model/create/", "800px", "300px", "");
}

// 删除
function del() {
    var row = dg.datagrid('getChecked');
    if (row == null || row == '') {
        layer.msg("请至少勾选一行记录！", {time: 1000});
        return;
    }
    top.layer.confirm('删除后无法恢复您确定要删除？', {icon: 3, title: '提示'}, function (index) {
        $.ajax({
            type: 'get',
            url: ctx + "/workflow/model/delete/" + row[0].id,
            success: function (data) {
                dg.datagrid('reload');
                dg.datagrid('clearChecked');
            }
        });
    });

}


function reset() {
    $("#searchFrom").form("clear");
    var obj = $("#searchFrom").serializeObject();
    dg.datagrid('load', obj);
    // if(btflg=='2') $("#filter_EQS_departmen").hide();
}

