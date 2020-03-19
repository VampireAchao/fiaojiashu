package cn.fiaojiashu.fast;

import cn.fiaojiashu.common.util.FastDFSClient;
import org.csource.fastdfs.*;
import org.junit.Test;

/**
 * @ClassName: FastDfsTest
 * @Date: 2020/3/16 15:29
 * @Description:
 */
public class FastDfsTest {
    /*@Test
    public void testUpload() throws Exception {
        //创建一个配置文件。文件名任意。内容就是tracker服务器的地址
        //使用全局对象加载配置文件。
        ClientGlobal.init("C:/Users/1/IdeaProjects/fiaojiashu/fjs-manager-web/src/main/resources/conf/client.conf");
        //创建一个trackerClient对象
        TrackerClient trackerClient = new TrackerClient();
        //通过trackerClient获得一个trackerServer对象
        TrackerServer trackerServer = trackerClient.getConnection();
        //创建一个StorageServer的引用，可以是null
        StorageServer storageServer = null;
        //创建一个StorageClient，参数需要StorageServer和TrackerServer
        StorageClient storageClient = new StorageClient(trackerServer, storageServer);
        //使用StrorageClient上传文件。
        String[] strings = storageClient.upload_file("C:/Users/1/Pictures/Camera Roll/myPC.PNG", "jpg", null);
        for (String string : strings) {
            System.out.println(string);
        }
    }*/

    @Test
    public void testFastDfsClient() throws Exception {
        FastDFSClient fastDFSClient = new FastDFSClient("C:/Users/1/IdeaProjects/fiaojiashu/fjs-manager-web/src/main/resources/conf/client.conf");
        String string = fastDFSClient.uploadFile("C:/Users/1/Desktop/study/video/qq_images/3a22a51002d457b5.gif");
        System.out.println(string);
    }
}
