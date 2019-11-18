//电子围栏显示
function getPolygonMarker(polygonMarkerId) {
	//polygonMarkerId?
	
    $.ajax({
        type:'post',
        url:"${ctx}/lydw/dzwl/dzwllist",
        success: function(data){
            //cleanFence();
            var data = $.parseJSON(data);
            for (var i = 0; i < data.length; i++) {
                groupId = data[i].floor;
                var newFenceData = [];
                var ponits = data[i].mappoint.split('||');
                for (var j = 0; j < ponits.length; j++) {
                    var p = ponits[j].split(',');
                    //移动到坐标位置
                    /*if(j == 0) {
                        map.moveTo({
                            x: p[0],
                            y: p[1],
                            time: 1,
                            callback: function() {
                                // console.log('moveTo Complete!');
                            }
                        });
                    }*/

                    var coord = {
                        x: p[0],
                        y: p[1],
                        z: map.getFMGroup(p[2]).groupHeight + map.layerLocalHeight
                    }
                    newFenceData.push(coord);
                }
                //createFence();//生产电子围栏
                addPmMarker(newFenceData,groupId);
            }
        }
    });
    console.log("getPolygonMarker");
}

// 真正想地图中添加电子围栏的方法
function addPmMarker(fenceData,floor) {
    var group = map.getFMGroup(map.groupIDs[floor-1]);

    //返回当前层中第一个polygonMarker,如果没有，则自动创建
    fenceLayer = group.getOrCreateLayer('polygonMarker');

    //createRectangleMaker();
    //layer.addMarker(rectangleMarker);

    //createCircleMaker();
    //layer.addMarker(circleMaker);

    createPolygonMaker(fenceData);
    fenceLayer.addMarker(polygonMarker);
    console.log("addPmMarker");
}

//创建自定义形状标注
function createPolygonMaker(fenceData) {
    polygonMarker = new fengmap.FMPolygonMarker({
        //设置透明度
        alpha: .5,
        //设置边框线的宽度
        lineWidth: 1,
        //设置高度
        height: 2,
        //设置多边形坐标点
        points: fenceData
    });
    console.log("createPolygonMaker");
}

/**
 * Created by CastingJ on 17/5/12.
 * 相对坐标转换为蜂鸟坐标
 */

//来至定位系统的参数 定位的原点坐标 已经定位的范围
var locOrigion = {'x':0,'y':0};//定位坐标原点
var locRange = {'x':389.1,'y':262.2};//定位范围

//根据定位四个角点的地图坐标点
var mapParas = [];
mapParas[0]={'x':13416372.546366975,'y':3685152.520980537};//定位原点地图坐标
mapParas[1]={'x':13416728.416398242,'y':3685236.793833829};//X轴终点地图坐标
mapParas[2]={'x':13416664.786264008,'y':3685435.736886943};//定位原点对角点地图坐标
mapParas[3]={'x':13416323.067946514,'y':3685353.820746671};//Y轴终点地图坐标

function CoordTransformer()
{
    var _locOrigion;
    var _locRange;

    var _mapOrigion;
    var _mapAxisX;
    var _mapAxisY;
    var _mapRange;

    this.getVectorLen = function(vector)
    {
        return Math.sqrt(vector.x*vector.x + vector.y*vector.y);
    }

    this.init = function(locOrigion,locRange,mapParas)
    {
        if(mapParas.length != 4)
            return false;

        _locOrigion = locOrigion;
        _locRange = locRange;

        _mapOrigion = mapParas[0];
        _mapAxisX = {'x':mapParas[1].x - mapParas[0].x ,'y':mapParas[1].y - mapParas[0].y};
        _mapAxisY = {'x':mapParas[3].x - mapParas[0].x ,'y':mapParas[3].y - mapParas[0].y};
        _mapRange = {'x':this.getVectorLen(_mapAxisX),'y':this.getVectorLen(_mapAxisY)};

        //向量单位化
        _mapAxisX.x /= _mapRange.x; _mapAxisX.y /= _mapRange.x;
        _mapAxisY.x /= _mapRange.y; _mapAxisY.y /= _mapRange.y;

    };

    this.transform = function(loc)
    {
        var offstRatio = {'x':(loc.x-_locOrigion.x)/_locRange.x,'y':(loc.y-_locOrigion.y)/_locRange.y};

        var mapOffset = {'x':offstRatio.x*_mapRange.x,'y':offstRatio.y*_mapRange.y};
        var mapCoord = {'x':_mapOrigion.x+_mapAxisX.x*mapOffset.x+_mapAxisY.x*mapOffset.y,'y':_mapOrigion.y+_mapAxisX.y*mapOffset.x+_mapAxisY.y*mapOffset.y};

        return mapCoord;
    };

}
