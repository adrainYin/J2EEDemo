package bean;

public class TestProduct {
    private String pid;
    private String pname;
    private String pinyin;

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    @Override
    public String toString() {
        return "TestProduct{" +
                "pid='" + pid + '\'' +
                ", pname='" + pname + '\'' +
                ", pinyin='" + pinyin + '\'' +
                '}';
    }
}
