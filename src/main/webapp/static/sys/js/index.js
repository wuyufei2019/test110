var $,tab,dataStr,layer,firstMenu;
var menuData = [];
layui.config({
	base : ctx+"/static/sys/js/"
}).extend({
	"bodyTab" : "bodyTab"
})

// 将use方法放入到方法体中，防止方法执行顺序出错
function useLayui(){
	layui.use(['bodyTab','form','element','layer','jquery'],function(){
		var form = layui.form,
			element = layui.element;
			$ = layui.$;
	    	layer = parent.layer === undefined ? layui.layer : top.layer;
			tab = layui.bodyTab({
				openTabNum : "100",  //最大可打开窗口数量
				url : "static/sys/json/navs.json" //获取菜单json地址
			});
	
		//通过顶部菜单获取左侧二三级菜单   注：此处只做演示之用，实际开发中通过接口传参的方式获取导航数据
		function getData(json){
			for(var m=0;m<menuData.length;m++){
				if(json==menuData[m].id && m==0){
					dataStr = menuData[m].children;
					//重新渲染左侧菜单
					tab.render(0);
				}else if(json==menuData[m].id && m!=0){
					dataStr = menuData[m].children;
					//重新渲染左侧菜单
					tab.render();
				}
			}
		}
		
		//页面加载时判断左侧菜单是否显示
		//通过顶部菜单获取左侧菜单
		$(".topLevelMenus li,.mobileTopLevelMenus dd").click(function(){
			if($(this).parents(".mobileTopLevelMenus").length != "0"){
				$(".topLevelMenus li").eq($(this).index()).addClass("layui-this").siblings().removeClass("layui-this");
			}else{
				$(".mobileTopLevelMenus dd").eq($(this).index()).addClass("layui-this").siblings().removeClass("layui-this");
			}
			$(".layui-layout-admin").removeClass("showMenu");
			$("body").addClass("site-mobile");
			getData($(this).data("menu"));
			//渲染顶部窗口
			tab.tabMove();
		})
	
		//隐藏左侧导航
		$(".hideMenu").click(function(){
			if($(".topLevelMenus li.layui-this a").data("url")){
				layer.msg("此栏目状态下左侧菜单不可展开");  //主要为了避免左侧显示的内容与顶部菜单不匹配
				return false;
			}
			$(".layui-layout-admin").toggleClass("showMenu");
			//渲染顶部窗口
			tab.tabMove();
		})
	
		//通过顶部菜单获取左侧二三级菜单   注：此处只做演示之用，实际开发中通过接口传参的方式获取导航数据
		getData(firstMenu);
	
		//手机设备的简单适配
	    $('.site-tree-mobile').on('click', function(){
			$('body').addClass('site-mobile');
		});
	    $('.site-mobile-shade').on('click', function(){
			$('body').removeClass('site-mobile');
		});
	
		// 添加新窗口
		$("body").on("click",".layui-nav .layui-nav-item a:not('.mobileTopLevelMenus .layui-nav-item a')",function(){
			$(this).parent().siblings().removeClass("layui-nav-itemed").removeClass("layui-this").find(".layui-nav-item").removeClass("layui-nav-itemed").removeClass("layui-this");
			//if(){
				// 将二级菜单打开图标合上
				$("ul.layui-nav.layui-nav-tree").find(".fa-folder-open").removeClass("fa-folder-open").addClass("fa-folder");
			//}
			//如果不存在子级
			if($(this).siblings().length == 0){
				addTab($(this));
				$('body').removeClass('site-mobile');  //移动端点击菜单关闭菜单层

				if($(this).parent().is("cap")){
					$(this).parent("cap").addClass("layui-this").parent("caps").parent("dd").removeClass("layui-this");
					if($(this).parents("caps").siblings("a").children("i").hasClass("fa-folder")){
						$(this).parents("caps").siblings("a").children("i").removeClass("fa-folder").addClass("fa-folder-open");
					}
				}
			}else{
				if($(this).parent().is("dd")){
					$(this).parent("dd").removeClass("layui-this");
					if($(this).children("i").hasClass("fa-folder")){
						$(this).children("i").removeClass("fa-folder").addClass("fa-folder-open");
					}
				}
			}

			// 根据layui-nav-more 来确定二级菜单图标开合
			if($(".layui-nav-itemed .layui-nav-child .layui-nav-more").siblings("i").hasClass("fa-folder-open")){
				$(".layui-nav-itemed .layui-nav-child .layui-nav-more").siblings("i").removeClass("fa-folder-open").addClass("fa-folder");
			}
			if($(".layui-nav-itemed .layui-nav-child .layui-nav-itemed .layui-nav-more").siblings("i").hasClass("fa-folder")){
				$(".layui-nav-itemed .layui-nav-child .layui-nav-itemed .layui-nav-more").siblings("i").removeClass("fa-folder").addClass("fa-folder-open");
			}
		})
	
		//清除缓存
		$(".clearCache").click(function(){
			window.sessionStorage.clear();
	        window.localStorage.clear();
	        var index = layer.msg('清除缓存中，请稍候',{icon: 16,time:false,shade:0.8});
	        setTimeout(function(){
	            layer.close(index);
	            layer.msg("缓存清除成功！");
	        },1000);
	    })
	
		//刷新后还原打开的窗口
	    if(cacheStr == "true") {
	        if (window.sessionStorage.getItem("menu") != null) {
	            menu = JSON.parse(window.sessionStorage.getItem("menu"));
	            curmenu = window.sessionStorage.getItem("curmenu");
	            var openTitle = '';
	            for (var i = 0; i < menu.length; i++) {
	                openTitle = '';
	                if (menu[i].icon) {
	                    if (menu[i].icon.split("-")[0] == 'icon') {
	                        openTitle += '<i class="seraph ' + menu[i].icon + '"></i>';
	                    } else {
	                        openTitle += '<i class="layui-icon">' + menu[i].icon + '</i>';
	                    }
	                }
	                openTitle += '<cite>' + menu[i].title + '</cite>';
	                openTitle += '<i class="layui-icon layui-unselect layui-tab-close" data-id="' + menu[i].layId + '">&#x1006;</i>';
	                element.tabAdd("bodyTab", {
	                    title: openTitle,
	                    content: "<iframe src='" + menu[i].href + "' data-id='" + menu[i].layId + "'></frame>",
	                    id: menu[i].layId
	                })
	                //定位到刷新前的窗口
	                if (curmenu != "undefined") {
	                    if (curmenu == '' || curmenu == "null") {  //定位到后台首页
	                        element.tabChange("bodyTab", '');
	                    } else if (JSON.parse(curmenu).title == menu[i].title) {  //定位到刷新前的页面
	                        element.tabChange("bodyTab", menu[i].layId);
	                    }
	                } else {
	                    element.tabChange("bodyTab", menu[menu.length - 1].layId);
	                }
	            }
	            //渲染顶部窗口
	            tab.tabMove();
	        }
	    }else{
			window.sessionStorage.removeItem("menu");
			window.sessionStorage.removeItem("curmenu");
		}
	})
}

//打开新窗口
function addTab(_this){
	tab.tabAdd(_this);
}

//图片管理弹窗
function showImg(){
    $.getJSON('static/sys/json/images.json', function(json){
        var res = json;
        layer.photos({
            photos: res,
            anim: 5
        });
    });
}


/*
 * 	cap
 *  
 *  菜单部分
 */
$(function(){
	$.ajax({
        type:"POST",
        url:ctx+'/system/permission/hgqy/menujson?pid='+ pid,
        dataType: 'json', 
        success:function(data){
        	// 按角色分菜单后，抽离项内容菜单
        	var data = data[0].children;

        	menuData = data;
        	firstMenu = menuData[0].id;
        	
        	createMenu(menuData);
        	useLayui();
        }
    });
})

// 生成上层一级菜单
function createMenu(menuData){
	var menuHtml = '';
	var mobileMenuHtml = '';
	for(var i=0;i<menuData.length;i++){
		if(i==0){
			menuHtml += '<li class="layui-nav-item layui-this" data-menu="'+menuData[i].id+'">';
			mobileMenuHtml += '<dd class="layui-this" data-menu="'+menuData[i].id+'">';
		}
		else{
			menuHtml += '<li class="layui-nav-item" data-menu="'+menuData[i].id+'">';
			mobileMenuHtml += '<dd data-menu="'+menuData[i].id+'">';
		}
		menuHtml += '<a href="javascript:;"><i class="fa '+menuData[i].icon+'" style="margin-right:5px"></i><cite>'+menuData[i].name+'</cite></a>';
		menuHtml += '</li>';
		mobileMenuHtml += '<a href="javascript:;"><i class="fa '+menuData[i].icon+'" style="margin-right:5px"></i><cite>'+menuData[i].name+'</cite></a>';
		mobileMenuHtml += '</dd>';
	}
	$(".layui-nav.topLevelMenus").html('').html(menuHtml);
	$(".layui-nav.mobileTopLevelMenus dl.layui-nav-child").html('').html(mobileMenuHtml);
}




























