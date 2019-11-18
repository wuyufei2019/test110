<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>添加安全措施</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/model/js/ztzyaqgl/zyaq/sxzy/aqcsindex.js?v=1.0"></script>
</head>
<body >
<table id="zyaqgl_sxzy_dg" ></table>

<script type="text/javascript">
	var flag=true;
	var index2 = parent.layer.getFrameIndex(window.name); //获取窗口索引
	var userid='${userid}';
	id1='${id1}';
	type='${type}';

	function doSubmit(){
		$('#zyaqgl_sxzy_dg').datagrid('endEdit', editIndex);
		alert(flag);
		if(type==0&&flag){
			flag=false;
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
					m2 : 2
				});
			}
			
			top.layer.confirm('是否添加安全措施？', {icon: 3, title:'提示'}, function(index){
				$.ajax({
					type:'get',
					url:ctx+"/ztzyaqgl/sxzy/createAqcs",
					data : {'list' : JSON.stringify(rs),'sxid' : id1, 'flag':'upd'},
					success: function(data){
						parent.layer.open({icon:1,title: '提示',offset: 'rb',content: data,shade: 0 ,time: 2000 });
						top.layer.close(index);
						parent.dg.datagrid('reload');
						parent.layer.close(index2);
					}
				});
			});
		}else{
			return false;
		}
	}
</script>
</body>
</html>