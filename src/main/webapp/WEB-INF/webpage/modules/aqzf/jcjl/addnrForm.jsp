<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>图片上传</title>
	<meta name="decorator" content="default"/>
<script type="text/javascript">
var index = parent.layer.getFrameIndex(window.name); //获取窗口索引	
</script>
</head>
<body>

     <form id="inputForm" action="" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right">检查内容：</label></td>
					<td class="width-85" colspan="3" >
						<ul id="tt" style="width:400px" ></ul>
					</td>
				</tr>
				</tbody>
			</table>
       </form>
 
<script type="text/javascript">	
$(function(){
	$('#tt').tree({
    	url: ctx+'/aqzf/jcfa/jcnr',
    	checkbox: true,
    	loadFilter: function(data){
			if (data.d){
				return data.d;
			} else {
				return data;
			}
    	},
    	onLoadSuccess:function(){
			
    	} 
	});
});

function getnrid(){
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
	return ids;
}
</script>
</body>
</html>