package com.taotao.bean;

import java.util.List;

public class QueryResult {
    /*
     * 代表了json格式的总行数
     * 因为json 总是 {total：10，rows={"a":"aa","id":1....}}
     */
    private Long total;
    private List<?> rows;
    public Long getTotal() {
        return total;
    }
    public void setTotal(Long total) {
        this.total = total;
    }
    public List<?> getRows() {
        return rows;
    }
    public void setRows(List<?> rows) {
        this.rows = rows;
    }
    public QueryResult(Long total, List<?> rows) {
        super();
        this.total = total;
        this.rows = rows;
    }
    public QueryResult() {
        super();
    }
}
