package com.foray.gw.Entity;

import lombok.Data;
import lombok.Getter;

@Data
public class PageVo {
    private int page=0;
    private int start=0;
    private int limit=10;
    private int paginationStart=0;
    private int TotalCount = 0;
    private int paginationLast=0;
    private String searchText;

    public PageVo(){
        this.start=0;
        this.limit=10;
        this.searchText="";
    }
    public PageVo(int start, int limit, String searchText) {
        this.start = start>0 ? start:0;
        this.limit = limit>0? limit :10;
        this.searchText = searchText !="" ? searchText : "";
    }
    public void calc()
    {
        this.start = start>0 ? start:0;
        this.limit = limit>0? limit :10;
        this.searchText = searchText !="" ? searchText : "";
        this.paginationStart = (int)(this.start +1);
        this.paginationLast =  this.start + 9 < this.TotalCount ? this.start + 9 : this.TotalCount ;
    }
}
