var dg;
var d;
$(function () {
    dg = $('#dg').datagrid({
        method: "post",
        url: ctx + '/workflow/processinstance/list',
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
            {field: 'id', title: '执行ID', sortable: false, width: 50, align: 'center'},
            {field: 'processInstanceId', title: '流程实例ID', sortable: false, width: 50, align: 'center'},
            {field: 'processDefinitionId', title: '流程定义ID', sortable: false, width: 50, align: 'center'},
            {field: 'suspended', title: '是否挂起', sortable: false, width: 20, align: 'center'}

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

//创建查询对象并查询
function search(){
    var obj=$("#searchFrom").serializeObject();
    dg.datagrid('load',obj);
}

function reset(){
    $("#searchFrom").form("clear");
    var obj=$("#searchFrom").serializeObject();
    dg.datagrid('load',obj);
}


