package test;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailTest {


    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.setProperty("mail.host","smtp.126.com");
        properties.setProperty("mail.smtp.auth" , "true");

        //定义了邮件中的身份验证类对象，用来做邮件的身份确认
        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("adrain" , "123000");
            }
        };

        //和邮件服务器端建立连接
        Session session = Session.getDefaultInstance(properties , authenticator);

        //从这里开始就是编写邮件
        Message message = new MimeMessage(session);
        //发件人 , 收件人 , 邮件主题 , 邮件内容
        try {
            message.setFrom(new InternetAddress("Adrain@126.com"));
            message.setRecipient(Message.RecipientType.TO , new InternetAddress("ych@126.com"));
            message.setSubject("这是一封测试用的邮件");
            message.setContent("我是邮件的内容" , "text/html;charset=UTF-8");

            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }


    }
}
