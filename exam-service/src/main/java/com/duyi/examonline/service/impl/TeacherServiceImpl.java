package com.duyi.examonline.service.impl;

import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.extra.pinyin.PinyinUtil;
import com.duyi.examonline.dao.TeacherMapper;
import com.duyi.examonline.domain.Teacher;
import com.duyi.examonline.service.TeacherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherMapper teacherMapper ;

    private Logger log = LoggerFactory.getLogger(TeacherServiceImpl.class) ;

    @Override
    public void save(Teacher teacher) {
        //生成教师名称对应的助记码 张三 zhangsan
        String mnemonicCode = PinyinUtil.getPinyin(teacher.getTname(), "");
        teacher.setMnemonicCode(mnemonicCode);

        //验证，确保教师名称和助记码是不重复的。利用数据库唯一性，也可以自己逻辑实现。

        //将密码进行加密：建议使用md5
        String pass = DigestUtil.md5Hex(teacher.getPass());
        teacher.setPass(pass);

        //teacher.setCreateTime(new Date());

        try{
            teacherMapper.insert(teacher);
        }catch(DuplicateKeyException e){
            //用户名或助记码重复
            log.info("名称或助记码重复");
        }

    }
}
