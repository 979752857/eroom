$(function() {
    $("#load-more").hide();
});
function checkLoadMore(data, tab){
    if(!data){
        if(tab.loadMore){
            $("#load-more").show();
        }else{
            $("#load-more").hide();
        }
        return;
    }

    if(data.curPage == data.page){
        $("#load-more").hide();
        tab.loadMore = false;
    }else if(data.curPage < data.page){
        $("#load-more").show();
        tab.loadMore = true;
    }else{
        tab.loadMore = false;
        $("#load-more").hide();
    }
}