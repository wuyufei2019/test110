var dg;
var d;
var gqCnt = 0;
$(function () {
    dg = $('#ztzr_yjya_dg').datagrid({
        method: "post",
        url: ctx + '/ztzr/yjya/list',
        fit: true,
        fitColumns: true,
        border: false,
        idField: 'qyid',
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
            {field: 'qyid', title: 'qyid', checkbox: true, width: 50, align: 'center'},
            {field: 'qyname', title: '企业名称', sortable: false, width: 80},
            {field: 'aqya', title: '应急预案', sortable: false, width: 50, align: 'center',
               	formatter : function(value, row, index) {
            		var html= "<a href=\"#\" style='margin-right:5px' onclick='showyjya("+row.qyid+")'>"+value+"</a>" ;
            		return html;
            	}
            },
            {field: 'yljh', title: '演练计划', sortable: false, width: 50, align: 'center',
               	formatter : function(value, row, index) {
            		var html= "<a href=\"#\" style='margin-right:5px' onclick='showyljh("+row.qyid+")'>"+value+"</a>" ;
            		return html;
            	}
            },
            {field: 'yljl', title: '演练记录', sortable: false, width: 50, align: 'center',
               	formatter : function(value, row, index) {
            		var html= "<a href=\"#\" style='margin-right:5px' onclick='showyljl("+row.qyid+")'>"+value+"</a>" ;
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
        toolbar: '#ztzr_yjya_tb'
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


//应急预案
function showyjya(qyid){
    layer.open({
        type: 2,
        area: ['90%', '90%'],
        title: '查看应急预案信息',
        maxmin: true,
        content: ctx+"/erm/yjyagl/index/?qyid="+qyid ,
        btn: ['关闭'],
        cancel: function(index){
        }
    });
}



//演练计划
function showyljh(qyid){
    layer.open({
        type: 2,
        area: ['90%', '90%'],
        title: '查看演练计划信息',
        maxmin: true,
        content: ctx+"/erm/yljh/index/?qyid="+qyid ,
        btn: ['关闭'],
        cancel: function(index){
        }
    });
}


//演练记录
function showyljl(qyid){
    layer.open({
        type: 2,
        area: ['90%', '90%'],
        title: '查看演练记录信息',
        maxmin: true,
        content: ctx+"/erm/yljl/index/?qyid="+qyid ,
        btn: ['关闭'],
        cancel: function(index){
        }
    });
}






