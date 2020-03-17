package cn.fiaojiashu.content.service.impl;

import cn.fiaojiashu.common.util.FiaoJiaShuResult;
import cn.fiaojiashu.content.service.ContentService;
import cn.fiaojiashu.mapper.TbContentMapper;
import cn.fiaojiashu.pojo.TbContent;
import cn.fiaojiashu.pojo.TbContentExample;
import cn.fiaojiashu.pojo.TbContentExample.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @ClassName: ContentServiceImpl
 * @Date: 2020/3/17 23:11
 * @Description:内容管理Service
 */
@Service
public class ContentServiceImpl implements ContentService {
    @Autowired
    private TbContentMapper contentMapper;

    /**
     * 内容添加
     *
     * @param content
     * @return
     */
    @Override
    public FiaoJiaShuResult addContent(TbContent content) {
        //将内容数据插入到内容表
        content.setCreated(new Date());
        content.setUpdated(new Date());
        contentMapper.insert(content);
        return FiaoJiaShuResult.ok();
    }

    /**
     * 根据内容分类id查询列表
     *
     * @param cid
     * @return
     */
    @Override
    public List<TbContent> getContentListByCid(long cid) {
        TbContentExample example = new TbContentExample();
        Criteria criteria = example.createCriteria();
        //设置查询条件
        criteria.andCategoryIdEqualTo(cid);
        //执行查询
        List<TbContent> list = contentMapper.selectByExampleWithBLOBs(example);
        return list;
    }
}
