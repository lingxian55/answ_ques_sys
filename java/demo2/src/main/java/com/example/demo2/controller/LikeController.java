package com.example.demo2.controller;

import com.example.demo2.beans.*;
import com.example.demo2.dao.LikeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

@Controller
public class LikeController {
    @Autowired
    LikeMapper likeMapper;
    @PostMapping("addLikeApi")
    @ResponseBody
    Response addLike(Like like, HttpSession session){
        Response response=new Response();
        User user=null;
        Date data=new Date();
        if ((user = (User) session.getAttribute("user")) != null){
            like.setPublisher(user.getUsername());
            like.setPublishTime(data);
            if(likeMapper.isLike(like)!=null){
                response.setStatus(ResponseCode.syserror);
            }else {
                response.setStatus(ResponseCode.normal);
                likeMapper.insert(like);
            }
            response.setStatus(ResponseCode.normal);
            response.setData(like);
        }else {
            response.setStatus(ResponseCode.accountremoved);
        }
        return response;
    }
    @PostMapping("deleteLikeApi")
    @ResponseBody
    Response deleteLike(Like like, HttpSession session){
        Response response=new Response();
        User user=null;
        if ((user = (User) session.getAttribute("user")) != null){
            like.setPublisher(user.getUsername());
            likeMapper.delete(like);
            response.setStatus(ResponseCode.normal);
            response.setData(like);
        }else {
            response.setStatus(ResponseCode.accountremoved);
        }
        return response;
    }
    @PostMapping("getQestionsByLikeApi")
    @ResponseBody
    Response getQestionsByLike(HttpSession session) {
        Response response = new Response();
        User user=null;
        if ((user = (User) session.getAttribute("user")) != null){
            List<Like> likes= likeMapper.getAllLikeByusername(user.getUsername());
            response.setStatus(ResponseCode.normal);
            response.setData(likes);
        }else
            response.setStatus(ResponseCode.accountremoved);
        return response;
    }
    @PostMapping("isLikeApi")
    @ResponseBody
    Response isLike( HttpSession session,Like like) {
        Response response = new Response();
        User user=null;
        if ((user = (User) session.getAttribute("user")) != null){
            response.setStatus(ResponseCode.normal);
            like.setPublisher(user.getUsername());
            if(likeMapper.isLike(like)!=null){
                response.setData(true);
            }else
                response.setData(false);

        }else
            response.setStatus(ResponseCode.accountremoved);
        return response;
    }
}
