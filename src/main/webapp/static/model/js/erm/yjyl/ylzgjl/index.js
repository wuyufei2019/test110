var dg;
var d;
$(function(){
    dg=$('#erm_ylzgjl_dg').datagrid({
        method: "post",
        url:ctx+'/erm/ylzgjl/list',
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
            {field:'qynm',title:'企业名称',width:100,align:'center'},
            {field:'m3',title:'演练名称',width:60,align:'center'},
            {field:'m1',title:'整改人员',width:120,align:'center'},
            {field:'m2',title:'整改照片',sortable:false,width:50,align:'center',
                formatter : function(value) {
                    if(value){
                        var urls=value.split(",");
                        var html="";
                        for(var index in urls){
                            html+="<a class='fa fa-picture-o btn-danger btn-outline' target='_blank' style='margin:1px auto' onclick='openImgView(\""+urls[index].split("||")[0]+"\")'> 照片"+(parseInt(index)+1)+"</a><br>";
                        }
                        return html;
                    }
                    else return ;
                }
            }
        ]],
        onDblClickRow: function (rowdata, rowindex, rowDomElement){
            view();
        },
        checkOnSelect:false,
        selectOnCheck:false,
        onLoadSuccess:function(){
            if(type=="1"){
                $(this).datagrid("hideColumn",['qynm']);
            }else{
                $(this).datagrid("showColumn",['qynm']);
                $(this).datagrid("autoMergeCells",['qynm']);
            }
        },
        toolbar:'#erm_ylzgjl_tb'
    });

});

//弹窗增加
function add() {
    openDialog("添加演练整改计划信息",ctx+"/erm/ylzgjl/create/","800px", "400px","");
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
            url:ctx+"/erm/ylzgjl/delete/"+ids,
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

    openDialog("修改演练计划信息",ctx+"/erm/ylzgjl/update/"+row.id,"800px", "400px","");

}

//查看
function view(){
    var row = dg.datagrid('getSelected');
    if(row==null) {
        layer.msg("请选择一行记录！",{time: 1000});
        return;
    }

    openDialogView("查看演练计划信息",ctx+"/erm/ylzgjl/view/"+row.id,"800px", "400px","");

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