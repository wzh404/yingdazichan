/**
 * Created by wangzunhui on 2015/11/28.
 */

"use strict";

var PagingItem = React.createClass({
    displayName: "PagingItem",

    render: function render() {
        return React.createElement(
            "span",
            { className: this.props.className },
            React.createElement(
                "a",
                { href: "#" },
                this.props.name
            )
        );
    }
});

var PagingCurrentItem = React.createClass({
    displayName: "PagingCurrentItem",

    render: function render() {
        return React.createElement(
            "span",
            { className: "pagingItemCurrent" },
            this.props.name
        );
    }
});

var Paging = React.createClass({
    displayName: "Paging",

    render: function render() {
        var rows = [];
        var k = 1;

        if (!this.isFirstPage()) {
            rows.push(React.createElement(PagingItem, { className: "pagingItem", name: "上一页", key: k++ }));
        }

        // home
        if (this.getFirstLinkedPage() > 1) {
            rows.push(React.createElement(PagingItem, { className: "pagingItem", name: "首页", key: k++ }));
        }

        if (this.getFirstLinkedPage() > 2) {
            rows.push(React.createElement(PagingItem, { className: "pagingDots", name: "...", key: k++ }));
        }

        //console.log(this.getFirstLinkedPage() + "-" + this.getLastLinkedPage());
        for (var i = this.getFirstLinkedPage(); i < this.getLastLinkedPage(); i++) {
            if (i == this.props.currentPage) rows.push(React.createElement(PagingCurrentItem, { name: i, key: k++ }));else {
                rows.push(React.createElement(PagingItem, { className: "pagingItem", name: i, key: k++ }));
            }
        }

        if (this.getLastLinkedPage() < this.getPageCount() - 2) {
            rows.push(React.createElement(PagingItem, { className: "pagingDots", name: "...", key: k++ }));
        }

        // tail
        if (this.getLastLinkedPage() < this.getPageCount()) {
            rows.push(React.createElement(PagingItem, { className: "pagingItem", name: "尾页", key: k++ }));
        }

        if (!this.isLastPage()) {
            rows.push(React.createElement(PagingItem, { className: "pagingItem", name: "下一页", key: k++ }));
        }

        return React.createElement(
            "div",
            { className: "pagination" },
            React.createElement(
                "div",
                { id: "pager_pager" },
                rows
            )
        );
    },
    getMaxLinkedPages: function getMaxLinkedPages() {
        return 10;
    },
    getPageSize: function getPageSize() {
        return 10;
    },
    getPageCount: function getPageCount() {
        var pageCount = Math.floor(this.props.elementSize / this.getPageSize());
        return this.props.elementSize % this.getPageCount == 0 ? pageCount : pageCount + 1;
    },
    isFirstPage: function isFirstPage() {
        return this.props.currentPage == 1;
    },
    isLastPage: function isLastPage() {
        return this.props.currentPage == this.getPageCount();
    },
    getFirstLinkedPage: function getFirstLinkedPage() {
        return Math.max(1, this.props.currentPage - this.getMaxLinkedPages() / 2);
    },
    getLastLinkedPage: function getLastLinkedPage() {
        return Math.min(this.getFirstLinkedPage() + this.getMaxLinkedPages(), this.getPageCount());
    }
});

function test_paging(el, elementSize, currentPage) {

    ReactDOM.render(React.createElement(Paging, { elementSize: elementSize, currentPage: currentPage }), document.getElementById(el));
}
