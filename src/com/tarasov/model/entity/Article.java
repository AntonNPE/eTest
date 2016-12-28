package com.tarasov.model.entity;

public class Article {

    private String articleName;

    public Article() {
    }

    public Article(String articleName) {
        this.articleName = articleName;
    }

    public String getArticleName() {
        return articleName;
    }

    public void setArticleName(String articleName) {
        this.articleName = articleName;
    }

    @Override
    public String toString() {
        return "Article is\t-\t" + articleName;
    }
}
