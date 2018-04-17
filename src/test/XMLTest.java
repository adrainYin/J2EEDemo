package test;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import servlet.MyServlet;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XMLTest {
    private static Map<String,String> servletMap = new HashMap<>();
    public static void femoForXML() throws Exception {
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(new File("web/WEB-INF/web.xml"));

        Element rootElement = document.getRootElement();
        String version = rootElement.attributeValue("version");
        System.out.println(version);
        //取得所有的子元素节点
        //通过反射和解析xml文档获得反射类对象
//        Element firServlet = rootElement.element("servlet");
//        String servletClass = firServlet.elementText("servlet-class");
//        Class cls = Class.forName(servletClass);
//        MyServlet myServlet = (MyServlet) cls.newInstance();
//        myServlet.start();
//        myServlet.run();
//        myServlet.end();

        //使用完整的map映射，将url地址映射到class类中
        List<Element> elementList = rootElement.elements();
        for (Element element : elementList){
            String elementName = element.getName();
            if (elementName.equals("servlet")){
                String servletClass = element.elementText("servlet-class");
                String servletName = element.elementText("servlet-name");
                servletMap.put(servletName , servletClass);
            }
            if (elementName.equals("servlet-mapping")){
                String servletName = element.elementText("servlet-name");
                String urlPatten = element.elementText("url-pattern");
                String servletClass = servletMap.get(servletName);
                servletMap.put(urlPatten , servletClass);
                //这个时候url已经和servlerClass形成了映射关系，所以要移除
                servletMap.remove(servletName);
            }
        }
        System.out.println(servletMap);
    }

    public static void main(String[] args) {
        String url = "/hello";
        //String servletClass = servletMap.get(url);
        try {
            XMLTest.femoForXML();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String servletClass = servletMap.get(url);

        try {
            Class cls = Class.forName(servletClass);
            try {
                MyServlet myServlet = (MyServlet)cls.newInstance();
                myServlet.start();
                myServlet.run();
                myServlet.end();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
