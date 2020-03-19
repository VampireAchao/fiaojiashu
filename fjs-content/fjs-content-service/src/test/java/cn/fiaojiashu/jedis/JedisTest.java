package cn.fiaojiashu.jedis;

import org.junit.Test;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName: JedisTest
 * @Date: 2020/3/18 17:11
 * @Description:jedis测试类
 */
public class JedisTest {
/*
    @Test
    public void testJedis() throws Exception {
        //创建连接Jedis对象，参数：host、port
        Jedis jedis = new Jedis("192.168.43.214", 6379);
        //直接使用Jedis操作redis。所有jedis的命令都对应一个方法
        jedis.set("test1234", "my first jedis test");
        String string = jedis.get("test1234");
        System.out.println(string);
        //关闭连接
        jedis.close();
    }

    @Test
    public void testJedisPool() throws Exception {
        //创建一个连接池对象，两个参数host，port
        JedisPool jedisPool = new JedisPool("192.168.43.214", 6379);
        //从连接池获得一个连接，就是一个jedis对象
        Jedis jedis = jedisPool.getResource();
        //使用jedis操作redis
        String string = jedis.get("test123");
        System.out.println(string);
        //关闭连接,每次使用完毕后都要关闭。连接池回收资源
        jedis.close();
        //关闭连接池
        jedisPool.close();
    }

    @Test
    public void testJedisCluster() throws Exception {
        //创建一个JedisCluster对象。有一个参数叫nodes，是一个set类型。set中包含若干个hostAndPort对象
        Set<HostAndPort> nodes = new HashSet<>();
        nodes.add(new HostAndPort("192.168.43.214", 7001));
        nodes.add(new HostAndPort("192.168.43.214", 7002));
        nodes.add(new HostAndPort("192.168.43.214", 7003));
        nodes.add(new HostAndPort("192.168.43.214", 7004));
        nodes.add(new HostAndPort("192.168.43.214", 7005));
        nodes.add(new HostAndPort("192.168.43.214", 7006));
        JedisCluster jedisCluster = new JedisCluster(nodes);
        //直接使用JedisCluster对象操作redis
        jedisCluster.set("test1", "123");
        String string = jedisCluster.get("test1");
        System.out.println(string);
        //关闭JedisClustor
        jedisCluster.close();
    }
*/
}
