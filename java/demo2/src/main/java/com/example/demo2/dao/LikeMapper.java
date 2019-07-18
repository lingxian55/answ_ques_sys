package com.example.demo2.dao;

import com.example.demo2.beans.Like;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface LikeMapper {
    @Insert("insert into Liked(publisher,answerid,vector,publishtime) values(#{publisher},#{answerId},#{vector},#{publishTime})")
    int insert(Like like);

    @Select(value = "select count(*) from Liked where vector=#{username}" )
    Integer countByUsername(String username);

    @Delete("Delete from Liked where publisher=#{publisher} and answerid=#{answerId}")
    int delete(Like like);
    @Select("select question,publisher,liked.publishtime from Liked,answer where liked.answerid=answer.answer_id and vector=#{username}")
    @Results(
            value = {
                    @Result(column = "question",property = "answerId"),
            }
    )
    List<Like> getAllLikeByusername(String username);
    @Select("select answerid from liked where answerid=#{answerId} and publisher=#{publisher}")
    @Results(
            value = {
                    @Result(column = "answerid",property = "answerId")
            }
    )
    Like isLike(Like collect);
}
