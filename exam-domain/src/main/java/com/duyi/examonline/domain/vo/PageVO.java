package com.duyi.examonline.domain.vo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 装载自定义分页展示数据
 */
public class PageVO implements Serializable {
    private int curr ;
    private int rows ;
    private long total ;
    private int max ;
    private int start ;
    private int end ;
    private List<?> data ;
    private Map<String,Object> condition ;

    public int getStartPage(){
        if(curr > 2 && curr + 2 < max){
            return curr-2 ;
        }else if(curr <= 2){
            return 1;
        }else{
            //curr + 2>=max
            return max-5+1;
        }
    }
    public int getEndPage(){
        if(curr > 2 && curr + 2 < max){
            return curr+2 ;
        }else if(curr <= 2){
            return 5;
        }else{
            //curr + 2>=max
            return max ;
        }
    }

    public int getCurr() {
        return curr;
    }

    public void setCurr(int curr) {
        this.curr = curr;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }

    public Map<String, Object> getCondition() {
        return condition;
    }

    public void setCondition(Map<String, Object> condition) {
        this.condition = condition;
    }

    public PageVO(int curr, int rows, long total, int max, int start, int end, List<?> data, Map<String, Object> condition) {
        this.curr = curr;
        this.rows = rows;
        this.total = total;
        this.max = max;
        this.start = start;
        this.end = end;
        this.data = data;
        this.condition = condition;
    }

    public PageVO() {
    }
}
