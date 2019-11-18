<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
    <title>应急处置方案</title>
    <meta name="decorator" content="default"/>
</head>
<body>
<form id="inputForm" action="${ctx}/erm/yjczfa/${action}"  method="post" class="form-horizontal" >
    <table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
        <tbody>
        <tr>
            <td class="width-15 active"><label class="pull-right">等级：</label></td>
            <td class="width-35">
                <input name="M1" class="easyui-combobox" value="${res.m1 }" style="width: 100%;height: 30px;"
                       data-options="required:true,editable:false,panelHeight:'auto',data:[{value:'厂级',text:'厂级'},{value:'车间级',text:'车间级'},{value:'班组级',text:'班组级'}]"/>
            </td>
            <td class="width-15 active"><label class="pull-right">岗位名称：</label></td>
            <td class="width-35">
                <input id="M2" name="M2" class="easyui-combobox" value="${res.m2 }" style="width: 100%;height: 30px;"
data-options="required:true,editable:false,panelHeight:'auto',data:[{value:'总指挥(主要负责人)_1',text:'总指挥(主要负责人)'},
                       {value:'副总指挥(应急负责人)_1',text:'副总指挥(应急负责人)'},
                       {value:'应急抢险组_2',text:'应急抢险组'},
                       {value:'污染防控组_2',text:'污染防控组'},
                       {value:'后勤保障组_2',text:'后勤保障组'},
                       {value:'警戒保卫组_2',text:'警戒保卫组'},
                       {value:'医疗救护组_2',text:'医疗救护组'},
                       {value:'甲醛生产线_3',text:'甲醛生产线'},
                       {value:'原料成品罐区(火灾)_3',text:'原料成品罐区(火灾)'},
                       {value:'原料成品罐区(泄漏)_3',text:'原料成品罐区(泄漏)'},
                       {value:'装车台(火灾)_3',text:'装车台(火灾)'},
                       {value:'装车台(泄漏)_3',text:'装车台(泄漏)'}]"/>
            </td>
        </tr>
        <tr class="fir">
            <td class="width-15 active"><label class="pull-right">生产安全事故</br>处置程序及职责 ：</label></td>
            <td class="width-85" colspan="3">
                <input id="M3" name="M3" style="width: 100%;height: 80px;" value="${res.m3 }" class="easyui-textbox"
                       data-options="multiline:'true',alidType:['length[0,500]'] ">
            </td>
        </tr>
        <tr class="sec">
            <td class="width-15 active"><label class="pull-right">风险提示 ：</label></td>
            <td class="width-85" colspan="3">
                <input id="M19" name="M19" style="width: 100%;height: 80px;" value="${res.m19 }" class="easyui-textbox"
                       data-options="multiline:'true',alidType:['length[0,500]'] ">
            </td>
        </tr>
        <tr class="sec">
            <td class="width-15 active"><label class="pull-right">应急处置方法 ：</label></td>
            <td class="width-85" colspan="3">
                <input id="M20" name="M20" style="width: 100%;height: 80px;" value="${res.m20 }" class="easyui-textbox"
                       data-options="multiline:'true',alidType:['length[0,500]'] ">
            </td>
        </tr>
        <tr>
            <td class="width-15 active"><label class="pull-right">注意事项 ：</label></td>
            <td class="width-85" colspan="3">
                <input name="M4" style="width: 100%;height: 80px;" value="${res.m4 }" class="easyui-textbox"
                       data-options="multiline:'true',alidType:['length[0,500]'] ">
            </td>
        </tr>
        <tr>
            <td class="width-100 active " colspan="4" style="text-align:center"><label class="center">内部应急联系方式</label>
            </td>
        </tr>
        <tr>
            <td class="width-15 active"><label class="pull-right">联系人岗位 ：</label></td>
            <td class="width-35 active"><input name="M5" style="width: 100%;height: 30px;" value="${res.m5 }" class="in easyui-combobox" data-options="editable:false"></td>
            <td class="width-15 active"><label class="pull-right">联系方式 ：</label></td>
            <td class="width-35" >
                <input name="M5_num" style="width: 100%;height: 40px;" value="${res.m5_num }" class="easyui-textbox"
                       data-options="multiline:'true',alidType:['length[0,500]'] ">
            </td>
        </tr>
        <tr>
            <td class="width-15 active"><label class="pull-right">联系人岗位 ：</label></td>
            <td class="width-35 active"><input name="M6" style="width: 100%;height: 30px;" value="${res.m6 }" class="in easyui-combobox" data-options="editable:false"></td>
            <td class="width-15 active"><label class="pull-right">联系方式 ：</label></td>
            <td class="width-35" >
                <input name="M6_num" style="width: 100%;height: 40px;" value="${res.m6_num }" class="easyui-textbox"
                       data-options="multiline:'true',alidType:['length[0,500]'] ">
            </td>
        </tr>
        <tr>
            <td class="width-15 active"><label class="pull-right">联系人岗位 ：</label></td>
            <td class="width-35 active"><input name="M7" style="width: 100%;height: 30px;" value="${res.m7 }" class="in easyui-combobox" data-options="editable:false"></td>
            <td class="width-15 active"><label class="pull-right">联系方式 ：</label></td>
            <td class="width-35" >
                <input name="M7_num" style="width: 100%;height: 40px;" value="${res.m7_num }" class="easyui-textbox"
                       data-options="multiline:'true',alidType:['length[0,500]'] ">
            </td>
        </tr>
        <tr>
            <td class="width-15 active"><label class="pull-right">联系人岗位 ：</label></td>
            <td class="width-35 active"><input name="M8" style="width: 100%;height: 30px;" value="${res.m8 }" class="in easyui-combobox" data-options="editable:false"></td>
            <td class="width-15 active"><label class="pull-right">联系方式 ：</label></td>
            <td class="width-35" >
                <input name="M8_num" style="width: 100%;height: 40px;" value="${res.m8_num }" class="easyui-textbox"
                       data-options="multiline:'true',alidType:['length[0,500]'] ">
            </td>
        </tr>
        <tr>
            <td class="width-15 active"><label class="pull-right">联系人岗位 ：</label></td>
            <td class="width-35 active"><input name="M9" style="width: 100%;height: 30px;" value="${res.m9 }" class="in easyui-combobox" data-options="editable:false"></td>
            <td class="width-15 active"><label class="pull-right">联系方式 ：</label></td>
            <td class="width-35" >
                <input name="M9_num" style="width: 100%;height: 40px;" value="${res.m9_num }" class="easyui-textbox"
                       data-options="multiline:'true',alidType:['length[0,500]'] ">
            </td>
        </tr>


        <tr>
            <td class="width-100 active" colspan="4" style="text-align:center"><label class="center">外部应急联系方式</label>
            </td>
        </tr>
        <tr>
            <td class="width-15 active"><label class="pull-right">联系人岗位 ：</label></td>
            <td class="width-35 active"><input name="M11" style="width: 100%;height: 30px;" value="${res.m11 }" class="out easyui-combobox" data-options="editable:false"></td>
            <td class="width-15 active"><label class="pull-right">联系方式 ：</label></td>
            <td class="width-35" >
                <input name="M11_num" style="width: 100%;height: 40px;" value="${res.m11_num }" class="easyui-textbox"
                       data-options="multiline:'true',alidType:['length[0,500]'] ">
            </td>
        </tr>
        <tr>
            <td class="width-15 active"><label class="pull-right">联系人岗位 ：</label></td>
            <td class="width-35 active"><input style="width: 100%;height: 30px;" name="M12" value="${res.m12 }" class="out easyui-combobox" data-options="editable:false"></td>
            <td class="width-15 active"><label class="pull-right">联系方式 ：</label></td>
            <td class="width-35" >
                <input name="M12_num" style="width: 100%;height: 40px;" value="${res.m12_num }" class="easyui-textbox"
                       data-options="multiline:'true',alidType:['length[0,500]'] ">
            </td>
        </tr>
        <tr>
            <td class="width-15 active"><label class="pull-right">联系人岗位 ：</label></td>
            <td class="width-35 active"><input name="M13" style="width: 100%;height: 30px;" value="${res.m13 }" class="out easyui-combobox" data-options="editable:false"></td>
            <td class="width-15 active"><label class="pull-right">联系方式 ：</label></td>
            <td class="width-35" >
                <input name="M13_num" style="width: 100%;height: 40px;" value="${res.m13_num }" class="easyui-textbox"
                       data-options="multiline:'true',alidType:['length[0,500]'] ">
            </td>
        </tr>
        <tr>
            <td class="width-15 active"><label class="pull-right">联系人岗位 ：</label></td>
            <td class="width-35 active"><input name="M14" style="width: 100%;height: 30px;" value="${res.m14 }" class="out easyui-combobox" data-options="editable:false"></td>
            <td class="width-15 active"><label class="pull-right">联系方式 ：</label></td>
            <td class="width-35" >
                <input name="M14_num" style="width: 100%;height: 40px;" value="${res.m14_num }" class="easyui-textbox"
                       data-options="multiline:'true',alidType:['length[0,500]'] ">
            </td>
        </tr>
        <tr>
            <td class="width-15 active"><label class="pull-right">联系人岗位 ：</label></td>
            <td class="width-35 active"><input name="M15" style="width: 100%;height: 30px;" value="${res.m15 }" class="out easyui-combobox" data-options="editable:false"></td>
            <td class="width-15 active"><label class="pull-right">联系方式 ：</label></td>
            <td class="width-35" >
                <input name="M15_num" style="width: 100%;height: 40px;" value="${res.m15_num }" class="easyui-textbox"
                       data-options="multiline:'true',alidType:['length[0,500]'] ">
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
    //判断树形结构是否选中
    function checkbm(id){
        var data = $("#"+id).combotree('tree').tree('getSelected');
        var text = $("#"+id).combotree('getText');
        if (!data) {//判断是否选中
            return false;
        }else if (data.text != text) {//防止选中后乱输信息
            return false;
        }

        return true;
    }
    //获取岗位信息
    function getData() {
        var inData = [{value:'本企业救援队',text:'本企业救援队'},{value:'总经理',text:'总经理'},{value:'企业负责人',text:'企业负责人'},{value:'应急负责人 ',text:'应急负责人 '},{value:'生产部门经理 ',text:'生产部门经理 '},{value:'生产副总',text:'生产副总'},{value:'调度中心 ',text:'调度中心 '},{value:'班长',text:'班长'}];
        var outData =[{value:'当地环保部门 ',text:'当地环保部门'},{value:'友邻单位',text:'友邻单位'},{value:'报警电话',text:'报警电话'},{value:'急救电话',text:'急救电话'},{value:'当地应急响应中心',text:'当地应急响应中心'},{value:'当地安监部门 ',text:'当地安监部门 '},{value:'消防队',text:'消防队'}]
        $(".in").combobox('loadData', inData);
        $(".out").combobox('loadData', outData);

    }
    //判断岗位名称类型
    function m2Type(m2) {
        var arr = m2.split("_");
        return arr.length>1?arr[1]:null;
    }
    //判断隐藏或是显示 和清空 1:清空；2：不清空
    function hiddenOrShow(m2,clear) {
        var value = m2Type(m2);
        if (value == 1 || value == 2) {
            $(".fir").show();
            $(".sec").hide();
            if (clear==1) {
                $("#19").textbox("setValue","");
                $("#20").textbox("setValue","");
            }
        }else {
            $(".sec").show();
            $(".fir").hide();
            if (clear==1) {
                $("#3").textbox("setValue","");
            }
        }
        
    }

    

    var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
    var validateForm;
    function doSubmit(){
        $("#inputForm").submit();
    }

    $(function(){
        $(".fir").hide();
        $(".sec").hide();
        getData();
        if ('update' == '${action}') {
            hiddenOrShow('${res.m2}',2)
        }
        $("#M2").combobox({
            onSelect:function (data) {
                hiddenOrShow(data.value,1);
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