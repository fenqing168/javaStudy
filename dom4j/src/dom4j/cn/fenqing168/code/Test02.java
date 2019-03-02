package dom4j.cn.fenqing168.code;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXWriter;
import org.dom4j.io.XMLWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class Test02 {

    public static void main(String[] args) throws IOException {

        Document documentHelper = DocumentHelper.createDocument();
        Element root = documentHelper.addElement("scores");
        Element student = root.addElement("student");
        student.addAttribute("id", "1");
        Element name = student.addElement("name");
        name.addText("fenqing");
        Element subject = student.addElement("subject");
        student.addText("java");
        Element sroce = student.addElement("sroce");
        sroce.addText("90");

        OutputFormat format = OutputFormat.createPrettyPrint();
        Writer writer = new FileWriter(new File("C:\\Users\\Administrator\\Desktop\\web.xml"));
        XMLWriter xmlWriter = new XMLWriter(writer, format);
        xmlWriter.write(documentHelper);
        writer.close();
        xmlWriter.close();
    }

}
