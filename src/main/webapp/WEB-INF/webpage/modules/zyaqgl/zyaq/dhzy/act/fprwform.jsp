<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp" %>
<html>
<head>
    <title>动火作业管理</title>
    <meta name="decorator" content="default"/>
</head>
<body>

<form id="inputForm" action="${ctx}/zyaqgl/dhzy/act/${action}" method="post" class="form-horizontal">
    <table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
        <tbody>
        <tr>
            <td class="width-20 active"><label class="pull-right">作业证编号：</label></td>
            <td class="width-30" colspan="3"><input style="width: 100%;height: 30px;" id="M1" name="M1"
                                                    class="easyui-textbox" value="${dhzy.m1 }"
                                                    data-options="readonly:'true',validType:['length[0,50]'] "/></td>
        </tr>

        <tr>
            <td class="width-20 active"><label class="pull-right">申请单位：</label></td>
            <td class="width-30" colspan="3"><input style="width: 100%;height: 30px;" id="M2" name="M2"
                                                    class="easyui-textbox" value="${dhzy.m2 }"
                                                    data-options="readonly:'true',validType:['length[0,50]'] "/></td>
        </tr>

        <tr>
            <td class="width-20 active"><label class="pull-right">动火作业级别：</label></td>
            <td class="width-30"><input style="width: 100%;height: 30px;" name="M3" class="easyui-combobox "
                                        value="${dhzy.m3 }" data-options="readonly:'true',panelHeight:'auto', editable:false ,data: [{value:'全部',text:'全部'},
																																		        {value:'特殊动火',text:'特殊动火'},
																																		        {value:'一级动火',text:'一级动火'},
																																		        {value:'二级动火',text:'二级动火'}] "/>
            </td>
            <td class="width-20 active"><label class="pull-right">动火方式：</label></td>
            <td class="width-30"><input style="width: 100%;height: 30px;" name="M4" class="easyui-combobox "
                                        value="${dhzy.m4 }" data-options="readonly:'true',panelHeight:'auto', editable:false ,
                                        data: [{value:'电焊',text:'电焊'},
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
                                                    data-options="readonly:'true',validType:['length[0,100]'] "/></td>
        </tr>

        <tr>
            <td class="width-20 active"><label class="pull-right">动火时间起：</label></td>
            <td class="width-30"><input name="M6" class="easyui-datebox" value="${dhzy.m6 }"
                                        style="width: 100%;height: 30px;" editable="false" readonly="readonly"/></td>
            <td class="width-20 active"><label class="pull-right">动火时间止：</label></td>
            <td class="width-30"><input name="M7" class="easyui-datebox" value="${dhzy.m7 }"
                                        style="width: 100%;height: 30px;" editable="false" readonly="readonly"/></td>
        </tr>

        <tr>
            <td class="width-20 active"><label class="pull-right">动火作业负责人：</label></td>
            <td class="width-30"><input style="width: 100%;height: 30px;" id="M8" name="M8" class="easyui-textbox"
                                        value="${dhzy.m8 }" data-options="readonly:'true',validType:['length[0,25]'] "/>
            </td>
            <td class="width-20 active"><label class="pull-right">动火人：</label></td>
            <td class="width-30"><input style="width: 100%;height: 30px;" id="M9" name="M9" class="easyui-textbox"
                                        value="${dhzy.m9 }" data-options="readonly:'true',validType:['length[0,25]'] "/>
            </td>
        </tr>

        <tr>
            <td class="width-20 active"><label class="pull-right">承包商名称：</label></td>
            <td class="width-30" colspan="3"><input style="width: 100%;height: 30px;" name="M29"
                                                    class="easyui-combobox " value="${dhzy.m29 }"
                                                    data-options="readonly:'true',panelHeight:'150px', editable:false ,valueField:'text',textField:'text',url:'${ctx}/zyaqgl/xgdw/getcbsjson',
								onSelect: function(row){
									$('[name=ID3]').val(row.id);
									console.log('id3:'+$('[name=ID3]').val());
								  }"/></td>
        </tr>

        <tr>
            <td class="width-20 active"><label class="pull-right">涉及的其他特殊作业：</label></td>
            <td colspan="3">
                <div style="width: 25%;float: left;"><input type="checkbox" name="M10" value="进入受限空间"
                                                            disabled="disabled"/>进入受限空间
                </div>
                <div style="width: 25%;float: left;"><input type="checkbox" name="M10" value="高处作业"
                                                            disabled="disabled"/>高处作业
                </div>
                <div style="width: 25%;float: left;"><input type="checkbox" name="M10" value="吊装作业"
                                                            disabled="disabled"/>吊装作业
                </div>
                <div style="width: 25%;float: left;"><input type="checkbox" name="M10" value="临时用电"
                                                            disabled="disabled"/>临时用电
                </div>
                <div style="width: 25%;float: left;"><input type="checkbox" name="M10" value="动土/断路作业"
                                                            disabled="disabled"/>动土/断路作业
                </div>
                <br/>
                <div style="width: 25%;float: left;"><input type="checkbox" name="M10" value="锁定" disabled="disabled"/>锁定
                </div>
                <div style="width: 25%;float: left;"><input type="checkbox" name="M10" value="盲板抽堵"
                                                            disabled="disabled"/>盲板抽堵
                </div>
            </td>
        </tr>

        <tr>
            <td class="width-20 active"><label class="pull-right">危害辨识：</label></td>
            <td colspan="3">
                <div style="width: 25%;float: left;"><input type="checkbox" name="M11" value="易燃易爆物质"
                                                            disabled="disabled"/>易燃易爆物质
                </div>
                <div style="width: 25%;float: left;"><input type="checkbox" name="M11" value="坠落" disabled="disabled"/>坠落
                </div>
                <div style="width: 25%;float: left;"><input type="checkbox" name="M11" value="腐蚀性物质"
                                                            disabled="disabled"/>腐蚀性物质
                </div>
                <div style="width: 25%;float: left;"><input type="checkbox" name="M11" value="蒸汽" disabled="disabled"/>蒸汽
                </div>
                <br/>
                <div style="width: 25%;float: left;"><input type="checkbox" name="M11" value="高压气体/液体"
                                                            disabled="disabled"/>高压气体/液体
                </div>
                <div style="width: 25%;float: left;"><input type="checkbox" name="M11" value="有毒有害物质"
                                                            disabled="disabled"/>有毒有害物质
                </div>
                <div style="width: 25%;float: left;"><input type="checkbox" name="M11" value="高温/低温"
                                                            disabled="disabled"/>高温/低温
                </div>
                <div style="width: 25%;float: left;"><input type="checkbox" name="M11" value="触电" disabled="disabled"/>触电
                </div>
                <br/>
                <div style="width: 25%;float: left;"><input type="checkbox" name="M11" value="窒息" disabled="disabled"/>窒息
                </div>
                <div style="width: 25%;float: left;"><input type="checkbox" name="M11" value="噪音" disabled="disabled"/>噪音
                </div>
                <div style="width: 25%;float: left;"><input type="checkbox" name="M11" value="产生火花/静电"
                                                            disabled="disabled"/>产生火花/静电
                </div>
                <div style="width: 25%;float: left;"><input type="checkbox" name="M11" value="旋转设备"
                                                            disabled="disabled"/>旋转设备
                </div>
                <br/>
                <div style="width: 25%;float: left;"><input type="checkbox" name="M11" value="机械伤害"
                                                            disabled="disabled"/>机械伤害
                </div>
                <div style="width: 25%;float: left;"><input type="checkbox" name="M11" value="粉尘" disabled="disabled"/>粉尘
                </div>
                <div style="width: 25%;float: left;"><input type="checkbox" name="M11" value="不利天气"
                                                            disabled="disabled"/>不利天气
                </div>
                <div style="width: 25%;float: left;"><input type="checkbox" name="M11" value="淹没/埋没"
                                                            disabled="disabled"/>淹没/埋没
                </div>
                <br/>
                <div style="width: 25%;float: left;"><input type="checkbox" name="M11" value="辐射" disabled="disabled"/>辐射
                </div>
                <div style="width: 25%;float: left;"><input type="checkbox" name="M11" value="交叉作业"
                                                            disabled="disabled"/>交叉作业
                </div>
                <div style="width: 25%;float: left;"><input type="checkbox" name="M11" value="其他" disabled="disabled"/>其他
                </div>
            </td>
        </tr>
        </tbody>
    </table>
    <p style="margin-top: 10px;color: red;font-size: 15px;"><strong>分配</strong></p>
    <hr style="height:3px;border:none;border-top:3px double #6B8E23;margin-top: -5px;margin-bottom: -2px;"/>
    <table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
        <tr>
            <td class="width-20 active"><label class="pull-right">附件：</label></td>
            <td class="width-30" colspan="3">
                <div id="uploader_ws_m12">
                    <div id="m12fileList" class="uploader-list"></div>
                    <div id="filePicker"></div>
                </div>
            </td>
        </tr>

        <tr>
            <td class="width-20 active"><label class="pull-right">安全措施编制人：</label></td>
            <td class="width-30"><input name="M15" style="width: 100%;height: 30px;" id="M15"
                                        class="easyui-combobox"
                                        data-options="panelHeight:'100',required:'true',valueField: 'id',textField: 'text'"/>
            </td>
            <td class="width-20 active"><label class="pull-right">安全措施确认人：</label></td>
            <td class="width-30"><input name="M16" style="width: 100%;height: 30px;" id="M16"
                                        class="easyui-combobox"
                                        data-options="panelHeight:'100',required:'true',valueField: 'id',textField: 'text'"/>
            </td>
        </tr>

        <tr>
            <td class="width-20 active"><label class="pull-right">生产部门负责人：</label></td>
            <td class="width-30"><input name="M13" style="width: 100%;height: 30px;" id="M13"
                                        class="easyui-combobox"
                                        data-options="panelHeight:'100',required:'true',valueField: 'id',textField: 'text'"/>
            </td>
            <td class="width-20 active"><label class="pull-right">监护人：</label></td>
            <td class="width-30"><input name="M17" style="width: 100%;height: 30px;" id="M17"
                                        class="easyui-combobox"
                                        data-options="panelHeight:'100',required:'true',valueField: 'id',textField: 'text'"/>
            </td>
        </tr>

        <tr>
            <td class="width-20 active"><label class="pull-right">作业区域负责人：</label></td>
            <td class="width-30"><input name="M19" style="width: 100%;height: 30px;" id="M19"
                                        class="easyui-combobox"
                                        data-options="panelHeight:'100',required:'true',valueField: 'id',textField: 'text'"/>
            </td>
            <td class="width-20 active"><label class="pull-right">实施安全教育人：</label></td>
            <td class="width-30"><input name="M21" style="width: 100%;height: 30px;" id="M21"
                                        class="easyui-combobox"
                                        data-options="panelHeight:'100',required:'true',valueField: 'id',textField: 'text'"/>
            </td>
        </tr>

        <tr>
            <td class="width-20 active"><label class="pull-right">申请单位负责人：</label></td>
            <td class="width-30"><input name="M22" style="width: 100%;height: 30px;" id="M22"
                                        class="easyui-combobox"
                                        data-options="panelHeight:'100',required:'true',valueField: 'id',textField: 'text'"/>
            </td>
            <td class="width-20 active"><label class="pull-right">安全管理部门负责人：</label></td>
            <td class="width-30"><input name="M23" style="width: 100%;height: 30px;" id="M23"
                                        class="easyui-combobox"
                                        data-options="panelHeight:'100',required:'true',valueField: 'id',textField: 'text'"/>
            </td>

        </tr>


        <tr>
            <td class="width-20 active"><label class="pull-right">动火审批人：</label></td>
            <td class="width-30"><input name="M24" style="width: 100%;height: 30px;" id="M24"
                                        class="easyui-combobox"
                                        data-options="panelHeight:'100',required:'true',valueField: 'id',textField: 'text'"/>
            </td>
            <td class="width-20 active"><label class="pull-right">动火分析人：</label></td>
            <td class="width-30"><input  id="M26" style="width: 100%;height: 30px;"
                                        class="easyui-combobox"
                                        data-options="panelHeight:'100',required:'true',valueField: 'id',textField: 'text'"/>
            </td>


        </tr>
        <tr>
            <td class="width-20 active"><label class="pull-right">验收人：</label></td>
            <td class="width-30"><input name="M25" style="width: 100%;height: 30px;" id="M25"
                                        class="easyui-combobox"
                                        data-options="panelHeight:'100',required:'true',valueField: 'id',textField: 'text'"/>
            </td>
        </tr>
      <%--  <tfoot>
        <tr>
            <td align="center" colspan="4">
                <a class="btn btn-primary btn-sm" data-toggle="tooltip" data-placement="left"
                   onclick="sumbit()" title="提交"><i class="fa fa-plus"></i>提交</a></td>
        </tr>
        </tfoot>--%>

        <c:if test="${not empty dhzy.ID}">
            <input type="hidden" name="ID" value="${dhzy.ID}"/>
            <input type="hidden" name="ID2" value="${dhzy.ID2}"/>
            <input type="hidden" name="ID3" value="${dhzy.ID3}">
            <input type="hidden" name="S1"
                   value="<fmt:formatDate value="${dhzy.s1}" pattern="yyyy-MM-dd HH:mm:ss"  />"/>
            <input type="hidden" name="S3" value="${dhzy.s3}"/>
            <input type="hidden" name="M10" value="${dhzy.m10}"/>
            <input type="hidden" name="processInstanceId" value="${dhzy.processInstanceId}"/>
            <input type="hidden" name="processDefinitionId" value="${dhzy.processDefinitionId}"/>
            <input type="hidden" name="M11" value="${dhzy.m11}"/>
            <input type="hidden" name="applyer" value="${dhzy.applyer}"/>
        </c:if>
        <input type="hidden" name="taskId" value="${taskId}"/>
        <input type="hidden" name="workflow_assigner_1" id="workflow_assigner_1"/>
        <input type="hidden" name="workflow_assigner_2" id="workflow_assigner_2"/>
        <input type="hidden" name="workflow_assigner_3" id="workflow_assigner_3"/>
        <input type="hidden" name="workflow_assigner_4" id="workflow_assigner_4"/>
        <input type="hidden" name="workflow_assigner_5" id="workflow_assigner_5"/>
        <input type="hidden" name="workflow_assigner_6" id="workflow_assigner_6"/>
        <input type="hidden" name="workflow_assigner_7" id="workflow_assigner_7"/>
        <input type="hidden" name="workflow_assigner_8" id="workflow_assigner_8"/>
        <input type="hidden" name="workflow_assigner_9" id="workflow_assigner_9"/>
        <input type="hidden" name="workflow_assigner_10" id="workflow_assigner_10"/>
        <input type="hidden" name="workflow_assigner_11" id="workflow_assigner_11"/>

    </table>

    <tbody>
</form>
<script type="text/javascript">
    var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
    var $ = jQuery,
        $list2 = $('#m12fileList'); //文件上传
    if ('${action}' == 'fprw') {
        var fjUrl = '${dhzy.m12}';
        if (fjUrl != null && fjUrl != '') {
            arry = fjUrl.split(",");
            for (var i = 0; i < arry.length; i++) {
                var arry2 = arry[i].split("||");
                var id = "ws_fj_" + i;
                var $li = $(
                    "<div id=\"" + id + "\" style=\"margin-bottom: 10px;\">" +
                    "<a style=\"color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;\" onclick=\"window.open('" + arry2[0] + "')\">" + arry2[1] + "</a>" +
                    "</div>"
                );
                $list2.append($li);
                var $input = $('<input id="input_' + id + '" type="hidden" name="M12" value="' + arry[i] + '"/>');
                $('#uploader_ws_m12').append($input);
            }
        }
    }


    function doSubmit() {
        $("#inputForm").submit();
    }

    $(function () {
        $.post('${ctx}/system/compuser/userloginnamejson', function (data) {
            data = eval(data);
            $("#M15").combobox("loadData", data);
            $("#M16").combobox("loadData", data);
            $("#M17").combobox("loadData", data);
            $("#M13").combobox("loadData", data);
            $("#M19").combobox("loadData", data);
            $("#M21").combobox("loadData", data);
            $("#M22").combobox("loadData", data);
            $("#M23").combobox("loadData", data);
            $("#M24").combobox("loadData", data);
            $("#M25").combobox("loadData", data);
            $("#M26").combobox("loadData", data);
        });
        $("#M15").combobox({
            onSelect: function (res) {
                $("#workflow_assigner_1").val(res.text);
            }
        });
        $("#M16").combobox({
            onSelect: function (res) {
                $("#workflow_assigner_2").val(res.text);
            }
        });
        $("#M13").combobox({
            onSelect: function (res) {
                $("#workflow_assigner_3").val(res.text);
            }
        });
        $("#M17").combobox({
            onSelect: function (res) {
                $("#workflow_assigner_4").val(res.text);
            }
        });
        $("#M19").combobox({
            onSelect: function (res) {
                $("#workflow_assigner_5").val(res.text);
            }
        });
        $("#M21").combobox({
            onSelect: function (res) {
                $("#workflow_assigner_6").val(res.text);
            }
        });
        $("#M22").combobox({
            onSelect: function (res) {
                $("#workflow_assigner_7").val(res.text);
            }
        });
        $("#M23").combobox({
            onSelect: function (res) {
                $("#workflow_assigner_8").val(res.text);
            }
        });
        $("#M24").combobox({
            onSelect: function (res) {
                $("#workflow_assigner_9").val(res.text);
            }
        });
        $("#M26").combobox({
            onSelect: function (res) {
                $("#workflow_assigner_10").val(res.text);
            }
        });
        $("#M25").combobox({
            onSelect: function (res) {
                $("#workflow_assigner_11").val(res.text);
            }
        });
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
    if (action == "fprw") {
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

</script>
</body>
</html>