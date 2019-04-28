package com.xxdainiyou.been;

import org.litepal.crud.LitePalSupport;

public class Memorandbeen extends LitePalSupport {
    String content;
    String address;
    String iscall;
    String time;
    String img;

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
