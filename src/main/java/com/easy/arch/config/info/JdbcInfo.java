package com.easy.arch.config.info;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:mysql.properties")
public class JdbcInfo {
    @Value("${Derive}")
    private String Derive;
    @Value("${Url}")
    private String Url;
    @Value("${uname}")
    private String uname;
    @Value("${passwd}")
    private String passwd;

    public String getDerive() {
        return Derive;
    }

    public String getUrl() {
        return Url;
    }

    public String getUname() {
        return uname;
    }

    public String getPasswd() {
        return passwd;
    }
}
