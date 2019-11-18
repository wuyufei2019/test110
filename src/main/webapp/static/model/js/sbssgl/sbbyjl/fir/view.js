var dg;

$(function(){
	getFirData();
})

//设备二级保养计划
function getFirData(){
	dg=$('#sbssgl_sbbystask_fir_dg').datagrid({    
		method: "post",
		url: ctx+'/sbssgl/sbbytaskfir/pagelist/'+taskid,
	    fit : true,
		fitColumns : true,
		border : false,
		idField : 'id',
		striped:true,
		pagination:true,
		rowNumbers:true,
		nowrap:false,
		pageNumber:1,
		pageSize : 10,
		pageList : [ 10, 50, 100, 150, 200, 250 ],
		scrollBarsize:2,
		singleSelect:true,
		striped:true,
		columns:[[    
	        {field:'sbbh',title:'设备编号',sortable:false,width:70,align:'center',rowspan: 2},
	        {field:'sbname',title:'设备名称',sortable:false,width:70,align:'center',rowspan: 2},
	        {field:'sbgg',title:'型号与规格',sortable:false,width:80,align:'center',rowspan: 2,
	        	formatter: function(value, row, index){
	        		return row.sbxh + " " + value;
	        	}
	        },
	        {field:'rq',title:'保养日期',sortable:false,align:'center',colspan: 31},
	        {field:'m32',title:'状态',sortable:false,width:70,align:'center',rowspan:2,
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
	        		if (row.m32 == '0' && uploadrole == '1') {//状态为待上传附件
	        			
	        			return "<a class='btn btn-warning btn-xs' onclick=uploadfj("+row.id+")>上传附件</a>";
	        			
	        		} else if (row.m32 == '1' && role == '1') {//状态为待审核
	        			
	        			var m33 = row.m33;
	        			var fileurl = m33.split("||");
	        			
	        			return "<a class='btn btn-info btn-xs' onclick=changeZt("+row.id+",'1')>审核</a>"+
	        				   "<a class='btn btn-warning btn-xs' onclick='window.open(\""+fileurl[0]+"\")' style='margin-left: 5px;'> 查看附件</a>";
	        			
	        		} else if (row.m32 == 2) {
        				return "<a class='btn btn-success btn-xs' onclick=viewShjl("+row.id+")>查看审核记录</a>";
        			
	        		} else if (row.m32 == 3 && uploadrole == '1') {
	        			
	        			return "<a class='btn btn-warning btn-xs' onclick=uploadfj("+row.id+")>上传附件</a> " + 
	        					"<a class='btn btn-success btn-xs' onclick=viewShjl("+row.id+")>查看审核记录</a>";
	        		} 
	        	} 
	        }
	    ],[
	    	{field:'m1',title:'1',sortable:false,width:20,align : 'center',
	    		formatter: function(value, row, index){
	    			if (value && value == '1') {
	    				one.push(row.id);
	    			}
	    			return '<input class="ck" name="cap_'+row.id+'" type="checkbox" value="1" style="width: 40px;height:20px;opacity:0" onclick="changebgcolor(this, '+row.id+');"/>';
	    		}
	    	},
	        {field:'m2',title:'2',sortable:false,width:20,align : 'center',
	        	formatter: function(value, row, index){
	        		if (value && value == '1') {
	        			tow.push(row.id);
	    			}
	    			return '<input class="ck" name="cap_'+row.id+'" type="checkbox" value="2" style="width: 40px;height:20px;opacity:0" onclick="changebgcolor(this, '+row.id+');"/>';
	    		}
	        },
	        {field:'m3',title:'3',sortable:false,width:20,align : 'center',
	        	formatter: function(value, row, index){
	        		if (value && value == '1') {
	        			thr.push(row.id);
	    			}
	    			return '<input class="ck" name="cap_'+row.id+'" type="checkbox" value="3" style="width: 40px;height:20px;opacity:0" onclick="changebgcolor(this, '+row.id+');"/>';
	    		}
	        },
	        {field:'m4',title:'4',sortable:false,width:20,align : 'center',
	        	formatter: function(value, row, index){
	        		if (value && value == '1') {
	        			fur.push(row.id);
	    			}
	    			return '<input class="ck" name="cap_'+row.id+'" type="checkbox" value="4" style="width: 40px;height:20px;opacity:0" onclick="changebgcolor(this, '+row.id+');"/>';
	    		}
	        },
	        {field:'m5',title:'5',sortable:false,width:20,align : 'center',
	        	formatter: function(value, row, index){
	        		if (value && value == '1') {
	        			fiv.push(row.id);
	    			}
	    			return '<input class="ck" name="cap_'+row.id+'" type="checkbox" value="5" style="width: 40px;height:20px;opacity:0" onclick="changebgcolor(this, '+row.id+');"/>';
	    		}
	        },
	        {field:'m6',title:'6',sortable:false,width:20,align : 'center',
	        	formatter: function(value, row, index){
	        		if (value && value == '1') {
	        			six.push(row.id);
	    			}
	    			return '<input class="ck" name="cap_'+row.id+'" type="checkbox" value="6" style="width: 40px;height:20px;opacity:0" onclick="changebgcolor(this, '+row.id+');"/>';
	    		}
	        },
	        {field:'m7',title:'7',sortable:false,width:20,align : 'center',
	        	formatter: function(value, row, index){
	        		if (value && value == '1') {
	        			sen.push(row.id);
	    			}
	    			return '<input class="ck" name="cap_'+row.id+'" type="checkbox" value="7" style="width: 40px;height:20px;opacity:0" onclick="changebgcolor(this, '+row.id+');"/>';
	    		}
	        },
	        {field:'m8',title:'8',sortable:false,width:20,align : 'center',
	        	formatter: function(value, row, index){
	        		if (value && value == '1') {
	        			eig.push(row.id);
	    			}
	    			return '<input class="ck" name="cap_'+row.id+'" type="checkbox" value="8" style="width: 40px;height:20px;opacity:0" onclick="changebgcolor(this, '+row.id+');"/>';
	    		}
	        },
	        {field:'m9',title:'9',sortable:false,width:20,align : 'center',
	        	formatter: function(value, row, index){
	        		if (value && value == '1') {
	        			nin.push(row.id);
	    			}
	    			return '<input class="ck" name="cap_'+row.id+'" type="checkbox" value="9" style="width: 40px;height:20px;opacity:0" onclick="changebgcolor(this, '+row.id+');"/>';
	    		}
	        },
	        {field:'m10',title:'10',sortable:false,width:20,align : 'center',
	        	formatter: function(value, row, index){
	        		if (value && value == '1') {
	        			ten.push(row.id);
	    			}
	    			return '<input class="ck" name="cap_'+row.id+'" type="checkbox" value="10" style="width: 40px;height:20px;opacity:0" onclick="changebgcolor(this, '+row.id+');"/>';
	    		}
	        },
	        {field:'m11',title:'11',sortable:false,width:20,align : 'center',
	        	formatter: function(value, row, index){
	        		if (value && value == '1') {
	        			ele.push(row.id);
	    			}
	    			return '<input class="ck" name="cap_'+row.id+'" type="checkbox" value="11" style="width: 40px;height:20px;opacity:0" onclick="changebgcolor(this, '+row.id+');"/>';
	    		}
	        },
	        {field:'m12',title:'12',sortable:false,width:20,align : 'center',
	        	formatter: function(value, row, index){
	        		if (value && value == '1') {
	        			twl.push(row.id);
	    			}
	    			return '<input class="ck" name="cap_'+row.id+'" type="checkbox" value="12" style="width: 40px;height:20px;opacity:0" onclick="changebgcolor(this, '+row.id+');"/>';
	    		}
	        },
	        {field:'m13',title:'13',sortable:false,width:20,align : 'center',
	        	formatter: function(value, row, index){
	        		if (value && value == '1') {
	        			tht.push(row.id);
	    			}
	    			return '<input class="ck" name="cap_'+row.id+'" type="checkbox" value="13" style="width: 40px;height:20px;opacity:0" onclick="changebgcolor(this, '+row.id+');"/>';
	    		}
	        },
	        {field:'m14',title:'14',sortable:false,width:20,align : 'center',
	        	formatter: function(value, row, index){
	        		if (value && value == '1') {
	        			fut.push(row.id);
	    			}
	    			return '<input class="ck" name="cap_'+row.id+'" type="checkbox" value="14" style="width: 40px;height:20px;opacity:0" onclick="changebgcolor(this, '+row.id+');"/>';
	    		}
	        },
	        {field:'m15',title:'15',sortable:false,width:20,align : 'center',
	        	formatter: function(value, row, index){
	        		if (value && value == '1') {
	        			fft.push(row.id);
	    			}
	    			return '<input class="ck" name="cap_'+row.id+'" type="checkbox" value="15" style="width: 40px;height:20px;opacity:0" onclick="changebgcolor(this, '+row.id+');"/>';
	    		}
	        },
	        {field:'m16',title:'16',sortable:false,width:20,align : 'center',
	        	formatter: function(value, row, index){
	        		if (value && value == '1') {
	        			sxt.push(row.id);
	    			}
	    			return '<input class="ck" name="cap_'+row.id+'" type="checkbox" value="16" style="width: 40px;height:20px;opacity:0" onclick="changebgcolor(this, '+row.id+');"/>';
	    		}
	        },
	        {field:'m17',title:'17',sortable:false,width:20,align : 'center',
	        	formatter: function(value, row, index){
	        		if (value && value == '1') {
	        			svt.push(row.id);
	    			}
	    			return '<input class="ck" name="cap_'+row.id+'" type="checkbox" value="17" style="width: 40px;height:20px;opacity:0" onclick="changebgcolor(this, '+row.id+');"/>';
	    		}
	        },
	        {field:'m18',title:'18',sortable:false,width:20,align : 'center',
	        	formatter: function(value, row, index){
	        		if (value && value == '1') {
	        			egt.push(row.id);
	    			}
	    			return '<input class="ck" name="cap_'+row.id+'" type="checkbox" value="18" style="width: 40px;height:20px;opacity:0" onclick="changebgcolor(this, '+row.id+');"/>';
	    		}
	        },
	        {field:'m19',title:'19',sortable:false,width:20,align : 'center',
	        	formatter: function(value, row, index){
	        		if (value && value == '1') {
	        			nnt.push(row.id);
	    			}
	    			return '<input class="ck" name="cap_'+row.id+'" type="checkbox" value="19" style="width: 40px;height:20px;opacity:0" onclick="changebgcolor(this, '+row.id+');"/>';
	    		}
	        },
	        {field:'m20',title:'20',sortable:false,width:20,align : 'center',
	        	formatter: function(value, row, index){
	        		if (value && value == '1') {
	        			tty.push(row.id);
	    			}
	    			return '<input class="ck" name="cap_'+row.id+'" type="checkbox" value="20" style="width: 40px;height:20px;opacity:0" onclick="changebgcolor(this, '+row.id+');"/>';
	    		}
	        },
	        {field:'m21',title:'21',sortable:false,width:20,align : 'center',
	        	formatter: function(value, row, index){
	        		if (value && value == '1') {
	        			tty_one.push(row.id);
	    			}
	    			return '<input class="ck" name="cap_'+row.id+'" type="checkbox" value="21" style="width: 40px;height:20px;opacity:0" onclick="changebgcolor(this, '+row.id+');"/>';
	    		}
	        },
	        {field:'m22',title:'22',sortable:false,width:20,align : 'center',
	        	formatter: function(value, row, index){
	        		if (value && value == '1') {
	        			tty_two.push(row.id);
	    			}
	    			return '<input class="ck" name="cap_'+row.id+'" type="checkbox" value="22" style="width: 40px;height:20px;opacity:0" onclick="changebgcolor(this, '+row.id+');"/>';
	    		}
	        },
	        {field:'m23',title:'23',sortable:false,width:20,align : 'center',
	        	formatter: function(value, row, index){
	        		if (value && value == '1') {
	        			tty_thr.push(row.id);
	    			}
	    			return '<input class="ck" name="cap_'+row.id+'" type="checkbox" value="23" style="width: 40px;height:20px;opacity:0" onclick="changebgcolor(this, '+row.id+');"/>';
	    		}
	        },
	        {field:'m24',title:'24',sortable:false,width:20,align : 'center',
	        	formatter: function(value, row, index){
	        		if (value && value == '1') {
	        			tty_fur.push(row.id);
	    			}
	    			return '<input class="ck" name="cap_'+row.id+'" type="checkbox" value="24" style="width: 40px;height:20px;opacity:0" onclick="changebgcolor(this, '+row.id+');"/>';
	    		}
	        },
	        {field:'m25',title:'25',sortable:false,width:20,align : 'center',
	        	formatter: function(value, row, index){
	        		if (value && value == '1') {
	        			tty_fiv.push(row.id);
	    			}
	    			return '<input class="ck" name="cap_'+row.id+'" type="checkbox" value="25" style="width: 40px;height:20px;opacity:0" onclick="changebgcolor(this, '+row.id+');"/>';
	    		}
	        },
	        {field:'m26',title:'26',sortable:false,width:20,align : 'center',
	        	formatter: function(value, row, index){
	        		if (value && value == '1') {
	        			tty_six.push(row.id);
	    			}
	    			return '<input class="ck" name="cap_'+row.id+'" type="checkbox" value="26" style="width: 40px;height:20px;opacity:0" onclick="changebgcolor(this, '+row.id+');"/>';
	    		}
	        },
	        {field:'m27',title:'27',sortable:false,width:20,align : 'center',
	        	formatter: function(value, row, index){
	        		if (value && value == '1') {
	        			tty_sen.push(row.id);
	    			}
	    			return '<input class="ck" name="cap_'+row.id+'" type="checkbox" value="27" style="width: 40px;height:20px;opacity:0" onclick="changebgcolor(this, '+row.id+');"/>';
	    		}
	        },
	        {field:'m28',title:'28',sortable:false,width:20,align : 'center',
	        	formatter: function(value, row, index){
	        		if (value && value == '1') {
	        			tty_eig.push(row.id);
	    			}
	    			return '<input class="ck" name="cap_'+row.id+'" type="checkbox" value="28" style="width: 40px;height:20px;opacity:0" onclick="changebgcolor(this, '+row.id+');"/>';
	    		}
	        },
	        {field:'m29',title:'29',sortable:false,width:20,align : 'center',
	        	formatter: function(value, row, index){
	        		if (value && value == '1') {
	        			tty_nin.push(row.id);
	    			}
	    			return '<input class="ck" name="cap_'+row.id+'" type="checkbox" value="29" style="width: 40px;height:20px;opacity:0" onclick="changebgcolor(this, '+row.id+');"/>';
	    		}
	        },
	        {field:'m30',title:'30',sortable:false,width:20,align : 'center',
	        	formatter: function(value, row, index){
	        		if (value && value == '1') {
	        			tiy.push(row.id);
	    			}
	    			return '<input class="ck" name="cap_'+row.id+'" type="checkbox" value="30" style="width: 40px;height:20px;opacity:0" onclick="changebgcolor(this, '+row.id+');"/>';
	    		}
	        },
	        {field:'m31',title:'31',sortable:false,width:20,align : 'center',
	        	formatter: function(value, row, index){
	        		if (value && value == '1') {
	        			tiy_one.push(row.id);
	    			}
	    			return '<input class="ck" name="cap_'+row.id+'" type="checkbox" value="31" style="width: 40px;height:20px;opacity:0" onclick="changebgcolor(this, '+row.id+');"/>';
	    		}
	        }
	    ]],
	    onLoadSuccess: function(){
	        one.forEach(function(el){ if (!$("input[name=cap_"+el+"][value=1]").attr("checked")) $("input[name=cap_"+el+"][value=1]").trigger("click");} );
	        tow.forEach(function(el){ if (!$("input[name=cap_"+el+"][value=2]").attr("checked")) $("input[name=cap_"+el+"][value=2]").trigger("click");} );
	        thr.forEach(function(el){ if (!$("input[name=cap_"+el+"][value=3]").attr("checked")) $("input[name=cap_"+el+"][value=3]").trigger("click");} );
	        fur.forEach(function(el){ if (!$("input[name=cap_"+el+"][value=4]").attr("checked")) $("input[name=cap_"+el+"][value=4]").trigger("click");} );
	        fiv.forEach(function(el){ if (!$("input[name=cap_"+el+"][value=5]").attr("checked")) $("input[name=cap_"+el+"][value=5]").trigger("click");} );
	        six.forEach(function(el){ if (!$("input[name=cap_"+el+"][value=6]").attr("checked")) $("input[name=cap_"+el+"][value=6]").trigger("click");} );
	        sen.forEach(function(el){ if (!$("input[name=cap_"+el+"][value=7]").attr("checked")) $("input[name=cap_"+el+"][value=7]").trigger("click");} );
	        eig.forEach(function(el){ if (!$("input[name=cap_"+el+"][value=8]").attr("checked")) $("input[name=cap_"+el+"][value=8]").trigger("click");} );
	        nin.forEach(function(el){ if (!$("input[name=cap_"+el+"][value=9]").attr("checked")) $("input[name=cap_"+el+"][value=9]").trigger("click");} );
	        ten.forEach(function(el){ if (!$("input[name=cap_"+el+"][value=10]").attr("checked")) $("input[name=cap_"+el+"][value=10]").trigger("click");} );
	        ele.forEach(function(el){ if (!$("input[name=cap_"+el+"][value=11]").attr("checked")) $("input[name=cap_"+el+"][value=11]").trigger("click");} );
	        twl.forEach(function(el){ if (!$("input[name=cap_"+el+"][value=12]").attr("checked")) $("input[name=cap_"+el+"][value=12]").trigger("click");} );
	        tht.forEach(function(el){ if (!$("input[name=cap_"+el+"][value=13]").attr("checked")) $("input[name=cap_"+el+"][value=13]").trigger("click");} );
	        fut.forEach(function(el){ if (!$("input[name=cap_"+el+"][value=14]").attr("checked")) $("input[name=cap_"+el+"][value=14]").trigger("click");} );
	        fft.forEach(function(el){ if (!$("input[name=cap_"+el+"][value=15]").attr("checked")) $("input[name=cap_"+el+"][value=15]").trigger("click");} );
	        sxt.forEach(function(el){ if (!$("input[name=cap_"+el+"][value=16]").attr("checked")) $("input[name=cap_"+el+"][value=16]").trigger("click");} );
	        svt.forEach(function(el){ if (!$("input[name=cap_"+el+"][value=17]").attr("checked")) $("input[name=cap_"+el+"][value=17]").trigger("click");} );
	        egt.forEach(function(el){ if (!$("input[name=cap_"+el+"][value=18]").attr("checked")) $("input[name=cap_"+el+"][value=18]").trigger("click");} );
	        nnt.forEach(function(el){ if (!$("input[name=cap_"+el+"][value=19]").attr("checked")) $("input[name=cap_"+el+"][value=19]").trigger("click");} );
	        tty.forEach(function(el){ if (!$("input[name=cap_"+el+"][value=20]").attr("checked")) $("input[name=cap_"+el+"][value=20]").trigger("click");} );
	        tty_one.forEach(function(el){ if (!$("input[name=cap_"+el+"][value=21]").attr("checked")) $("input[name=cap_"+el+"][value=21]").trigger("click");} );
	        tty_two.forEach(function(el){ if (!$("input[name=cap_"+el+"][value=22]").attr("checked")) $("input[name=cap_"+el+"][value=22]").trigger("click");} );
	        tty_thr.forEach(function(el){ if (!$("input[name=cap_"+el+"][value=23]").attr("checked")) $("input[name=cap_"+el+"][value=23]").trigger("click");} );
	        tty_fur.forEach(function(el){ if (!$("input[name=cap_"+el+"][value=24]").attr("checked")) $("input[name=cap_"+el+"][value=24]").trigger("click");} );
	        tty_fiv.forEach(function(el){ if (!$("input[name=cap_"+el+"][value=25]").attr("checked")) $("input[name=cap_"+el+"][value=25]").trigger("click");} );
	        tty_six.forEach(function(el){ if (!$("input[name=cap_"+el+"][value=26]").attr("checked")) $("input[name=cap_"+el+"][value=26]").trigger("click");} );
	        tty_sen.forEach(function(el){ if (!$("input[name=cap_"+el+"][value=27]").attr("checked")) $("input[name=cap_"+el+"][value=27]").trigger("click");} );
	        tty_eig.forEach(function(el){ if (!$("input[name=cap_"+el+"][value=28]").attr("checked")) $("input[name=cap_"+el+"][value=28]").trigger("click");} );
	        tty_nin.forEach(function(el){ if (!$("input[name=cap_"+el+"][value=29]").attr("checked")) $("input[name=cap_"+el+"][value=29]").trigger("click");} );
	        tiy.forEach(function(el){ if (!$("input[name=cap_"+el+"][value=30]").attr("checked")) $("input[name=cap_"+el+"][value=30]").trigger("click");} );
	        tiy_one.forEach(function(el){ if (!$("input[name=cap_"+el+"][value=31]").attr("checked")) $("input[name=cap_"+el+"][value=31]").trigger("click");} );
	        
	        $(":checkbox").attr("disabled", "disabled");
	        
	        if (flag != '1') {// 查看页面不显示状态和操作栏
	        	$(this).datagrid("hideColumn", [ 'm32']);
	        	$(this).datagrid("hideColumn", [ 'caozuo']);
	        }
	    },
	    onDblClickRow: function (rowdata, rowindex, rowdomelement){
	    },
	    checkOnSelect:false,
		selectOnCheck:false,
		singleSelect:true,
	    toolbar:'#sbssgl_sbbystask_fir_tb'
	});
}

// 点击td中的复选框改变td的背景颜色
function changebgcolor(obj, id){
	var ckvalue = $(obj).val();
	if($(obj).prop("checked")){
		// 若该数组中不包含此id，则执行添加
		if ((ckvalue == '1') && (one.indexOf(id) == -1)) { one.push(id); console.log(one);} 
		else if ((ckvalue == '2')  && (tow.indexOf(id) == -1)) { tow.push(id); } 
		else if ((ckvalue == '3')  && (thr.indexOf(id) == -1)) { thr.push(id); } 
		else if ((ckvalue == '4')  && (fur.indexOf(id) == -1)) { fur.push(id); } 
		else if ((ckvalue == '5')  && (fiv.indexOf(id) == -1)) { fiv.push(id); } 
		else if ((ckvalue == '6')  && (six.indexOf(id) == -1)) { six.push(id); } 
		else if ((ckvalue == '7')  && (sen.indexOf(id) == -1)) { sen.push(id); } 
		else if ((ckvalue == '8')  && (eig.indexOf(id) == -1)) { eig.push(id); } 
		else if ((ckvalue == '9')  && (nin.indexOf(id) == -1)) { nin.push(id); } 
		else if ((ckvalue == '10') && (ten.indexOf(id) == -1)) { ten.push(id); } 
		else if ((ckvalue == '11') && (ele.indexOf(id) == -1)) { ele.push(id); } 
		else if ((ckvalue == '12') && (twl.indexOf(id) == -1)) { twl.push(id); }
		else if ((ckvalue == '13') && (tht.indexOf(id) == -1)) { tht.push(id); }
		else if ((ckvalue == '14') && (fut.indexOf(id) == -1)) { fut.push(id); }
		else if ((ckvalue == '15') && (fft.indexOf(id) == -1)) { fft.push(id); }
		else if ((ckvalue == '16') && (sxt.indexOf(id) == -1)) { sxt.push(id); }
		else if ((ckvalue == '17') && (svt.indexOf(id) == -1)) { svt.push(id); }
		else if ((ckvalue == '18') && (egt.indexOf(id) == -1)) { egt.push(id); }
		else if ((ckvalue == '19') && (nnt.indexOf(id) == -1)) { nnt.push(id); }
		else if ((ckvalue == '20') && (tty.indexOf(id) == -1)) { tty.push(id); }
		else if ((ckvalue == '21') && (tty_one.indexOf(id) == -1)) { tty-one.push(id); }
		else if ((ckvalue == '22') && (tty_two.indexOf(id) == -1)) { tty-two.push(id); }
		else if ((ckvalue == '23') && (tty_thr.indexOf(id) == -1)) { tty-thr.push(id); }
		else if ((ckvalue == '24') && (tty_fur.indexOf(id) == -1)) { tty-fur.push(id); }
		else if ((ckvalue == '25') && (tty_fiv.indexOf(id) == -1)) { tty-fiv.push(id); }
		else if ((ckvalue == '26') && (tty_six.indexOf(id) == -1)) { tty-six.push(id); }
		else if ((ckvalue == '27') && (tty_sen.indexOf(id) == -1)) { tty-sen.push(id); }
		else if ((ckvalue == '28') && (tty_eig.indexOf(id) == -1)) { tty-eig.push(id); }
		else if ((ckvalue == '29') && (tty_nin.indexOf(id) == -1)) { tty-nin.push(id); }
		else if ((ckvalue == '30') && (tiy.indexOf(id) == -1)) { tiy.push(id); }
		else if ((ckvalue == '31') && (tiy_one.indexOf(id) == -1)) { tiy-one.push(id); }
		// 改变复选框所在td的背景颜色
		$(obj).parent().parent().css('background-color','rgb(36, 198, 200)');
	}else{
		// 当复选框取消选中状态时，则移除执行删除
		if (ckvalue == '1') { var oneindex = one.indexOf(id); one.splice(oneindex, 1);} 
		else if (ckvalue == '2')  { var towindex = tow.indexOf(id); tow.splice(towindex, 1); } 
		else if (ckvalue == '3')  { var thrindex = thr.indexOf(id); thr.splice(thrindex, 1); } 
		else if (ckvalue == '4')  { var furindex = fur.indexOf(id); fur.splice(furindex, 1); }
		else if (ckvalue == '5')  { var fivindex = fiv.indexOf(id); fiv.splice(fivindex, 1); }
		else if (ckvalue == '6')  { var sixindex = six.indexOf(id); six.splice(sixindex, 1); } 
		else if (ckvalue == '7')  { var senindex = sen.indexOf(id); sen.splice(senindex, 1); } 
		else if (ckvalue == '8')  { var eigindex = eig.indexOf(id); eig.splice(eigindex, 1); } 
		else if (ckvalue == '9')  { var ninindex = nin.indexOf(id); nin.splice(ninindex, 1); }
		else if (ckvalue == '10') { var tenindex = ten.indexOf(id); ten.splice(tenindex, 1); }
	    else if (ckvalue == '11') { var eleindex = ele.indexOf(id); ele.splice(eleindex, 1); } 
		else if (ckvalue == '12') { var twlindex = twl.indexOf(id); twl.splice(twlindex, 1); }
		else if (ckvalue == '13') { var thtindex = tht.indexOf(id); tht.splice(thtindex, 1); } 
		else if (ckvalue == '14') { var futindex = fut.indexOf(id); fut.splice(futindex, 1); } 
		else if (ckvalue == '15') { var fftindex = fft.indexOf(id); fft.splice(fftindex, 1); }
		else if (ckvalue == '16') { var sxtindex = sxt.indexOf(id); sxt.splice(sxtindex, 1); }
		else if (ckvalue == '17') { var svtindex = svt.indexOf(id); svt.splice(svtindex, 1); } 
		else if (ckvalue == '18') { var egtindex = egt.indexOf(id); egt.splice(egtindex, 1); } 
		else if (ckvalue == '19') { var nntindex = nnt.indexOf(id); nnt.splice(nntindex, 1); }
		else if (ckvalue == '20') { var ttyindex = tty.indexOf(id); tty.splice(ttyindex, 1); }
	    else if (ckvalue == '21') { var tyyonindex = tty_one.indexOf(id); tty_one.splice(tyyonindex, 1); } 
		else if (ckvalue == '22') { var ttytwindex = tty_two.indexOf(id); tty_two.splice(ttytwindex, 1); }
		else if (ckvalue == '23') { var ttythindex = tty_thr.indexOf(id); tty_thr.splice(ttythindex, 1); } 
		else if (ckvalue == '24') { var ttyfuindex = tty_fur.indexOf(id); tty_fur.splice(ttyfuindex, 1); }
		else if (ckvalue == '25') { var ttyfiindex = tty_fiv.indexOf(id); tty_fiv.splice(ttyfiindex, 1); }
		else if (ckvalue == '26') { var ttysiindex = tty_six.indexOf(id); tty_six.splice(ttysiindex, 1); } 
		else if (ckvalue == '27') { var ttyseindex = tty_sen.indexOf(id); tty_sen.splice(ttyseindex, 1); } 
		else if (ckvalue == '28') { var ttyeiindex = tty_eig.indexOf(id); tty_eig.splice(ttyeiindex, 1); } 
		else if (ckvalue == '29') { var ttyniindex = tty_nin.indexOf(id); tty_nin.splice(ttyniindex, 1); }
		else if (ckvalue == '30') { var tiyindex = tiy.indexOf(id); tiy.splice(tiyindex, 1); }
	    else if (ckvalue == '31') { var tiyonindex = tiy_one.indexOf(id); tiy_one.splice(tiyonindex, 1); } 
		// 回复复选框所在td的背景颜色
		$(obj).parent().parent().css('background-color','#fff');
	}
}

//上传附件
function uploadfj(id){
	openDialog("上传附件",ctx+"/sbssgl/sbbytaskfir/uploadindex/"+id,"400px", "250px","");
}

//选择审核结果操作
function changeZt(id){
	openDialog("选择审核结果",ctx+"/sbssgl/sbbytaskfir/shjg/"+id,"400px", "200px","");
}

//查看审核记录
function viewShjl(id){
	openDialogView("查看审核记录",ctx+"/sbssgl/sbbytaskfir/viewshjl/"+id,"600px", "300px","");
}

