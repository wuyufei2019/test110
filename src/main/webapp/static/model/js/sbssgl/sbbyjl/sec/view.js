var dg;

$(function(){
	getSecData();
})

//设备二级保养计划
function getSecData(){
	dg=$('#sbssgl_sbbystask_sec_dg').datagrid({    
		method: "post",
		url: ctx+'/sbssgl/sbbytasksec/pagelist/'+taskid,
	    fit : true,
		fitColumns : true,
		border : false,
		idfield : 'id',
		striped:true,
		pagination:true,
		rownumbers:true,
		nowrap:false,
		pagenumber:1,
		pagesize : 10,
		pagelist : [ 10, 50, 100, 150, 200, 250 ],
		scrollbarsize:2,
		
		striped:true,
	    columns:[[    
	        {field:'sbbh',title:'设备编号',sortable:false,width:70,align:'center',rowspan: 2},
	        {field:'sbname',title:'设备名称',sortable:false,width:70,align:'center',rowspan: 2},
	        {field:'sbgg',title:'型号与规格',sortable:false,width:80,align:'center',rowspan: 2,
	        	formatter: function(value, row, index){
	        		return row.sbxh + " " + value;
	        	}
	        },
	        {field:'yf',title:'保养月份',sortable:false,align:'center',colspan: 12},
	        {field:'m13',title:'状态',sortable:false,width:70,align:'center',rowspan:2,
	        	formatter : function(value, row, index) {
	        		if (value == '1') {
	        			return "<span style='color: white;'>待审核</span>";
	        		} else if(value == '2') {
	        			return "<span style='color: white;'>完成</span>";
	        		} else if(value == '3') {
	        			return "<span style='color: white;'>未完成</span>";
	        		} else {
	        			return "待上传附件";
	        		}
	        	},
	            styler: function(value, row, index){
	            	if (value == '1') {//当状态为通过时，背景颜色为黄色    
	            		return 'background-color: #f8ac59';
	        		} else if (value == '2') {//当状态为已完成时，背景颜色为绿色    #f8ac59
	            		return 'background-color: #23c6c8';
	        		} else if (value == '3') {//当状态为时，背景颜色为红色
	            		return 'background-color: #ed5565';
	        		} 
	        	} 
	        },
	        {field:'caozuo',title:'操作',sortable:false,width:70,align:'center',rowspan:2,
	        	formatter : function(value, row, index) {
	        		if (row.m13 == '0' && uploadrole == '1') {//状态为待上传附件
	        			
	        			return "<a class='btn btn-warning btn-xs' onclick=uploadfj("+row.id+")>上传附件</a>";
	        			
	        		} else if (row.m13 == '1' && role == '1') {//状态为待审核
	        			
	        			var m14 = row.m14;
	        			var fileurl = m14.split("||");
	        			
	        			return "<a class='btn btn-info btn-xs' onclick=changeZt("+row.id+",'1')>审核</a>"+
	        				   "<a class='btn btn-warning btn-xs' onclick='window.open(\""+fileurl[0]+"\")' style='margin-left: 5px;'> 查看附件</a>";
	        		} else if (row.m13 == 2) {
	        				return "<a class='btn btn-success btn-xs' onclick=viewShjl("+row.id+")>查看审核记录</a>";
	        			
	        		} else if (row.m13 == 3 && uploadrole == '1') {
	        			
	        			return "<a class='btn btn-warning btn-xs' onclick=uploadfj("+row.id+")>上传附件</a> " + 
	        					"<a class='btn btn-success btn-xs' onclick=viewShjl("+row.id+")>查看审核记录</a>";
	        		}
	        	} 
	        }
	    ],[
	    	{field:'m1',title:'1',sortable:false,width:20,align : 'center',
	    		formatter: function(value, row, index){
	    			if (value && value == '1') {
	    				jau.push(row.id);
	    			}
	    			return '<input class="ck" name="cap_'+row.id+'" type="checkbox" value="1" style="width: 40px;height:20px;opacity:0" onclick="changebgcolor(this, '+row.id+');"/>';
	    		}
	    	},
	        {field:'m2',title:'2',sortable:false,width:20,align : 'center',
	        	formatter: function(value, row, index){
	        		if (value && value == '1') {
	    				feb.push(row.id);
	    			}
	    			return '<input class="ck" name="cap_'+row.id+'" type="checkbox" value="2" style="width: 40px;height:20px;opacity:0" onclick="changebgcolor(this, '+row.id+');"/>';
	    		}
	        },
	        {field:'m3',title:'3',sortable:false,width:20,align : 'center',
	        	formatter: function(value, row, index){
	        		if (value && value == '1') {
	    				mar.push(row.id);
	    			}
	    			return '<input class="ck" name="cap_'+row.id+'" type="checkbox" value="3" style="width: 40px;height:20px;opacity:0" onclick="changebgcolor(this, '+row.id+');"/>';
	    		}
	        },
	        {field:'m4',title:'4',sortable:false,width:20,align : 'center',
	        	formatter: function(value, row, index){
	        		if (value && value == '1') {
	    				apr.push(row.id);
	    			}
	    			return '<input class="ck" name="cap_'+row.id+'" type="checkbox" value="4" style="width: 40px;height:20px;opacity:0" onclick="changebgcolor(this, '+row.id+');"/>';
	    		}
	        },
	        {field:'m5',title:'5',sortable:false,width:20,align : 'center',
	        	formatter: function(value, row, index){
	        		if (value && value == '1') {
	    				may.push(row.id);
	    			}
	    			return '<input class="ck" name="cap_'+row.id+'" type="checkbox" value="5" style="width: 40px;height:20px;opacity:0" onclick="changebgcolor(this, '+row.id+');"/>';
	    		}
	        },
	        {field:'m6',title:'6',sortable:false,width:20,align : 'center',
	        	formatter: function(value, row, index){
	        		if (value && value == '1') {
	    				jun.push(row.id);
	    			}
	    			return '<input class="ck" name="cap_'+row.id+'" type="checkbox" value="6" style="width: 40px;height:20px;opacity:0" onclick="changebgcolor(this, '+row.id+');"/>';
	    		}
	        },
	        {field:'m7',title:'7',sortable:false,width:20,align : 'center',
	        	formatter: function(value, row, index){
	        		if (value && value == '1') {
	    				jul.push(row.id);
	    			}
	    			return '<input class="ck" name="cap_'+row.id+'" type="checkbox" value="7" style="width: 40px;height:20px;opacity:0" onclick="changebgcolor(this, '+row.id+');"/>';
	    		}
	        },
	        {field:'m8',title:'8',sortable:false,width:20,align : 'center',
	        	formatter: function(value, row, index){
	        		if (value && value == '1') {
	    				aug.push(row.id);
	    			}
	    			return '<input class="ck" name="cap_'+row.id+'" type="checkbox" value="8" style="width: 40px;height:20px;opacity:0" onclick="changebgcolor(this, '+row.id+');"/>';
	    		}
	        },
	        {field:'m9',title:'9',sortable:false,width:20,align : 'center',
	        	formatter: function(value, row, index){
	        		if (value && value == '1') {
	    				sep.push(row.id);
	    			}
	    			return '<input class="ck" name="cap_'+row.id+'" type="checkbox" value="9" style="width: 40px;height:20px;opacity:0" onclick="changebgcolor(this, '+row.id+');"/>';
	    		}
	        },
	        {field:'m10',title:'10',sortable:false,width:20,align : 'center',
	        	formatter: function(value, row, index){
	        		if (value && value == '1') {
	    				oct.push(row.id);
	    			}
	    			return '<input class="ck" name="cap_'+row.id+'" type="checkbox" value="10" style="width: 40px;height:20px;opacity:0" onclick="changebgcolor(this, '+row.id+');"/>';
	    		}
	        },
	        {field:'m11',title:'11',sortable:false,width:20,align : 'center',
	        	formatter: function(value, row, index){
	        		if (value && value == '1') {
	    				nov.push(row.id);
	    			}
	    			return '<input class="ck" name="cap_'+row.id+'" type="checkbox" value="11" style="width: 40px;height:20px;opacity:0" onclick="changebgcolor(this, '+row.id+');"/>';
	    		}
	        },
	        {field:'m12',title:'12',sortable:false,width:20,align : 'center',
	        	formatter: function(value, row, index){
	        		if (value && value == '1') {
	    				dec.push(row.id);
	    			}
	    			return '<input class="ck" name="cap_'+row.id+'" type="checkbox" value="12" style="width: 40px;height:20px;opacity:0" onclick="changebgcolor(this, '+row.id+');"/>';
	    		}
	        }
	    ]],
	    onLoadSuccess: function(){
	    	jau.forEach(function(el){ if (!$("input[name=cap_"+el+"][value=1]").attr("checked")) $("input[name=cap_"+el+"][value=1]").trigger("click");} );
	    	feb.forEach(function(el){ if (!$("input[name=cap_"+el+"][value=2]").attr("checked")) $("input[name=cap_"+el+"][value=2]").trigger("click");} );
	    	mar.forEach(function(el){ if (!$("input[name=cap_"+el+"][value=3]").attr("checked")) $("input[name=cap_"+el+"][value=3]").trigger("click");} );
	    	apr.forEach(function(el){ if (!$("input[name=cap_"+el+"][value=4]").attr("checked")) $("input[name=cap_"+el+"][value=4]").trigger("click");} );
	    	may.forEach(function(el){ if (!$("input[name=cap_"+el+"][value=5]").attr("checked")) $("input[name=cap_"+el+"][value=5]").trigger("click");} );
	    	jun.forEach(function(el){ if (!$("input[name=cap_"+el+"][value=6]").attr("checked")) $("input[name=cap_"+el+"][value=6]").trigger("click");} );
	    	jul.forEach(function(el){ if (!$("input[name=cap_"+el+"][value=7]").attr("checked")) $("input[name=cap_"+el+"][value=7]").trigger("click");} );
	    	aug.forEach(function(el){ if (!$("input[name=cap_"+el+"][value=8]").attr("checked")) $("input[name=cap_"+el+"][value=8]").trigger("click");} );
	    	sep.forEach(function(el){ if (!$("input[name=cap_"+el+"][value=9]").attr("checked")) $("input[name=cap_"+el+"][value=9]").trigger("click");} );
	    	oct.forEach(function(el){ if (!$("input[name=cap_"+el+"][value=10]").attr("checked")) $("input[name=cap_"+el+"][value=10]").trigger("click");} );
	    	nov.forEach(function(el){ if (!$("input[name=cap_"+el+"][value=11]").attr("checked")) $("input[name=cap_"+el+"][value=11]").trigger("click");} );
	    	dec.forEach(function(el){ if (!$("input[name=cap_"+el+"][value=12]").attr("checked")) $("input[name=cap_"+el+"][value=12]").trigger("click");} );
	    	
	    	$(":checkbox").attr("disabled", "disabled");
	    	
	    	if (flag != '1') {// 查看页面不显示状态和操作栏
	        	$(this).datagrid("hideColumn", [ 'm13']);
	        	$(this).datagrid("hideColumn", [ 'caozuo']);
	        }
	    },
	    onDblClickRow: function (rowdata, rowindex, rowdomelement){
	    },
	    checkOnSelect:false,
		selectOnCheck:false,
		singleSelect:true,
	    toolbar:'#sbssgl_sbbystask_sec_tb'
	});
}

// 点击td中的复选框改变td的背景颜色
function changebgcolor(obj, id){
	var ckvalue = $(obj).val();
	if($(obj).prop("checked")){
		// 若该数组中不包含此id，则执行添加
		if ((ckvalue == '1') && (jau.indexOf(id) == -1)) { jau.push(id);} 
		else if ((ckvalue == '2')  && (feb.indexOf(id) == -1)) { feb.push(id); } 
		else if ((ckvalue == '3')  && (mar.indexOf(id) == -1)) { mar.push(id); } 
		else if ((ckvalue == '4')  && (apr.indexOf(id) == -1)) { apr.push(id); } 
		else if ((ckvalue == '5')  && (may.indexOf(id) == -1)) { may.push(id); } 
		else if ((ckvalue == '6')  && (jun.indexOf(id) == -1)) { jun.push(id); } 
		else if ((ckvalue == '7')  && (jul.indexOf(id) == -1)) { jul.push(id); } 
		else if ((ckvalue == '8')  && (aug.indexOf(id) == -1)) { aug.push(id); } 
		else if ((ckvalue == '9')  && (sep.indexOf(id) == -1)) { sep.push(id); } 
		else if ((ckvalue == '10') && (oct.indexOf(id) == -1)) { oct.push(id); } 
		else if ((ckvalue == '11') && (nov.indexOf(id) == -1)) { nov.push(id); } 
		else if ((ckvalue == '12') && (dec.indexOf(id) == -1)) { dec.push(id); }
		// 改变复选框所在td的背景颜色
		$(obj).parent().parent().css('background-color','rgb(36, 198, 200)');
		console.log(ckvalue);
	}else{
		// 当复选框取消选中状态时，则移除执行删除
		if (ckvalue == '1') { var jindex = jau.indexOf(id); jau.splice(jindex, 1);} 
		else if (ckvalue == '2')  { var findex = feb.indexOf(id);  feb.splice(findex, 1); } 
		else if (ckvalue == '3')  { var mindex = mar.indexOf(id);  mar.splice(mindex, 1); } 
		else if (ckvalue == '4')  { var aindex = apr.indexOf(id);  apr.splice(aindex, 1); }
		else if (ckvalue == '5')  { var myindex = may.indexOf(id); may.splice(myindex, 1); }
		else if (ckvalue == '6')  { var juindex = jun.indexOf(id); jun.splice(juindex, 1); } 
		else if (ckvalue == '7')  { var jlindex = jul.indexOf(id); jul.splice(jlindex, 1); } 
		else if (ckvalue == '8')  { var auindex = aug.indexOf(id); aug.splice(auindex, 1); } 
		else if (ckvalue == '9')  { var sindex = sep.indexOf(id);  sep.splice(sindex, 1); }
		else if (ckvalue == '10') { var oindex = oct.indexOf(id);  oct.splice(oindex, 1); }
	    else if (ckvalue == '11') { var nindex = nov.indexOf(id);  nov.splice(nindex, 1); } 
		else if (ckvalue == '12') { var dindex = dec.indexOf(id);  dec.splice(dindex, 1); }
		// 回复复选框所在td的背景颜色
		$(obj).parent().parent().css('background-color','#fff');
	}
}

//上传附件
function uploadfj(id){
	openDialog("上传附件",ctx+"/sbssgl/sbbytasksec/uploadindex/"+id,"400px", "250px","");
}

//选择审核结果操作
function changeZt(id){
	openDialog("选择审核结果",ctx+"/sbssgl/sbbytasksec/shjg/"+id,"400px", "200px","");
}

//查看审核记录
function viewShjl(id){
	openDialogView("查看审核记录",ctx+"/sbssgl/sbbytasksec/viewshjl/"+id,"600px", "300px","");
}

