/**
 * Created by wangzunhui on 2015/11/28.
 */

var PagingItem = React.createClass({
    render: function () {
        return <span className={this.props.className}><a href="javascript: void(0);" onClick={this.click} >{this.props.name}</a></span>
    },
    click:function(){
        //var url = this.props.url + "?page=" + this.props.name;
        //console.log(this.props.type + '-' + this.props.page);
        product(this.props.type, this.props.page);
    }
});

var PagingCurrentItem = React.createClass({
    render: function () {
        return <span className="pagingItemCurrent">{this.props.name}</span>
    }
});

var Paging = React.createClass({
    render: function() {
        var rows = [];
        var k = 1;

        if (this.getPageCount() <= 1){
            return (
                <div className="pagination">
                    <div id="pager_pager">
                    </div>
                </div>
            );
        }

        if (!this.isFirstPage()){
            rows.push(<PagingItem className="pagingItem" name="首页" key={k++} page={1} type={this.props.type}/>);
            rows.push(<PagingItem className="pagingItem" name="上一页" key={k++} page={this.props.currentPage - 1} type={this.props.type}/>);
        }

        // home
        //if (this.getFirstLinkedPage() > 1){
        //    rows.push(<PagingItem className="pagingItem" name="首页" key={k++}/>);
        //}
        //
        //if (this.getFirstLinkedPage() > 2){
        //    rows.push(<PagingItem className="pagingDots" name="..." key={k++}/>);
        //}

        //console.log(this.getFirstLinkedPage() + "-" + this.getLastLinkedPage());
        for (var i = this.getFirstLinkedPage(); i <= this.getLastLinkedPage(); i++) {
            if (i == this.props.currentPage){
                rows.push(<PagingCurrentItem name={i} key={k++}/>);
            }
            else{
                rows.push(<PagingItem className="pagingItem" name={i} key={k++}  page={i} type={this.props.type}/>);
            }
        }

        //if (this.getLastLinkedPage() < this.getPageCount() - 2){
        //    rows.push(<PagingItem className="pagingDots" name="..." key={k++}/>);
        //}

        // tail
        //if (this.getLastLinkedPage() < this.getPageCount()){
        //    rows.push(<PagingItem className="pagingItem" name="尾页" key={k++}/>);
        //}

        if (!this.isLastPage()){
            rows.push(<PagingItem className="pagingItem" name="下一页" key={k++} page={this.props.currentPage + 1} type={this.props.type}/>);
            rows.push(<PagingItem className="pagingItem" name="尾页" key={k++} page={this.getPageCount()} type={this.props.type}/>);
        }

        return (
            <div className="pagination">
                <div id="pager_pager">
                    {rows}
                </div>
            </div>
        );
    },
    getMaxLinkedPages: function(){
        return 5;
    },
    getPageSize:function(){
        return 5;
    },
    getPageCount: function(){
        var pageCount = Math.floor(this.props.elementSize / this.getPageSize());
        return ((this.props.elementSize % this.getPageSize()) == 0) ? pageCount : (pageCount + 1);
    },
    isFirstPage: function(){
        return this.props.currentPage == 1;
    },
    isLastPage: function(){
        return this.props.currentPage == this.getPageCount();
    },
    getFirstLinkedPage: function(){
        return Math.max(1, this.props.currentPage - (this.getMaxLinkedPages() / 2));
    },
    getLastLinkedPage: function(){
        return Math.min(this.getFirstLinkedPage() + this.getMaxLinkedPages(), this.getPageCount());
    }
});

function render_paging(el, elementSize, currentPage, type){
    ReactDOM.render(
        <Paging elementSize={elementSize} currentPage={currentPage} type={type}/>,
        document.getElementById(el)
    );
}

