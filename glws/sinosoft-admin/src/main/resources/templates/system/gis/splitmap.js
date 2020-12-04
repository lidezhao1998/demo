//放大联动
this._activeMapEventHandlers.push(this.activeMap.on("zoom-end", function (evt) {
    self._maps.forEach(function (map) {
        if (map != self.activeMap) {
            map.setExtent(evt.extent);
        }
    });
}));
//平移联动
this._activeMapEventHandlers.push(this.activeMap.on("pan-end", function (evt) {
    self._maps.forEach(function (map) {
        if (map != self.activeMap) {
            map.setExtent(evt.extent);
        }
    });
}));

//鼠标联动
this._activeMapEventHandlers.push(this.activeMap.on("mouse-move", function (evt) {
    self._maps.forEach(function (map) {
        var idx = self._maps.indexOf(map);
        var graphicLayer = map.getLayer("layer")
        var graphic = self._mouseGraphics[idx];
        if (map != self.activeMap) {
            graphicLayer.show();
            graphic.setGeometry(evt.mapPoint);
        } else {
            graphicLayer.hide();
        }
    });
}));