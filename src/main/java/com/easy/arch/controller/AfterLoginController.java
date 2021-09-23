package com.easy.arch.controller;

import com.easy.arch.entity.Comment;
import com.easy.arch.entity.Message;
import com.easy.arch.service.CommentSeriviceImpl;
import com.easy.arch.service.MessageServiceImpl;
import com.easy.arch.status.UserStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/success")
public class AfterLoginController {
    @Autowired
    private MessageServiceImpl messageService;

    private String msgid;

    @Autowired
    private CommentSeriviceImpl commentSerivice;


    @RequestMapping(value = "/findMessage",method = RequestMethod.GET)
    public String findMessage(ModelMap model, String content){
        List<Message> list=messageService.findMessageByContent(content);
        model.addAttribute("messages",list);
//        return new ModelAndView("/result","model",model);
        return "result";
    }


    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public String test(HttpServletRequest request){
//        map.addAttribute("hello","hello!!");
        request.setAttribute("hello","hljljlj");
        return "test";
    }

    @RequestMapping("/wel")
    public String welcome(Model model){
        List<Message> list=messageService.showAllMessage();
        model.addAttribute("list",list);
        model.addAttribute("username",UserStatus.getInstance().getUser().getUsername());
        return "afterLogin";
    }

    @RequestMapping("/showMyMessages")
    public String showMyMessages(Model model){
        List<Message> list=messageService.showMyMessages();
        model.addAttribute("list",list);

        for (Message message : list) {
            System.out.println(message.getMsgid());
            System.out.println(message.getUserid());
            System.out.println(message.getContent());
        }


        return "showMyMessages";
    }

    @RequestMapping("/deletemsg")
    public String deletemsg(String msgid,Model model){
        commentSerivice.deleteTheCommentByMsgid(msgid);
        messageService.deleteMessage(msgid);
//        Message message=messageService.showMessageByMsgid(msgid);
//        model.addAttribute("content",message.getContent());
//        model.addAttribute("userid",message.getUserid());
        List<Message> list=messageService.showMyMessages();
        model.addAttribute("list",list);
        return "showMyMessages";
    }

    //showmsg?msgid=A100@001
    @RequestMapping(value = "/showmsg",method = RequestMethod.GET)
    public String showmsg(String msgid,Model model ){
        Message message=messageService.showMessageByMsgid(msgid);
        this.msgid=msgid;
        model.addAttribute("content",message.getContent());
        model.addAttribute("userid",message.getUserid());
        List<Comment> list=commentSerivice.showAllThisMessageComment(msgid);
        model.addAttribute("list",list);
        return "showContent";
    }

    @RequestMapping(value = "/updatemsg",method = RequestMethod.GET)
    public String updatemsg(String msgid,Model model){
        Message message=messageService.showMessageByMsgid(msgid);
        this.msgid=message.getMsgid();
        model.addAttribute("content",message.getContent());
        model.addAttribute("msgid",message.getMsgid());
        return "updateMsg";
    }

    @RequestMapping("/afterupdate")
    public String afterupdate(String upcontent,Model model){
        messageService.updateMessage(msgid,upcontent);
        Message message=messageService.showMessageByMsgid(msgid);
        model.addAttribute("content",message.getContent());
        model.addAttribute("userid",message.getUserid());
        List<Comment> list=commentSerivice.showAllThisMessageComment(msgid);
        model.addAttribute("list",list);
        return "showContent";
    }

    @RequestMapping("/insert")
    public String insert(){
        return "insertpage";
    }


    @RequestMapping(value = "/afterinsert",method = RequestMethod.GET)
    public String afterinsert(String content,Model model){
        messageService.insertMessage(UserStatus.getInstance().getUser().getId(),content);
        List<Message> list=messageService.showMyMessages();
        model.addAttribute("list",list);
        return "showMyMessages";
    }

    @RequestMapping("/addcomment")
    public String addcomment(String addcomment,Model model){
        System.out.println("addcomment: "+addcomment);
        String userid=UserStatus.getInstance().getUser().getId();
        Message message=messageService.showMessageByMsgid(msgid);
        model.addAttribute("content",message.getContent());
        model.addAttribute("userid",message.getUserid());
        commentSerivice.insertTheCommentintoMessage(msgid,userid,addcomment);
        List<Comment> list=commentSerivice.showAllThisMessageComment(msgid);
        model.addAttribute("list",list);
        return "showContent";
    }


    @RequestMapping("/showMyComment")
    public String showMyComment(Model model){
        String userid=UserStatus.getInstance().getUser().getId();
        List<Comment> list=commentSerivice.showMyComment(userid);
        System.out.println("sj: "+userid);
        model.addAttribute("list",list);
        return "showMyComment";
    }

    @RequestMapping("/deleteCot")
    public String deleteCot(String cotid,Model model){
        commentSerivice.deleteTheCommentByUserid(cotid);
        String userid=UserStatus.getInstance().getUser().getId();
        List<Comment> list=commentSerivice.showMyComment(userid);
        model.addAttribute("list",list);
        return "showMyComment";
    }
}
