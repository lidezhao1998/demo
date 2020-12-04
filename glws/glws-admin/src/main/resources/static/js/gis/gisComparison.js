var map_left,map_right;
var view =new ol.View({
    //地图初始中心点
    center: [105, 35],
    projection: "EPSG:4326",
    //地图初始显示级别
    zoom: 5,
    minZoom: 5,
    maxZoom: 19
});
// 创建第一个地图
map_left= new ol.Map({
    logo:false,
    layers: [TiandiMap_vec, TiandiMap_cva, TiandiMap_img, TiandiMap_cia,Provinces],
    view: view,
});

// 创建第二个地图
map_right=new ol.Map({
    logo:false,
    layers: [TiandiMap_vec, TiandiMap_cva, TiandiMap_img, TiandiMap_cia,Provinces],
    view: view,
});

//双屏对比
function comparison() {
    $("#map").hide();
    $("#map-left").show();
    $("#map-right").show();
    map_left.setTarget("map-left")
    map_right.setTarget("map-right")
}
/*展示树状列表*/
$('.layer_tree_show_map_left').on('click',function () {
    $('.west-left').show();
    $('.layer_tree_hide_left').show();
    queryDeptTree(map_left,"_left");
    serviceTree(map_left,"_left");
});

$('.layer_tree_hide_left i').on('click',function () {
    $('.west-left').hide();
    $('.layer_tree_hide_left').hide();
});

/*展示树状列表*/
$('.layer_tree_show_map_right').on('click',function () {
    $('.west-right').show();
    $('.layer_tree_hide_right').show();
    queryDeptTree(map_right,"_right");
    serviceTree(map_right,"_right");
});

$('.layer_tree_hide_right i').on('click',function () {
    $('.west-right').hide();
    $('.layer_tree_hide_right').hide();
});
function gisQuit() {
    $("#map").show();
    $("#map-left").hide();
    $("#map-right").hide();
}
