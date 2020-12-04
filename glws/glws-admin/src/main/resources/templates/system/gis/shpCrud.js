var url = "http://192.168.1.109:8084/geoserver/wfs";
var featureNS = "http://testshape";
var featurePrefix = "testshape";
var featureType = "cc";
var type = "POST";

/**
 * 添加shp区域
 * @param point
 */
function addRegion(Feature) {
    var newFeature = new ol.Feature({
        the_geom: new ol.geom.MultiPolygon([Feature]),
        '目': "李仁东测试",
        code: "121233",
        color: "red"
    });
    var WFSTSerializer = new ol.format.WFS();
    var featObject = WFSTSerializer.writeTransaction([newFeature],
        null, null, {
            featureNS: featureNS,
            featurePrefix: featurePrefix,//工作空间名称
            featureType: featureType,//图层名称
        });
    var serializer = new XMLSerializer();
    var featString = serializer.serializeToString(featObject);
    $.ajax({
        url: url,
        type: type,
        data: featString,
        contentType: 'text/xml',
        success: function (req) {
            var xmlDoc = $.parseXML(req);
            var type = $(xmlDoc).find("wfs\\:totalInserted").text();
            if (type == 1) {
                addGisMap(0, 2);
            }
        }
    });
}

/**
 * 根据区域fid删除区域
 * @param featureId
 */
function delRegion(featureId) {
    var format = new ol.format.WFS();
    var xml = format.writeTransaction(null, null, [featureId], {
        featureNS: featureNS,
        featurePrefix: featurePrefix,//工作空间名称
        featureType: featureType//图层名称
    });
    var serializer = new XMLSerializer();
    var featString = serializer.serializeToString(xml);

    $.ajax({
        url: url,
        type: type,
        data: featString,
        contentType: 'text/xml',
        success: function (req) {
            var xmlDoc = $.parseXML(req);
            var type = $(xmlDoc).find("wfs\\:totalDeleted").text();
            if (type == 1) {
            }
        }
    });
}
/**
 * 重新编辑区域
 * @param modifyFeatures
 */
function updateRegion(modifyFeatures) {
    if (modifyFeatures && modifyFeatures.getLength() > 0) {
        var modifyFeature = modifyFeatures.item(0).clone();
        modifyFeature.setId(modifyFeatures.item(0).getId());
        modifyFeature.setGeometryName("the_geom");
        var format = new ol.format.WFS();
        var xml = format.writeTransaction(null, [modifyFeature], null, {
            featureNS: featureNS,
            featurePrefix: featurePrefix,//工作空间名称
            featureType: featureType//图层名称
        });
        var serializer = new XMLSerializer();
        var featString = serializer.serializeToString(xml);
        $.ajax({
            url: url,
            type: type,
            data: featString,
            contentType: 'text/xml',
            success: function (req) {
                var xmlDoc = $.parseXML(req);
                var type = $(xmlDoc).find("wfs\\:totalUpdated").text();
                if (type == 1) {
                }
            }
        });
    }
}
