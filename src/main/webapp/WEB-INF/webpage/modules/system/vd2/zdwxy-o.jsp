<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>

<!doctype html>
<head>
<meta charset="utf-8">
<title>重大危险源大数据</title>
<meta name="decorator" content="default"/>
<script type="text/javascript" src="${ctxStatic}/ckplayer/ckplayer.js"></script>
<link href="${ctxStatic}/font-awesome-4.7.0/css/font-awesome.min.css"  type="text/css" media="all"/>
<link rel="stylesheet" type="text/css" href="${ctx}/static/jquery-select2/htools.select.skin.css">
<link href="${ctx}/static/model/vd2/css/style.css"  rel="stylesheet" type="text/css" media="all"/>
<link href="${ctx}/static/model/vd2/css/gov.css"  rel="stylesheet" type="text/css" media="all"/>
<link href="${ctx}/static/jqueryToast/css/toast.style.css" rel="stylesheet">
<script type="text/javascript" src="${ctx}/static/jqueryToast/js/toast.script.js"></script>

<style>
.amiddboxttop,.arightboxtop {
	overflow: hidden;
	background: url(${ctx}/static/model/vd2/img/lbBg.png);
	background-size: 100% 100%;
	background-repeat: no-repeat;
	background-position: top center;
	width: 100%;
}
.amiddboxttop .tab_ctt,.aleftboxtmidd .tab_ctt{
    height: 80%;
}
.spjk_{
	width: 95.2%;
    margin: 2% 2.4%;
    height: 84%;
    position: relative;
    /*background: #000;*/
}

.spjk_btns{
	display: flex;
    font-size: 12px;
    position: absolute;
    right: 10px;
    margin-top: 15px;
    color: #ababab;
}

.spjk_btn{
    margin: 0 4px;
    padding: 4px 9px;
    background: #013d59;
    border-radius: 3px;
    cursor: pointer;
}
.btn_chos{
	background: #2d75ad;
	color: #fff;
}

.amiddboxtbott1content {
    height: 85%;
}

@media screen and (max-width: 10000px){
	.pt2,.ptm,.ptm__ {
	    padding-top: 2.4%!important;
	}
}


@media screen and (max-width: 1366px){
	.pt2,.ptm,.ptm__ {
	    padding-top: 1.8%!important;
	}
}


/* 重写easyui */
.panel-body {
    background-color: #00075a;
    color: #fff;
    font-size: 12px;
}

.datagrid-htable, .datagrid-btable, .datagrid-ftable,.datagrid-cell-rownumber {
    color: #f4f4f4;
}

.datagrid-header, .datagrid-td-rownumber{
    background-color: #1d639a;
    background: #1d639a;
}

.datagrid-header, .datagrid-toolbar, .datagrid-pager, .datagrid-footer-inner {
    border-color: #7799c7;
}

.datagrid-header td, .datagrid-body td, .datagrid-footer td {
    border-color: #638192;
    border-style: solid;
}

.datagrid-row-alt {
    background: #3d5f7b;
}

.datagrid-header td.datagrid-header-over {
    background: #0a3e82!important;
    color: #fff;
    cursor: pointer;
}

.datagrid-row-over{
    background: #00064c;
    color: #fff;
    cursor: pointer;
}

.datagrid-row-alt.datagrid-row-over {
    background: #32536f;
    color: #fff;
    cursor: pointer;
}

.datagrid-row-selected {
    background: #ffe48d!important;
    color: #000000!important;
}

.datagrid-toolbar, .datagrid-pager {
    background: #00075a;
    color: #bdbdbd;
}

.pagination-page-list, .pagination .pagination-num {
    border-color: #638192;
    background-color: #3d5f7b;
}

.datagrid-pager.pagination td:nth-child(1),.datagrid-pager.pagination td:nth-child(2){
	display:none;
}

.line_{
    overflow: hidden;
    text-overflow: ellipsis;
    display: -webkit-box;
    -webkit-line-clamp: 1;
    -webkit-box-orient: vertical;
}

.date__{
flex-shrink: 0;
}
</style>
</head>
<body style="background: #000000 url(${ctx}/static/model/vd2/img/a_bg.png);background-size: cover;background-position:center;">
<div class="wpbox">

<div class="bnt" style="background: url(${ctx}/static/model/vd2/img/header_bg.png);background-size:100% 100%;background-position:center;">
  <div class="topbnt_left fl">
   <ul>
      <li class="weather"></li>
   </ul>
  </div>
  <h1 class="tith1 fl">重大危险源监控预警系统</h1>
  <div class=" fr topbnt_right">
    <ul>
       <li style="width: 120px;cursor: pointer;"><a onclick="entersys()">进入系统</a></li>
    </ul>
   
  </div>
</div>
<!-- bnt end -->
	<div class="left1" style="width: 33%;">
		<div class="amiddboxttop" style="height: 50%;">
			<h2 class="tith2 pt2" style="padding-top: 2.5%;">储罐实时监控</h2>
			<div style="margin-left: 2.5%;    margin-top: 1%;height: 87%;width: 95%;">
				<table id="ssjc_cg_dg"></table>
			</div>
		</div>
	
		<div class="arightboxtop" style="height: 50%;position: relative;">
			<h2 class="tith2 ptm__" style="padding-top:1.5%;">视频实时监测</h2>
            <div class="spjk_btns" style="z-index: 100;">
                <div class="spjk_btn btn_chos" id="sp1" style="display: none;">1</div>
                <div class="spjk_btn" id="sp2" style="display: none;">2</div>
                <div class="spjk_btn" id="sp3" style="display: none;">3</div>
                <div class="spjk_btn" id="spmore" style="display: none;">更多</div>
            </div>
            <div class="spjk_" id="playlive">

            </div>
	    </div>
	</div>
	
	<!--  left1 end -->
     <div class="mrbox_topmidd" style="width: 33.6%;margin-left:0.4%">
         <div class="amiddboxttop" style="height: 50%;">
             <h2 class="tith2 pt2" style="padding-top: 2.5%;">气体实时监测</h2>
             <div style="margin-left: 2.5%;    margin-top: 1%;height: 87%;width: 95%;">
				<table id="ssjc_qt_dg"></table>
			 </div>
         </div>
		
         <div class="amidd_bott" style="height: 50%;">
             <div class="amiddboxtbott1 fl">
                <h2 class="tith2 ptm">二道门进出信息</h2>
                <div class="labic_" style="margin: 15px;">
                    <%--<div class="ul__ dowebok" style="overflow:hidden">
                        <div class="li__" style="display: flex; justify-content: space-between;">
                            <div style="display: flex;align-item:center;">
                                <div class="tag__">
                                    <img src="${ctxStatic}/model/images/home/skin_/new.png" />
                                </div>
                                <div class="line_ news__">
                                    周八斤从公司南门离开
                                </div>
                            </div>
                            <div class="date__">
                                2019-09-02 17:10:02
                            </div>
                        </div>
                        <div class="li__" style="display: flex; justify-content: space-between;">
                            <div style="display: flex;align-item:center;">
                                <div class="tag__">
                                    <img src="${ctxStatic}/model/images/home/skin_/new.png" />
                                </div>
                                <div class="line_ news__">
                                    周八斤从公司北门进入
                                </div>
                            </div>
                            <div class="date__">
                                2019-09-02 07:25:52
                            </div>
                        </div>
                    </div>--%>
                        <div style="text-align: center;">
                            <img src="${ctxStatic}/model/images/hgqy/noedm.png" style="height:300px;" />
                        </div>
			    </div>
             </div>
         </div>
    </div>

     <div class="mrbox_top_right" style="width: 32.6%;">
		<div class="amiddboxttop" style="height: 50%;">
        	<h2 class="tith2 pt2" style="padding-top: 2.5%;">高危工艺实时监测</h2>
        	<div style="margin-left: 2.5%;    margin-top: 1%;height: 87%;width: 95%;">
				<table id="ssjc_gwgy_dg"></table> 
			 </div>
        </div>
     
	     <div class="arightboxtop" style="height: 50%;">
	     	<h2 class="tith2 ptm">报警信息</h2>
	     	<div class="labic_" style="margin: 15px;">
				<div class="ul__ dowebok" style="overflow:hidden;text-align: center;">
                    <c:choose>
                        <c:when test="${bjxxList ne [] }">
                            <c:forEach items="${bjxxList }" var="s">
                                <div class="li__" style="display: flex; justify-content: space-between;">
                                <div style="display: flex;align-item:center;">
                                    <div class="tag__">
                                        <img src="${ctxStatic}/model/images/home/skin_/new.png" />
                                    </div>
                                    <div class="line_ news__">
                                        <c:choose>
                                            <c:when test="${s.type == 1}">
                                                储罐${s.bh }温度报警：${s.bjxx } ℃
                                            </c:when>
                                            <c:when test="${s.type == 2}">
                                                储罐${s.bh }压力报警：${s.bjxx } kPa
                                            </c:when>
                                            <c:when test="${s.type == 3}">
                                                储罐${s.bh }液位报警：${s.bjxx } cm
                                            </c:when>
                                            <c:when test="${s.type == 4}">
                                                有毒气体${s.bh }浓度报警：${s.bjxx } mg/m³或ppm
                                            </c:when>
                                            <c:when test="${s.type == 5}">
                                                可燃气体${s.bh }浓度报警：${s.bjxx } %LEL
                                            </c:when>
                                        </c:choose>
                                    </div>
                                </div>

                                <div class="date__">
                                    <fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${s.cjsj }" />
                                </div>
                            </div>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <img src="${ctxStatic}/model/images/hgqy/nobjxx.png" style="height:300px;" />
                    </c:otherwise>
                    </c:choose>
				</div>
			</div>
  		 </div>
	</div>

    <%-- 报警信息toast弹出框 --%>
    <div id="msg__s" style="position:fixed;bottom:0;right:0">

    </div>

<script type="text/javascript">
    var qyid = '${qyid}';

function entersys(){
	window.location.href="${ctx}/hgqyIndex/sys/1/4309";
}

window.onload = function(){
	$(".spjk_btn").click(function(){
		$(this).addClass("btn_chos").siblings(".spjk_btn").removeClass("btn_chos");
	})

	getBjxx();
}

//阻止冒泡事件的兼容性处理 
function stopBubble(e) { 
   if(e && e.stopPropagation) { //非IE 
    	e.stopPropagation(); 
  	} else { //IE 
    	window.event.cancelBubble = true; 
  	} 
}

// 查看详情提示框
function tipsInfo(idName){
    layer.tips("<span style='color:#fff;'>点击查看详情</span>",'#'+idName,{tips:[3,'#f8ac59'],time:1000,area: 'auto',maxWidth:500,tipsMore:true});
}

$(function(){   
	dg=$('#ssjc_cg_dg').datagrid({// 储罐
	nowrap:false,
	method: "post",
    url:'${ctx}/zxjkyj/zdwxycg/list',
    queryParams: {qyid: qyid},
    fit : true,
	fitColumns : true,
	selectOnCheck:false,
	nowrap: false,
	idField : 'id',
	striped:true,
	scrollbarSize:0,
	pagination:true,
	rownumbers:true,
	pageNumber:1,
	pageSize : 50,
	pageList : [20, 50, 100, 150, 200, 250 ],
	singleSelect:true,
    columns:[[
        {field:'wh',title:'储罐位号',sortable:false,width:60,align:'center',
            formatter : function(value, row, index) {
                if (value==null||value=='')
                    return '/';
                else
                    return '<a id="wh_'+row.RowNumber+'" onclick="viewxq('+row.tankid+')" onmouseover=tipsInfo("wh_'+row.RowNumber+'")>'+value+'</a>';
            }
        },
        {field:'cgname',title:'储罐名称',sortable:false,width:80,align:'center',
            formatter : function(value, row, index) {
                if (value==null||value=='')
                    return '/';
                else
                    return '<a id="cgname_'+row.RowNumber+'" onclick="viewxq('+row.tankid+')" onmouseover=tipsInfo("cgname_'+row.RowNumber+'")>'+value+'</a>';
            }
        },
		
		//{field:'cgqbh',title:'传感器编码',sortable:false,width:80,align:'center'},
		{field:'jctype',title:'监测类型',sortable:false,width:60,align:'center',
			formatter : function(value, row, index) {
				if (value==1)
					return '温度';
				else if (value==2)
					return '压力';
				else
					return '液位';
			}
		},
		{field:'bjxx',title:'报警信息',sortable:false,width:80,align:'center',
			formatter : function(value, row, index) {
				if (value==null || value == 0.00){
					return '/';
				}else{
					if (row.jctype==1)
						return value.toFixed(2)+" ℃";
					else if (row.jctype==2)
						return value.toFixed(2)+" kPa";
					else
						return value.toFixed(2)+" cm";
				}
			},
			styler: function(value){
				if (value != null && value != 0.00) {
					return 'background-color:red;color:white';
				}
			}
		},
		{field:'jcsj',title:'监测数据',sortable:false,width:80,align:'center',
			formatter : function(value, row, index) {
				if (value==null)
					return '/';
				else if (row.jctype==1)
					return value.toFixed(2)+" ℃";
				else if (row.jctype==2)
					return value.toFixed(2)+" kPa";
				else
					return value.toFixed(2)+" cm";
			}
		},
		{field:'cjsj',title:'采集时间',sortable:false,width:100,align:'center',
			formatter : function(value) {
				var datetime = new Date(value);
				return datetime.format("yyyy-MM-dd hh:mm:ss");
			}
		},
		{field:'cz',title:'操作',sortable:false,width:150,align:'center',
			formatter : function(value, row, index) {
				var a = "";
				a+= "<a style='margin:2px' class='btn btn-warning btn-xs' onclick='viewbjxx(" + row.tankid + ","+row.jctype+")'>报警信息</a>";
				a+= "<a style='margin:2px' class='btn btn-primary btn-xs' onclick='viewbdt(" + row.tankid + ","+row.jctype+")'>趋势图</a>";
				return a;
			}
		}
    ]],
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
    },
    onLoadSuccess: function(){
    	$(this).datagrid("autoMergeCells",['qyname','cgname','wh','wl','lx']);
    },
    onLoadError:function(){
    	layer.open({title: '提示',offset: 'rb',content: '数据加载中，请耐心等待。',shade: 0 ,time: 2000 });
    },
    rowStyler:function(index,row){
		if (row.m10!=null&&row.m10>0&&row.m8>row.m10){
			return 'background-color:rgb(232, 190, 101);';
		}
	},
    enableHeaderClickMenu: true,
    enableRowContextMenu: false,
    toolbar:''
	});
	
	dg2=$('#ssjc_qt_dg').datagrid({// 气体
		nowrap:false,
		method: "post",
	    url:ctx+'/zxjkyj/zdwxyqt/list',
        queryParams: {qyid: qyid},
	    fit : true,
		fitColumns : true,
		selectOnCheck:false,
		nowrap: false,
		idField : 'id',
		striped:true,
		scrollbarSize:0,
		pagination:true,
		rownumbers:true,
		pageNumber:1,
		pageSize : 50,
		pageList : [20, 50, 100, 150, 200, 250 ],
		singleSelect:true,
	    columns:[[
			{field:'cgqbh',title:'传感器编号',sortable:false,width:100,align:'center',
				formatter : function(value, row, index) {
					if (value==null||value=='')
						return '/';
					else
						return '<a onclick="viewxq1('+value+')">'+value+'</a>';
				}
			},
			{field:'qttype',title:'气体类型',sortable:false,width:80,align:'center',
				formatter : function(value, row, index) {
					if(value == 1){
						return '有毒气体';
					}else{
						return '可燃气体';
					}
				}
			},
			//{field:'cgqbh',title:'传感器编号',sortable:false,width:100,align:'center'},
			{field:'bjnd',title:'气体报警浓度',sortable:false,width:100,align:'center',
				formatter : function(value, row, index) {
					if (value==null || value == 0.00) {
						return '/';
					} else{
						var result = value.toFixed(2);
						var dw = '';
						if (row.qttype  == 1) {
							dw = 'mg/m³或ppm';
						} else {
							dw = '%LEL';
						}
						return result + dw;
					}
				},
				styler: function(value){
					if (value != null && value != 0.00) {
						return 'background-color:red;color:white';
					}
				}
			},
			/* {field:'bjsj',title:'气体报警时间',sortable:false,width:100,align:'center',
                formatter: function(value){
                    if (value) {
                        var datetime = new Date(value);
                        return datetime.format("yyyy-MM-dd hh:mm:ss");
                    } else {
                        return '/';
                    }

                },
                styler: function(value){
                    if (value) {
                        return 'background-color:red;color:white';
                    }
                }
            }, */
			{field:'ssnd',title:'实时浓度',sortable:false,width:80,align:'center',
				formatter : function(value, row, index) {
					if (value!=null){
						return value.toFixed(2);
					}
					else
						return '/';
				}
			},
			{field:'cjsj',title:'采集时间',sortable:false,width:100,align:'center',
				formatter : function(value, row, index) {
					var datetime = new Date(value);
					return datetime.format('yyyy-MM-dd hh:mm:ss');
				}
			},
			{field:'cz',title:'操作',sortable:false,width:150,align:'center',
				formatter : function(value, row, index) {
					var a = "";
					a+= "<a style='margin:2px' class='btn btn-warning btn-xs' onclick='viewbjxx1(" + row.cgqbh + ")'>报警信息</a>";
					a+= "<a style='margin:2px' class='btn btn-primary btn-xs' onclick=viewbdt1('" + row.cgqbh + "','"+row.qttype+"')>趋势图</a>";
					return a;
				}
			}
	    ]],
	    onDblClickRow: function (rowdata, rowindex, rowDomElement){
	    },
	    onLoadSuccess: function(){
	        $(this).datagrid("autoMergeCells",['qyname']);
	    },
	    onLoadError:function(){
	    	layer.open({title: '提示',offset: 'rb',content: '数据加载中，请耐心等待。',shade: 0 ,time: 2000 });
	    },
	    rowStyler:function(index,row){
			if (row.m10!=null&&row.m10>0&&row.m8>row.m10){
				return 'background-color:rgb(232, 190, 101);';
			}
		},
	    enableHeaderClickMenu: true,
	    enableRowContextMenu: false,
	    toolbar:''
		});
	
	dg3=$('#ssjc_gwgy_dg').datagrid({    
		nowrap:false,
		method: "post",
	    url:ctx+'/zxjkyj/gwgyjc/list',
	    fit : true,
		fitColumns : true,
		selectOnCheck:false,
		nowrap: false,
		idField : 'id',
		striped:true,
		scrollbarSize:0,
		pagination:true,
		rownumbers:true,
		pageNumber:1,
		pageSize : 50,
		pageList : [20, 50, 100, 150, 200, 250 ],
		singleSelect:true,
	    columns:[[    
	        {field:'name',title:'高危工艺名称',sortable:false,width:100,align:'center'},    
	        {field:'wl',title:'反应物料',sortable:false,width:100,align:'center'},
	        {field:'rj',title:'容积(m³)',sortable:false,width:100,align:'center' ,
	        	formatter : function(value, row, index) {
	     		if (value==null||value=='')
	     			return 'N/A';
	     		else
	     			return value;
	 		}},
	 		{field:'level',title:'液位',sortable:false,width:100,align:'center',
	 			formatter : function(value, row, index) {
	 	     		if (value==null||value=='')
	 	     			return 'N/A';
	 	     		else
	 	     			return value;
	 	 		}},
	 		/* {field:'innertemp',title:'釜内温度(℃)',sortable:false,width:100,align:'center',
	 			formatter : function(value, row, index) {
	 	     		if (value==null||value=='')
	 	     			return 'N/A';
	 	     		else
	 	     			return value;
	 	 		}},
	 		{field:'ourtertemp',title:'夹套温度(℃)',sortable:false,width:100,align:'center',
	 			formatter : function(value, row, index) {
	 	     		if (value==null||value=='')
	 	     			return 'N/A';
	 	     		else
	 	     			return value;
	 	 		}},
	        {field:'pressure',title:'压力(MPa)',sortable:false,width:100,align:'center' ,
	        	formatter : function(value, row, index) {
	         		if (value==null||value=='')
	         			return 'N/A';
	         		else
	         			return value;
	     		}},    
	        {field:'flux',title:'流量(m3/h)',sortable:false,width:100,align:'center' ,
	        	formatter : function(value, row, index) {
	         		if (value==null||value=='')
	         			return 'N/A';
	         		else
	         			return value;
	     		}}, */
	        {field:'weight',title:'称重(kg)',sortable:false,width:100,align:'center' ,
	        	formatter : function(value, row, index) {
	         		if (value==null||value=='')
	         			return 'N/A';
	         		else
	         			return value;
	     		}},
	     	/* {field:'current',title:'搅拌电流(A)',sortable:false,width:80,align:'center' ,
	     		formatter : function(value, row, index) {
	         		if (value==null||value=='')
	         			return 'N/A';
	         		else
	         			return value;
	     		}}, */
	     	{field:'ph',title:'PH值',sortable:false,width:80,align:'center' ,
	     		formatter : function(value, row, index) {
	         		if (value==null||value=='')
	         			return 'N/A';
	         		else
	         			return value;
	     		}},
	     	{field:'colltime',title:'更新时间',sortable:false,width:100,align:'center',
	             	formatter : function(value, row, index) {
	             		var datetime = new Date(value);  
	                    return datetime.format("yyyy-MM-dd hh:mm:ss");
	         	}}
	        
	    ]],
	    onDblClickRow: function (rowdata, rowindex, rowDomElement){
	    },
	    onLoadSuccess: function(){
	    },
	     
	    rowStyler:function(index,row){
			if (row.m10!=null&&row.m10>0&&row.m8>row.m10){
				return 'background-color:rgb(232, 190, 101);';
			}
		},
	    enableHeaderClickMenu: true,
	    enableRowContextMenu: false,
	    toolbar:''
		});
});

// 视频实时监测
var spUrls = [];
$(function () {
	$.ajax({
		type: 'POST',
		url: ctx+'/bis/spsbxx/zdwxySpUrls',
        data: {'qyid': qyid},
		dataType: 'json',
		success: function(data) {
            var parseData = JSON.parse(data);
            if (parseData != "" && parseData != null && parseData != undefined) {
                if (parseData.length == 1) {// 如果只有一个重大危险源视频，则只显示'1'按钮
                    $('#sp1').show();
                } else if (parseData.length == 2) {// 如果有两个重大危险源视频，则显示'1,2'按钮
                    $('#sp1').show();
                    $('#sp2').show();
                } else if (parseData.length == 3) {// 如果有三个重大危险源视频，则显示'1,2,3'按钮
                    $('#sp1').show();
                    $('#sp2').show();
                    $('#sp3').show();
                } else if (parseData.length > 3) {// 如果超过三个重大危险源视频，则显示全部按钮
                    $('#sp1').show();
                    $('#sp2').show();
                    $('#sp3').show();
                    $('#spmore').show();
                }

                // 循环将url放入spUrls中，方便按钮点击事件获取对应的url
                $.each(parseData, function (index, el) {
                    spUrls.push(el.url);
                })
                // 显示视频图像
                showVideo(spUrls[0]);
            } else {
                $('.spjk_btns').hide();
                $('#playlive').append('<img src="${ctxStatic}/model/images/hgqy/nozdwxysp.png" style="height:300px;" />');
            }
		}
	})
});

// 显示视频播放器
function showVideo(url) {
	var videoObject = {
		container: '#playlive',//“#”代表容器的ID，“.”或“”代表容器的class
		variable: 'player',//该属性必需设置，值等于下面的new chplayer()的对象
		autoplay: false, //是否自动播放
		video:url //视频地址
	};
	var player=new ckplayer(videoObject);
}

// '1,2,3,更多'按钮点击触发事件
$('#sp1').click(function(){
	showVideo(spUrls[0]);
});

$('#sp2').click(function(){
	showVideo(spUrls[1]);
});

$('#sp3').click(function(){
	showVideo(spUrls[2]);
});

$('#spmore').click(function(){
	openDialogView("查看更多重大危险源视频",ctx+"/zdwxy/spjk/index","90%", "90%","");
});

/* 储罐实时监测 */
//查看详情
function viewxq(tankid){
	openDialogView("查看储罐实时监测信息",ctx+"/zxjkyj/zdwxycg/viewxq/"+tankid,"1000px", "80%","");
}

//查看报警信息
function viewbjxx(tankid,jctype){
	openDialogView("查看储罐报警信息",ctx+"/zxjkyj/zdwxycg/viewbjxx/"+tankid+"/"+jctype,"800px", "400px","");
}

//查看波动图信息
function viewbdt(tankid,jctype){
	openDialogView("查看历史波动图",ctx+"/zxjkyj/zdwxycg/viewbdtindex/"+tankid+"/"+jctype,"90%", "80%","");
}

/* 气体实时监测 */
//查看详情
function viewxq1(cgqbh){
	openDialogView("查看气体实时监测信息",ctx+"/zxjkyj/zdwxyqt/viewxq/"+cgqbh,"900px", "450px","");
}

//查看报警信息
function viewbjxx1(cgqbh){
	openDialogView("查看气体报警信息",ctx+"/zxjkyj/zdwxyqt/viewbjxx/"+cgqbh,"800px", "400px","");
}

//查看波动图信息
function viewbdt1(cgqbh,qttype){
	openDialogView("查看历史波动图",ctx+"/zxjkyj/zdwxyqt/viewbdtindex/"+cgqbh+"/"+qttype,"90%", "80%","");	
}

// ajax获取最新的报警信息
function getBjxx() {
    // 清空 消息列表
    $("#msg__s").html("");

	$.ajax({
		type: 'POST',
		url: ctx + '/zxjkyj/bjxx/getNewBjxx',
		success: function (data) {
			var parseData = JSON.parse(data);
			if (parseData != null && parseData != '' && parseData != undefined) {
                $.each(parseData, function (index, el) {
                    if (el.type == 1) {
                        toastInfo("<span onclick='showBjxx("+el.sigid+",1)' style='cursor: pointer;'>储罐 <span style='color: purple;'><strong>"+el.bh+"</strong></span> 温度报警，请及时处理！</span>");
                    } else if (el.type == 2) {
                        toastInfo("<span onclick='showBjxx("+el.sigid+",2)' style='cursor: pointer;'>储罐 <span style='color: purple;'><strong>"+el.bh+"</strong></span> 压力报警，请及时处理！</span>");
                    } else if (el.type == 3) {
                        toastInfo("<span onclick='showBjxx("+el.sigid+",3)' style='cursor: pointer;'>储罐 <span style='color: purple;'><strong>"+el.bh+"</strong></span> 液位报警，请及时处理！</span>");
                    } else if (el.type == 4) {
                        toastInfo("<span onclick='showBjxx1("+el.sigid+",1)' style='cursor: pointer;'>有毒气体 <span style='color: purple;'><strong>"+el.bh+"</strong></span> 浓度报警，请及时处理！</span>");
                    } else if (el.type == 5) {
                        toastInfo("<span onclick='showBjxx1("+el.sigid+",2)' style='cursor: pointer;'>可燃气体 <span style='color: purple;'><strong>"+el.bh+"</strong></span> 浓度报警，请及时处理！</span>");
                    }
                })
			}
		}
	})
}

// 每隔一分钟请求一次报警数据
timerID = setInterval("getBjxx()", 60000);

// toast提示框
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

// 点击toast弹出框查看对应的储罐报警信息
function showBjxx(sigid, type) {
    openDialogView("查看储罐报警信息",ctx+"/zxjkyj/bjxx/cgbjindex?sigid="+sigid+"&&type="+type,"900px", "450px","");
}

// 点击toast弹出框查看对应的气体报警信息
function showBjxx1(sigid, type) {
    openDialogView("查看气体报警信息",ctx+"/zxjkyj/bjxx/ndbjindex?sigid="+sigid+"&&type="+type,"900px", "450px","");
}
</script>
</body>
</html>
