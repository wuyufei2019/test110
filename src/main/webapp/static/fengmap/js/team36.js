/*
	Content ：the part of search funtion view
	Arthur ：    Captain of TEAM36
	Date ：          18/12/18
*/
var row2Height = 0;
var row3Height = 0;
var blur_state = 'blur';
var input_val_state = 'input';

/**
 * 首次点击input  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 方法待调整
 */
function inp_click(state) {
	input_val_state = 'input';
	if(state != 1) {
		$('.d1').attr("onmouseover", "");
		$('.searchView').attr("onmouseleave", "");
		//alert('state!=1')

	}

	//禁止重复请求
	if(!$('.inp').hasClass('focus')) {
		//alert('我没有focus')
		if($('.inp').val() == null || $('.inp').val() == '') {
			$(".d2").height('auto');

			d_sel1();
			//d_sel2();
			d_his();
			d_btm();

			$('.row1').css('border-bottom', '0.1px solid gainsboro');
			$(".d2").css('opacity', '0');
			$(".d2").animate({
				opacity: '1',
				display: 'flex'
			}, "100");
		}
		$('.inp').addClass('focus')
	}
};

/**
 * input 时去焦点 
 */
function inp_blur() {
	if(blur_state == 'blur') {
		//alert('blur')
		$('.inp').removeClass('focus');
		$(".d2").animate({
			opacity: '0'
		}, "100");

		// 只在input有值 且失去了焦点后 给fun view [d1] 添加 mouseover、 mouseout 事件， 当 input再次触发 click事件时 除去fun view [d1] 的mouseover、 mouseout 事件.
		if($('.inp').val() != null && $('.inp').val() != '') {
			$('.d1').attr("onmouseover", "view_mouseover()");
			$('.searchView').attr("onmouseleave", "view_mouseleave()");
		}
	} else {
		//alert('aa');
		blur_state = 'blur';
		if($('.inp').val() != null && $('.inp').val() != '') {
			//$('.d1').attr("onmouseover","view_mouseover()");
			//$('.searchView').attr("onmouseleave","view_mouseleave()");
		}
	}
}

/**
 * input mouseover 时回显 fun view[d2]
 */
function view_mouseover() {
	$(".d2").animate({
		opacity: '1'
	}, "100");
}

/**
 *  fun view mouseleave 时隐藏fun view[d2]
 */
function view_mouseleave() {
	$(".d2").animate({
		opacity: '0'
	}, "100");
}

/**
 * 加载后事件
 */
function view_close() {
	$('.row1div').remove();
	$('.row2div').remove();
	$('.row4div').remove();
	$('.row3ul').remove();
	//alert('mousedown')
	$('.inp').val('');
	$('.inp').removeClass('focus');
	$('.closeic').css('display', 'none');
	//$(".d2").height('auto');
	$(".d2").animate({
		height: 'auto'
	}, "100");
	//inp_click(1);
	input();
}

/**
 * 默认检索提示分类一
 */
function d_sel1() {
	$('.row1div').remove();
	// 设置默认显示检索类数据 d_data1
	var d_data1 = [{
		"text": "区域",
		"img": "img/area.png"
	}, {
		"text": "人员",
		"img": "img/staff.png"
	}, {
		"text": "全部",
		"img": "img/all.png"
	}];

	var html = '<div class="row1div">'; //外层div
	for(var cap in d_data1) {
		html += '<div class="d_row1div">'; //子div 项
		html += '<img class="d_row1ic" src="/static/fengmap/' + d_data1[cap].img + '"/>'; //默认一类图标
		html += '<span class="d_row1span">' + d_data1[cap].text + '</span>'; //默认一类内容
		html += '</div>';
	}
	html += '</div>';
	$('.row1').html(html);
}

/**
 * 默认检索提示分类二
 */
function d_sel2() {
	$('.row2div').remove();
	// 设置默认显示检索类数据 d_data1
	var d_data1 = [{
		"text": "cap",
		"img": "img/ic1.png"
	}, {
		"text": "John",
		"img": "img/ic1.png"
	}, {
		"text": "hinhin",
		"img": "img/ic1.png"
	}];

	var html = '<div class="row2div">'; //外层div
	for(var cap in d_data1) {
		html += '<div class="d_row2div">'; //子div 项
		html += '<img class="d_row2ic" src="/static/fengmap/' + d_data1[cap].img + '"/>'; //默认一类图标
		html += '<span class="d_row2span">' + d_data1[cap].text + '</span>'; //默认一类内容
		html += '</div>';
	}
	html += '</div>';
	$('.row2').html(html);
	$('.row2div').css('border-top', '0.1px solid gainsboro');
}

/**
 * 默认历史记录
 */
function d_his() {
	$('.row3ul').remove();
	// 从缓存中获得 历史记录
	var data = ['大堂', '所有围栏', '质检员', '第二车间', '化工车间3', '老王', '第二消毒室', '摄像头', '摄像头3', '办公室'];

	// 遍历data 组装li
	if(data.length > 0) {
		var html = '<ul class="row3ul">';
		for(var cap in data) {
			html += '<li class="row3li" onmousedown="itemSearch(this)">';
			html += '<div class="liDiv">'; //外层div
			html += '<div class="liDiv1">'; //内层左div
			html += '<img class="liIc" src="/static/fengmap/img/histroy.png"/>'; //图标
			html += '<span class="liSpan">' + data[cap] + '</span>'; //内容1
			html += '</div>';
			// html += '<div class="liDiv2">' + data[cap] + '</div>'; //右侧内容2
            html += '<div class="liDiv2"></div>'; //右侧内容2
			html += '</div></li>';
		}
		html += '</ul>';
		$('.row3').html(html);

		// 计算row1 此时高度
		if(data.length > 8) {
			$('.row3ul').height('200');
		}
	};
}

/**
 * 底部小功能栏
 */
function d_btm() {
	$('.row4div').remove();
	// 遍历data 组装li
	var html = '<div class="row4div">'; //外层div
	// html += '<div class="" style="width: 60%;display: flex;">这是<span class="span">一嘎达</span>测试文字</div>';
    html += '<div class="" style="width: 65%;display: flex;"><span class="span"></span></div>';
	html += '<div class="" style="width: 20%;">';
	//html += '<img class="ic" src="/static/fengmap/img/search2.png"/>';
	html += '</div>';
	html += '<div class="" style="width: 15%;color: lightgrey">删除历史</div>';
	html += '</div>';

	$('.row4').html(html);
}

/**
 * funtion input 输入值后 检索
 */
function input() {
	if(input_val_state == 'input') {
		//alert('input')
		$('.row1div').remove();
		$('.row2div').remove();
		$('.row4div').remove();
		$('.row3ul').remove();
		$('.inp').removeClass('focus');

		// input 有值时：
		if($('.inp').val() != null && $('.inp').val() != '') {

			$('.closeic').css('display', 'block');

			// 调用检索列表项 生成检索结果提示row3区内容
			sel3();
			$(".d2").css('opacity', '0');
			$(".d2").animate({
				opacity: '1',
				display: 'flex'
			}, "100");
		}
	}
}

/**
 * input值时 检索结果提示列表项
 */
function sel3() {
	$('.row3ul').remove();
	// 生成row3 检索结果提示列表项ul：ajax 获得 data
	var key = $('.inp').val();
	// ...
	var data = ['a', 'ab', 'abc', 'abcd', 'abcde', 'abcdef', 'abcdef', 'abcdef', 'abcdef', 'abcdef', 'abcdef', 'abcdef', 'abcdef', 'abcdef', 'abcdef', 'abcdef', 'abcdef', 'abcdef'];

	// 遍历data 组装li
	if(data.length > 0) {
		var html = '<ul class="row3ul">';
		for(var cap in data) {
			html += '<li class="row3li" onmousedown="itemSearch(this)">';
			html += '<div class="liDiv">'; //外层div
			html += '<div class="liDiv1">'; //内层左div
			html += '<img class="liIc" src="/static/fengmap/img/search2.png"/>'; //图标
			html += '<span class="liSpan">' + data[cap] + '</span>'; //内容1
			html += '</div>';
			html += '<div class="liDiv2">' + data[cap] + '</div>'; //右侧内容2
			html += '</div></li>';
		}
		html += '</ul>';
		$('.row3').html(html);

		// 计算row1 此时高度
		if(data.length > 12) {
			$('.row3ul').height('400');
		}
		row3Height = $('.row3ul').height();
		//alert(row3Height+"row3")
		return row3Height;
	};
};

/**
 *  Search Icon
 */
function search36() {
	var key = $('.inp').val();
	doSearch(key);
	//alert(aa)

	$(".inp").focus();
};

/**
 *  Search Ii Item
 */
function itemSearch(e) {
	var key = $(e).children().children('.liDiv1').children('.liSpan').text();
	//alert(key)
	doSearch(key);
};

/**
 *  the Main Function Body of Search
 */
function doSearch(key) {
	blur_state = 'select';
	input_val_state = 'select';

	$('.inp').val(key);
	$('.closeic').css('display', 'block');
	$(".d2").css('opacity', '0');
	$('.row1div').remove();
	$('.row2div').remove();
	$('.row3ul').remove();
	$('.row4div').remove();
	$(".d2").animate({
		opacity: '1'
	}, "100");
	//var row2divWidth =	$('.d1').width();

	// 生成row3 检索结果提示列表项ul：ajax 获得 data
	// ...
	var result = [{
		"user": "Junn",
		"resultName": "测试数据测试数据测试数据测试数据测试数据",
		"resultLab1": "lab1",
		"resultLab2": "lab2",
		"state": "测试"
	}, {
		"user": "Junn",
		"resultName": "测试数据",
		"resultLab1": "lab1",
		"resultLab2": "lab2",
		"state": "测试"
	}, {
		"user": "Junn",
		"resultName": "测试数据",
		"resultLab1": "lab1",
		"resultLab2": "lab2",
		"state": "测试"
	}, {
		"user": "Junn",
		"resultName": "测试数据",
		"resultLab1": "lab1",
		"resultLab2": "lab2",
		"state": "测试"
	}, {
		"user": "Junn",
		"resultName": "测试数据",
		"resultLab1": "lab1",
		"resultLab2": "lab2",
		"state": "测试"
	}, {
		"user": "Junn",
		"resultName": "测试数据",
		"resultLab1": "lab1",
		"resultLab2": "lab2",
		"state": "测试"
	}, {
		"user": "Junn",
		"resultName": "测试数据",
		"resultLab1": "lab1",
		"resultLab2": "lab2",
		"state": "测试"
	}, {
		"user": "Junn",
		"resultName": "测试数据",
		"resultLab1": "lab1",
		"resultLab2": "lab2",
		"state": "测试"
	}];

	// 遍历data 组装li
	if(result.length > 0) {
		var html = '<div class="row2div">';
		// html += '<div class="titleDiv">';
        html += '<div class="titleDiv" style="background: white;height: 0px;padding: 0">';
		// html += '<div class="titleDivL">';
		// html += '登录即可一键发送地点到手机';
		// html += '</div>';
		// html += '<div class="titleDivR">';
		// html += 'Junn';
		// html += '</div>';
		 html += '</div>';

		html += '<div class="resultItems">';
		for(var cap in result) {
			html += '<div class="resultItem">';
			html += '<div class="resultInfo">';
			html += '<div class="resultRow">';
			html += '<div class="resultName line">';
			html += result[cap].resultName;
			html += '</div>';
			html += '<div class="resultLab1">';
			html += result[cap].resultLab1;
			html += '</div>';
			html += '</div>';
			html += '<div class="resultLab2">';
			html += result[cap].resultLab2;
			html += '</div>';
			html += '</div>';

			html += '<div class="resultRight">';
			html += '<div class="resultState">';
			html += result[cap].state;
			html += '</div>';

			html += '</div>';
			html += '</div>';
		}
		html += '</div>';
		html += '</div>'

		$('.row2').html(html);

		// 计算row1 此时高度
		if(result.length > 5) {
			$('.row2div').height('400px');
		} else {
			$('.row2div').height('auto');
		}
	};
	//$('.row2div').css('width',row2divWidth);		
	setTimeout(function() {
		$(".inp").focus()
	}, 200);
};

/*
	Content ：the part of funtion Bar view
	Arthur ：    Captain of TEAM36
	Date ：          18/12/19
*/
/**
 * 	function bar
 */
$(function() {
	$('.funcDiv0').click(function() {
		var index = $(this).index();
		if(index > 0 && index < 5) {
			$(this).children().children('.funcIc2').removeClass("hid").siblings('.funcIc').addClass('hid').siblings('.funcName').addClass('color').parent().parent().siblings().children().children('.funcIc2').addClass('hid').siblings('.funcIc').removeClass('hid').siblings('.funcName').removeClass('color');
		} else {
			$(this).siblings().children().children('.funcIc2').addClass('hid').siblings('.funcIc').removeClass('hid').siblings('.funcName').removeClass('color');
		}
	})
})

//警报展示列表
		$('.warning').click(function(){
			var html = '<ul class="warningUl"';

		})
function view_warning() {
	$('.warningUl').remove();
	$('.upIc').addClass('downIc').removeClass('upIc');

	var warning_data = [{
		"date": "19/8/17 09:12",
		"area": "静止超时",
		"guy": "访客：王华"
	}, {
		"date": "19/8/17 09:22",
		"area": "离岗",
		"guy": "环安部：周维"
	}, {
		"date": "19/8/17 09:32",
		"area": "串岗",
		"guy": "信息部：杨现"
	}, {
		"date": "19/8/17 09:42",
		"area": "离岗",
		"guy": "信息部：李海"
	}, {
		"date": "19/8/17 09:42",
		"area": "离岗",
		"guy": "信息部：李海"
	}];

	var html = '<div class="warningIc"></div><ul class="warningUl">'
	for(var cap in warning_data) {
		html += '<li class="warningLi">';
		html += '<div class="warningLiDiv">';
		html += '<img class="warningLiImg" src="/static/fengmap/img/warning.png"/>';
		html += '<span class="warningLiDate line">' + warning_data[cap].date + '</span>';
		html += '<span class="warningLiArea line">' + warning_data[cap].area + '</span>';
		html += '<span class="warningLiGuy line">' + warning_data[cap].guy + '</span>';
		html += '</div>';
		html += '</li>';
	}
	html += '</ul>';

	$('.warning_list').html(html).css('opacity', 0);
	$('.warning_list').animate({
		opacity: '1'
	}, "100");
	setTimeout(function() {
		$(".warning_list").show()
	}, 100);

}

// 警报div 失去焦点
function divBlur(e) {
	$(e).children('.funcDiv').children('.funcIc').removeClass('hid').siblings('.funcIc2').addClass('hid').siblings('.funcName').removeClass('color');
	$('.downIc').removeClass('downIc').addClass('upIc');
	$('.warning_list').animate({
		opacity: '0'
	}, "100");
	setTimeout(function() {
		$(".warning_list").hide()
	}, 200);
};

// 点击其他功能按钮 改function bar div 失去焦点 
function funcDivBlur(e) {
	$(e).children().children().children('.funcIc').removeClass("hid").siblings('.funcIc2').addClass('hid').siblings('.funcName').removeClass('color');
};

/*
	Content ：the part of pluralistic funtion Bar view 多元功能栏
	Arthur ：  Captain of TEAM36
	Date ：      18/12/24
*/
$(function() {

	$('.plurIc').click(function() {
		$('.funcBar').removeClass('overhid');
		$('.plurBar').removeClass('overhid');

		if($(this).hasClass('rorate')) {
			$(this).removeClass('rorate').addClass('trans rorateBack');

			// 隐藏 func bar
			$('.funcBar').animate({
				opacity: 0,
				width: '0'
			}, '100', function() {
				$('.funcBar').css('display', 'none')
			});

			// 隐藏 plur func bar
			$('.plurBar').animate({
				opacity: 0,
				height: 0
			}, '100');

		} else {
			$(this).removeClass('rorateBack').addClass('trans rorate');

			// 显示 func bar
			$('.funcBar').css('display', 'flex')
			$('.funcBar').animate({
				width: '494px',
				opacity: 1
			}, '100');

			// 下拉 plurBar
			$('.plurBar').animate({
				height: '200px',
				opacity: 1
			}, '100');

		}

	});

	/**
			 *  生成 item menu list
			 
			// 	多级菜单 json
			//	var menu = [{ "text":"a","child": [ {  "text":"aa","child": [ { "text":"aaa","child": "" } ] } ]},{ "text":"b","child": [ {  "text":"bb","child": [ { "text":"bbb","child":[ {  "text":"bbbb","child": "" },{  "text":"bbbb2","child": "" } ] } ] } ]}];
			//	alert(menu[1].child[0].child[0].child[1].text);
			var menu = [{ "text":"a","child": [ {  "text":"aa","child": "" },{  "text":"aa","child": "" },{  "text":"aa","child": "" } ] },
						{ "text":"a","child": [ {  "text":"aa","child": "" },{  "text":"aa","child": "" },{  "text":"aa","child": "" } ] },
						{ "text":"a","child": [ {  "text":"aa","child": "" },{  "text":"aa","child": "" },{  "text":"aa","child": "" } ] },
						{ "text":"a","child": [ {  "text":"aa","child": "" },{  "text":"aa","child": "" },{  "text":"aa","child": "" } ] },
						{ "text":"b","child": [ {  "text":"bb","child": [ { "text":"bbb0","child":[ {  "text":"bbbb","child": "" },{  "text":"bbbb2","child": "" } ] } ] },{  "text":"bb","child": [ { "text":"bbb1","child": "" },{ "text":"bbb2","child": "" } ] },{ "text":"bb","child": "" },{ "text":"bb","child": "" } ]} ];
			
			//alert(menu[0].child[0].child.length);
			for(var cap = 0; cap < menu.length; cap++) {
				// 创建 itemMenuView
				if(menu[cap].child.length > 0){
					var html = '<div class="itemMenuView"><ul class="itemMenuUl">';
					for (var i = 0; i < menu[cap].child.length; i++) {
						html += '<li class="itemMenuLi">';
						
						// 生成三级菜单列表
						if(menu[cap].child[i].child.length > 0){
							var html2 = '<div class="itemMenuView"><ul class="itemMenuUl">';
							for (var l = 0; l < menu[cap].child[i].child.length; l++) {
								html2 += '<li class="itemMenuLi">';
								
//								// 生成三级菜单列表
//								if(menu[cap].child[i].child.length > 0){
//								
//								}
								
								html2 += menu[cap].child[i].child[l].text;
								html2 += '</li>';
							}	
							html2 += '</ul></div>';
							

						}
						
						html += menu[cap].child[i].text;
						html += '</li>';
					}	
					html += '</ul></div>';
					
					var n = cap + 1;
					// 把生成的itemMenuView 加入相应的plurItem 中
					$('.plurItem'+n).prepend(html);
				}
				
			}*/

	var plurMenu_data = [
		['综合楼一', '综合楼二', '生产辅助楼'],
		['循环消防水站', '冷冻站', '废液收集池', '中控室'],
		['事故水收集池', '车间六', '车间五'],
		['仓库', '仓库二'],
		['污水站辅助用房']
	];

	//遍历每个菜单区
	for(var cap = 0; cap < plurMenu_data.length; cap++) {
		// 创建 itemMenuView
		if(plurMenu_data[cap].length > 0) {
			var html = '<div class="itemMenuView"><ul class="itemMenuUl">';
			for(var i = 0; i < plurMenu_data[cap].length; i++) {
				html += '<li class="itemMenuLi">';
				html += plurMenu_data[cap][i];
				html += '</li>';
			}
			html += '</ul></div>';

			var n = cap + 1;
			// 把生成的itemMenuView 加入相应的plurItem 中
			$('.plurItem' + n).prepend(html);
		}

	}

	// 鼠标移上 展示子菜单 
	$('.plurItem').hover(function() {
		$(this).siblings().children('div').css('display', 'none');
		$(this).children('div').css('display', 'flex');
	})

	// 鼠标移出  隐藏子菜单 
	$('.plurItem').mouseleave(function() {
		$(this).children('div').css('display', 'none');
	});

	// 点击子菜单 隐藏
	$('.itemMenuLi').click(function() {
		$(this).parent().parent().css('display', 'none');
	});

})


/**
    加载完成 显示警报通知卡
    captain		19/1/2

var timer;

function warningCard(){
    // ajax获取数据warning_data 生成警报通知卡
    var warning_data = [0,1,2,3,4];

    if(warning_data.length > 0){
        var html = '<div class="warningView" style="opacity:0" onmouseover="moveToCard()" onmouseout="moveoutCard()">';
        for(var cap in warning_data) {
            html += '<div class="warningCard bgb">';
            html += '<div class="warningCardIc"></div>';
            html += '<div class="warningDiv">';
            html += '<div class="warningInfo">';
            html += '<div class="warningType">围栏警报</div>';
            html += '<div class="warningContent line">您有'+warning_data[cap]+'条警报信息未处理</div></div>';
            html += '<div class="warningClose"></div>';
            html += '</div></div>';
        }
        html += '</div>';
        $('.bd').append(html);

        $('.warningView').animate({
            opacity: '1'
        }, "100");

        // 五秒后隐藏
        timer = setTimeout(hideCard,5000);
    }
}


//    五秒后隐藏

function hideCard(){
    $('.warningView').animate({opacity: '0'}, "100",function(){$('.warningView').css('display','none')});
}


 //   鼠标移入警报卡 结束隐藏事件

function moveToCard() {
    clearTimeout(timer);
    //alert(0)
}


 //   鼠标移出警报卡 开启隐藏事件

function moveoutCard() {
    // 五秒后隐藏
    timer = setTimeout(hideCard,3000);
}

//警报
$(window).load(function(){
    setTimeout(warningCard,3000);
}); */