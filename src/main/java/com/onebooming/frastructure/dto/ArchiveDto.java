package com.onebooming.frastructure.dto;

import com.onebooming.frastructure.model.ContentDomain;

import java.util.List;

/**
 * 文章归档类
 * @author Onebooming
 * @version 1.0
 * @date 2019/12/12 13:53
 */
public class ArchiveDto {
    private String date;
    private String count;
    private List<ContentDomain> articles;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public List<ContentDomain> getArticles() {
        return articles;
    }

    public void setArticles(List<ContentDomain> articles) {
        this.articles = articles;
    }

}
