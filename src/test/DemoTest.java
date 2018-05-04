package test;

import net.sf.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class DemoTest {

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("b001", "books");
        map.put("b002", "clothes");

        System.out.println(JSONObject.fromObject(map).toString());
    }
}
