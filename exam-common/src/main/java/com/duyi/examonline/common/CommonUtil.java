package com.duyi.examonline.common;

import com.duyi.examonline.domain.vo.PageVO;
import com.github.pagehelper.PageInfo;

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

}
