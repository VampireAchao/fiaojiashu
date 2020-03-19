package cn.fiaojiashu.search.service;

import cn.fiaojiashu.common.pojo.SearchResult;

/**
 * @ClassName: SearchService
 * @Date: 2020/3/19 20:56
 * @Description:商品查询接口(索引)
 */
public interface SearchService {
    SearchResult search(String keyword, int page,int rows) throws Exception;
}
