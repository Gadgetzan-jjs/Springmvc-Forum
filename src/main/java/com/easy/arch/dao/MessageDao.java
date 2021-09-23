package com.easy.arch.dao;

import com.easy.arch.annotation.Cache;
import com.easy.arch.entity.Message;
import com.easy.arch.status.UserStatus;
import javafx.beans.binding.StringBinding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MessageDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Cache
    public List<Message> findMessageByContent(String content){
        List<Message> list=new ArrayList();
        StringBuilder sql=new StringBuilder("select * from message ");
        sql.append("where content like ?");
        List<Object> params=new ArrayList<>();
        params.add("%"+content+"%");
        jdbcTemplate.query(sql.toString(), params.toArray(), rs -> {
            Message message=new Message();
            message.setMsgid(rs.getString(1));
            message.setUserid(rs.getString(2));
            message.setContent(rs.getString(3));
            list.add(message);
        });
        return list;
    }
    @Cache
    public Message showMessageByMsgid(String msgid){
        final Message message=new Message();
        String sql="select * from message where msgid = ? ";
        jdbcTemplate.query(sql, new Object[]{msgid}, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                message.setMsgid(rs.getString(1));
                message.setUserid(rs.getString(2));
                message.setContent(rs.getString(3));
            }
        });
        return message;
    }
    @Cache
    public List<Message> showAllMessage(){
        List<Message> list=new ArrayList<>();
        String sql="select * from message";
        jdbcTemplate.query(sql, new Object[]{}, rs -> {
            Message message=new Message();
            message.setContent(rs.getString(3));
            message.setUserid(rs.getString(2));
            message.setMsgid(rs.getString(1));
            list.add(message);
        });
        return list;
    }
    @Cache
    public List<Message> showMyMessages(){
        List<Message> list=new ArrayList<>();
        String sql="select * from message where userid = ? ";
        jdbcTemplate.query(sql, new Object[]{UserStatus.getInstance().getUser().getId()}, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                Message message=new Message();
                message.setContent(rs.getString(3));
                message.setUserid(rs.getString(2));
                message.setMsgid(rs.getString(1));
                list.add(message);
            }
        });
        return list;
    }
    public int deleteMessage(String msgid){
        String sql="delete from message where msgid= ?";
        return jdbcTemplate.update(sql,new Object[]{msgid});
    }


    public int updateMessage(String msgid,String content){
        String sql="update message set content= ? where msgid= ?";
        return jdbcTemplate.update(sql,new Object[]{content,msgid});
    }

    public int insertMessage(String userid,String content){
        String part=content.substring(0,5);
        String msgid=(userid+"@"+part);
        String sql="insert into message values(?,?,?)";
        return jdbcTemplate.update(sql,new Object[]{msgid,userid,content});
    }



}
