var dg;
var d;
var gqCnt = 0;
$(function () {
    dg = $('#glr_yjyl_dg').datagrid({
        method: "post",
        url: ctx + '/glr/yjyl/list',
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
            {field: 'yljh', title: '演练计划', sortable: false, width: 50, align: 'center',
            	formatter : function(value, row, index) {
        			return "<a onclick='openDialogView(\"演练计划\",\""+ctx+"/erm/yljh/index/?qyid="+row.qyid+"\",\"90%\", \"90%\",\"\")'>"+value+"</a>"; 
            	}
            },
            {field: 'yljl', title: '演练记录', sortable: false, width: 50, align: 'center',
            	formatter : function(value, row, index) {
        			return "<a onclick='openDialogView(\"演练记录\",\""+ctx+"/erm/yljl/index/?qyid="+row.qyid+"\",\"90%\", \"90%\",\"\")'>"+value+"</a>"; 
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
        toolbar: '#glr_yjyl_tb'
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
    //if(btflg=='2') $("#filter_EQS_departmen").hide();
}
