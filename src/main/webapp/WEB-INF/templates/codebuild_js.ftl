var dg;<#assign fileflag = false>
$(function(){
	dg=$('#table_dg').datagrid({    
	method: "post",
	url:ctx+'/${code_mapurl!'' }/list', 
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
	scrollbarSize:0,
	singleSelect:true,
	striped:true,
	columns:[[ 
            {field:'id',title:'id',checkbox:true,width:50,align:'center'},    
             <#list columns as map>   
             <#if (map.col_viewable!'false')=='true'>
            {field:'${map.col_ename!'' }',title:'${map.col_cname!'' }',sortable:false,width:100<#if map.col_showtype??&&map.col_showtype=='combobox'>,formatter:function(value){
                 var html="";
                   if(value==""){
                     html = "";
                   }else if(value==""){
                     html = "";
                   }else if(value==""){
                     html = "";
                   }
                   return html;
            }</#if><#if map.col_showtype??&&map.col_showtype=='datebox'>,formatter:function(value){
                  if(value) return new Date(value).format("yyyy-MM-dd");
            }</#if><#if map.col_showtype??&&map.col_showtype=='datetimebox'>,formatter:function(value){
                  if(value) return new Date(value).format("yyyy-MM-dd hh:mm:ss");
            }</#if>}<#if map.col_showtype??&&(map.col_showtype=='file'||map.col_showtype=='image')>,formatter:function(value){<#assign fileflag=true>
                     if(value){
                    	var arrs= value.split(",");
                    	var html="";
                    	for(var i=0;i<arrs.length;i++){
                    		html+='<a class="btn btn-info btn-xs mgr10" onclick="downloadRow(\''+arrs[i].split("||")[0]
								+'\')" href="javascript:void(0)" ><i class="fa fa-cloud-download"></i>附件'+(i+1)+'</a>'
                        }
                        return html;
					}
            }</#if>},
            </#if>   
            </#list>  
        ]],
        onLoadSuccess: function(){
        },
        onDblClickRow: function (rowindex, rowdata, rowDomElement){
             view();
        },
	checkOnSelect:false,
	selectOnCheck:false,
	toolbar:'#dg_tb'
	});
});

//弹窗增加
function add() {
	openDialog("添加${code_description }信息",ctx+"/${code_mapurl!'' }/create/","800px", "400px","");
}

//删除
function del(){
	var rows = dg.datagrid('getChecked');
	if(rows.length==0) {
		layer.msg("请至少勾选一行记录！",{time: 1000});
		return;
	}

	var ids="";
    for(var item of rows){
       if(ids==""){
          ids=item.id;
       }else{
          ids=ids+","+item.id;
       }
    }

	top.layer.confirm('删除后无法恢复您确定要删除？', {icon: 3, title:'提示'}, function(index){
		$.ajax({
			type:'get',
			url:ctx+"/${code_mapurl!'' }/delete/"+ids,
			success: function(data){
				layer.alert(data, {offset: 'rb',shade: 0,time: 2000}); 
				top.layer.close(index);
				dg.datagrid('reload');
				dg.datagrid('clearChecked');
			}
		});
	});
 
}

//弹窗修改
function upd(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	openDialog("修改${code_description }信息",ctx+"/${code_mapurl!'' }/update/"+row.id,"800px", "400px","");
	
}

//查看
function view(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	openDialogView("查看${code_description }信息",ctx+"/${code_mapurl!'' }/view/"+row.id,"800px", "300px","");
	
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
}
<#if fileflag==true>
//下载
function downloadRow(url){
    window.open(ctx+url);
}
</#if>

