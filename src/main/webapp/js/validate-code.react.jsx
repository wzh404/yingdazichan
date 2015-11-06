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

function createId(params, value, low, high){
    if (params == 2){
        var id = '';
        if (low != null && low != ''){
            id += low + "L";
        }
        if (high != null && high != ''){
            id += high + "H";
        }
        if (id == ''){
            id = 'all';
        }

        return id;
    }
    else{
        return value == '' ? 'all' : value;
    }
}

var Factor = React.createClass({
    render: function () {
        var selectedStyle = {};
        if (this.getSelected()){
            selectedStyle = {color: 'white',backgroundColor: 'red'};;
        }
        var id = this.getId();
        var uri = this.getUri()
        console.log(id + " -> " + uri);
        return (
            <span>
                <a id={id} style={selectedStyle} href={uri} >{this.props.factor.key}</a>
                &nbsp;&nbsp;
            </span>
        );
    },
    getUri : function(){
        if (this.props.params == 2){
            return this.getLowAndHighUri();
        }
        else{
            return this.getSelectUri();
        }
    },
    getSelectUri: function(){
        var factor = this.props.factor;
        var uri = this.props.uri;
        if (factor.value != 'all') {
            return uri + "&" + this.props.name + "=" + factor.value;
        }
        else{
            return uri;
        }
    },
    getLowAndHighUri: function(){
        var factor = this.props.factor;
        var uri = this.props.uri;
        if (factor.low != null) {
            uri += "&low_" + this.props.name + "=" + factor.low;
        }
        if (factor.high != null) {
            uri += "&high_" + this.props.name + "=" + factor.high;;
        }
        return uri;
    },
    getSelected: function(){
        if (this.props.params == 2){
            var id = createId(this.props.params, '', this.props.factor.low, this.props.factor.high);
            console.log(this.props.value + "---selected id ->" +  id);
            return this.props.value == id;
        }
        else{
            return this.props.value == this.props.factor.value;
        }
    },
    getId: function(){
        return this.props.name + "_" + createId(this.props.params, this.props.value, this.props.factor.low, this.props.factor.high);
   }
});

var LowAndHighFactor = React.createClass({
    render: function () {
        var lowId = this.props.name + "_low_text";
        var highId = this.props.name + "_high_text";
        return (
            <span>
                <input type="text" id={lowId} style={{width: this.props.width}} defaultValue={this.props.low}/> -
                <input type="text" id={highId} style={{width: this.props.width}} defaultValue={this.props.high}/>&nbsp;&nbsp;
                <input type="button" value="确定" onClick={this.submit}/>
            </span>
        );
    },

    submit: function(){
        var uri = this.props.uri;
        var name = this.props.name;
        var l = $('#' + name +"_low_text").val();
        var h = $('#' + name +"_high_text").val();

        l = $.trim(l);
        h = $.trim(h);

        if (l == '' && h == ''){
            alert('不能为空!')
            return;
        }

        if (l != ''){
            uri += '&low_' + name + "=" + l;
        }
        if (h != ''){
            uri += '&high_' + name + "=" + h;
        }

        console.log("submit -> " + uri);
        window.location.href=uri;
    }
});

var QueryFactor = React.createClass({
    render: function () {
        var name = this.props.query.name;
        var uri = this.props.query.uri;
        var options = this.props.query.options;
        var type = this.props.query.type;
        var input = this.props.query.input;
        var params = this.props.query.params;

        var value = createId(params, this.props.query.value, this.props.query.low, this.props.query.high);
        if (input == 'no'){
            return (
                <div>{
                    options.map(function(factor, id) {
                        return <Factor factor={factor} params={params} uri={uri}  value={value} name={name}  key={id}/>
                    })
                }
                </div>
            );
        }
        else if (input == 'low_high'){
            var low = this.props.query.low;
            var high = this.props.query.high;
            var width = this.props.query.width;
            return (
                <div>{
                    options.map(function(factor, id) {
                        return <Factor factor={factor} params={params} uri={uri}  value={value} name={name}  key={id}/>
                    })
                }
                <LowAndHighFactor width={width} type={type} value={value} name={name} uri={uri} low={low} high={high}/>
                </div>
            );
        }
    }
});

function react_slides_render(elementName){
    var slides
    $.ajax({
        url: "/cache/slide",
        data: {version: "0.1.3"},
        type: "get",
        dataType: "json",
        async: false,
        success: function (data) {
            slides = data;
        },
        error: function(xhr, status, err) {
            alert(status + ": " + xhr.status);
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



function test_factor(el, query){
    ReactDOM.render(
        <QueryFactor query={query}/>,
        document.getElementById(el)
    );
}


