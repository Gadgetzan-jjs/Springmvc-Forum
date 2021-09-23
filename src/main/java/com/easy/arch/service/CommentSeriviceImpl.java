package com.easy.arch.service;

import com.easy.arch.dao.CommentDao;
import com.easy.arch.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentSeriviceImpl {
    @Autowired
    private CommentDao commentDao;

    public List<Comment> showAllThisMessageComment(String msgid){
        return commentDao.showAllThisMessageComment(msgid);
    }

    public int insertTheCommentintoMessage(String msgid,String userid,String comment){
        return commentDao.insertTheCommentintoMessage(msgid,userid,comment);
    }

    public int deleteTheCommentByUserid(String cotid){
        return commentDao.deleteTheCommentByUserid(cotid);
    }

    public List<Comment> showMyComment(String userid){
        return commentDao.showMyComment(userid);
    }

    public int deleteTheCommentByMsgid(String msgid){
        return commentDao.deleteTheCommentByMsgid(msgid);
    }
}
