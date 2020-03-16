package cn.fiaojiashu.common.pojo;

import java.io.Serializable;

/**
 * @ClassName: EasyUITreeNode
 * @Date: 2020/3/15 13:57
 * @Description:商品分类的pojo
 */
public class EasyUITreeNode implements Serializable {
    private long id;            //分类id
    private String text;        //分类
    private String state;       //分类状态(父类?"closed":"open")

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
