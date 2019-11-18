var dg;
$(function () {
    dg = $('#table_dg').datagrid({
        url: ctx + '/basic/car/list?type='+type,
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
            {field: 'm3', title: '牵引车车牌号码', sortable: false, align: 'center', width: 130},
            {field: 'm13', title: '挂车车牌号码', sortable: false, align: 'center', width: 130},
            {field: 'm9', title: '车辆负责人', sortable: false, align: 'center', width: 130},
            {field: 'm2', title: '车品牌', sortable: false, width: 130, align: 'center'},
            {field: 'm10', title: '联系电话', sortable: false, align: 'center', width: 130}/*,
            {field: 'score', title: '积分', sortable: false, align: 'center', width: 130,
            formatter:function (value) {
                return value;
            }},
            {field: 'operation', title: '操作', sortable: false, align: 'center', width: 140,
            formatter:function (value,row) {
                "<a class='btn btn-primary btn-xs' onclick='ydjl(\""+row.m3+"\")'>运单记录</a>"+
            	return	"<a class='btn btn-primary btn-xs' onclick='clwzjl(\""+row.m3+"\")'>车辆违章记录</a>";
            }}*/
        ]],
        onLoadSuccess: function () {
            /*if(usertype=='1'){
                dg.datagrid("hideColumn","score");
            }*/
        },
        onDblClickRow: function (rowindex, rowdata, rowDomElement) {
            view();
        },
        checkOnSelect: false,
        selectOnCheck: false,
        toolbar: '#dg_tb'
    });
});

//查看运单记录
function ydjl(m3) {
    openDialogView("查看运单记录信息", ctx + "/basic/car/viewydjl?m3="+m3, "90%", "90%", "");
}

//查看车辆违章记录
function clwzjl(m3) {
    openDialogView("查看车辆违章记录信息", ctx + "/basic/car/viewclwzjl?m3="+m3, "800px", "400px", "");
}

//弹窗增加
function add() {
    openDialog("添加车辆信息", ctx + "/basic/car/create/", "800px", "400px", "");
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
            url: ctx + "/basic/car/delete/" + ids,
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
    openDialog("修改车辆信息", ctx + "/basic/car/update/" + row.id, "800px", "400px", "");

}

//查看
function view() {
    var row = dg.datagrid('getSelected');
    if (row == null) {
        layer.msg("请选择一行记录！", {time: 1000});
        return;
    }
    openDialogView("查看车辆信息", ctx + "/basic/car/view/" + row.id, "800px", "400px", "");

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
        {colval: 'm3', coltext: '牵引车车牌号码'},
        {colval: 'm13', coltext: '挂车车牌号码'},
        {colval: 'm2', coltext: '车辆品牌'},
        {colval: 'm1', coltext: '车型'},
        {colval: 'm4', coltext: '吨位'},
        {colval: 'm5', coltext: '最大核载人数'},
        {colval: 'm6', coltext: '车高(米)'},
        {colval: 'm7', coltext: '车长(米)'},
        {colval: 'm8', coltext: '车宽(米)'},
        {colval: 'm9', coltext: '车辆负责人'},
        {colval: 'm10', coltext: '联系方式'},
        {colval: 'm12', coltext: '保险公司'}
    ];
    layer.open({
        type: 2,
        area: ['350px', '350px'],
        title: '导出列选择',
        maxmin: false,
        shift: 1,
        content: ctx + '/basic/car/colindex',
        btn: ['导出'],
        yes: function (index, layero) {
            var body = layer.getChildFrame('body', index);
            var iframeWin = layero.find('iframe')[0];
            var inputForm = body.find('#excel_mainform');
            iframeWin.contentWindow.doExport();
        },
    });

}

//生成二维码图片
function exportewm(plateNum){
    $.ajax({
        type : 'get',
        url : ctx + "/QRcode/export/" + plateNum,
        success : function(data) {
            window.open(ctx+data);
        }
    });
}