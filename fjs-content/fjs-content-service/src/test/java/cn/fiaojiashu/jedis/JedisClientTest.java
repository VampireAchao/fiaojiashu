package cn.fiaojiashu.jedis;

import cn.fiaojiashu.common.jedis.JedisClient;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @ClassName: JedisClientTest
 * @Date: 2020/3/18 17:43
 * @Description:
 */
public class JedisClientTest {
    @Test
    public void testJedisClient() throws Exception {
        //初始化一个spring容器
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-redis.xml");
        //从容器中获得JedisClient对象
        JedisClient jedisClient = applicationContext.getBean(JedisClient.class);
        jedisClient.set("mytest", "jedisClient");
        String string = jedisClient.get("mytest");
        System.out.println(string);
    }
}
