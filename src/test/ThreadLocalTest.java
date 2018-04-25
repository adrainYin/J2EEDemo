package test;

public class ThreadLocalTest {
    /**
     * 定义ThreadLocal类对象，并且覆写初始化方法，每次初始化时候将value设置为0
     * 方法详解：
     * 每一个Thread维护一个ThreadLocalMap
     * ThreadLocalMap用当前的ThreadLocal实例作为key用具体的value值作为value
     */
    //在这个例子里面，每一个Thread的ThreadLocalMap维护了两个map
    //一个是local,另一个就是stringBufferLocal
    private static final ThreadLocal<Integer> local = ThreadLocal.withInitial(() -> 0);
    private static final ThreadLocal<StringBuffer> stringBufferLocal = ThreadLocal.withInitial(() -> new StringBuffer("hello"));

    /**
     * 定义静态的内部类
     */
    static class MyThread implements Runnable{
        private int index;
        public MyThread(int index){
            this.index = index;
        }
        @Override
        public void run() {
            //防止线程执行过快，所以加入睡眠
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程" + index + "的初始值为" + local.get());
            for (int i = 0; i < 10; i++) {
                local.set(local.get() + i);
            }
            System.out.println("线程" + index + "的累加值为" + local.get());

            System.out.println("线程" + index + "的初始值为" + stringBufferLocal.get());
            for (int i = 0; i < 10; i++) {
                stringBufferLocal.set(stringBufferLocal.get().append("a"));
            }
            System.out.println("线程" + index + "的累加值为" + stringBufferLocal.get());
        }
    }
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new Thread(new MyThread(i)).start();
        }
    }
}
