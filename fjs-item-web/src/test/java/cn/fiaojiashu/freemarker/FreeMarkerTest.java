package cn.fiaojiashu.freemarker;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: FreeMarkerTest
 * @Date: 2020/3/21 16:05
 * @Description:
 */
public class FreeMarkerTest {
    /*@Test
    public void testFreeMarker() throws Exception {
        //1、创建一个模板文件
        //2、创建一个Configuration对象
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_20);
        //3、设置模板文件保存目录
        configuration.setDirectoryForTemplateLoading(new File("C:/Users/1/IdeaProjects/fiaojiashu/fjs-item-web/src/main/webapp/WEB-INF/ftl"));
        //4、设置模板文件的编码格式，一般是utf-8
        configuration.setDefaultEncoding("utf-8");
        //5、加载一个模板文件，创建一个模板对象
        Template template = configuration.getTemplate("hello.ftl");
        //6、创建一个数据集，可以是pojo也可以是map,推荐map
        Map data = new HashMap<>();
        data.put("hello", "hello freemarker!");
        //7、创建一个Writer对象，指定输出文件的路径及文件名
        Writer out = new FileWriter("D:/freemarkerOutput/hello.txt");
        //8、生成静态页面
        template.process(data, out);
        //9、关闭流
        out.close();
    }*/
}
