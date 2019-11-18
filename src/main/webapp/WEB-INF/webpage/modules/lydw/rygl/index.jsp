<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>员工工卡管理</title>
	<meta name="decorator" content="default"/>
</head>
<body >
<!-- 工具栏 -->
<div id="lydw_rygl_tb" style="padding:5px;height:auto">
	<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">				
	<div class="form-group">
	<input type="text" id="lydw_rygl_cx_m2" name="lydw_rygl_cx_m2" class="easyui-textbox"  style="height: 30px;width: 120px;" data-options="prompt: '工号'"/>
	<input type="text" id="lydw_rygl_cx_m1" name="lydw_rygl_cx_m1" class="easyui-textbox"  style="height: 30px;width: 100px;" data-options="prompt: '姓名'"/>
	<input type="text" name="lydw_rygl_cx_status" class="easyui-combobox" style="height: 30px;width: 100px" data-options="prompt: '人员状态',editable:false ,panelHeight:'auto', data: [
										  {value:'1',text:'在职'},
								          {value:'2',text:'离职'}]" />
	<input type="text" name="lydw_rygl_cx_filecode" class="easyui-textbox" style="height: 30px;" data-options="prompt: '工卡号' " />
	<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</span>
	<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span>
    </div>
	</form>
</div>
<table id="lydw_rygl_dg"></table> 

<script type="text/javascript">
    var dg;
    var d;
    $(function(){
        dg=$('#lydw_rygl_dg').datagrid({
            method: "post",
            url:ctx+'/lydw/rygl/listjson',
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
                {field:'m2',title:'工号',sortable:false,width:60},
                {field:'m1',title:'姓名',sortable:false,width:40,align:'center'},
                {field:'m3',title:'性别',sortable:false,width:40,align:'center'},
                {field:'m8',title:'身份证号',sortable:false,width:100,align:'center'},
                {field:'bm',title:'部门',sortable:false,width:60,align:'center'},
                {field:'m4',title:'职位',sortable:false,width:60,align:'center'},
                {field:'m9',title:'联系方式',sortable:false,width:70,align:'center'},
                {field:'status',title:'人员状态',sortable:false,width:60,align:'center',
                    formatter : function(value, row, index) {
                        if(value == 2) {
                            return '离职';
                        }else {
                            return '在职';
                        }
                    }
                },
                {field:'fileid',title:'工卡号',sortable:false,width:80,align:'center',formatter:function (value,row) {
                        if(!value){  return '/'; }else{ return  value; }
                	} },
                {field:'val1',title:'状态',sortable:false,width:80,align:'center',formatter:function (value,row) {
                        if(!value){  return '/'; }else{ return  value; }
                	} },
                {field:'online',title:'是否在线',sortable:false,width:80,align:'center',
                    formatter : function (value, row, index) {
                        if(value == '0') {
                            return '离线';
                        }else if(value == '1'){
                            return '在线';
                        }
                    }},
                {field:'cz',title:'工卡绑定',sortable:false,width:80,align:'center',formatter:function (value,row) {
                        var html="";
                        if(!row.fileid){
                            html="<a class='btn btn-success btn-xs'  onclick='doCard("+row.id+")'>绑卡</a>";
                        }else{
                            html="<a class='btn btn-danger btn-xs' onclick='undoCard("+row.bdid+")'>解绑</a>";
                        }
                        return html;
                    }
                }
            ]],
            onDblClickRow: function (rowdata, rowindex, rowDomElement){

            },
            checkOnSelect:false,
            selectOnCheck:false,
            toolbar:'#lydw_rygl_tb'
        });

    });

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

    //绑卡
    function doCard(ygid){
        openDialog("选择工卡",ctx+"/lydw/rygl/selectcard/"+ygid,"400px", "300px","");
    }

    //解绑
    function undoCard(bdid){
        top.layer.confirm('确定要解除工卡绑定吗？', {icon: 3, title:'提示'}, function(index){
            $.ajax({
                type:'get',
                url:ctx+"/lydw/rygl/undocard/"+bdid,
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
</script>
</body>
</html>