package com.easy.arch.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    private String msgid;
    private String userid;
    private String comment;
    private String cotid;

    public String getCotid() {
        return cotid;
    }

    public void setCotid(String cotid) {
        this.cotid = cotid;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
