
var ids = [];
var initialPreview = [];//初始化图片加载路径
var initialPreviewConfig = [];//初始化图片加载属性
var savefiles=[];//存储上传图片内容；多个图片

/*
*
* 初始化方法
* */
$(function() {




});

//上传初始化方法id为加载标签id值
/*
*id为加载标签id值
*prefix上传、删除路径
*bloobe是否展示图片简介区域区分于编辑和查看页面
*initialPreview初始化图片加载路径
*initialPreviewConfig初始化图片加载属性
*
*
*
* */
function chush(id,prefix,bloobe,initialPreview,initialPreviewConfig,obj){
    if(IsEmpty(id)){
        id="fileinput-demo-2";
    }
    if(IsEmpty(prefix)){
        prefix=ctx + "caiji/survey";
    }
    $("#"+id).fileinput({
        'theme': 'explorer-fas',
        'uploadUrl': prefix + '/upload',
        deleteUrl:prefix+"/delete",
        overwriteInitial: false,
        uploadIcon: "<i class=\"glyphicon glyphicon-upload\"></i>",
        initialPreviewAsData: true,
        showBrowse:true,
        showPreview:true,
        uploadAsync: true,
        showCancel: false,
        showRemove:true,
        showUpload:false,
        showClose:false,
        dropZoneEnabled:false,
        showCaption:bloobe,
        allowedFileExtensions: ['jpg', 'gif', 'png', 'bmp', 'jpeg'],//指出带有哪些后缀名的文件才是被接受上传的
        previewFileIcon: '',//当文件无法被预览时出现在缩略图中的图标，默认是
        maxFileCount: 10,
        layoutTemplates: {
            actionUpload: ""
        },
        deleteExtraData: {
            files: ""
        },
        initialPreview: initialPreview,
        initialPreviewConfig: initialPreviewConfig,


    }).on("filebatchselected", function(event, files) {
        savefiles=[];
        console.log(event);
        console.log(files);
        for(var item in files) { //item遍历数组时为数组的下标，遍历对象时为对象的key值
            console.log(item); // 1, 2, 3
            var values=files[item].file;
            savefiles.push(values);
            console.log(values);
        };
        console.log("------"+savefiles);
       // alert(savefiles.length);
    }).on('filebatchuploaderror', function (event, data, msg) {
        alert("上传失败");
    }).on("filepredelete", function (event, key, jax, data) {
        return !confirm("确定删除？");
    }).on('filedeleted', function (event, key) {
        //ids 存储的id key删除图片id
        //删除图片
        removearry(ids, key)
        //删除图片路径
        removearryUrl(initialPreview,initialPreviewConfig, key)
        alert("删除成功");

        obj.value = ids;
    });
}

//取值在数组中的下标
function indexOfArry(arry, val) {
    for (var i = 0; i < arry.length; i++) {
        if (arry[i] == val) return i;
    }
    return -1;
};
function indexOfArry1(arry, val) {
    for (var i = 0; i < arry.length; i++) {
        if (arry[i].key== val) return i;
    }
    return -1;
};

//删除数组中固定的值
function removearry(arry, val) {
    var index = indexOfArry(arry, val);
    if (index > -1) {
        arry.splice(index, 1);
    }
};

function removearryUrl(arry1,arry2 ,val) {
    var index = indexOfArry1(arry2, val);
    if (index > -1) {
        arry1.splice(index, 1);
        arry2.splice(index, 1);
    }
};
