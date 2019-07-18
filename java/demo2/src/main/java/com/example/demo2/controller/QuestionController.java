package com.example.demo2.controller;

import com.example.demo2.beans.*;
import com.example.demo2.dao.QuestionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class QuestionController {
    @Autowired
    QuestionMapper questionMapper;
    @PostMapping("/getAllQuestionByUsernameApi")
    @ResponseBody
    public Response getALLQuestionApi(HttpSession session){
        Response response=new Response();
        User user=null;
        List<Question> questions=null;
        if((user=(User)session.getAttribute("user"))!=null){
            questions=questionMapper.selectByUsername(user.getUsername());
            response.setData(questions);
            response.setStatus(ResponseCode.normal);
        }else{
            response.setStatus(ResponseCode.tokenfail);
        }
        return response;
    }
    @PostMapping("/addQuestionByUsernameApi")
    @ResponseBody
    public Response addQuestionApi(HttpSession session,Question question){
        Response response=new Response();
        User user=null;
        if(question.getQuestionContent()==null||question.getQuestionContent().equals("NaN")){
            response.setStatus("404");
            response.setMessage("问题内容为空");
            return response;
        }
        if((user=(User) session.getAttribute("user"))!=null){
            question.setQuestionVector(user.getUsername());
            question.setPublishTime(new Date());
            questionMapper.insert(question);
            response.setStatus(ResponseCode.normal);
            response.setData(question);
        }else {
            response.setStatus(ResponseCode.tokenfail);
        }
        return response;
    }
    @PostMapping("/getAllQuestionApi")
    @ResponseBody
    public Response getAllQuestion(){
        Response response=new Response();
        List<Question>questions;
        questions=questionMapper.selectAll();
        response.setData(questions);
        response.setStatus(ResponseCode.normal);
        return response;
    }
    @PostMapping("/getQuestionByIdApi")
    @ResponseBody
    public Response getQuestionById(int questionId){
        Response response=new Response();
        Question question=questionMapper.selectByPrimaryKey(questionId);
        if(question!=null){
            response.setData(question);
            response.setStatus(ResponseCode.normal);
        }else {
            response.setStatus(ResponseCode.syserror);
        }
        return response;
    }
    @PostMapping("/searchQuestionApi")
    @ResponseBody
    public Response searchQuestion(String key){
        Response response=new Response();
        List<String> regs=new ArrayList<>();
        for(String string:Split.split(key)){
            string='%'+string+'%';
            regs.add(string);
        }
        List<Question> questions=questionMapper.fuzzySearch(regs);
        response.setData(questions);
        response.setStatus(ResponseCode.normal);
        return response;
    }
}
