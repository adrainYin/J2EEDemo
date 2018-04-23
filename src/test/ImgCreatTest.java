package test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.Buffer;
import java.util.Random;

public class ImgCreatTest {

    public static BufferedImage createBufferedImage(){
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

        return bufferedImage;
    }

    public static int storeImg(BufferedImage bufferedImage){
        try {
            ImageIO.write(bufferedImage , "jpg" , new File("src" + File.separator+ "temp.jpg"));
            return 1;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }


    public static void main(String[] args) {
        BufferedImage bufferedImage = ImgCreatTest.createBufferedImage();
        int mark = ImgCreatTest.storeImg(bufferedImage);
        if (mark != 0){
            System.out.println("验证码创建成功");
        }else {
            System.out.println("验证码创建失败");
        }
    }
}
