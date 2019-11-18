<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
   <title>代码创建工具</title>
   <meta name="decorator" content="default" />
</head>
<body>

<table id="rwtable" class="table table-bordered">
   <tfoot>
   <tr>
      <td align="center" colspan="4"><a class="btn btn-info btn-sm" data-toggle="tooltip" data-placement="left"
                                        onclick="openDialog('字段添加', '${ctx}/system/buildcode/addcolumn', '900px', '400px', '')" title="添加字段">
         <i class="fa fa-plus"> </i>添加字段
      </a> <a class="btn btn-info btn-sm" data-toggle="tooltip" data-placement="left" onclick="buildcode()" title="生成代码">
         <i class="fa fa-file"> </i>生成代码
      </a></td>
   </tr>
   </tfoot>
   <tbody>
   <tr id="basicinfo">
      <td align="center" colspan="4">
         <div class="easyui-accordion" id="accordion" border="false">
            <div title="基本信息" border="false">
               <form id="basicForm">
                  <table class="table table-bordered  dataTable">
                     <tbody>
                     <tr>
                        <td class="width-15 active"><label class="pull-right">Java类包路径：</label></td>
                        <td class="width-35"><input id="code_classpack" name="code_classpack" type="text" class="easyui-combobox"
                                                    style="width: 100%;height: 30px;"
                                                    data-options="prompt: '例如:com.cczu.model.xxx',valueField: 'text',textField: 'text',panelHeight:'auto',
                                        data:[{text:'com.cczu.model'},{text:'com.cczu.model.xxx'}],validType:'packformat'">
                        <td class="width-15 active"><label class="pull-right">前台文件存放路径：</label></td>
                        <td class="width-35"><input id="code_viewpath" name="code_viewpath" type="text" class="easyui-textbox"
                                                    style="width: 100%;height: 30px;"
                                                    data-options="prompt: '例如:xxx/xxx'">

                     </tr>
                     <tr>
                        <td class="width-15 active"><label class="pull-right">实体类名：</label></td>
                        <td class="width-35"><input id="code_classname" name="code_classname" style="width: 100%;height: 30px;"
                                                    class="easyui-textbox" data-options="required:'true',validType:'englishOrNum'" /></td>
                        <td class="width-15 active"><label class="pull-right">类描述：</label></td>
                        <td class="width-35"><input id="code_description" name="code_description" style="width: 100%;height: 30px;"
                                                    class="easyui-textbox" /></td>
                     </tr>
                     <tr>
                        <td class="width-15 active"><label class="pull-right">映射路径：</label></td>
                        <td class="width-35"><input id="code_mapurl" name="code_mapurl" style="width: 100%;height: 30px;"
                                                    class="easyui-textbox" data-options="prompt: '例如:system/buildcode'" /></td>
                        <td class="width-15 active"><label class="pull-right">文件存放路径：</label></td>
                        <td class="width-35"><input id="code_fileurl" name="code_fileurl" style="width: 100%;height: 30px;"
                                                    class="easyui-textbox" /></td>
                     </tr>
                     <tr>
                        <td class="width-15 active"><label class="pull-right">是否生成系统管理菜单：</label></td>
                        <td class="width-35"><input type="radio" value="true" class="i-checks" name="code_sysmeun" />生成 <input
                                type="radio" value="false" class="i-checks" name="code_sysmeun" checked="checked" />不生成</td>
                        <td class="width-15 active"><label class="pull-right">是否启用shiro权限：</label></td>
                        <td class="width-35" ><input type="radio" value="true" class="i-checks" name="code_permisson" />启用 <input
                                type="radio" value="false" class="i-checks" name="code_permisson" checked="checked" />不启用</td>
                     </tr>
                     </tbody>
                  </table>
               </form>
            </div>
         </div>
      </td>
   </tr>

   <tr id="meun" style="display: none;">
      <td align="center" colspan="4">
         <div class="easyui-accordion" id="accordion" border="false">
            <div title="菜单信息" border="false">
               <form id="meunForm" action="${ctx}/system/permission/createReId" method="post" class="form-horizontal">
                  <table class="table table-bordered  dataTable">
                     <tbody>
                     <tr>
                        <input type="hidden" name="type" value="F"/>
                        <td class="width-15 active"><label class="pull-right">菜单名称:</label></td>
                        <td class="width-35"><%--<input id="name" name="name" class="easyui-textbox " style="width: 100%;height: 30px;"
                                       data-options="required:true" /></td>--%>
                           <input type="text" id="menuname" name="name" class="easyui-combotree"
                                  style="width: 100%;height: 30px;" data-options="valueField: 'text',textField: 'text',editabled:true,required:'true',url:'${ctx}/system/permission/menu/json2'" /></td>
                        <input type="hidden" id="menuid" name="id">
                        <td class="width-15 active"><label class="pull-right">菜单路径:</label></td>
                        <td class="width-35"><input id="url" name="url" class="easyui-textbox " style="width: 100%;height: 30px;" /></td>
                     </tr>
                     <tr>
                        <td class="width-15 active"><label class="pull-right">菜图标:</label></td>
                        <td class="width-35"><input id="icon" name="icon" style="width: 100%;height: 30px;" /></td>
                        <td class="width-15 active"><label class="pull-right">上级菜单:</label></td>
                        <td class="width-35"><input type="text" id="pid" name="pid" class="easyui-combotree"
                                                    style="width: 100%;height: 30px;" data-options="required:'true',url:'${ctx}/system/permission/menu/json2'" /></td>
                     </tr>
                     <tr>
                        <td class="width-15 active"><label class="pull-right">排序:</label></td>
                        <td class="width-35"><input type="text" id="menusort" name="sort" class="easyui-textbox easyui-validatebox"
                                                    style="width: 100%;height: 30px;" data-options="validType:'number'" /></td>
                        <td class="width-15 active"><label class="pull-right">描述:</label></td>
                        <td class="width-35"><input type="text" id="menudesc" name="description" class="easyui-textbox"
                                                    style="width: 100%;height: 80px;" data-options="multiline:true" /></td>
                     </tr>
                     </tbody>
                  </table>
               </form>
            </div>
         </div>
      </td>
   </tr>

   <tr id="permisson" style="display: none;">
      <td align="center" colspan="4">
         <div class="easyui-accordion" id="accordion" border="false">
            <div title="权限信息" border="false">
               <form id="perForm">
                  <table class="table table-bordered  dataTable">
                     <tbody>
                     <tr>
                        <td class="width-15 active"><label class="pull-right">权限编码：</label></td>
                        <td class="width-35"><input id="permCode" name="permCode" class="easyui-textbox"
                                                    style="width:100%;height: 30px;" data-options="required:true ,prompt: '省略增删改查后缀'" /></td>
                        <td class="width-15 active"><label class="pull-right">描述：</label></td>
                        <td class="width-35"><input id="description" name="description" class="easyui-textbox"
                                                    style="width:100% ;height: 30px;" data-options="prompt: '省略增删改查后缀'" /></td>
                     </tr>
                     <tr>
                        <td class="width-15 active"><label class="pull-right">是否同步至数据库：</label></td>
                        <td class="width-35"><input type="radio" value="true" class="i-checks" name="persyncdatabase" />同步 <input
                                type="radio" value="false" class="i-checks" name="persyncdatabase" /> 不同步</td>
                     </tr>
                     </tbody>
                  </table>
               </form>
            </div>
         </div>
      </td>
   </tr>
   </tbody>
</table>
<div class="easyui-accordion" id="accordion" border="false">
   <div title="字段信息" data-options="selected:true" style="padding:10px;">
      <table id="column_dg"></table>
   </div>
</div>

<script type="text/javascript">
    var G_coldata=[{col_ename: "ID", col_cname: "编号", col_datatype: "bigint", col_nullable: "false", col_serach: "false"},
        {col_ename: "S1", col_cname: "创建时间", col_datatype: "datetime", col_nullable: "false", col_serach: "false"},
        {col_ename: "S2", col_cname: "更新时间", col_datatype: "datetime", col_nullable: "false", col_serach: "false"},
        {col_ename: "S3", col_cname: "删除标识", col_datatype: "int", col_nullable: "false", col_serach: "false"}] ; //字段数据
    var dg;
    $(function() {
        $("#menuname").combotree({
            editable:true,
            onSelect :function (rec) {
                $("#menuid").val(rec.id);
                $("#menusort").textbox("setValue",rec.sort);
                if(rec.pid&&rec.pid!='null'){
                    $("#pid").combotree("setValue",rec.pid);
                }else{
                    $("#pid").combotree("setValue",null);
                }
            }
        });//启用编辑功能
        $("#code_fileurl").textbox("setValue","C:\\codebuild");
        $("[name='code_sysmeun']").on('ifChecked', function(event){
            $("input",$("#code_mapurl").next("span")).blur();//让mapurl控件失去焦点，否则取不到值
            $("#meun").toggle();
            $.parser.parse('#meun'); // 解析
            $("#url").textbox("setValue",$("#code_mapurl").textbox("getValue")+"/index");
            $("#menudesc").textbox("setValue",$("#code_description").textbox("getValue"));
            if($(event.target).val()=="true"){
                $("[name='persyncdatabase']").iCheck("enable");
            }else{
                $("[name='persyncdatabase']").iCheck("disable");
            }
        });
        $("[name='code_permisson']").on('ifChecked', function(event){
            $("#permisson").toggle();
            $.parser.parse('#permisson'); // 解析
            $("#description").textbox("setValue",$("#code_description").textbox("getValue"));
            //补填：
            $("input", $("#permCode").next("span")).blur(function() {
                var permCode = $("#permCode").textbox("getValue");
                if((permCode.charAt(permCode.length-1))!=":"){
                    $("#permCode").textbox("setValue",permCode+":");
                }

            });
            //补填-
            $("input", $("#description").next("span")).blur(function() {
                var description = $("#description").textbox("getValue");
                if((description.charAt(description.length-1))!="-"){
                    $("#description").textbox("setValue",description+"-");
                }
            });
            var issysmeun=$("[name='code_sysmeun']:checked").val();
            //生成菜单，才能生成权限
            if(issysmeun=="true"){
                $("[name='persyncdatabase']:first").iCheck('check');
            }else{
                $("[name='persyncdatabase']:last").iCheck('check');
                $("[name='persyncdatabase']").iCheck("disable");
            }
        });

        $('#icon').combobox({
            valueField:'value',
            textField:'text',
            url:'${ctx}/system/permission/getBigIcons',
            editable:true,
            formatter:function(data){
                return '<img class="item-img" width="50" height="50" src="${ctxStatic}/model/main/images/'+data.text+'"/><span class="item-text">'+data.value+'</span>';
            },
        });
        dg = $('#column_dg').datagrid({
            fitColumns : true,
            border : false,
            striped : true,
            rownumbers : true,
            nowrap : false,
            scrollbarSize : 0,
            singleSelect : true,
            striped : true,
            columns : [ [
                {field:'col_ename',title:'字段英文名',sortable:false,width:50},
                {field:'col_cname',title:'字段中文含义',sortable:false,width:50},
                {field:'col_datatype',title:'数据类型',sortable:false,width:40},
                {field:'col_nullable',title:'可否为空/必填项',sortable:false,width:40, formatter : function (value){
                        if(value=="true")
                            return "可为空 ";
                        else
                            return "不为空 ";
                    }},
                {field:'col_serach',title:'是否为查询条件',sortable:false,width:40, formatter : function (value){
                        if(value=="false")
                            return "是 ";
                        else
                            return "否";
                    }},
                {field:'caozuo',title:'操作', sortable:false, width:30,align :'center',
                    formatter : function (value, row, index){
                        if(row.col_ename!="ID"&&row.col_ename!="S1"&&row.col_ename!="S2"&&row.col_ename!="S3")
                            return "<a class=' fa fa-times-circle btn btn-danger btn-xs' onclick='remove(\""+row.col_ename+"\")'>移除</a>";
                    }}
            ] ],
            onLoadSuccess: function(){
            },
            checkOnSelect : false,
            selectOnCheck : false
        });
        dg.datagrid('loadData',G_coldata);
    });

    //移除dg数据行
    function remove(val){
        for(var index in G_coldata){
            if(G_coldata[index].col_ename==val){
                G_coldata.splice(index,1);
                break;
            }
        }
        dg.datagrid('loadData',G_coldata);
    }

    //生成代码
    function buildcode(){
        var tindex=layer.load();
        if(!$("#basicForm").form('validate')){
            layer.close(tindex);
            return false;
        }
        var obj = $("#basicForm").serializeObject();
        var meunobj = {};
        var perobj = {};
        //生成菜单菜单
        if(obj.code_sysmeun=="true"){
            if($("#meunForm").form('validate')){
                meunobj = $("#meunForm").serializeObject();
                Object.assign(obj,{meun : meunobj});
            }else{
                return ;
            }
        }
        //生成权限
        if(obj.code_permisson=="true"){
            if($("#perForm").form('validate')){
                perobj =$("#perForm").serializeObject();
                Object.assign(obj,{permisson : perobj});
            }else{return ;}
        }
        Object.assign(obj,{columns : G_coldata});
        $.post("${ctx}/system/buildcode/create",{set:JSON.stringify(obj)},function(data){
            layer.msg(data);
            layer.close(tindex);
        });

    }

</script>


</body>
</html>