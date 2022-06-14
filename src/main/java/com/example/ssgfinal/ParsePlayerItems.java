package com.example.ssgfinal;

public class ParsePlayerItems {
    private String title;
    private String subTitle;
    private String subSubTitle;

    public ParsePlayerItems() {
    }

    public ParsePlayerItems(String title, String subTitle, String subSubTitle) {
        this.title = title;
        this.subTitle = subTitle;
        this.subSubTitle = subSubTitle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getSubSubTitle() {
        return subSubTitle;
    }

    public void setSubSubTitle(String subSubTitle) {
        this.subSubTitle = subSubTitle;
    }
}
