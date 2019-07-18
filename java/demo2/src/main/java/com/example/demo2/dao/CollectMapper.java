package com.example.demo2.dao;

import com.example.demo2.beans.Collect;
import com.example.demo2.beans.Like;
import com.example.demo2.beans.Question;
import org.apache.ibatis.annotations.*;
import java.util.List;
@Mapper
public interface CollectMapper {
    @Insert( "insert into collect(collector,question_id) values(#{collector}, #{questionId})")
    int insert(Collect collect);

    @Delete("Delete from collect where collector=#{collector} and question_id=#{questionId}")
    int delete(Collect collect);

    @Select("select * from question,collect where collector=#{collector} and question.question_id=collect.question_id")
    @Results(
            value =
                    {
                            @Result(column = "question_content",property = "questionContent"),
                            @Result(column = "question_id", property = "questionId"),
                            @Result(column = "publish_time",property = "publishTime"),
                            @Result(column = "question_vector",property = "questionVector")
                    }

    )
    List<Question> select(String collector);
    @Select("select question_id from collect where question_id=#{questionId} and collector=#{collector}")
    @Results(
            value = {
                    @Result(column = "question_id",property = "questionId")
            }
    )
    Collect iscollect(Collect collect);
}
