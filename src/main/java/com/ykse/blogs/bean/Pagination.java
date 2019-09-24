package com.ykse.blogs.bean;

import java.util.ArrayList;
import java.util.List;

public class Pagination<T> implements java.io.Serializable{

    private static final long serialVersionUID = 1L;

    private List<T> content = new ArrayList<>();
    
    private Integer numPerPage;

    private Integer currentPage;

    private Integer totalPageNum;

    private Integer totalCount;

    public Pagination(){
        
    }

    public Pagination(List<T> content, Integer numPerPage, Integer currentPage,
            Integer totalPageNum, Integer totalCount) {
        super();
        this.content = content;
        this.numPerPage = numPerPage;
        this.currentPage = currentPage;
        this.totalPageNum = totalPageNum;
        this.totalCount = totalCount;
    }
    
    public void calculatePage(){
        if(totalCount == null || totalCount < 0) {
            totalPageNum = 1;
            totalCount = 0;
        } else {
            totalPageNum = totalCount % numPerPage == 0
                    ? totalCount / numPerPage : (totalCount / numPerPage + 1);
        }
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public Integer getNumPerPage() {
        return numPerPage;
    }

    public void setNumPerPage(Integer numPerPage) {
        this.numPerPage = numPerPage;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getTotalPageNum() {
        return totalPageNum;
    }

    public void setTotalPageNum(Integer totalPageNum) {
        this.totalPageNum = totalPageNum;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }
    
}