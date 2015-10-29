/**
 * Created by wangzunhui on 2015/10/22.
 */

'use strict';

var ValidateCode = React.createClass({
    displayName: 'ValidateCode',

    render: function render() {
        var show = this.showValidateCode();
        if (show) {
            $("#" + this.props.element).parent().attr('style', 'display:block');
            return React.createElement(
                'p',
                null,
                React.createElement('img', { id: 'validate_code', src: '/vdcode', width: '100', height: '37', onClick: this.refreshValidateCode })
            );
        } else {
            $("#" + this.props.element).parent().attr('style', 'display:none');
            return React.createElement('p', null);
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

var Slide = React.createClass({
    displayName: 'Slide',

    render: function render() {
        var imgUrl = 'url(' + this.props.img + ') no-repeat center top';
        var liStyle = {
            background: imgUrl
        };

        return React.createElement(
            'li',
            { style: liStyle },
            React.createElement('a', { href: this.props.url, target: '_blank' })
        );
    }
});

var Slides = React.createClass({
    displayName: 'Slides',

    render: function render() {
        return React.createElement(
            'ul',
            { id: 'slides' },
            this.props.slides.map(function (slider, id) {
                return React.createElement(Slide, { url: slider.sliderUrl, img: slider.sliderImg, key: id });
            })
        );
    }
});

function react_slides_render(elementName) {
    var slides;
    $.ajax({
        url: "/cache/slide",
        data: { version: "0.1.1" },
        type: "get",
        dataType: "json",
        async: false,
        success: function success(data) {
            slides = data;
        },
        error: function error(xhr, status, err) {
            alert(status + ": " + xhr.status);
        }
    });

    //for (var i in slides){
    //    console.log(slides[i].sliderImg);
    //}

    ReactDOM.render(React.createElement(Slides, { slides: slides }), document.getElementById(elementName));
}
