var dg;
var d;
var gqCnt = 0;
$(function () {
    dg = $('#glr_gzzd_dg').datagrid({
        method: "post",
        url: ctx + '/glr/gzzd/list',
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
            {field: 'aqzd', title: '安全管理制度', sortable: false, width: 50, align: 'center',
            	formatter : function(value, row, index) {
        			return "<a onclick='openDialogView(\"安全管理制度\",\""+ctx+"/zdgl/glzd/index?qyid="+row.qyid+"\",\"90%\", \"90%\",\"\")'>"+value+"</a>"; 
            	}
            },
            {field: 'czgc', title: '操作规程', sortable: false, width: 50, align: 'center',
            	formatter : function(value, row, index) {
        			return "<a onclick='openDialogView(\"安全操作规程\",\""+ctx+"/zdgl/czgc/index?qyid="+row.qyid+"\",\"90%\", \"90%\",\"\")'>"+value+"</a>"; 
            	}
            },
            {field: 'aqya', title: '应急预案', sortable: false, width: 50, align: 'center',
               	formatter : function(value, row, index) {
               		return "<a onclick='openDialogView(\"应急预案\",\""+ctx+"/erm/yjyagl/index/?qyid="+row.qyid+"\",\"90%\", \"90%\",\"\")'>"+value+"</a>";
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
        },
        checkOnSelect: false,
        selectOnCheck: false,
        toolbar: '#glr_gzzd_tb'
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
}









