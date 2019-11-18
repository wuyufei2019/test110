<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title></title>
<meta name="decorator" content="default"/>
</head>
<body>
		<form id="excel_mainform" action="${ctx}/system/user/selmenu" method="post">
		
		<input type="hidden" id="secid" name="ID" />
	 	<input type="hidden" id="userid" name="userid" />
	 	<input type="hidden" id="menuid" name="menuid" />
		
		<table id="exceldg" class="easyui-datagrid" style="width:100%;height:250px;" data-options="fitColumns:true">   
		</table>  
       	
       	</form> 
<script type="text/javascript">
var secid=parent.secid;//id
var userid=parent.userid;//用户id
var menuid=parent.menuid;//所选菜单id集合
var index = parent.layer.getFrameIndex(window.name); //获取窗口索引

$('#secid').val(secid);
$('#userid').val(userid);
$('#menuid').val(menuid);

var arr=menuid.split(",");
var dg;
var obj={"total":4,"rows":[{id:"0",name:"安全生产十大禁令"},{id:"1",name:"企业卫星图"}/* ,{id:"2",name:"隐患排查数据"} */,{id:"3",name:"最新公司动态通知"}/* ,{id:"4",name:"风险预警雷达图"} */]};

dg=$('#exceldg').datagrid({    
    columns:[[    
		        {field:'id',title:'id',checkbox:true,width:50,align:'center'},    
		        {field:'name',title:'菜单名',width:100,align:'center'}
		    ]],
    data: obj,
    scrollbarSize:0,
    onLoadSuccess:function(){
    	var rows = $("#exceldg").datagrid("getRows");
    	if(arr!=null&&arr!=""){
			for (var j = 0; j < arr.length; j++) {
				$('#exceldg').datagrid('selectRow',arr[i]);//选中上一次选择的菜单
				for(var i=0;i<rows.length;i++){
			      var rowId = rows[i].id;
		           if(rowId==arr[j]){
		        	   $('#exceldg').datagrid('selectRow',i);//选中上一次选择的菜单
		           }
				 }
			}
    	}
      },
});

function doSubmit(){
	var row = dg.datagrid('getChecked');
	if(row==null||row=='') {
		layer.msg("请至少勾选一行记录！",{time: 1000});
		return;
	}
	
	var ids="";
	for(var i=0;i<row.length;i++){
		if(ids==""){
			ids=row[i].id;
		}else{
			ids=ids+","+row[i].id;
		}
	}
	
	$('#menuid').val(ids);//保存选择的菜单
	
	$("#excel_mainform").submit();

};

$(function(){
	$('#excel_mainform').form({
		success : function(data){
			$.jBox.closeTip();
			if (data == 'success')
				parent.layer.open({
					icon : 1,
					title : '提示',
					offset : 'rb',
					content : '操作成功！',
					shade : 0,
					time : 2000
				});
			else
				parent.layer.open({
					icon : 2,
					title : '提示',
					offset : 'rb',
					content : '操作失败！',
					shade : 0,
					time : 2000
				});
			parent.location.reload();
			parent.layer.close(index);//关闭对话框。
		}
	})
})
</script>
</body>
</html>