var dg;
var d;

$(function(){ 
	dg=$('#jclsjl_index_dg').datagrid({    
		nowrap:false,
	method: "post",
    url:ctx+'/ead/jclsjl/list', 
    fit : true,
	fitColumns : true,
	checkOnSelect:false,
	selectOnCheck:false,
	border : false,
	idField : 'id',
	striped:true,
	pagination:true,
	rownumbers:false,
	pageNumber:1,
	pageSize : 50,
	pageList : [20, 50, 100, 150, 200, 250 ],
	singleSelect:true,
	scrollbarSize:0,
    columns:[[    
		{field:'index',title:' ',align: 'center',
			formatter:function(val,row,index){
				var options = dg.datagrid('getPager').data("pagination").options; 
			    var currentPage = options.pageNumber;
			    var pageSize = options.pageSize;
			    return value=(pageSize * (currentPage -1))+(index+1);
			}
		},
        {field:'ID',title:'id',checkbox:true,width:50,align:'center'},    
        {field:'qyname',title:'企业名称',width:80},
        {field:'s1',title:'创建时间',width:80,align:'center',
         	formatter : function(value, row, index) {
         		 return jsonTimeStamp(value);
        	} 
        },
        {field:'type',title:'事故类型',width:100,align:'center',
         	formatter : function(value, row, index) {
            	if(value=='1') return value='池火灾';
		        if(value=='2') return value='喷射火';
		        if(value=='3') return value='火球';
		        if(value=='4') return value='物理爆炸（压力容器爆炸）';
		        if(value=='5') return value='化学爆炸';
		        if(value=='6') return value='压缩气体物理爆炸';
		        if(value=='7') return value='持续泄漏';
		        if(value=='8') return value='瞬时泄漏';
        	} 
        },
        {field:'fileurl',title:'下载',width:100,align:'center',
         	formatter : function(value, row, index) {
            	if(value==""){
            		return "-";
            	}else{
            		var btn = '<a class="btn btn-info btn-xs" onclick="downloadRow(\''+value+'\')" href="javascript:void(0)" style="width:100px;"><i class="fa fa-cloud-download"></i>下载</a>';  
                    return btn;  
            	}
        	} 
        }
    ]],
    onLoadSuccess:function(data){  
        $('.downloadcls').linkbutton({text:'下载',plain:true,iconCls:'icon-standard-page-white-word'});  
    },
    toolbar:'#jclsjl_index_tb'
	});
});

//下载
function downloadRow(url){
	window.open(ctx+url);
}

//删除
function del(){
	var cds=dg.datagrid("getChecked");
	if(cds == null || cds ==""){
		layer.msg("请勾选需要删除的数据！",{time: 1000});
		return;
	}
	
	var ids="";
	for(var i=0;i<cds.length;i++){
		if(ids==""){
			ids=cds[i].id;
		}else{
			ids=ids+","+cds[i].id;
		}
	}


		top.layer.confirm('删除后无法恢复您确定要删除？', {icon: 3, title:'提示'}, function(index){
			$.ajax({
				url:ctx+"/ead/jclsjl/delete/"+ids,
				type:"POST",
				async:false,
				success:function(data){
					layer.alert(data, {offset: 'rb',shade: 0,time: 2000}); 
					top.layer.close(index);
					dg.datagrid('reload');
					dg.datagrid('clearChecked');
					dg.datagrid('clearSelections');
				}
			});
		});
	
}

//查询
function cx(){
	var obj=$("#jclsjl_searchFrom").serializeObject();
	dg.datagrid('load',obj); 
}

function clearf(){
	$("#jclsjl_searchFrom").form("clear");
}

//时间戳格式化为yyyy-MM-dd HH:mm:ss
function jsonTimeStamp(milliseconds) {
    if (milliseconds != "" && milliseconds != null
            && milliseconds != "null") {
        var datetime = new Date();
        datetime.setTime(milliseconds);
        var year = datetime.getFullYear();
        var month = datetime.getMonth() + 1 < 10 ? "0"
                + (datetime.getMonth() + 1) : datetime.getMonth() + 1;
        var date = datetime.getDate() < 10 ? "0" + datetime.getDate()
                : datetime.getDate();
        var hour = datetime.getHours() < 10 ? "0" + datetime.getHours()
                : datetime.getHours();
        var minute = datetime.getMinutes() < 10 ? "0"
                + datetime.getMinutes() : datetime.getMinutes();
        var second = datetime.getSeconds() < 10 ? "0"
                + datetime.getSeconds() : datetime.getSeconds();
        return year + "-" + month + "-" + date + " " + hour + ":" + minute
                + ":" + second;
    } else {
        return "";
    }
 
}

//时间戳格式化为yyyy-MM-dd
function jsonYearMonthDay(milliseconds) {
    var datetime = new Date();
    datetime.setTime(milliseconds);
    var year = datetime.getFullYear();
    var month = datetime.getMonth() + 1 < 10 ? "0"
            + (datetime.getMonth() + 1) : datetime.getMonth() + 1;
    var date = datetime.getDate() < 10 ? "0" + datetime.getDate()
            : datetime.getDate();
    return year + "-" + month + "-" + date;
 
}