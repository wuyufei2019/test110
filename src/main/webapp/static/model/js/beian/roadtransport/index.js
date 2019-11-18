var dg;
var gqCnt = 0;
$(function () {
    dg = $('#table_dg').datagrid({
        url: ctx + '/beian/roadtransport/list',
        method: "post",
        fit : true,
    	fitColumns : true,
    	border : false,
    	idField : 'id',
    	striped:true,
    	pagination:true,
    	rownumbers:true,
    	nowrap:false,
    	pageNumber:1,
    	pageSize : 50,
    	pageList : [ 50, 100, 150, 200, 250 ],
    	scrollbarSize:0,
    	singleSelect:true,
    	striped:true,
        columns: [[
            {field: 'id', title: 'id', checkbox: true, width: 100, align: 'center'},
            {field: 'm1', title: '业户名称', sortable: false, width: 100, align: 'center'},
            {field: 'm2', title: '地址', sortable: false, width: 100, align: 'center'},
            {field: 'm3', title: '车辆号牌', sortable: false, align: 'center', width: 100},
            {field: 'm4', title: '道路运输证号', sortable: false, align: 'center', align: 'center', width: 100},
            {field: 'm5', title: '车辆类型', sortable: false, align: 'center', width: 100},
            {field: 'm8', title: '经营范围', sortable: false, align: 'center', width: 100},
            {
                field: 'm9', title: '发证日期', sortable: false, align: 'center', width: 100,
                formatter: function (value) {
                    if (value != null) {
                        return new Date(value).format("yyyy-MM-dd");
                    }
                }
            },
            {
                field: 'm10', title: '有效期', sortable: false, align: 'center', width: 100,
                formatter: function (value) {
                    if (value != null) {
                        var datetime = new Date(value);
                        return datetime.format('yyyy-MM-dd');
                    }
                },
                styler: function (value, row, index) {
                    var nowhm = (new Date()).getTime();
                    var time = value;
                    if (time) {//在时间存在的情况下进行
                        if (nowhm <= time) {//未过期（3个月以内）
                            var cha = (time - nowhm) / 1000 / 60 / 60 / 24;
                            if (cha <= 90) return 'background-color:rgb(255,240,245);';
                        } else {//过期
                            gqCnt = gqCnt + 1;
                            return 'background-color:rgb(249, 156, 140);';
                        }
                    }
                }
            },
            /*{
                field: 'state', title: '状态', sortable: false, width: 100, align: 'center',
                formatter: function (value, row) {
                    if (value == "0" || !value) {
                        return "<a class='btn btn-success btn-outline btn-xs'> 待审核</a>";
                    } else if (value == "2") {
                        var html = "<a class='btn btn-success btn-outline btn-xs'>审核不通过</a>";
                        if (usertype == 1 && userid == row.userid) {
                            html += "<a class='btn btn-success btn-xs' onclick='upd2(" + row.id + ")'>修改信息</a>";
                        }
                        return html;
                    }

                    else if (value == '1')
                        return "<a class='btn btn-success btn-xs btn-outline '>审核通过</a>";
                }
            },
            {
                field: 'operation', title: '操作', sortable: false, width: 60, align: 'center',
                formatter: function (value, row) {
                    if (row.state == "0" || !row.state) {
                        return "<a class='btn btn-success btn-xs' onclick='review(" + row.id + ")'>审核</a>";
                    } else if (row.state == "2")
                        return "<a class='btn btn-success btn-outline  btn-xs'>审核不通过</a>";
                    else
                        return "<a class='btn btn-success btn-outline  btn-xs'>审核通过</a>";
                }
            },*/
            {field: 'name', title: '操作人', sortable: false, align: 'center', width: 100},
        ]],
        onLoadSuccess: function () {
            if (gqCnt > 0) {
                layer.open({
                    icon: 1,
                    title: '提示',
                    offset: 'rb',
                    content: "有" + gqCnt + "个道路运输证已过期",
                    shade: 0,
                    time: 2000
                });
            }
            gqCnt = 0;
            if (usertype == 1) {
                dg.datagrid("hideColumn", "operation");
            }
        },
        onDblClickRow: function (rowindex, rowdata, rowDomElement) {
            view();
        },
        checkOnSelect: false,
        selectOnCheck: false,
        toolbar: '#dg_tb'
    });
});

//弹窗增加
function add() {
    openDialog("添加道路运输证信息", ctx + "/beian/roadtransport/create/", "800px", "400px", "");
}

//修改审核不通过信息
function upd2(id) {
    openDialog("修改道路运输证信息", ctx + "/beian/roadtransport/update2/" + id, "800px", "400px", "");
}

//修改审核状态
function review(id) {
    layer.open({
        type: 2,
        shift: 1,
        area: ['800px', '400px'],
        title: "道路运输证审核",
        content: ctx + '/beian/roadtransport/view/' + id,
        btn: ['审核通过', '审核不通过'],
        yes: function (index, layero) {
            layer.confirm("你确定同意该道路运输证？", {icon: 3, title: '提示'}, function (index) {
                ajaxChangeStatus(id, 1, "");
            });
        }
        , btn2: function (index, layero) {
            layer.prompt({title: '请输入审核不通过原因'}, function (value) {
                ajaxChangeStatus(id, 2, value);
            });
            return false
        }
        , cancel: function () {

        }
    });


    function ajaxChangeStatus(id, type, opinion) {
        $.ajax({
            type: 'get',
            url: ctx + "/beian/roadtransport/changeclcrstatus/" +
                id + "/" + type + "?opinion=" + opinion,
            success: function (data) {
                layer.closeAll();
                dg.datagrid('reload');
                if (data == 'success') {
                    layer.alert("审核成功！", {offset: 'rb', shade: 0, time: 2000});
                } else {
                    layer.alert("审核失败！", {offset: 'rb', shade: 0, time: 2000});
                }
            }
        });
    }

}

//删除
function del() {
    var rows = dg.datagrid('getChecked');
    if (rows.length == 0) {
        layer.msg("请至少勾选一行记录！", {time: 1000});
        return;
    }

    var ids = "";
    for (var item of rows) {
        if ((item.userid && userid == item.userid) || userRoleCode == "ajcountyadmin") {
            if (ids == "") {
                ids = item.id;
            } else {
                ids = ids + "," + item.id;
            }
        }else if (userid != null && userid != item.userid) {
            layer.msg("非本人上传资质，不允许删除！",{time: 1000});
            return;
        }
    }

    top.layer.confirm('删除后无法恢复您确定要删除？', {icon: 3, title: '提示'}, function (index) {
        $.ajax({
            type: 'get',
            url: ctx + "/beian/roadtransport/delete/" + ids,
            success: function (data) {
                if (data == 'success') {
                    layer.alert('删除成功', {offset: 'rb', shade: 0, time: 2000});
                    top.layer.close(index);
                    dg.datagrid('reload');
                    dg.datagrid('clearChecked');
                    dg.datagrid('clearSelections');
                }
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
    if(row.userid == null || row.userid =="" || userRoleCode == "ajcountyadmin"){
        openDialog("修改道路运输证信息", ctx + "/beian/roadtransport/update/" + row.id, "800px", "400px", "");
        return;
    }else if(userid != null && userid != row.userid){
        layer.msg("非本人上传资质，不允许修改！",{time: 1000});
        return;
    }
    openDialog("修改道路运输证信息", ctx + "/beian/roadtransport/update/" + row.id, "800px", "400px", "");

}

//查看
function view() {
    var row = dg.datagrid('getSelected');
    if (row == null) {
        layer.msg("请选择一行记录！", {time: 1000});
        return;
    }
    openDialogView("查看道路运输证信息", ctx + "/beian/roadtransport/view/" + row.id, "800px", "400px", "");

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
}

//导出
function fileexport() {
    window.expara = $("#searchFrom").serializeObject();
    window.exdata = [
        {colval: 'm1', coltext: '业户名称'},
        {colval: 'm2', coltext: '地址'},
        {colval: 'm3', coltext: '车辆号牌'},
        {colval: 'm4', coltext: '道路运输证号'},
        {colval: 'm5', coltext: '车辆类型'},
        {colval: 'm6', coltext: '吨(座)位'},
        {colval: 'm7', coltext: '车辆(毫米)'},
        {colval: 'm8', coltext: '经营范围'},
        {colval: 'm9', coltext: '发证日期'},
        {colval: 'm10', coltext: '有效期'}
    ];
    layer.open({
        type: 2,
        area: ['350px', '350px'],
        title: '导出列选择',
        maxmin: false,
        shift: 1,
        content: ctx + '/beian/roadtransport/colindex',
        btn: ['导出'],
        yes: function (index, layero) {
            var body = layer.getChildFrame('body', index);
            var iframeWin = layero.find('iframe')[0];
            var inputForm = body.find('#excel_mainform');
            iframeWin.contentWindow.doExport();
        },
    });

}