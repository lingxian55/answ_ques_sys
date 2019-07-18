package com.example.demo2.beans;

import java.util.Date;

public class Like {
    private String publisher;
    private Integer answerId;
    private String vector;
    private Date publishTime;
    public Like() {
    }
    public Like(String publisher, Integer answerId, String vector) {
        this.publisher = publisher;
        this.answerId = answerId;
        this.vector = vector;
        this.publishTime=new Date();
    }
    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }
    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Integer getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Integer answerId) {
        this.answerId = answerId;
    }

    public String getVector() {
        return vector;
    }

    public void setVector(String vector) {
        this.vector = vector;
    }
}