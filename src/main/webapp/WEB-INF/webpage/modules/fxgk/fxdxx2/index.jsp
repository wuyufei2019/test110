<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>危险因素辨识管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/model/js/fxgk/fxdxx2/index.js?v=1.9"></script>
	<script type="text/javascript">
	   var f='${sys}';
	   var fxdj='${fxdj}';
	   var dg;
	   var d;
	   var zz = 1;
	   $(function() {
	  		var qyname = "${qyname}";
	  		var url;
	        if(qyname != null && qyname != undefined && qyname !=''){
				$("#fxgk_fxxx_qyname").textbox('setValue',qyname);
				url = ctx + '/fxgk/fxxx/ajlist/'+qyname;
		  	}else{
		  	    url = ctx + '/fxgk/fxxx/ajlist';
		  	}
			dg = $('#fxgk_fxxx_dg').datagrid(
					{
						method : "post",
						url : url,
						fit : true,
						fitColumns : true,
						border : false,
						idField : 'id',
						striped : true,
						pagination : true,
						rownumbers : true,
						nowrap : false,
						pageNumber : 1,
						pageSize : 50,
						pageList : [ 50, 100, 150, 200, 250 ],
						scrollbarSize : 5,
						singleSelect : true,
						striped : true,
						columns : [ [
				{field:'id',title:'id',checkbox:true,width:50,align:'center'},    
		        {field:'qyname',title:'企业名称',sortable:false,width:100},  
		        {field:'m1',title:'风险点名称',sortable:false,width:100,align:'center'},
				{field:'m18',title:'分厂',sortable:false,width:100,align:'center'},
		        {field:'m4',title:'场所/环节/部位',sortable:false,width:100,align:'center'},
		        {field:'m9',title:'风险分级',sortable:false,width:100,align:'center',sortable:true,
		        	formatter : function(value, row, index){
		        		if(value=='1') return value='红';
		        		else if(value=='2') return value='橙';
		        		else if(value=='3') return value='黄';
		        		else if(value=='4') return value='蓝'; 
		        	},
		        	styler : function(value, row, index){
		        		if(value=='1')  return 'background-color:#FF0000;color:#1E1E1E';
		        		else if(value=='2')  return 'background-color:#FFC125;color:#1E1E1E';
		        		else if(value=='3')  return 'background-color:#FFFF00;color:#1E1E1E';
		        		else if(value=='4')  return 'background-color:#3A5FCD;color:#1E1E1E'; 
		     		}
		        },{field:'m16',title:'现场照片',sortable:false,width:50,align:'center',
		        	formatter : function(value, row, index){
		        		var content="";
	                  	if(value!=null&&value!='') {
	                  		var urls=value.split(",");
	                  		for(var i in urls){
	                    		content=content+ "<a class='fa fa-picture-o btn-danger btn-outline' onclick=openImgView('"+urls[i].split("||")[0]+"')>照片"+(parseInt(i)+1)+"</a>";
	                    	} 
	                    	return content;
	                  	}else
	                  		return '/';
		        	}
		        },
		        {field:'caozuo',title:'在线监管',sortable:false,width:100,align:'center',
		        	formatter : function(value, row, index){
		        	    return "<a class='btn btn-success btn-xs' onclick='viewXjjl("+row.id+")'>查看历史巡检记录</a>";
		        	}
		        }
						] ],
						onDblClickRow : function(rowdata, rowindex, rowDomElement) {
							view();
						},
						onLoadSuccess : function(rowdata, rowindex, rowDomElement) {
							$(this).datagrid("autoMergeCells", [ 'qyname' ]);
						},
						 onBeforeLoad:function(param){
						    	if(f!=''&&f=='sys'){
						    		$("#fxgk_fxxx_fxfj").combobox('setValue',fxdj);
						    		param.fxgk_fxxx_fxfj=fxdj;
						    	 }
							    },
						checkOnSelect : false,
						selectOnCheck : false,
						toolbar : '#fxgk_fxxx_tb'
					});
		});
	</script>
</head>
<body >
<!-- 工具栏 -->
<div id="fxgk_fxxx_tb" style="padding:5px;height:auto">
	<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">				
	<div class="form-group">
		<input type="text" id="fxgk_fxxx_qyname" name="fxgk_fxxx_qyname" class="easyui-combobox" style="height: 30px;" data-options="editable:true ,valueField: 'text',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson',prompt: '企业名称' " />
	    <input type="text" name="fxgk_fxxx_name" class="easyui-textbox" style="height: 30px;" data-options="prompt: '风险点名称'" />

		<input type="text" name="fxgk_fxxx_name" class="easyui-textbox" style="height: 30px;" data-options="prompt: '风险点名称'" />
		<input type="text" id="fxgk_fxxx_fxfj"name="fxgk_fxxx_fxfj" class="easyui-combobox" style="height: 30px;" data-options="editable:false ,prompt: '风险分级'
		,panelHeight:'auto',data: [{value:'1',text:'红'},
								        {value:'2',text:'橙'},
								        {value:'3',text:'黄'},
								        {value:'4',text:'蓝'}]" />
		<input type="text" name="fxgk_fxxx_sglx" class="easyui-combobox" style="height: 30px;" data-options="prompt: '事故类型',
								panelHeight:'200px',editable:true ,valueField: 'text',textField: 'text',url:'${ctx}/fxgk/fxxx/accident'" />
		<input  type="hidden" id="panduan" name="panduan" value="1"/>
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</span>
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span>       
    </div>
	</form>

	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
			<shiro:hasPermission name="fxgk:fxxx:add">
		       		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add()" title="添加"><i class="fa fa-plus"></i> 添加</button>
			</shiro:hasPermission>
			<shiro:hasPermission name="fxgk:fxxx:addfz">
		       		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="addfz()" title="复制"><i class="fa fa-copyright"></i> 复制</button>
			</shiro:hasPermission>
			<shiro:hasPermission name="fxgk:fxxx:update">
			    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd()" title="修改"><i class="fa fa-file-text-o"></i> 修改</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="fxgk:fxxx:delete">
				<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除"><i class="fa fa-trash-o"></i> 删除</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="fxgk:fxxx:view">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view()" title="查看"><i class="fa fa-search-plus"></i> 查看</button> 
        	</shiro:hasPermission>
        	<shiro:hasPermission name="fxgk:fxxx:export">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="exportexc()" title="导出Excel"><i class="fa fa-file-excel-o"></i> 导出Excel</button> 
        	</shiro:hasPermission>
	        
        	<shiro:hasPermission name="fxgk:fxxx:export">
        		<button class="btn btn-primary btn-sm" data-toggle="tooltip" data-placement="left" onclick="exportbs()" title="危险有害因素告知卡"><i class="fa fa-file-word-o"></i> 危险有害因素告知卡</button> 
        	</shiro:hasPermission>
        	 	<shiro:hasPermission name="fxgk:fxxx:export">
        		<button class="btn btn-success btn-sm" data-toggle="tooltip" data-placement="left" onclick="exportfxgzA2()" title="导出彩色风险告知卡WORD(A2)"><i class="fa fa-file-word-o"></i> 导出彩色风险告知卡(A2)</button> 
        	</shiro:hasPermission>
        	<shiro:hasPermission name="fxgk:fxxx:export">
        		<button class="btn btn-success btn-sm" data-toggle="tooltip" data-placement="left" onclick="exportfxgzA3()" title="导出彩色风险告知卡WORD(A3)"><i class="fa fa-file-word-o"></i> 导出彩色风险告知卡(A3)</button> 
        	</shiro:hasPermission>
        	<%-- <shiro:hasPermission name="fxgk:fxxx:export">
        		<button class="btn btn-danger btn-sm" data-toggle="tooltip" data-placement="left" onclick="exportpdf()" title="导出彩色风险告知卡PDF"><i class="fa fa-file-pdf-o"></i> 导出彩色风险告知卡PDF</button> 
        	</shiro:hasPermission> --%>
            <button class="btn btn-warning btn-sm" data-toggle="tooltip" data-placement="left" onclick="fxdfbt()" title="风险点分布图"><i class="	glyphicon glyphicon-picture"></i> 风险点分布图</button>
            <button class="btn btn-primary btn-sm" data-toggle="tooltip" data-placement="left" onclick="tjfx()" title="统计分析"><i class="glyphicon glyphicon-indent-left"></i> 统计分析</button>	
            <button class="btn btn-info btn-sm"  data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>			
			</div>
		<div class="pull-right">
		</div>
	</div>
	</div> 
	   
</div>

<table id="fxgk_fxxx_dg"></table> 

</body>
</html>