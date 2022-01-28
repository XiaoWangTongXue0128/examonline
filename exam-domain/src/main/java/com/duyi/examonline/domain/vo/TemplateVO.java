package com.duyi.examonline.domain.vo;

import com.duyi.examonline.domain.Teacher;
import com.duyi.examonline.domain.Template;

import java.util.List;

public class TemplateVO  extends Template{

    private Teacher teacher ;

    private List<Teacher> shareTeachers ;

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public List<Teacher> getShareTeachers() {
        return shareTeachers;
    }

    public void setShareTeachers(List<Teacher> shareTeachers) {
        this.shareTeachers = shareTeachers;
    }
}
