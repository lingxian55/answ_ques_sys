package com.example.demo2.controller;
import com.example.demo2.beans.Message;
import com.example.demo2.beans.Response;
import com.example.demo2.beans.ResponseCode;
import com.example.demo2.beans.User;
import com.example.demo2.dao.MessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
public class MessageController {
    @Autowired
    MessageMapper messageMapper;
    @PostMapping("/addMessageApi")
    @ResponseBody
    public Response addMessage(Message message,HttpSession session){
        Date date=new Date();
        Response response=new Response();
        String content=message.getMessageContent();
        String messageManR=null;
        if(content.charAt(0)=='@'){
            messageManR=content.split(" ")[0];
            content=content.replaceAll(messageManR+" ","");
            messageManR=messageManR.replaceAll("@","");
        }
        message.setMessageManR(messageManR);
        message.setMessageContent(content);
        User user=(User)session.getAttribute("user");
        message.setMessageMan(user.getUsername());
        message.setPublishTime(date);
        messageMapper.insert(message);
        response.setData(message);
        response.setStatus(ResponseCode.normal);
        return response;
    }
    @PostMapping("/getMessageByQuestionIdApi")
    @ResponseBody
    public Response getMessageByquestionId(int questionId){
        Response response=new Response();
        List<Message> messages=messageMapper.getAllMessageByQuestionId(questionId);
        response.setStatus(ResponseCode.normal);
        response.setData(messages);
        return response;
    }
    @PostMapping("/getMessageByMessageManRApi")
    @ResponseBody
    public Response getMessageByMessageManR(HttpSession session){
        Response response=new Response();
        User user=(User) session.getAttribute("user") ;
        List<Message> messages=messageMapper.getAllMessageByMessageManR(user.getUsername());
        response.setStatus(ResponseCode.normal);
        response.setData(messages);
        return response;
    }
}
