package com.xxdainiyou.been;

import org.litepal.crud.LitePalSupport;

import java.io.Serializable;

public class Memorandbeen extends LitePalSupport implements Serializable {
    String content;
    String address;
    String iscall;
    String time;
    String img;
    double jing;
    double weidu;

    public double getJing() {
        return jing;
    }

    public void setJing(double jing) {
        this.jing = jing;
    }

    public double getWeidu() {
        return weidu;
    }

    public void setWeidu(double weidu) {
        this.weidu = weidu;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIscall() {
        return iscall;
    }

    public void setIscall(String iscall) {
        this.iscall = iscall;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
