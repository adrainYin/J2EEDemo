package servlet;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class VerifyCodeServlet  extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /**
         * 绘制验证码图片
          */
        int height = 30;
        int weight = 60;
        String data = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random random = new Random();
        //初始化一个图片缓存
        BufferedImage bufferedImage = new BufferedImage(weight , height , BufferedImage.TYPE_INT_BGR);
        Graphics graphics = bufferedImage.getGraphics();
        //设置图片的填充域
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0  ,0 , weight , height);
        graphics.setColor(Color.WHITE);
        graphics.fillRect(1 ,1  , weight -2 , height - 2);
        graphics.setFont(new Font("宋体" , Font.BOLD|Font.ITALIC , 25));

        for (int i = 0; i < 4; i++) {
            graphics.setColor(new Color(new Random().nextInt(255), new Random().nextInt(255),
                              new Random().nextInt(255)));
            //截取到随机的字母
            int index  = new Random().nextInt(data.length());
            String string = data.substring(index , index + 1);
            //将响应的字母写入图片
            graphics.drawString(string , weight / 6 * (i +1) , 20);
        }

        for (int i = 0; i < 3; i++) {
            graphics.setColor(new Color(new Random().nextInt(255) , new Random().nextInt(255) ,
                              new Random().nextInt(255)));
            graphics.drawLine(random.nextInt(weight) , random.nextInt(height) ,
                              random.nextInt(weight) , random.nextInt(height));
            graphics.drawOval(random.nextInt(weight) , random.nextInt(height) , 2 ,2);

        }
        /**
         * 将图片的缓存对象，写到响应的输出流
         */
        ImageIO.write(bufferedImage , "jpg" , resp.getOutputStream());



    }
}
