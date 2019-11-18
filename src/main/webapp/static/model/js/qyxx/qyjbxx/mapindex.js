function reloadMap(v) {
    addMarker(v);
    createPolygon();
}

//设置搜索数据
function setSearchData(data) {
    window.searchClass = new SearchClass();
    searchClass.setData(data);
}

//分别获取红橙黄蓝风险点数据 加载海量点
function getQyfbJson() {
    map.clearOverlays();
    createPolygon();
    BIS_B = getJson(4);
    BIS_Y = getJson(3);
    BIS_O = getJson(2);
    BIS_R = getJson(1);
}

// 加载热力图
function loadHeatMap(data, maxvalue) {
    if (data != undefined) {
        if (!isSupportCanvas()) {
            alert('热力图目前只支持有canvas支持的浏览器,您所使用的浏览器不能使用热力图功能~');
            return;
        }
        var points = eval(data);
        heatmapOverlay = new BMapLib.HeatmapOverlay({
            "radius": 30
        });
        map.addOverlay(heatmapOverlay);
        heatmapOverlay.setDataSet({
            data: points,
            max: maxvalue
        });
    }

}

// 判断浏览区是否支持canvas
function isSupportCanvas() {
    var elem = document.createElement('canvas');
    return !!(elem.getContext && elem.getContext('2d'));
}

//根据风险等级返回企业信息，并用海量点创建marker点
function getJson(type, qyname) {
    var d;
    $.ajax({
        type: "POST",
        url: ctx + "/bis/qyjbxx/maplist?type=" + type + (qyname ? "&qyname=" + qyname : ""),
        async: false,
        dataType: 'json',
        success: function (data) {
            d = data.data;
            if (d) {
                pointCollection(d, type);//向地图中添加marker
            }
        }
    });
    return d;
}

//企业搜索
function getQyfbJsonByQyname() {
    //layer.msg("若企业未定位则不予显示");
    var keyword = $("#keyword").combobox("getValue");
    map.clearOverlays();
    createPolygon();
    getJson(4, keyword);
    getJson(3, keyword);
    getJson(2, keyword);
    getJson(1, keyword);
}

//创建多边形图
function createPolygon() {
    if (mappoint != "") {
        var arry = mappoint.split("||");//lat，lnt
        var maparry = [];//坐标数组
        var narry;
        var m;
        for (var i = 0; i < arry.length; i++) {
            narry = arry[i].split(",");
            m = new BMap.Point(narry[0], narry[1]);
            maparry.push(m);
        }
        polygon = new BMap.Polygon(maparry, {
            strokeColor: "red",
            strokeWeight: 2,
            strokeOpacity: 0.5,
            fillOpacity: 0.3
        }); //创建多边形
        map.addOverlay(polygon); //增加多边形
        map.setViewport(polygon.getPath());
    }
}

// 创建多边形图
function createPolygons(wglist, colorlist) {
    var len = wglist.length;
    for (var i = 0; i < len; i++) {
        var mappoint = wglist[i].mappoint;
        if (mappoint != null && mappoint != "" && mappoint != "undefined") {
            var arry = mappoint.split("||");// lat，lnt
            var maparry = [];// 坐标数组
            for (var j = 0; j < arry.length; j++) {
                var narry = arry[j].split(",");
                var m = new BMap.Point(narry[0], narry[1]);
                maparry.push(m);
            }
            var polygon = new BMap.Polygon(maparry, {
                strokeColor: colorlist[i],
                strokeWeight: 1.5,
                strokeOpacity: 0.5,
                fillOpacity: 0.5
            }); // 创建多边形
            polygon.setFillColor(colorlist[i]);
            map.addOverlay(polygon); // 增加多边形
        }
    }
}

// 将hex表示方式转换为rgb表示方式(这里返回rgb数组模式)
function colorRgb(sColor) {
    var reg = /^#([0-9a-fA-f]{3}|[0-9a-fA-f]{6})$/;
    var sColor = sColor.toLowerCase();
    if (sColor && reg.test(sColor)) {
        if (sColor.length === 4) {
            var sColorNew = "#";
            for (var i = 1; i < 4; i += 1) {
                sColorNew += sColor.slice(i, i + 1).concat(sColor.slice(i, i + 1));
            }
            sColor = sColorNew;
        }
        // 处理六位的颜色值
        var sColorChange = [];
        for (var i = 1; i < 7; i += 2) {
            sColorChange.push(parseInt("0x" + sColor.slice(i, i + 2)));
        }
        return sColorChange;
    } else {
        return sColor;
    }
};

// 创建海量点
function pointCollection(data, type) {
    var color;
    if (type == 1) color = "#FF5151";
    if (type == 2) color = "#FFA042";
    if (type == 3) color = "#F9F900";
    if (type == 4) color = "#2894FF";
    if (document.createElement('canvas').getContext) {  // 判断当前浏览器是否支持绘制海量点
        var points = [];  // 添加海量点数据
        for (var i = 0; i < data.length; i++) {
            var point = new BMap.Point(data[i].pointx, data[i].pointy);
            points.push(point);
            /*    var opts = {
                    position: point,    // 指定文本标注所在的地理位置
                    offset: new BMap.Size(30, -30)    //设置文本偏移量
                }
                var label = new BMap.Label("11", opts);  // 创建文本标注对象
                label.setStyle({
                    color: "red",
                    fontSize: "12px",
                    height: "20px",
                    lineHeight: "20px",
                    fontFamily: "微软雅黑"
                });
                map.addOverlay(label);*/
        }
        var options = {
            size: BMAP_POINT_SIZE_NORMAL,
            shape: BMAP_POINT_SHAPE_CIRCLE,
            color: color,
        }

        var pointCollection = new BMap.PointCollection(points, options);  // 初始化PointCollection
        addListener(pointCollection);
        map.addOverlay(pointCollection);  // 添加Overlay
    } else {
        alert('当前浏览器不支持海量点查看，请在chrome、safari、IE8+以上浏览器查看地图');
    }
}

//海量点点击事件监听
function addListener(pointCollection) {
    pointCollection.addEventListener('click', function (e) {
        var d = getJsonByPoint(e);
        var iw = createInfoWindow(d);
        map.openInfoWindow(iw, e.point);
    });
}

//点击marker点 根据经纬度确定企业返回信息
function getJsonByPoint(e) {
    var d;
    $.ajax({
        type: "POST",
        url: ctx + "/bis/qyjbxx/findpoint?lng=" + e.point.lng + "&lat=" + e.point.lat,
        dataType: 'json',
        async: false,
        success: function (data) {
            if (data) {
                d = data;
            }
        }
    });
    return d;
}

function show(i, title) {
    top.layer.open({
        type: 2,
        area: ['1000px', '480px'],
        title: title + '基本信息',
        maxmin: false,
        content: ctx + "/bis/qyjbxx/viewmain/" + i,
        btn: ['关闭'],
        cancel: function (index) {
        }
    });
}

function showqyfb(i, title) {
    show(i, title);
}

function showspjk(i, title) {
    top.layer.open({
        type: 2,
        area: ['1100px', '450px'],
        title: title + '视频信息',
        maxmin: false,
        content: ctx + "/zxjkyj/spjk/showqysp/" + i,
        btn: ['关闭'],
        cancel: function (index) {
        }
    });
}

//查看应急队伍信息
function showyjdw(id) {
    openDialogView("查看应急队伍信息", ctx + "/erm/yjdw/view/" + id, "800px", "400px", "");

}

//查看应急队伍信息
function showyjzb(id) {
    openDialogView("查看应急装备信息", ctx + "/erm/yjzb/view/" + id, "800px", "400px", "");

}

//查看应急队伍信息
function showyjwz(id) {
    openDialogView("查看应急物资信息", ctx + "/erm/yjwz/view/" + id, "800px", "400px", "");

}

//查看应急队伍信息
function showbncs(id) {
    openDialogView("查看避难场所信息", ctx + "/erm/bncs/view/" + id, "800px", "400px", "");

}

function showwxzy(id) {
    openDialogView("查看承诺公告信息", ctx + "/cbsgl/cngg/view/" + id, "800px", "400px", "");
}

function showcbs(id) {
    openDialogView("查看承诺公告信息", ctx + "/cbsgl/cngg/view/" + id, "800px", "400px", "");
}

function showfxxx(id, title) {
    layer.open({
        type: 1,
        area: ['800px', '450px'],
        title: title + '风险信息',
        maxmin: false,
        content: $("#select_type"),
        btn: ['关闭'],
        success: function (layero, index) {
            var dg = $('#areadata').datagrid({
                nowrap: false,
                method: "post",
                url: ctx + '/fxgk/fxxx/tablist/' + id,
                loadMsg: '正在加载',
                fit: true,
                fitColumns: true,
                selectOnCheck: false,
                border: false,
                idField: 'id',
                striped: true,
                pagination: true,
                rownumbers: true,
                pageNumber: 1,
                pageSize: 50,
                pageList: [50, 100, 150, 200, 250],
                scrollbarSize: 0,
                selectOnCheck: false,
                border: false,
                singleSelect: true,
                checkOnSelect: false,
                columns: [[
                    {field: 'm1', title: '较大风险点名称', sortable: false, width: 100, align: 'center'},
                    {field: 'm2', title: '风险类别', sortable: false, width: 100, align: 'center'},
                    {field: 'm3', title: '行业', sortable: false, width: 100, align: 'center'},
                    {field: 'm4', title: '行业类别', sortable: false, width: 100, align: 'center'},
                    {field: 'm8', title: '事故类型', sortable: false, width: 100, align: 'center'},
                    {
                        field: 'm9', title: '风险分级', sortable: false, width: 100, align: 'center', sortable: true,
                        formatter: function (value, row, index) {
                            if (value == '1') return value = '红';
                            else if (value == '2') return value = '橙';
                            else if (value == '3') return value = '黄';
                            else if (value == '4') return value = '蓝';
                        },
                        styler: function (value, row, index) {
                            if (value == '1') return 'background-color:#FF0000;color:#1E1E1E';
                            else if (value == '2') return 'background-color:#FFC125;color:#1E1E1E';
                            else if (value == '3') return 'background-color:#FFFF00;color:#1E1E1E';
                            else if (value == '4') return 'background-color:#3A5FCD;color:#1E1E1E';
                        }
                    }
                ]],
                /*onLoadSuccess:function(rowdata, rowindex, rowDomElement){
                    dg.datagrid("autoMergeCells", [ 'qyname' ]);
                },*/
                checkOnSelect: true,
                selectOnCheck: false,
            });
            //dg.datagrid('loadData',{"rows":rydata});//datagrid加载数据
        },
        cancel: function (index) {
        }
    });
}

function showcgxx(id, title) {
    layer.open({
        type: 1,
        title: title + '储罐信息',
        area: ['800px', '450px'],
        content: $("#select_type"),
        btn: ['关闭'],
        success: function (layero, index) {
            var dg = $('#areadata').datagrid({
                nowrap: false,
                method: "post",
                url: ctx + '/bis/cgxx/tablist/' + id,
                loadMsg: '正在加载',
                fit: true,
                fitColumns: true,
                selectOnCheck: false,
                border: false,
                idField: 'id',
                striped: true,
                pagination: true,
                rownumbers: true,
                pageNumber: 1,
                pageSize: 50,
                pageList: [50, 100, 150, 200, 250],
                scrollbarSize: 0,
                selectOnCheck: false,
                border: false,
                singleSelect: true,
                checkOnSelect: false,
                columns: [[
                    {field: 'm1', title: '储罐名称', sortable: false, width: 100},
                    {field: 'M9', title: '储罐位号', sortable: false, width: 100, align: 'center'},
                    {
                        field: 'm2', title: '储罐类型', sortable: false, width: 100, align: 'center',
                        formatter: function (value, row, index) {
                            if (value == '1') return value = '立式圆筒形储罐';
                            if (value == '2') return value = '卧式圆筒形储罐';
                            if (value == '3') return value = '球形储罐';
                            if (value == '4') return value = '其他储罐';
                        }
                    },
                    {
                        field: 'm4', title: '材质', sortable: false, width: 100, align: 'center',
                        formatter: function (value, row, index) {
                            if (value == '1') return value = '滚塑';
                            if (value == '2') return value = '玻璃钢';
                            if (value == '3') return value = '碳钢';
                            if (value == '4') return value = '陶瓷';
                            if (value == '5') return value = '橡胶';
                            if (value == '6') return value = '其他';
                        }
                    },
                    {
                        field: 'm6', title: '火灾危险性等级', sortable: false, width: 100, align: 'center',
                        formatter: function (value, row, index) {
                            if (value == '1') return value = '甲类';
                            if (value == '2') return value = '乙类';
                            if (value == '3') return value = '丙类';
                            if (value == '4') return value = '丁类';
                            if (value == '5') return value = '戊类';
                        }
                    },
                    {field: 'm7', title: '存储物料名称', sortable: false, width: 100, align: 'center'},
                    {field: 'm8', title: 'CAS号', sortable: false, width: 100, align: 'center'}
                ]],
                onLoadSuccess: function (rowdata, rowindex, rowDomElement) {
                },
                checkOnSelect: true,
                selectOnCheck: false,
            });
            //dg.datagrid('loadData',{"rows":rydata});//datagrid加载数据
        },
        cancel: function (index) {
        }
    });
}

function showzdzd(id, title, type) {
    layer.open({
        type: 1,
        title: title + '两重点一重大',
        area: ['850px', '450px'],
        content: $("#zdzdtab"),
        btn: ['关闭'],
        success: function (layero, index) {
            //两重点一重大地图中，优先级为 重大危险源>高危工艺>重点监管危化品
            //if (type.indexOf("zdjg") != -1) {
                addZdjgTab();//添加tab页
                loadZdjgData(id);//加载数据
            //}
            //if (type.indexOf("wxgy") != -1) {
                addGwgyTab();//添加tab页
                loadGwgyData(id);//加载数据
            //}
            //if (type.indexOf("wxy") != -1) {
                addWxyTab();//添加tab页
                loadWxyData(id);//加载数据
            //}
            //dg.datagrid('loadData',{"rows":rydata});//datagrid加载数据
        },
        end: function () {
            var tabs = $("#zdzdtab").tabs("tabs");
            var len = tabs.length;
            for (var i = 0; i < len; i++) {
                $("#zdzdtab").tabs("close", 0);
            }
        }
    });
}

//添加重点监管危化品标签页
function addZdjgTab() {
    $('#zdzdtab').tabs('add', {
        title: '重点监管危化品',
        content: '<table id="zdjg"></table>'
    });
}

function loadZdjgData(qyid) {
    $('#zdjg').datagrid({
        method: "post",
        url: ctx + '/bis/wlxx/list2',
        fit: true,
        queryParams: {
            'qyid': qyid
        },
        fitColumns: true,
        selectOnCheck: false,
        border: false,
        idField: 'id',
        striped: true,
        pagination: true,
        rownumbers: true,
        pageNumber: 1,
        pageSize: 10,
        pageList: [10, 20, 30, 40, 50],
        singleSelect: true,
        scrollbarSize: 0,
        columns: [[{
            field: 'm1',
            title: '物料名称',
            sortable: false,
            width: 100
        }, {
            field: 'm2',
            title: '年用量/年产量(t)',
            sortable: true,
            width: 70,
            align: 'center',
            formatter: function (value, row, index) {
                if (value != null && value != "") {
                    return value.toFixed(2);
                }
            }
        }, {
            field: 'm3',
            title: '最大储量(t)',
            sortable: true,
            width: 70,
            align: 'center',
            formatter: function (value, row, index) {
                if (value != null && value != "") {
                    return value.toFixed(2);
                }
            }
        }, {
            field: 'm5',
            title: '储存方式',
            sortable: true,
            width: 50,
            align: 'center',
            formatter: function (value, row, index) {
                if (value == '1')
                    return value = '储罐';
                if (value == '2')
                    return value = '桶装';
                if (value == '3')
                    return value = '袋装';
                if (value == '4')
                    return value = '其他';
            }
        }, {
            field: 'm8',
            title: '危化品类别',
            sortable: false,
            width: 80,
            align: 'center'
        }, {
            field: 'm12',
            title: '重点监管危化品',
            sortable: true,
            width: 50,
            align: 'center',
            formatter: function (value, row, index) {
                if (value == null || value == '')
                    return value = '/';
                if (value == '0')
                    return value = '否';
                if (value == '1')
                    return value = '是';
            }
        }, {
            field: 'm13',
            title: '剧毒',
            sortable: true,
            width: 50,
            align: 'center',
            formatter: function (value, row, index) {
                if (value == null || value == '')
                    return value = '/';
                if (value == '0')
                    return value = '否';
                if (value == '1')
                    return value = '是';
            }
        }, {
            field: 'm14',
            title: '易制毒',
            sortable: true,
            width: 50,
            align: 'center',
            formatter: function (value, row, index) {
                if (value == null || value == '')
                    return value = '/';
                if (value == '0')
                    return value = '否';
                if (value == '1')
                    return value = '是';
            }
        }]],
        enableHeaderClickMenu: true,
        enableRowContextMenu: false
    });
}

//添加高危工艺标签页
function addGwgyTab() {
    $('#zdzdtab').tabs('add', {
        title: '高危工艺',
        content: '<table id="gwgy"></table>'
    });
}

function loadGwgyData(qyid) {
    $('#gwgy').datagrid({
        method: "post",
        url: ctx + '/bis/gwgy/tablist/' + qyid,
        fit: true,
        fitColumns: true,
        selectOnCheck: false,
        border: false,
        idField: 'id',
        striped: true,
        pagination: true,
        rownumbers: true,
        pageNumber: 1,
        pageSize: 10,
        pageList: [10, 20, 30, 40, 50],
        singleSelect: true,
        scrollbarSize: 0,
        columns: [[{
            field: 'LABEL',
            title: '高危工艺',
            sortable: false,
            width: 100
        }, {
            field: 'M2',
            title: '套数（套）',
            sortable: false,
            width: 100,
            align: 'center'
        }]],
        enableHeaderClickMenu: true,
        enableRowContextMenu: false
    });
}

//添加重大危险源标签页
function addWxyTab() {
    $('#zdzdtab').tabs('add', {
        title: '重大危险源',
        content: '<table id="wxy"></table>'
    });
}

function loadWxyData(qyid) {
    $('#wxy').datagrid({
        method: "post",
        url: ctx + '/bis/hazardIdentity/ajlist/' + qyid,
        fit: true,
        fitColumns: true,
        selectOnCheck: false,
        border: false,
        idField: 'ID',
        striped: true,
        pagination: true,
        rownumbers: true,
        pageNumber: 1,
        pageSize: 50,
        pageList: [50, 100, 150, 200, 250],
        scrollbarSize: 0,
        singleSelect: true,
        columns: [[{
            field: 'ID',
            title: 'ID',
            hidden: true
        }, {
            field: 'm1',
            title: '类别',
            width: 100
        }, {
            field: 'm2',
            title: '辨识物质',
            width: 150,
            align: 'center'
        }, {
            field: 'm3',
            title: '最大储量（t）',
            width: 70,
            align: 'center'
        }, {
            field: 'm4',
            title: '临界储量（t）',
            width: 70,
            align: 'center'
        },
            // {field:'m5',title:'计算',width:50,align:'center'}
        ]]
    });
}

// 创建InfoWindow
function createInfoWindow(json) {
    var param = json.id;
    var bz = '';
    if (json.zylb != null && json.zylb != '') {
        bz = "作业类别：" + json.zylb + "</br>";
    }
    if (json.cbs != null && json.cbs != '') {
        bz = "承包商：" + json.cbs + "</br>";
    }
    var iw = new BMap.InfoWindow("<div style='width:280px;height:40px; cursor: pointer;' onclick=\"show" + MAP_TYPE
        + "('" + param + "','" + json.title + "','" + json.type + "')\" ><p style='font-size:14px;'>名称：" + json.title
        + "</br>地址：" + json.address + "</br>" + bz + "</p></div>");

    return iw;
}

// 创建一个Icon
function createIcon(json) {
    var icon = new BMap.Icon(ctx + json, new BMap.Size(31, 42));// , new
                                                                // BMap.Size(23,25),{imageOffset:
                                                                // new
                                                                // BMap.Size(-46,-21),infoWindowOffset:new
                                                                // BMap.Size(17,1),offset:new
                                                                // BMap.Size(9,25)});
    return icon;
}

function SearchClass(data) {
    this.datas = data;
}

SearchClass.prototype.search = function (rule) {
    if (this.datas == null) {
        return false;
    }

    var reval = [];
    var datas = this.datas;
    var len = datas.length;
    var me = this;
    var ruleReg = new RegExp(this.trim(rule.d));
    var hasOpen = false;

    var addData = function (data, isOpen) {
        // 最后一条数据打开信息窗口
        if (isOpen && !hasOpen) {
            hasOpen = true;
            data.isOpen = 1;
        } else {
            data.isOpen = 0;
        }
        data.label = 1;//设置label
        reval.push(data);
    }
    var getData = function (data, key) {
        var ks = me.trim(key).split(/\./);
        var i = null, s = "data";
        if (ks.length == 0) {
            return data;
        } else {
            for (var i = 0; i < ks.length; i++) {
                s += '["' + ks[i] + '"]';
            }
            return eval(s);
        }
    }
    for (var cnt = 0; cnt < len; cnt++) {
        var data = datas[cnt];
        var d = getData(data, rule.k);
        if (rule.t == "single" && rule.d == d) {
            addData(data, false);
        } else if (rule.t != "single" && ruleReg.test(d)) {
            addData(data, false);
        } else if (rule.s == "all") {
            addData(data, true);
        }
    }
    return reval;
}
SearchClass.prototype.setData = function (data) {
    this.datas = data;
}
SearchClass.prototype.trim = function (str) {
    if (str == null) {
        str = "";
    } else {
        str = str.toString();
    }
    return str.replace(/(^[\s\t\xa0\u3000]+)|([\u3000\xa0\s\t]+$)/g, "");
}
//回车键事件
document.onkeydown = function (event) {
    var e = event || window.event || arguments.callee.caller.arguments[0];
//    if(e && e.keyCode==27){ // 按 Esc 
//        //要做的事情
//      }
//    if(e && e.keyCode==113){ // 按 F2 
//         //要做的事情
//       }            
    if (e && e.keyCode == 13) { // enter 键
        //要做的事情
        search('keyword');
    }
};
// ///
// 搜索方法 param{searchTypeRadio_name：搜索radio的名字,keyword_name:搜索文本框的id}
window.search = function (keyword_name) {
    // 获取页面dom
    if (MAP_TYPE == "spjk" || MAP_TYPE == "cgxx" || MAP_TYPE == "zdzd") {
        var keyword = $("#keyword").combobox("getValue");
        if (keyword && keyword != null) {
            // 获取dom的值
            var dd = searchClass.search({
                k: "title",
                d: keyword,
                s: ""
            });
            // 向地图中添加marker
            reloadMap(dd);
        } else {
            reset();
        }
    } else if (MAP_TYPE == "yjdw" || MAP_TYPE == "yjwz" || MAP_TYPE == "yjzb" || MAP_TYPE == "bncs" || MAP_TYPE == "ryfb" || MAP_TYPE == "whpcl") {
        if (keyword_name && keyword_name != null) {
            // 获取dom的值
            var dd = searchClass.search({
                k: "title",
                d: keyword_name,
                t: "single",
                s: ""
            });
            // 向地图中添加marker
            reloadMap(dd);
        } else {
            reset();
        }

    } else if (MAP_TYPE == "wxzy" || MAP_TYPE == "cbs") {
        var keyword = $("#keyword").combobox("getValue");
        if (keyword && keyword != null) {
            // 获取dom的值
            var dd = searchClass.search({
                k: "title",
                d: keyword,
                t: "single",
                s: ""
            });
            // 向地图中添加marker
            reloadMap(dd);
        } else {
            reset();
        }
    } else if (MAP_TYPE == "qyfb" || MAP_TYPE == "fxxx") {
        getQyfbJsonByQyname();
    }
}

// 重置返回所有结果
function reset() {
    // s:{''只返回找到的结果|all返回所有的}
    $("#keyword").textbox('setValue', '');
    if (MAP_TYPE == "qyfb" || MAP_TYPE == "fxxx") {
        getQyfbJson();
    } else if (MAP_TYPE != "fxyt" && MAP_TYPE != "wgfx") {
        var dd = searchClass.search({
            k: "m1",
            d: "显示全部",
            s: "all"
        });
        reloadMap(dd);// 向地图中添加marker
    }
}

// 创建marker
window.addMarker = function (data) {
    map.clearOverlays();
    for (var i = 0; i < data.length; i++) {
        var json = data[i];// 整数据
        var p0 = json.pointx;// 经度
        var p1 = json.pointy;// 纬度
        var point = new BMap.Point(p0, p1);
        var iconImg = createIcon(json.icon);
        var marker = new BMap.Marker(point, {
            icon: iconImg
        });
        map.addOverlay(marker);
        (function () {
            var _json = json;
            var _iw = createInfoWindow(_json);
            var _marker = marker;
            _marker.addEventListener("click", function () {
                this.openInfoWindow(_iw);
            });
            if (json.isOpen) {
                _marker.openInfoWindow(_iw);
            }
        })()
    }
}
