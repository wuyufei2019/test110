<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
    <title></title>
    <meta name="decorator" content="default"/>
</head>
<body >
<!-- 工具栏 -->
<div id="sbgl_sbyfbyndjh_tb" style="padding:5px;height:auto">
    <form id="searchFrom"  style="margin-bottom: 8px;" class="form-inline pull-left">
        <div class="form-group">
            <c:if test="${ type eq '2' }">
                <input type="text" id="view_qyname" name="view_qyname" class="easyui-combobox"  style="height: 30px;" data-options="editable:true ,valueField: 'text',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson',prompt: '企业名称' "/>
            </c:if>
            <input type="text" id="m8" name="m8" class="easyui-combobox"  style="height: 30px;" data-options="prompt: '年份' "/>
            <input type="text" id="m1" name="m1" class="easyui-textbox"  style="height: 30px;" data-options="prompt: '项目名称' "/>

        </div>
    </form>
    <div class="pull-left">
        <button  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</button>
        <button  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 重置</button>
    </div>
    <div class="row">
        <div class="col-sm-12">
            <div class="pull-left">
                <shiro:hasPermission name="sbgl:yfbyndjh:add">
                    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add()" title="添加"><i class="fa fa-plus"></i> 添加</button>
                </shiro:hasPermission>
                <shiro:hasPermission name="sbgl:yfbyndjh:update">
                    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd()" title="修改"><i class="fa fa-file-text-o"></i> 修改</button>
                </shiro:hasPermission>
                <shiro:hasPermission name="sbgl:yfbyndjh:delete">
                    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除"><i class="fa fa-trash-o"></i> 删除</button>
                </shiro:hasPermission>
                <shiro:hasPermission name="sbgl:yfbyndjh:view">
                    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view()" title="查看"><i class="fa fa-search-plus"></i> 查看</button>
                </shiro:hasPermission>
                <shiro:hasPermission name="sbgl:yfbyndjh:export">
                    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="fileexport()" title="导出"><i class="fa fa-file-excel-o"></i> 导出</button>
                </shiro:hasPermission>
                <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
            </div>
        </div>
    </div>
</div>
<table id="sbgl_sbyfbyndjh_dg"></table>
<script type="text/javascript">
    var type='${type}';
    var usertype='${usertype}';
    var dg;
    $(function(){
        dg=$('#sbgl_sbyfbyndjh_dg').datagrid({
            method: "post",
            url:ctx+'/sbgl/yfbyndjh/list',
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
                {field:'id',title:'id',checkbox:true,width:50,align:'center'},
                {field:'qyname',title:'企业名称',sortable:false,width:100,align:'left'},
                {field:'m8',title:'年度',sortable:false,width:80,align:'center'},
                {field:'m1',title:'项目名称',sortable:false,width:80,align:'center'},
                {field:'m2',title:'检修内容',sortable:false,width:90},
                {field:'m3',title:'检修方案',sortable:false,width:90},
                {field:'m4',title:'检修人员',sortable:false,width:40,align:'center'},
                {field:'m7',title:'检修进度', sortable:false,width:50,align:'center'}
            ]],
            onDblClickRow: function (rowdata, rowindex, rowDomElement){
                view();
            },
            onLoadSuccess: function(){
                $(this).datagrid("autoMergeCells",['sbname']);
                if(type=="1"){
                    $(this).datagrid("hideColumn",['qyname']);
                }else{
                    $(this).datagrid("showColumn",['qyname']);
                    $(this).datagrid("autoMergeCells",['qyname']);
                }
            },
            checkOnSelect:false,
            selectOnCheck:false,
            toolbar:'#sbgl_sbyfbyndjh_tb'
        });

    });
    //弹窗增加
    function add(u) {
        openDialog("添加预防保养年度计划",ctx+"/sbgl/yfbyndjh/create/","900px", "500px","");
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
                url:ctx+"/sbgl/yfbyndjh/delete/"+ids,
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
        openDialog("修改预防保养年度计划",ctx+"/sbgl/yfbyndjh/update/"+row.id,"800px", "400px","");
    }

    //查看
    function view(){
        var row = dg.datagrid('getSelected');
        if(row==null) {
            layer.msg("请选择一行记录！",{time: 1000});
            return;
        }

        openDialogView("查看预防保养年度计划",ctx+"/sbgl/yfbyndjh/view/"+row.id,"800px", "400px","");

    }

    //导出
    function fileexport(){
        window.expara=$("#searchFrom").serializeObject();
        window.exdata=[
            {colval:'m8', coltext:'检修年度'},
            {colval:'m1', coltext:'项目名称'},
            {colval:'m2', coltext:'检修内容'},
            {colval:'m3', coltext:'检修方案'},
            {colval:'m4', coltext:'检修人员'},
            {colval:'m5', coltext:'安全措施'},
            {colval:'m6', coltext:'检修质量'},
            {colval:'m6', coltext:'检修进度'}
        ];
        layer.open({
            type: 2,
            area: ['350px', '350px'],
            title: '导出列选择',
            maxmin: true,
            shift: 1,
            content: ctx+'/sbgl/yfbyndjh/colindex',
            btn: ['导出'],
            yes: function(index, layero){
                var body = layer.getChildFrame('body', index);
                var iframeWin = layero.find('iframe')[0]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
                var inputForm = body.find('#excel_mainform');
                iframeWin.contentWindow.doExport();
            },
        });

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

    //年份下拉框初始化
    $("#m8").combobox({
        valueField:'year',
        textField:'year',
        panelHeight:'auto'
    });
    var data = [];
    var thisYear;
    var startYear=new Date().getUTCFullYear()+2;

    for(var i=0;i<6;i++){
        thisYear=startYear-i;
        data.push({"year":thisYear});
    }

    $("#m8").combobox("loadData", data);//下拉框加载数据

</script>

</body>
</html>