package org.login.common.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class XmlUtil {
    public static String xmlChangeString(String fileName){
        try {
            SAXReader saxReader = new SAXReader();
            System.out.println("@@@@@@@@@@@@@@@@@@@" + XmlUtil.class.getClassLoader().toString());
            Document tempDocument = saxReader.read(XmlUtil.class.getClassLoader().getResourceAsStream(fileName));
            return tempDocument.asXML();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void strChangeXML(String str) throws IOException {
        SAXReader saxReader = new SAXReader();
        Document document;
        try {
        	document = saxReader.read(new ByteArrayInputStream(str.getBytes("UTF-8")));
        	OutputFormat format = OutputFormat.createPrettyPrint();
        	XMLWriter writer = new XMLWriter(new FileWriter(new File("src/com/webdesk/swing/powertable/digester/cctv.xml")),format);
        	writer.write(document);
        	writer.close();
        } catch (DocumentException e) {
        	e.printStackTrace();
        }
    }
}
