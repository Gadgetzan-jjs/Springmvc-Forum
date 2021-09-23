package com.easy.arch.status;

import com.easy.arch.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Data
public class UserStatus {

    private static AtomicInteger atomicInteger=new AtomicInteger(0);
    private User user;
    private static UserStatus userStatus;
    private UserStatus(){

    }
    public static UserStatus getInstance(){
        while (!atomicInteger.compareAndSet(0,1));
        if (userStatus==null){
            userStatus=new UserStatus();
        }
        atomicInteger.compareAndSet(1, 0);
        return userStatus;

    }
}
