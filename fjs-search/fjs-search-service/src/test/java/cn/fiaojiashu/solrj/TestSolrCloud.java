package cn.fiaojiashu.solrj;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.CloudSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

/**
 * @ClassName: TestSolrCloud
 * @Date: 2020/3/20 17:21
 * @Description:
 */
public class TestSolrCloud {
/*
    @Test
    public void testAddDocument() throws Exception {
        //创建一个集群的连接，应该使用CloudSolrServer创建
        CloudSolrServer solrServer = new CloudSolrServer("192.168.43.215:2181,192.168.43.215:2182,192.168.43.215:2183");
        //zkHost:zookeeper的地址列表
        //设置一个defaultCollection属性
        solrServer.setDefaultCollection("collection2");
        //创建一个文档对象
        SolrInputDocument document = new SolrInputDocument();
        //向文档中添加域
        document.addField("id", "solrcloud01");
        document.setField("item_title", "测试商品01");
        document.setField("item_price", 200);
        //把文档写入索引库
        solrServer.add(document);
        //提交
        solrServer.commit();
    }
*/

    /*@Test
    public void testQueryDocument() throws Exception {
        //创建一个CloudSolrServer对象
        CloudSolrServer solrServer = new CloudSolrServer("192.168.43.215:2181,192.168.43.215:2182,192.168.43.215:2183");
        //设置默认的Collection
        solrServer.setDefaultCollection("collection2");
        //创建一个查询对象
        SolrQuery query = new SolrQuery();
        //设置查询条件
        query.setQuery("*:*");
        //执行查询
        QueryResponse queryResponse = solrServer.query(query);
        //取查询结果
        SolrDocumentList results = queryResponse.getResults();
        //打印
        System.out.println("总记录数" + results.getNumFound());
        for (SolrDocument result : results) {
            System.out.println(result.get("id"));
            System.out.println(result.get("title"));
            System.out.println(result.get("item_title"));
            System.out.println(result.get("item_price"));
        }
    }*/
}
