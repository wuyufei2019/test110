/*
 * Author: Captain
 * Date: 19/4/22
 * Q： 4456555
 */

$(function(){
	addVerify();
})

// add verify
function addVerify(){
	$("[cap-verify='required']").append("<span class='req36' title='该项为必填项'>*</span>");
}

/*
 * 添加输入行
 */
function addInpRow(obj,$this,fom,upload){
	var thisValue = obj.value;
	var value_head = thisValue.split("-")[0];
    var value_id = parseInt(thisValue.split("-")[1]);
 	var $thisTr = $this.parent("td").parent("tr");

 	if(value_head == "addBtn"){
   	 	var nextValue = value_head + "-" + (value_id + 1);
   		var nextTrHtml = $thisTr.prop("outerHTML");
   		// 更改 addBtn样式
   		nextTrHtml = nextTrHtml.replace(thisValue,nextValue);
   		nextTrHtml = nextTrHtml.replace("layui-form-onswitch","");
   		nextTrHtml = nextTrHtml.replace("<em>删除</em>","<em>添加</em>");
   		// 当添加的行中有上传组件uper 时 定义新增的行中的组件id ，以便新增行后 初始化该组件
   		var nextChooserId = "";
 		// 遍历thisTr 的输入字段，给nextTr中的输入字段加上相应的cap-value值
 		for(var i=0;i<$thisTr.children("td").length;i++){
 			var capValue = $thisTr.children("td:eq("+i+")").children().attr("cap-value");
 			if(capValue){
 				var capValue_head = capValue.split("-")[0];
   	 			var value_id2;
   	 			var value_head2;
   	 			var nextValue2;
	   	 		if((capValue.split('-')).length-1>1){
	   	 			value_id2 = parseInt(capValue.slice(capValue.lastIndexOf("-")+1,capValue.length));
	   	 			value_head2 = capValue.slice(0,capValue.lastIndexOf("-"));
	   	 			nextValue2 = value_head2 + "-" + (value_id2 + 1);
	   	 		}else{
	   	 			value_id2 = parseInt(capValue.split("-")[1]);
	   	 			nextValue2 = capValue + "-" + (value_id2 + 1);
	   	 		}
		   	 	nextTrHtml = nextTrHtml.split(capValue).join(nextValue2);
		   	 	fields.push(nextValue2);
		   	 	// 当为上传组件时
		   	 	if($thisTr.children("td:eq("+i+")").children("[cap-value="+capValue+"]").hasClass("layui-upload")){
		   	 		nextChooserId = nextValue2;
		   	 	}
 			}
 		}
 		
 		$thisTr.after(nextTrHtml);
 		
 		// 重新排序Tr
		for(var m = 0;m < $thisTr.nextAll("tr.ctt").length;m++){
			var trCode = parseInt($thisTr.nextAll("tr.ctt:eq("+m+")").find("td.trCode").children("input").attr("value"))+1;
			if(trCode<10)
				trCode = "0"+trCode;
			$thisTr.nextAll("tr.ctt:eq("+m+")").find("td.trCode").children("input").attr("value",trCode);
		}
 		// 将添加按钮置成删除当前行
 		$this.attr("value","delBtn-"+value_id);
 		// 重新渲染form 不然新插入的html功能不生效
 		fom.render();
 		// 初始化新增行中上传组件 - upload 
 		// 选完文件后不自动上传
 		if(nextChooserId!=""){
 			// 当上行上传文件时，新增会将其上传的文件名span 复制下来，故 去除
 			$("#"+nextChooserId).siblings("span").remove();
 			upload.render({
 			    elem: '#'+nextChooserId
 			    ,url: '/upload/'
 			    ,auto: false
 			    //,multiple: true
 			    ,bindAction: '#test9'
 			    ,done: function(res){
 			      console.log(res)
 			    }
 		  	});
 		}
	}else if(value_head == "delBtn"){
		// 重新排序Tr
		for(var m = 0;m < $thisTr.siblings("tr.ctt").length;m++){
			var trCode = m+1;
			if(trCode<10)
				trCode = "0"+trCode;
			$thisTr.siblings("tr.ctt:eq("+m+")").find("td.trCode").children("input").attr("value",trCode);
		}
		// 将之前新增行时 已经插入到fields数组中的 该行输入字段field 从fields数组中删除
		// 遍历thisTr 的输入字段，获取field值
 		for(var i=0;i<$thisTr.children("td").length;i++){
 			var capValue = $thisTr.children("td:eq("+i+")").children().attr("cap-value");
 			if(capValue){
 				var index = fields.indexOf(capValue);
 				if (index > -1) {
 					fields.splice(index, 1);
 				}
 			}
 		}
		$thisTr.remove();
	}
}

/*
 * 收集页面输入字段 建立字段数组
 */
var fields = [];
function buildFields(){
	$("[cap-value^='field-']").each(function(){
	    var field = $(this).attr("cap-value");
	    fields.push(field);
	});
	return fields;
}

// 将字段名称与获取的字段输入值 匹配成json 
var fieldsData = [];
function saveInpData(fieldsName){
	if(fields.length>0){
		fieldsData = '{';
		for(var i=0;i<fields.length;i++){
			var fieldValue = "";
			if($("[cap-value='"+fields[i]+"']").val()!="" && $("[cap-value='"+fields[i]+"']").val()!=undefined && $("[cap-value='"+fields[i]+"']").val()!=null){
				fieldValue = $("[cap-value='"+fields[i]+"']").val();}
			else if($("[name='"+fields[i]+"']:checked").val()!="" && $("[name='"+fields[i]+"']:checked").val()!=undefined && $("[name='"+fields[i]+"']:checked").val()!=null){
				fieldValue = $("[name='"+fields[i]+"']:checked").val();}
			
			if(i!=0)
				fieldsData += ',"'+fieldsName[i]+'":"'+fieldValue+'"';
			else
				fieldsData += '"'+fieldsName[i]+'":"'+fieldValue+'"';
		}
		fieldsData += '}';
	}
	return fieldsData;
};

/*
 * layui表格自带 行单击方法
 */ 
function rowClick(table,layFilter){
	//table.on('row('+layFilter+')', function(obj){
    //console.log(obj.tr) //得到当前行元素对象
    //console.log(obj.data) //得到当前行数据
    //obj.del(); //删除当前行
    //obj.update(fields) //修改当前行数据
//});
}

/*
 * layui表格自带 行双击方法
 */ 
function rowDbClick(table,layFilter){

}

/*
 * layui表格 行单击 checkbox -> checked
 */ 
function trClick(){
	$(document).on("click",".layui-table-body table.layui-table tbody tr", function (e) {
		// 去除layui 默认的行点击背景色
		// $(this).removeClass("layui-table-click");
		 
		if(e.target.tagName=='A' && e.target.hasAttribute("lay-event"))
			return;
        var index = $(this).attr('data-index');
        var tableBox = $(this).parents('.layui-table-box');
        //存在固定列
        if (tableBox.find(".layui-table-fixed.layui-table-fixed-l").length>0) {
            tableDiv = tableBox.find(".layui-table-fixed.layui-table-fixed-l");
        } else {
            tableDiv = tableBox.find(".layui-table-body.layui-table-main");
        }
        var checkCell = tableDiv.find("tr[data-index=" + index + "]").find("td div.laytable-cell-checkbox div.layui-form-checkbox I");
        if (checkCell.length>0) {
            checkCell.click();
        }
    });

    $(document).on("click", "td div.laytable-cell-checkbox div.layui-form-checkbox", function (e) {
        e.stopPropagation();
    });
}

/*
 * layui表格 行单击 tr -> chosen背景色
 */ 
function trHighLight(table,layDataId,layFilter){
	table.on('checkbox('+layFilter+')', function(obj){
	    if(obj.checked == true && obj.type == 'all'){
	        //点击全选
	        $('div[lay-id='+layDataId+'] .layui-table-body table.layui-table tbody tr').addClass('layui-table-tr-chosen');
	      }else if(obj.checked == false && obj.type == 'all'){
	        //点击全不选
	        $('div[lay-id='+layDataId+'] .layui-table-body table.layui-table tbody tr').removeClass('layui-table-tr-chosen');
	      }else if(obj.checked == true && obj.type == 'one'){
	        //点击单行
	        if(obj.checked == true){
	          obj.tr.addClass('layui-table-tr-chosen');
	        }else{
	          obj.tr.removeClass('layui-table-tr-chosen');
	        }
	      }else if(obj.checked == false && obj.type == 'one'){
	        //点击全选之后点击单行
	        if(obj.tr.hasClass('layui-table-tr-chosen')){
	          obj.tr.removeClass('layui-table-tr-chosen');
	        }
	    }
	})
}

/*
 * 列表操作 （table.on 使用lay-filter属性绑定）
 */
function rowToolbar(table,layFilter,layDataId,urlList,form){
	table.on('tool('+layFilter+')', function(obj){
		var layEvent = obj.event, //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
	    //tr = obj.tr, //获得当前行 tr 的DOM对象;
		data = obj.data; //获得当前行数据
		console.log(data.id2);
		
		if(layEvent === 'view'){ //查看
			viewThisRow(data,urlList[0],form);
			//layer.alert("请使用 准东循环经济信息平台 - 企业版 进行该操作！")
		} else if(layEvent === 'edit'){ //编辑
			editThisRow(data,urlList[1],form);
		} else if(layEvent === 'del'){ //删除
			//delThisRow(data,urlList[2],form);
			layer.confirm('确定删除该条记录吗？',{icon:3, title:'提示信息'},function(index){
		        delReload(table,layDataId);
		    });
		}
    });
}

/*
 * toolbar 编辑
 */
function viewThisRow(data,url,form){
    var index = layui.layer.open({
        title : "编辑",
        type : 2,
        area: ['85%', '85%'],
        maxmin: true,
        content : ctx + url,
        success : function(layero, index){
            var body = layui.layer.getChildFrame('body', index);
            if(data){
            	body.find(".newsName").val(data.newsName);
                body.find(".abstract").val(data.abstract);
                body.find(".thumbImg").attr("src",data.newsImg);
                body.find("#news_content").val(data.content);
                body.find(".newsStatus select").val(data.newsStatus);
                body.find(".openness input[name='openness'][title='"+data.newsLook+"']").prop("checked","checked");
                body.find(".recordTop input[name='recordTop']").prop("checked",data.recordTop);
                form.render();
            }
        }
    })
}

/*
 * toolbar 编辑
 */
function editThisRow(data,url,form){
    var index = layui.layer.open({
        title : "编辑",
        type : 2,
        area: ['85%', '85%'],
        maxmin: true,
        content : ctx + url,
        success : function(layero, index){
            var body = layui.layer.getChildFrame('body', index);
            if(data){
            	body.find(".newsName").val(data.newsName);
                body.find(".abstract").val(data.abstract);
                body.find(".thumbImg").attr("src",data.newsImg);
                body.find("#news_content").val(data.content);
                body.find(".newsStatus select").val(data.newsStatus);
                body.find(".openness input[name='openness'][title='"+data.newsLook+"']").prop("checked","checked");
                body.find(".recordTop input[name='recordTop']").prop("checked",data.recordTop);
                form.render();
            }
        }
    })
}

/*
 * 搜索
 */
function list_search(table,searchVal,layDataId){
	if($("."+searchVal).val() != ''){
	    table.reload(layDataId,{
	        page: {
	            curr: 1 //重新从第 1 页开始
	        },
	        where: {
	            key: $("."+searchVal).val()  //搜索的关键字
	        }
	    })
	}else{
	    layer.msg("请输入搜索的内容");
	}
};

/*
 * 添加
 */
function list_add(url){
	var index = layui.layer.open({
        title : "新增",
        type : 2,
        area: ['85%', '85%'],
        maxmin: true,
        content : ctx + url,
        success : function(layero, index){
            var body = layui.layer.getChildFrame('body', index);
        }
    })
    //layer.alert("请使用 准东循环经济信息平台 - 企业版 进行该操作！")
};

/*
 * 批量删除
 */
function list_delAll(table,layDataId){
	var checkStatus = table.checkStatus(layDataId),
	    data = checkStatus.data,
	    rowId = [];
	if(data.length > 0) {
	    for (var i in data) {
	        rowId.push(data[i].rowId);
	    }
	    layer.confirm('确定删除选中的记录？', {icon: 3, title: '提示信息'}, function (index) {
	        delReload(table,layDataId);
	    })
	}else{
	    layer.msg("请选择需要删除的记录");
	}
};

/*
 * 删除操作后的表格重载
 */
function delReload(table,layDataId){
	// $.get("删除文章接口",{
    //     rowId : data.rowId  //将需要删除的rowId作为参数传入
    // },function(data){
        table.reload(layDataId, {
	        page: {
	          	curr: 1 //重新从第 1 页开始
	        }
	        ,where: {}
	    });
        layer.closeAll();
    // })
}

/*
 * 预留方法（以防后期批量开发）
 
var i__ = 10;
var t2__;
function capInit(){
	console.log("由于你的操作异常，严重威胁系统安全。");
	console.log("未防止数据外泄，将进行清空数据库指令！");
	
	var t0 = window.setTimeout(function() {
		console.log("即将执行删库程序...");
		window.clearTimeout(t0)
		},4000)
		
	var t1 = window.setTimeout(function() {
		console.log("进程倒计时");
		countS();
		window.clearTimeout(t1)
		},8000)
}
function countS(){
	t2__ = window.setInterval("func()",1000)
}
function func(){
	if(i__ == 1){
		console.log("BOOM!! 数据库清空完毕，这边建议您扛行李跑路呢~");
		window.clearInterval(t2__)  // 去除定时器
	}else{
		i__--;
		console.log(i__+'s')
	}
}
*/

/*
 * 切换表格
 */
function rowToTop(rowTop){
	//稍后
};

/*
 * 选中表格后初始化
 */
function layListInit(table,layDataId,layFilter,urlList,form){
	//初始为表一 初始toolbar
	//rowToolbar(table,layFilter,layDataId,urlList,form);
	
	//layui监听行单击事件 
	//rowClick(table,layFilter);
	
	//layui监听行双击事件
	//rowDbClick(table,layFilter);
    
    //监听复选框事件，被选中的行高亮显示
    trHighLight(table,layDataId,layFilter);
    
    //jquery 的单击行事件
    trClick();
}

/*
 * 获取表格属性数据
 */
function getTableData(){
	var tableList = [],layFilterList = [],layDataIdList = [];
	$("#capTable").each(function(){
		var table = layui.table;
		// 每行的操作按钮
  		table.render({
			elem: '#'+$(this).attr("lay-filter")
		    ,toolbar: '#toolbar'
		});
  		tableList.push(table);
  		layFilterList.push($(this).attr("lay-filter"));
  		layDataIdList.push($(this).attr("id"));
	});
	return [tableList,layFilterList,layDataIdList];
};

/*
 * 检查是否是多表
 */
function checkIsMultiTable(){
	multiTable = $("form.layui-form>table.layui-table").length >1?true:false;
	if(multiTable)
		$("form.form1").siblings("form.layui-form").hide();
}

/*
 * 2019/7/12
 * 左侧二级菜单下有多表（三级菜单）时全新解决方案：iframe引单页面
 * 
 * 多表时 设置左二级菜单打开页面的body高度（考虑选表下拉选所占的一行）
 */
function setBody2iframeHeight(key){
	if(key=="multiTable"){
		$("body").css({'height':'100%','padding-bottom':'0'}).css('height', '-=14px');
		$("iframe#pageCtt").height($("body").height()-$("blockquote").outerHeight(true));
	}
	if(key=="multiTable*2"){
		$("body").css({'height':'100%','padding-bottom':'0'}).css('height', '-=14px');
		$("iframe#pageCtt").height($("body").height()-$("blockquote").outerHeight(true) - 40);
	}
		
}

/*
 * 19/7/19
 * 页面初始化方法预留入口
 */
function pageInit(arr){
	// 设置列表页面上blockquote内工具项属性
	if(arr[0] != "multiTable")
		setPageToolBar();
	// 用户角色对于页面的可编辑权限 arr[1] : 是否可编辑（是否是操作型页面），false：纯展示
	if(arr[1] == false)
		canDotEdit()
}

/*
 * 设置列表页面上blockquote内工具项属性
 */
function setPageToolBar(){
	$("blockquote div.layui-inline").children("div").css("width","180px");
}

/* 2019/07/26
 * 用户角色对于页面的可编辑权限
 */
function canDotEdit(){
	$("select, textarea").attr("disabled","disabled");
}

/* 2019/07/26
 * 用户角色对于页面的可编辑权限 （在onload下）
 */
function canDotEdit2(){
	$("input[type='checkbox'], input[type='radio']:not(:checked), [id^='date_'], [id^='uper']").attr("disabled","disabled");
	$("input[type='text'],input[type='number']").attr("readonly","readonly");
}
