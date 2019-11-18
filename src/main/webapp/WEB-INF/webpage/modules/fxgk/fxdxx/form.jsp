<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>添加风险信息</title>
<meta name="decorator" content="default" />
<%@ include file="/WEB-INF/webpage/include/kindeditor.jsp"%>
<script type="text/javascript" src="${ctx}/static/model/js/fxgk/fxpg/data.js?v=1.1"></script>
<style type="text/css">

.selected{
    border-color:red;
    background-color: rgb(221, 221, 221);
}

ul.icon-selector-ul li {
    list-style-type: none;
    float: left;
    cursor: pointer;
    margin: 2px;
    width: auto;
    height: auto;
    border-width: 1px;
    border-style: solid;
    }

	.BMap_cpyCtrl  
    {  
        display:none;   
    }  
    .anchorBL{  
        display:none;   
    }  
    .ball {
    width: 10px;
    height: 10px;
    background: red;
    border-radius: 50%;
    position: absolute;
	} 
	.wrap{
    background: #ccc;
    position: relative;
    width：800px;
	}
	.tipdiv{
	width：100%;
	}
	.fxdxxtip{
	width:600px;
    color:#ffffff;
    background: rgba(103,103,103,0.9);
    position:absolute;
    top:10px;
    border-radius: 20px;
    padding:10px 10px 5px 0px;
    z-index:1200000;
    display: none;
    text-align: left;
    font-size: 5px;
	}
	.red{
	color: red;
	}
	.orange{
	color: orange;
	}
	.yellow{
	color: yellow;
	}
	.blue{
	color: blue;
	}
	.width-14{
		width: 14%;
	}
	.width-86{
		width: 86%;
		padding-bottom: 5px;
	}
	.width-14 label{
		color: #d0ebff;
	}
</style>
</head>
<body>


	<form id="inputForm" action="${ctx}/fxgk/fxxx/${action}"
		method="post" class="form-horizontal">
		<table
			class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
			<tbody>
				<c:if test="${usertype != 1}">
                  <td class="width-20 active"><label class="pull-right">企业名称：</label></td>
                  <td class="width-80" colspan="3"><input type="text" id="ID1" name="ID1" style="width: 100%;height: 30px;" class="easyui-combobox" value="${sgfx.id1 }"
                     <c:if test="${action eq 'updateSub'}">readonly="true"</c:if> data-options="required:'true',panelHeight:'200px',editable:true ,valueField: 'id',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson' " /></td>
               </c:if>
				
				<tr>
					<td  class="width-20 active"><label class="pull-right">负责人：</label></td>
					<td  class="width-30"  >
						<input type="text" id="m13" name="M13" style="width: 100%;height: 30px;" class="easyui-textbox" value="${sgfx.m13 }"  data-options=" " />
					</td>
					<td  class="width-20 active"><label class="pull-right">联系方式：</label></td>
					<td  class="width-30"  >
						<input type="text" id="m14" name="M14" validtype="mobileAndTel" style="width: 100%;height: 30px;" class="easyui-textbox" value="${sgfx.m14 }"  data-options=" " />
					</td>
				</tr>
				</tr>
					<tr>
					<td class="width-20 active"><label class="pull-right">风险点名称：</label></td>
					<td class="width-30" >
						<input id="M1" name="M1" style="width: 84%;height: 30px;" class="easyui-combogrid" data-options="panelHeight:'308px'" value="${sgfx.m1 }" />
						<span  class="btn btn-success btn-sm  "  style="width: 15%;height: 30px;" data-options="validType:'length[0,50]'" onclick="search()"><i class="fa fa-search"></i> 查询</span>
					</td>
					<td class="swidth-20 active"><label class="pull-right">编号：</label></td>
					<td class="width-30"><input  style="width: 100%;height: 30px;"class="easyui-textbox" name="M18" value="${sgfx.m18}"  data-options="required:'true',validType:'length[0,100]'" /></td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">风险分类：</label></td>
					<td class="width30"><input class="easyui-combobox" style="width: 100%;height: 30px;" id="M2" name="M2" value="${sgfx.m2 }"  data-options="required:'true',validType:'length[0,50]',
								editable:true ,panelHeight:'200px',valueField: 'text',textField: 'text',url:'${ctx}/tcode/dict/fxfl'"/></td>
					<td class="width-20 active"><label class="pull-right">是否重大危险源：</label></td>
					<td class="width-30"><input class="easyui-combobox" style="width: 100%;height: 30px;" name="M23" id="M23" value="${sgfx.m23 }"  data-options="required:'true',
								 editable:false,panelHeight:'auto',data: [
								        {value:'1',text:'是'},
								        {value:'0',text:'否'}]"/>
					</td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">选择风险分级方式：</label></td>
					<td>
						<label><input id="radio1" name="radio1" type="radio" value="1" checked/>LEC </label>
						<label><input id="radio3" name="radio1" type="radio" value="3"/>LS </label>
					</td>
				</tr>

				<!--  LEC分级方式    -->
				<tr id="lectr">
					<td class="width-20 active"><label class="pull-right" style="color: red;">可能性(L)：</label></td>
					<td class="width-30"><input name="aprobability" id="aprobability" style="width: 100%;height: 30px;"
												class="easyui-combobox"
												value="${sgfx.aprobability }"/></td>
					<td class="width-20 active"><label class="pull-right" style="color: red;">暴露频率(E)：</label></td>
					<td class="width-30"><input name="exposefrequency" id="exposefrequency" style="width: 100%;height: 30px;"
												class="easyui-combobox"
												value="${sgfx.exposefrequency }"/></td>
				</tr>
				<tr id="lectr1">
					<td class="width-20 active"><label class="pull-right" style="color: red;">严重度(C)：</label></td>
					<td class="width-30" colspan="3"><input name="aseverity" id="aseverity" style="width: 100%;height: 30px;"
															class="easyui-combobox"
															value="${sgfx.aseverity }"/></td>
				</tr>

				<!--  LS分级方式    -->
				<tr id="lstr">
					<td class="width-20 active"><label class="pull-right" style="color: red">事故发生的可能性（L）：</label></td>
					<td class="width-30">
						<input name="aprobability2" id="aprobability2" style="width: 100%;height: 30px;" class="easyui-combobox"
							   value="${sgfx.aprobability }" data-options="editable:false ,panelHeight:'auto',data: [
								        {value:'1',text:'1、极不可能'},
								        {value:'2',text:'2、很不可能，可以设想'},
								        {value:'3',text:'3、可能性小，完全意外'},
								        {value:'4',text:'4、可能但不经常'},
								        {value:'5',text:'5、很可能'}] "/>
					</td>
					<td class="width-20 active"><label class="pull-right" style="color: red">事故后果严重性（S）：</label></td>
					<td class="width-30">
						<input name="aseverity2" id="aseverity2" style="width: 100%;height: 30px;" class="easyui-combobox"
							   value="${sgfx.aseverity }" data-options="editable:false ,panelHeight:'auto',data: [
								        {value:'1',text:'1、较轻，导致轻伤，财产损失较小'},
								        {value:'2',text:'2、较重，导致重伤，财产损失较大'},
								        {value:'3',text:'3、严重，导致一人死亡或多人重伤，财产损失很大'},
								        {value:'4',text:'4、灾难，导致多人死亡或巨大财产损失'}] "/>
					</td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right" style="color: red;">风险分级：</label></td>
					<td class="width-30" id="fenxi"><input class="easyui-combobox" readonly="readonly"
														   style="width: 100%;height: 30px;" name="M9" id="M9"
														   value="${sgfx.m9 }" data-options="required:'true',
								 editable:false,panelHeight:'auto',data: [
								        {value:'1',text:'红'},
								        {value:'2',text:'橙'},
								        {value:'3',text:'黄'},
								        {value:'4',text:'蓝'}]"/>
					</td>
				</tr>

				 <tr>
					<td class="width-20 active"><label class="pull-right">场地名称：(备注:一车间、二车间、罐区)</label></td>
					<td class="width-80"  colspan="3">
						<input  name="areaname" style="width: 100%;height: 30px;" class="easyui-textbox" value="${sgfx.areaname }"  data-options="validType:['length[0,100]'] " />
					</td>
				</tr>
            <tr>
					<td class="width-20 active"><label class="pull-right">行业：</label></td>
					<td class="width-80" colspan="3"><input  style="width: 100%;height: 30px;" id="sgfx_fxxx_m3" name="M3" class="easyui-combobox" value="${sgfx.m3 }"  data-options="
								required:'true',panelHeight:'auto',editable:true,validType:['length[0,50]'],data: [
								        {value:'纺织',text:'纺织'},
								        {value:'机械',text:'机械'},
								        {value:'建材',text:'建材'},
								        {value:'轻工',text:'轻工'},
								        {value:'冶金',text:'冶金'},
								        {value:'有色',text:'有色'},
								        {value:'化工',text:'化工'} ]" /></td>
					
				</tr>
				<tr>
				<td class="width-20 active"><label class="pull-right">行业类别：</label></td>
					<td class="width-80" colspan="3"><input style="width: 100%;height: 30px;" class="easyui-combobox" id="sgfx_fxxx_m4" name="M4" value="${sgfx.m4 }"  data-options="required:'true',
								validType:['length[0,50]'],panelHeight:'150',editable:true , valueField: 'text',textField: 'text' "/></td>
				</tr>
					<tr>
					<td class="width-20 active"><label class="pull-right">工段：</label></td>
					<td class="width-80" colspan="3"><input style="width: 100%;height: 30px;" class="easyui-combobox" id="sgfx_fxxx_m5" name="M5" value="${sgfx.m5 }"  data-options="required:'true',
								 validType:['length[0,50]'],panelHeight:'150',editable:true , valueField: 'text',textField: 'text' "/></td>
					
				</tr>
				
				<tr>
				<td class="width-20 active"><label class="pull-right">部位：</label></td>
				<td class="width-80" colspan="3"><input  style="width: 100%;height: 30px;"class="easyui-combobox" id="sgfx_fxxx_m6" name="M6" value="${sgfx.m6 }"  data-options="required:'true',
								validType:['length[0,50]'],panelHeight:'150',editable:true ,valueField: 'text',textField: 'text'"/></td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">易发生事故类型 ：</label></td>
					<td class="width-80" colspan="3"> 
					<input style="width: 100%;height: 30px;" class="easyui-combobox" id="sgfx_fxxx_m8"  name="M8" value="${sgfx.m8 }"  data-options="multiple:'true',required:'true',
								validType:['length[0,250]'],panelHeight:'150',editable:true "/>	
	
				</tr>
            <tr>
               <td class="width-20 active"><label class="pull-right">职业病危害因素类别：</label></td>
               <td class="width-30"><input class="easyui-combobox" style="width: 100%;height: 30px;"
                     data-options="panelHeight:'auto' ,data: [
                                {value:'粉尘',text:'粉尘'},
                                {value:'化学因素',text:'化学因素'},
                                {value:'物理因素',text:'物理因素'},
                                {value:'放射性因素',text:'放射性因素'},
                                {value:'生物因素',text:'生物因素'},
                                {value:'其他因素',text:'其他因素'} ],
               onSelect: function(rec){
                     $('#occupharm_factor').combobox('clear');
                     var url = '${ctx}/bis/occupharm/zybzd2/'+rec.text;
                    $('#occupharm_factor').combobox('reload', url);
                    }" /></td>
               <td class="width-20 active"><label id="factor_label" class="pull-right">职业病危害因素名称：</label></td>
               <td class="width-30"><input id="occupharm_factor" class="easyui-combobox" style="width: 100%;height: 30px;"
                     data-options="valueField: 'id',textField: 'text' ,panelHeight:'150',
                     onSelect: function(rec){
                     	var wxys=$('#sgfx_fxxx_m7').textbox('getValue');
                     	if(wxys.lastIndexOf('可导致职业病危害因素名称：')>0)
                     		$('#sgfx_fxxx_m7').textbox('setValue',wxys+'、'+rec.text);
                     	else
                     		$('#sgfx_fxxx_m7').textbox('setValue',wxys+'  可导致职业病危害因素名称：'+rec.text);
                    }" /></td>
            </tr>
            <tr>
					<td class="width-20 active"><label class="pull-right">主要危险有害性：</label></td>
					<td class="width-80" colspan="3"> <input  id="sgfx_fxxx_m7" name="M7"   style="width: 100%;height: 80px;" value="${sgfx.m7 }" class="easyui-textbox" data-options="multiline:'true',validType:['length[0,500]'] "></input>
					</td>				
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">管控措施 ：</label></td>
					<td class="width-80" colspan="3"> 
					<input id="sgfx_fxxx_m10" name="M10"  style="width: 100%;height: 80px;" value="${sgfx.m10 }" class="easyui-textbox" data-options="multiline:'true',alidType:['length[0,500]'] " ></input>
					</td>				
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">依据 ：</label></td>
					<td class="width-80" colspan="3"> 
					<input id="sgfx_fxxx_m12"  name="M12" style="width: 100%;height: 80px;" value="${sgfx.m12 }"class="easyui-textbox" data-options="multiline:'true',validType:['length[0,500]'] " ></input></td>				
				</tr>
				 <tr>
					<td class="width-20 active"><label class="pull-right">应急处置对策：</label></td>
					<td class="width-80" colspan="3">
					<div><input id="sgfx_fxxx_m11" class="easyui-textbox" name="M11"   value="${sgfx.m11 }"style="width: 100%;height: 80px;"  data-options="multiline:'true' " ></input></div>
					<input id="sgfx_fxxx_m11Text"  style="display: none" ></input>
					</td>				
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">管控层级：</label></td>
					<td class="width-80" colspan="3">
						<div>
							<input id="M15" name="M15" class="easyui-combobox" value="${sgfx.m15 }" style="width: 100%;height: 30px;" data-options="validType:['length[0,500]'],editable:false,multiple:true,panelHeight:'auto', data: [
												        {value:'公司',text:'公司'},
												        {value:'部门',text:'部门'},
												        {value:'班组',text:'班组'},
												        {value:'岗位',text:'岗位'}]"/>
						</div>
					</td>

				</tr>
		
				<tr>
					<td colspan="4" style="text-align: center;color: dodgerblue;font-size: 14px;"><strong>风险分级管控责任单位及责任人</strong></td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">公司：</label></td>
					<td class="width-30">
						<input style="width: 100%;height: 30px;" name="fjgk1" class="easyui-textbox " value="${sgfx.fjgk1 }" data-options="validType:'length[0,50]'"/></td>
					<td class="width-20 active"><label class="pull-right">部门：</label></td>
					<td class="width-30">
						<input style="width: 100%;height: 30px;" name="fjgk2" class="easyui-textbox " value="${sgfx.fjgk2 }" data-options="validType:'length[0,50]'"/>
					</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">班组：</label></td>
					<td class="width-30">
						<input style="width: 100%;height: 30px;" name="fjgk3" class="easyui-textbox " value="${sgfx.fjgk3 }" data-options="validType:'length[0,50]'"/></td>
					<td class="width-20 active"><label class="pull-right">岗位：</label></td>
					<td class="width-30">
						<input style="width: 100%;height: 30px;" name="fjgk4" class="easyui-textbox " value="${sgfx.fjgk4 }" data-options="validType:'length[0,50]'"/>
					</td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">安全承诺卡：</label></td>
					<td colspan="3">
						<input type="hidden" id="M24" name="M24" value="${sgfx.m24}" />
						<div id="uploader_qy_m24">
							<div id="m24fileList" class="uploader-list" ></div>
							<div id="filePickerm24">选择文件</div>
						</div>
					</td>
				</tr>
				
				  <tr>
					<td class="width-20 active"><label class="pull-right">现场图片：</label></td>
					<td class="width-80" colspan="3">
						<div id="uploader_ws_m16">
					    <div id="m16fileList" class="uploader-list" ></div>
					    <div id="imagePicker">选择图片</div>
					</div> 
					</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">警示标志：</label></td>
					
					<td class="width-80" colspan="3">
                    <div >
					    <div id="m17fileList" class="uploader-list" ></div>
                      <a  href="javascript:void(0)" id="slctfilebtM17" class="btn btn-info btn-sm" data-toggle="tooltip" data-placement="left"  title="选择国标标志"><i class="fa fa-plus"></i> 选择国标标志</a>
                     </div>
						<div id="uploader_ws_m17">
					    <div id="imagePicker2" style="margin-top: 10px">自定义上传</div>
						</div> 
						
					
					</td>
				<tr>
					<td class="width-20 active"><label class="pull-right">平面图坐标：</label></td>
					<td colspan="3" style="height:30px;line-height:30px;">
						<label style="margin-left:19px" >x：</label>
						<input id="bis_map_c_x" name="M19" value="${sgfx.m19 }" class="easyui-textbox" readonly="readonly" style="width:150px;height:30px;"/>
						<label style="margin-left:19px">y：</label> 
						<input id="bis_map_c_y" name="M20" value="${sgfx.m20 }" class="easyui-textbox" readonly="readonly" style="width:150px;height:30px;"/>
						<a class="btn btn-primary" onclick="showpmt( )" style="width:60px;">定位</a></td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">绑定rfid：</label></td>
					<td class="width-30" >
						<input type="text" style="width: 100%;height: 30px;" name="rfid"  class="easyui-textbox" value="${sgfx.rfid}" data-options="validType:['length[0,50]'] "  />
					</td>
					<td class="width-20 active"><label class="pull-right">rfid卡批次代码：</label></td>
					<td class="width-30" >
						<input type="text" style="width: 100%;height: 30px;" name="checkpointadderss"  class="easyui-textbox" value="${sgfx.checkpointadderss }" data-options="validType:['length[0,100]'] "  />
					</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">绑定二维码：</label></td>
					<td class="width-30">
					 	<input type="text" style="width: 100%;height: 30px;" class="easyui-textbox" name="bindcontent" value="${sgfx.bindcontent}"  data-options="validType:['length[0,100]'] "  />
					</td>
				</tr>
             <tr>
               <td class="width-20" colspan="4" style="text-align:center;"><label>
                     <a class='btn btn-success btn-xs' onclick='addbdrn()'>添加巡检内容</a>
                     <a class='btn btn-success btn-xs' onclick='delbdrn()'>删除巡检内容</a>
                  </label>
                  </td>
            </tr>
				
				<!-- 隐藏字段 -->
            <!-- 序列化绑定内容ids -->
                <input type="hidden" id="bdnrids" name="bdnrids"/>
				<input  type="hidden" id="usetype" name="usetype" value="${sgfx.usetype }"/>
			<c:if test="${action eq 'updateSub'}">
				<input type="hidden"  id="ID" name="ID" value="${sgfx.id}" />
			</c:if>
				<input type="hidden"  id="depid" name="depid" value="${sgfx.depid}" />
				<input type="hidden" name="S1" value="<fmt:formatDate value="${sgfx.s1}" pattern="yyyy-MM-dd HH:mm:ss"  />" />
			<tbody>
		</table>
         <div id="bdnr">
               <table id="fxgk_bdnr_dg" style="height: 350px;"></table>
            </div>
 		<div id="select_dlg" style="display:none"  >
		  <ul id="fxxx-selects-ul" class="icon-selector-ul"></ul>
		</div>
		
		<div id="xfss_dlg" style="background-color:#F4F4F4;padding:10px 20px;text-align:center;display: none;" >               
            <div class="ftitle" style="color: red;">请在平面图上标注设施位置!</div>
        	<div id="xfss_dlg_map" class="wrap" style="margin:0 auto;width:800px"><img style="width:800px" id="img1" alt=""></img></div>
        </div>
	</form>

	<div class="fxdxxtip"></div>

	<script type="text/javascript">
		var Lscore,Escore,Cscore;
		var usertype= '${usertype}';
		var action='${action}';
		var fxdid;//风险点id
		uploadImgFlag=false;//是否上传图片

		//下拉关联信息
		var hashMap={}; 
		var dg;

		$("#M9").combobox({
			onChange: function (n,o) {
				var level=$("#M9").combobox("getText");
				if(level=='红')
					$('#M15').combobox('setValues','公司,部门,班组,岗位'.split(','));
				if(level=='橙')
					$('#M15').combobox('setValues','部门,班组,岗位'.split(','));
				if(level=='黄')
					$('#M15').combobox('setValues','班组,岗位'.split(','));
				if(level=='蓝')
					$('#M15').combobox('setValues','岗位'.split(','));
			}
		});

		$(function(){
	    for(var index in lecL){
		    if(lecL[index].score=="${sgfx.aprobability }"){
			    Lscore=lecL[index].score;
		    }
	    }
	    for(var index in lecE){
		    if(lecE[index].score=="${sgfx.exposefrequency }"){
		 	    Escore=lecE[index].score;
		    }
	    }
	    for(var index in lecC){
		    if(lecC[index].score=="${sgfx.aseverity }"){
			    Cscore=lecC[index].score;
		    }
	    }			

	    $("#aprobability").combobox({
		    editable: false,
		    panelHeight:'100',
		    valueField: 'score',
		    textField: 'possibility',
		    data : lecL,
		    onSelect : function(rec){
		 	    Lscore=rec.score;
			    if(Escore&&Cscore){
				    calculation(Lscore,Escore,Cscore);
			    }
		    }
	    });
	    
	    $('#exposefrequency').combobox({
		    editable: false,
		    panelHeight:'100',
		    valueField: 'score',
		    textField: 'frequency',
		    data : lecE,
		    onSelect : function(rec){
			    Escore=rec.score;
			    if(Lscore&&Cscore){
				    calculation(Lscore,Escore,Cscore);
			    }
		    }
	    });
	    
	    $('#aseverity').combobox({
		    editable: false,
		    panelHeight:'100',
		    valueField: 'score',
		    textField: 'consequence',
		    data : lecC,
		    onSelect : function(rec){
			    Cscore=rec.score;
			    if(Lscore&&Escore){
				    calculation(Lscore,Escore,Cscore);
			    }
		    }
	    });

			/*************************************       风险分级     ****************************************/
			if ('${action}' == 'createSub') {
				$('#lstr').hide();
			}

			if ('${action}' == 'updateSub') {
				var radio1 = '${radio1}';
				if (radio1 == 1) {
					$('#lectr').show();
					$('#lectr1').show();
					$('#lstr').hide();

				} else if (radio1 == 3) {
					$("#radio3").attr("checked", "checked");
					$('#lstr').show();
					$('#lectr').hide();
					$('#lectr1').hide();
				}
			}

			$('#radio3').click(function () {
				$('#lstr').show();
				$('#lectr').hide();
				$('#lectr1').hide();
				$.parser.parse($('#lstr'));
				$('#M9').combobox("setValue", "");
				$("#fenxi").find(".textbox-text").css("background","white");
				Lscore=0;
				Escore=0;
				Cscore=0;
			})

			$('#radio1').click(function () {
				$('#lectr').show();
				$('#lectr1').show();
				$('#lstr').hide()
				$.parser.parse($('#lectr'));
				$.parser.parse($('#lectr1'));
				$('#M9').combobox("setValue", "");
				$("#fenxi").find(".textbox-text").css("background","white");
				Lscore=0;
				Escore=0;
				Cscore=0;
			})

			/*************************************************  LS ********************************************************************/
			$("#aprobability2").combobox({
				onSelect: function (rec) {
					var aseverity = $("#aseverity2").combobox("getValue");
					if (aseverity != "" && aseverity != null) {
						var aprobability = $("#aprobability2").combobox("getValue");
						var product=aseverity * aprobability;
						if (aseverity * aprobability >= 12 && aseverity * aprobability <= 20) {
							$('#M9').combobox("setValue", "1");
							$("#fenxi").find(".textbox-text").css("background", "#FFAEB9");
						} else if (aseverity * aprobability >= 8 && aseverity * aprobability <= 11) {
							$('#M9').combobox("setValue", "2");
							$("#fenxi").find(".textbox-text").css("background", "#FFA500");
						} else if (aprobability >= 4 || aseverity >= 2) {
							$('#M9').combobox("setValue", "3");
							$("#fenxi").find(".textbox-text").css("background", "#FFFF00");
						} else {
							$('#M9').combobox("setValue", "4");
							$("#fenxi").find(".textbox-text").css("background", "#CAE1FF");
						}
						var score=$("#M9").combobox("getText");
						$("#M9").combobox("setText",score+"("+product+")");
					}
				}
			});

			$("#aseverity2").combobox({
				onSelect: function (rec) {
					var aprobability = $("#aprobability2").combobox("getValue");
					if (aprobability != "" && aprobability != null) {
						var aseverity = $("#aseverity2").combobox("getValue");
						var product=aseverity * aprobability;
						if (aseverity * aprobability >= 12 && aseverity * aprobability <= 20) {
							$('#M9').combobox("setValue", "1");
							$("#fenxi").find(".textbox-text").css("background", "#FFAEB9");
						} else if (aseverity * aprobability >= 8 && aseverity * aprobability <= 11) {
							$('#M9').combobox("setValue", "2");
							$("#fenxi").find(".textbox-text").css("background", "#FFA500");
						} else if (aprobability >= 4 || aseverity >= 2) {
							$('#M9').combobox("setValue", "3");
							$("#fenxi").find(".textbox-text").css("background", "#FFFF00");
						} else {
							$('#M9').combobox("setValue", "4");
							$("#fenxi").find(".textbox-text").css("background", "#CAE1FF");
						}
						var score=$("#M9").combobox("getText");
						$("#M9").combobox("setText",score+"("+product+")");
					}
				}
			});

			//存储页面加载时应急处置措施的值
		window.old=$('#sgfx_fxxx_m11').textbox('getValue');
		if('${action}'=='updateSub'){
			var pmtpath='${sgfx.pmt}';
			var url=pmtpath.split('||');
			initImg(url[0]);
		}

		if(usertype=='1'&&'${action}'=='createSub'){
			var pmtpath='${sgfx.pmt}';
			var url=pmtpath.split('||');
			initImg(url[0]);
		}
			
		$('.wrap').click(function(e){
	        var offset=$('.wrap').offset();
	        var top=e.offsetY+"px";
	        var left=e.offsetX+"px";
	        $('.ball').remove();
	        $('.wrap').append('<span class="ball" style="top:'+top+';left:'+left+'"></span>');
	       	//计算x轴长度比例
	       	wh=$("#img1").width();
			var xp=(e.offsetX/wh).toFixed(4);
	       	//计算y轴长度比例
	       	wh=$("#img1").height();
			var yp=(e.offsetY/wh).toFixed(4);			
	        $("#bis_map_c_x").textbox("setValue",xp);//X坐标
			$("#bis_map_c_y").textbox("setValue",yp);//Y坐标
	    });
		
		if('${action}'=="createSub"){
			fxdid="add";
		}
		else
			fxdid='${id1}';
			
			dg=$('#fxgk_bdnr_dg').datagrid({    
				method: "post",
			    url:ctx+'/fxgk/fxxx/xjnrlist/'+fxdid,
			    fit : false,
				fitColumns : true,
				idField : 'ID',
				striped:true,
				pagination:false,
				rownumbers:true,
				nowrap:false,
				pageNumber:1,
				pageSize : 1000,
				pageList : [ 1000, 2000, 3000, 4000, 5000 ],
				striped:true,
			    columns:[[    
			  			{field : 'ID',title : 'id',checkbox : true,width : 50,align : 'center'},  
			  	        {field:'dangerlevel',title:'隐患级别',width:50,
			  	        	formatter : function(value, row, index){
			  	        		if(value=="1") return value='一般';
			  	        		if(value=="2") return value='重大';
			  	        	}
			  	        },
			  	        {field:'checktitle',title:'检查单元',width:80 },  
			  	        {field:'content',title:'检查项目',width:150 },  
			    ]],
			    onLoadSuccess: function(){
			    },
			    onLoadError:function(){
			    	layer.open({title: '提示',offset: 'rb',content: '数据加载中，请耐心等待。',shade: 0 ,time: 2000 });
			    },
				checkOnSelect:true,
				checkOnSelect:true
				});
		})

	    //自动计算值
	    function calculation(Lscore,Escore,Cscore){
	    	var product=Lscore*Escore*Cscore;
	    	for(var index in lecD){
	    		var d=lecD[index];
	    		if(product>=d.min&&product<d.max){
	    			$("#M9").combobox("setValue",d.level);
	    			var score=$("#M9").combobox("getText");
	    			$("#M9").combobox("setText",score+"("+product+")");
					$("#result").textbox('setValue',product);
					calculationColor(d.level);
	    		}
	    	}
	    }	
		
		//根据风险等级设置颜色
		function calculationColor(fxdj){
	        if(fxdj=="1"){
	      	  $("#fenxi").find(".textbox-text").css("background","#FFAEB9");
	        }else if(fxdj=="2"){
	          $("#fenxi").find(".textbox-text").css("background","#FFA500");
	        }else if(fxdj=="3"){
	          $("#fenxi").find(".textbox-text").css("background","#FFFF00");
	        }else if(fxdj=="4"){
	          $("#fenxi").find(".textbox-text").css("background","#CAE1FF");
	   	 	}
		}
		
		function initImg(pmtpath){
			$("#img1").attr("src",pmtpath);
		}
		
		//添加
		function addbdrn() {
			if(usertype=='1')
				qyid='${qyid}';
			else
			 	qyid=$("#ID1").combobox("getValue");
			if(qyid==null||qyid==""){
				layer.msg("请先选择企业！");
				return;
			}
			var id1='${id1}';
			
			layer.open({
			    type: 2,  
			    shift: 1,
			    area: ["800px", "90%"],
			    title: "绑定巡检内容",
		        maxmin: true, 
			    content: ctx+"/fxgk/fxxx/xjnrcreate/"+id1+","+qyid 

			}); 	
			//openDialogView("绑定巡检内容",ctx+"/fxgk/fxxx/xjnrcreate/"+id1+","+qyid,"900px", "95%","");
		}
		//删除
		function delbdrn() {
			var rows = dg.datagrid('getChecked');
			if(rows==null||rows=='') {
				layer.msg("请至少勾选一行记录！",{time: 1000});
				return;
			}
			while(rows.length!=0){
				var row=dg.datagrid("getChecked")[0];
				var rowIndex = dg.datagrid('getRowIndex', row);
				dg.datagrid('deleteRow',rowIndex);  
			}
		}
		
if(usertype != 1){
	$("#ID1").combobox({
		onSelect: function(rec){
			$.ajax({
				type:'get',
				url: '${ctx}/bis/qyjbxx/qydetail/'+rec.id,
				success : function(data) {
					var d=JSON.parse(data);
					$("#m13").textbox('setValue',d.m23);	
					$("#m14").textbox('setValue',d.m25);	
				}
			});
			$.ajax({
			 	url:'${ctx}/wghgl/wgd/qypmt',
					data:{'qyid':rec.id},
					dataType:'text',
					type : 'POST',
					contentType:'application/x-www-form-urlencoded; charset=UTF-8',
		 			success: function (data){
		 				var url=data.split('||');
		 				initImg(url[0]);
         			}
		    });
		}
	});
}

$("#sgfx_fxxx_m3").combobox({
	loader:function (param, success, error) {
	   var hy=$('#sgfx_fxxx_m3').combobox('getValue');
	    if(hy!=''){
		    $.ajax({  
		       url:wfwurl+'/MicroService/fxbs/data/getlb',
	           data:{'hy':hy},
	           dataType : 'json',
	           type : 'POST',
	           success: function (data){
	           	$('#sgfx_fxxx_m4').combobox('loadData', data); 
	           	}
		    });
	    }
	 },
	 onSelect: function(rec){
		  	$.ajax({
		  		   url:wfwurl+'/MicroService/fxbs/data/getlb',
		           data:{'hy':rec.text},
		           dataType : 'json',
		           type : 'POST',
		           success: function (data){
		           		$('#sgfx_fxxx_m4').combobox('setValue', '');
		           		$('#sgfx_fxxx_m4').combobox('loadData', data);
						$('#sgfx_fxxx_m5').combobox('setValue', '');
		           		$('#sgfx_fxxx_m5').combobox('loadData', '');
		           		$('#sgfx_fxxx_m6').combobox('setValue', '');
		           		$('#sgfx_fxxx_m6').combobox('loadData', '');
		           		$('#sgfx_fxxx_m7').val('');
		           		$('#sgfx_fxxx_m10').val('');
		           		$('#sgfx_fxxx_m12').val('');
		           }
		     });
	 }
});
$("#sgfx_fxxx_m4").combobox({
	loader:function (param, success, error) {
	   var hy=$('#sgfx_fxxx_m3').combobox('getValue');
	   var hylb=$('#sgfx_fxxx_m4').combobox('getValue');
	    if(hy!=''&&hylb!=''){
	    $.ajax({  
	       url:wfwurl+'/MicroService/fxbs/data/getgd',
		   data:{'hy':hy,'hylb':hylb},
        dataType : 'json',
        type : 'POST',
        success: function (data){
        	$('#sgfx_fxxx_m5').combobox('loadData', data);
        }
	    });
	    }
	 },
	 onSelect: function(rec){
			var hylb=rec.text;
		  	var hy=$('#sgfx_fxxx_m3').combobox('getValue');
		  	if(hy!=''&&hylb!=''){
			  	$.ajax({
			  		   url:wfwurl+'/MicroService/fxbs/data/getgd',
			           data:{'hy':hy,'hylb':hylb},
			           dataType : 'json',
			           type : 'POST',
			           success: function (data){
			           		$('#sgfx_fxxx_m5').combobox('setValue', '');
			           		$('#sgfx_fxxx_m5').combobox('loadData', data);
			           		$('#sgfx_fxxx_m6').combobox('setValue', '');
		           			$('#sgfx_fxxx_m6').combobox('loadData', '');
		           			$('#sgfx_fxxx_m7').val(''); 
		           			$('#sgfx_fxxx_m10').val('');
		           			$('#sgfx_fxxx_m12').val('');
			           }
			     });
		  	}
			}
		});
		
$("#sgfx_fxxx_m5").combobox({
	loader:function (param, success, error) {
	   var hy=$('#sgfx_fxxx_m3').combobox('getValue');
	   var hylb=$('#sgfx_fxxx_m4').combobox('getValue');
	   var gd=$('#sgfx_fxxx_m5').combobox('getValue');
	    if(hy!=''&&hylb!=''&&gd!=''){
	    $.ajax({  
	       url:wfwurl+'/MicroService/fxbs/data/getbw',	    	
		   data:{'hy':hy,'hylb':hylb,'gd':gd},
           dataType : 'json',
           type : 'POST',
           success: function (data){
           	$('#sgfx_fxxx_m6').combobox('loadData', data);
           }
	    });
	    }
	 },
	 onSelect: function(rec){
			var hylb=$('#sgfx_fxxx_m4').combobox('getValue');
		  	var hy=$('#sgfx_fxxx_m3').combobox('getValue');
		  	var gd=rec.text;
		  	if(hy!=''&&hylb!=''&&gd!=''){
			  	$.ajax({
			  		   url:wfwurl+'/MicroService/fxbs/data/getbw',
			           data:{'hy':hy,'hylb':hylb,'gd':gd},
			           dataType : 'json',
			           type : 'POST',
			           success: function (data){
			           		$('#sgfx_fxxx_m6').combobox('setValue', '');
		           			$('#sgfx_fxxx_m6').combobox('loadData', data);
		           			$('#sgfx_fxxx_m7').val('');
		           			$('#sgfx_fxxx_m10').val('');
		           			$('#sgfx_fxxx_m12').val('');
			           }
			     });
		  	}
			} 
		});

$("#sgfx_fxxx_m6").combobox({
	onSelect: function(rec){
	var hy=$('#sgfx_fxxx_m3').combobox('getValue');
	var hylb=$('#sgfx_fxxx_m4').combobox('getValue');
	var gd=$('#sgfx_fxxx_m5').combobox('getValue');
	var bw=rec.text;
	if(hy!=''&&hylb!=''&&gd!=''&&bw!=''){
	$.ajax({
			url:wfwurl+'/MicroService/fxbs/data/getinfor',
			data:{'hy':hy,'hylb':hylb,'gd':gd,'bw':bw},
			dataType : 'json',
			type : 'POST',
			success: function (data){
			        $('#sgfx_fxxx_m7').textbox("setValue",data.m5);
					$('#sgfx_fxxx_m10').textbox("setValue",data.m7);
					$('#sgfx_fxxx_m12').textbox("setValue",data.m8);
			    } 
			      });}
				  } });


 $("#sgfx_fxxx_m8").combobox({
    url:wfwurl+'/MicroService/fxbs/data/accident',
    valueField:'text',
    textField:'text',
	onSelect: function(rec){
		
		 hashMap[rec.text] = rec.extra; 
			var fxxx= '';
			for(value in hashMap){  
		    fxxx+=hashMap[value]+'\n';
			}  
		 $('#sgfx_fxxx_m11').textbox('setValue',fxxx+old);
		 $('#sgfx_fxxx_m11').keyup();
	},
	onUnselect: function(rec){
	delete hashMap[rec.text];
		var fxxx= '';
			for(value in hashMap)  {  
		    fxxx+=hashMap[value]+'\n';
			} 
		$('#sgfx_fxxx_m11').textbox('setValue',fxxx+old);
	}
});	

 		var $ = jQuery,
 		$list = $('#m16fileList'); //图片上传
 		$list2 = $('#m17fileList'); //图片上传
		var action = $("[name=action]").val();
		var uploader = WebUploader.create({

		    auto: true,

		    swf:  '${ctxStatic}/webuploader-0.1.5/Uploader.swf',

		    server: '${ctx}/kindeditor/upload?dir=image',

		    pick: {
		    	id:'#imagePicker',
		    	multiple : false,
		    },
		    duplicate :true,	    
		    accept: {
		        title: 'Images',
		        extensions: 'gif,jpg,jpeg,bmp,png',
		        mimeTypes: 'image/jpg,image/jpeg,image/png' 
		    },
	    compress :{
	        width: 1200,
	        height: 1200,
	        quality: 90,
	        allowMagnify: false,
	        crop: false,
	        preserveHeaders: false,
	        noCompressIfLarger: false,
	        compressSize: 1024*50
	    }
		});
		var uploader2 = WebUploader.create({

		    auto: true,

		    swf:  '${ctxStatic}/webuploader-0.1.5/Uploader.swf',

		    server: '${ctx}/kindeditor/upload?dir=image',

		    pick: {
		    	id:'#imagePicker2',
		    	multiple : false,
		    },
		    duplicate :true,	    
		    accept: {
		        title: 'Images',
		        extensions: 'gif,jpg,jpeg,bmp,png',
		        mimeTypes: 'image/jpg,image/jpeg,image/png' 
		    },
	    compress :{
	        width: 1200,
	        height: 1200,
	        quality: 90,
	        allowMagnify: false,
	        crop: false,
	        preserveHeaders: false,
	        noCompressIfLarger: false,
	        compressSize: 1024*50
	    }
		});

		uploader.on( 'uploadProgress', function( file, percentage ) {
			$.jBox.tip("正在上传，请稍等...",'loading',{opacity:0});
		});
	  
		// 图片上传成功，显示预览图
		uploader.on( 'uploadSuccess', function( file ,sgfx) {
			$.jBox.closeTip();
			if(sgfx.error==0){
				var $li = $(
			            "<div id=\"" + file.id + "\" class=\"file-item thumbnail\">" +
			            	"<span class=\"cancel\" onClick=\"removeFile('"+file.id+"')\" style=\"cursor: pointer\">删除</span>"+
			                "<img onclick=openImgView('"+sgfx.url+"')>" +
			                "<div class=\"info\">" + file.name + "</div>" +
			            "</div>"
			            ),
		
			    $img = $li.find('img');

			    $list.append( $li );
		
			    // 创建缩略图
			    uploader.makeThumb( file, function( error, src ) {
			        if ( error ) {
			            $img.replaceWith('<span>不能预览</span>');
			            return;
			        }
		
			        $img.attr( 'src', src );
			    }, 100, 100 );
				
				
				var newurl=sgfx.url+"||"+sgfx.fileName;
				var $input = $('<input id="input_'+file.id+'" type="hidden" name="M16" value="'+newurl+'"/>');
				
				$('#uploader_ws_m16').append( $input );
				uploadImgFlag=true;//存在上传图片
				
			}else{
				layer.msg(sgfx.message,{time: 3000});
			}
		});
		
		uploader2.on( 'uploadProgress', function( file, percentage ) {
			$.jBox.tip("正在上传，请稍等...",'loading',{opacity:0});
		});
		// 图片上传成功，显示预览图
		uploader2.on( 'uploadSuccess', function( file ,sgfx) {
			$.jBox.closeTip();
			if(sgfx.error==0){
				var $li = $(
			            "<div id=\"" + file.id + "\" class=\"file-item thumbnail\">" +
			            	"<span class=\"cancel\" onClick=\"removeFile('"+file.id+"')\" style=\"cursor: pointer\">删除</span>"+
			                "<img>" +
			                "<div class=\"info\">" + file.name + "</div>" +
			            "</div>"
			            ),
		
			    $img = $li.find('img');

			    $list2.append( $li );
		
			    // 创建缩略图
			    uploader2.makeThumb( file, function( error, src ) {
			        if ( error ) {
			            $img.replaceWith('<span>不能预览</span>');
			            return;
			        }
		
			        $img.attr( 'src', src );
			    }, 100, 100 );
				
				
				var newurl=sgfx.url+"||"+sgfx.fileName;
				var $input = $('<input id="input_'+file.id+'" type="hidden" name="M17" value="'+newurl+'"/>');
				
				$('#uploader_ws_m17').append( $input );
			}else{
				layer.msg(sgfx.message,{time: 3000});
			}
		});

		$list4 = $('#m24fileList');
		var fileuploader2 = WebUploader.create({

			auto: true,

			swf:  '${ctxStatic}/webuploader-0.1.5/Uploader.swf',

			server: '${ctx}/kindeditor/upload?dir=file',

			pick: {
				id:'#filePickerm24',
				multiple : false,
			},
			duplicate :true
		});

		fileuploader2.on( 'uploadProgress', function( file, percentage ) {
			$.jBox.tip("正在上传，请稍等...",'loading',{opacity:0});
		});

		// 文件上传成功
		fileuploader2.on( 'uploadSuccess', function( file ,res) {
			$.jBox.closeTip();
			if(res.error==0){
				var $li = $(
						"<div id=\"" + file.id + "\" style=\"margin-bottom: 10px;\">" +
						"<a style=\"color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;\" onclick=\"window.open('"+res.url+"')\">"+res.fileName+"</a>"+
						"<span class=\"ss\" onClick=\"removeFile('"+file.id+"')\" style=\"cursor: pointer\">删除</span>"+
						"</div>"
				);

				$list4.html( $li );

				var newurl=res.url+"||"+res.fileName;

				$('#M24').val(newurl);
			}else{
				layer.msg(res.message,{time: 3000});
			}
		});
		
		if('${action}' == 'updateSub'){
			var zpUrl = '${sgfx.m16}';
			if(zpUrl!=null&&zpUrl!=''){
				var arry =zpUrl.split(",");
				for(var i=0;i<arry.length;i++){
					var arry2 =arry[i].split("||");
					var id="ws_zp_"+i;
					var $li = $(
				            "<div id=\"" + id + "\" class=\"file-item thumbnail\">" +
				            	"<span class=\"cancel\" onClick=\"removeFile('"+id+"')\" style=\"cursor: pointer\">删除</span>"+
				                "<img src=\""+arry2[0]+"\" style=\"width:100px; height: 100px\" onclick=openImgView('"+arry2[0]+"')>" +
				                "<div class=\"info\">" + arry2[1] + "</div>" +
				            "</div>"
				            );

				    $list.append( $li );
				    var $input = $('<input id="input_'+id+'" type="hidden" name="M16" value="'+arry[i]+'"/>');
					$('#uploader_ws_m16').append( $input );
				}
			}
			
			var zpUrl = '${sgfx.m17}';
			if(zpUrl!=null&&zpUrl!=''){
				var arry =zpUrl.split(",");
				for(var i=0;i<arry.length;i++){
					var arry2 =arry[i].split("||");
					var id="ws_zp_"+i;
					var $li = $(
				            "<div id=\"update_" + id + "\" class=\"file-item thumbnail\">" +
				            	"<span class=\"cancel\" onClick=\"removeFile('update_"+id+"')\" style=\"cursor: pointer\">删除</span>"+
				                "<img src=\""+arry2[0]+"\" style=\"width:100px; height: 100px\">" +
				                "<div class=\"info\">" + arry2[1] + "</div>" +
				            "</div>"
				            );

				    $list2.append( $li );
				    var $input = $('<input id="input_update_'+id+'" type="hidden" name="M17" value="'+arry[i]+'"/>');
					$('#uploader_ws_m17').append( $input );
				}
			}

			var zpUrl = '${sgfx.m24}';
			if(zpUrl!=null&&zpUrl!=''){
				var arry =zpUrl.split(",");
				for(var i=0;i<arry.length;i++){
					var arry2 =arry[i].split("||");
					var id="ws_zp_"+i;
					var $li = $(
							"<div style=\"margin-bottom: 10px;\">" +
							"<a style=\"color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;\" onclick=\"window.open('"+arry2[0]+"')\">"+arry2[1]+"</a>" +
							"</div>"
					);

					$list4.html( $li );
				}
			}
			isuploadImg();
		}
		
		//国际图标
		var url="${ctx}/static/model/images/dangersign/";
		var dangersigns=[{"url":url+"当心爆炸.png","title":"当心爆炸.png"},
		                 {"url":url+"当心表面高温.png","title":"当心表面高温.png"}, 
		                 {"url":url+"当心叉车.png","title":"当心叉车.png"},
		                 {"url":url+"当心车辆.png","title":"当心车辆.png"},
		                 {"url":url+"当心触电.png","title":"当心触电.png"},
		                 {"url":url+"当心低温.png","title":"当心低温.png"},
		                 {"url":url+"当心电缆.png","title":"当心电缆.png"},
		                 {"url":url+"当心电离辐射.png","title":"当心电离辐射.png"},
		                 {"url":url+"当心吊物.png","title":"当心吊物.png"},
		                 {"url":url+"当心跌落.png","title":"当心跌落.png"},
		                 {"url":url+"当心缝隙.png","title":"当心缝隙.png"},
		                 {"url":url+"当心腐蚀.png","title":"当心腐蚀.png"},
		                 {"url":url+"当心滑倒.png","title":"当心滑倒.png"},
		                 {"url":url+"当心火灾.png","title":"当心火灾.png"},
		                 {"url":url+"当心机械伤人.png","title":"当心机械伤人.png"},
		                 {"url":url+"当心激光.png","title":"当心激光.png"},
		                 {"url":url+"当心挤压.png","title":"当心挤压.png"},
		                 {"url":url+"当心夹手.png","title":"当心夹手.png"},
		                 {"url":url+"当心坑洞.png","title":"当心坑洞.png"},
		                 {"url":url+"当心裂变物质.png","title":"当心裂变物质.png"},
		                 {"url":url+"当心落水.png","title":"当心落水.png"},
		                 {"url":url+"当心落物.png","title":"当心落物.png"},
		                 {"url":url+"当心冒顶.png","title":"当心冒顶.png"},
		                 {"url":url+"当心碰头.png","title":"当心碰头.png"},
		                 {"url":url+"当心伤手.png","title":"当心伤手.png"},
		                 {"url":url+"当心塌方.png","title":"当心塌方.png"},
		                 {"url":url+"当心烫伤.png","title":"当心烫伤.png"},
		                 {"url":url+"当心扎脚.png","title":"当心扎脚.png"},
		                 {"url":url+"当心障碍物.png","title":"当心障碍物.png"},
		                 {"url":url+"当心中毒.png","title":"当心中毒.png"},
		                 {"url":url+"当心坠落.png","title":"当心坠落.png"},
		                 {"url":url+"当心自动启动.png","title":"当心自动启动.png"},
		                 {"url":url+"注意安全.png","title":"注意安全.png"},
		                 {"url":url+"当心辐射.png","title":"当心辐射.png"},
		                 {"url":url+"噪声有害.png","title":"噪声有害.png"},
		                 {"url":url+"注意防尘.png","title":"注意防尘.png"},
		                 {"url":url+"注意弧光.png","title":"注意弧光.png"},
		                 {"url":url+"必须穿戴绝缘保护用品.png","title":"必须穿戴绝缘保护用品.png"},
		                 {"url":url+"必须穿救生衣.png","title":"必须穿救生衣.png"},
		                 {"url":url+"必须带防护眼镜.png","title":"必须带防护眼镜.png"},
		                 {"url":url+"必须带自救器.png","title":"必须带自救器.png"},
		                 {"url":url+"必须戴安全帽.png","title":"必须戴安全帽.png"},
		                 {"url":url+"必须戴防尘口罩.png","title":"必须戴防尘口罩.png"},
		                 {"url":url+"必须戴防毒口罩.png","title":"必须戴防毒口罩.png"},
		                 {"url":url+"必须戴防护帽.png","title":"必须戴防护帽.png"},
		                 {"url":url+"必须戴防护手套.png","title":"必须戴防护手套.png"},
		                 {"url":url+"必须戴护耳器.png","title":"必须戴护耳器.png"},
		                 {"url":url+"必须加锁.png","title":"必须加锁.png"},
		                 {"url":url+"必须桥上通过.png","title":"必须桥上通过.png"},
		                 {"url":url+"必须系安全带.png","title":"必须系安全带.png"},
		                 {"url":url+"必须携带矿灯.png","title":"必须携带矿灯.png"},
		                 {"url":url+"注意通风.png","title":"注意通风.png"},
		                 {"url":url+"走人行道.png","title":"走人行道.png"}

		];
		var arfj = ($('#M17').val()==null?"":$('#M17').val());
		$.each(dangersigns, function(idx, obj) {
			if(arfj.indexOf(obj.title)>0){
				$(".icon-selector-ul").append("<li  class='fxxxSelectIcon selected'  ><img   src='"+obj.url+"' width='100px' height='100px'   title='"+obj.title+"'  /></li> ");
			}else{
				$(".icon-selector-ul").append("<li  class='fxxxSelectIcon'  ><img   src='"+obj.url+"' width='100px' height='100px'   title='"+obj.title+"'  /></li> ");
			}

		});
		 
	/* 	$('#slctfilebtM17').click(function() {
			$("#select_dlg").dialog("open").dialog("center").dialog(
					"setTitle", "选择标志");
		}); */
		$('#slctfilebtM17').click(function() {
			$(".fxxxSelectIcon").each(function(index,e){
				if($(e).hasClass("selected"))
					$(e).removeClass("selected");
			});
			layer.open({
			    type: 1,  
			    title: '选择标志',
			    area:'600px',
			    content: $("#select_dlg"),
			    btn: ['确定', '关闭'],
			    yes: function(index, layero){
			    	var i=0;
			    	$("#fxxx-selects-ul").children(".fxxxSelectIcon.selected").each(
			    			function(i,obj){
			    				var url=$(obj).find("img").attr("src");
			    				var title=$(obj).find("img").attr("title");
			    				 
			    				if (typeof (title) == "undefined") {
			    					return true;
			    				}
			    				
			    				var newurl=url+"||"+title;
			    					var $li = $("<div id=\"img2_" + i + "\"  class=\"file-item thumbnail\">" +
			    				            	"<span class=\"cancel\" onClick=\"deleteupimg17('"+i+"')\" style=\"cursor: pointer\">删除</span>"+
			    				                "<img>" +
			    				                "<div class=\"info\">" + title + "</div>" +
			    				            "</div>");
			    			
			    				    $img = $li.find('img');
			    				    $img.css({"height":"100px"});
			    				    $list2.append( $li );
			    				    $img.attr( 'src',url );
			    					var $input = $('<input id="input2_'+i+'" type="hidden" name="M17" value="'+newurl+'"/>');
			    					$('#uploader_ws_m17').append( $input );
			    			});
			    	layer.close(index);//关闭对话框。
			    },
			cancel: function(index){}
			}); 	
		}); 
		
		  // 负责预览图的销毁
	    function removeFile(fileid) {
	    	$("#"+fileid).remove();
	    	$("#input_"+fileid).remove();
	    	isuploadImg();
	    };
		//M17删除上传图片数量
		function deleteupimg17 (fileid) {
			$("#img2_"+fileid).remove();
	    	$("#input2_"+fileid).remove();
		}	

		$(".fxxxSelectIcon").click(function () { 
			 if($(this).hasClass("selected")){
				 $(this).removeClass("selected");
			 }else{
				// $(this).css("border","1px red solid");
				 $(this).addClass("selected");
			 }
			
		
		});
		

		var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
		var validateForm;

		function doSubmit() {
			var rows = dg.datagrid('getData').rows;
			if(rows.length==0){
				layer.open({title: '提示',offset: 'auto',content: '未绑定巡检内容，请绑定！',shade: 0 ,time: 2000 });
				return;
			}
			var ids="";
			for(var i=0;i<rows.length;i++){
				if(ids==""){
					ids=rows[i].id;
				}else{
					ids=ids+","+rows[i].id;
				}
			}
			if(uploadImgFlag==false){
				layer.open({title: '提示',offset: 'auto',content: '未上传现场照片，请上传！',shade: 0 ,time: 2000 });
				return;
			}
			$("#bdnrids").val(ids);
			$("#inputForm").serializeObject();
			$("#inputForm").submit();
		}
		$(function(){
			var flag=true;
			$('#inputForm').form({
			    onSubmit: function(){    
			    	var isValid = $(this).form('validate');
			    	if(isValid&&flag){
			    		flag=false;
			    		$.jBox.tip("正在提交，请稍等...",'loading',{opacity:0});
			    		return true;
			    	}
					return false; //返回false终止表单提交
			    },
			    success:function(data){
			    	$.jBox.closeTip();
			    	if(data=='success')
			    		parent.layer.open({icon:1,title: '提示',offset: 'rb',content: '操作成功！',shade: 0 ,time: 2000 });
			    	else if(data=='ewmerror')
			    		parent.layer.open({icon:2,title: '提示',offset: 'rb',content: '二维码重复，操作失败！',shade: 0 ,time: 2000 });
			    	else if(data=='rfiderror')
			    		parent.layer.open({icon:2,title: '提示',offset: 'rb',content: 'rfid重复，操作失败！',shade: 0 ,time: 2000 });
			    	else if(data=='bderror')
			    		parent.layer.open({icon:2,title: '提示',offset: 'rb',content: '绑定巡检内容失败！',shade: 0 ,time: 2000 });
			    	else
			    		parent.layer.open({icon:2,title: '提示',offset: 'rb',content: '操作失败！',shade: 0 ,time: 2000 });
			    	parent.dg.datagrid('reload');
			    	parent.layer.close(index);//关闭对话框。
			    }    
			});
			
			//设置默认颜色
			calculationColor('${sgfx.m9 }');
		});

	//弹出平面图界面
	function showpmt(){		
		layer.open({
		    type: 1,  
		    area: ['100%', '100%'],
		    title: '标注坐标',
	        maxmin: true, 
	        shift: 1,
	        shade :0,
		    content: $('#xfss_dlg'),
		    btn: ['关闭平面图'],
		    success: function(layero, index){
		    },
		    yes: function(index, layero){
		    	layer.close(index);
			  },
			  cancel: function(index){ 
		       }
		})
		if('${action}'=='updateSub')
		{
			initMap();
		}
	}

	function initMap(){
		var wh=$("#img1").width();
		var wh2=$("#img1").height();
		var x=$("#bis_map_c_x").val();
		var y=$("#bis_map_c_y").val();
		if(x!="" && y!=""){
	        var top=y*wh2+"px";
	        var left=x*wh+"px";
	        $('.ball').remove();
	        $('.wrap').append('<div class="ball" style="top:'+top+';left:'+left+'"></div>');
		}
	}
	
	//判断是否上传图片
	function isuploadImg(){
		var img=$("input[name='M16']").val();
		if(img==null||img==""){
			uploadImgFlag=false;
		}else{
			uploadImgFlag=true;
		}
	}
	
	//根据输入的风险名称查找相应的数据并自动填充
	$('#M1').combogrid({
		panelWidth : 900,
		idField : 'm1', //ID字段 
		textField : 'm1', //显示的字段 
		fitColumns : true,
		method: "post",
		url:wfwurl+'/MicroService/fxxx/data/fxdlist',
		striped : true,
		editable : true,
		pagination : true,//是否分页 
		rownumbers : true,//序号 
		collapsible : false,//是否可折叠的 
		nowrap:false,
		pageSize : 10,//每页显示的记录条数，默认为10 
		pageList : [ 10 ],//可以设置每页记录条数的列表 
		method : 'post',
		columns : [ [ 
	        {field:'m1',title:'较大风险点名称',sortable:false,width:120,align:'center',
	        	formatter : function(value, row, index){
	        		return "<div class=tipdiv onmouseover=mouseovertip(event,'"+index+"') onmouseout=mouseouttip('"+index+"')>" + value + "</div>";
	        	}	
	        },    
	        {field:'m2',title:'风险类别',sortable:false,width:50,align:'center'},
	        {field:'m3',title:'行业',sortable:false,width:50,align:'center'},
	        {field:'m4',title:'行业类别',sortable:false,width:50,align:'center'},
	        {field:'m5',title:'工段',sortable:false,width:50,align:'center'},
	        {field:'m6',title:'部位',sortable:false,width:50,align:'center'},
	        {field:'m8',title:'易发生事故类型',sortable:false,width:100,align:'center'},
	        {field:'m9',title:'风险分级',sortable:false,width:30,align:'center', 
	        	formatter : function(value, row, index){
	        		if(value=='1') return value='红';
	        		else if(value=='2') return value='橙';
	        		else if(value=='3') return value='黄';
	        		else if(value=='4') return value='蓝'; 
	        	},
	        	styler : function(value, row, index){
	        		if(value=='1')  return 'background-color:#FF0000;color:#1E1E1E';
	        		else if(value=='2')  return 'background-color:#FFC125;color:#1E1E1E';
	        		else if(value=='3')  return 'background-color:#FFFF00;color:#1E1E1E';
	        		else if(value=='4')  return 'background-color:#3A5FCD;color:#1E1E1E'; 
	     		}
	        
	        },
			{field : 'cz',title : '操作',width : 150,hidden:true,
				formatter : function(value, row, index){
					return "<a style='margin:2px' class='btn btn-success btn-xs' onclick='view("+row.id+")'>查看</a>"; 
	        	}
			} 
		] ],
     	onSelect : function (rowIndex, rowData){
    		if(rowData.m2!=null&&rowData.m2!="")
    	  		$('#M2').combobox("setValue",rowData.m2);
 			if(rowData.aprobability!=null)
				$('#aprobability').combobox("setValue",rowData.aprobability);
			if(rowData.exposefrequency!=null)
				$('#exposefrequency').combobox("setValue",rowData.exposefrequency);
			if(rowData.aseverity!=null)
				$('#aseverity').combobox("setValue",rowData.aseverity); 
			if(rowData.m9!=null)
				$('#M9').combobox("setValue",rowData.m9);
			if(rowData.m3!=null)
				$('#sgfx_fxxx_m3').combobox("setValue",rowData.m3);
			if(rowData.m4!=null)
				$('#sgfx_fxxx_m4').combobox("setValue",rowData.m4);
			if(rowData.m5!=null)
				$('#sgfx_fxxx_m5').combobox("setValue",rowData.m5);
			if(rowData.m6!=null)
				$('#sgfx_fxxx_m6').combobox("setValue",rowData.m6);
			if(rowData.m8!=null)
				$('#sgfx_fxxx_m8').combobox("setValue",rowData.m8);
			if(rowData.m7!=null)
				$('#sgfx_fxxx_m7').textbox("setValue",rowData.m7);
			if(rowData.m10!=null)
				$('#sgfx_fxxx_m10').textbox("setValue",rowData.m10);
			if(rowData.m12!=null)
				$('#sgfx_fxxx_m12').textbox("setValue",rowData.m12);
			if(rowData.m11!=null)
				$('#sgfx_fxxx_m11').textbox("setValue",rowData.m11);
			calculationColor(rowData.m9);
		}   
	});
	
	function search(){
		var fxname=$('#M1').combogrid("getText");
		$('#M1').combogrid("showPanel");
		$('#M1').combogrid("grid").datagrid("reload", { 'm1': fxname });
	}
	
	function mouseovertip(event,index){
var prows=$('#M1').combogrid("grid").datagrid("getData");
		
		var tiphtml = "<table style='width: 100%;'><tbody>";
		tiphtml += "<tr><td class='width-14 active'><label class='pull-right' valign='top'>风险点名称：</label></td>";
		tiphtml += "<td class='width-86'>"+prows.rows[index].m1+"</td></tr>";
		tiphtml += "<tr><td class='width-14 active'><label class='pull-right' valign='top'>风险分类：</label></td>";
		tiphtml += "<td class='width-86'>"+prows.rows[index].m2+"</td></tr>";
		var ys ="";
		var fxfjys ="";
		if(prows.rows[index].m9=='1') {
			ys='红';
			fxfjys='red';
		}else if(prows.rows[index].m9=='2') {
			ys='橙';
			fxfjys='orange';
		}else if(prows.rows[index].m9=='3') {
			ys='黄';
			fxfjys='yellow';
		}else if(prows.rows[index].m9=='4') {
			ys='蓝'; 
			fxfjys='blue';
		}
		tiphtml += "<tr><td class='width-14 active'><label class='pull-right' valign='top'>风险分级：</label></td>";
		tiphtml += "<td class='width-86'><span class='"+fxfjys+"'>"+ys+"</span></td></tr>";
		tiphtml += "<tr><td class='width-14 active' valign='top'><label class='pull-right'>场地名称：</label></td>";
		tiphtml += "<td class='width-86'>"+prows.rows[index].areaname+"</td></tr>";
		tiphtml += "<tr><td class='width-14 active' valign='top'><label class='pull-right'>行业：</label></td>";
		tiphtml += "<td class='width-86'>"+prows.rows[index].m3+"</td></tr>";
		tiphtml += "<tr><td class='width-14 active' valign='top'><label class='pull-right'>行业类别：</label></td>";
		tiphtml += "<td class='width-86'>"+prows.rows[index].m4+"</td></tr>";
		tiphtml += "<tr><td class='width-14 active' valign='top'><label class='pull-right'>工段：</label></td>";
		tiphtml += "<td class='width-86'>"+prows.rows[index].m5+"</td></tr>";
		tiphtml += "<tr><td class='width-14 active' valign='top'><label class='pull-right'>部位：</label></td>";
		tiphtml += "<td class='width-86'>"+prows.rows[index].m6+"</td></tr>";
		tiphtml += "<tr><td class='width-14 active' valign='top'><label class='pull-right'>事故类型：</label></td>";
		tiphtml += "<td class='width-86'>"+prows.rows[index].m8+"</td></tr>";
		tiphtml += "<tr><td class='width-14 active' valign='top'><label class='pull-right'>危险有害性：</label></td>";
		tiphtml += "<td class='width-86'>"+prows.rows[index].m7+"</td></tr>";
		tiphtml += "<tr><td class='width-14 active' valign='top'><label class='pull-right'>管控措施：</label></td>";
		tiphtml += "<td class='width-86'>"+prows.rows[index].m10+"</td></tr>";
		tiphtml += "<tr><td class='width-14 active' valign='top'><label class='pull-right'>依据：</label></td>";
		tiphtml += "<td class='width-86'>"+prows.rows[index].m12+"</td></tr>";
		tiphtml += "<tr><td class='width-14 active' valign='top'><label class='pull-right'>处置对策：</label></td>";
		tiphtml += "<td class='width-86'>"+prows.rows[index].m11+"</td></tr>";
		tiphtml += "<tr><td class='width-14 active' valign='top'><label class='pull-right'>备注：</label></td>";
		tiphtml += "<td class='width-86'>"+prows.rows[index].m15+"</td></tr>";
		tiphtml += "</tbody></table>";
		
		$(".fxdxxtip").html(tiphtml);
		
		x=event.clientX;
		var left = 0;
		if(x>620){
			left = x - 620;
		}else{
			left = x + 20;
		}
		
		$(".fxdxxtip").show();
		$(".fxdxxtip").css("left", left ); 
	}
	
	function mouseouttip(index){
		$(".fxdxxtip").hide();
	}


	</script>
	</body>
</html>