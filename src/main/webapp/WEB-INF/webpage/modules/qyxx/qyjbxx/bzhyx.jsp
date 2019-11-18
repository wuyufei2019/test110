<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title></title>
<meta name="decorator" content="default" />
<link rel="stylesheet" href="${ctxStatic}/awesome/4.4/css/font-awesome.min.css"/>
<script type="text/javascript">
    var ctx='${ctx}';
    var  qyid='${qyid}';
</script>
<style>
*,
*::before,
*::after {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

body {
  font: normal 16px/1.5 'Titillium Web', sans-serif;
  background: linear-gradient(54deg, #9164ff, #8bfff4);
  color: #3c3f64;
  overflow-x: hidden;
  padding-bottom: 50px;
}

.timeline ul li {
  list-style-type: none;
  position: relative;
  width: 6px;
  margin: 0 auto;
  padding-top: 0px;
  background: #fff;
}
.timeline ul li::after {
  content: '';
  position: absolute;
  left: 50%;
  bottom: 50%;
  transform: translateX(-50%) rotate(45deg);
  width: 20px;
  height: 20px;
  z-index: 2;
  background: #fff;
}
.timeline ul li div {
  position: relative;
  bottom: 0;
  width: 190px;
  padding: 6px 6px;
  background: #fff;
  box-shadow: 4px 13px 30px 1px rgba(252, 56, 56, 0.2);
  border-radius: 5px;
  display: flex;
  align-items: center;
}
.timeline ul li div time {
  position: absolute;
  background: #f5af19;
  width: 50px;
  height: 24px;
  top: -15px;
  border-radius: 5px;
  display: flex;
  justify-content: center;
  align-items: center;
  letter-spacing: 2px;
}
.timeline ul li div time i{
  font-size: 18px;
}
.timeline ul li div div {
  height: 40px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
.timeline ul li div div h1 {
    font-size: 16px;
        margin-top: 10px;
}
.timeline ul li div div p {
  text-align: center;
	display: none
}
.timeline ul li div div span {
    font-size: 14px;
}
.timeline ul li div .discovery {
  margin-right: 10px;
}
.timeline ul li:nth-of-type(odd) > div {
  left: 45px;
}
.timeline ul li:nth-of-type(even) > div {
  left: -230px;
}

.timeline ul li div {
  visibility: hidden;
  opacity: 0;
  /*transition: all 0.5s ease-in-out;*/
}
.timeline ul li:nth-of-type(odd) div{
  /*transform: translate3d(100px, -10px, 0) rotate(10deg);*/
}
.timeline ul li:nth-of-type(even) div {
  /*transform: translate3d(-100px, -10px, 0) rotate(10deg);*/
}
.timeline ul li:nth-of-type(even) div time{
  position: absolute;
  background: #f5af19;
  width: 50px;
  height: 24px;
  top: -15px;
  right:16px;
  border-radius: 5px;
  display: flex;
  justify-content: center;
  align-items: center;
  letter-spacing: 2px;
}
.timeline ul li.in-view div {
  transform: none;
  visibility: visible;
  opacity: 1;
}

@media screen and (max-width: 900px) {
  .timeline ul li div {
    width: 250px;
    flex-direction: column;
  }
  .timeline ul li div div {
    width: 80%;
    margin: 10px;
  }
  .timeline ul li:nth-of-type(even) > div {
    left: -269px;
  }
}
@media screen and (max-width: 600px) {
  body {
    background: #8bfff4;
  }

  .timeline ul li {
    margin-left: 20px;
  }
  .timeline ul li div {
    width: calc(100vw - 91px);
  }
  .timeline ul li:nth-of-type(even) > div {
    left: 45px;
  }
}
.discovery:hover,.scientist:hover{
cursor: pointer;}
.com_{display: flex;
    justify-content: center;
    margin: 20px 0 20px;}
.comName{

    padding: 14px 40px;
    font-size: 24px;
    font-weight: 600;
    color: #907cfd;
    width: auto;
    text-align: center;
    background: #fff;
    box-shadow: 4px 13px 30px 1px rgba(252, 56, 56, 0.2);
    border-radius: 5px;
    text-shadow: 4px 4px 4px #cac5c4;
        border-bottom: #949494 2px solid;
    }
    .timeline ul li div div.scientist{display:none;}
    .timeline ul li div div.discovery{margin-right:0px;width: 190px;}
    .timeline ul li div div h1 span{    color: #ff7800;
    font-weight: 600;
    font-size: 18px;}
</style>
</head>
<body>
<section class="timeline">
    <div class="com_">
	    <div class="comName">
	         企业安全信息总览
		</div>
	</div>
    <ul>
        <li>
            <div>
                <time><i class="fa fa-building"></i></time>
                <div class="discovery" onclick="yqyd('${qyid}')">
                    <h1>一企一档&nbsp;&nbsp;&nbsp;<span>(${wcd}%)</span></h1>
                    <p>
                        Laws of motion
                    </p>
                </div>
                <div class="scientist" >
                    <h1></h1>
                    <span>Newton</span>
                </div>
            </div>
        </li>
        <li>
            <div>
                <time><i class="fa fa-map-pin"></i></time>
                <div class="discovery" onclick="fxdxx('${qyid}')">
                    <h1>风险点信息<span>(${mksl.fxdCount})</span></h1>
                    <p>
                        Law of electrostatic attraction
                    </p>
                </div>
                <div class="scientist">
                    <h1></h1>
                    <span>Coulomb</span>
                </div>
            </div>
        </li>
        <li>
            <div>
                <time><i class="fa fa-exclamation-triangle"></i></time>
                <div class="discovery" onclick="zdwxy('${qyid}')">
                    <h1>重大危险源<span>(${mksl.zdwxylCount})</span></h1>
                    <p>
                        Law of Electric Resistance
                    </p>
                </div>
                <div class="scientist">
                    <h1>('${mksl.fxdCount}')</h1>
                    <span>G.S Ohm</span>
                </div>
            </div>
        </li>
        <li>
            <div>
                <time><i class="fa fa-search-minus"></i></time>
                <div class="discovery" onclick="yhpc('${qyid}')">
                    <h1>隐患排查<span>(${mksl.yhpcCount})</span></h1>
                    <p>
                        Electromagnetic Induction
                    </p>
                </div>
                <div class="scientist">
                    <h1>15%</h1>
                    <span>Michael Faraday</span>
                </div>
            </div>
        </li>
        <li>
            <div>
                <time><i class="fa fa-book"></i></time>
                <div class="discovery">
                    <h1>安全培训</h1>
                    <p>
                        Dynamite
                    </p>
                </div>
                <div class="scientist">
                    <h1>254</h1>
                    <span>Alfred Nobel</span>
                </div>
            </div>
        </li>
        <li>
            <div>
                <time><i class="fa fa-calendar-times-o"></i></time>
                <div class="discovery">
                    <h1>危险作业</h1>
                    <p>
                        X Rays
                    </p>
                </div>
                <div class="scientist">
                    <h1>125</h1>
                    <span>Roentgen</span>
                </div>
            </div>
        </li>
        <li>
            <div>
                <time><i class="fa fa-slack"></i></time>
                <div class="discovery">
                    <h1>标准化运行</h1>
                    <p>
                        Electron
                    </p>
                </div>
                <div class="scientist">
                    <h1>854</h1>
                    <span>J.J.Thomson</span>
                </div>
            </div>
        </li>
        <li>
            <div>
                <time><i class="fa fa-binoculars"></i></time>
                <div class="discovery">
                    <h1>监督排查</h1>
                    <p>
                        Radium
                    </p>
                </div>
                <div class="scientist">
                    <h1>54%</h1>
                    <span>Madam Curie</span>
                </div>
            </div>
        </li>
        <li>
            <div>
                <time><i class="fa fa-exclamation-triangle"></i></time>
                <div class="discovery">
                    <h1>涉爆粉尘</h1>
                    <p>
                        Radium
                    </p>
                </div>
                <div class="scientist">
                    <h1>54%</h1>
                    <span>Madam Curie</span>
                </div>
            </div>
        </li>
        <li>
            <div>
                <time><i class="fa fa-book"></i></time>
                <div class="discovery">
                    <h1>受限空间危险化学品</h1>
                    <p>
                        Radium
                    </p>
                </div>
                <div class="scientist">
                    <h1>54%</h1>
                    <span>Madam Curie</span>
                </div>
            </div>
        </li>
        <li>
            <div>
                <time><i class="fa fa-search-minus"></i></time>
                <div class="discovery">
                    <h1>高温熔融金属</h1>
                    <p>
                        Radium
                    </p>
                </div>
                <div class="scientist">
                    <h1>54%</h1>
                    <span>Madam Curie</span>
                </div>
            </div>
        </li>
        <li>
            <div>
                <time><i class="fa fa-map-pin"></i></time>
                <div class="discovery">
                    <h1>主体责任</h1>
                    <p>
                        Radium
                    </p>
                </div>
                <div class="scientist">
                    <h1>54%</h1>
                    <span>Madam Curie</span>
                </div>
            </div>
        </li>
    </ul>
</section>

<script>
var items = document.querySelectorAll(".timeline li");

function isElementInViewport(el) {
  var rect = el.getBoundingClientRect();
  return (
    rect.top >= 0 &&
    rect.left >= 0 &&
    rect.bottom <= (window.innerHeight || document.documentElement.clientHeight) &&
    rect.right <= (window.innerWidth || document.documentElement.clientWidth)
  );
}

//一企一档
function callbackFunc() { console.log(1)
    for (var i = 0; i < items.length; i++) {
        items[i].classList.add("in-view");
    }
  for (var i = 0; i < items.length; i++) {
    if (isElementInViewport(items[i])) {
      if(!items[i].classList.contains("in-view")){
        items[i].classList.add("in-view");
      }
    } else if(items[i].classList.contains("in-view")) {
        //items[i].classList.remove("in-view");
    }
  }
}

//一企一档
function yqyd(id){
    layer.open({
        type: 2,
        area: ['90%', '90%'],
        title: '查看一企一档信息',
        maxmin: true,
        content: ctx+"/bis/qyjbxx/view/"+id ,
        btn: ['关闭'],
        cancel: function(index){
        }
    });
}

//风险点信息
function fxdxx(id){
    layer.open({
        type: 2,
        area: ['90%', '90%'],
        title: '查看风险点信息信息',
        maxmin: true,
        content: ctx+"/fxgk/fxxx/index/"+id ,
        btn: ['关闭'],
        cancel: function(index){
        }
    });
}

//重大危险源
function zdwxy(id){
    layer.open({
        type: 2,
        area: ['90%', '90%'],
        title: '查看重大危险源信息',
        maxmin: true,
        content: ctx+"/bis/hazard/tabindex/"+id ,
        btn: ['关闭'],
        cancel: function(index){
        }
    });
}

//隐患排查
function yhpc(id){
    layer.open({
        type: 2,
        area: ['90%', '90%'],
        title: '查看隐患排查信息',
        maxmin: true,
        content: ctx+"/yhpc/yhpcjl/index?qyid="+id ,
        btn: ['关闭'],
        cancel: function(index){
        }
    });
}
window.addEventListener("load", callbackFunc);
window.addEventListener("scroll", callbackFunc);
</script>
</body>
</html>