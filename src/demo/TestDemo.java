package demo;

public class TestDemo {

    @MyTestUtil
    public void suanShu(){
        System.out.println("1234567890");
    }
    @MyTestUtil
    public void jiafa(){
        System.out.println("1+1="+(1+1));
    }
    @MyTestUtil
    public void jianfa(){
        System.out.println("1-1="+(1-1));
    }

    @MyTestUtil
    public void chufa(){
        System.out.println("6 / 0="+ 6 / 0);
    }

    @MyTestUtil
    public void chengfa(){
        System.out.println("3 x 5="+ 3*5);
    }

    public void ziwojieshao(){
        System.out.println("我写的程序没有 bug!");
    }

}
