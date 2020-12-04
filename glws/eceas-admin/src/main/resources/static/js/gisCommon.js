var gisUrl= "http://47.94.105.131:8083/geoserver";
var baseUrlLayer = "/gwc/service/wms";
var urlLayer = "/gis/wms?service=WMS";
var cityUrlData = "/gis/ows?service=WFS&version=1.0.0&request=GetFeature&maxFeatures=50&outputFormat=application/json&typeName=gis:grasslandArea&";
var gisMapName="gis:eceas-gisMap";
var gisTabName="gis:eceas-gisTab";
var mapClick;
function load() {
    var source_vec = new ol.source.XYZ({
        url: "http://t3.tianditu.com/DataServer?T=vec_w&x={x}&y={y}&l={z}&tk=3defde9e465f739e73513ce9e8595fe3",//parent.TiandituKey()为天地图密钥
        wrapX: false
    });
    var TiandiMap_vec = new ol.layer.Tile({
        title: "天地图矢量图层",
        source: source_vec
    });
    var source_cva = new ol.source.XYZ({
        url: "http://t3.tianditu.com/DataServer?T=cva_w&x={x}&y={y}&l={z}&v=4.0&tk=3defde9e465f739e73513ce9e8595fe3",//parent.TiandituKey()为天地图密钥
        wrapX: false
    });
    var TiandiMap_cva = new ol.layer.Tile({
        title: "天地图矢量注记图层",
        source: source_cva

    })
    //实例化鼠标位置控件
    var mousePositionControl = new ol.control.MousePosition({
        //坐标格式
        coordinateFormat: ol.coordinate.createStringXY(4),
        //地图投影坐标系（若未设置则输出为默认投影坐标系下的坐标）
        projection: 'EPSG:4326',
        //坐标信息显示样式，默认是'ol-mouse-position'
        className: 'custom-mouse-position',
        //显示鼠标位置信息的目标容器
        target: document.getElementById('mouse-position'),
        //未定义坐标的标记
        undefinedHTML: '&nbsp;'
    });
    //实例化Map对象加载地图
    map = new ol.Map({
        //地图容器div的ID
        target: 'map',
        //地图容器中加载的图层
        layers: [TiandiMap_vec, TiandiMap_cva],
        //地图视图设置
        view: new ol.View({
            //地图初始中心点
            center: [105, 35],
            projection: "EPSG:4326",
            //地图初始显示级别
            zoom: 5,
            minZoom: 5,
            maxZoom: 18,
        }),
        //加载控件到地图容器中
        controls: ol.control.defaults({}).extend([mousePositionControl]) //加载鼠标位置控件
    });
    map.on('click', function (event) {
        map.forEachFeatureAtPixel(event.pixel, function (feature) {
            // 为移动到的feature发送自定义的mousemove消息
            feature.dispatchEvent({type: 'click', event: event});
        });
    });
}
/**
 * 测量
 * @param type
 */
function measurementGis(type) {
    var off = document.getElementById('off');
    $("#off").val("取消测量");
    $("#off").show();
    //加载测量的绘制矢量层
    var source = new ol.source.Vector(); //图层数据源
    var vector = new ol.layer.Vector({
        source: source,
        style: new ol.style.Style({ //图层样式
            fill: new ol.style.Fill({
                color: 'rgba(255, 255, 255, 0.2)' //填充颜色
            }),
            stroke: new ol.style.Stroke({
                color: '#ffcc33',  //边框颜色
                width: 2   // 边框宽度
            }),
            image: new ol.style.Circle({
                radius: 7,
                fill: new ol.style.Fill({
                    color: '#ffcc33'
                })
            })
        })
    });
    map.addLayer(vector);


    var wgs84Sphere = new ol.Sphere(6378137); //定义一个球对象
    /**
     * 当前绘制的要素（Currently drawn feature.）
     * @type {ol.Feature}
     */
    var sketch;
    /**
     * 帮助提示框对象（The help tooltip element.）
     * @type {Element}
     */
    var helpTooltipElement;
    /**
     *帮助提示框显示的信息（Overlay to show the help messages.）
     * @type {ol.Overlay}
     */
    var helpTooltip;
    /**
     * 测量工具提示框对象（The measure tooltip element. ）
     * @type {Element}
     */
    var measureTooltipElement;
    /**
     *测量工具中显示的测量值（Overlay to show the measurement.）
     * @type {ol.Overlay}
     */
    var measureTooltip;
    /**
     *  当用户正在绘制多边形时的提示信息文本
     * @type {string}
     */
    var continuePolygonMsg = '正在绘制多边形';
    /**
     * 当用户正在绘制线时的提示信息文本
     * @type {string}
     */
    var continueLineMsg = '正在绘制线';

    /**
     * 鼠标移动事件处理函数
     * @param {ol.MapBrowserEvent} evt
     */
    var pointerMoveHandler = function (evt) {
        if (evt.dragging) {
            return;
        }
        if (type == '')
        return;
        /** @type {string} */
        var helpMsg = '点击开始绘图';//当前默认提示信息
        //判断绘制几何类型设置相应的帮助提示信息
        if (sketch) {
            var geom = (sketch.getGeometry());
            if (geom instanceof ol.geom.Polygon) {
                helpMsg = continuePolygonMsg; //绘制多边形时提示相应内容
            } else if (geom instanceof ol.geom.LineString) {
                helpMsg = continueLineMsg; //绘制线时提示相应内容
            }
        }
        helpTooltipElement.innerHTML = helpMsg; //将提示信息设置到对话框中显示
        helpTooltip.setPosition(evt.coordinate);//设置帮助提示框的位置
        $(helpTooltipElement).removeClass('hidden');//移除帮助提示框的隐藏样式进行显示
    };
    map.on('pointermove', pointerMoveHandler); //地图容器绑定鼠标移动事件，动态显示帮助提示框内容
    //地图绑定鼠标移出事件，鼠标移出时为帮助提示框设置隐藏样式
    $(map.getViewport()).on('mouseout', function () {
        $(helpTooltipElement).addClass('hidden');
    });

    var draw; // global so we can remove it later
    /**
     * 加载交互绘制控件函数
     */
    function addInteraction() {
        if (type == '')
        return;
        draw = new ol.interaction.Draw({
            source: source,//测量绘制层数据源
            type: /** @type {ol.geom.GeometryType} */ (type),  //几何图形类型
            style: new ol.style.Style({//绘制几何图形的样式
                fill: new ol.style.Fill({
                    color: 'rgba(255, 255, 255, 0.2)'
                }),
                stroke: new ol.style.Stroke({
                    color: 'rgba(0, 0, 0, 0.5)',
                    lineDash: [10, 10],
                    width: 2
                }),
                image: new ol.style.Circle({
                    radius: 5,
                    stroke: new ol.style.Stroke({
                        color: 'rgba(0, 0, 0, 0.7)'
                    }),
                    fill: new ol.style.Fill({
                        color: 'rgba(255, 255, 255, 0.2)'
                    })
                })
            })
        });
        map.addInteraction(draw);

        createMeasureTooltip(); //创建测量工具提示框
        createHelpTooltip(); //创建帮助提示框

        var listener;
        //绑定交互绘制工具开始绘制的事件
        draw.on('drawstart',
            function (evt) {
                // set sketch
                sketch = evt.feature; //绘制的要素

                /** @type {ol.Coordinate|undefined} */
                var tooltipCoord = evt.coordinate;// 绘制的坐标
                //绑定change事件，根据绘制几何类型得到测量长度值或面积值，并将其设置到测量工具提示框中显示
                listener = sketch.getGeometry().on('change', function (evt) {
                    var geom = evt.target;//绘制几何要素
                    var output;
                    if (geom instanceof ol.geom.Polygon) {
                        output = formatArea(/** @type {ol.geom.Polygon} */(geom));//面积值
                        tooltipCoord = geom.getInteriorPoint().getCoordinates();//坐标
                    } else if (geom instanceof ol.geom.LineString) {
                        output = formatLength( /** @type {ol.geom.LineString} */(geom));//长度值
                        tooltipCoord = geom.getLastCoordinate();//坐标
                    }
                    measureTooltipElement.innerHTML = output;//将测量值设置到测量工具提示框中显示
                    measureTooltip.setPosition(tooltipCoord);//设置测量工具提示框的显示位置
                });
            }, this);
        //绑定交互绘制工具结束绘制的事件
        draw.on('drawend',
            function (evt) {
                measureTooltipElement.className = 'tooltip tooltip-static'; //设置测量提示框的样式
                measureTooltip.setOffset([0, -7]);
                // unset sketch
                sketch = null; //置空当前绘制的要素对象
                // unset tooltip so that a new one can be created
                measureTooltipElement = null; //置空测量工具提示框对象
                createMeasureTooltip();//重新创建一个测试工具提示框显示结果
                ol.Observable.unByKey(listener);
            }, this);
    }


    /**
     *创建一个新的帮助提示框（tooltip）
     */
    function createHelpTooltip() {
        if (helpTooltipElement) {
            helpTooltipElement.parentNode.removeChild(helpTooltipElement);
        }
        helpTooltipElement = document.createElement('div');
        helpTooltipElement.className = 'tooltip hidden';
        helpTooltip = new ol.Overlay({
            element: helpTooltipElement,
            offset: [15, 0],
            positioning: 'center-left'
        });
        map.addOverlay(helpTooltip);
    }

    /**
     *创建一个新的测量工具提示框（tooltip）
     */
    function createMeasureTooltip() {
        if (measureTooltipElement) {
            measureTooltipElement.parentNode.removeChild(measureTooltipElement);
        }
        measureTooltipElement = document.createElement('div');
        measureTooltipElement.className = 'tooltip tooltip-measure';
        measureTooltip = new ol.Overlay({
            element: measureTooltipElement,
            offset: [0, -15],
            positioning: 'bottom-center'
        });
        map.addOverlay(measureTooltip);
    }

    /**
     * 让用户切换选择测量类型（长度/面积）
     * @param {Event} e Change event.
     */
    off.onclick = function (e) {
        map.removeInteraction(draw); //移除绘制图形
        type='';
        $("#off").hide();
        if (type != '')  addInteraction();//添加绘图进行测量
    };

    /**
     * 测量长度输出
     * @param {ol.geom.LineString} line
     * @return {string}
     */
    var formatLength = function (line) {
        var length;
        var coordinates = line.getCoordinates();//解析线的坐标
        length = 0;
        var sourceProj = map.getView().getProjection(); //地图数据源投影坐标系
        //通过遍历坐标计算两点之前距离，进而得到整条线的长度
        for (var i = 0, ii = coordinates.length - 1; i < ii; ++i) {
            var c1 = ol.proj.transform(coordinates[i], sourceProj, 'EPSG:4326');
            var c2 = ol.proj.transform(coordinates[i + 1], sourceProj, 'EPSG:4326');
            length += wgs84Sphere.haversineDistance(c1, c2);
        }
        var output;
        if (length > 100) {
            output = (Math.round(length / 1000 * 100) / 100) + ' ' + 'km'; //换算成KM单位
        } else {
            output = (Math.round(length * 100) / 100) + ' ' + 'm'; //m为单位
        }
        return output;//返回线的长度
    };
    /**
     * 测量面积输出
     * @param {ol.geom.Polygon} polygon
     * @return {string}
     */
    var formatArea = function (polygon) {
        var area;
        var sourceProj = map.getView().getProjection();//地图数据源投影坐标系
        var geom = /** @type {ol.geom.Polygon} */(polygon.clone().transform(sourceProj, 'EPSG:4326')); //将多边形要素坐标系投影为EPSG:4326
        var coordinates = geom.getLinearRing(0).getCoordinates();//解析多边形的坐标值
        area = Math.abs(wgs84Sphere.geodesicArea(coordinates)); //获取面积
        var output;
        if (area > 10000) {
            output = (Math.round(area / 1000000 * 100) / 100) + ' ' + 'km<sup>2</sup>'; //换算成KM单位
        } else {
            output = (Math.round(area * 100) / 100) + ' ' + 'm<sup>2</sup>';//m为单位
        }
        return output; //返回多边形的面积
    };

    addInteraction(); //调用加载绘制交互控件方法，添加绘图进行测量

}

/**
 * 绘制
 * @param type
 */
function drawGis(type,tableType) {
    var off = document.getElementById('off');
    $("#off").val("取消绘制");
    $("#off").show();
    //绘制类型对象
    var typeSelect = type;
    //绘制对象
    var draw;
    //实例化一个矢量图层Vector作为绘制层
    var source = new ol.source.Vector({wrapX: false});
    var vector = new ol.layer.Vector({
        source: source,
        style: new ol.style.Style({
            fill: new ol.style.Fill({
                color: 'rgba(255, 255, 255, 0.2)'
            }),
            stroke: new ol.style.Stroke({
                color: '#ffcc33',
                width: 2
            }),
            image: new ol.style.Circle({
                radius: 7,
                fill: new ol.style.Fill({
                    color: '#ffcc33'
                })
            })
        })
    });
    //将绘制层添加到地图容器中
    map.addLayer(vector);

    //根据绘制类型进行交互绘制图形处理
    function addInteraction() {
        //绘制类型
        var value = typeSelect;
        if (value !== '') {
            if(value=='Grassland'){
                value='Polygon';
            }
            if (source == null) {
                source = new ol.source.Vector({wrapX: false});
                //添加绘制层数据源
                vector.setSource(source);
            }
            var geometryFunction, maxPoints;

            //实例化交互绘制类对象并添加到地图容器中
            draw = new ol.interaction.Draw({
                //绘制层数据源
                source: source,
                /** @type {ol.geom.GeometryType} */
                //几何图形类型
                type: (value),
                //几何信息变更时调用函数
                geometryFunction: geometryFunction,
                //最大点数
                maxPoints: maxPoints
            });
            draw.on('drawend', function (e) {
                switch (typeSelect) {
                    case 'Point':
                        var coordinates_Point = e.feature.getGeometry().getCoordinates();
                        spotinfos = coordinates_Point;
                        if(tableType=="tab"){
                            addGisTab(0, 1, coordinates_Point,spotinfos);
                        }else{
                            addGisMap(0, 1, coordinates_Point,spotinfos);
                        }
                        break;
                    case 'Grassland':
                        spotinfos="";
                        var coordinates_Polygon = e.feature.getGeometry().getCoordinates();
                        for (var i = 0; i < coordinates_Polygon[0].length; i++) {
                            spotinfos+=coordinates_Polygon[0][i]+";";
                        }
                        $.post(prefixGisTab+"/getGrassLandArea",
                            {geo:spotinfos},function (res) {
                                console.log(res);
                            })
                        break;
                    default:
                        var coordinates_Polygon = e.feature.getGeometry().getCoordinates();
                        spotinfos="";
                        for (var i = 0; i < coordinates_Polygon[0].length; i++) {
                            spotinfos+=coordinates_Polygon[0][i]+";";
                        }
                        if(tableType=="tab"){
                            addGisTab(0, 2,spotinfos,coordinates_Polygon[0][0]);
                        }else{
                            addGisMap(0, 2,spotinfos,coordinates_Polygon[0][0]);
                        }
                        break;
                }
            });
            map.addInteraction(draw);
        } else {
            source = null;
            //清空绘制图形
            vector.setSource(source);
        }
    }

    /**
     * 用户更改绘制类型触发的事件.
     * @param {Event} e 更改事件
     */
    off.onclick = function (e) {
        //移除绘制图形
        map.removeInteraction(draw);
        typeSelect='';
        $("#off").hide();
        if (typeSelect != '')
        //添加交互绘制功能控件
            addInteraction();
    };
    //添加交互绘制功能控件
    addInteraction();
}

/**
 * 获取业务草地图层
 * @param shpData
 */
function shpInfo(shpData) {
    var tiled = new ol.layer.Tile({
        title: shpData.server_name,
        id: shpData.server_name,
        source: new ol.source.TileWMS({
            url: gisUrl + baseUrlLayer,
            params: {
                FORMAT: 'image/png',
                VERSION: '1.1.1',
                tiled: true,
                exceptions: 'application/vnd.ogc.se_inimage',
                LAYERS: 'gis:' + shpData.server_name
            }
        })
    });
    map.addLayer(tiled);
    mapClick=map.on('singleclick', function (evt) {
        var view = map.getView()
        var viewResolution = view.getResolution();
        var source = tiled.getSource();
        var url = source.getGetFeatureInfoUrl(
            evt.coordinate, viewResolution, view.getProjection(),
            {'INFO_FORMAT': 'application/json', 'FEATURE_COUNT': 50});
        $.getJSON(url,function(data){
            var feature = data.features;
            if(feature==[] || feature.length==0 ||feature==null){
                layer.msg("此区域为 : 暂无数据信息!", {icon: 6, time: 2000});
            }
            for (var i = 0; i < feature.length; i++) {
                var jsonstr = feature[i].properties;
                nfid = feature[i].id;
                var text = jsonstr["科"];
                if (jsonstr["属"] != '' && jsonstr["属"] != null) {
                    text += "-" + jsonstr["属"];
                }
                if (jsonstr["丛"] != '' && jsonstr["丛"] != null) {
                    text += "-" + jsonstr["丛"];
                }
                layer.msg("此区域为 : " + text, {icon: 6, time: 2000});
            }
        })
    })

}

/**
 * 加载省市区图层数据
 * @param cityName 城市
 * @param cqlName  查询条件
 * @param cityId   id
 * @param layers   图层名称
 */
function plotCity(cityName, cqlName, cityId, layers) {
    if (vectorDrawing != null) {
        map.removeLayer(vectorDrawing);
    }
    vectorDrawing = new ol.layer.Tile({
        title: cityName,
        id: cityId,
        source: new ol.source.TileWMS({
            url: gisUrl + urlLayer,
            params: {
                FORMAT: 'image/png',
                VERSION: '1.1.1',
                tiled: true,
                exceptions: 'application/vnd.ogc.se_inimage',
                LAYERS: 'gis:' + layers,
                CQL_FILTER: cqlName
            }
        })
    });
    map.addLayer(vectorDrawing);
}

/**
 * 图层加载
 * @param cqlName  "project_type='" + cqlName + "'" 查询字段
 * @param layers  图层明
 */
function loadgis(cqlName,layers) {
    var gisSource = new ol.source.TileWMS({
        url: gisUrl + urlLayer,
        params: {
            FORMAT: 'image/png',
            VERSION: '1.1.1',
            tiled: true,
            exceptions: 'application/vnd.ogc.se_inimage',
            LAYERS: layers,
            CQL_FILTER: cqlName
        }
    });
    var vector = new ol.layer.Tile({
        source: gisSource
    });
    map.addLayer(vector);
    //定义修改几何图形的功能控件
    var Modify = {
        init: function(){
            //初始化一个交互选择控件，并添加到地图容器中
            this.select = new ol.interaction.Select();
            map.addInteraction(this.select);
            //初始化一个交互编辑控件，并添加到地图容器中
            this.modify = new ol.interaction.Modify({
                features: this.select.getFeatures()           //选中的要素集
            });
            map.addInteraction(this.modify);
            //设置激活状态变更的处理
            this.setEvents();
        },
        setEvents: function(){
            var selectedFeatures = this.select.getFeatures();       //选中的要素集
            //添加选中要素变更事件
            this.select.on("change:active", function(){
                //遍历选择要素集，返回当前第一个要素（即移除的要素）
                selectedFeatures.forEach(selectedFeatures.remove, selectedFeatures);
            });
        },
        setActive: function(active){
            this.select.setActive(active);                  //激活选择要素控件
            this.modify.setActive(active);                  //激活修改要素控件
        }
    };
    Modify.init();                          //初始化几何图形修改控件
    Modify.setActive(true);                 //激活几何图形修改控件
}

/**
 * 上报绘图结束调用此方法
 * @param id
 * @param type
 * @param geo
 * @param centre
 */
function addGisMap(id, type, geo,centre) {
    map.removeEventListener("dblclick", addGisMap);
    $.modal.open("添加Gis上报信息", prefixGisMap + "/addGisMap?id=" + id + "&type=" + type + "&geo=" + geo+ "&centre=" + centre, 400, 550);
}
/**
 * 上报绘图结束调用此方法
 * @param id
 * @param type
 * @param geo
 * @param centre
 */
function addGisTab(id, type, geo,centre) {
    map.removeEventListener("dblclick", addGisMap);
    $.modal.open("标注信息", prefixGisTab + "/addGisTab?id=" + id + "&type=" + type + "&geo=" + geo+ "&centre=" + centre, 400, 550);
}
function getTabList() {
    $.get(prefixGisTab+"/getList",function (res) {
        console.log(res);
    })
}
//绘制点
function drawPoint(poi) {
    //创建一个点
    var point = new ol.Feature({
        geometry: new ol.geom.Point(poi)
    });
    //设置点1的样式信息
    point.setStyle(new ol.style.Style({
        //填充色
        fill: new ol.style.Fill({
            color: 'rgba(255, 255, 255, 0.2)'
        }),
        //边线颜色
        stroke: new ol.style.Stroke({
            color: '#F12A07',
            width: 2
        }),
        //形状
        image: new ol.style.Circle({
            radius: 10,
            fill: new ol.style.Fill({
                color:  '#F12A07'
            })
        })
    }));

    //实例化一个矢量图层Vector作为绘制层
    var source = new ol.source.Vector({
        features: [point]
    });
    //创建一个图层
    var vector = new ol.layer.Vector({
        source: source
    });
    //将绘制层添加到地图容器中
    map.addLayer(vector);
}
//上报图层加载
function loadgisByGid(gid) {
    var vector = new ol.layer.Tile({
        source: new ol.source.TileWMS({
            url: gisUrl + urlLayer,
            params: {
                FORMAT: 'image/png',
                VERSION: '1.1.1',
                tiled: true,
                exceptions: 'application/vnd.ogc.se_inimage',
                LAYERS: gisMapName,
                CQL_FILTER: "gid='" + gid + "'"
            }
        })
    });
    map.addLayer(vector);
}

//图层加载
function loadgisByMapId(mid,centre) {
    var vector = new ol.layer.Tile({
        source: new ol.source.TileWMS({
            url: gisUrl + urlLayer,
            params: {
                FORMAT: 'image/png',
                VERSION: '1.1.1',
                tiled: true,
                exceptions: 'application/vnd.ogc.se_inimage',
                LAYERS: gisMapName,
                FeatureID: mid
            }
        })
    });
    if(center!=null){
        var cent= centre.split(",");
        var view = map.getView();
        view.setZoom(12);
        view.setCenter([parseFloat(cent[0]), parseFloat(cent[1])]);
    }
    map.addLayer(vector);
}

//标记图层加载
function loadgisByTabId(tid,centre) {
    var vector = new ol.layer.Tile({
        source: new ol.source.TileWMS({
            url: gisUrl + urlLayer,
            params: {
                FORMAT: 'image/png',
                VERSION: '1.1.1',
                tiled: true,
                exceptions: 'application/vnd.ogc.se_inimage',
                LAYERS: gisTabName,
                FeatureID: tid
            }
        })
    });
    if(center!=null){
        var cent= centre.split(",");
        var view = map.getView();
        view.setZoom(10);
        view.setCenter([parseFloat(cent[0]), parseFloat(cent[1])]);
    }
    map.addLayer(vector);
}