package response;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class CHineseDownloadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String filename = new String(req.getParameter("filename").getBytes("ISO-8859-1"),"UTF-8");
        String type = this.getServletContext().getMimeType(filename);
        resp.setHeader("Content-Type" , type);
        resp.setHeader("Content-Disposition" , "attachment;filename=" + filename);
        //设置输入流的realPath
        String realPath = this.getServletContext().getRealPath("/download/"+filename);
        //得到用户代理
        String agent = req.getHeader("User-Agent");
        //匹配字符串
        //根据不同的浏览器设置不同的编码和解码方式
        if (agent.contains("Chrome")){
        }
        InputStream inputStream = new FileInputStream(realPath);
        OutputStream outputStream = resp.getOutputStream();
        int len = 0;
        byte[] data = new byte[1024];
        while((len = inputStream.read(data)) != 0){
            outputStream.write(data , 0 , len);
        }
        inputStream.close();
    }
}
