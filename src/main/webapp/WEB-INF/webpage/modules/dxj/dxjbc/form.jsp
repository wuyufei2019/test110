<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>点巡检班次任务设置</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/model/js/dxj/dxjbc/index.js?v=1"></script>
</head>
<body>
     <form id="inputForm" action="${ctx}/dxj/bcrw/${action}"  method="post" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right">班次名称：</label></td>
					<td class="width-35"><input data-options="required:'true',validType:'length[0,100]'" type="text" id="name" name="name" class="easyui-textbox" value="${bcrw.name}"  style="height: 30px;width: 100%" /></td>
					<td class="width-15 active"><label class="pull-right">班次类型：</label></td>
					<td class="width-35"><input data-options="required:'true',panelHeight:'auto',editable:false,data: [
										{value:'1',text:'日检'},
								        {value:'2',text:'周检'},
								        {value:'3',text:'月检'},
								        {value:'4',text:'年检'}
								        ]" type="text" id="type" name="type" class="easyui-combobox" value="${bcrw.type}"  style="height: 30px;width: 100%" /></td>
				</tr>
				<tr>
				    <td align="center" colspan="4">
						<a class="btn btn-info btn-sm" data-toggle="tooltip" data-placement="left" onclick="addRw()" title="添加任务"><i class="fa fa-plus"></i> 添加任务</a>
					</td>	
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">任务设置：</label></td>
					<td class="width-35" colspan="3">
						<table id="rwtable" style="width: 100%;"  border="1" cellspacing="0" cellpadding="0" bordercolor="#e7e7e7" >
							<tr >
								<td style="text-align: center;width: 37%;">起始时间</td>
								<td style="text-align: center;width: 37%;">结束时间</td>
								<td style="text-align: center;">操作</td>
							</tr>
						</table>	
					</td>
				</tr>		
				<tr>
					<td class="width-15 active"><label class="pull-right">设备项目：</label></td>
					<td class="width-35" colspan="3">
						<!-- 负责传值 -->
						<div id="jcdIDs">
							<input id="jcdids" type="hidden" name="jcdids" />
							<div id="jcdList"></div>
							<a  href="javascript:void(0)"  class="btn btn-info btn-sm" data-toggle="tooltip" data-placement="left" onclick="openjcdlist()" title="选择设备项目"><i class="fa fa-plus"></i> 选择设备项目</a>
						</div>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">检查人员：</label></td>
					<td class="width-35" colspan="3">
						<!-- 负责传值 -->
						<div id="xjryIDs">
							<input id="xjryids" type="hidden" name="xjryids" />
							<div id="xjryList"></div>
							<a  href="javascript:void(0)"  class="btn btn-info btn-sm" data-toggle="tooltip" data-placement="left" onclick="openxjrylist()" title="选择检查人员"><i class="fa fa-plus"></i> 选择检查人员</a>
						</div>
					</td>
				</tr>
				<c:if test="${action eq 'update'}">
				</c:if>
				<c:if test="${not empty bcrw.ID}">
					<input type="hidden" name="ID" value="${bcrw.ID}" />
					<input type="hidden" name="createtime"  value="<fmt:formatDate value="${bcrw.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/>" />
					<input type="hidden" name="userid" value="${bcrw.userid}" />
					<input type="hidden" name="ID1" value="${bcrw.ID1}" />
				</c:if>
				</tbody>
			</table>
       </form>
 
<script type="text/javascript">
	var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
	
	var rwid=1;
	if ('${action}' == 'update') {
			var $list = $("#xjryList");
			var ids = '${idname}';
			var xjryids="";
			if (ids != null && ids != '' && ids != 'null') {
				var arry3 = ids.split(",");
				for (var i = 0; i < arry3.length-1; i++) {
					var arry4 = arry3[i].split("||");
					var $li = $("<div id=\"" +arry4[0]+ "\" style=\"margin-bottom: 10px;\">"
							+ "<a style=\"color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;\">"
							+ arry4[1]
							+ "</a>"
							+ "<span class=\"ss\" onClick=\"removeXjry('"
							+ arry4[0]
							+ "')\" style=\"cursor: pointer\">删除</span>"
							+ "</div>");
					xjryids += arry4[0] + ",";
					$list.append($li);
				}
			}
			$("#xjryids").val(xjryids);
			
			var $list2 = $("#jcdList");
			var ids2 = '${idname2}';
			var jcdids="";
			if (ids2 != null && ids2 != '' && ids2 != 'null') {
				var arry1 = ids2.split(",");
				for (var i = 0; i < arry1.length-1; i++) {
					var arry2 = arry1[i].split("||");
					var $li2 = $("<div id=\"sbxm_" +arry2[0]+ "\" style=\"margin-bottom: 10px;\">"
							+ "<a style=\"color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;\">"
							+ arry2[4] +" —— "+ arry2[1]
							+ "</a>"
							+ "<span class=\"ss\" onClick=\"removeJcd('sbxm_"
							+ arry2[0]
							+ "')\" style=\"cursor: pointer\">删除</span>"
							+ "</div>");
					jcdids += arry2[0] + ",";
					$list2.append($li2);
				}
			}
			$("#jcdids").val(jcdids);
			
			var bctype = '${bcrw.type}';
			var rwsjlist = '${rwsjlist}';
			sjlist=JSON.parse(rwsjlist);  
			if(bctype == 1){
		        $.each(sjlist, function(idx, obj) {
		            var $list= $("#rwtable tr").eq(-1);
					var $li = $(
						'<tr style="height:40px" >'+
						'<td style="width:37%" align="center">'+
						'<input type="text" id="ristart_'+rwid+'" name="start" class="easyui-timespinner" value="'+obj.starttime+'"  style="height: 30px;width: 230px" data-options="required:true,showSeconds:true"/>'+
						'</td>'+
						'<td style="width:37%" align="center">'+
						'<input type="text" id="riend_'+rwid+'" name="end" class="easyui-timespinner" value="'+obj.endtime+'"  style="height: 30px;width: 230px" data-options="required:true,showSeconds:true"/>' +
						'</td>'+
						'<td align="center">'+
						'<button class="btn btn-info btn-sm" style="width:80px" data-toggle="tooltip" data-placement="left" onclick="removeTr($(this).parent().parent())" title="删除"><i class="fa fa-trash-o"></i> 删除</button>'+
						'</td>'+
						'</tr>'
			            );
					$list.after( $li );
					$('#ristart_'+rwid).timespinner({   
						validType:"equalstime['ristart_"+rwid+"']"		
			  		});  
			  		$('#riend_'+rwid).timespinner({
			  			validType:"equalsendtime['riend_"+rwid+"']"	   
			  		}); 
	  				rwid=rwid+1;     
		        });
			}else if(bctype == 2){
			    $.each(sjlist, function(idx, obj) {
		            var $list= $("#rwtable tr").eq(-1);
					var $li = $(
						'<tr style="height:40px" >'+
						'<td style="width:37%" align="center">'+
						'<input data-options="" type="text" id="zhoustart_'+rwid+'" name="start" class="easyui-combobox" value="'+obj.starttime+'"  style="height: 30px;width: 230px" />'+
						'</td>'+
						'<td style="width:37%" align="center">'+
						'<input data-options="" type="text" id="zhouend_'+rwid+'" name="end" class="easyui-combobox" value="'+obj.endtime+'"  style="height: 30px;width: 230px" />' +
						'</td>'+
						'<td align="center">'+
						'<button class="btn btn-info btn-sm" style="width:80px" data-toggle="tooltip" data-placement="left" onclick="removeTr($(this).parent().parent())" title="删除"><i class="fa fa-trash-o"></i> 删除</button>'+
						'</td>'+
						'</tr>'
			            );
					$list.after( $li );
					$('#zhoustart_'+rwid).combobox({  
			    		panelHeight:'auto', 
			    		editable:false,
			    		required:true,
			     	    data: [{value:'1',text:'周一'},{value:'2',text:'周二'},
			           	    {value:'3',text:'周三'},{value:'4',text:'周四'},
			                {value:'5',text:'周五'},{value:'6',text:'周六'},{value:'7',text:'周日'}],
					     onSelect: function () {  
					           var startid = this.id;
					           var endid = "zhouend_"+startid.split("_")[1];
					           var start = $('#'+startid).combobox('getValue');
					           var end = $('#'+endid).combobox('getValue');
					           if(end!=''&&start!=''){
					           		if(start>end){
					           			$('#'+startid).combobox('setValue','');
					           			layer.msg("起始时间不能大于结束时间！",{time: 2000});
					           		}
					           }
					     } 
	  				}); 
	  				$('#zhouend_'+rwid).combobox({   
	  		    	 	panelHeight:'auto', 
			     	 	editable:false,
			     	 	required:true,
			     	 	data: [{value:'1',text:'周一'},{value:'2',text:'周二'},
			           	 	{value:'3',text:'周三'},{value:'4',text:'周四'},
			            	{value:'5',text:'周五'},{value:'6',text:'周六'},{value:'7',text:'周日'}],
					     onSelect: function () {  
					           var endid = this.id;
					           var startid = "zhoustart_"+endid.split("_")[1];
					           var start = $('#'+startid).combobox('getValue');
					           var end = $('#'+endid).combobox('getValue');
					           if(end!=''&&start!=''){
					           		if(start>end){
					           			$('#'+endid).combobox('setValue','');
					           			layer.msg("结束时间不能小于起始时间！",{time: 2000});
					           		}
					           }
			       		 }   
	  				}); 
	  				rwid=rwid+1; 
	  			});    
			}else if(bctype == 3){
			    $.each(sjlist, function(idx, obj) {
		            var $list= $("#rwtable tr").eq(-1);
					var $li = $(
						'<tr style="height:40px" >'+
						'<td style="width:37%" align="center">'+
						'<input data-options="" type="text" id="yuestart_'+rwid+'" name="start" class="easyui-combobox" value="'+obj.starttime+'"  style="height: 30px;width: 230px" />'+
						'</td>'+
						'<td style="width:37%" align="center">'+
						'<input data-options="" type="text" id="yueend_'+rwid+'" name="end" class="easyui-combobox" value="'+obj.endtime+'"  style="height: 30px;width: 230px" />' +
						'</td>'+
						'<td align="center">'+
						'<button class="btn btn-info btn-sm" style="width:80px" data-toggle="tooltip" data-placement="left" onclick="removeTr($(this).parent().parent())" title="删除"><i class="fa fa-trash-o"></i> 删除</button>'+
						'</td>'+
						'</tr>'
			            );
					$list.after( $li );
					$('#yuestart_'+rwid).combobox({  
			     		editable:false,
			     		required:true,
			    		data: [{value:'1',text:'1日'},{value:'2',text:'2日'},{value:'3',text:'3日'},{value:'4',text:'4日'},{value:'5',text:'5日'},
							   {value:'6',text:'6日'},{value:'7',text:'7日'},{value:'8',text:'8日'},{value:'9',text:'9日'},{value:'10',text:'10日'},
							   {value:'11',text:'11日'},{value:'12',text:'12日'},{value:'13',text:'13日'},{value:'14',text:'14日'},{value:'15',text:'15日'},
					       	   {value:'16',text:'16日'},{value:'17',text:'17日'},{value:'18',text:'18日'},{value:'19',text:'19日'},{value:'20',text:'20日'},
					           {value:'21',text:'21日'},{value:'22',text:'22日'},{value:'23',text:'23日'},{value:'24',text:'24日'},{value:'25',text:'25日'},
					           {value:'26',text:'26日'},{value:'27',text:'27日'},{value:'28',text:'28日'},{value:'29',text:'29日'},{value:'30',text:'30日'},
					           {value:'31',text:'31日'}],
					     onSelect: function () {  
					           var startid = this.id;
					           var endid = "yueend_"+startid.split("_")[1];
					           var start = $('#'+startid).combobox('getValue');
					           var end = $('#'+endid).combobox('getValue');
					           if(end!=''&&start!=''){
					           		if(start>end){
					           			$('#'+startid).combobox('setValue','');
					           			layer.msg("起始时间不能大于结束时间！",{time: 2000});
					           		}
					           }
					     }
	  				}); 
			  		$('#yueend_'+rwid).combobox({   
			  		     editable:false,
			  		     required:true,
					     data: [{value:'1',text:'1日'},{value:'2',text:'2日'},{value:'3',text:'3日'},{value:'4',text:'4日'},{value:'5',text:'5日'},
								{value:'6',text:'6日'},{value:'7',text:'7日'},{value:'8',text:'8日'},{value:'9',text:'9日'},{value:'10',text:'10日'},
								{value:'11',text:'11日'},{value:'12',text:'12日'},{value:'13',text:'13日'},{value:'14',text:'14日'},{value:'15',text:'15日'},
						        {value:'16',text:'16日'},{value:'17',text:'17日'},{value:'18',text:'18日'},{value:'19',text:'19日'},{value:'20',text:'20日'},
						        {value:'21',text:'21日'},{value:'22',text:'22日'},{value:'23',text:'23日'},{value:'24',text:'24日'},{value:'25',text:'25日'},
						        {value:'26',text:'26日'},{value:'27',text:'27日'},{value:'28',text:'28日'},{value:'29',text:'29日'},{value:'30',text:'30日'},
						        {value:'31',text:'31日'}],
					     onSelect: function () {  
					           var endid = this.id;
					           var startid = "yuestart_"+endid.split("_")[1];
					           var start = $('#'+startid).combobox('getValue');
					           var end = $('#'+endid).combobox('getValue');
					           if(end!=''&&start!=''){
					           		if(start>end){
					           			$('#'+endid).combobox('setValue','');
					           			layer.msg("结束时间不能小于起始时间！",{time: 2000});
					           		}
					           }
			       		 }  
			  		}); 
	  				rwid=rwid+1; 
	  			});    
			}else if(bctype == 4){
			    $.each(sjlist, function(idx, obj) {
		            var $list= $("#rwtable tr").eq(-1);
					var $li = $(
						'<tr style="height:40px" >'+
						'<td style="width:37%" align="center">'+
						'<input data-options="" type="text" id="nianstart_'+rwid+'" name="start" class="easyui-combobox" value="'+obj.starttime+'"  style="height: 30px;width: 230px" />'+
						'</td>'+
						'<td style="width:37%" align="center">'+
						'<input data-options="" type="text" id="nianend_'+rwid+'" name="end" class="easyui-combobox" value="'+obj.endtime+'"  style="height: 30px;width: 230px" />' +
						'</td>'+
						'<td align="center">'+
						'<button class="btn btn-info btn-sm" style="width:80px" data-toggle="tooltip" data-placement="left" onclick="removeTr($(this).parent().parent())" title="删除"><i class="fa fa-trash-o"></i> 删除</button>'+
						'</td>'+
						'</tr>'
			            );
					$list.after( $li );
					$('#nianstart_'+rwid).combobox({  
					     editable:false,
					     required:true,
					     data: [{value:'1',text:'一月'},{value:'2',text:'二月'},
					            {value:'3',text:'三月'},{value:'4',text:'四月'},
					            {value:'5',text:'五月'},{value:'6',text:'六月'},
					            {value:'7',text:'七月'},{value:'8',text:'八月'},
					            {value:'9',text:'九月'},{value:'10',text:'十月'},
					            {value:'11',text:'十一月'},{value:'12',text:'十二月'}],
					     onSelect: function () {  
					           var startid = this.id;
					           var endid = "nianend_"+startid.split("_")[1];
					           var start = $('#'+startid).combobox('getValue');
					           var end = $('#'+endid).combobox('getValue');
					           if(end!=''&&start!=''){
					           		if(start>end){
					           			$('#'+startid).combobox('setValue','');
					           			layer.msg("起始时间不能大于结束时间！",{time: 2000});
					           		}
					           }
					     }  
	  				}); 
			  		$('#nianend_'+rwid).combobox({  
					     editable:false,
					     required:true,
					     data: [{value:'1',text:'一月'},{value:'2',text:'二月'},
					            {value:'3',text:'三月'},{value:'4',text:'四月'},
					            {value:'5',text:'五月'},{value:'6',text:'六月'},
					            {value:'7',text:'七月'},{value:'8',text:'八月'},
					            {value:'9',text:'九月'},{value:'10',text:'十月'},
					            {value:'11',text:'十一月'},{value:'12',text:'十二月'}],
					     onSelect: function () {  
					           var endid = this.id;
					           var startid = "nianstart_"+endid.split("_")[1];
					           var start = $('#'+startid).combobox('getValue');
					           var end = $('#'+endid).combobox('getValue');
					           if(end!=''&&start!=''){
					           		if(start>end){
					           			$('#'+endid).combobox('setValue','');
					           			layer.msg("结束时间不能小于起始时间！",{time: 2000});
					           		}
					           }
			       		 }    
			  		}); 
	  				rwid=rwid+1; 
	  			});    
			}
	}
		
	$.extend($.fn.validatebox.defaults.rules, {
	    equalstime: {
			validator: function(value,param){
				var startid = param[0];
	            var endid = "riend_"+startid.split("_")[1];
	            var starth = $('#'+startid).timespinner('getHours');
	            var endh = $('#'+endid).timespinner('getHours');
	            var startm = $('#'+startid).timespinner('getHours');
	            var endm = $('#'+endid).timespinner('getHours');
	            var starts = $('#'+startid).timespinner('getHours');
	            var ends = $('#'+endid).timespinner('getHours');
	            if(!isNaN(endh)){
		            if(starth<endh){
		            	if(startm<endm){
		            		if(starts<ends){
		            			return true;
		            		}else{
		            			return false;
		            		}
		            	}else{
		            		return false;
		            	}
		            }else{
		            	return false;
		            }
	            }
	            return true;
			},
			message: '起始时间不能大于结束时间！'
	    },
	    equalsendtime: {
			validator: function(value,param){
				var endid = param[0];
	            var startid = "ristart_"+endid.split("_")[1];
	            var starth = $('#'+startid).timespinner('getHours');
	            var endh = $('#'+endid).timespinner('getHours');
	            var startm = $('#'+startid).timespinner('getHours');
	            var endm = $('#'+endid).timespinner('getHours');
	            var starts = $('#'+startid).timespinner('getHours');
	            var ends = $('#'+endid).timespinner('getHours');
	            if(!isNaN(starth)){
		            if(starth<endh){
		            	if(startm<endm){
		            		if(starts<ends){
		            			return true;
		            		}else{
		            			return false;
		            		}
		            	}else{
		            		return false;
		            	}
		            }else{
		            	return false;
		            }
	            }
	            return true;
			},
			message: '结束时间不能小于起始时间！'
	    }
	});
	
	function addRw() {
		var type= $("#type").combobox('getValue');
		if(type==1){
		//日检
			var $list= $("#rwtable tr").eq(-1);
			var $li = $(
						'<tr style="height:40px" >'+
						'<td style="width:37%" align="center">'+
						'<input data-options="" type="text" id="ristart_'+rwid+'" name="start" class="easyui-textbox" value=""  style="height: 30px;width: 230px" />'+
						'</td>'+
						'<td style="width:37%" align="center">'+
						'<input data-options="" type="text" id="riend_'+rwid+'" name="end" class="easyui-textbox" value=""  style="height: 30px;width: 230px" />' +
						'</td>'+
						'<td align="center">'+
						'<button class="btn btn-info btn-sm" style="width:80px" data-toggle="tooltip" data-placement="left" onclick="removeTr($(this).parent().parent())" title="删除"><i class="fa fa-trash-o"></i> 删除</button>'+
						'</td>'+
						'</tr>'
			            );
			$list.after( $li );
			$('#ristart_'+rwid).timespinner({   
				required:true,
	    		showSeconds:true,
				validType:"equalstime['ristart_"+rwid+"']"		
	  		});  
	  		$('#riend_'+rwid).timespinner({   
	  			required:true,
	    		showSeconds:true,
				validType:"equalsendtime['riend_"+rwid+"']"	  
	  		}); 
	  		rwid=rwid+1;
		}else if(type==2){
		//周检
			var $list= $("#rwtable tr").eq(-1);
			var $li = $(
						'<tr style="height:40px" >'+
						'<td style="width:37%" align="center">'+
						'<input data-options="" type="text" id="zhoustart_'+rwid+'" name="start" class="easyui-textbox" value=""  style="height: 30px;width: 230px" />'+
						'</td>'+
						'<td style="width:37%" align="center">'+
						'<input data-options="" type="text" id="zhouend_'+rwid+'" name="end" class="easyui-textbox" value=""  style="height: 30px;width: 230px" />' +
						'</td>'+
						'<td align="center">'+
						'<button class="btn btn-info btn-sm" style="width:80px" data-toggle="tooltip" data-placement="left" onclick="removeTr($(this).parent().parent())" title="删除"><i class="fa fa-trash-o"></i> 删除</button>'+
						'</td>'+
						'</tr>'
			            );
			$list.after( $li );
			$('#zhoustart_'+rwid).combobox({  
			     panelHeight:'auto', 
			     editable:false,
			     required:true,
			     data: [{value:'1',text:'周一'},{value:'2',text:'周二'},
			            {value:'3',text:'周三'},{value:'4',text:'周四'},
			            {value:'5',text:'周五'},{value:'6',text:'周六'},{value:'7',text:'周日'}],
			     onSelect: function () {  
			           var startid = this.id;
			           var endid = "zhouend_"+startid.split("_")[1];
			           var start = $('#'+startid).combobox('getValue');
			           var end = $('#'+endid).combobox('getValue');
			           if(end!=''&&start!=''){
			           		if(start>end){
			           			$('#'+startid).combobox('setValue','');
			           			layer.msg("起始时间不能大于结束时间！",{time: 2000});
			           		}
			           }
			     }
	  		}); 
	  		$('#zhouend_'+rwid).combobox({   
	  		     panelHeight:'auto', 
			     editable:false,
			     required:true,
			     data: [{value:'1',text:'周一'},{value:'2',text:'周二'},
			            {value:'3',text:'周三'},{value:'4',text:'周四'},
			            {value:'5',text:'周五'},{value:'6',text:'周六'},{value:'7',text:'周日'}],
			     onSelect: function () {  
			           var endid = this.id;
			           var startid = "zhoustart_"+endid.split("_")[1];
			           var start = $('#'+startid).combobox('getValue');
			           var end = $('#'+endid).combobox('getValue');
			           if(end!=''&&start!=''){
			           		if(start>end){
			           			$('#'+endid).combobox('setValue','');
			           			layer.msg("结束时间不能小于起始时间！",{time: 2000});
			           		}
			           }
	       		 }  
	  		}); 
	  		rwid=rwid+1;
		}else if(type==3){
		//月检
		    var $list= $("#rwtable tr").eq(-1);
			var $li = $(
						'<tr style="height:40px" >'+
						'<td style="width:37%" align="center">'+
						'<input data-options="" type="text" id="yuestart_'+rwid+'" name="start" class="easyui-textbox" value=""  style="height: 30px;width: 230px" />'+
						'</td>'+
						'<td style="width:37%" align="center">'+
						'<input data-options="" type="text" id="yueend_'+rwid+'" name="end" class="easyui-textbox" value=""  style="height: 30px;width: 230px" />' +
						'</td>'+
						'<td align="center">'+
						'<button class="btn btn-info btn-sm" style="width:80px" data-toggle="tooltip" data-placement="left" onclick="removeTr($(this).parent().parent())" title="删除"><i class="fa fa-trash-o"></i> 删除</button>'+
						'</td>'+
						'</tr>'
			            );
			$list.after( $li );
			$('#yuestart_'+rwid).combobox({  
			     editable:false,
			     required:true,
			     data: [{value:'1',text:'1日'},{value:'2',text:'2日'},{value:'3',text:'3日'},{value:'4',text:'4日'},{value:'5',text:'5日'},
						{value:'6',text:'6日'},{value:'7',text:'7日'},{value:'8',text:'8日'},{value:'9',text:'9日'},{value:'10',text:'10日'},
						{value:'11',text:'11日'},{value:'12',text:'12日'},{value:'13',text:'13日'},{value:'14',text:'14日'},{value:'15',text:'15日'},
				        {value:'16',text:'16日'},{value:'17',text:'17日'},{value:'18',text:'18日'},{value:'19',text:'19日'},{value:'20',text:'20日'},
				        {value:'21',text:'21日'},{value:'22',text:'22日'},{value:'23',text:'23日'},{value:'24',text:'24日'},{value:'25',text:'25日'},
				        {value:'26',text:'26日'},{value:'27',text:'27日'},{value:'28',text:'28日'},{value:'29',text:'29日'},{value:'30',text:'30日'},
				        {value:'31',text:'31日'}],
			     onSelect: function () {  
			           var startid = this.id;
			           var endid = "yueend_"+startid.split("_")[1];
			           var start = $('#'+startid).combobox('getValue');
			           var end = $('#'+endid).combobox('getValue');
			           if(end!=''&&start!=''){
			           		if(start>end){
			           			$('#'+startid).combobox('setValue','');
			           			layer.msg("起始时间不能大于结束时间！",{time: 2000});
			           		}
			           }
			     }
	  		}); 
	  		$('#yueend_'+rwid).combobox({   
	  		     editable:false,
	  		     required:true,
			     data: [{value:'1',text:'1日'},{value:'2',text:'2日'},{value:'3',text:'3日'},{value:'4',text:'4日'},{value:'5',text:'5日'},
						{value:'6',text:'6日'},{value:'7',text:'7日'},{value:'8',text:'8日'},{value:'9',text:'9日'},{value:'10',text:'10日'},
						{value:'11',text:'11日'},{value:'12',text:'12日'},{value:'13',text:'13日'},{value:'14',text:'14日'},{value:'15',text:'15日'},
				        {value:'16',text:'16日'},{value:'17',text:'17日'},{value:'18',text:'18日'},{value:'19',text:'19日'},{value:'20',text:'20日'},
				        {value:'21',text:'21日'},{value:'22',text:'22日'},{value:'23',text:'23日'},{value:'24',text:'24日'},{value:'25',text:'25日'},
				        {value:'26',text:'26日'},{value:'27',text:'27日'},{value:'28',text:'28日'},{value:'29',text:'29日'},{value:'30',text:'30日'},
				        {value:'31',text:'31日'}],
			     onSelect: function () {  
			           var endid = this.id;
			           var startid = "yuestart_"+endid.split("_")[1];
			           var start = $('#'+startid).combobox('getValue');
			           var end = $('#'+endid).combobox('getValue');
			           if(end!=''&&start!=''){
			           		if(start>end){
			           			$('#'+endid).combobox('setValue','');
			           			layer.msg("结束时间不能小于起始时间！",{time: 2000});
			           		}
			           }
	       		 }  
	  		}); 
	  		rwid=rwid+1;
		}else if(type==4){
		//年检
		    var $list= $("#rwtable tr").eq(-1);
			var $li = $(
						'<tr style="height:40px" >'+
						'<td style="width:37%" align="center">'+
						'<input data-options="" type="text" id="nianstart_'+rwid+'" name="start" class="easyui-textbox" value=""  style="height: 30px;width: 230px" />'+
						'</td>'+
						'<td style="width:37%" align="center">'+
						'<input data-options="" type="text" id="nianend_'+rwid+'" name="end" class="easyui-textbox" value=""  style="height: 30px;width: 230px" />' +
						'</td>'+
						'<td align="center">'+
						'<button class="btn btn-info btn-sm" style="width:80px" data-toggle="tooltip" data-placement="left" onclick="removeTr($(this).parent().parent())" title="删除"><i class="fa fa-trash-o"></i> 删除</button>'+
						'</td>'+
						'</tr>'
			            );
			$list.after( $li );
			$('#nianstart_'+rwid).combobox({  
			     editable:false,
			     required:true,
			     data: [{value:'1',text:'一月'},{value:'2',text:'二月'},
			            {value:'3',text:'三月'},{value:'4',text:'四月'},
			            {value:'5',text:'五月'},{value:'6',text:'六月'},
			            {value:'7',text:'七月'},{value:'8',text:'八月'},
			            {value:'9',text:'九月'},{value:'10',text:'十月'},
			            {value:'11',text:'十一月'},{value:'12',text:'十二月'}],
			     onSelect: function () {  
			           var startid = this.id;
			           var endid = "nianend_"+startid.split("_")[1];
			           var start = $('#'+startid).combobox('getValue');
			           var end = $('#'+endid).combobox('getValue');
			           if(end!=''&&start!=''){
			           		if(start>end){
			           			$('#'+startid).combobox('setValue','');
			           			layer.msg("起始时间不能大于结束时间！",{time: 2000});
			           		}
			           }
			     } 
	  		}); 
	  		$('#nianend_'+rwid).combobox({  
			     editable:false,
			     required:true,
			     data: [{value:'1',text:'一月'},{value:'2',text:'二月'},
			            {value:'3',text:'三月'},{value:'4',text:'四月'},
			            {value:'5',text:'五月'},{value:'6',text:'六月'},
			            {value:'7',text:'七月'},{value:'8',text:'八月'},
			            {value:'9',text:'九月'},{value:'10',text:'十月'},
			            {value:'11',text:'十一月'},{value:'12',text:'十二月'}],
			     onSelect: function () {  
			           var endid = this.id;
			           var startid = "nianstart_"+endid.split("_")[1];
			           var start = $('#'+startid).combobox('getValue');
			           var end = $('#'+endid).combobox('getValue');
			           if(end!=''&&start!=''){
			           		if(start>end){
			           			$('#'+endid).combobox('setValue','');
			           			layer.msg("结束时间不能小于起始时间！",{time: 2000});
			           		}
			           }
	       		 }   
	  		}); 
	  		rwid=rwid+1;
		}else{
			layer.msg("您还没有选择班次类型!",{time: 2000});
		}
		
	}
	
	//删除指定行的任务
	function removeTr(obj) {
		obj.remove();
		rwid = rwid - 1;
	}
	
	function doSubmit(){
		var bctype = $("#type").combobox("getValue"); 
		var start = new Array();
		var end = new Array();
		$("input[name='start']").each(function(index,item){
			start[index] = $(this).val();
		});
		$("input[name='end']").each(function(index,item){
			end[index] = $(this).val();
		});
		for(i = 0; i < start.length; i++) {
			for(j = 0; j < start.length; j++){
				if((start[i]>start[j]&&start[i]<end[j])||(end[i]>start[j]&&end[i]<end[j])){
					layer.msg("任务设置不能穿插重复!",{time: 3000});
					return;
				}
			}
		}
		var divtxt=$("#xjryList").text();
		var divtxt2=$("#jcdList").text();
		if((divtxt==null||divtxt=='')&&(divtxt2!=null&&divtxt2!='')){
			top.layer.confirm('您还没有选择任何巡检人员,是否继续!', {
				icon : 3,
				title : '提示'
			}, function(index) {
				$("#inputForm").serializeObject();
				$("#inputForm").submit();
				top.layer.close(index);
			});
		}else if((divtxt2==null||divtxt2=='')&&(divtxt!=null&&divtxt!='')){
			top.layer.confirm('您还没有选择任何检查点,是否继续!', {
				icon : 3,
				title : '提示'
			}, function(index) {
				$("#inputForm").serializeObject();
				$("#inputForm").submit();
				top.layer.close(index);
			});
		}else if((divtxt==null||divtxt=='')&&(divtxt2==null||divtxt2=='')){
			top.layer.confirm('您还没有选择任何检查点和巡检人员，是否继续!', {
				icon : 3,
				title : '提示'
			}, function(index) {
				$("#inputForm").serializeObject();
				$("#inputForm").submit();
				top.layer.close(index);
			});
		}else {
			$("#inputForm").serializeObject();
			$("#inputForm").submit();
		}
	}

	$(function(){
  		$("#type").combobox({
            onChange: function(n,o){
               $("#rwtable  tr:not(:first)").remove();
               rwid = 1;
            } 
        });  
	
	    var flag=true;
		$('#inputForm').form({
			onSubmit : function() {
				var isValid = $(this).form('validate');
		    	if(isValid&&flag){
		    		flag=false;
		    		$.jBox.tip("正在提交，请稍等...",'loading',{opacity:0});
		    		return true;
		    	}
				return false; // 返回false终止表单提交
			},  
		    success:function(data){ 
		         $.jBox.closeTip();
		    	if(data=='success')
		    		parent.layer.open({icon:1,title: '提示',offset: 'rb',content: '操作成功！',shade: 0 ,time: 2000 });
		    	else
		    		parent.layer.open({icon:2,title: '提示',offset: 'rb',content: '操作失败！',shade: 0 ,time: 2000 });
		    	parent.dg.datagrid('reload');
		    	parent.dg2.datagrid('reload');
		    	parent.layer.close(index);//关闭对话框。
		    	
		    }    
		});
	
	});
	
</script>
</body>
</html>