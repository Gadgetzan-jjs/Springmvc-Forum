package com.easy.arch.dao;

import com.easy.arch.annotation.Cache;
import com.easy.arch.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CommentDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Cache
    public List<Comment> showAllThisMessageComment(String msgid){
        String sql="select * from Comment where msgid=?";
        List<Comment> list=new ArrayList<>();
        jdbcTemplate.query(sql, new Object[]{msgid}, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                Comment comment=new Comment();
                comment.setComment(rs.getString(3));
                comment.setUserid(rs.getString(2));
                comment.setMsgid(rs.getString(1));
                comment.setCotid(rs.getString(4));
                list.add(comment);
            }
        });
        return list;
    }


    @Cache
    public List<Comment> showMyComment(String userid){
        String sql="select * from Comment where userid=?";
        List <Comment> list=new ArrayList<>();
        jdbcTemplate.query(sql, new Object[]{userid}, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                Comment comment=new Comment();
                comment.setMsgid(rs.getString(1));
                comment.setUserid(rs.getString(2));
                comment.setComment(rs.getString(3));
                comment.setCotid(rs.getString(4));
                list.add(comment);
            }
        });
        return list;
    }


    public int insertTheCommentintoMessage(String msgid,String userid,String comment){
        String sql="insert into Comment values(?,?,?,?)";
        String cotid=(msgid+userid);
        return jdbcTemplate.update(sql,new Object[]{msgid,userid,comment,cotid});
    }

    public int deleteTheCommentByUserid(String cotid){
        String sql="delete from Comment where cotid=?";
        return jdbcTemplate.update(sql,new Object[]{cotid});
    }

    public int deleteTheCommentByMsgid(String msgid){
        String sql="delete from Comment where msgid=?";
        return jdbcTemplate.update(sql,new Object[]{msgid});
    }

}
