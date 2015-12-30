/**
 * Created by wangzunhui on 2015/11/28.
 */
function formatNum(num, n) {
    num = String(num.toFixed(n));

    var re = /(-?\d+)(\d{3})/;
    while (re.test(num)) num = num.replace(re, "$1,$2");

    return num;
}

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
                <p style={c1Style}>{this.props.data} {this.props.unit}</p>
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
                <p style={c2Style}>已投{this.props.progress}%</p>
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
            var amount = formatNum(this.props.remain, 0);
            return (
                <div className="xrb_a21">
                    <p style={c1Style}>{amount}（剩余金额）</p>
                    <div className="xrb_a20_1">
                        <input type="text"  id="buy_amt" style={c2Style} />
                        <a style={c3Style} href="javascript: void(0);" onClick={this.buy}>立即抢购</a>
                    </div>
                </div>
            );
        }
    },
    buy: function(){
        var amt = $('#buy_amt').val();
        var isValidMoney = /^\d{2,8}(\.\d{0,2})?$/.test(amt);
        if (isValidMoney){
            var r = confirm("投资金额：" + formatNum(parseFloat(amt), 2) + "元, 确定？");
            if (r){
                var num = parseFloat(amt).toFixed(2) * 100;
                var resp = callAjax('/invest', {'amt' : num , 'product': this.props.product});
                if (resp.resultCode == 'login'){
                    windows.location.href="/usr/login.jsp";
                }
                else{
                    alert(resp.resultCode);
                }
            }
        }
        else{
            alert('请输入正确的投资金额');
        }
    }
});

var Investment = React.createClass({
    render: function() {
        var buyed = this.props.invest.totalAmount - this.props.invest.residualAmount;
        var progress = Math.floor(buyed * 100 / this.props.invest.totalAmount);
        var buy = progress == 100 ? false : true;
        var icon = this.props.imgPath + "/" + this.props.invest.productType + ".jpg";
        var amount = formatNum(this.props.invest.totalAmount, 0);

        return (
            <div className="xh_0">
                <InvestmentIcon  img = {icon}/>
                <div className="xh_01">
                    <InvestmentTitle title={this.props.invest.productName} />
                    <div className="xrb_a2">
                        <InvestmentField name="年化收益率" data={this.props.invest.loanRate} unit="%"/>
                        <InvestmentField name="投资期限" data={this.props.invest.investDay} unit="天"/>
                        <InvestmentField name="标的总额" data={amount} unit="元"/>
                        <InvestmentProgress progress={progress} />
                        <InvestmentBuy buy={buy} min={this.props.invest.minAmount} remain={this.props.invest.residualAmount} product={this.props.invest.productId}/>
                    </div>
                </div>
            </div>
        );
    }
});


var Investments = React.createClass({
    render: function () {
        var imagePath = this.props.imgPath;
        return (
          <div>
              {this.props.invests.map(function(invest, id) {
                  return <Investment invest={invest} imgPath={imagePath} key={id}/>;
              })}
          </div>
        );
    }
});


function react_investment_render(elementName, data, imgPath){
    ReactDOM.render(
        <Investments invests={data} imgPath={imgPath}/>,
        document.getElementById(elementName)
    );
}