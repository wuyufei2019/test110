var dg;
var d;
var gqCnt = 0;
$(function () {
    dg = $('#ztzr_sgbg_dg').datagrid({
        method: "post",
        url: ctx + '/ztzr/fzr/sgbg/list',
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
           /* {field: 'nd', title: '年度', sortable: false, width: 80,align: 'center'},*/
            {field: 'sgbg', title: '事故记录', sortable: false, width: 50, align: 'center',
            	formatter : function(value, row, index) {
            		var html= "<a href=\"#\" style='margin-right:5px' onclick='showsgjl("+row.id+")'>"+value+"</a>" ;
            		return html;
            	}
            },
            {field: 'ysb', title: '已上报事故', sortable: false, width: 50, align: 'center',
            	formatter : function(value, row, index) {
            		var html= "<a href=\"#\" style='margin-right:5px' onclick='showsbsg("+row.id+")'>"+value+"</a>" ;
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
        toolbar: '#ztzr_sgbg_tb'
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


//事故记录
function showsgjl(id){
    layer.open({
        type: 2,
        area: ['90%', '90%'],
        title: '查看事故记录信息',
        maxmin: true,
        content: ctx+"/sggl/sgxx/index/?id="+id ,
        btn: ['关闭'],
        cancel: function(index){
        }
    });
}

//事故记录
function showsbsg(id){
    layer.open({
        type: 2,
        area: ['90%', '90%'],
        title: '查看上报事故信息',
        maxmin: true,
        content: ctx+"/sggl/sgxx/index/?id="+id+"&zt=1" ,
        btn: ['关闭'],
        cancel: function(index){
        }
    });
}













