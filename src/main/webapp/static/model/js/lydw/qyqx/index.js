var dg;
$(function(){
    dg=$('#lydw_qyqx_dg').datagrid({
        method: "post",
        url:ctx+'/lydw/qyqx/listjson',
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
            {field:'id',title:'id',checkbox:true,width:20,align:'center'},
            {field:'name',title:'区域名称',sortable:false,width:50,align:'center'},
            {field:'floor',title:'所在楼层',sortable:false,width:50,align:'center'},
            {field:'deptnames',title:'允许进出部门',sortable:false,width:50,align:'center'},
            {field:'mappoint',title:'是否标注区域',sortable:false,width:50,align:'center',
                formatter : function(value, row, index) {
                    if(value != null && value != '') {
                        return '是';
                    }else {
                        return '否';
                    }
                }}

        ]],
        onClickRow: function() {
            var row = dg.datagrid('getSelected');
            if(layer0!=null){
                console.log('1');
                cleanFence();
                newFenceData=[];
            }
            wlId = row.id;

            if(row.floor != null && row.floor != '') {
                floor = row.floor;
                groupControl.changeFocusGroup(floor);
            }
            // 点击相应列,显示在地图上
            if(row.mappoint != null && row.mappoint != ''){
                cleanFence();
                var ponits = row.mappoint.split('||');
                for (var i = 0; i < ponits.length; i++) {
                    var p = ponits[i].split(',');
                    //移动到坐标位置
                    if(i == 0) {
                        map.moveTo({
                            x: p[0],
                            y: p[1],
                            time: 1,
                            callback: function() {
                                // console.log('moveTo Complete!');
                            }
                        });
                    }

                    var coord1 = {
                        x: p[0],
                        y: p[1],
                        z: map.getFMGroup(p[2]).groupHeight + map.layerLocalHeight
                    }
                    newFenceData.push(coord1);
                }
                createFence();//生产区域权限

            }else {
                cleanFence();
            }

        },
        onDblClickRow: function (rowdata, rowindex, rowDomElement){
        },

        checkOnSelect:false,
        selectOnCheck:false,
        toolbar:'#lydw_qyqx_tb'
    });

});

//弹窗增加
function add() {
    openDialog("添加区域权限信息",ctx+"/lydw/qyqx/create","550px", "300px","");
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
            url:ctx+"/lydw/qyqx/delete/"+ids,
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

    openDialog("修改区域权限信息",ctx+"/lydw/qyqx/update/"+row.id,"550px", "300px","");

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

//区域权限标注总览
function searchDzwl() {
    $.ajax({
        type:'post',
        url:ctx+"/lydw/qyqx/qyqxlist",
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
                createFence();//生产区域权限
            }
            //floor = 0;
        }
    });
}