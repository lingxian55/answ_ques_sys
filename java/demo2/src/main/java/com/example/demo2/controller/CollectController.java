package com.example.demo2.controller;

import com.example.demo2.beans.*;
import com.example.demo2.dao.CollectMapper;
import com.example.demo2.dao.QuestionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.jws.soap.SOAPBinding;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class CollectController {
    @Autowired
    CollectMapper collectMapper;
    @Autowired
    QuestionMapper questionMapper;
    @PostMapping("addCollectApi")
    @ResponseBody
    Response addCollect(Collect collect, HttpSession session){
        Response response=new Response();
        User user=null;
        if ((user = (User) session.getAttribute("user")) != null){
            collect.setCollector(user.getUsername());
            collectMapper.insert(collect);
            response.setStatus(ResponseCode.normal);
            response.setData(collect);
        }else {
            response.setStatus(ResponseCode.accountremoved);
        }
        return response;
    }
    @PostMapping("deleteCollectApi")
    @ResponseBody
    Response deleteCollect(Collect collect, HttpSession session){
        Response response=new Response();
        User user=null;
        if ((user = (User) session.getAttribute("user")) != null){
            collect.setCollector(user.getUsername());
            collectMapper.delete(collect);
            response.setStatus(ResponseCode.normal);
            response.setData(collect);
        }else {
            response.setStatus(ResponseCode.accountremoved);
        }
        return response;
    }
    @PostMapping("getQestionsByCollectorApi")
    @ResponseBody
    Response addCollect( HttpSession session) {
        Response response = new Response();
        User user=null;
        if ((user = (User) session.getAttribute("user")) != null){
           List<Question> collects= collectMapper.select(user.getUsername());
            response.setStatus(ResponseCode.normal);
            response.setData(collects);
        }else
            response.setStatus(ResponseCode.accountremoved);
        return response;
    }
    @PostMapping("isCollectApi")
    @ResponseBody
    Response isCollect( HttpSession session,Collect collect) {
        Response response = new Response();
        User user=null;
        if ((user = (User) session.getAttribute("user")) != null){
            response.setStatus(ResponseCode.normal);
            collect.setCollector(user.getUsername());
            if(collectMapper.iscollect(collect)!=null){
                response.setData(true);
            }else
                response.setData(false);

        }else
            response.setStatus(ResponseCode.accountremoved);
        return response;
    }
}
