package com.duyi.examonline.common;

import com.duyi.examonline.domain.Question;
import com.duyi.examonline.domain.vo.PageVO;
import com.duyi.examonline.domain.vo.QuestionVO;
import com.github.pagehelper.PageInfo;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

public final class CommonUtil {

    private CommonUtil(){}

    /**
     * 分页转换器，将mybatis分页插件中的pageInfo转换成前端回显所需要的PageVO
     * @return
     */
    public static PageVO pageCast(PageInfo info, Map condition){
        return new PageVO(
                info.getPageNum(),
                info.getPageSize(),
                info.getTotal(),
                info.getNavigateLastPage(),
                (int)info.getStartRow(),
                (int)info.getEndRow(),
                info.getList(),
                condition
            );
    }

    public static QuestionVO questionCast(Question question, int index){
        QuestionVO questionVO = new QuestionVO();
        //原来cache中有10条记录，当前这个就是第11个，序号也应该是11，恰好是cache.size()
        questionVO.setIndex( index );
        questionVO.setSubject( question.getSubject() );
        questionVO.setType( question.getType() );

        String[] optionArray = question.getOptions().split(CommonData.SPLIT_SEPARATOR);
        questionVO.setOptionList( Arrays.asList(optionArray) );

        String[] answerArray = question.getAnswer().split(CommonData.SPLIT_SEPARATOR);
        questionVO.setAnswerList( Arrays.asList(answerArray) );

        return questionVO ;
    }


    public static String getSystemDateString(String pattern){
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        return df.format(new Date());
    }

}
