package cn.fiaojiashu.content.service.impl;

import cn.fiaojiashu.common.pojo.EasyUITreeNode;
import cn.fiaojiashu.common.util.FiaoJiaShuResult;
import cn.fiaojiashu.content.service.ContentCategoryService;
import cn.fiaojiashu.mapper.TbContentCategoryMapper;
import cn.fiaojiashu.pojo.TbContentCategory;
import cn.fiaojiashu.pojo.TbContentCategoryExample;
import cn.fiaojiashu.pojo.TbContentCategoryExample.Criteria;
import cn.fiaojiashu.pojo.TbContentExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName: ContentCategoryServiceImpl
 * @Date: 2020/3/17 20:01
 * @Description:内容分类管理Service
 */
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

    @Autowired
    private TbContentCategoryMapper contentCategoryMapper;

    @Override
    public List<EasyUITreeNode> getContentCatList(long parentId) {
        //根据parentid查询子节点列表
        TbContentCategoryExample example = new TbContentCategoryExample();
        Criteria criteria = example.createCriteria();
        //设置查询条件
        criteria.andParentIdEqualTo(parentId);
        //执行查询
        List<TbContentCategory> catList = contentCategoryMapper.selectByExample(example);
        //转换成EasyUITreeNode列表
        List<EasyUITreeNode> nodeList = new ArrayList<>();
        for (TbContentCategory tbContentCategory : catList) {
            EasyUITreeNode node = new EasyUITreeNode();
            node.setId(tbContentCategory.getId());
            node.setText(tbContentCategory.getName());
            node.setState(tbContentCategory.getIsParent() ? "closed" : "open");
            //添加到列表
            nodeList.add(node);
        }
        return nodeList;
    }

    @Override
    public FiaoJiaShuResult addContentCategory(long parentId, String name) {
        //创建一个tb_content_category表对应的pojo对象
        TbContentCategory contentCategory = new TbContentCategory();
        //设置pojo属性
        contentCategory.setParentId(parentId);
        contentCategory.setName(name);
        //1(正常),2(删除)
        contentCategory.setStatus(1);
        //默认排序就是1
        contentCategory.setSortOrder(1);
        //新添加的节点一定是叶子节点
        contentCategory.setIsParent(false);
        contentCategory.setCreated(new Date());
        contentCategory.setUpdated(new Date());
        //插入到数据库
        contentCategoryMapper.insert(contentCategory);
        //判断父节点的isparent属性,如果不是true改为true
        //根据parentid查询父节点
        TbContentCategory parent = contentCategoryMapper.selectByPrimaryKey(parentId);
        if (!parent.getIsParent()) {
            parent.setIsParent(true);
            //更新父节点到数据库中
            contentCategoryMapper.updateByPrimaryKey(parent);
        }
        //返回结果,返回FiaoJiaShuResult，包含pojo
        return FiaoJiaShuResult.ok(contentCategory);
    }

    @Override
    public FiaoJiaShuResult removeContentCategory(long id) {
        //根据id查询该节点
        TbContentCategory contentCategory = contentCategoryMapper.selectByPrimaryKey(id);
        if (contentCategory == null) {
            return FiaoJiaShuResult.build(201, "该节点不存在");
        }
        //删除内容分类
        contentCategoryMapper.deleteByPrimaryKey(id);
        //取出父节点id
        long parentId = contentCategory.getParentId();
        //判断父节点下面还有没有子节点
        TbContentCategoryExample example = new TbContentCategoryExample();
        Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        List<TbContentCategory> contentCategories = contentCategoryMapper.selectByExample(example);
        if (contentCategories == null || contentCategories.size() == 0) {
            //如果没有则父节点的isparent属性改为false
            //根据id查询父节点
            TbContentCategory parent = new TbContentCategory();
            parent.setId(parentId);
            parent.setIsParent(false);
            //更新父节点到数据库中
            contentCategoryMapper.updateByPrimaryKeySelective(parent);
        }
        //返回结果
        return FiaoJiaShuResult.ok();
    }

    @Override
    public FiaoJiaShuResult renameContentCategory(long id, String name) {
        //创建一个对象
        TbContentCategory contentCategory = new TbContentCategory();
        //修改属性
        contentCategory.setId(id);
        contentCategory.setUpdated(new Date());
        contentCategory.setName(name);
        //更新到数据库
        contentCategoryMapper.updateByPrimaryKeySelective(contentCategory);
        //返回结果
        return FiaoJiaShuResult.ok();
    }
}
