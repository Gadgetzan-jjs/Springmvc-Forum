package com.easy.arch.interceptor;

import com.easy.arch.entity.User;
import com.easy.arch.status.UserStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;

public class LoginInterceptor extends HandlerInterceptorAdapter {
    private String FourZeroOne="you are not accessible";
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("prehandle");
        response.setCharacterEncoding("UTF-8");
        if (UserStatus.getInstance().getUser()!=null) {
            if (UserStatus.getInstance().getUser().getUsername() != null) {
                return true;
            } else {
                response.setStatus(401);
                response.getWriter().println(FourZeroOne);
                return false;
            }
        }else {
            response.setStatus(401);
            response.getWriter().println(FourZeroOne);
            return false;
        }
//        String key="";
//        String value="";
//        User user=(User)request.getAttribute("username");
////        Cookie[] cookies=request.getCookies();
//        if (user==null){
//            response.setStatus(401);
//            response.getWriter().write(FourZeroOne);
//            response.setContentLength(FourZeroOne.length());
//            return false;
//        }
//        if (user.getUsername().equals(UserStatus.getInstance().getUser().getUsername())){
//            return true;
//        }else{
//            response.setStatus(401);
//            response.getWriter().println(FourZeroOne);
//            return false;
//        }
//        for (User obj:user){
//            key=obj.getName();
//            value=obj.getValue();
//            if (key.equals("username")&&value.equals(UserStatus.getInstance().getUser().getUsername())){
//                return true;
//            }
//            response.setStatus(401);
//            response.getWriter().println("验证未通过");
//            return false;
//        }
//        response.setStatus(401);
//        response.getWriter().println("验证未通过");
//        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion");
    }
}
