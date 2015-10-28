/**
 * Created by wangzunhui on 2015/10/22.
 */

var ValidateCode = React.createClass({
    render: function() {
        var show = this.showValidateCode();
        //console.log(this.props.element);
        if (show){
            $("#" + this.props.element).attr('style', 'display:block');
            return(
                <p>
                <img id="validate_code" src="/vdcode" width="100" height="37" onClick={this.refreshValidateCode}/>
                </p>
            );
        }
        else{
            $("#" + this.props.element).attr('style', 'display:none');
            return <p></p>;
        }
    },

    refreshValidateCode: function(event){
        $('#validate_code').attr('src', '/vdcode?' + Math.random());
    },

    showValidateCode: function(){
        var result = false;
        $.ajax({
            url: "/ajax/vdshow",
            data: {type: "login"},
            type: "get",
            dataType: "json",
            async: false,
            success: function (data) {
                console.log(data.showValidateCode);
                if (data.showValidateCode) {
                    result = true;
                }
            },
            error: function(XMLHttpRequest, textStatus, errorThrown) {
                alert(textStatus + ": " + XMLHttpRequest.status);
            }
        });

        return result;kkkkk
    }
});

function react_validate_code_render(elementName){
    var element_id = document.getElementById(elementName);
    ReactDOM.render(
        <ValidateCode element={elementName}/>,
        element_id
    );
}

