package com.easy.arch.service;

import com.easy.arch.dao.MessageDao;
import com.easy.arch.dao.UserDao;
import com.easy.arch.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl {
    @Autowired
    private MessageDao messageDao;

    public List<Message> findMessageByContent(String content){
        return messageDao.findMessageByContent(content);
    }

    public Message showMessageByMsgid(String msgid){
        return messageDao.showMessageByMsgid(msgid);
    }

    public List<Message> showAllMessage(){
        return messageDao.showAllMessage();
    }
    public List<Message> showMyMessages(){
        return messageDao.showMyMessages();
    }
    public int deleteMessage(String msgid){
        return messageDao.deleteMessage(msgid);
    }

    public int updateMessage(String msgid,String content){
        return messageDao.updateMessage(msgid,content);
    }

    public int insertMessage(String userid,String content){
        return messageDao.insertMessage(userid,content);
    }
}
