package com.easy.arch.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class Message {
    private String msgid;
    private String userid;
    private String content;


    private String partContent;

    public String getPartContent() {
        if (getContent().length() < 10&&getContent().length()>=5) {
            return getContent().substring(0, 5);
        }else if (getContent().length()<5&&getContent().length()>=2){
            return getContent().substring(0,2);
        }else {
            return getContent();
        }
    }

    public void setPartContent(String partContent) {
        this.partContent = partContent;
    }

    public String getMsgid() {
        return msgid;
    }

    public void setMsgid(String msgid) {
        this.msgid = msgid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
