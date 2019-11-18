var dg;
$(function () {
    dg = $('#table_dg').datagrid({
        method: "post",
        url: ctx + '/yszy/kkzysb/list?cphm='+cphm,
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
            {field: 'entrust_company', title: '托运方单位', sortable: false, width: 100, align: 'center'},
            {field: 'consignee_company', title: '收货方单位', sortable: false, width: 100, align: 'center'},
            {field: 'accept_company', title: '承运方单位', sortable: false, width: 100, align: 'center'},
            {field: 'plate_num', title: '车牌号码', sortable: false, align: 'center', align: 'center', width: 100},
            {field: 'driver_name', title: '驾驶员姓名', sortable: false, width: 100, align: 'center'},
            {field: 'loading_time', title: '装货日期', sortable: false, align: 'center', width: 100,
                formatter: function (value) {
                    return new Date(value).format("yyyy-MM-dd");
                }},
            {
                field: 'state', title: '订单状态', sortable: false, width: 100, align: 'center',
                formatter: function (value, row) {
                	var html = "";
                    //var html = "<a class='btn btn-primary btn-xs' onclick='exportewm(\"" + row.number + "\")'>导出二维码</a>";
                    if (value == "3" || !value) {
                        return "<a class='btn btn-success btn-outline btn-xs'> 待审核</a>";
                    } else if (value == "5"){
                        var html = "<a class='btn btn-success btn-outline btn-xs'>审核不通过</a>";
                        if(usertype ==1){
                            html += "<a class='btn btn-success btn-xs' onclick='upd("+row.id+")'>修改订单</a>";
                        }
                        return html;
                    }

                    else if (value == '2')
                        return "<a class='btn btn-success btn-xs btn-outline '>订单已完成</a>" + html;
                    else
                        return "<a class='btn btn-warning btn-xs btn-outline '>订单未完成</a>" + html;
                }
            },
            {
                field: 'operation', title: '操作', sortable: false, width: 100, align: 'center',
                formatter: function (value, row) {
                    if (row.state == "3" || !row.state) {
                        return "<a class='btn btn-success btn-xs' onclick='review(" + row.id + ")'>审核</a>";
                    } else if (row.state == "5")
                        return "<a class='btn btn-success btn-outline  btn-xs'>审核不通过</a>";
                    else
                        return "<a class='btn btn-success btn-outline  btn-xs'>审核通过</a>";
                }
            }
        ]],
        onLoadSuccess: function () {
            if (usertype == 1) {
                dg.datagrid("hideColumn", "operation");
            }
            if (carview == 'carview') {
            	dg.datagrid("hideColumn", "state");
            	dg.datagrid("hideColumn", "operation");
            	dg.datagrid("hideColumn", "id");
            }
            if(userRoleCode != 'ajcountyadmin'){
                dg.datagrid("hideColumn","operation");
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
    openDialog("添加运单信息", ctx + "/yszy/kkzysb/create/", "100%", "100%", "");
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
        if (ids == "") {
            ids = item.id;
        } else {
            ids = ids + "," + item.id;
        }
    }

    top.layer.confirm('删除后无法恢复您确定要删除？', {icon: 3, title: '提示'}, function (index) {
        $.ajax({
            type: 'get',
            url: ctx + "/yszy/kkzysb/delete/" + ids,
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

//审核不符合修改订单
function upd(id) {
    openDialog("修改运单信息", ctx + "/yszy/kkzysb/update/" + id, "100%", "100%", "");
}

//弹窗修改
function upd2() {
    var row = dg.datagrid('getSelected');
    if (row == null) {
        layer.msg("请选择一行记录！", {time: 1000});
        return;
    }
    openDialog("修改运单信息", ctx + "/yszy/kkzysb/update2/" + row.id, "1000px", "500px", "");

}

//查看
function view() {
    var row = dg.datagrid('getSelected');
    if (row == null) {
        layer.msg("请选择一行记录！", {time: 1000});
        return;
    }
    openDialogView("查看运单信息", ctx + "/yszy/kkzysb/view/" + row.id, "100%", "100%", "");

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
        {colval: 'm1', coltext: '订单号码'},
        {colval: 'm2', coltext: '驾驶员姓名'},
        {colval: 'm3', coltext: '身份证号'},
        {colval: 'm4', coltext: '联系方式'},
        {colval: 'm5', coltext: '车牌号码'},
        {colval: 'm6', coltext: '车型'},
        {colval: 'm7', coltext: '所属车队'},
        {colval: 'm8', coltext: '货物名称'},
        {colval: 'm9', coltext: '货物重量/货物件数'},
    ];
    layer.open({
        type: 2,
        area: ['350px', '350px'],
        title: '导出列选择',
        maxmin: false,
        shift: 1,
        content: ctx + '/yszy/kkzysb/colindex',
        btn: ['导出'],
        yes: function (index, layero) {
            var body = layer.getChildFrame('body', index);
            var iframeWin = layero.find('iframe')[0];
            var inputForm = body.find('#excel_mainform');
            iframeWin.contentWindow.doExport();
        },
    });

}

//订单管理按钮事件
function ddgl(id) {
    layer.open({
        type: 2,
        area: ['100%', '100%'],
        title: '查看订单历史轨迹',
        maxmin: true,
        shift: 2,
        shade: 0,
        content: ctx + '/yszy/kkzysb/viewddmap/' + id
    });
}

//车辆出入状态
function review(id, type) {
    layer.open({
        type: 2,
        shift: 1,
        area: ['100%', '100%'],
        title: "订单审核",
        content: ctx + '/yszy/kkzysb/view/' + id,
        btn: ['审核通过', '审核不通过'],
        yes: function (index, layero) {
            top.layer.confirm("你确定同意该车辆入园？", {icon: 3, title: '提示'}, function (index) {
            	$.ajax({
                    type: 'get',
                    url: ctx + "/yszy/kkzysb/changeclcrstatus/" + id + "/4",
                    success: function (data) {
                        layer.closeAll();
                        top.layer.close(index);
                        dg.datagrid('reload');
                        if(data =='success'){
                            layer.alert("审核成功！", {offset: 'rb', shade: 0, time: 2000});
                        }else{
                            layer.alert("审核失败！", {offset: 'rb', shade: 0, time: 2000});

                        }
                    }
                });
            });
        }
        , btn2: function (index, layero) {
        	layer.prompt({title:'请输入审核不通过原因'},function(value, index, elem){
        		$.ajax({
                    type: 'get',
                    url: ctx + "/yszy/kkzysb/changeclcrstatus/" + id + "/5" + "?yj="+value,
                    success: function (data) {
                        layer.closeAll();
                        top.layer.close(index);
                        dg.datagrid('reload');
                        if(data =='success'){
                            layer.alert("审核成功！", {offset: 'rb', shade: 0, time: 2000});
                        }else{
                            layer.alert("审核失败！", {offset: 'rb', shade: 0, time: 2000});

                        }
                    }
                });
	      	});
            return false
        }
        , cancel: function () {

        }
    });
}


//生成二维码图片
function exportewm(number) {
    $.ajax({
        type: 'get',
        url: ctx + "/QRcode/export/" + number,
        success: function (data) {
            window.open(ctx + data);
        }
    });
}



