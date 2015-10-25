/**
 * Created by wangzunhui on 2015/10/22.
 */

"use strict";

var ValidateCode = React.createClass({
    displayName: "ValidateCode",

    render: function render() {
        var show = this.showValidateCode();
        //console.log(this.props.element);
        if (show) {
            return React.createElement(
                "p",
                null,
                React.createElement("img", { id: "validate_code", src: "/vdcode", onClick: this.refreshValidateCode })
            );
        } else {
            $("#" + this.props.element).attr('style', 'display:none');
            return React.createElement("p", null);
        }
    },

    refreshValidateCode: function refreshValidateCode(event) {
        $('#validate_code').attr('src', '/vdcode?' + Math.random());
    },

    showValidateCode: function showValidateCode() {
        var result = false;
        $.ajax({
            url: "/ajax/vdshow",
            data: { type: "login" },
            type: "get",
            dataType: "json",
            async: false,
            success: function success(data) {
                //console.log(data.showValidateCode);
                if (data.showValidateCode) {
                    result = true;
                }
            },
            error: function error(XMLHttpRequest, textStatus, errorThrown) {
                alert(textStatus + ": " + XMLHttpRequest.status);
            }
        });

        return result;
    }
});

function react_validate_code_render(elementName) {
    var element_id = document.getElementById(elementName);
    ReactDOM.render(React.createElement(ValidateCode, { element: elementName }), element_id);
}
