var dg;
$(function(){
    dg=$('#lydw_dzwl_dg').datagrid({
        method: "post",
        url:ctx+'/lydw/dzwl/listjson',
        fit : true,
        fitColumns : true,
        queryParams:{'type':'1'},
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
            {field:'id',title:'id',checkbox:true,width:50,align:'center'},
            {field:'name',title:'围栏名称',sortable:false,width:80,align:'center'},
            {field:'floor',title:'所在楼层',sortable:false,width:50,align:'center'},
            {field:'buildingname',title:'所在建筑',sortable:false,width:50,align:'center'},
            {field:'bz',title:'绘制围栏',sortable:false,width:50,align:'center',formatter : function(value, row, index) {
                    return "<a style='margin:2px' class='btn btn-danger btn-xs' onclick='createFence("+row.id + ")'>绘制围栏</a>"
                }
            }

        ]],
        onDblClickRow: function (rowdata, rowindex, rowDomElement){
        },

        checkOnSelect:false,
        selectOnCheck:false,
        toolbar:'#lydw_dzwl_tb'
    });

});

//弹窗增加
function add() {
    openDialog("添加电子围栏信息",ctx+"/lydw/dzwl/create?type=1","800px", "300px","");
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
            url:ctx+"/lydw/dzwl/delete/"+ids,
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

    openDialog("修改电子围栏信息",ctx+"/lydw/dzwl/update/"+row.id,"800px", "300px","");

}

//创建查询对象并查询
function search() {
    var obj=$("#searchFrom").serializeObject();
    dg.datagrid('load',obj);
}

function reset() {
    $("#searchFrom").form("clear");
    var obj=$("#searchFrom").serializeObject();
    dg.datagrid('load',obj);
}

//电子围栏标注总览
function searchDzwl() {
    $.ajax({
        type:'post',
        url:ctx+"/lydw/dzwl/dzwllist",
        success: function(data){
            cleanFence();
            var data = $.parseJSON(data);
            for (var i = 0; i < data.length; i++) {
                floor = data[i].floor;
                newFenceData = [];
                var ponits = data[i].mappoint.split('||');
                for (var j = 0; j < ponits.length; j++) {
                    var p = ponits[j].split(',');
                    //移动到坐标位置
                    if(j == 0) {
                        map.moveTo({
                            x: p[0],
                            y: p[1],
                            time: 1,
                            callback: function() {
                                // console.log('moveTo Complete!');
                            }
                        });
                    }

                    var coord = {
                        x: p[0],
                        y: p[1],
                        z: map.getFMGroup(p[2]).groupHeight + map.layerLocalHeight
                    }
                    newFenceData.push(coord);
                }
                createFence();//生产电子围栏
            }
            //floor = 0;
        }
    });
}