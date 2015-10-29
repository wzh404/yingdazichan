/**
 * Created by wangzunhui on 2015/10/22.
 */

var ValidateCode = React.createClass({
    render: function() {
        var show = this.showValidateCode();
        if (show){
            $("#" + this.props.element).parent().attr('style', 'display:block');
            return(
                <p>
                <img id="validate_code" src="/vdcode" width="100" height="37" onClick={this.refreshValidateCode}/>
                </p>
            );
        }
        else{
            $("#" + this.props.element).parent().attr('style', 'display:none');
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
                //console.log(data.showValidateCode);
                if (data.showValidateCode) {
                    result = true;
                }
            },
            error: function(XMLHttpRequest, textStatus, errorThrown) {
                alert(textStatus + ": " + XMLHttpRequest.status);
            }
        });

        return result;
    }
});

function react_validate_code_render(elementName){
    var element_id = document.getElementById(elementName);
    ReactDOM.render(
        <ValidateCode element={elementName}/>,
        element_id
    );
}

var Slide = React.createClass({
    render: function () {
        var imgUrl = 'url(' + this.props.img + ') no-repeat center top';
        var liStyle = {
            background: imgUrl
        };

        return(
            <li style={liStyle}><a href={this.props.url} target="_blank" ></a></li>);
    }
});

var Slides = React.createClass({
    render: function () {
        return(
            <ul id="slides">
                {this.props.slides.map(function(slider, id) {
                    return <Slide url={slider.sliderUrl} img={slider.sliderImg} key={id}/>;
                })}
            </ul>);
    }
});

function react_slides_render(elementName){
    var slides
    $.ajax({
        url: "/cache/slide",
        data: {version: "0.1.1"},
        type: "get",
        dataType: "json",
        async: false,
        success: function (data) {
            slides = data;
        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
            alert(textStatus + ": " + XMLHttpRequest.status);
        }
    });

    //for (var i in slides){
    //    console.log(slides[i].sliderImg);
    //}

    ReactDOM.render(
        <Slides slides={slides}/>,
        document.getElementById(elementName)
    );
}


