package com.example.demo2.controller;

import com.example.demo2.beans.*;
import com.example.demo2.dao.AnswerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
public class AnswerController {
    @Autowired
    AnswerMapper answerMapper;
    @PostMapping("/getAllAnswerByUsernameApi")
    @ResponseBody
    public Response getALLAnswerApi(HttpSession session){
        Response response=new Response();
        User user=null;
        List<Answer> answers=null;
        if((user=(User)session.getAttribute("user"))!=null){
            answers=answerMapper.selectByUsername(user.getUsername());
            response.setData(answers);
            response.setStatus(ResponseCode.normal);
        }else{
            response.setStatus(ResponseCode.tokenfail);
        }
        return response;
    }
    @PostMapping("/addAnswerByUsernameApi")
    @ResponseBody
    public Response addAnswerByUsername(HttpSession session,Answer answer){
        Response response=new Response();
        User user=null;
        if((user=(User) session.getAttribute("user"))!=null){
            answer.setQuestionVector(user.getUsername());
            answer.setPublishTime(new Date());
            answerMapper.insert(answer);
            response.setStatus(ResponseCode.normal);
            response.setData(answer);
        }else {
            response.setStatus(ResponseCode.tokenfail);
        }
        return response;
    }
    @PostMapping("/getAllAnswerByQuestionIdApi")
    @ResponseBody
    public Response getAllAnswerByQuestionIdApi(Integer questionId){
        Response response=new Response();
        List<Answer> answers;
        if(questionId==null){
            response.setStatus(ResponseCode.syserror);
        }
        else {
            answers=answerMapper.selectByQuestionId(questionId);
            response.setStatus(ResponseCode.normal);
            response.setData(answers);

        }
        return response;
    }


}
