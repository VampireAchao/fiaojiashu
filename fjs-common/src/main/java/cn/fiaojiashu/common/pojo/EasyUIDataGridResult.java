package cn.fiaojiashu.common.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: EasyUIDataGridResult
 * @Date: 2020/3/14 16:17
 * @Description:分页查询的pojo
 */
public class EasyUIDataGridResult implements Serializable {
    private long total;     //共多少条数据
    private List rows;      //当前页数据结果List

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }
}
