package com.dsc.domain;

public class CustomPage {
    //当前页
    private Integer curPage=1;
    //每页的条数
    private Integer pageNumber=5;
    //总页数
    private Integer totalPage;
    //总条数
    private Integer totalSize;//21
    //上一页
    private Integer prePage;
    //下一页
    private Integer nextPage;

    //需要判断是否到第一页了
    public Integer getPrePage() {
        if(this.curPage-1<1){
            this.prePage = 1;
        }else {
           this.prePage = this.curPage - 1;
        }
        return this.prePage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    //21/5=4
    //总页数,需要先知道总条数
    public Integer getTotalPage() {
        if(this.totalSize%this.pageNumber==0){
            totalPage = totalSize/pageNumber;
        }else{
            totalPage = totalSize/pageNumber+1;
        }
        return totalPage;
    }

    //判断尾页
    public Integer getNextPage() {
       if((this.curPage+1)>getTotalPage()){
            this.nextPage = getTotalPage();
        }else{
            this.nextPage = this.curPage+1;
        }
        return this.nextPage;
    }

    public Integer getCurPage() {
        return curPage;
    }

    public void setCurPage(Integer curPage) {
        this.curPage = curPage;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(Integer totalSize) {
        this.totalSize = totalSize;
    }

    public void setPrePage(Integer prePage) {
        this.prePage = prePage;
    }

    public void setNextPage(Integer nextPage) {
        this.nextPage = nextPage;
    }
}
