package com.consult.analysis.service.enums;

/**
 * @author : laoa
 * @describe : 消息来源
 * @email : laoa@markcoin.net
 */
public enum MessageSource {
    TWITTER("twitter");
    private String txt;
    MessageSource(String txt) {
        this.txt=txt;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }
}
