/**
 * Created by wangzunhui on 2015/11/28.
 */

var InvestmentIcon = React.createClass({
    render: function() {
        return <p className="xh_0_"><img src={this.props.img} /></p>
    }
});

var InvestmentTitle = React.createClass({
    render: function() {

        return <div className="xh_01a_">{this.props.title}</div>
    }
});

var InvestmentField = React.createClass({
    render: function() {
        var c1Style = {
            fontSize: 18
        };

        var c2Style = {
            color: '#848587',
            fontSize:18
        };

        return(
            <div className="xrb_a21_">
                <p style={c1Style}>{this.props.data}</p>
                <p style={c2Style}>{this.props.name}</p>
            </div>
        );
    }
});

var InvestmentProgress = React.createClass({
    render: function() {
        var outStyle = {
            background: '#dcdcdc',
            verflow: 'hidden',
            height: '16px',
            lineHeight: '16px',
            display: 'block',
            float: 'left',
            width: '100px',
            borderRadius: '4px'
        };

        var inStyle = {
            height: '16px',
            lineHeight: '16px',
            display: 'block',
            float: 'left',
            background: '#eb953a',
            borderRadius: '4px'
        };
        inStyle.width = this.props.progress;

        var c1Style = {
            marginTop:'12px',
            height: '24px',
            fontSize:18
        };

        var c2Style = {
            color: '#848587',
            marginTop:'3px',
            fontSize:18
        };
        return (
            <div className="xrb_a21_">
                <p style={c1Style}><span style={outStyle}><span style={inStyle}></span></span></p>
                <p style={c2Style}>已投{this.props.progress}</p>
            </div>
        );
    }
});

var InvestmentBuy = React.createClass({
    render: function() {
        var c1Style ={
            paddingTop:'20px',
            textIndent:'2em',
            color:'#292929'
        };

        var c2Style = {
            paddingTop:'2px',
            color: '#848587',
            float:'left',
            width:'112px',
            height:'30px'
        };

        var c3Style = {
            paddingRight:'24px',
            float:'right'
        };

        if (!this.props.buy){
            return (<div className="xrb_a20_0"></div>);
        }
        else {
            return (
                <div className="xrb_a21">
                    <p style={c1Style}>333，600（剩余金额）</p>
                    <div className="xrb_a20_1">
                        <input type="text"  style={c2Style}/>
                        <a style={c3Style} href="">立即抢购</a>
                    </div>
                </div>
            );
        }
    }
});

var Investment = React.createClass({
    render: function() {

        return (
            <div className="xh_0">
                <InvestmentIcon  img = {this.props.invest.img}/>
                <div className="xh_01">
                    <InvestmentTitle title={this.props.invest.title} />
                    <div className="xrb_a2">
                        <InvestmentField name="年化收益率" data={this.props.invest.rate}/>
                        <InvestmentField name="投资期限" data={this.props.invest.deadline}/>
                        <InvestmentField name="标的总额" data={this.props.invest.total}/>
                        <InvestmentProgress progress={this.props.invest.progress}/>
                        <InvestmentBuy buy={this.props.invest.buy}/>
                    </div>
                </div>
            </div>
        );
    }
});


var Investments = React.createClass({
    render: function () {
        return (
          <div>
              {this.props.invests.map(function(invest, id) {
                  return <Investment invest={invest} key={id}/>;
              })}
          </div>
        );
    }
});


function react_investment_render(elementName, data){
    ReactDOM.render(
        <Investments invests={data}/>,
        document.getElementById(elementName)
    );
}