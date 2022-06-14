package com.example.ssgfinal;

public class Players {
    public String imgURL, Name, Stat1, Stat2;


    public Players() {
    }

    public Players(String name, String imgURL, String stat1, String stat2) {
        Name = name;
        Stat1 = stat1;
        Stat2 = stat2;
        imgURL = imgURL;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getStat1() {
        return Stat1;
    }

    public void setStat1(String stat1) {
        Stat1 = stat1;
    }

    public String getStat2() {
        return Stat2;
    }

    public void setStat2(String stat2) {
        Stat2 = stat2;
    }
}
