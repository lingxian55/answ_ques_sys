package com.example.demo2.controller;
import com.example.demo2.beans.*;
import com.example.demo2.dao.AnswerMapper;
import com.example.demo2.dao.QuestionMapper;
import com.example.demo2.dao.UserMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.tomcat.util.net.openssl.OpenSSLUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
@Controller
public class UserController {

    @Autowired
    UserMapper userMapper;
    @Autowired
    QuestionMapper questionMapper;
    @Autowired
    AnswerMapper answerMapper;

    @PostMapping("/registeApi")
    public @ResponseBody
    Response registe(User user, HttpSession httpSession) {
        Response response = new Response();
        if(user.getPicture()==null){
            user.setPicture("img/default.jpg");
        }
        if (userMapper.selectByPrimaryKey(user.getUsername()) == null) {
            Date date = new Date();
            user.setRegtime(date);
            user.setPassword(MD5.md5(user.getPassword()));
            userMapper.insert(user);
            response.setStatus(ResponseCode.normal);
            httpSession.setAttribute("user", user);
        } else {
            response.setStatus(ResponseCode.accounttexisted);
        }
        return response;

    }

    @PostMapping("/loginApi")
    @ResponseBody
    public Response show(String username, String password, HttpSession httpSession) {
        User user = null;
        if(password!=null) {
            password = MD5.md5(password);
        }
        Response response = new Response();
        if ((user = (User) httpSession.getAttribute("user")) != null) {
            response.setData(user);
            response.setStatus(ResponseCode.normal);
        } else {
            user = userMapper.selectByUsernameAndPassword(username, password);
            if (user == null) {
                response.setStatus(ResponseCode.accountnotexist);
            } else {
                response.setData(user);
                httpSession.setAttribute("user", user);
                response.setStatus(ResponseCode.normal);
            }
        }
        return response;
    }

    @PostMapping("/updataPasswordApi")
    @ResponseBody
    public Response updataPassword(HttpSession session, String oldPassword,String newPassword) {
        Response response = new Response();
        oldPassword=MD5.md5(oldPassword);
        newPassword=MD5.md5(newPassword);
        User user = null;
        user = (User) session.getAttribute("user");
        if (user == null) {
            response.setStatus(ResponseCode.tokenfail);
        } else {
            if (user.getPassword().equals(oldPassword)) {
                user.setPassword(newPassword);
                userMapper.updateByPrimaryKey(user);
                response.setStatus(ResponseCode.normal);
                response.setData(user);
                session.setAttribute("user", user);
            } else {
                //password not incorrect
                response.setStatus(ResponseCode.passworderror);
            }
        }

        return response;
    }
    @PostMapping("/upUserImageApi")
    @ResponseBody
    public Response upUserIamge(HttpSession session, @RequestParam("file") MultipartFile file){
        Response response=new Response<>();
        User user=null;
        if((user=(User)session.getAttribute("user"))!=null){
            String path="C:/Users/lingxian/Documents/java/img/"+user.getUsername()+".jpg";
            String Hpath="img/"+user.getUsername()+".jpg";
            File file1=new File(path);
            try {
                file.transferTo(file1);
                response.setStatus(ResponseCode.normal);
                user.setPicture(Hpath);
                userMapper.updateByPrimaryKey(user);
                response.setData(user);
            } catch (IOException e) {
                e.printStackTrace();
                response.setStatus(ResponseCode.syserror);
            }finally {

            }
        }else
            response.setStatus(ResponseCode.tokenfail);
        return response;
    }
    @PostMapping("/logoutApi")
    @ResponseBody
    public Response logoutApi(HttpSession session){
        Response response=new Response();
        if((User)session.getAttribute("user")!=null){
            session.removeAttribute("user");
            response.setStatus(ResponseCode.normal);
        }else {
            response.setStatus(ResponseCode.tokenfail);
        }
        return response;
    }
    @PostMapping("/getUserByUsernameApi")
    @ResponseBody
    public Response getUserByUsername(String username){
        Response response=new Response();
        User user=null;
        if((user=userMapper.selectByPrimaryKey(username))!=null){
            response.setStatus(ResponseCode.normal);
            response.setData(user);
        }
        else
            response.setStatus(ResponseCode.accountnotexist);
        return response;
    }
}

