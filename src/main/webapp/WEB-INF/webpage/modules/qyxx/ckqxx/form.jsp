<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp" %>
<html>
<head>
    <title>仓库区管理</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript" src="${ctxStatic}/common/GlobalProvinces_main.js"></script>
    <script type="text/javascript" src="${ctxStatic}/common/GlobalProvinces_extend.js"></script>
    <script type="text/javascript" src="${ctxStatic}/common/initLocation.js"></script>
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=OTVG2uzqudiovwoHlFIs0P8Z3T7ID4K2"></script>
    <script type="text/javascript" src="${ctx}/static/common/initmap.js?v=1.1"></script>
    <script type="text/javascript">
        var usertype =${usertype};

        var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
        var validateForm;

        function doSubmit() {
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

    </script>
</head>
<body>

<form id="inputForm" action="${ctx}/bis/ckqxx/${action}" method="post" class="form-horizontal">
    <table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
        <tbody>
        <c:if test="${usertype != '1' and action eq 'create'}">
            <tr>
                <td class="width-20 active"><label class="pull-right">企业名称：</label></td>
                <td class="width-30" colspan="3">
                    <input value="${ckqxx.ID1 }" id="_qyid" name="ID1" style="width: 100%;height: 30px;"
                           class="easyui-combobox"
                           data-options="required:'true',valueField: 'id',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson'"/>
                </td>
            </tr>
        </c:if>
        <c:if test="${usertype != '1' and action eq 'update'}">
            <tr>
                <td class="width-20 active"><label class="pull-right">企业名称：</label></td>
                <td class="width-30" colspan="3">
                    <input value="${ckqxx.ID1 }" id="_qyid" name="ID1" style="width: 100%;height: 30px;"
                           class="easyui-combobox"
                           data-options="editable:false, disabled:true, valueField: 'id',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson'"/>
                </td>
            </tr>
        </c:if>

        <tr>
            <td class="width-20 active"><label class="pull-right">仓库区编号：</label></td>
            <td class="width-30" colspan="3">
                <input name="M1" class="easyui-textbox" value="${ckqxx.m1 }" style="width: 100%;height: 30px;"
                       data-options="validType:'length[0,25]'"/>
            </td>
        </tr>

        <tr>
            <td class="width-20 active"><label class="pull-right">仓库区名称：</label></td>
            <td class="width-30" colspan="3">
                <input name="M2" class="easyui-textbox" value="${ckqxx.m2 }" style="width: 100%;height: 30px;"
                       data-options="required:'true',validType:'length[0,25]'"/>
            </td>
        </tr>

        <tr>
            <td class="width-20 active"><label class="pull-right">仓库区面积(㎡)：</label></td>
            <td class="width-30"><input class="easyui-numberbox" name="M3" value="${ckqxx.m3 }"
                                        style="width: 100%;height: 30px;" data-options="min:0,precision:2"/></td>

            <td class="width-20 active"><label class="pull-right">库房个数：</label></td>
            <td class="width-30"><input class="easyui-numberbox" name="M4" value="${ckqxx.m4 }"
                                        style="width: 100%;height: 30px;" data-options="min:0"/></td>
        </tr>

        <tr>
            <td class="width-20 active"><label class="pull-right">仓库区位置：</label></td>
            <td colspan="3" style="height:30px;line-height:30px;">
                <label>经度：</label>
                <input id="bis_map_c_x" name="M5" value="${ckqxx.m5 }" class="easyui-textbox" readonly="readonly" data-options="" style="width:150px;height:30px;"/>
                <label>纬度：</label>
                <input id="bis_map_c_y" name="M6" value="${ckqxx.m6 }" class="easyui-textbox" readonly="readonly" data-options="" style="width:150px;height:30px;"/>
                <a class="btn btn-primary" onclick="showMapXY( )" style="width:60px;">定位</a></td>
        </tr>

        <tr>
            <td class="width-20 active"><label class="pull-right">备注：</label></td>
            <td class="width-30" colspan="3">
                <input name="M7" class="easyui-textbox" value="${ckqxx.m7 }" style="width: 100%;height: 80px;"
                       data-options="multiline:true,validType:'length[0,250]'"/>
            </td>
        </tr>
        <c:if test="${not empty ckqxx.ID}">
            <input type="hidden" name="ID" value="${ckqxx.ID}"/>
            <input type="hidden" name="ID1" value="${ckqxx.ID1}"/>
            <input type="hidden" name="S1"
                   value="<fmt:formatDate value="${ckqxx.s1}" pattern="yyyy-MM-dd HH:mm:ss"  />"/>
            <input type="hidden" name="S3" value="${ckqxx.s3}"/>
        </c:if>
        </tbody>
    </table>
</form>

<div id="ckqxx_dlg" style="width:100%;height:100%; text-align:center;display: none;" >
    <div><span style="color: red;margin: 0 10px;">点击地图标注企业位置!</span>
        <input class="easyui-searchbox" style="width:300px" data-options="prompt:'请输入搜索条件',searcher:function search(value,name){ addmap(value); }" />
    </div>
    <div id="bis_ckqxx_dlg_map" style="width:100%;height: 285px;"></div>
</div>

<script type="text/javascript">
    var locx ='${locx}';
    var locy ='${locy}';

    function addmap(searchcon){
        initMap("bis_ckqxx_dlg_map",locx,locy);
        map.setDefaultCursor("crosshair");//设置地图默认的鼠标指针样式
        var local = new BMap.LocalSearch(map, {
            renderOptions:{map: map}
        });
        local.search(searchcon);

        var marker = new BMap.Marker(point); //创建marker对象
        map.addOverlay(marker); //在地图中添加marker

        map.addEventListener("click", function(e){
            locx=e.point.lng;
            locy=e.point.lat;
            var now_point =  new BMap.Point(e.point.lng, e.point.lat );
            marker.setPosition(now_point);//设置覆盖物位置
            marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
        });

    }

    //弹出地图界面
    function showMapXY( ){
        layer.open({
            type: 1,
            area: ['500px', '400px'],
            title: '标注坐标',
            maxmin: true,
            shift: 1,
            shade :0,
            content: $('#ckqxx_dlg'),
            btn: ['确定', '关闭'],
            success: function(layero, index){
                addmap("");
            },
            yes: function(index, layero){
                $("#bis_map_c_x").textbox("setValue", locx);//经度
                $("#bis_map_c_y").textbox("setValue", locy);//纬度
                layer.close(index);
            },
            cancel: function(index){
            }
        });
    }
</script>
</body>
</html>