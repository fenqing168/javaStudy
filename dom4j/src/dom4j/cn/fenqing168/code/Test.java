package dom4j.cn.fenqing168.code;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.Iterator;

/**
 * @author fenqing
 */
public class Test {

    public static void main(String[] args) throws DocumentException {

        //创建xml读取对象
        SAXReader saxReader = new SAXReader();

        //指定读取对象文件
        Document document = saxReader.read(Test.class.getClassLoader().getResource("score.xml"));

        //获取根元素
        Element root = document.getRootElement();

        //获取元素迭代器
        Iterator<?> iterator = root.elementIterator();

        while(iterator.hasNext()){

            Element z = (Element) iterator.next();
            Attribute a = z.attribute("id");
            System.out.println("元素" + z.getName() + "-->" + a.getName() + "=" + a.getValue());
            Element n = z.element("name");
            Element s = z.element("subject");
            Element score = z.element("score");
            System.out.println("元素" + n.getName() + ":text:" + n.getText());
            System.out.println("元素" + s.getName() + ":text:" + s.getText());
            System.out.println("元素" + score.getName() + ":text:" + score.getText());
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

        }
    }

}
