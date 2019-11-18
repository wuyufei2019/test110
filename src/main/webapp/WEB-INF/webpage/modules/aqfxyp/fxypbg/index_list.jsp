<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
    <title>安全风险研判选择</title>
    <meta name="decorator" content="default"/>
</head>
<body >
<!-- 工具栏 -->
<div id="aqfxyp_listchoose_tb" style="padding:5px;height:auto">
    <form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">
        <div class="form-group" style="margin-top:10px">
            <input type="text" id="m2" name="m2"  class="easyui-textbox" style="height: 30px" data-options="prompt: '风险研判内容小类'" />
        </div>
    </form>
    <div class="pull-left" style="margin-top:-45px;margin-left:180px">
        <button  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</button>
        <button  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</button>
    </div>
</div>
<table id="aqfxyp_listchoose_dg"></table>
<script type="text/javascript">
    var dg;
    //创建查询对象并查询
    function search(){
        var obj=$("#searchFrom").serializeObject();
        dg.datagrid('load',obj);
    }

    function reset(){
        $("#m2").textbox('clear');
        search();
    }
    function getidname(){
        var row=$('#aqfxyp_listchoose_dg').datagrid('getChecked');
        var idname="";
        if (row != null) {
            for (var i = 0; i < row.length; i++) {
                idname = idname +row[i].m2+ ",";
            }
        }
        return idname;
    }
    $(function(){
        dg=$('#aqfxyp_listchoose_dg').datagrid({
            method: "post",
            url:ctx+'/aqfxyp/aqfxypk/list?m1='+${type},
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
            checkOnSelect:true,
            selectOnCheck:false,
            columns:[[
                {field:'ID',title:'id',checkbox:true,width:50,align:'center'},
                {field:'m1',title:'大类',width:60,align:'center',
                    formatter : function(value) {
                        if (value == 1) {
                            return '生产装置安全运行状态';
                        }else if (value == 2) {
                            return '危险化学品、罐区、仓库等重大危险源的安全运行状态';
                        }else if (value == 3) {
                            return '高危生产活动及作业的安全风险可控状态';
                        }else {
                            return;
                        }
                    }},
                {field:'m2',title:'小类',width:120,align:'center'},
                {field:'m3',title:'备注',width:120,align:'center'}
            ]],
            onDblClickRow: function (rowdata, rowindex, rowDomElement){
            },
            onLoadSuccess:function(){
                $(this).datagrid("autoMergeCells",['m1']);
            },
            toolbar:'#aqfxyp_listchoose_tb'
        });
    });
</script>
</body>
</html>