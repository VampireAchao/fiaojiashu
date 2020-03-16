package cn.fiaojiashu.common.pojo;

import java.io.Serializable;

/**
 * @ClassName: EasyUITreeNode
 * @Date: 2020/3/15 13:57
 * @Description:商品分类的pojo
 */
public class EasyUITreeNode implements Serializable {
    private long id;
    private String text;
    private String state;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
