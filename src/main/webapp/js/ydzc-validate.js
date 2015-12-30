function trim(_s) { return _s.replace(/^\s+|\s+$/, ""); }
function isMobile(v) { var reg = /^1[3|4|5|7|8][\d]{9}$/; return (v.match(reg) == null) ? false : true; }
function isYearRate(v) { var reg = /(^\d{1,2}|(\.\d)){1}$/; return (v.match(reg) == null) ? false : true; }
function isValideCode(v) { var reg = /\W/; return (v.match(reg) == null) ? true : false; }
function isNotNull(v) { return trim(v) == "" ? false : true; }

var validate = [
    {
        name : 'isMobile',
        errorMsg: '手机号码不正确'
    },
    {
        name : 'isNotNull',
        errorMsg: '不能为空'
    },
    {
        name : 'isYearRate',
        errorMsg: '非法年化利率'
    }
];

//验证密码
function checkPassword(id) {
    //console.log($('#' + id).val());

    if ($('#' + id).val() == ""){
        showErrorMsg(id, "密码不能为空");
        return  false;
    }else if (($('#' + id).val().length < 6 || $('#' + id).val().length > 16)){
        showErrorMsg(id, "密码为6~16位字符");
        return false;
    }else {
        var reg = /^((?=.*[0-9])(?=.*[a-zA-Z!@#$%^&*()_+|{}?><\-\]\\[\/]).{6,16})|((?=.*[a-zA-Z])(?=.*[0-9!@#$%^&*()_+|{}?><\-\]\\[\/]).{6,16})|((?=.*[0-9a-zA-Z])(?=.*[!@#$%^&*()_+|{}?><\-\]\\[\/]).{6,16})$/;
        result = ($('#' + id)).val().match(reg) == null ? false : true;
        if (!result) {
            showErrorMsg(id, "6~16位字符，至少包含数字、字母（区分大小写）、符号中的2种");
            return false;
        }
    }

    hideErrorMsg(id);
    return true;
}

//再次验证密码
function comparePassword(v1, v2) {
    if ($('#' + v1).val() != $('#' + v2).val()){
        showErrorMsg(v2, "两次输入的密码不同");
        return false;
    }
    hideErrorMsg(v2);
    return true;
}

//检查验证码
function checkValideCode(id) {
    if ($('#' + id).val().length != 4){
        showErrorMsg(id, "验证码为4位字符");
        return false;
    }
    else if (!isValideCode($('#' + id).val())) {
        showErrorMsg(id, "不正确的验证码");
        return false;
    }

    hideErrorMsg(id);
    return true;
}

function checkMobile(id){
    var json = callAjax('/ajax/checkMobile', {mobile: $('#' + id).val()});
    if (json == null){
        showErrorMsg(id, "检查失败！");
        return false;
    }

    if (json.resultCode == 'OK'){
        showErrorMsg(id, "手机已注册");
        return false;
    }

    hideErrorMsg(id);
    return true;
}

function showErrorMsg(id, msg){
    $('#span_errmsg_' + id).show();
    $('#span_errmsg_' + id).text(msg);
}

function hideErrorMsg(id){
    $('#span_errmsg_' + id).hide();
    $('#span_errmsg_' + id).text('');
}

function check(k, v, id){
    var r = false;
    for(var i = 0; i < validate.length; i++){
        if (validate[i].name == k){
            if (k.substring(0, 2) == 'is'){
                r = eval(k + "('" + $('#' + id).val() + "')");
            }
            else{
                r = eval(k + "('" + $('#' + id).val() + "', '" + v + "')");
            }

            if (!r){
                showErrorMsg(id, validate[i].errorMsg);
                return false;
            }

            hideErrorMsg(id);
            return true;
        }
    }

    return r;
}

function checkValue(id){
    var r = false;
    var data = $('#' + id).data('validate');
    var a = data.split(';');

    for (var j = 0; j < a.length; j++) {
        var tmp = trim(a[j]);
        if (tmp == "")
            continue;

        var kv = tmp.split("=");
        if (kv.length != 2)
            continue;

        var k = trim(kv[0]);
        var v = trim(kv[1]);

        if (k == 'function'){
            r = eval(v + "('" + id + "')");
        }
        else{
            r = check(k, v, id);
        }

        if (!r) return false;
    }

    return true;
}
