/**
 * Created by wangzunhui on 2015/11/28.
 */
"use strict";

function formatNum(num, n) {
    num = String(num.toFixed(n));

    var re = /(-?\d+)(\d{3})/;
    while (re.test(num)) num = num.replace(re, "$1,$2");

    return num;
}

var InvestmentIcon = React.createClass({
    displayName: "InvestmentIcon",

    render: function render() {
        return React.createElement(
            "p",
            { className: "xh_0_" },
            React.createElement("img", { src: this.props.img })
        );
    }
});

var InvestmentTitle = React.createClass({
    displayName: "InvestmentTitle",

    render: function render() {

        return React.createElement(
            "div",
            { className: "xh_01a_" },
            this.props.title
        );
    }
});

var InvestmentField = React.createClass({
    displayName: "InvestmentField",

    render: function render() {
        var c1Style = {
            fontSize: 18
        };

        var c2Style = {
            color: '#848587',
            fontSize: 18
        };

        return React.createElement(
            "div",
            { className: "xrb_a21_" },
            React.createElement(
                "p",
                { style: c1Style },
                this.props.data,
                " ",
                this.props.unit
            ),
            React.createElement(
                "p",
                { style: c2Style },
                this.props.name
            )
        );
    }
});

var InvestmentProgress = React.createClass({
    displayName: "InvestmentProgress",

    render: function render() {
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
            marginTop: '12px',
            height: '24px',
            fontSize: 18
        };

        var c2Style = {
            color: '#848587',
            marginTop: '3px',
            fontSize: 18
        };
        return React.createElement(
            "div",
            { className: "xrb_a21_" },
            React.createElement(
                "p",
                { style: c1Style },
                React.createElement(
                    "span",
                    { style: outStyle },
                    React.createElement("span", { style: inStyle })
                )
            ),
            React.createElement(
                "p",
                { style: c2Style },
                "已投",
                this.props.progress,
                "%"
            )
        );
    }
});

var InvestmentBuy = React.createClass({
    displayName: "InvestmentBuy",

    render: function render() {
        var c1Style = {
            paddingTop: '20px',
            textIndent: '2em',
            color: '#292929'
        };

        var c2Style = {
            paddingTop: '2px',
            color: '#848587',
            float: 'left',
            width: '112px',
            height: '30px'
        };

        var c3Style = {
            paddingRight: '24px',
            float: 'right'
        };

        if (!this.props.buy) {
            return React.createElement("div", { className: "xrb_a20_0" });
        } else {
            var amount = formatNum(this.props.remain, 0);
            return React.createElement(
                "div",
                { className: "xrb_a21" },
                React.createElement(
                    "p",
                    { style: c1Style },
                    amount,
                    "（剩余金额）"
                ),
                React.createElement(
                    "div",
                    { className: "xrb_a20_1" },
                    React.createElement("input", { type: "text", id: "buy_amt", style: c2Style }),
                    React.createElement(
                        "a",
                        { style: c3Style, href: "javascript: void(0);", onClick: this.buy },
                        "立即抢购"
                    )
                )
            );
        }
    },
    buy: function buy() {
        var amt = $('#buy_amt').val();
        var isValidMoney = /^\d{2,8}(\.\d{0,2})?$/.test(amt);
        if (isValidMoney) {
            var r = confirm("投资金额：" + formatNum(parseFloat(amt), 2) + "元, 确定？");
            if (r) {
                var num = parseFloat(amt).toFixed(2) * 100;
                var resp = callAjax('/invest', { 'amt': num, 'product': this.props.product });
                if (resp.resultCode == 'login') {
                    location.href = "/user/login.jsp";
                } else if (resp.resultCode == 'OK') {
                    alert('投资成功');
                } else {
                    alert('投资失败');
                }
            }
        } else {
            alert('请输入正确的投资金额');
        }
    }
});

var Investment = React.createClass({
    displayName: "Investment",

    render: function render() {
        var buyed = this.props.invest.totalAmount - this.props.invest.residualAmount;
        var progress = Math.floor(buyed * 100 / this.props.invest.totalAmount);
        var buy = progress == 100 ? false : true;
        var icon = this.props.imgPath + "/" + this.props.invest.productType + ".jpg";
        var amount = formatNum(this.props.invest.totalAmount, 0);

        return React.createElement(
            "div",
            { className: "xh_0" },
            React.createElement(InvestmentIcon, { img: icon }),
            React.createElement(
                "div",
                { className: "xh_01" },
                React.createElement(InvestmentTitle, { title: this.props.invest.productName }),
                React.createElement(
                    "div",
                    { className: "xrb_a2" },
                    React.createElement(InvestmentField, { name: "年化收益率", data: this.props.invest.loanRate, unit: "%" }),
                    React.createElement(InvestmentField, { name: "投资期限", data: this.props.invest.investDay, unit: "天" }),
                    React.createElement(InvestmentField, { name: "标的总额", data: amount, unit: "元" }),
                    React.createElement(InvestmentProgress, { progress: progress }),
                    React.createElement(InvestmentBuy, { buy: buy, min: this.props.invest.minAmount, remain: this.props.invest.residualAmount, product: this.props.invest.productId })
                )
            )
        );
    }
});

var Investments = React.createClass({
    displayName: "Investments",

    render: function render() {
        var imagePath = this.props.imgPath;
        return React.createElement(
            "div",
            null,
            this.props.invests.map(function (invest, id) {
                return React.createElement(Investment, { invest: invest, imgPath: imagePath, key: id });
            })
        );
    }
});

function react_investment_render(elementName, data, imgPath) {
    ReactDOM.render(React.createElement(Investments, { invests: data, imgPath: imgPath }), document.getElementById(elementName));
}
