package com.duyi.examonline.domain.vo;

import java.io.Serializable;
import java.util.List;

/**
 * 添加静态模板时，对加入的试题回显信息的装载
 */
public class QuestionVO implements Serializable {
    /**页面显示的题号从1开始*/
    private int index ;

    private String type ;

    private String subject ;

    private List<String> optionList ;

    private List<String> answerList ;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public List<String> getOptionList() {
        return optionList;
    }

    public void setOptionList(List<String> optionList) {
        this.optionList = optionList;
    }

    public List<String> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(List<String> answerList) {
        this.answerList = answerList;
    }

    public QuestionVO(int index, String type, String subject, List<String> optionList, List<String> answerList) {
        this.index = index;
        this.type = type;
        this.subject = subject;
        this.optionList = optionList;
        this.answerList = answerList;
    }

    public QuestionVO() {
    }
}
