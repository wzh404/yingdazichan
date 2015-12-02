var console = console || {
        log : function(){
            return false;
        }
    }

function callAjax(url, data){
    var json = null;

    $.ajax({
        url: url,
        data: data,
        type: "get",
        dataType: "json",
        async: false,
        success: function success(jsonData) {
            console.log(jsonData.resultCode);
            json = jsonData;
        },
        error: function error(XMLHttpRequest, textStatus, errorThrown) {
            console.log(textStatus + ": " + XMLHttpRequest.status);
        }
    });

    return json;
}