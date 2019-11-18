<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
    <title>轨迹查询</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript" src="${ctx}/static/model/js/lydw/gjcx/qy_index.js?v=1.1"></script>
    <link rel="stylesheet" href="${ctxStatic}/fengmap/css/style.css">
    <link rel="stylesheet" href="${ctxStatic}/model/css/rydw/style.css">
    <!-- 引入自定义 css js -->
    <link rel="stylesheet" href="${ctxStatic}/fengmap/css/team36.css">
    <script type="text/javascript" src="${ctxStatic}/fengmap/js/team36.js"></script>
    <script src="${ctxStatic}/fengmap/lib/fengmap.min.js"></script>
    <script src="${ctxStatic}/fengmap/js/layerGroup.js"></script>
    <script type="text/javascript" src="${ctxStatic}/model/js/lydw/rydw/index.js"></script>
</head>
<body >
<!-- 工具栏 -->
<div id="target_mbzp_tb" style="padding:5px;height:30px">
    <form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline pull-left">
        <div class="form-group">
            <input name="starttime" class="easyui-datetimebox"  style="height: 30px;width:120px;" data-options="editable:false,prompt: '开始时间'" />&nbsp;～&nbsp;
            <input name="endtime" class="easyui-datetimebox"  style="height: 30px;width:120px;" data-options="editable:false,prompt: '结束时间'" />
            <input name="tagid" style="width: 200px;height: 30px;" class="easyui-combogrid" data-options="prompt: '车牌号',panelWidth:350,fitColumns : true,editable:true ,idField: 'id',textField: 'num',url:'${ctx}/lydw/rydw/carlist',
								columns:[[
										   {field:'num',title:'车牌号',width:100},
										   {field:'drivers',title:'驾驶员姓名',width:60}]]" />
        </div>
    </form>
    <div class="pull-left" style="margin-left:10px">
        <button  class="btn btn-primary btn-rounded btn-outline btn-sm aa" onclick="searchShow()" ><i class="fa fa-search"></i> 查询</button>
        <button  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 重置</button>
    </div>
</div>

<iframe id="thingJsMapiframe" name="thingJsMapiframe" style="width:100%;height:100%;border-width: 0;" src="https://www.thingjs.com/s/72bd7e33a3b6c9738d6edb8c"></iframe>
<script type="text/javascript">
    function postThingJSIns(startAddFence,param){
        console.log(param);
        var iframe = document.getElementById('thingJsMapiframe');
        var message = {
            'funcName': startAddFence,// 所要调用ThingJS页面里的函数名
            'param': param
        }
        iframe.contentWindow.postMessage(message, 'https://www.thingjs.com');
    }

    window.addEventListener('message', function(e){
        if (e.origin !== "https://www.thingjs.com" || !e.data.param) {
            return;
        }
        let data = e.data;
        let funcName = data.funcName;
        let param = data.param;
        let funcEval = funcName + '(param);'
        eval(funcEval)
    });

    function setPageName(){
        postThingJSIns('getPageName','gjcx-cl');
    }

    function reset(){
        $("#searchFrom").form("clear");
    }

    var flag=true;
    function searchShow() {
        var obj=$("#searchFrom").serializeObject();
        if(obj.starttime==""||obj.endtime==""||obj.ygid==""){
            layer.msg("请输入查询条件！",{time: 2000});
            return;
        }

        if(flag){
            // 删除之前线标注

            // 清空线路点位数组

            $.ajax({
                type:'post',
                url:ctx+"/lydw/rydw/hisgjlistcar",
                data:obj,
                success: function(data){
                    var data = jQuery.parseJSON(data);
                    console.log(data);
                    if(data.path.length>0){
                        flag=false;

                        // 模拟回放

                        //
                        var staff = (data.maninfo)[0];
                        staff.id = 'track_'+ staff.ygid;
                        console.log(staff.id);
                        postThingJSIns('createStaffMarker',staff);

                        var points_staffId = [];
                        points_staffId.push(data.path);
                        points_staffId.push(staff.id);
                        postThingJSIns('trackingCar',points_staffId);
                    }else{
                        layer.msg("没有历史轨迹数据！",{time: 2000});
                    }
                }
            });
        }else{
            layer.msg("请不要频繁查询，请稍等！",{time: 2000});
        }
    }

    //查询历史轨迹

</script>
</body>
</html>
