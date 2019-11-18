<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>电子围栏管理</title>
	<meta name="decorator" content="default"/>
    <script type="text/javascript" src="${ctx}/static/model/js/lydw/dzwl/index2.js?v=1"></script>
    <script src="${ctxStatic}/fengmap/lib/fengmap.min.js"></script>
    <link rel="stylesheet" href="${ctxStatic}/fengmap/css/style.css">
    <script type="text/javascript" src="${ctx}/static/model/js/lydw/rydw/lydwwebsocket.js"></script>
    <script type="text/javascript" src="${ctx}/static/model/js/lydw/rydw/pushposition.js"></script>
</head>
<body >
<!-- 工具栏 -->
<div class="easyui-layout" style="height: 100%">

    <div data-options="region:'west',split:true" style="width: 30%">
            <div id="lydw_dzwl_tb" style="padding:5px;height:auto;"  >
                <form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">
                <div class="form-group">
                <input type="text" name="lydw_dzwl_cx_name" class="easyui-textbox"  style="height: 30px;width: 120px;" data-options="prompt: '线路名称'"/>
                <input type="text" name="lydw_dzwl_cx_floor" class="easyui-combobox" style="height: 30px;width: 100px" data-options="prompt: '楼层',editable:false ,panelHeight:'auto', data: [
                                                      {value:'1',text:'1层'},
                                                      {value:'2',text:'2层'},
                                                      {value:'3',text:'3层'},
                                                      {value:'4',text:'4层'},
                                                      {value:'5',text:'5层'}]" />
                <span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</span>
                <span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span>
                <span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="searchDzwl()" ><i class="fa fa-map-o"></i> 总览</span>
                </div>
                </form>
                <div class="row">
                <div class="col-sm-12">
                    <div class="pull-left">

                        <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add()" title="添加"><i class="fa fa-plus"></i> 添加</button>
                        <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd()" title="修改"><i class="fa fa-file-text-o"></i> 修改</button>
                        <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除"><i class="fa fa-trash-o"></i> 删除</button>

                        <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
                    </div>
                </div>
                </div>
            </div>
            <table id="lydw_dzwl_dg"></table>
    </div>

    <div id="cas" data-options="region:'center',split:true" style="width: 68%">
        <iframe id="thingJsMapiframe" name="thingJsMapiframe" style="width:100%;height:100%;border-width: 0;" src="https://www.thingjs.com/s/72bd7e33a3b6c9738d6edb8c"></iframe>
    </div>
</div>



<script>
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
        postThingJSIns('getPageName','dzwl');
    }

    function print(){
        console.log('444')
    }

    function loadFences(){
        // 获得围栏信息数组
        $.ajax({
            type:'post',
            url:ctx+"/lydw/dzwl/dzwllist",
            success: function(data){
                var dzwl = jQuery.parseJSON(data);
                for(var i = 0; i < dzwl.length; i++){
                    console.info(dzwl[i].points);
                    postThingJSIns('loadFence',jQuery.parseJSON(dzwl[i].points));
                    //loadFence(jQuery.parseJSON(dzwl[i].points));
                }
            }
        });

    }

    var startAddFence = 'startAddFence';
    function createFence(id){
        postThingJSIns('startAddFence',id);
    }

    var points;
    var fenceId = '';
    //    电子围栏
    function receiveFenceInfo(param){
        console.log(param);
        points = param;
        fenceId = points.thingid;
        console.log(fenceId);
    }

    function doSaveTip() {
        layer.confirm('保存该电子围栏？', {
            btn: ['保存','取消'],
            cancel: function(index, layero){
                    postThingJSIns('deleteFence',fenceId);
                }
            }, function(){
            //
            var fences = points.fences;
            var mappoint =[];
            for (let i = 0, len = fences.length; i < len; i++) {
                mappoint.push({x : fences[i][0],y : fences[i][1],z : fences[i][2]})
            }
            //
            var parent = points.parent;
            //在空地上建围栏，没有楼层信息
            var data = {};
            if(parent && parent.length<2){
                data ={ID:points.thingid,mappoint: JSON.stringify(mappoint).toString(),floor:1,floorname:'',building:'',buildingname:'',points:JSON.stringify(points).toString()};
            }else{
                data = {ID:points.thingid,mappoint: JSON.stringify(mappoint).toString(),floor:parent[0].id,floorname:parent[0].name,building:parent[1].id,buildingname:parent[0].name,points:JSON.stringify(points).toString()}
            }

            $.ajax({
                type:'post',
                url:ctx+"/lydw/dzwl/update2",
                data:data,
                success: function(data){
                    layer.msg('保存成功!', {icon: 1});
                }
            });

            }, function(){
                postThingJSIns('deleteFence',fenceId);
            }
        );
    }
</script>
</body>
</html>