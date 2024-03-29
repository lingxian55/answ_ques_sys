package com.example.demo2.beans;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Question {

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column question.question_id
     *
     * @mbg.generated Wed Jun 19 22:49:44 CST 2019
     */
    private Integer questionId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column question.publish_time
     *
     * @mbg.generated Wed Jun 19 22:49:44 CST 2019
     */

    private Date publishTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column question.question_vector
     *
     * @mbg.generated Wed Jun 19 22:49:44 CST 2019
     */
    private String questionVector;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column question.question_content
     *
     * @mbg.generated Wed Jun 19 22:49:44 CST 2019
     */
    private String questionContent;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column question.question_id
     *
     * @return the value of question.question_id
     *
     * @mbg.generated Wed Jun 19 22:49:44 CST 2019
     */
    public Integer getQuestionId() {
        return questionId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column question.question_id
     *
     * @param questionId the value for question.question_id
     *
     * @mbg.generated Wed Jun 19 22:49:44 CST 2019
     */
    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column question.publish_time
     *
     * @return the value of question.publish_time
     *
     * @mbg.generated Wed Jun 19 22:49:44 CST 2019
     */
    public Date getPublishTime() {
        return publishTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column question.publish_time
     *
     * @param publishTime the value for question.publish_time
     *
     * @mbg.generated Wed Jun 19 22:49:44 CST 2019
     */
    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column question.question_vector
     *
     * @return the value of question.question_vector
     *
     * @mbg.generated Wed Jun 19 22:49:44 CST 2019
     */
    public String getQuestionVector() {
        return questionVector;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column question.question_vector
     *
     * @param questionVector the value for question.question_vector
     *
     * @mbg.generated Wed Jun 19 22:49:44 CST 2019
     */
    public void setQuestionVector(String questionVector) {
        this.questionVector = questionVector == null ? null : questionVector.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column question.question_content
     *
     * @return the value of question.question_content
     *
     * @mbg.generated Wed Jun 19 22:49:44 CST 2019
     */
    public String getQuestionContent() {
        return questionContent;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column question.question_content
     *
     * @param questionContent the value for question.question_content
     *
     * @mbg.generated Wed Jun 19 22:49:44 CST 2019
     */
    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent == null ? null : questionContent.trim();
    }
}