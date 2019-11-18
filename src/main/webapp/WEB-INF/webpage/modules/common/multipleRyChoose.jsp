<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>人员多选</title>
	<meta name="decorator" content="default"/>
</head>
<body >
<!-- 工具栏 -->
<div id="ry_tb" style="padding:5px;height:auto">
	<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">
				
	<div class="form-group">
		<input type="text" id="ry_name" name="ry_name" class="easyui-textbox" style="height: 30px;" data-options="prompt: '姓名'" />
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</span>
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span>
    </div>
	</form>
</div>


<table id="ry_dg"></table>

<script type="text/javascript">
    var dg;
    var ryids = '${content}';
    $(function(){
        dg=$('#ry_dg').datagrid({
            method: "post",
            url:ctx+'/system/compuser/userjsonlist',
            fit : true,
            fitColumns : true,
            border : false,
            idField : 'id',
            striped:true,
            rownumbers:true,
            nowrap:false,
            scrollbarSize:5,
            singleSelect:false,
            striped:false,
            columns:[[
                {field:'id',title:'id',checkbox:true,align:'center'},
                {field:'text',title:'姓名',width:60}
            ]],
            checkOnSelect:true,
            selectOnCheck:true,
            onLoadSuccess:function(rowdata){
                $.each(rowdata.rows,function(i,row){
                    if((','+ryids+',').indexOf(','+row.id+',')!=-1){
                        dg.datagrid('selectRow',i);
                    }
                });
            },
            toolbar:'#ry_tb'
        });
    });

    //清空
    function reset(){
        $("#searchFrom").form("clear");
        search();
    }

    //查询
    function search(){
        var obj=$("#searchFrom").serializeObject();
        dg.datagrid('load',obj);
    }

    function getidname(){
        var row=$('#ry_dg').datagrid('getChecked');
        var idname="";
        if (row != null) {
            for (var i = 0; i < row.length; i++) {
                idname = idname + row[i].id +"||"+row[i].text+ ",";
            }
        }
        return idname;
    }
</script>
</body>
</html>