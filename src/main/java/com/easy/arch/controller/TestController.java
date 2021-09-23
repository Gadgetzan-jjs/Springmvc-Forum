package com.easy.arch.controller;

import com.easy.arch.entity.Message;
import com.easy.arch.entity.User;
import com.easy.arch.service.MessageServiceImpl;
import com.easy.arch.service.UserServiceImpl;
import com.easy.arch.status.UserStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class TestController {
    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private MessageServiceImpl messageService;

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(){
        return "Login";
    }

    @RequestMapping(value = "/dologin",method = RequestMethod.GET)
    public String dologin(String username,String password){
        System.out.println("do do login");
        User user=userService.findUserByUsernameAndPassword(username,password);
        if (user!=null){
            UserStatus.getInstance().setUser(user);
            return "redirect:/success/wel";
        }else{
            return "redirect:/err";
        }
    }

    @RequestMapping(value = "/register",method = RequestMethod.GET)
    public String register(){
        return "Register";
    }
    @RequestMapping(value = "/doregister" ,method = RequestMethod.GET)
    public String doreigister(String username,String password){
        if (userService.registerUser(username,password)!=0){
            return "Login";
        }else{
            return "redirect:err";
        }
    }


    @RequestMapping("/wel")
    public String welcome(Model model){
        List<Message> list=messageService.showAllMessage();
        model.addAttribute("list",list);
        System.out.println("XXXXXXXXXXXXXXXXXXXXXX");
        for (Message message : list) {
            System.out.println(message.getMsgid());
            System.out.println(message.getUserid());
            System.out.println(message.getPartContent());
        }
        return "welcome";
    }

    @RequestMapping(value = "/err",method = RequestMethod.GET)
    @ResponseBody
    public String err(){
        return "WARNING:something is wrong!";
    }
}
