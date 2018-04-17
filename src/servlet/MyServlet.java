package servlet;

public class MyServlet implements HelloMyServlet{
    @Override
    public void start() {
        System.out.println("开始");
    }

    @Override
    public void run() {
        System.out.println("运行");
    }

    @Override
    public void end() {
        System.out.println("结束");
    }
}
