<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
    <!--
        Author : cap
        Date : 19/10/23
     -->
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>人员定位-ThingJS</title>
    <script type="application/javascript" src="${ctx}/static/jquery/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="${ctxStatic}/jqueryToast/js/toast.script.js"></script>
    <link href="${ctxStatic}/jqueryToast/css/toast.style.css" rel="stylesheet">
    <style>

    </style>
    <script>
        <%--function aaa(){--%>
            <%--thingJsMapiframe.document.getElementsByTagName("link")[0].href="${ctx}/static/thingJS/css/style.css";--%>
        <%--}--%>
    </script>
</head>
<body style="margin: 0;">
<div style="width:100%;height:100%;">
    <iframe id="thingJsMapiframe" name="thingJsMapiframe" style="width:100%;height:100%;border-width: 0;" src="https://www.thingjs.com/s/72bd7e33a3b6c9738d6edb8c"></iframe>
    <div id="msg__s" style="position:fixed;bottom:0;right:0;z-index: 99999999999;width: 320px;"></div>
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
        postThingJSIns('getPageName','index');
    }


function getConnect() {
    var websocket;

    if('WebSocket' in window) {
        websocket = new WebSocket("ws://" + document.location.host + "${ctx}/lydwWebsocket");
    }else{
        alert('当前浏览器不支持WebSocket，请升级您的浏览器！');
    }
    var iframe = document.getElementById('thingJsMapiframe');

    websocket.onerror = function () {
        setMessageInnerHTML("WebSocket连接发生错误");
    }
    websocket.onopen = function () {
        setMessageInnerHTML("WebSocket连接成功");
    }
    websocket.onmessage = function (event) {
        console.log("接收到数据");
        setMessageInnerHTML(event.data);
    }
    websocket.onclose = function () {
        setMessageInnerHTML("WebSocket连接关闭");
    }
}

    function setMessageInnerHTML(innerHTML) {
        if(innerHTML == 'WebSocket连接成功' || innerHTML == 'WebSocket连接发生错误' || innerHTML == 'WebSocket连接关闭'){
            console.log(innerHTML);
        }else{
            dealData(innerHTML);
        }
    }

    function send() {
        var message = document.getElementById('text').value;
        websocket.send(message);
    }

    function dealData(data) {
        var head = data.substring(0, 2);
        var item = data.substring(3, data.length);
        var info = JSON.parse(item);
        switch (head) {
            case "DW":
                console.log('推送单条数据！');
                useMapMethod('updateInfo', info);
                break;
            case "BJ":
                console.log('收到报警信息！');
                toastInfo(info.msg);
                break;
            default:
                console.log(data);
        }
    }

    /*调用地图端方法*/
    function useMapMethod(funcName , param){
        iframe = document.getElementById('thingJsMapiframe');
        var message = {
            'funcName': funcName,
            'param':  param
        }
        iframe.contentWindow.postMessage(message , "https://www.thingjs.com");
    }

    /*交互事件监听*/
    window.addEventListener('message', function (e) {
        if(e.origin !== "https://www.thingjs.com" || !e.data.param){
            return;
        }
        var data = e.data;
        var funcName = data.funcName;
        var parm = data.param;
        var funcEval = funcName + parm;
        eval(funcEval);
    })

    /*本地事件*/
        /*显示报警Toast*/
    function toastInfo(msg) {
        $.Toast($("#msg__s"),"报警信息", msg, "error", {
            stack: true,
            has_icon:true,
            has_close_btn:true,
            fullscreen:false,
            timeout:0,
            sticky:false,
            has_progress:true,
            rtl:false,
        });
    }
</script>
</body>
</html>
