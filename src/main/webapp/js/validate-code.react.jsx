/**
 * Created by wangzunhui on 2015/10/22.
 */

var ValidateCode = React.createClass({
    render: function() {
        //var show = this.showValidateCode();
        //if (show){
            $("#" + this.props.element).parent().attr('style', 'display:block');
            return(
                <p>
                <img id="validate_code" src="/vdcode" width="100" height="37" onClick={this.refreshValidateCode}/>
                </p>
            );
        //}
        //else{
            //$("#" + this.props.element).parent().attr('style', 'display:none');
            //return <p></p>;
        //}
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

/* Slide class */
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

/* query class */
var Option = React.createClass({
    render: function () {
        var selectedStyle = {color: 'black', padding: "5px 10px 5px 10px"};
        if (this.props.option.id == this.props.server.id){
            selectedStyle = {color: 'white', backgroundColor: '#0e90d2', padding: "5px 10px 5px 10px"};
        }
        var id = this.props.name+ "_" + this.props.option.id;
        var uri = this.getUri()
        console.log(id + " -> " + uri);
        return (
            <span>
                <a id={id} style={selectedStyle}  href={uri} >{this.props.option.name}</a>
                &nbsp;&nbsp;
            </span>
        );
    },
    getUri : function(){
        if (this.props.method == 'low-high'){
            return this.getLowAndHighUri();
        }
        else{
            return this.getSelectUri();
        }
    },
    getSelectUri: function(){
        var option = this.props.option;
        var uri = this.props.server.uri;
        if (option.value != 'all') {
            return uri + "&" + this.props.name + "=" + option.value;
        }
        else{
            return uri;
        }
    },
    getLowAndHighUri: function(){
        var option = this.props.option;
        var uri = this.props.server.uri;
        if (option.low != null) {
            uri += "&low_" + this.props.name + "=" + option.low;
        }
        if (option.high != null) {
            uri += "&high_" + this.props.name + "=" + option.high;
        }
        return uri;
    }
});

var LowAndHighInput = React.createClass({
    render: function () {
        var lowId = this.props.name + "_low_text";
        var highId = this.props.name + "_high_text";
        return (
            <span>
                <input type="text" id={lowId} style={{width: this.props.input.width}} defaultValue={this.props.server.low}/> -
                <input type="text" id={highId} style={{width: this.props.input.width}} defaultValue={this.props.server.high}/>&nbsp;&nbsp;
                <input type="button" value="确定" onClick={this.submit}/>
            </span>
        );
    },

    submit: function(){
        var uri = this.props.server.uri;
        var name = this.props.name;
        var l = $('#' + name +"_low_text").val();
        var h = $('#' + name +"_high_text").val();

        l = $.trim(l);
        h = $.trim(h);

        if (l == '' && h == ''){
            alert('不能为空!');
            return;
        }

        if (l != ''){
            uri += '&low_' + name + "=" + l;
        }
        if (h != ''){
            uri += '&high_' + name + "=" + h;
        }
        uri += "&" + name + '=other';

        console.log("submit -> " + uri);
        window.location.href=uri;
    }
});

function createServerId(method, server){
    if (method == 'low-high'){
        return createLowAndHighId(server);
    }
    else{
        var lowAndHighValue = (server.low == null || server.low == '') && (server.high == null || server.high == '');
        if (lowAndHighValue){
            return createDefaultId(server);
        }
        else{
            return 'low-high-value-id';
        }
    }
}

function createDefaultId(opt){
    return opt.value == '' ? 'all' : opt.value;
}

function createLowAndHighId(opt){
    var low = opt.low;
    var high = opt.high;

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

function createOptionId(method, opt){
    if (method == 'low-high'){
       return createLowAndHighId(opt);
    }
    else{
        return createDefaultId(opt);
    }
}

var QueryFactor = React.createClass({
    render: function () {
        var name = this.props.query.name;
        var server = this.props.query.server;
        var select = this.props.query.select;
        var input = this.props.query.input;
        var serverId = createServerId(select.method, server);
        server.id = serverId;

        if (input == null){
            return (
                <div>{
                    select.options.map(function(option, id) {
                        option.id = createOptionId(select.method, option);
                        return <Option option={option}  method={select.method} server={server}  name={name} key={id}/>
                    })
                }
                </div>
            );
        }
        else if (input.type == 'low-high'){
            return (
                <div>{
                    select.options.map(function(option, id) {
                        option.id = createOptionId(select.method, option);
                        return <Option option={option}  method={select.method} server={server} name={name} key={id}/>
                    })
                }
                <LowAndHighInput input={input} server={server} name={name}/>
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


