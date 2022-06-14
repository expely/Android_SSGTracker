package com.example.ssgfinal;

public class ParseItem {
    private String imgUrl;
    private String title;
    private String subTitle;
    private String subSubTitle;
    private String mainUrl;

    public ParseItem() {
    }

    public ParseItem(String imgUrl, String title, String subTitle, String subSubTitle) {
        this.imgUrl = imgUrl;
        this.title = title;
        this.subTitle = subTitle;
        this.subSubTitle = subSubTitle;
    }

    public String getSubSubTitle() {
        return subSubTitle;
    }

    public void setSubSubTitle(String subSubTitle) {
        this.subSubTitle = subSubTitle;
    }

    public String getMainUrl() {
        return mainUrl;
    }

    public void setMainUrl(String mainUrl) {
        this.mainUrl = mainUrl;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
