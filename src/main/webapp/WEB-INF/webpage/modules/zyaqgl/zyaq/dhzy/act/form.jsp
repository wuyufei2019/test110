<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp" %>
<html>
<head>
    <title>动火作业管理</title>
    <meta name="decorator" content="default"/>
    <script src="${ctxStatic}/common/webUpload.js"></script>
</head>
<body>

<form id="inputForm" action="${ctx}/zyaqgl/dhzy/act/${action}" method="post" class="form-horizontal">
    <table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
        <tbody>
        <tr>
            <td class="width-20 active"><label class="pull-right">申请单位：</label></td>
            <td class="width-30" colspan="3"><input style="width: 100%;height: 30px;" id="M2" name="M2"
                                                    class="easyui-textbox" value="${dhzy.m2 }" required="true"
                                                    data-options="validType:['length[0,50]'] "/></td>
        </tr>

        <tr>
            <td class="width-20 active"><label class="pull-right">动火时间起：</label></td>
            <td class="width-30"><input id="M6" name="M6" class="easyui-datetimebox" value="${dhzy.m6 }"
                                        style="width: 100%;height: 30px;"
                                        data-options="required:'true',editable:false,showSeconds:false"/></td>
            <td class="width-20 active"><label class="pull-right">动火时间止：</label></td>
            <td class="width-30"><input id="M7" name="M7" class="easyui-datetimebox" value="${dhzy.m7 }"
                                        style="width: 100%;height: 30px;"
                                        data-options="required:'true',editable:false,showSeconds:false"/></td>
        </tr>

        <tr>
            <td class="width-20 active"><label class="pull-right">动火作业级别：</label></td>
            <td class="width-30"><input style="width: 100%;height: 30px;" name="M3" class="easyui-combobox "
                                        value="${dhzy.m3 }" data-options="panelHeight:'auto', editable:false ,data: [{value:'全部',text:'全部'},
																																		        {value:'特殊动火',text:'特殊动火'},
																																		        {value:'一级动火',text:'一级动火'},
																																		        {value:'二级动火',text:'二级动火'}] "/>
            </td>
            <td class="width-20 active"><label class="pull-right">动火方式：</label></td>
            <td class="width-30"><input style="width: 100%;height: 30px;" name="M4" class="easyui-combobox "
                                        value="${dhzy.m4 }" data-options="panelHeight:'auto', editable:false ,data: [{value:'电焊',text:'电焊'},
																																		        {value:'气焊(割)',text:'气焊(割)'},
																																		        {value:'喷灯',text:'喷灯'},
																																		        {value:'电钻',text:'电钻'},
																																		        {value:'砂轮',text:'砂轮'}] "/>
            </td>
        </tr>

        <tr>
            <td class="width-20 active"><label class="pull-right">动火地点：</label></td>
            <td class="width-30" colspan="3"><input style="width: 100%;height: 30px;" id="M5" name="M5"
                                                    class="easyui-textbox" value="${dhzy.m5 }"
                                                    data-options="validType:['length[0,100]'] "/></td>
        </tr>

        <tr>
            <td class="width-20 active"><label class="pull-right">动火作业负责人：</label></td>
            <td class="width-30"><input style="width: 100%;height: 30px;" id="M8" name="M8" class="easyui-textbox"
                                        value="${dhzy.m8 }" data-options="validType:['length[0,25]'] "/></td>
            <td class="width-20 active"><label class="pull-right">动火人：</label></td>
            <td class="width-30"><input style="width: 100%;height: 30px;" id="M9" name="M9" class="easyui-textbox"
                                        value="${dhzy.m9 }" data-options="validType:['length[0,25]'] "/></td>
        </tr>

        <tr>
            <td class="width-20 active"><label class="pull-right">承包商名称：</label></td>
            <td class="width-30" colspan="3"><input style="width: 100%;height: 30px;" name="M29"
                                                    class="easyui-combobox " value="${dhzy.m29 }"
                                                    data-options="panelHeight:'150px', editable:false ,valueField:'text',textField:'text',url:'${ctx}/zyaqgl/xgdw/getcbsjson',
								onSelect: function(row){
									$('[name=ID3]').val(row.id);
								  }"/></td>
        </tr>

        <tr>
            <td class="width-20 active"><label class="pull-right">涉及的其他特殊作业：</label></td>
            <td colspan="3">
                <div style="width: 25%;float: left;"><input type="checkbox" name="M10" value="进入受限空间"/>进入受限空间</div>
                <div style="width: 25%;float: left;"><input type="checkbox" name="M10" value="高处作业"/>高处作业</div>
                <div style="width: 25%;float: left;"><input type="checkbox" name="M10" value="吊装作业"/>吊装作业</div>
                <div style="width: 25%;float: left;"><input type="checkbox" name="M10" value="临时用电"/>临时用电</div>
                <div style="width: 25%;float: left;"><input type="checkbox" name="M10" value="动土/断路作业"/>动土/断路作业</div>
                <br/>
                <div style="width: 25%;float: left;"><input type="checkbox" name="M10" value="锁定"/>锁定</div>
                <div style="width: 25%;float: left;"><input type="checkbox" name="M10" value="盲板抽堵"/>盲板抽堵</div>
            </td>
        </tr>

        <tr>
            <td class="width-20 active"><label class="pull-right">危害辨识：</label></td>
            <td colspan="3">
                <div style="width: 25%;float: left;"><input type="checkbox" name="M11" value="易燃易爆物质"/>易燃易爆物质</div>
                <div style="width: 25%;float: left;"><input type="checkbox" name="M11" value="坠落"/>坠落</div>
                <div style="width: 25%;float: left;"><input type="checkbox" name="M11" value="腐蚀性物质"/>腐蚀性物质</div>
                <div style="width: 25%;float: left;"><input type="checkbox" name="M11" value="蒸汽"/>蒸汽</div>
                <br/>
                <div style="width: 25%;float: left;"><input type="checkbox" name="M11" value="高压气体/液体"/>高压气体/液体</div>
                <div style="width: 25%;float: left;"><input type="checkbox" name="M11" value="有毒有害物质"/>有毒有害物质</div>
                <div style="width: 25%;float: left;"><input type="checkbox" name="M11" value="高温/低温"/>高温/低温</div>
                <div style="width: 25%;float: left;"><input type="checkbox" name="M11" value="触电"/>触电</div>
                <br/>
                <div style="width: 25%;float: left;"><input type="checkbox" name="M11" value="窒息"/>窒息</div>
                <div style="width: 25%;float: left;"><input type="checkbox" name="M11" value="噪音"/>噪音</div>
                <div style="width: 25%;float: left;"><input type="checkbox" name="M11" value="产生火花/静电"/>产生火花/静电</div>
                <div style="width: 25%;float: left;"><input type="checkbox" name="M11" value="旋转设备"/>旋转设备</div>
                <br/>
                <div style="width: 25%;float: left;"><input type="checkbox" name="M11" value="机械伤害"/>机械伤害</div>
                <div style="width: 25%;float: left;"><input type="checkbox" name="M11" value="粉尘"/>粉尘</div>
                <div style="width: 25%;float: left;"><input type="checkbox" name="M11" value="不利天气"/>不利天气</div>
                <div style="width: 25%;float: left;"><input type="checkbox" name="M11" value="淹没/埋没"/>淹没/埋没</div>
                <br/>
                <div style="width: 25%;float: left;"><input type="checkbox" name="M11" value="辐射"/>辐射</div>
                <div style="width: 25%;float: left;"><input type="checkbox" name="M11" value="交叉作业"/>交叉作业</div>
                <div style="width: 25%;float: left;"><input type="checkbox" name="M11" value="其他"/>其他</div>
            </td>
        </tr>

        <tr>
            <td class="width-15 active"><label class="pull-right">附件：</label></td>
            <td class="width-35" colspan="3">
                <div id="m12_uploader" style="margin-left:10px"></div>
            </td>
        </tr>

        <input type="hidden" name="ID3" value="${dhzy.id3}">
        <c:if test="${not empty dhzy.id}">
        <input type="hidden" name="ID" value="${dhzy.id}"/>
        <input type="hidden" name="ID1" value="${dhzy.id1}"/>
        <input type="hidden" name="ID2" value="${dhzy.id2}"/>
        <input type="hidden" name="S1"
               value="<fmt:formatDate value="${dhzy.s1}" pattern="yyyy-MM-dd HH:mm:ss"  />"/>
        <input type="hidden" name="S3" value="${dhzy.s3}"/>
        <input type="hidden" name="zt" value="${dhzy.zt}"/>
        </c:if>
    </table>

    <tbody>
</form>
<script type="text/javascript">
    var usertype =${usertype};
    var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
    var validateForm;
    $("#m12_uploader").WebFileUpload({hiddenInputId:'m12',existurl:'${entity.m12}'});


    function doSubmit() {
        $("#inputForm").serializeObject();
        $("#inputForm").submit();
    }

    $(function () {
        var flag = true;
        $('#inputForm').form({
            onSubmit: function () {
                var isValid = $(this).form('validate');
                if (isValid && flag) {
                    flag = false;
                    $.jBox.tip("正在提交，请稍等...", 'loading', {opacity: 0});
                    return true;
                }
                return false;	// 返回false终止表单提交
            },
            success: function (data) {
                $.jBox.closeTip();
                if (data == 'success')
                    parent.layer.open({icon: 1, title: '提示', offset: 'rb', content: '操作成功！', shade: 0, time: 2000});
                else
                    parent.layer.open({icon: 2, title: '提示', offset: 'rb', content: '操作失败！', shade: 0, time: 2000});
                parent.dg.datagrid('reload');
                parent.layer.close(index);//关闭对话框。
            }
        });

    });

    var action = "${action}";
    if (action == "update") {
        //特殊作业
        var tszy = "${dhzy.m10}";
        var tszyArr = tszy.split(",");
        for (var i = 0; i < tszyArr.length; i++) {
            $("input[name='M10']:checkbox[value='" + tszyArr[i] + "']").attr('checked', 'true');
        }

        //危害辨识
        var whbs = "${dhzy.m11}";
        var whbsArr = whbs.split(",");
        for (var i = 0; i < whbsArr.length; i++) {
            $("input[name='M11']:checkbox[value='" + whbsArr[i] + "']").attr('checked', 'true');
        }
    }

    $(function () {
        $("input[name='M10']:checkbox").css("width", "18px");
        $("input[name='M10']:checkbox").css("height", "18px");
        $("input[name='M11']:checkbox").css("width", "18px");
        $("input[name='M11']:checkbox").css("height", "18px");
    });

    $("#M6").datetimebox({
        onChange: function (date) {
            var ksdate = $("#M6").datetimebox("getValue");
            var jsdate = $("#M7").datetimebox("getValue");
            if (jsdate != '') {
                if (ksdate > jsdate) {
                    $("#M6").datetimebox("setValue", "");
                    layer.msg("不能大于作业截止时间！", {
                        time: 2000
                    });
                }
            }
        }
    });

    $("#M7").datetimebox({
        onChange: function (date) {
            var ksdate = $("#M6").datetimebox("getValue");
            var jsdate = $("#M7").datetimebox("getValue");
            if (ksdate != '') {
                if (jsdate < ksdate) {
                    $("#M7").datetimebox("setValue", "");
                    layer.msg("不能小于作业开始时间！", {
                        time: 2000
                    });
                }
            }
        }
    });
</script>
</body>
</html>