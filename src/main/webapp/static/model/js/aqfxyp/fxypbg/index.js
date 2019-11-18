var dg;
var d;
$(function(){
    dg=$('#aqfxyp_fxypbg_dg').datagrid({
        method: "post",
        url:ctx+'/aqfxyp/fxypbg/list',
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
        scrollbarSize:5,
        singleSelect:true,
        striped:true,
        columns:[[
            {field:'ID',title:'id',checkbox:true,width:50,align:'center'},
            {field:'qyname',title:'企业名称',width:100,align:'center'},
            {field:'m1',title:'等级',width:60,align:'center'},
            {field:'m2',title:'车间',width:120,align:'center'},
            {field:'m3',title:'班组',width:120,align:'center'},
            {field:'m10',title:'负责人',width:120,align:'center'},
            {field:'m11',title:'日期',width:120,align:'center'}
        ]],
        onDblClickRow: function (rowdata, rowindex, rowDomElement){
            view();
        },
        checkOnSelect:false,
        selectOnCheck:false,
        onLoadSuccess:function(){
            if(type=="1"){
                $(this).datagrid("hideColumn",['qyname']);
                $(this).datagrid("autoMergeCells",['m1']);
            }else{
                $(this).datagrid("showColumn",['qyname']);
                $(this).datagrid("autoMergeCells",['qyname','m1']);
            }
        },
        toolbar:'#aqfxyp_fxypbg_tb'
    });

});

//弹窗增加
function add() {
    openDialog("添加风险研判报告信息",ctx+"/aqfxyp/fxypbg/create/","1000px", "600px","");
}
//导出word
function fileexportword() {
    var row = dg.datagrid('getSelected');
    if(row==null) {
        layer.msg("请选择一行记录！",{time: 1000});
        return;
    }

    $.ajax({
        url:ctx+"/aqfxyp/fxypbg/exportword/"+row.id,
        type:"POST",
        success:function(data){
            window.open(ctx+data);
        }
    });
}

//删除
function del(){
    var row = dg.datagrid('getChecked');
    if(row==null||row=='') {
        layer.msg("请至少勾选一行记录！",{time: 1000});
        return;
    }

    var ids="";
    for(var i=0;i<row.length;i++){
        if(ids==""){
            ids=row[i].id;
        }else{
            ids=ids+","+row[i].id;
        }
    }

    top.layer.confirm('删除后无法恢复您确定要删除？', {icon: 3, title:'提示'}, function(index){
        $.ajax({
            type:'get',
            url:ctx+"/aqfxyp/fxypbg/delete/"+ids,
            success: function(data){
                layer.alert(data, {offset: 'rb',shade: 0,time: 2000});
                top.layer.close(index);
                dg.datagrid('reload');
                dg.datagrid('clearChecked');
                dg.datagrid('clearSelections');
            }
        });
    });

}


//弹窗修改
function upd(){
    var row = dg.datagrid('getSelected');
    if(row==null) {
        layer.msg("请选择一行记录！",{time: 1000});
        return;
    }

    openDialog("修改风险研判报告",ctx+"/aqfxyp/fxypbg/update/"+row.id,"1000px", "600px","");

}

//查看
function view(){
    var row = dg.datagrid('getSelected');
    if(row==null) {
        layer.msg("请选择一行记录！",{time: 1000});
        return;
    }

    openDialogView("查看风险研判报告",ctx+"/aqfxyp/fxypbg/view/"+row.id,"1000px", "600px","");

}

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