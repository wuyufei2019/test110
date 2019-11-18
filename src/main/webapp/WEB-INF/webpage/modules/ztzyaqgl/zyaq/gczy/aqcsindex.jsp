<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>安全措施</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/model/js/ztzyaqgl/zyaq/gczy/aqcsindex.js?v=1.0"></script>
</head>
<body >
<table id="zyaqgl_gczy_dg" ></table>

<table id="csdata" class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
	 <tbody>
		<tr>
			<td class="width-30 active"><label class="pull-right">安全交底：</label></td>
			<td class="width-70" >
				<div id="uploader_ws_m21_4">
			    <div id="m21_4fileList" class="uploader-list" ></div>
			    <div id="filePicker">选择文件</div>
			</div> 
			</td>
		</tr>
		
		<tr>
			<td class="width-30 active"><label class="pull-right">施工方案：</label></td>
			<td class="width-70" >
				<div id="uploader_ws_m21_5">
			    <div id="m21_5fileList" class="uploader-list" ></div>
			    <div id="filePicker2">选择文件</div>
			</div> 
			</td>
		</tr>
		
		<tr>
			<td class="width-30 active"><label class="pull-right">是否涉及外来方：</label></td>
			<td class="width-70" >
				<input type="radio" value="涉及" class="i-checks" id="spflag" name="spflag" />涉及
				<input type="radio" value="不涉及" class="i-checks" id="spflag" name="spflag" checked="checked"  />不涉及
			</div> 
			</td>
		</tr>	
		
		<tr id="wlf" style="display:none" >
			<td class="width-30 active"><label class="pull-right">外来方名称：</label></td>
			<td class="width-70" >
				<!-- 负责传值 -->
				<div id="wlfIDs">
					<input id="wlfids" type="hidden" name="wlfids" />
					<a  href="javascript:void(0)"  class="btn btn-info btn-sm" data-toggle="tooltip" data-placement="left" onclick="openwlflist()" title="选择外来方"><i class="fa fa-plus"></i> 选择外来方</a><span id="tip" style="color:red"></span>
					<div id="wlfList"></div>
				</div>
			</td>
		</tr>
		
		<tr id="wlfname">
		
		</tr>				   			   			   
	<tbody>
</table>

<script type="text/javascript">
	var flag=true;
	var index2 = parent.layer.getFrameIndex(window.name); //获取窗口索引
	var userid='${userid}';
	id1='${id1}';
	type='${type}';

	$('.i-checks').on('ifChecked', function(event){    
	    var selectedvalue = $(event.target).val();
	    if(selectedvalue=='涉及'){
	    	$("#wlf").css("display", "");
	    	$("#wlfdw").combobox({
				url: '${ctx}/ztzyaqgl/xgfpdjh/xgfdwjson',
				method: 'get',
	            valueField: 'id',//绑定字段ID
	            textField: 'text',//绑定字段Name
	            required:true,
	            panelHeight: '100',//自适应
	            multiple: true,
	            onClick: function () {
	                var selid= $("#wlfdw").combobox('getValues');
	                var seltxt= $("#wlfdw").combobox('getText');
	            }
	    	});
	    }else{
	    	$("#wlf").css("display", "none");
	    }
	});
	
	function doSubmit(){
	$('#zyaqgl_gczy_dg').datagrid('endEdit', editIndex);
	if(type==0){
		var row = dg.datagrid('getChecked');
		if(row==null||row=='') {
			layer.msg("请至少勾选一行记录！",{time: 1000});
			return;
		}

		var rows = dg.datagrid('getChecked');
		var rs = [];
		var m1="";
		for(var i=0;i<rows.length;i++){
			rs.push({
				id1 : id1,
				id2 : userid,
				m1 : rows[i].m1,
				m2 : 3
			});
		}
		
		top.layer.confirm('是否编制安全措施？', {icon: 3, title:'提示'}, function(index){
			if(flag) {
				flag = false;
				var aqjd="";
				var sgfa="";
				$("input[name='M21_4']").each(function(j,item){
					aqjd+=item.value+",";
				});
				aqjd=aqjd.substr(0,aqjd.length-1);
				$("input[name='M21_5']").each(function(j,item){
					sgfa+=item.value+",";
				});
				sgfa=sgfa.substr(0,sgfa.length-1);
				$.ajax({
					type: 'get',
					url: ctx + "/ztzyaqgl/gczy/createAqcs",
					data: {
						'list': JSON.stringify(rs),
						'gcid': id1,
						'aqjd': aqjd,
						'sgfa': sgfa,
						'wlfids': $("input[name='wlfids']").val()
					},
					success: function (data) {
						parent.layer.open({icon: 1, title: '提示', offset: 'rb', content: data, shade: 0, time: 2000});
						top.layer.close(index);
						parent.dg.datagrid('reload');
						parent.layer.close(index2);
					}
				});
			}
		});
	}
}
	
	$list = $('#m21_4fileList'); //文件上传
	$list2 = $('#m21_5fileList'); //文件上传
	
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
	
		    
		    $list.append( $li );
	 
			var newurl=res.url+"||"+res.fileName;
			
			var $input = $('<input id="input_'+file.id+'" type="hidden" name="M21_4" value="'+newurl+'"/>');
			
			$('#uploader_ws_m21_4').append( $input );
		}else{
			layer.msg(res.message,{time: 3000});
		}
	});

	var fileuploader2 = WebUploader.create({

	    auto: true,

	    swf:  '${ctxStatic}/webuploader-0.1.5/Uploader.swf',

	    server: '${ctx}/kindeditor/upload?dir=file',
	    		
	    pick: {
	    	id:'#filePicker2',
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
	
		    
		    $list2.append( $li );
	 
			var newurl=res.url+"||"+res.fileName;
			
			var $input = $('<input id="input_'+file.id+'" type="hidden" name="M21_5" value="'+newurl+'"/>');
			
			$('#uploader_ws_m21_5').append( $input );
		}else{
			layer.msg(res.message,{time: 3000});
		}
	});
	
	// 负责预览图的销毁
    function removeFile(fileid) {
    	$("#"+fileid).remove();
    	$("#input_"+fileid).remove();
    };
    
</script>
</body>
</html>