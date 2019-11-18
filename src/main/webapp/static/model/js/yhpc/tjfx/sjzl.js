var dg;
var url;
var type;
var column;
//企业列表
var column1=[[
              {field:'m1',title:'企业名称',sortable:false,width:200},    
              {field:'m19',title:'主要负责人',sortable:false,width:100,align:'center'},
              {field:'m21',title:'主要负责人移动电话',sortable:false,width:100,align:'center'},
              {field:'m23',title:'安全负责人',sortable:false,width:100,align:'center'},
              {field:'m25',title:'安全负责人移动电话',sortable:false,width:100,align:'center'},
              {field:'m39',title:'重大危险源',sortable:true,width:100,align:'center',
              	formatter : function(value, row, index){
              		if(value=='1') return '是';
              		if(value=='0') return '否';
              		else return '/';
              	}
              },
              {field:'m47',title:'重点监管化学品',sortable:true,width:100,align:'center',
              	formatter : function(value, row, index){
              		if(value=='1') return '是';
              		if(value=='0') return '否';
              		else return '/';
              	}
              },
              {field:'m48',title:'高危工艺',sortable:true,width:60,align:'center',  
                  formatter : function(value, row, index) {
                  	if(value=='1') return '是';
                  	if(value=='0') return '否';
                  	else return '/';
              	} 
              },
              {field:'m40',title:'重大危险源等级',sortable:true,width:60,align:'center',  
                  formatter : function(value, row, index) {
            		  if(row.m39=='1'){
                  	if(value=='1') return '一级';
                  	if(value=='2') return '二级';
                  	if(value=='3') return '三级';
                  	if(value=='4') return '四级';
            		  }else{
            			return '无';
            		  }
              	} 
               },
               {field:'m44',title:'企业风险等级',sortable:true,width:60,align:'center',
               	formatter : function(value, row, index) {
                  	if(value=='1') return "<i class='fa fa-flag' style='font-size: 18px;color:red;'></i>";
                  	if(value=='2') return "<i class='fa fa-flag' style='font-size: 18px;color:orange;'></i>";
                  	if(value=='3') return "<i class='fa fa-flag' style='font-size: 18px;color:yellow;'></i>";
                  	if(value=='4') return "<i class='fa fa-flag' style='font-size: 18px;color:blue;'></i>";
              	}
               }
               ]];

//检查记录表
var column2=[[
    		{field : 'qyname',title : '企业名称',sortable : false,width : 90},
    		{field : 'jcdname',title : '检查点',sortable : false,width : 80,align : 'center'},
  			{field : 'name',title : '所属班次',sortable : false,width : 50,align : 'center',
				formatter : function(value, row, index) {
	              	if(value==null||value=='') {
	              		return '该班次已被删除';
	              	}else{
	              		return value;
	              	}
	            }      			
  			},
  			{field : 'createtime',title : '检查时间',sortable : false,width : 70,align : 'center',
  				formatter : function(value, row, index) {
  	              	if(value!=null&&value!='') {
  	              		var datetime=new Date(value);
  	              		return datetime.format('yyyy-MM-dd hh:mm:ss');
  	              	}
  	            }},
  			{field : 'uname',title : '检查人',sortable : false,width : 60,align : 'center'},
  			{field : 'checkresult',title : '检查结果',sortable : false,width : 40,align : 'center',
  				formatter : function(value, row, index) {
                		if(value=='0'){
                			return '无隐患';
                		}else if(value=='1'){
                			return '有隐患';
                		}
  	            },styler: function(value,row,index){
  					if (value == '1'){
  						return 'color:red;';
  					}
  				}
  	            
  			},
  			{field : 'note',title : '问题备注',sortable : false,width : 100,align : 'center'},
  			{field : 'checkphoto',title : '现场照片',sortable : false,width : 60,align : 'center',
  				formatter : function(value, row, index) {
      				var content="";
                    	if(value!=null&&value!='') {
                    		var arr1=value.split("||");
                      	for (var i = 0; i < arr1.length-1; i++) {
                      		content=content+ '<img onclick=openImgView("'+arr1[i]+'") src='+arr1[i]+' style=margin-right:5px;max-width:100px;max-height:50px; />'; 
                      	} 
                      	return content;
                    	}else{
                    		return '/';
                    	}
                  }
  			}
              ]]

//隐患表
var column3=[[
  			{field : 'wgname',title : '所属网格列',sortable : false,width :50},
  			{field : 'qyname',title : '所属企业',sortable : false,width : 70,align : 'center'},
  			{field : 'xjdname',title : '巡检点名称',sortable : false,width : 70,align : 'center'},
  			{field : 'jcnr',title : '隐患内容',sortable : false,width : 90,align : 'center'},
  			{field : 'yhjb',title : '隐患级别',sortable : false,width : 90,align : 'center',
				formatter : function(value, row, index) {
	              	if(value=='1') {
	              		return '一般';
	              	}else{
	              		return '重大';
	              	}
	            }
  			},
			{field : 'yhfxr',title : '隐患发现人',sortable : false,width : 50,align : 'center'},
			{field : 'createtime',title : '发现时间',sortable : false,width : 60,align : 'center',
				formatter : function(value, row, index) {
	              	if(value!=null&&value!='') {
	              		var datetime=new Date(value);
	              		return datetime.format('yyyy-MM-dd hh:mm:ss');
	              	}
	            }
			},
			{field : 'dangerphoto',title : '隐患照片',sortable : false,width : 60,align : 'center',
				formatter : function(value, row, index) {
    				var content="";
                  	if(value!=null&&value!='') {
                  		var arr1=value.split("||");
                    	for (var i = 0; i < arr1.length-1; i++) {
                    		content=content+ '<img onclick=openImgView("'+arr1[i]+'") src='+arr1[i]+' style=margin-right:5px;max-width:100px;max-height:50px; />'; 
                    	} 
                    	return content;
                  	}else{
                  		return '/';
                  	}
                }
			},
			{field : 'dangerstatus',title : '隐患状态',sortable : false,width : 50,align : 'center',
				formatter : function(value, row, index) {
					if(value == '1'){
              			return "<a style='margin:2px' class='btn btn-danger btn-xs'>企业整改完成</a>"
              		}else if(value == '2'){
              			return "<a style='margin:2px' class='btn btn-danger btn-xs'>审核未通过</a>"
              		}else if(value == '3'){
              			return "<a  class='btn btn-success btn-xs'>审核通过</a>"
              		}else{
              			return "<a style='margin:2px' class='btn btn-danger btn-xs' >未整改</a>"
              		}
	            }
			}
              ]]

$(function(){
	if(type<5){
		column=column1;
	}else if(type==5){
		column=column2;
	}else if(type>5){
		column=column3;
	}
	dg=$('#yhpc_tjfx_dg').datagrid({    
	method: "post",
    url:ctx+'/yhpc/tjfx/list2?type='+type, 
    fit : true,
	fitColumns : true,
	selectOnCheck:false,
	idField : 'id',
	striped:true,
	pagination:true,
	rownumbers:true,
	nowrap:false,
	pageNumber:1,
	pageSize : 50,
	pageList : [ 50, 100, 150, 200, 250 ],
	scrollbarSize:5,
	singleSelect:true,
	striped:true,
    columns:column,
    onLoadSuccess: function(){
    },
    onLoadError:function(){
    	layer.open({title: '提示',offset: 'rb',content: '数据加载中，请耐心等待。',shade: 0 ,time: 2000 });
    },
	checkOnSelect:false,
	selectOnCheck:false,
    toolbar:'#yhpc_tjfx_tb'
	});
	
});
