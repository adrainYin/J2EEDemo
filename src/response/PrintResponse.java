package response;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class PrintResponse extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//数据输出流的使用

        //        resp.setCharacterEncoding("UTF-8");
//        String data = "this is a testing demo for response servlet";
        /**
         * 在resp实例化的过程中，输出流已经实例化，所以只需要调用get方法即可获得
         */
//        OutputStream servletOutputStream  = resp.getOutputStream();
//        servletOutputStream.write(data.getBytes());
        //取得请求参数的文件名
        String filename = req.getParameter("filename");
        //得到文件在服务器上设置的类型
        String type = this.getServletContext().getMimeType(filename);
        //设置响应参数传递的实体内容的类型和设置处理数据的方式和文件的路径
        resp.setHeader("content-Type" , type);
        resp.setHeader("Content-Disposition" , "attachment;filename=" + filename);
        /**
         * 这里的输入流是将文件读取进服务器的内存中，用的是输入流
         */
        String realPath = this.getServletContext().getRealPath("/download/" + filename);
        //读进内存
        InputStream inputStream = new FileInputStream(realPath);
        /**
         * 下面是对客户端的输出，所以用输出流，并且用resp进行输出。这时候就需要对resp的响应头设置
         * (1) 设置文件的Mime类型
         * (2) 设置文件的数据处理方法和文件名
         * @return 返回一个输出流，用resp将文件以字节流的方式发送到客户端
         */
        OutputStream outputStream = resp.getOutputStream();
        int len = 0;
        byte[] data = new byte[1024];
        while((len = inputStream.read(data)) != -1){
            outputStream.write(data , 0 , len);
        }
        inputStream.close();




    }
}
