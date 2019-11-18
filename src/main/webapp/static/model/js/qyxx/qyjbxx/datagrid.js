var dg;
var zdzd=[];//重点重大数据，第一次进入遍历去重，查询时直接遍历该数据即可

$(function () {
    //统计属性
    dg = $('#mapdatagrid').datagrid({
        nowrap: false,
        method: "post",
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
        pageSize: 30,
        pageList: [30, 50, 100, 150, 200],
        scrollbarSize: 10,
        selectOnCheck: false,
        border: false,
        singleSelect: true,
        checkOnSelect: false,
        enableHeaderClickMenu: true,
        enableRowContextMenu: false,
        toolbar: '#dg_tb'
    });
});

/**
 * 企业分布
 */
function loadMapData_qyfb() {
    dg.datagrid({
        url: ctx + '/bis/qyjbxx/list',
        columns: [[
            {field: 'm1', title: '企业名称', sortable: false, width: 200},
            {
                field: 'm44', title: '企业风险等级', sortable: true, width: 60, align: 'center',
                formatter: function (value) {
                    if (value == '1') return "<i class='fa fa-flag' style='font-size: 18px;color:red;'></i>";
                    if (value == '2') return "<i class='fa fa-flag' style='font-size: 18px;color:orange;'></i>";
                    if (value == '3') return "<i class='fa fa-flag' style='font-size: 18px;color:yellow;'></i>";
                    if (value == '4') return "<i class='fa fa-flag' style='font-size: 18px;color:blue;'></i>";
                }
            }
        ]],
        onDblClickRow: function (index, row) {
            $('#keyword').combobox('setValue',row.m1);
            search();
        }
    });
}

function search_qyfb(){
    var obj={check_company_name:$('#keyword').combobox('getText')};
    dg.datagrid('load',obj);
}
function reset_qyfb(){
    dg.datagrid('load',{});
}

/**
 * 储罐信息
 */
function loadMapData_cgxx() {
    dg.datagrid({
        url: ctx + '/bis/cgxx/ajlist',
        columns: [[
            {field: 'qyname', title: '企业名称', sortable: false, width: 200, align: 'left'},
            {field: 'M1', title: '储罐名称', sortable: false, width: 100}
        ]],
        onDblClickRow: function (index, row) {
            $('#keyword').combobox('setValue',row.qyname);
            search();
        }
    });
}

function search_cgxx(){
    var obj={bis_cgxx_cx_qyname:$('#keyword').combobox('getText')};
    dg.datagrid('load',obj);
}

/**
 * 视频监控
 */
function loadMapData_spjk() {
    dg.datagrid({
        url: ctx + '/zxjkyj/spjk/qylist',
        columns: [[
            {field: 'm1', title: '企业名称', sortable: false, width: 200, align: 'left'}
        ]],
        onDblClickRow: function (index, row) {
            $('#keyword').combobox('setValue',row.m1);
            search();
        }
    });
}
function search_spjk(){
    var obj={qyname:$('#keyword').combobox('getText')};
    dg.datagrid('load',obj);
}

/**
 * 两重点一重大，不分页返回数据，数据顺序为：重点监管--》高危工艺--》重点监管危化品
 * 显示优先级为 重大危险源>高危工艺>重点监管危化品
 */
function loadMapData_zdzd() {
    if(zdzd.length==0)//第一次进去加载数据
        zdzdDataFilter(Global_Search_Data);
    dg.datagrid({
        url: null,
        columns: [[
            {field: 'title', title: '企业名称', sortable: false, width: 200, align: 'left'},
            {
                field: 'type', title: '重点重大标示', sortable: false, width: 60, align: 'center',
                formatter: function (value, row) {
                    var html = "";
                    if (value) {
                        if (value.indexOf('zdjg') != -1) html += "<i class='fa fa-shield fa-2x' style='color:#ffd075;'></i>";
                        if (value.indexOf('wxgy') != -1) html += "<i class='fa fa-flask fa-2x' style='color:#e57e20;'></i>";
                        if (value.indexOf('wxy') != -1) html += "<i class='fa fa-exclamation-triangle fa-2x' style='color:#ed2d2d;'></i>";
                    }
                    return html;
                }
            }
        ]],
        data: zdzd,
        loadFilter: pagerFilter,
        onDblClickRow: function (index, row) {
            $('#keyword').combobox('setValue',row.title);
            search();
        }
    });
}
function search_zdzd(){
    var keyword =$("#keyword").textbox("getText");
    var newdata = [];
    $.each(zdzd,function (index,item) {
        if(item.title.indexOf(keyword)!=-1){
            newdata.push(item);
        }
    });
    dg.datagrid("loadData",newdata);
}
function reset_zdzd(){
    loadMapData_zdzd();
}

/**
 * 网格风险统计
 * @param data
 */
function loadMapData_wgfx(data,colorlist) {
    var unsortd = data.concat();
    var loaddata = data.sort(compare('COUNT'));
    dg.datagrid({
        url: null,
        columns: [[
            {field: 'm1', title: '网格名称', sortable: false, width: 200, align: 'left'},
            {field: 'COUNT', title: '风险总值', sortable: false, width: 60, align: 'center'}
        ]],
        data: loaddata,
        loadFilter: pagerFilter,
        onDblClickRow: function (index, row) {
            map.clearOverlays();
            for(var i =0;i<unsortd.length;i++){
                if(unsortd[i].code==row.code){
                    createPolygons([row], [colorlist[i]]);
                }
            }
        }
    });
}

/**
 * 应急队伍、应急装备、应急物资，数量少量，就不重新获取数据了。
 * @param data
 */
function loadMapData_yj(data) {
    dg.datagrid({
        url: null,
        columns: [[
            {field: 'title', title: '名称', sortable: false, width: 200, align: 'left'},
            {field: 'address', title: '地址', sortable: false, width: 200, align: 'left'}
        ]],
        data: data,
        loadFilter: pagerFilter,
        onDblClickRow: function (index, row) {
            search(row.title);
        }
    });
}
/**
 * 人员分布
 * @param data
 */
function loadMapData_ryfb(data) {
    dg.datagrid({
        url: null,
        columns: [[
            {field: 'title', title: '姓名', sortable: false, width: 50, align: 'left'},
            {field: 'address', title: '所属单位', sortable: false, width: 150, align: 'left'},
            {field: 'contact', title: '联系方式', sortable: false, width: 60, align: 'left'}
        ]],
        data: data,
        loadFilter: pagerFilter,
        onDblClickRow: function (index, row) {
            search(row.title);
        }
    });
}
/**
 * 危险作业
 * @param data
 */
function loadMapData_wxzy() {
    dg.datagrid({
        url: ctx + '/cbsgl/cngg/wxzylist',
        columns: [[
            {field: 'qyname', title: '企业名称', sortable: false, width: 80, align: 'left'},
            {field: 'zylb', title: '作业类别', sortable: false, width: 60, align: 'left'},
            {field: 'zyjb', title: '作业级别', sortable: false, width: 40, align: 'left'},
            {field: 'zysl', title: '几处', sortable: false, width: 30, align: 'left'}
        ]],
        loadFilter: pagerFilter,
        onDblClickRow: function (index, row) {
            $('#keyword').combobox('setValue',row.qyname);
            search();
        },
        onLoadSuccess: function(){
            $(this).datagrid("autoMergeCells",['qyname']);
        },
    });
}
function search_wxzy(){
    var obj={bis_wxzy_cx_qyname:$('#keyword').combobox('getText')};
    dg.datagrid('load',obj);
}
/*
承包商分布
 */
function loadMapData_cbs() {
    dg.datagrid({
        url: ctx + '/cbsgl/cngg/cbslist',
        columns: [[
            {field: 'qyname', title: '企业名称', sortable: false, width: 80, align: 'left'},
            {field: 'cbsname', title: '承包商名称', sortable: false, width: 80, align: 'left'},
            {field: 'zyrs', title: '作业人数', sortable: false, width: 30, align: 'left'}
        ]],
        loadFilter: pagerFilter,
        onDblClickRow: function (index, row) {
            $('#keyword').combobox('setValue',row.qyname);
            search();
        },
        onLoadSuccess: function(){
            $(this).datagrid("autoMergeCells",['qyname']);
        },
    });
}
function search_cbs(){
    var obj={bis_cbs_cx_qyname:$('#keyword').combobox('getText')};
    dg.datagrid('load',obj);
}
/**
 * 危化品车辆
 * @param data
 */
function loadMapData_whpcl(data) {
    dg.datagrid({
        url: null,
        columns: [[
            {field: 'title', title: '企业名称', sortable: false, width: 100, align: 'left'},
            {field: 'count', title: '数量', sortable: false, width: 20, align: 'left'}
        ]],
        data: data,
        loadFilter: pagerFilter,
        onDblClickRow: function (index, row) {
            search(row.title);
        }
    });
}

/**
 * 本地分页函数
 * @param data
 * @returns {*}
 */
function pagerFilter(data) {
    if (typeof data.length == 'number' && typeof data.splice == 'function') {   // is array
        data = {
            total: data.length,
            rows: data
        }
    }
    var opts = dg.datagrid('options');
    var pager = dg.datagrid('getPager');
    pager.pagination({
        onSelectPage: function (pageNum, pageSize) {
            opts.pageNumber = pageNum;
            opts.pageSize = pageSize;
            pager.pagination('refresh', {
                pageNumber: pageNum,
                pageSize: pageSize
            });
            dg.datagrid('loadData', data);
        }
    });
    if (!data.originalRows) {
        data.originalRows = (data.rows);
    }
    var start = (opts.pageNumber - 1) * parseInt(opts.pageSize);
    var end = start + parseInt(opts.pageSize);
    data.rows = (data.originalRows.slice(start, end));
    return data;
}

//排序规则
function compare(property){
    return function(a,b){
        var value1 = a[property];
        var value2 = b[property];
        return value1 - value2;
    }
}

/**
 * 两重点一重大数据filter
 * 目的：1、删除重复的企业数据2、将数据type属性重组，格式为type：“wxy ,wxgy,zdjg ”
 */
function zdzdDataFilter(data) {
    var len = data.length;
    for (var i = len - 1; i >= 0; i--) {
        var tmp = data[i];
        if (tmp.type == 'wxy') {
            zdzd.push(tmp);
        } else if (tmp.type == 'wxgy') {
            var f = true;
            //循环查看wxy中与wxgy是否有重复的企业名称（减少循环次数）
            $.each(zdzd, function (index, item) {
                if (item.type == 'wxgy') {
                    return false;
                } else {
                    if (item.id == tmp.id) {
                        item.type += "," + tmp.type;
                        f = false;
                        return false;
                    }
                }
            });
            if (f)
                zdzd.push(tmp);
        } else if (tmp.type == 'zdjg') {
            var f = true;
            //循环查看wxy、wxgy中与zhjg是否有重复的企业名称（减少循环次数）
            $.each(zdzd, function (index, item) {
                if (item.type == 'zdjg') {
                    return false;
                } else {
                    if (item.id == tmp.id) {
                        item.type += "," + tmp.type;
                        f = false;
                        return false;
                    }
                }
            });
            if (f)
                zdzd.push(tmp);
        }
    }
}