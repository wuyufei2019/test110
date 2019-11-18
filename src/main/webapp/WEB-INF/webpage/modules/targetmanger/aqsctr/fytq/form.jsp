<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>费用提取管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>

     <form id="inputForm" action="${ctx}/aqsctr/fytq/${action}"  method="post" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
			  	<c:if test="${usertype != '1' and action eq 'create'}">
					<tr>
						<td class="width-15 active"><label class="pull-right">企业名称：</label></td>
						<td class="width-35" colspan="3">
							<input value="${fytq.qyid }" id="_qyid" name="qyid" style="width: 100%;height: 30px;"
									class="easyui-combobox"
									data-options="required:'true',valueField: 'id',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson'" />
						</td>
					</tr>
			    </c:if>
			    <c:if test="${usertype != '1' and action eq 'update'}">
					<tr>  
						<td class="width-15 active" ><label class="pull-right">企业名称：</label></td>
						<td class="width-35" colspan="3">
							<input value="${fytq.qyid }" id="_qyid" name="qyid" style="width: 100%;height: 30px;"
										class="easyui-combobox"
										data-options="editable:false, disabled:true, valueField: 'id',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson'" /></td>
					</tr>
				</c:if>

				<tr >
					<td class="width-15 active"><label class="pull-right">年度：</label></td>
					<td class="width-35">
						<input style="width: 100%;height: 30px;" id="M1" name="M1" class="easyui-textbox" value="${fytq.m1 }" data-options="editable:false ,validType:'length[0,50]'"/>
					</td>

					<td class="width-15 active"><label class="pull-right">月份：</label></td>
					<td class="width-35">
						<input style="width: 100%;height: 30px;" id="M1_2" name="M1_2" class="easyui-combobox" value="${fytq.m1_2 }" data-options="editable:false ,validType:'length[0,50]',data: [
						         {value:'1月',text:'1月'},
						         {value:'2月',text:'2月'},
						         {value:'3月',text:'3月'},
						         {value:'4月',text:'4月'},
						         {value:'5月',text:'5月'},
						         {value:'6月',text:'6月'},
						         {value:'7月',text:'7月'},
						         {value:'8月',text:'8月'},
						         {value:'9月',text:'9月'},
						         {value:'10月',text:'10月'},
						         {value:'11月',text:'11月'},
						         {value:'12月',text:'12月'}
						        	] "/>
					</td>
				</tr>

				<tr>
					<td class="width-15 active"><label class="pull-right">销售收入(万元)：</label></td>
					<td class="width-35"><input style="width: 100%;height: 30px;" required="true" id="M2" name="M2" class="easyui-textbox" value="${fytq.m2 }" data-options="validType:['number','length[0,10]'] " /></td>
					<td class="width-15 active"><label class="pull-right">行业类型：</label></td>
					<td class="width-35"><input type="text" id="M3" name="M3" required="true" class="easyui-combobox" style="height: 30px; width: 100%;" value="${fytq.m3}" data-options="panelHeight:'100px' ,editable:true ,valueField:'text',textField:'text',url:'${ctx }/aqsctr/fytq/lxlist'" /></td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">提取标准：</label></td>
					<td class="width-85" colspan="3">
						<input style="width: 100%;height: 80px;" id="M4" name="M4" class="easyui-textbox" value="${fytq.m4 }" data-options="multiline:true, editable:true " />
					</td>
				</tr>

				<tr>
					<td class="width-15 active"><label class="pull-right">提取数(万元)：</label></td>
					<td class="width-85" colspan="3">
						<input style="width: 100%;height: 30px;" id="M5" name="M5" class="easyui-textbox" value="${fytq.m5 }" data-options="validType:['number','length[0,10]'] "/>
					</td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">备注：</label></td>
					<td class="width-35" colspan="3"><input id="M6" name="M6" class="easyui-textbox" style="width: 100%;height: 80px;" value="${fytq.m6 }" data-options="multiline:true, editable:true " /></td>
				</tr>
				
			    <tr>
					<td class="width-20 active"><label class="pull-right">附件：</label></td>
					<td class="width-30" colspan="3">
						<div id="uploader_ws_m7">
					    <div id="m7fileList" class="uploader-list" ></div>
					    <div id="filePicker">选择文件</div>
					</div>
					</td>
				</tr>

				<c:if test="${not empty fytq.ID}">
					<input type="hidden" name="ID" value="${fytq.ID}" />
					<input type="hidden" name="qyid" value="${fytq.qyid}" />
					<input type="hidden" name="S1"
						value="<fmt:formatDate value="${fytq.s1}" pattern="yyyy-MM-dd HH:mm:ss"  />" />
					<input type="hidden" name="S3" value="${fytq.s3}" />
				</c:if>
				
			</table>

		  	<tbody>
       </form>
 
<script type="text/javascript">
var usertype=${usertype};
var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
var validateForm;	

//年份下拉框初始化
$("#M1").combobox({ 
	valueField:'year',    
	textField:'year',  
	panelHeight:'auto'
});

//根据行业类型获取标准并计算提取数
$("#M3").combobox({ 
	onSelect: function(){
		var type=$("#M3").combobox('getValue');
		var m2=$("#M2").textbox('getValue');
		if(m2){
		$.ajax({
			url:ctx+"/aqsctr/fytq/count",
			data:{"m2":m2,"type":type},
			dataType : 'json',
			success: function(data){
				$("#M4").textbox('setValue',data.standard);
				$("#M5").textbox('setValue',data.sum);
			}
		});
		}
	}
});

var data = [];
var thisYear;
var startYear=new Date().getUTCFullYear()+2;

for(var i=0;i<6;i++){
	thisYear=startYear-i;
	data.push({"year":thisYear});
}
	
$("#M1").combobox("loadData", data);//下拉框加载数据
if('${action}' == 'create') {{
	//获取今年年份,设置年度下拉框默认值
	var nowyear=new Date().getUTCFullYear();
	$("#M1").combobox("setValue", nowyear);//下拉框加载数据
}}

function doSubmit(){
	$("#inputForm").serializeObject();
	$("#M3").combobox("setValue",$("#M3").combotree("getText"));
	$("#inputForm").submit(); 
}

$(function(){
	$("input",$("#M2").next("span")).blur(function(){
		var type=$("#M3").combobox('getValue');
		var m2=$("#M2").textbox('getValue');
		if(type){
		$.ajax({
			url:ctx+"/aqsctr/fytq/count",
			data:{"m2":m2,"type":type},
			dataType : 'json',
			success: function(data){
					$("#M4").textbox('setValue',data.standard);
					$("#M5").textbox('setValue',data.sum);
				}
			});
		}
	});
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
	    	parent.layer.close(index);//关闭对话框。
	    }    
	});

});


$list2 = $('#m7fileList'); //文件上传

var fileuploader = WebUploader.create({

    auto: true,

    swf:  '${ctxStatic}/webuploader-0.1.5/Uploader.swf',

    server: '${ctx}/kindeditor/upload?dir=file',
    		
    pick: {
    	id:'#filePicker',
    	multiple : false,
    },
    duplicate :true  
});

fileuploader.on( 'uploadProgress', function( file, percentage ) {
	$.jBox.tip("正在上传，请稍等...",'loading',{opacity:0});
});

// 文件上传成功 
fileuploader.on( 'uploadSuccess', function( file ,res) {
	$.jBox.closeTip();
	if(res.error==0){
		var $li = $(
	            "<div id=\"" + file.id + "\" style=\"margin-bottom: 10px;\">" +
	            	"<a style=\"color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;\" onclick=\"window.open('"+res.url+"')\">"+res.fileName+"</a>"+
	            	"<span class=\"ss\" onClick=\"removeFile('"+file.id+"')\" style=\"cursor: pointer\">删除</span>"+
	            "</div>"
	            );

	    
	    $list2.append( $li );
 
		var newurl=res.url+"||"+res.fileName;
		
		var $input = $('<input id="input_'+file.id+'" type="hidden" name="M7" value="'+newurl+'"/>');
		
		$('#uploader_ws_m7').append( $input );
	}else{
		layer.msg(res.message,{time: 3000});
	}
});

// 负责预览图的销毁
   function removeFile(fileid) {
   	 $("#"+fileid).remove();
   	 $("#input_"+fileid).remove();
   };
   
	if('${action}' == 'update'){
		var fjUrl = '${fytq.m7}';
		console.log(fjUrl);
		if(fjUrl!=null&&fjUrl!=''){
			arry =fjUrl.split(",");
			for(var i=0;i<arry.length;i++){
				var arry2 =arry[i].split("||");
				var id="ws_fj_"+i;
				var $li = $(
			            "<div id=\"" + id + "\" style=\"margin-bottom: 10px;\">" +
			            	"<a style=\"color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;\" onclick=\"window.open('"+arry2[0]+"')\">"+arry2[1]+"</a>"+
			            	"<span class=\"ss\" onClick=\"removeFile('"+id+"')\" style=\"cursor: pointer\">删除</span>"+
			            "</div>"
			            );
			    $list2.append( $li );

			    var $input = $('<input id="input_'+id+'" type="hidden" name="M7" value="'+arry[i]+'"/>');
				$('#uploader_ws_m7').append( $input );
			}
		}
	}
















</script>
</body>
</html>