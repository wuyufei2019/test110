<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
    <title>风险研判报告</title>
    <meta name="decorator" content="default"/>
</head>
<body>
<form id="inputForm" action="${ctx}/aqfxyp/fxypbg/${action}"  method="post" class="form-horizontal" >
    <table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
        <tbody>
        <tr>
            <td class="width-15 active"><label class="pull-right">等级：</label></td>
            <td class="width-35">
                <input id="M1" name="M1" class="easyui-combobox" value="${res.m1 }" style="width: 100%;height: 30px;"
                       data-options="required:true,editable:false,panelHeight:'auto',data:[{value:'厂级',text:'厂级'},{value:'车间级',text:'车间级'},{value:'班组级',text:'班组级'}]"/>
            </td>
            <td class="width-15 active"><label class="pull-right">负责人：</label></td>
            <td class="width-35">
                <input name="M10" class="easyui-textbox" value="${res.m10 }" style="width: 100%;height: 30px;"
                       data-options="required:true,alidType:['length[0,20]']"/>
            </td>

        </tr>
        <tr>
            <td class="width-15 active"><label class="pull-right">日期：</label></td>
            <td class="width-35">
                <input id="M11" name="M11" class="easyui-datebox" value="${res.m11 }" style="width: 100%;height: 30px;"
                       data-options="required:true"/>
            </td>

        </tr>
        <tr class="level">
            <td class="width-15 active"><label class="pull-right">车间：</label></td>
            <td class="width-35">
                <input id="M2" name="M2" class="easyui-textbox" value="${res.m2 }" style="width: 100%;height: 30px;"
                       data-options="alidType:['length[0,50]'] "/>
            </td>
            <td class="width-15 active"><label class="pull-right">班组：</label></td>
            <td class="width-35">
                <input id="M3" name="M3" class="easyui-textbox" value="${res.m3 }" style="width: 100%;height: 30px;"
                       data-options="alidType:['length[0,50]'] "/>
            </td>
        </tr>
        <tr class="level">
            <td class="width-15 active"><label class="pull-right">研判风险 ：</label></td>
            <td class="width-85" colspan="3">
                <input id="M4" name="M4" style="width: 100%;height: 50px;" value="${res.m4 }" class="easyui-textbox"
                       data-options="multiline:'true',alidType:['length[0,500]'] "/>
            </td>
        </tr>
        <tr class="level">
            <td class="width-15 active"><label class="pull-right">高危生产活动及</br>作业的安全风险</br>可控状态 ：</label></td>
            <td class="width-85" colspan="3">
                <div style="text-align:center;white-space:normal;height:auto;" class="datagrid-cell datagrid-cell-c1-zt"><a  class='btn btn-success btn-xs '  onclick='openlist(3)'>添加信息</a></div>
                <input id="M5" name="M5" style="width: 100%;height: 80px;"  class="easyui-textbox"
                       data-options="multiline:'true',alidType:['length[0,2000]'] "/>
            </td>
        </tr>
        <tr class="level">
            <td class="width-15 active"><label class="pull-right">生产装置</br>运行状态 ：</label></td>
            <td class="width-85" colspan="3">
                <div style="text-align:center;white-space:normal;height:auto;" class="datagrid-cell datagrid-cell-c1-zt"><a  class='btn btn-success btn-xs '  onclick='openlist(1)'>添加信息</a></div>
                <input id="M6" name="M6" style="width: 100%;height: 80px;"  class="easyui-textbox"
                       data-options="multiline:'true',alidType:['length[0,2000]'] "/>
            </td>
        </tr>
        <tr class="level">
            <td class="width-15 active"><label class="pull-right">涉及罐区、仓库等</br>重大危险源</br>处于安全状态 ：</label></td>
            <td class="width-85" colspan="3">
                <div style="text-align:center;white-space:normal;height:auto;" class="datagrid-cell datagrid-cell-c1-zt"><a  class='btn btn-success btn-xs '  onclick='openlist(2)'>添加信息</a></div>
                <input id="M7" name="M7" style="width: 100%;height: 80px;"  class="easyui-textbox"
                       data-options="multiline:'true',alidType:['length[0,2000]'] "/>
            </td>
        </tr>
        <tr>
            <td class="width-15 active"><label class="pull-right">安全状态 ：</label></td>
            <td class="width-85" colspan="3">
                <input id="M8" name="M8" style="width: 100%;height: 80px;"  class="easyui-textbox"
                       data-options="multiline:'true',alidType:['length[0,2000]'] "/>
            </td>
        </tr>
        <tr>
            <td class="width-15 active"><label class="pull-right">承诺 ：</label></td>
            <td class="width-85" colspan="3">
                <input id="M9" name="M9" style="width: 100%;height: 80px;" value="${res.m9 }" class="easyui-textbox"
                       data-options="multiline:'true',alidType:['length[0,2000]'] "/>
            </td>
        </tr>

        <c:if test="${not empty res.ID}">
            <input type="hidden" name="ID" value="${res.ID}"/>
            <input type="hidden" name="ID1" value="${res.ID1}"/>
            <input type="hidden" name="S1"
                   value="<fmt:formatDate value="${res.s1}" pattern="yyyy-MM-dd HH:mm:ss"  />"/>
            <input type="hidden" name="S3" value="${res.s3}"/>
        </c:if>
        </tr></tbody>
    </table>
</form>
<script type="text/javascript">
    //风险研判点
    var str1 = "风险研判点（ ）处；\r\n各风险点风险研判结果落实相应的风险管控措施 （ 是/否 ）";
    //高危。。状态固定句
    var str2 = "一级动火作业（ ）处;二级动火作业（ ）处；\r\n特级动火作业（ ）处;受限空间作业（ ）处；\r\n高处作业（ ）处;其它特殊作业（ ）处；";
    //班组安全状况
    var str3 = "班组岗位（ ）处，其中运行（ 处），停产（ ）处，检修（ ）处；";
    //班组承诺
    var str4 = "今天我班组已根据岗位风险辨识管控相关内容以及安全风险评估表进行安全风险研判，各项安全风险防范措施已落实到位，我承诺本岗位处于安全运行状态，涉及罐区、仓库等重大危险源安全风险得到有效管控。\r\n ";
    //车间安全状况
    var str5 = "车间岗位（ ）处，其中运行（ ）处，停产（ ）处，检修（ ）处";
    //车间承诺
    var str6 = "今天我车间已根据岗位风险辨识管控相关内容以及安全风险评估表进行安全风险研判，各项安全风险防范措施已落实到位，我承诺本岗位处于安全运行状态，涉及罐区、仓库等重大危险源安全风险得到有效管控。\r\n";
    //企业状态
    var str7 = "生产装置 （ ）套，其中运行 （ ）套，停产（ ） 套，检修（ ） 套；\r\n 特殊作业：特级动火（ ） 处;一级动火（ ） 处;二级动火（ ） 处；进入受限空间作业（ ） 处；\r\n" +
        "是否处于试生产  （ 是/否 )\r\n是否处于开停车状态  （ 是/否 )\r\n重大危险源是否处于安全状态   （ 是/否 )\r\n";
    //企业承诺
    var str8 = "今天我公司已运行安全风险研判，各项安全风险防范措施已落实到位，我承诺所有生产装置处于安全运行状态，重大危险源装置及罐区得到有效管控。\r\n  ";

    var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
    var validateForm;
    function doSubmit(){
        $("#inputForm").submit();
    }

    //根据等级隐藏信息
    function showOrHidden(m1) {
        if (m1 == "厂级") {
            $(".level").hide();
        }else {
            $(".level").show();
        }
    }

    //初始化数据
    function init(m1){

        if (m1) {
            if (m1 == "厂级") {
                $("#M4").textbox("setValue", "");
                $("#M5").textbox("setValue", "");
                $("#M6").textbox("setValue", "");
                $("#M7").textbox("setValue", "");
                $("#M8").textbox("setValue", str7);
                $("#M9").textbox("setValue", str8);
            }else if (m1 == "车间级") {
                $("#M4").textbox("setValue", str1);
                $("#M5").textbox("setValue", str2);
                $("#M8").textbox("setValue", str5);
                $("#M9").textbox("setValue", str6);
            }else {
                $("#M4").textbox("setValue", str1);
                $("#M5").textbox("setValue", str2);
                $("#M8").textbox("setValue", str3);
                $("#M9").textbox("setValue", str4);
            }
        }else {
            $("#M4").textbox("setValue", str1);
            $("#M5").textbox("setValue", str2);
        }
    }
    //将str 中的 o 替换为 n
    function replaceTextarea(str,o,n){
        var reg=new RegExp(o,"g");
        str = str.replace(reg,n);
        return str;
    }


    //修改时赋值，保留换行
    function setValue() {
        $("#M4").textbox("setValue", replaceTextarea("${res.m4}","<br>","\r\n"));
        $("#M5").textbox("setValue", replaceTextarea("${res.m5}","<br>","\r\n"));
        $("#M6").textbox("setValue", replaceTextarea("${res.m6}","<br>","\r\n"));
        $("#M7").textbox("setValue", replaceTextarea("${res.m7}","<br>","\r\n"));
        $("#M8").textbox("setValue", replaceTextarea("${res.m8}","<br>","\r\n"));
        $("#M9").textbox("setValue", replaceTextarea("${res.m9}","<br>","\r\n"));
    }

    //弹出安全风险研判选择框
    function openlist(type) {
        layer.open({
            type: 2,
            area: ['700px', '500px'],
            title: '选择安全风险研判',
            maxmin: false,
            content: ctx + "/aqfxyp/fxypbg/listchoose/"+type ,
            btn: ['确定', '关闭'],
            yes: function(index, layero){
                var  $list;
                if (type == 1) {
                    $list= $("#M6");
                }else if (type == 2) {
                    $list= $("#M7");
                }else {
                    $list= $("#M5");
                }
                var oldText = $list.textbox("getValue");
                var newText = "";
                if (oldText != "") {
                    newText = oldText ;
                }
                var iframeWin = layero.find('iframe')[0];
                var idname=iframeWin.contentWindow.getidname();
                var arr=idname.split(",");
                for (var i = 0; i < arr.length-1; i++) {
                    // 安全风险研判拼接
                    if (arr[i]!="") {
                        newText = newText+"\r\n"+ arr[i] +"    （ 是/否 ）；";
                    }
                }
                $list.textbox("setValue", newText);
                layer.close(index);
            },
            cancel: function(index){}
        });
    }


    $(function(){
        if ('create'=='${action}') {
            init();
        }else {
            showOrHidden('${res.m1 }');
            setValue();
        }
        $("#M1").combobox({
            onSelect:function (data) {
                showOrHidden(data.value);
                init(data.value);
            }
        })

        var flag=true;


        $('#inputForm').form({
            onSubmit : function() {
                var isValid = $(this).form('validate');
                if(isValid&&flag){
                    flag=false;
                    $.jBox.tip("正在提交，请稍等...",'loading',{opacity:0});
                    return true;
                }
                return false; // 返回false终止表单提交
            },
            success:function(data){
                $.jBox.closeTip();
                if(data=='success')
                    parent.layer.open({icon:1,title: '提示',offset: 'rb',content: '操作成功！',shade: 0 ,time: 2000 });
                else
                    parent.layer.open({icon:2,title: '提示',offset: 'rb',content: '操作失败！',shade: 0 ,time: 2000 });
                parent.dg.datagrid('reload');
                parent.layer.close(index);//关闭对话框。
            }
        });
    });
</script>
</body>
</html>