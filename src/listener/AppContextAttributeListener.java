package listener;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;

public class AppContextAttributeListener implements ServletContextAttributeListener {
    @Override
    public void attributeAdded(ServletContextAttributeEvent servletContextAttributeEvent) {
        System.out.println("增加对象" + servletContextAttributeEvent.getName() + "-->" +
                          servletContextAttributeEvent.getValue());

    }

    @Override
    public void attributeRemoved(ServletContextAttributeEvent servletContextAttributeEvent) {
        System.out.println("删除对象" + servletContextAttributeEvent.getName() + "-->" +
                servletContextAttributeEvent.getValue());
    }

    @Override
    public void attributeReplaced(ServletContextAttributeEvent servletContextAttributeEvent) {
        System.out.println("修改对象" + servletContextAttributeEvent.getName() + "-->" +
                servletContextAttributeEvent.getValue());
    }
}
