var AJAX_REQ_TYPE = "POST"
var AJAX_REQ_URL = "queryjson.json";
var AJAX_REQ_DATATYPE = "json";

function ajaxCall(element, url, data, sucCallback, failCallback) {
	jQuery.ajax( {
		type :AJAX_REQ_TYPE,
		url : url,
		cache :false,
		data :data,
		async:true,
		dataType :AJAX_REQ_DATATYPE,
		beforeSend: function(XMLHttpRequest){
			changeToBusy(element);
		},
		success: function(data){
			if(data.success) {
				if(data.msg){
					$.messager.alert("系统提示", data.msg);
				}
				sucCallback(data);
			}else{
				$.messager.alert("系统提示", data.msg);
				if(failCallback){
					failCallback(data);
				}
			}
		},
		complete: function(XMLHttpRequest, textStatus){
			changeToArrow(element);
		},
		error: function(){
			$.messager.alert("系统提示", "服务器连接失败,请稍候再试!");
			changeToArrow(element);
		}
	});
}
function ajaxCallNoMsg(element, url, data, sucCallback, failCallback) {
	jQuery.ajax( {
		type :AJAX_REQ_TYPE,
		url :url,
		cache :false,
		data :data,
		async:true,
		dataType :AJAX_REQ_DATATYPE,
		beforeSend: function(XMLHttpRequest){
			changeToBusy(element);
		},
		success: function(data){
			if(data.success) {
				sucCallback(data);
			}else{
				if(failCallback){
					failCallback(data);
				}
			}
		},
		complete: function(XMLHttpRequest, textStatus){
			changeToArrow(element);
		},
		error: function(){
			$.messager.alert("系统提示", "服务器连接失败,请稍候再试!");
			changeToArrow(element);
		}
	});
}
function ajaxCallAsync(element, data, sucCallback) {
	var ackFlag = false;
	jQuery.ajax( {
		type :AJAX_REQ_TYPE,
		url :AJAX_REQ_URL,
		cache :false,
		data :data,
		async:false,
		dataType :AJAX_REQ_DATATYPE,
		success: function(data){
			if(data.HeadInfo.Code=="0") {
				ackFlag = true;
				if(data.HeadInfo.Message != ""){
					alert(data.HeadInfo.Message);
				}
				sucCallback(data.DataInfo);
			}else{
				alert(data.HeadInfo.Message);
			}
		},
		error: function(){
			alert("服务器连接失败,请稍候再试!");
			changeToArrow(element);
		}
	});
	return ackFlag;
}
function ajaxCallAsyncNoMsg(element, data, sucCallback) {
	var ackFlag = false;
	jQuery.ajax( {
		type :AJAX_REQ_TYPE,
		url :AJAX_REQ_URL,
		cache :false,
		data :data,
		async:false,
		dataType :AJAX_REQ_DATATYPE,
		success: function(data){
			if(data.HeadInfo.Code=="0") {
				ackFlag = true;
				sucCallback(data.DataInfo);
			}else{
				alert(data.HeadInfo.Message);
			}
		},
		error: function(){
			alert("服务器连接失败,请稍候再试!");
			changeToArrow(element);
		}
	});
	return ackFlag;
}
function isNumber(str){
	if(str.match(/^\d+$/) != null)
		return true;
	else
		return false;
}

function isDoubleNumber(str){
	if(str.match(/^(-)?\d+(\.\d+)?$/) != null)
		return true;
	else
		return false;
}

function isFormatDate(str){
	if(str.match(/^\d{4}-\d{2}-\d{2}$/) != null)
		return true;
	else
		return false;
}
function requestHashToArray(param){
	if(param.indexOf("#")==0){
		param = param.substr(1);
	}
	var result = param.split("#");
	return result
}

function changeToBusy(element) {
	if(!element){
		element = $("body")
	}
	var width = $(element).width();
	var height = $(element).height();
	var left = $(element).offset().left;
	var top = $(element).offset().top;

	var id = "_____BusyWindow";
	var divHtml = "<div id=\""+id+"\" style=\"position: absolute; z-index: 10000; font-size: 10pt; font-weight:bold; background-color: silver; filter: Alpha(opacity = 40); opacity: 0.95; -moz-opacity: 0.3; text-align: center; color: red; overflow: hidden;\">";
	divHtml = divHtml +	"<div id=\"timeSpan\" style=\"margin:"+height/2+"px;\">";
	divHtml = divHtml +	"<img src = \""+getContextPath() + "/img/denglu/loading.gif\">"; 
	divHtml = divHtml +	"</div>";
	divHtml = divHtml +	"</div>";
	$(element).prepend(divHtml);
	$(element).children("#_____BusyWindow").width(width);
	$(element).children("#_____BusyWindow").height(height);
	$(element).children("#_____BusyWindow").css("left",left);
	$(element).children("#_____BusyWindow").css("top",top);
	$("#"+id).focus();
}

function changeToBusy2(element){
	if(!element){
		element = $("body")
	}
	var divHtml = "<div id='_____BusyWindow' style=\"position:absolute;z-index:1000; margin-top:30px; top:0px;left:0px;width:100%;height:100%;background:#DDDDDB ;text-align:center;padding-top: 20%;\"><h1><image src='"+getContextPath() + "/img/denglu/loading.gif'/></h1></div>";
	$(element).prepend(divHtml);
}

function changeToClose(){
	$("#_____BusyWindow").fadeOut("normal",function(){
		$(this).remove();
	});
}

function changeToArrow(element) {
	if(!element){
		element = $("body")
	}
	if($(element).children("#_____BusyWindow")){
		$(element).children("#_____BusyWindow").remove();
	}
}
function getContextPath(){
	var path = document.location.pathname;
	var pos = path.indexOf("/",1);
	return path.substring(0,pos);
}

function ajaxCallAsyncNoMsgWithErr(element, data, sucCallback,errCallBack,url) {
	var ackFlag = false;
	jQuery.ajax( {
		type :AJAX_REQ_TYPE,
		url :url,
		cache :false,
		data :data,
		async:false,
		dataType :AJAX_REQ_DATATYPE,
		success: function(data){
			if(data.HeadInfo.Code=="0") {
				ackFlag = true;
				sucCallback(data.DataInfo);
			}else{  
				alert(data.HeadInfo.Message);
				errCallBack();
			}
		},
		error: function(){
			alert("服务器连接失败,请稍候再试!");
			changeToArrow(element);
		}
	});
	return ackFlag;
}

function ajaxCallAsyncShZz(element, data, sucCallback,url) {
	var ackFlag = false;
	jQuery.ajax( {
		type :AJAX_REQ_TYPE,
		url :url,
		cache :false,
		data :data,
		async:false,
		dataType :AJAX_REQ_DATATYPE,
		success: function(data){
			if(data.HeadInfo.Code=="0") {
				ackFlag = true;
				if(data.HeadInfo.Message != ""){
					alert(data.HeadInfo.Message);
				}
				sucCallback(data.DataInfo);
			}else{
				alert(data.HeadInfo.Message);
			}
		},
		error: function(){
			alert("服务器连接失败,请稍候再试!");
			changeToArrow(element);
		}
	});
	return ackFlag;
}

function ajaxCallShZz(element, data, sucCallback,url) {
	jQuery.ajax( {
		type :AJAX_REQ_TYPE,
		url :url,
		cache :false,
		data :data,
		async:true,
		dataType :AJAX_REQ_DATATYPE,
		beforeSend: function(XMLHttpRequest){
			changeToBusy(element);
		},
		success: function(data){
			if(data.HeadInfo.Code=="0") {
				if(data.HeadInfo.Message != ""){
					alert(data.HeadInfo.Message);
				}
				sucCallback(data.DataInfo);
			}else{
				alert(data.HeadInfo.Message);
				if($("a[sId='j_change_check_image']"))
				    $("a[sId='j_change_check_image']").click();
			}
		},
		complete: function(XMLHttpRequest, textStatus){
			changeToArrow(element);
		},
		error: function(){
			alert("服务器连接失败,请稍候再试!");
			changeToArrow(element);
		}
	});
}

function ajaxCallNoMsgShZz(element, data, sucCallback,url) {
	jQuery.ajax( {
		type :AJAX_REQ_TYPE,
		url :url,
		cache :false,
		data :data,
		async:true,
		dataType :AJAX_REQ_DATATYPE,
		beforeSend: function(XMLHttpRequest){
			changeToBusy(element);
		},
		success: function(data){
			if(data.HeadInfo.Code=="0") {
				sucCallback(data.DataInfo);
			}else{
				alert(data.HeadInfo.Message);
			}
		},
		complete: function(XMLHttpRequest, textStatus){
			changeToArrow(element);
		},
		error: function(){
			alert("服务器连接失败,请稍候再试!");
			changeToArrow(element);
		}
	});
}
function ajaxCallAsyncNoMsgShZz(element, data, sucCallback,url) {
	var ackFlag = false;
	jQuery.ajax( {
		type :AJAX_REQ_TYPE,
		url :url,
		cache :false,
		data :data,
		async:false,
		dataType :AJAX_REQ_DATATYPE,
		success: function(data){
			if(data.HeadInfo.Code=="0") {
				ackFlag = true;
				sucCallback(data.DataInfo);
			}else{
				alert(data.HeadInfo.Message);
			}
		},
		error: function(){
			alert("服务器连接失败,请稍候再试!");
			changeToArrow(element);
		}
	});
	return ackFlag;
}

function ajaxCallShZz(element, data, sucCallback,url) {
	jQuery.ajax( {
		type :AJAX_REQ_TYPE,
		url :url,
		cache :false,
		data :data,
		async:true,
		dataType :AJAX_REQ_DATATYPE,
		beforeSend: function(XMLHttpRequest){
			changeToBusy(element);
		},
		success: function(data){
			if(data.HeadInfo.Code=="0") {
				if(data.HeadInfo.Message != ""){
					alert(data.HeadInfo.Message);
				}
				sucCallback(data.DataInfo);
			}else{
				alert(data.HeadInfo.Message);
				if($("a[sId='j_change_check_image']"))
				    $("a[sId='j_change_check_image']").click();
			}
		},
		complete: function(XMLHttpRequest, textStatus){
			changeToArrow(element);
		},
		error: function(){
			alert("服务器连接失败,请稍候再试!");
			changeToArrow(element);
		}
	});
}
/**
 * 从数组对象中找到对应的值
 * @param array 数组对象
 * @param idField key
 * @param valueField value
 * @param id 要找到的key
 */
function findValueFromArrayObjectById(array, id, idField, valueField){
	if(array){
		if(!idField){
			idField = "value";
		}
		if(!valueField){
			valueField = "text";
		}
		for(var i=0; i<array.length; i++){
			var temp = array[i];
			if(temp[idField] == id){
				return temp[valueField];
			}
		}
	}
	return id;
}

/**
 * 关闭窗口
 */
function closeWindow(windowId){
	$("#" + windowId).window("close");
}
/**
 * 打开窗口
 */
function openWindow(windowId){
	$("#" + windowId).window("open");
}
/**
 * 格式化金额
 * @param num
 * @returns {String}
 */
function formatCurrency(num) {
    num = num.toString().replace(/\$|\,/g,'');
    if(isNaN(num))
    num = "0";
    sign = (num == (num = Math.abs(num)));
    num = Math.floor(num*100+0.50000000001);
    cents = num%100;
    num = Math.floor(num/100).toString();
    if(cents<10)
    cents = "0" + cents;
    for (var i = 0; i < Math.floor((num.length-(1+i))/3); i++)
    num = num.substring(0,num.length-(4*i+3))+','+
    num.substring(num.length-(4*i+3));
    return (((sign)?'':'-') + num + '.' + cents);
}


/**
 * 获取存储在本地的数据
 */
function getLocalStorage(){
	if(!window.localStorage){
		$('body').popup('当前环境不支持本地存储！');
		return;
	}
	return localStorage.getItem("productInCart");
}

$.fn.extend({
    resizeDataGrid: function (heightMargin, widthMargin, minHeight, minWidth) {
        var height = $(document.body).height() - heightMargin;
        var width = $(document.body).width() - widthMargin;
        height = height < minHeight ? minHeight : height;
        width = width < minWidth ? minWidth : width;
        $(this).datagrid('resize', {
            height: height,
            width: width
        });
    }
});