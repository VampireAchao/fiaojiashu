package cn.fiaojiashu.common.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: SearchResult
 * @Date: 2020/3/19 20:35
 * @Description:索引查询结果pojo
 */
public class SearchResult implements Serializable {

    private long recordCount;   //总记录数
    private int totalPages;     //总页数
    private List<SearchItem> itemList;  //结果List

    public long getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(long recordCount) {
        this.recordCount = recordCount;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public List<SearchItem> getItemList() {
        return itemList;
    }

    public void setItemList(List<SearchItem> itemList) {
        this.itemList = itemList;
    }
}
