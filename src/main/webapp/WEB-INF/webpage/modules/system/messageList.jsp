<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>消息中心</title>
	<meta name="decorator" content="default"/>
</head>
<body >
<!-- 工具栏 -->
<div id="tb" style="padding:5px;height:auto">
	<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">
		<div class="form-group">
	    	<span>类型：</span>
	    	<select id="filter_EQS_MSGTYPE" name="filter_EQS_MSGTYPE" class="form-control">
				<option value="" selected="selected">全部</option>
				<option value="dxj">待巡检</option>
				<option value="dsh">待审核</option>
				<option value="dsp">待审批</option>
				<option value="djc">待检查</option>
				<option value="dzg">待整改</option>
				<option value="xwj">新文件</option>
				<option value="pxtz">培训通知</option>
				<option value="dcwj">调查问卷</option>
		    </select>
		    <span>状态：</span>
		    <select name="filter_EQS_SENDSTATUE" class="form-control">
				<option value="" selected="selected">全部</option>
				<option value="0">未读</option>
				<option value="1">已读</option> 
		    </select>
	    </div>
	    <span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</span>
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span>
	</form>

	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
 			<shiro:hasPermission name="sys:log:delete">
				<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除"><i class="fa fa-trash-o"></i> 删除</button>
			</shiro:hasPermission>
			<shiro:hasPermission name="sys:log:exportExcel">
			    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="export()" title="导出"><i class="fa fa-file-text-o"></i> 导出</button> 
			</shiro:hasPermission>
		</div>
		<div class="pull-right">
			
		</div>
	</div>
	</div> 
</div>
<table id="dg"></table> 

<script type="text/javascript">
var dg;
var d;
$(function(){
	var msgtype = '${msgtype}';
	if(msgtype!=''&&msgtype!=null&&msgtype!=undefined){
		$("#filter_EQS_MSGTYPE").val(msgtype);
	}
	
	dg=$('#dg').datagrid({    
	method: "get",
	url:'${ctx}/system/message/list', 
	queryParams : {filter_EQS_MSGTYPE:$("#filter_EQS_MSGTYPE").val()},
    fit : true,
	fitColumns : true,
	border : false,
	idField : 'id',
	striped:true,
	pagination:true,
	rownumbers:true,
	nowrap:false,
	pageNumber:1,
	pageSize : 50,
	pageList : [ 50, 100, 150, 200, 250 ],
	scrollbarSize:10,
	singleSelect:true,
	checkOnSelect:false,
	selectOnCheck:false,
    columns:[[    
        {field:'id',title:'id',checkbox:true,align:'center'},    
        {field:'msgtype',title:'类别',width:30,align:'center',
        	formatter:function(value,row,index){
        		if(value=='dxj')
     				return "待巡检";
     			else if(value=='dsh')
     				return "待审核";
     			else if(value=='dsp')
     				return "待审批";
     			else if(value=='djc')
     				return "待检查";
     			else if(value=='xwj')
     				return "新文件";
     			else if(value=='pxtz')
     				return "培训通知";
     			else if(value=='aqhy')
     				return "安全会议";
     			else if(value=='dcwj')
     				return "调查问卷";
     			else return value;
     		}  
        },
        {field:'sendstatue',title:'状态',width:20,align:'center',
        	formatter:function(value,row,index){
     			if(value==0)
     				return "<span class='btn-primary'>未读</span>";
     			else if(value==1)
     				return "<span class='btn-default'>已读</span>";
     			else return value;
     		}  
        },
        {field:'title',title:'标题内容',width:100,
        	formatter:function(value,row){
            var url="";

            if(row.url){
                try {
                    url=JSON.parse(row.url);
                    url=url.PC;
                } catch (e) {
                }
			}
     		return "<a onclick='uptmsg(\""+url+"\","+row.id+")'>"+value+"</a>";
        } },
        {field:'createtime',title:'创建时间',width:50,  
                     formatter:function(value,row,index){
                     		if(value!=null){
                         var datetime = new Date(value);  
                         return datetime.format("yyyy-MM-dd hh:mm:ss");  }  
        	}  
        }
    ]],
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
                
    },
    toolbar:'#tb'
	});
	
});


//删除
function del(){
	var row = dg.datagrid('getChecked');
	if(row==null||row=='') {
		layer.msg("请至少勾选一行记录！",{time: 1000});
		return;
	}

	var ids=[];
	for(var i=0;i<row.length;i++){
		ids.push(row[i].id);
	}

	top.layer.confirm('删除后无法恢复您确定要删除？', {icon: 3, title:'提示'}, function(index){
		$.ajax({
			type:'post',
			contentType:"application/json",               
			url:"${ctx}/system/log/delete",
			data:JSON.stringify(ids),
			success: function(data){
				layer.alert(data, {offset: 'rb',shade: 0,time: 2000}); 
				top.layer.close(index);
				dg.datagrid('reload');
				dg.datagrid('clearChecked');
			}
		});
	});
 
}


//创建查询对象并查询
function search(){
	var obj=$("#searchFrom").serializeObject();
	dg.datagrid('load',obj); 
}

function reset(){
	$("#searchFrom").form("clear");
	var obj=$("#searchFrom").serializeObject();
	dg.datagrid('load',obj); 
	//if(btflg=='2') $("#filter_EQS_departmen").hide();
}
 
function uptmsg(msgurl,msgid){
    if(msgurl)
        openDialog("",'${ctx}/'+msgurl,"80%","90%","");
    /*parent.layer.open({
        type: 2,
        shift: 1,
        area: ["100%","100%"],
        title: "",
        maxmin: false,
        content: '${ctx}/'+msgurl ,
        btn: ['确定', '关闭'],
        yes: function(index, layero){
            var body = layer.getChildFrame('body', index);
            var iframeWin = layero.find('iframe')[0];
            var inputForm = body.find('#inputForm');
            iframeWin.contentWindow.doSubmit();
        },
        cancel: function(index){
        }
    });*/
	$.ajax({
		type:'get',
		url:ctx+"/system/message/uptmsg/"+msgid,
		success: function(data){
			search();
		}
	});
}
</script>
</body>
</html>