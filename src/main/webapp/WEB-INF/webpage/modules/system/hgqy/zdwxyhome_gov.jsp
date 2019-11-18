<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>首页</title>
<meta name="decorator" content="default" />
<link href="${ctx}/static/model/css/home/styles.css?v=1.0" rel="stylesheet" />
<script type="text/javascript" src="${ctxStatic}/model/js/home/jquery.liMarquee.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=OTVG2uzqudiovwoHlFIs0P8Z3T7ID4K2"></script>
<script type="text/javascript" src="${ctx}/static/common/initmap.js?v=1.2"></script>
<script type="text/javascript" src="${ctxStatic}/echarts/echarts.js"></script>
<style type="text/css">

body {
	background-color: #FAFAFA
}

.tit {
	padding: 0 10px 0 6px;
	border-left: 3px solid #2571ad;
	line-height: 15px;
	font-size: 15px;
	color: #2571ad;
	font-weight: 600;
}

.lab {
	height: 345px;
	margin-left: 0px;
	margin-right: 0px;
	align-items: unset;
	flex-direction: column;
	background: rgb(255, 255, 255);
}

.margin_ {
	margin-top: 13px;
	margin-bottom: 13px;
	padding-left: 13px;
	padding-right: 13px;
}

#block_item .lab {
	height: 716px
}

.labwds_ {
	padding: 10px;
	background: rgba(33, 150, 243, 0.16862745098039217);
}

.labic_ {
	height: calc(100% - 38px);
}

.ul__ {
	height: 100%;
	padding: 6px 0px;
	overflow-y: scroll;
	overflow-x: hidden;
}

.li__ {
	display: flex;
	align-items: center;
	padding: 10px 10px
}

.tag__ {
	display: flex;
	align-items: center;
}

.tag__ img {
	margin-top: 2px;
	margin-right: 4px;
}

.li__ .news__ {
	margin-right: 10px;
}

.ul__ .li__:nth-child(odd) {
	background: #f7f7f7;
}

.ul__ .li__:nth-child(even) {
	background: #fff;
}

.line_ {
	overflow: hidden;
	text-overflow: ellipsis;
	display: -webkit-box;
	-webkit-line-clamp: 1;
	-webkit-box-orient: vertical;
}

.str_wrap {
	overflow: hidden; //
	zoom: 1;
	width: 100%;
	font-size: 13px;
	line-height: 16px;
	position: relative;
	-moz-user-select: none;
	-khtml-user-select: none;
	user-select: none;
	background: #fff;
	white-space: nowrap;
}

.str_move {
	white-space: nowrap;
	position: absolute;
	top: 0;
	left: 0;
	cursor: move;
	width: 100%;
}

#fxdlb_ctt {
	margin: 10px;
}

#fxdlb_ctt .list_h,#fxdlb_ctt .list_b,#fxdlb_ctt .list_b>div.list_li {
	display: flex;
}

#fxdlb_ctt .list_h>div {
	text-align: center;
	padding: 6px;
}

#fxdlb_ctt .list_b>div.list_li>div {
	text-align: center;
	padding: 8px 6px;
}

#fxdlb_ctt .list_h {
	background: #2d75ad;
}

#fxdlb_ctt .list_b>div.list_li:hover,#cqmnt_ctt .camera_l:hover,.userInfo .xfq:hover
	{
	cursor: pointer;
}

.w25 {
	width: 25%
}

.w20 {
	width: 20%
}

.w15 {
	width: 15%
}

.w35 {
	width: 35%
}

.w30 {
	width: 30%
}

#fxdlb_ctt .list_b {
	flex-direction: column;
	overflow-y: scroll;
	/*height: 252px*/
}

#fxdlb_ctt .list_b>div.list_li:nth-child(odd) {
	width: 100%;
	border-bottom: .1px solid #dedede;
	background: #f7f7f7;
	color: #444;
	flex-shrink: 0;
}

#fxdlb_ctt .list_b>div.list_li:nth-child(even) {
	width: 100%;
	border-bottom: .1px solid #dedede;
	background: #ffffff;
	color: #444;
	flex-shrink: 0;
}
/*
.line {
	overflow: hidden;
	text-overflow: ellipsis;
	display: -webkit-box;
	-webkit-line-clamp: 1;
	-webkit-box-orient: vertical;
}*/

#fxdlb_ctt .list_b>div.list_li>div>div {
	margin: 0px 13% 0 22%;
	color: #fff;
}

#fxdlb_ctt .list_b>div.list_li>div>div.clrd {
	color: #333;
}

.bgr {
	background: red
}

.bgo {
	background: orange
}
.bggr {
	background: #45a549;
}
.bgy {
	background: yellow
}

.bgb {
	background: #2196f3
}

.lab {
	color: #333;
}


/* operate */
*{margin:0;padding:0;list-style-type:none;}
a,img{border:0;}
.none{display:none}
.operate{margin: 10px; border: 1px solid #eaeaea;height: calc(100% - 20px);}
.operate h3{font-family:"Microsoft YaHei",微软雅黑;font-size:16px;background:#f7f7f7;height:43px;line-height:43px;padding-left:12px;}
.operate li{border-bottom:1px dotted #d2d2d2;display:inline-block;width:100%;position:relative;min-height:43px;_height:43px;z-index:10;zoom:1 }
.operate li h4{cursor:pointer;background:url(${ctx}/static/model/vd2/img/bg3.png) no-repeat calc(100% - 15px) 18px;padding-left:20px;text-decoration:none;font-size:14px;color:#555;display:block; line-height:43px;font-weight:normal;}
.operate li.selected h4{background-position:calc(100% - 15px) -37px;}
.operate li .list-item{padding:5px 0;position:relative;zoom:1 }
.operate li .list-item p{padding-left:0px;margin: 0 0 4px;}
.operate li .list-item p.on a{color:#8caf00;font-weight:bold;}
.operate li .list-item p a{background:url(${ctx}/static/model/vd2/img/bg1.png) no-repeat 22px 12px;color:#333333;display:block;height:32px;line-height:32px;margin:0 -1px 0 1px;padding-left:40px;padding-right:10px; position:relative;text-decoration:none;font-size:12px;}
.operate li .list-item p:hover a{background-color:#8caf00;color:#fff;text-decoration:none;background-position:22px -14px;}
.operate li .list-item p a.selectThis{background-color:#8caf00;color:#fff;text-decoration:none;background-position:22px -14px;}

.operate li .list-item p a.line {
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-line-clamp: 1;
  -webkit-box-orient: vertical;
}
</style>
</head>
<body>
	<div class="">
		<div class="col-lg-12 col-md-12 col-xs-12 mgb10" style="padding: 0px;">
			<div class="col-lg-3 col-md-3 col-xs-12 mgt10 mtb8" style="display: flex;padding:0px;">
				<div class="col-lg-12 col-md-12 col-xs-12 margin_" id="block_item">
					<div class="lab">
						<div class="labwds_" style="">
							<span class="tit">企业分级列表</span>
						</div>
						<div class="labic_ list__">
							<!-- <div class="ul__ dowebok" style="overflow:hidden">
								<c:forEach
									items="[1,1,1,1,1,1,1,1]"
									var="s">
									<div class="li__">
										<div class="tag__">
											<img src="${ctxStatic}/model/images/home/skin_/new.png" />
										</div>
										<div class="line_ news__">
											实时报警预警信息实时报警预警信息,实时报警预警信息实时报警预警信息</div>
										<div class="date__">8/14</div>
									</div>
								</c:forEach>
							</div> -->
	
		<div class="operate" style="overflow-x: hidden">
		<!-- <h3>常见问题</h3> -->
		<ul id="J_navlist">
			<li class="selected">
				<h4>一级</h4>
				<div class="list-item">
					<p><a class="selectThis" href="#">下载游戏</a></p>
					<p><a href="#" class="line" title="">运行与更新</a></p>
				</div>
			</li>
			<li>
				<h4>二级</h4>
				<div class="list-item none">
					<p><a href="#" class="line" title="">帐号问题</a></p>
					<p><a href="#" class="line" title="">密码问题</a></p>
					<p><a href="#" class="line" title="">其他登录问题其他登录问题其他登录问题</a></p>
				</div>
			</li>
			<li>
				<h4>三级</h4>
				<div class="list-item none">
					<p><a href="#" class="line" title="">游戏卡充值</a></p>
					<p><a href="#" class="line" title="">第三方充值</a></p>
					<p><a href="#" class="line" title="">会员充值</a></p>
					<p><a href="#" class="line" title="">声讯充值</a></p>
					<p><a href="#" class="line" title="">通宝充值</a></p>
					<p><a href="#" class="line" title="">充值兑换</a></p>
					<p><a href="#" class="line" title="">充值到账</a></p>
					<p><a href="#" class="line" title="">游戏卡充值</a></p>
					<p><a href="#" class="line" title="">第三方充值</a></p>
					<p><a href="#" class="line" title="">会员充值</a></p>
					<p><a href="#" class="line" title="">声讯充值</a></p>
					<p><a href="#" class="line" title="">通宝充值</a></p>
					<p><a href="#" class="line" title="">充值兑换</a></p>
					<p><a href="#" class="line" title="">充值到账</a></p>
					<p><a href="#" class="line" title="">游戏卡充值</a></p>
					<p><a href="#" class="line" title="">第三方充值</a></p>
					<p><a href="#" class="line" title="">会员充值</a></p>
					<p><a href="#" class="line" title="">声讯充值</a></p>
					<p><a href="#" class="line" title="">通宝充值</a></p>
					<p><a href="#" class="line" title="">充值兑换</a></p>
					<p><a href="#" class="line" title="">充值到账</a></p>
				</div>
			</li>
			<li>
				<h4>四级</h4>
				<div class="list-item none">
					<p><a href="#" class="line" title="">银子获取</a></p>
					<p><a href="#" class="line" title="">银子兑换与转账</a></p>
					<p><a href="#" class="line" title="">银子使用</a></p>
					<p><a href="#" class="line" title="">积分相关问题</a></p>
				</div>
			</li>
		</ul>
	</div>
					
						</div>
					</div>
				</div>
			</div>
			
			<div class="col-lg-9 col-md-9 col-xs-12 mgt10 mtb8" id="block_items"
				style="padding:0px;">
				<div class="col-lg-6 col-md-6 col-xs-12 margin_">
					<div class="lab">
						<div class="labwds_" style="">
							<span class="tit">储罐温度实时监测</span>
						</div>
						<div class="labic_">
							<div id="fxdlb_ctt" class="__aa" style="border: 1px solid #ffe0de;">
								<table id="ssjc_wl_dg"></table>
							</div>
						</div>
					</div>
				</div>
				<div class="col-lg-6 col-md-6 col-xs-12 margin_">
					<div class="lab">
						<div class="labwds_" style="">
							<span class="tit">储罐压力实时监测</span>
						</div>
						<div class="labic_">
							<div id="fxdlb_ctt" class="__aa" style="border: 1px solid #ffd3a6;">
								<table id="ssjc_wl_dg2"></table>
							</div>
						</div>
					</div>
				</div>
			

			<div class="col-lg-6 col-md-6 col-xs-12 margin_">
				<div class="lab">
					<div class="labwds_" style="">
						<span class="tit">储罐液位实时监测</span>
					</div>
					<div class="labic_">
						<div id="fxdlb_ctt" class="__aa" style="border: 1px solid #ffefbe;">
							<table id="ssjc_wl_dg3"></table>
						</div>
					</div>
				</div>
			</div>

			<div class="col-lg-6 col-md-6 col-xs-12 margin_">
				<div class="lab">
					<div class="labwds_" style="">
						<span class="tit">有毒/可燃气体实时监测</span>
					</div>
					<div class="labic_">
						<div id="fxdlb_ctt" class="__aa" style="border: 1px solid #daeeff;">
							<table id="ssjc_qt_dg"></table>
						</div>
					</div>
				</div>
			</div>
		</div>
		
	</div>
	</div>


	<script type="text/javascript">
		$(function() {
			$('.dowebok').liMarquee({
				direction : 'up',
				scrollamount : 10
			});
		});

		$(window).ready(function() {
			$(".__aa").height($(".__aa").parent().height()-26);
			
			$(".__aa .list_b").height($(".__aa").height()-$(".__aa .list_h").height());
			
			
			/*
			 * 菜单
			 */
			$("#cqmnt_ctt .vw_ic, .videoWindow .vw_cls").click(function(ev){
				// 根据点击按钮， 获取相应内容
				if($(".videoWindow").hasClass("show_")){
					$(".videoWindow").hide();
					$(".videoWindow").removeClass("show_");
				}else{
					$(".videoWindow").show();
					$(".videoWindow").addClass("show_");
				}
			})
			$(".menuul .menu1").click(function(){
				$(".menu1ul .menu2ul").hide();
				if($(this).parent().hasClass("menu1ul")){
					if($(this).hasClass("show_")){
						$(this).removeClass("show_");
					}else{
						$(".menu1ul .menu1.show_").removeClass("show_");
						$(this).siblings("ul").toggle();
						$(this).addClass("show_");
					}
				}else{
					$(".menu1ul .menu1.show_").removeClass("show_");
					$(".menuul li.chos_").removeClass("chos_");
					$(this).addClass("chos_");
				}
			})
			$(".menuul .menu2").click(function(){
				$(".menuul li.chos_").removeClass("chos_");
				$(this).addClass("chos_");
			})
			
		})

		$(window).resize(function() {
		})

		function initBlocks() {

		}
		
		
		$(function(){   
			dg=$('#ssjc_wl_dg').datagrid({    
			nowrap:false,
			method: "post",
		    url:ctx+'/zxjkyj/wdcgq/list',
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
		        {field:'wh',title:'储罐位号',sortable:false,width:80,align:'center',
		        	formatter : function(value, row, index) {
		     		if (value==null||value=='')
		     			return '/';
		     		else
		     			return value;
		     	}},    
		        {field:'wl',title:'存储物料名称',sortable:false,width:100,align:'center'},
		        {field:'lx',title:'储罐类型',sortable:false,width:100,align:'center',
		         	formatter : function(value, row, index) {
		            	if(value=='1') return value='立式圆筒形储罐';
		            	if(value=='2') return value='卧式圆筒形储罐';
						if(value=='3') return value='球形储罐';
						if(value=='4') return value='其他储罐'; 
						if(value==null) return value='仓库';
		        	}      
		        },
		        {field:'sswd',title:'实时温度(℃)',sortable:false,width:80,align:'center',
		     		formatter : function(value, row, index) {
		         		if (value==null)
		         			return '/';
		         		else
		         			return value.toFixed(2);
		         	}
		     	},
		     	{field:'cjsj',title:'更新时间',sortable:false,width:100,align:'center',
		         	formatter : function(value) {
		         		var datetime = new Date(value);
		                return datetime.format("yyyy-MM-dd hh:mm:ss");
		     	}}
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
		  
			dg2=$('#ssjc_wl_dg2').datagrid({    
				nowrap:false,
				method: "post",
			    url:ctx+'/zxjkyj/ylcgq/list',
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
			        {field:'wh',title:'储罐位号',sortable:false,width:80,align:'center',
			        	formatter : function(value, row, index) {
			     		if (value==null||value=='')
			     			return '/';
			     		else
			     			return value;
			     	}},    
			        {field:'wl',title:'存储物料名称',sortable:false,width:100,align:'center'},
			        {field:'lx',title:'储罐类型',sortable:false,width:100,align:'center',
			         	formatter : function(value, row, index) {
			            	if(value=='1') return value='立式圆筒形储罐';
			            	if(value=='2') return value='卧式圆筒形储罐';
							if(value=='3') return value='球形储罐';
							if(value=='4') return value='其他储罐'; 
							if(value==null) return value='仓库';
			        	}      
			        },
			        {field:'ssyl',title:'实时压力(kPa)',sortable:false,width:80,align:'center',
			     		formatter : function(value, row, index) {
			         		if (value==null)
			         			return '/';
			         		else
			         			return value.toFixed(2);
			         	}
			     	},
			     	{field:'cjsj',title:'更新时间',sortable:false,width:100,align:'center',
			         	formatter : function(value) {
			         		var datetime = new Date(value);
			                return datetime.format("yyyy-MM-dd hh:mm:ss");
			     	}}
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
			
			
			dg3=$('#ssjc_wl_dg3').datagrid({    
				nowrap:false,
				method: "post",
			    url:ctx+'/zxjkyj/ywcgq/list',
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
			        {field:'wh',title:'储罐位号',sortable:false,width:80,align:'center',
			        	formatter : function(value, row, index) {
			     		if (value==null||value=='')
			     			return '/';
			     		else
			     			return value;
			     	}},    
			        {field:'wl',title:'存储物料名称',sortable:false,width:100,align:'center'},
			        {field:'lx',title:'储罐类型',sortable:false,width:100,align:'center',
			         	formatter : function(value, row, index) {
			            	if(value=='1') return value='立式圆筒形储罐';
			            	if(value=='2') return value='卧式圆筒形储罐';
							if(value=='3') return value='球形储罐';
							if(value=='4') return value='其他储罐'; 
							if(value==null) return value='仓库';
			        	}      
			        },
			        {field:'ssyw',title:'实时液位(cm)',sortable:false,width:80,align:'center',
			     		formatter : function(value, row, index) {
			         		if (value==null)
			         			return '/';
			         		else
			         			return value.toFixed(2);
			         	}
			     	},
			     	{field:'cjsj',title:'更新时间',sortable:false,width:100,align:'center',
			         	formatter : function(value) {
			         		var datetime = new Date(value);
			                return datetime.format("yyyy-MM-dd hh:mm:ss");
			     	}}
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
			
			dg4=$('#ssjc_qt_dg').datagrid({    
				nowrap:false,
				method: "post",
			    url:ctx+'/zxjkyj/krqtcgq/list',
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
					{field:'krqtmc',title:'可燃气体名称',sortable:false,width:100,align:'center'},
			        {field:'krqtssnd',title:'实时浓度(% LEL)',sortable:false,width:80,align:'center',
			     		formatter : function(value, row, index) {
			         		if (value==null)
			         			return '/';
			         		else
			         			return value.toFixed(2);
			         	}
			     	},
			     	{field:'cjsj',title:'更新时间',sortable:false,width:100,align:'center',
			         	formatter : function(value) {
			         		var datetime = new Date(value);
			                return datetime.format("yyyy-MM-dd hh:mm:ss");
			     	}}
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
			
			dg5=$('#ssjc_qt_dg2').datagrid({    
				nowrap:false,
				method: "post",
			    url:ctx+'/zxjkyj/ydqtcgq/list',
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
			        {field:'ydqtmc',title:'有毒气体名称',sortable:false,width:100,align:'center'},
			        {field:'ydqtssnd',title:'有毒气体实时浓度',sortable:false,width:60,align:'center',
						formatter : function(value, row, index) {
							if (value==null)
								return '/';
							else
								return value.toFixed(2);
						}
			     	},
			     	{field:'cjsj',title:'更新时间',sortable:false,width:100,align:'center',
			         	formatter : function(value) {
			         		var datetime = new Date(value);
			                return datetime.format("yyyy-MM-dd hh:mm:ss");
			     	}}
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
		});
	</script>
	
	
<script type="text/javascript">
// 企业分级列表相关
navList(12);

function navList(id) {
    var $obj = $("#J_navlist");
    
    $obj.find("h4").click(function () {
        var $div = $(this).siblings(".list-item");
        if ($(this).parent().hasClass("selected")) {
            $div.slideUp(600);
            $(this).parent().removeClass("selected");
        }
        if ($div.is(":hidden")) {
            $("#J_navlist li").find(".list-item").slideUp(400);
            $("#J_navlist li").removeClass("selected");
            $(this).parent().addClass("selected");
            $div.slideDown(400);

        } else {
            $div.slideUp(400);
        }
    });
    
    $obj.find("p").click(function () {
    	$obj.find("a").removeClass("selectThis");
    	$(this).find("a").addClass("selectThis");
    });
}
</script>
</body>
</html>