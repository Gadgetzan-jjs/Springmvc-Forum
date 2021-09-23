package com.easy.arch.dao;

import com.easy.arch.annotation.Cache;
import com.easy.arch.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void checkJdbcTemplate(){
        System.out.println(jdbcTemplate.toString());
    }

    @Cache
    public User findUserByUsernameAndPassword(String username,String password){
        final User[] user = {null};
        String sql="select * from user where username=? and password=?";
        jdbcTemplate.query(sql,new Object[]{username,password},new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                user[0] =new User();
                user[0].setId(resultSet.getString(3));
                user[0].setUsername(resultSet.getString(1));
                user[0].setPassword(resultSet.getString(2));
            }
            });
        return user[0];
    }

    public int registerUser(String username,String password){
        String id=String.valueOf((username+password).hashCode());
        String sql="insert into user values(?,?,?)";
        return jdbcTemplate.update(sql,username,password,id);
    }


}
