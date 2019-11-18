<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
    <title>安全管理制度修订记录</title>
    <meta name="decorator" content="default"/>
</head>
<body >
<div id="zdgl_glzd_tb_his" style="padding:5px;height:auto">

    <div class="row">
        <div class="col-sm-12">
            <div class="pull-left">
                <shiro:hasPermission name="zdgl:glzd:view">
                    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view()" title="查看"><i class="fa fa-search-plus"></i> 查看</button>
                </shiro:hasPermission>
            </div>
            <div class="pull-right">
            </div>
        </div>
    </div>
</div>
<table id="zdgl_glzd_dg_his"></table>
<script type="text/javascript">
    var id2 = '${id2}';
    var dg;
    $(function(){
        dg=$('#zdgl_glzd_dg_his').datagrid({
            method: "post",
            url:ctx+'/zdgl/glzd/hislist?id2='+id2,
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
                {field:'m2',title:'制度编号',sortable:false,width:60},
                {field:'m1',title:'制度名称',sortable:false,width:100,align:'center',
                    formatter : function(value, row, index) {
                        return "<a onclick='view2("+row.id+")'>"+value+"</a>";
                    }
                },
                {field:'m3',title:'版本号',sortable:false,width:60,align:'center'},
                {field:'m4',title:'发布日期',sortable:false,width:60,align:'center',
                    formatter : function(value, row, index) {
                        if(value!==null&&value!='') {
                            var datetime=new Date(value);
                            return datetime.format('yyyy-MM-dd');
                        }
                    }
                },
                {field:'s1',title:'修订日期',sortable:false,width:60,align:'center',
                    formatter : function(value, row, index) {
                        if(value!==null&&value!='') {
                            var datetime=new Date(value);
                            return datetime.format('yyyy-MM-dd');
                        }
                    }
                }
            ]],
            onDblClickRow: function (rowdata, rowindex, rowDomElement){
                view();
            },
            checkOnSelect:false,
            selectOnCheck:false,
            toolbar:'#zdgl_glzd_tb_his'
        });
    });
    //查看
    function view(){
        var row = dg.datagrid('getSelected');
        if(row==null) {
            layer.msg("请选择一行记录！",{time: 1000});
            return;
        }
        openDialogView("查看安全管理制度信息",ctx+"/zdgl/glzd/hisview/"+row.id,"800px", "400px","");
    }
</script>
</body>
</html>