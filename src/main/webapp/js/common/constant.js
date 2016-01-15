// 常量js
var customType = {
	'01' : '老人端',
	'02' : '亲属端',
	'03' : '急救车',
	'04' : '120中心'
}

function setOption(element, data, defaultValue, defaultName) {
	if (defaultValue || defaultName) {
		element.append("<option value='" + defaultValue + "'>" + defaultName + "</option>");
	}
	for (var key in data) {
		element.append("<option value='" + key + "'>" + data[key] + "</option>");
	}
}

function getValueByKey(key, data) {
	for (var o in data) {
		if (key == o) {
			return data[o];
		}
	}
	return "";
}