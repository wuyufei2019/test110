var websocket;

if('WebSocket' in window) {
    websocket = new WebSocket("ws://" + document.location.host + "/HGEHS/lydwWebsocket");
}else{
    alert('当前浏览器不支持WebSocket，请升级您的浏览器！');
}
var iframe = document.getElementById('thingJsMapiframe');

websocket.onerror = function () {
    setMessageInnerHTML("WebSocket连接发生错误！");
}
websocket.onopen = function () {
    setMessageInnerHTML("WebSocket连接成功！");
}
websocket.onmessage = function (event) {
    console.log("接收到数据");
    setMessageInnerHTML(event.data);
}
websocket.onclose = function () {
    setMessageInnerHTML("WebSocket连接关闭！");
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
        case "TJ":
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
function getUserHistory(tagId, datetime1, datetime2){
    websocket.send("GUH:" + tagId + datetime1 + datetime2);
}

