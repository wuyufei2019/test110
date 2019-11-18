var dg;
var dg2;


$(function(){
	getFirData();
	getSecData();
})

//设备一级保养计划
function getFirData(){
	dg=$('#sbssgl_sbbystask_fir_dg').datagrid({    
		method: "post",
		url: ctx+'/sbssgl/sbgl/list?sbtype='+ sbtype,
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
		pageList : [10, 50, 100, 150, 200, 250 ],
		scrollBarsize:5,
		singleSelect:true,
		striped:true,
		columns:[[    
		          {field:'m1',title:'设备编号',sortable:false,width:70,align:'center',rowspan: 2},
		          {field:'m2',title:'设备名称',sortable:false,width:70,align:'center',rowspan: 2},
		          {field:'m3',title:'型号与规格',sortable:false,width:80,align:'center',rowspan: 2,
		          	formatter: function(value, row, index){
		          		return row.m27 + " " + value;
		          	}
		          },
		          {field:'rq',title:'保养日期',sortable:false,width:868,align:'center',colspan: 31}
		      ],[
		      	  {field:'rq1',title:'1',sortable:false,width:28,align : 'center',
		      		  formatter: function(value, row, index){
		      			  return '<input class="ck" name="rq_'+row.id+'" type="checkbox" value="1" style="width: 40px;height:20px;opacity:0" onclick="changebgcolor(this, '+row.id+');"/>';
		      		  }
		      	  },
		          {field:'rq2',title:'2',sortable:false,width:28,align : 'center',
		          	  formatter: function(value, row, index){
		      			  return '<input class="ck" name="rq_'+row.id+'" type="checkbox" value="2" style="width: 40px;height:20px;opacity:0" onclick="changebgcolor(this, '+row.id+');"/>';
		      		  }
		          },
		          {field:'rq3',title:'3',sortable:false,width:28,align : 'center',
	          		  formatter: function(value, row, index){
		      			  return '<input class="ck" name="rq_'+row.id+'" type="checkbox" value="3" style="width: 40px;height:20px;opacity:0" onclick="changebgcolor(this, '+row.id+');"/>';
		      		  }	
		          },
		          {field:'rq4',title:'4',sortable:false,width:28,align : 'center',
		          	  formatter: function(value, row, index){
		      			  return '<input class="ck" name="rq_'+row.id+'" type="checkbox" value="4" style="width: 40px;height:20px;opacity:0" onclick="changebgcolor(this, '+row.id+');"/>';
		      		  }
		          }, 
		          {field:'rq5',title:'5',sortable:false,width:28,align : 'center',
		          	  formatter: function(value, row, index){
		      			  return '<input class="ck" name="rq_'+row.id+'" type="checkbox" value="5" style="width: 40px;height:20px;opacity:0" onclick="changebgcolor(this, '+row.id+');"/>';
		      		  }
		          },
		          {field:'rq6',title:'6',sortable:false,width:28,align : 'center',
		          	  formatter: function(value, row, index){
		      			  return '<input class="ck" name="rq_'+row.id+'" type="checkbox" value="6" style="width: 40px;height:20px;opacity:0" onclick="changebgcolor(this, '+row.id+');"/>';
		      		  }
		          },
		          {field:'rq7',title:'7',sortable:false,width:28,align : 'center',
		          	  formatter: function(value, row, index){
		      			  return '<input class="ck" name="rq_'+row.id+'" type="checkbox" value="7" style="width: 40px;height:20px;opacity:0" onclick="changebgcolor(this, '+row.id+');"/>';
		      		  }
		          },
		          {field:'rq8',title:'8',sortable:false,width:28,align : 'center',
		          	  formatter: function(value, row, index){
		      			  return '<input class="ck" name="rq_'+row.id+'" type="checkbox" value="8" style="width: 40px;height:20px;opacity:0" onclick="changebgcolor(this, '+row.id+');"/>';
		      		  }
		          },
		          {field:'rq9',title:'9',sortable:false,width:28,align : 'center',
		          	  formatter: function(value, row, index){
		      			  return '<input class="ck" name="rq_'+row.id+'" type="checkbox" value="9" style="width: 40px;height:20px;opacity:0" onclick="changebgcolor(this, '+row.id+');"/>';
		      		  }
		          },
		          {field:'rq10',title:'10',sortable:false,width:28,align : 'center',
		          	  formatter: function(value, row, index){
		      			  return '<input class="ck" name="rq_'+row.id+'" type="checkbox" value="10" style="width: 40px;height:20px;opacity:0" onclick="changebgcolor(this, '+row.id+');"/>';
		      		  }
		          },
		          {field:'rq11',title:'11',sortable:false,width:28,align : 'center',
		          	  formatter: function(value, row, index){
		      			  return '<input class="ck" name="rq_'+row.id+'" type="checkbox" value="11" style="width: 40px;height:20px;opacity:0" onclick="changebgcolor(this, '+row.id+');"/>';
		      		  }
		          },
		          {field:'rq12',title:'12',sortable:false,width:28,align : 'center',
		          	  formatter: function(value, row, index){
		      			  return '<input class="ck" name="rq_'+row.id+'" type="checkbox" value="12" style="width: 40px;height:20px;opacity:0" onclick="changebgcolor(this, '+row.id+');"/>';
		      		  }
		          },
		          {field:'rq13',title:'13',sortable:false,width:28,align : 'center',
		          	  formatter: function(value, row, index){
		      			  return '<input class="ck" name="rq_'+row.id+'" type="checkbox" value="13" style="width: 40px;height:20px;opacity:0" onclick="changebgcolor(this, '+row.id+');"/>';
		      		  }
		          },
		          {field:'rq14',title:'14',sortable:false,width:28,align : 'center',
		          	  formatter: function(value, row, index){
		      			  return '<input class="ck" name="rq_'+row.id+'" type="checkbox" value="14" style="width: 40px;height:20px;opacity:0" onclick="changebgcolor(this, '+row.id+');"/>';
		      		  }
		          },   
		          {field:'rq15',title:'15',sortable:false,width:28,align : 'center',
		          	  formatter: function(value, row, index){
		      			  return '<input class="ck" name="rq_'+row.id+'" type="checkbox" value="15" style="width: 40px;height:20px;opacity:0" onclick="changebgcolor(this, '+row.id+');"/>';
		      		  }
		          },
		          {field:'rq16',title:'16',sortable:false,width:28,align : 'center',
		          	  formatter: function(value, row, index){
		      			  return '<input class="ck" name="rq_'+row.id+'" type="checkbox" value="16" style="width: 40px;height:20px;opacity:0" onclick="changebgcolor(this, '+row.id+');"/>';
		      		  }
		          },
		          {field:'rq17',title:'17',sortable:false,width:28,align : 'center',
		          	  formatter: function(value, row, index){
		      			  return '<input class="ck" name="rq_'+row.id+'" type="checkbox" value="17" style="width: 40px;height:20px;opacity:0" onclick="changebgcolor(this, '+row.id+');"/>';
		      		  }
		          },
		          {field:'rq18',title:'18',sortable:false,width:28,align : 'center',
		          	  formatter: function(value, row, index){
		      			  return '<input class="ck" name="rq_'+row.id+'" type="checkbox" value="18" style="width: 40px;height:20px;opacity:0" onclick="changebgcolor(this, '+row.id+');"/>';
		      		  }
		          },
		          {field:'rq19',title:'19',sortable:false,width:28,align : 'center',
		          	  formatter: function(value, row, index){
		      			  return '<input class="ck" name="rq_'+row.id+'" type="checkbox" value="19" style="width: 40px;height:20px;opacity:0" onclick="changebgcolor(this, '+row.id+');"/>';
		      		  }
		          },
		          {field:'rq20',title:'20',sortable:false,width:28,align : 'center',
		          	  formatter: function(value, row, index){
		      			  return '<input class="ck" name="rq_'+row.id+'" type="checkbox" value="20" style="width: 40px;height:20px;opacity:0" onclick="changebgcolor(this, '+row.id+');"/>';
		      		  }
		          },
		          {field:'rq21',title:'21',sortable:false,width:28,align : 'center',
		          	  formatter: function(value, row, index){
		      			  return '<input class="ck" name="rq_'+row.id+'" type="checkbox" value="21" style="width: 40px;height:20px;opacity:0" onclick="changebgcolor(this, '+row.id+');"/>';
		      		  }
		          },
		          {field:'rq22',title:'22',sortable:false,width:28,align : 'center',
		          	  formatter: function(value, row, index){
		      			  return '<input class="ck" name="rq_'+row.id+'" type="checkbox" value="22" style="width: 40px;height:20px;opacity:0" onclick="changebgcolor(this, '+row.id+');"/>';
		      		  }
		          },
		          {field:'rq23',title:'23',sortable:false,width:28,align : 'center',
		          	  formatter: function(value, row, index){
		      			  return '<input class="ck" name="rq_'+row.id+'" type="checkbox" value="23" style="width: 40px;height:20px;opacity:0" onclick="changebgcolor(this, '+row.id+');"/>';
		      		  }
		          },
		          {field:'rq24',title:'24',sortable:false,width:28,align : 'center',
		          	  formatter: function(value, row, index){
		      			  return '<input class="ck" name="rq_'+row.id+'" type="checkbox" value="24" style="width: 40px;height:20px;opacity:0" onclick="changebgcolor(this, '+row.id+');"/>';
		      		  }
		          },
		          {field:'rq25',title:'25',sortable:false,width:28,align : 'center',
		          	  formatter: function(value, row, index){
		      			  return '<input class="ck" name="rq_'+row.id+'" type="checkbox" value="25" style="width: 40px;height:20px;opacity:0" onclick="changebgcolor(this, '+row.id+');"/>';
		      		  }
		          },
		          {field:'rq26',title:'26',sortable:false,width:28,align : 'center',
		          	  formatter: function(value, row, index){
		      			  return '<input class="ck" name="rq_'+row.id+'" type="checkbox" value="26" style="width: 40px;height:20px;opacity:0" onclick="changebgcolor(this, '+row.id+');"/>';
		      		  }
		          },
		          {field:'rq27',title:'27',sortable:false,width:28,align : 'center',
		          	  formatter: function(value, row, index){
		      			  return '<input class="ck" name="rq_'+row.id+'" type="checkbox" value="27" style="width: 40px;height:20px;opacity:0" onclick="changebgcolor(this, '+row.id+');"/>';
		      		  }
		          },
		          {field:'rq28',title:'28',sortable:false,width:28,align : 'center',
		          	  formatter: function(value, row, index){
		      			  return '<input class="ck" name="rq_'+row.id+'" type="checkbox" value="28" style="width: 40px;height:20px;opacity:0" onclick="changebgcolor(this, '+row.id+');"/>';
		      		  }
		          },
		          {field:'rq29',title:'29',sortable:false,width:28,align : 'center',
		           	  formatter: function(value, row, index){
		      			  return '<input class="ck" name="rq_'+row.id+'" type="checkbox" value="29" style="width: 40px;height:20px;opacity:0" onclick="changebgcolor(this, '+row.id+');"/>';
		      		  }
		          },
		          {field:'rq30',title:'30',sortable:false,width:28,align : 'center',
			          formatter: function(value, row, index){
			      		  return '<input class="ck" name="rq_'+row.id+'" type="checkbox" value="30" style="width: 40px;height:20px;opacity:0" onclick="changebgcolor(this, '+row.id+');"/>';
			      	  }
			      },
		          {field:'rq31',title:'31',sortable:false,width:28,align : 'center',
			          formatter: function(value, row, index){
			      		  return '<input class="ck" name="rq_'+row.id+'" type="checkbox" value="31" style="width: 40px;height:20px;opacity:0" onclick="changebgcolor(this, '+row.id+');"/>';
			      	  }
			      },
		      ]],
	      onLoadSuccess: function(){
	    	one.forEach(function(el){$("input[name=rq_"+el+"][value=1]").trigger("click");});
	    	tow.forEach(function(el){$("input[name=rq_"+el+"][value=2]").trigger("click");});
	    	thr.forEach(function(el){$("input[name=rq_"+el+"][value=3]").trigger("click");});
	    	fur.forEach(function(el){$("input[name=rq_"+el+"][value=4]").trigger("click");});
	    	fiv.forEach(function(el){$("input[name=rq_"+el+"][value=5]").trigger("click");});
	    	six.forEach(function(el){$("input[name=rq_"+el+"][value=6]").trigger("click");});
	    	sen.forEach(function(el){$("input[name=rq_"+el+"][value=7]").trigger("click");});
	    	eig.forEach(function(el){$("input[name=rq_"+el+"][value=8]").trigger("click");});
	    	nin.forEach(function(el){$("input[name=rq_"+el+"][value=9]").trigger("click");});
	    	ten.forEach(function(el){$("input[name=rq_"+el+"][value=10]").trigger("click");});
	    	ele.forEach(function(el){$("input[name=rq_"+el+"][value=11]").trigger("click");});
	    	twl.forEach(function(el){$("input[name=rq_"+el+"][value=12]").trigger("click");});
	    	tht.forEach(function(el){$("input[name=rq_"+el+"][value=13]").trigger("click");});
	    	fut.forEach(function(el){$("input[name=rq_"+el+"][value=14]").trigger("click");});
	    	fft.forEach(function(el){$("input[name=rq_"+el+"][value=15]").trigger("click");});
	    	sxt.forEach(function(el){$("input[name=rq_"+el+"][value=16]").trigger("click");});
	    	svt.forEach(function(el){$("input[name=rq_"+el+"][value=17]").trigger("click");});
	    	egt.forEach(function(el){$("input[name=rq_"+el+"][value=18]").trigger("click");});
	    	nnt.forEach(function(el){$("input[name=rq_"+el+"][value=19]").trigger("click");});
	    	tty.forEach(function(el){$("input[name=rq_"+el+"][value=20]").trigger("click");});
	    	tty_one.forEach(function(el){$("input[name=rq_"+el+"][value=21]").trigger("click");});
	    	tty_two.forEach(function(el){$("input[name=rq_"+el+"][value=22]").trigger("click");});
	    	tty_thr.forEach(function(el){$("input[name=rq_"+el+"][value=23]").trigger("click");});
	    	tty_fur.forEach(function(el){$("input[name=rq_"+el+"][value=24]").trigger("click");});
	    	tty_fiv.forEach(function(el){$("input[name=rq_"+el+"][value=25]").trigger("click");});
	    	tty_six.forEach(function(el){$("input[name=rq_"+el+"][value=26]").trigger("click");});
	    	tty_sen.forEach(function(el){$("input[name=rq_"+el+"][value=27]").trigger("click");});
	    	tty_eig.forEach(function(el){$("input[name=rq_"+el+"][value=28]").trigger("click");});
	    	tty_nin.forEach(function(el){$("input[name=rq_"+el+"][value=29]").trigger("click");});
	    	tiy.forEach(function(el){$("input[name=rq_"+el+"][value=30]").trigger("click");});
	    	tiy_one.forEach(function(el){$("input[name=rq_"+el+"][value=31]").trigger("click");});
	    },
	    onDblClickRow: function (rowdata, rowindex, rowdomelement){
	    },
		checkOnSelect:false,
		selectOnCheck:false,
	    toolbar:'#sbssgl_sbbystask_fir_tb'
	});
}

//设备二级保养计划
function getSecData(){
	dg2=$('#sbssgl_sbbystask_sec_dg').datagrid({    
		method: "post",
	    url: ctx+'/sbssgl/sbgl/list?sbtype='+ sbtype,
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
	        {field:'m1',title:'设备编号',sortable:false,width:70,align:'center'},
	        {field:'m2',title:'设备名称',sortable:false,width:70,align:'center'},
	        {field:'m3',title:'型号与规格',sortable:false,width:80,align:'center',
	        	formatter: function(value, row, index){
	        		return row.m27 + " " + value;
	        	}
	        },
	        
	    	{field:'yf1',title:'1',sortable:false,width:30,align : 'center',
	    		formatter: function(value, row, index){
	    			return '<input class="ck" name="cap_'+row.id+'" type="checkbox" value="1" style="width: 40px;height:30px;opacity:0" onclick="changebgcolor2(this, '+row.id+');"/>';
	    		}
	    	},
	        {field:'yf2',title:'2',sortable:false,width:30,align : 'center',
	        	formatter: function(value, row, index){
	    			return '<input class="ck" name="cap_'+row.id+'" type="checkbox" value="2" style="width: 40px;height:30px;opacity:0" onclick="changebgcolor2(this, '+row.id+');"/>';
	    		}
	        },
	        {field:'yf3',title:'3',sortable:false,width:30,align : 'center',
	        	formatter: function(value, row, index){
	    			return '<input class="ck" name="cap_'+row.id+'" type="checkbox" value="3" style="width: 40px;height:30px;opacity:0" onclick="changebgcolor2(this, '+row.id+');"/>';
	    		}
	        },
	        {field:'yf4',title:'4',sortable:false,width:30,align : 'center',
	        	formatter: function(value, row, index){
	    			return '<input class="ck" name="cap_'+row.id+'" type="checkbox" value="4" style="width: 40px;height:30px;opacity:0" onclick="changebgcolor2(this, '+row.id+');"/>';
	    		}
	        },
	        {field:'yf5',title:'5',sortable:false,width:30,align : 'center',
	        	formatter: function(value, row, index){
	    			return '<input class="ck" name="cap_'+row.id+'" type="checkbox" value="5" style="width: 40px;height:30px;opacity:0" onclick="changebgcolor2(this, '+row.id+');"/>';
	    		}
	        },
	        {field:'yf6',title:'6',sortable:false,width:30,align : 'center',
	        	formatter: function(value, row, index){
	    			return '<input class="ck" name="cap_'+row.id+'" type="checkbox" value="6" style="width: 40px;height:30px;opacity:0" onclick="changebgcolor2(this, '+row.id+');"/>';
	    		}
	        },
	        {field:'yf7',title:'7',sortable:false,width:30,align : 'center',
	        	formatter: function(value, row, index){
	    			return '<input class="ck" name="cap_'+row.id+'" type="checkbox" value="7" style="width: 40px;height:30px;opacity:0" onclick="changebgcolor2(this, '+row.id+');"/>';
	    		}
	        },
	        {field:'yf8',title:'8',sortable:false,width:30,align : 'center',
	        	formatter: function(value, row, index){
	    			return '<input class="ck" name="cap_'+row.id+'" type="checkbox" value="8" style="width: 40px;height:30px;opacity:0" onclick="changebgcolor2(this, '+row.id+');"/>';
	    		}
	        },
	        {field:'yf9',title:'9',sortable:false,width:30,align : 'center',
	        	formatter: function(value, row, index){
	    			return '<input class="ck" name="cap_'+row.id+'" type="checkbox" value="9" style="width: 40px;height:30px;opacity:0" onclick="changebgcolor2(this, '+row.id+');"/>';
	    		}
	        },
	        {field:'yf10',title:'10',sortable:false,width:30,align : 'center',
	        	formatter: function(value, row, index){
	    			return '<input class="ck" name="cap_'+row.id+'" type="checkbox" value="10" style="width: 40px;height:30px;opacity:0" onclick="changebgcolor2(this, '+row.id+');"/>';
	    		}
	        },
	        {field:'yf11',title:'11',sortable:false,width:30,align : 'center',
	        	formatter: function(value, row, index){
	    			return '<input class="ck" name="cap_'+row.id+'" type="checkbox" value="11" style="width: 40px;height:30px;opacity:0" onclick="changebgcolor2(this, '+row.id+');"/>';
	    		}
	        },
	        {field:'yf12',title:'12',sortable:false,width:30,align : 'center',
	        	formatter: function(value, row, index){
	    			return '<input class="ck" name="cap_'+row.id+'" type="checkbox" value="12" style="width: 40px;height:30px;opacity:0" onclick="changebgcolor2(this, '+row.id+');"/>';
	    		}
	        }
	    ]],
	    onLoadSuccess: function(){
	    	console.log(1);
	    	console.log("jau:"+jau)
	    	jau.forEach(function(el){$("input[name=cap_"+el+"][value=1]").trigger("click");});
	    	feb.forEach(function(el){$("input[name=cap_"+el+"][value=2]").trigger("click");});
	    	mar.forEach(function(el){$("input[name=cap_"+el+"][value=3]").trigger("click");});
	    	apr.forEach(function(el){$("input[name=cap_"+el+"][value=4]").trigger("click");});
	    	may.forEach(function(el){$("input[name=cap_"+el+"][value=5]").trigger("click");});
	    	jun.forEach(function(el){$("input[name=cap_"+el+"][value=6]").trigger("click");});
	    	jul.forEach(function(el){$("input[name=cap_"+el+"][value=7]").trigger("click");});
	    	aug.forEach(function(el){$("input[name=cap_"+el+"][value=8]").trigger("click");});
	    	sep.forEach(function(el){$("input[name=cap_"+el+"][value=9]").trigger("click");});
	    	oct.forEach(function(el){$("input[name=cap_"+el+"][value=10]").trigger("click");});
	    	nov.forEach(function(el){$("input[name=cap_"+el+"][value=11]").trigger("click");});
	    	dec.forEach(function(el){$("input[name=cap_"+el+"][value=12]").trigger("click");});
	    },
	    onDblClickRow: function (rowdata, rowindex, rowdomelement){
	    },
	    checkOnSelect:false,
		selectOnCheck:false,
	    toolbar:'#sbssgl_sbbystask_sec_tb'
	});
}

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
		else if ((ckvalue == '21') && (tty_one.indexOf(id) == -1)) { tty_one.push(id); }
		else if ((ckvalue == '22') && (tty_two.indexOf(id) == -1)) { tty_two.push(id); }
		else if ((ckvalue == '23') && (tty_thr.indexOf(id) == -1)) { tty_thr.push(id); }
		else if ((ckvalue == '24') && (tty_fur.indexOf(id) == -1)) { tty_fur.push(id); }
		else if ((ckvalue == '25') && (tty_fiv.indexOf(id) == -1)) { tty_fiv.push(id); }
		else if ((ckvalue == '26') && (tty_six.indexOf(id) == -1)) { tty_six.push(id); }
		else if ((ckvalue == '27') && (tty_sen.indexOf(id) == -1)) { tty_sen.push(id); }
		else if ((ckvalue == '28') && (tty_eig.indexOf(id) == -1)) { tty_eig.push(id); }
		else if ((ckvalue == '29') && (tty_nin.indexOf(id) == -1)) { tty_nin.push(id); }
		else if ((ckvalue == '30') && (tiy.indexOf(id) == -1)) { tiy.push(id); }
		else if ((ckvalue == '31') && (tiy_one.indexOf(id) == -1)) { tiy_one.push(id); }
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


function changebgcolor2(obj, id){
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
	}else{
		console.log(jau);
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
