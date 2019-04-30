package com.xxdainiyou.been;

import org.litepal.crud.LitePalSupport;

public class Mylocation extends LitePalSupport {
    double jingdu;
    double weidu;
    String lou;

    public String getLou() {
        return lou;
    }

    public void setLou(String lou) {
        this.lou = lou;
    }

    public double getJingdu() {
        return jingdu;
    }

    public void setJingdu(double jingdu) {
        this.jingdu = jingdu;
    }

    public double getWeidu() {
        return weidu;
    }

    public void setWeidu(double weidu) {
        this.weidu = weidu;
    }
}
