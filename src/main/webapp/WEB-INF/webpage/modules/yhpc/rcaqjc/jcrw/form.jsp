<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>检查任务管理</title>
	<meta name="decorator" content="default"/>
	<style type="text/css">
   .tree-title{white-space:pre-wrap;display:inline-block;height: 100%;width: 80%}
   .tree-node {white-space:pre-wrap;height: 100%;}
	</style>
</head>
<body>

     <form id="inputForm" action="${ctx}/yhpc/jcrw/${action}"  method="post" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
			  	<tr>
					<td class="width-20 active"><label class="pull-right">任务名称：</label></td>
					<td class="width-30" colspan="3">
						<input type="text" name="M7" class="easyui-textbox" value="${jcrw.m7 }"  style="width: 100%;height: 30px;  validType:'length[0,50]'" data-options="required:true" />
					</td>
			  	</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">部门：</label></td>
					<td class="width-30" colspan="2"><input name="M1" id="M1" value="${jcrw.m1}" type="text" class="easyui-combobox"  style="width: 100%;height: 30px;"
						data-options="panelHeight:'150px', editable:false "/></td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">检查人：</label></td>
					<td class="width-30" colspan="2">
						<input value="${jcrw.jcr }" name="M2_1" id="M2_1" style="width: 100%;height: 60px;" class="easyui-textbox"
							data-options="multiline:true,panelHeight:'100',required:'true',textField:'text',readonly:true" />
					</td>
					<td>
						<a  href="javascript:void(0)"  id="btn" class="btn btn-info btn-sm" data-toggle="tooltip" data-placement="left" onclick="addjcr()" title="选择巡检人员"><i class="fa fa-plus"></i> 选择巡检人员</a>
					</td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">计划检查时间：</label></td>
					<td class="width-30" ><input id="M3" name="M3" class="easyui-datebox" value="${jcrw.m3 }" style="width: 100%;height: 30px;" jcrweditable:false, required:'true' " /></td>
					<td class="width-20 active"><label class="pull-right">类别：</label></td>
					<td class="width-30" ><input name="M5" id="M5" class="easyui-combobox" value="${jcrw.m5 }" style="width: 100%;height: 30px;" data-options="required:true, validType:'length[0,100]' "/></td>
				</tr>
								
				<tr>
					<td class="width-20 active"><label class="pull-right">检查内容：</label></td>
					<td class="width-80" colspan="3" >
						<table id="tt" style="" ></table>
					</td>
				</tr>
				<input type="hidden" name="M2" value="${jcrw.m2}">
				<c:if test="${not empty jcrw.id}">
					<input type="hidden" name="ID" value="${jcrw.id}" />
					<input type="hidden" name="ID1" value="${jcrw.id1}" />
					<input type="hidden" name="ID2" value="${jcrw.id2}" />
					<input type="hidden" name="S1"
						value="<fmt:formatDate value="${jcrw.s1}" pattern="yyyy-MM-dd HH:mm:ss"  />" />
					<input type="hidden" name="S3" value="${jcrw.s3}" />
					<input type="hidden" id="M6" name="M6" value="${jcrw.m6}" />
				</c:if>
				<input type="hidden" id="M4" name="M4" value="${jcrw.m4}" />
				</tbody>
			</table>
       </form>
 
<script type="text/javascript">
var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
var validateForm;

//得到当前日期
function myformatter(date){  
    var y = date.getFullYear();  
    var m = date.getMonth()+1;  
    var d = date.getDate();  
    return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);  
}  
 
function doSubmit(){
	var nodes = $('#tt').tree('getChecked');
	var ids="";
	for(var i=0;i<nodes.length;i++){
		if(ids==""){
			ids=nodes[i].id;
		}else{
			if(nodes[i].id!=0){
				ids=ids+","+nodes[i].id;
			}
		}
	}
	$("#M4").val(ids);
	$("#inputForm").submit(); 
}

$(function(){
    //第一次添加时,设置默认时间  ;
	var curr_time = new Date();
	if('${action}'=='create'){    
		$("#M3").datebox("setValue",myformatter(curr_time)); 	
	}
	var flag=true;
	$('#inputForm').form({    
	    onSubmit: function(){    
	    	var isValid = $(this).form('validate');
	    	if(isValid&&flag){
	    		flag=false;
	    		$.jBox.tip("正在提交，请稍等...",'loading',{opacity:0});
	    		return true;
	    	}
			return false;	// 返回false终止表单提交
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

function content(type){
	var jd;
	var jd1 = '';
	$('#tt').tree({
    	url: ctx+'/yhpc/jcrw/jcnr',
    	queryParams:{"lx":type},
    	checkbox: true,
    	width:100,
    	formatter:function(node){
    	    /* var patt =/(\d{1,2})/g;
    	    if(patt.test(node.text))
    			return node.text.replace(patt,"<br>$1").replace("<br>", "");
    		else  */
    			jd = node.id;
				jd1 += (jd + ",");
    		    return node.text;
    	    
		},
		
    	onLoadSuccess:function(){
    		if ('${action}' == 'create') {
    			var array = jd1.split(",");
    			for(var i=0;i<array.length;i++) {
    				var node = $('#tt').tree('find',array[i]);
	         		$('#tt').tree('check',node.target);
    			}
    		}
    		if ('${action}' == 'update') {
			var m4=	$("#M4").val();
			if(m4!=null&&m4!=""){
			var array = m4.split(",");
    		for(var i=0;i<array.length;i++)  
       			{  
         			var node = $('#tt').tree('find',array[i]);
         			$('#tt').tree('check',node.target);  
       			} 
       		} 	
		  }
    	} 
	});
}


//检查类型下拉框
$("#M5").combobox({
	panelHeight:'120px',
	editable:true ,
	valueField: 'text',
	textField: 'text',
	url:'${ctx}/yhpc/rcjcbk/xwlblist', 
	onLoadSuccess:function (param, success, error) {
	   var lx=$('#M5').combobox('getValue');
	    if(lx!=''){
	    	content(lx);
	    }
	 },
	 onSelect: function(rec){
		 content(rec.text);
	 }
});

//部门下拉框
$("#M1").combobox({
	panelHeight:'120px',
	editable:true ,
	valueField: 'id',
	textField: 'text',
	multiple: true,
	required: true,
	url:'${ctx}/system/department/deptjson', 
	onLoadSuccess:function (param, success, error) {
	   // var M1=$('#M1').combobox('getValue');
	    $.ajax({  
		       url:'${ctx}/yhpc/jcrw/bmrylist',
	           // data:{'depid':M1},
	           dataType : 'json',
	           type : 'POST',
	           success: function (data){
	           	 //$('#M2').combobox('loadData', data); 
	           }
		});	    	
	 },
	onChange: function(rec){
		var M1=$('#M1').combobox('getValues').join(",");
		//console.log(M1);
		/* $.ajax({  
		       url:'${ctx}/yhpc/jcrw/bmrylist',
		       data:{'depid':M1},
		       dataType : 'json',
		       type : 'POST',
		       success: function (data){
		       		$('#M2').combobox('clear');
		       		$('#M2').combobox('loadData', data);
		       }
		});	 */
	 }
});

function addjcr(){
	var M1=$('#M1').combobox('getValues').join(",");
   	if(M1 ==null || M1 ==""){
   		layer.msg('请先选择部门', {time: 3000});
   	}else{
   		layer.open({
			type: 2,  
			title:'添加检查任务人',
			content: ctx + '/yhpc/jcrw/addjcr?depids='+M1+'&jcrids='+$("[name='M2']").val(),
			area: ['95%', '75%'],
	        maxmin: false,
		    btn: ['确定', '关闭'],
		    yes: function(index, layero){
		    	 var body = layer.getChildFrame('body', index);
		         var iframeWin = layero.find('iframe')[0];
		         var checkPersons = iframeWin.contentWindow.dg.datagrid('getChecked');
		         var jcrtext='';
		         var ids = '';
		         $.each(checkPersons,function(index, el){
		        	 var id = el.id;// 子页面勾选的检查人
		        	 if(ids == '') {
		        		 ids = id;
		        	 }else{
		        		 ids += ',' + id;
		        	 }
		        	 if(jcrtext == '') {
		        		 jcrtext = el.text;
		        	 }else{
		        		 jcrtext += ',' + el.text;
		        	 }
		         }) 
		         $("[name='M2']").val(ids);
		         $("#M2_1").textbox('setValue',jcrtext);
		         layer.close(index);
			},
			cancel: function(index){ 
				layer.close(index);
		    }
		});
   	}	
}
</script>
</body>
</html>