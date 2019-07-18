package com.example.demo2.beans;

import com.huaban.analysis.jieba.JiebaSegmenter;

import java.util.ArrayList;
import java.util.List;

public class Split {
    public static List<String> split(String text){
        List<String> strings=new ArrayList<>();
        JiebaSegmenter segmenter=new JiebaSegmenter();
        for (String string : segmenter.sentenceProcess(text)){
            if(string.length()>=2){
                strings.add(string);
            }
        }
        return strings;
    }
}
