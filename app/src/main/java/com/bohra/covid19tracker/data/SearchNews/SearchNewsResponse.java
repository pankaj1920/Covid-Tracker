package com.bohra.covid19tracker.data.SearchNews;

import java.util.List;

public class SearchNewsResponse {

    private String status;
    private String totalResults;
    List<SearchNewsArticle> articles;

    public SearchNewsResponse(String status, String totalResults, List<SearchNewsArticle> articles) {
        this.status = status;
        this.totalResults = totalResults;
        this.articles = articles;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(String totalResults) {
        this.totalResults = totalResults;
    }

    public List<SearchNewsArticle> getArticles() {
        return articles;
    }

    public void setArticles(List<SearchNewsArticle> articles) {
        this.articles = articles;
    }


}
