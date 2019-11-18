<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>安全措施</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/model/js/zyaqgl/zyaq/dhzy/aqcsindex.js?v=1.1"></script>
</head>
<body >
<table id="zyaqgl_dhzy_dg"></table> 
<script type="text/javascript">
	var index2 = parent.layer.getFrameIndex(window.name); //获取窗口索引
	id1='${id1}';
	type='${type}';
	
	function doSubmit(){
	$('#zyaqgl_dhzy_dg').datagrid('endEdit', editIndex);
	if(type==0){
		var row = dg.datagrid('getChecked');
		if(row==null||row=='') {
			layer.msg("请至少勾选一行记录！",{time: 1000});
			return;
		}
        var rs = [];
        for(var i=0;i<row.length;i++){
            rs.push({
                id : row[i].id,
                id1 : id1,
                m1 : row[i].m1,
                m2 : 1
            });
        }
	

		
		top.layer.confirm('是否编制安全措施？', {icon: 3, title:'提示'}, function(index){
			$.ajax({
				type:'get',
				url:ctx+"/zyaqgl/dhzy/createAqcs/"+id1,
                data : {'list' : JSON.stringify(rs)},
				success: function(data){
					parent.layer.open({icon:1,title: '提示',offset: 'rb',content: data,shade: 0 ,time: 2000 });
					top.layer.close(index);
					parent.dg.datagrid('reload');
					parent.layer.close(index2);
				}
			});
		});
	}else{
		
		var rows = dg.datagrid('getData').rows;
		var rs = [];
		var m1="";
		for(var i=0;i<rows.length;i++){
			rs.push({
				id : rows[i].id,
				id1 : id1,
				m1 : rows[i].m1,
				m2 : 1
			});
		}
		top.layer.confirm('是否确认安全措施？', {icon: 3, title:'提示'}, function(index){
			$.ajax({
				type:'get',
				url:ctx+"/zyaqgl/dhzy/confirmAqcs",
				data : {'list' : JSON.stringify(rs)},
				success: function(data){
					parent.layer.open({icon:1,title: '提示',offset: 'rb',content: data,shade: 0 ,time: 2000 });
					top.layer.close(index);
					parent.dg.datagrid('reload');
					parent.layer.close(index2);
				}
			});
		});
	}
}
</script>
</body>
</html>