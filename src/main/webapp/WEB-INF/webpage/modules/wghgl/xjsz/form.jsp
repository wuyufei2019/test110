<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>网格点巡检班次任务设置</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/model/js/wghgl/xjsz/index.js?v=2"></script>
</head>
<body>

     <form id="inputForm" action="${ctx}/wghgl/xjsz/${action}"  method="post" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right">班次名称：</label></td>
					<td class="width-35"><input data-options="required:'true',validType:'length[0,100]'" type="text" id="name" name="name" class="easyui-textbox" value="${bcrw.name}"  style="height: 30px;width: 100%" /></td>
					<td class="width-15 active"><label class="pull-right">班次类型：</label></td>
					<td class="width-35"><input data-options="required:'true',panelHeight:'auto',editable:false,data: [
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
					<td class="width-15 active"><label class="pull-right">所属网格：</label></td>
					<td class="width-35">
					<input type="text" id="wgid" name="wgid" class="easyui-combotree" value="${bcrw.wgid}"  style="height: 30px;width: 100%" 
					       data-options="required:'true',editable:false,valueField: 'id',textField: 'text',url:'${ctx}/system/admin/xzqy/idjson',prompt: '选择网格' " />
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
			var bctype = '${bcrw.type}';
			var rwsjlist = '${rwsjlist}';
			sjlist=JSON.parse(rwsjlist);  
			if(bctype == 3){
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
			    		data: [{value:'1',text:'1日'},{value:'2',text:'2日'},{value:'3',text:'3日'},{value:'4',text:'4日'},{value:'5',text:'5日'},
							   {value:'6',text:'6日'},{value:'7',text:'7日'},{value:'8',text:'8日'},{value:'9',text:'9日'},{value:'10',text:'10日'},
							   {value:'11',text:'11日'},{value:'12',text:'12日'},{value:'13',text:'13日'},{value:'14',text:'14日'},{value:'15',text:'15日'},
					       	   {value:'16',text:'16日'},{value:'17',text:'17日'},{value:'18',text:'18日'},{value:'19',text:'19日'},{value:'20',text:'20日'},
					           {value:'21',text:'21日'},{value:'22',text:'22日'},{value:'23',text:'23日'},{value:'24',text:'24日'},{value:'25',text:'25日'},
					           {value:'26',text:'26日'},{value:'27',text:'27日'},{value:'28',text:'28日'},{value:'29',text:'29日'},{value:'30',text:'30日'},
					           {value:'31',text:'31日'}]
	  				}); 
			  		$('#yueend_'+rwid).combobox({   
			  		     editable:false,
					     data: [{value:'1',text:'1日'},{value:'2',text:'2日'},{value:'3',text:'3日'},{value:'4',text:'4日'},{value:'5',text:'5日'},
								{value:'6',text:'6日'},{value:'7',text:'7日'},{value:'8',text:'8日'},{value:'9',text:'9日'},{value:'10',text:'10日'},
								{value:'11',text:'11日'},{value:'12',text:'12日'},{value:'13',text:'13日'},{value:'14',text:'14日'},{value:'15',text:'15日'},
						        {value:'16',text:'16日'},{value:'17',text:'17日'},{value:'18',text:'18日'},{value:'19',text:'19日'},{value:'20',text:'20日'},
						        {value:'21',text:'21日'},{value:'22',text:'22日'},{value:'23',text:'23日'},{value:'24',text:'24日'},{value:'25',text:'25日'},
						        {value:'26',text:'26日'},{value:'27',text:'27日'},{value:'28',text:'28日'},{value:'29',text:'29日'},{value:'30',text:'30日'},
						        {value:'31',text:'31日'}]
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
					     data: [{value:'1',text:'一月初'},{value:'2',text:'二月初'},
					            {value:'3',text:'三月初'},{value:'4',text:'四月初'},
					            {value:'5',text:'五月初'},{value:'6',text:'六月初'},
					            {value:'7',text:'七月初'},{value:'8',text:'八月初'},
					            {value:'9',text:'九月初'},{value:'10',text:'十月初'},
					            {value:'11',text:'十一月初'},{value:'12',text:'十二月初'}] 
	  				}); 
			  		$('#nianend_'+rwid).combobox({  
					     editable:false,
					     data: [{value:'1',text:'一月末'},{value:'2',text:'二月末'},
					            {value:'3',text:'三月末'},{value:'4',text:'四月末'},
					            {value:'5',text:'五月末'},{value:'6',text:'六月末'},
					            {value:'7',text:'七月末'},{value:'8',text:'八月末'},
					            {value:'9',text:'九月末'},{value:'10',text:'十月末'},
					            {value:'11',text:'十一月末'},{value:'12',text:'十二月末'}] 
			  		}); 
	  				rwid=rwid+1; 
	  			});    
	  		}
	}
	
	function addRw() {
		var type= $("#type").combobox('getValue');
		if(type==3){
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
			     data: [{value:'1',text:'1日'},{value:'2',text:'2日'},{value:'3',text:'3日'},{value:'4',text:'4日'},{value:'5',text:'5日'},
						{value:'6',text:'6日'},{value:'7',text:'7日'},{value:'8',text:'8日'},{value:'9',text:'9日'},{value:'10',text:'10日'},
						{value:'11',text:'11日'},{value:'12',text:'12日'},{value:'13',text:'13日'},{value:'14',text:'14日'},{value:'15',text:'15日'},
				        {value:'16',text:'16日'},{value:'17',text:'17日'},{value:'18',text:'18日'},{value:'19',text:'19日'},{value:'20',text:'20日'},
				        {value:'21',text:'21日'},{value:'22',text:'22日'},{value:'23',text:'23日'},{value:'24',text:'24日'},{value:'25',text:'25日'},
				        {value:'26',text:'26日'},{value:'27',text:'27日'},{value:'28',text:'28日'},{value:'29',text:'29日'},{value:'30',text:'30日'},
				        {value:'31',text:'31日'}]
	  		}); 
	  		$('#yueend_'+rwid).combobox({   
	  		     editable:false,
			     data: [{value:'1',text:'1日'},{value:'2',text:'2日'},{value:'3',text:'3日'},{value:'4',text:'4日'},{value:'5',text:'5日'},
						{value:'6',text:'6日'},{value:'7',text:'7日'},{value:'8',text:'8日'},{value:'9',text:'9日'},{value:'10',text:'10日'},
						{value:'11',text:'11日'},{value:'12',text:'12日'},{value:'13',text:'13日'},{value:'14',text:'14日'},{value:'15',text:'15日'},
				        {value:'16',text:'16日'},{value:'17',text:'17日'},{value:'18',text:'18日'},{value:'19',text:'19日'},{value:'20',text:'20日'},
				        {value:'21',text:'21日'},{value:'22',text:'22日'},{value:'23',text:'23日'},{value:'24',text:'24日'},{value:'25',text:'25日'},
				        {value:'26',text:'26日'},{value:'27',text:'27日'},{value:'28',text:'28日'},{value:'29',text:'29日'},{value:'30',text:'30日'},
				        {value:'31',text:'31日'}]
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
			     data: [{value:'1',text:'一月初'},{value:'2',text:'二月初'},
			            {value:'3',text:'三月初'},{value:'4',text:'四月初'},
			            {value:'5',text:'五月初'},{value:'6',text:'六月初'},
			            {value:'7',text:'七月初'},{value:'8',text:'八月初'},
			            {value:'9',text:'九月初'},{value:'10',text:'十月初'},
			            {value:'11',text:'十一月初'},{value:'12',text:'十二月初'}] 
	  		}); 
	  		$('#nianend_'+rwid).combobox({  
			     editable:false,
			     data: [{value:'1',text:'一月末'},{value:'2',text:'二月末'},
			            {value:'3',text:'三月末'},{value:'4',text:'四月末'},
			            {value:'5',text:'五月末'},{value:'6',text:'六月末'},
			            {value:'7',text:'七月末'},{value:'8',text:'八月末'},
			            {value:'9',text:'九月末'},{value:'10',text:'十月末'},
			            {value:'11',text:'十一月末'},{value:'12',text:'十二月末'}] 
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

	$(function(){
  		$("#type").combobox({
            onChange: function(n,o){
               $("#rwtable  tr:not(:first)").remove();
               rwid = 1;
            } 
    	});  
    });
		
	function doSubmit(){
		$("#inputForm").serializeObject();
		$("#inputForm").submit();
	}
	
	$(function(){
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
		    	parent.layer.close(index);//关闭对话框。
		    }    
		});
	
	});
	
</script>
</body>
</html>