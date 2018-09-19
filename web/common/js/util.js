var MyUtil = {};


/**
 * getContext
 * @return {String}
 */
MyUtil.getContext = function () {
    url = self.location.href;
    tmp = url;
    ind = tmp.indexOf("//");
    tmp = tmp.substring(ind + 2);
    ind = tmp.indexOf("/");
    serverAddress = tmp.substring(0, ind);
    tmp = tmp.substring(ind + 1);
    ind = tmp.indexOf("/");
    context = tmp.substring(0, ind);
    var returnVal = "http://" + serverAddress + "/";
    // if (!context || context == ""){
    //    returnVal += context + "/";
    // }
    return returnVal;
};

/**
 * getContextURI
 * @return {String}
 */
MyUtil.getContextURI = function () {
    url = self.location.href;
    tmp = url;
    ind = tmp.indexOf("//");
    tmp = tmp.substring(ind + 2);
    ind = tmp.indexOf("/");
    serverAddress = tmp.substring(0, ind);
    tmp = tmp.substring(ind + 1);
    ind = tmp.indexOf("/");
    context = tmp.substring(0, ind);
    if (!context || context == "")
        return "";
    return "/" + context;
};

/**
 * 获取request的query parameters
 * @param {} paramName
 * @return {}
 */
MyUtil.getRequestParams = function (paramName) {
    var args = new Object();
    var query = location.search.substring(1); // Get query string
    var pairs = query.split("&"); // Break at ampersand
    for (var i = 0; i < pairs.length; i++) {
        var pos = pairs[i].indexOf('='); // Look for "name=value"
        if (pos == -1)
            continue; // If not found, skip
        var argname = pairs[i].substring(0, pos); // Extract the name
        var value = pairs[i].substring(pos + 1); // Extract the value
        value = decodeURIComponent(value); // Decode it, if needed
        args[argname] = value; // Store as a property
    }
    return args[paramName]; // Return the object
};

/**
 * 将对象的属性转换为URL的请求参数
 *
 * @param params - 参数,JSON对象
 * @return 返回请求参数
 */
MyUtil.appendRequestParams = function (params) {
    if (!Ext.isEmpty(params)) {
        var param = "";
        for (key in params) {
            if (!Ext.isEmpty(key) && Ext.isPrimitive(params[key])) {
                param += ("&" + key + "=" + params[key]);
            }
        }
        if (param.charAt(0) == "&") param = param.substring(1);
        //param = Ext.urlEncode(params);
        return param;
    }
    return "";
};

/**
 * 获得URL的全路径
 *
 * @param {} url 相对或绝对路径
 * @return {} 返回全路径
 */
MyUtil.getAbstractURL = function (url) {
    if (url && url.trim().indexOf("http://") != 0) {
        return MyUtil.getContext() + url;
    }
    return url;
};
/**
 * 求两个时间的天数差 日期格式为 YYYY-MM-dd
 */
MyUtil.daysBetween = function (DateOne, DateTwo) {
    var OneMonth = DateOne.substring(5, DateOne.lastIndexOf('-'));
    var OneDay = DateOne.substring(DateOne.length, DateOne.lastIndexOf('-') + 1);
    var OneYear = DateOne.substring(0, DateOne.indexOf('-'));
    var TwoMonth = DateTwo.substring(5, DateTwo.lastIndexOf('-'));
    var TwoDay = DateTwo.substring(DateTwo.length, DateTwo.lastIndexOf('-') + 1);
    var TwoYear = DateTwo.substring(0, DateTwo.indexOf('-'));

    var cha = ((Date.parse(OneMonth + '/' + OneDay + '/' + OneYear) - Date.parse(TwoMonth + '/' + TwoDay + '/' + TwoYear)) / 86400000);
    return cha;
};
//---------------------------------------------------  
//日期格式化  
//格式 YYYY/yyyy/YY/yy 表示年份  
//MM/M 月份  
//W/w 星期  
//dd/DD/d/D 日期  
//hh/HH/h/H 时间  
//mm/m 分钟  
//ss/SS/s/S 秒  
//---------------------------------------------------
MyUtil.DateFormat = function (formatStr) {
    var _thisDate = new Date();
    var str = formatStr;
    var Week = ['日', '一', '二', '三', '四', '五', '六'];
    str = str.replace(/yyyy|YYYY/, _thisDate.getFullYear());
    str = str.replace(/yy|YY/, (_thisDate.getYear() % 100) > 9 ? (_thisDate.getYear() % 100).toString() : '0' + (_thisDate.getYear() % 100));
    str = str.replace(/MM/, _thisDate.getMonth() + 1 > 9 ? (_thisDate.getMonth() + 1) : '0' + (_thisDate.getMonth() + 1));
    str = str.replace(/M/g, _thisDate.getMonth() + 1);
    str = str.replace(/w|W/g, Week[_thisDate.getDay()]);
    str = str.replace(/dd|DD/, _thisDate.getDate() > 9 ? _thisDate.getDate().toString() : '0' + _thisDate.getDate());
    str = str.replace(/d|D/g, _thisDate.getDate());
    str = str.replace(/hh|HH/, _thisDate.getHours() > 9 ? _thisDate.getHours().toString() : '0' + _thisDate.getHours());
    str = str.replace(/h|H/g, _thisDate.getHours());
    str = str.replace(/mm/, _thisDate.getMinutes() > 9 ? _thisDate.getMinutes().toString() : '0' + _thisDate.getMinutes());
    str = str.replace(/m/g, _thisDate.getMinutes());
    str = str.replace(/ss|SS/, _thisDate.getSeconds() > 9 ? _thisDate.getSeconds().toString() : '0' + _thisDate.getSeconds());
    str = str.replace(/s|S/g, _thisDate.getSeconds());
    return str;
};
/**
 * 根据时间获取今天、明天、过去
 * DateStr:yyyy-MM-dd HH:mm:ss
 */
MyUtil.getDateShowStr = function (DateStr) {
    var _tm = "";
    var _d = new Date();
    var _today = MyUtil.DateFormat("YYYY-MM-dd");
    var _oToday = DateStr.substring(0, 11);
    var _tHour = _d.getHours();
    var _tMinutes = _d.getMinutes();
    //获取小时
    var _oHour = parseInt(DateStr.substring(10, DateStr.indexOf(":")));
    var _oMinutes = parseInt(DateStr.substring(DateStr.indexOf(":") + 1, DateStr.lastIndexOf(":")));
    var _xcts = MyUtil.daysBetween(_oToday, _today);
    switch (_xcts) {
        case 0:
            _tm = "" + (_tHour - _oHour == 0 ? ("" + (_tMinutes - _oMinutes) + "分钟") : ("" + (_tHour - _oHour) + "小时")) + "以前";
            break;
        case -1:
            _tm = "昨天";
            break;
        case -2:
            _tm = "前天";
            break;
        default:
            _tm = DateStr.substring(0, 11);

    }
    return _tm;
};

/*
 * 获取html里的图片
 */
MyUtil.getImgSrc = function (htmlstr) {
    var reg = /<img.+?src=('|")?([^'"]+)('|")?(?:\s+|>)/gim;
    var arr = [];
    while (tem = reg.exec(htmlstr)) {
        arr.push(tem[2]);
    }
    return arr;
};
/*
 * 去掉HTML标签
 */
MyUtil.delHtmlTag = function (str) {
    return str.replace(/<[^>]+>/g, "");//去掉所有的html标记
};

MyUtil.subString = function (str, len) {
    if (str.length) {
        if (str.length > len) {
            return str.substring(0, len) + "...";
        } else {
            return str;
        }
    }
};
/*
 * map
 */

//定义map
function Map() {
    this.container = {};
}
//将key-value放入map中
Map.prototype.put = function (key, value) {
    try {
        if (key != null && key != "")
            this.container[key] = value;

    } catch (e) {
        return e;
    }
};
//根据key从map中取出对应的value
Map.prototype.get = function (key) {
    try {
        return this.container[key];

    } catch (e) {
        return e;
    }
};




