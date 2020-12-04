var map, spotinfos,vectorDrawing;
var prefixGis = ctx + "system/gis";
var prefixRegion = ctx + "system/region";
var prefixGisFeatures = ctx + "system/gisFeatures";
var prefixGisTab = ctx + "system/gisTab";
var prefixGisMap = ctx + "system/gisMap";
var prefixPostGis = ctx + "system/postGis";
function onLoad() {
    load();
    if(document.getElementById("layerTree")!=null && document.getElementById("layerTree")!=''){
        loadLayersControl(map, "layerTree");
    }
}
function onLoadMap() {
    loadMap();
    if(document.getElementById("layerTree")!=null && document.getElementById("layerTree")!=''){
        loadLayersControl(map, "layerTree");
    }
}
function gisClean(id,map) {
    if(pointQueryTitleSource!=null && pointQueryTitleSource!=""){
        map.removeLayer(pointQueryTitleSource);
    }
    if (id!=''){
        clearGis(map);
    }else{
        if(titleNames.length>0){
            for(var i=0;i<titleNames.length;i++){
                map.removeLayer(titleNames[i]);
            }
        }
    }
    titleNames=[];
}
//加载专题数据 点击专题
function queryDeptTree(map,id) {
    var url = prefixGisFeatures + "/getList";
    var options = {
        id:"thematicLayer"+id,
        url: url,
        expandLevel: 1,
        check: {enable: true},
        onCheck: zOnClick
    };
    $.tree.init(options);

    function zOnClick(event, tree) {
        gisClean(id,map);
        var idsThematicLayer = $.tree.getGisCheckedNodes("thematicLayer"+id);
        var idsBusinessLayer = $.tree.getGisCheckedNodes("businessLayer"+id);
        if (idsBusinessLayer != undefined && idsBusinessLayer != null && idsBusinessLayer != '') {
            var generic_code="generic_code in (" + idsBusinessLayer + ")";
            var bundle_code="or bundle_code in (" + idsBusinessLayer + ")";
            shpInfo(generic_code+bundle_code,map);
        }
        if (idsThematicLayer != undefined && idsThematicLayer != null && idsThematicLayer != '') {
            $.post(prefixGis + "/getSpot",
                {shpIds: idsThematicLayer}, function (res) {
                    for (var i = 0; i < res.data.length; i++) {
                        var type="project_type='" + res.data[i].id + "'";
                        loadgis(type,gisMapName,map);
                    }
                })
        }
    }
}

//加载业务图层 点击事件
function serviceTree(map,id) {
    var url = prefixPostGis + "/getGressTypeList";
    var options = {
        id:"businessLayer"+id,
        url: url,
        expandLevel: 1,
        check: {enable: true},
        onCheck: zOnClick
    };
    $.tree.init(options);
    function zOnClick(event, tree) {
        gisClean(id,map);
        var idsThematicLayer = $.tree.getGisCheckedNodes("thematicLayer"+id);
        var idsBusinessLayer = $.tree.getGisCheckedNodes("businessLayer"+id);
        if (idsThematicLayer != undefined && idsThematicLayer != null && idsThematicLayer != '') {
            $.post(prefixGis + "/getSpot",
                {shpIds: idsThematicLayer}, function (res) {
                    for (var i = 0; i < res.data.length; i++) {
                        var type="project_type='" + res.data[i].id + "'";
                        loadgis(type,gisMapName,map);
                    }
                })
        }
        if (idsBusinessLayer != undefined && idsBusinessLayer != null && idsBusinessLayer != '') {
            var generic_code="generic_code in (" + idsBusinessLayer + ")";
            var bundle_code="or bundle_code in (" + idsBusinessLayer + ")";
            shpInfo(generic_code+bundle_code,map);            }
    }
}

$('#btnExpand').click(function () {
    $._tree.expandAll(true);
    $(this).hide();
    $('#btnCollapse').show();
});
/*$('#btnRefresh').click(function () {
    queryDeptTree();
});*/

$('#btnCollapse').click(function () {
    $._tree.expandAll(false);
    $(this).hide();
    $('#btnExpand').show();
});

function updateCity() {
    $.get(prefixRegion + "/province",
        function (res) {
            if (vectorDrawing != null) {
                map.removeLayer(vectorDrawing);
                map.addLayer(Provinces);
            }
            var cityHtml = "";
            for (var i = 0; i < res.length; i++) {
                let cityName="";
                if(res[i].province.length>5){
                    cityName=res[i].province.substring(0,5)+"..";
                }else{
                    cityName=res[i].province.substring(0,res[i].province.length);
                }
                cityHtml += "<li onclick=getSheng(" + res[i].province_code + ",'" + res[i].province + "','"+res[i].lon_lat+"')>" + cityName + "</li>";
            }
            $(".zq_ul").text("");
            $(".zq_ul").append(cityHtml);
            $("#type3").val('全国');
            $(".content_ul").html('全国');
            $("#dist_positioning").show();
        })
}

function getSheng(id,province,lonlat) {
    map.removeLayer(Provinces);
    var view = map.getView();
    $.get(prefixRegion + "/city?provinceCode=" + id, function (res) {
        var cityHtml = "";
        for (var i = 0; i < res.length; i++) {
            let cityName="";
            if(res[i].city.length>5){
                cityName=res[i].city.substring(0,5)+"..";
            }else{
                cityName=res[i].city.substring(0,res[i].city.length);
            }
            cityHtml += "<li onclick=getShi(" + res[i].city_code + ",'" + res[i].city + "','"+res[i].lon_lat+"')>" + cityName + "</li>";
        }
        var cityHtm = "<span style='cursor: pointer' onclick='updateCity()'>全国</span> <span style='cursor: pointer;color: #43bfac;' onclick=getSheng("+id+",'"+province+"','"+lonlat+"')> > " + province + "</span>";
        $(".content_ul").html(cityHtm);
        $(".zq_ul").text("");
        $(".zq_ul").append(cityHtml);
        $("#dist_positioning").show();
        var can = lonlat.split(",");
        view.setCenter([parseFloat(can[0]), parseFloat(can[1])]);
        view.setZoom(6);
        $("#type3").val(province);
        if(id==99999){
            plotCity( "province_code in (510000,530000,520000,500000,320000,360000,340000,420000,430000,330000,310000)", "xian");

        }else{
            plotCity( "province_code="+id, "xian");
        }
    })
}

function getShi(id,city,lonlat) {
    var view = map.getView();
    $.get(prefixRegion + "/county?cityCode=" + id, function (res) {
        $(".zq_ul").text("");
        var cityHtml = "";
        for (var i = 0; i < res.length; i++) {
            let cityName="";
            if(res[i].name.length>5){
                cityName=res[i].name.substring(0,5)+"..";
            }else{
                cityName=res[i].name.substring(0,res[i].name.length);
            }
            cityHtml += "<li onclick=getQu(" + res[i].pac + ",'" + res[i].name + "','" + res[i].lon_lat + "')>" + cityName + "</li>";
        }
        $(".zq_ul").append(cityHtml);
        var cityHtm = "<span style='cursor: pointer'> > " + city + "</span>";
        $(".content_ul").append(cityHtm);
        $("#dist_positioning").show();
        var can = lonlat.split(",");
        view.setCenter([parseFloat(can[0]), parseFloat(can[1])]);
        view.setZoom(7);
        $("#type3").val(city);
        plotCity("city_code ="+id, "xian");
    })
}

function getQu(id,name,lonlat) {
    var view = map.getView();
    $("#dist_positioning").show();
    var can = lonlat.split(",");
    view.setCenter([parseFloat(can[0]), parseFloat(can[1])]);
    view.setZoom(10);
    $("#type3").val(name);
    plotCity( "pac="+id,  "xian");
}

function cityDivDoneById(name) {
    var newNam = "#" + name;
    $(newNam).hide();
    $("#type3").val('全国');
    if (vectorDrawing != null) {
        map.removeLayer(vectorDrawing);
    }

}
$(function () {
    var panehHidden = false;
    if ($(this).width() < 769) {
        panehHidden = true;
    }
    $('body').layout({initClosed: panehHidden, west__size: 185});
});

/*展示树状列表*/
$('.layer_tree_show_map').on('click',function () {
    $('.west').show();
    $('.layer_tree_hide').show();
    queryDeptTree(map,"");
    serviceTree(map,"");
});

$('.layer_tree_hide i').on('click',function () {
    $('.west').hide();
    $('.layer_tree_hide').hide();
});

/* 上报信息列表-条件查询弹框 */
$("#conditionQuery").click(function(){
    let conditionQuery_sel = $('.conditionQuery_sel');
    if(conditionQuery_sel[0].style.display=="none"){
        $('.conditionQuery_sel').show();
    }else{
        $('.conditionQuery_sel').hide();
    }
});

/* 标记列表 */
$("#markedList").click(function(){
    let options = {
        id:"tagTable",
        url: prefixGisTab+"/getList",
        modalName: "新增退牧还草工程进度",
        showSearch: false,
        showToggle: false,
        showColumns: false,
        showRefresh: false,
        pageList:[5,10,15],//分页步进值
        pageSize: 5,
        columns: [
            [
                {
                    field: 'index',
                    title: '序号',
                    rowspan: 1,
                    colspan: 1,
                    align: 'center',
                    valign: 'middle',
                    formatter: function (value, row, index) {
                        return index + 1;
                    }
                },
                {
                    field: 'address',
                    title: '地区',
                    rowspan: 1,
                    colspan: 1,
                    align: 'center',
                    valign: 'middle',
                    formatter: function (value, row, index) {
                        let ht = "<div onclick=onClickRowMark('"+row.id + "','" + row.centre + "')></div>";
                        return row.address + ht;
                    }
                },
                {
                    field: 'name',
                    title: '标签标题',
                    rowspan: 1,
                    colspan: 1,
                    align: 'center',
                    valign: 'middle',
                },
                {
                    field: 'createTime',
                    title: '创建时间',
                    rowspan: 1,
                    colspan: 1,
                    align: 'center',
                    valign: 'middle',
                },
                {
                    title: '标签类型',
                    rowspan: 1,
                    colspan: 1,
                    align: 'center',
                    valign: 'middle',
                    formatter: function (value, row, index) {
                        return row.type == 1 ? '点':'面';
                    }
                },
                {
                    title: '面积',
                    rowspan: 1,
                    colspan: 1,
                    align: 'center',
                    valign: 'middle',
                    border: 1,
                    formatter: function (value, row, index) {
                        return row.area + '万km²';
                    }
                },
                {
                    field: 'remark',
                    title: '备注',
                    rowspan: 1,
                    colspan: 1,
                    align: 'center',
                    valign: 'middle'
                },
            ],
        ]
    };
    $.table.init(options);
    $('.reportList').show();
    $('#reportDiv').hide();//专题
    $('#businessDiv').hide();//业务
    $('#tagDiv').show();//标记
});

/* 业务查询 */
$("#business").click(function(){
    businessSearch();
    let cityHtml = '<option value="">--请选择--</option>';
    $.get(prefixPostGis + "/getSection",
        function (res) {
            for (var i = 0; i < res.length; i++) {
                cityHtml += '<option value="'+ res[i].section +'">'+ res[i].section +'</option>';
            }
            $("#section").text("");
            $("#section").append(cityHtml);
        })
});
function sectionOncheng() {
    let cityHtml = '<option value="">--请选择--</option>';
    $("#bundle").text("");
    $("#bundle").append(cityHtml);
    $("#generic").text("");
    $("#generic").append(cityHtml);
    var section= $("#section").val()
    if(section!=null && section!=''){
        $.get(prefixPostGis + "/getGeneric?section="+section,
            function (res) {
                for (var i = 0; i < res.length; i++) {
                    cityHtml += '<option value="'+ res[i].generic +'">'+ res[i].generic +'</option>';
                }
                $("#generic").text("");
                $("#generic").append(cityHtml);
            })
    }

}
function bundleOncheng() {
    let cityHtml = '<option value="">--请选择--</option>';
    $("#bundle").text("");
    $("#bundle").append(cityHtml);
    var generic= $("#generic").val()
    if(generic!=null && generic!=""){
        $.get(prefixPostGis + "/getBundle?generic="+generic,
            function (res) {
                for (var i = 0; i < res.length; i++) {
                    cityHtml += '<option value="'+ res[i].bundle +'">'+ res[i].bundle +'</option>';
                }
                $("#bundle").text("");
                $("#bundle").append(cityHtml);
            })
    }
}
/* 业务查询搜索 */
function businessSearch(){
    $.table.destroy('businessTable');
    var options = {
        id:"businessTable",
        pagination:true,
        url:prefixPostGis + "/getGressList",
        showSearch: false,
        showToggle: false,
        showColumns: false,
        showRefresh: false,
        pageList:[5,10,15],//分页步进值
        pageSize: 5,
        columns: [
            {
                field: 'province_name',
                title: '地区',
                align: 'center',
                valign: 'middle',
                class:true,
                formatter: function (value, row, index){
                    var centroid=  row.centroid.replace("POINT(","").replace(")","").replace(" ",",");
                    let ht ="<div onclick=grassPositioning('"+row.id + "','" + centroid + "')></div>";
                    return row.province_name + ht;
                }
            },
            {
                field: 'item',
                title: '属',
                align: 'center',
                valign: 'middle',
            },
            {
                field: 'section',
                title: '目',
                align: 'center',
                valign: 'middle',
            },
            {
                field: 'generic',
                title: '科',
                align: 'center',
                valign: 'middle',
            },
            {
                field: 'bundle',
                title: '丛',
                align: 'center',
                valign: 'middle',
            },
            {
                field: 'area',
                title: '占地面积(km²)',
                align: 'center',
                valign: 'middle',
                formatter: function (value, row, index){
                    var num =row.area /1000000;
                    return num.toFixed(2);
                }
            },

        ]
    }
    $.table.init(options);
    $('.reportList').show();
    $('#reportDiv').hide();//专题
    $('#businessDiv').show();//业务
    $('#tagDiv').hide();//标记
}




/* 专题查询 */
$("#special").click(function(){
    var prefix = ctx + "caiji/survey";

    var options = {
        id:"reportTable",
        url: prefix + "/list",
        createUrl: prefix + "/add",
        updateUrl: prefix + "/edit/{id}",
        removeUrl: prefix + "/remove",
        detailUrl: prefix + "/check/{id}",
        exportUrl: prefix + "/export",
        showSearch:false,
        showRefresh:false,
        showColumns:false,
        showToggle:false,
        onClickRow: onClickRow,
        //pageList:[3,6,9,12],//分页步进值
        pageSize: 5,
        modalName: "地面调查数据",
        columns: [
            {
                field : 'codeId',
                align: 'center',
                valign: 'middle',
                title : '样地编号'
            },
            {
                field : 'provincialLevelName',
                align: 'center',
                valign: 'middle',
                title : '省级名称'
            },
            {
                field : 'cityLevelName',
                align: 'center',
                valign: 'middle',
                title : '地级名称'
            },
            {
                field : 'countyLevelName',
                align: 'center',
                valign: 'middle',
                title : '县级名称'
            },
            /*  {
                  field : 'surveyTime',
                  align: 'center',
                  valign: 'middle',
                  title : '调查时间'
              },*/
            {
                field : 'createTime',
                align: 'center',
                valign: 'middle',
                title : '创建时间'
            },
            {
                field : 'staute',
                align: 'center',
                valign: 'middle',
                title : '状态',
            },
        ]
    };

    $.table.init(options);
    $('.reportList').show();
    $('#reportDiv').show();//专题
    $('#businessDiv').hide();//业务
    $('#tagDiv').hide();//标记
});

/*专题列表点击图标定位*/
function specialPositioning(mapId) {
    if(mapId!=null && mapId!='null'){
        $.get(prefixGisMap+"/getTabById?id="+mapId,function (res) {
            if(res!=null && res!=""){
                loadgisByMapId(res.id,res.centre);
            }else{
                layer.msg("当前数据未绑定图层",{icon: 6, time: 3000});
            }
        })
    }else{
        layer.msg("当前数据未绑定图层",{icon: 6, time: 3000});
    }
}

/* 关闭上报信息列表 */
function closeReportList() {
    $('.reportList').hide();
}

/* 标记列表 单击行定位上报图层 */
function onClickRowMark(id,centre){
    loadgisByTabId(id,centre);
}

function onClickRow(row, $element) {

    if (row.mapId != null) {
        $.get(prefixGisMap + "/getTabById?id=" + row.mapId, function (res) {
            if(res!=null && res!=""){
                loadgisByMapId(res.id,res.centre);
            }else{
                layer.msg("当前数据未绑定图层.",{icon: 6, time: 3000});
            }
        })
    }

}


function grassPositioning(id,centroid){
    var idType="id =" + id + "";
    grassPosit(idType,centroid);
}

layui.use('laydate', function () {
    var laydate = layui.laydate;
    //执行一个laydate实例
    laydate.render({
        elem: '#test2'
        , type: 'year'
    });
});

/* 省市区三级联动 */
// ===== 切换省份 =====
$("#province").change(function () {

    let provinceCode = $('#province option:selected').val();
    $("#addressCity").empty();
    $("#addrArea").empty();
    //获取所属市级
    getCitiess(provinceCode);
});
// ===== 切换市级 =====
$("#addressCity").change(function () {
    let cityCode = $('#addressCity option:selected').val();
    $("#addrArea").empty();
    //获取区县级
    getAreass(cityCode);
});

// ===== 加载市列表 =====
function getCitiess(value) {
    $("#addressCity").empty();
    $("#addrArea").empty();
    var url = ctx + "caiji/survey/getCities";
    var data = "provinceCode=" + value;
    $.ajax({
        "url": url,
        "data": data,
        "type": "GET",
        "dataType": "json",
        "success": function (data) {
            var op = document.createElement("option");
            op.value = '';
            op.text = "-- 请选择地址 --";

            document.getElementById("addressCity").appendChild(op);
            for (var i = 0; i < data.length; i++) {
                var op = document.createElement("option");
                op.value = data[i].dictCode;
                op.text = data[i].dictLabel;
                document.getElementById("addressCity").appendChild(op);
            }
        }
    });

    var op2 = document.createElement("option");
    op2.value = '';
    op2.text = "-- 请选择地址 --";

    document.getElementById("addrArea").appendChild(op2);
}

// ===== 加载区列表 =====
function getAreass(value) {
    $("#addrArea").empty();
    $("#addrdz").empty();
    //var url = "<%=basePath%>dict/getAreas";
    var url = ctx + "caiji/survey/getAreas";
    var data = "cityCode=" + value;
    $.ajax({
        "url": url,
        "data": data,
        "type": "GET",
        "dataType": "json",
        "success": function (data) {
            var op = document.createElement("option");
            op.value = '';
            op.text = "-- 请选择地址 --";

            document.getElementById("addrArea").appendChild(op);
            for (var i = 0; i < data.length; i++) {
                var op = document.createElement("option");
                op.value = data[i].dictCode;
                op.text = data[i].dictLabel;
                document.getElementById("addrArea").appendChild(op);
            }
        }
    });

}
/* 省市区三级联动end */

/* 经纬度定位 */
$('#locationShow').on("click",function () {
    $('.coordinatePositioning').show();
})

/* 经纬度定位 */
$("#positioning_btn").on("click",function () {
    let decimalLon = $('#decimalLon').val();//经度
    let decimalLat = $('#decimalLat').val();//纬度
    locationCenter(decimalLon,decimalLat);
})
/* 关闭 */
$("#closePositioning").on("click",function () {
    $(".coordinatePositioning").hide();
})
function show3D() {
    $("#map").hide();
    $("#cesiumContainer").show();
    // 服务域名
    var tdtUrl = 'https://t{s}.tianditu.gov.cn/';
    // 服务负载子域
    var subdomains=['0','1','2','3','4','5','6','7'];

    // cesium 初始化
    var viewer = new Cesium.Map('cesiumContainer', {
        animation:false,//是否创建动画小器件，左下角仪表
        timeline: false,//是否显示时间轴
        sceneModePicker: false,//是否显示3D/2D选择器
        baseLayerPicker: false,//是否显示图层选择器
        geocoder: false,//是否显示geocoder小器件，右上角查询按钮
        scene3DOnly: true,//如果设置为true，则所有几何图形以3D模式绘制以节约GPU资源
        navigationHelpButton: false,//是否显示右上角的帮助按钮
        homeButton: false,//是否显示Home按钮
        infoBox: false,//是否显示信息框
        showRenderLoopErrors: false//如果设为true，将在一个HTML面板中显示错误信息
    });

    // 抗锯齿
    viewer.scene.postProcessStages.fxaa.enabled=false;
    // 水雾特效
    viewer.scene.globe.showGroundAtmosphere = true;
    // 设置最大俯仰角，[-90,0]区间内，默认为-30，单位弧度
    viewer.scene.screenSpaceCameraController.constrainedPitch = Cesium.Math.toRadians(-20);
    // 取消默认的双击事件
    viewer.cesiumWidget.screenSpaceEventHandler.removeInputAction(Cesium.ScreenSpaceEventType.LEFT_DOUBLE_CLICK);

    // 叠加影像服务
    var imgMap = new Cesium.UrlTemplateImageryProvider({
        url: tdtUrl + 'DataServer?T=img_w&x={x}&y={y}&l={z}&tk=' + TiandituKey,
        subdomains: subdomains,
        tilingScheme : new Cesium.WebMercatorTilingScheme(),
        maximumLevel : 18
    });
    viewer.imageryLayers.addImageryProvider(imgMap);

    // 叠加国界服务
    var iboMap = new Cesium.UrlTemplateImageryProvider({
        url: tdtUrl + 'DataServer?T=ibo_w&x={x}&y={y}&l={z}&tk=' + TiandituKey,
        subdomains: subdomains,
        tilingScheme : new Cesium.WebMercatorTilingScheme(),
        maximumLevel : 10
    });
    viewer.imageryLayers.addImageryProvider(iboMap);

    // 叠加地形服务
    var terrainUrls = new Array();

    for (var i = 0; i < subdomains.length; i++){
        var url = tdtUrl.replace('{s}', subdomains[i]) + 'DataServer?T=elv_c&tk=' + TiandituKey;
        terrainUrls.push(url);
    }

    var provider = new Cesium.GeoTerrainProvider({
        urls: terrainUrls
    });

    viewer.terrainProvider = provider;

    // 将三维球定位到中国
    viewer.camera.flyTo({
        destination: Cesium.Cartesian3.fromDegrees(103.84, 31.15, 17850000),
        orientation: {
            heading :  Cesium.Math.toRadians(348.4202942851978),
            pitch : Cesium.Math.toRadians(-89.74026687972041),
            roll : Cesium.Math.toRadians(0)
        },
        complete:function callback() {
            // 定位完成之后的回调函数
        }
    });

    // 叠加三维地名服务
    var wtfs = new Cesium.GeoWTFS({
        viewer,
        //三维地名服务，使用wtfs服务
        subdomains:subdomains,
        metadata:{
            boundBox: {
                minX: -180,
                minY: -90,
                maxX: 180,
                maxY: 90
            },
            minLevel: 1,
            maxLevel: 20
        },
        aotuCollide: true, //是否开启避让
        collisionPadding: [5, 10, 8, 5], //开启避让时，标注碰撞增加内边距，上、右、下、左
        serverFirstStyle: true, //服务端样式优先
        labelGraphics: {
            font:"28px sans-serif",
            fontSize: 28,
            fillColor:Cesium.Color.WHITE,
            scale: 0.5,
            outlineColor:Cesium.Color.BLACK,
            outlineWidth: 5,
            style:Cesium.LabelStyle.FILL_AND_OUTLINE,
            showBackground:false,
            backgroundColor:Cesium.Color.RED,
            backgroundPadding:new Cesium.Cartesian2(10, 10),
            horizontalOrigin:Cesium.HorizontalOrigin.MIDDLE,
            verticalOrigin:Cesium.VerticalOrigin.TOP,
            eyeOffset:Cesium.Cartesian3.ZERO,
            pixelOffset:new Cesium.Cartesian2(0, 8)
        },
        billboardGraphics: {
            horizontalOrigin:Cesium.HorizontalOrigin.CENTER,
            verticalOrigin:Cesium.VerticalOrigin.CENTER,
            eyeOffset:Cesium.Cartesian3.ZERO,
            pixelOffset:Cesium.Cartesian2.ZERO,
            alignedAxis:Cesium.Cartesian3.ZERO,
            color:Cesium.Color.WHITE,
            rotation:0,
            scale:1,
            width:18,
            height:18
        }
    });

    //三维地名服务，使用wtfs服务
    wtfs.getTileUrl = function(){
        return tdtUrl + 'mapservice/GetTiles?lxys={z},{x},{y}&tk='+ TiandituKey;
    }

    wtfs.getIcoUrl = function(){
        return tdtUrl + 'mapservice/GetIcon?id={id}&tk='+ TiandituKey;
    }

    wtfs.initTDT([{"x":6,"y":1,"level":2,"boundBox":{"minX":90,"minY":0,"maxX":135,"maxY":45}},{"x":7,"y":1,"level":2,"boundBox":{"minX":135,"minY":0,"maxX":180,"maxY":45}},{"x":6,"y":0,"level":2,"boundBox":{"minX":90,"minY":45,"maxX":135,"maxY":90}},{"x":7,"y":0,"level":2,"boundBox":{"minX":135,"minY":45,"maxX":180,"maxY":90}},{"x":5,"y":1,"level":2,"boundBox":{"minX":45,"minY":0,"maxX":90,"maxY":45}},{"x":4,"y":1,"level":2,"boundBox":{"minX":0,"minY":0,"maxX":45,"maxY":45}},{"x":5,"y":0,"level":2,"boundBox":{"minX":45,"minY":45,"maxX":90,"maxY":90}},{"x":4,"y":0,"level":2,"boundBox":{"minX":0,"minY":45,"maxX":45,"maxY":90}},{"x":6,"y":2,"level":2,"boundBox":{"minX":90,"minY":-45,"maxX":135,"maxY":0}},{"x":6,"y":3,"level":2,"boundBox":{"minX":90,"minY":-90,"maxX":135,"maxY":-45}},{"x":7,"y":2,"level":2,"boundBox":{"minX":135,"minY":-45,"maxX":180,"maxY":0}},{"x":5,"y":2,"level":2,"boundBox":{"minX":45,"minY":-45,"maxX":90,"maxY":0}},{"x":4,"y":2,"level":2,"boundBox":{"minX":0,"minY":-45,"maxX":45,"maxY":0}},{"x":3,"y":1,"level":2,"boundBox":{"minX":-45,"minY":0,"maxX":0,"maxY":45}},{"x":3,"y":0,"level":2,"boundBox":{"minX":-45,"minY":45,"maxX":0,"maxY":90}},{"x":2,"y":0,"level":2,"boundBox":{"minX":-90,"minY":45,"maxX":-45,"maxY":90}},{"x":0,"y":1,"level":2,"boundBox":{"minX":-180,"minY":0,"maxX":-135,"maxY":45}},{"x":1,"y":0,"level":2,"boundBox":{"minX":-135,"minY":45,"maxX":-90,"maxY":90}},{"x":0,"y":0,"level":2,"boundBox":{"minX":-180,"minY":45,"maxX":-135,"maxY":90}}]);
}
function quit3D() {
    $("#map").show();
    $("#cesiumContainer").hide();
}