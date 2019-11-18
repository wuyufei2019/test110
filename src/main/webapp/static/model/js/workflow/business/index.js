var dg;
var d;
$(function () {
    dg = $('#dg').datagrid({
        method: "post",
        url: ctx + '/BusinessAndProcess/list',
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
            {field: 'businessname', title: '业务名称', sortable: false, width: 50, align: 'center'},
            {field: 'businesstype', title: '业务类型', sortable: false, width: 20, align: 'center'},
            {field: 'processid', title: '流程', sortable: false, width: 80, align: 'center'}

        ]],
        onDblClickRow: function () {

        },
        checkOnSelect: false,
        selectOnCheck: false,
        onLoadSuccess: function () {
        },
        toolbar: '#dg_tb'
    });
});




function upd() {
    var row = dg.datagrid('getSelected');
    if(row==null) {
        layer.msg("请选择一行记录！",{time: 1000});
        return;
    }

    openDialog("分配流程",ctx+"/BusinessAndProcess/update/"+row.id,"800px", "400px","");
}




